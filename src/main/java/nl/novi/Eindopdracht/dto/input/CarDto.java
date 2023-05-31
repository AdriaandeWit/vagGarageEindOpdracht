package nl.novi.Eindopdracht.dto.input;

import lombok.Getter;
import lombok.Setter;
import nl.novi.Eindopdracht.Models.Data.*;
import nl.novi.Eindopdracht.Models.Data.Enum.*;

import java.time.LocalDate;

@Getter
@Setter
public class CarDto {

    
    public CarBrand brand;
    public CarModel model;
    public LocalDate yearOfBuild;
    public Colors color;
    public String licensePlate;
    public Integer mileAge;
    public EngineType engineType;
    public Body body;
    public Transmission transmission;
    public Fuel fuel;

    public CustomerAccount account;


}
