package nl.novi.Eindopdracht.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerAccountDto {

    public Long id;
    @NotBlank(message = "please enter a customer name ")
    public String customerName;
    @NotBlank(message = "please enter the firstname")@Pattern(regexp = "[A-Za-z]{2,15}")
    public String firstName;
    @NotBlank(message = "please enter the lastname ")
    public String lastName;
    @NotBlank(message = "I would like to have the following addresses, starting with the street name and then the house number. ")
    public String address;
    @NotBlank @Pattern(regexp = "[0-9]{1,3}-[0-9]{8,10}",message = "Can you provide a correct telephone number for example 06-12345678 ")
    public String phoneNumber;
    @NotBlank(message = "please enter a billing addres ")
    public String billingAddress;
    @NotBlank @Pattern(regexp = "[A-Z]{2}[0-9]{2}-[A-Z]{4}-[0-9]{2,4}-[0-9]{2,4}-[0-9]{2,4}",message = "Please enter the input in the specified format: NL21-INGB-001-2023-02 ")
    public String bankAccountNumber;


}
