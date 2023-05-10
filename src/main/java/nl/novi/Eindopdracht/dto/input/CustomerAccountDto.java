package nl.novi.Eindopdracht.dto.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerAccountDto {


    public static class CustomerAccountAllDto {
    public Long id;
    public String customerName;
    public String firstName;
    public String lastName;
    public String address;
    public String phoneNumber;
    public String billingAddress;
    public String bankAccountNumber;
}
    public static class CustomerNameDto {

        public String firstName;
        public String lastname;
    }
    public static class CustomerFinanceDto {

        public String billingAddress;
        public String bankAccountNumber;
    }

    public static class  CustomerAddressDto{

        public String Address;
        public String billingAddress;
    }

}
