package com.payearly.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payearly.config.Constants;
import com.payearly.domain.User;
import com.payearly.repository.UserRepository;
import com.payearly.service.UserService;
import com.payearly.service.exapi.TestFeginClient;
import com.payearly.service.util.HeaderUtil;
import com.payearly.web.rest.errors.BadRequestAlertException;
import com.payearly.web.rest.errors.EmailAlreadyUsedException;
import com.payearly.web.rest.errors.LoginAlreadyUsedException;

import springfox.documentation.spring.web.json.Json;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = Constants.API_URL)

public class UserResource {

    
    private final Logger log = LoggerFactory.getLogger(UserResource.class);

    @Value("${spring.application.name}")
    private String applicationName;

    private final UserService userService;

    private final UserRepository userRepository;
    
    private final TestFeginClient testFeginClient;


    public UserResource(UserService userService, UserRepository userRepository,
            TestFeginClient testFeginClient) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.testFeginClient = testFeginClient;
    }

    /**
     * {@code POST  /users}  : Creates a new user.
     * <p>
     * Creates a new user if the login and email are not already used, and sends an
     * mail with an activation link.
     * The user needs to be activated on creation.
     *
     * @param userDTO the user to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new user, or with status {@code 400 (Bad Request)} if the login or email is already in use.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     * @throws BadRequestAlertException {@code 400 (Bad Request)} if the login or email is already in use.
     */
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User userDTO) throws URISyntaxException {
        log.debug("REST request to save User : {}", userDTO);

        if (userDTO.getId() != null) {
            throw new BadRequestAlertException("A new user cannot already have an ID", "userManagement", "idexists");
            // Lowercase the user login before comparing with database
        } else if (userRepository.findOneByLogin(userDTO.getLogin().toLowerCase()).isPresent()) {
            throw new LoginAlreadyUsedException();
        } else if (userRepository.findOneByEmailIgnoreCase(userDTO.getEmail()).isPresent()) {
            throw new EmailAlreadyUsedException();
        } else {
            User newUser = userService.createUser(userDTO);
            return ResponseEntity.created(new URI("/api/users/" + newUser.getLogin()))
                .headers(HeaderUtil.createAlert(applicationName,  "A user is created with identifier " + newUser.getLogin()))
                .body(newUser);
        }
    }

    /**
     * {@code PUT /users} : Updates an existing User.
     *
     * @param userDTO the user to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated user.
     * @throws URISyntaxException 
     * @throws EmailAlreadyUsedException {@code 400 (Bad Request)} if the email is already in use.
     * @throws LoginAlreadyUsedException {@code 400 (Bad Request)} if the login is already in use.
     */
    @PutMapping("/users")
    public ResponseEntity<User> updateUser(@Valid @RequestBody User user) throws URISyntaxException {
        log.debug("REST request to update User : {}", user);
        Optional<User> existingUser = userRepository.findOneByEmailIgnoreCase(user.getEmail());
        if (existingUser.isPresent() && (!existingUser.get().getId().equals(user.getId()))) {
            throw new EmailAlreadyUsedException();
        }
        existingUser = userRepository.findOneByLogin(user.getLogin().toLowerCase());
        if (existingUser.isPresent() && (!existingUser.get().getId().equals(user.getId()))) {
            throw new LoginAlreadyUsedException();
        }
        User updatedUser = userService.updateUser(user);

        return ResponseEntity.created(new URI("/api/users/" + updatedUser.getLogin()))
                .headers(HeaderUtil.createAlert(applicationName,  "A user is created with identifier " + updatedUser.getLogin()))
                .body(updatedUser);
    }

    /**
     * {@code GET /users} : get all users.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all users.
     */
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(Pageable pageable) {
        
        final Page<User> page = userService.getAllManagedUsers(pageable);
       // HttpHeders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page.getContent(), HttpStatus.OK);
    }
//
//    @GetMapping("/user")
//    public void testUsers() {
//        
//        JSONObject jsons = testFeginClient.getUser();
//        System.out.println(">>>>>>>>>>>>>>"+ jsons);
}
