package nl.novi.Eindopdracht.dto.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    @NotBlank @Pattern(regexp = "[a-zA-Z]", message = "we need a user name")
    public String username;
    @NotBlank @Pattern(regexp = )
    public String password;
    @NotNull
    public Boolean enabled;
    public String apikey;
    @NotBlank @Email(message = "please enter a good structured email")
    public String email;
}
