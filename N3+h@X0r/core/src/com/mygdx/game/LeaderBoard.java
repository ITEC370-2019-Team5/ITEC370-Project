package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.io.*;

public class LeaderBoard {
    //private Texture leaderboard, star;
    private FileHandle handle;          //for reading in the file of fake data
    private ArrayList<Player> leaderboardList = new ArrayList<Player>();

    //Empty Constructor
    public LeaderBoard() {
        //get the pictures
        //getPictures();

        //read the file
        readFile(leaderboardList);
    }
    /*
    public void getPictures() {
        leaderboard = new Texture("core/assets/leaderboardSEfinal.pdf");
        star = new Texture("core/assets/star.png");
    }
    */

    //reads in the file for fake data
    public void readFile(ArrayList<Player> list) {
        handle = Gdx.files.internal("core/assets/files/Fake_Leaderboard_Data.txt");
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
}
