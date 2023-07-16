package nl.novi.Eindopdracht.Models.Data.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;


@Getter
public enum Transmission {
    MANUAL,
    AUTOMATIC,
    SEMIAUTOMATIC,
    NOT_FOUND_EXCEPTION;


    @JsonCreator
    public static Transmission fromString(String value) {
        try {
            return Transmission.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Transmission.NOT_FOUND_EXCEPTION;
        }
    }


}
