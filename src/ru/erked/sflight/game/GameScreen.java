package ru.erked.sflight.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Vector3;

import ru.erked.sflight.StartSFlight;
import ru.erked.sflight.controllers.SFlightInputController;
import ru.erked.sflight.menu.MainMenu;
import ru.erked.sflight.planets.EmionScreen;
import ru.erked.sflight.planets.LoonScreen;
import ru.erked.sflight.random.INF;
import ru.erked.sflight.tech.CurPR;
import ru.erked.sflight.tech.Planet;
import ru.erked.sflight.tech.Rocket;
import ru.erked.sflight.tech.SFButtonA;
import ru.erked.sflight.tech.SFButtonS;

public class GameScreen implements Screen{

	final String FONT_CHARS_RU = "абвгдежзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
	private static final float width = Gdx.graphics.getWidth();
	private static final float height = Gdx.graphics.getHeight();
	
	private final StartSFlight game;
	private SpriteBatch batch;
	public static OrthographicCamera camera;
	private SFlightInputController controller;
	
	private Music cash = Gdx.audio.newMusic(Gdx.files.internal("sounds/misc/cash.wav"));
	private Music bubble = Gdx.audio.newMusic(Gdx.files.internal("sounds/misc/bubble.wav"));
	private Music anvil = Gdx.audio.newMusic(Gdx.files.internal("sounds/misc/anvil.wav"));
	private Music flight = Gdx.audio.newMusic(Gdx.files.internal("sounds/misc/WavLibraryNet_Sound6386.mp3"));
	private boolean isStarted;
	
	public static boolean isFromMenu = true;
	private float speed;
	
//Background
	private Texture backgroundTexture;
	public static Sprite backgroundSprite;
	private float backgroundX;
	private float backgroundY;
	public static final float backgroundTentionIndex = (float)2*width/2560.0F;
	
//Hangar
	private SFButtonS hangar;
//Analytic center
	private SFButtonS analytic;	
//Control tower
	private SFButtonS control;	
//Science center
	private SFButtonS gunStore;	
	
//FuelFactory
	private Texture fuelFactoryTexture;
	public static Sprite fuelFactorySprite;
	private float fuelFactoryWidth;
	private float fuelFactoryHeight;
	private float fuelFactoryX;
	private float fuelFactoryY;
	private float fuelFactoryTentionIndex;
//CoinFactory
	private Texture coinFactoryTexture;
	public static Sprite coinFactorySprite;
	private float coinFactoryWidth;
	private float coinFactoryHeight;
	private float coinFactoryX;
	private float coinFactoryY;
	private float coinFactoryTentionIndex;
//MetalFactory
	private Texture metalFactoryTexture;
	public static Sprite metalFactorySprite;
	private float metalFactoryWidth;
	private float metalFactoryHeight;
	private float metalFactoryX;
	private float metalFactoryY;
	private float metalFactoryTentionIndex;
	
//Scroll
	private static float prevDragX;
	private static float prevDragY;
	
//"Main Menu" Button
	private SFButtonA btnMN;
	private SFButtonA btnPlus;
	
//Resources
	private Sprite moneySprite;
	private Sprite fuelSprite;
	private Sprite metalSprite;
	private float moneyX;
	private float moneyY;
	private float moneyWidth;
	private float moneyHeight;
	private static BitmapFont text;
	private static BitmapFont textBtn;
	private Sprite line;
	private Sprite cosmocoinLine;
	private Sprite fuelLine;
	private Sprite metalLine;
	
	private Sprite blackAlpha = new Sprite(new Texture("objects/black.png"));
	private float alp = 1.0F;
	private boolean isTransGame;
	
	private static String schFuel;
	private static String schCoin;
	private static String schMetal;
	
	//Planets
	private Planet planet;
	
	//Rockets
	private Rocket rocket;
	private SFButtonS rocketS;
	private SFButtonS rocketBall;
	private SFButtonS rocketCircle;
	private SFButtonS rocketBasic;
	private SFButtonS rocketKinetic;
	
	private Sprite rocketFire;
	private String schFire;
	private float rocketX;
	private float rocketY;	
	
	public GameScreen(final StartSFlight game){
		this.game = game;
	}
	
