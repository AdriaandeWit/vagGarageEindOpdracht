package nl.novi.Eindopdracht.Models.Data.CarParts;


import jakarta.persistence.*;
import lombok.*;
import nl.novi.Eindopdracht.Models.Data.CarRepair;
import nl.novi.Eindopdracht.Models.Data.Enum.PartType;

import java.util.List;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class CarParts {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    @Enumerated(EnumType.STRING)
    private PartType partType;
    @NonNull
    private String partName;
    @NonNull
     private String partNumber;
    @NonNull
    private Double price;
    @NonNull
    private Integer amountOfParts;


    @ManyToOne
    @JoinColumn(name = "car_repair_id")
    private CarRepair carRepair;

}
