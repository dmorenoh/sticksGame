package com.sticksGame.mapper;

import com.sticksGame.model.GlobalStatistics;
import com.sticksGame.model.UserStatistic;

import java.util.List;

/**
 * Created by dmorenoh on 8/1/17.
 */
public interface GlobalStatisticsMapper {
    GlobalStatistics map(List<UserStatistic> statistics);
}
