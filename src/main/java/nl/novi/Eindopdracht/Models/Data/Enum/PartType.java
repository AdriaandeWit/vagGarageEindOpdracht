package nl.novi.Eindopdracht.Models.Data.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
@Getter
public enum PartType {
    BRAKE,
    TYRES,
    SPARK_PLUG,
     NOT_FOUND_EXCEPTION;

    @JsonCreator
    public static PartType fromString(String value) {
        try {
            return PartType.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            return PartType.NOT_FOUND_EXCEPTION;
        }
    }
}
