package nl.novi.Eindopdracht.Models.Data.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum EngineType {

    TSI,
    FSI,
    TSFI,
    TDI,
    NOT_FOUND_EXCEPTION;



    @JsonCreator
    public static EngineType fromString(String value) {
        try {
            return EngineType.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            return EngineType.NOT_FOUND_EXCEPTION;
        }
    }
}
