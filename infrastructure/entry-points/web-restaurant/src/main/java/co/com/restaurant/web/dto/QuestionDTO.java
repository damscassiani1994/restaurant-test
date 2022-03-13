package co.com.restaurant.web.dto;

import co.com.restaurant.model.util.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class QuestionDTO {
    private Long id;
    private String description;
    private QuestionType type;
    private List<ChoiceDTO> choices = new ArrayList<>();
}
