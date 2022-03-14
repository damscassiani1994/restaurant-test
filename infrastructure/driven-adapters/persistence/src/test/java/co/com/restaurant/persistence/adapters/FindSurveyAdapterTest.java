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
public class FindSurveyAdapterTest {

    private static final String TITLE = "Encuesta 1";
    private static final Long ID = 1L;

    @Mock
    private SurveyJPARepository surveyJPARepositoryMock;

    @InjectMocks
    private FindSurveyAdapter findSurveyAdapter;

    @Test
    public void createSurveySuccess() {
        SurveyEntity surveyEntity = new SurveyEntityDataBuilder().build();
        Mockito.when(surveyJPARepositoryMock.findById(ID))
                .thenReturn(Optional.of(surveyEntity));


        Optional<Survey> surveyRS = findSurveyAdapter.findById(ID);

        Assert.assertEquals(TITLE, surveyRS.get().getTitle());
        Mockito.verify(surveyJPARepositoryMock, Mockito.times(1)).findById(ID);
    }
}
