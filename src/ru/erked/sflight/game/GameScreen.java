package ru.erked.sflight.game;

import java.util.Random;

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
import ru.erked.sflight.menu.TechnicScreen;
import ru.erked.sflight.random.ImgResDraw;
import ru.erked.sflight.random.InfoAndStats;
import ru.erked.sflight.random.ResetTheGame;

public class GameScreen implements Screen{

	final String FONT_CHARS_RU = "абвгдежзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
	private static final float width = Gdx.graphics.getWidth();
	private static final float height = Gdx.graphics.getHeight();
	private final Random rand = new Random();
	
	private Game game;
	private FlightScreen flight;
	public static OrthographicCamera camera;
	private SpriteBatch batch;
	private SFlightInputController controller;
	public static boolean isLaunch;
	private float launchTimer;
	private float speed;
	
	public static boolean isFromMenu = true;
	
//Фон
	private Texture backgroundTexture; //Текстура фона
	public static Sprite backgroundSprite; //Спрайт фона
	private float backgroundX;
	private float backgroundY;
	public static final float backgroundTentionIndex = (float)2*width/2560.0F;
	
//Ангар_1
	private Texture angar1Texture;
	public static Sprite angar1Sprite;
	private float angar1Width;
	private float angar1Height;
	private float angar1X;
	private float angar1Y;
	private float angar1TentionIndex;
//Ангар_2
	private Texture angar2Texture;
	public static Sprite angar2Sprite;
	private float angar2Width;
	private float angar2Height;
	private float angar2X;
	private float angar2Y;
	
//Аналитический_центр_1
	private Texture analytic1Texture;
	public static Sprite analytic1Sprite;
	private float analytic1Width;
	private float analytic1Height;
	private float analytic1X;
	private float analytic1Y;
	private float analytic1TentionIndex;
//Аналитический_центр_2
	private Texture analytic2Texture;
	public static Sprite analytic2Sprite;
	private float analytic2Width;
	private float analytic2Height;
	private float analytic2X;
	private float analytic2Y;
	
	//Диспетчерская_вышка_1
	private Texture control1Texture;
	public static Sprite control1Sprite;
	private float control1Width;
	private float control1Height;
	private float control1X;
	private float control1Y;
	private float control1TentionIndex;
	//Диспетчерская_вышка_2
	private Texture control2Texture;
	public static Sprite control2Sprite;
	private float control2Width;
	private float control2Height;
	private float control2X;
	private float control2Y;
	
	//Ангар_1
	private Texture scienceCentre1Texture;
	public static Sprite scienceCentre1Sprite;
	private float scienceCentre1Width;
	private float scienceCentre1Height;
	private float scienceCentre1X;
	private float scienceCentre1Y;
	private float scienceCentre1TentionIndex;
	//Ангар_2
	private Texture scienceCentre2Texture;
	public static Sprite scienceCentre2Sprite;
	private float scienceCentre2Width;
	private float scienceCentre2Height;
	private float scienceCentre2X;
	private float scienceCentre2Y;
	
//Облако_1
	private Texture cloud1Texture;
	public static Sprite cloud1Sprite;
	private float cloud1Width;
	private float cloud1Height;
	private float cloud1X;
	private static float cloud1PrevX;
	private float cloud1Y;
	private static float cloud1PrevY;
	private float cloud1TentionIndex;
//Облако_2
	private Texture cloud2Texture;
	public static Sprite cloud2Sprite;
	private float cloud2Width;
	private float cloud2Height;
	private float cloud2X;
	private static float cloud2PrevX;
	private float cloud2Y;
	private static float cloud2PrevY;
	private float cloud2TentionIndex;
//Ещё облака
	public static Sprite cloud3Sprite;
	private float cloud3X;
	private static float cloud3PrevX;
	private float cloud3Y;
	private static float cloud3PrevY;
	public static Sprite cloud4Sprite;
	private float cloud4X;
	private static float cloud4PrevX;
	private float cloud4Y;
	private static float cloud4PrevY;
	private float speed1 = 0.01F*rand.nextInt(100);
	private float speed2 = 0.01F*rand.nextInt(100);
	private float speed3 = 0.01F*rand.nextInt(100);
	private float speed4 = 0.01F*rand.nextInt(100);
	
//Для прокрутки
	private static float prevDragX;
	private static float prevDragY;
	
//Копка "Main Menu"
	private Texture backButtonInactive;
	private Texture backButtonActive;
	private Sprite backButtonInactiveSprite;
	private Sprite backButtonActiveSprite;
	private float backButtonX;
	private float backButtonY;
	private float backButtonWidth;
	private float backButtonHeight;
	public static float backButtonTentionIndex; //Соотношение сторон кнопки
	
//Копка "Next Day"
	private Texture nextDayButtonInactive;
	private Texture nextDayButtonActive;
	private Sprite nextDayButtonInactiveSprite;
	private Sprite nextDayButtonActiveSprite;
	private float nextDayButtonX;
	private float nextDayButtonY;
	private float nextDayButtonWidth;
	private float nextDayButtonHeight;
	public static float nextDayButtonTentionIndex; //Соотношение сторон кнопки
	
//Иконка КосмоКоинов
	private Texture money;
	private Sprite moneySprite;
	private Sprite fuelSprite;
	private Sprite metalSprite;
	private float moneyX;
	private float moneyY;
	private float moneyWidth;
	private float moneyHeight;
	private static BitmapFont text;
	private static BitmapFont textGameOver;
	
//Анимация следующего дня
	private Sprite nextDaySprite;
	private float nextDayX;
	private float nextDayY;
	private float nextDayWidth;
	private float nextDayHeight;
	public static boolean isNextDayDraw = false;
	private static String lifeTime;
	private static long days;
	private static long weeks;
	private static long months;
	private static long years;
//Выход из анимации следующего дня
	private Sprite backNextDaySpriteInactive;
	private Sprite backNextDaySpriteActive;
	private float backNextDay1X;
	private float backNextDay1Y;
	private float backNextDay1Width;
	private float backNextDay1Height;
	private float backNextDay2X;
	private float backNextDay2Y;
	private float backNextDay2Width;
	private float backNextDay2Height;
//Часики
	private Texture clock;
	private Sprite clockSprite;
	private float clockX;
	private float clockY;
	private float clockWidth;
	private float clockHeight;
	private Texture grayArea;
	private Sprite grayAreaSprite;
	private float grayAreaX;
	private float grayAreaY;
	private float grayAreaWidth;
	private float grayAreaHeight;
	
