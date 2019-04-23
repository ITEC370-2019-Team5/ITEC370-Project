package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import java.util.ArrayList;

public class Playground implements Screen , ApplicationListener {
	private NetworkingGame game;

	private boolean showOnce = false;
	private boolean rendOnce = false;
	private double id = Math.random();
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera;
	private TiledMapTileLayer platformingLayer;
	private int[] decorationLayers;
	private Player player;
	private NPC boss;
	private Texture standing, itemTexture;
	private Item testItem, hintItem, leftDoor, topDoor, bottomDoor1, bottomDoor2;
	private ArrayList<Item> itemList = new ArrayList<Item>();
	private boolean pressingEnter;
	private Texture prevTexture;
	private Inventory inventory = new Inventory(game);

	private float x = 8;
	private float y = 1;

	private float itemX = 4;
	private float itemY = 1;

	private ArrayList<Integer> itemListX = new ArrayList<Integer>();
	private ArrayList<Integer> itemListY = new ArrayList<Integer>();

	private int upTime = 0;
	private int downTime = 0;
	private int leftTime = 0;
	private int rightTime = 0;
	private int charSelect;

	public Playground(NetworkingGame game){
		this.game = game;
	}


	@Override
	public void show() {

		if(showOnce == false)
		{
			prevTexture = new Texture("core/assets/CharSelectPics/C" + (charSelect + 1) + "_WalkDown2.png");
			map = new TmxMapLoader().load("core/assets/OfficeRoom.tmx");
			itemListX.add(12);
			itemListY.add(3);
			itemListX.add(7);
			itemListY.add(3);
		}

		testItem = new Item(new Sprite(new Texture("core/assets/test_item.png")), 'I',
				"Test Item", "core/assets/test_item.png", 1, platformingLayer, itemListX.get(0), itemListY.get(0));
		hintItem = new Item(new Sprite(new Texture("core/assets/sticky_note.png")), 'H',
				"Hint 1", "core/assets/sticky_note.png", 1, platformingLayer, itemListX.get(1), itemListY.get(1),
				"We're no strangers to love\n" +
						"You know the rules and so do I\n" +
						"A full commitment's what I'm thinking of\n" +
						"You wouldn't get this from any other guy\n" +
						"I just wanna tell you how I'm feeling\n" +
						"Gotta make you understand\n" +
						"...");

		//4 doors on the first level of the map
		leftDoor = new Item('D', platformingLayer, 0.0f, 2.0f, 1);
		topDoor = new Item('D', platformingLayer, 3.0f, 12.0f, 2);

		itemList.add(testItem);
		itemList.add(hintItem);
		itemList.add(leftDoor);
		itemList.add(topDoor);

		itemListX.clear();
		itemListY.clear();

		renderer = new OrthogonalTiledMapRenderer(map);
		MapLayers layers = map.getLayers();
		platformingLayer = (TiledMapTileLayer) layers.get("Platforming");
		decorationLayers = new int[]{
				layers.getIndex("Background")
		};

		if(showOnce == false)
		{
			x = x * platformingLayer.getTileWidth();
			y = y * platformingLayer.getTileHeight();

			showOnce = true;
		}
		player = new Player(new Sprite(prevTexture), platformingLayer, charSelect);
		player.setBounds(0, 0, 16, 16);
		player.setPosition(x, y);

		String[] diag = {"Oh good, you finally made it!\n" +
				"Now before we get started, let's see\n" +
				"if you know the basics",
							"Which of the following ip's are\n" +
							"within the private IP range?\n" +
									"A: 192.167.4.3\n" +
									"B: 172.16.94.217\n" +
									"C: 154.3.43.196",
						"Well done, now..."};

		char[] types = {'d', 'q'};
		char[] answers = {'b'};
		boss = new NPC(new Sprite(new Texture("core/assets/Boss_StandDown.png")), platformingLayer, diag, types, answers);
		boss.setBounds(0,0,16,16);
		boss.setPosition(3 * 16, 2 * 16);

		for(int i = 0;i < itemList.size(); i++) {
			itemList.get(i).setBounds(itemList.get(i).updateCoordX(), itemList.get(i).updateCoordY(), 16, 16);
		}

		//Setting the items
		for(int i = 0; i < itemList.size(); i++)
		{
			itemX = itemList.get(i).getX();
			itemY = itemList.get(i).getY();
			itemList.get(i).setPosition(itemX, itemY);
		}

		itemX = itemList.get(0).getX();
		itemY = itemList.get(0).getY();

		camera = new OrthographicCamera();
	}