	@Override
	public void show() {
		
		batch = new SpriteBatch();
		
		controller = new SFlightInputController();
		MainMenu.music.play();
		
		rocketsPreInit();
		planet = CurPR.getCurPlanet();
		rocket = CurPR.getCurRocket();
		if(rocket != null){
			if(rocket.equals(INF.rocketBall)){
				rocketS = rocketBall;
			}else if(rocket.equals(INF.rocketCircle)){
				rocketS = rocketCircle;
			}else if(rocket.equals(INF.rocketBasic)){
				rocketS = rocketBasic;
			}else if(rocket.equals(INF.rocketKinetic)){
				rocketS = rocketKinetic;
			}
		}
		
		cash.setVolume(1.0F);
		bubble.setVolume(1.0F);
		anvil.setVolume(1.0F);
		isStarted = false;
		flight.setVolume(0.5F);
		
		backgroundTexture = new Texture("bckgrnd/spaceport_4.png");
		backgroundSprite = new Sprite(backgroundTexture);
		backgroundX = 0.0F;
		backgroundY = 0.0F;
		backgroundSprite.setBounds(backgroundX, backgroundY, width*2, backgroundTentionIndex*2560.0F);
		
		camera = new OrthographicCamera(width, height);
		camera.position.set(new Vector3(0.6F*backgroundSprite.getWidth(), 0.8F*backgroundSprite.getHeight(), 0));
		
		FreeTypeFontGenerator genUS = new FreeTypeFontGenerator(Gdx.files.internal("fonts/prototype.ttf"));
		FreeTypeFontGenerator genRU = new FreeTypeFontGenerator(Gdx.files.internal("fonts/9840.otf"));
		FreeTypeFontParameter param = new FreeTypeFontParameter();
		FreeTypeFontParameter param2 = new FreeTypeFontParameter();
		param.color = Color.WHITE;
		param.size = 40;
		param2.color = Color.WHITE;
		param2.size = 60;
		if(INF.lngRussian){
			param.characters = FONT_CHARS_RU;
			param2.characters = FONT_CHARS_RU;
			text = genRU.generateFont(param);
			textBtn = genRU.generateFont(param2);
			textBtn.getData().setScale((float)(0.00045F*width));
		}else{
			text = genUS.generateFont(param);
			textBtn = genUS.generateFont(param2);
			textBtn.getData().setScale((float)(0.0006F*width));
		}
		text.getData().setScale((float)(0.00045F*width));
		
		moneyInit();
		fuelFactoryInit();
		coinFactoryInit();
		metalFactoryInit();
		rocketsInit();
		
		btnMN = new SFButtonA("btns/button", 0.132F*width, 0.86F*width, 0.01F*height, camera, 1.0F);
		btnMN.getSprite().setColor(Color.CYAN);
		btnPlus = new SFButtonA("btns/button", 0.132F*width, 0.86F*width, 0.01F*height + 1.1F*btnMN.getHeight(), camera, 0.775F);
		btnPlus.getSprite().setColor(Color.RED);
		hangar = new SFButtonS("objects/angar", 0.5F*width, 0.150F*backgroundSprite.getWidth(), 0.625F*backgroundSprite.getHeight(), 1.0F);		
		analytic = new SFButtonS("objects/analytic", 0.2F*width, 0.739F*backgroundSprite.getWidth(), 0.785F*backgroundSprite.getHeight(), 1.0F);		
		control = new SFButtonS("objects/control", 0.125F*width, 0.469F*backgroundSprite.getWidth(), 0.665F*backgroundSprite.getHeight(), 1.0F);		
		gunStore = new SFButtonS("objects/gunStore", 0.35F*width, 0.359F*backgroundSprite.getWidth(), 0.45F*backgroundSprite.getHeight(), 1.0F);		
		
		isTransGame = false;
		blackAlpha.setBounds(0.0F, 0.0F, backgroundSprite.getWidth(), backgroundSprite.getHeight());
		blackAlpha.setAlpha(1.0F);
		
		speed = 0.01F;
		if(rocket != null){
			rocketX = rocketS.getSprite().getX() + rocketS.getSprite().getWidth()/2;
			rocketY = rocketS.getSprite().getY() + rocketS.getSprite().getHeight()/2;
		}
	}

	@Override
	public void render(float delta) {
		INF.elapsedTime++;
		resourcesCheck();
		
		if(alp>0.0F && (!isTransGame)){
			blackAlpha.setAlpha(alp);
			alp-=0.05F;
		}else if(!isTransGame){
			blackAlpha.setAlpha(0.0F);
			alp = 0.0F;
		}
		
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		touchUpdate();
		
		moneyCoords();
		
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		
		backgroundSprite.draw(batch);
		
		drawBuildings();
		drawRockets();
		if(INF.isLaunch){
			drawFlight();
			if(!isStarted){
				flight.play();
				isStarted = true;
			}
		}
		drawButtons();
		drawMoney();
		
		blackAlpha.draw(batch);
		
		batch.end();
		
		buttonListener();
		
	}
	
