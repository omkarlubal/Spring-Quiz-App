package com.omkarlubal.quiz.services;

import com.omkarlubal.quiz.exchanges.ProvideQuestionsResponse;
import com.omkarlubal.quiz.exchanges.SubmitQuestionRequest;
import com.omkarlubal.quiz.exchanges.SubmitQuestionResponse;

public interface QuestionService {

  ProvideQuestionsResponse fetchAllQuestions(int moduleId);

  SubmitQuestionResponse fetchAllQuestionsWithResponse(int moduleId, 
      SubmitQuestionRequest submitQuestionRequest);

}