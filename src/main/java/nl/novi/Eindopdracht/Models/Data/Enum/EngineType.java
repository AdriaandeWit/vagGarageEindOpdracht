package nl.novi.Eindopdracht.Models.Data.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum EngineType {

    TSI("et1"),
    FSI("et2"),
    tSFI("et3"),
    TDI("et4");

    private final String EngineTypeCode;

    EngineType(String engineTypeCode) {
        EngineTypeCode = engineTypeCode;
    }

    @JsonCreator
    public static EngineType getBodyFormCode(String value) {
        for (EngineType eT : EngineType.values()) {
            if (eT.getEngineTypeCode().equals(value)) {
                return eT;
            }
        }
        return null;

    }
}
