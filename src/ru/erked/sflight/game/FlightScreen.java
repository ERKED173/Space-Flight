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
import ru.erked.sflight.random.InfoAndStats;
import ru.erked.sflight.random.RocketDetailStats;

public class FlightScreen implements Screen{
	
	final String FONT_CHARS_RU = "абвгдежзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
	private static final float width = Gdx.graphics.getWidth();
	private static final float height = Gdx.graphics.getHeight();
	private final Random rand = new Random();
	
	public static Music music = Gdx.audio.newMusic(Gdx.files.internal("sounds/music/Voice Over Under.mp3"));
	public static Music detachSound = Gdx.audio.newMusic(Gdx.files.internal("sounds/misc/detach.wav"));
	public static Music explosionSound = Gdx.audio.newMusic(Gdx.files.internal("sounds/misc/explosion.wav"));
	public static Music flightSound = Gdx.audio.newMusic(Gdx.files.internal("sounds/misc/flight.wav"));
	private Game game;
	private SpriteBatch batch;
	private SFlightInputController controller;
	public static OrthographicCamera camera;
	private static BitmapFont text;
	private static BitmapFont textSuc;
	private static BitmapFont textFail;
	private float timer;
	private float speed;
	private float firstWT;
	private float secondWT;
	private float thirdWT;
	
	private boolean hasThirdStage;
	private boolean hasSecondStage;
	
	private boolean isSuccess;
	private boolean isOver;
	
	//Фон
	private Texture backgroundTexture; //Текстура фона
	public static Sprite backgroundSprite; //Спрайт фона
	private float backgroundX;
	private float backgroundY;
	public static final float backgroundTentionIndex = (float)width/512.0F;
	
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
	private static Sprite smokeYellowSprite;
	private float smokeWidth;
	private float smokeHeight;
	private float smokeX;
	private float smokeY;
	//Партикль дыма метеоров
	private static Sprite smokeMeteorSprite;
	//NULL-TEXTURE
	public static Sprite nullSprite;
	
	//Путь
	private Texture distanceTexture;
	private static Sprite distanceSprite;
	private float distanceWidth;
	private float distanceHeight;
	private float distanceX;
	private float distanceY;
	private Texture distanceCursorTexture;
	private static Sprite distanceCursorSprite;
	private float distanceCursorWidth;
	private float distanceCursorHeight;
	private float distanceCursorX;
	private float distanceCursorY;
	
	//Отметка конца пути ракеты
	private Texture rocketEndTexture;
	private static Sprite rocketEndSprite;
	private float rocketEndWidth;
	private float rocketEndHeight;
	private float rocketEndX;
	private float rocketEndY;
	
	//Сброс третьей ступени
	private Sprite thirdSpriteInactive;
	private Sprite thirdSpriteActive;
	private float third1X;
	private float third1Y;
	private float third1Width;
	private float third1Height;
	private float third2X;
	private float third2Y;
	private float third2Width;
	private float third2Height;
	
	//Сброс второй ступени
	private Sprite secondSpriteInactive;
	private Sprite secondSpriteActive;
	private float second1X;
	private float second1Y;
	private float second1Width;
	private float second1Height;
	private float second2X;
	private float second2Y;
	private float second2Width;
	private float second2Height;
	
	//Метеоры
	private static Sprite meteor1Sprite;
	private float rotation1;
	private float angle1;
	private float timer1;
	private boolean smokeDraw1;
	private float mX1;
	private float mY1;
	private float mW1;
	private float mH1;
	private static Sprite meteor2Sprite;
	private float rotation2;
	private float angle2;
	private float timer2;
	private boolean smokeDraw2;
	private float mX2;
	private float mY2;
	private float mW2;
	private float mH2;
	private static Sprite meteor3Sprite;
	private float rotation3;
	private float angle3;
	private float timer3;
	private boolean smokeDraw3;
	private float mX3;
	private float mY3;
	private float mW3;
	private float mH3;
	private static Sprite meteor4Sprite;
	private float rotation4;
	private float angle4;
	private float timer4;
	private boolean smokeDraw4;
	private float mX4;
	private float mY4;
	private float mW4;
	private float mH4;
	
	private boolean isFirstPlayed;
	private boolean isSecondPlayed;
	
	public FlightScreen(Game game){
		this.game = game;
	}
	
	@Override
	public void show() {

		batch = new SpriteBatch();
		controller = new SFlightInputController();
		timer = 0.0F;
		
		music.setVolume(0.35F);
		music.setLooping(true);
		music.play();
		
		isFirstPlayed = false;
		isSecondPlayed = false;
		
		flightSound.setVolume(0.65F);
		flightSound.setLooping(true);
		flightSound.play();
		
		//Фон\\
		backgroundTexture = new Texture("bckgrnd/space.png");
		backgroundSprite = new Sprite(backgroundTexture);
		backgroundX = 0.0F;
		backgroundY = 0.0F;
		backgroundSprite.setBounds(backgroundX, backgroundY, width, backgroundTentionIndex*3072.0F);
		
		nullSprite = new Sprite(new Texture("rockets/null.png"));
		nullSprite.setBounds(0, 0, 1, 1);

		//Камера\\
		camera = new OrthographicCamera(width, height);
		camera.position.set(new Vector3(width/2, height/2, 0));

		//Инициализация\\
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
		
		distanceInit();
		rocketEndInit();
		thirdAndSecondInit();
		meteorInit();
		
		hasThirdStage = true;
		hasSecondStage = true;
		
		isSuccess = false;
		isOver = false;
		
		firstWT = InfoAndStats.firstStage.getRocketDetailTime();
		secondWT = InfoAndStats.secondStage.getRocketDetailTime();
		thirdWT = InfoAndStats.thirdStage.getRocketDetailTime();
		
		float difference = InfoAndStats.currentOrder.getOrderMass() - RocketDetailStats.totalThrust;
		if(difference > 0.0F){
			if(!(RocketDetailStats.totalSpecificImpulse - difference <= 0.0F)){
				speed = RocketDetailStats.totalSpecificImpulse - difference;
			}else{
				speed = 0.1F*RocketDetailStats.totalSpecificImpulse;
			}
		}else{
			speed = RocketDetailStats.totalSpecificImpulse;
		}
	
		//Текста\\
		FreeTypeFontGenerator genUS = new FreeTypeFontGenerator(Gdx.files.internal("fonts/prototype.ttf"));
		FreeTypeFontGenerator genRU = new FreeTypeFontGenerator(Gdx.files.internal("fonts/9840.otf"));
		FreeTypeFontParameter param = new FreeTypeFontParameter();
		FreeTypeFontParameter paramFail = new FreeTypeFontParameter();
		FreeTypeFontParameter paramSuc = new FreeTypeFontParameter();
		param.color = Color.WHITE;
		param.size = 40;
		paramFail.color = Color.RED;
		paramFail.size = 40;
		paramSuc.color = Color.GREEN;
		paramSuc.size = 40;
		if(InfoAndStats.lngRussian){
			param.characters = FONT_CHARS_RU;
			paramFail.characters = FONT_CHARS_RU;
			paramSuc.characters = FONT_CHARS_RU;
			text = genRU.generateFont(param);
			textFail = genRU.generateFont(paramFail);
			textSuc = genRU.generateFont(paramSuc);
		}else{
			text = genUS.generateFont(param);
			textFail = genUS.generateFont(paramFail);
			textSuc = genUS.generateFont(paramSuc);
		}
		text.getData().setScale((float)(0.00075F*width));
		textFail.getData().setScale((float)(0.00075F*width));
		textSuc.getData().setScale((float)(0.00075F*width));
		
	}

