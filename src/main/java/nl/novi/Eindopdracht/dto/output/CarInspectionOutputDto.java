package nl.novi.Eindopdracht.dto.output;

import java.time.LocalDate;

public class CarInspectionOutputDto {


    public Long id;
    public int mileAge;
    public String licensePlate;
    public LocalDate inspectionDate;
    public boolean carIsCorrect;
    public String carIsFine;

    public String hasProblem;

}
