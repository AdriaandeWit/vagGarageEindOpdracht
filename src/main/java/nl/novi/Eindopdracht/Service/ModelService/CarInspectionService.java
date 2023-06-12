package nl.novi.Eindopdracht.Service.ModelService;

import nl.novi.Eindopdracht.Exceptions.CarStatusNotFoundException;
import nl.novi.Eindopdracht.Exceptions.InspectionNotFoundException;
import nl.novi.Eindopdracht.Exceptions.RecordNotFoundException;
import nl.novi.Eindopdracht.Models.Data.CarInspection;
import nl.novi.Eindopdracht.Repository.CarInspectionRepository;
import nl.novi.Eindopdracht.dto.input.CarInspectionDto;
import nl.novi.Eindopdracht.dto.output.CarInspectionOutputDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarInspectionService {

    private final CarInspectionRepository carInspectionRepos;

    public CarInspectionService(CarInspectionRepository carInspectionRepos) {
        this.carInspectionRepos = carInspectionRepos;
    }


    public Long createInspection(CarInspectionDto carInspectionDto) {
        CarInspection inspection = DtoToCarInspection(carInspectionDto);
        carInspectionRepos.save(inspection);
        return inspection.getId();
    }

    public CarInspectionOutputDto getInspectionByID(long id) {
        Optional<CarInspection> inspection = carInspectionRepos.findById(id);
        if (inspection.isEmpty()) {
            throw new RecordNotFoundException("Cannot find inspcetion please enter a anther carId ");
        } else {
            CarInspection i = inspection.get();
            return inspectionToDto(i);
        }
    }

    public List<CarInspectionOutputDto> getAllInspections() {
        List<CarInspectionOutputDto> collectionOff = new ArrayList<>();
        List<CarInspection> list = carInspectionRepos.findAll();
        if (list.isEmpty()) {
            throw new RecordNotFoundException("there are no records found on please check a anther carId");
        } else {
            for (CarInspection i : list) {
                collectionOff.add(inspectionToDto(i));
            }
            return collectionOff;
        }
    }


    public CarInspectionDto updateMileAge(Long id, int milleAge) {
        Optional<CarInspection> optionalCarInspection = carInspectionRepos.findById(id);
        if (optionalCarInspection.isPresent()) {
            CarInspection carInspection = optionalCarInspection.get();
            carInspection.setMileAge(milleAge);
            carInspectionRepos.save(carInspection);
        } else {
            throw new RecordNotFoundException("Can find " + id + " please enter a anther carId");
        }
        return null;
    }


    public CarInspectionDto updateInspectionDate(Long id, LocalDate inspectionDate) {
        Optional<CarInspection> optionalCarInspection = carInspectionRepos.findById(id);
        if (optionalCarInspection.isPresent()) {
            CarInspection carInspection = optionalCarInspection.get();
            carInspection.setInspectionDate(inspectionDate);
            carInspectionRepos.save(carInspection);
        } else {
            throw new RecordNotFoundException("cannot find " + id + "please enter a anther carId");

        }
        return null;
    }


    public CarInspectionDto updateCarIsFine(Long id, String carIsFine) {
        Optional<CarInspection> optionalCarInspection = carInspectionRepos.findById(id);
        if (optionalCarInspection.isEmpty()) {
            throw new RecordNotFoundException("cannot find " + id + "please enter a anther carId");

        } else {
            CarInspection carInspection = optionalCarInspection.get();
            carInspection.setCarIsFine(carIsFine);
            carInspectionRepos.save(carInspection);
        }
        return null;
    }

    public CarInspectionDto updateHasProblem(Long id, String hasProblem) {
        Optional<CarInspection> optionalCarInspection = carInspectionRepos.findById(id);
        if (optionalCarInspection.isEmpty()) {
            throw new RecordNotFoundException("cannot find " + id + "please enter a anther carId");
        } else {
            CarInspection carInspection = optionalCarInspection.get();
            carInspection.setHasProblem(hasProblem);
            carInspectionRepos.save(carInspection);
        }
        return null;

    }

    public void updateCarStatus(Long id, boolean carIsCorrect) {
        Optional<CarInspection> optionalCarInspection = carInspectionRepos.findById(id);
        if (optionalCarInspection.isEmpty()) {
            throw new CarStatusNotFoundException("status", "id", "id");
        } else {
            CarInspection latestInspection = optionalCarInspection.get();
            latestInspection.setCarIsCorrect(carIsCorrect);
            carInspectionRepos.save(latestInspection);
        }
    }


    public String deleteInspectionById(Long id) {
        if (!carInspectionRepos.existsById(id)) {
            throw new InspectionNotFoundException("Inspection with carId" + id + "is not found");
        } else {
            Long count = carInspectionRepos.count();
            carInspectionRepos.deleteById(id);
            return "you deleted " + count + " in the " + id;
        }
    }

    public String deleteAllInspections() {
        Long count = carInspectionRepos.count();
        carInspectionRepos.deleteAll();
        return "We deleted " + count + " inspections";
    }

    public CarInspection DtoToCarInspection(CarInspectionDto inspectionDto) {
        CarInspection inspection = new CarInspection();
        inspection.setId(inspectionDto.id);
        inspection.setMileAge(inspectionDto.mileAge);
        inspection.setLicensePlate(inspectionDto.licensePlate);
        inspection.setInspectionDate(inspectionDto.inspectionDate);
        inspection.setCarIsCorrect(inspectionDto.carIsCorrect);
        inspection.setCarIsFine(inspectionDto.carIsFine);
        inspection.setHasProblem(inspectionDto.hasProblem);


        return inspection;
    }


    public CarInspectionOutputDto inspectionToDto(CarInspection carInspection) {
        CarInspectionOutputDto dto = new CarInspectionOutputDto();

        dto.id = carInspection.getId();
        dto.mileAge = carInspection.getMileAge();
        dto.licensePlate = carInspection.getLicensePlate();
        dto.inspectionDate = carInspection.getInspectionDate();
        dto.carIsCorrect = carInspection.isCarIsCorrect();
        dto.carIsFine = carInspection.getCarIsFine();
        dto.hasProblem = carInspection.getHasProblem();

        return dto;

    }


}


