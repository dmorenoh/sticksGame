package com.sticksGame.utils;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

/**
 * Created by dmorenoh on 9/1/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class UtilsTest {

    public static final int COUNT = 4;
    public static final int TOTAL = 10;
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void getAverage_totalEqualToZero() {
        BigDecimal returned = Utils.getAverage(0, 0);
        assertThat(returned, equalTo(new BigDecimal("0.00")));
    }

    @Test
    public void getAverage_negativeParameters_throwsException() {
        expectedException.expect(IllegalArgumentException.class);
        BigDecimal returned = Utils.getAverage(-4, -10);

    }

    @Test
    public void getAverage_totalLowerThanCount_throwsException() {
        expectedException.expect(IllegalArgumentException.class);
        BigDecimal returned = Utils.getAverage(40, 2);

    }

    @Test
    public void getAverage_validParameters_returnAverage() {
        BigDecimal expectedAvg = new BigDecimal((double) COUNT / (double) TOTAL);
        BigDecimal returned = Utils.getAverage(COUNT, TOTAL);
        assertThat(returned, equalTo(expectedAvg.setScale(2, RoundingMode.CEILING)));
    }
}