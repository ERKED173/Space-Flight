package ru.erked.sflight.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

import ru.erked.sflight.StartSFlight;
import ru.erked.sflight.controllers.SFlightInputController;
import ru.erked.sflight.menu.MainMenu;
import ru.erked.sflight.random.INF;
import ru.erked.sflight.tech.SFButtonS;

public class AnalyticCentreScreen implements Screen{

	final String FONT_CHARS_RU = "абвгдежзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
	private static final float width = Gdx.graphics.getWidth();
	private static final float height = Gdx.graphics.getHeight();
	
	private final StartSFlight game;
	private SFlightInputController controller;
	public static float prevDay = (-1.0F)*1.0F;
	
	//Background
	private Texture backgroundTexture;
	public static Sprite backgroundSprite;
	private float backgroundX;
	private float backgroundY;
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
	
	public AnalyticCentreScreen(final StartSFlight game){
		this.game = game;
	}
	
	@Override
	public void show() {

		MainMenu.music.play();
		
		controller = new SFlightInputController();
		
		backgroundTexture = new Texture("bckgrnd/analytic_inside.png");
		backgroundSprite = new Sprite(backgroundTexture);
		if((float)backgroundTexture.getWidth()/backgroundTexture.getHeight() > (float)width/height){
			backgroundX = 0.0F;
			backgroundY = height/2 - width*(float)backgroundTexture.getHeight()/backgroundTexture.getWidth()/2;
			backgroundSprite.setBounds(backgroundX, backgroundY, width, width*(float)backgroundTexture.getHeight()/backgroundTexture.getWidth());
		}else{
			backgroundX = width/2 - 0.5625F*((float)height*((float)backgroundTexture.getWidth()/backgroundTexture.getHeight()));
			backgroundY = 0.0F;
			backgroundSprite.setBounds(backgroundX, backgroundY, (float)height*((float)backgroundTexture.getWidth()/backgroundTexture.getHeight()), height);
		}

		resourcePanelInit();
		
		FreeTypeFontGenerator genUS = new FreeTypeFontGenerator(Gdx.files.internal("fonts/prototype.ttf"));
		FreeTypeFontGenerator genRU = new FreeTypeFontGenerator(Gdx.files.internal("fonts/9840.otf"));
		FreeTypeFontParameter param = new FreeTypeFontParameter();
		param.color = Color.WHITE;
		param.size = 40;
		if(!INF.lngRussian){
			text = genUS.generateFont(param);
			text.getData().setScale((float)(0.0007F*width));
		}else{
			param.characters = FONT_CHARS_RU;
			text = genRU.generateFont(param);
			text.getData().setScale((float)(0.0006F*width));
		}
		
		board = new SFButtonS("objects/board", 0.2F*width, 0.6F*backgroundSprite.getWidth(), backgroundSprite.getY() + 0.475F*backgroundSprite.getHeight(), 1.0F);
		if(!INF.lngRussian){
			back = new SFButtonS("btns/back", 0.132F*width, width - 0.147F*width, 0.005F*height, 1.0F);
		}else{
			back = new SFButtonS("btns/RU/backR", 0.132F*width, width - 0.147F*width, 0.005F*height, 1.0F);
		}
		
		genUS.dispose();
		genRU.dispose();
		
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
		back.getSprite().draw(game.batch);
	}
	private void drawScoreboard(){
		if(controller.isOn(board.getX(), board.getY(), board.getWidth(), board.getHeight())){
			board.setMode(true);
		}else{
			board.setMode(false);
		}
		board.getSprite().draw(game.batch);
	}
	private void drawResourcePanel(){
		if(controller.isOn(resourcePanel1X, resourcePanel1Y, resourcePanel1Width, resourcePanel1Height)){
			if(INF.elapsedTime % 15 == 0){
				resourcePanelActiveSprite.setTexture(new Texture(schResA));
				if(schResA.equals("objects/resourcesPanelActive/resourcesPanelActive_1.png")) schResA = "objects/resourcesPanelActive/resourcesPanelActive_2.png";
				else if(schResA.equals("objects/resourcesPanelActive/resourcesPanelActive_2.png")) schResA = "objects/resourcesPanelActive/resourcesPanelActive_3.png";
				else if(schResA.equals("objects/resourcesPanelActive/resourcesPanelActive_3.png")) schResA = "objects/resourcesPanelActive/resourcesPanelActive_1.png";
			}
			resourcePanelActiveSprite.draw(game.batch);
		}else{
			if(INF.elapsedTime % 15 == 0){
				resourcePanelInactiveSprite.setTexture(new Texture(schResI));
				if(schResI.equals("objects/resourcesPanelInactive/resourcesPanelInactive_1.png")) schResI = "objects/resourcesPanelInactive/resourcesPanelInactive_2.png";
				else if(schResI.equals("objects/resourcesPanelInactive/resourcesPanelInactive_2.png")) schResI = "objects/resourcesPanelInactive/resourcesPanelInactive_3.png";
				else if(schResI.equals("objects/resourcesPanelInactive/resourcesPanelInactive_3.png")) schResI = "objects/resourcesPanelInactive/resourcesPanelInactive_1.png";
			}
			resourcePanelInactiveSprite.draw(game.batch);
		}
	}

	@Override
	public void render(float delta) {
		INF.elapsedTime++;
		resourcesCheck();
		
		if(alp>0.0F && (!isTransAnalytic)){
			blackAlpha.setAlpha(alp);
			alp-=0.05F;
		}else if(!isTransAnalytic){
			blackAlpha.setAlpha(0.0F);
			alp = 0.0F;
		}
		
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.batch.begin();
		
		backgroundSprite.draw(game.batch);

		if(!INF.lngRussian){
			text.draw(game.batch, "Analytic centre", 0.01F*width, 0.99F*height);
		}else{
			text.draw(game.batch, "Аналитический центр", 0.01F*width, 0.99F*height);
		}
		
		drawBackButton();
		drawScoreboard();
		drawResourcePanel();
		
		blackAlpha.draw(game.batch);
		
		game.batch.end();
		
		btnListener();
		
	}

	private void resourcesCheck(){
		if(INF.elapsedTime%(3600/INF.moneyAmount) == 0){
			INF.money++;
		}
		if(INF.elapsedTime%(3600/INF.fuelAmount) == 60){
			INF.fuel++;
		}
		if(INF.elapsedTime%(3600/INF.metalAmount) == 120){
			INF.metal++;
		}
		if(INF.money>INF.moneyFull) INF.money = INF.moneyFull;
		if(INF.fuel>INF.fuelFull) INF.fuel = INF.fuelFull;
		if(INF.metal>INF.metalFull) INF.metal = INF.metalFull;
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
		blackAlpha.getTexture().dispose();
		back.getSprite().getTexture().dispose();
		backgroundTexture.dispose();
		board.getTexture().dispose();
		resourcePanelActive.dispose();
		resourcePanelInactive.dispose();
	}
	
	@Override
	public void dispose() {
		text.dispose();
		textureDispose();
		GameScreen.isFromMenu = false;
	}

}