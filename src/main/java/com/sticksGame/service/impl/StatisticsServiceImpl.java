package com.sticksGame.service.impl;

import com.sticksGame.mapper.GlobalStatisticsMapper;
import com.sticksGame.mapper.UserStatisticMapper;
import com.sticksGame.model.GlobalStatistics;
import com.sticksGame.model.UserStatistic;
import com.sticksGame.model.request.StatisticRequest;
import com.sticksGame.repository.StatisticsRepository;
import com.sticksGame.service.StatisticsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by dmorenoh on 8/1/17.
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Inject
    private StatisticsRepository statisticsRepository;

    @Inject
    private UserStatisticMapper userStatisticMapper;

    @Inject
    private GlobalStatisticsMapper globalStatisticsMapper;

    @Override
    public GlobalStatistics getGlobalStatistics() {
        List<UserStatistic> statistics = statisticsRepository.findAll();
        GlobalStatistics globalStatistics = globalStatisticsMapper.map(statistics);
        return globalStatistics;
    }

    @Override
    public UserStatistic saveUserStatistics(String userId, StatisticRequest request) {
        validateUserId(userId);
        UserStatistic userStatistic = userStatisticMapper.map(userId, request);
        return statisticsRepository.save(userStatistic);
    }

    @Override
    public UserStatistic getUserStatistics(String userId) {
        validateUserId(userId);
        UserStatistic userStatistic = statisticsRepository.findByUserId(userId);
        return userStatistic;
    }

    private void validateUserId(String userId) {
        if (StringUtils.isBlank(userId)) {
            throw new IllegalArgumentException("userId not provided");
        }
    }


}
