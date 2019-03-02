package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


import java.util.ArrayList;

public class LeaderBoard implements Screen {
    private ArrayList<Player> leaderboardList = new ArrayList<Player>();
    private Texture leaderboard, star;
    private SpriteBatch batch;

    //Empty Constructor
    public LeaderBoard() {
        //get the pictures
        //getPictures();

        //read the file once it's initialized
        readFile(leaderboardList);
    }

    //reads in the file for fake data
    public void readFile(ArrayList<Player> list) {
        FileHandle handle = Gdx.files.internal("core/assets/files/Fake_Leaderboard_Data.txt");
        String eachLine = handle.readString();
        String lineArray[] = eachLine.split(",");

        //adding split array values to ArrayList
        String playerName = lineArray[0];
        boolean beatGame = Boolean.parseBoolean(lineArray[1]);
        int collectableCount = Integer.parseInt(lineArray[2]);
        int virusesFixed = Integer.parseInt(lineArray[3]);

        Player p = new Player(playerName, beatGame, collectableCount, virusesFixed);
        list.add(p);
    }

    @Override
    public void render(float delta) {
        batch.begin();

        batch.end();
    }

    @Override
    public void show() {
        leaderboard = new Texture("core/assets/leaderboardSEfinal.pdf");
        star = new Texture("core/assets/star.png");
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose () {
    }
}
