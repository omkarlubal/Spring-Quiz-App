package com.omkarlubal.quiz.repositoryservices;

import com.omkarlubal.quiz.dto.OnlyQuestion;
import com.omkarlubal.quiz.dto.Question;
import com.omkarlubal.quiz.model.QuestionEntity;
import com.omkarlubal.quiz.repository.QuestionRepository;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionRepositoryServiceImpl implements QuestionRepositoryService {

  @Autowired
  QuestionRepository questionRepository;

  // Convert QuestionEntity to OnlyQuestion DTO
  List<OnlyQuestion> convertEntityToOnlyQuestion(List<QuestionEntity> questionEntities) {
    ModelMapper modelMapper = new ModelMapper();
    List<OnlyQuestion> onlyQuestions = new ArrayList<>();
    for (QuestionEntity questionEntity : questionEntities) {
      OnlyQuestion onlyQuestion = new OnlyQuestion();
      modelMapper.map(questionEntity, onlyQuestion);
      onlyQuestions.add(onlyQuestion);
    }

    return onlyQuestions;
  }

  // Only questions with options is returned
  @Override
  public List<OnlyQuestion> fetchOnlyQuestions(int moduleId) {
    List<QuestionEntity> quesEntities = questionRepository.findAll();
    List<OnlyQuestion> onlyQuestions = convertEntityToOnlyQuestion(quesEntities);
    return onlyQuestions;
  }

  // Convert QuestionEntity to Question DTO
  List<Question> convertEntityToQuestionAnswer(List<QuestionEntity> questionEntities) {
    ModelMapper modelMapper = new ModelMapper();

    modelMapper.addMappings(new PropertyMap<QuestionEntity, Question>() {
      // skipping fields that mismatch with Question DTO
      @Override
      protected void configure() {
          skip(destination.getUserAnswer());
          skip(destination.isAnswerCorrect());
          map().setCorrect(source.getCorrectAnswer());
      }
    });

    List<Question> questionsWithAnswers = new ArrayList<>();
    for (QuestionEntity questionEntity : questionEntities) {
      Question question = new Question();
      modelMapper.map(questionEntity, question);
      questionsWithAnswers.add(question);
    }

    return questionsWithAnswers;
  }

  // Questions with options as well as answers is returned
  @Override
  public List<Question> fetchQuestionsWithAnswers(int moduleId) {
    List<QuestionEntity> quesEntities = questionRepository.findAll();
    List<Question> questionsWithAnswers = convertEntityToQuestionAnswer(quesEntities);
    return questionsWithAnswers;
  }
    
}