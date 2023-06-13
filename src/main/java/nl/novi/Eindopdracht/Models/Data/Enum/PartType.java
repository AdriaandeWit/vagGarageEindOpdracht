package nl.novi.Eindopdracht.Models.Data.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import nl.novi.Eindopdracht.Exceptions.EnumNotFoundException;
@Getter
public enum PartType {
    Brake("brake"),
    Tyres("tyres"),
    SparkPlug("Sparkplug");


    private final String partTypeCode;

    PartType(String partType) {
        this.partTypeCode = partType;
    }

    @JsonCreator
    public static PartType getBodyFormCode(String value) {
        for (PartType pt : PartType.values()) {
            if (pt.getPartTypeCode().equals(value)) {
                return pt;
            }
        }
        throw new EnumNotFoundException("Fuel ", "Fuel Code" ,value);

    }
}
