package nl.novi.Eindopdracht.IntergrationTest;

import nl.novi.Eindopdracht.Models.Data.CarParts.Brakes;
import nl.novi.Eindopdracht.Models.Data.Enum.PartType;
import nl.novi.Eindopdracht.Repository.BrakeRepository;
import nl.novi.Eindopdracht.Repository.CarRepository;
import nl.novi.Eindopdracht.Service.ModelService.BrakeService;
import nl.novi.Eindopdracht.dto.input.CarPartsDto.BrakesDto;
import nl.novi.Eindopdracht.dto.output.CarPartsDto.BrakesOutputDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;


import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class BrakeIntergrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BrakeService brakeService;
    @Autowired
    private BrakeRepository brakeRepos;
    // @Autowired
    // private CarInspectionRepository carInspectionRepository;
    // @Autowired
    // private CarInspectionService carInspectionService;
    // @Autowired
    // private CarService carService;
    @Autowired
    private CarRepository carRepos;

    BrakesDto brakesDto1;

    BrakesDto brakesDto2;

    BrakesDto brakesDto3;

    Brakes brake1;

    Brakes brake2;

    Brakes brake3;
    BrakesOutputDto brakesOutputDto1;
    BrakesOutputDto brakesOutputDto2;
    BrakesOutputDto brakesOutputDto3;

    @BeforeEach
    void setUp() {
        brake1 = new Brakes(1L, 'BRAKE' ,"brake", "1234", 12.00, 4, null, 23.00, 22.00, 10.00, 15.00, 28.00, 43.00, "F8,4", 14.00, true, false);
        brakeRepos.save(brake1);
    }

    /*
        @Test
        void getAllBrakes()throws Exception{

                mockMvc.perform(get("/parts/brakes/car/find/all"))
                        .andExpect(status().isOk())
                        .andExpect((ResultMatcher) jsonPath("$[0].id").value(1L))
                        .andExpect((ResultMatcher) jsonPath("$[0].partName").value("Brake Part"))
                        .andExpect((ResultMatcher) jsonPath("$[0].partNumber").value("123456"))
                        .andExpect((ResultMatcher) jsonPath("$[0].price").value(9.99))
                        .andExpect((ResultMatcher) jsonPath("$[0].amountOfParts").value(1))
                        .andExpect((ResultMatcher) jsonPath("$[0].outerDiameter").value(10.5))
                        .andExpect((ResultMatcher) jsonPath("$[0].centerDiameter").value(5.5))
                        .andExpect((ResultMatcher) jsonPath("$[0].boreTypeNumberOfHoles").value("Type A, 4 holes"))
                        .andExpect((ResultMatcher) jsonPath("$[0].wheelStudDiameter").value(6.0))
                        .andExpect((ResultMatcher) jsonPath("$[0].withoutWheelMountingBolts").value(false))
                        .andExpect((ResultMatcher) jsonPath("$[0]. withoutWheelHub").value(true))

                        .andExpect((ResultMatcher) jsonPath("$[1].id").value(2L))
                        .andExpect((ResultMatcher) jsonPath("$[1].partName").value("Brake Part2"))
                        .andExpect((ResultMatcher) jsonPath("$[1].partNumber").value("789012"))
                        .andExpect((ResultMatcher) jsonPath("$[1].price").value(14.99))
                        .andExpect((ResultMatcher) jsonPath("$[1].amountOfParts").value(2))
                        .andExpect((ResultMatcher) jsonPath("$[1].outerDiameter").value(11.0))
                        .andExpect((ResultMatcher) jsonPath("$[1].centerDiameter").value(6.0))
                        .andExpect((ResultMatcher)jsonPath("$[1].boreTypeNumberOfHoles").value( "Type B, 5 holes"))
                        .andExpect((ResultMatcher)jsonPath("$[1].wheelStudDiameter").value(6.5))
                        .andExpect((ResultMatcher)jsonPath("$[1].withoutWheelMountingBolts").value(true))
                        .andExpect((ResultMatcher)jsonPath("$[1]. withoutWheelHub").value(false))

                        .andExpect((ResultMatcher)jsonPath("$[2].id").value(3L))
                        .andExpect((ResultMatcher)jsonPath("$[2].partName").value("Brake Part 3"))
                        .andExpect((ResultMatcher)jsonPath("$[2].partNumber").value("345678"))
                        .andExpect((ResultMatcher)jsonPath("$[2].price").value(19.99))
                        .andExpect((ResultMatcher)jsonPath("$[2].amountOfParts").value(1))
                        .andExpect((ResultMatcher)jsonPath("$[2].outerDiameter").value(10.0))
                        .andExpect((ResultMatcher)jsonPath("$[2].centerDiameter").value(5.0))
                        .andExpect((ResultMatcher)jsonPath("$[2].boreTypeNumberOfHoles").value("Type C, 6 holes"))
                        .andExpect((ResultMatcher)jsonPath("$[2].wheelStudDiameter").value(6.2))
                        .andExpect((ResultMatcher)jsonPath("$[2].withoutWheelMountingBolts").value(false))
                        .andExpect((ResultMatcher)jsonPath("$[2]. withoutWheelHub").value(true));


        }*/
    @Test
    void getBrakeById() throws Exception {
        mockMvc.perform(get("/parts/brakes/find/" + brake1.getId()))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("partName").value(brake1.getPartName()));
             //andExpect((ResultMatcher) jsonPath("boreTypeNumberOfHoles").value(brake1.getBoreTypeNumberOfHoles()));
            //    .andExpect((ResultMatcher) jsonPath("outerDiameter").value(brake1.getOuterDiameter().toString()))
            //    .andExpect((ResultMatcher) jsonPath("withoutWheelHub").value(brake1.getWithoutWheelHub()));

    }


}
