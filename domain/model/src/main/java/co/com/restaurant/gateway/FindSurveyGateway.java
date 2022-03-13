package co.com.restaurant.gateway;

import java.util.List;
import java.util.Optional;

import co.com.restaurant.model.Question;
import co.com.restaurant.model.Survey;

public interface FindSurveyGateway {
	public Optional<Survey> findById(Long id);
}
