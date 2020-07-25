package com.omkarlubal.quiz.exchanges;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.omkarlubal.quiz.dto.Question;
import com.omkarlubal.quiz.dto.Summary;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubmitQuestionResponse {
  private List<Question> questions;
  private Summary summary;
}