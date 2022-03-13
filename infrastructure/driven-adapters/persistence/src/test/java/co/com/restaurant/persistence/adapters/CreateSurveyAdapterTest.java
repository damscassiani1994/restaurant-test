package co.com.restaurant.persistence.adapters;

import co.com.restaurant.model.Survey;
import co.com.restaurant.persistence.databuilder.SurveyDomainDataBuilder;
import co.com.restaurant.persistence.databuilder.SurveyEntityDataBuilder;
import co.com.restaurant.persistence.entity.SurveyEntity;
import co.com.restaurant.persistence.repository.SurveyJPARepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class CreateSurveyAdapterTest {

    private static final String TITLE = "Encuesta 1";

    @Mock
    private SurveyJPARepository surveyJPARepositoryMock;

    @InjectMocks
    private CreateSurveyAdapter createSurveyAdapter;

    @Test
    @DisplayName("when run create survey successfully in persistence")
    public void createSurveySuccess() {
        SurveyEntity surveyEntity = new SurveyEntityDataBuilder().build();
        Mockito.when(surveyJPARepositoryMock.save(Mockito.any()))
                .thenReturn(surveyEntity);


        Optional<Survey> surveyRS = createSurveyAdapter.create(new SurveyDomainDataBuilder().build());

        Assert.assertEquals(TITLE, surveyRS.get().getTitle());
        Mockito.verify(surveyJPARepositoryMock, Mockito.times(1)).save(Mockito.any());
    }
}
