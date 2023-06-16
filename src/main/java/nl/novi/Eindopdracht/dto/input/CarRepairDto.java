package nl.novi.Eindopdracht.dto.input;

import lombok.Getter;
import lombok.Setter;
import nl.novi.Eindopdracht.Models.Data.Enum.PartType;
import nl.novi.Eindopdracht.dto.input.CarPartsDto.CarPartsDto;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter

public class CarRepairDto {
    public Long id;

    public String Car;
    public String carProblem;
    public LocalDate repairDate;
    public Double partCost;
    public Double laborCost;
    public Double totalCost;

    private List<CarPartsDto> carParts;
    public PartType partType;

}
