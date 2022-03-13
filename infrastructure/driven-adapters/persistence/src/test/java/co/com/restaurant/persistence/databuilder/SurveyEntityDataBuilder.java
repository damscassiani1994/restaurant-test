package co.com.restaurant.persistence.databuilder;

import co.com.restaurant.model.Choice;
import co.com.restaurant.model.Question;
import co.com.restaurant.model.Survey;
import co.com.restaurant.model.util.QuestionType;
import co.com.restaurant.persistence.entity.ChoiceEntity;
import co.com.restaurant.persistence.entity.QuestionEntity;
import co.com.restaurant.persistence.entity.SurveyEntity;

import java.util.Arrays;
import java.util.List;

public class SurveyEntityDataBuilder {
    private static final Long ID_SURVEY = 1L;
    private static final String TITLE_SURVEY = "Encuesta 1";
    private static final List<QuestionEntity> QUESTIONS = Arrays
            .asList(QuestionEntity
                    .builder()
                            .id(1L)
                            .description("Pregunta 1")
                            .type(QuestionType.MULTIPLE)
                            .choices(Arrays.
                            asList(ChoiceEntity
                                    .builder()
                                            .id(1L)
                                            .correct(true)
                                            .description("Opcion 1").build(),
                                    ChoiceEntity
                                            .builder()
                                            .id(1L)
                                            .correct(false)
                                            .description("Opcion 2").build())).build(),
                    QuestionEntity
                            .builder()
                            .id(1L)
                            .description("Pregunta 2")
                            .type(QuestionType.OPEN)
                            .choices(Arrays.asList()).build());

    private String tittle;
    private List<QuestionEntity> questions;

    public SurveyEntityDataBuilder() {
        this.tittle = TITLE_SURVEY;
        this.questions = QUESTIONS;
    }


    public String getTittle() {
        return tittle;
    }

    public SurveyEntityDataBuilder withTitle(String title) {
        this.tittle = tittle;
        return this;
    }

    public List<QuestionEntity> getQuestions() {
        return questions;
    }

    public SurveyEntityDataBuilder withQuestions(List<QuestionEntity> questions) {
        this.questions = questions;
        return this;
    }

    public SurveyEntity build() {
        return SurveyEntity
                .builder()
                .id(ID_SURVEY)
                .questions(questions)
                .title(tittle).build();
    }


}