	@Override
	public void render(float delta) {
		InfoAndStats.elapsedTime++;
		
		if(thirdWT > 0.0F && hasThirdStage){
			thirdWT -= delta;
		}else{
			thirdWT = 0.0F;
			if(!isFirstPlayed && hasThirdStage){
				flightSound.pause();
				isFirstPlayed = true;
			}
		}
		if(thirdWT == 0.0F && !hasThirdStage){
			if(secondWT > 0.0F && hasSecondStage){
				secondWT -= delta;
			}else{
				secondWT = 0.0F;
				if(!isSecondPlayed && hasSecondStage){
					flightSound.pause();
					isSecondPlayed = true;
				}
			}
			if(secondWT == 0.0F && !hasSecondStage){
				if(firstWT > 0.0F){
					firstWT -= delta;
				}else{
					flightSound.pause();
					firstWT = 0.0F;
				}
			}
		}
		
		if(hasThirdStage && thirdWT == 0.0F){
			speed -= 0.1F*(1000.0F/InfoAndStats.getFirstStageFlight().getY());
		}
		if(hasSecondStage && secondWT == 0.0F){
			speed -= 0.1F*(1000.0F/InfoAndStats.getFirstStageFlight().getY());
		}
		
		destroyCheck();
		
		if(thirdWT == 0.0F && secondWT == 0.0F && firstWT == 0.0F && !isOver){
			if(distanceCursorSprite.getY() + distanceCursorSprite.getHeight() >= rocketEndSprite.getY()){
				speed = 0.0F;
				isSuccess = true;
				isOver = true;
				meteor1Sprite.setX(meteor1Sprite.getX());
				meteor1Sprite.setY(meteor1Sprite.getY());
				meteor2Sprite.setX(meteor2Sprite.getX());
				meteor2Sprite.setY(meteor2Sprite.getY());
				meteor3Sprite.setX(meteor3Sprite.getX());
				meteor3Sprite.setY(meteor3Sprite.getY());
				meteor4Sprite.setX(meteor4Sprite.getX());
				meteor4Sprite.setY(meteor4Sprite.getY());
			}else{
				if(InfoAndStats.getFirstStageFlight().getY() > 0.05F*backgroundSprite.getHeight() && !isOver){
					speed -= 0.1F*(1000.0F/InfoAndStats.getFirstStageFlight().getY());
				}else{
					speed = 0.0F;
				}
				isOver = true;
				isSuccess = false;
			}
		}
		
		//Необходимо для уничтожения эффекта следов*/
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.position.set(new Vector3(InfoAndStats.getFirstStageFlight().getX() + 0.5F*InfoAndStats.getFirstStageFlight().getWidth(), InfoAndStats.getFirstStageFlight().getY(), 0));
		
		if(camera.position.y <= height/5){
			camera.position.y = height/5;
			if(InfoAndStats.getFirstStageFlight().getY() > 0.0F){
				speed -= 0.1F*(1000.0F/InfoAndStats.getFirstStageFlight().getY());
			}else{
				speed = 0.0F;
			}
			isSuccess = false;
		}
		
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();
		
		backgroundSprite.draw(batch);
		
		drawMeteor();
		drawMeteorSmoke(delta);
		drawRocket();
		drawSmokeParticles();
		drawDistance();
		drawThirdAndSecond();
		drawSuccessFail(delta);
		
		btnListeners();
		
		batch.end();
		
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
		smokeSprite.setRotation(rand.nextInt(359));
		smokeYellowSprite.setBounds(smokeX, smokeY, smokeWidth, smokeHeight);
		smokeYellowSprite.setRotation(rand.nextInt(359));
		//Партикль дыма метеоров\\
		smokeMeteorSprite = new Sprite(smokeTexture);
		smokeMeteorSprite.setBounds(smokeX, smokeY, smokeWidth, smokeHeight);
		smokeMeteorSprite.setRotation(rand.nextInt(359));
	}
	private void UT_1202Init(){
		//UT_1202\\
		UT_1202Texture = new Texture("rockets/UT_1202_stin.png");
		UT_1202Sprite = new Sprite(UT_1202Texture);
		UT_1202Width = 0.06F*backgroundSprite.getWidth();
		UT_1202Height = UT_1202Width;
		UT_1202X = backgroundSprite.getX() + 0.5F*backgroundSprite.getWidth() - 0.5F*UT_1202Width;
		UT_1202Y = backgroundSprite.getY() + 0.11755F*backgroundSprite.getHeight();
		UT_1202Sprite.setBounds(UT_1202X, UT_1202Y, UT_1202Width, UT_1202Height);
	}
	private void MT_0112Init(){
		//MT_0112\\
		MT_0112Texture = new Texture("rockets/MT_0112.png");
		MT_0112Sprite = new Sprite(MT_0112Texture);
		MT_0112Width = 0.1175F*backgroundSprite.getWidth();
		MT_0112Height = MT_0112Width;
		MT_0112X = backgroundSprite.getX() + 0.5F*backgroundSprite.getWidth() - 0.5F*MT_0112Width;
		MT_0112Y = backgroundSprite.getY() + 0.1F*backgroundSprite.getHeight();
		MT_0112Sprite.setBounds(MT_0112X, MT_0112Y, MT_0112Width, MT_0112Height);
	}
	private void LT_116Init(){
		//LT_116\\
		LT_116Texture = new Texture("rockets/LT_116.png");
		LT_116Sprite = new Sprite(LT_116Texture);
		LT_116Width = 0.094F*backgroundSprite.getWidth();
		LT_116Height = LT_116Width;
		LT_116X = backgroundSprite.getX() + 0.5F*backgroundSprite.getWidth() - 0.5F*LT_116Width;
		LT_116Y = backgroundSprite.getY() + 0.0859F*backgroundSprite.getHeight();
		LT_116Sprite.setBounds(LT_116X, LT_116Y, LT_116Width, LT_116Height);
	}
	private void UM_1034Init(){
		//UM_1034\\
		UM_1034Texture = new Texture("rockets/UM_1034.png");
		UM_1034Sprite = new Sprite(UM_1034Texture);
		UM_1034Width = 0.06F*backgroundSprite.getWidth();
		UM_1034Height = UM_1034Width;
		UM_1034X = backgroundSprite.getX() + 0.5F*backgroundSprite.getWidth() - 0.5F*UM_1034Width;
		UM_1034Y = backgroundSprite.getY() + 0.1175F*backgroundSprite.getHeight();
		UM_1034Sprite.setBounds(UM_1034X, UM_1034Y, UM_1034Width, UM_1034Height);
	}
	private void MM_4Init(){
		//MM_4\\
		MM_4Texture = new Texture("rockets/MM_4.png");
		MM_4Sprite = new Sprite(MM_4Texture);
		MM_4Width = 0.1175F*backgroundSprite.getWidth();
		MM_4Height = MM_4Width;
		MM_4X = backgroundSprite.getX() + 0.5F*backgroundSprite.getWidth() - 0.5F*MM_4Width;
		MM_4Y = backgroundSprite.getY() + 0.1F*backgroundSprite.getHeight();
		MM_4Sprite.setBounds(MM_4X, MM_4Y, MM_4Width, MM_4Height);
	}
	private void LM_087Init(){
		//LM_087\\
		LM_087Texture = new Texture("rockets/LM_087.png");
		LM_087Sprite = new Sprite(LM_087Texture);
		LM_087Width = 0.094F*backgroundSprite.getWidth();
		LM_087Height = LM_087Width;
		LM_087X = backgroundSprite.getX() + 0.5F*backgroundSprite.getWidth() - 0.5F*LM_087Width;
		LM_087Y = backgroundSprite.getY() + 0.0859F*backgroundSprite.getHeight();
		LM_087Sprite.setBounds(LM_087X, LM_087Y, LM_087Width, LM_087Height);
	}
	private void UF_02Init(){
		//UF_02\\
		UF_02Texture = new Texture("rockets/UF_02.png");
		UF_02Sprite = new Sprite(UF_02Texture);
		UF_02Width = 0.06F*backgroundSprite.getWidth();
		UF_02Height = UF_02Width;
		UF_02X = backgroundSprite.getX() + 0.5F*backgroundSprite.getWidth() - 0.5F*UF_02Width;
		UF_02Y = backgroundSprite.getY() + 0.1175F*backgroundSprite.getHeight();
		UF_02Sprite.setBounds(UF_02X, UF_02Y, UF_02Width, UF_02Height);
	}
	private void MF_043Init(){
		//MF_043\\
		MF_043Texture = new Texture("rockets/MF_043.png");
		MF_043Sprite = new Sprite(MF_043Texture);
		MF_043Width = 0.1175F*backgroundSprite.getWidth();
		MF_043Height = MF_043Width;
		MF_043X = backgroundSprite.getX() + 0.5F*backgroundSprite.getWidth() - 0.5F*MF_043Width;
		MF_043Y = backgroundSprite.getY() + 0.1F*backgroundSprite.getHeight();
		MF_043Sprite.setBounds(MF_043X, MF_043Y, MF_043Width, MF_043Height);
	}
	private void LF_15Init(){
		//LF_15\\
		LF_15Texture = new Texture("rockets/LF_15.png");
		LF_15Sprite = new Sprite(LF_15Texture);
		LF_15Width = 0.094F*backgroundSprite.getWidth();
		LF_15Height = LF_15Width;
		LF_15X = backgroundSprite.getX() + 0.5F*backgroundSprite.getWidth() - 0.5F*LF_15Width;
		LF_15Y = backgroundSprite.getY() + 0.0859F*backgroundSprite.getHeight();
		LF_15Sprite.setBounds(LF_15X, LF_15Y, LF_15Width, LF_15Height);
	}
	
