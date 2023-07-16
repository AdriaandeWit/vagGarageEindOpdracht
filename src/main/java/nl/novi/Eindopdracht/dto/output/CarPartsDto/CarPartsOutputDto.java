package nl.novi.Eindopdracht.dto.output.CarPartsDto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
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
        @JsonSubTypes.Type(value = BrakesOutputDto.class, name = "BRAKE"),
        @JsonSubTypes.Type(value = TyresOutputDto.class, name = "TYRES"),
        @JsonSubTypes.Type(value = SparkPlugOutputDto.class,name = "SPARKPLUG")
})
@Getter
@Setter
public abstract class CarPartsOutputDto {
    public Long id;
    public PartType partType;
    public String partName;
    public String partNumber;
    public Double price;
    public Integer amountOfParts;

}
