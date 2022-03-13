package co.com.restaurant.web.dto;

import co.com.restaurant.model.Choice;
import co.com.restaurant.model.Question;
import co.com.restaurant.model.Survey;
import lombok.NonNull;

import java.util.stream.Collectors;

public class Response {

    public static SurveyDTO getSurveyDTO(Survey survey) {
        return SurveyDTO
                .builder()
                .id(survey.getId())
                .title(survey.getTitle())
                .questions(survey
                        .getQuestions().
                        stream()
                        .map(Response::getQuestionDTO)
                        .collect(Collectors.toList())).build();
    }

    private static QuestionDTO getQuestionDTO(Question question) {
        return QuestionDTO
                .builder()
                .id(question.getId())
                .description(question.getDescription())
                .type(question.getType())
                .choices(question.getChoices()
                        .stream()
                        .map(Response::getChoiceDTO)
                        .collect(Collectors.toList())).build();
    }

    private static ChoiceDTO getChoiceDTO(Choice choice) {
        return ChoiceDTO
                .builder()
                .id(choice.getId())
                .description(choice.getDescription())
                .correct(choice.getCorrect())
                .build();
    }
}
