package nl.novi.Eindopdracht.Models.Data;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table
public class CarInspection {
    @Id
    @Column
    @GeneratedValue

    private Long id;
    @Column
    private int mileAge;
    @Column(unique = false, nullable = false)
    @Pattern(regexp = "[A-Za-z0-9]{1,2}-[A-Za-z0-9]{2,3}-[A-Za-z0-9]{2}")
    private String licensePlate;
    @Column
    private LocalDate inspectionDate;
    @Column
    private boolean carIsCorrect;
    @Column
    private String carIsFine;
    @Column
    private String hasProblem;

    //  @OneToOne(mappedBy ="Car")
    @OneToOne(mappedBy = "carInspection")
    private Car Car;

    @OneToMany
    private List<CarRepair> carRepair;


}