	private void distanceInit(){
		//Дистанция\\
		distanceTexture = new Texture("objects/progressFlight.png");
		distanceSprite = new Sprite(distanceTexture);
		distanceWidth = 0.0425F*backgroundSprite.getWidth();
		distanceHeight = distanceWidth/0.07692307692307692307692307692308F;
		distanceX = camera.position.x + 3.5F*width - 2.5F*distanceWidth;
		distanceY = camera.position.y - 2.25F*height + 0.1F*distanceHeight;
		distanceSprite.setBounds(distanceX, distanceY, distanceWidth, distanceHeight);
		//Курсор дистанции\\
		distanceCursorTexture = new Texture("objects/progressFlightCursor.png");
		distanceCursorSprite = new Sprite(distanceCursorTexture);
		distanceCursorWidth = 0.0525F*backgroundSprite.getWidth();
		distanceCursorHeight = distanceCursorWidth;
		distanceCursorX = camera.position.x + 0.5F*width - 3.0F*distanceCursorWidth;
		distanceCursorY = camera.position.y - 0.5F*height + 2.25F*distanceCursorHeight;
		distanceCursorSprite.setBounds(distanceCursorX, distanceCursorY, distanceCursorWidth, distanceCursorHeight);
	}
	private void rocketEndInit(){
		//Маркер конца полёта\\
		rocketEndTexture = new Texture("random/red.png");
		rocketEndSprite = new Sprite(rocketEndTexture);
		rocketEndWidth = 0.0475F*backgroundSprite.getWidth();
		rocketEndHeight = rocketEndWidth/5.0F;
		rocketEndX = distanceX;
		rocketEndY = distanceY;
		rocketEndSprite.setBounds(rocketEndX, rocketEndY, rocketEndWidth, rocketEndHeight);
	}
	private void thirdAndSecondInit(){
		thirdSpriteInactive = new Sprite(new Texture("btns/thirdStageInactive.png"));
		thirdSpriteActive = new Sprite(new Texture("btns/thirdStageActive.png"));
		third1Width = 0.1F*width;
		third1Height = third1Width;
		third1X = camera.position.x + width/5.0F;
		third1Y = distanceSprite.getY();
		thirdSpriteInactive.setBounds(third1X, third1Y, third1Width, third1Height);
		third2Width = 0.175F*width;
		third2Height = third2Width;
		third2X = camera.position.x + width/5.0F - 0.21428571428571428571428571428571F*third2Width;
		third2Y = distanceSprite.getY() - 0.21428571428571428571428571428571F*third2Height;
		thirdSpriteActive.setBounds(third2X, third2Y, third2Width, third2Height);
		///***\\\
		secondSpriteInactive = new Sprite(new Texture("btns/secondStageInactive.png"));
		secondSpriteActive = new Sprite(new Texture("btns/secondStageActive.png"));
		second1Width = 0.1F*width;
		second1Height = second1Width;
		second1X = camera.position.x + width/5.0F;
		second1Y = distanceSprite.getY();
		secondSpriteInactive.setBounds(second1X, second1Y, second1Width, second1Height);
		second2Width = 0.175F*width;
		second2Height = second2Width;
		second2X = camera.position.x + width/5.0F - 0.21428571428571428571428571428571F*second2Width;
		second2Y = distanceSprite.getY() - 0.21428571428571428571428571428571F*second2Height;
		secondSpriteActive.setBounds(second2X, second2Y, second2Width, second2Height);
	}
	private void meteorInit(){
		meteor1Sprite = new Sprite(new Texture("objects/meteor_1.png"));
		meteor2Sprite = new Sprite(new Texture("objects/meteor_2.png"));
		meteor3Sprite = new Sprite(new Texture("objects/meteor_3.png"));
		meteor4Sprite = new Sprite(new Texture("objects/meteor_4.png"));
		float width1 = 0.05F*width + rand.nextFloat()*width/8;
		float width2 = 0.05F*width + rand.nextFloat()*width/8;
		float width3 = 0.05F*width + rand.nextFloat()*width/8;
		float width4 = 0.05F*width + rand.nextFloat()*width/8;
		meteor1Sprite.setBounds(camera.position.x - width/2 - width1, camera.position.y + height/2 + rand.nextFloat()*height, width1, width1);
		meteor2Sprite.setBounds(camera.position.x - width/2 - width2, camera.position.y + height/2 + rand.nextFloat()*height, width2, width2);
		meteor3Sprite.setBounds(camera.position.x + width/2, camera.position.y + height/2 + rand.nextFloat()*height, width3, width3);
		meteor4Sprite.setBounds(camera.position.x + width/2, camera.position.y + height/2 + rand.nextFloat()*height, width4, width4);
		meteor1Sprite.setOriginCenter();
		meteor2Sprite.setOriginCenter();
		meteor3Sprite.setOriginCenter();
		meteor4Sprite.setOriginCenter();
		meteor1Sprite.setRotation(rand.nextFloat()*360.0F);
		meteor2Sprite.setRotation(rand.nextFloat()*360.0F);
		meteor3Sprite.setRotation(rand.nextFloat()*360.0F);
		meteor4Sprite.setRotation(rand.nextFloat()*360.0F);
		rotation1 = rand.nextFloat();
		rotation2 = rand.nextFloat();
		rotation3 = rand.nextFloat();
		rotation4 = rand.nextFloat();
		angle1 = rand.nextFloat()*5.0F;
		angle2 = rand.nextFloat()*5.0F;
		angle3 = rand.nextFloat()*5.0F;
		angle4 = rand.nextFloat()*5.0F;
		smokeDraw1 = false;
		smokeDraw2 = false;
		smokeDraw3 = false;
		smokeDraw4 = false;
		timer1 = 4.0F;
		timer2 = 4.0F;
		timer3 = 4.0F;
		timer4 = 4.0F;
	}
	
