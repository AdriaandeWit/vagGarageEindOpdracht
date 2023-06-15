package nl.novi.Eindopdracht.Controllers.models;

import nl.novi.Eindopdracht.Service.ModelService.CarInspectionService;
import nl.novi.Eindopdracht.dto.input.CarInspectionDto;
import nl.novi.Eindopdracht.dto.output.CarInspectionOutputDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RequestMapping("inspection")
@RestController
public class CarInspectionController {

    private final CarInspectionService carInspectionService;

    public CarInspectionController(CarInspectionService carInspectionService) {
        this.carInspectionService = carInspectionService;
    }

    @PostMapping("/create/")
    public ResponseEntity<Object> createInspection(CarInspectionDto carInspectionDto) {
        Long id = carInspectionService.createInspection(carInspectionDto);
        carInspectionDto.id = id;

        URI uri = URI.create(ServletUriComponentsBuilder.
                fromCurrentRequest().path("/" + id).toUriString());

        return ResponseEntity.created(uri).body(carInspectionDto);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<CarInspectionOutputDto> getInspectionById(@PathVariable long id) {
        CarInspectionOutputDto carInspectionOutputDto = carInspectionService.getInspectionByID(id);
        return ResponseEntity.ok(carInspectionOutputDto);
    }

    @GetMapping("/find/all/")
    public ResponseEntity<List<CarInspectionOutputDto>> getAllInspections() {
        List<CarInspectionOutputDto> carInspectionOutputDtos = carInspectionService.getAllInspections();
        return ResponseEntity.ok(carInspectionOutputDtos);
    }

    @PutMapping("/update/mileage/{id}")
    public ResponseEntity<CarInspectionOutputDto> updateMileAge(@PathVariable Long id, @RequestBody CarInspectionDto carInspectionDto) {
        carInspectionService.updateMileAge(id, carInspectionDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/inspectionDate/{id}")
    public ResponseEntity<CarInspectionOutputDto> updateInspectionDate(@PathVariable Long id, @RequestBody CarInspectionDto carInspectionDto) {
        carInspectionService.updateInspectionDate(id, carInspectionDto);
        return ResponseEntity.ok().build();
    }

    /*@PutMapping("/update/isFine/statusCar/{id}")
    public ResponseEntity<String> updateCarIsFine(@PathVariable Long id, @RequestBody CarInspectionDto carInspectionDto) {
        carInspectionService.updateCarIsFine(id, carInspectionDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/isIncorrect/statusCar/{id}")
    public ResponseEntity<String> updateHasProblem(@PathVariable Long id, @RequestBody CarInspectionDto carInspectionDto) {
        carInspectionService.updateHasProblem(id, carInspectionDto);
        return ResponseEntity.ok().build();
    }*/

    @PutMapping("/update/carIsCorrect/{id}")
    public ResponseEntity<Object> updateStatusCar(@PathVariable Long id, @RequestBody CarInspectionDto carInspectionDto) {
        carInspectionService.updateCarStatus(id, carInspectionDto);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/delete/inspection/{id}")
    public ResponseEntity<String> deleteInspectionById(@PathVariable Long id) {
        carInspectionService.deleteInspectionById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/all/inspections")
    public ResponseEntity<String> deleteAllInspections() {
        carInspectionService.deleteAllInspections();
        return ResponseEntity.noContent().build();

    }

}
