package co.com.restaurant.usecase.createsurvey;

import co.com.restaurant.model.Survey;
import co.com.restaurant.gateway.CreateSurveyGateway;
import co.com.restaurant.model.exception.ErrorMessage;
import co.com.restaurant.model.exception.SurveyException;

public class CreateSurveyUseCase implements CreateSurveyUseCaseGateway{
    private CreateSurveyGateway createSurveyGateway;

    public CreateSurveyUseCase(CreateSurveyGateway createSurveyGateway) {
        this.createSurveyGateway = createSurveyGateway;
    }

    @Override
    public Survey create(Survey survey) {
        return createSurveyGateway.create(survey)
                .orElseThrow(() -> new SurveyException(ErrorMessage.ERROR_INTERNAL_SERVER));
    }
}
