/**
 * CharacterScreen.java
 *
 * @author Michael Donaldson
 * @since 3-4-2019
 */

package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class LoadGameScreen implements Screen
{
    private NetworkingGame game; //Instance of the NetworkingGame class.
    private OrthographicCamera camera; //The camera object.
    private Texture loadChar, chooseFile, returnHome, coverLoad, coverNext; //LoadGame's textures.
    private Skin skin; //Skin object used for the TextField.
    private SpriteBatch batch; //Sprite batch object.
    private Stage stage; //Stage being displayed.
    private TextField textField; //The TextField object.
    private int countPush = 0; //Used to only click button once. -AR
    private boolean init; //Stops memory leaks.

    //Method to init the class.
    public LoadGameScreen(NetworkingGame game){
        this.game = game;

        camera = new OrthographicCamera();
        init = false;
    }

    @Override
    public void render(float deltaTime){

        float x = camera.viewportWidth; //Halfway across x-axis
        float y = camera.viewportHeight; //Halfway across y-axis

        batch.begin();

        batch.draw(loadChar, x/2 - 325, y/2 - 325); //Center the main image (According to a 900x900 screen)
        batch.draw(returnHome, x/2 - 200, y/2 + 180);
        batch.draw(chooseFile, x/2 - 200, y/2 + 110);
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
                Gdx.input.getY() >= (y/2 - 180) - 60 && Gdx.input.getY() <= y/2 - 110) {
            if (Gdx.input.isTouched() == true) {
                countPush++;
                if (countPush == 1) {
                    System.out.println(textField.getText());
                    //HERE IS WHERE WE GET THE FILE TO INPUT.
                }
                countPush = 0;
            }
        }
        batch.end();
        stage.act(deltaTime);
        stage.draw();
    }

    @Override
    public void show() {
        if(init == false) {
            batch = new SpriteBatch();
            stage = new Stage();
            init = true;

            skin = new Skin(Gdx.files.internal("core/assets/clean-crispy/skin/clean-crispy-ui.json"));

            textField = new TextField("", skin);
            textField.setPosition(300, 250);
            textField.setSize(300, 40);

            stage.addActor(textField);
            Gdx.input.setInputProcessor(stage);

            camera.setToOrtho(false, 600, 400);
            returnHome = new Texture("core/assets/LoadGamePics/Return_Button.png");
            loadChar = new Texture("core/assets/LoadGamePics/Load_Screen.png");
            chooseFile = new Texture("core/assets/LoadGamePics/Load_File.png");
            coverLoad = new Texture("core/assets/LoadGamePics/Black_Image.png");
            coverNext = new Texture("core/assets/LoadGamePics/Black_Image.png");
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