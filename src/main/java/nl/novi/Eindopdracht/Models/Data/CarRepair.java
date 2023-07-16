package nl.novi.Eindopdracht.Models.Data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.novi.Eindopdracht.Models.Data.CarParts.CarParts;
import nl.novi.Eindopdracht.Models.Data.Enum.CarBrand;
import nl.novi.Eindopdracht.Models.Data.Enum.PartType;

import javax.naming.Name;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "car_repairs")
public class CarRepair {
    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private CarBrand car;
    private String carProblem;
    private LocalDate repairDate;
    private Double partCost;
    private Double laborCost;
    private Double totalCost;

    @OneToMany(mappedBy = "carRepair")
    private List<CarParts> carParts;


}
