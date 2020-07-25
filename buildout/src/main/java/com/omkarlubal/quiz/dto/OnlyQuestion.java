package com.omkarlubal.quiz.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OnlyQuestion {
  private String questionId;
  private String title;
  private String type;
  private Map<String, String> options;
}