	private void drawDistance(){
		distanceSprite.setX(camera.position.x + 0.5F*width - 3.0F*distanceWidth);
		distanceSprite.setY(camera.position.y - 0.5F*height + 0.1F*distanceHeight);
		distanceSprite.draw(batch);
		
		rocketEndSprite.setX(distanceSprite.getX() - 0.0025F*backgroundSprite.getWidth());
		if(InfoAndStats.currentOrder.getOrderLevel() == 0)
			rocketEndSprite.setY(distanceSprite.getY() + 0.2F*distanceSprite.getHeight());
		else if(InfoAndStats.currentOrder.getOrderLevel() == 1)
			rocketEndSprite.setY(distanceSprite.getY() + 0.4F*distanceSprite.getHeight());
		else if(InfoAndStats.currentOrder.getOrderLevel() == 2)
			rocketEndSprite.setY(distanceSprite.getY() + 0.6F*distanceSprite.getHeight());
		else if(InfoAndStats.currentOrder.getOrderLevel() == 3)
			rocketEndSprite.setY(distanceSprite.getY() + 0.8F*distanceSprite.getHeight());
		rocketEndSprite.draw(batch);
		
		distanceCursorSprite.setX(distanceSprite.getX() - 0.005F*backgroundSprite.getWidth());
		distanceCursorSprite.setY(distanceSprite.getY() + ((float)InfoAndStats.getFirstStageFlight().getY()/backgroundSprite.getHeight())*distanceSprite.getHeight() - distanceCursorSprite.getHeight());
		distanceCursorSprite.draw(batch);
	}
	private void drawSmokeParticles(){
		for(int i=0;i<20;i++){
			smokeSprite.rotate(rand.nextInt(359));
			if(hasThirdStage && thirdWT != 0.0F){
				smokeSprite.setX(InfoAndStats.getThirdStageFlight().getX() + rand.nextInt((int)InfoAndStats.getThirdStageFlight().getWidth()) - 0.8F*smokeSprite.getWidth());
				smokeSprite.setY(InfoAndStats.getThirdStageFlight().getY() - rand.nextInt((int)(2*InfoAndStats.getThirdStageFlight().getHeight())) - 1.5F*smokeSprite.getHeight());
				smokeSprite.draw(batch);
			}
			if(!hasThirdStage && hasSecondStage && secondWT != 0.0F){
				smokeSprite.setX(InfoAndStats.getSecondStageFlight().getX() + rand.nextInt((int)(0.25F*InfoAndStats.getSecondStageFlight().getWidth())) + 0.15F*InfoAndStats.getSecondStageFlight().getWidth());
				smokeSprite.setY(InfoAndStats.getSecondStageFlight().getY() - rand.nextInt((int)(2*InfoAndStats.getSecondStageFlight().getHeight())) - 1.5F*smokeSprite.getHeight());
				smokeSprite.draw(batch);
			}
			if(!hasThirdStage && !hasSecondStage && firstWT != 0.0F){
				smokeSprite.setX(InfoAndStats.getFirstStageFlight().getX() + rand.nextInt((int)(0.25F*InfoAndStats.getFirstStageFlight().getWidth())));
				smokeSprite.setY(InfoAndStats.getFirstStageFlight().getY() - rand.nextInt((int)(2*InfoAndStats.getFirstStageFlight().getHeight())) - 1.5F*smokeSprite.getHeight());
				smokeSprite.draw(batch);
			}
		}
		for(int i=0;i<10;i++){
			if(hasThirdStage && thirdWT != 0.0F){
				smokeYellowSprite.setX(InfoAndStats.getThirdStageFlight().getX() + 0.25F*InfoAndStats.getThirdStageFlight().getWidth() + rand.nextInt((int)InfoAndStats.getThirdStageFlight().getWidth()/2) - 0.45F*smokeYellowSprite.getWidth());
				smokeYellowSprite.setY(InfoAndStats.getThirdStageFlight().getY() - rand.nextInt((int)(InfoAndStats.getThirdStageFlight().getHeight())) - 1.3F*smokeYellowSprite.getHeight());
				smokeYellowSprite.draw(batch);
			}
			if(!hasThirdStage && hasSecondStage && secondWT != 0.0F){
				smokeYellowSprite.setX(InfoAndStats.getSecondStageFlight().getX() + 0.375F*InfoAndStats.getSecondStageFlight().getWidth() + rand.nextInt((int)InfoAndStats.getSecondStageFlight().getWidth()/4) - 0.45F*smokeYellowSprite.getWidth());
				smokeYellowSprite.setY(InfoAndStats.getSecondStageFlight().getY() - rand.nextInt((int)(InfoAndStats.getSecondStageFlight().getHeight())) - 1.15F*smokeYellowSprite.getHeight());
				smokeYellowSprite.draw(batch);
			}
			if(!hasThirdStage && !hasSecondStage && firstWT != 0.0F){
				smokeYellowSprite.setX(InfoAndStats.getFirstStageFlight().getX() + 0.375F*InfoAndStats.getFirstStageFlight().getWidth() + rand.nextInt((int)InfoAndStats.getFirstStageFlight().getWidth()/4) - 0.45F*smokeYellowSprite.getWidth());
				smokeYellowSprite.setY(InfoAndStats.getFirstStageFlight().getY() - rand.nextInt((int)(InfoAndStats.getFirstStageFlight().getHeight())) - 1.0F*smokeYellowSprite.getHeight());
				smokeYellowSprite.draw(batch);
			}
		}
	}
	private void drawRocket(){
		if(hasThirdStage) 
			InfoAndStats.getThirdStageFlight().setY(InfoAndStats.getThirdStageFlight().getY() + speed);
		else
			InfoAndStats.getThirdStageFlight().setY(InfoAndStats.getThirdStageFlight().getY() - Math.abs(speed));
		InfoAndStats.getThirdStageFlight().draw(batch);
		if(hasSecondStage)
			InfoAndStats.getSecondStageFlight().setY(InfoAndStats.getSecondStageFlight().getY() + speed);
		else
			InfoAndStats.getSecondStageFlight().setY(InfoAndStats.getSecondStageFlight().getY() - Math.abs(speed));
		InfoAndStats.getSecondStageFlight().draw(batch);
		InfoAndStats.getFirstStageFlight().setY(InfoAndStats.getFirstStageFlight().getY() + speed);
		InfoAndStats.getFirstStageFlight().draw(batch);
	}
	private void drawThirdAndSecond(){
		third1X = camera.position.x + width/5.0F;
		third1Y = distanceSprite.getY();
		thirdSpriteInactive.setBounds(third1X, third1Y, third1Width, third1Height);
		third2X = camera.position.x + width/5.0F - 0.21428571428571428571428571428571F*third2Width;
		third2Y = distanceSprite.getY() - 0.21428571428571428571428571428571F*third2Height;
		thirdSpriteActive.setBounds(third2X, third2Y, third2Width, third2Height);
		second1X = camera.position.x + width/5.0F;
		second1Y = distanceSprite.getY();
		secondSpriteInactive.setBounds(second1X, second1Y, second1Width, second1Height);
		second2X = camera.position.x + width/5.0F - 0.21428571428571428571428571428571F*second2Width;
		second2Y = distanceSprite.getY() - 0.21428571428571428571428571428571F*second2Height;
		secondSpriteActive.setBounds(second2X, second2Y, second2Width, second2Height);
		if(hasThirdStage){
			if(controller.isOnGameStaticFlight(third1X, third1Y, third1Width, third1Height)){
				thirdSpriteActive.draw(batch);
			}else{
				thirdSpriteInactive.draw(batch);
			}
		}
		if(!hasThirdStage && hasSecondStage){
			if(controller.isOnGameStaticFlight(second1X, second1Y, second1Width, second1Height)){
				secondSpriteActive.draw(batch);
			}else{
				secondSpriteInactive.draw(batch);
			}
		}
	}
	private void drawSuccessFail(float delta){
		if(isSuccess && (thirdWT == 0.0F && secondWT == 0.0F && firstWT == 0.0F)){
			if(!InfoAndStats.lngRussian){
				textSuc.draw(batch, "Launch successful", 0.01F*width, camera.position.y + 0.49F*height);
			}else{
				textSuc.draw(batch, "Успешный запуск", 0.01F*width, camera.position.y + 0.49F*height);
			}
			timer += delta;
			if(timer > 5.0F){
				InfoAndStats.money += InfoAndStats.currentOrder.getOrderReward();
				InfoAndStats.hasOrder = false;
				InfoAndStats.launch++;
				InfoAndStats.currentOrder.setOrderLevel(0);
				InfoAndStats.currentOrder.setOrderMass(0.0F);
				InfoAndStats.currentOrder.setOrderName("");
				InfoAndStats.currentOrder.setOrderReward(0);
				if(InfoAndStats.selectedFirst.equals("UT_1202")) InfoAndStats.UT_1202.setRocketDetailAmount(InfoAndStats.UT_1202.getRocketDetailAmount() -1);
				if(InfoAndStats.selectedFirst.equals("UM_1034")) InfoAndStats.UM_1034.setRocketDetailAmount(InfoAndStats.UM_1034.getRocketDetailAmount() -1);
				if(InfoAndStats.selectedFirst.equals("UF_02")) InfoAndStats.UF_02.setRocketDetailAmount(InfoAndStats.UF_02.getRocketDetailAmount() -1);
				if(InfoAndStats.selectedSecond.equals("MT_0112")) InfoAndStats.MT_0112.setRocketDetailAmount(InfoAndStats.MT_0112.getRocketDetailAmount() -1);
				if(InfoAndStats.selectedSecond.equals("MM_4")) InfoAndStats.MM_4.setRocketDetailAmount(InfoAndStats.MM_4.getRocketDetailAmount() -1);
				if(InfoAndStats.selectedSecond.equals("MF_043")) InfoAndStats.MF_043.setRocketDetailAmount(InfoAndStats.MF_043.getRocketDetailAmount() -1);
				if(InfoAndStats.selectedThird.equals("LT_116")) InfoAndStats.LT_116.setRocketDetailAmount(InfoAndStats.LT_116.getRocketDetailAmount() -1);
				if(InfoAndStats.selectedThird.equals("LM_087")) InfoAndStats.LM_087.setRocketDetailAmount(InfoAndStats.LM_087.getRocketDetailAmount() -1);
				if(InfoAndStats.selectedThird.equals("LF_15")) InfoAndStats.LF_15.setRocketDetailAmount(InfoAndStats.LF_15.getRocketDetailAmount() -1);
				InfoAndStats.selectedFirst = "null";
				InfoAndStats.selectedSecond = "null";
				InfoAndStats.selectedThird = "null";
				InfoAndStats.firstStage.setRocketDetailAccessibility(false);
				InfoAndStats.firstStage.setRocketDetailAmount(0);
				InfoAndStats.firstStage.setRocketDetailImpulse(0.0F);
				InfoAndStats.firstStage.setRocketDetailName("", "");
				InfoAndStats.firstStage.setRocketDetailPrice(0);
				InfoAndStats.firstStage.setRocketDetailThrust(0.0F);
				InfoAndStats.firstStage.setRocketDetailTime(0.0F);
				InfoAndStats.secondStage.setRocketDetailAccessibility(false);
				InfoAndStats.secondStage.setRocketDetailAmount(0);
				InfoAndStats.secondStage.setRocketDetailImpulse(0.0F);
				InfoAndStats.secondStage.setRocketDetailName("", "");
				InfoAndStats.secondStage.setRocketDetailPrice(0);
				InfoAndStats.secondStage.setRocketDetailThrust(0.0F);
				InfoAndStats.secondStage.setRocketDetailTime(0.0F);
				InfoAndStats.thirdStage.setRocketDetailAccessibility(false);
				InfoAndStats.thirdStage.setRocketDetailAmount(0);
				InfoAndStats.thirdStage.setRocketDetailImpulse(0.0F);
				InfoAndStats.thirdStage.setRocketDetailName("", "");
				InfoAndStats.thirdStage.setRocketDetailPrice(0);
				InfoAndStats.thirdStage.setRocketDetailThrust(0.0F);
				InfoAndStats.thirdStage.setRocketDetailTime(0.0F);
				RocketDetailStats.totalName = "";
				RocketDetailStats.totalSpecificImpulse = 0.0F;
				RocketDetailStats.totalThrust = 0.0F;
				RocketDetailStats.totalWorkingTime = 0.0F;
				GameScreen.isNextDayDraw = true;
				InfoAndStats.date++;
				this.dispose();
			}
		}else if(!isSuccess && (thirdWT == 0.0F && secondWT == 0.0F && firstWT == 0.0F)){
			if(!InfoAndStats.lngRussian){
				textFail.draw(batch, "Launch is falied", 0.01F*width, camera.position.y + 0.49F*height);
			}else{
				textFail.draw(batch, "Запуск провален", 0.01F*width, camera.position.y + 0.49F*height);
			}
			timer += delta;
			if(timer > 5.0F){
				InfoAndStats.hasOrder = false;
				InfoAndStats.launch++;
				InfoAndStats.currentOrder.setOrderLevel(0);
				InfoAndStats.currentOrder.setOrderMass(0.0F);
				InfoAndStats.currentOrder.setOrderName("");
				InfoAndStats.currentOrder.setOrderReward(0);
				if(InfoAndStats.selectedFirst.equals("UT_1202")) InfoAndStats.UT_1202.setRocketDetailAmount(InfoAndStats.UT_1202.getRocketDetailAmount() -1);
				if(InfoAndStats.selectedFirst.equals("UM_1034")) InfoAndStats.UM_1034.setRocketDetailAmount(InfoAndStats.UM_1034.getRocketDetailAmount() -1);
				if(InfoAndStats.selectedFirst.equals("UF_02")) InfoAndStats.UF_02.setRocketDetailAmount(InfoAndStats.UF_02.getRocketDetailAmount() -1);
				if(InfoAndStats.selectedSecond.equals("MT_0112")) InfoAndStats.MT_0112.setRocketDetailAmount(InfoAndStats.MT_0112.getRocketDetailAmount() -1);
				if(InfoAndStats.selectedSecond.equals("MM_4")) InfoAndStats.MM_4.setRocketDetailAmount(InfoAndStats.MM_4.getRocketDetailAmount() -1);
				if(InfoAndStats.selectedSecond.equals("MF_043")) InfoAndStats.MF_043.setRocketDetailAmount(InfoAndStats.MF_043.getRocketDetailAmount() -1);
				if(InfoAndStats.selectedThird.equals("LT_116")) InfoAndStats.LT_116.setRocketDetailAmount(InfoAndStats.LT_116.getRocketDetailAmount() -1);
				if(InfoAndStats.selectedThird.equals("LM_087")) InfoAndStats.LM_087.setRocketDetailAmount(InfoAndStats.LM_087.getRocketDetailAmount() -1);
				if(InfoAndStats.selectedThird.equals("LF_15")) InfoAndStats.LF_15.setRocketDetailAmount(InfoAndStats.LF_15.getRocketDetailAmount() -1);
				InfoAndStats.selectedFirst = "null";
				InfoAndStats.selectedSecond = "null";
				InfoAndStats.selectedThird = "null";
				InfoAndStats.firstStage.setRocketDetailAccessibility(false);
				InfoAndStats.firstStage.setRocketDetailAmount(0);
				InfoAndStats.firstStage.setRocketDetailImpulse(0.0F);
				InfoAndStats.firstStage.setRocketDetailName("", "");
				InfoAndStats.firstStage.setRocketDetailPrice(0);
				InfoAndStats.firstStage.setRocketDetailThrust(0.0F);
				InfoAndStats.firstStage.setRocketDetailTime(0.0F);
				InfoAndStats.secondStage.setRocketDetailAccessibility(false);
				InfoAndStats.secondStage.setRocketDetailAmount(0);
				InfoAndStats.secondStage.setRocketDetailImpulse(0.0F);
				InfoAndStats.secondStage.setRocketDetailName("", "");
				InfoAndStats.secondStage.setRocketDetailPrice(0);
				InfoAndStats.secondStage.setRocketDetailThrust(0.0F);
				InfoAndStats.secondStage.setRocketDetailTime(0.0F);
				InfoAndStats.thirdStage.setRocketDetailAccessibility(false);
				InfoAndStats.thirdStage.setRocketDetailAmount(0);
				InfoAndStats.thirdStage.setRocketDetailImpulse(0.0F);
				InfoAndStats.thirdStage.setRocketDetailName("", "");
				InfoAndStats.thirdStage.setRocketDetailPrice(0);
				InfoAndStats.thirdStage.setRocketDetailThrust(0.0F);
				InfoAndStats.thirdStage.setRocketDetailTime(0.0F);
				RocketDetailStats.totalName = "";
				RocketDetailStats.totalSpecificImpulse = 0.0F;
				RocketDetailStats.totalThrust = 0.0F;
				RocketDetailStats.totalWorkingTime = 0.0F;
				GameScreen.isNextDayDraw = true;
				InfoAndStats.date++;
				this.dispose();
			}
		}else if(!isSuccess && InfoAndStats.getFirstStageFlight().getY() <= height/2){
			if(!InfoAndStats.lngRussian){
				textFail.draw(batch, "Launch is falied", 0.01F*width, camera.position.y + 0.49F*height);
			}else{
				textFail.draw(batch, "Запуск провален", 0.01F*width, camera.position.y + 0.49F*height);
			}
			timer += delta;
			if(timer > 5.0F){
				InfoAndStats.hasOrder = false;
				InfoAndStats.launch++;
				InfoAndStats.currentOrder.setOrderLevel(0);
				InfoAndStats.currentOrder.setOrderMass(0.0F);
				InfoAndStats.currentOrder.setOrderName("null");
				InfoAndStats.currentOrder.setOrderReward(0);
				if(InfoAndStats.selectedFirst.equals("UT_1202")) InfoAndStats.UT_1202.setRocketDetailAmount(InfoAndStats.UT_1202.getRocketDetailAmount() -1);
				if(InfoAndStats.selectedFirst.equals("UM_1034")) InfoAndStats.UM_1034.setRocketDetailAmount(InfoAndStats.UM_1034.getRocketDetailAmount() -1);
				if(InfoAndStats.selectedFirst.equals("UF_02")) InfoAndStats.UF_02.setRocketDetailAmount(InfoAndStats.UF_02.getRocketDetailAmount() -1);
				if(InfoAndStats.selectedSecond.equals("MT_0112")) InfoAndStats.MT_0112.setRocketDetailAmount(InfoAndStats.MT_0112.getRocketDetailAmount() -1);
				if(InfoAndStats.selectedSecond.equals("MM_4")) InfoAndStats.MM_4.setRocketDetailAmount(InfoAndStats.MM_4.getRocketDetailAmount() -1);
				if(InfoAndStats.selectedSecond.equals("MF_043")) InfoAndStats.MF_043.setRocketDetailAmount(InfoAndStats.MF_043.getRocketDetailAmount() -1);
				if(InfoAndStats.selectedThird.equals("LT_116")) InfoAndStats.LT_116.setRocketDetailAmount(InfoAndStats.LT_116.getRocketDetailAmount() -1);
				if(InfoAndStats.selectedThird.equals("LM_087")) InfoAndStats.LM_087.setRocketDetailAmount(InfoAndStats.LM_087.getRocketDetailAmount() -1);
				if(InfoAndStats.selectedThird.equals("LF_15")) InfoAndStats.LF_15.setRocketDetailAmount(InfoAndStats.LF_15.getRocketDetailAmount() -1);
				InfoAndStats.selectedFirst = "null";
				InfoAndStats.selectedSecond = "null";
				InfoAndStats.selectedThird = "null";
				InfoAndStats.firstStage.setRocketDetailAccessibility(false);
				InfoAndStats.firstStage.setRocketDetailAmount(0);
				InfoAndStats.firstStage.setRocketDetailImpulse(0.0F);
				InfoAndStats.firstStage.setRocketDetailName("", "");
				InfoAndStats.firstStage.setRocketDetailPrice(0);
				InfoAndStats.firstStage.setRocketDetailThrust(0.0F);
				InfoAndStats.firstStage.setRocketDetailTime(0.0F);
				InfoAndStats.secondStage.setRocketDetailAccessibility(false);
				InfoAndStats.secondStage.setRocketDetailAmount(0);
				InfoAndStats.secondStage.setRocketDetailImpulse(0.0F);
				InfoAndStats.secondStage.setRocketDetailName("", "");
				InfoAndStats.secondStage.setRocketDetailPrice(0);
				InfoAndStats.secondStage.setRocketDetailThrust(0.0F);
				InfoAndStats.secondStage.setRocketDetailTime(0.0F);
				InfoAndStats.thirdStage.setRocketDetailAccessibility(false);
				InfoAndStats.thirdStage.setRocketDetailAmount(0);
				InfoAndStats.thirdStage.setRocketDetailImpulse(0.0F);
				InfoAndStats.thirdStage.setRocketDetailName("", "");
				InfoAndStats.thirdStage.setRocketDetailPrice(0);
				InfoAndStats.thirdStage.setRocketDetailThrust(0.0F);
				InfoAndStats.thirdStage.setRocketDetailTime(0.0F);
				RocketDetailStats.totalName = "";
				RocketDetailStats.totalSpecificImpulse = 0.0F;
				RocketDetailStats.totalThrust = 0.0F;
				RocketDetailStats.totalWorkingTime = 0.0F;
				GameScreen.isNextDayDraw = true;
				InfoAndStats.date++;
				this.dispose();
			}
		}else if(!isSuccess && isOver){
			if(!InfoAndStats.lngRussian){
				textFail.draw(batch, "Launch is falied", 0.01F*width, camera.position.y + 0.49F*height);
			}else{
				textFail.draw(batch, "Запуск провален", 0.01F*width, camera.position.y + 0.49F*height);
			}
			timer += delta;
			if(timer > 5.0F){
				InfoAndStats.hasOrder = false;
				InfoAndStats.launch++;
				InfoAndStats.currentOrder.setOrderLevel(0);
				InfoAndStats.currentOrder.setOrderMass(0.0F);
				InfoAndStats.currentOrder.setOrderName("null");
				InfoAndStats.currentOrder.setOrderReward(0);
				if(InfoAndStats.selectedFirst.equals("UT_1202")) InfoAndStats.UT_1202.setRocketDetailAmount(InfoAndStats.UT_1202.getRocketDetailAmount() -1);
				if(InfoAndStats.selectedFirst.equals("UM_1034")) InfoAndStats.UM_1034.setRocketDetailAmount(InfoAndStats.UM_1034.getRocketDetailAmount() -1);
				if(InfoAndStats.selectedFirst.equals("UF_02")) InfoAndStats.UF_02.setRocketDetailAmount(InfoAndStats.UF_02.getRocketDetailAmount() -1);
				if(InfoAndStats.selectedSecond.equals("MT_0112")) InfoAndStats.MT_0112.setRocketDetailAmount(InfoAndStats.MT_0112.getRocketDetailAmount() -1);
				if(InfoAndStats.selectedSecond.equals("MM_4")) InfoAndStats.MM_4.setRocketDetailAmount(InfoAndStats.MM_4.getRocketDetailAmount() -1);
				if(InfoAndStats.selectedSecond.equals("MF_043")) InfoAndStats.MF_043.setRocketDetailAmount(InfoAndStats.MF_043.getRocketDetailAmount() -1);
				if(InfoAndStats.selectedThird.equals("LT_116")) InfoAndStats.LT_116.setRocketDetailAmount(InfoAndStats.LT_116.getRocketDetailAmount() -1);
				if(InfoAndStats.selectedThird.equals("LM_087")) InfoAndStats.LM_087.setRocketDetailAmount(InfoAndStats.LM_087.getRocketDetailAmount() -1);
				if(InfoAndStats.selectedThird.equals("LF_15")) InfoAndStats.LF_15.setRocketDetailAmount(InfoAndStats.LF_15.getRocketDetailAmount() -1);
				InfoAndStats.selectedFirst = "null";
				InfoAndStats.selectedSecond = "null";
				InfoAndStats.selectedThird = "null";
				InfoAndStats.firstStage.setRocketDetailAccessibility(false);
				InfoAndStats.firstStage.setRocketDetailAmount(0);
				InfoAndStats.firstStage.setRocketDetailImpulse(0.0F);
				InfoAndStats.firstStage.setRocketDetailName("", "");
				InfoAndStats.firstStage.setRocketDetailPrice(0);
				InfoAndStats.firstStage.setRocketDetailThrust(0.0F);
				InfoAndStats.firstStage.setRocketDetailTime(0.0F);
				InfoAndStats.secondStage.setRocketDetailAccessibility(false);
				InfoAndStats.secondStage.setRocketDetailAmount(0);
				InfoAndStats.secondStage.setRocketDetailImpulse(0.0F);
				InfoAndStats.secondStage.setRocketDetailName("", "");
				InfoAndStats.secondStage.setRocketDetailPrice(0);
				InfoAndStats.secondStage.setRocketDetailThrust(0.0F);
				InfoAndStats.secondStage.setRocketDetailTime(0.0F);
				InfoAndStats.thirdStage.setRocketDetailAccessibility(false);
				InfoAndStats.thirdStage.setRocketDetailAmount(0);
				InfoAndStats.thirdStage.setRocketDetailImpulse(0.0F);
				InfoAndStats.thirdStage.setRocketDetailName("", "");
				InfoAndStats.thirdStage.setRocketDetailPrice(0);
				InfoAndStats.thirdStage.setRocketDetailThrust(0.0F);
				InfoAndStats.thirdStage.setRocketDetailTime(0.0F);
				RocketDetailStats.totalName = "";
				RocketDetailStats.totalSpecificImpulse = 0.0F;
				RocketDetailStats.totalThrust = 0.0F;
				RocketDetailStats.totalWorkingTime = 0.0F;
				GameScreen.isNextDayDraw = true;
				InfoAndStats.date++;
				this.dispose();
			}
		}
	}
	private void drawMeteor(){
		if(!isOver){
			if(meteor1Sprite.getX() < camera.position.x + width/2){
				meteor1Sprite.setX(meteor1Sprite.getX() + 0.5F + rand.nextFloat());
			}else{
				meteor1Sprite.setX(camera.position.x - rand.nextFloat()*width/2 - meteor1Sprite.getWidth());
			}
			if(meteor1Sprite.getY() > camera.position.y - height/2 - meteor1Sprite.getWidth()){
				meteor1Sprite.setY(meteor1Sprite.getY() - angle1);
			}else{
				meteor1Sprite.setY(camera.position.y + height/2 + rand.nextFloat()*height);
			}
			//**\\
			if(meteor2Sprite.getX() < camera.position.x + width/2){
				meteor2Sprite.setX(meteor2Sprite.getX() + 0.5F + rand.nextFloat());
			}else{
				meteor2Sprite.setX(camera.position.x - rand.nextFloat()*width/2 - meteor2Sprite.getWidth());
			}
			if(meteor2Sprite.getY() > camera.position.y - height/2 - meteor2Sprite.getWidth()){
				meteor2Sprite.setY(meteor2Sprite.getY() - angle2);
			}else{
				meteor2Sprite.setY(camera.position.y + height/2 + rand.nextFloat()*height);
			}
			//**\\
			if(meteor3Sprite.getX() > camera.position.x - width/2 - meteor3Sprite.getWidth()){
				meteor3Sprite.setX(meteor3Sprite.getX() - 0.5F - rand.nextFloat());
			}else{
				meteor3Sprite.setX(camera.position.x + rand.nextFloat()*width/2);
			}
			if(meteor3Sprite.getY() > camera.position.y - height/2 - meteor3Sprite.getWidth()){
				meteor3Sprite.setY(meteor3Sprite.getY() - angle3);
			}else{
				meteor3Sprite.setY(camera.position.y + height/2 + rand.nextFloat()*height);
			}
			//**\\
			if(meteor4Sprite.getX() > camera.position.x - width/2 - meteor4Sprite.getWidth()){
				meteor4Sprite.setX(meteor4Sprite.getX() - 0.5F - rand.nextFloat());
			}else{
				meteor4Sprite.setX(camera.position.x + rand.nextFloat()*width/2);
			}
			if(meteor4Sprite.getY() > camera.position.y - height/2 - meteor4Sprite.getWidth()){
				meteor4Sprite.setY(meteor4Sprite.getY() - angle4);
			}else{
				meteor4Sprite.setY(camera.position.y + height/2 + rand.nextFloat()*height);
			}
			meteor1Sprite.rotate(rotation1);
			meteor2Sprite.rotate(rotation2);
			meteor3Sprite.rotate(rotation3);
			meteor4Sprite.rotate(rotation4);
		}
		meteor1Sprite.draw(batch);
		meteor2Sprite.draw(batch);
		meteor3Sprite.draw(batch);
		meteor4Sprite.draw(batch);
	}
	private void drawMeteorSmoke(float delta){
		if(smokeDraw1){
			if(timer1 > 0.0F){
				timer1 -= 2.0F*delta;
				for(float i=0;i<2*Math.PI;i+=Math.PI/12){
					smokeMeteorSprite.setX((float)(mX1 + mW1/2.0F + (60.0F - timer1*15.0F)*(Math.cos(i))));
					smokeMeteorSprite.setY((float)(mY1 + mH1/2.0F + (60.0F - timer1*15.0F)*(Math.sin(i))));
					smokeMeteorSprite.setOriginCenter();
					smokeMeteorSprite.rotate(rand.nextFloat());
					smokeMeteorSprite.draw(batch);
				}
				for(float i=0;i<2*Math.PI;i+=Math.PI/6){
					smokeYellowSprite.setX((float)(mX1 + mW1/2.0F + (30.0F - timer1*7.5F)*(Math.cos(i))));
					smokeYellowSprite.setY((float)(mY1 + mH1/2.0F + (30.0F - timer1*7.5F)*(Math.sin(i))));
					smokeYellowSprite.setOriginCenter();
					smokeYellowSprite.rotate(rand.nextFloat());
					smokeYellowSprite.draw(batch);
				}
			}else{
				timer1 = 4.0F;
				smokeDraw1 = false;
			}
		}
		if(smokeDraw2){
			if(timer2 > 0.0F){
				timer2 -= 2.0F*delta;
				for(float i=0;i<2*Math.PI;i+=Math.PI/12){
					smokeMeteorSprite.setX((float)(mX2 + mW2/2.0F + (60.0F - timer2*15.0F)*(Math.cos(i))));
					smokeMeteorSprite.setY((float)(mY2 + mH2/2.0F + (60.0F - timer2*15.0F)*(Math.sin(i))));
					smokeMeteorSprite.setOriginCenter();
					smokeMeteorSprite.rotate(rand.nextFloat());
					smokeMeteorSprite.draw(batch);
				}
				for(float i=0;i<2*Math.PI;i+=Math.PI/6){
					smokeYellowSprite.setX((float)(mX2 + mW2/2.0F + (30.0F - timer2*7.5F)*(Math.cos(i))));
					smokeYellowSprite.setY((float)(mY2 + mH2/2.0F + (30.0F - timer2*7.5F)*(Math.sin(i))));
					smokeYellowSprite.setOriginCenter();
					smokeYellowSprite.rotate(rand.nextFloat());
					smokeYellowSprite.draw(batch);
				}
			}else{
				timer2 = 4.0F;
				smokeDraw2 = false;
			}
		}
		if(smokeDraw3){
			if(timer3 > 0.0F){
				timer3 -= 2.0F*delta;
				for(float i=0;i<2*Math.PI;i+=Math.PI/12){
					smokeMeteorSprite.setX((float)(mX3 + mW3/2.0F + (60.0F - timer3*15.0F)*(Math.cos(i))));
					smokeMeteorSprite.setY((float)(mY3 + mH3/2.0F + (60.0F - timer3*15.0F)*(Math.sin(i))));
					smokeMeteorSprite.setOriginCenter();
					smokeMeteorSprite.rotate(rand.nextFloat());
					smokeMeteorSprite.draw(batch);
				}
				for(float i=0;i<2*Math.PI;i+=Math.PI/6){
					smokeYellowSprite.setX((float)(mX3 + mW3/2.0F + (30.0F - timer3*7.5F)*(Math.cos(i))));
					smokeYellowSprite.setY((float)(mY3 + mH3/2.0F + (30.0F - timer3*7.5F)*(Math.sin(i))));
					smokeYellowSprite.setOriginCenter();
					smokeYellowSprite.rotate(rand.nextFloat());
					smokeYellowSprite.draw(batch);
				}
			}else{
				timer3 = 4.0F;
				smokeDraw3 = false;
			}
		}
		if(smokeDraw4){
			if(timer4 > 0.0F){
				timer4 -= 2.0F*delta;
				for(float i=0;i<2*Math.PI;i+=Math.PI/12){
					smokeMeteorSprite.setX((float)(mX4 + mW4/2.0F + (60.0F - timer4*15.0F)*(Math.cos(i))));
					smokeMeteorSprite.setY((float)(mY4 + mH4/2.0F + (60.0F - timer4*15.0F)*(Math.sin(i))));
					smokeMeteorSprite.setOriginCenter();
					smokeMeteorSprite.rotate(rand.nextFloat());
					smokeMeteorSprite.draw(batch);
				}
				for(float i=0;i<2*Math.PI;i+=Math.PI/6){
					smokeYellowSprite.setX((float)(mX4 + mW4/2.0F + (30.0F - timer4*7.5F)*(Math.cos(i))));
					smokeYellowSprite.setY((float)(mY4 + mH4/2.0F + (30.0F - timer4*7.5F)*(Math.sin(i))));
					smokeYellowSprite.setOriginCenter();
					smokeYellowSprite.rotate(rand.nextFloat());
					smokeYellowSprite.draw(batch);
				}
			}else{
				timer4 = 4.0F;
				smokeDraw4 = false;
			}
		}
	}
	
