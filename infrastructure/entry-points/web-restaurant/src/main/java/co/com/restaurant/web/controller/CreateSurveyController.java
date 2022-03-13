package co.com.restaurant.web.controller;

import co.com.restaurant.usecase.createsurvey.CreateSurveyUseCaseGateway;
import co.com.restaurant.web.dto.Request;
import co.com.restaurant.web.dto.Response;
import co.com.restaurant.web.dto.SurveyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/api/v1/survey")
@RestController
public class CreateSurveyController {
    private final CreateSurveyUseCaseGateway createSurveyUseCaseGateway;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public SurveyDTO create(@Valid @RequestBody SurveyDTO surveyDTO) {
        return Response.getSurveyDTO(createSurveyUseCaseGateway.create(Request.getSurveyDTO(surveyDTO)));
    }

}