	private void moneyCoords(){
		moneyX = camera.position.x - moneyWidth - (width/2 - 0.065F*width);
		moneyY = camera.position.y - moneyHeight + (height/2 - 0.015F*height);
		moneySprite.setX(moneyX);
		moneySprite.setY(moneyY);
		fuelSprite.setX(moneyX + 10.0F*moneyWidth);
		fuelSprite.setY(moneyY);
		metalSprite.setX(moneyX + 5.0F*moneyWidth);
		metalSprite.setY(moneyY);
		
		cosmocoinLine.setBounds(
				cosmocoinLine.getX(),
				cosmocoinLine.getY(),
				((float)INF.money/(float)INF.moneyFull)*0.825F*line.getWidth() + 0.005F*width,
				cosmocoinLine.getHeight()
				);
		fuelLine.setBounds(
				fuelLine.getX(),
				fuelLine.getY(),
				((float)INF.fuel/(float)INF.fuelFull)*0.825F*line.getWidth() + 0.005F*width,
				fuelLine.getHeight()
				);
		metalLine.setBounds(
				metalLine.getX(),
				metalLine.getY(),
				((float)INF.metal/(float)INF.metalFull)*0.825F*line.getWidth() + 0.005F*width,
				metalLine.getHeight()
				);
		
		line.setX(moneyX + 1.15F*moneyWidth);
		line.setY(moneyY);
		cosmocoinLine.setX(moneySprite.getX() + 1.45F*moneySprite.getWidth());
		cosmocoinLine.setY(moneySprite.getY() + 0.25F*moneySprite.getHeight());
		fuelLine.setX(fuelSprite.getX() + 1.45F*fuelSprite.getWidth());
		fuelLine.setY(fuelSprite.getY() + 0.25F*fuelSprite.getHeight());
		metalLine.setX(metalSprite.getX() + 1.45F*metalSprite.getWidth());
		metalLine.setY(metalSprite.getY() + 0.25F*metalSprite.getHeight());
	}	

