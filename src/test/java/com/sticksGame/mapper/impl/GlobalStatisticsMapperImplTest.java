package com.sticksGame.mapper.impl;

import com.sticksGame.model.GlobalStatistics;
import com.sticksGame.model.UserStatistic;
import com.sticksGame.utils.Utils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;

/**
 * Created by dmorenoh on 9/1/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class GlobalStatisticsMapperImplTest {
    public static final String USER_ID_1 = "userId1";
    public static final String USER_ID_2 = "userId2";
    @InjectMocks
    private GlobalStatisticsMapperImpl testSubject;

    @Test
    public void map_emptyListOfStatistics_emptyGlobalStatistics(){
        GlobalStatistics returned = testSubject.map(new ArrayList<>());
        assertThat(returned.getTotalGames(), equalTo(0));
        assertThat(returned.getTotalGamesLost(), equalTo(0));
        assertThat(returned.getTotalGamesWon(), equalTo(0));
        assertThat(returned.getGamesLostAverage(), equalTo(new BigDecimal("0.00")));
        assertThat(returned.getGamesWonAverage(), equalTo(new BigDecimal("0.00")));

    }

    @Test
    public void map_listOfUsers_getGlobalStatistics(){
        List<UserStatistic> gameUsers = aListStatistics();
        GlobalStatistics returned = testSubject.map(gameUsers);
        assertThat(returned, is(not(nullValue())));
        assertThat(returned.getTotalGames(), equalTo(50));
        assertThat(returned.getTotalGamesLost(), equalTo(20));
        assertThat(returned.getTotalGamesWon(), equalTo(30));
        assertThat(returned.getGamesLostAverage(), equalTo(Utils.getAverage(20,50)));
        assertThat(returned.getGamesWonAverage(), equalTo(Utils.getAverage(30,50)));
    }

    private List<UserStatistic> aListStatistics() {
        UserStatistic userStatistic1 = new UserStatistic();
        userStatistic1.setUserId(USER_ID_1);
        userStatistic1.setGamesLost(10);
        userStatistic1.setGamesWon(20);
        userStatistic1.setTotalGames(30);

        UserStatistic userStatistic2 = new UserStatistic();
        userStatistic2.setUserId(USER_ID_2);
        userStatistic2.setGamesLost(10);
        userStatistic2.setGamesWon(10);
        userStatistic2.setTotalGames(20);

        List<UserStatistic> statistics = new ArrayList<>();
        statistics.add(userStatistic1);
        statistics.add(userStatistic2);
        return statistics;
    }
}