package nl.novi.Eindopdracht.dto.input.CarPartsDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.security.core.parameters.P;

@Getter
@Setter
public class BrakesDto extends CarPartsDto {
    @NotBlank
    @PositiveOrZero(message = "this field must be a have a positive number")
    public Double outerDiameter;
    @NotBlank
    @PositiveOrZero(message = "this field must be a have a positive number")
    public Double centerDiameter;
    @NotBlank
    @PositiveOrZero(message = "this field must be a have a positive number")
    public Double height;
    @NotBlank
    @PositiveOrZero(message = "this field must be a have a positive number")
    public Double minThickness;
    @NotBlank
    @PositiveOrZero(message = "this field must be a have a positive number")
    public Double surface;
    @NotBlank
    @PositiveOrZero(message = "this field must be a have a positive number")
    public Double discThickness;
    @NotBlank
    @Pattern(regexp = "[A-Za-z0-9]{5,10}", message = "make for example patterns with 5x100")
    public String boreTypeNumberOfHoles;
    @NotBlank(message = "this field must be a have a positive number")
    public Double wheelStudDiameter;
    @NonNull
    public Boolean withoutWheelMountingBolts;
    @NotBlank
    public Boolean withoutWheelHub;


}
