package nl.novi.Eindopdracht.Service;

import nl.novi.Eindopdracht.Exceptions.CarStatusNotFoundException;
import nl.novi.Eindopdracht.Exceptions.InspectionNotFoundException;
import nl.novi.Eindopdracht.Exceptions.RecordNotFoundException;
import nl.novi.Eindopdracht.Models.Data.Car;
import nl.novi.Eindopdracht.Models.Data.CarInspection;
import nl.novi.Eindopdracht.Models.Data.CarParts.SparkPlug;
import nl.novi.Eindopdracht.Models.Data.CarRepair;
import nl.novi.Eindopdracht.Models.Data.CustomerAccount;
import nl.novi.Eindopdracht.Models.Data.Enum.*;
import nl.novi.Eindopdracht.Repository.CarInspectionRepository;
import nl.novi.Eindopdracht.Service.ModelService.CarInspectionService;
import nl.novi.Eindopdracht.dto.input.CarInspectionDto;
import nl.novi.Eindopdracht.dto.output.CarInspectionOutputDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CarInspectionServiceTest {

    @Mock
    CarInspectionRepository carInpectionRepos;

    @InjectMocks
    CarInspectionService carInspectionService;

    @Captor
    ArgumentCaptor<CarInspection> captor;

    Car car1;

    Car car2;

    CarInspectionDto iDto;

    CarInspectionDto i2Dto;

    CustomerAccount account1;

    CustomerAccount account2;

    CarInspection carInspection1;

    CarInspection carInspection2;

    CarRepair carRepair1;

    SparkPlug carPart1;


    @BeforeEach
    void setUp() {
        account1 = new CustomerAccount(1L, "Adriaan de Wit", "Adriaan", "De wit", "Prinsessenweg 19", "0623123421", "Prinsessenweg 19", "nl21INGB 5555 555 05");
        account2 = new CustomerAccount(2L, "Hendrick lopers", "Hendrick ", "Lopers", "DaltonLaan 21", "06123456778", "Daltonlaan 21", "nl21 55553218");

        car1 = new Car(CarBrand.VOLKSWAGEN, CarModel.GOLF, LocalDate.of(2020, 4, 12), Colors.BLACK, "D-899-PP", 10202, EngineType.TSI, Body.HATCHBACK, Transmission.AUTOMATIC, Fuel.Petrol, account1, null);
        car2 = new Car(CarBrand.AUDI, CarModel.A3, LocalDate.of(2022, 8, 2), Colors.BROWN, "D-710-PP", 150123, EngineType.TDI, Body.HATCHBACK, Transmission.Manual, Fuel.DIESEL, account2, null);

        carInspection1 = new CarInspection(1L, 10202, "D-899-PP", LocalDate.of(2023, 5, 15), true, "No problem with the car", "", car1, null);
        carInspection2 = new CarInspection(2L, 15121, "D-899-QQ", LocalDate.of(2023, 3, 6), false, "", "The car has broken spark plugs.", car1, null);

        iDto = new CarInspectionDto();
        iDto.setId(1L);
        iDto.setMileAge(10202);
        iDto.setLicensePlate("D-899-PP");
        iDto.setInspectionDate(LocalDate.of(2023, 5, 15));
        iDto.setCarIsCorrect(true);
        iDto.setCarIsFine("No problem with the car");
        iDto.setHasProblem("");
        iDto.setCar(car1);
        iDto.setCarRepairList(null);

        i2Dto = new CarInspectionDto();
        i2Dto.setId(3L);
        i2Dto.setMileAge(115432);
        i2Dto.setLicensePlate("A-311-QQ");
        i2Dto.setInspectionDate(LocalDate.of(2016, 2, 15));
        i2Dto.setCarIsCorrect(true);
        i2Dto.setCarIsFine("No problem with the car");
        i2Dto.setHasProblem("");
        i2Dto.setCar(car2);
        i2Dto.setCarRepairList(null);

        carRepair1 = new CarRepair();
        carRepair1.setId(1L);
        carRepair1.setCar("Volkswagen Golf");
        carRepair1.setCarProblem("spark plug defect");
        carRepair1.setRepairDate(LocalDate.of(2023, 4, 10));
        carRepair1.setCarParts(null);
        carRepair1.setLaborCost(null);
        carRepair1.setTotalCost(null);

        carPart1 = new SparkPlug();
        carPart1.setId(1L);
        carPart1.setPartName("Spark Plug 1");
        carPart1.setPartNumber("SP001");
        carPart1.setPrice(7.96);
        carPart1.setAmountOfParts(5);
        carPart1.setCarRepair(null);
        carPart1.setSpannerSize(13);
        carPart1.setQuality("Copper- nickel 150000");
        carPart1.setWarmthDegree(8);
        carPart1.setThreadLength(19.00);
        carPart1.setTorque(12);
        carPart1.setSparkPosition(1);


    }

    @Test
    void createInspection() {

        when(carInpectionRepos.save(carInspection1)).thenReturn(carInspection1);

        Long inspectionId = carInspectionService.createInspection(iDto);

        assertEquals(carInspection1.getId(),inspectionId);

        verify(carInpectionRepos, times(1)).save(captor.capture());
        CarInspection capturedInspection = captor.getValue();


        assertEquals(carInspection1.getId(),capturedInspection.getId());
        assertEquals(carInspection1.getMileAge(),capturedInspection.getMileAge());
        assertEquals(carInspection1.getLicensePlate(),capturedInspection.getLicensePlate());
        assertEquals(carInspection1.getInspectionDate(),capturedInspection.getInspectionDate());
        assertEquals(carInspection1.getCarIsFine(),capturedInspection.getCarIsFine());



    }

    @Test
    void getInspectionByID_InvalidId() {
        long id = 1L;

        when(carInpectionRepos.findById(id)).thenReturn(Optional.empty());

        assertThrows(RecordNotFoundException.class, () -> carInspectionService.getInspectionByID(id));


    }

    @Test
    void getInspectionByID_ValidId() {

        when(carInpectionRepos.findById(1L)).thenReturn(Optional.of(carInspection1));

        CarInspectionOutputDto optionalCarInspection = carInspectionService.getInspectionByID(1L);

        assertEquals(carInspection1.getId(), optionalCarInspection.id);
        assertEquals(carInspection1.getInspectionDate(), optionalCarInspection.inspectionDate);
        assertEquals(carInspection1.isCarIsCorrect(), optionalCarInspection.carIsCorrect);


    }

    @Test
    void getAllInspections_Invalid() {

        when(carInpectionRepos.findAll()).thenReturn(List.of());

        assertThrows(RecordNotFoundException.class, () -> carInspectionService.getAllInspections());


    }

    @Test
    void getAllInspections_Valid() {
        when(carInpectionRepos.findAll()).thenReturn(List.of(carInspection1, carInspection2));

        List<CarInspection> inspections = carInpectionRepos.findAll();
        List<CarInspectionOutputDto> dtoList = carInspectionService.getAllInspections();

        assertEquals(inspections.get(0).getId(), dtoList.get(0).id);
        assertEquals(inspections.get(0).getLicensePlate(), dtoList.get(0).licensePlate);
        assertEquals(inspections.get(0).getMileAge(), dtoList.get(0).mileAge);
        assertEquals(inspections.get(1).getId(), dtoList.get(1).id);
        assertEquals(inspections.get(1).getLicensePlate(), dtoList.get(1).licensePlate);
        assertEquals(inspections.get(1).getMileAge(), dtoList.get(1).mileAge);

    }



    @Test
    void updateMileAge_InvalidId() {
        long id = 1L;
        int mileAge = 1500;

        when(carInpectionRepos.findById(id)).thenReturn(Optional.empty());

        assertThrows(RecordNotFoundException.class, () -> carInspectionService.updateMileAge(id, mileAge));


    }

    @Test
    void updateMileAge_ValidId() {

        when(carInpectionRepos.findById(1L)).thenReturn(Optional.of(carInspection1));
        when(carInpectionRepos.save(carInspection1)).thenReturn(carInspection1);
        //act
        carInspectionService.updateMileAge(1L, 1500);

        //asserts
        verify(carInpectionRepos, times(1)).save(carInspection1);
        assertEquals(1L, carInspection1.getId());
        assertEquals(1500, carInspection1.getMileAge());


    }

    @Test
    void updateInspectionDate_InvalidId() {
        Long id = 2L;
        LocalDate localDate = LocalDate.of(2023, 5, 30);

        when(carInpectionRepos.findById(id)).thenReturn(Optional.empty());

        assertThrows(RecordNotFoundException.class, () -> carInspectionService.updateInspectionDate(id, localDate));


    }

    @Test
    void updateInspectionDate_ValidId() {
        when(carInpectionRepos.findById(2L)).thenReturn(Optional.of(carInspection2));
        when(carInpectionRepos.save(carInspection2)).thenReturn(carInspection2);

        carInspectionService.updateInspectionDate(2L, LocalDate.of(2023, 5, 30));

        verify(carInpectionRepos, times(1)).save(carInspection2);
        assertEquals(2L, carInspection2.getId());
        assertEquals(LocalDate.of(2023, 5, 30), carInspection2.getInspectionDate());


    }

    @Test
    void updateCarIsFine_InvalidId() {
        Long id = 1L;
        String isFine = "Broken brake pads";

        when(carInpectionRepos.findById(id)).thenReturn(Optional.empty());

        assertThrows(RecordNotFoundException.class, () -> carInspectionService.updateCarIsFine(id, isFine));

    }


    @Test
    void updateCarIsFine_ValidId() {

        when(carInpectionRepos.findById(1L)).thenReturn(Optional.of(carInspection1));
        when(carInpectionRepos.save(carInspection1)).thenReturn(carInspection1);

        carInspectionService.updateCarIsFine(1L, "car is totally fine");

        verify(carInpectionRepos, times(1)).save(carInspection1);
        assertEquals(1L, carInspection1.getId());
        assertEquals("car is totally fine", carInspection1.getCarIsFine());
    }

    @Test
    void updateHasProblem_InvalidId() {
        Long id = 1L;
        String hasProblem = "Broken brake pads";

        when(carInpectionRepos.findById(id)).thenReturn(Optional.empty());

        assertThrows(RecordNotFoundException.class, () -> carInspectionService.updateHasProblem(id, hasProblem));


    }

    @Test
    void updateHasProblem_ValidId() {

        Long id = carInspection1.getId();
        String hasProblem = "car has a no profile on te tyres and the brakes are to below the min thickness";

        when(carInpectionRepos.findById(id)).thenReturn(Optional.of(carInspection1));
        when(carInpectionRepos.save(carInspection1)).thenReturn(carInspection1);

        CarInspectionDto result = carInspectionService.updateHasProblem(id, hasProblem);

        verify(carInpectionRepos, times(1)).save(carInspection1);

        assertEquals(1L, carInspection1.getId());
        assertEquals(hasProblem, carInspection1.getHasProblem());
        assertNull(result);
    }

    @Test
    void updateCarStatus_InvalidId(){
        Long id = 1L;
        boolean carIsCorrect = true;

        when(carInpectionRepos.findById(id)).thenReturn(Optional.empty());

        assertThrows(CarStatusNotFoundException.class, ()-> carInspectionService.updateCarStatus(id,carIsCorrect));
    }
    @Test
    void updateCarStatus_ValidId() {
        Long id = 1L;
        boolean carIsCorrect = true;

        CarInspection existingCarInspection = new CarInspection();
        existingCarInspection.setId(id);
        existingCarInspection.setCarIsCorrect(!carIsCorrect);

        when(carInpectionRepos.findById(id)).thenReturn(Optional.of(existingCarInspection));
        when(carInpectionRepos.save(existingCarInspection)).thenReturn(existingCarInspection);


        carInspectionService.updateCarStatus(id,carIsCorrect);

        assertTrue(existingCarInspection.isCarIsCorrect());
        verify(carInpectionRepos,times(1)).findById(id);
        verify(carInpectionRepos,times(1)).save(existingCarInspection);
    }

    @Test
    void deleteInspectionById_InvalidId() {
        Long id = 1L;

        when(carInpectionRepos.existsById(id)).thenReturn(false);

        assertThrows(InspectionNotFoundException.class,()-> carInspectionService.deleteInspectionById(id));
    }

    @Test
    void deleteInspectionById_ValidId(){
        Long id = 1L;

        CarInspection inspection = new CarInspection();
        inspection.setId(id);

        when(carInpectionRepos.existsById(id)).thenReturn(true);

        Long count = carInpectionRepos.count();
        String result = carInspectionService.deleteInspectionById(id);


        verify(carInpectionRepos).existsById(id);
        verify(carInpectionRepos).deleteById(id);
        assertEquals("you deleted "+ count + " in the "+ id,result);

    }




    @Test
    void deleteAllInspections() {

        when(carInpectionRepos.count()).thenReturn(2L);

        String result = carInspectionService.deleteAllInspections();

        verify(carInpectionRepos,times(1)).count();
        verify(carInpectionRepos,times(1)).deleteAll();

        assertEquals("We deleted 2 inspections",result );
    }
    @Test
    void dtoToCarInspection() {

        when(carInpectionRepos.save(carInspection1)).thenReturn(carInspection1);



        // Act
        carInspectionService.DtoToCarInspection(iDto);

        // Assert
        assertEquals(iDto.mileAge, carInspection1.getMileAge());
        assertEquals(iDto.licensePlate, carInspection1.getLicensePlate());
        assertEquals(iDto.inspectionDate, carInspection1.getInspectionDate());
        assertEquals(iDto.carIsCorrect, carInspection1.isCarIsCorrect());
        assertEquals(iDto.carIsFine, carInspection1.getCarIsFine());
        assertEquals(iDto.hasProblem, carInspection1.getHasProblem());

    }


    @Test
    void inspectionToDto() {

        when(carInpectionRepos.save(carInspection1)).thenReturn(carInspection1);

        carInspectionService.inspectionToDto(carInspection1);

        assertEquals(iDto.mileAge, carInspection1.getMileAge());
        assertEquals(iDto.licensePlate, carInspection1.getLicensePlate());
        assertEquals(iDto.inspectionDate, carInspection1.getInspectionDate());
        assertEquals(iDto.carIsCorrect, carInspection1.isCarIsCorrect());
        assertEquals(iDto.carIsFine, carInspection1.getCarIsFine());
        assertEquals(iDto.hasProblem, carInspection1.getHasProblem());
    }


}