package co.com.restaurant.web.dto;

import co.com.restaurant.model.Choice;
import co.com.restaurant.model.Question;
import co.com.restaurant.model.Survey;
import co.com.restaurant.model.exception.ErrorMessage;
import co.com.restaurant.model.exception.SurveyException;
import co.com.restaurant.web.util.CreateSurveyUtil;

import java.util.stream.Collectors;

public class Request {

    public static Survey getSurveyDTO(SurveyDTO surveyDTO) {
        return Survey
                .builder()
                .id(surveyDTO.getId())
                .title(surveyDTO.getTitle())
                .questions(surveyDTO
                        .getQuestions().
                        stream()
                        .map(Request::getQuestion)
                        .collect(Collectors.toList())).build();
    }

    private static Question getQuestion(QuestionDTO questionDTO) {
        if (!CreateSurveyUtil.questionIsValid(questionDTO)) {
            throw new SurveyException(ErrorMessage.BAD_REQUEST);
        }
        return Question
                .builder()
                .id(questionDTO.getId())
                .description(questionDTO.getDescription())
                .type(questionDTO.getType())
                .choices(questionDTO.getChoices()
                        .stream()
                        .map(Request::getChoice)
                        .collect(Collectors.toList())).build();
    }

    private static Choice getChoice(ChoiceDTO choiceDTO) {
        return Choice
                .builder()
                .id(choiceDTO.getId())
                .description(choiceDTO.getDescription())
                .correct(choiceDTO.getCorrect())
                .build();
    }
}
