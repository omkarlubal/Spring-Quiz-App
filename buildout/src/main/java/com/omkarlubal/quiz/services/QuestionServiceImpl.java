package com.omkarlubal.quiz.services;

import com.omkarlubal.quiz.dto.OnlyQuestion;
import com.omkarlubal.quiz.dto.Question;
import com.omkarlubal.quiz.dto.Response;
import com.omkarlubal.quiz.dto.Summary;
import com.omkarlubal.quiz.exchanges.ProvideQuestionsResponse;
import com.omkarlubal.quiz.exchanges.SubmitQuestionRequest;
import com.omkarlubal.quiz.exchanges.SubmitQuestionResponse;
import com.omkarlubal.quiz.repositoryservices.QuestionRepositoryService;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {

  @Autowired
  QuestionRepositoryService questionRepositoryService;

  // return questions with only options
  @Override
  public ProvideQuestionsResponse fetchAllQuestions(int moduleId) {
    List<OnlyQuestion> onlyQuestions = questionRepositoryService
        .fetchOnlyQuestions(moduleId);
    Collections.sort(onlyQuestions, Comparator.comparing(OnlyQuestion::getQuestionId));
    ProvideQuestionsResponse provideQuestionsResponse = new ProvideQuestionsResponse(onlyQuestions);
    return provideQuestionsResponse;
  }

  // check whether answers returned by the client match the answers stored in database
  private boolean checkAnswers(Question question) {
    List<String> correctAnswer = question.getCorrect();
    List<String> userAnswer = question.getUserAnswer();

    // for multiple answer, if less than required answers selected then return false;
    if (correctAnswer.size() != userAnswer.size()) {
      return false;
    }

    Set<String> correctSet = new HashSet<>(correctAnswer);
    for (String answer : userAnswer) {
      if (!correctSet.contains(answer)) {
        return false;
      }
    }
    return true;
  }

  // populate remaining fields like user's answers and checking whether it is correct.
  private void fillRemainingFields(List<Question> questionsWithAnswers, 
      SubmitQuestionRequest submitQuestionRequest) {
    List<Response> responses = submitQuestionRequest.getResponses();
    Collections.sort(responses, Comparator.comparing(Response::getQuestionId));
    for (int i = 0; i < questionsWithAnswers.size(); i++) {
      Question question = questionsWithAnswers.get(i);
      Response response = responses.get(i);
      question.setUserAnswer(response.getUserResponse());
      boolean answerCorrect = checkAnswers(question);
      question.setAnswerCorrect(answerCorrect);
      questionsWithAnswers.set(i, question);
    }
  }

  // calculate number of answers the user has answered correctly
  private Summary calculateSummary(List<Question> questionsWithAnswers) {
    int correctAnswer = 0;
    for (Question question : questionsWithAnswers) {
      if (question.isAnswerCorrect()) {
        correctAnswer++;
      }
    }

    return new Summary(correctAnswer, questionsWithAnswers.size());
  }

  @Override
  public SubmitQuestionResponse fetchAllQuestionsWithResponse(int moduleId, 
      SubmitQuestionRequest submitQuestionRequest) {
    List<Question> questionsWithAnswers = questionRepositoryService
        .fetchQuestionsWithAnswers(moduleId);
    // populate questions object with remaining fields
    // userAnwer, Explanation, answerCorrect
    Collections.sort(questionsWithAnswers, Comparator.comparing(Question::getQuestionId));
    fillRemainingFields(questionsWithAnswers, submitQuestionRequest);

    // insert final summary
    Summary summary = calculateSummary(questionsWithAnswers);
    SubmitQuestionResponse submitQuestionResponse = new SubmitQuestionResponse(
        questionsWithAnswers, summary);
    return submitQuestionResponse;
  }
}