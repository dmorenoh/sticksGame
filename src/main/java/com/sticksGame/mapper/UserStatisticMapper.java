package com.sticksGame.mapper;

import com.sticksGame.model.UserStatistic;
import com.sticksGame.model.request.StatisticRequest;

/**
 * Created by dmorenoh on 8/1/17.
 */
public interface UserStatisticMapper {
    UserStatistic map(String userId, StatisticRequest statisticRequest);
}
