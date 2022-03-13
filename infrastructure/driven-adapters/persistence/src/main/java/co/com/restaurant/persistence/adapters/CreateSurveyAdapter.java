package co.com.restaurant.persistence.adapters;

import co.com.restaurant.model.Survey;
import co.com.restaurant.gateway.CreateSurveyGateway;
import co.com.restaurant.persistence.entity.SurveyEntity;
import co.com.restaurant.persistence.repository.SurveyJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CreateSurveyAdapter implements CreateSurveyGateway {

    private final SurveyJPARepository surveyJPARepository;
    @Override
    public Optional<Survey> create(Survey survey) {
        SurveyEntity surveyRS = surveyJPARepository.save(SurveyEntity.getSurveyEntity(survey));
        return Optional.of(SurveyEntity.getSurvey(surveyRS));
    }
}
