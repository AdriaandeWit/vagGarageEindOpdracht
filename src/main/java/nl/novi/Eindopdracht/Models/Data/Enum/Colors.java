package nl.novi.Eindopdracht.Models.Data.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import nl.novi.Eindopdracht.Exceptions.EnumNotFoundException;

@Getter
public enum Colors {
    BLACK("c1"),
    WHITE("c2"),
    SILVER("c3"),
    GRAY("c4"),
    BLUE("c5"),
    RED("c6"),
    BROWN("c7"),
    GREEN("c8"),
    YELLOW("c9"),
    ORANGE("c10");

    private String ColorCode;

    Colors(String ColorCode) {
        this.ColorCode = ColorCode;
    }

    @JsonCreator
    public static Colors getColorsFromColorCode(String value) {
        for (Colors c : Colors.values()) {
            if (c.getColorCode().equals(value)) {
                return c;
            }
        }
        throw new EnumNotFoundException("Color ", "Color Code", value);
    }

}
