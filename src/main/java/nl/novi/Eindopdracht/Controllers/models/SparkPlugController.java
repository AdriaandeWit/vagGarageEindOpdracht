package nl.novi.Eindopdracht.Controllers.models;

import lombok.AllArgsConstructor;
import nl.novi.Eindopdracht.Service.ModelService.SparkPlugService;
import nl.novi.Eindopdracht.dto.input.CarPartsDto.SparkPlugDto;
import nl.novi.Eindopdracht.dto.output.CarPartsDto.SparkPlugOutputDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@AllArgsConstructor

@RequestMapping("/parts/sparkPlugs")
@RestController
public class SparkPlugController {

    private final SparkPlugService sparkPlugService;


    @PostMapping("create")
    public ResponseEntity<Object> createSparkPlug(@RequestBody SparkPlugDto sparkPlugDto) {
        Long id = sparkPlugService.createSparkPlug (sparkPlugDto);
        sparkPlugDto.id = id;

        URI uri = URI.create(ServletUriComponentsBuilder.
                fromCurrentRequest().path("/" + id).toUriString());
        return ResponseEntity.created(uri).body(sparkPlugDto);
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
    public ResponseEntity<Object> updateAmountOfSparkPlugs(@PathVariable long id, @RequestParam Integer amountOfParts){
        sparkPlugService.updateAmountOfParts(id,amountOfParts);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/update/price/{id}")
    public ResponseEntity<Object>updatePrice(@PathVariable long id,@RequestParam Double price){
        sparkPlugService.updatePrice(id,price);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/update/partnumber{id}")
    public ResponseEntity<Object>updatePartNumber(@PathVariable long id, @RequestParam String partNumber){
        sparkPlugService.updatePartNumber(id,partNumber);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTyreBuId(@PathVariable long id){
        sparkPlugService.deleteSparkPlugById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/All")
    public ResponseEntity<String> deleteAllTyres(){
        sparkPlugService.deleteAllBrakes();
        return ResponseEntity.noContent().build();
    }

}
