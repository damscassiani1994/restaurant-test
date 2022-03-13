package co.com.restaurant.model;

import java.util.List;

import co.com.restaurant.model.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Survey {

	private Long id;
	private String title;
	private List<Question> questions;

}
