package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player extends Sprite {
    protected String playerName = "";
    public static final int COLLECTABLES = 0;
    private boolean beatGame = false;
    private int collectableCount = 0;
    private int virusesFixed = 0;

    //Empty Constructor
    public Player() {
        super();
    }

    //Full Constructor
    public Player(String playerName, boolean beatGame, int collectableCount, int virusesFixed) {
        super();
        this.playerName = playerName;
        this.beatGame = beatGame;
        this.collectableCount = collectableCount;
        this.virusesFixed = virusesFixed;
    }
}
