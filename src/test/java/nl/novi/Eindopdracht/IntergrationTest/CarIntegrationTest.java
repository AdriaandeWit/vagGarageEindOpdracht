package nl.novi.Eindopdracht.IntergrationTest;

import nl.novi.Eindopdracht.Models.Data.Car;
import nl.novi.Eindopdracht.Models.Data.CarInspection;
import nl.novi.Eindopdracht.Models.Data.CustomerAccount;
import nl.novi.Eindopdracht.Models.Data.Enum.*;
import nl.novi.Eindopdracht.Repository.CarRepository;
import nl.novi.Eindopdracht.Repository.CustomerAccountRepository;
import nl.novi.Eindopdracht.Service.ModelService.CarService;
import nl.novi.Eindopdracht.dto.output.CarOutputDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;


import static nl.novi.Eindopdracht.Models.Data.Enum.Transmission.MANUAL;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class CarIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CarService carService;
    @Autowired
    private CarRepository carRepos;
    @Autowired
    private CustomerAccountRepository accountRepos;


    Car car1;

    Car car2;

    Car car3;

    CustomerAccount account1;
    CustomerAccount account2;
    CustomerAccount account3;

    CarOutputDto carOutputDto1;
    CarOutputDto carOutputDto2;
    CarOutputDto carOutputDto3;


    @BeforeEach
    void setUp() {


        car1 = new Car(1L, CarBrand.AUDI, CarModel.A3, LocalDate.of(2020, 4, 12), Colors.BLACK, "D-899-PP", 10202, EngineType.TSFI, Body.HATCHBACK, Transmission.AUTOMATIC, Fuel.PETROL, null, null);
        car2 = new Car(2L, CarBrand.AUDI, CarModel.A3, LocalDate.of(2022, 8, 2), Colors.BROWN, "D-710-PP", 150123, EngineType.TDI, Body.HATCHBACK, MANUAL, Fuel.DIESEL, null, null);
        car3 = new Car(3L, CarBrand.AUDI, CarModel.A4, LocalDate.of(2018, 2, 5), Colors.SILVER, "G-810-DD", 501, EngineType.TSI, Body.SEDAN, Transmission.SEMIAUTOMATIC, Fuel.PETROL, null, null);

        account1 = new CustomerAccount("Adriaan de Wit", "Adriaan", "De wit", "Prinsessenweg 19", "0623123421", "Prinsessenweg 19", "nl21INGB 5555 555 05");
        account2 = new CustomerAccount("Hendrick lopers", "Hendrick ", "Lopers", "DaltonLaan 21", "06123456778", "Daltonlaan 21", "nl21 55553218");
        account3 = new CustomerAccount("Jan Vermeer", "Jan", "Vermeer", "Biltstraat 3", "06789344561", "Biltstraat 3", "nl21 INGB 343321");

        carOutputDto1 = new CarOutputDto();
        carOutputDto1.setId(car1.getId());
        carOutputDto1.setBrand(car1.getBrand());
        carOutputDto1.setModel(car1.getModel());
        carOutputDto1.setYearOfBuild(car1.getYearOfBuild());
        carOutputDto1.setColor(car1.getColor());
        carOutputDto1.setLicensePlate(car1.getLicensePlate());
        carOutputDto1.setMileAge(car1.getMileAge());
        carOutputDto1.setEngineType(car1.getEngineType());
        carOutputDto1.setBody(car1.getBody());
        carOutputDto1.setTransmission(car1.getTransmission());
        carOutputDto1.setFuel(car1.getFuel());

        carOutputDto2 = new CarOutputDto();
        carOutputDto2.setId(car2.getId());
        carOutputDto2.setBrand(car2.getBrand());
        carOutputDto2.setModel(car2.getModel());
        carOutputDto2.setYearOfBuild(car2.getYearOfBuild());
        carOutputDto2.setColor(car2.getColor());
        carOutputDto2.setLicensePlate(car2.getLicensePlate());
        carOutputDto2.setMileAge(car2.getMileAge());
        carOutputDto2.setEngineType(car2.getEngineType());
        carOutputDto2.setBody(car2.getBody());
        carOutputDto2.setTransmission(car2.getTransmission());
        carOutputDto2.setFuel(car2.getFuel());

        carOutputDto3 = new CarOutputDto();
        carOutputDto3.setId(car3.getId());
        carOutputDto3.setBrand(car3.getBrand());
        carOutputDto3.setModel(car3.getModel());
        carOutputDto3.setYearOfBuild(car3.getYearOfBuild());
        carOutputDto3.setColor(car3.getColor());
        carOutputDto3.setLicensePlate(car3.getLicensePlate());
        carOutputDto3.setMileAge(car3.getMileAge());
        carOutputDto3.setEngineType(car3.getEngineType());
        carOutputDto3.setBody(car3.getBody());
        carOutputDto3.setTransmission(car3.getTransmission());
        carOutputDto3.setFuel(car3.getFuel());



        accountRepos.save(account1);
        accountRepos.save(account2);
        accountRepos.save(account3);

        carRepos.save(car1);
        carRepos.save(car2);
        carRepos.save(car3);
    }


    @Test
    void getAllCars() throws Exception {

        mockMvc.perform(get("/car/find/all-cars"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(car1.getId().toString()))
                .andExpect(jsonPath("$[0].brand").value("AUDI"))
                .andExpect(jsonPath("$[0].model").value("A3"))
                .andExpect(jsonPath("$[0].yearOfBuild").value("2020-04-12"))
                .andExpect(jsonPath("$[0].color").value("BLACK"))
                .andExpect(jsonPath("$[0].licensePlate").value("D-899-PP"))
                .andExpect(jsonPath("$[0].mileAge").value(10202))
                .andExpect(jsonPath("$[0].engineType").value("TSFI"))
                .andExpect(jsonPath("$[0].body").value("HATCHBACK"))
                .andExpect(jsonPath("$[0].transmission").value("AUTOMATIC"))
                .andExpect(jsonPath("$[0].fuel").value("PETROL"))


                .andExpect(jsonPath("$[1].id").value(car2.getId().toString()))
                .andExpect(jsonPath("$[1].brand").value("AUDI"))
                .andExpect(jsonPath("$[1].model").value("A3"))
                .andExpect(jsonPath("$[1].yearOfBuild").value("2022-08-02"))
                .andExpect(jsonPath("$[1].color").value("BROWN"))
                .andExpect(jsonPath("$[1].licensePlate").value("D-710-PP"))
                .andExpect(jsonPath("$[1].mileAge").value(150123))
                .andExpect(jsonPath("$[1].engineType").value("TDI"))
                .andExpect(jsonPath("$[1].body").value("HATCHBACK"))
                .andExpect(jsonPath("$[1].transmission").value("MANUAL"))
                .andExpect(jsonPath("$[1].fuel").value("DIESEL"))


                .andExpect(jsonPath("$[2].id").value(car3.getId().toString()))
                .andExpect(jsonPath("$[2].brand").value("AUDI"))
                .andExpect(jsonPath("$[2].model").value("A4"))
                .andExpect(jsonPath("$[2].yearOfBuild").value("2018-02-05"))
                .andExpect(jsonPath("$[2].color").value("SILVER"))
                .andExpect(jsonPath("$[2].licensePlate").value("G-810-DD"))
                .andExpect(jsonPath("$[2].mileAge").value(501))
                .andExpect(jsonPath("$[2].engineType").value("TSI"))
                .andExpect(jsonPath("$[2].body").value("SEDAN"))
                .andExpect(jsonPath("$[2].transmission").value("SEMIAUTOMATIC"))
                .andExpect(jsonPath("$[2].fuel").value("PETROL"));


    }

    @Test
    void getCarById() throws Exception {
        mockMvc.perform(get("/car/find/car")
                        .param("licensePlate", car1.getLicensePlate()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(car1.getId().toString()))
                .andExpect(jsonPath("brand").value("AUDI"))
                .andExpect(jsonPath("model").value("A3"))
                .andExpect(jsonPath("yearOfBuild").value("2020-04-12"))
                .andExpect(jsonPath("color").value("BLACK"))
                .andExpect(jsonPath("licensePlate").value("D-899-PP"))
                .andExpect(jsonPath("mileAge").value(10202))
                .andExpect(jsonPath("engineType").value("TSFI"))
                .andExpect(jsonPath("body").value("HATCHBACK"))
                .andExpect(jsonPath("transmission").value("AUTOMATIC"))
                .andExpect(jsonPath("fuel").value("PETROL"));

    }


}
