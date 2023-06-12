package nl.novi.Eindopdracht.Security;

import nl.novi.Eindopdracht.Service.SecurityService.JwtService;
import nl.novi.Eindopdracht.Service.SecurityService.MyUserDetailService;
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
    private final JwtService jwtService;
    private final MyUserDetailService myUserDetailService;

    public final PasswordEncoder passwordEncoder;

    public SecurityConfig(JwtService service, MyUserDetailService myUserDetailService,PasswordEncoder passwordEncoder) {
        this.jwtService = service;
        this.myUserDetailService = myUserDetailService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean //Authenticatie met customUserDatailService en passwordEncoder
    public AuthenticationManager authManager(HttpSecurity http, PasswordEncoder passwordEncoder, MyUserDetailService myUserDetailService) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(myUserDetailService)
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
                .requestMatchers(HttpMethod.POST,"/authenticate").permitAll()
                .requestMatchers(HttpMethod.PUT, "/carRepair/**").hasAnyRole("MECHANIC","ADMIN")
                .requestMatchers(HttpMethod.PUT, "/inspection/**").hasAnyRole("MECHANIC","ADMIN")
                .requestMatchers(HttpMethod.GET, "/customer/**").hasAnyRole("MECHANIC","ADMIN")
                .requestMatchers(HttpMethod.GET, "/customer/**").hasAnyRole("MECHANIC","ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/parts/**").hasAnyRole("MECHANIC","ADMIN")
                .requestMatchers(HttpMethod.POST, "/car").hasAnyRole("SERVICE_SPECIALIST","ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/car").hasAnyRole("SERVICE_SPECIALIST","ADMIN")
                .requestMatchers(HttpMethod.POST,"/customer/**").hasAnyRole("SERVICE_SPECIALIST","ADMIN")
                .requestMatchers(HttpMethod.PUT,"/customer/**").hasAnyRole("SERVICE_SPECIALIST","ADMIN")
                .requestMatchers(HttpMethod.POST, "/parts").hasAnyRole("BACK_OFFICE_EMPLOYEE","ADMIN")
                .requestMatchers(HttpMethod.PUT, "/parts/**").hasAnyRole("BACK_OFFICE_EMPLOYEE","ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/parts/**").hasAnyRole("BACK_OFFICE_EMPLOYEE","ADMIN")
                .anyRequest().denyAll()
                .and()
                .addFilterBefore(new JwtRequestFilter(myUserDetailService, jwtService), UsernamePasswordAuthenticationFilter.class)

                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        return http.build();
    }

}
