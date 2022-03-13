package co.com.restaurant.web.util;

import co.com.restaurant.model.Question;
import co.com.restaurant.model.util.QuestionType;
import co.com.restaurant.web.dto.QuestionDTO;

public class CreateSurveyUtil {

    public static Boolean questionIsValid(QuestionDTO question) {
        if (QuestionType.MULTIPLE == question.getType() &&
                question.getChoices().isEmpty()) {
            return false;
        }

        if (QuestionType.OPEN == question.getType() &&
                !question.getChoices().isEmpty()) {
            return false;
        }
        return true;
    }

}
