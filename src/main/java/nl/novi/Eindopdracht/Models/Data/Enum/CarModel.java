package nl.novi.Eindopdracht.Models.Data.Enum;


import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import nl.novi.Eindopdracht.Exceptions.EnumNotFoundException;

@Getter
public enum CarModel {
    A1("cm1"),
    A3("cm2"),
    A4("cm3"),
    A5("cm4"),
    A6("cm5"),
    A7("cm6"),
    A8("cm7"),
    Q2("cm8"),
    Q3("cm9"),
    Q5("cm10"),
    Q7("cm11"),
    Q8("cm12"),
    R8("cm13"),
    TT("cm14"),
    UP("cm15"),
    POLO("cm16"),
    GOLF("cm17"),
    GOLF_VARIANT("cm18"),
    TAIGO("cm19"),
    T_CROSS("cm20"),
    T_ROC("cm21"),
    TIGUAN("cm22"),
    TIGUAN_ALLSPACE("cm23"),
    PASSAT("cm24"),
    PASSAT_VARIANT("cm25"),
    ARTEON("cm26"),
    ARTEON_SHOOTING_BRAKE("cm27"),
    MULTIVAN("cm28"),
    IBIZA("cm29"),
    ARONA("cm30"),
    LEON("cm31"),
    LEON_SPORTSTOURER("cm32"),
    ATECA("cm33"),
    TARRACO("cm34");
    private final String CarModelCode;

    CarModel(String bodyCode) {
        this.CarModelCode = bodyCode;
    }

    @JsonCreator
    public static CarModel getBodyFormCode(String value) {
        for (CarModel cM : CarModel.values()) {
            if (cM.getCarModelCode().equals(value)) {
                return cM;
            }
        }
        throw new EnumNotFoundException("CarModel", "body code", value);
    }
}




