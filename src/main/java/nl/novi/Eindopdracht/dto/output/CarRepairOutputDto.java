package nl.novi.Eindopdracht.dto.output;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import nl.novi.Eindopdracht.Models.Data.Enum.CarBrand;
import nl.novi.Eindopdracht.Models.Data.Enum.PartType;
import nl.novi.Eindopdracht.dto.output.CarPartsDto.CarPartsOutputDto;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class CarRepairOutputDto {

    public Long id;
    public CarBrand car;
    public String carProblem;
    public LocalDate repairDate;
    public Double partCost;
    public Double laborCost;
    public Double totalCost;

    public List<CarPartsOutputDto> carParts;

}
