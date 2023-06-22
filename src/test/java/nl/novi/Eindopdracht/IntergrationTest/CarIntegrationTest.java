package nl.novi.Eindopdracht.IntergrationTest;

import nl.novi.Eindopdracht.Models.Data.Car;
import nl.novi.Eindopdracht.Models.Data.CustomerAccount;
import nl.novi.Eindopdracht.Models.Data.Enum.*;
import nl.novi.Eindopdracht.Repository.CarRepository;
import nl.novi.Eindopdracht.Repository.CustomerAccountRepository;
import nl.novi.Eindopdracht.Service.ModelService.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;


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


    @BeforeEach
    void setUp() {


        car1 = new Car(1L, CarBrand.AUDI, CarModel.A3, LocalDate.of(2020, 4, 12), Colors.BLACK, "D-899-PP", 10202, EngineType.TSFI, Body.HATCHBACK, Transmission.AUTOMATIC, Fuel.PETROL, account1, null);
        car2 = new Car(2L, CarBrand.AUDI, CarModel.A3, LocalDate.of(2022, 8, 2), Colors.BROWN, "D-710-PP", 150123, EngineType.TDI, Body.HATCHBACK, Transmission.MANUAL, Fuel.DIESEL, account2, null);
        car3 = new Car(3L, CarBrand.AUDI, CarModel.A4, LocalDate.of(2018, 2, 5), Colors.SILVER, "G-810-DD", 501, EngineType.TSI, Body.SEDAN, Transmission.SEMIAUTOMATIC, Fuel.PETROL, account3, null);

        account1 = new CustomerAccount(1L, "Adriaan de Wit", "Adriaan", "De wit", "Prinsessenweg 19", "0623123421", "Prinsessenweg 19", "nl21INGB 5555 555 05");
        account2 = new CustomerAccount(2L, "Hendrick lopers", "Hendrick ", "Lopers", "DaltonLaan 21", "06123456778", "Daltonlaan 21", "nl21 55553218");
        account3 = new CustomerAccount(3L, "Jan Vermeer", "Jan", "Vermeer", "Biltstraat 3", "06789344561", "Biltstraat 3", "nl21 INGB 343321");

        carRepos.save(car1);
        carRepos.save(car2);
        carRepos.save(car3);


        accountRepos.save(account1);
        accountRepos.save(account2);
        accountRepos.save(account3);

    }


        @Test
        void getAllCars()throws Exception{

                mockMvc.perform(get("/car/find/all-cars"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$[0].id").value(car1.getId().toString()))
                        .andExpect( jsonPath("$[0].carBrand").value("AUDI"))
                        .andExpect( jsonPath("$[0].carModel").value("A3"))
                        .andExpect( jsonPath("$[0].manufactureDate").value("2020-04-12"))
                        .andExpect( jsonPath("$[0].color").value("BLACK"))
                        .andExpect( jsonPath("$[0].licensePlate").value("D-899-PP"))
                        .andExpect( jsonPath("$[0].mileage").value(10202))
                        .andExpect( jsonPath("$[0].engineType").value("TSFI"))
                        .andExpect( jsonPath("$[0].bodyType").value("HATCHBACK"))
                        .andExpect( jsonPath("$[0].transmissionType").value("AUTOMATIC"))
                        .andExpect( jsonPath("$[0].fuelType").value("PETROL"))
                        .andExpect( jsonPath("$[0].owner.id").value(1L))

                        .andExpect(jsonPath("$[1].id").value(car2.getId().toString()))
                        .andExpect(jsonPath("$[1].carBrand").value("AUDI"))
                        .andExpect( jsonPath("$[1].carModel").value("A3"))
                        .andExpect( jsonPath("$[1].manufactureDate").value("2022-08-02"))
                        .andExpect(jsonPath("$[1].color").value("BROWN"))
                        .andExpect(jsonPath("$[1].licensePlate").value("D-710-PP"))
                        .andExpect( jsonPath("$[1].mileage").value(150123))
                        .andExpect(jsonPath("$[1].engineType").value("TDI"))
                        .andExpect( jsonPath("$[1].bodyType").value("HATCHBACK"))
                        .andExpect( jsonPath("$[1].transmissionType").value("MANUAL"))
                        .andExpect( jsonPath("$[1].fuelType").value("DIESEL"))
                        .andExpect( jsonPath("$[1].owner.id").value(2L))

                        .andExpect( jsonPath("$[2].id").value(car3.getId().toString()))
                        .andExpect(jsonPath("$[2].carBrand").value("AUDI"))
                        .andExpect( jsonPath("$[2].carModel").value("A4"))
                        .andExpect( jsonPath("$[2].manufactureDate").value("2018-02-05"))
                        .andExpect( jsonPath("$[2].color").value("SILVER"))
                        .andExpect( jsonPath("$[2].licensePlate").value("G-810-DD"))
                        .andExpect(jsonPath("$[2].mileage").value(501))
                        .andExpect( jsonPath("$[2].engineType").value("TSI"))
                        .andExpect( jsonPath("$[2].bodyType").value("SEDAN"))
                        .andExpect(jsonPath("$[2].transmissionType").value("SEMIAUTOMATIC"))
                        .andExpect( jsonPath("$[2].fuelType").value("PETROL"))
                        .andExpect( jsonPath("$[2].owner.id").value(3L));


        }
    @Test
    void getCarById() throws Exception {
        mockMvc.perform(get("/car/find/car/" + car1.getLicensePlate()))
                .andExpect(status().isOk())
                .andExpect( jsonPath("$.id").value(car1.getId().toString()))
                .andExpect( jsonPath("$.carBrand").value("AUDI"))
                .andExpect( jsonPath("$.carModel").value("A3"))
                .andExpect( jsonPath("$.manufactureDate").value("2020-04-12"))
                .andExpect( jsonPath("$.color").value("BLACK"))
                .andExpect( jsonPath("$.licensePlate").value("D-899-PP"))
                .andExpect( jsonPath("$.mileage").value(10202))
                .andExpect(jsonPath("$.engineType").value("TSFI"))
                .andExpect( jsonPath("$.bodyType").value("HATCHBACK"))
                .andExpect( jsonPath("$.transmissionType").value("AUTOMATIC"))
                .andExpect( jsonPath("$.fuelType").value("PETROL"))
                .andExpect( jsonPath("$.owner.id").value(1L));

    }


}
