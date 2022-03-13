package co.com.restaurant.web.controller;

import co.com.restaurant.usecase.createsurvey.CreateSurveyUseCaseGateway;
import co.com.restaurant.web.databuilder.SurveyDTODataBuilder;
import co.com.restaurant.web.databuilder.SurveyDomainDataBuilder;
import co.com.restaurant.web.dto.SurveyDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CreateSurveyController.class , webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class CreateSurveyControllerTest {
    private static String PATH_CREATE = "/api/v1/survey";


    @MockBean
    private CreateSurveyUseCaseGateway createSurveyUseCaseGateway;
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int randomServerPort;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void createSurveySuccess() throws Exception {
        final String baseUrl = "http://localhost:"+randomServerPort+PATH_CREATE;
        URI uri = new URI(baseUrl);

        Mockito.when(createSurveyUseCaseGateway.create(new SurveyDomainDataBuilder().build()))
                .thenReturn(new SurveyDomainDataBuilder().build());

        HttpEntity<SurveyDTO> request = new HttpEntity<>(new SurveyDTODataBuilder().build());

        ResponseEntity<SurveyDTO> result = this.restTemplate.postForEntity(baseUrl, request, SurveyDTO.class);

        Assert.assertEquals(201, result.getStatusCodeValue());

    }


}
