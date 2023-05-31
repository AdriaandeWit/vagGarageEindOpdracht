package nl.novi.Eindopdracht.Service.SecurityService;

import nl.novi.Eindopdracht.Models.Security.Authority;
import nl.novi.Eindopdracht.dto.output.UserOutputDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserService userService;

    public CustomUserDetailService(UserService userService) {
        this.userService =userService;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserOutputDto userOutputDTO = userService.getUser(username);

        String password = userOutputDTO.;

        Set<Authority> authorities = userOutputDTO.;
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Authority authority :authorities){
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
        }



        return new org.springframework.security.core.userdetails.User(username,password,grantedAuthorities);


}
