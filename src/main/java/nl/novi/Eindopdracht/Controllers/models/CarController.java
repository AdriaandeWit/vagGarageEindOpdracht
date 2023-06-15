package nl.novi.Eindopdracht.Controllers.models;


import lombok.AllArgsConstructor;
import nl.novi.Eindopdracht.Models.Data.Enum.EngineType;
import nl.novi.Eindopdracht.Service.ModelService.CarService;
import nl.novi.Eindopdracht.Service.ModelService.CustomerAccountService;
import nl.novi.Eindopdracht.dto.input.CarDto;
import nl.novi.Eindopdracht.dto.output.CarOutputDto;
import nl.novi.Eindopdracht.dto.output.CustomerAccountOutputDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequestMapping("/car")
@RestController
@AllArgsConstructor
public class CarController {

    private final CarService carService;

    private final CustomerAccountService cAService;


    @PostMapping("/create/car")
    public ResponseEntity<Object> createCar(@RequestBody CarDto carDto) {
        carService.createCar(carDto);

        URI uri = URI.create(ServletUriComponentsBuilder.
                fromCurrentRequest().path("/" + carDto.licensePlate).toUriString());

        return ResponseEntity.created(uri).body(carDto);
    }

    @GetMapping("find/all-cars")
    public ResponseEntity<List<CarOutputDto>> getAllCars() {
        List<CarOutputDto> carOutputDto = carService.getAllCars();
        return ResponseEntity.ok(carOutputDto);
    }

    @GetMapping("/find/car")
    public ResponseEntity<CarOutputDto> getCarById(@RequestParam String licensePlate) {
        CarOutputDto carOutputDto = carService.getCarByCarLicensePlate(licensePlate);
        return ResponseEntity.ok(carOutputDto);
    }

    @GetMapping("/find/owner")
    public ResponseEntity<CustomerAccountOutputDto> getAccountByLicensePlate(@RequestParam String licensePlate) {
        CustomerAccountOutputDto account = cAService.getAccountByLicensePlate(licensePlate);
        return ResponseEntity.ok(account);
    }

    @PutMapping("/update/mileage")
    public ResponseEntity<Object> updateCarMileage(@RequestParam String licensePlate, @RequestBody CarDto carDto) {

        carService.updateCarMileage(licensePlate, carDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/engineType")
    public ResponseEntity<Object> updateEngineType(@RequestParam String licensePlate, @RequestBody CarDto carDto) {
        carService.updateEngineType(licensePlate, carDto);
        return ResponseEntity.ok().build();

    }

    @PutMapping("/add/owner")
    public ResponseEntity<Object> addAccountToCar(@RequestParam String licensePlate, @RequestParam String customerName) {
        carService.addAccountToCar(licensePlate, customerName);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCarById(@RequestParam String licensePlate) {
        carService.deleteCarByLicensePlate(licensePlate);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<String> deleteAllCars() {
        carService.deleteAllCars();
        return ResponseEntity.noContent().build();

    }
}
