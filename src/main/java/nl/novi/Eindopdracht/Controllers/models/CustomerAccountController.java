package nl.novi.Eindopdracht.Controllers.models;


import lombok.AllArgsConstructor;
import nl.novi.Eindopdracht.Service.ModelService.CarService;
import nl.novi.Eindopdracht.Service.ModelService.CustomerAccountService;
import nl.novi.Eindopdracht.dto.input.CustomerAccountDto;
import nl.novi.Eindopdracht.dto.output.CarOutputDto;
import nl.novi.Eindopdracht.dto.output.CustomerAccountOutputDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.List;
@AllArgsConstructor
@RequestMapping("customer")
@RestController
public class CustomerAccountController {

    private final CustomerAccountService cAService;

    private final CarService carService;


    @PostMapping()
    public ResponseEntity<Object> createCustomer(@RequestBody CustomerAccountDto cADto){
        Long id = cAService.createCostumer(cADto);
        cADto.id = id;


        URI uri = URI.create(ServletUriComponentsBuilder.
                fromCurrentRequest().path("/"+ id).toUriString());

        return ResponseEntity.created(uri).body(cADto);
    }

    @GetMapping
    public ResponseEntity<List<CustomerAccountOutputDto>> getAllCustomers(){
        List<CustomerAccountOutputDto> customers = cAService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/find")
    public ResponseEntity<CustomerAccountOutputDto > getCustomerByCustomerName(@RequestParam String customerName ){
        CustomerAccountOutputDto customer = cAService.getCustomerByCustomerName(customerName);
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/find/billing-address")
    public ResponseEntity<CustomerAccountOutputDto.CustomerFinanceOutputDto>getBillingAddressByCustomermerName(@RequestParam String costumerName){
        CustomerAccountOutputDto.CustomerFinanceOutputDto customer = cAService.getBillingAddressByCustomerName(costumerName);
        return ResponseEntity.ok(customer);
    }
    @GetMapping("/find/car")
    public ResponseEntity<Collection<CarOutputDto>> getCarsByCustomerName(@RequestParam String customerName) {
        Collection<CarOutputDto> car = carService.getAllCarsByCustomerName(customerName);
        return ResponseEntity.ok(car);
    }

    @PutMapping("/update/name/{id}")
    public ResponseEntity<CustomerAccountDto> updateCustomerNameById(@PathVariable long id, @RequestParam String customerFirstname, @RequestParam String customerLastName){

        CustomerAccountDto customer =  cAService.updateCustomerNameById(id,customerFirstname,customerLastName);
        return ResponseEntity.ok(customer);
    }

    @PutMapping("/update/address")
    public ResponseEntity<CustomerAccountDto> updateFinanceByCustomerName(@RequestParam String customerName, @RequestParam String billingAdress, @RequestParam String bankAccountNumber){
        CustomerAccountDto customer = cAService.updateFinance(customerName,billingAdress,bankAccountNumber);
        return ResponseEntity.ok(customer);

    }

    @DeleteMapping("/delete/by-name")
    public ResponseEntity<String> deleteCustomerByCustomerName(@RequestParam String customerName){
        cAService.deleteCustomerByCustomerName(customerName);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAllCustomers(){
        cAService.deleteAllCustomers();
        return ResponseEntity.noContent().build();

    }


}
