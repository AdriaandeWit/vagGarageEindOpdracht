package nl.novi.Eindopdracht.Models.Data.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;


@Getter
public enum Body {
    SUV,
    HATCHBACK,
    SEDAN,
    STATIONWAGON,
    MPV,
    COUPE,
    CABRIOLET,
    NOT_FOUND_EXCEPTION;


    @JsonCreator
    public static Body fromString(String value) {
        try {
            return Body.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Body.NOT_FOUND_EXCEPTION;
        }
    }
}


