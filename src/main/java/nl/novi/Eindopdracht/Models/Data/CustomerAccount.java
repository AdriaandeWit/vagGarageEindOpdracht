package nl.novi.Eindopdracht.Models.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @GeneratedValue
    private Long id;
    @Id
    private String customerName;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String billingAddress;
    private String bankAccountNumber;

    @OneToMany(mappedBy = "account")
    List<Car> cars;


    public CustomerAccount(Long id, String customerName, String firstName, String lastName, String address, String phoneNumber, String billingAddress, String bankAccountNumber) {
        this.id = id;
        this.customerName = customerName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.billingAddress = billingAddress;
        this.bankAccountNumber = bankAccountNumber;
    }
}