	@Override
	public void render (float delta) {
		pressingEnter = Gdx.input.isKeyPressed(Input.Keys.ENTER);

		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.position.x = 120;
		camera.position.y = 120;
		camera.zoom = 3/5f;
		camera.setToOrtho(false, 600, 400);

		map = new TmxMapLoader().load("core/assets/OfficeRoom.tmx");
		renderer = new OrthogonalTiledMapRenderer(map);
		MapLayers layers = map.getLayers();
		platformingLayer = (TiledMapTileLayer) layers.get("Platforming");
		decorationLayers = new int[]{
				layers.getIndex("Background")
		};

		if(rendOnce == false)
		{
			player.setBounds(player.getX(),player.getY(),16,16);
			rendOnce = true;
		}

		//setting the pixel width and height of each item
		for(int i = 0;i < itemList.size(); i++) {
			char theType = itemList.get(i).getType();
			if(theType == 'H') {
				itemList.get(i).setBounds(itemList.get(i).getX(), itemList.get(i).getY(), 6, 6);
			}
			else if(theType == 'I') {
				itemList.get(i).setBounds(itemList.get(i).getX(), itemList.get(i).getY(), 16, 16);
			}
		}

		//debugging purposes
		//System.out.println("X : " + player.getX());
		//System.out.println(" Y : " + player.getY());

		//giant if statement when you press ENTER at all throughout the playground
		if(pressingEnter) {
			float playerXCoord = player.getX();
			float playerYCoord = player.getY();

			for(int i = 0;i < itemList.size(); i++) {
				itemX = itemList.get(i).getX();
				itemY = itemList.get(i).getY();

				//doors
				if(itemList.get(i).getType() == 'D')
				{
					//Left door
					if(itemList.get(i).getID() == 1) {
						if (playerXCoord > ((itemX * 16) - 10) && playerXCoord < ((itemX * 16) + 10) &&
								playerYCoord > ((itemY * 16) - 24) && playerYCoord < ((itemY * 16) + 24)) {

							game.changeScreen(11);
						}
					}
					//Top door
					if(itemList.get(i).getID() == 2) {
						if (playerXCoord > ((itemX * 16) - 10) && playerXCoord < ((itemX * 16) + 10) &&
								playerYCoord > ((itemY * 16) - 24) && playerYCoord < ((itemY * 16) + 24)) {
							System.out.println("TOP DOOR ENTERED");
						}
					}
				}

				//items and hints
				else {
					if (playerXCoord > ((itemX * 16) - 10) && playerXCoord < ((itemX * 16) + 10) &&
							playerYCoord > ((itemY * 16) - 10) && playerYCoord < ((itemY * 16) + 10)) {
						//hints
						if (itemList.get(i).getType() == 'H') {

							game.changeStr(itemList.get(i).getHint(), 'h');
							game.changeScreen(6);
						}
						//items
						else {
							game.addToInv(new Sprite(new Texture(itemList.get(i).getLocation())));
							itemList.get(i).setX(itemList.get(i).updateCoordX() * -1);
							itemList.get(i).setY(itemList.get(i).updateCoordY() * -1);
						}
					}
				}
			}
			//Boss Dialogue
			if(player.getX() > (boss.getX() - 20) && player.getX() < (boss.getX() + 4) &&
					player.getY() > (boss.getY() - 20) && player.getY() < (boss.getY() + 4)){
				game.changeStr(boss.getDialogue(boss.getDiagSoFar()), 'b');
				game.changeScreen(6);
			}

			if (playerXCoord > ((9 * 16) - 10) && playerXCoord < ((9 * 16) + 10) &&
					playerYCoord > ((8 * 16) - 10) && playerYCoord < ((8 * 16) + 10))
			{
				game.changeScreen(7);
			}
		}

		//Setting the player
		x = player.updateCoordX(x);
		y = player.updateCoordY(y);
		player.setCollisionLayer(platformingLayer);
		player.setPosition(x, y);

		boss.setPosition(3 * 16, 2 * 16);

		//Setting the items
		for(int i = 0; i < itemList.size(); i++)
		{
			itemX = itemList.get(i).getX();
			itemY = itemList.get(i).getY();
			if(itemList.get(i).getType() != 'D') {
				itemList.get(i).setPosition(itemX * platformingLayer.getTileWidth(), itemY * platformingLayer.getTileHeight());
			}
		}

		//Updating the player
		player.setUpTime(upTime);
		player.setDownTime(downTime);
		player.setLeftTime(leftTime);
		player.setRightTime(rightTime);

		//Update render and camera
		camera.update();
		renderer.setView(camera);
		renderer.render(decorationLayers);
		renderer.getBatch().begin();
		renderer.renderTileLayer(platformingLayer);

		for( int i = 0; i < itemList.size(); i++) {
			if(itemList.get(i).getType() != 'D') {
				itemList.get(i).update(delta);
				itemList.get(i).draw(renderer.getBatch());

				itemListX.add((int) itemList.get(i).getX() / 16);
				itemListY.add((int) itemList.get(i).getY() / 16);
			}
		}

		player.update(delta);
		prevTexture = new Texture(player.prevTexture());
		boss.update(delta);

		upTime = player.getUpTime();
		downTime = player.getDownTime();
		leftTime = player.getLeftTime();
		rightTime = player.getRightTime();

		player.draw(renderer.getBatch());
		boss.draw(renderer.getBatch());
		renderer.getBatch().end();

		itemList.clear();
	}

	@Override
	public void render() {
	}

	@Override
	public void resize(int width, int height) {
		camera.viewportWidth = width;
		camera.viewportHeight = height;
	}

	@Override
	public void create() {
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
		player.getTexture().dispose();
		boss.getTexture().dispose();
		map.dispose();
		testItem.getTexture().dispose();
		renderer.dispose();
	}
	public double getID(){
		return id;
	}

	public void updateChar(int num)
	{
		charSelect = num;
	}

	public String getNextDialogue()
	{
		boss.setdiagSoFar();
		return boss.getDialogue(boss.getDiagSoFar());
	}
	public char getNextDiagType()
	{
		return boss.getDiagType();
	}
	public char getAnswer()
	{
		return boss.getAnswer();
	}
	public void incAnsIndex()
	{
		boss.incAnsIndex();
	}
}