package nl.novi.Eindopdracht.dto.input;

import lombok.Getter;
import lombok.Setter;
import nl.novi.Eindopdracht.Models.Data.Car;
import nl.novi.Eindopdracht.Models.Data.CarRepair;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class CarInspectionDto {



    public int mileAge;

    public String licensePlate;

    public LocalDate inspectionDate;
    public boolean carIsCorrect;
    public String carIsFine;

    public String hasProblem;

    private Car Car;

    private List<CarRepair> carRepairList;
}
