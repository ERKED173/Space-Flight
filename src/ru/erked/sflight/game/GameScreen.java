package ru.erked.sflight.game;

import com.badlogic.gdx.Game;
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

import ru.erked.sflight.controllers.SFlightInputController;
import ru.erked.sflight.menu.MainMenu;
import ru.erked.sflight.random.InfoAndStats;
import ru.erked.sflight.tech.SFButtonA;
import ru.erked.sflight.tech.SFButtonS;

public class GameScreen implements Screen{

	final String FONT_CHARS_RU = "абвгдежзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
	private static final float width = Gdx.graphics.getWidth();
	private static final float height = Gdx.graphics.getHeight();
	
	private Game game;
	public static OrthographicCamera camera;
	private SpriteBatch batch;
	private SFlightInputController controller;
	
	private Music cash = Gdx.audio.newMusic(Gdx.files.internal("sounds/misc/cash.wav"));
	private Music bubble = Gdx.audio.newMusic(Gdx.files.internal("sounds/misc/bubble.wav"));
	private Music anvil = Gdx.audio.newMusic(Gdx.files.internal("sounds/misc/anvil.wav"));
	
	public static boolean isFromMenu = true;
	
//Background
	private Texture backgroundTexture; //Текстура фона
	public static Sprite backgroundSprite; //Спрайт фона
	private float backgroundX;
	private float backgroundY;
	public static final float backgroundTentionIndex = (float)2*width/2560.0F;
	
//Hangar
	private SFButtonS hangar;
//Analytic centre
	private SFButtonS analytic;	
//Control tower
	private SFButtonS control;	
	
	//Научный_центр_1
	private Texture scienceCentre1Texture;
	public static Sprite scienceCentre1Sprite;
	private float scienceCentre1Width;
	private float scienceCentre1Height;
	private float scienceCentre1X;
	private float scienceCentre1Y;
	private float scienceCentre1TentionIndex;
	//Научный_центр_2
	private Texture scienceCentre2Texture;
	public static Sprite scienceCentre2Sprite;
	private float scienceCentre2Width;
	private float scienceCentre2Height;
	private float scienceCentre2X;
	private float scienceCentre2Y;
	
	//Нефтедобыча
	private Texture fuelFactoryTexture;
	public static Sprite fuelFactorySprite;
	private float fuelFactoryWidth;
	private float fuelFactoryHeight;
	private float fuelFactoryX;
	private float fuelFactoryY;
	private float fuelFactoryTentionIndex;
	//Майнинг
	private Texture coinFactoryTexture;
	public static Sprite coinFactorySprite;
	private float coinFactoryWidth;
	private float coinFactoryHeight;
	private float coinFactoryX;
	private float coinFactoryY;
	private float coinFactoryTentionIndex;
	//Металодобыча
	private Texture metalFactoryTexture;
	public static Sprite metalFactorySprite;
	private float metalFactoryWidth;
	private float metalFactoryHeight;
	private float metalFactoryX;
	private float metalFactoryY;
	private float metalFactoryTentionIndex;
	
//Для прокрутки
	private static float prevDragX;
	private static float prevDragY;
	
//Копка "Main Menu"
	private SFButtonA btnMN;
	
//Иконка КосмоКоинов
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
	
	private Music launchSoundPath = Gdx.audio.newMusic(Gdx.files.internal("sounds/misc/WavLibraryNet_Sound6386.mp3"));
	
	private Sprite blackAlpha = new Sprite(new Texture("objects/black.png"));
	private float alp = 1.0F;
	private boolean isTransGame;
	
	private static String schFuel;
	private static String schCoin;
	private static String schMetal;
	
	public GameScreen(Game game){
		this.game = game;
	}
	
