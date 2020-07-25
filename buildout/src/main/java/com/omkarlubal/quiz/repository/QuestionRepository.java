package com.omkarlubal.quiz.repository;

import com.omkarlubal.quiz.model.QuestionEntity;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends MongoRepository<QuestionEntity, String> {

}