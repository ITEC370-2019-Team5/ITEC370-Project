/**
 * CharacterScreen.java
 *
 * @author Alexander Rhine
 * @since 3-2-2019
 */

package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CharacterScreen implements Screen
{
    private NetworkingGame game; //Instance of the NetworkingGame class.
    private OrthographicCamera camera; //The camera object.
    private Texture chooseChar, randomChar, returnHome, coverLoad, coverNext; //CharScreen's Textures.
    private SpriteBatch batch; //Sprite batch object.
    private int countPush = 0; //Used to only click button once. -AR
    private boolean init; //Stops memory leaks.

    //Method to init the class.
    public CharacterScreen(NetworkingGame game){
        this.game = game;
        camera = new OrthographicCamera();
        init = false;
    }

    @Override
    public void render(float deltaTime){

        float x = camera.viewportWidth; //Halfway across x-axis
        float y = camera.viewportHeight; //Halfway across y-axis

        batch.begin();
        batch.draw(chooseChar, x/2 - 325, y/2 - 325); //Center the main image (According to a 900x900 screen)
        batch.draw(returnHome, x/2 - 200, y/2 + 180);
        batch.draw(randomChar, x/2 - 200, y/2 + 110);
        batch.draw(coverLoad, x/2 - 200, 60);
        batch.draw(coverNext, x/2 + 5, 60);

        //Area where Return button is clickable.
        if(Gdx.input.getX() >= x/2 - 200 && Gdx.input.getX() <= x/2 + 200
                &&
                Gdx.input.getY() >= (y/2 - 180) - 60 && Gdx.input.getY() <= y/2 - 180)
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
        if(Gdx.input.getX() >= x/2 - 200 && Gdx.input.getX() <= x/2 + 200
                &&
                Gdx.input.getY() >= (y/2 - 110) - 60 && Gdx.input.getY() <= y/2 - 110) {
            if (Gdx.input.isTouched() == true) {
                countPush++;
                if (countPush == 1) {
                    System.out.println("RANDOM BUTTON PUSHED");
                }
                countPush = 0;
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