	private void moneyInit(){
		moneySprite = new Sprite(new Texture("objects/cosmocoin.png"));
		fuelSprite = new Sprite(new Texture("objects/fuel.png"));
		metalSprite = new Sprite(new Texture("objects/metal.png"));
		
		line = new Sprite(new Texture("objects/line.png"));
		cosmocoinLine = new Sprite(new Texture("objects/cosmocoinLine.png"));
		fuelLine = new Sprite(new Texture("objects/fuelLine.png"));
		metalLine = new Sprite(new Texture("objects/metalLine.png"));
		
		moneyWidth = 0.05F*width;
		moneyHeight = moneyWidth;
		moneyX = camera.position.x - moneyWidth - (width/2 - 0.065F*width);
		moneyY = camera.position.y - moneyHeight + (height/2 - 0.015F*height);
		
		moneySprite.setBounds(moneyX, moneyY, moneyWidth, moneyHeight);
		fuelSprite.setBounds(moneyX + moneyWidth, moneyY, moneyWidth, moneyHeight);
		metalSprite.setBounds(moneyX + 2.0F*moneyWidth, moneyY, moneyWidth, moneyHeight);
		
		line.setBounds(moneyX + 1.1F*moneyWidth, moneyY - 2.1F*moneyHeight, moneyWidth/0.28125F, moneyWidth);
		cosmocoinLine.setBounds(moneySprite.getX() + 1.5F*moneySprite.getWidth(), moneySprite.getY() + moneySprite.getHeight(), 0.5F*moneyWidth, 0.5F*moneyHeight);
		fuelLine.setBounds(fuelSprite.getX() + 1.5F*fuelSprite.getWidth(), fuelSprite.getY() + fuelSprite.getHeight(), 0.5F*moneyWidth, 0.5F*moneyHeight);
		metalLine.setBounds(metalLine.getX() + 1.5F*metalLine.getWidth(), metalLine.getY() + metalLine.getHeight(), 0.5F*moneyWidth, 0.5F*moneyHeight);
		
	}
	private void fuelFactoryInit(){
		fuelFactoryTexture = new Texture("objects/fuelFactory_1.png");
		fuelFactorySprite = new Sprite(fuelFactoryTexture);
		fuelFactoryTentionIndex = (float)fuelFactorySprite.getWidth()/fuelFactorySprite.getHeight();
		fuelFactoryWidth = 0.25F*width;
		fuelFactoryHeight = (float)fuelFactoryWidth/fuelFactoryTentionIndex;
		fuelFactoryX = 0.058F*backgroundSprite.getWidth();
		fuelFactoryY = 0.335F*backgroundSprite.getHeight();
		fuelFactorySprite.setBounds(fuelFactoryX, fuelFactoryY, fuelFactoryWidth, fuelFactoryHeight);
		schFuel = "objects/fuelFactory_1.png";
	}
	private void coinFactoryInit(){
		coinFactoryTexture = new Texture("objects/coinFactory_1.png");
		coinFactorySprite = new Sprite(coinFactoryTexture);
		coinFactoryTentionIndex = (float)coinFactorySprite.getWidth()/coinFactorySprite.getHeight();
		coinFactoryWidth = 0.25F*width;
		coinFactoryHeight = (float)coinFactoryWidth/coinFactoryTentionIndex;
		coinFactoryX = 0.058F*backgroundSprite.getWidth();
		coinFactoryY = 0.245F*backgroundSprite.getHeight();
		coinFactorySprite.setBounds(coinFactoryX, coinFactoryY, coinFactoryWidth, coinFactoryHeight);
		schCoin = "objects/coinFactory_1.png";
	}
	private void metalFactoryInit(){
		metalFactoryTexture = new Texture("objects/metalFactory/metalFactory_1.png");
		metalFactorySprite = new Sprite(metalFactoryTexture);
		metalFactoryTentionIndex = (float)metalFactorySprite.getWidth()/metalFactorySprite.getHeight();
		metalFactoryWidth = 0.25F*width;
		metalFactoryHeight = (float)metalFactoryWidth/metalFactoryTentionIndex;
		metalFactoryX = 0.07F*backgroundSprite.getWidth();
		metalFactoryY = 0.1665F*backgroundSprite.getHeight();
		metalFactorySprite.setBounds(metalFactoryX, metalFactoryY, metalFactoryWidth, metalFactoryHeight);
		schMetal = "objects/metalFactory/metalFactory_1.png";
	}
	private void rocketsPreInit(){
		rocketBall = new SFButtonS("rockets/rocketBall", 0.05F*height, 0.115F*width, 0.5F*height, 1.0F);
		rocketCircle = new SFButtonS("rockets/rocketCircle", 0.05F*height, 0.265F*width, 0.5F*height, 1.0F);
		rocketBasic = new SFButtonS("rockets/rocketBasic", 0.059262771F*height, 0.415F*width, 0.5F*height, 1.0F);
		rocketKinetic = new SFButtonS("rockets/rocketKinetic", 0.03884503531366846697133361030328F*height, 0.135F*width, 0.5F*height, 1.0F);
	}
	private void rocketsInit(){
		/***/
		rocketBall.setMode(false);
		rocketBall.setX(0.6535F*backgroundSprite.getWidth());
		rocketBall.setY(0.6775F*backgroundSprite.getHeight());
		rocketBall.setWidth(0.04F*backgroundSprite.getHeight());
		rocketBall.setHeight(rocketBall.getWidth()/rocketBall.getAspectRatio());		
		/***/
		rocketCircle.setMode(false);
		rocketCircle.setX(0.6535F*backgroundSprite.getWidth());
		rocketCircle.setY(0.6775F*backgroundSprite.getHeight());
		rocketCircle.setWidth(0.04F*backgroundSprite.getHeight());
		rocketCircle.setHeight(rocketCircle.getWidth()/rocketCircle.getAspectRatio());		
		/***/
		rocketBasic.setMode(false);
		rocketBasic.setX(0.6485F*backgroundSprite.getWidth());
		rocketBasic.setY(0.6825F*backgroundSprite.getHeight());
		rocketBasic.setWidth(0.05F*backgroundSprite.getHeight());
		rocketBasic.setHeight(rocketBasic.getWidth()/rocketBasic.getAspectRatio());		
		/***/
		rocketKinetic.setMode(false);
		rocketKinetic.setX(0.6485F*backgroundSprite.getWidth());
		rocketKinetic.setY(0.685F*backgroundSprite.getHeight());
		rocketKinetic.setWidth(0.05F*backgroundSprite.getHeight());
		rocketKinetic.setHeight(rocketKinetic.getWidth()/rocketKinetic.getAspectRatio());			
		/***/
		rocketFire = new Sprite(new Texture("rockets/fire_1.png"));
		schFire = "rockets/fire_1.png";
		if(rocket != null){
			if(rocket.equals(INF.rocketBall)){
				rocketFire.setBounds(rocketS.getSprite().getX()+0.3F*rocketS.getSprite().getWidth(),
						rocketS.getSprite().getY()-1.01F*rocketS.getSprite().getHeight(),
						0.4F*rocketS.getSprite().getWidth(),
						0.8F*rocketS.getSprite().getWidth());
			}else if(rocket.equals(INF.rocketCircle)){
				rocketFire.setBounds(rocketS.getSprite().getX()+0.3F*rocketS.getSprite().getWidth(),
						rocketS.getSprite().getY()+0.01F*rocketS.getSprite().getHeight(),
						0.4F*rocketS.getSprite().getWidth(),
						0.8F*rocketS.getSprite().getWidth());
			}else if(rocket.equals(INF.rocketBasic)){
				rocketFire.setBounds(rocketS.getSprite().getX()+0.05F*rocketS.getSprite().getWidth(),
						rocketS.getSprite().getY()+0.01F*rocketS.getSprite().getHeight(),
						0.4F*rocketS.getSprite().getWidth(),
						0.8F*rocketS.getSprite().getWidth());
			}else if(rocket.equals(INF.rocketKinetic)){
				rocketFire.setBounds(rocketS.getSprite().getX()+0.05F*rocketS.getSprite().getWidth(),
						rocketS.getSprite().getY()+0.01F*rocketS.getSprite().getHeight(),
						0.7F*rocketS.getSprite().getWidth(),
						1.4F*rocketS.getSprite().getWidth());
			}
		}
	}
	
