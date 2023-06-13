package nl.novi.Eindopdracht.Controllers.models;

import lombok.AllArgsConstructor;
import nl.novi.Eindopdracht.Service.ModelService.TyresService;
import nl.novi.Eindopdracht.dto.input.CarPartsDto.TyresDto;
import nl.novi.Eindopdracht.dto.output.CarPartsDto.TyresOutputDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@AllArgsConstructor


@RequestMapping("/parts/tyres")
@RestController
public class TyresController {

    private final TyresService tyresService;


    @PostMapping("create")
    public ResponseEntity<Object> createBrake(@RequestBody TyresDto tyresDto) {
        Long id = tyresService.createTyre(tyresDto);
        tyresDto.id = id;

        URI uri = URI.create(ServletUriComponentsBuilder.
                fromCurrentRequest().path("/" + id).toUriString());
        return ResponseEntity.created(uri).body(tyresDto);
    }
    @GetMapping("find/all")
    public ResponseEntity<List<TyresOutputDto>>getAllTyres(){
        List<TyresOutputDto> tyresOutputDtoList = tyresService.getAllTyres();
        return ResponseEntity.ok(tyresOutputDtoList);
    }
    @GetMapping("find/{id}")
    public ResponseEntity<TyresOutputDto>getTyresByID(@PathVariable long id){
        TyresOutputDto tyresOutputDto = tyresService.getTyreById(id);
        return ResponseEntity.ok(tyresOutputDto);
    }
    @PutMapping("/update/amountOfParts/{id}")
    public ResponseEntity<Object> updateAmountOfTyreSets(@PathVariable long id, @RequestBody TyresDto tDto){
        tyresService.updateAmountOfParts(id,tDto);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/update/price/{id}")
    public ResponseEntity<Object>updatePrice(@PathVariable long id,@RequestBody TyresDto tDto){
        tyresService.updatePrice(id,tDto);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/update/part-number/{id}")
    public ResponseEntity<Object>updatePartNumber(@PathVariable long id, @RequestBody TyresDto tDto){
        tyresService.updatePartNumber(id,tDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTyreBuId(@PathVariable long id){
        tyresService.deletetyreById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<String> deleteAllTyres(){
        tyresService.deleteAllBrakes();
        return ResponseEntity.noContent().build();
    }

}
