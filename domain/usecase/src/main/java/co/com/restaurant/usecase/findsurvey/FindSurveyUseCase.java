package co.com.restaurant.usecase.findsurvey;

import co.com.restaurant.model.Survey;
import co.com.restaurant.gateway.FindSurveyGateway;
import co.com.restaurant.model.exception.ErrorMessage;
import co.com.restaurant.model.exception.SurveyException;

import java.util.Optional;

public class FindSurveyUseCase implements FindSurveyUseCaseGateway {
	
	private FindSurveyGateway questionGateway;
	
	public FindSurveyUseCase(FindSurveyGateway questionGateway) {
		this.questionGateway = questionGateway;
	}

	@Override
	public Survey findById(Long id) {
		return questionGateway
				.findById(id)
				.orElseThrow(() -> new SurveyException(ErrorMessage.NOT_FOUND));
	}
	

}
