package nl.novi.Eindopdracht.Service.ModelService;

import lombok.AllArgsConstructor;
import nl.novi.Eindopdracht.Exceptions.RecordNotFoundException;
import nl.novi.Eindopdracht.Models.Data.CarParts.SparkPlug;
import nl.novi.Eindopdracht.Repository.CarRepository;
import nl.novi.Eindopdracht.Repository.SprakPlugRepository;
import nl.novi.Eindopdracht.dto.input.CarPartsDto.SparkPlugDto;
import nl.novi.Eindopdracht.dto.output.CarPartsDto.SparkPlugOutputDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class SparkPlugService {

    private final SprakPlugRepository SPRepos;

    private final CarRepository carRepos;


    public Long createSparkPlug(SparkPlugDto sparkPlugDto) {
        SparkPlug SP = mapToSparkPlug(sparkPlugDto);
        SparkPlug savedSP = SPRepos.save(SP);

        SparkPlugOutputDto savedSpDto = mapToSPDto(savedSP);

        return savedSpDto.id;
    }

    public List<SparkPlugOutputDto> getAllSparkPlugs() {
        List<SparkPlugOutputDto> collectionSparkPlugs = new ArrayList<>();
        List<SparkPlug> sparkPlugList = SPRepos.findAll();
        for (SparkPlug sparkPlug : sparkPlugList) {
            collectionSparkPlugs.add(mapToSPDto(sparkPlug));
        }
        return collectionSparkPlugs;


    }

    public SparkPlugOutputDto getSparkPlugById(long id) {
        SparkPlug Sp = SPRepos.findById(id).orElseThrow(
                () -> new RecordNotFoundException("Tyres", "id", id)
        );
        return mapToSPDto(Sp);
    }

    public void updateAmountOfParts(Long id, SparkPlugDto sparkPlugDto) {
        Optional<SparkPlug> optionalSparkPlug = SPRepos.findById(id);
        if (optionalSparkPlug.isEmpty()) {
            throw new RecordNotFoundException("amountOfParts", "id", id);

        } else {
            SparkPlug sparkPlug = optionalSparkPlug.get();
            sparkPlug.setAmountOfParts(sparkPlugDto.amountOfParts);
            SPRepos.save(sparkPlug);

        }
    }

    public void updatePrice(Long id, SparkPlugDto sparkPlugDto) {
        Optional<SparkPlug> optionalSparkPlug = SPRepos.findById(id);
        if (optionalSparkPlug.isEmpty()) {
            throw new RecordNotFoundException("price", "id", id);
        } else {
            SparkPlug sparkPlug = optionalSparkPlug.get();
            sparkPlug.setPrice(sparkPlugDto.price);
            SPRepos.save(sparkPlug);
        }

    }


    public void updatePartNumber(Long id, SparkPlugDto sparkPlugDto) {
        Optional<SparkPlug> optionalSparkPlug = SPRepos.findById(id);
        if (optionalSparkPlug.isEmpty()) {
            throw new RecordNotFoundException("partNumber", "id", id);
        } else {
            SparkPlug sparkPlug = optionalSparkPlug.get();
            sparkPlug.setPartNumber(sparkPlugDto.partNumber);
            SPRepos.save(sparkPlug);
        }
    }


    public String deleteSparkPlugById(Long id) {
        SPRepos.findById(id).orElseThrow(
                () -> new RecordNotFoundException("Tyres", "id", id)
        );

        SPRepos.deleteById(id);
        return "you delete the Sparkplug";


    }

    public String deleteAllBrakes() {
        long count = SPRepos.count();
        SPRepos.deleteAll();
        return "You deleted" + count + "Sparkplugs";
    }


    public SparkPlugOutputDto mapToSPDto(SparkPlug sparkPlug) {
        if (sparkPlug == null) {
            return null;
        }

        SparkPlugOutputDto sparkPlugOutputDto = new SparkPlugOutputDto();

        sparkPlugOutputDto.id = sparkPlug.getId();
        sparkPlugOutputDto.partType =sparkPlug.getPartType();
        sparkPlugOutputDto.partName = sparkPlug.getPartName();
        sparkPlugOutputDto.partNumber = sparkPlug.getPartNumber();
        sparkPlugOutputDto.price = sparkPlug.getPrice();
        sparkPlugOutputDto.amountOfParts = sparkPlug.getAmountOfParts();
        sparkPlugOutputDto.spannerSize = sparkPlug.getSpannerSize();
        sparkPlugOutputDto.quality = sparkPlug.getQuality();
        sparkPlugOutputDto.warmthDegree = sparkPlug.getWarmthDegree();
        sparkPlugOutputDto.threadLength = sparkPlug.getThreadLength();
        sparkPlugOutputDto.torque = sparkPlug.getTorque();
        sparkPlugOutputDto.sparkPosition = sparkPlug.getSparkPosition();

        return sparkPlugOutputDto;
    }

    public SparkPlug mapToSparkPlug(SparkPlugDto SPDto) {
        if (SPDto == null) {
            return null;
        }

        SparkPlug sp = new SparkPlug();

        sp.setId(SPDto.id);
        sp.setPartName(SPDto.partName);
        sp.setPartNumber(SPDto.partNumber);
        sp.setPrice(SPDto.price);
        sp.setAmountOfParts(SPDto.amountOfParts);
        sp.setSpannerSize(SPDto.spannerSize);
        sp.setQuality(SPDto.quality);
        sp.setWarmthDegree(SPDto.warmthDegree);
        sp.setThreadLength(SPDto.threadLength);
        sp.setTorque(SPDto.torque);
        sp.setSparkPosition(SPDto.sparkPosition);


        return sp;
    }


}
