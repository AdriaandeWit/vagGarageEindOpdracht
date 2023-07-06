package nl.novi.Eindopdracht.dto.input.CarPartsDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SparkPlugDto extends CarPartsDto {
    @NotBlank(message = "we need a spanner size")
    public int spannerSize;
    @NotBlank(message = "we need a quality")
    public String quality;
    @NotBlank(message = "we need a warmth degree")
    public int warmthDegree;
    @NotBlank(message = "we need a thread lengthe")
    public Double threadLength;
    @NotBlank(message = "we need a torque")
    public int torque;
    @NotBlank(message = "we need a spark position")
    public int sparkPosition;
}
