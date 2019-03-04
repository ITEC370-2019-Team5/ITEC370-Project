package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;


import java.util.ArrayList;

public class LeaderBoard implements Screen {
    private ArrayList<Player> leaderboardList = new ArrayList<Player>();
    private Texture leaderboard, star;
    private NetworkingGame game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private boolean init;
    private FileHandle handle;
    private TextField nameText1, nameText2, nameText3, collectableText1, colleactableText2, collectableText3,
            virusText1, virusText2, virusText3;
    private String[] lineArray;
    private Skin skin;
    private Stage stage;

    //Empty Constructor
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
        int virusesFixed = Integer.parseInt(lineArray[3]);

        Player p = new Player(playerName, beatGame, collectableCount, virusesFixed);
        System.out.println(p.toString()); //debugging purposes
        list.add(p);
    }

    public void sortByVirusesFixed(ArrayList<Player> list) {
        int startVal = 0;

        for(int i=0;i<list.size();i++) {
            int virusScore = list.get(i).getVirusesFixed();
            if(virusScore >= startVal) {
                System.out.println(list.get(i).toString());
                startVal = virusScore;
            }
        }
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(leaderboard, 5, 150);

        if(lineArray[1].equals("true"))
        {
            batch.draw(star, 100,0);
        }
        if(lineArray[5].equals("true"))
        {
            batch.draw(star, 100,0);
        }
        if(lineArray[9].equals("true"))
        {
            batch.draw(star, 100,100);
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
            leaderboard = new Texture("core/assets/finalleaderboard.png");
            star = new Texture("core/assets/star.png");

            init = true;
            readFile(leaderboardList);            //read the file once it's initialized

            skin = new Skin(Gdx.files.internal("core/assets/clean-crispy/skin/clean-crispy-ui.json"));

            //nameText1
            nameText1 = new TextField(lineArray[0], skin);
            nameText1.setPosition(300, 150);
            nameText1.setSize(110, 20);
            stage.addActor(nameText1);

            //nameText2
            nameText2 = new TextField(lineArray[5], skin);
            nameText2.setPosition(300, 250);
            nameText2.setSize(300, 40);
            stage.addActor(nameText2);

            //nameText3
            nameText3 = new TextField(lineArray[8], skin);
            nameText3.setPosition(300, 250);
            nameText3.setSize(300, 40);
            stage.addActor(nameText3);

            //collectableText1
            collectableText1 = new TextField(lineArray[2], skin);
            collectableText1.setPosition(300, 250);
            collectableText1.setSize(300, 40);
            stage.addActor(collectableText1);

            //collectableText2
            colleactableText2 = new TextField(lineArray[6], skin);
            colleactableText2.setPosition(300, 250);
            colleactableText2.setSize(300, 40);
            stage.addActor(colleactableText2);

            //collectableText3
            collectableText3 = new TextField(lineArray[10], skin);
            collectableText3.setPosition(300, 250);
            collectableText3.setSize(300, 40);
            stage.addActor(collectableText3);

            //virusText1
            virusText1 = new TextField(lineArray[3], skin);
            virusText1.setPosition(300, 250);
            virusText1.setSize(300, 40);
            stage.addActor(virusText1);

            //virusText2
            virusText2 = new TextField(lineArray[7], skin);
            virusText2.setPosition(300, 250);
            virusText2.setSize(300, 40);
            stage.addActor(virusText2);

            //virusText3
            virusText3 = new TextField(lineArray[11], skin);
            virusText3.setPosition(300, 250);
            virusText3.setSize(300, 40);
            stage.addActor(virusText3);

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
