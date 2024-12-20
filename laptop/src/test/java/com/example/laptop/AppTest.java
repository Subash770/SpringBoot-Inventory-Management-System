package com.example.laptop;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.nio.charset.Charset;

import javax.transaction.Transactional;

import static org.hamcrest.CoreMatchers.is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.laptop.entity.Laptop;
import com.example.laptop.repository.LaptopRepository;
import com.example.laptop.service.LaptopService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class AppTest {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	@Autowired
    private MockMvc mockMvc;

	@Autowired
    private LaptopService laptopService;

    @Autowired
    private LaptopRepository laptopRepository;

    @BeforeEach
    public void setUp() throws Exception {
        Laptop laptop1 = new Laptop();
        laptop1.setLaptopBrand("Dell");
        laptop1.setModelName("Inspiron");
        laptop1.setLaptopTag("DELL-INS-001");
        laptopRepository.save(laptop1);

		Laptop laptop2 = new Laptop();
        laptop2.setLaptopBrand("Dell");
        laptop2.setModelName("Latitude 7400");
        laptop2.setLaptopTag("Business Ultrabook");
        laptopRepository.save(laptop2);
    }

    @Test
    @DisplayName("test post laptop entity")
    @Order(1)
	public void testSaveLaptops() throws Exception {

        Laptop laptop3 = new Laptop();;
        laptop3.setLaptopBrand("HP");
        laptop3.setModelName("Pavilion x360");
        laptop3.setLaptopTag("Convertible Laptop");
        laptopRepository.save(laptop3);

        Laptop laptop = laptopService.saveLaptop(laptop3);

        assertEquals(laptop3.getId(), laptop.getId());
        assertEquals(laptop3.getLaptopBrand(), laptop.getLaptopBrand());
        assertEquals(laptop3.getModelName(), laptop.getModelName());
        assertEquals(laptop3.getLaptopTag(), laptop.getLaptopTag());

    }

    @Test
    @DisplayName("test delete by Id")
    @Transactional
    @Order(3)
	public void testDeleteById() throws Exception{

        String delString = laptopService.deleteLaptopById(2);

        assertEquals("The requested id got deleted", delString);

    }

    @Test
    @DisplayName("test post Laptop entity created response")
    @Order(4)
	public void testSaveLaptopsCreatedResponse() throws Exception {

        Laptop laptop3 = new Laptop();
        laptop3.setLaptopBrand("HP");
        laptop3.setModelName("Pavilion x360");
        laptop3.setLaptopTag("Convertible Laptop");
        laptopRepository.save(laptop3);
      

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(laptop3);

        mockMvc.perform(post("/laptops").contentType(APPLICATION_JSON_UTF8).content(requestJson))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.laptopBrand", is("HP")))
                .andExpect(jsonPath("$.modelName", is("Pavilion x360")))
                .andExpect(jsonPath("$.laptopTag", is("Convertible Laptop")));
    }

    @Test
    @DisplayName("test post Laptop entity bad request for null")
    @Order(5)
	public void testSaveLaptopsBadRequestNull() throws Exception {

        Laptop laptop3 = new Laptop();
        laptop3.setLaptopBrand("HP");
        laptop3.setModelName("");
        laptop3.setLaptopTag("Convertible Laptop");
        laptopRepository.save(laptop3);


        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(laptop3);

        mockMvc.perform(post("/laptops").contentType(APPLICATION_JSON_UTF8).content(requestJson))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("check delete laptops data Ok response")
    @Order(7)
    public void testDeleteLaptopByIdOk() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/laptops/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("check delete laptops data NotFound response")
    @Order(8)
    public void testDeleteLaptopByIdNotFound() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/laptops/15")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
