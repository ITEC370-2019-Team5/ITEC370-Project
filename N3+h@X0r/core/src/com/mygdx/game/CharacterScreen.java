/**
 * CharacterScreen.java
 *
 * @author Alexander Rhine
 * @since 3-2-2019
 */

package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CharacterScreen implements Screen
{
    private NetworkingGame game;
    private OrthographicCamera camera;
    private Texture chooseChar, randomChar, returnHome, coverLoad, coverNext, myChar;
    private SpriteBatch batch;
    private boolean init;
    private int charSelect = 0;

    public CharacterScreen(NetworkingGame game){
        this.game = game;
        camera = new OrthographicCamera();
        init = false;
    }

    @Override
    public void render(float deltaTime){
        batch.begin();
        batch.draw(chooseChar, camera.viewportWidth / 2 - 325, camera.viewportHeight / 2 - 325);
        batch.draw(returnHome, camera.viewportWidth / 2 - 200, 625);
        batch.draw(randomChar, camera.viewportWidth / 2 - 200, 555);
        batch.draw(coverLoad, camera.viewportWidth / 2 - 200, 60);
        batch.draw(coverNext, camera.viewportWidth / 2 + 5, 60);
        batch.draw(myChar, camera.viewportHeight / 2 - 120, 210);

        float x = camera.viewportWidth / 2; //Halfway across x-axis
        float y = camera.viewportHeight / 2; //Halfway across y-axis

        //Area where Return button is clickable.
        if(Gdx.input.getX() >= x - 200 && Gdx.input.getX() <= x + 200
                &&
                Gdx.input.getY() >= (y * 2) - 685 && Gdx.input.getY() <= (y * 2) - 625)
        {
            if(Gdx.input.isTouched() == true)
            {
                game.changeScreen(0); //Returns to home screen.
            }
        }
        //Area where Random Character button is clickable.
        if(Gdx.input.getX() >= x - 200 && Gdx.input.getX() <= x + 200
                &&
                Gdx.input.getY() >= (y * 2) - 615 && Gdx.input.getY() <= (y * 2) - 555) {
            if (Gdx.input.isTouched() == true) {
                game.changeChar();
                charSelect = game.getChar();
            }
        }

        batch.end();
    }

    @Override
    public void show() {
        if(init == false) {
            init = true;
            camera.setToOrtho(false, 600, 400);
            batch = new SpriteBatch();
            returnHome = new Texture("core/assets/CharSelectPics/Return_Button.png");
            randomChar = new Texture("core/assets/CharSelectPics/Random_Button.png");
            chooseChar = new Texture("core/assets/CharSelectPics/Character_Screen.png");
            coverLoad = new Texture("core/assets/CharSelectPics/Black_Image.png");
            coverNext = new Texture("core/assets/CharSelectPics/Black_Image.png");
        }
        myChar = new Texture("core/assets/CharSelectPics/C" + (charSelect + 1) + "_Display.png");
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