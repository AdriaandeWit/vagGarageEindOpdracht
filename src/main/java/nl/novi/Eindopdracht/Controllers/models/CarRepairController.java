package nl.novi.Eindopdracht.Controllers.models;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nl.novi.Eindopdracht.Exceptions.RecordNotFoundException;
import nl.novi.Eindopdracht.Service.ModelService.CarRepairService;
import nl.novi.Eindopdracht.dto.input.CarRepairDto;
import nl.novi.Eindopdracht.dto.input.PartDto;
import nl.novi.Eindopdracht.dto.output.CarRepairOutputDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static nl.novi.Eindopdracht.Utils.Utillities.getErrorString;

@AllArgsConstructor

@RequestMapping("carRepair")
@RestController
public class CarRepairController {

    private final CarRepairService repairService;

    @PostMapping("/create")
    public ResponseEntity<Object> createCarReport(@Valid @RequestBody CarRepairDto carRepairDto, BindingResult br) {
        if (br.hasErrors()) {
            String errorString = getErrorString(br);
            return new ResponseEntity<>(errorString,HttpStatus.BAD_REQUEST);
        } else {
            Long id = repairService.createCarReport(carRepairDto).getId();
            carRepairDto.id = id;

            URI uri = URI.create(ServletUriComponentsBuilder.
                    fromCurrentRequest()
                    .path("/" + id)
                    .toUriString());

            return ResponseEntity.created(uri).body(carRepairDto);
        }
    }

    @GetMapping("/find/all")
    public ResponseEntity<List<CarRepairOutputDto>> getAllRapairs() {
        List<CarRepairOutputDto> carRepairOutputDtos = repairService.getAllRapairs();
        return ResponseEntity.ok(carRepairOutputDtos);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<CarRepairOutputDto> getRepairById(@PathVariable Long id) {
        CarRepairOutputDto dto = repairService.getRepairByID(id);
        return ResponseEntity.ok(dto);
    }

/*    @GetMapping("/totalCost{customerNumber}")
    public ResponseEntity<CarRepairOutputDto> getTotalCostById(@PathVariable long customerNumber) {
        CarRepairOutputDto dto = repairService.getTotalCostByID(customerNumber);
        return ResponseEntity.ok(dto);
    }*/

    @PutMapping("/update/car-problem/{id}")
    public ResponseEntity<Object> updateCarProblem(@PathVariable long id,@Valid @RequestBody CarRepairDto repairDto ,BindingResult br) {
        if (br.hasErrors()){
            String errorString = getErrorString(br);
            return new ResponseEntity<>(errorString, HttpStatus.BAD_REQUEST);
        }else {
            CarRepairOutputDto dto = repairService.updateCarProblem(id, repairDto);
            return ResponseEntity.ok(dto);
        }
    }

    @PutMapping("/update/repair-date/{id}")
    public ResponseEntity<Object> updateRepairDate(@PathVariable long id,@Valid @RequestBody CarRepairDto repairDto,BindingResult br) {
        if (br.hasErrors()){
            String errorString = getErrorString(br);
            return new ResponseEntity<>(errorString, HttpStatus.BAD_REQUEST);
        }else {
            CarRepairOutputDto dto = repairService.updateRepairDate(id, repairDto);
            return ResponseEntity.ok(dto);
        }
    }

    @PutMapping("/add/parts/{carRepairId}")
    public ResponseEntity<String> addPartToCarRepair(
            @PathVariable Long carRepairId,
            @Valid @RequestBody PartDto partDto,BindingResult br
    ) {
        try {
            if (br.hasErrors()){
                String errorString = getErrorString(br);
                return new ResponseEntity<>(errorString, HttpStatus.BAD_REQUEST);
            }else {
                repairService.addPartToCarRepair(carRepairId, partDto);
                return ResponseEntity.ok("Part added successfully to car repair");
            }
        } catch (RecordNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRepairById(@PathVariable long id) {
        repairService.deleteRepairById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<String> deleteAllRepairs() {
        repairService.deleteAllRepairs();
        return ResponseEntity.noContent().build();
    }


}
