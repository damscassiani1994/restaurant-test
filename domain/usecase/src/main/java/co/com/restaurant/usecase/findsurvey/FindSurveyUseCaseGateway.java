package co.com.restaurant.usecase.findsurvey;

import java.util.List;
import java.util.Optional;

import co.com.restaurant.model.Question;
import co.com.restaurant.model.Survey;

public interface FindSurveyUseCaseGateway {
	public Survey findById(Long id);

}
