package nl.novi.Eindopdracht.Service;


import nl.novi.Eindopdracht.Exceptions.AccountNotFoundException;
import nl.novi.Eindopdracht.Exceptions.CarNotFoundException;
import nl.novi.Eindopdracht.Exceptions.RecordNotFoundException;
import nl.novi.Eindopdracht.Models.Data.*;
import nl.novi.Eindopdracht.Models.Data.Enum.*;
import nl.novi.Eindopdracht.Repository.CarInspectionRepository;
import nl.novi.Eindopdracht.Repository.CarRepository;
import nl.novi.Eindopdracht.Repository.CustomerAccountRepository;

import nl.novi.Eindopdracht.Service.ModelService.CarService;
import nl.novi.Eindopdracht.dto.input.CarDto;
import nl.novi.Eindopdracht.dto.output.CarOutputDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CarServiceTest {
    @Mock
    CarRepository carRepos;

    @Mock
    CustomerAccountRepository cARepos;

    @Mock
    CarInspectionRepository inspectionRepos;

    @InjectMocks
    CarService carService;


    @Captor
    ArgumentCaptor<Car> argumentCaptor;


    CustomerAccount account1;
    CustomerAccount account2;
    CustomerAccount account3;

    Car car1;
    Car car2;
    Car car3;
    Car car4;

    CarDto carDto1;
    CarDto carDto2;
    CarDto carDto3;
    CarDto carDto4;

    CarOutputDto carOutputDto1;
    CarOutputDto carOutputDto2;
    CarOutputDto carOutputDto3;
    CarOutputDto carOutputDto4;

    CarInspection carInspection1;


    @BeforeEach
    void setUp() {


        account1 = new CustomerAccount(1L, "Adriaan de Wit", "Adriaan", "De wit", "Prinsessenweg 19", "0623123421", "Prinsessenweg 19", "nl21INGB 5555 555 05");
        account2 = new CustomerAccount(2L, "Hendrick lopers", "Hendrick ", "Lopers", "DaltonLaan 21", "06123456778", "Daltonlaan 21", "nl21 55553218");
        account3 = new CustomerAccount(3L, "Jan Vermeer", "Jan", "Vermeer", "Biltstraat 3", "06789344561", "Biltstraat 3", "nl21 INGB 343321");

        car1 = new Car(1L, CarBrand.AUDI, CarModel.A3, LocalDate.of(2020, 4, 12), Colors.BLACK, "D-899-PP", 10202, EngineType.TSFI, Body.HATCHBACK, Transmission.AUTOMATIC, Fuel.PETROL, account1, null);
        car2 = new Car(2L, CarBrand.AUDI, CarModel.A3, LocalDate.of(2022, 8, 2), Colors.BROWN, "D-710-PP", 150123, EngineType.TDI, Body.HATCHBACK, Transmission.MANUAL, Fuel.DIESEL, account2, null);
        car3 = new Car(3L, CarBrand.AUDI, CarModel.A4, LocalDate.of(2018, 2, 5), Colors.SILVER, "G-810-DD", 501, EngineType.TSI, Body.SEDAN, Transmission.SEMIAUTOMATIC, Fuel.PETROL, account3, null);
        car4 = new Car(4L, CarBrand.SEAT, CarModel.LEON, LocalDate.of(2020, 8, 2), Colors.GRAY, "G-703-DF", 23761, EngineType.TSI, Body.STATIONWAGON, Transmission.AUTOMATIC, Fuel.PETROL, account1, null);

        carInspection1 = new CarInspection(1l, 10202, "D-899-PP", LocalDate.of(2023, 5, 14), false, null, "car has problem with spark plugs", car1, null);

        carDto1 = new CarDto();
        carDto1.setBrand(CarBrand.AUDI);
        carDto1.setModel(CarModel.A3);
        carDto1.setYearOfBuild(LocalDate.of(2020, 4, 12));
        carDto1.setColor(Colors.BLACK);
        carDto1.setLicensePlate("D-899-PP");
        carDto1.setMileAge(10202);
        carDto1.setEngineType(EngineType.TSFI);
        carDto1.setBody(Body.HATCHBACK);
        carDto1.setTransmission(Transmission.AUTOMATIC);
        carDto1.setFuel(Fuel.PETROL);

        carDto2 = new CarDto();
        carDto2.setBrand(CarBrand.AUDI);
        carDto2.setModel(CarModel.A3);
        carDto2.setYearOfBuild(LocalDate.of(2022, 8, 2));
        carDto2.setColor(Colors.BROWN);
        carDto2.setLicensePlate("D-710-PP");
        carDto2.setMileAge(150123);
        carDto2.setEngineType(EngineType.TDI);
        carDto2.setBody(Body.HATCHBACK);
        carDto2.setTransmission(Transmission.MANUAL);
        carDto2.setFuel(Fuel.DIESEL);

        carDto3 = new CarDto();
        carDto3.setBrand(CarBrand.AUDI);
        carDto3.setModel(CarModel.A4);
        carDto3.setYearOfBuild(LocalDate.of(2018, 2, 5));
        carDto3.setColor(Colors.SILVER);
        carDto3.setLicensePlate("G-810-DD");
        carDto3.setMileAge(501);
        carDto3.setEngineType(EngineType.TSI);
        carDto3.setBody(Body.SEDAN);
        carDto3.setTransmission(Transmission.SEMIAUTOMATIC);
        carDto3.setFuel(Fuel.PETROL);

        carDto4 = new CarDto();
        carDto4.setBrand(CarBrand.SEAT);
        carDto4.setModel(CarModel.LEON);
        carDto4.setYearOfBuild(LocalDate.of(2020, 8, 2));
        carDto4.setColor(Colors.GRAY);
        carDto4.setLicensePlate("G-703-DF");
        carDto4.setMileAge(23761);
        carDto4.setEngineType(EngineType.TSI);
        carDto4.setBody(Body.STATIONWAGON);
        carDto4.setTransmission(Transmission.AUTOMATIC);
        carDto4.setFuel(Fuel.PETROL);


        carOutputDto1 = new CarOutputDto();
        carOutputDto1.setId(1L);
        carOutputDto1.setBrand(CarBrand.AUDI);
        carOutputDto1.setModel(CarModel.A3);
        carOutputDto1.setYearOfBuild(LocalDate.of(2020, 4, 12));
        carOutputDto1.setColor(Colors.BLACK);
        carOutputDto1.setLicensePlate("D-899-PP");
        carOutputDto1.setMileAge(10202);
        carOutputDto1.setEngineType(EngineType.TSFI);
        carOutputDto1.setBody(Body.HATCHBACK);
        carOutputDto1.setTransmission(Transmission.AUTOMATIC);
        carOutputDto1.setFuel(Fuel.PETROL);

        carOutputDto2 = new CarOutputDto();
        carOutputDto2.setId(2L);
        carOutputDto2.setBrand(CarBrand.AUDI);
        carOutputDto2.setModel(CarModel.A3);
        carOutputDto2.setYearOfBuild(LocalDate.of(2022, 8, 2));
        carOutputDto2.setColor(Colors.BROWN);
        carOutputDto2.setLicensePlate("D-710-PP");
        carOutputDto2.setMileAge(150123);
        carOutputDto2.setEngineType(EngineType.TDI);
        carOutputDto2.setBody(Body.HATCHBACK);
        carOutputDto2.setTransmission(Transmission.MANUAL);
        carOutputDto2.setFuel(Fuel.DIESEL);

        carOutputDto3 = new CarOutputDto();
        carOutputDto3.setId(3L);
        carOutputDto3.setBrand(CarBrand.AUDI);
        carOutputDto3.setModel(CarModel.A4);
        carOutputDto3.setYearOfBuild(LocalDate.of(2018, 2, 5));
        carOutputDto3.setColor(Colors.SILVER);
        carOutputDto3.setLicensePlate("G-810-DD");
        carOutputDto3.setMileAge(501);
        carOutputDto3.setEngineType(EngineType.TSI);
        carOutputDto3.setBody(Body.SEDAN);
        carOutputDto3.setTransmission(Transmission.SEMIAUTOMATIC);
        carOutputDto3.setFuel(Fuel.PETROL);

        carOutputDto4 = new CarOutputDto();
        carOutputDto4.setId(4L);
        carOutputDto4.setBrand(CarBrand.SEAT);
        carOutputDto4.setModel(CarModel.LEON);
        carOutputDto4.setYearOfBuild(LocalDate.of(2020, 8, 2));
        carOutputDto4.setColor(Colors.GRAY);
        carOutputDto4.setLicensePlate("G-703-DF");
        carOutputDto4.setMileAge(23761);
        carOutputDto4.setEngineType(EngineType.TSI);
        carOutputDto4.setBody(Body.STATIONWAGON);
        carOutputDto4.setTransmission(Transmission.AUTOMATIC);
        carOutputDto4.setFuel(Fuel.PETROL);


    }


    @Test
        // @Disabled
    void createCar() {

        when(carRepos.save(car4)).thenReturn(car4);
        carService.createCar(carDto4);

        verify(carRepos, times(1)).save(argumentCaptor.capture());
        Car car = argumentCaptor.getValue();

        assertEquals(car4.getBrand(), car4.getBrand());
        assertEquals(car4.getModel(), car.getModel());
        assertEquals(car4.getYearOfBuild(), car.getYearOfBuild());
        assertEquals(car4.getColor(), car.getColor());
        assertEquals(car4.getLicensePlate(), car.getLicensePlate());
        assertEquals(car4.getMileAge(), car.getMileAge());
        assertEquals(car4.getEngineType(), car.getEngineType());
        assertEquals(car4.getBody(), car.getBody());
        assertEquals(car4.getTransmission(), car.getTransmission());
        assertEquals(car4.getFuel(), car.getFuel());

    }

    @Test
        // @Disabled
    void testGetAllCars() {

        when(carRepos.findAll()).thenReturn(List.of(car1, car2, car3, car4));

        List<Car> cars = carRepos.findAll();
        List<CarOutputDto> dtoList = carService.getAllCars();

        assertEquals(cars.get(0).getLicensePlate(), dtoList.get(0).licensePlate);
        assertEquals(cars.get(0).getBrand(), dtoList.get(0).brand);
        assertEquals(cars.get(1).getLicensePlate(), dtoList.get(1).licensePlate);
        assertEquals(cars.get(1).getBrand(), dtoList.get(1).brand);
        assertEquals(cars.get(2).getLicensePlate(), dtoList.get(2).licensePlate);
        assertEquals(cars.get(2).getBrand(), dtoList.get(2).brand);
        assertEquals(cars.get(3).getLicensePlate(), dtoList.get(3).licensePlate);
        assertEquals(cars.get(3).getBrand(), dtoList.get(3).brand);

    }

    @Test
        // @Disabled
    void getCarByCarLicensePlate_InvalidLicensePlate() {
        when(carRepos.findByLicensePlate(car1.getLicensePlate())).thenReturn(Optional.empty());
        assertThrows(CarNotFoundException.class, () -> carService.getCarByCarLicensePlate(car1.getLicensePlate()));
    }

    @Test
        //  @Disabled
    void getCarByCarLicensePlate_ValidLicensePlate() {

        when(carRepos.findByLicensePlate(car1.getLicensePlate())).thenReturn(Optional.ofNullable(car1));

        CarOutputDto result = carService.getCarByCarLicensePlate(car1.getLicensePlate());

        assertEquals(car1.getLicensePlate(), result.getLicensePlate());
        assertEquals(car1.getBrand(), result.getBrand());
        assertEquals(car1.getModel(), result.getModel());
        verify(carRepos, times(1)).findByLicensePlate(car1.getLicensePlate());


    }

    @Test
        //  @Disabled
    void getAllCarsByCustomerName_InvalidCostumerName() {
        when(cARepos.findAccountByCustomerName(account1.getCustomerName())).thenReturn(Optional.empty());
        assertThrows(AccountNotFoundException.class, () -> carService.getAllCarsByCustomerName(account1.getCustomerName()));

    }

    @Test
        // @Disabled
    void getAllCarsByCustomerName_ValidCostumerName() {
        String customerName = account1.getCustomerName();
        List<Car> cars = new ArrayList<>();
        cars.add(car1);
        cars.add(car2);
        cars.add(car3);
        cars.add(car4);
        account1.setCars(cars);
        Optional<CustomerAccount> optionalAccount = Optional.of(account1);
        when(cARepos.findAccountByCustomerName(customerName)).thenReturn(optionalAccount);

        List<CarOutputDto> result = carService.getAllCarsByCustomerName(customerName);

        assertEquals(4, result.size());
        assertEquals(car1.getBrand(), result.get(0).brand);
        assertEquals(car1.getModel(), result.get(0).model);
        assertEquals(car2.getBrand(), result.get(1).brand);
        assertEquals(car2.getModel(), result.get(1).model);
        assertEquals(car3.getBrand(), result.get(2).brand);
        assertEquals(car3.getModel(), result.get(2).model);
        assertEquals(car4.getBrand(), result.get(3).brand);
        assertEquals(car4.getModel(), result.get(3).model);

    }


    @Test
    void getCarByLicensePlate_InvalidLicensePlate() {
        when(cARepos.findAccountByCustomerName(account1.getCustomerName())).thenReturn(Optional.of(account1));
        when(carRepos.findByLicensePlate(car2.getLicensePlate())).thenReturn(Optional.empty());
        assertThrows(CarNotFoundException.class, () -> carService.getCarByLicensePlate(car2.getLicensePlate()));
    }

    @Test
    void getAccountByCustomerName_InValidCostumerAccount() {
        when(carRepos.findByLicensePlate(car2.getLicensePlate())).thenReturn(Optional.of(car2));
        when(cARepos.findAccountByCustomerName(account1.getCustomerName())).thenReturn(Optional.empty());
        assertThrows(AccountNotFoundException.class, () -> carService.getAccountByCustomerName(account1.getCustomerName()));
    }

    @Test
        // @Disabled
    void addAccountToCar_validLicensePlateAndAccount() {
        when(carRepos.findByLicensePlate(car2.getLicensePlate())).thenReturn(Optional.of(car2));
        when(cARepos.findAccountByCustomerName(account1.getCustomerName())).thenReturn(Optional.of(account1));

        carService.addAccountToCar(car2.getLicensePlate(), account1.getCustomerName());

        verify(carRepos, times(1)).save(argumentCaptor.capture());

        Car capture = argumentCaptor.getValue();

        assertEquals(car2.getAccount(), capture.getAccount());

    }

    @Test
        //  @Disabled
    void updateCarMileage_InvalidLicensePlate() {
        when(carRepos.findByLicensePlate(car1.getLicensePlate())).thenReturn(Optional.empty());
        assertThrows(RecordNotFoundException.class, () -> carService.updateCarMileage(car1.getLicensePlate(), carDto1));
    }

    @Test
        //@Disabled
    void updateCarMileage_ValidLicensePlate() {
//arrange

        String licensePlate = car1.getLicensePlate();

        when(carRepos.findByLicensePlate(licensePlate)).thenReturn(Optional.of(car1));
        when(carRepos.save(car1)).thenReturn(car1);
//act
        carService.updateCarMileage(licensePlate, carDto1);

//asserts
        verify(carRepos, times(1)).save(car1);
        assertEquals(licensePlate, car1.getLicensePlate());
        assertEquals(carDto1.mileAge, car1.getMileAge());


    }

    @Test
        // @Disabled
    void updateEngineType_InvalidLicensePlate() {
        when(carRepos.findByLicensePlate(car2.getLicensePlate())).thenReturn(Optional.empty());

        assertThrows(RecordNotFoundException.class, () -> carService.updateEngineType(car2.getLicensePlate(), carDto2));

    }

    @Test
        //@Disabled
    void updateEngineType_ValidLicensePlate() {

        //arrange
        when(carRepos.findByLicensePlate(car2.getLicensePlate())).thenReturn(Optional.of(car2));
        when(carRepos.save(car2)).thenReturn(car2);
        //act
        carService.updateEngineType(car2.getLicensePlate(), carDto2);

        verify(carRepos, times(1)).save(car2);

        assertEquals(carDto2.engineType, car2.getEngineType());

    }

    @Test
    void AddInspectionToCar_InvalidLicensePlate(){

        when(carRepos.findByLicensePlate(car1.getLicensePlate())).thenReturn(Optional.empty());
        when(inspectionRepos.findById(carInspection1.getId())).thenReturn(Optional.empty());

        assertThrows(RecordNotFoundException.class, () -> carService.addInspectionToCar(carInspection1.getId(), car1.getLicensePlate()));

    }
    @Test
    void AddInspectionToCar_InvalidCarInspectionId() {
        when(carRepos.findByLicensePlate(car1.getLicensePlate())).thenReturn(Optional.empty());
        when(inspectionRepos.findById(carInspection1.getId())).thenReturn(Optional.of(carInspection1));

       assertThrows(CarNotFoundException.class,()-> carService.addInspectionToCar(carInspection1.getId(), car2.getLicensePlate()));

    }@Test
    void AddInspectionToCar_ValidLicensePlate() {
        when(inspectionRepos.findById(carInspection1.getId())).thenReturn(Optional.of(carInspection1));
        when(carRepos.findByLicensePlate(car1.getLicensePlate())).thenReturn(Optional.of(car1));
        when(carRepos.save(any(Car.class))).thenReturn(car1);

        // Reset the carInspection list of car1 to an empty list
        car1.setCarInspection(new ArrayList<>());

        // Call the addInspectionToCar method
        carService.addInspectionToCar(carInspection1.getId(), car1.getLicensePlate());

        // Verify that the save method was called once
        verify(carRepos, times(1)).save(car1);

        // Assert that the car object contains the added CarInspection
        assertTrue(car1.getCarInspection().contains(carInspection1));
    }


    @Test
        // @Disabled
    void deleteCarByLicensePlate_InvalidLicensePlate() {
        String LicensePlate = car1.getLicensePlate();

        when(carRepos.findByLicensePlate(LicensePlate)).thenReturn(Optional.empty());

        assertThrows(CarNotFoundException.class, () -> carService.deleteCarByLicensePlate(LicensePlate));
    }

    @Test
        // @Disabled
    void deleteCarByLicensePlate_ValidLicensePlate() {
        String licensePlate = car3.getLicensePlate();
        //arrange
        when(carRepos.findByLicensePlate(car3.getLicensePlate())).thenReturn(Optional.of(car3));
        long count = carRepos.count();

        //act
        String result = carService.deleteCarByLicensePlate(car3.getLicensePlate());
        //asserts
        verify(carRepos).deleteByLicensePlate(car3.getLicensePlate());
        assertEquals("You delete" + count + "in the carId" + licensePlate, result);
    }

    @Test
        // @Disabled
    void deleteAllCars() {
        when(carRepos.count()).thenReturn(2L);

        String result = carService.deleteAllCars();

        verify(carRepos, times(1)).count();
        verify(carRepos, times(1)).deleteAll();

        assertEquals("You deleted 2 cars", result);
    }

    @Test
        // @Disabled
    void carToDto() {

        when(carRepos.save(car1)).thenReturn(car1);

        CarOutputDto actualDto = carService.carToDto(car1);

        assertEquals(car1.getBrand(), actualDto.brand);
        assertEquals(car1.getModel(), actualDto.model);
        assertEquals(car1.getYearOfBuild(), actualDto.yearOfBuild);
        assertEquals(car1.getColor(), actualDto.color);
        assertEquals(car1.getLicensePlate(), actualDto.licensePlate);
        assertEquals(car1.getMileAge(), actualDto.mileAge);
        assertEquals(car1.getEngineType(), actualDto.engineType);
        assertEquals(car1.getTransmission(), actualDto.transmission);

    }

    @Test
        //@Disabled
    void dtoToCar() {

        when(carRepos.save(car1)).thenReturn(car1);


        Car car1 = carService.transferDtoToCar(carDto1);


        assertEquals(car1.getBrand(), carDto1.brand);
        assertEquals(car1.getModel(), carDto1.model);
        assertEquals(car1.getYearOfBuild(), carDto1.yearOfBuild);
        assertEquals(car1.getColor(), carDto1.color);
        assertEquals(car1.getLicensePlate(), carDto1.licensePlate);
        assertEquals(car1.getMileAge(), carDto1.mileAge);
        assertEquals(car1.getEngineType(), carDto1.engineType);
        assertEquals(car1.getTransmission(), carDto1.transmission);

    }
}

