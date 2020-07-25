package com.omkarlubal.buildouts.repositoryservices;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omkarlubal.quiz.QuizApplication;
import com.omkarlubal.quiz.dto.OnlyQuestion;
import com.omkarlubal.quiz.dto.Question;
import com.omkarlubal.quiz.model.QuestionEntity;
import com.omkarlubal.quiz.repositoryservices.QuestionRepositoryService;
import com.omkarlubal.quiz.util.FixtureHelpers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = {QuizApplication.class})
@ActiveProfiles("test")
public class QuestionRepositoryServiceTest {

  @Autowired
  QuestionRepositoryService questionRepositoryService;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private MongoTemplate mongoTemplate;

  List<QuestionEntity> allQuestion = new ArrayList<>();

  @BeforeEach
  void setup() throws IOException {
    allQuestion = listOfRestaurants();
    for (QuestionEntity restaurantEntity : allQuestion) {
      mongoTemplate.save(restaurantEntity, "question");
    }
  }

  @AfterEach
  void teardown() {
    mongoTemplate.dropCollection("question");
  }

  @Test
  void checkData() {
    List<OnlyQuestion> onlyQuestions = questionRepositoryService.fetchOnlyQuestions(1);
    assertEquals(3, onlyQuestions.size());
    List<Question> questions = questionRepositoryService.fetchQuestionsWithAnswers(1);
    assertEquals(3, questions.size());

  }

  private List<QuestionEntity> listOfRestaurants() throws IOException {
    String fixture =
        FixtureHelpers.fixture("initial_data_load.json");

    return objectMapper.readValue(fixture, new TypeReference<List<QuestionEntity>>() {
    });
  }

}