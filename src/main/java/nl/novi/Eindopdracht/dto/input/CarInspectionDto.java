package nl.novi.Eindopdracht.dto.input;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import nl.novi.Eindopdracht.Models.Data.Car;
import nl.novi.Eindopdracht.Models.Data.CarRepair;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class CarInspectionDto {

    public  Long id;
    @Min(value = 0, message = "the value must be positive")
    public int mileAge;
    @Pattern(regexp = "[A-Za-z0-9]{1,2}-[A-Za-z0-9]{2,3}-[A-Za-z0-9]{2}")
    public String licensePlate;

    public LocalDate inspectionDate;
    public boolean carIsCorrect;
    public String carIsFine;

    public String hasProblem;


    private Car Car;

    private List<CarRepair> carRepairList;
}
