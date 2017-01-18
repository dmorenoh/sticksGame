package com.sticksGame.service;

import com.sticksGame.model.GlobalStatistics;
import com.sticksGame.model.UserStatistic;
import com.sticksGame.model.request.StatisticRequest;
import org.springframework.social.facebook.api.User;

/**
 * Created by dmorenoh on 8/1/17.
 */
public interface StatisticsService {
    UserStatistic saveUserStatistics(String userId, StatisticRequest request);
    UserStatistic getUserStatistics(String userId);
    GlobalStatistics getGlobalStatistics();
}
