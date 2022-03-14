package co.com.restaurant.usecase.findsurvey;

import co.com.restaurant.gateway.FindSurveyGateway;
import co.com.restaurant.model.Survey;
import co.com.restaurant.model.exception.ErrorMessage;
import co.com.restaurant.model.exception.SurveyException;
import co.com.restaurant.usecase.databuilder.SurveyDataBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class FindSurveyUseCaseTest {

    private static final Long ID = 1L;
    private static final String TITLE = "Encuesta 1";
    private static final String ERROR_MESSAGE = "No se encontraron registros";
    private static final Integer ERROR_CODE = 404;

    @Mock
    private FindSurveyGateway findSurveyGateway;
    @InjectMocks
    private FindSurveyUseCase findSurveyUseCase;

    @Test
    public void findSurveySuccess() {

        Mockito.when(findSurveyGateway.findById(ID)).thenReturn(Optional.of(new SurveyDataBuilder().build()));

        Survey surveyRS = findSurveyUseCase.findById(ID);

        Assert.assertNotNull(surveyRS);
        Assert.assertEquals(ID, surveyRS.getId());
        Assert.assertEquals(TITLE, surveyRS.getTitle());

    }

    @Test
    public void findSurveyWithErrorNOTFOUND() {

        Mockito.when(findSurveyGateway.findById(ID)).thenReturn(Optional.empty());;


        try {
            findSurveyUseCase.findById(ID);
            Assert.fail();
        } catch (SurveyException exc) {
            Assert.assertEquals(ERROR_MESSAGE, exc.getErrorMessage().getMessage());
            Assert.assertEquals(ERROR_CODE, exc.getErrorMessage().getCode());
        }

    }
}
