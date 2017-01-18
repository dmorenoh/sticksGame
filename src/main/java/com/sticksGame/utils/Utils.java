package com.sticksGame.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by dmorenoh on 9/1/17.
 */
public class Utils {
    private Utils() {

    }

    public static BigDecimal getAverage(int count, int total) {
        if (total == 0) {
            return new BigDecimal("0.00");
        }

        if (total < 0 || count < 0) {
            throw new IllegalArgumentException("not valid negative numbers when calculating average");
        }

        if (total < count ) {
            throw new IllegalArgumentException("total can't be lower than count");
        }
        BigDecimal average = new BigDecimal((double) count / (double) total);
        return average.setScale(2, RoundingMode.CEILING);
    }
}