	private void destroyCheck(){
		if(meteor1Sprite.getX()+meteor1Sprite.getWidth() >= InfoAndStats.getFirstStageFlight().getX()+0.3F*InfoAndStats.getFirstStageFlight().getWidth() && meteor1Sprite.getX() <= InfoAndStats.getFirstStageFlight().getX()+0.7F*InfoAndStats.getFirstStageFlight().getWidth()){
			if(meteor1Sprite.getY()+meteor1Sprite.getHeight() >= InfoAndStats.getFirstStageFlight().getY() && meteor1Sprite.getY() <= InfoAndStats.getFirstStageFlight().getY()+InfoAndStats.getFirstStageFlight().getHeight()){
				isOver = true;
				isSuccess = false;
				speed = 0.0F;
				meteor1Sprite.setX(meteor1Sprite.getX());
				meteor1Sprite.setY(meteor1Sprite.getY());
				meteor2Sprite.setX(meteor2Sprite.getX());
				meteor2Sprite.setY(meteor2Sprite.getY());
				meteor3Sprite.setX(meteor3Sprite.getX());
				meteor3Sprite.setY(meteor3Sprite.getY());
				meteor4Sprite.setX(meteor4Sprite.getX());
				meteor4Sprite.setY(meteor4Sprite.getY());
			}
		}
		if(meteor2Sprite.getX()+meteor2Sprite.getWidth() >= InfoAndStats.getFirstStageFlight().getX()+0.3F*InfoAndStats.getFirstStageFlight().getWidth() && meteor2Sprite.getX() <= InfoAndStats.getFirstStageFlight().getX()+0.7F*InfoAndStats.getFirstStageFlight().getWidth()){
			if(meteor2Sprite.getY()+meteor2Sprite.getHeight() >= InfoAndStats.getFirstStageFlight().getY() && meteor2Sprite.getY() <= InfoAndStats.getFirstStageFlight().getY()+InfoAndStats.getFirstStageFlight().getHeight()){
				isOver = true;
				isSuccess = false;
				speed = 0.0F;
				meteor1Sprite.setX(meteor1Sprite.getX());
				meteor1Sprite.setY(meteor1Sprite.getY());
				meteor2Sprite.setX(meteor2Sprite.getX());
				meteor2Sprite.setY(meteor2Sprite.getY());
				meteor3Sprite.setX(meteor3Sprite.getX());
				meteor3Sprite.setY(meteor3Sprite.getY());
				meteor4Sprite.setX(meteor4Sprite.getX());
				meteor4Sprite.setY(meteor4Sprite.getY());
			}
		}
		if(meteor3Sprite.getX()+meteor3Sprite.getWidth() >= InfoAndStats.getFirstStageFlight().getX()+0.3F*InfoAndStats.getFirstStageFlight().getWidth() && meteor3Sprite.getX() <= InfoAndStats.getFirstStageFlight().getX()+0.7F*InfoAndStats.getFirstStageFlight().getWidth()){
			if(meteor3Sprite.getY()+meteor3Sprite.getHeight() >= InfoAndStats.getFirstStageFlight().getY() && meteor3Sprite.getY() <= InfoAndStats.getFirstStageFlight().getY()+InfoAndStats.getFirstStageFlight().getHeight()){
				isOver = true;
				isSuccess = false;
				speed = 0.0F;
				meteor1Sprite.setX(meteor1Sprite.getX());
				meteor1Sprite.setY(meteor1Sprite.getY());
				meteor2Sprite.setX(meteor2Sprite.getX());
				meteor2Sprite.setY(meteor2Sprite.getY());
				meteor3Sprite.setX(meteor3Sprite.getX());
				meteor3Sprite.setY(meteor3Sprite.getY());
				meteor4Sprite.setX(meteor4Sprite.getX());
				meteor4Sprite.setY(meteor4Sprite.getY());
			}
		}
		if(meteor4Sprite.getX()+meteor4Sprite.getWidth() >= InfoAndStats.getFirstStageFlight().getX()+0.3F*InfoAndStats.getFirstStageFlight().getWidth() && meteor4Sprite.getX() <= InfoAndStats.getFirstStageFlight().getX()+0.7F*InfoAndStats.getFirstStageFlight().getWidth()){
			if(meteor4Sprite.getY()+meteor4Sprite.getHeight() >= InfoAndStats.getFirstStageFlight().getY() && meteor4Sprite.getY() <= InfoAndStats.getFirstStageFlight().getY()+InfoAndStats.getFirstStageFlight().getHeight()){
				isOver = true;
				isSuccess = false;
				speed = 0.0F;
				meteor1Sprite.setX(meteor1Sprite.getX());
				meteor1Sprite.setY(meteor1Sprite.getY());
				meteor2Sprite.setX(meteor2Sprite.getX());
				meteor2Sprite.setY(meteor2Sprite.getY());
				meteor3Sprite.setX(meteor3Sprite.getX());
				meteor3Sprite.setY(meteor3Sprite.getY());
				meteor4Sprite.setX(meteor4Sprite.getX());
				meteor4Sprite.setY(meteor4Sprite.getY());
			}
		}
		if(hasSecondStage){
			if(meteor1Sprite.getX()+meteor1Sprite.getWidth() >= InfoAndStats.getSecondStageFlight().getX()+0.4F*InfoAndStats.getSecondStageFlight().getWidth() && meteor1Sprite.getX() <= InfoAndStats.getSecondStageFlight().getX()+0.6F*InfoAndStats.getSecondStageFlight().getWidth()){
				if(meteor1Sprite.getY()+meteor1Sprite.getHeight() >= InfoAndStats.getSecondStageFlight().getY() && meteor1Sprite.getY() <= InfoAndStats.getSecondStageFlight().getY()+InfoAndStats.getSecondStageFlight().getHeight()){
					isOver = true;
					isSuccess = false;
					speed = 0.0F;
					meteor1Sprite.setX(meteor1Sprite.getX());
					meteor1Sprite.setY(meteor1Sprite.getY());
					meteor2Sprite.setX(meteor2Sprite.getX());
					meteor2Sprite.setY(meteor2Sprite.getY());
					meteor3Sprite.setX(meteor3Sprite.getX());
					meteor3Sprite.setY(meteor3Sprite.getY());
					meteor4Sprite.setX(meteor4Sprite.getX());
					meteor4Sprite.setY(meteor4Sprite.getY());
				}
			}
			if(meteor2Sprite.getX()+meteor2Sprite.getWidth() >= InfoAndStats.getSecondStageFlight().getX()+0.4F*InfoAndStats.getSecondStageFlight().getWidth() && meteor2Sprite.getX() <= InfoAndStats.getSecondStageFlight().getX()+0.6F*InfoAndStats.getSecondStageFlight().getWidth()){
				if(meteor2Sprite.getY()+meteor2Sprite.getHeight() >= InfoAndStats.getSecondStageFlight().getY() && meteor2Sprite.getY() <= InfoAndStats.getSecondStageFlight().getY()+InfoAndStats.getSecondStageFlight().getHeight()){
					isOver = true;
					isSuccess = false;
					speed = 0.0F;
					meteor1Sprite.setX(meteor1Sprite.getX());
					meteor1Sprite.setY(meteor1Sprite.getY());
					meteor2Sprite.setX(meteor2Sprite.getX());
					meteor2Sprite.setY(meteor2Sprite.getY());
					meteor3Sprite.setX(meteor3Sprite.getX());
					meteor3Sprite.setY(meteor3Sprite.getY());
					meteor4Sprite.setX(meteor4Sprite.getX());
					meteor4Sprite.setY(meteor4Sprite.getY());
				}
			}
			if(meteor3Sprite.getX()+meteor3Sprite.getWidth() >= InfoAndStats.getSecondStageFlight().getX()+0.4F*InfoAndStats.getSecondStageFlight().getWidth() && meteor3Sprite.getX() <= InfoAndStats.getSecondStageFlight().getX()+0.6F*InfoAndStats.getSecondStageFlight().getWidth()){
				if(meteor3Sprite.getY()+meteor3Sprite.getHeight() >= InfoAndStats.getSecondStageFlight().getY() && meteor3Sprite.getY() <= InfoAndStats.getSecondStageFlight().getY()+InfoAndStats.getSecondStageFlight().getHeight()){
					isOver = true;
					isSuccess = false;
					speed = 0.0F;
					meteor1Sprite.setX(meteor1Sprite.getX());
					meteor1Sprite.setY(meteor1Sprite.getY());
					meteor2Sprite.setX(meteor2Sprite.getX());
					meteor2Sprite.setY(meteor2Sprite.getY());
					meteor3Sprite.setX(meteor3Sprite.getX());
					meteor3Sprite.setY(meteor3Sprite.getY());
					meteor4Sprite.setX(meteor4Sprite.getX());
					meteor4Sprite.setY(meteor4Sprite.getY());
				}
			}
			if(meteor4Sprite.getX()+meteor4Sprite.getWidth() >= InfoAndStats.getSecondStageFlight().getX()+0.4F*InfoAndStats.getSecondStageFlight().getWidth() && meteor4Sprite.getX() <= InfoAndStats.getSecondStageFlight().getX()+0.6F*InfoAndStats.getSecondStageFlight().getWidth()){
				if(meteor4Sprite.getY()+meteor4Sprite.getHeight() >= InfoAndStats.getSecondStageFlight().getY() && meteor4Sprite.getY() <= InfoAndStats.getSecondStageFlight().getY()+InfoAndStats.getSecondStageFlight().getHeight()){
					isOver = true;
					isSuccess = false;
					speed = 0.0F;
					meteor1Sprite.setX(meteor1Sprite.getX());
					meteor1Sprite.setY(meteor1Sprite.getY());
					meteor2Sprite.setX(meteor2Sprite.getX());
					meteor2Sprite.setY(meteor2Sprite.getY());
					meteor3Sprite.setX(meteor3Sprite.getX());
					meteor3Sprite.setY(meteor3Sprite.getY());
					meteor4Sprite.setX(meteor4Sprite.getX());
					meteor4Sprite.setY(meteor4Sprite.getY());
				}
			}
		}
		if(hasThirdStage){
			if(meteor1Sprite.getX()+meteor1Sprite.getWidth() >= InfoAndStats.getThirdStageFlight().getX()+0.25F*InfoAndStats.getThirdStageFlight().getWidth() && meteor1Sprite.getX() <= InfoAndStats.getThirdStageFlight().getX()+0.75F*InfoAndStats.getThirdStageFlight().getWidth()){
				if(meteor1Sprite.getY()+meteor1Sprite.getHeight() >= InfoAndStats.getThirdStageFlight().getY() && meteor1Sprite.getY() <= InfoAndStats.getThirdStageFlight().getY()+InfoAndStats.getThirdStageFlight().getHeight()){
					isOver = true;
					isSuccess = false;
					speed = 0.0F;
					meteor1Sprite.setX(meteor1Sprite.getX());
					meteor1Sprite.setY(meteor1Sprite.getY());
					meteor2Sprite.setX(meteor2Sprite.getX());
					meteor2Sprite.setY(meteor2Sprite.getY());
					meteor3Sprite.setX(meteor3Sprite.getX());
					meteor3Sprite.setY(meteor3Sprite.getY());
					meteor4Sprite.setX(meteor4Sprite.getX());
					meteor4Sprite.setY(meteor4Sprite.getY());
				}
			}
			if(meteor2Sprite.getX()+meteor2Sprite.getWidth() >= InfoAndStats.getThirdStageFlight().getX()+0.25F*InfoAndStats.getThirdStageFlight().getWidth() && meteor2Sprite.getX() <= InfoAndStats.getThirdStageFlight().getX()+0.75F*InfoAndStats.getThirdStageFlight().getWidth()){
				if(meteor2Sprite.getY()+meteor2Sprite.getHeight() >= InfoAndStats.getThirdStageFlight().getY() && meteor2Sprite.getY()+meteor2Sprite.getHeight() <= InfoAndStats.getThirdStageFlight().getY()+InfoAndStats.getThirdStageFlight().getHeight()){
					isOver = true;
					isSuccess = false;
					speed = 0.0F;
					meteor1Sprite.setX(meteor1Sprite.getX());
					meteor1Sprite.setY(meteor1Sprite.getY());
					meteor2Sprite.setX(meteor2Sprite.getX());
					meteor2Sprite.setY(meteor2Sprite.getY());
					meteor3Sprite.setX(meteor3Sprite.getX());
					meteor3Sprite.setY(meteor3Sprite.getY());
					meteor4Sprite.setX(meteor4Sprite.getX());
					meteor4Sprite.setY(meteor4Sprite.getY());
				}
			}
			if(meteor3Sprite.getX()+meteor3Sprite.getWidth() >= InfoAndStats.getThirdStageFlight().getX()+0.25F*InfoAndStats.getThirdStageFlight().getWidth() && meteor3Sprite.getX() <= InfoAndStats.getThirdStageFlight().getX()+0.75F*InfoAndStats.getThirdStageFlight().getWidth()){
				if(meteor3Sprite.getY()+meteor3Sprite.getHeight() >= InfoAndStats.getThirdStageFlight().getY() && meteor3Sprite.getY() <= InfoAndStats.getThirdStageFlight().getY()+InfoAndStats.getThirdStageFlight().getHeight()){
					isOver = true;
					isSuccess = false;
					speed = 0.0F;
					meteor1Sprite.setX(meteor1Sprite.getX());
					meteor1Sprite.setY(meteor1Sprite.getY());
					meteor2Sprite.setX(meteor2Sprite.getX());
					meteor2Sprite.setY(meteor2Sprite.getY());
					meteor3Sprite.setX(meteor3Sprite.getX());
					meteor3Sprite.setY(meteor3Sprite.getY());
					meteor4Sprite.setX(meteor4Sprite.getX());
					meteor4Sprite.setY(meteor4Sprite.getY());
				}
			}
			if(meteor4Sprite.getX()+meteor4Sprite.getWidth() >= InfoAndStats.getThirdStageFlight().getX()+0.25F*InfoAndStats.getThirdStageFlight().getWidth() && meteor4Sprite.getX() <= InfoAndStats.getThirdStageFlight().getX()+0.75F*InfoAndStats.getThirdStageFlight().getWidth()){
				if(meteor4Sprite.getY()+meteor4Sprite.getHeight() >= InfoAndStats.getThirdStageFlight().getY() && meteor4Sprite.getY() <= InfoAndStats.getThirdStageFlight().getY()+InfoAndStats.getThirdStageFlight().getHeight()){
					isOver = true;
					isSuccess = false;
					speed = 0.0F;
					meteor1Sprite.setX(meteor1Sprite.getX());
					meteor1Sprite.setY(meteor1Sprite.getY());
					meteor2Sprite.setX(meteor2Sprite.getX());
					meteor2Sprite.setY(meteor2Sprite.getY());
					meteor3Sprite.setX(meteor3Sprite.getX());
					meteor3Sprite.setY(meteor3Sprite.getY());
					meteor4Sprite.setX(meteor4Sprite.getX());
					meteor4Sprite.setY(meteor4Sprite.getY());
				}
			}
		}
	}
	private void btnListeners(){
		if(!isOver){
			if(hasThirdStage){
				if(controller.isClickedFlight(third1X, third1Y, third1Width, third1Height)){
					detachSound.play();
					if(!flightSound.isPlaying()){
						flightSound.play();
					}
					hasThirdStage = false;
					float difference = InfoAndStats.currentOrder.getOrderMass() - RocketDetailStats.totalThrust;
					if(difference > 0.0F)
						speed = RocketDetailStats.totalSpecificImpulse - difference;
					else
						speed = RocketDetailStats.totalSpecificImpulse; 
				}
			}
			if(!hasThirdStage && hasSecondStage){
				if(controller.isClickedFlight(second1X, second1Y, second1Width, second1Height)){
					detachSound.play();
					if(!flightSound.isPlaying()){
						flightSound.play();
					}
					hasSecondStage = false;
					float difference = InfoAndStats.currentOrder.getOrderMass() - RocketDetailStats.totalThrust;
					if(difference > 0.0F)
						speed = RocketDetailStats.totalSpecificImpulse - difference;
					else
						speed = RocketDetailStats.totalSpecificImpulse; 
				}
			}
			if(controller.isClickedFlight(meteor1Sprite.getX(), meteor1Sprite.getY(), meteor1Sprite.getWidth(), meteor1Sprite.getHeight())){
				mX1 = meteor1Sprite.getX();
				mY1 = meteor1Sprite.getY();
				mW1 = meteor1Sprite.getWidth();
				mH1 = meteor1Sprite.getHeight();
				smokeDraw1 = true;
				meteor1Sprite.setX(camera.position.x - rand.nextFloat()*width/2 - meteor1Sprite.getWidth());
				meteor1Sprite.setY(camera.position.y + height/2 + rand.nextFloat()*height);
				if(explosionSound.isPlaying()) explosionSound.stop();
				explosionSound.play();
			}
			if(controller.isClickedFlight(meteor2Sprite.getX(), meteor2Sprite.getY(), meteor2Sprite.getWidth(), meteor2Sprite.getHeight())){
				mX2 = meteor2Sprite.getX();
				mY2 = meteor2Sprite.getY();
				mW2 = meteor2Sprite.getWidth();
				mH2 = meteor2Sprite.getHeight();
				smokeDraw2 = true;
				meteor2Sprite.setX(camera.position.x - rand.nextFloat()*width/2 - meteor2Sprite.getWidth());
				meteor2Sprite.setY(camera.position.y + height/2 + rand.nextFloat()*height);
				if(explosionSound.isPlaying()) explosionSound.stop();
				explosionSound.play();
			}
			if(controller.isClickedFlight(meteor3Sprite.getX(), meteor3Sprite.getY(), meteor3Sprite.getWidth(), meteor3Sprite.getHeight())){
				mX3 = meteor3Sprite.getX();
				mY3 = meteor3Sprite.getY();
				mW3 = meteor3Sprite.getWidth();
				mH3 = meteor3Sprite.getHeight();
				smokeDraw3 = true;
				meteor3Sprite.setX(camera.position.x + rand.nextFloat()*width/2);
				meteor3Sprite.setY(camera.position.y + height/2 + rand.nextFloat()*height);
				if(explosionSound.isPlaying()) explosionSound.stop();
				explosionSound.play();
			}
			if(controller.isClickedFlight(meteor4Sprite.getX(), meteor4Sprite.getY(), meteor4Sprite.getWidth(), meteor4Sprite.getHeight())){
				mX4 = meteor4Sprite.getX();
				mY4 = meteor4Sprite.getY();
				mW4 = meteor4Sprite.getWidth();
				mH4 = meteor4Sprite.getHeight();
				smokeDraw4 = true;
				meteor4Sprite.setX(camera.position.x + rand.nextFloat()*width/2);
				meteor4Sprite.setY(camera.position.y + height/2 + rand.nextFloat()*height);
				if(explosionSound.isPlaying()) explosionSound.stop();
				explosionSound.play();
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
		distanceTexture.dispose();
		distanceCursorTexture.dispose();
		rocketEndTexture.dispose();
	}
	
	@Override
	public void dispose() {
		text.dispose();
		textSuc.dispose();
		textFail.dispose();
		music.stop();
		music.dispose();
		flightSound.stop();
		flightSound.dispose();
		detachSound.stop();
		detachSound.dispose();
		explosionSound.stop();
		explosionSound.dispose();
		game.dispose();
		batch.dispose();
		textureDispose();
		game.setScreen(new GameScreen(game));
	}
	
}
