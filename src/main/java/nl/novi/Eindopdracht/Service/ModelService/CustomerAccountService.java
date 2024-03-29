package nl.novi.Eindopdracht.Service.ModelService;

import lombok.AllArgsConstructor;
import nl.novi.Eindopdracht.Exceptions.AccountNotFoundException;
import nl.novi.Eindopdracht.Exceptions.CarNotFoundException;
import nl.novi.Eindopdracht.Exceptions.RecordNotFoundException;
import nl.novi.Eindopdracht.Models.Data.Car;
import nl.novi.Eindopdracht.Models.Data.CustomerAccount;
import nl.novi.Eindopdracht.Repository.CarRepository;
import nl.novi.Eindopdracht.Repository.CustomerAccountRepository;
import nl.novi.Eindopdracht.dto.input.CustomerAccountDto;
import nl.novi.Eindopdracht.dto.output.CustomerAccountOutputDto;
import org.springframework.stereotype.Service;

import java.util.*;
@AllArgsConstructor
@Service
public class CustomerAccountService {


    private final CustomerAccountRepository customerAccountRepos;

    private final CarRepository carRepos;




    public CustomerAccountOutputDto createCostumer(CustomerAccountDto cADto) {
        CustomerAccount account = mapToAccount(cADto);
        account = customerAccountRepos.save(account);

        CustomerAccountOutputDto savedAccountDto = mapToDTo1(account);
        return savedAccountDto;
    }

    public List<CustomerAccountOutputDto> getAllCustomers() {
        List<CustomerAccountOutputDto> collection = new ArrayList<>();
        List<CustomerAccount> list = customerAccountRepos.findAll();
        for (CustomerAccount account : list) {
            collection.add(mapToDTo1(account));
        }
        return collection;
    }

    public CustomerAccountOutputDto getCustomerByCustomerName(String customerName) {
        Optional<CustomerAccount> account = customerAccountRepos.findAccountByCustomerName(customerName);
        if (account.isEmpty()) {
            throw new RecordNotFoundException("cannot find customer please enter a new carId ");
        } else {
            CustomerAccount a = account.get();
            return mapToDTo1(a);
        }
    }

    public CustomerAccountOutputDto.CustomerNameOutputDto getCustomerByLastName(String customerLastName) {
        Optional<List<CustomerAccount>> optionalAccounts = customerAccountRepos.findByLastName(customerLastName);
        if (!optionalAccounts.isPresent()) {
            throw new RecordNotFoundException("cannot find customer please enter a anther name");
        } else {
            CustomerAccount a = (CustomerAccount) optionalAccounts.get();
            return mapToDTo2(a);
        }

    }

    public CustomerAccountOutputDto.CustomerFinanceOutputDto getBillingAddressByCustomerName(String customerName) {
        Optional<CustomerAccount> accountOptional = customerAccountRepos.findAllByCustomerName(customerName);
        if (!accountOptional.isPresent()) {
            throw new RecordNotFoundException("cannot find the billing address so please enter a anther name");
        } else {
            CustomerAccount a = accountOptional.get();
            return mapToDTo3(a);
        }
    }
    public CustomerAccountOutputDto getAccountByLicensePlate(String licensePlate) {
        Optional<Car> carOptional = carRepos.findByLicensePlate(licensePlate);
        if (carOptional.isEmpty()) {
            throw new AccountNotFoundException("account", "licensePlate" , licensePlate);

        } else {
            Car car = carOptional.get();
            CustomerAccount account = car.getAccount();
            return mapToDTo1(account);
        }
        }



    public CustomerAccountDto updateFinance(String customerName, CustomerAccountDto dto) {
        Optional<CustomerAccount> accountOptional = customerAccountRepos.findAccountByCustomerName(customerName);
        if (accountOptional.isEmpty()) {
            throw new RecordNotFoundException("cannot find the files, please give me anther customer name");
        } else {
            CustomerAccount a = accountOptional.get();
            a.setBankAccountNumber(dto.bankAccountNumber);
            a.setBillingAddress(dto.billingAddress);
            customerAccountRepos.save(a);
            return null;
        }
    }

    public String deleteCustomerByCustomerName(String customerName) {
        if (!customerAccountRepos.existsById(customerName)) {
            throw new CarNotFoundException("car  off customer:" + customerName + "is not found");
        } else {
            long count = customerAccountRepos.count();
            customerAccountRepos.deleteById(customerName);
            return "You delete" + count + "in the de name" + customerName;
        }
    }

    public String deleteAllCustomers() {
        long count = customerAccountRepos.count();
        customerAccountRepos.deleteAll();
        return "You deleted " + count + "cars";
    }


    public CustomerAccountOutputDto mapToDTo1(CustomerAccount account) {
        CustomerAccountOutputDto dto = new CustomerAccountOutputDto();

        dto.customerName = account.getCustomerName();
        dto.firstName = account.getFirstName();
        dto.lastName = account.getLastName();
        dto.address = account.getAddress();
        dto.phoneNumber = account.getPhoneNumber();
        dto.billingAddress = account.getBillingAddress();
        dto.bankAccountNumber = account.getBankAccountNumber();

        return dto;
    }

    public CustomerAccountOutputDto.CustomerNameOutputDto mapToDTo2(CustomerAccount account) {
        CustomerAccountOutputDto.CustomerNameOutputDto dto = new CustomerAccountOutputDto.CustomerNameOutputDto();


        dto.firstName = account.getFirstName();
        dto.lastName = account.getLastName();


        return dto;
    }

    public CustomerAccountOutputDto.CustomerFinanceOutputDto mapToDTo3(CustomerAccount account) {
        CustomerAccountOutputDto.CustomerFinanceOutputDto dto = new CustomerAccountOutputDto.CustomerFinanceOutputDto();

        dto.billingAddress = account.getBillingAddress();
        dto.bankAccountNumber = account.getBankAccountNumber();

        return dto;
    }

    public CustomerAccount mapToAccount(CustomerAccountDto accountDto) {
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

