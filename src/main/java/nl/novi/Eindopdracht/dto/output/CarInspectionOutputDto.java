package nl.novi.Eindopdracht.dto.output;

import lombok.Getter;
import lombok.Setter;

import nl.novi.Eindopdracht.Models.Data.Car;
import nl.novi.Eindopdracht.Models.Data.CarRepair;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class CarInspectionOutputDto {


    public Long id;
    public int mileAge;
    public String licensePlate;
    public LocalDate inspectionDate;
    public boolean carIsCorrect;
    public String carIsFine;

    public String hasProblem;


    public CarOutputDto carOutputDto;

    public List<CarRepairOutputDto> carRepairOutputDtoList;

}
