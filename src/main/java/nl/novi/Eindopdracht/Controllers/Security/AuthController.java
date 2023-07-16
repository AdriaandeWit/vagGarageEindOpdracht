package nl.novi.Eindopdracht.Controllers.Security;


import nl.novi.Eindopdracht.Service.SecurityService.JwtService;
import nl.novi.Eindopdracht.Service.SecurityService.CustomUserDetailsService;
import nl.novi.Eindopdracht.payload.AuthenticationRequest;
import nl.novi.Eindopdracht.payload.AuthenticationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin
@RestController
public class AuthController {

    private final AuthenticationManager authManager;

    private final CustomUserDetailsService customUserDetailsService;

    private final JwtService jwtService;

    public AuthController(AuthenticationManager man, JwtService service, CustomUserDetailsService customUserDetailsService) {
        this.authManager = man;
        this.customUserDetailsService = customUserDetailsService;
        this.jwtService = service;
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();

        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
        } catch (BadCredentialsException ex) {
            throw new Exception("Incorrect username or password", ex);
        }

        final UserDetails userDetails = customUserDetailsService
                .loadUserByUsername(username);

        final String jwt = jwtService.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}
