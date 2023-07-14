package nl.novi.Eindopdracht.Service.ModelService;

import nl.novi.Eindopdracht.Exceptions.RecordNotFoundException;

import nl.novi.Eindopdracht.Models.Data.CarParts.Brakes;
import nl.novi.Eindopdracht.Models.Data.CarParts.CarParts;
import nl.novi.Eindopdracht.Models.Data.CarParts.SparkPlug;
import nl.novi.Eindopdracht.Models.Data.CarParts.Tyres;
import nl.novi.Eindopdracht.Models.Data.CarRepair;
import nl.novi.Eindopdracht.Models.Data.Enum.PartType;
import nl.novi.Eindopdracht.Repository.BrakeRepository;
import nl.novi.Eindopdracht.Repository.CarReparationRepository;

import nl.novi.Eindopdracht.Repository.SprakPlugRepository;
import nl.novi.Eindopdracht.Repository.TyreRepository;
import nl.novi.Eindopdracht.dto.input.CarRepairDto;

import nl.novi.Eindopdracht.dto.input.PartDto;
import nl.novi.Eindopdracht.dto.output.CarPartsDto.BrakesOutputDto;
import nl.novi.Eindopdracht.dto.output.CarPartsDto.CarPartsOutputDto;
import nl.novi.Eindopdracht.dto.output.CarPartsDto.SparkPlugOutputDto;
import nl.novi.Eindopdracht.dto.output.CarPartsDto.TyresOutputDto;
import nl.novi.Eindopdracht.dto.output.CarRepairOutputDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ForkJoinPool;


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


    public CarRepairOutputDto createCarReport(CarRepairDto carRepairDto) {
        CarRepair carRepair = DtoToRepair(carRepairDto);
        Double totalCost = calculateTotalCost(carRepair.getPartCost(),carRepair.getLaborCost());
        carRepair.setTotalCost(totalCost);

        CarRepair savedCarRepair =repairRepos.save(carRepair);
        CarRepairOutputDto outputDto = RepairToDto(savedCarRepair);

        return outputDto;
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
    public void addPartToCarRepair(Long carRepairId, PartDto partDto) {
        Optional<CarRepair> optionalCarRepair = repairRepos.findById(carRepairId);
        if (optionalCarRepair.isEmpty()) {
            throw new RecordNotFoundException("car repair not found");
        }

        CarRepair carRepair = optionalCarRepair.get();
        PartType partType = partDto.partType;
        Long partId = partDto.partID;
        switch (partType) {
            case BRAKE -> {
                Optional<Brakes> optionalBrakes = brakeRepos.findById(partId);
                if (optionalBrakes.isEmpty()) {
                    throw new RecordNotFoundException("Brakes", "brakeId", partId);
                }
                Brakes brakes = optionalBrakes.get();
                brakes.setCarRepair(carRepair);
                brakeRepos.save(brakes);

            }
            case SPARKPLUG -> {
                Optional<SparkPlug> optionalSparkPlug = sprakPlugRepos.findById(partId);
                if (optionalSparkPlug.isEmpty()) {
                    throw new RecordNotFoundException("spark plug", "sparkPlugId", partId);
                }
                SparkPlug sparkPlug = optionalSparkPlug.get();
                sparkPlug.setCarRepair(carRepair);
                sprakPlugRepos.save(sparkPlug);
            }
            case TYRES -> {
                Optional<Tyres> optionalTyre = tyreRepos.findById(partId);
                if (optionalTyre.isEmpty()) {
                    throw new RecordNotFoundException("tyre", "tyreId", partId);
                }
                Tyres tyres = optionalTyre.get();
                tyres.setCarRepair(carRepair);
                tyreRepos.save(tyres);
            }
            default -> throw new IllegalArgumentException("Invalid part type");
        }

    }

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

    private Double calculateTotalCost(Double partCost, Double laborCost) {
        return partCost + laborCost;
    }

//    private List<CarPartsOutputDto> mapCarPartsToDtos(List<CarParts> carParts) {
//        List<CarPartsOutputDto> partsDtos = new ArrayList<>();
//        for (CarParts part : carParts) {
//            CarPartsOutputDto partDto = createCarPartsOutputDto(part);
//            if (partDto != null) {
//                partsDtos.add(partDto);
//            }
//        }
//        return partsDtos;
//    }
 private List<CarPartsOutputDto> mapCarPartsToDtos(List<CarParts> carParts) {
       if(carParts == null){
           return new ArrayList<>();
       }
       List<CarPartsOutputDto> partsDtos = new ArrayList<>();
        for (CarParts parts : carParts){
            CarPartsOutputDto partsOutputDto = createCarPartsOutputDto(parts);
            if(partsOutputDto !=null){
                partsDtos.add(partsOutputDto);
            }
        }
        return partsDtos;
    }

    private CarPartsOutputDto createCarPartsOutputDto(CarParts part) {
        if (part instanceof Brakes) {
            BrakesOutputDto brakesDto = new BrakesOutputDto();
            mapCommonProperties(brakesDto, part);
            mapBrakesSpecificProperties(brakesDto, (Brakes) part);
            return brakesDto;
        } else if (part instanceof Tyres) {
            TyresOutputDto tyresDto = new TyresOutputDto();
            mapCommonProperties(tyresDto, part);
            mapTyresSpecificProperties(tyresDto, (Tyres) part);
            return tyresDto;
        } else if (part instanceof SparkPlug) {
            SparkPlugOutputDto sparkPlugDto = new SparkPlugOutputDto();
            mapCommonProperties(sparkPlugDto, part);
            mapSparkPlugSpecificProperties(sparkPlugDto, (SparkPlug) part);
            return sparkPlugDto;
        } else {
            return null;
        }
    }

    private void mapCommonProperties(CarPartsOutputDto partDto, CarParts part) {
        partDto.setId(part.getId());
        partDto.setPartType(part.getPartType());
        partDto.setPartName(part.getPartName());
        partDto.setPartNumber(part.getPartNumber());
        partDto.setPrice(part.getPrice());
        partDto.setAmountOfParts(part.getAmountOfParts());
    }

    private void mapBrakesSpecificProperties(BrakesOutputDto brakesDto, Brakes brakes) {
        brakesDto.setOuterDiameter(brakes.getOuterDiameter());
        brakesDto.setCenterDiameter(brakes.getCenterDiameter());
        brakesDto.setHeight(brakes.getHeight());
        brakesDto.setMinThickness(brakes.getMinThickness());
        brakesDto.setSurface(brakes.getSurface());
        brakesDto.setDiscThickness(brakes.getDiscThickness());
        brakesDto.setBoreTypeNumberOfHoles(brakes.getBoreTypeNumberOfHoles());
        brakesDto.setWheelStudDiameter(brakes.getWheelStudDiameter());
        brakesDto.setWithoutWheelMountingBolts(brakes.getWithoutWheelMountingBolts());
        brakesDto.setWithoutWheelHub(brakes.getWithoutWheelHub());
    }

    private void mapTyresSpecificProperties(TyresOutputDto tyresDto, Tyres tyres) {
        tyresDto.setTyresHight(tyres.getTyresHight());
        tyresDto.setTyresWidth(tyres.getTyresWidth());
        tyresDto.setDiameter(tyres.getDiameter());
        tyresDto.setLoadIndex(tyres.getLoadIndex());
        tyresDto.setSpeedIndex(tyres.getSpeedIndex());
        tyresDto.setProductionDate(tyres.getProductionDate());
    }

    private void mapSparkPlugSpecificProperties(SparkPlugOutputDto sparkPlugDto, SparkPlug sparkPlug) {
        sparkPlugDto.setSpannerSize(sparkPlug.getSpannerSize());
        sparkPlugDto.setQuality(sparkPlug.getQuality());
        sparkPlugDto.setWarmthDegree(sparkPlug.getWarmthDegree());
        sparkPlugDto.setThreadLength(sparkPlug.getThreadLength());
        sparkPlugDto.setTorque(sparkPlug.getTorque());
        sparkPlugDto.setSparkPosition(sparkPlug.getSparkPosition());
    }


    public CarRepairOutputDto RepairToDto(CarRepair repair) {
        CarRepairOutputDto dto = new CarRepairOutputDto();

        dto.id = repair.getId();
        dto.car = repair.getCar();
        dto.carProblem = repair.getCarProblem();
        dto.repairDate = repair.getRepairDate();
        dto.partCost = repair.getPartCost();
        dto.laborCost = repair.getLaborCost();
        dto.totalCost = repair.getTotalCost();
        dto.carParts = mapCarPartsToDtos(repair.getCarParts());


        return dto;
    }

    public CarRepair DtoToRepair(CarRepairDto repairDto) {
        CarRepair carR = new CarRepair();

        carR.setCar(repairDto.car);
        carR.setCarProblem(repairDto.carProblem);
        carR.setRepairDate(repairDto.repairDate);
        carR.setPartCost(repairDto.partCost);
        carR.setLaborCost(repairDto.laborCost);
        carR.setTotalCost(repairDto.totalCost);


        return carR;
    }



}
