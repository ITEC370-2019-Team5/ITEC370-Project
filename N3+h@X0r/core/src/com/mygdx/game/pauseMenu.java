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

public class pauseMenu implements Screen
{

    private NetworkingGame game;
    private OrthographicCamera camera;
    private Texture pause, resume, save, returnHome;
    private SpriteBatch batch;
    private int countPush = 0; //Used to only click button once. -AR
    private Stage stage;
    private boolean init;

    public pauseMenu(NetworkingGame game){
        this.game = game;

        camera = new OrthographicCamera();
        init = false;
    }

    @Override
    public void render(float deltaTime){
        batch.begin();
        batch.draw(pause, camera.viewportWidth / 2 - 325, camera.viewportHeight / 2 - 325);
        batch.draw(returnHome, camera.viewportWidth / 2 - 200, 625);
        batch.draw(resume, camera.viewportWidth / 2 - 200, 555);
        batch.draw(save, camera.viewportWidth / 2 - 200, 480);

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
        //Area where Save the file
        if(Gdx.input.getX() >= x - 200 && Gdx.input.getX() <= x + 200
                &&
                Gdx.input.getY() >= (y * 2) - 610 && Gdx.input.getY() <= (y * 2) - 555) {
            if (Gdx.input.isTouched() == true) {
                countPush++;
                if (countPush == 1) {
                    System.out.println("Save game");
                }
                countPush = 0;
            }
        }
        //Area where you resume the game
        if(Gdx.input.getX() >= x - 200 && Gdx.input.getX() <= x + 200
                &&
                Gdx.input.getY() >= (y * 2) - 535 && Gdx.input.getY() <= (y * 2) - 480) {
            if (Gdx.input.isTouched() == true) {
                countPush++;
                if (countPush == 1) {
                    game.changeScreen(game.currentScreenNum);
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

            camera.setToOrtho(false, 600, 400);
            returnHome = new Texture("core/assets/LoadGamePics/Return_Button.png");
            pause = new Texture("core/assets/PauseMenuPics/Pause_Screen.png");
            resume = new Texture("core/assets/PauseMenuPics/resume.png");
            save = new Texture("core/assets/PauseMenuPics/Save_file.png");
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