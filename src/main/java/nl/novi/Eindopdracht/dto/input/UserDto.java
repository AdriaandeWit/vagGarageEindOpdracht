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
    @NotBlank @Pattern(regexp = "[a-zA-Z]{2,12}", message = "we need a user name and the username must be of 2 to 12 legth with no special characters")
    public String username;
    @NotBlank @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{4,12}$",
            message = "password must be min 4 and max 12 length containing atleast 1 uppercase, 1 lowercase, 1 special character and 1 digit " )
    public String password;
    @NotNull
    public Boolean enabled;
    public String apikey;
    @NotBlank @Email(message = "please enter a good structured email")
    public String email;
}
