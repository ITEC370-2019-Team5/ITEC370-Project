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

    private NetworkingGame game;
    private OrthographicCamera camera;
    private Texture loadChar, chooseFile, returnHome, coverLoad, coverNext, setName;
    private SpriteBatch batch;
    private int countPush = 0; //Used to only click button once. -AR
    private Stage stage;
    private Skin skin;
    private TextField textField;
    private boolean init;

    public LoadGameScreen(NetworkingGame game){
        this.game = game;

        camera = new OrthographicCamera();
        init = false;
    }

    @Override
    public void render(float deltaTime){
        batch.begin();
        batch.draw(loadChar, camera.viewportWidth / 2 - 325, camera.viewportHeight / 2 - 325);
        batch.draw(returnHome, camera.viewportWidth / 2 - 200, 625);
        batch.draw(chooseFile, camera.viewportWidth / 2 - 200, 555);
        batch.draw(setName, camera.viewportWidth / 2 - 200, 485);
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
        //Area where Load file is.
        if(Gdx.input.getX() >= x - 200 && Gdx.input.getX() <= x + 200
                &&
                Gdx.input.getY() >= (y * 2) - 615 && Gdx.input.getY() <= (y * 2) - 555) {
            if (Gdx.input.isTouched() == true) {
                countPush++;
                if (countPush == 1) {
                    System.out.println(textField.getText());
                }
                countPush = 0;
            }
        }
        //Area where Load file is.
        if(Gdx.input.getX() >= x - 200 && Gdx.input.getX() <= x + 200
                &&
                Gdx.input.getY() >= (y * 2) - 615 && Gdx.input.getY() <= (y * 2) - 485) {
            if (Gdx.input.isTouched() == true) {
                countPush++;
                if (countPush == 1) {
                    game.setGameName(textField.getText());
                    textField.clear();
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
            setName = new Texture("core/assets/LoadGamePics/SaveGameName.png");
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