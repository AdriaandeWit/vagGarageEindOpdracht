package nl.novi.Eindopdracht.Models.Data.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import nl.novi.Eindopdracht.Exceptions.EnumNotFoundException;

@Getter
public enum CarBrand {
    AUDI("cb1"),
    VOLKSWAGEN("cb2"),
    SEAT("cb3");

    private final String CarBrandCode;

    CarBrand(String CarBrandCode) {
        this.CarBrandCode = CarBrandCode;
    }

    @JsonCreator
    public static CarBrand getCarBrandFormCarCode(String value) {
        for (CarBrand cb : CarBrand.values()) {
            if (cb.getCarBrandCode().equals(value)) {
                return cb;
            }
        }
        throw new EnumNotFoundException("CarBrand ", "car brand Code" ,value);


    }
}

