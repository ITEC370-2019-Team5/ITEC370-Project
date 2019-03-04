package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainMenu implements Screen
{
    public static final int PLAY_BUTTON_WIDTH = 400;
    public static final int PLAY_BUTTON_HEIGTH = 60;
    public static final int LOAD_BUTTON_WIDTH = 195;
    public static final int LOAD_BUTTON_HEIGHT = 60;
    public static final int NEXT_CHAR_BUTTON_WIDTH = 195;
    public static final int NEXT_CHAR_BUTTON_HEIGHT = 60;

    private NetworkingGame game;
    private OrthographicCamera camera;
    private Texture mainMenu, play, load, next;
    private SpriteBatch batch;
    private int countPush = 0; //Used to only click button once. -AR


    public MainMenu(NetworkingGame game){
        this.game = game;
        camera = new OrthographicCamera();
    }

    @Override
    public void render(float deltaTime){
        batch.begin();
        batch.draw(mainMenu, camera.viewportWidth / 2 - 325, camera.viewportHeight / 2 - 325);
        batch.draw(play, camera.viewportWidth / 2 - 200, 130);
        batch.draw(load, camera.viewportWidth / 2 - 200, 60);
        batch.draw(next, camera.viewportWidth / 2 + 5, 60);

        float x = camera.viewportWidth / 2; //Halfway across x-axis
        float y = camera.viewportHeight / 2; //Halfway across y-axis

        //Play button
        if(Gdx.input.getX() >= x - 200 && Gdx.input.getX() <= x + 200
           &&
           Gdx.input.getY() >= (y * 2) - 190 && Gdx.input.getY() <= (y * 2) - 130)
        {
            if(Gdx.input.isTouched() == true)
            {
                game.changeScreen(1);
            }
        }
        //Load button
        if(Gdx.input.getX() >= x - 200 && Gdx.input.getX() <= x - 5
                &&
                Gdx.input.getY() >= (y * 2) - 120 && Gdx.input.getY() <= (y * 2) - 60)
        {
            if(Gdx.input.isTouched() == true)
            {
                game.changeScreen(4);
            }
        }
        //Next Char button
        if(Gdx.input.getX() >= x + 5 && Gdx.input.getX() <= x + 200
                &&
                Gdx.input.getY() >= (y * 2) - 120 && Gdx.input.getY() <= (y * 2) - 60)
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
        camera.setToOrtho(false, 600, 400);
        batch = new SpriteBatch();
        play = new Texture("core/assets/MainMenuPics/Play_Button.png");
        load = new Texture("core/assets/MainMenuPics/Load_Button.png");
        next = new Texture("core/assets/MainMenuPics/Next_Button.png");
        mainMenu = new Texture("core/assets/MainMenuPics/Title_Screen.png");
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
    }
}