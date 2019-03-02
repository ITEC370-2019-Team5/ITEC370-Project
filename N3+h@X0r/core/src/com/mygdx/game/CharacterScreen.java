package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CharacterScreen implements Screen
{
    public static final int RETURN_HOME_BUTTON_WIDTH = 400;
    public static final int RETURN_HOME_BUTTON_HEIGTH = 60;
    public static final int PREV_BUTTON_WIDTH = 195;
    public static final int PREV_BUTTON_HEIGHT = 60;
    public static final int NEXT_CHAR_BUTTON_WIDTH = 195;
    public static final int NEXT_CHAR_BUTTON_HEIGHT = 60;

    public static final int SCREEN_WIDTH = 0;
    public static final int SCREEN_HEIGHT = 0;

    private NetworkingGame game;
    private OrthographicCamera camera;
    private Texture chooseChar, previous, next, returnHome;
    private SpriteBatch batch;
    private int countPush = 0; //Used to only click button once. -AR


    public CharacterScreen(NetworkingGame game){
        this.game = game;
        camera = new OrthographicCamera();
    }

    @Override
    public void render(float deltaTime){
        batch.begin();
        batch.draw(chooseChar, camera.viewportWidth / 2 - 325, camera.viewportHeight / 2 - 325);
        batch.draw(returnHome, camera.viewportWidth / 2 - 200, 130);
        batch.draw(previous, camera.viewportWidth / 2 - 200, 60);
        batch.draw(next, camera.viewportWidth / 2 + 5, 60);

        float x = camera.viewportWidth / 2; //Halfway across x-axis
        float y = camera.viewportHeight / 2; //Halfway across y-axis

        //Return Home button
        if(Gdx.input.getX() >= x - 200 && Gdx.input.getX() <= x + 200
                &&
                Gdx.input.getY() >= (y * 2) - 190 && Gdx.input.getY() <= (y * 2) - 130)
        {
            if(Gdx.input.isTouched() == true)
            {
                countPush++;
                if (countPush == 1)
                {
                    game.changeScreen(0);
                    System.out.println("HOME SCREEN BUTTON PUSHED");
                }
                countPush = 0;
            }
        }
        //Previous Character button
        if(Gdx.input.getX() >= x - 200 && Gdx.input.getX() <= x - 5
                &&
                Gdx.input.getY() >= (y * 2) - 120 && Gdx.input.getY() <= (y * 2) - 60)
        {
            if(Gdx.input.isTouched() == true)
            {
                countPush++;
                if (countPush == 1)
                {
                    System.out.println("PREVIOUS BUTTON PUSHED");
                }
                countPush = 0;
            }
        }
        //Next Character button
        if(Gdx.input.getX() >= x + 5 && Gdx.input.getX() <= x + 200
                &&
                Gdx.input.getY() >= (y * 2) - 120 && Gdx.input.getY() <= (y * 2) - 60)
        {
            if(Gdx.input.isTouched() == true)
            {
                countPush++;
                if (countPush == 1)
                {
                    System.out.println("NEXT CHARACTER BUTTON PUSHED");
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
        returnHome = new Texture("core/assets/Return_Button.png");
        previous = new Texture("core/assets/Previous_Button.png");
        next = new Texture("core/assets/Next_Character_Button.png");
        chooseChar = new Texture("core/assets/Character_Screen.png");
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