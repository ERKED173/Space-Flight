package ru.erked.sflight.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

import ru.erked.sflight.controllers.SFlightInputController;
import ru.erked.sflight.menu.MainMenu;
import ru.erked.sflight.random.InfoAndStats;
import ru.erked.sflight.tech.SFButtonS;

public class AnalyticCentreScreen implements Screen{

	final String FONT_CHARS_RU = "абвгдежзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
	private static final float width = Gdx.graphics.getWidth();
	private static final float height = Gdx.graphics.getHeight();
	
	private Game game;
	private SpriteBatch batch;
	private SFlightInputController controller;
	public static float prevDay = (-1.0F)*1.0F;
	
	//Background
	private Texture backgroundTexture;
	public static Sprite backgroundSprite;
	private float backgroundX;
	private float backgroundY;
	public static final float backgroundTentionIndex = (float)width/400.0F;
	//"Back" Button
	private SFButtonS back;
	//Statistics' board
	private SFButtonS board;
	//Resource panel
	private Texture resourcePanelInactive;
	private Texture resourcePanelActive;
	private Sprite resourcePanelInactiveSprite;
	private Sprite resourcePanelActiveSprite;
	private float resourcePanel1X;
	private float resourcePanel1Y;
	private float resourcePanel1Width;
	private float resourcePanel1Height;
	private float resourcePanel2X;
	private float resourcePanel2Y;
	private float resourcePanel2Width;
	private float resourcePanel2Height;
	private static String schResI;
	private static String schResA;
	//Text
	private static BitmapFont text;
	
	private Sprite blackAlpha = new Sprite(new Texture("objects/black.png"));
	private float alp = 1.0F;
	private boolean isTransAnalytic;
	
	public AnalyticCentreScreen(Game game){
		this.game = game;
	}
	
	@Override
	public void show() {

		MainMenu.music.play();
		
		batch = new SpriteBatch();
		controller = new SFlightInputController();
		
		backgroundTexture = new Texture("bckgrnd/analytic_inside.png");
		backgroundSprite = new Sprite(backgroundTexture);
		backgroundX = 0.0F;
		backgroundY = (-1)*(230*backgroundTentionIndex)/2 + height/2;
		backgroundSprite.setBounds(backgroundX, backgroundY, width, backgroundTentionIndex*230.0F);

		resourcePanelInit();
		
		FreeTypeFontGenerator genUS = new FreeTypeFontGenerator(Gdx.files.internal("fonts/prototype.ttf"));
		FreeTypeFontGenerator genRU = new FreeTypeFontGenerator(Gdx.files.internal("fonts/9840.otf"));
		FreeTypeFontParameter param = new FreeTypeFontParameter();
		param.color = Color.WHITE;
		param.size = 40;
		if(!InfoAndStats.lngRussian){
			text = genUS.generateFont(param);
			text.getData().setScale((float)(0.0007F*width));
		}else{
			param.characters = FONT_CHARS_RU;
			text = genRU.generateFont(param);
			text.getData().setScale((float)(0.0006F*width));
		}
		
		board = new SFButtonS("objects/board", 0.2F*width, 0.6F*backgroundSprite.getWidth(), backgroundSprite.getY() + 0.475F*backgroundSprite.getHeight());
		back = new SFButtonS("btns/back", 0.132F*width, width - 0.147F*width, 0.005F*height);
		
		isTransAnalytic = false;
		blackAlpha.setBounds(0.0F, 0.0F, width, height);
		blackAlpha.setAlpha(1.0F);
		
	}

	private void resourcePanelInit(){
		resourcePanelInactive = new Texture("objects/resourcesPanelInactive/resourcesPanelInactive_1.png");
		resourcePanelActive = new Texture("objects/resourcesPanelActive/resourcesPanelActive_1.png");
		resourcePanelInactiveSprite = new Sprite(resourcePanelInactive);
		resourcePanelActiveSprite = new Sprite(resourcePanelActive);
		resourcePanel1Width = 0.115F*width;
		resourcePanel1Height = 1.4090909090909090909090909090909F*resourcePanel1Width;
		resourcePanel1X = 0.415F*backgroundSprite.getWidth();
		resourcePanel1Y = backgroundSprite.getY() + 0.385F*backgroundSprite.getHeight();
		resourcePanel2Width = 2.5F*resourcePanel1Width;
		resourcePanel2Height = 2.5F*resourcePanel1Height;
		resourcePanel2X = 0.415F*backgroundSprite.getWidth() - 0.3F*resourcePanel2Width;
		resourcePanel2Y = backgroundSprite.getY() + 0.385F*backgroundSprite.getHeight() - 0.3F*resourcePanel2Height;
		resourcePanelInactiveSprite.setBounds(resourcePanel1X, resourcePanel1Y, resourcePanel1Width, resourcePanel1Height);
		resourcePanelActiveSprite.setBounds(resourcePanel2X, resourcePanel2Y, resourcePanel2Width, resourcePanel2Height);
		schResI = "objects/resourcesPanelInactive/resourcesPanelInactive_1.png";
		schResA = "objects/resourcesPanelActive/resourcesPanelActive_1.png";
	}
	
