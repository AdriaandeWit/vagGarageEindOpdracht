package nl.novi.Eindopdracht.Models.Data.CarParts;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import nl.novi.Eindopdracht.Models.Data.CarRepair;
import nl.novi.Eindopdracht.Models.Data.Enum.PartType;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table
public class Brakes extends CarParts {


    private Double outerDiameter;
    private Double centerDiameter;
    private Double height;
    private Double minThickness;
    private Double surface;
    private Double discThickness;
    private String boreTypeNumberOfHoles;
    private Double wheelStudDiameter;
    private Boolean withoutWheelMountingBolts;
    private Boolean withoutWheelHub;

    public Brakes( Long id, @NonNull PartType partType, @NonNull String partName, @NonNull String partNumber, @NonNull Double price, @NonNull Integer amountOfParts, List<CarRepair> carRepair, Double outerDiameter, Double centerDiameter, Double height, Double minThickness, Double surface, Double discThickness, String boreTypeNumberOfHoles, Double wheelStudDiameter, Boolean withoutWheelMountingBolts, Boolean withoutWheelHub) {
        super(id,partType, partName, partNumber, price, amountOfParts, carRepair);
        this.outerDiameter = outerDiameter;
        this.centerDiameter = centerDiameter;
        this.height = height;
        this.minThickness = minThickness;
        this.surface = surface;
        this.discThickness = discThickness;
        this.boreTypeNumberOfHoles = boreTypeNumberOfHoles;
        this.wheelStudDiameter = wheelStudDiameter;
        this.withoutWheelMountingBolts = withoutWheelMountingBolts;
        this.withoutWheelHub = withoutWheelHub;
    }
}
