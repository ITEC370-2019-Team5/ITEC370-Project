package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainMenu implements Screen
{
    private NetworkingGame game; //Instance of the NetworkingGame class.
    private OrthographicCamera camera; //The camera object.
    private Texture mainMenu, play, load, next; //Main menu Textures.
    private SpriteBatch batch; //MainMenu's sprite batch.
    private int countPush = 0; //Used to only click button once. -AR
    private boolean init; //Used to stop memory leaks.

    //Method to init the class.
    public MainMenu(NetworkingGame game){
        this.game = game;
        camera = new OrthographicCamera();
        init = false;
    }

    @Override
    public void render(float deltaTime){

        float x = camera.viewportWidth; //Halfway across x-axis
        float y = camera.viewportHeight; //Halfway across y-axis

        batch.begin();

        //Draw everything
        batch.draw(mainMenu, x/2 - 325, y/2 - 325); //Center the main image (According to a 900x900 screen)
        batch.draw(play, x/2 - 200, 130);
        batch.draw(load, x/2 - 200, 60);
        batch.draw(next, x/2 + 5, 60);

        //Play button
        if(Gdx.input.getX() >= x/2 - 200 && Gdx.input.getX() <= x/2 + 200
           &&
           Gdx.input.getY() >= y - 190 && Gdx.input.getY() <= y - 130)
        {
            if(Gdx.input.isTouched() == true)
            {
                game.changeScreen(1);
            }
        }
        //Load button
        if(Gdx.input.getX() >= x/2 - 200 && Gdx.input.getX() <= x/2 - 5
                &&
                Gdx.input.getY() >= y - 120 && Gdx.input.getY() <= y - 60)
        {
            if(Gdx.input.isTouched() == true)
            {
                game.changeScreen(4);
            }
        }
        //Next Char button
        if(Gdx.input.getX() >= x/2 + 5 && Gdx.input.getX() <= x/2 + 200
                &&
                Gdx.input.getY() >= y - 120 && Gdx.input.getY() <= y - 60)
        {
            if(Gdx.input.isTouched() == true)
            {
                countPush++;
                if (countPush == 1)
                {
                    game.changeScreen(2);
                }
                countPush = 0;
            }
        }
        batch.end();
    }

    @Override
    public void show() {
        if(init == false) {
            batch = new SpriteBatch();
            init = true;

            //Set Textures
            camera.setToOrtho(false, 600, 400);
            play = new Texture("core/assets/MainMenuPics/Play_Button.png");
            load = new Texture("core/assets/MainMenuPics/Load_Button.png");
            next = new Texture("core/assets/MainMenuPics/Next_Button.png");
            mainMenu = new Texture("core/assets/MainMenuPics/Title_Screen.png");
        }
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
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
        //batch.dispose();
    }
}