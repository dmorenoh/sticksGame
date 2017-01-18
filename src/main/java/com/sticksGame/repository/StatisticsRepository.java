package com.sticksGame.repository;

import com.sticksGame.model.UserStatistic;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by dmorenoh on 10/1/17.
 */
public interface StatisticsRepository extends MongoRepository<UserStatistic, String> {
    UserStatistic findByUserId(String userId);
}
