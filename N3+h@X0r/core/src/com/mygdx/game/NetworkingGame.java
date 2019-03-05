package com.mygdx.game;

import com.badlogic.gdx.*;

public class NetworkingGame extends Game implements ApplicationListener {

    private int screen = 0;
    MainMenu mainMenu = new MainMenu(this);
    Playground playground = new Playground(this);
    CharacterScreen charScreen = new CharacterScreen(this);
    LeaderBoard leaderboard = new LeaderBoard(this);
    LoadGameScreen load = new LoadGameScreen(this);

    int currentScreenNum = 0;//Keeps Track of screen number for leaderboard.

    @Override
    public void create () {
        setScreen(mainMenu);
    }

    public void changeScreen(int setting)
    {
        screen = setting;
    }

    //Look for button press

    @Override
    public void render () {
        super.render();
        if(screen == 0) //MainMenu
        {
            currentScreenNum = 0;
            setScreen(mainMenu);
            if(Gdx.input.isKeyPressed(Input.Keys.L))
            {
                changeScreen(3); //shows leaderboard
            }
        }
        else if(screen == 1) //PlayGround
        {
            currentScreenNum = 1;
            setScreen(playground);
            if(Gdx.input.isKeyPressed(Input.Keys.L))
            {
                changeScreen(3); //shows leaderboard
            }
        }
        else if(screen == 2) //Character Selection
        {
            currentScreenNum = 2;
            setScreen(charScreen);
            if(Gdx.input.isKeyPressed(Input.Keys.L))
            {
                changeScreen(3); //shows leaderboard
            }
        }
        else if(screen == 3) //Leaderboard
        {
            setScreen(leaderboard);
            if(Gdx.input.isKeyPressed(Input.Keys.L))
            {
                changeScreen(currentScreenNum); //shows leaderboard
            }
        }
        else if(screen == 4) //Loading screen
        {
            setScreen(load);
        }
        else
        {
            System.out.println("Error!");
        }
    }

    @Override
    public void dispose () {
        super.dispose();
    }

    @Override
    public void resize ( int width, int height){
        super.resize(width, height);
    }

    @Override
    public void pause () {
        super.pause();
    }

    @Override
    public void resume () {
        super.resume();
    }
}