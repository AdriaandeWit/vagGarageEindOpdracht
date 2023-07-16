package nl.novi.Eindopdracht.dto.output.CarPartsDto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
@Getter
@Setter
public class TyresOutputDto extends CarPartsOutputDto {
    public Double tyresHight;
    public Double tyresWidth;
    public int diameter;
    public String loadIndex;
    public String speedIndex;
    public LocalDate productionDate;
}
