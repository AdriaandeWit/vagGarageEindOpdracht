package nl.novi.Eindopdracht.Controllers.models;

import nl.novi.Eindopdracht.Service.ModelService.BrakeService;
import nl.novi.Eindopdracht.Service.ModelService.CarService;
import nl.novi.Eindopdracht.dto.input.CarPartsDto.BrakesDto;
import nl.novi.Eindopdracht.dto.output.CarPartsDto.BrakesOutputDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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
    public ResponseEntity<Object> createBrake(@RequestBody BrakesDto brakesDto) {
        Long id = brakeService.createBrake(brakesDto);
        brakesDto.id = id;

        URI uri = URI.create(ServletUriComponentsBuilder.
                fromCurrentRequest().path("/" + id).toUriString());
        return ResponseEntity.created(uri).body(brakesDto);

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

    @PutMapping("/update/price/{id}/")
    public ResponseEntity<Object> updatePrice(@PathVariable Long id, @RequestBody BrakesDto brakesDto) {
        brakeService.updatePrice(id, brakesDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/part-number/{id}")
    public ResponseEntity<Object> updatePartNumber(@PathVariable Long id, @RequestBody BrakesDto brakesDto) {
        brakeService.updatePartNumber(id, brakesDto);
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
