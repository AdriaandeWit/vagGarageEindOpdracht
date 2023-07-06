package nl.novi.Eindopdracht.Controllers.models;

import jakarta.validation.Valid;
import nl.novi.Eindopdracht.Exceptions.RecordNotFoundException;
import nl.novi.Eindopdracht.Service.ModelService.CarInspectionService;
import nl.novi.Eindopdracht.Service.ModelService.CarService;
import nl.novi.Eindopdracht.dto.input.CarInspectionDto;
import nl.novi.Eindopdracht.dto.output.CarInspectionOutputDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static nl.novi.Eindopdracht.Utils.Utillities.getErrorString;

@RequestMapping("inspection")
@RestController
public class CarInspectionController {

    private final CarInspectionService carInspectionService;

    private final CarService carService;

    public CarInspectionController(CarInspectionService carInspectionService, CarService carService) {
        this.carInspectionService = carInspectionService;
        this.carService = carService;
    }

    @PostMapping("/create/")
    public ResponseEntity<Object> createInspection(@Valid @RequestBody CarInspectionDto carInspectionDto, BindingResult br) {
        if (br.hasErrors()) {
            String errorString = getErrorString(br);
            return new ResponseEntity<>(errorString, HttpStatus.BAD_REQUEST);
        } else {
            Long id = carInspectionService.createInspection(carInspectionDto);
            carInspectionDto.id = id;

            URI uri = URI.create(ServletUriComponentsBuilder.
                    fromCurrentRequest().path("/" + id).toUriString());

            return ResponseEntity.created(uri).body(carInspectionDto);
        }
    }

        @GetMapping("/find/{id}")
        public ResponseEntity<CarInspectionOutputDto> getInspectionById ( @PathVariable long id){
            CarInspectionOutputDto carInspectionOutputDto = carInspectionService.getInspectionByID(id);
            return ResponseEntity.ok(carInspectionOutputDto);
        }

        @GetMapping("/find/all/")
        public ResponseEntity<List<CarInspectionOutputDto>> getAllInspections () {
            List<CarInspectionOutputDto> carInspectionOutputDtos = carInspectionService.getAllInspections();
            return ResponseEntity.ok(carInspectionOutputDtos);
        }

        @PutMapping("/update/mileage/{id}")
        public ResponseEntity<CarInspectionOutputDto> updateMileAge (@PathVariable Long
        id, @RequestBody CarInspectionDto carInspectionDto){
            carInspectionService.updateMileAge(id, carInspectionDto);
            return ResponseEntity.ok().build();
        }

        @PutMapping("/update/inspectionDate/{id}")
        public ResponseEntity<CarInspectionOutputDto> updateInspectionDate (@PathVariable Long
        id, @RequestBody CarInspectionDto carInspectionDto){
            carInspectionService.updateInspectionDate(id, carInspectionDto);
            return ResponseEntity.ok().build();
        }

        @PutMapping("/update/carIsCorrect/{id}")
        public ResponseEntity<Object> updateStatusCar (@PathVariable Long id, @RequestBody CarInspectionDto
        carInspectionDto){
            carInspectionService.updateCarStatus(id, carInspectionDto);
            return ResponseEntity.ok().build();
        }
        @PutMapping("/add/repair/{id}/{repairId}")
        public ResponseEntity<String> addRepairToInspection ( @PathVariable long id, @PathVariable long repairId){
            try {
                carInspectionService.addRepairToInspection(id, repairId);
                return ResponseEntity.ok("Repair add to inspection");
            } catch (RecordNotFoundException e) {
                return ResponseEntity.notFound().build();
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @PutMapping("/add/car/{inspectionId}")
        public ResponseEntity<String> addInspectionToCar ( @PathVariable long inspectionId,
        @RequestParam String licensePlate){
            try {
                carService.addInspectionToCar(inspectionId, licensePlate);
                return ResponseEntity.ok("car added successfully to inspection");
            } catch (RecordNotFoundException e) {
                return ResponseEntity.notFound().build();
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @DeleteMapping("/delete/inspection/{id}")
        public ResponseEntity<String> deleteInspectionById (@PathVariable Long id){
            carInspectionService.deleteInspectionById(id);
            return ResponseEntity.noContent().build();
        }

        @DeleteMapping("/delete/all/inspections")
        public ResponseEntity<String> deleteAllInspections () {
            carInspectionService.deleteAllInspections();
            return ResponseEntity.noContent().build();

        }

    }
