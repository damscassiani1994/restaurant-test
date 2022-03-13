package co.com.restaurant.gateway;

import co.com.restaurant.model.Survey;

import java.util.Optional;

public interface CreateSurveyGateway {
    public Optional<Survey> create(Survey survey);
}
