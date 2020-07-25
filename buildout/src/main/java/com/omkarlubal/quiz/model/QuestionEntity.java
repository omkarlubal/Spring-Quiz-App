package com.omkarlubal.quiz.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "question")
@NoArgsConstructor
@AllArgsConstructor
public class QuestionEntity {

  @Id
  private String id;

  @NotNull
  private String questionId;

  @NotNull
  private String title;

  @NotNull
  private String description;

  @NotNull
  private String type;

  @NotNull
  private Map<String, String> options;

  // has different name in response as correct
  @NotNull
  private List<String> correctAnswer = new ArrayList<>();
}