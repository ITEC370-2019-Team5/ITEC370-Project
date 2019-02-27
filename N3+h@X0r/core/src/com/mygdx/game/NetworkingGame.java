package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class NetworkingGame extends Game implements ApplicationListener {

    Screen stage = new Stage();

    @Override
    public void create () {
        setScreen(stage);
    }

    @Override
    public void render () {
        super.render();
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