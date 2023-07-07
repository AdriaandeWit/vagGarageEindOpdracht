package nl.novi.Eindopdracht.dto.input;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;
import nl.novi.Eindopdracht.Models.Data.Enum.CarBrand;
import nl.novi.Eindopdracht.Models.Data.Enum.PartType;
import nl.novi.Eindopdracht.dto.input.CarPartsDto.CarPartsDto;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter

public class CarRepairDto {
    public Long id;
    @NotBlank(message = "please enter a vag model with capital letters")
    @Enumerated(EnumType.STRING)
    public CarBrand Car;
    @NotBlank(message = "please enter a problem")
    public String carProblem;
    @PastOrPresent(message = "please enter a date that is in the past or present day")
    public LocalDate repairDate;
    @NotNull(message = "we need a value if it is 0 or 1 thaht is fine, butt not null")
    public Double partCost;
    @NotNull(message = "we need a value if it is 0 or 1 thaht is fine, butt not null")
    public Double laborCost;
    public Double totalCost;

    private List<CarPartsDto> carParts;
    public PartType partType;

}