	//UT_1202
	private Texture UT_1202Texture;
	public static Sprite UT_1202Sprite;
	private float UT_1202Width;
	private float UT_1202Height;
	private float UT_1202X;
	private float UT_1202Y;
	//MT_0112
	private Texture MT_0112Texture;
	public static Sprite MT_0112Sprite;
	private float MT_0112Width;
	private float MT_0112Height;
	private float MT_0112X;
	private float MT_0112Y;
	//LT_116
	private Texture LT_116Texture;
	public static Sprite LT_116Sprite;
	private float LT_116Width;
	private float LT_116Height;
	private float LT_116X;
	private float LT_116Y;
	//UM_1034
	private Texture UM_1034Texture;
	public static Sprite UM_1034Sprite;
	private float UM_1034Width;
	private float UM_1034Height;
	private float UM_1034X;
	private float UM_1034Y;
	//MM_4
	private Texture MM_4Texture;
	public static Sprite MM_4Sprite;
	private float MM_4Width;
	private float MM_4Height;
	private float MM_4X;
	private float MM_4Y;
	//LM_087
	private Texture LM_087Texture;
	public static Sprite LM_087Sprite;
	private float LM_087Width;
	private float LM_087Height;
	private float LM_087X;
	private float LM_087Y;
	//UF_02
	private Texture UF_02Texture;
	public static Sprite UF_02Sprite;
	private float UF_02Width;
	private float UF_02Height;
	private float UF_02X;
	private float UF_02Y;
	//MF_043
	private Texture MF_043Texture;
	public static Sprite MF_043Sprite;
	private float MF_043Width;
	private float MF_043Height;
	private float MF_043X;
	private float MF_043Y;
	//LF_15
	private Texture LF_15Texture;
	public static Sprite LF_15Sprite;
	private float LF_15Width;
	private float LF_15Height;
	private float LF_15X;
	private float LF_15Y;
	
	//Партикль дыма
	private Texture smokeTexture;
	private static Sprite smokeSprite;
	private float smokeWidth;
	private float smokeHeight;
	private float smokeX;
	private float smokeY;
	//Партикль дыма
	private static Sprite smokeYellowSprite;
	//NULL-TEXTURE
	public static Sprite nullSprite;
	
	private boolean wasLaunchSound;
	private Music launchSoundPath = Gdx.audio.newMusic(Gdx.files.internal("sounds/misc/WavLibraryNet_Sound6386.mp3"));
	
	private static int cost;
	private float timer;
	
	public GameScreen(Game game){
		this.game = game;
	}
	
	@Override
	public void show() {
		
		batch = new SpriteBatch();
		controller = new SFlightInputController();
		launchTimer = 0.0F;
		speed = 0.1F;
		nullSprite = new Sprite(new Texture("rockets/null.png"));
		nullSprite.setBounds(0, 0, 1, 1);
		flight = new FlightScreen(game);
		MainMenu.music.play();
		wasLaunchSound = false;
		timer = 0.0F;
//Фон\\
		backgroundTexture = new Texture("bckgrnd/spaceport3.png");
		backgroundSprite = new Sprite(backgroundTexture);
		backgroundX = 0.0F;
		backgroundY = 0.0F;
		backgroundSprite.setBounds(backgroundX, backgroundY, width*2, backgroundTentionIndex*2560.0F);
		
//Камера\\
		camera = new OrthographicCamera(width, height);
		camera.position.set(new Vector3(backgroundSprite.getWidth()/2, backgroundSprite.getHeight()/2, 0));
		
//Обычный белый текст\\
		FreeTypeFontGenerator genUS = new FreeTypeFontGenerator(Gdx.files.internal("fonts/prototype.ttf"));
		FreeTypeFontGenerator genRU = new FreeTypeFontGenerator(Gdx.files.internal("fonts/9840.otf"));
		FreeTypeFontParameter param = new FreeTypeFontParameter();
		FreeTypeFontParameter param2 = new FreeTypeFontParameter();
		param.color = Color.WHITE;
		param.size = 40;
		param2.color = Color.RED;
		param2.size = 60;
		if(InfoAndStats.lngRussian){
			param.characters = FONT_CHARS_RU;
			param2.characters = FONT_CHARS_RU;
			text = genRU.generateFont(param);
			textGameOver = genRU.generateFont(param2);
		}else{
			text = genUS.generateFont(param);
			textGameOver = genUS.generateFont(param2);
		}
		text.getData().setScale((float)(0.00075F*width));
		textGameOver.getData().setScale((float)(0.0015F*width));
		
		mainMenuButtonInit();
		analyticInit();
		angarInit();
		scienceCentreInit();
		cloudsInit();
		nextDayButtonInit();
		moneyInit();
		nextDayInit();
		controlInit();
		smokeInit();
		
		UT_1202Init();
		MT_0112Init();
		LT_116Init();
		UM_1034Init();
		MM_4Init();
		LM_087Init();
		UF_02Init();
		MF_043Init();
		LF_15Init();
	}

	@Override
	public void render(float delta) {
		InfoAndStats.elapsedTime++;
			
		if(isLaunch){
			if(!wasLaunchSound){
				launchSoundPath.play();
				wasLaunchSound = true;
			}
			launchTimer += delta;
			speed *= 1.0025F;
		}
		if(launchTimer > 20.0F){
			isLaunch = false;
			launchTimer = 0.0F;
			speed = 0.1F;
			MainMenu.music.pause();
			game.setScreen(new TechnicScreen(game, flight, 0.5F));
			this.dispose();
		}
		
//Необходимо для уничтожения эффекта следов*/
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		touchUpdate();
		
		mainMenuButtonCoords();
		cloudsCoords(delta);
		nextDayButtonCoords();
		moneyCoords();
		nextDayCoords();
		
//Апдейт камеры обязательно после всех манипуляций с ней*/
//И именно в таком порядке*/
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		
		/**Отрисовка объектов*/
		batch.begin();
		
		backgroundSprite.draw(batch);
		
		drawBuildings();
		drawRocket();
		drawClouds();
		drawButtons();
		drawNextDayInterface(delta);
		
		if(isLaunch) drawSmokeParticles();
		
		moneySprite.draw(batch);
		fuelSprite.draw(batch);
		metalSprite.draw(batch);
		text.draw(batch, ": " + Long.toString((int)(InfoAndStats.money)), moneySprite.getX() + moneySprite.getWidth() + moneySprite.getWidth()*0.15F, moneySprite.getY() + moneySprite.getHeight() - text.getCapHeight()/2);
		text.draw(batch, ": " + Long.toString((int)(InfoAndStats.fuel)), fuelSprite.getX() + fuelSprite.getWidth() + fuelSprite.getWidth()*0.15F, fuelSprite.getY() + fuelSprite.getHeight() - text.getCapHeight()/2);
		text.draw(batch, ": " + Long.toString((int)(InfoAndStats.metal)), metalSprite.getX() + metalSprite.getWidth() + metalSprite.getWidth()*0.15F, metalSprite.getY() + metalSprite.getHeight() - text.getCapHeight()/2);
		
		batch.end();
		/**Отрисовка объектов*/
		
		buttonListener();
		
	}
	
