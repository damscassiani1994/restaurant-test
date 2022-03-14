package co.com.restaurant.usecase.createsurvey;

import co.com.restaurant.gateway.CreateSurveyGateway;
import co.com.restaurant.model.Survey;
import co.com.restaurant.model.exception.ErrorMessage;
import co.com.restaurant.model.exception.SurveyException;
import co.com.restaurant.usecase.databuilder.SurveyDataBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class CreateSurveyUseCaseTest {

    private static final Long ID = 1L;
    private static final String ERROR_MESSAGE = "Error interno del servidor";
    private static final Integer ERROR_CODE = 500;

    @Mock
    private CreateSurveyGateway createSurveyGateway;
    @InjectMocks
    private CreateSurveyUseCase createSurveyUseCase;

    Survey surveyMock;

    @Before
    public void setUp() {
        surveyMock = new SurveyDataBuilder().build();
    }

    @Test
    public void createSurveySuccess() {

        Mockito.when(createSurveyGateway.create(surveyMock)).thenReturn(Optional.of(surveyMock));

        Survey surveyRS = createSurveyUseCase.create(surveyMock);

        Assert.assertNotNull(surveyRS);
        Assert.assertEquals(ID, surveyRS.getId());
        Mockito.verify(createSurveyGateway, Mockito.times(1)).create(surveyMock);
    }

    @Test
    public void createSurveyWithErrorInternalSever() {

        Mockito.when(createSurveyGateway.create(surveyMock))
                .thenReturn(Optional.empty());

        try {
            createSurveyUseCase.create(surveyMock);
            Assert.fail();
        } catch (SurveyException exc) {
            Assert.assertEquals(ERROR_MESSAGE, exc.getErrorMessage().getMessage());
            Assert.assertEquals(ERROR_CODE, exc.getErrorMessage().getCode());
        }

    }

}
