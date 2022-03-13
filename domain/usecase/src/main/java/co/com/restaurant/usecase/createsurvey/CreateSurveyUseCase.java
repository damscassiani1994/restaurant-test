package co.com.restaurant.usecase.createsurvey;

import co.com.restaurant.model.Survey;
import co.com.restaurant.gateway.CreateSurveyGateway;

public class CreateSurveyUseCase implements CreateSurveyUseCaseGateway{
    private CreateSurveyGateway createSurveyGateway;

    public CreateSurveyUseCase(CreateSurveyGateway createSurveyGateway) {
        this.createSurveyGateway = createSurveyGateway;
    }

    @Override
    public Survey create(Survey survey) {
        return createSurveyGateway.create(survey).get();
    }
}
