package com.payearly.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
                   http.cors().and().csrf().disable()
                  .authorizeRequests().antMatchers("/api/authenticate").permitAll()
                  .antMatchers("/api/**").permitAll()
                                .and()
                                .httpBasic()
                                .authenticationEntryPoint(authenticationEntryPoint)
                                .and()
                                .sessionManagement()
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("PEMaker").password(passwordEncoder().encode("Welcome1")).roles("USER"); 
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(16);
    }
    
//    @Override
//    public void configure(WebSecurity web) {
//        web.ignoring()
//            .antMatchers(HttpMethod.OPTIONS, "/**")
//            .antMatchers("/app/**/*.{js,html}")
//            .antMatchers("/i18n/**")
//            .antMatchers("/content/**")
//            .antMatchers("/swagger-ui/index.html")
//            .antMatchers("/test/**");
//    }

}