	private void mainMenuButtonCoords(){
//Установка координат кнопки "Main Menu"*/
		backButtonX = camera.position.x - backButtonWidth + (width/2 - 0.01F*width);
		backButtonY = camera.position.y - (height/2 - 0.01F*height);
		backButtonInactiveSprite.setX(backButtonX);
		backButtonInactiveSprite.setY(backButtonY);
		backButtonActiveSprite.setX(backButtonX);
		backButtonActiveSprite.setY(backButtonY);
	}	
	private void cloudsCoords(float delta){
		//Установка коодрдинат для облаков
		if(cloud1Sprite.getX() > backgroundX + backgroundSprite.getWidth()){
			cloud1Sprite.setX(0.0F - rand.nextInt((int)(width)) - cloud1Width);
			cloud1Sprite.setY(0.0F + rand.nextInt((int)(backgroundSprite.getHeight() - cloud1Height)));
			speed1 = delta*rand.nextInt(100);
		}
		else
			cloud1Sprite.setX(cloud1Sprite.getX() + speed1);
		/***/
		if(cloud2Sprite.getX() > backgroundX + backgroundSprite.getWidth()){
			cloud2Sprite.setX(0.0F - rand.nextInt((int)(width)) - cloud2Width);
			cloud2Sprite.setY(0.0F + rand.nextInt((int)(backgroundSprite.getHeight() - cloud1Height)));
			speed2 = delta*rand.nextInt(100);
		}
		else
			cloud2Sprite.setX(cloud2Sprite.getX() + speed2);
		/***/
		if(cloud3Sprite.getX() > backgroundX + backgroundSprite.getWidth()){
			cloud3Sprite.setX(0.0F - rand.nextInt((int)(width)) - cloud1Width);
			cloud3Sprite.setY(0.0F + rand.nextInt((int)(backgroundSprite.getHeight() - cloud1Height)));
			speed3 = delta*rand.nextInt(100);
		}
		else
			cloud3Sprite.setX(cloud3Sprite.getX() + speed3);
		/***/
		if(cloud4Sprite.getX() > backgroundX + backgroundSprite.getWidth()){
			cloud4Sprite.setX(0.0F - rand.nextInt((int)(width)) - cloud2Width);
			cloud4Sprite.setY(0.0F + rand.nextInt((int)(backgroundSprite.getHeight() - cloud2Height)));
			speed4 = delta*rand.nextInt(100);
		}
		else
			cloud4Sprite.setX(cloud4Sprite.getX() + speed4);
		//Установка коодрдинат для облаков
	}
	private void nextDayButtonCoords(){
		//Установка координат кнопки "Next Day"*/
		nextDayButtonX = camera.position.x - nextDayButtonWidth + (width/2 - 0.01F*width);
		nextDayButtonY = camera.position.y - (height/2 - 0.175F*height);
		nextDayButtonInactiveSprite.setX(nextDayButtonX);
		nextDayButtonInactiveSprite.setY(nextDayButtonY);
		nextDayButtonActiveSprite.setX(nextDayButtonX);
		nextDayButtonActiveSprite.setY(nextDayButtonY);
	}
	private void moneyCoords(){
		//Установка координат иконки КосмоКоинов*/
		moneyX = camera.position.x - moneyWidth - (width/2 - 0.065F*width);
		moneyY = camera.position.y - moneyHeight + (height/2 - 0.015F*height);
		moneySprite.setX(moneyX);
		moneySprite.setY(moneyY);
		fuelSprite.setX(moneyX);
		fuelSprite.setY(moneyY - 1.25F*moneyHeight);
		metalSprite.setX(moneyX);
		metalSprite.setY(moneyY - 2.5F*moneyHeight);
	}
	private void nextDayCoords(){
		//Установка координат следующего дня*/
		nextDayX = camera.position.x - 0.5F*nextDayWidth;
		nextDayY = camera.position.y - 0.5F*nextDayHeight;
		nextDaySprite.setX(nextDayX);
		nextDaySprite.setY(nextDayY);
		backNextDay1X = nextDayX + 0.95F*nextDayWidth - backNextDay1Width;
		backNextDay1Y = nextDayY + 0.05F*nextDayHeight;
		backNextDaySpriteInactive.setX(backNextDay1X);
		backNextDaySpriteInactive.setY(backNextDay1Y);
		backNextDay2X = nextDayX + 0.95F*nextDayWidth - backNextDay1Width - 0.16666666666666666666666666666667F*backNextDay2Width;
		backNextDay2Y = nextDayY + 0.05F*nextDayHeight - 0.16666666666666666666666666666667F*backNextDay2Height;
		backNextDaySpriteActive.setX(backNextDay2X);
		backNextDaySpriteActive.setY(backNextDay2Y);
		clockX = camera.position.x - clockSprite.getWidth()/2;
		clockY = camera.position.y - clockSprite.getHeight()/2;
		clockSprite.setX(clockX);
		clockSprite.setY(clockY);
		grayAreaX = camera.position.x - grayAreaWidth/2;
		grayAreaY = clockY - 0.01F*clockHeight;
		grayAreaSprite.setX(grayAreaX);
		grayAreaSprite.setY(grayAreaY);
		//Не коорды, но тем не менне относится к этому*/
		days = InfoAndStats.date%7 + 1;
		weeks = (int)InfoAndStats.date/7;
		months = (int)weeks/4;
		years = (int)months/12;
		if(!InfoAndStats.lngRussian)
			lifeTime = "The company exists: " + Long.toString((int)(years)) + "y/" + Long.toString((int)(months)) + "m/" + Long.toString((int)(weeks)) + "w/" + Long.toString((int)(days))  + "d";
		else
			lifeTime = "Компания существует: " + Long.toString((int)(years)) + "л/" + Long.toString((int)(months)) + "м/" + Long.toString((int)(weeks)) + "н/" + Long.toString((int)(days))  + "дн";
	}
	
