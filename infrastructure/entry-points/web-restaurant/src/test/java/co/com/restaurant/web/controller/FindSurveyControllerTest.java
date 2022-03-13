package co.com.restaurant.web.controller;

import co.com.restaurant.usecase.findsurvey.FindSurveyUseCaseGateway;
import co.com.restaurant.web.databuilder.SurveyDTODataBuilder;
import co.com.restaurant.web.databuilder.SurveyDomainDataBuilder;
import co.com.restaurant.web.dto.SurveyDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FindSurveyController.class , webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class FindSurveyControllerTest {
    private static String PATH_CREATE = "/api/v1/survey/1";
    private static Long ID = 1L;

    @MockBean
    private FindSurveyUseCaseGateway findSurveyUseCaseGateway;
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int randomServerPort;

    @Test
    public void findSurveySuccess() throws Exception {
        final String baseUrl = "http://localhost:"+randomServerPort+PATH_CREATE;
        URI uri = new URI(baseUrl);

        Mockito.when(findSurveyUseCaseGateway.findById(ID)).thenReturn(new SurveyDomainDataBuilder().build());

        HttpEntity<SurveyDTO> request = new HttpEntity<>(new SurveyDTODataBuilder().build());

        ResponseEntity<SurveyDTO> result = this.restTemplate.getForEntity(uri, SurveyDTO.class);

        Assert.assertEquals(200, result.getStatusCodeValue());

    }
}
