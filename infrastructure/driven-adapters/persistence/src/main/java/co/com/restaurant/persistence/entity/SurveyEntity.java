package co.com.restaurant.persistence.entity;

import co.com.restaurant.model.Survey;
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
@Table(name = "survey")
public class SurveyEntity implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @OneToMany(targetEntity = QuestionEntity.class, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestionEntity> questions = new ArrayList<>();


    public static Survey getSurvey(@NonNull SurveyEntity surveyEntity) {
        return Survey
                .builder()
                .id(surveyEntity.getId())
                .title(surveyEntity.getTitle())
                .questions(surveyEntity.getQuestions()
                        .stream()
                        .map(QuestionEntity::getQuestion)
                        .collect(Collectors.toList())).build();
    }

    public static SurveyEntity getSurveyEntity(@NonNull Survey survey) {
        return SurveyEntity
                .builder()
                .title(survey.getTitle())
                .questions(survey.getQuestions()
                        .stream()
                        .map(QuestionEntity::getQuestionEntity)
                        .collect(Collectors.toList())).build();
    }


}

