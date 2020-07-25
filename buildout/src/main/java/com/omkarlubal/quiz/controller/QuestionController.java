package com.omkarlubal.quiz.controller;

import com.omkarlubal.quiz.exchanges.ProvideQuestionsResponse;
import com.omkarlubal.quiz.exchanges.SubmitQuestionRequest;
import com.omkarlubal.quiz.exchanges.SubmitQuestionResponse;
import com.omkarlubal.quiz.services.QuestionService;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
public class QuestionController {

  public static final String QUIZ_API_ENDPOINT = "/quiz";

  @Autowired
  QuestionService questionService;

  // Map GET requests to this method
  @GetMapping(QUIZ_API_ENDPOINT + "/1")
  public ProvideQuestionsResponse provideQuestions(Integer moduleId) {
    // module id to be integrated in future
    moduleId = 1;
    ProvideQuestionsResponse provideQuestionsResponse = questionService.fetchAllQuestions(moduleId);
    return provideQuestionsResponse;
  }

  // Map POST requests to this method
  @PostMapping(QUIZ_API_ENDPOINT + "/1")
  public SubmitQuestionResponse submitResponseWithAnswer(
        @RequestBody SubmitQuestionRequest submitQuestionRequest, Integer moduleId) {
    // module id to be integrated in future
    moduleId = 1;
    log.debug("#################### request received: {}", submitQuestionRequest.toString());
    SubmitQuestionResponse submitQuestionResponse = 
        questionService.fetchAllQuestionsWithResponse(moduleId, submitQuestionRequest);
    return submitQuestionResponse;
  }
}