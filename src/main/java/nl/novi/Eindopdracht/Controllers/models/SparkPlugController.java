package nl.novi.Eindopdracht.Controllers.models;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nl.novi.Eindopdracht.Service.ModelService.SparkPlugService;
import nl.novi.Eindopdracht.dto.input.CarPartsDto.SparkPlugDto;
import nl.novi.Eindopdracht.dto.output.CarPartsDto.SparkPlugOutputDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static nl.novi.Eindopdracht.Utils.Utillities.getErrorString;

@AllArgsConstructor

@RequestMapping("/parts/sparkPlugs")
@RestController
public class SparkPlugController {

    private final SparkPlugService sparkPlugService;


    @PostMapping("create")
    public ResponseEntity<Object> createSparkPlug(@Valid @RequestBody SparkPlugDto sparkPlugDto, BindingResult br) {
    if (br.hasErrors()){
        String errorString = getErrorString(br);
        return new ResponseEntity<>(errorString, HttpStatus.BAD_REQUEST);
    }else{
            Long id = sparkPlugService.createSparkPlug(sparkPlugDto);
            sparkPlugDto.id = id;

            URI uri = URI.create(ServletUriComponentsBuilder.
                    fromCurrentRequest().path("/" + id).toUriString());
            return ResponseEntity.created(uri).body(sparkPlugDto);
        }
    }
    @GetMapping("/find/all")
    public ResponseEntity<List<SparkPlugOutputDto>>getAllSparkPlugs(){
        List<SparkPlugOutputDto> sparkPlugOutputDtoList = sparkPlugService.getAllSparkPlugs();
        return ResponseEntity.ok(sparkPlugOutputDtoList);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<SparkPlugOutputDto>getSparkPlugById(@PathVariable long id){
        SparkPlugOutputDto sparkPlugOutputDto = sparkPlugService.getSparkPlugById(id);
        return ResponseEntity.ok(sparkPlugOutputDto);
    }
    @PutMapping("/update/amountOfParts/{id}")
    public ResponseEntity<SparkPlugOutputDto> updateAmountOfSparkPlugs(@PathVariable long id, @RequestBody SparkPlugDto sPDto){
        sparkPlugService.updateAmountOfParts(id, sPDto);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/update/price/{id}")
    public ResponseEntity<SparkPlugOutputDto>updatePrice(@PathVariable long id,@RequestBody SparkPlugDto sPDto){
        sparkPlugService.updatePrice(id, sPDto);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/update/part-number/{id}")
    public ResponseEntity<Object>updatePartNumber(@PathVariable long id, @RequestBody SparkPlugDto sPDto){
        sparkPlugService.updatePartNumber(id, sPDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTyreBuId(@PathVariable long id){
        sparkPlugService.deleteSparkPlugById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<String> deleteAllTyres(){
        sparkPlugService.deleteAllBrakes();
        return ResponseEntity.noContent().build();
    }

}
