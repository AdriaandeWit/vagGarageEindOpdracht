package nl.novi.Eindopdracht.Models.Data.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import nl.novi.Eindopdracht.Exceptions.EnumNotFoundException;

@Getter
public enum Fuel {

        LGP("f1"),
        Petrol("f2"),
        DIESEL("f3");

        private final String fuelTypeCode;

        Fuel(String fuelType) {
            this.fuelTypeCode = fuelType;
        }

    @JsonCreator
    public static Fuel getBodyFormCode(String value) {
        for (Fuel f : Fuel.values()) {
            if (f.getFuelTypeCode().equals(value)) {
                return f;
            }
        }
        throw new EnumNotFoundException("Fuel ", "Fuel Code" ,value);

    }


}
