package co.com.restaurant.persistence.entity;

import co.com.restaurant.model.Question;
import co.com.restaurant.model.util.QuestionType;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Question")
public class QuestionEntity implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String description;
    @Enumerated(EnumType.STRING)
    private QuestionType type;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private SurveyEntity survey;

    @OneToMany(targetEntity = ChoiceEntity.class, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChoiceEntity> choices = new ArrayList<>();


    public static Question getQuestion(@NonNull QuestionEntity questionEntity) {
        return Question.builder()
                .id(questionEntity.getId())
                .type(questionEntity.getType())
                .description(questionEntity.getDescription())
                .choices(questionEntity.getChoices()
                        .stream()
                        .map(ChoiceEntity::getChoice)
                        .collect(Collectors.toList())).build();
    }

    public static QuestionEntity getQuestionEntity(@NonNull Question question) {
        return QuestionEntity
                .builder()
                .description(question.getDescription())
                .type(question.getType())
                .choices(ChoiceEntity.valid(question.getChoices())
                        .stream()
                        .map(ChoiceEntity::getChoiceEntity)
                        .collect(Collectors.toList()))
                .build();
    }


}

