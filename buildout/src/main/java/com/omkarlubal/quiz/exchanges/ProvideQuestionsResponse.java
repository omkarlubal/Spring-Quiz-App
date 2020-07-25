package com.omkarlubal.quiz.exchanges;

import com.omkarlubal.quiz.dto.OnlyQuestion;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProvideQuestionsResponse {
  private List<OnlyQuestion> questions;
}