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

public class HangarScreen implements Screen{

	final String FONT_CHARS_RU = "абвгдежзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
	private static final float width = Gdx.graphics.getWidth();
	private static final float height = Gdx.graphics.getHeight();
	
	private final StartSFlight game;
	private SFlightInputController controller;
	
	//Background
	private Texture backgroundTexture;
	public static Sprite backgroundSprite;
	private float backgroundX;
	private float backgroundY;
	
	//"Back" Button
	private SFButtonS back;
	
	//Hangar panel
	private Texture panelInactive;
	private Texture panelActive;
	private Sprite panelInactiveSprite;
	private Sprite panelActiveSprite;
	private float panel1X;
	private float panel1Y;
	private float panel1Width;
	private float panel1Height;
	private float panel2X;
	private float panel2Y;
	private float panel2Width;
	private float panel2Height;
	private static String schResI;
	private static String schResA;
	
	//Text
	private static BitmapFont text;
	
	private Sprite blackAlpha = new Sprite(new Texture("objects/black.png"));
	private float alp = 1.0F;
	private boolean isTransAngar;
	
	public HangarScreen(final StartSFlight game){
		this.game = game;
	}
	
	@Override
	public void show() {
		
		controller = new SFlightInputController();

		MainMenu.music.play();
		
		backgroundTexture = new Texture("bckgrnd/angarInside.png");
		backgroundSprite = new Sprite(backgroundTexture);
		if((float)backgroundTexture.getWidth()/backgroundTexture.getHeight() > (float)width/height){
			backgroundX = 0.0F;
			backgroundY = height/2 - width*(float)backgroundTexture.getHeight()/backgroundTexture.getWidth()/2;
			backgroundSprite.setBounds(backgroundX, backgroundY, width, width*(float)backgroundTexture.getHeight()/backgroundTexture.getWidth());
		}else{
			backgroundX = width/2 - 0.6F*((float)height*((float)backgroundTexture.getWidth()/backgroundTexture.getHeight()));
			backgroundY = 0.0F;
			backgroundSprite.setBounds(backgroundX, backgroundY, (float)height*((float)backgroundTexture.getWidth()/backgroundTexture.getHeight()), height);
		}
		
		if(!INF.lngRussian){
			back = new SFButtonS("btns/back", 0.132F*width, width - 0.147F*width, 0.005F*height, 1.0F);
		}else{
			back = new SFButtonS("btns/RU/backR", 0.132F*width, width - 0.147F*width, 0.005F*height, 1.0F);
		}
		
		panelInit();
		
		FreeTypeFontGenerator genUS = new FreeTypeFontGenerator(Gdx.files.internal("fonts/prototype.ttf"));
		FreeTypeFontGenerator genRU = new FreeTypeFontGenerator(Gdx.files.internal("fonts/9840.otf"));
		FreeTypeFontParameter param = new FreeTypeFontParameter();
		param.color = Color.WHITE;
		param.size = 40;
		if(!INF.lngRussian){
			text = genUS.generateFont(param);
			text.getData().setScale((float)(0.00075F*width));
		}else{
			param.characters = FONT_CHARS_RU;
			text = genRU.generateFont(param);
			text.getData().setScale((float)(0.0006F*width));
		}
		
		isTransAngar = false;
		blackAlpha.setBounds(0.0F, 0.0F, width, height);
		blackAlpha.setAlpha(1.0F);
		
	}

	@Override
	public void render(float delta) {
		INF.elapsedTime++;
		resourcesCheck();
		
		if(alp>0.0F && (!isTransAngar)){
			blackAlpha.setAlpha(alp);
			alp-=0.05F;
		}else if(!isTransAngar){
			blackAlpha.setAlpha(0.0F);
			alp = 0.0F;
		}
		
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.batch.begin();
		
		backgroundSprite.draw(game.batch);
		
		if(!INF.lngRussian){
			text.draw(game.batch, "Hangar", 0.01F*width, 0.99F*height);
		}else{
			text.draw(game.batch, "Ангар", 0.01F*width, 0.99F*height);
		}
		
		drawPanel();
		drawBackButton();
		
		blackAlpha.draw(game.batch);
		
		game.batch.end();
		
		buttonListeners();
		
	}

	private void panelInit(){
		panelInactive = new Texture("objects/hangarPanel/hangarPanelInactive_1.png");
		panelActive = new Texture("objects/hangarPanel/hangarPanelActive_1.png");
		panelInactiveSprite = new Sprite(panelInactive);
		panelActiveSprite = new Sprite(panelActive);
		panel1Width = 0.2F*width;
		panel1Height = 1.0253164556962025316455696202532F*panel1Width;
		panel1X = 0.125F*backgroundSprite.getWidth();
		panel1Y = backgroundSprite.getY() + 0.165F*backgroundSprite.getHeight();
		panel2Width = 1.6455696202531645569620253164557F*panel1Width;
		panel2Height = 1.641975308641975308641975308642F*panel1Height;
		panel2X = 0.125F*backgroundSprite.getWidth() - 0.19230769230769230769230769230769F*panel2Width;
		panel2Y = backgroundSprite.getY() + 0.165F*backgroundSprite.getHeight() - 0.19548872180451127819548872180451F*panel2Height;
		panelInactiveSprite.setBounds(panel1X, panel1Y, panel1Width, panel1Height);
		panelActiveSprite.setBounds(panel2X, panel2Y, panel2Width, panel2Height);
		schResI = "objects/hangarPanel/hangarPanelInactive_1.png";
		schResA = "objects/hangarPanel/hangarPanelActive_1.png";
	}

	private void drawBackButton(){
		if(controller.isOn(back.getX(), back.getY(), back.getWidth(), back.getHeight())){
			back.setMode(true);
		}else{
			back.setMode(false);
		}
		back.getSprite().draw(game.batch);
	}
	private void drawPanel(){
		if(controller.isOn(panel1X, panel1Y, panel1Width, panel1Height)){
			if(INF.elapsedTime % 15 == 0){
				panelActiveSprite.setTexture(new Texture(schResA));
				if(schResA.equals("objects/hangarPanel/hangarPanelActive_1.png")) schResA = "objects/hangarPanel/hangarPanelActive_2.png";
				else if(schResA.equals("objects/hangarPanel/hangarPanelActive_2.png")) schResA = "objects/hangarPanel/hangarPanelActive_1.png";
			}
			panelActiveSprite.draw(game.batch);
		}else{
			if(INF.elapsedTime % 15 == 0){
				panelInactiveSprite.setTexture(new Texture(schResI));
				if(schResI.equals("objects/hangarPanel/hangarPanelInactive_1.png")) schResI = "objects/hangarPanel/hangarPanelInactive_2.png";
				else if(schResI.equals("objects/hangarPanel/hangarPanelInactive_2.png")) schResI = "objects/hangarPanel/hangarPanelInactive_1.png";
			}
			panelInactiveSprite.draw(game.batch);
		}
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
	
	private void buttonListeners(){
		if(controller.isClicked(panel1X, panel1Y, panel1Width, panel1Height)){
			game.setScreen(new HangarPanelScreen(game));
			this.dispose();
		}
		if(controller.isClicked(back.getX(), back.getY(), back.getWidth(), back.getHeight())){
			game.setScreen(new GameScreen(game));
			this.dispose();
		}
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
	}
	
	@Override
	public void dispose() {
		text.dispose();
		textureDispose();
		GameScreen.isFromMenu = false;
	}

}