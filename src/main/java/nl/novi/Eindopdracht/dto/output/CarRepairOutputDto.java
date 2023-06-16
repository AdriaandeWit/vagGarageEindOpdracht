package nl.novi.Eindopdracht.dto.output;

import lombok.Getter;
import lombok.Setter;
import nl.novi.Eindopdracht.Models.Data.Enum.PartType;
import nl.novi.Eindopdracht.dto.output.CarPartsDto.CarPartsOutputDto;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class CarRepairOutputDto {

    public Long id;
    public String car;
    public String carProblem;
    public LocalDate repairDate;
    public Double partCost;
    public Double laborCost;
    public Double totalCost;

    public List<CarPartsOutputDto> carParts;

    public PartType partType;
}
