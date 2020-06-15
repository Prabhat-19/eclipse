package com.payearly.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payearly.config.Constants;
import com.payearly.domain.Address;
import com.payearly.service.AddressService;
import com.payearly.service.util.HeaderUtil;
import com.payearly.web.rest.errors.BadRequestAlertException;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = Constants.API_URL)
public class AddressResource {

    private final Logger log = LoggerFactory.getLogger(AddressResource.class);

    private static final String ENTITY_NAME = "address";

    private final AddressService addressService;

    public AddressResource(AddressService addressService) {
        super();
        this.addressService = addressService;
    }

    /**
     * {@code POST  /address}  : Creates a new address.
     * <p>
     * Creates a new address
     *
     * @param scoreCardMaster the Address to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new Address, or with status {@code 400 (Bad Request)} 
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     * @throws BadRequestAlertException {@code 400 (Bad Request)}
     */
    
    @PostMapping("/address")
    public ResponseEntity<Address> createAddress(@Valid @RequestBody Address address) throws URISyntaxException {
        log.debug("REST request to save Address : {}", address);
        Address addresss = addressService.createAddress(address);
       
        return ResponseEntity.created(new URI("/api/addresss/" + addresss.getId()))
                    .headers(HeaderUtil.createAlert(ENTITY_NAME, "A addresss is created with identifier " + addresss.getId()))
                .body(addresss);

    }

    /**
     * {@code PUT /address} : Updates an existing address.
     *
     * @param entityMappings the address to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated address.
     * @throws URISyntaxException 
     */
    @PutMapping("/address")
    public ResponseEntity<Address> updateddress(@Valid @RequestBody Address address) throws URISyntaxException {
        log.debug("REST request to update Address : {}", address);
        if (address.getId() == null) {
            createAddress(address);
        }
        Address saveAddress = addressService.updateAddress(address);

        return ResponseEntity.created(new URI("/api/address/" + saveAddress.getId()))
                .headers(HeaderUtil.createAlert(ENTITY_NAME,  "A address is created with identifier " + saveAddress.getId()))
                .body(address);
    }

    /**
     * {@code GET /address} : get all address.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all entityMappings.
     */
    @GetMapping("/address")
    public ResponseEntity<List<Address>> getAllEntityMapping(Pageable pageable) {

        final Page<Address> page = addressService.getAddress(pageable);
    //   HttpHeders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page.getContent(), HttpStatus.OK);
    }

    /**
     * {@code GET /address} : get all address.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all address.
     */
    @GetMapping("/address/{id}")
    public ResponseEntity<Address> getEntityMapping(@PathVariable("id") UUID id) {
        Address  address = addressService.getAddress(id);
      
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

}
