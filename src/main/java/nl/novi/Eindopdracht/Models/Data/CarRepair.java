package nl.novi.Eindopdracht.Models.Data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.novi.Eindopdracht.Models.Data.CarParts.CarParts;
import nl.novi.Eindopdracht.Models.Data.Enum.PartType;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
public class CarRepair {
@Id
@GeneratedValue
private Long id;
private String car;
//@Enumerated(EnumType.STRING)
  //  public PartType partType;
private String carProblem;
private LocalDate repairDate;
private Double partCost;
private Double laborCost;
private Double totalCost;

@OneToMany
private List<CarParts> carParts;


}
