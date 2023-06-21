package nl.novi.Eindopdracht.Service.ModelService;


import nl.novi.Eindopdracht.Exceptions.AccountNotFoundException;
import nl.novi.Eindopdracht.Exceptions.CarNotFoundException;
import nl.novi.Eindopdracht.Exceptions.RecordNotFoundException;
import nl.novi.Eindopdracht.Models.Data.Car;
import nl.novi.Eindopdracht.Models.Data.CarInspection;
import nl.novi.Eindopdracht.Models.Data.CustomerAccount;
import nl.novi.Eindopdracht.Repository.CarInspectionRepository;
import nl.novi.Eindopdracht.Repository.CarRepository;
import nl.novi.Eindopdracht.Repository.CustomerAccountRepository;
import nl.novi.Eindopdracht.dto.input.CarDto;
import nl.novi.Eindopdracht.dto.output.CarOutputDto;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class CarService {

    private final CarRepository carRepos;

    private final CustomerAccountRepository cARepos;
    private final CarInspectionRepository carInspectionRepos;


    public CarService(CarRepository carRepos, CustomerAccountRepository cARepos, CarInspectionRepository carInspectionRepos) {
        this.carRepos = carRepos;
        this.cARepos = cARepos;
        this.carInspectionRepos = carInspectionRepos;
    }

    public Car createCar(CarDto carDto) {
        Car car = transferDtoToCar(carDto);
        carRepos.save(car);
        return car;
    }

    public List<CarOutputDto> getAllCars() {
        List<CarOutputDto> collection = new ArrayList<>();
        List<Car> list = carRepos.findAll();
        for (Car car : list) {
            collection.add(carToDto(car));
        }
        return collection;
    }

    public CarOutputDto getCarByCarLicensePlate(String licensePlate) {
        Optional<Car> carOptional = carRepos.findByLicensePlate(licensePlate);
        if (carOptional.isEmpty()) {
            throw new CarNotFoundException("Cars", "licensePlate", licensePlate);
        } else {
            Car c = carOptional.get();
            return carToDto(c);
        }
    }

    public List<CarOutputDto> getAllCarsByCustomerName(String customerName) {
        Optional<CustomerAccount> optionalAccount = cARepos.findAccountByCustomerName(customerName);
        if (optionalAccount.isEmpty()) {
            throw new AccountNotFoundException("list of cars", "customerName", customerName);
        }
        CustomerAccount account = optionalAccount.get();
        List<CarOutputDto> carOutputDtos = new ArrayList<>();
        for (Car car : account.getCars()) {
            carOutputDtos.add(carToDto(car));
        }
        return carOutputDtos;
    }

    public Car getCarByLicensePlate(String licensePlate) {
        Optional<Car> optionalCar = Optional.ofNullable(carRepos.findByLicensePlate(licensePlate)
                .orElseThrow(() -> new CarNotFoundException("car", "licensePlate", licensePlate)));
        return optionalCar.get();
    }

    public CustomerAccount getAccountByCustomerName(String customerName) {
        Optional<CustomerAccount> optionalAccount = Optional.ofNullable(cARepos.findAccountByCustomerName(customerName)
                .orElseThrow(() -> new AccountNotFoundException("account", "customerName", customerName)));
        return optionalAccount.get();
    }

    public void addAccountToCar(String licensePlate, String customerName) {
        Car car = getCarByLicensePlate(licensePlate);
        CustomerAccount account = getAccountByCustomerName(customerName);

        car.setAccount(account);
        carRepos.save(car);
    }

    public CarOutputDto updateCarMileage(String licensePlate, CarDto carDto) {
        Optional<Car> optionalCar = carRepos.findByLicensePlate(licensePlate);
        if (optionalCar.isEmpty()) {
            throw new RecordNotFoundException("Can find " + optionalCar + " please enter a anther onwername");
        } else {
            Car car = optionalCar.get();
            car.setMileAge(carDto.mileAge);
            Car car1 = carRepos.save(car);
            return carToDto(car1);
        }
    }

    public CarOutputDto updateEngineType(String licensePlate, CarDto carDto) {
        Optional<Car> optionalCar = carRepos.findByLicensePlate(licensePlate);
        if (optionalCar.isEmpty()) {
            throw new RecordNotFoundException("Can find " + optionalCar + " please enter a anther onwername");
        } else {
            Car car = optionalCar.get();
            car.setEngineType(carDto.engineType);
            Car car1 = carRepos.save(car);
            return carToDto(car1);
        }
    }

    public void addInspectionToCar(long inspectionId, String licensePlate) {
        Optional<CarInspection> optionalCarInspection = carInspectionRepos.findById(inspectionId);
        Optional<Car> optionalCar = carRepos.findByLicensePlate(licensePlate);

        if (optionalCarInspection.isEmpty()) {
            throw new RecordNotFoundException("Inspection", "inspectionId", inspectionId);
        }
        if (optionalCar.isEmpty()) {
            throw new CarNotFoundException("car", "car license plate", licensePlate);
        } else {
            CarInspection inspection = optionalCarInspection.get();
            Car car = optionalCar.get();

            car.getCarInspection().add(inspection);
            carRepos.save(car);
        }
    }

    public String deleteCarByLicensePlate(String licensePlate) {
        Optional<Car> optionalCar = carRepos.findByLicensePlate(licensePlate);
        long count;
        if (optionalCar.isEmpty()) {
            throw new CarNotFoundException("car with carId:" + licensePlate + "is not found");
        } else {
            count = carRepos.count();
            carRepos.deleteByLicensePlate(licensePlate);
        }
        return "You delete" + count + "in the carId" + licensePlate;
    }

    public String deleteAllCars() {
        long count = carRepos.count();
        carRepos.deleteAll();
        return "You deleted " + count + " cars";
    }

    public CarOutputDto carToDto(Car car) {
        CarOutputDto dto = new CarOutputDto();
        dto.brand = car.getBrand();
        dto.model = car.getModel();
        dto.yearOfBuild = car.getYearOfBuild();
        dto.color = car.getColor();
        dto.licensePlate = car.getLicensePlate();
        dto.mileAge = car.getMileAge();
        dto.engineType = car.getEngineType();
        dto.body = car.getBody();
        dto.transmission = car.getTransmission();
        dto.fuel = car.getFuel();
        dto.account = car.getAccount();

        return dto;
    }

    public Car transferDtoToCar(CarDto carDto) {
        Car car = new Car();
        car.setBrand(carDto.brand);
        car.setModel(carDto.model);
        car.setYearOfBuild(carDto.yearOfBuild);
        car.setColor(carDto.color);
        car.setLicensePlate(carDto.licensePlate);
        car.setMileAge(carDto.mileAge);
        car.setEngineType(carDto.engineType);
        car.setBody(carDto.body);
        car.setTransmission(carDto.transmission);
        car.setFuel(carDto.fuel);
        return car;
    }
}
