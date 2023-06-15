package nl.novi.Eindopdracht.Models.Data.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import nl.novi.Eindopdracht.Exceptions.EnumNotFoundException;

@Getter
public enum Fuel {
    LGP,
    PETROL,
    DIESEL,
    NOT_FOUND_EXCEPTION;

    @JsonCreator
    public static Fuel fromString(String value) {
        try {
            return Fuel.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Fuel.NOT_FOUND_EXCEPTION;
        }
    }


}
