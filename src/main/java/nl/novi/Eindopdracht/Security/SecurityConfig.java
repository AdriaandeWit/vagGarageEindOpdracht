package nl.novi.Eindopdracht.Security;

import nl.novi.Eindopdracht.Service.SecurityService.CustomUserDetailsService;
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
    public final CustomUserDetailsService customUserDetailsService;

    private final JwtRequestFilter jwtRequestFilter;


    public SecurityConfig(CustomUserDetailsService customUserDetailsService, JwtRequestFilter jwtRequestFilter) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean //Authenticatie met customUserDatailService en passwordEncoder
    public AuthenticationManager authManager(HttpSecurity http, PasswordEncoder passwordEncoder, CustomUserDetailsService customUserDetailsService) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(customUserDetailsService)
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
                .requestMatchers("/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/users/create").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/authenticate").permitAll()
                //----------------------------------------Endpoints user --------------------------------------
                .requestMatchers(HttpMethod.GET,"/users{username}").permitAll()
                .requestMatchers(HttpMethod.GET, "/users").hasAnyRole("ADMIN", "MECHANIC", "SERVICE_SPECIALIST")
                .requestMatchers(HttpMethod.GET, "/users/{username}/authorities").hasAnyRole("ADMIN", "MECHANIC", "SERVICE_SPECIALIST")
                .requestMatchers(HttpMethod.POST, "/users/{username}/authorities").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/users/{username}").hasAnyRole("ADMIN", "SERVICE_SPECIALIST")
                .requestMatchers(HttpMethod.DELETE, "/users/{username}").hasRole("ADMIN")

                //----------------------------------------Endpoints Spark plug--------------------------------------
                .requestMatchers(HttpMethod.POST, "/parts/sparkPlugs/create").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/parts/sparkPlugs/find/{customerNumber}").hasAnyRole("MECHANIC","BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/parts/sparkPlugs/find/all").hasAnyRole("MECHANIC","BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/parts/sparkPlugs/update/amountOfParts/{customerNumber}").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/parts/sparkPlugs/update/price/{customerNumber}").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/parts/sparkPlugs/update/part-number/{customerNumber}").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/parts/sparkPlugs/delete/{customerNumber}").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/parts/sparkPlugs/delete/all").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                //----------------------------------------Endpoints tyres  --------------------------------------
                .requestMatchers(HttpMethod.POST, "/parts/tyres/create").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/parts/tyres/find/all").hasAnyRole("MECHANIC","BACK_OFFICE_EMPLOYEE" ,"ADMIN")
                .requestMatchers(HttpMethod.GET, "/parts/tyres/find/{customerNumber}").hasAnyRole("MECHANIC","BACK_OFFICE_EMPLOYEE" ,"ADMIN")
                .requestMatchers(HttpMethod.PUT, "/parts/tyres/update/amountOfParts/{customerNumber}").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/parts/tyres/update/price/{customerNumber}").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/parts/tyres/update/part-number/{customerNumber}").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/parts/tyres/delete/{customerNumber}").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/parts/tyres/delete/all").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                //----------------------------------------Endpoints brakes  --------------------------------------
                .requestMatchers(HttpMethod.POST, "/parts/brakes/create").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/parts/brakes/find/all").hasAnyRole("MECHANIC","BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/parts/brakes/find/{customerNumber}").hasAnyRole("MECHANIC","BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/parts/brakes/update/amountOfParts/{customerNumber}").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/parts/brakes/update/price/{customerNumber}").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/parts/brakes/update/part-number/{customerNumber}").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/parts/brakes/delete/{customerNumber}").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/parts/brakes/delete/all").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                //----------------------------------------Endpoints inspection car  --------------------------------------
                .requestMatchers(HttpMethod.POST, "/car/create/car").hasAnyRole("SERVICE_SPECIALIST", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/inspection/find/{customerNumber}").hasAnyRole("MECHANIC","SERVICE_SPECIALIST", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/inspection/find/all/").hasAnyRole("MECHANIC","SERVICE_SPECIALIST", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/inspection/update/mileage/{customerNumber}").hasAnyRole("MECHANIC", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/inspection/update/inspectionDate/{customerNumber}").hasAnyRole("MECHANIC", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/inspection/add/repair/{customerNumber}/{repairId}").hasAnyRole("MECHANIC", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/inspection/add/car/{inspectionId}").hasAnyRole("MECHANIC", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/inspection/update/carIsCorrect/{customerNumber}").hasAnyRole("MECHANIC", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/inspection/delete/inspection/{customerNumber}").hasAnyRole("SERVICE_SPECIALIST", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/inspection/delete/all/inspections").hasAnyRole("SERVICE_SPECIALIST", "ADMIN")
                //----------------------------------------Endpoints car repair   --------------------------------------

                .requestMatchers(HttpMethod.POST, "/carRepair/create/").hasAnyRole("MECHANIC","BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/carRepair/find/all").hasAnyRole("MECHANIC","SERVICE_SPECIALIST", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/carRepair/find/{customerNumber}").hasAnyRole("MECHANIC","SERVICE_SPECIALIST", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/carRepair/totalCost").hasAnyRole("MECHANIC","SERVICE_SPECIALIST", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/carRepair/update/car-problem/{customerNumber}").hasAnyRole("MECHANIC", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/carRepair/update/repair-date/{customerNumber}").hasAnyRole("MECHANIC", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/carRepair/{carRepairId}/add/parts").hasAnyRole("MECHANIC", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/carRepair/delete/{customerNumber}").hasAnyRole("SERVICE_SPECIALIST", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/carRepair/delete/all").hasAnyRole("SERVICE_SPECIALIST", "ADMIN")

                //----------------------------------------Endpoints car    --------------------------------------
                .requestMatchers(HttpMethod.POST, "/inspection/create/").hasAnyRole("MECHANIC","SERVICE_SPECIALIST", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/car/find/all-cars").hasAnyRole("MECHANIC","SERVICE_SPECIALIST", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/car/find/car").hasAnyRole("MECHANIC","SERVICE_SPECIALIST", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/car/find/owner").hasAnyRole("MECHANIC","SERVICE_SPECIALIST", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/car/update/mileage").hasAnyRole("MECHANIC", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/car/update/engineType").hasAnyRole("MECHANIC", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/car//add/owner").hasAnyRole("MECHANIC", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/car/delete").hasAnyRole("SERVICE_SPECIALIST", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/car/delete/all").hasAnyRole("SERVICE_SPECIALIST", "ADMIN")
                //----------------------------------------Endpoints CostumerAccount     --------------------------------------
                . requestMatchers(HttpMethod.POST, "/customer/create/").hasAnyRole("SERVICE_SPECIALIST", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/customer/find/all/").hasAnyRole("MECHANIC","SERVICE_SPECIALIST", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/customer/find/byId/").hasAnyRole("MECHANIC","SERVICE_SPECIALIST","ADMIN")
                .requestMatchers(HttpMethod.GET, "/customer/find/billing-address/").hasAnyRole("MECHANIC","SERVICE_SPECIALIST", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/customer/find/car/").hasAnyRole("MECHANIC","SERVICE_SPECIALIST","ADMIN")
                .requestMatchers(HttpMethod.PUT, "/customer/update/name/{customerNumber}").hasAnyRole("MECHANIC", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/customer/update/address/").hasAnyRole("MECHANIC", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/customer/delete/by-name/").hasAnyRole("SERVICE_SPECIALIST", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/customer/delete/all/").hasAnyRole("SERVICE_SPECIALIST", "ADMIN")
                .anyRequest().denyAll()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

}
