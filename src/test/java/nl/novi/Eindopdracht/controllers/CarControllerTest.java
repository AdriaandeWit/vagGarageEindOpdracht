/*package nl.novi.Eindopdracht.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import nl.novi.Eindopdracht.Controllers.models.CarController;
import nl.novi.Eindopdracht.Models.Data.Car;
import nl.novi.Eindopdracht.Models.Data.CustomerAccount;
import nl.novi.Eindopdracht.Models.Data.Enum.*;
import nl.novi.Eindopdracht.Service.ModelService.CarService;
import nl.novi.Eindopdracht.Service.ModelService.CustomerAccountService;
import nl.novi.Eindopdracht.dto.input.CarDto;
import nl.novi.Eindopdracht.dto.input.CustomerAccountDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CarController.class)
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService carService;

    @MockBean
    private CustomerAccountService customerAccountService;

    CustomerAccount account1;
    CustomerAccount account2;
    CustomerAccount account3;

    CustomerAccountDto accountDto1;
    CustomerAccountDto accountDto2;
    CustomerAccountDto accountDto3;

    Car car1;
    Car car2;
    Car car3;
    Car car4;

    CarDto carDto1;
    CarDto carDto2;
    CarDto carDto3;
    CarDto CarDto4;


    @BeforeEach
    public void setUp() {

        account1 = new CustomerAccount(1L, "Adriaan de Wit", "Adriaan", "De wit", "Prinsessenweg 19", "0623123421", "Prinsessenweg 19", "nl21INGB 5555 555 05");
        account2 = new CustomerAccount(2L, "Hendrick lopers", "Hendrick ", "Lopers", "DaltonLaan 21", "06123456778", "Daltonlaan 21", "nl21 55553218");
        account3 = new CustomerAccount(3L, "Jan Vermeer", "Jan", "Vermeer", "Biltstraat 3", "06789344561", "Biltstraat 3", "nl21 INGB 343321");

        CustomerAccountDto customerAccount1 = new CustomerAccountDto();
        customerAccount1.setId(1L);
        customerAccount1.setCustomerName("Adriaan de Wit");
        customerAccount1.setFirstName("Adriaan");
        customerAccount1.setLastName("De wit");
        customerAccount1.setAddress("Prinsessenweg 19");
        customerAccount1.setPhoneNumber("0623123421");
        customerAccount1.setBillingAddress("Prinsessenweg 19");
        customerAccount1.setBankAccountNumber("nl21INGB 5555 555 05");

        CustomerAccountDto customerAccount2 = new CustomerAccountDto();
        customerAccount2.setId(2L);
        customerAccount2.setCustomerName("Hendrick lopers");
        customerAccount2.setFirstName("Hendrick");
        customerAccount2.setLastName("Lopers");
        customerAccount2.setAddress("DaltonLaan 21");
        customerAccount2.setPhoneNumber("06123456778");
        customerAccount2.setBillingAddress("Daltonlaan 21");
        customerAccount2.setBankAccountNumber("nl21 55553218");

        CustomerAccountDto customerAccount3 = new CustomerAccountDto();
        customerAccount3.setId(3L);
        customerAccount3.setCustomerName("Jan Vermeer");
        customerAccount3.setFirstName("Jan");
        customerAccount3.setLastName("Vermeer");
        customerAccount3.setAddress("Biltstraat 3");
        customerAccount3.setPhoneNumber("06789344561");
        customerAccount3.setBillingAddress("Biltstraat 3");
        customerAccount3.setBankAccountNumber("nl21 INGB 343321");


        car1 = new Car(CarBrand.VOLKSWAGEN, CarModel.GOLF, LocalDate.of(2020, 4, 12), Colors.BLACK, "D-899-PP", 10202, EngineType.TSI, Body.HATCHBACK, Transmission.AUTOMATIC, Fuel.Petrol, account1, null);
        car2 = new Car(CarBrand.AUDI, CarModel.A3, LocalDate.of(2022, 8, 2), Colors.BROWN, "D-710-PP", 150123, EngineType.TDI, Body.HATCHBACK, Transmission.Manual, Fuel.DIESEL, account2, null);
        car3 = new Car(CarBrand.AUDI, CarModel.A4, LocalDate.of(2018, 2, 5), Colors.SILVER, "G-810-DD", 501, EngineType.TSI, Body.SEDAN, Transmission.SEMIAUTOMATIC, Fuel.Petrol, account3, null);
        car4 = new Car(CarBrand.SEAT, CarModel.LEON, LocalDate.of(2020, 8, 2), Colors.GRAY, "G-703-DF", 23761, EngineType.TSI, Body.STATIONWAGON, Transmission.AUTOMATIC, Fuel.Petrol, account1, null);

        carDto1 = new CarDto();
        carDto1.setBrand(CarBrand.VOLKSWAGEN);
        carDto1.setModel(CarModel.GOLF);
        carDto1.setYearOfBuild(LocalDate.of(2020, 4, 12));
        carDto1.setColor(Colors.BLACK);
        carDto1.setLicensePlate("D-899-PP");
        carDto1.setMileAge(10202);
        carDto1.setEngineType(EngineType.TSI);
        carDto1.setBody(Body.HATCHBACK);
        carDto1.setTransmission(Transmission.AUTOMATIC);
        carDto1.setFuel(Fuel.Petrol);
        carDto1.setAccount(account1);

        CarDto carDto2 = new CarDto();
        carDto2.setBrand(CarBrand.AUDI);
        carDto2.setModel(CarModel.A3);
        carDto2.setYearOfBuild(LocalDate.of(2022, 8, 2));
        carDto2.setColor(Colors.BROWN);
        carDto2.setLicensePlate("D-710-PP");
        carDto2.setMileAge(150123);
        carDto2.setEngineType(EngineType.TDI);
        carDto2.setBody(Body.HATCHBACK);
        carDto2.setTransmission(Transmission.Manual);
        carDto2.setFuel(Fuel.DIESEL);
        carDto2.setAccount(account2);

        CarDto carDto3 = new CarDto();
        carDto3.setBrand(CarBrand.AUDI);
        carDto3.setModel(CarModel.A4);
        carDto3.setYearOfBuild(LocalDate.of(2018, 2, 5));
        carDto3.setColor(Colors.SILVER);
        carDto3.setLicensePlate("G-810-DD");
        carDto3.setMileAge(501);
        carDto3.setEngineType(EngineType.TSI);
        carDto3.setBody(Body.SEDAN);
        carDto3.setTransmission(Transmission.SEMIAUTOMATIC);
        carDto3.setFuel(Fuel.Petrol);
        carDto3.setAccount(account3);

        CarDto carDto4 = new CarDto();
        carDto4.setBrand(CarBrand.SEAT);
        carDto4.setModel(CarModel.LEON);
        carDto4.setYearOfBuild(LocalDate.of(2020, 8, 2));
        carDto4.setColor(Colors.GRAY);
        carDto4.setLicensePlate("G-703-DF");
        carDto4.setMileAge(23761);
        carDto4.setEngineType(EngineType.TSI);
        carDto4.setBody(Body.STATIONWAGON);
        carDto4.setTransmission(Transmission.AUTOMATIC);
        carDto4.setFuel(Fuel.Petrol);
        carDto4.setAccount(account1);

    }


    @Test
    void createCar() throws Exception {

        given(carService.createCar(carDto1)).willReturn(car1);


        mockMvc.perform(post("/car/create/car")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(carDto1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("brand").value(carDto1.brand.toString()))
                .andExpect(jsonPath("model").value(carDto1.model.toString()))
                .andExpect(jsonPath("yearOfBuild").value(carDto1.yearOfBuild.toString()))
                .andExpect(jsonPath("color").value(carDto1.color.toString()))
                .andExpect(jsonPath("licensePlate").value(carDto1.licensePlate))
                .andExpect(jsonPath("mileAge").value(carDto1.mileAge.toString()))
                .andExpect(jsonPath("engineType").value(carDto1.engineType.toString()))
                .andExpect(jsonPath("body").value(carDto1.body.toString()))
                .andExpect(jsonPath("transmission").value(carDto1.transmission.toString()))
                .andExpect(jsonPath("fuel").value(carDto1.fuel.toString()));
    }

    @Test
    void getAllCars() {
    }

    @Test
    void getCarById() {
    }

    @Test
    void getAccountByLicensePlate() {
    }

    @Test
    void updateCarMileage() {
    }

    @Test
    void updateEngineType() {
    }

    @Test
    void addAccountToCar() {
    }

    @Test
    void deleteCarById() {
    }

    @Test
    void deleteAllCars() {
    }

    public static String asJsonString(final Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            SimpleModule module = new SimpleModule();
            module.addSerializer(LocalDate.class,new LocalDateSerializer(DateTimeFormatter.ISO_DATE));
            objectMapper.registerModule(module);
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String transfer


}
*/