	private void mainMenuButtonInit(){
		//Кнопка "Main Menu"\\
				backButtonInactive = new Texture("btns/button_main_menu_inactive.png");
				backButtonActive = new Texture("btns/button_main_menu_active.png");
				backButtonInactiveSprite = new Sprite(backButtonInactive);
				backButtonActiveSprite = new Sprite(backButtonActive);
				if(InfoAndStats.lngRussian){
					backButtonInactiveSprite.setTexture(ImgResDraw.mainMenuButtonInactiveRU);
					backButtonActiveSprite.setTexture(ImgResDraw.mainMenuButtonActiveRU);
				}
				backButtonTentionIndex = (float)backButtonInactive.getWidth()/backButtonInactive.getHeight();
				backButtonWidth = 0.132F*width;
				backButtonHeight = backButtonWidth/backButtonTentionIndex;
				backButtonX = camera.position.x - backButtonWidth + (width/2 - 0.01F*width);
				backButtonY = camera.position.y - (height/2 - 0.01F*height);
				backButtonInactiveSprite.setBounds(backButtonX, backButtonY, backButtonWidth, backButtonHeight);
				backButtonActiveSprite.setBounds(backButtonX, backButtonY, backButtonWidth, backButtonHeight);
			}
	private void angarInit(){
		//Ангар_1\\
		angar1Texture = new Texture("objects/angar_1.png");
		angar1Sprite = new Sprite(angar1Texture);
		angar1TentionIndex = (float)angar1Sprite.getWidth()/angar1Sprite.getHeight();
		angar1Width = 0.5F*width;
		angar1Height = (float)angar1Width/angar1TentionIndex;
		angar1X = 0.150F*backgroundSprite.getWidth();
		angar1Y = 0.625F*backgroundSprite.getHeight();
		angar1Sprite.setBounds(angar1X, angar1Y, angar1Width, angar1Height);
		//Ангар_2\\
		angar2Texture = new Texture("objects/angar_2.png");
		angar2Sprite = new Sprite(angar2Texture);
		angar2Width = 0.625F*width;
		angar2Height = (float)angar2Width/angar1TentionIndex;
		angar2X = 0.150F*backgroundSprite.getWidth() - 0.1F*angar2Width;
		angar2Y = 0.625F*backgroundSprite.getHeight() - 0.10035714285714285714285714285714F*angar2Height;
		angar2Sprite.setBounds(angar2X, angar2Y, angar2Width, angar2Height);
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
	private void cloudsInit(){
		//Облако_1\\
		cloud1Texture = new Texture("objects/cloud_1.png");
		cloud1Sprite = new Sprite(cloud1Texture);
		cloud1TentionIndex = (float)cloud1Sprite.getWidth()/cloud1Sprite.getHeight();
		cloud1Width = 0.125F*width;
		cloud1Height = (float)cloud1Width/cloud1TentionIndex;
		if(isFromMenu){
			cloud1X = 0.0F - rand.nextInt((int)(width)) - cloud1Width;
			cloud1Y = 0.0F + rand.nextInt((int)(backgroundSprite.getHeight() - cloud1Height));
		}else{
			cloud1X = cloud1PrevX;
			cloud1Y = cloud1PrevY;
		}
		cloud1Sprite.setBounds(cloud1X, cloud1Y, cloud1Width, cloud1Height);
		//Облако_2\\
		cloud2Texture = new Texture("objects/cloud_2.png");
		cloud2Sprite = new Sprite(cloud2Texture);
		cloud2TentionIndex = (float)cloud2Sprite.getWidth()/cloud2Sprite.getHeight();
		cloud2Width = 0.150F*width;
		cloud2Height = (float)cloud2Width/cloud2TentionIndex;
		if(isFromMenu){
			cloud2X = 0.0F - rand.nextInt((int)(width)) - cloud2Width;
			cloud2Y = 0.0F + rand.nextInt((int)(backgroundSprite.getHeight() - cloud2Height));
		}else{
			cloud2X = cloud2PrevX;
			cloud2Y = cloud2PrevY;
		}
		cloud2Sprite.setBounds(cloud2X, cloud2Y, cloud2Width, cloud2Height);
		//Ещё облака\\
		cloud3Sprite = new Sprite(cloud1Texture);
		if(isFromMenu){
			cloud3X = 0.0F - rand.nextInt((int)(width)) - cloud1Width;
			cloud3Y = 0.0F + rand.nextInt((int)(backgroundSprite.getHeight() - cloud1Height));
		}else{
			cloud3X = cloud3PrevX;
			cloud3Y = cloud3PrevY;
		}
		cloud3Sprite.setBounds(cloud3X, cloud3Y, cloud1Width, cloud1Height);
		cloud4Sprite = new Sprite(cloud2Texture);
		if(isFromMenu){
			cloud4X = 0.0F - rand.nextInt((int)(width)) - cloud2Width;
			cloud4Y = 0.0F + rand.nextInt((int)(backgroundSprite.getHeight() - cloud2Height));
		}else{
			cloud4X = cloud4PrevX;
			cloud4Y = cloud4PrevY;
		}
		cloud4Sprite.setBounds(cloud4X, cloud4Y, cloud2Width, cloud2Height);	
	}
	private void analyticInit(){
		//Аналитический_центр_1\\
		analytic1Texture = new Texture("objects/analytic_1.png");
		analytic1Sprite = new Sprite(analytic1Texture);
		analytic1TentionIndex = (float)analytic1Sprite.getWidth()/analytic1Sprite.getHeight();
		analytic1Width = 0.20F*width;
		analytic1Height = (float)analytic1Width/analytic1TentionIndex;
		analytic1X = 0.739F*backgroundSprite.getWidth();
		analytic1Y = 0.8F*backgroundSprite.getHeight();
		analytic1Sprite.setBounds(analytic1X, analytic1Y, analytic1Width, analytic1Height);
		//Аналитический_центр_2\\
		analytic2Texture = new Texture("objects/analytic_2.png");
		analytic2Sprite = new Sprite(analytic2Texture);
		analytic2Width = 0.3F*width;
		analytic2Height = (float)analytic2Width/analytic1TentionIndex;
		analytic2X = 0.739F*backgroundSprite.getWidth() - 0.16625615763546798029556650246305F*analytic2Width;
		analytic2Y = 0.8F*backgroundSprite.getHeight() - 0.16598778004073319755600814663951F*analytic2Height;
		analytic2Sprite.setBounds(analytic2X, analytic2Y, analytic2Width, analytic2Height);
	}
	private void nextDayButtonInit(){
		//Кнопка "Next Day"\\
		nextDayButtonInactive = new Texture("btns/button_next_day_inactive.png");
		nextDayButtonActive = new Texture("btns/button_next_day_active.png");
		nextDayButtonInactiveSprite = new Sprite(nextDayButtonInactive);
		nextDayButtonActiveSprite = new Sprite(nextDayButtonActive);
		if(InfoAndStats.lngRussian){
			nextDayButtonInactiveSprite.setTexture(ImgResDraw.nextDayButtonInactiveRU);
			nextDayButtonActiveSprite.setTexture(ImgResDraw.nextDayButtonActiveRU);
		}
		nextDayButtonTentionIndex = (float)nextDayButtonInactive.getWidth()/nextDayButtonInactive.getHeight();
		nextDayButtonWidth = 0.132F*width;
		nextDayButtonHeight = nextDayButtonWidth/nextDayButtonTentionIndex;
		nextDayButtonX = camera.position.x - nextDayButtonWidth + (width/2 - 0.01F*width);
		nextDayButtonY = camera.position.y - (height/2 - 0.175F*height);
		nextDayButtonInactiveSprite.setBounds(nextDayButtonX, nextDayButtonY, nextDayButtonWidth, nextDayButtonHeight);
		nextDayButtonActiveSprite.setBounds(nextDayButtonX, nextDayButtonY, nextDayButtonWidth, nextDayButtonHeight);
	}
	private void moneyInit(){
		//Иконка КосмоКоинов\\
		money = new Texture("objects/cosmocoin.png");
		moneySprite = new Sprite(money);
		fuelSprite = new Sprite(new Texture("objects/fuel.png"));
		metalSprite = new Sprite(new Texture("objects/metal.png"));
		moneySprite = new Sprite(money);
		moneyWidth = 0.05F*width;
		moneyHeight = moneyWidth;
		moneyX = camera.position.x - moneyWidth - (width/2 - 0.15F*width);
		moneyY = camera.position.y - moneyHeight + (height/2 - 0.015F*height);
		moneySprite.setBounds(moneyX, moneyY, moneyWidth, moneyHeight);
		fuelSprite.setBounds(moneyX, moneyY - 1.1F*moneyHeight, moneyWidth, moneyHeight);
		metalSprite.setBounds(moneyX, moneyY - 2.1F*moneyHeight, moneyWidth, moneyHeight);
	}
	private void nextDayInit(){
		//Окно следующего дня\\
		nextDaySprite = new Sprite(ImgResDraw.dialogWindow);
		nextDayWidth = 0.75F*width;
		nextDayHeight = nextDayWidth/1.45F;
		nextDayX = camera.position.x - 0.5F*nextDayWidth;
		nextDayY = camera.position.y - 0.5F*nextDayHeight;
		nextDaySprite.setBounds(nextDayX, nextDayY, nextDayWidth, nextDayHeight);
		//Кнопка выхода из окна следующего дня\\
		backNextDaySpriteInactive = new Sprite(ImgResDraw.backIconInactive);
		backNextDaySpriteActive = new Sprite(ImgResDraw.backIconActive);
		backNextDay1Width = 0.065F*width;
		backNextDay1Height = backNextDay1Width;
		backNextDay1X = nextDayX + 0.95F*nextDayWidth - backNextDay1Width;
		backNextDay1Y = nextDayY + 0.05F*nextDayHeight;
		backNextDaySpriteInactive.setBounds(backNextDay1X, backNextDay1Y, backNextDay1Width, backNextDay1Height);
		backNextDay2Width = 0.0975F*width;
		backNextDay2Height = backNextDay2Width;
		backNextDay2X = nextDayX + 0.95F*nextDayWidth - backNextDay1Width - 0.16666666666666666666666666666667F*backNextDay2Width;
		backNextDay2Y = nextDayY + 0.05F*nextDayHeight - 0.16666666666666666666666666666667F*backNextDay2Height;
		backNextDaySpriteActive.setBounds(backNextDay2X, backNextDay2Y, backNextDay2Width, backNextDay2Height);
		//Часики\\
		clock = new Texture("objects/clock.png");
		clockSprite = new Sprite(clock);
		clockWidth = 0.45F*width;
		clockHeight = clockWidth;
		clockX = camera.position.x - clockWidth;
		clockY = camera.position.y - clockHeight;
		clockSprite.setBounds(clockX, clockY, clockWidth, clockHeight);
		clockSprite.setOriginCenter();
		grayArea = new Texture("objects/gray.png");
		grayAreaSprite = new Sprite(grayArea);
		grayAreaWidth = 0.5F*width;
		grayAreaHeight = clockWidth/1.65F;
		grayAreaX = camera.position.x - grayAreaWidth/2;
		grayAreaY = clockY - 0.01F*clockHeight;
		grayAreaSprite.setBounds(grayAreaX, grayAreaY, grayAreaWidth, grayAreaHeight);
		days = InfoAndStats.date%7 + 1;
		weeks = (int)InfoAndStats.date/7;
		months = (int)weeks/4;
		years = (int)months/12;
		if(!InfoAndStats.lngRussian)
			lifeTime = "The lifetime of the company: " + Long.toString((int)(years)) + "y/" + Long.toString((int)(months)) + "m/" + Long.toString((int)(weeks)) + "w/" + Long.toString((int)(days))  + "d";
		else
			lifeTime = "Возраст компании: " + Long.toString((int)(years)) + "лет/" + Long.toString((int)(months)) + "мес/" + Long.toString((int)(weeks)) + "нед/" + Long.toString((int)(days))  + "дней";
	}
	private void controlInit(){
		//Диспетчерская_вышка_1\\
		control1Texture = new Texture("objects/control_1.png");
		control1Sprite = new Sprite(control1Texture);
		control1TentionIndex = (float)control1Sprite.getWidth()/control1Sprite.getHeight();
		control1Width = 0.125F*width;
		control1Height = (float)control1Width/control1TentionIndex;
		control1X = 0.469F*backgroundSprite.getWidth();
		control1Y = 0.665F*backgroundSprite.getHeight();
		control1Sprite.setBounds(control1X, control1Y, control1Width, control1Height);
		//Диспетчерская_вышка_2\\
		control2Texture = new Texture("objects/control_2.png");
		control2Sprite = new Sprite(control2Texture);
		control2Width = 0.22163120567375886524822695035461F*width;
		control2Height = (float)control2Width/control1TentionIndex;
		control2X = 0.469F*backgroundSprite.getWidth() - 0.21568627450980392156862745098039F*control2Width;
		control2Y = 0.665F*backgroundSprite.getHeight() - 0.218F*control2Height;
		control2Sprite.setBounds(control2X, control2Y, control2Width, control2Height);
	}
	private void smokeInit(){
		//Партикль дыма\\
		smokeTexture = new Texture("objects/dustParticle.png");
		smokeSprite = new Sprite(smokeTexture);
		smokeYellowSprite = new Sprite(new Texture("objects/dustParticleY.png"));
		smokeWidth = 0.025F*backgroundSprite.getWidth();
		smokeHeight = smokeWidth;
		smokeX = 0-1000;
		smokeY = 0-1000;
		smokeSprite.setBounds(smokeX, smokeY, smokeWidth, smokeHeight);
		smokeYellowSprite.setBounds(smokeX, smokeY, smokeWidth, smokeHeight);
		smokeSprite.setRotation(rand.nextInt(359));
		smokeYellowSprite.setRotation(rand.nextInt(359));
	}
	
	private void UT_1202Init(){
		//UT_1202\\
		UT_1202Texture = new Texture("rockets/UT_1202_stin.png");
		UT_1202Sprite = new Sprite(UT_1202Texture);
		UT_1202Width = 0.06F*backgroundSprite.getWidth();
		UT_1202Height = UT_1202Width;
		UT_1202X = backgroundSprite.getX() + 0.59375F*backgroundSprite.getWidth();
		UT_1202Y = backgroundSprite.getY() + 0.905F*backgroundSprite.getHeight();
		UT_1202Sprite.setBounds(UT_1202X, UT_1202Y, UT_1202Width, UT_1202Height);
	}
	private void MT_0112Init(){
		//MT_0112\\
		MT_0112Texture = new Texture("rockets/MT_0112.png");
		MT_0112Sprite = new Sprite(MT_0112Texture);
		MT_0112Width = 0.1175F*backgroundSprite.getWidth();
		MT_0112Height = MT_0112Width;
		MT_0112X = backgroundSprite.getX() + 0.56475F*backgroundSprite.getWidth();
		MT_0112Y = backgroundSprite.getY() + 0.8F*backgroundSprite.getHeight();
		MT_0112Sprite.setBounds(MT_0112X, MT_0112Y, MT_0112Width, MT_0112Height);
	}
	private void LT_116Init(){
		//LT_116\\
		LT_116Texture = new Texture("rockets/LT_116.png");
		LT_116Sprite = new Sprite(LT_116Texture);
		LT_116Width = 0.094F*backgroundSprite.getWidth();
		LT_116Height = LT_116Width;
		LT_116X = backgroundSprite.getX() + 0.57685F*backgroundSprite.getWidth();
		LT_116Y = backgroundSprite.getY() + 0.715F*backgroundSprite.getHeight();
		LT_116Sprite.setBounds(LT_116X, LT_116Y, LT_116Width, LT_116Height);
	}
	private void UM_1034Init(){
		//UM_1034\\
		UM_1034Texture = new Texture("rockets/UM_1034.png");
		UM_1034Sprite = new Sprite(UM_1034Texture);
		UM_1034Width = 0.06F*backgroundSprite.getWidth();
		UM_1034Height = UM_1034Width;
		UM_1034X = backgroundSprite.getX() + 0.59375F*backgroundSprite.getWidth();
		UM_1034Y = backgroundSprite.getY() + 0.905F*backgroundSprite.getHeight();
		UM_1034Sprite.setBounds(UM_1034X, UM_1034Y, UM_1034Width, UM_1034Height);
	}
	private void MM_4Init(){
		//MM_4\\
		MM_4Texture = new Texture("rockets/MM_4.png");
		MM_4Sprite = new Sprite(MM_4Texture);
		MM_4Width = 0.1175F*backgroundSprite.getWidth();
		MM_4Height = MM_4Width;
		MM_4X = backgroundSprite.getX() + 0.56475F*backgroundSprite.getWidth();
		MM_4Y = backgroundSprite.getY() + 0.8F*backgroundSprite.getHeight();
		MM_4Sprite.setBounds(MM_4X, MM_4Y, MM_4Width, MM_4Height);
	}
	private void LM_087Init(){
		//LM_087\\
		LM_087Texture = new Texture("rockets/LM_087.png");
		LM_087Sprite = new Sprite(LM_087Texture);
		LM_087Width = 0.094F*backgroundSprite.getWidth();
		LM_087Height = LM_087Width;
		LM_087X = backgroundSprite.getX() + 0.57685F*backgroundSprite.getWidth();
		LM_087Y = backgroundSprite.getY() + 0.715F*backgroundSprite.getHeight();
		LM_087Sprite.setBounds(LM_087X, LM_087Y, LM_087Width, LM_087Height);
	}
	private void UF_02Init(){
		//UF_02\\
		UF_02Texture = new Texture("rockets/UF_02.png");
		UF_02Sprite = new Sprite(UF_02Texture);
		UF_02Width = 0.06F*backgroundSprite.getWidth();
		UF_02Height = UF_02Width;
		UF_02X = backgroundSprite.getX() + 0.59375F*backgroundSprite.getWidth();
		UF_02Y = backgroundSprite.getY() + 0.905F*backgroundSprite.getHeight();
		UF_02Sprite.setBounds(UF_02X, UF_02Y, UF_02Width, UF_02Height);
	}
	private void MF_043Init(){
		//MF_043\\
		MF_043Texture = new Texture("rockets/MF_043.png");
		MF_043Sprite = new Sprite(MF_043Texture);
		MF_043Width = 0.1175F*backgroundSprite.getWidth();
		MF_043Height = MF_043Width;
		MF_043X = backgroundSprite.getX() + 0.56475F*backgroundSprite.getWidth();
		MF_043Y = backgroundSprite.getY() + 0.8F*backgroundSprite.getHeight();
		MF_043Sprite.setBounds(MF_043X, MF_043Y, MF_043Width, MF_043Height);
	}
	private void LF_15Init(){
		//LF_15\\
		LF_15Texture = new Texture("rockets/LF_15.png");
		LF_15Sprite = new Sprite(LF_15Texture);
		LF_15Width = 0.094F*backgroundSprite.getWidth();
		LF_15Height = LF_15Width;
		LF_15X = backgroundSprite.getX() + 0.57685F*backgroundSprite.getWidth();
		LF_15Y = backgroundSprite.getY() + 0.715F*backgroundSprite.getHeight();
		LF_15Sprite.setBounds(LF_15X, LF_15Y, LF_15Width, LF_15Height);
	}
	
	private void touchUpdate(){
		if(!isLaunch){
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
		}else{
			camera.position.set(new Vector3(0.625F*backgroundSprite.getWidth(), 0.825F*backgroundSprite.getHeight(), 0));
		}
	}

	private void drawRocket(){
		if(InfoAndStats.getThirdDetail().getRocketDetailAmount() > 0){ InfoAndStats.getThirdStage().draw(batch);}
		if(!InfoAndStats.selectedThird.equals("null") && InfoAndStats.getSecondDetail().getRocketDetailAmount() > 0){
			InfoAndStats.getSecondStage().draw(batch);
			if(!InfoAndStats.selectedSecond.equals("null") && InfoAndStats.getFirstDetail().getRocketDetailAmount() > 0){
					InfoAndStats.getFirstStage().draw(batch);
			}
		}
		if(isLaunch){
			InfoAndStats.getThirdStage().setY(InfoAndStats.getThirdStage().getY() + speed);
			InfoAndStats.getSecondStage().setY(InfoAndStats.getSecondStage().getY() + speed);
			InfoAndStats.getFirstStage().setY(InfoAndStats.getFirstStage().getY() + speed);
		}
	}
	private void drawClouds(){
		cloud1Sprite.draw(batch);
		cloud2Sprite.draw(batch);
		cloud3Sprite.draw(batch);
		cloud4Sprite.draw(batch);
	}
	private void drawButtons(){
		if(!isLaunch){
			if(controller.isOnGameStatic(backButtonX, backButtonY, backButtonWidth, backButtonHeight)){
				if(!isNextDayDraw){
					backButtonActiveSprite.draw(batch);
				}else{
					backButtonInactiveSprite.draw(batch);
				}
			}else{
				backButtonInactiveSprite.draw(batch);
			}
		
			if(controller.isOnGameStatic(nextDayButtonX, nextDayButtonY, nextDayButtonWidth, nextDayButtonHeight)){
				if(!isNextDayDraw){
					nextDayButtonActiveSprite.draw(batch);
				}else{
					nextDayButtonInactiveSprite.draw(batch);
				}
			}else{
				nextDayButtonInactiveSprite.draw(batch);
			}
		}else{
			backButtonInactiveSprite.draw(batch);
			nextDayButtonInactiveSprite.draw(batch);
		}
	}
	private void drawBuildings(){
		if(!isLaunch){
			//Ангар
			if(controller.isOnGame(angar1Sprite.getX(), angar1Sprite.getY(), angar1Width, angar1Height)){
				if(!isNextDayDraw){
					angar2Sprite.draw(batch);
				}else{
					angar1Sprite.draw(batch);
				}
			}else{
				angar1Sprite.draw(batch);
			}
			//Аналитический центр
			if(controller.isOnGame(analytic1Sprite.getX(), analytic1Sprite.getY(), analytic1Width, analytic1Height)){
				if(!isNextDayDraw){
					analytic2Sprite.draw(batch);
				}else{
					analytic1Sprite.draw(batch);
				}
			}else{
				analytic1Sprite.draw(batch);
			}
			//Диспетчерская вышка
			if(controller.isOnGame(control1Sprite.getX(), control1Sprite.getY(), control1Width, control1Height)){
				if(!isNextDayDraw){
					control2Sprite.draw(batch);
				}else{
					control1Sprite.draw(batch);
				}
			}else{
				control1Sprite.draw(batch);
			}
			//Научный центр
			if(controller.isOnGame(scienceCentre1Sprite.getX(), scienceCentre1Sprite.getY(), scienceCentre1Width, scienceCentre1Height)){
				if(!isNextDayDraw){
					scienceCentre2Sprite.draw(batch);
				}else{
					scienceCentre1Sprite.draw(batch);
				}
			}else{
				scienceCentre1Sprite.draw(batch);
			}
		}else{
			angar1Sprite.draw(batch);
			analytic1Sprite.draw(batch);
			control1Sprite.draw(batch);
			scienceCentre1Sprite.draw(batch);
		}
	}
	private void drawNextDayInterface(float delta){
		if(isNextDayDraw){
			if(InfoAndStats.money <= 0){
				if(timer < 7.5F){
					timer += delta;
					nextDaySprite.draw(batch);
					if(!InfoAndStats.lngRussian){
						textGameOver.draw(batch, "Game over!", nextDaySprite.getX() + 0.185F*nextDaySprite.getWidth(), nextDaySprite.getY() + 0.9F*nextDaySprite.getHeight());
						text.draw(batch, "The company went bankrupt!", nextDaySprite.getX() + 0.235F*nextDaySprite.getWidth(), nextDaySprite.getY() + 0.9F*nextDaySprite.getHeight() - 1.5F*textGameOver.getCapHeight());
						text.draw(batch, "Game will be reset", nextDaySprite.getX() + 0.325F*nextDaySprite.getWidth(), nextDaySprite.getY() + 0.9F*nextDaySprite.getHeight() - 2.5F*textGameOver.getCapHeight());
						text.draw(batch, "Wait for a few seconds", nextDaySprite.getX() + 0.285F*nextDaySprite.getWidth(), nextDaySprite.getY() + 0.9F*nextDaySprite.getHeight() - 2.5F*textGameOver.getCapHeight() - 1.5F*text.getCapHeight());
					}else{
						textGameOver.draw(batch, "Игра окончена!", nextDaySprite.getX() + 0.04F*nextDaySprite.getWidth(), nextDaySprite.getY() + 0.9F*nextDaySprite.getHeight());
						text.draw(batch, "Компания обанкротилась!", nextDaySprite.getX() + 0.245F*nextDaySprite.getWidth(), nextDaySprite.getY() + 0.9F*nextDaySprite.getHeight() - 1.5F*textGameOver.getCapHeight());
						text.draw(batch, "Игра будет сброшена", nextDaySprite.getX() + 0.2925F*nextDaySprite.getWidth(), nextDaySprite.getY() + 0.9F*nextDaySprite.getHeight() - 2.5F*textGameOver.getCapHeight());
						text.draw(batch, "Подождите пару секунд", nextDaySprite.getX() + 0.265F*nextDaySprite.getWidth(), nextDaySprite.getY() + 0.9F*nextDaySprite.getHeight() - 2.5F*textGameOver.getCapHeight() - 1.5F*text.getCapHeight());
					}
				}else{
					isNextDayDraw = false;
					ResetTheGame.reset();
					game.setScreen(new MainMenu(game));
					this.dispose();
				}
			}else{
				if(clockSprite.getRotation() < 360.0F)
					clockSprite.rotate(1.0F);
				nextDaySprite.draw(batch);
				if(controller.isOnGameStatic(backNextDay1X, backNextDay1Y, backNextDay1Width, backNextDay1Height)){
					backNextDaySpriteActive.draw(batch);
				}else{
					backNextDaySpriteInactive.draw(batch);
				}
				clockSprite.draw(batch);
				grayAreaSprite.draw(batch);
				if(!InfoAndStats.lngRussian){
					text.draw(batch, lifeTime, camera.position.x - 0.29F*width, camera.position.y - 0.1F*height);
					text.draw(batch, "Costs of the company: " + cost + " cosmocoins", camera.position.x - 0.29F*width, camera.position.y - 0.1F*height - 1.5F*text.getCapHeight());
				}else{
					text.draw(batch, lifeTime, camera.position.x - 0.3275F*width, camera.position.y - 0.1F*height);
					text.draw(batch, "Расходы компанни: " + cost + " космокоинов", camera.position.x - 0.3275F*width, camera.position.y - 0.1F*height - 1.5F*text.getCapHeight());
				}
			}
		}
	}
	private void drawSmokeParticles(){
		for(int i=0;i<40;i++){
			float h;
			h = (float)launchTimer/10.0F;
			smokeSprite.setX(InfoAndStats.getThirdStage().getX() + rand.nextInt((int)InfoAndStats.getThirdStage().getWidth()) - 0.6F*smokeSprite.getWidth());
			smokeSprite.setY(InfoAndStats.getThirdStage().getY() - rand.nextInt((int)(h*InfoAndStats.getThirdStage().getHeight())) - 0.6F*smokeSprite.getHeight());
			smokeSprite.rotate(rand.nextInt(359));
			smokeSprite.draw(batch);
		}
		for(int i=0;i<20;i++){
			float h;
			h = (float)launchTimer/10.0F;
			smokeYellowSprite.setX(InfoAndStats.getThirdStage().getX() + 0.25F*InfoAndStats.getThirdStage().getWidth() + rand.nextInt((int)InfoAndStats.getThirdStage().getWidth()/2) - 0.6F*smokeYellowSprite.getWidth());
			smokeYellowSprite.setY(InfoAndStats.getThirdStage().getY() - rand.nextInt((int)(h*InfoAndStats.getThirdStage().getHeight())/2) - 0.6F*smokeYellowSprite.getHeight());
			smokeYellowSprite.rotate(rand.nextInt(359));
			smokeYellowSprite.draw(batch);
		}
	}
	
	private void buttonListener(){
		//Слушатель нажатия на кнопку "Main Menu"*/
		if(controller.isClickedGame(backButtonX, backButtonY, backButtonWidth, backButtonHeight)){
			if(!isNextDayDraw){
				game.setScreen(new MainMenu(game));
				this.dispose();
			}
		}
		//Слушатель нажатия на кнопку "Next Day"*/
		if(controller.isClickedGame(nextDayButtonX, nextDayButtonY, nextDayButtonWidth, nextDayButtonHeight)){
			if(!isNextDayDraw){
				if(!(InfoAndStats.money == 1)){
					cost = rand.nextInt((int)(InfoAndStats.money/100.0F*(float)(rand.nextInt(101) + 1) + 1));
				}else{
					cost = 1;
				}
				InfoAndStats.date++;
				InfoAndStats.money -= cost;
				isNextDayDraw = true;
			}
		}
		//Слушатель нажатия на кнопку "Выход из Next Day"*/
		if(controller.isClickedGame(backNextDay1X, backNextDay1Y, backNextDay1Width, backNextDay1Height)){
			if(isNextDayDraw){
			isNextDayDraw = false;
			clockSprite.setRotation(0);
			}
		}
		//Слушатель нажатия на ангар*/
		if(controller.isClickedGame(angar1Sprite.getX(), angar1Sprite.getY(), angar1Width, angar1Height)){
			if(!isNextDayDraw){
				game.setScreen(new AngarScreen(game));
				this.dispose();
			}
		}
		//Слушатель нажатия на аналитический центр*/
		if(controller.isClickedGame(analytic1Sprite.getX(), analytic1Sprite.getY(), analytic1Width, analytic1Height)){
			if(!isNextDayDraw){	
				game.setScreen(new AnalyticCentreScreen(game));
				this.dispose();
			}
		}
		//Слушатель нажатия на диспетчерскую вышку*/
		if(controller.isClickedGame(control1Sprite.getX(), control1Sprite.getY(), control1Width, control1Height)){
			if(!isNextDayDraw){	
				game.setScreen(new ControlCentreScreen(game));
				this.dispose();
			}
		}
		//Слушатель нажатия на научный центр*/
		if(controller.isClickedGame(scienceCentre1Sprite.getX(), scienceCentre1Sprite.getY(), scienceCentre1Width, scienceCentre1Height)){
			if(!isNextDayDraw){	
				game.setScreen(new ScienceCentreScreen(game));
				this.dispose();
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

	private void textureDispose(){
		backgroundTexture.dispose();
		angar1Texture.dispose();
		angar2Texture.dispose();
		analytic1Texture.dispose();
		analytic2Texture.dispose();
		control1Texture.dispose();
		control2Texture.dispose();
		scienceCentre1Texture.dispose();
		scienceCentre2Texture.dispose();
		cloud1Texture.dispose();
		cloud2Texture.dispose();
		backButtonInactive.dispose();
		backButtonActive.dispose();
		nextDayButtonInactive.dispose();
		nextDayButtonActive.dispose();
		money.dispose();
		clock.dispose();
		UT_1202Texture.dispose();
		MT_0112Texture.dispose();
		LT_116Texture.dispose();
		UM_1034Texture.dispose();
		MM_4Texture.dispose();
		LM_087Texture.dispose();
		UF_02Texture.dispose();
		MF_043Texture.dispose();
		LF_15Texture.dispose();
		smokeTexture.dispose();
	}
	
	@Override
	public void dispose() {
		text.dispose();
		textGameOver.dispose();
		launchSoundPath.stop();
		launchSoundPath.dispose();
		game.dispose();
		textureDispose();
		cloud1PrevX = cloud1Sprite.getX();
		cloud1PrevY = cloud1Sprite.getY();
		cloud2PrevX = cloud2Sprite.getX();
		cloud2PrevY = cloud2Sprite.getY();
		cloud3PrevX = cloud3Sprite.getX();
		cloud3PrevY = cloud3Sprite.getY();
		cloud4PrevX = cloud4Sprite.getX();
		cloud4PrevY = cloud4Sprite.getY();
	}

}
