package nl.novi.Eindopdracht.dto.input;

import lombok.Getter;
import lombok.Setter;
import nl.novi.Eindopdracht.Models.Data.Enum.PartType;
@Getter
@Setter
public class PartDto {
   public Long partID;
   public PartType partType;
}
