package nl.novi.Eindopdracht.Repository;

import nl.novi.Eindopdracht.Models.Data.Car;
import nl.novi.Eindopdracht.Models.Data.CarInspection;
import nl.novi.Eindopdracht.dto.output.CarInspectionOutputDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarInspectionRepository extends JpaRepository<CarInspection,Long> {



}
