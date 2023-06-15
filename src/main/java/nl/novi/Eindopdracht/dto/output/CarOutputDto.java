package nl.novi.Eindopdracht.dto.output;


import lombok.Getter;
import lombok.Setter;
import nl.novi.Eindopdracht.Models.Data.*;
import nl.novi.Eindopdracht.Models.Data.Enum.*;


import java.time.LocalDate;

@Getter
@Setter
public class CarOutputDto {


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

    public CarInspection carInspection;


}
