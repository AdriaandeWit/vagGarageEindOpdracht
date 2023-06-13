package nl.novi.Eindopdracht.dto.input.CarPartsDto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import nl.novi.Eindopdracht.Models.Data.Enum.PartType;

@JsonTypeInfo(
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "PartType",
        use = JsonTypeInfo.Id.NAME,
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = BrakesDto.class, name = "Brake"),
        @JsonSubTypes.Type(value = TyresDto.class, name = "Tyres"),
        @JsonSubTypes.Type(value = SparkPlugDto.class,name = "SparkPlug")
})
@Getter
@Setter
public abstract class CarPartsDto {
    public Long id;
    public PartType partType;
    public String partName;
    public String partNumber;
    public Double price;
    public Integer amountOfParts;


}