	private void btnListener(){
		if(controller.isClicked(back.getX(), back.getY(), back.getWidth(), back.getHeight())){
			game.setScreen(new GameScreen(game));
			this.dispose();
		}
		if(controller.isClicked(board.getX(), board.getY(), board.getWidth(), board.getHeight())){
			game.setScreen(new StatisticScreen(game));
			this.dispose();
		}
		if(controller.isClicked(resourcePanel1X, resourcePanel1Y, resourcePanel1Width, resourcePanel1Height)){
			game.setScreen(new ResourceScreen(game));
			this.dispose();
		}
	}
	
	private void drawBackButton(){
		if(controller.isOn(back.getX(), back.getY(), back.getWidth(), back.getHeight())){
			back.setMode(true);
		}else{
			back.setMode(false);
		}
		back.getSprite().draw(batch);
	}
	private void drawScoreboard(){
		if(controller.isOn(board.getX(), board.getY(), board.getWidth(), board.getHeight())){
			board.setMode(true);
		}else{
			board.setMode(false);
		}
		board.getSprite().draw(batch);
	}
	private void drawResourcePanel(){
		if(controller.isOn(resourcePanel1X, resourcePanel1Y, resourcePanel1Width, resourcePanel1Height)){
			if(InfoAndStats.elapsedTime % 15 == 0){
				resourcePanelActiveSprite.setTexture(new Texture(schResA));
				if(schResA.equals("objects/resourcesPanelActive/resourcesPanelActive_1.png")) schResA = "objects/resourcesPanelActive/resourcesPanelActive_2.png";
				else if(schResA.equals("objects/resourcesPanelActive/resourcesPanelActive_2.png")) schResA = "objects/resourcesPanelActive/resourcesPanelActive_3.png";
				else if(schResA.equals("objects/resourcesPanelActive/resourcesPanelActive_3.png")) schResA = "objects/resourcesPanelActive/resourcesPanelActive_1.png";
			}
			resourcePanelActiveSprite.draw(batch);
		}else{
			if(InfoAndStats.elapsedTime % 15 == 0){
				resourcePanelInactiveSprite.setTexture(new Texture(schResI));
				if(schResI.equals("objects/resourcesPanelInactive/resourcesPanelInactive_1.png")) schResI = "objects/resourcesPanelInactive/resourcesPanelInactive_2.png";
				else if(schResI.equals("objects/resourcesPanelInactive/resourcesPanelInactive_2.png")) schResI = "objects/resourcesPanelInactive/resourcesPanelInactive_3.png";
				else if(schResI.equals("objects/resourcesPanelInactive/resourcesPanelInactive_3.png")) schResI = "objects/resourcesPanelInactive/resourcesPanelInactive_1.png";
			}
			resourcePanelInactiveSprite.draw(batch);
		}
	}

	@Override
	public void render(float delta) {
		InfoAndStats.elapsedTime++;
		
		if(alp>0.0F && (!isTransAnalytic)){
			blackAlpha.setAlpha(alp);
			alp-=0.05F;
		}else if(!isTransAnalytic){
			blackAlpha.setAlpha(0.0F);
			alp = 0.0F;
		}
		
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		
		backgroundSprite.draw(batch);

		if(!InfoAndStats.lngRussian){
			text.draw(batch, "Analytic centre", 0.01F*width, 0.99F*height);
		}else{
			text.draw(batch, "Аналитический центр", 0.01F*width, 0.99F*height);
		}
		
		drawBackButton();
		drawScoreboard();
		drawResourcePanel();
		
		blackAlpha.draw(batch);
		
		batch.end();
		
		resourcesCheck();
		btnListener();
		
	}

	private void resourcesCheck(){
		if(InfoAndStats.elapsedTime%(3600/InfoAndStats.moneyAmount) == 0){
			InfoAndStats.money++;
		}
		if(InfoAndStats.elapsedTime%(3600/InfoAndStats.fuelAmount) == 60){
			InfoAndStats.fuel++;
		}
		if(InfoAndStats.elapsedTime%(3600/InfoAndStats.metalAmount) == 120){
			InfoAndStats.metal++;
		}
		if(InfoAndStats.money>InfoAndStats.moneyFull) InfoAndStats.money = InfoAndStats.moneyFull;
		if(InfoAndStats.fuel>InfoAndStats.fuelFull) InfoAndStats.fuel = InfoAndStats.fuelFull;
		if(InfoAndStats.metal>InfoAndStats.metalFull) InfoAndStats.metal = InfoAndStats.metalFull;
	}
	
	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	private void textureDispose(){
		backgroundTexture.dispose();
		board.getTexture().dispose();
	}
	
	@Override
	public void dispose() {
		game.dispose();
		batch.dispose();
		text.dispose();
		textureDispose();
		GameScreen.isFromMenu = false;
	}

}