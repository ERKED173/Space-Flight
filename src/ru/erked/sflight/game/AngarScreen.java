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

public class AngarScreen implements Screen{

	final String FONT_CHARS_RU = "абвгдежзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
	private static final float width = Gdx.graphics.getWidth();
	private static final float height = Gdx.graphics.getHeight();
	
	private Game game;
	private SpriteBatch batch;
	private SFlightInputController controller;
	
	//Background
	private Texture backgroundTexture;
	public static Sprite backgroundSprite;
	private float backgroundX;
	private float backgroundY;
	public static final float backgroundTentionIndex = (float)width/150.0F;
	
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
	
	public AngarScreen(Game game){
		this.game = game;
	}
	
	@Override
	public void show() {
		
		batch = new SpriteBatch();
		controller = new SFlightInputController();

		MainMenu.music.play();
		
		backgroundTexture = new Texture("bckgrnd/angarInside.png");
		backgroundSprite = new Sprite(backgroundTexture);
		backgroundX = 0.0F;
		backgroundY = (height - width/1.6129032258064516129032258064516F)/2.0F;
		backgroundSprite.setBounds(backgroundX, backgroundY, width, width/1.6129032258064516129032258064516F);
		
		back = new SFButtonS("btns/back", 0.132F*width, width - 0.147F*width, 0.005F*height);
		
		panelInit();
		
		FreeTypeFontGenerator genUS = new FreeTypeFontGenerator(Gdx.files.internal("fonts/prototype.ttf"));
		FreeTypeFontGenerator genRU = new FreeTypeFontGenerator(Gdx.files.internal("fonts/9840.otf"));
		FreeTypeFontParameter param = new FreeTypeFontParameter();
		param.color = Color.WHITE;
		param.size = 40;
		if(!InfoAndStats.lngRussian){
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
		InfoAndStats.elapsedTime++;
		
		if(alp>0.0F && (!isTransAngar)){
			blackAlpha.setAlpha(alp);
			alp-=0.05F;
		}else if(!isTransAngar){
			blackAlpha.setAlpha(0.0F);
			alp = 0.0F;
		}
		
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		
		backgroundSprite.draw(batch);
		
		if(!InfoAndStats.lngRussian){
			text.draw(batch, "Hangar", 0.01F*width, 0.99F*height);
		}else{
			text.draw(batch, "Ангар", 0.01F*width, 0.99F*height);
		}
		
		drawPanel();
		drawBackButton();
		
		blackAlpha.draw(batch);
		
		batch.end();
		
		buttonListeners();
		resourcesCheck();
		
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
		back.getSprite().draw(batch);
	}
	private void drawPanel(){
		if(controller.isOn(panel1X, panel1Y, panel1Width, panel1Height)){
			if(InfoAndStats.elapsedTime % 15 == 0){
				panelActiveSprite.setTexture(new Texture(schResA));
				if(schResA.equals("objects/hangarPanel/hangarPanelActive_1.png")) schResA = "objects/hangarPanel/hangarPanelActive_2.png";
				else if(schResA.equals("objects/hangarPanel/hangarPanelActive_2.png")) schResA = "objects/hangarPanel/hangarPanelActive_1.png";
			}
			panelActiveSprite.draw(batch);
		}else{
			if(InfoAndStats.elapsedTime % 15 == 0){
				panelInactiveSprite.setTexture(new Texture(schResI));
				if(schResI.equals("objects/hangarPanel/hangarPanelInactive_1.png")) schResI = "objects/hangarPanel/hangarPanelInactive_2.png";
				else if(schResI.equals("objects/hangarPanel/hangarPanelInactive_2.png")) schResI = "objects/hangarPanel/hangarPanelInactive_1.png";
			}
			panelInactiveSprite.draw(batch);
		}
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
		game.dispose();
		batch.dispose();
		text.dispose();
		textureDispose();
		GameScreen.isFromMenu = false;
	}

}