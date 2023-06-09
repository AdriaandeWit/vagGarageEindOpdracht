package nl.novi.Eindopdracht.Models.Data.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum Body {
    SUV("b1"),
    HATCHBACK("b2"),
    SEDAN("b3"),
    STATIONWAGON("b4"),
    MPV("b5"),
    COUPE("b6"),
    CABRIOLET("b7");

    private final String bodyCode;

    Body(String bodyCode) {
        this.bodyCode = bodyCode;
    }

    @JsonCreator
    public static Body getBodyFormCode(String value) {
        for (Body b : Body.values()) {
            if (b.getBodyCode().equals(value)) {
                return b;
            }
        }
        return null;

    }
}
