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

public class ControlCentreScreen implements Screen{

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
	public static final float backgroundTentionIndex = (float)width/185.0F;
	
	//"Back" Button
	private SFButtonS back;
	
	//Control panel
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
	private float panelAspectRatio;
	private static String schPanelI;
	private static String schPanelA;
	
	//Text
	private static BitmapFont text;
	
	private Sprite blackAlpha = new Sprite(new Texture("objects/black.png"));
	private float alp = 1.0F;
	private boolean isTransControl;
	
	public ControlCentreScreen(final StartSFlight game){
		this.game = game;
	}
	
	@Override
	public void show() {
		
		controller = new SFlightInputController();

		MainMenu.music.play();
		
		backgroundTexture = new Texture("bckgrnd/control_inside.png");
		backgroundSprite = new Sprite(backgroundTexture);
		backgroundX = 0.0F;
		backgroundY = (-1)*(74*backgroundTentionIndex)/2 + height/2;
		backgroundSprite.setBounds(backgroundX, backgroundY, width, backgroundTentionIndex*74.0F);
		
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
		}else{
			param.characters = FONT_CHARS_RU;
			text = genRU.generateFont(param);
		}
		text.getData().setScale((float)(0.00075F*width));
		
		genUS.dispose();
		genRU.dispose();
		
		isTransControl = false;
		blackAlpha.setBounds(0.0F, 0.0F, width, height);
		blackAlpha.setAlpha(1.0F);
		
	}

	@Override
	public void render(float delta) {
		INF.elapsedTime++;
		resourcesCheck();
		
		if(alp>0.0F && (!isTransControl)){
			blackAlpha.setAlpha(alp);
			alp-=0.05F;
		}else if(!isTransControl){
			blackAlpha.setAlpha(0.0F);
			alp = 0.0F;
		}
		
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.batch.begin();
		
		backgroundSprite.draw(game.batch);
		
		if(!INF.lngRussian){
			text.draw(game.batch, "Control tower", 0.01F*width, 0.99F*height);
		}else{
			text.draw(game.batch, "Диспетчерская вышка", 0.01F*width, 0.99F*height);
		}
		
		if(controller.isOn(back.getX(), back.getY(), back.getWidth(), back.getHeight())){
			back.setMode(true);
		}else{
			back.setMode(false);
		}
		back.getSprite().draw(game.batch);
		
		drawPanel();
		
		blackAlpha.draw(game.batch);
		
		game.batch.end();
		
		btnListeners();
		
	}

	private void panelInit(){
		panelInactive = new Texture("objects/controlPanelInactive/controlPanelInactive_1.png");
		panelActive = new Texture("objects/controlPanelActive/controlPanelActive_1.png");
		panelInactiveSprite = new Sprite(panelInactive);
		panelActiveSprite = new Sprite(panelActive);
		panelAspectRatio = (float)panelInactive.getWidth()/panelInactive.getHeight();
		panel1Width = 0.075F*width;
		panel1Height = (float)panel1Width/panelAspectRatio;
		panel1X = 0.55F*backgroundSprite.getWidth();
		panel1Y = backgroundSprite.getY() + 0.35F*backgroundSprite.getHeight();
		panel2Width = 0.14423076923076923076923076923077F*width;
		panel2Height = (float)panel2Width/panelAspectRatio;
		panel2X = 0.55F*backgroundSprite.getWidth() - 0.24F*panel2Width;
		panel2Y = backgroundSprite.getY() + 0.35F*backgroundSprite.getHeight() - 0.24038461538461538461538461538462F*panel2Height;
		panelInactiveSprite.setBounds(panel1X, panel1Y, panel1Width, panel1Height);
		panelActiveSprite.setBounds(panel2X, panel2Y, panel2Width, panel2Height);
		schPanelI = "objects/controlPanelInactive/controlPanelInactive_1.png";
		schPanelA = "objects/controlPanelActive/controlPanelActive_1.png";
	}
	private void drawPanel(){
		if(controller.isOn(panel1X, panel1Y, panel1Width, panel1Height)){
			if(INF.elapsedTime % 15 == 0){
				panelActiveSprite.setTexture(new Texture(schPanelA));
				if(schPanelA.equals("objects/controlPanelActive/controlPanelActive_1.png")) schPanelA = "objects/controlPanelActive/controlPanelActive_2.png";
				else if(schPanelA.equals("objects/controlPanelActive/controlPanelActive_2.png")) schPanelA = "objects/controlPanelActive/controlPanelActive_3.png";
				else if(schPanelA.equals("objects/controlPanelActive/controlPanelActive_3.png")) schPanelA = "objects/controlPanelActive/controlPanelActive_1.png";
			}
			panelActiveSprite.draw(game.batch);
		}else{
			if(INF.elapsedTime % 15 == 0){
				panelInactiveSprite.setTexture(new Texture(schPanelI));
				if(schPanelI.equals("objects/controlPanelInactive/controlPanelInactive_1.png")) schPanelI = "objects/controlPanelInactive/controlPanelInactive_2.png";
				else if(schPanelI.equals("objects/controlPanelInactive/controlPanelInactive_2.png")) schPanelI = "objects/controlPanelInactive/controlPanelInactive_3.png";
				else if(schPanelI.equals("objects/controlPanelInactive/controlPanelInactive_3.png")) schPanelI = "objects/controlPanelInactive/controlPanelInactive_1.png";
			}
			panelInactiveSprite.draw(game.batch);
		}
	}
	
	private void btnListeners(){
		if(controller.isClicked(back.getX(), back.getY(), back.getWidth(), back.getHeight())){
			game.setScreen(new GameScreen(game));
			this.dispose();
		}
		if(controller.isClicked(panel1X, panel1Y, panel1Width, panel1Height)){
			game.setScreen(new StartPanelScreen(game));
			this.dispose();
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
		panelInactive.dispose();
		panelActive.dispose();
		back.getSprite().getTexture().dispose();
		blackAlpha.getTexture().dispose();
	}
	
	@Override
	public void dispose() {
		text.dispose();
		textureDispose();
		GameScreen.isFromMenu = false;
	}

}