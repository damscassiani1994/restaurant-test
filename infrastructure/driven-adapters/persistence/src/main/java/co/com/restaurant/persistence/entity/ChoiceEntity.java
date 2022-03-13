package co.com.restaurant.persistence.entity;

import co.com.restaurant.model.Choice;
import lombok.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "choice")
public class ChoiceEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Boolean correct;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private QuestionEntity question;

    public static Choice getChoice(@NonNull ChoiceEntity choiceEntity) {
        return Choice
                .builder()
                .id(choiceEntity.getId())
                .correct(choiceEntity.getCorrect())
                .description(choiceEntity.getDescription())
                .build();
    }
    public static ChoiceEntity getChoiceEntity(@NonNull Choice choice) {
        return ChoiceEntity
                .builder()
                .correct(choice.getCorrect())
                .description(choice.getDescription())
                .build();
    }

    public static List<Choice> valid(List<Choice> choices) {
        if (null != choices) {
            return choices;
        }

        return Arrays.asList();
    }




}