	@Override
	public void show() {
		
		batch = new SpriteBatch();
		controller = new SFlightInputController();
		MainMenu.music.play();
		
		cash.setVolume(1.0F);
		bubble.setVolume(1.0F);
		anvil.setVolume(1.0F);
		
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
		if(InfoAndStats.lngRussian){
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
		
		scienceCentreInit();
		moneyInit();
		fuelFactoryInit();
		coinFactoryInit();
		metalFactoryInit();
		
		btnMN = new SFButtonA("btns/button", 0.132F*width, width - 0.14F*width, 0.01F*height, camera);
		hangar = new SFButtonS("objects/angar", 0.5F*width, 0.150F*backgroundSprite.getWidth(), 0.625F*backgroundSprite.getHeight());		
		analytic = new SFButtonS("objects/analytic", 0.2F*width, 0.739F*backgroundSprite.getWidth(), 0.785F*backgroundSprite.getHeight());		
		control = new SFButtonS("objects/control", 0.125F*width, 0.469F*backgroundSprite.getWidth(), 0.665F*backgroundSprite.getHeight());		
		
		isTransGame = false;
		blackAlpha.setBounds(0.0F, 0.0F, backgroundSprite.getWidth(), backgroundSprite.getHeight());
		blackAlpha.setAlpha(1.0F);
	}

	@Override
	public void render(float delta) {
		InfoAndStats.elapsedTime++;
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
		
		cloudsCoords(delta);
		moneyCoords();
		
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		
		/**Drawing objects*/
		batch.begin();
		
		backgroundSprite.draw(batch);
		
		drawBuildings();
		drawClouds();
		drawButtons();
		drawMoney();
		
		blackAlpha.draw(batch);
		
		batch.end();
		/**Drawing objects*/
		
		buttonListener();
		
	}
	
	private void cloudsCoords(float delta){
		//Установка коодрдинат для облаков
		/**TODO: Облащка 1*/
		//Установка коодрдинат для облаков
	}
	private void moneyCoords(){
		//Установка координат иконки КосмоКоинов*/
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
				((float)InfoAndStats.money/(float)InfoAndStats.moneyFull)*0.8F*line.getWidth() + 0.005F*width,
				cosmocoinLine.getHeight()
				);
		fuelLine.setBounds(
				fuelLine.getX(),
				fuelLine.getY(),
				((float)InfoAndStats.fuel/(float)InfoAndStats.fuelFull)*0.8F*line.getWidth() + 0.005F*width,
				fuelLine.getHeight()
				);
		metalLine.setBounds(
				metalLine.getX(),
				metalLine.getY(),
				((float)InfoAndStats.metal/(float)InfoAndStats.metalFull)*0.8F*line.getWidth() + 0.005F*width,
				metalLine.getHeight()
				);
		
		line.setX(moneyX + 1.15F*moneyWidth);
		line.setY(moneyY);
		cosmocoinLine.setX(moneySprite.getX() + 1.5F*moneySprite.getWidth());
		cosmocoinLine.setY(moneySprite.getY() + 0.25F*moneySprite.getHeight());
		fuelLine.setX(fuelSprite.getX() + 1.5F*fuelSprite.getWidth());
		fuelLine.setY(fuelSprite.getY() + 0.25F*fuelSprite.getHeight());
		metalLine.setX(metalSprite.getX() + 1.5F*metalSprite.getWidth());
		metalLine.setY(metalSprite.getY() + 0.25F*metalSprite.getHeight());
	}	

