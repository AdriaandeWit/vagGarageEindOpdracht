package nl.novi.Eindopdracht.dto.input;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import nl.novi.Eindopdracht.Models.Data.*;
import nl.novi.Eindopdracht.Models.Data.Enum.*;

import java.time.LocalDate;

@Getter
@Setter
public class CarDto {

    @Enumerated(EnumType.STRING)
    @NotBlank(message = "please enter a vag car brand with capital letters")
    public CarBrand brand;
    @Enumerated(EnumType.STRING)
    @NotBlank(message = "please enter a vag model with capital letters")
    public CarModel model;
    @Enumerated(EnumType.STRING)
    @PastOrPresent
    public LocalDate yearOfBuild;
    @Enumerated(EnumType.STRING)
    @NotBlank(message = "please enter a color with capital letters")
    public Colors color;
    @NotBlank
    @Pattern(regexp = "[A-Za-z0-9]{1,2}-[A-Za-z0-9]{2,3}-[A-Za-z0-9]{1,2}", message = "make for example patterns like JF-800-l or J-800-RF ")
    public String licensePlate;
    @Min(value = 0, message = "the value must be positive")
    public Integer mileAge;
    @Enumerated(EnumType.STRING)
    @NotBlank(message = "please enter a vag engine with capital letters")
    public EngineType engineType;
    @Enumerated(EnumType.STRING)
    @NotBlank(message = "please enter a car body with capital letters")
    public Body body;
    @Enumerated(EnumType.STRING)
    @NotBlank(message = "please enter a vag transmission with capital letters")
    public Transmission transmission;
    @Enumerated(EnumType.STRING)
    @NotBlank(message = "please enter a fuel with capital letters")
    public Fuel fuel;

    public CustomerAccount account;

    public CarInspection carInspection;


}
