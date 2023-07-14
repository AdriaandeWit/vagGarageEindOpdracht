package nl.novi.Eindopdracht.Models.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.novi.Eindopdracht.Models.Data.Enum.*;
import nl.novi.Eindopdracht.Models.Security.Authority;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @Column
    @Pattern(regexp = "[A-Za-z0-9]{1,2}-[A-Za-z0-9]{2,3}-[A-Za-z0-9]{1,2}")
    private String licensePlate;
    @Column
    @Min(value = 0)
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    @JsonIgnore
    private CustomerAccount account;

    @OneToMany(targetEntity = CarInspection.class,
            mappedBy = "Car")
    private List<CarInspection>  carInspections;


    public void addCarInspection(CarInspection carInspection){
        this.carInspections.add(carInspection);
    }




}