	private void scienceCentreInit(){
		//Научный_Центр_1\\
		scienceCentre1Texture = new Texture("objects/scienceCentre1.png");
		scienceCentre1Sprite = new Sprite(scienceCentre1Texture);
		scienceCentre1TentionIndex = (float)scienceCentre1Sprite.getWidth()/scienceCentre1Sprite.getHeight();
		scienceCentre1Width = 0.5F*width;
		scienceCentre1Height = (float)scienceCentre1Width/scienceCentre1TentionIndex;
		scienceCentre1X = 0.359F*backgroundSprite.getWidth();
		scienceCentre1Y = 0.45F*backgroundSprite.getHeight();
		scienceCentre1Sprite.setBounds(scienceCentre1X, scienceCentre1Y, scienceCentre1Width, scienceCentre1Height);
		//Научный_Центр_2\\
		scienceCentre2Texture = new Texture("objects/scienceCentre2.png");
		scienceCentre2Sprite = new Sprite(scienceCentre2Texture);
		scienceCentre2Width = 0.73818897637795275590551181102363F*width;
		scienceCentre2Height = (float)scienceCentre2Width/scienceCentre1TentionIndex;
		scienceCentre2X = 0.359F*backgroundSprite.getWidth() - 0.16133333333333333333333333333334F*scienceCentre2Width;
		scienceCentre2Y = 0.45F*backgroundSprite.getHeight() - 0.16153846153846153846153846153846F*scienceCentre2Height;
		scienceCentre2Sprite.setBounds(scienceCentre2X, scienceCentre2Y, scienceCentre2Width, scienceCentre2Height);
	}	
	private void moneyInit(){
		//Иконки ресурсов\\
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
		cosmocoinLine.setBounds(moneySprite.getX() + moneySprite.getWidth() + moneySprite.getWidth()*0.5F, moneySprite.getY() + moneySprite.getHeight(), 0.5F*moneyWidth, 0.5F*moneyHeight);
		fuelLine.setBounds(fuelSprite.getX() + fuelSprite.getWidth() + fuelSprite.getWidth()*0.5F, fuelSprite.getY() + fuelSprite.getHeight(), 0.5F*moneyWidth, 0.5F*moneyHeight);
		metalLine.setBounds(metalLine.getX() + metalLine.getWidth() + metalLine.getWidth()*0.5F, metalLine.getY() + metalLine.getHeight(), 0.5F*moneyWidth, 0.5F*moneyHeight);
		
	}

