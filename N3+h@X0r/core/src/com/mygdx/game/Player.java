package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player extends Sprite {
    protected String playerName = "";
    public static final int COLLECTABLES = 0;
    private boolean beatGame = false;
    private int collectableCount = 0;
    private int totalScore = 0;

    //Empty Constructor
    public Player() {
        super();
    }

    //Full Constructor
    public Player(String playerName, boolean beatGame, int collectableCount, int totalScore) {
        super();
        this.playerName = playerName;
        this.beatGame = beatGame;
        this.collectableCount = collectableCount;
        this.totalScore = totalScore;
    }

    public String toString() {
        return beatGame+"\t"+playerName+"\t"+
                        collectableCount+"\t"+totalScore+"\n";
    }
    //getters and setters
    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public boolean getBeatGame() {
        return beatGame;
    }

    public void setBeatGame(boolean beatGame) {
        this.beatGame = beatGame;
    }

    public int getCollectableCount() {
        return collectableCount;
    }

    public void setCollectableCount(int collectableCount) {
        this.collectableCount = collectableCount;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }
}
