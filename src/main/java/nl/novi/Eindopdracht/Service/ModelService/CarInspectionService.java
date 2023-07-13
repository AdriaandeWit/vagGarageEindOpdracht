package nl.novi.Eindopdracht.Service.ModelService;


import nl.novi.Eindopdracht.Exceptions.CarStatusNotFoundException;
import nl.novi.Eindopdracht.Exceptions.InspectionNotFoundException;
import nl.novi.Eindopdracht.Exceptions.RecordNotFoundException;

import nl.novi.Eindopdracht.Models.Data.CarInspection;
import nl.novi.Eindopdracht.Models.Data.CarRepair;
import nl.novi.Eindopdracht.Repository.CarInspectionRepository;
import nl.novi.Eindopdracht.Repository.CarReparationRepository;
import nl.novi.Eindopdracht.Repository.CarRepository;
import nl.novi.Eindopdracht.dto.input.CarInspectionDto;
import nl.novi.Eindopdracht.dto.output.CarInspectionOutputDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarInspectionService {

    private final CarInspectionRepository carInspectionRepos;

    private final CarReparationRepository carRepairRepos;

    private final CarRepository carRepos;

    public CarInspectionService(CarInspectionRepository carInspectionRepos, CarReparationRepository carRepairRepos, CarRepository carRepos) {
        this.carInspectionRepos = carInspectionRepos;
        this.carRepairRepos = carRepairRepos;
        this.carRepos = carRepos;
    }


    public Long createInspection(CarInspectionDto carInspectionDto) {
        CarInspection inspection = DtoToCarInspection(carInspectionDto);
        carInspectionRepos.save(inspection);
        return inspection.getId();
    }

    public CarInspectionOutputDto getInspectionByID(long id) {
        Optional<CarInspection> inspection = carInspectionRepos.findById(id);
        if (inspection.isEmpty()) {
            throw new RecordNotFoundException("Cannot find inspection please enter a anther carId ");
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


    public CarInspectionOutputDto updateMileAge(Long id, CarInspectionDto carInspectionDto) {
        Optional<CarInspection> optionalCarInspection = carInspectionRepos.findById(id);
        if (optionalCarInspection.isPresent()) {
            CarInspection carInspection = optionalCarInspection.get();
            carInspection.setMileAge(carInspectionDto.mileAge);
            carInspectionRepos.save(carInspection);
        } else {
            throw new RecordNotFoundException("Can find " + id + " please enter a anther carId");
        }
        CarInspectionOutputDto outputDto = new CarInspectionOutputDto();
        return outputDto;
    }


    public CarInspectionOutputDto updateInspectionDate(Long id, CarInspectionDto carInspectionDto) {
        Optional<CarInspection> optionalCarInspection = carInspectionRepos.findById(id);
        if (optionalCarInspection.isEmpty()) {
            throw new RecordNotFoundException("cannot find " + id + "please enter a anther carId");

        } else {
            CarInspection carInspection = optionalCarInspection.get();
            carInspection.setInspectionDate(carInspectionDto.inspectionDate);
            carInspectionRepos.save(carInspection);
        }
        CarInspectionOutputDto outputDto = new CarInspectionOutputDto();
        return outputDto;
    }

    public CarInspectionOutputDto updateCarStatus(Long id, CarInspectionDto carInspectionDto) {
        Optional<CarInspection> optionalCarInspection = carInspectionRepos.findById(id);
        if (optionalCarInspection.isEmpty()) {
            throw new CarStatusNotFoundException("status", "id", "id");
        }
        CarInspection latestInspection = optionalCarInspection.get();
        latestInspection.setCarIsCorrect(carInspectionDto.carIsCorrect);
        if (!carInspectionDto.carIsCorrect) {
            latestInspection.setHasProblem(carInspectionDto.hasProblem);
            latestInspection.setCarIsFine(null);
        }
        latestInspection.setCarIsFine(carInspectionDto.carIsFine);
        latestInspection.setHasProblem(null);
        carInspectionRepos.save(latestInspection);

        CarInspectionOutputDto outputDto = new CarInspectionOutputDto();
        return outputDto;
    }

    public void addRepairToInspection(Long id, Long repairId) {
        Optional<CarInspection> optionalCarInspection = carInspectionRepos.findById(id);
        Optional<CarRepair> optionalCarRepair = carRepairRepos.findById(repairId);

        if (optionalCarInspection.isEmpty()) {
            throw new RecordNotFoundException("car inspection", "id ", id);
        }
        if (optionalCarRepair.isEmpty()) {
            throw new RecordNotFoundException("car repair", "repair id ", repairId);
        }
        CarInspection inspection = optionalCarInspection.get();
        CarRepair repair = optionalCarRepair.get();

        inspection.getCarRepair().add(repair);
        carInspectionRepos.save(inspection);

    }

    public String deleteInspectionById(Long id) {
        if (!carInspectionRepos.existsById(id)) {
            throw new InspectionNotFoundException("car", "CarId", id);
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
