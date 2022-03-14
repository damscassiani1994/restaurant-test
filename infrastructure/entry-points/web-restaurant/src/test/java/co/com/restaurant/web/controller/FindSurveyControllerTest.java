package co.com.restaurant.web.controller;

import co.com.restaurant.model.exception.ErrorMessage;
import co.com.restaurant.model.exception.SurveyException;
import co.com.restaurant.usecase.findsurvey.FindSurveyUseCaseGateway;
import co.com.restaurant.web.databuilder.SurveyDomainDataBuilder;
import co.com.restaurant.web.dto.ErrorResponse;
import co.com.restaurant.web.dto.SurveyDTO;
import co.com.restaurant.web.error.HandlerError;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FindSurveyController.class, HandlerError.class} , webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class FindSurveyControllerTest {
    private static final String PATH_FIND = "/api/v1/survey/1";
    private static final String HOST_NAME = "http://localhost:";
    private static final String ERROR_MESSAGE = "No se encontraron registros";
    private static final Long ID = 1L;

    @MockBean
    private FindSurveyUseCaseGateway findSurveyUseCaseGateway;
    @Autowired
    private TestRestTemplate restTemplate;
    @LocalServerPort
    private String randomServerPort;
    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = HOST_NAME.concat(randomServerPort).concat(PATH_FIND);
    }

    @Test
    public void findSurveySuccess()  {


        Mockito.when(findSurveyUseCaseGateway.findById(ID)).thenReturn(new SurveyDomainDataBuilder().build());

        ResponseEntity<SurveyDTO> result = this.restTemplate.getForEntity(baseUrl, SurveyDTO.class);

        Assert.assertEquals(200, result.getStatusCodeValue());

    }

    @Test
    public void findSurveyWithError404() {

        Mockito.when(findSurveyUseCaseGateway.findById(ID)).thenThrow(new SurveyException(ErrorMessage.NOT_FOUND));

        ResponseEntity<ErrorResponse> result = this.restTemplate.getForEntity(baseUrl, ErrorResponse.class);

        Assert.assertEquals(404, result.getStatusCodeValue());
        Assert.assertEquals(ERROR_MESSAGE,
                result.getBody().getMessage());

    }
}
