package nl.novi.Eindopdracht.Controllers.Security;


import nl.novi.Eindopdracht.Service.SecurityService.JwtService;
import nl.novi.Eindopdracht.Service.SecurityService.MyUserDetailService;
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

    private final MyUserDetailService myUserDetailService;

    private final JwtService jwtService;

    public AuthController(AuthenticationManager man, JwtService service, MyUserDetailService myUserDetailService) {
        this.authManager = man;
        this.myUserDetailService = myUserDetailService;
        this.jwtService = service;
    }

    @GetMapping(value = "/authenticated")
    public ResponseEntity<Object> authenticated(Authentication authentication, Principal principal) {
        return ResponseEntity.ok().body(principal);
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

        final UserDetails userDetails = myUserDetailService
                .loadUserByUsername(username);

        final String jwt = jwtService.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}
