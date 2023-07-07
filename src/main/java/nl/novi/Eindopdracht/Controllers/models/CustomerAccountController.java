package nl.novi.Eindopdracht.Controllers.models;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nl.novi.Eindopdracht.Service.ModelService.CarService;
import nl.novi.Eindopdracht.Service.ModelService.CustomerAccountService;
import nl.novi.Eindopdracht.dto.input.CustomerAccountDto;
import nl.novi.Eindopdracht.dto.output.CarOutputDto;
import nl.novi.Eindopdracht.dto.output.CustomerAccountOutputDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.List;

import static nl.novi.Eindopdracht.Utils.Utillities.getErrorString;

@AllArgsConstructor
@RequestMapping("customer")
@RestController
public class CustomerAccountController {

    private final CustomerAccountService cAService;

    private final CarService carService;


    @PostMapping("/create/")
    public ResponseEntity<Object> createCustomer(@Valid @RequestBody CustomerAccountDto cADto, BindingResult br){
        if (br.hasErrors()){
            String errorString = getErrorString(br);
            return new ResponseEntity<>(errorString, HttpStatus.BAD_REQUEST);
        }else {

            Long id = cAService.createCostumer(cADto);
            cADto.id = id;


            URI uri = URI.create(ServletUriComponentsBuilder.
                    fromCurrentRequest().path("/" + id).toUriString());

            return ResponseEntity.created(uri).body(cADto);
        }
    }

    @GetMapping("/find/all/")
    public ResponseEntity<List<CustomerAccountOutputDto>> getAllCustomers(){
        List<CustomerAccountOutputDto> customers = cAService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/find/byId/")
    public ResponseEntity<CustomerAccountOutputDto > getCustomerByCustomerName(@RequestParam String customerName ){
        CustomerAccountOutputDto customer = cAService.getCustomerByCustomerName(customerName);
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/find/billing-address/")
    public ResponseEntity<CustomerAccountOutputDto.CustomerFinanceOutputDto>getBillingAddressByCustomerName(@RequestParam String customerName){
        CustomerAccountOutputDto.CustomerFinanceOutputDto customer = cAService.getBillingAddressByCustomerName(customerName);
        return ResponseEntity.ok(customer);
    }
    @GetMapping("/find/car/")
    public ResponseEntity<Collection<CarOutputDto>> getCarsByCustomerName(@RequestParam String customerName) {
        Collection<CarOutputDto> car = carService.getAllCarsByCustomerName(customerName);
        return ResponseEntity.ok(car);
    }

    @PutMapping("/update/name/{id}")
    public ResponseEntity<Object> updateCustomerNameById( @PathVariable long id,@Valid @RequestBody CustomerAccountDto accountDto,BindingResult br){
        if (br.hasErrors()) {
            String errorString = getErrorString(br);
            return new ResponseEntity<>(errorString, HttpStatus.BAD_REQUEST);

        }else {
            CustomerAccountDto customer = cAService.updateCustomerNameById(id, accountDto);
            return ResponseEntity.ok(customer);
        }
    }

    @PutMapping("/update/address/")
    public ResponseEntity<Object> updateFinanceByCustomerName( @RequestParam String customerName, @Valid @RequestBody CustomerAccountDto dto,BindingResult br){
        if (br.hasErrors()){
            String errorString = getErrorString(br);
            return new ResponseEntity<>(errorString, HttpStatus.BAD_REQUEST);
        }else{
            CustomerAccountDto customer = cAService.updateFinance(customerName,dto);
            return ResponseEntity.ok(customer);
        }
    }

    @DeleteMapping("/delete/by-name/")
    public ResponseEntity<String> deleteCustomerByCustomerName(@RequestParam String customerName){
        cAService.deleteCustomerByCustomerName(customerName);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/all/")
    public ResponseEntity<String> deleteAllCustomers(){
        cAService.deleteAllCustomers();
        return ResponseEntity.noContent().build();

    }


}
