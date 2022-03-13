package co.com.restaurant.usecase.createsurvey;

import co.com.restaurant.gateway.CreateSurveyGateway;
import co.com.restaurant.model.Survey;
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
public class CreateSurveyUseCaseTest {

    private static final Long ID = 1L;

    @Mock
    private CreateSurveyGateway createSurveyGateway;
    @InjectMocks
    private CreateSurveyUseCase createSurveyUseCase;

    @Test
    public void createSurveySuccess() {
        Survey surveyMock = new SurveyDataBuilder().build();

        Mockito.when(createSurveyGateway.create(surveyMock)).thenReturn(Optional.of(surveyMock));

        Survey surveyRS = createSurveyUseCase.create(surveyMock);

        Assert.assertNotNull(surveyRS);
        Assert.assertEquals(ID, surveyRS.getId());
        Mockito.verify(createSurveyGateway, Mockito.times(1)).create(surveyMock);
    }

}
