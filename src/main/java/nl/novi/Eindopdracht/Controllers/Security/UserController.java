package nl.novi.Eindopdracht.Controllers.Security;

import nl.novi.Eindopdracht.Exceptions.BadRequestException;
import nl.novi.Eindopdracht.Service.SecurityService.UserService;
import nl.novi.Eindopdracht.dto.input.UserDto;
import nl.novi.Eindopdracht.dto.output.UserOutputDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

    @RestController
    @RequestMapping(value = "/users")
    public class UserController {

        private final UserService userService;

        public UserController(UserService service) {
            this.userService = service;
        }


        @GetMapping
        public ResponseEntity<List<UserOutputDto>> getUsers() {

            List<UserOutputDto> userOutputDTOS = userService.getUsers();
            return ResponseEntity.ok().body(userOutputDTOS);
        }

        @GetMapping(value = "/{username}")
        public ResponseEntity<UserOutputDto> getUser(@PathVariable("username") String username) {
            UserOutputDto optionalUser = userService.getUser(username);
            return ResponseEntity.ok().body(optionalUser);
        }
        @GetMapping(value = "/{username}/authorities")
        public ResponseEntity<Object> getAuthority(@PathVariable String username) {
            return ResponseEntity.ok().body(userService.getAuthorities(username));
        }


        @PostMapping("/create")
        public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
            String newUsername = userService.createUser(userDto);
            userService.addAuthority(newUsername, "ROLE_USER");


            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{username}")
                    .buildAndExpand(userDto.username)
                    .toUri();


            return ResponseEntity.created(location).build();
        }
        @PostMapping(value = "/{username}/authorities")
        public ResponseEntity<Object> addUserAuthority(@PathVariable("username") String username, @RequestBody Map<String, Object> fields) {
            try {
                String autorityName = (String) fields.get("authorities");
                userService.addAuthority(username, autorityName);
                return ResponseEntity.noContent().build();
            } catch (Exception ex) {
                throw new BadRequestException();
            }
        }
        @PutMapping(value = "/{username}")
        public ResponseEntity<String> updateUser(@PathVariable("username") String username, @RequestBody UserDto dto) {

            userService.updateUser(username, dto);

            return ResponseEntity.ok().body("user updated");
        }

        @DeleteMapping(value = "/{username}")
        public ResponseEntity<Object> deleteUser(@PathVariable("username") String username) {
            userService.deleteUser(username);
            return ResponseEntity.noContent().build();
        }

        @DeleteMapping(value = "/{username}/authorities/{authority}")
        public ResponseEntity<Object> deleteAuthority(@PathVariable("username") String username, @PathVariable("authority") String autority) {
            userService.removeAuthority(username, autority);
            return ResponseEntity.noContent().build();
        }


    }

