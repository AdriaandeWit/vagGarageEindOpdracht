package nl.novi.Eindopdracht.dto.input.CarPartsDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrakesDto extends CarPartsDto {

    public Double outerDiameter;
    public Double centerDiameter;
    public Double height;
    public Double minThickness;
    public Double surface;
    public Double discThickness;
    public String boreTypeNumberOfHoles;
    public Double wheelStudDiameter;
    public Boolean withoutWheelMountingBolts;
    public Boolean withoutWheelHub;


}
