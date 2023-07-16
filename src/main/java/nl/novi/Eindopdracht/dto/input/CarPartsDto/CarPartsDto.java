package nl.novi.Eindopdracht.dto.input.CarPartsDto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import nl.novi.Eindopdracht.Models.Data.Enum.PartType;

@JsonTypeInfo(
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "partType",
        use = JsonTypeInfo.Id.NAME,
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = BrakesDto.class, name = "BRAKE"),
        @JsonSubTypes.Type(value = TyresDto.class, name = "TYRES"),
        @JsonSubTypes.Type(value = SparkPlugDto.class, name = "SPARKPLUG")
})
@Getter
@Setter
public abstract class CarPartsDto {
    public Long id;
    @Enumerated(EnumType.STRING)
    public PartType partType;
    @NotBlank
    public String partName;
    @Pattern(regexp = "[A-Za-z]{1,2}-[0-9]{3}", message = "part number must be start with a letter before you set the numbers")
    public String partNumber;
    @Min(value = 0L, message = "the value must be positive")
    public Double price;
    @NotBlank
    public Integer amountOfParts;


}


