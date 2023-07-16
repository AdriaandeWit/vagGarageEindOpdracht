package nl.novi.Eindopdracht.Models.Data;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "customerAccounts", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"customerName"}),
        @UniqueConstraint(columnNames = {"phoneNumber"})
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerAccount {

    @Id
    private String customerName;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String billingAddress;
    private String bankAccountNumber;

    @OneToMany()
    @JoinColumn(name = "owner_id")
    List<Car> cars;


    public CustomerAccount(String customerName, String firstName, String lastName, String address, String phoneNumber, String billingAddress, String bankAccountNumber) {
        this.customerName = customerName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.billingAddress = billingAddress;
        this.bankAccountNumber = bankAccountNumber;
    }


}
