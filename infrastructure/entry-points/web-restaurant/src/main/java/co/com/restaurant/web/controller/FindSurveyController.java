package co.com.restaurant.web.controller;

import co.com.restaurant.usecase.findsurvey.FindSurveyUseCaseGateway;
import co.com.restaurant.web.dto.Response;
import co.com.restaurant.web.dto.SurveyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/survey")
@RestController
public class FindSurveyController {
    private final FindSurveyUseCaseGateway findSurveyUseCaseGateway;

    @GetMapping("{id}")
    public SurveyDTO getSurvey(@PathVariable Long id) {
        return Response.getSurveyDTO(findSurveyUseCaseGateway.findById(id));
    }
}
