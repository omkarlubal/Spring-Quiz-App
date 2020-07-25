package com.omkarlubal.quiz.repositoryservices;

import com.omkarlubal.quiz.dto.OnlyQuestion;
import com.omkarlubal.quiz.dto.Question;

import java.util.List;

public interface QuestionRepositoryService {

  List<OnlyQuestion> fetchOnlyQuestions(int moduleId);

  List<Question> fetchQuestionsWithAnswers(int moduleId);

}