	private void resourcesCheck(){
		if(INF.elapsedTime%(3600/INF.moneyAmount) == 0){
			if(INF.money<INF.moneyFull) cash.play();
			INF.money++;
		}
		if(INF.elapsedTime%(3600/INF.fuelAmount) == 60){
			if(INF.fuel<INF.fuelFull)bubble.play();
			INF.fuel++;
		}
		if(INF.elapsedTime%(3600/INF.metalAmount) == 120){
			if(INF.metal<INF.metalFull)anvil.play();
			INF.metal++;
		}
		if(INF.money>INF.moneyFull) INF.money = INF.moneyFull;
		if(INF.fuel>INF.fuelFull) INF.fuel = INF.fuelFull;
		if(INF.metal>INF.metalFull) INF.metal = INF.metalFull;
	}
	
	private void touchUpdate(){
		if(!INF.isLaunch){
			if(prevDragX != 0.0F && SFlightInputController.touchDragX != 0.0F)
				camera.position.x -= SFlightInputController.touchDragX - prevDragX;
			if(prevDragY != 0.0F && SFlightInputController.touchDragY != 0.0F)
				camera.position.y += SFlightInputController.touchDragY - prevDragY;
			prevDragX = SFlightInputController.touchDragX;
			prevDragY = SFlightInputController.touchDragY;
			
			if(camera.position.x < backgroundSprite.getX() + width/2)
				camera.position.set(new Vector3(backgroundSprite.getX() + width/2, camera.position.y, 0));
			if(camera.position.y < backgroundSprite.getY() + height/2)
				camera.position.set(new Vector3(camera.position.x, backgroundSprite.getY() + height/2, 0));
			if(camera.position.x > (backgroundSprite.getX() + backgroundSprite.getWidth()) - width/2)
				camera.position.set(new Vector3((backgroundSprite.getX() + backgroundSprite.getWidth()) - width/2, camera.position.y, 0));
			if(camera.position.y > (backgroundSprite.getY() + backgroundSprite.getHeight()) - height/2)
				camera.position.set(new Vector3(camera.position.x, (backgroundSprite.getY() + backgroundSprite.getHeight()) - height/2, 0));
		}else{
			camera.position.set(new Vector3(rocketX, rocketY, 0));
		}
	}
	private void drawButtons(){
		btnMN.setCoordinates();
		btnMN.getSprite().draw(batch);
		if(controller.isOnGameStatic(btnMN.getX(), btnMN.getY(), btnMN.getWidth(), btnMN.getHeight())){
			if(!INF.isLaunch)
				btnMN.setMode(true);
			if(!INF.lngRussian){
				textBtn.draw(batch, "Main", btnMN.getX() + 0.225F*btnMN.getWidth(), btnMN.getY() + btnMN.getHeight() - 0.6F*textBtn.getCapHeight());
				textBtn.draw(batch, "menu", btnMN.getX() + 0.175F*btnMN.getWidth(), btnMN.getY() + btnMN.getHeight() - 1.85F*textBtn.getCapHeight());
			}else{
				textBtn.draw(batch, "Главное", btnMN.getX() + 0.075F*btnMN.getWidth(), btnMN.getY() + btnMN.getHeight() - 1.1F*textBtn.getCapHeight());
				textBtn.draw(batch, "меню", btnMN.getX() + 0.2F*btnMN.getWidth(), btnMN.getY() + btnMN.getHeight() - 2.6F*textBtn.getCapHeight());
			}
		}else{
			btnMN.setMode(false);
			if(!INF.lngRussian){
				textBtn.draw(batch, "Main", btnMN.getX() + 0.225F*btnMN.getWidth(), btnMN.getY() + btnMN.getHeight() - 0.5F*textBtn.getCapHeight());
				textBtn.draw(batch, "menu", btnMN.getX() + 0.175F*btnMN.getWidth(), btnMN.getY() + btnMN.getHeight() - 1.75F*textBtn.getCapHeight());
			}else{
				textBtn.draw(batch, "Главное", btnMN.getX() + 0.075F*btnMN.getWidth(), btnMN.getY() + btnMN.getHeight() - 1.0F*textBtn.getCapHeight());
				textBtn.draw(batch, "меню", btnMN.getX() + 0.2F*btnMN.getWidth(), btnMN.getY() + btnMN.getHeight() - 2.5F*textBtn.getCapHeight());
			}
		}
		btnPlus.setCoordinates();
		btnPlus.getSprite().draw(batch);
		if(controller.isOnGameStatic(btnPlus.getX(), btnPlus.getY(), btnPlus.getWidth(), btnPlus.getHeight())){
			if(!INF.isLaunch)
				btnPlus.setMode(true);
		}else{
			btnPlus.setMode(false);
		}
	}
	private void drawBuildings(){
		//Hangar
		if(controller.isOnGame(hangar.getX(), hangar.getY(), hangar.getWidth(), hangar.getHeight())){
			hangar.setMode(true);
		}else{
			hangar.setMode(false);
		}
		hangar.getSprite().draw(batch);
		//Analytic center
		if(controller.isOnGame(analytic.getX(), analytic.getY(), analytic.getWidth(), analytic.getHeight())){
			analytic.setMode(true);
		}else{
			analytic.setMode(false);
		}
		analytic.getSprite().draw(batch);
		//Control tower
		if(controller.isOnGame(control.getX(), control.getY(), control.getWidth(), control.getHeight())){
			control.setMode(true);
		}else{
			control.setMode(false);
		}
		control.getSprite().draw(batch);
		//Armory
		if(controller.isOnGame(gunStore.getX(), gunStore.getY(), gunStore.getWidth(), gunStore.getHeight())){
			gunStore.setMode(true);
		}else{
			gunStore.setMode(false);
		}
		gunStore.getSprite().draw(batch);
		/***/
		if(INF.elapsedTime % 30 == 0){
			fuelFactorySprite.setTexture(new Texture(schFuel));
			if(schFuel.equals("objects/fuelFactory_1.png")) schFuel = "objects/fuelFactory_2.png";
			else if(schFuel.equals("objects/fuelFactory_2.png")) schFuel = "objects/fuelFactory_3.png";
			else if(schFuel.equals("objects/fuelFactory_3.png")) schFuel = "objects/fuelFactory_4.png";
			else if(schFuel.equals("objects/fuelFactory_4.png")) schFuel = "objects/fuelFactory_1.png";
		}
		for(int i=0;i<(int)INF.fuelAmount;i++){
			fuelFactorySprite.setX(fuelFactoryX + (1.2F*i)*fuelFactoryWidth);
			fuelFactorySprite.draw(batch);
		}
		/***/
		if(INF.elapsedTime % 15 == 0){
			coinFactorySprite.setTexture(new Texture(schCoin));
			if(schCoin.equals("objects/coinFactory_1.png")) schCoin = "objects/coinFactory_2.png";
			else if(schCoin.equals("objects/coinFactory_2.png")) schCoin = "objects/coinFactory_3.png";
			else if(schCoin.equals("objects/coinFactory_3.png")) schCoin = "objects/coinFactory_1.png";
		}
		for(int i=0;i<(int)INF.moneyAmount;i++){
			coinFactorySprite.setX(coinFactoryX + (1.2F*i)*coinFactoryWidth);
			coinFactorySprite.draw(batch);
		}
		/***/
		if(INF.elapsedTime % 6 == 0){
			metalFactorySprite.setTexture(new Texture(schMetal));
			if(schMetal.equals("objects/metalFactory/metalFactory_1.png")) schMetal = "objects/metalFactory/metalFactory_2.png";
			else if(schMetal.equals("objects/metalFactory/metalFactory_2.png")) schMetal = "objects/metalFactory/metalFactory_3.png";
			else if(schMetal.equals("objects/metalFactory/metalFactory_3.png")) schMetal = "objects/metalFactory/metalFactory_4.png";
			else if(schMetal.equals("objects/metalFactory/metalFactory_4.png")) schMetal = "objects/metalFactory/metalFactory_5.png";
			else if(schMetal.equals("objects/metalFactory/metalFactory_5.png")) schMetal = "objects/metalFactory/metalFactory_6.png";
			else if(schMetal.equals("objects/metalFactory/metalFactory_6.png")) schMetal = "objects/metalFactory/metalFactory_7.png";
			else if(schMetal.equals("objects/metalFactory/metalFactory_7.png")) schMetal = "objects/metalFactory/metalFactory_8.png";
			else if(schMetal.equals("objects/metalFactory/metalFactory_8.png")) schMetal = "objects/metalFactory/metalFactory_9.png";
			else if(schMetal.equals("objects/metalFactory/metalFactory_9.png")) schMetal = "objects/metalFactory/metalFactory_10.png";
			else if(schMetal.equals("objects/metalFactory/metalFactory_10.png")) schMetal = "objects/metalFactory/metalFactory_11.png";
			else if(schMetal.equals("objects/metalFactory/metalFactory_11.png")) schMetal = "objects/metalFactory/metalFactory_12.png";
			else if(schMetal.equals("objects/metalFactory/metalFactory_12.png")) schMetal = "objects/metalFactory/metalFactory_13.png";
			else if(schMetal.equals("objects/metalFactory/metalFactory_13.png")) schMetal = "objects/metalFactory/metalFactory_14.png";
			else if(schMetal.equals("objects/metalFactory/metalFactory_14.png")) schMetal = "objects/metalFactory/metalFactory_15.png";
			else if(schMetal.equals("objects/metalFactory/metalFactory_15.png")) schMetal = "objects/metalFactory/metalFactory_16.png";
			else if(schMetal.equals("objects/metalFactory/metalFactory_16.png")) schMetal = "objects/metalFactory/metalFactory_17.png";
			else if(schMetal.equals("objects/metalFactory/metalFactory_17.png")) schMetal = "objects/metalFactory/metalFactory_18.png";
			else if(schMetal.equals("objects/metalFactory/metalFactory_18.png")) schMetal = "objects/metalFactory/metalFactory_19.png";
			else if(schMetal.equals("objects/metalFactory/metalFactory_19.png")) schMetal = "objects/metalFactory/metalFactory_20.png";
			else if(schMetal.equals("objects/metalFactory/metalFactory_20.png")) schMetal = "objects/metalFactory/metalFactory_21.png";
			else if(schMetal.equals("objects/metalFactory/metalFactory_21.png")) schMetal = "objects/metalFactory/metalFactory_22.png";
			else if(schMetal.equals("objects/metalFactory/metalFactory_22.png")) schMetal = "objects/metalFactory/metalFactory_23.png";
			else if(schMetal.equals("objects/metalFactory/metalFactory_23.png")) schMetal = "objects/metalFactory/metalFactory_24.png";
			else if(schMetal.equals("objects/metalFactory/metalFactory_24.png")) schMetal = "objects/metalFactory/metalFactory_1.png";
		}
		for(int i=0;i<(int)INF.metalAmount;i++){
			metalFactorySprite.setX(metalFactoryX + (1.2F*i)*metalFactoryWidth);
			metalFactorySprite.draw(batch);
		}
		/***/
	}
	private void drawMoney(){
		for(float i=0.25F;i<3.25F;i+=1.0F){
			line.setX(moneyX + (i*5.0F)*moneyWidth);
			line.draw(batch);
		}
		moneySprite.draw(batch);
		fuelSprite.draw(batch);
		metalSprite.draw(batch);
		cosmocoinLine.draw(batch);
		fuelLine.draw(batch);
		metalLine.draw(batch);
		text.draw(batch, ":     " + Long.toString((int)(INF.money)) + "/" + Long.toString((int)(INF.moneyFull)), moneySprite.getX() + 1.05F*moneySprite.getWidth(), moneySprite.getY() + 0.825F*moneySprite.getHeight() - text.getCapHeight()/1.4F);
		text.draw(batch, ":     " + Long.toString((int)(INF.fuel)) + "/" + Long.toString((int)(INF.fuelFull)), fuelSprite.getX() + 1.05F*fuelSprite.getWidth(), fuelSprite.getY() + 0.825F*moneySprite.getHeight() - text.getCapHeight()/1.4F);
		text.draw(batch, ":     " + Long.toString((int)(INF.metal)) + "/" + Long.toString((int)(INF.metalFull)), metalSprite.getX() + 1.05F*metalSprite.getWidth(), metalSprite.getY() + 0.825F*moneySprite.getHeight() - text.getCapHeight()/1.4F);
	}
	private void drawRockets(){
		if(rocket != null){
			if(INF.isLaunch) rocketFire.draw(batch);
			rocketS.getSprite().draw(batch);
		}
	}
	private void drawFlight(){
		speed *= 1.01F;
		if(rocketS.getSprite().getY() <= camera.position.y + height){
			rocketS.getSprite().setY(rocketS.getSprite().getY() + speed);
			if(rocket != null){
				if(rocket.equals(INF.rocketBall)){
					rocketFire.setX(rocketS.getSprite().getX()+0.3F*rocketS.getSprite().getWidth());
					rocketFire.setY(rocketS.getSprite().getY()+0.2F*rocketS.getSprite().getHeight());
				}else if(rocket.equals(INF.rocketCircle)){
					rocketFire.setX(rocketS.getSprite().getX()+0.3F*rocketS.getSprite().getWidth());
					rocketFire.setY(rocketS.getSprite().getY()+0.2F*rocketS.getSprite().getHeight());
				}else if(rocket.equals(INF.rocketBasic)){
					rocketFire.setX(rocketS.getSprite().getX()+0.3F*rocketS.getSprite().getWidth());
					rocketFire.setY(rocketS.getSprite().getY()-0.15F*rocketS.getSprite().getHeight());
				}else if(rocket.equals(INF.rocketKinetic)){
					rocketFire.setX(rocketS.getSprite().getX()+0.15F*rocketS.getSprite().getWidth());
					rocketFire.setY(rocketS.getSprite().getY()-0.3F*rocketS.getSprite().getHeight());
				}
			}
			if(INF.elapsedTime%3==0){
				if(schFire.equals("rockets/fire_1.png")){
					schFire = "rockets/fire_2.png";
				}else{
					schFire = "rockets/fire_1.png";
				}
			}
			rocketFire.setTexture(new Texture(schFire));
		}else{
			isTransGame = true;
			if(alp>1.0F){
				if(planet.equals(INF.planetLoon)){
					game.setScreen(new LoonScreen(game));
				}else if(planet.equals(INF.planetEmion)){
					game.setScreen(new EmionScreen(game));
				}
				INF.isLaunch = false;
				alp = 1.0F;
				this.dispose();
			}else{
				blackAlpha.setAlpha(alp);
				alp+=0.05F;
			}
		}
	}
	
