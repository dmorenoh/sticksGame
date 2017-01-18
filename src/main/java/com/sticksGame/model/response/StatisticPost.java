package com.sticksGame.model.response;

/**
 * Created by dmorenoh on 8/1/17.
 */
public class StatisticPost {
    private String message;

    private final String statisticsDetailsUrl;

    public StatisticPost(String url){
        this.statisticsDetailsUrl = url;
    }

    public String getStatisticsDetailsUrl() {
        return statisticsDetailsUrl;
    }
}
