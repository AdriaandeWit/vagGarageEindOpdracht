package nl.novi.Eindopdracht.Models.Data.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import nl.novi.Eindopdracht.Exceptions.EnumNotFoundException;

@Getter
public enum CarBrand {
    AUDI,
    VOLKSWAGEN,
    SEAT,
    NOT_FOUND_EXCEPTION;


    @JsonCreator
    public static CarBrand fromString(String value) {
        try {
            return CarBrand.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            return CarBrand.NOT_FOUND_EXCEPTION;
        }
    }

}


