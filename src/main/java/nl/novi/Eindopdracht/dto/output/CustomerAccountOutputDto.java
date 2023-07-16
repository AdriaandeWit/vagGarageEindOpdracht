package nl.novi.Eindopdracht.dto.output;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerAccountOutputDto {


    public String customerName;
    public String firstName;
    public String lastName;

    public String address;
    public String phoneNumber;
    public String billingAddress;
    public String bankAccountNumber;



    public static class CustomerNameOutputDto {

        public String firstName;
        public String lastName;
    }


    public static class CustomerFinanceOutputDto{

        public String billingAddress;
        public String bankAccountNumber;
    }

    public static class CustomerAddressOutputDto {

        public String Address;
        public String billingAddress;
    }

}