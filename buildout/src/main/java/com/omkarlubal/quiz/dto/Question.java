package com.omkarlubal.quiz.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Question {
  private String questionId;
  private String title;
  private String description;
  private String type;
  private Map<String, String> options;
  // has different name in GetQuestionResponse as userResponse
  private List<String> userAnswer;

  // has different name in init as correctAnswer
  // will have to map correctAnswer to correct
  private List<String> correct;

  // these fields not present in mongo, but will be in return response
  private String explanation;
  private boolean answerCorrect;
}