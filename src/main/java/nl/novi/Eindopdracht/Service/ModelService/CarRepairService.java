package nl.novi.Eindopdracht.Service.ModelService;

import nl.novi.Eindopdracht.Exceptions.RecordNotFoundException;

import nl.novi.Eindopdracht.Models.Data.CarParts.Brakes;
import nl.novi.Eindopdracht.Models.Data.CarParts.SparkPlug;
import nl.novi.Eindopdracht.Models.Data.CarParts.Tyres;
import nl.novi.Eindopdracht.Models.Data.CarRepair;
import nl.novi.Eindopdracht.Models.Data.Enum.PartType;
import nl.novi.Eindopdracht.Repository.BrakeRepository;
import nl.novi.Eindopdracht.Repository.CarReparationRepository;

import nl.novi.Eindopdracht.Repository.SprakPlugRepository;
import nl.novi.Eindopdracht.Repository.TyreRepository;
import nl.novi.Eindopdracht.dto.input.CarRepairDto;

import nl.novi.Eindopdracht.dto.output.CarRepairOutputDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CarRepairService {

    private final CarReparationRepository repairRepos;

    private final BrakeRepository brakeRepos;
    private final TyreRepository tyreRepos;
    private final SprakPlugRepository sprakPlugRepos;

    public CarRepairService(CarReparationRepository repairRepos, BrakeRepository brakeRepos, TyreRepository tyreRepos, SprakPlugRepository sprakPlugRepos) {
        this.repairRepos = repairRepos;
        this.brakeRepos = brakeRepos;
        this.tyreRepos = tyreRepos;
        this.sprakPlugRepos = sprakPlugRepos;
    }


    public Long createCarReport(CarRepairDto carRepairDto) {
        CarRepair carRepair = DtoToRepair(carRepairDto);

        repairRepos.save(carRepair);
        return carRepair.getId();
    }

    public CarRepairOutputDto getRepairByID(Long id) {
        Optional<CarRepair> optionalR = repairRepos.findById(id);
        if (optionalR.isEmpty()) {
            throw new RecordNotFoundException("Rapair", "id", id);
        } else {
            CarRepair carR = optionalR.get();
            return RepairToDto(carR);

        }
    }

    public List<CarRepairOutputDto> getAllRapairs() {
        List<CarRepairOutputDto> collection = new ArrayList<>();
        List<CarRepair> list = repairRepos.findAll();
        for (CarRepair carRepair : list) {
            collection.add(RepairToDto(carRepair));
        }
        return collection;
    }

    public CarRepairOutputDto updateCarProblem(long id, CarRepairDto dto ) {
        Optional<CarRepair> optionalRepair = repairRepos.findById(id);
        if (optionalRepair.isEmpty()) {
            throw new RecordNotFoundException("CarProblem", "id", id);

        } else {
            CarRepair carR = optionalRepair.get();
            carR.setCarProblem(dto.carProblem);
            CarRepair carRepair1= repairRepos.save(carR);
            return RepairToDto(carRepair1);
        }

    }

    public CarRepairOutputDto updateRepairDate(long id, CarRepairDto dto) {
        Optional<CarRepair> optionalRepair = repairRepos.findById(id);
        if ((optionalRepair.isEmpty())) {
            throw new RecordNotFoundException("repair Date", "id", id);
        } else {
            CarRepair carR = optionalRepair.get();
            carR.setRepairDate(dto.repairDate);
            CarRepair carRepair1 = repairRepos.save(carR);
            return RepairToDto(carRepair1) ;
        }

    }
    public void addPartToCarRepair(long carRepairId, long partId, PartType partType) {
        Optional<CarRepair> optionalCarRepair = repairRepos.findById(carRepairId);
        if (optionalCarRepair.isEmpty()) {
            throw new RecordNotFoundException("Car repair not found");
        }

        CarRepair carRepair = optionalCarRepair.get();

        switch (partType) {
            case BRAKE -> {
                Optional<Brakes> optionalBrakes = brakeRepos.findById(partId);
                if (optionalBrakes.isEmpty()) {
                    throw new RecordNotFoundException("Brakes", "brakeId", partId);
                }
                Brakes brakes = optionalBrakes.get();
                carRepair.getCarParts().add(brakes);
            }
            case SPARK_PLUG -> {
                Optional<SparkPlug> optionalSparkPlug = sprakPlugRepos.findById(partId);
                if (optionalSparkPlug.isEmpty()) {
                    throw new RecordNotFoundException("spark plug", "sparkPlugId", partId);
                }
                SparkPlug sparkPlug = optionalSparkPlug.get();
                carRepair.getCarParts().add(sparkPlug);
            }
            case TYRES -> {
                Optional<Tyres> optionalTyre = tyreRepos.findById(partId);
                if (optionalTyre.isEmpty()) {
                    throw new RecordNotFoundException("tyre", "tyreId", partId);
                }
                Tyres tyres = optionalTyre.get();
                carRepair.getCarParts().add(tyres);
            }
            default -> throw new IllegalArgumentException("Invalid part type");
        }

        repairRepos.save(carRepair);
    }

    /*public void addBrakeToCarRepair(long id, long brakeId) {
        Optional<CarRepair> optionalCarRepair = repairRepos.findById(id);
        Optional<Brakes> optionalBrake = brakeRepos.findById(brakeId);
        if (optionalCarRepair.isEmpty()) {
            throw new RecordNotFoundException("Repair", "id", id);
        }
        if (optionalBrake.isEmpty()) {
            throw new RecordNotFoundException("brake", "brakeId", brakeId);
        } else {
            CarRepair carR = optionalCarRepair.get();
            Brakes brake = optionalBrake.get();
            carR.getCarParts().add(brake);
            repairRepos.save(carR);
        }
    }
        public void addSparkPlugToCarRepair(long id, long sparkPlugId) {
        Optional<CarRepair> optionalCarRepair1 = repairRepos.findById(id);
        Optional<SparkPlug> optionalSparkPlug = sprakPlugRepos.findById(sparkPlugId);
        if(optionalCarRepair1.isEmpty()){
            throw new RecordNotFoundException("Repair","id",id);
        }if(optionalSparkPlug.isEmpty()){
            throw new RecordNotFoundException("Spark-plug","sparkPlugId" ,sparkPlugId);
        }else {
            CarRepair carR = optionalCarRepair1.get();
            SparkPlug sparkPlug = optionalSparkPlug.get();
            carR.getCarParts().add(sparkPlug);
            repairRepos.save(carR);
        }
    }
    public void addTyreToCarRepair(long id, long tyreId) {
        Optional<CarRepair> optionalCarRepair2 = repairRepos.findById(id);
        Optional<Tyres> optionalTyre  = tyreRepos.findById(tyreId);
        if(optionalCarRepair2.isEmpty()){
            throw new RecordNotFoundException("Repair","id",id);
        }if(optionalTyre.isEmpty()){
            throw new RecordNotFoundException("Tyre","tyreId" ,tyreId);
        }else {
            CarRepair carR = optionalCarRepair2.get();

            Tyres tyres = optionalTyre.get();
            carR.getCarParts().add(tyres);
            repairRepos.save(carR);
        }
    }*/
    public String deleteRepairById(long id) {
        if (repairRepos.existsById(id)) {
            long count = repairRepos.count();
            repairRepos.deleteById(id);
            return "You delete " + count + " in the carId " + id;
        } else {
            throw new RecordNotFoundException("repair", "id", id);
        }
    }

    public String deleteAllRepairs() {
        long count = repairRepos.count();
        repairRepos.deleteAll();
        return "You deleted " + count + "cars";

    }

/*    public  getTotalCostByID(long id) {

        Optional<CarRepair> optionalCarRepair = repairRepos.findById(id);
        if (optionalCarRepair.isEmpty()) {
            throw new CarRepairNotFoundException("car Repair", "id", id);
        }
        CarRepair carRepair = optionalCarRepair.get();
        Double totalCost = carRepair.getTotalCost() + carRepair.getLaborCost();

        CarRepairOutputDto outputDto = new CarRepairOutputDto();
        outputDto.setId(carRepair.getId());
        outputDto.setTotalCost(totalCost);
        return outputDto;
    }*/




    public CarRepairOutputDto RepairToDto(CarRepair repair) {
        CarRepairOutputDto dto = new CarRepairOutputDto();

        dto.id = repair.getId();
        dto.car = repair.getCar();
        dto.carProblem = repair.getCarProblem();
        dto.repairDate = repair.getRepairDate();
        dto.partCost = repair.getPartCost();
        dto.laborCost = repair.getLaborCost();
        dto.totalCost = repair.getTotalCost();
        //dto.partType = repair.getPartType();


        return dto;
    }

    public CarRepair DtoToRepair(CarRepairDto repairDto) {
        CarRepair carR = new CarRepair();

        carR.setCar(repairDto.Car);
        carR.setCarProblem(repairDto.carProblem);
        carR.setRepairDate(repairDto.repairDate);
        carR.setPartCost(repairDto.partCost);
        carR.setLaborCost(repairDto.laborCost);
        carR.setTotalCost(repairDto.totalCost);
      //  carR.setPartType(repairDto.partType);

        return carR;
    }



}
