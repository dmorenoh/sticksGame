package com.sticksGame.mapper.impl;

import com.sticksGame.mapper.UserStatisticMapper;
import com.sticksGame.model.UserStatistic;
import com.sticksGame.model.request.StatisticRequest;
import org.springframework.stereotype.Component;

/**
 * Created by dmorenoh on 8/1/17.
 */
@Component
public class UserStatisticMapperImpl implements UserStatisticMapper {
    @Override
    public UserStatistic map(String userId, StatisticRequest statisticRequest) {
        UserStatistic userStatistic = new UserStatistic();
        userStatistic.setUserId(userId);
        userStatistic.setGamesLost(statisticRequest.getLost());
        userStatistic.setGamesWon(statisticRequest.getWon());
        userStatistic.setTotalGames(statisticRequest.getLost() + statisticRequest.getWon());
        return userStatistic;
    }

}
