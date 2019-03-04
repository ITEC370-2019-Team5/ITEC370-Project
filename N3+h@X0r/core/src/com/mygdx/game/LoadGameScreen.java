/**
 * CharacterScreen.java
 *
 * @author Michael Donaldson
 * @since 3-4-2019
 */

package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LoadGameScreen implements Screen
{
    public static final int RETURN_HOME_BUTTON_WIDTH = 400;
    public static final int RETURN_HOME_BUTTON_HEIGTH = 60;
    public static final int NEXT_CHAR_BUTTON_WIDTH = 400;
    public static final int NEXT_CHAR_BUTTON_HEIGHT = 60;


    private NetworkingGame game;
    private OrthographicCamera camera;
    private Texture loadChar, chooseFile, returnHome, coverLoad, coverNext;
    private SpriteBatch batch;
    private int countPush = 0; //Used to only click button once. -AR

    public LoadGameScreen(NetworkingGame game){
        this.game = game;
        camera = new OrthographicCamera();
    }

    @Override
    public void render(float deltaTime){
        batch.begin();
        batch.draw(loadChar, camera.viewportWidth / 2 - 325, camera.viewportHeight / 2 - 325);
        batch.draw(returnHome, camera.viewportWidth / 2 - 200, 625);
        batch.draw(chooseFile, camera.viewportWidth / 2 - 200, 555);
        batch.draw(coverLoad, camera.viewportWidth / 2 - 200, 60);
        batch.draw(coverNext, camera.viewportWidth / 2 + 5, 60);

        float x = camera.viewportWidth / 2; //Halfway across x-axis
        float y = camera.viewportHeight / 2; //Halfway across y-axis

        //Area where Return button is clickable.
        if(Gdx.input.getX() >= x - 200 && Gdx.input.getX() <= x + 200
                &&
                Gdx.input.getY() >= (y * 2) - 685 && Gdx.input.getY() <= (y * 2) - 625)
        {
            if(Gdx.input.isTouched() == true)
            {
                countPush++;
                if (countPush == 1)
                {
                    game.changeScreen(0); //Returns to home screen.
                }
                countPush = 0;
            }
        }
        //Area where Previous Character button is clickable.
        if(Gdx.input.getX() >= x - 200 && Gdx.input.getX() <= x + 200
                &&
                Gdx.input.getY() >= (y * 2) - 615 && Gdx.input.getY() <= (y * 2) - 555) {
            if (Gdx.input.isTouched() == true) {
                countPush++;
                if (countPush == 1) {
                    System.out.println("LOAD BUTTON PUSHED");
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
        returnHome = new Texture("core/assets/LoadGamePics/Return_Button.png");
        loadChar = new Texture("core/assets/CharSelectPics/Character_Screen.png");
        chooseFile = new Texture("core/assets/LoadGamePics/Load_File.png");
        coverLoad = new Texture("core/assets/LoadGamePics/Black_Image.png");
        coverNext = new Texture("core/assets/LoadGamePics/Black_Image.png");
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