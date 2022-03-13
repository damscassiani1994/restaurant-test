package co.com.restaurant.usecase.databuilder;

import co.com.restaurant.model.Choice;
import co.com.restaurant.model.Question;
import co.com.restaurant.model.Survey;
import co.com.restaurant.model.util.QuestionType;

import java.util.Arrays;
import java.util.List;

public class SurveyDataBuilder {
    private static final Long ID_SURVEY = 1L;
    private static final String TITLE_SURVEY = "Encuesta 1";
    private static final List<Question> QUESTIONS = Arrays
            .asList(Question
                    .builder()
                    .id(1L)
                    .description("Pregunta 1")
                    .type(QuestionType.MULTIPLE)
                    .choices(Arrays.
                            asList(Choice
                                    .builder()
                                    .id(1L)
                                    .correct(true)
                                    .description("Opcion 1").build(),
                                    Choice
                                            .builder()
                                            .id(2L)
                                            .correct(false)
                                            .description("Opcion 2").build())).build(),
                    Question
                            .builder()
                            .id(2L)
                            .description("Pregunta 2")
                            .type(QuestionType.OPEN).build());

    private Long id;
    private String tittle;
    private List<Question> questions;

    public SurveyDataBuilder() {
        this.id = ID_SURVEY;
        this.tittle = TITLE_SURVEY;
        this.questions = QUESTIONS;
    }

    public Long getId() {
        return id;
    }

    public SurveyDataBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public String getTittle() {
        return tittle;
    }

    public SurveyDataBuilder withTitle(String title) {
        this.tittle = tittle;
        return this;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public SurveyDataBuilder withQuestions(List<Question> questions) {
        this.questions = questions;
        return this;
    }

    public Survey build() {
        return Survey
                .builder()
                .questions(questions)
                .id(id)
                .title(tittle).build();
    }


}
