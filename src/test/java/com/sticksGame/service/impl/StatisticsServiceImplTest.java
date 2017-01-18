package com.sticksGame.service.impl;

import com.sticksGame.mapper.UserStatisticMapper;
import com.sticksGame.model.UserStatistic;
import com.sticksGame.model.request.StatisticRequest;
import com.sticksGame.repository.StatisticsRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by dmorenoh on 10/1/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class StatisticsServiceImplTest {
    public static final String USER_ID = "userId";
    public static final StatisticRequest STATISTIC_REQUEST = new StatisticRequest();
    public static final UserStatistic USER_STATISTIC = new UserStatistic();
    @InjectMocks
    private StatisticsServiceImpl testSubject;

    @Mock
    private StatisticsRepository statisticsRepository;

    @Mock
    private UserStatisticMapper userStatisticMapper;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void getUserStatistics_emptyUserId_throwsException(){
        expectedException.expect(IllegalArgumentException.class);
        testSubject.getUserStatistics("");
    }

    @Test
    public void getUserStatistics_userIdProvided_returnUserStatisticFound(){
        UserStatistic userStatistic = new UserStatistic();
        when(statisticsRepository.findByUserId(USER_ID)).thenReturn(userStatistic);
        UserStatistic returned = testSubject.getUserStatistics(USER_ID);
        assertThat(returned, equalTo(returned));
    }

    @Test
    public void saveUserStatistics_notUserIdProvided_throwsException(){
        expectedException.expect(IllegalArgumentException.class);
        testSubject.saveUserStatistics("", STATISTIC_REQUEST);
    }

    @Test
    public void saveUserStatistics_userIdProvided_saveUserStatistic(){
        when(userStatisticMapper.map(anyString(),any(StatisticRequest.class))).thenReturn(USER_STATISTIC);
        when(statisticsRepository.save(any(UserStatistic.class))).thenReturn(USER_STATISTIC);
        UserStatistic returned = testSubject.saveUserStatistics(USER_ID, STATISTIC_REQUEST);
        assertThat(returned,equalTo(USER_STATISTIC));
        verify(userStatisticMapper).map(USER_ID,STATISTIC_REQUEST);
        verify(statisticsRepository).save(USER_STATISTIC);
    }

}