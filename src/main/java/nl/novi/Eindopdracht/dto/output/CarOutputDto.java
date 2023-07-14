package nl.novi.Eindopdracht.dto.output;


import lombok.Getter;
import lombok.Setter;
import nl.novi.Eindopdracht.Models.Data.CustomerAccount;
import nl.novi.Eindopdracht.Models.Data.Enum.*;


import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class CarOutputDto {

    public Long id;
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
    public CustomerAccountOutputDto customerAccountOutputDto;

    public List<CarInspectionOutputDto> carInspectionOutputDtos;


}
