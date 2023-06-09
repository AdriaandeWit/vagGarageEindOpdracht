package nl.novi.Eindopdracht.Models.Data.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import nl.novi.Eindopdracht.Exceptions.EnumNotFoundException;

@Getter
public enum Transmission {
        Manual("t1"),
        Automatic("t2"),
        SEMIAUTOMATIC("t3");

        private final String transmissionTypeCode;

        Transmission(String transmissionType) {
            this.transmissionTypeCode = transmissionType;
        }

    @JsonCreator
    public static Transmission getBodyFormCode(String value) {
        for (Transmission t : Transmission.values()) {
            if (t.getTransmissionTypeCode().equals(value)) {
                return t;
            }
        }
        throw new EnumNotFoundException("Enum", "transmission", value);

    }


}
