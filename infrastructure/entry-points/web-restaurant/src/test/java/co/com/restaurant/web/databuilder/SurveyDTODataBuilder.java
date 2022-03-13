package co.com.restaurant.web.databuilder;

import co.com.restaurant.model.util.QuestionType;
import co.com.restaurant.web.dto.ChoiceDTO;
import co.com.restaurant.web.dto.QuestionDTO;
import co.com.restaurant.web.dto.SurveyDTO;

import java.util.Arrays;
import java.util.List;

public class SurveyDTODataBuilder {
    private static final Long ID_SURVEY = 1L;
    private static final String TITLE_SURVEY = "Encuesta 1";
    private static final List<QuestionDTO> QUESTIONS = Arrays
            .asList(QuestionDTO
                    .builder()
                    .id(1L)
                    .description("Pregunta 1")
                    .type(QuestionType.MULTIPLE)
                    .choices(Arrays.
                            asList(ChoiceDTO
                                    .builder()
                                    .id(1L)
                                    .correct(true)
                                    .description("Opcion 1").build(),
                                    ChoiceDTO
                                            .builder()
                                            .id(2L)
                                            .correct(false)
                                            .description("Opcion 2").build())).build());

    private Long id;
    private String tittle;
    private List<QuestionDTO> questions;

    public SurveyDTODataBuilder() {
        this.id = ID_SURVEY;
        this.tittle = TITLE_SURVEY;
        this.questions = QUESTIONS;
    }

    public Long getId() {
        return id;
    }

    public SurveyDTODataBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public String getTittle() {
        return tittle;
    }

    public SurveyDTODataBuilder withTitle(String title) {
        this.tittle = tittle;
        return this;
    }

    public List<QuestionDTO> getQuestions() {
        return questions;
    }

    public SurveyDTODataBuilder withQuestions(List<QuestionDTO> questions) {
        this.questions = questions;
        return this;
    }

    public SurveyDTO build() {
        return SurveyDTO
                .builder()
                .questions(questions)
                .id(id)
                .title(tittle).build();
    }


}
