package co.com.restaurant.model;

import java.util.List;

import co.com.restaurant.model.Choice;
import co.com.restaurant.model.util.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Question {

	private Long id;
	private String description;
	private QuestionType type;
	private List<Choice> choices;

}
