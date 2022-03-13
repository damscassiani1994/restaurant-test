package co.com.restaurant.persistence.adapters;

import co.com.restaurant.model.exception.ErrorMessage;
import co.com.restaurant.model.exception.SurveyException;
import co.com.restaurant.persistence.entity.SurveyEntity;
import co.com.restaurant.persistence.repository.SurveyJPARepository;
import co.com.restaurant.model.Survey;
import co.com.restaurant.gateway.FindSurveyGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindSurveyAdapter implements FindSurveyGateway {
    private final SurveyJPARepository surveyJPARepository;
    @Override
    public Optional<Survey> findById(Long id) {
        return surveyJPARepository.findById(id)
                .map(SurveyEntity::getSurvey);
    }
}
