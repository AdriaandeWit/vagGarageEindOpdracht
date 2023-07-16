package nl.novi.Eindopdracht.Models.Data.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;


@Getter
public enum Colors {
    BLACK,
    WHITE,
    SILVER,
    GRAY,
    BLUE,
    RED,
    BROWN,
    GREEN,
    YELLOW,
    ORANGE,
    NOT_FOUND_EXCEPTION;


    @JsonCreator
    public static Colors fromString(String value) {
        try {
            return Colors.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Colors.NOT_FOUND_EXCEPTION;
        }
    }

}
