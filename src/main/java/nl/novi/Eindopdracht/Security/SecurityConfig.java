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
                .requestMatchers(HttpMethod.POST, "/users/create").permitAll()
                .requestMatchers(HttpMethod.POST, "/authenticate").permitAll()
                //----------------------------------------Endpoints Spark plug--------------------------------------
                .requestMatchers(HttpMethod.POST, "/parts/sparkPlugs/create").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/parts/sparkPlugs/find/{id}").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/parts/sparkPlugs/find/all").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/parts/sparkPlugs/update/amountOfParts/{id}").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/parts/sparkPlugs/update/price/{id}").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/parts/sparkPlugs/update/part-number/{id}").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/parts/sparkPlugs/delete/{id}").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/parts/sparkPlugs/delete/all").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                //----------------------------------------Endpoints tyres  --------------------------------------
                .requestMatchers(HttpMethod.POST, "/parts/tyres/create").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/parts/tyres/find/all").hasAnyRole("MECHANIC", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/parts/tyres/find/{id}").hasAnyRole("MECHANIC", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/parts/tyres/update/amountOfParts/{id}").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/parts/tyres/update/price/{id}").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/parts/tyres/update/part-number/{id}").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/parts/tyres/delete/{id}").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/parts/tyres/delete/all").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                //----------------------------------------Endpoints brakes  --------------------------------------
                .requestMatchers(HttpMethod.POST, "/parts/brakes/create").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/parts/brakes/find/all").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/parts/brakes/find/{id}").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/parts/brakes/update/amountOfParts/{id}").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/parts/brakes/update/price/{id}").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/parts/brakes/update/part-number/{id}").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/parts/brakes/delete/{id}").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/parts/brakes/delete/all").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                //----------------------------------------Endpoints inspection car  --------------------------------------
                .requestMatchers(HttpMethod.POST, "/car/create/car").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/inspection/find/{id}").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/inspection/find/all/").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/inspection/update/mileage/{id}").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/inspection/update/inspectionDate/{id}").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
               // .requestMatchers(HttpMethod.PUT, "/inspection/update/isFine/statusCar/{id}").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
              //  .requestMatchers(HttpMethod.PUT, "/inspection/update/isIncorrect/statusCar/{id}").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/inspection/update/carIsCorrect/{id}").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/inspection/delete/inspection/{id}").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/inspection/delete/all/inspections").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                //----------------------------------------Endpoints car repair   --------------------------------------

                .requestMatchers(HttpMethod.POST, "/carRepair/create/").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/carRepair/find/all").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/carRepair/find/{id}").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/carRepair/totalCost").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/carRepair/update/car-problem/{id}").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/carRepair/update/repair-date/{id}").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/carRepair/{carRepairId}/add/parts").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/carRepair/delete/{id}").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/carRepair/delete/all").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")

                //----------------------------------------Endpoints car    --------------------------------------
                .requestMatchers(HttpMethod.POST, "/inspection/create/").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/car/find/all-cars").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/car/find/car").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/car/find/owner").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/car/update/mileage").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/car/update/engineType").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/car//add/owner").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/car/delete").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/car/delete/all").hasAnyRole("BACK_OFFICE_EMPLOYEE", "ADMIN")
                .anyRequest().denyAll()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

}
