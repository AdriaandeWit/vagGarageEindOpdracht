package nl.novi.Eindopdracht.dto.input;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import nl.novi.Eindopdracht.Models.Data.*;
import nl.novi.Eindopdracht.Models.Data.Enum.*;
import nl.novi.Eindopdracht.Utils.validatie.ValueOfEnum;

import java.time.LocalDate;

@Getter
@Setter
public class CarDto {

    @Enumerated(EnumType.STRING)
    @ValueOfEnum(enumClass = CarBrand.class)
    public CarBrand brand;
    @Enumerated(EnumType.STRING)
    @ValueOfEnum(enumClass = CarModel.class)
    public CarModel model;
    @Enumerated(EnumType.STRING)
    @PastOrPresent
    public LocalDate yearOfBuild;
    @Enumerated(EnumType.STRING)
    @ValueOfEnum(enumClass = Colors.class)
    public Colors color;
    @NotBlank
    @Pattern(regexp = "[A-Za-z0-9]{1,2}-[A-Za-z0-9]{2,3}-[A-Za-z0-9]{1,2}", message = "make for example patterns like JF-800-l or J-800-RF ")
    public String licensePlate;
    @Min(value = 0, message = "the value must be positive")
    public Integer mileAge;
    @Enumerated(EnumType.STRING)
    @ValueOfEnum(enumClass = EngineType.class)
    public EngineType engineType;
    @Enumerated(EnumType.STRING)
    @ValueOfEnum(enumClass = Body.class)
    public Body body;
    @Enumerated(EnumType.STRING)
    @ValueOfEnum(enumClass = Transmission.class)
    public Transmission transmission;
    @Enumerated(EnumType.STRING)
    @ValueOfEnum(enumClass = Fuel.class)
    public Fuel fuel;

    public CustomerAccount account;

    public CarInspection carInspection;

}
