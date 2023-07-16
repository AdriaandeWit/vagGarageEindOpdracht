package nl.novi.Eindopdracht.Models.Data.Enum;


import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;


@Getter
public enum CarModel {
    A1,
    A3,
    A4,
    A5,
    A6,
    A7,
    A8,
    Q2,
    Q3,
    Q5,
    Q7,
    Q8,
    R8,
    TT,
    UP,
    POLO,
    GOLF,
    GOLF_VARIANT,
    TAIGO,
    T_CROSS,
    T_ROC,
    TIGUAN,
    TIGUAN_ALLSPACE,
    PASSAT,
    PASSAT_VARIANT,
    ARTEON,
    ARTEON_SHOOTING_BRAKE,
    MULTIVAN,
    IBIZA,
    ARONA,
    LEON,
    LEON_SPORTSTOURER,
    ATECA,
    TARRACO,
    NOT_FOUND_EXCEPTION;

    @JsonCreator
    public static CarModel fromString(String value) {
        try {
            return CarModel.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            return CarModel.NOT_FOUND_EXCEPTION;
        }
    }
}





