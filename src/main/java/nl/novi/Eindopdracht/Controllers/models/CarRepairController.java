package nl.novi.Eindopdracht.Controllers.models;

import lombok.AllArgsConstructor;
import nl.novi.Eindopdracht.Exceptions.RecordNotFoundException;
import nl.novi.Eindopdracht.Models.Data.Enum.PartType;
import nl.novi.Eindopdracht.Service.ModelService.CarRepairService;
import nl.novi.Eindopdracht.dto.input.CarRepairDto;
import nl.novi.Eindopdracht.dto.input.PartDto;
import nl.novi.Eindopdracht.dto.output.CarPartsDto.SparkPlugOutputDto;
import nl.novi.Eindopdracht.dto.output.CarRepairOutputDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@AllArgsConstructor

@RequestMapping("carRepair")
@RestController
public class CarRepairController {

    private final CarRepairService reparationService;

    @PostMapping("/create/")
    public ResponseEntity<Object> createCarReport(@RequestBody CarRepairDto carRepairDto) {
        Long id = reparationService.createCarReport(carRepairDto);
        carRepairDto.id = id;

        URI uri = URI.create(ServletUriComponentsBuilder.
                fromCurrentRequest()
                .path("/" + id)
                .toUriString());

        return ResponseEntity.created(uri).body(carRepairDto);
    }

    @GetMapping("/find/all")
    public ResponseEntity<List<CarRepairOutputDto>> getAllRapairs() {
        List<CarRepairOutputDto> carRepairOutputDtos = reparationService.getAllRapairs();
        return ResponseEntity.ok(carRepairOutputDtos);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<CarRepairOutputDto> getRepairById(@PathVariable Long id) {
        CarRepairOutputDto dto = reparationService.getRepairByID(id);
        return ResponseEntity.ok(dto);
    }

/*    @GetMapping("/totalCost{id}")
    public ResponseEntity<CarRepairOutputDto> getTotalCostById(@PathVariable long id) {
        CarRepairOutputDto dto = reparationService.getTotalCostByID(id);
        return ResponseEntity.ok(dto);
    }*/

    @PutMapping("/update/car-problem/{id}")
    public ResponseEntity<CarRepairOutputDto> updateCarProblem(@PathVariable long id, @RequestBody CarRepairDto repairDto) {
        CarRepairOutputDto dto = reparationService.updateCarProblem(id, repairDto);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/update/repair-date/{id}")
    public ResponseEntity<CarRepairOutputDto> updateRepairDate(@PathVariable long id, @RequestBody CarRepairDto repairDto) {
        CarRepairOutputDto dto = reparationService.updateRepairDate(id, repairDto);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{carRepairId}/add/parts")
    public ResponseEntity<String> addPartToCarRepair(
            @PathVariable long carRepairId,
            @RequestBody PartDto partDto
            ) {
        try {
            reparationService.addPartToCarRepair(carRepairId, partDto);
            return ResponseEntity.ok("Part added successfully to car repair");
        } catch (RecordNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        }


  /*  @PutMapping("/add/brake/{id}/{brakeId}")
    public ResponseEntity<Object> addBrakeToCarRepair(@PathVariable long id, @PathVariable long brakeId) {
        reparationService.addBrakeToCarRepair(id, brakeId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/add/spark-plug/{id}/{sparkPlugId}")
    public ResponseEntity<Object> addSparkPlugToCarRepair(@PathVariable long id, @PathVariable long sparkPlugId) {
        reparationService.addSparkPlugToCarRepair(id, sparkPlugId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/add/tyre/{id}/{tyreId}")
    public ResponseEntity<Object> addTyreToCarRepair(@PathVariable long id, @PathVariable long tyreId) {
        reparationService.addTyreToCarRepair(id, tyreId);
        return ResponseEntity.ok().build();
    }*/

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRepairById(@PathVariable long id) {
        reparationService.deleteRepairById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<String> deleteAllRepairs() {
        reparationService.deleteAllRepairs();
        return ResponseEntity.noContent().build();
    }


}
