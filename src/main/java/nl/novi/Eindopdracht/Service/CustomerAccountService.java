package nl.novi.Eindopdracht.Service;

import lombok.AllArgsConstructor;
import nl.novi.Eindopdracht.Exceptions.AccountNotFoundException;
import nl.novi.Eindopdracht.Exceptions.CarNotFoundException;
import nl.novi.Eindopdracht.Exceptions.RecordNotFoundException;
import nl.novi.Eindopdracht.Models.Data.Car;
import nl.novi.Eindopdracht.Models.Data.CustomerAccount;
import nl.novi.Eindopdracht.Repository.CarRepository;
import nl.novi.Eindopdracht.Repository.CustomerAccountRepository;
import nl.novi.Eindopdracht.dto.input.CustomerAccountDto;
import nl.novi.Eindopdracht.dto.output.CarOutputDto;
import nl.novi.Eindopdracht.dto.output.CustomerAccountOutputDto;
import org.springframework.stereotype.Service;

import java.util.*;
@AllArgsConstructor
@Service
public class CustomerAccountService {


    private final CustomerAccountRepository cARepos;

    private final CarRepository carRepos;




    public Long createCostumer(CustomerAccountDto.CustomerAccountAllDto cADto) {
        CustomerAccount account = DtoToAccount(cADto);

        cARepos.save(account);
        return account.getId();
    }

    public List<CustomerAccountOutputDto> getAllCustomers() {
        List<CustomerAccountOutputDto> collection = new ArrayList<>();
        List<CustomerAccount> list = cARepos.findAll();
        for (CustomerAccount account : list) {
            collection.add(accountToDTo1(account));
        }
        return collection;
    }

    public CustomerAccountOutputDto getCustomerByCustomerName(String customerName) {
        Optional<CustomerAccount> account = cARepos.findAccountByCustomerName(customerName);
        if (account.isEmpty()) {
            throw new RecordNotFoundException("cannot find customer please enter a new carId ");
        } else {
            CustomerAccount a = account.get();
            return accountToDTo1(a);
        }
    }

    public CustomerAccountOutputDto.CustomerNameOutputDto getCustomerByLastName(String customerLastName) {
        Optional<List<CustomerAccount>> optionalAccounts = cARepos.findByLastName(customerLastName);
        if (!optionalAccounts.isPresent()) {
            throw new RecordNotFoundException("cannot find customer please enter a anther name");
        } else {
            CustomerAccount a = (CustomerAccount) optionalAccounts.get();
            return accountToDTo2(a);
        }

    }

    public CustomerAccountOutputDto.CustomerFinanceOutputDto getBillingAddressByCustomerName(String customerName) {
        Optional<CustomerAccount> accountOptional = cARepos.findAllByCustomerName(customerName);
        if (!accountOptional.isPresent()) {
            throw new RecordNotFoundException("cannot find the billing address so please enter a anther carId");
        } else {
            CustomerAccount a = accountOptional.get();
            return accountToDTo3(a);
        }
    }
    public CustomerAccountOutputDto getAccountByLicensePlate(String licensePlate) {
        Optional<Car> carOptional = carRepos.findByLicensePlate(licensePlate);
        if (carOptional.isEmpty()) {
            throw new AccountNotFoundException("account", "licensePlate" , licensePlate);

        } else {
            Car car = carOptional.get();
            CustomerAccount account = car.getAccount();
            return accountToDTo1(account);
        }
        }





    public CustomerAccountDto.CustomerNameDto updateCustomerNameById(long id, String firstName, String LastName) {
        Optional<CustomerAccount> accountOptional = cARepos.findById(id);

        if (accountOptional.isEmpty()) {
            throw new RecordNotFoundException("Can not find " + firstName + LastName + "so please enter a anther carId ");
        } else {
            CustomerAccount name = accountOptional.get();
            name.setFirstName(firstName);
            name.setLastName(LastName);
            cARepos.save(name);
            return null;
        }


    }

    public CustomerAccountDto.CustomerFinanceDto updateFinance(String customerName, String billingAdress, String bankAccountNumber) {
        Optional<CustomerAccount> accountOptional = cARepos.findAccountByCustomerName(customerName);
        if (accountOptional.isEmpty()) {
            throw new RecordNotFoundException("cannot find the files, please give me anther carId");
        } else {
            CustomerAccount a = accountOptional.get();
            a.setBankAccountNumber(bankAccountNumber);
            a.setBillingAddress(billingAdress);
            cARepos.save(a);
            return null;
        }
    }

    public String deleteCustomerByCustomerName(String customerName) {
        if (!cARepos.existsById(customerName)) {
            throw new CarNotFoundException("Car  off customer:" + customerName + "is not found");
        } else {
            long count = cARepos.count();
            cARepos.deleteById(customerName);
            return "You delete" + count + "in the de name" + customerName;
        }
    }

    public String deleteAllCustomers() {
        long count = cARepos.count();
        cARepos.deleteAll();
        return "You deleted " + count + "cars";
    }


    public CustomerAccountOutputDto accountToDTo1(CustomerAccount account) {
        CustomerAccountOutputDto dto = new CustomerAccountOutputDto();

        dto.id = account.getId();
        dto.customerName = account.getCustomerName();
        dto.firstName = account.getFirstName();
        dto.lastName = account.getLastName();
        dto.address = account.getAddress();
        dto.phoneNumber = account.getPhoneNumber();
        dto.billingAddress = account.getBillingAddress();
        dto.bankAccountNumber = account.getBankAccountNumber();

        return dto;
    }

    public CustomerAccountOutputDto.CustomerNameOutputDto accountToDTo2(CustomerAccount account) {
        CustomerAccountOutputDto.CustomerNameOutputDto dto = new CustomerAccountOutputDto.CustomerNameOutputDto();


        dto.firstName = account.getFirstName();
        dto.lastName = account.getLastName();


        return dto;
    }

    public CustomerAccountOutputDto.CustomerFinanceOutputDto accountToDTo3(CustomerAccount account) {
        CustomerAccountOutputDto.CustomerFinanceOutputDto dto = new CustomerAccountOutputDto.CustomerFinanceOutputDto();

        dto.billingAddress = account.getBillingAddress();
        dto.bankAccountNumber = account.getBankAccountNumber();

        return dto;
    }

    public CustomerAccount DtoToAccount(CustomerAccountDto.CustomerAccountAllDto accountDto) {
        CustomerAccount account = new CustomerAccount();

        account.setCustomerName(accountDto.customerName);
        account.setFirstName(accountDto.firstName);
        account.setLastName(accountDto.lastName);
        account.setAddress(accountDto.address);
        account.setPhoneNumber(accountDto.phoneNumber);
        account.setBillingAddress(accountDto.billingAddress);
        account.setBankAccountNumber(accountDto.bankAccountNumber);


        return account;
    }




}

