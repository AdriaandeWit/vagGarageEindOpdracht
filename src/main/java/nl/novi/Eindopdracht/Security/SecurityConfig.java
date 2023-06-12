package nl.novi.Eindopdracht.Security;

import nl.novi.Eindopdracht.Service.SecurityService.CustomerUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    public CustomerPasswordEncoder passwordEncoder;
    public final CustomerUserDetailsService customUserDetailsService;



    private final JwtRequestFilter jwtRequestFilter;



    public SecurityConfig(CustomerUserDetailsService customUserDetailsService, JwtRequestFilter jwtRequestFilter) {

        this.customUserDetailsService = customUserDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }
//    private final JwtRequestFilter jwtRequestFilter;
//    private final CustomerUserDetailsService customerUserDetailsService;
//
//
//
//    public SecurityConfig(JwtRequestFilter jwtRequestFilter, CustomerUserDetailsService customerUserDetailsService, CustomerPasswordEncoder passwordEncoder) {
//        this.jwtRequestFilter = jwtRequestFilter;
//        this.customerUserDetailsService = customerUserDetailsService;
//        this.passwordEncoder = passwordEncoder;
//    }

    @Bean //Authenticatie met customUserDatailService en passwordEncoder
    public AuthenticationManager authManager(HttpSecurity http, PasswordEncoder passwordEncoder, CustomerUserDetailsService customerUserDetailsService) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(customerUserDetailsService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }


    @Bean //Aithorizatie met jwt
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .httpBasic().disable()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/users/create").permitAll()
                .requestMatchers(HttpMethod.POST, "/authenticate").permitAll()
                .requestMatchers(HttpMethod.PUT, "/carRepair/**").hasAnyRole("MECHANIC", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/inspection/**").hasAnyRole("MECHANIC", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/customer/**").hasAnyRole("MECHANIC", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/customer/**").hasAnyRole("MECHANIC", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/parts/**").hasAnyRole("MECHANIC", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/parts/sparkPlugs/find/{id}").hasAnyRole("MECHANIC", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/parts/sparkPlugs/find/all").hasAnyRole("MECHANIC", "ADMIN")
                .requestMatchers(HttpMethod.POST, "/car").hasAnyRole("SERVICE_SPECIALIST", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/car/find/**").hasAnyRole("SERVICE_SPECIALIST", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/car").hasAnyRole("SERVICE_SPECIALIST", "ADMIN")
                .requestMatchers(HttpMethod.POST, "/customer/**").hasAnyRole("SERVICE_SPECIALIST", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/customer/**").hasAnyRole("SERVICE_SPECIALIST", "ADMIN")
                .requestMatchers(HttpMethod.POST, "/parts").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/parts/**").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/parts/**").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .anyRequest().denyAll()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

}
