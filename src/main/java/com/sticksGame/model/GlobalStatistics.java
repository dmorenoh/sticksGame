package com.sticksGame.model;

import java.math.BigDecimal;

/**
 * Created by dmorenoh on 8/1/17.
 */
public class GlobalStatistics {
    private int totalGamesWon;
    private int totalGamesLost;
    private int totalGames;
    private BigDecimal gamesWonAverage = new BigDecimal("0.00");
    private BigDecimal gamesLostAverage = new BigDecimal("0.00");


    public int getTotalGamesWon() {
        return totalGamesWon;
    }

    public void setTotalGamesWon(int totalGamesWon) {
        this.totalGamesWon = totalGamesWon;
    }

    public int getTotalGamesLost() {
        return totalGamesLost;
    }

    public void setTotalGamesLost(int totalGamesLost) {
        this.totalGamesLost = totalGamesLost;
    }

    public int getTotalGames() {
        return totalGames;
    }

    public void setTotalGames(int totalGames) {
        this.totalGames = totalGames;
    }

    public BigDecimal getGamesWonAverage() {
        return gamesWonAverage;
    }

    public void setGamesWonAverage(BigDecimal gamesWonAverage) {
        this.gamesWonAverage = gamesWonAverage;
    }

    public BigDecimal getGamesLostAverage() {
        return gamesLostAverage;
    }

    public void setGamesLostAverage(BigDecimal gamesLostAverage) {
        this.gamesLostAverage = gamesLostAverage;
    }
}
