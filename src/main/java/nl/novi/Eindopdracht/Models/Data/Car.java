package nl.novi.Eindopdracht.Models.Data;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.novi.Eindopdracht.Models.Data.Enum.*;


import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "cars")
public class Car {
    @Column
    @GeneratedValue
    private Long id;
    @Column
    @Enumerated(EnumType.STRING)
    private CarBrand brand;
    @Column
    @Enumerated(EnumType.STRING)
    private CarModel model;
    @Column
    private LocalDate yearOfBuild;
    @Column
    @Enumerated(EnumType.STRING)
    private Colors color;
    @Id
    @Column(unique = true,nullable = false)
    @Pattern(regexp = "[A-Za-z0-9]{2}-[A-Za-z0-9]{3}-[A-Za-z0-9]{2}")
    private String licensePlate;
    @Column
    @Enumerated(EnumType.STRING)
    private Integer mileAge;
    @Column
    @Enumerated(EnumType.STRING)
    private EngineType engineType;
    @Column
    @Enumerated(EnumType.STRING)
    private Body body;
    @Column
    @Enumerated(EnumType.STRING)
    private Transmission transmission;
    @Column
    @Enumerated(EnumType.STRING)
    private Fuel fuel;

    @ManyToOne
    private CustomerAccount account;

    @OneToOne
    private CarInspection carInspection;


}