	private void buttonListener(){
		if(!INF.isLaunch){
			if(controller.isClickedGame(btnMN.getX(), btnMN.getY(), btnMN.getWidth(), btnMN.getHeight()) || isTransGame){
				isTransGame = true;
				if(alp>1.0F){
					game.setScreen(new MainMenu(game));
					alp = 1.0F;
					this.dispose();
				}else{
					blackAlpha.setAlpha(alp);
					alp+=0.05F;
				}
			}
			if(controller.isClickedGame(hangar.getX(), hangar.getY(), hangar.getWidth(), hangar.getHeight())){
				game.setScreen(new HangarScreen(game));
				this.dispose();
			}
			if(controller.isClickedGame(analytic.getX(), analytic.getY(), analytic.getWidth(), analytic.getHeight())){
				game.setScreen(new AnalyticCentreScreen(game));
				this.dispose();
			}
			if(controller.isClickedGame(control.getX(), control.getY(), control.getWidth(), control.getHeight())){
				game.setScreen(new ControlCentreScreen(game));
				this.dispose();
			}
			if(controller.isClickedGame(gunStore.getX(), gunStore.getY(), gunStore.getWidth(), gunStore.getHeight())){
				game.setScreen(new ArmoryScreen(game));
				this.dispose();
			}
			if(controller.isClickedGame(btnPlus.getX(), btnPlus.getY(), btnPlus.getWidth(), btnPlus.getHeight())){
				INF.money += 5;
				INF.fuel += 5;
				INF.metal += 5;
			}
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
	
	@Override
	public void dispose() {
		flight.stop();
		flight.dispose();
		text.dispose();
		textBtn.dispose();
		backgroundTexture.dispose();
		hangar.getTexture().dispose();
		analytic.getTexture().dispose();
		control.getTexture().dispose();
		gunStore.getTexture().dispose();
		fuelFactoryTexture.dispose();
		coinFactoryTexture.dispose();
		metalFactoryTexture.dispose();
		btnMN.getSprite().getTexture().dispose();
		btnPlus.getSprite().getTexture().dispose();
		moneySprite.getTexture().dispose();
		fuelSprite.getTexture().dispose();
		metalSprite.getTexture().dispose();
		cosmocoinLine.getTexture().dispose();
		fuelLine.getTexture().dispose();
		metalLine.getTexture().dispose();
		blackAlpha.getTexture().dispose();
		rocketFire.getTexture().dispose();
		rocketBall.getSprite().getTexture().dispose();
		rocketCircle.getSprite().getTexture().dispose();
		rocketBasic.getSprite().getTexture().dispose();
		rocketKinetic.getSprite().getTexture().dispose();
		cash.dispose();
		bubble.dispose();
		anvil.dispose();
		batch.dispose();
	}

}