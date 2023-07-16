package nl.novi.Eindopdracht.dto.input.CarPartsDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter

public class TyresDto extends CarPartsDto {
    @NotBlank(message = "we need a tyre hight")
    public Double tyresHight;
    @NotBlank(message = "we need a tyre with")
    public Double tyresWidth;
    @NotBlank(message = "we need a diameter")
    public int diameter;
    @NotBlank(message = "we need a laod index")
    @Pattern(regexp = "[A-Z]-[0-9]{1,2}")
    public String loadIndex;
    @NotBlank
    @Pattern(regexp = "[A-Z]")
    public String speedIndex;

    @PastOrPresent(message = "the production of the tyre must be in the past not in the further ")
    public LocalDate productionDate;
}
