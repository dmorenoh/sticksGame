package com.sticksGame.mapper.impl;

import com.sticksGame.mapper.GlobalStatisticsMapper;
import com.sticksGame.model.GlobalStatistics;
import com.sticksGame.model.UserStatistic;
import com.sticksGame.utils.Utils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by dmorenoh on 8/1/17.
 */
@Component
public class GlobalStatisticsMapperImpl implements GlobalStatisticsMapper {

    @Override
    public GlobalStatistics map(List<UserStatistic> statistics) {
        GlobalStatistics globalStatistics = new GlobalStatistics();
        if (CollectionUtils.isEmpty(statistics)){
            return globalStatistics;
        }
        globalStatistics.setTotalGamesLost(
                statistics.stream().mapToInt(item -> item.getGamesLost()).sum());
        globalStatistics.setTotalGamesWon(
                statistics.stream().mapToInt(item -> item.getGamesWon()).sum());
        globalStatistics.setTotalGames(
                statistics.stream().mapToInt(item -> item.getTotalGames()).sum());
        globalStatistics.setGamesLostAverage(
                Utils.getAverage(globalStatistics.getTotalGamesLost(), globalStatistics.getTotalGames()));
        globalStatistics.setGamesWonAverage(
                Utils.getAverage(globalStatistics.getTotalGamesWon(), globalStatistics.getTotalGames()));

        return globalStatistics;
    }

}
