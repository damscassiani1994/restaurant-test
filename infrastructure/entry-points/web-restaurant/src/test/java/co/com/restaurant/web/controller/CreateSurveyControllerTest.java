package co.com.restaurant.web.controller;

import co.com.restaurant.model.exception.ErrorMessage;
import co.com.restaurant.model.exception.SurveyException;
import co.com.restaurant.usecase.createsurvey.CreateSurveyUseCaseGateway;
import co.com.restaurant.web.databuilder.SurveyDTODataBuilder;
import co.com.restaurant.web.databuilder.SurveyDomainDataBuilder;
import co.com.restaurant.web.dto.ErrorResponse;
import co.com.restaurant.web.dto.SurveyDTO;
import co.com.restaurant.web.error.HandlerError;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CreateSurveyController.class, HandlerError.class} , webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class CreateSurveyControllerTest {
    private static final String PATH_CREATE = "/api/v1/survey";
    private static final String HOST_NAME = "http://localhost:";
    private static final String ERROR_MESSAGE = "Error interno del servidor";
    private static final String ERROR_MESSAGE_BAD_REQUEST = "El cuerpo de la solicitud es inavlido";


    @MockBean
    private CreateSurveyUseCaseGateway createSurveyUseCaseGateway;
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private String randomServerPort;

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = HOST_NAME.concat(randomServerPort).concat(PATH_CREATE);
    }

    @Test
    public void createSurveySuccess() throws Exception {
        Mockito.when(createSurveyUseCaseGateway.create(new SurveyDomainDataBuilder().build()))
                .thenReturn(new SurveyDomainDataBuilder().build());

        HttpEntity<SurveyDTO> request = new HttpEntity<>(new SurveyDTODataBuilder().build());

        ResponseEntity<SurveyDTO> result = this.restTemplate.postForEntity(baseUrl, request, SurveyDTO.class);

        Assert.assertEquals(201, result.getStatusCodeValue());

    }

    @Test
    public void createSurveyWithError500InternalServerError() throws Exception {
        Mockito.when(createSurveyUseCaseGateway.create(new SurveyDomainDataBuilder().build()))
                .thenThrow(new SurveyException(ErrorMessage.ERROR_INTERNAL_SERVER));

        HttpEntity<SurveyDTO> request = new HttpEntity<>(new SurveyDTODataBuilder().build());

        ResponseEntity<ErrorResponse> result = this.restTemplate.postForEntity(baseUrl, request, ErrorResponse.class);

        Assert.assertEquals(500, result.getStatusCodeValue());
        Assert.assertEquals(ERROR_MESSAGE, result.getBody().getMessage());

    }

    @Test
    public void createSurveyWithError400BadRequest() throws Exception {
        HttpInputMessage inputMessage = Mockito.mock(HttpInputMessage.class);

        Mockito.when(createSurveyUseCaseGateway.create(new SurveyDomainDataBuilder().build()))
                .thenThrow(new HttpMessageNotReadableException(ERROR_MESSAGE_BAD_REQUEST, inputMessage));

        HttpEntity<SurveyDTO> request = new HttpEntity<>(new SurveyDTODataBuilder().build());

        ResponseEntity<ErrorResponse> result = this.restTemplate.postForEntity(baseUrl, request, ErrorResponse.class);

        Assert.assertEquals(400, result.getStatusCodeValue());
        Assert.assertEquals(ERROR_MESSAGE_BAD_REQUEST, result.getBody().getMessage());

    }




}
