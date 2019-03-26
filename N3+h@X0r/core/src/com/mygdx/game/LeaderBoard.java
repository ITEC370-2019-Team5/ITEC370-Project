package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;


import java.util.ArrayList;

public class LeaderBoard implements Screen {

    private NetworkingGame game; //Instance of the NetworkingGame class.
    private OrthographicCamera camera; //The camera object.
    private SpriteBatch batch; //Sprite batch object.
    private Stage stage; //Stage to be displayed.
    private Texture leaderboard, star; //LeaderBoard's textures.
    private Skin skin; //Skin object used for the TextField.
    private TextField nameText1, nameText2, nameText3, collectableText1, colleactableText2, collectableText3,
            scoreText1, scoreText2, scoreText3; // Textfields that are used as the leaderboard.
    private FileHandle handle; //handles file input.
    private ArrayList<Player> leaderboardList = new ArrayList<Player>(); //ArrayList of player on the leaderboard.
    private String[] lineArray; //String of input data.
    private boolean init; //Stops memory leaks.

    // Established coordintates so to set x or y coordinates for 1st place, 2nd place, etc.
    private int starXCoord = 250;
    private int nameXCoord = 325;
    private int collectXCoord = 475;
    private int scoreXCoord = 625;
    private int thirdPlaceYCoord = 360;
    private int secondPlaceYCoord = 460;
    private int firstPlaceYCoord = 560;

    //Method to init the class.
    public LeaderBoard(NetworkingGame game){
        this.game = game;
        camera = new OrthographicCamera();
        //sortByVirusesFixed(leaderboardList);
        init = false;
    }

    //reads in the file for fake data
    public void readFile(ArrayList<Player> list) {
        handle = Gdx.files.internal("core/assets/files/Fake_Leaderboard_Data.txt");
        String eachLine = handle.readString();
        System.out.println(eachLine);
        lineArray = eachLine.split(",");

        //adding split array values to ArrayList
        String playerName = lineArray[0];
        boolean beatGame = Boolean.parseBoolean(lineArray[1]);
        int collectableCount = Integer.parseInt(lineArray[2]);
        int totalScore = Integer.parseInt(lineArray[3]);

        Player p = new Player(playerName, beatGame, collectableCount, totalScore);
        System.out.println(p.toString()); //debugging purposes
        list.add(p);
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(leaderboard, 130, 60);

        if(lineArray[1].equals("true"))
        {
            batch.draw(star, starXCoord,thirdPlaceYCoord);
        }
        if(lineArray[5].equals("true"))
        {
            batch.draw(star, starXCoord, firstPlaceYCoord);
        }
        if(lineArray[9].equals("true"))
        {
            batch.draw(star, starXCoord,secondPlaceYCoord);
        }

        //batch.draw(star, 55, 0);
        batch.end();
        stage.act(delta);
        stage.draw();


    }

    @Override
    public void show() {
        if(init == false)
        {
            camera.setToOrtho(false, 600, 400);
            batch = new SpriteBatch();
            stage = new Stage();
            leaderboard = new Texture("core/assets/bigleaderboard.png");
            star = new Texture("core/assets/star.png");

            init = true;
            readFile(leaderboardList);            //read the file once it's initialized

            skin = new Skin(Gdx.files.internal("core/assets/clean-crispy/skin/clean-crispy-ui.json"));

            //nameText1
            nameText1 = new TextField(lineArray[0], skin);
            nameText1.setPosition(nameXCoord, 360);
            nameText1.setSize(100, 25);
            stage.addActor(nameText1);

            //nameText2
            nameText2 = new TextField(lineArray[4], skin);
            nameText2.setPosition(nameXCoord, 560);
            nameText2.setSize(100, 25);
            stage.addActor(nameText2);

            //nameText3
            nameText3 = new TextField(lineArray[8], skin);
            nameText3.setPosition(nameXCoord, 460);
            nameText3.setSize(100, 25);
            stage.addActor(nameText3);

            //collectableText1
            collectableText1 = new TextField(lineArray[2] + "/25", skin);
            collectableText1.setPosition(collectXCoord, 360);
            collectableText1.setSize(45, 25);
            stage.addActor(collectableText1);

            //collectableText2
            colleactableText2 = new TextField(lineArray[6] + "/25", skin);
            colleactableText2.setPosition(collectXCoord, 560);
            colleactableText2.setSize(47, 25);
            stage.addActor(colleactableText2);

            //collectableText3
            collectableText3 = new TextField(lineArray[10] + "/25", skin);
            collectableText3.setPosition(collectXCoord, 460);
            collectableText3.setSize(47, 25);
            stage.addActor(collectableText3);

            //scoreText1
            scoreText1 = new TextField(lineArray[3], skin);
            scoreText1.setPosition(scoreXCoord, 360);
            scoreText1.setSize(50, 25);
            stage.addActor(scoreText1);

            //scoreText2
            scoreText2 = new TextField(lineArray[7], skin);
            scoreText2.setPosition(scoreXCoord, 560);
            scoreText2.setSize(50, 25);
            stage.addActor(scoreText2);

            //scoreText3
            scoreText3 = new TextField(lineArray[11], skin);
            scoreText3.setPosition(scoreXCoord, 460);
            scoreText3.setSize(50, 25);
            stage.addActor(scoreText3);

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