	private void fuelFactoryInit(){
		//Нефтедобыча\\
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
		//Нефтедобыча\\
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
		//Нефтедобыча\\
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
	
	private void resourcesCheck(){
		if(InfoAndStats.elapsedTime%(3600/InfoAndStats.moneyAmount) == 0){
			if(InfoAndStats.money<InfoAndStats.moneyFull) cash.play();
			InfoAndStats.money++;
		}
		if(InfoAndStats.elapsedTime%(3600/InfoAndStats.fuelAmount) == 60){
			if(InfoAndStats.fuel<InfoAndStats.fuelFull)bubble.play();
			InfoAndStats.fuel++;
		}
		if(InfoAndStats.elapsedTime%(3600/InfoAndStats.metalAmount) == 120){
			if(InfoAndStats.metal<InfoAndStats.metalFull)anvil.play();
			InfoAndStats.metal++;
		}
		if(InfoAndStats.money>InfoAndStats.moneyFull) InfoAndStats.money = InfoAndStats.moneyFull;
		if(InfoAndStats.fuel>InfoAndStats.fuelFull) InfoAndStats.fuel = InfoAndStats.fuelFull;
		if(InfoAndStats.metal>InfoAndStats.metalFull) InfoAndStats.metal = InfoAndStats.metalFull;
	}
	
	private void touchUpdate(){
		/**Обработка нажатия aka прокрутки*/
		if(prevDragX != 0.0F && SFlightInputController.touchDragX != 0.0F)
			camera.position.x -= SFlightInputController.touchDragX - prevDragX;
		if(prevDragY != 0.0F && SFlightInputController.touchDragY != 0.0F)
			camera.position.y += SFlightInputController.touchDragY - prevDragY;
		prevDragX = SFlightInputController.touchDragX;
		prevDragY = SFlightInputController.touchDragY;
		/**Обработка нажатия aka прокрутки*/
			
		/**Границы прокрутки*/
		//Выравниваем по левой грани*/
		if(camera.position.x < backgroundSprite.getX() + width/2)
			camera.position.set(new Vector3(backgroundSprite.getX() + width/2, camera.position.y, 0));
		//Выравниваем по нижней грани*/
		if(camera.position.y < backgroundSprite.getY() + height/2)
			camera.position.set(new Vector3(camera.position.x, backgroundSprite.getY() + height/2, 0));
		//Выравниваем по правой грани*/
		if(camera.position.x > (backgroundSprite.getX() + backgroundSprite.getWidth()) - width/2)
			camera.position.set(new Vector3((backgroundSprite.getX() + backgroundSprite.getWidth()) - width/2, camera.position.y, 0));
		//Выравниваем по верхней грани*/
		if(camera.position.y > (backgroundSprite.getY() + backgroundSprite.getHeight()) - height/2)
			camera.position.set(new Vector3(camera.position.x, (backgroundSprite.getY() + backgroundSprite.getHeight()) - height/2, 0));
		/**Границы прокрутки*/
	}
	private void drawClouds(){
		/**TODO: Облащка 3*/
	}
	private void drawButtons(){
		btnMN.setCoordinates();
		btnMN.getSprite().draw(batch);
		if(controller.isOnGameStatic(btnMN.getX(), btnMN.getY(), btnMN.getWidth(), btnMN.getHeight())){
			btnMN.setMode(true);
			if(!InfoAndStats.lngRussian){
				textBtn.draw(batch, "Main", btnMN.getX() + 0.225F*btnMN.getWidth(), btnMN.getY() + btnMN.getHeight() - 0.6F*textBtn.getCapHeight());
				textBtn.draw(batch, "menu", btnMN.getX() + 0.175F*btnMN.getWidth(), btnMN.getY() + btnMN.getHeight() - 1.85F*textBtn.getCapHeight());
			}else{
				textBtn.draw(batch, "Главное", btnMN.getX() + 0.075F*btnMN.getWidth(), btnMN.getY() + btnMN.getHeight() - 1.1F*textBtn.getCapHeight());
				textBtn.draw(batch, "меню", btnMN.getX() + 0.2F*btnMN.getWidth(), btnMN.getY() + btnMN.getHeight() - 2.6F*textBtn.getCapHeight());
			}
		}else{
			btnMN.setMode(false);
			if(!InfoAndStats.lngRussian){
				textBtn.draw(batch, "Main", btnMN.getX() + 0.225F*btnMN.getWidth(), btnMN.getY() + btnMN.getHeight() - 0.5F*textBtn.getCapHeight());
				textBtn.draw(batch, "menu", btnMN.getX() + 0.175F*btnMN.getWidth(), btnMN.getY() + btnMN.getHeight() - 1.75F*textBtn.getCapHeight());
			}else{
				textBtn.draw(batch, "Главное", btnMN.getX() + 0.075F*btnMN.getWidth(), btnMN.getY() + btnMN.getHeight() - 1.0F*textBtn.getCapHeight());
				textBtn.draw(batch, "меню", btnMN.getX() + 0.2F*btnMN.getWidth(), btnMN.getY() + btnMN.getHeight() - 2.5F*textBtn.getCapHeight());
			}
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
		//Analytic centre
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
		//Научный центр
		if(controller.isOnGame(scienceCentre1Sprite.getX(), scienceCentre1Sprite.getY(), scienceCentre1Width, scienceCentre1Height)){
			scienceCentre2Sprite.draw(batch);
		}else{
			scienceCentre1Sprite.draw(batch);
		}
		/***/
		if(InfoAndStats.elapsedTime % 30 == 0){
			fuelFactorySprite.setTexture(new Texture(schFuel));
			if(schFuel.equals("objects/fuelFactory_1.png")) schFuel = "objects/fuelFactory_2.png";
			else if(schFuel.equals("objects/fuelFactory_2.png")) schFuel = "objects/fuelFactory_3.png";
			else if(schFuel.equals("objects/fuelFactory_3.png")) schFuel = "objects/fuelFactory_4.png";
			else if(schFuel.equals("objects/fuelFactory_4.png")) schFuel = "objects/fuelFactory_1.png";
		}
		for(int i=0;i<(int)InfoAndStats.fuelAmount;i++){
			fuelFactorySprite.setX(fuelFactoryX + (1.2F*i)*fuelFactoryWidth);
			fuelFactorySprite.draw(batch);
		}
		/***/
		if(InfoAndStats.elapsedTime % 15 == 0){
			coinFactorySprite.setTexture(new Texture(schCoin));
			if(schCoin.equals("objects/coinFactory_1.png")) schCoin = "objects/coinFactory_2.png";
			else if(schCoin.equals("objects/coinFactory_2.png")) schCoin = "objects/coinFactory_3.png";
			else if(schCoin.equals("objects/coinFactory_3.png")) schCoin = "objects/coinFactory_1.png";
		}
		for(int i=0;i<(int)InfoAndStats.moneyAmount;i++){
			coinFactorySprite.setX(coinFactoryX + (1.2F*i)*coinFactoryWidth);
			coinFactorySprite.draw(batch);
		}
		/***/
		if(InfoAndStats.elapsedTime % 6 == 0){
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
		for(int i=0;i<(int)InfoAndStats.metalAmount;i++){
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
		text.draw(batch, ":    " + Long.toString((int)(InfoAndStats.money)) + "/" + Long.toString((int)(InfoAndStats.moneyFull)), moneySprite.getX() + 1.05F*moneySprite.getWidth(), moneySprite.getY() + 0.825F*moneySprite.getHeight() - text.getCapHeight()/1.4F);
		text.draw(batch, ":    " + Long.toString((int)(InfoAndStats.fuel)) + "/" + Long.toString((int)(InfoAndStats.fuelFull)), fuelSprite.getX() + 1.05F*fuelSprite.getWidth(), fuelSprite.getY() + 0.825F*moneySprite.getHeight() - text.getCapHeight()/1.4F);
		text.draw(batch, ":    " + Long.toString((int)(InfoAndStats.metal)) + "/" + Long.toString((int)(InfoAndStats.metalFull)), metalSprite.getX() + 1.05F*metalSprite.getWidth(), metalSprite.getY() + 0.825F*moneySprite.getHeight() - text.getCapHeight()/1.4F);
	}

	private void buttonListener(){
		//Слушатель нажатия на кнопку "Main Menu"*/
		if(controller.isClickedGame(btnMN.getX(), btnMN.getY(), btnMN.getWidth(), btnMN.getHeight()) || isTransGame){
			isTransGame = true;
			if(alp>1.0F){
				this.dispose();
				game.setScreen(new MainMenu(game));
				alp = 1.0F;
			}else{
				blackAlpha.setAlpha(alp);
				alp+=0.05F;
			}
		}
		//Слушатель нажатия на ангар*/
		if(controller.isClickedGame(hangar.getX(), hangar.getY(), hangar.getWidth(), hangar.getHeight())){
				game.setScreen(new AngarScreen(game));
				this.dispose();
		}
		//Слушатель нажатия на аналитический центр*/
		if(controller.isClickedGame(analytic.getX(), analytic.getY(), analytic.getWidth(), analytic.getHeight())){
			game.setScreen(new AnalyticCentreScreen(game));
			this.dispose();
		}
		//Слушатель нажатия на диспетчерскую вышку*/
		if(controller.isClickedGame(control.getX(), control.getY(), control.getWidth(), control.getHeight())){
			game.setScreen(new ControlCentreScreen(game));
			this.dispose();
		}
		//Слушатель нажатия на научный центр*/
		if(controller.isClickedGame(scienceCentre1Sprite.getX(), scienceCentre1Sprite.getY(), scienceCentre1Width, scienceCentre1Height)){
			game.setScreen(new ScienceCentreScreen(game));
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
		scienceCentre1Texture.dispose();
		scienceCentre2Texture.dispose();
	}
	
	@Override
	public void dispose() {
		text.dispose();
		launchSoundPath.stop();
		launchSoundPath.dispose();
		game.dispose();
		textureDispose();
		cash.dispose();
		bubble.dispose();
		anvil.dispose();
	}

}