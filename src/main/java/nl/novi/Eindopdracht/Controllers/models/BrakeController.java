package nl.novi.Eindopdracht.Controllers.models;

import jakarta.validation.Valid;
import nl.novi.Eindopdracht.Service.ModelService.BrakeService;
import nl.novi.Eindopdracht.Service.ModelService.CarService;
import nl.novi.Eindopdracht.dto.input.CarPartsDto.BrakesDto;
import nl.novi.Eindopdracht.dto.output.CarPartsDto.BrakesOutputDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static nl.novi.Eindopdracht.Utils.Utillities.getErrorString;

@RequestMapping("/parts/brakes")
@RestController
public class BrakeController {

    private final BrakeService brakeService;

    private final CarService carService;


    public BrakeController(BrakeService brakeService, CarService carService) {
        this.brakeService = brakeService;
        this.carService = carService;
    }


    @PostMapping("create")
    public ResponseEntity<Object> createBrake(@Valid @RequestBody BrakesDto brakesDto, BindingResult br) {
        if (br.hasErrors()) {
           String errorString = getErrorString(br);
           return new ResponseEntity<>(errorString, HttpStatus.BAD_REQUEST);

        } else {
            Long id = brakeService.createBrake(brakesDto);
            brakesDto.id = id;

            URI uri = URI.create(ServletUriComponentsBuilder.
                    fromCurrentRequest().path("/" + id).toUriString());
            return ResponseEntity.created(uri).body(brakesDto);
        }
    }

    @GetMapping("/find/all")
    public ResponseEntity<List<BrakesOutputDto>> getAllBrakes() {
        List<BrakesOutputDto> brakesOutputDtoList = brakeService.getAllBrakes();
        return ResponseEntity.ok(brakesOutputDtoList);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<BrakesOutputDto> getBrakeById(@PathVariable Long id) {
        BrakesOutputDto brakesOutputDto = brakeService.getBrakeById(id);
        return ResponseEntity.ok(brakesOutputDto);
    }

    @PutMapping("/update/amountOfParts/{id}")
    public ResponseEntity<Object> updateAmountOfParts(@PathVariable Long id, @RequestBody BrakesDto brakesDto) {
        brakeService.updateAmountOfParts(id, brakesDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/price/{brakeId}")
    public ResponseEntity<Object> updatePrice(@PathVariable Long brakeId, @RequestBody BrakesDto brakesDto) {
        brakeService.updatePrice(brakeId, brakesDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/part-number/{brakeId}")
    public ResponseEntity<Object> updatePartNumber(@PathVariable Long brakeId, @RequestBody BrakesDto brakesDto) {
        brakeService.updatePartNumber(brakeId, brakesDto);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBrakeById(@PathVariable Long id) {
        brakeService.deleteBrakeById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<String> deleteAllBrakes() {
        brakeService.deleteAllBrakes();
        return ResponseEntity.noContent().build();
    }


}
