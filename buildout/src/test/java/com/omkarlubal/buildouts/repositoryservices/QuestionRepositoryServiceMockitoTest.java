package com.omkarlubal.buildouts.repositoryservices;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omkarlubal.quiz.QuizApplication;
import com.omkarlubal.quiz.dto.Question;
import com.omkarlubal.quiz.model.QuestionEntity;
import com.omkarlubal.quiz.repository.QuestionRepository;
import com.omkarlubal.quiz.repositoryservices.QuestionRepositoryService;
import com.omkarlubal.quiz.util.FixtureHelpers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {QuizApplication.class})
@ActiveProfiles("test")
public class QuestionRepositoryServiceMockitoTest {
  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private MongoTemplate mongoTemplate;

  @MockBean
  QuestionRepository mockQuestionRepository;

  @Autowired
  QuestionRepositoryService questionRepositoryService;

  List<QuestionEntity> allQuestion = new ArrayList<>();

  @BeforeEach
  void setup() throws IOException {
    //MockitoAnnotations.initMocks(this);
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
  public void checkData() {

    when(mockQuestionRepository.findAll()).thenReturn(allQuestion);
    
    List<Question> questions = questionRepositoryService.fetchQuestionsWithAnswers(1);
    verify(mockQuestionRepository, times(1)).findAll();
    assertEquals(3, questions.size());

  }

  private List<QuestionEntity> listOfRestaurants() throws IOException {
    String fixture =
        FixtureHelpers.fixture("initial_data_load.json");

    return objectMapper.readValue(fixture, new TypeReference<List<QuestionEntity>>() {
    });
  }
    
}