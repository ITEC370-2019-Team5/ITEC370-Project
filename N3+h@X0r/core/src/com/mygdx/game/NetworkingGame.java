package com.mygdx.game;

import com.badlogic.gdx.*;

public class NetworkingGame extends Game implements ApplicationListener {

    private int screen = 0;
    MainMenu mainMenu = new MainMenu(this);
    CharacterScreen charScreen = new CharacterScreen(this);
    LeaderBoard leaderboard = new LeaderBoard(this);
    Screen stage = new Stage();

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
        if(screen == 0)
        {
            setScreen(mainMenu);
            if(Gdx.input.isKeyPressed(Input.Keys.L))
            {
                changeScreen(3); //shows leaderboard
            }
        }
        else if(screen == 1)
        {
            setScreen(stage);
        }
        else if(screen == 2)
        {
            setScreen(charScreen);
        }
        else if(screen == 3)
        {
            setScreen(leaderboard);
            if(Gdx.input.isKeyPressed(Input.Keys.L))
            {
                changeScreen(0); //shows leaderboard
            }
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