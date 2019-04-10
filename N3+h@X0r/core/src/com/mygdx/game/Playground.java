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
	private Texture standing, itemTexture;
	private Item testItem, testItem2;
	private ArrayList<Item> itemList = new ArrayList<Item>();
	private boolean pressingEnter;
	private Texture prevTexture;
	private Inventory inventory;

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
			itemListX.add(4);
			itemListY.add(1);
			itemListX.add(7);
			itemListY.add(3);
		}

		testItem = new Item(new Sprite(new Texture("core/assets/test_item.png")), 'I',
				"Test Item", "core/assets/test_item.png", 1, platformingLayer, itemListX.get(0), itemListY.get(0));
		testItem2 = new Item(new Sprite(new Texture("core/assets/sticky_note.png")), 'H',
				"Hint 1", "core/assets/sticky_note.png", 1, platformingLayer, itemListX.get(1), itemListY.get(1),
				"We're no strangers to love\n" +
						"You know the rules and so do I\n" +
						"A full commitment's what I'm thinking of\n" +
						"You wouldn't get this from any other guy\n" +
						"I just wanna tell you how I'm feeling\n" +
						"Gotta make you understand\n" +
						"...");


		itemList.add(testItem);
		itemList.add(testItem2);

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
			if(itemList.get(i).getType() == 'H') {
				itemList.get(i).setBounds(itemList.get(i).getX(), itemList.get(i).getY(), 6, 6);
			}
			else {
				itemList.get(i).setBounds(itemList.get(i).getX(), itemList.get(i).getY(), 16, 16);
				//itemList.get(i).setCollisionLayer(platformingLayer);
			}
		}

		if(pressingEnter) {
			for(int i = 0;i < itemList.size(); i++) {
				itemX = itemList.get(i).getX();
				itemY = itemList.get(i).getY();

				if(player.getX() > ((itemX * 16) - 10) && player.getX() < ((itemX * 16) + 10) &&
						player.getY() > ((itemY * 16) - 10) && player.getY() < ((itemY * 16) + 10)) {

					if(itemList.get(i).getType() == 'H')
					{
						game.changeStr(itemList.get(i).getHint());
						game.changeScreen(6);
					}
					else
					{
						//need to somehow add that item's sprite to the inventory screen
						//inventory.itemSpriteList[i] = itemList.get(i). -need to get the sprite somehow after this dot notation
					}
					itemList.get(i).setX(itemList.get(i).updateCoordX() * -1);
					itemList.get(i).setY(itemList.get(i).updateCoordY() * -1);
				}
			}
		}

		//Setting the player
		x = player.updateCoordX(x);
		y = player.updateCoordY(y);
		player.setCollisionLayer(platformingLayer);
		player.setPosition(x, y);

		//Setting the items
		for(int i = 0; i < itemList.size(); i++)
		{
			itemX = itemList.get(i).getX();
			itemY = itemList.get(i).getY();
			itemList.get(i).setPosition(itemX * platformingLayer.getTileWidth(), itemY * platformingLayer.getTileHeight());
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

			itemList.get(i).update(delta);
			itemList.get(i).draw(renderer.getBatch());

			itemListX.add((int) itemList.get(i).getX() / 16);
			itemListY.add((int) itemList.get(i).getY() / 16);
		}

		player.update(delta);
		prevTexture = new Texture(player.prevTexture());

		upTime = player.getUpTime();
		downTime = player.getDownTime();
		leftTime = player.getLeftTime();
		rightTime = player.getRightTime();

		player.draw(renderer.getBatch());
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
}