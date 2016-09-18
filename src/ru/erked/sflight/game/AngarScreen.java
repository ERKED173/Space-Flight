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
import ru.erked.sflight.random.ImgResDraw;
import ru.erked.sflight.random.InfoAndStats;

public class AngarScreen implements Screen{

	final String FONT_CHARS_RU = "абвгдежзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
	private static final float width = Gdx.graphics.getWidth();
	private static final float height = Gdx.graphics.getHeight();
	
	private Game game;
	private SpriteBatch batch;
	private SFlightInputController controller;
	
	//Фон
	private Texture backgroundTexture; //Текстура фона
	public static Sprite backgroundSprite; //Спрайт фона
	private float backgroundX;
	private float backgroundY;
	public static final float backgroundTentionIndex = (float)width/800.0F;
	
	//Копка "Back"
	private Sprite backButtonInactiveSprite;
	private Sprite backButtonActiveSprite;
	private float backButtonX;
	private float backButtonY;
	private float backButtonWidth;
	private float backButtonHeight;
	public static float backButtonTentionIndex; //Соотношение сторон кнопки
	
	//Деталь ракеты 1
	private Texture rocket1Inactive;
	private Texture rocket1Active;
	private Sprite rocket1InactiveSprite;
	private Sprite rocket1ActiveSprite;
	private float rocket11X;
	private float rocket11Y;
	private float rocket11Width;
	private float rocket11Height;
	private float rocket12X;
	private float rocket12Y;
	private float rocket12Width;
	private float rocket12Height;
	private float rocket1AspectRatio;
	private boolean isRocket1DetailActive;
	//Деталь ракеты 2
	private Texture rocket2Inactive;
	private Texture rocket2Active;
	private Sprite rocket2InactiveSprite;
	private Sprite rocket2ActiveSprite;
	private float rocket21X;
	private float rocket21Y;
	private float rocket21Width;
	private float rocket21Height;
	private float rocket22X;
	private float rocket22Y;
	private float rocket22Width;
	private float rocket22Height;
	private float rocket2AspectRatio;
	private boolean isRocket2DetailActive;
	//Деталь ракеты 3
	private Texture rocket3Inactive;
	private Texture rocket3Active;
	private Sprite rocket3InactiveSprite;
	private Sprite rocket3ActiveSprite;
	private float rocket31X;
	private float rocket31Y;
	private float rocket31Width;
	private float rocket31Height;
	private float rocket32X;
	private float rocket32Y;
	private float rocket32Width;
	private float rocket32Height;
	private float rocket3AspectRatio;
	private boolean isRocket3DetailActive;
	
	//Окно деталей ракеты
	private Texture rocket1Window;
	private Sprite rocket1WindowSprite;
	private float rocket1WindowX;
	private float rocket1WindowY;
	private float rocket1WindowWidth;
	private float rocket1WindowHeight;
	private static boolean isRocket1WindowDraw;
	//Выход из окна деталей ракеты
	private Sprite backRocket1WindowSpriteInactive;
	private Sprite backRocket1WindowSpriteActive;
	private float backRocket1Window1X;
	private float backRocket1Window1Y;
	private float backRocket1Window1Width;
	private float backRocket1Window1Height;
	private float backRocket1Window2X;
	private float backRocket1Window2Y;
	private float backRocket1Window2Width;
	private float backRocket1Window2Height;
	//Заглушка
	private Sprite backZSprite;
	//Выделение
	public static Sprite boundGold1;
	public static float boundGold1X;
	public static float boundGold1Y;
	public static float boundGold1Width;
	public static float boundGold1Height;
	public static float boundGold1XPREV;
	public static float boundGold1YPREV;
	public static float boundGold1WidthPREV;
	public static float boundGold1HeightPREV;
	public static boolean isBoundDraw1;
	public static Sprite boundGold2;
	public static float boundGold2X;
	public static float boundGold2Y;
	public static float boundGold2Width;
	public static float boundGold2Height;
	public static float boundGold2XPREV;
	public static float boundGold2YPREV;
	public static float boundGold2WidthPREV;
	public static float boundGold2HeightPREV;
	public static boolean isBoundDraw2;
	public static Sprite boundGold3;
	public static float boundGold3X;
	public static float boundGold3Y;
	public static float boundGold3Width;
	public static float boundGold3Height;
	public static float boundGold3XPREV;
	public static float boundGold3YPREV;
	public static float boundGold3WidthPREV;
	public static float boundGold3HeightPREV;
	public static boolean isBoundDraw3;
	
	//Замочек
	private Texture lockTexture;
	private Sprite lockSprite;
	
	//UT_1202
	private Texture UT_1202Texture;
	private Sprite UT_1202Sprite;
	private float UT_1202Width;
	private float UT_1202Height;
	private float UT_1202X;
	private float UT_1202Y;
	public static boolean isUT_1202Selected;
	//MT_0112
	private Texture MT_0112Texture;
	private Sprite MT_0112Sprite;
	private float MT_0112Width;
	private float MT_0112Height;
	private float MT_0112X;
	private float MT_0112Y;
	public static boolean isMT_0112Selected;
	//LT_116
	private Texture LT_116Texture;
	private Sprite LT_116Sprite;
	private float LT_116Width;
	private float LT_116Height;
	private float LT_116X;
	private float LT_116Y;
	public static boolean isLT_116Selected;
	//UM_1034
	private Texture UM_1034Texture;
	private Sprite UM_1034Sprite;
	private float UM_1034Width;
	private float UM_1034Height;
	private float UM_1034X;
	private float UM_1034Y;
	public static boolean isUM_1034Selected;
	public static boolean isUM_1034PriceSelected;
	//MM_4
	private Texture MM_4Texture;
	private Sprite MM_4Sprite;
	private float MM_4Width;
	private float MM_4Height;
	private float MM_4X;
	private float MM_4Y;
	public static boolean isMM_4Selected;
	public static boolean isMM_4PriceSelected;
	//LM_087
	private Texture LM_087Texture;
	private Sprite LM_087Sprite;
	private float LM_087Width;
	private float LM_087Height;
	private float LM_087X;
	private float LM_087Y;
	public static boolean isLM_087Selected;
	public static boolean isLM_087PriceSelected;
	//UF_02
	private Texture UF_02Texture;
	private Sprite UF_02Sprite;
	private float UF_02Width;
	private float UF_02Height;
	private float UF_02X;
	private float UF_02Y;
	public static boolean isUF_02Selected;
	public static boolean isUF_02PriceSelected;
	//MF_043
	private Texture MF_043Texture;
	private Sprite MF_043Sprite;
	private float MF_043Width;
	private float MF_043Height;
	private float MF_043X;
	private float MF_043Y;
	public static boolean isMF_043Selected;
	public static boolean isMF_043PriceSelected;
	//LF_15
	private Texture LF_15Texture;
	private Sprite LF_15Sprite;
	private float LF_15Width;
	private float LF_15Height;
	private float LF_15X;
	private float LF_15Y;
	public static boolean isLF_15Selected;
	public static boolean isLF_15PriceSelected;
	
	//Кнопка покупки
	private Sprite buy1Sprite;
	private Sprite buy2Sprite;
	private Sprite buy3Sprite;
	private float buy1Width;
	private float buy1Height;
	private float buy1X;
	private float buy1Y;
	private float buy2Width;
	private float buy2Height;
	private float buy2X;
	private float buy2Y;
	
	//КосмоКоин
	private Sprite cosmocoinSprite;
	private float cosmocoinWidth;
	private float cosmocoinHeight;
	private float cosmocoinX;
	private float cosmocoinY;
	
	//Текст
	private static BitmapFont text;
	
	public AngarScreen(Game game){
		this.game = game;
	}
	
	@Override
	public void show() {

		batch = new SpriteBatch();
		controller = new SFlightInputController();

		MainMenu.music.play();
		
		//Фон\\
		backgroundTexture = new Texture("bckgrnd/angar_inside.png");
		backgroundSprite = new Sprite(backgroundTexture);
		backgroundX = 0.0F;
		backgroundY = (-1)*(450*backgroundTentionIndex)/2 + height/2;
		backgroundSprite.setBounds(backgroundX, backgroundY, width, backgroundTentionIndex*450.0F);
		
		//Кнопка "Back"\\
		backButtonInactiveSprite = new Sprite(ImgResDraw.backButtonInactive);
		backButtonActiveSprite = new Sprite(ImgResDraw.backButtonActive);
		if(InfoAndStats.lngRussian){
			backButtonInactiveSprite.setTexture(ImgResDraw.backButtonInactiveRU);
			backButtonActiveSprite.setTexture(ImgResDraw.backButtonActiveRU);
		}
		backButtonTentionIndex = (float)ImgResDraw.backButtonInactive.getWidth()/ImgResDraw.backButtonInactive.getHeight();
		backButtonWidth = 0.132F*width;
		backButtonHeight = backButtonWidth/backButtonTentionIndex;
		backButtonX = width - 0.015F*width - backButtonWidth;
		backButtonY = 0 + 0.005F*height;
		backButtonInactiveSprite.setBounds(backButtonX, backButtonY, backButtonWidth, backButtonHeight);
		backButtonActiveSprite.setBounds(backButtonX, backButtonY, backButtonWidth, backButtonHeight);
	
		rocketDetail1Init();
		rocketDetail2Init();
		rocketDetail3Init();
		
		rocket1WindowInit();
		
		UT_1202Init();
		MT_0112Init();
		LT_116Init();
		UM_1034Init();
		MM_04Init();
		LM_087Init();
		UF_02Init();
		MF_043Init();
		LF_15Init();
		
		buyInit();
		
		//Текст\\
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
		
	}

	@Override
	public void render(float delta) {
		InfoAndStats.elapsedTime++;
		
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		
		backgroundSprite.draw(batch);
		
		//Отрисовка кнопки "Back"//
		if(controller.isOn(backButtonX, backButtonY, backButtonWidth, backButtonHeight)){
			if(!isRocket1WindowDraw){
				backButtonActiveSprite.draw(batch);
			}else{
				backButtonInactiveSprite.draw(batch);
			}
		}else{
			backButtonInactiveSprite.draw(batch);
		}
		
		//Отрисовка текста в ангаре//
		if(!InfoAndStats.lngRussian){
			text.draw(batch, "Hangar", 0.01F*width, 0.99F*height);
		}else{
			text.draw(batch, "Ангар", 0.01F*width, 0.99F*height);
		}
			
		rocketDetailsDraw();
		rocketWindowDraw();
		
		batch.end();
		
		buttonListeners();
		
	}
	
	private void rocketDetail1Init(){
		//Деталь ракеты 1\\
		rocket1Inactive = new Texture("objects/rocket_1_inactive.png");
		rocket1Active = new Texture("objects/rocket_1_active.png");
		rocket1InactiveSprite = new Sprite(rocket1Inactive);
		rocket1ActiveSprite = new Sprite(rocket1Active);
		rocket1AspectRatio = (float)rocket1Inactive.getWidth()/rocket1Inactive.getHeight();
		rocket11Width = 0.075F*width;
		rocket11Height = (float)rocket11Width/rocket1AspectRatio;
		rocket11X = 0.2F*backgroundSprite.getWidth();
		rocket11Y = 0.225F*backgroundSprite.getHeight();
		rocket12Width = 0.140625F*width;
		rocket12Height = (float)rocket12Width/rocket1AspectRatio;
		rocket12X = 0.2F*backgroundSprite.getWidth() - 0.23333333333333333333333333333333F*rocket12Width;
		rocket12Y = 0.225F*backgroundSprite.getHeight() - 0.23489932885906040268456375838926F*rocket12Height;
		rocket1InactiveSprite.setBounds(rocket11X, rocket11Y, rocket11Width, rocket11Height);
		rocket1ActiveSprite.setBounds(rocket12X, rocket12Y, rocket12Width, rocket12Height);
		isRocket1DetailActive = false;
	}
	private void rocketDetail2Init(){
		//Деталь ракеты 2\\
		rocket2Inactive = new Texture("objects/rocket_2_inactive.png");
		rocket2Active = new Texture("objects/rocket_2_active.png");
		rocket2InactiveSprite = new Sprite(rocket2Inactive);
		rocket2ActiveSprite = new Sprite(rocket2Active);
		rocket2AspectRatio = (float)rocket2Inactive.getWidth()/rocket2Inactive.getHeight();
		rocket21Width = 0.25F*width;
		rocket21Height = (float)rocket21Width/rocket2AspectRatio;
		rocket21X = 0.325F*backgroundSprite.getWidth();
		rocket21Y = 0.325F*backgroundSprite.getHeight();
		rocket22Width = 0.39906832298136645962732919254659F*width;
		rocket22Height = (float)rocket22Width/rocket2AspectRatio;
		rocket22X = 0.325F*backgroundSprite.getWidth() - 0.18677042801556420233463035019455F*rocket22Width;
		rocket22Y = 0.325F*backgroundSprite.getHeight() - 0.18666666666666666666666666666667F*rocket22Height;
		rocket2InactiveSprite.setBounds(rocket21X, rocket21Y, rocket21Width, rocket21Height);
		rocket2ActiveSprite.setBounds(rocket22X, rocket22Y, rocket22Width, rocket22Height);
		isRocket2DetailActive = false;
	}
	private void rocketDetail3Init(){
		//Деталь ракеты 3\\
		rocket3Inactive = new Texture("objects/rocket_3_inactive.png");
		rocket3Active = new Texture("objects/rocket_3_active.png");
		rocket3InactiveSprite = new Sprite(rocket3Inactive);
		rocket3ActiveSprite = new Sprite(rocket3Active);
		rocket3AspectRatio = (float)rocket3Inactive.getWidth()/rocket3Inactive.getHeight();
		rocket31Width = 0.125F*width;
		rocket31Height = (float)rocket31Width/rocket3AspectRatio;
		rocket31X = 0.6F*backgroundSprite.getWidth();
		rocket31Y = 0.55F*backgroundSprite.getHeight();
		rocket32Width = 0.19946808510638297872340425531915F*width;
		rocket32Height = (float)rocket32Width/rocket3AspectRatio;
		rocket32X = 0.6F*backgroundSprite.getWidth() - 0.18677042801556420233463035019455F*rocket32Width;
		rocket32Y = 0.55F*backgroundSprite.getHeight() - 0.18666666666666666666666666666667F*rocket32Height;
		rocket3InactiveSprite.setBounds(rocket31X, rocket31Y, rocket31Width, rocket31Height);
		rocket3ActiveSprite.setBounds(rocket32X, rocket32Y, rocket32Width, rocket32Height);
		isRocket3DetailActive = false;
	}
	private void rocket1WindowInit(){
		//Окно деталей ракеты\\
		rocket1Window = new Texture("objects/angarWindow.png");
		rocket1WindowSprite = new Sprite(rocket1Window);
		rocket1WindowWidth = 0.75F*width;
		rocket1WindowHeight = rocket1WindowWidth/1.5F;
		rocket1WindowX = 0.5F*width - 0.5F*rocket1WindowWidth;
		rocket1WindowY = 0.5F*height - 0.5F*rocket1WindowHeight;
		rocket1WindowSprite.setBounds(rocket1WindowX, rocket1WindowY, rocket1WindowWidth, rocket1WindowHeight);
		isRocket1WindowDraw = false;
		backRocket1WindowSpriteInactive = new Sprite(ImgResDraw.backIconInactive);
		backRocket1WindowSpriteActive = new Sprite(ImgResDraw.backIconActive);
		backRocket1Window1Width = 0.065F*width;
		backRocket1Window1Height = backRocket1Window1Width;
		backRocket1Window1X = rocket1WindowX + 0.033333333333333333333333333333333F*rocket1WindowWidth;
		backRocket1Window1Y = rocket1WindowY + 0.05F*rocket1WindowHeight;
		backRocket1WindowSpriteInactive.setBounds(backRocket1Window1X, backRocket1Window1Y, backRocket1Window1Width, backRocket1Window1Height);
		backRocket1Window2Width = 0.0975F*width;
		backRocket1Window2Height = backRocket1Window2Width;
		backRocket1Window2X = rocket1WindowX + 0.033333333333333333333333333333333F*rocket1WindowWidth - 0.16666666666666666666666666666667F*backRocket1Window2Width;
		backRocket1Window2Y = rocket1WindowY + 0.05F*rocket1WindowHeight - 0.16666666666666666666666666666667F*backRocket1Window2Height;
		backRocket1WindowSpriteActive.setBounds(backRocket1Window2X, backRocket1Window2Y, backRocket1Window2Width, backRocket1Window2Height);
		backZSprite = new Sprite(new Texture("bckgrnd/backgroundGray.png"));
		backZSprite.setBounds(0.0F, 0.0F, width, height);
		boundGold1 = new Sprite(new Texture("btns/angarActive.png"));
		boundGold1.setBounds(boundGold1XPREV, boundGold1YPREV, boundGold1WidthPREV, boundGold1HeightPREV);
		boundGold2 = new Sprite(new Texture("btns/angarActive.png"));
		boundGold2.setBounds(boundGold2XPREV, boundGold2YPREV, boundGold2WidthPREV, boundGold2HeightPREV);
		boundGold3 = new Sprite(new Texture("btns/angarActive.png"));
		boundGold3.setBounds(boundGold3XPREV, boundGold3YPREV, boundGold3WidthPREV, boundGold3HeightPREV);
		lockTexture = new Texture("objects/lock.png");
		lockSprite = new Sprite(lockTexture);
		lockSprite.setBounds(0.0F, 0.0F, 0.0F, 0.0F);
	}
	
	private void UT_1202Init(){
		//UT_1202\\
		UT_1202Texture = new Texture("rockets/UT_1202_stin.png");
		UT_1202Sprite = new Sprite(UT_1202Texture);
		UT_1202Width = 0.2F*rocket1WindowHeight;
		UT_1202Height = UT_1202Width;
		UT_1202X = rocket1WindowX + 0.7F*rocket1WindowWidth;
		UT_1202Y = rocket1WindowY + 0.7F*rocket1WindowHeight;
		UT_1202Sprite.setBounds(UT_1202X, UT_1202Y, UT_1202Width, UT_1202Height);
	}
	private void MT_0112Init(){
		//MT_0112\\
		MT_0112Texture = new Texture("rockets/MT_0112.png");
		MT_0112Sprite = new Sprite(MT_0112Texture);
		MT_0112Width = 0.2F*rocket1WindowHeight;
		MT_0112Height = MT_0112Width;
		MT_0112X = rocket1WindowX + 0.7F*rocket1WindowWidth;
		MT_0112Y = rocket1WindowY + 0.7F*rocket1WindowHeight;
		MT_0112Sprite.setBounds(MT_0112X, MT_0112Y, MT_0112Width, MT_0112Height);
	}
	private void LT_116Init(){
		//LT_116\\
		LT_116Texture = new Texture("rockets/LT_116.png");
		LT_116Sprite = new Sprite(LT_116Texture);
		LT_116Width = 0.2F*rocket1WindowHeight;
		LT_116Height = LT_116Width;
		LT_116X = rocket1WindowX + 0.7F*rocket1WindowWidth;
		LT_116Y = rocket1WindowY + 0.7F*rocket1WindowHeight;
		LT_116Sprite.setBounds(LT_116X, LT_116Y, LT_116Width, LT_116Height);
	}
	private void UM_1034Init(){
		//UM_1034\\
		UM_1034Texture = new Texture("rockets/UM_1034.png");
		UM_1034Sprite = new Sprite(UM_1034Texture);
		UM_1034Width = 0.2F*rocket1WindowHeight;
		UM_1034Height = UM_1034Width;
		UM_1034X = rocket1WindowX + 0.7F*rocket1WindowWidth;
		UM_1034Y = rocket1WindowY + 0.4F*rocket1WindowHeight;
		UM_1034Sprite.setBounds(UM_1034X, UM_1034Y, UM_1034Width, UM_1034Height);
		isUM_1034PriceSelected = false;
	}
	private void MM_04Init(){
		//MM_4\\
		MM_4Texture = new Texture("rockets/MM_4.png");
		MM_4Sprite = new Sprite(MM_4Texture);
		MM_4Width = 0.2F*rocket1WindowHeight;
		MM_4Height = MM_4Width;
		MM_4X = rocket1WindowX + 0.7F*rocket1WindowWidth;
		MM_4Y = rocket1WindowY + 0.4F*rocket1WindowHeight;
		MM_4Sprite.setBounds(MM_4X, MM_4Y, MM_4Width, MM_4Height);
		isMM_4PriceSelected = false;
	}
	private void LM_087Init(){
		//LM_087\\
		LM_087Texture = new Texture("rockets/LM_087.png");
		LM_087Sprite = new Sprite(LM_087Texture);
		LM_087Width = 0.2F*rocket1WindowHeight;
		LM_087Height = LM_087Width;
		LM_087X = rocket1WindowX + 0.7F*rocket1WindowWidth;
		LM_087Y = rocket1WindowY + 0.4F*rocket1WindowHeight;
		LM_087Sprite.setBounds(LM_087X, LM_087Y, LM_087Width, LM_087Height);
		isLM_087PriceSelected = false;
	}
	private void UF_02Init(){
		//UF_02\\
		UF_02Texture = new Texture("rockets/UF_02.png");
		UF_02Sprite = new Sprite(UF_02Texture);
		UF_02Width = 0.2F*rocket1WindowHeight;
		UF_02Height = UF_02Width;
		UF_02X = rocket1WindowX + 0.7F*rocket1WindowWidth;
		UF_02Y = rocket1WindowY + 0.1F*rocket1WindowHeight;
		UF_02Sprite.setBounds(UF_02X, UF_02Y, UF_02Width, UF_02Height);
		isUF_02PriceSelected = false;
	}
	private void MF_043Init(){
		//MF_043\\
		MF_043Texture = new Texture("rockets/MF_043.png");
		MF_043Sprite = new Sprite(MF_043Texture);
		MF_043Width = 0.2F*rocket1WindowHeight;
		MF_043Height = MF_043Width;
		MF_043X = rocket1WindowX + 0.7F*rocket1WindowWidth;
		MF_043Y = rocket1WindowY + 0.1F*rocket1WindowHeight;
		MF_043Sprite.setBounds(MF_043X, MF_043Y, MF_043Width, MF_043Height);
		isMF_043PriceSelected = false;
	}
	private void LF_15Init(){
		//LF_15\\
		LF_15Texture = new Texture("rockets/LF_15.png");
		LF_15Sprite = new Sprite(LF_15Texture);
		LF_15Width = 0.2F*rocket1WindowHeight;
		LF_15Height = LF_15Width;
		LF_15X = rocket1WindowX + 0.7F*rocket1WindowWidth;
		LF_15Y = rocket1WindowY + 0.1F*rocket1WindowHeight;
		LF_15Sprite.setBounds(LF_15X, LF_15Y, LF_15Width, LF_15Height);
		isLF_15PriceSelected = false;
	}
	
	private void buyInit(){
		buy1Sprite = new Sprite(new Texture("btns/buyInactiveNoUS.png"));
		buy2Sprite = new Sprite(new Texture("btns/buyInactiveYesUS.png"));
		buy3Sprite = new Sprite(new Texture("btns/buyActiveYesUS.png"));
		if(InfoAndStats.lngRussian){
			buy1Sprite.setTexture(new Texture("btns/RU/buyInactiveNoRU.png"));
			buy2Sprite.setTexture(new Texture("btns/RU/buyInactiveYesRU.png"));
			buy3Sprite.setTexture(new Texture("btns/RU/buyActiveYesRU.png"));
		}
		buy1Width = 0.25F*rocket1WindowWidth;
		buy1Height = buy1Width/2.0F;
		buy1X = rocket1WindowX + 0.235F*rocket1WindowWidth;
		buy1Y = rocket1WindowY + 0.05F*rocket1WindowHeight;
		buy2Width = 0.390625F*rocket1WindowWidth;
		buy2Height = buy2Width/2;
		buy2X = buy1X - 0.18F*buy2Width;
		buy2Y = buy1Y - 0.18F*buy2Height;
		buy1Sprite.setBounds(buy1X, buy1Y, buy1Width, buy1Height);
		buy2Sprite.setBounds(buy1X, buy1Y, buy1Width, buy1Height);
		buy3Sprite.setBounds(buy2X, buy2Y, buy2Width, buy2Height);
		//***\\
		cosmocoinSprite = new Sprite(new Texture("objects/cosmocoin.png"));
		cosmocoinWidth = 0.1F*rocket1WindowWidth;
		cosmocoinHeight = cosmocoinWidth;
		cosmocoinX = buy1X - 0.2F*rocket1WindowWidth;
		cosmocoinY = buy1Y + 0.25F*rocket1WindowHeight;
		cosmocoinSprite.setBounds(cosmocoinX, cosmocoinY, cosmocoinWidth, cosmocoinHeight);
	}
	
	private void rocketDetailsDraw(){
		//Отрисовка ракетной детали 1//
		if(controller.isOn(rocket11X, rocket11Y, rocket11Width, rocket11Height)){
			if(!isRocket1WindowDraw){
				rocket1ActiveSprite.draw(batch);
			}else{
				rocket1InactiveSprite.draw(batch);
			}
		}else{
			rocket1InactiveSprite.draw(batch);
		}
		//Отрисовка ракетной детали 2//
		if(controller.isOn(rocket21X, rocket21Y, rocket21Width, rocket21Height)){
			if(!isRocket1WindowDraw){
				rocket2ActiveSprite.draw(batch);
			}else{
				rocket2InactiveSprite.draw(batch);
			}
		}else{
			rocket2InactiveSprite.draw(batch);
		}
		//Отрисовка ракетной детали 3//
		if(controller.isOn(rocket31X, rocket31Y, rocket31Width, rocket31Height)){
			if(!isRocket1WindowDraw){
				rocket3ActiveSprite.draw(batch);
			}else{
				rocket3InactiveSprite.draw(batch);
			}
		}else{
			rocket3InactiveSprite.draw(batch);
		}
	}
	private void rocketWindowDraw(){
		//Отрисовка диалогового окна деталей ракет//
		if(isRocket1WindowDraw){
			backZSprite.draw(batch);
			rocket1WindowSprite.draw(batch);
			if(controller.isOn(backRocket1Window1X, backRocket1Window1Y, backRocket1Window1Width, backRocket1Window1Height))
				backRocket1WindowSpriteActive.draw(batch);
			else
				backRocket1WindowSpriteInactive.draw(batch);
			
			//Третья ступень
			if(isRocket1DetailActive){
				if(!InfoAndStats.lngRussian){
					text.draw(batch, "Third stage", rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.95F*rocket1WindowHeight);
				}else{
					text.draw(batch, "Третья ступень", rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.95F*rocket1WindowHeight);
				}
				if(isBoundDraw1) boundGold1.draw(batch);
				UT_1202Sprite.draw(batch);
				if(!InfoAndStats.UT_1202.getRocketDetailAccessibility()){
					lockSprite.setBounds(UT_1202Sprite.getX() + 0.25F*UT_1202Sprite.getWidth(), UT_1202Sprite.getY() + 0.25F*UT_1202Sprite.getHeight(), 0.5F*UT_1202Sprite.getWidth(), 0.5F*UT_1202Sprite.getHeight());
					lockSprite.draw(batch);
				}
				if(isUT_1202Selected) UT_1202TextDraw();
				UM_1034Sprite.draw(batch);
				if(!InfoAndStats.UM_1034.getRocketDetailAccessibility()){
					lockSprite.setBounds(UM_1034Sprite.getX() + 0.25F*UM_1034Sprite.getWidth(), UM_1034Sprite.getY() + 0.25F*UM_1034Sprite.getHeight(), 0.5F*UM_1034Sprite.getWidth(), 0.5F*UM_1034Sprite.getHeight());
					lockSprite.draw(batch);
				}
				UM_1034TextDraw();
				UF_02Sprite.draw(batch);
				if(!InfoAndStats.UF_02.getRocketDetailAccessibility()){
					lockSprite.setBounds(UF_02Sprite.getX() + 0.25F*UF_02Sprite.getWidth(), UF_02Sprite.getY() + 0.25F*UF_02Sprite.getHeight(), 0.5F*UF_02Sprite.getWidth(), 0.5F*UF_02Sprite.getHeight());
					lockSprite.draw(batch);
				}
				UF_02TextDraw();
			}//Вторая ступень
			if(isRocket2DetailActive){
				if(!InfoAndStats.lngRussian){
					text.draw(batch, "Second stage", rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.95F*rocket1WindowHeight);
				}else{
					text.draw(batch, "Вторая ступень", rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.95F*rocket1WindowHeight);
				}
				if(isBoundDraw2) boundGold2.draw(batch);
				MT_0112Sprite.draw(batch);
				if(!InfoAndStats.MT_0112.getRocketDetailAccessibility()){
					lockSprite.setBounds(MT_0112Sprite.getX() + 0.25F*MT_0112Sprite.getWidth(), MT_0112Sprite.getY() + 0.25F*MT_0112Sprite.getHeight(), 0.5F*MT_0112Sprite.getWidth(), 0.5F*MT_0112Sprite.getHeight());
					lockSprite.draw(batch);
				}
				if(isMT_0112Selected) MT_0112TextDraw();
				MM_4Sprite.draw(batch);
				if(!InfoAndStats.MM_4.getRocketDetailAccessibility()){
					lockSprite.setBounds(MM_4Sprite.getX() + 0.25F*MM_4Sprite.getWidth(), MM_4Sprite.getY() + 0.25F* MM_4Sprite.getHeight(), 0.5F*MM_4Sprite.getWidth(), 0.5F* MM_4Sprite.getHeight());
					lockSprite.draw(batch);
				}
				MM_4TextDraw();
				MF_043Sprite.draw(batch);
				if(!InfoAndStats.MF_043.getRocketDetailAccessibility()){
					lockSprite.setBounds(MF_043Sprite.getX() + 0.25F*MF_043Sprite.getWidth(), MF_043Sprite.getY() + 0.25F* MF_043Sprite.getHeight(), 0.5F*MF_043Sprite.getWidth(), 0.5F* MF_043Sprite.getHeight());
					lockSprite.draw(batch);
				}
				MF_043TextDraw();
			}//Первая ступень
			if(isRocket3DetailActive){
				if(!InfoAndStats.lngRussian){
					text.draw(batch, "First stage", rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.95F*rocket1WindowHeight);
				}else{
					text.draw(batch, "Первая ступень", rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.95F*rocket1WindowHeight);
				}
				if(isBoundDraw3) boundGold3.draw(batch);
				LT_116Sprite.draw(batch);
				if(!InfoAndStats.LT_116.getRocketDetailAccessibility()){
					lockSprite.setBounds(LT_116Sprite.getX() + 0.25F*LT_116Sprite.getWidth(), LT_116Sprite.getY() + 0.25F*LT_116Sprite.getHeight(), 0.5F*LT_116Sprite.getWidth(), 0.5F*LT_116Sprite.getHeight());
					lockSprite.draw(batch);
				}
				if(isLT_116Selected) LT_116TextDraw();
				LM_087Sprite.draw(batch);
				if(!InfoAndStats.LM_087.getRocketDetailAccessibility()){
					lockSprite.setBounds(LM_087Sprite.getX() + 0.25F*LM_087Sprite.getWidth(), LM_087Sprite.getY() + 0.25F*LM_087Sprite.getHeight(), 0.5F*LM_087Sprite.getWidth(), 0.5F*LM_087Sprite.getHeight());
					lockSprite.draw(batch);
				}
				LM_087TextDraw();
				LF_15Sprite.draw(batch);
				if(!InfoAndStats.LF_15.getRocketDetailAccessibility()){
					lockSprite.setBounds(LF_15Sprite.getX() + 0.25F*LF_15Sprite.getWidth(), LF_15Sprite.getY() + 0.25F*LF_15Sprite.getHeight(), 0.5F*LF_15Sprite.getWidth(), 0.5F*LF_15Sprite.getHeight());
					lockSprite.draw(batch);
				}
				LF_15TextDraw();
			}
		}
	}
	
	private void UT_1202TextDraw(){
		if(isUT_1202Selected && isBoundDraw1 && InfoAndStats.UT_1202.getRocketDetailAccessibility()){
			if(!InfoAndStats.lngRussian){
				text.draw(batch, "Name: " + InfoAndStats.UT_1202.getRocketDetailName(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 1.5F*text.getCapHeight());
				text.draw(batch, "Thrust: " + InfoAndStats.UT_1202.getRocketDetailThrust(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 3.0F*text.getCapHeight());
				text.draw(batch, "Specific impulse: " + InfoAndStats.UT_1202.getRocketDetailImpulse(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 4.5F*text.getCapHeight());
				text.draw(batch, "Working time: " + InfoAndStats.UT_1202.getRocketDetailTime(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 6.0F*text.getCapHeight());
				text.draw(batch, "Amount: " + InfoAndStats.UT_1202.getRocketDetailAmount(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 7.5F*text.getCapHeight());
				text.draw(batch, "Level: " + InfoAndStats.UT_1202.getRocketDetailLevel(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 9.0F*text.getCapHeight());
				text.draw(batch, "Price: " + InfoAndStats.UT_1202.getRocketDetailPrice() + " cosmocoins", rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 10.5F*text.getCapHeight());
				text.draw(batch, "You have: " + InfoAndStats.money + " cosmocoins", rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 12.0F*text.getCapHeight());
			}else{
				text.draw(batch, "Название: " + InfoAndStats.UT_1202.getRocketDetailName(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 1.5F*text.getCapHeight());
				text.draw(batch, "Тяга: " + InfoAndStats.UT_1202.getRocketDetailThrust(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 3.0F*text.getCapHeight());
				text.draw(batch, "Удельный импульс: " + InfoAndStats.UT_1202.getRocketDetailImpulse(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 4.5F*text.getCapHeight());
				text.draw(batch, "Рабочее время: " + InfoAndStats.UT_1202.getRocketDetailTime(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 6.0F*text.getCapHeight());
				text.draw(batch, "Количество: " + InfoAndStats.UT_1202.getRocketDetailAmount(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 7.5F*text.getCapHeight());
				text.draw(batch, "Уровень: " + InfoAndStats.UT_1202.getRocketDetailLevel(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 9.0F*text.getCapHeight());
				text.draw(batch, "Цена: " + InfoAndStats.UT_1202.getRocketDetailPrice() + " космокоинов", rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 10.5F*text.getCapHeight());
				text.draw(batch, "У Вас есть: " + InfoAndStats.money + " космокоинов", rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 12.0F*text.getCapHeight());
			}
			if(InfoAndStats.money >= InfoAndStats.UT_1202.getRocketDetailPrice()){
				if(controller.isOn(buy1X, buy1Y, buy1Width, buy1Height)){
					buy3Sprite.draw(batch);
				}else{
					buy2Sprite.draw(batch);
				}
			}else{
				buy1Sprite.draw(batch);
			}
		}
	}
	private void MT_0112TextDraw(){
		if(isMT_0112Selected && isBoundDraw2 && InfoAndStats.MT_0112.getRocketDetailAccessibility()){
			if(!InfoAndStats.lngRussian){
				text.draw(batch, "Name: " + InfoAndStats.MT_0112.getRocketDetailName(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 1.5F*text.getCapHeight());
				text.draw(batch, "Thrust: " + InfoAndStats.MT_0112.getRocketDetailThrust(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 3.0F*text.getCapHeight());
				text.draw(batch, "Specific impulse: " + InfoAndStats.MT_0112.getRocketDetailImpulse(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 4.5F*text.getCapHeight());
				text.draw(batch, "Working time: " + InfoAndStats.MT_0112.getRocketDetailTime(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 6.0F*text.getCapHeight());
				text.draw(batch, "Amount: " + InfoAndStats.MT_0112.getRocketDetailAmount(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 7.5F*text.getCapHeight());
				text.draw(batch, "Level: " + InfoAndStats.MT_0112.getRocketDetailLevel(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 9.0F*text.getCapHeight());
				text.draw(batch, "Price: " + InfoAndStats.MT_0112.getRocketDetailPrice() + " cosmocoins", rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 10.5F*text.getCapHeight());
				text.draw(batch, "You have: " + InfoAndStats.money + " cosmocoins", rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 12.0F*text.getCapHeight());
			}else{
				text.draw(batch, "Название: " + InfoAndStats.MT_0112.getRocketDetailName(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 1.5F*text.getCapHeight());
				text.draw(batch, "Тяга: " + InfoAndStats.MT_0112.getRocketDetailThrust(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 3.0F*text.getCapHeight());
				text.draw(batch, "Удельный импульс: " + InfoAndStats.MT_0112.getRocketDetailImpulse(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 4.5F*text.getCapHeight());
				text.draw(batch, "Рабочее время: " + InfoAndStats.MT_0112.getRocketDetailTime(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 6.0F*text.getCapHeight());
				text.draw(batch, "Количество: " + InfoAndStats.MT_0112.getRocketDetailAmount(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 7.5F*text.getCapHeight());
				text.draw(batch, "Уровень: " + InfoAndStats.MT_0112.getRocketDetailLevel(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 9.0F*text.getCapHeight());
				text.draw(batch, "Цена: " + InfoAndStats.MT_0112.getRocketDetailPrice() + " космокоинов", rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 10.5F*text.getCapHeight());
				text.draw(batch, "У Вас есть: " + InfoAndStats.money + " космокоинов", rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 12.0F*text.getCapHeight());
			}
			if(InfoAndStats.money >= InfoAndStats.MT_0112.getRocketDetailPrice()){
				if(controller.isOn(buy1X, buy1Y, buy1Width, buy1Height)){
					buy3Sprite.draw(batch);
				}else{
					buy2Sprite.draw(batch);
				}
			}else{
				buy1Sprite.draw(batch);
			}
		}
	}
	private void LT_116TextDraw(){
		if(isLT_116Selected && isBoundDraw3 && InfoAndStats.LT_116.getRocketDetailAccessibility()){
			if(!InfoAndStats.lngRussian){
				text.draw(batch, "Name: " + InfoAndStats.LT_116.getRocketDetailName(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 1.5F*text.getCapHeight());
				text.draw(batch, "Thrust: " + InfoAndStats.LT_116.getRocketDetailThrust(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 3.0F*text.getCapHeight());
				text.draw(batch, "Specific impulse: " + InfoAndStats.LT_116.getRocketDetailImpulse(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 4.5F*text.getCapHeight());
				text.draw(batch, "Working time: " + InfoAndStats.LT_116.getRocketDetailTime(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 6.0F*text.getCapHeight());
				text.draw(batch, "Amount: " + InfoAndStats.LT_116.getRocketDetailAmount(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 7.5F*text.getCapHeight());
				text.draw(batch, "Level: " + InfoAndStats.LT_116.getRocketDetailLevel(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 9.0F*text.getCapHeight());
				text.draw(batch, "Price: " + InfoAndStats.LT_116.getRocketDetailPrice() + " cosmocoins", rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 10.5F*text.getCapHeight());
				text.draw(batch, "You have: " + InfoAndStats.money + " cosmocoins", rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 12.0F*text.getCapHeight());
			}else{
				text.draw(batch, "Название: " + InfoAndStats.LT_116.getRocketDetailName(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 1.5F*text.getCapHeight());
				text.draw(batch, "Тяга: " + InfoAndStats.LT_116.getRocketDetailThrust(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 3.0F*text.getCapHeight());
				text.draw(batch, "Удельный импульс: " + InfoAndStats.LT_116.getRocketDetailImpulse(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 4.5F*text.getCapHeight());
				text.draw(batch, "Рабочее время: " + InfoAndStats.LT_116.getRocketDetailTime(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 6.0F*text.getCapHeight());
				text.draw(batch, "Количество: " + InfoAndStats.LT_116.getRocketDetailAmount(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 7.5F*text.getCapHeight());
				text.draw(batch, "Уровень: " + InfoAndStats.LT_116.getRocketDetailLevel(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 9.0F*text.getCapHeight());
				text.draw(batch, "Цена: " + InfoAndStats.LT_116.getRocketDetailPrice() + " космокоинов", rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 10.5F*text.getCapHeight());
				text.draw(batch, "У Вас есть: " + InfoAndStats.money + " космокоинов", rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 12.0F*text.getCapHeight());
			}
			if(InfoAndStats.money >= InfoAndStats.LT_116.getRocketDetailPrice()){
				if(controller.isOn(buy1X, buy1Y, buy1Width, buy1Height)){
					buy3Sprite.draw(batch);
				}else{
					buy2Sprite.draw(batch);
				}
			}else{
				buy1Sprite.draw(batch);
			}
		}
	}
	private void UM_1034TextDraw(){
		if(isUM_1034Selected && isBoundDraw1 && InfoAndStats.UM_1034.getRocketDetailAccessibility()){
			if(!InfoAndStats.lngRussian){
				text.draw(batch, "Name: " + InfoAndStats.UM_1034.getRocketDetailName(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 1.5F*text.getCapHeight());
				text.draw(batch, "Thrust: " + InfoAndStats.UM_1034.getRocketDetailThrust(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 3.0F*text.getCapHeight());
				text.draw(batch, "Specific impulse: " + InfoAndStats.UM_1034.getRocketDetailImpulse(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 4.5F*text.getCapHeight());
				text.draw(batch, "Working time: " + InfoAndStats.UM_1034.getRocketDetailTime(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 6.0F*text.getCapHeight());
				text.draw(batch, "Amount: " + InfoAndStats.UM_1034.getRocketDetailAmount(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 7.5F*text.getCapHeight());
				text.draw(batch, "Level: " + InfoAndStats.UM_1034.getRocketDetailLevel(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 9.0F*text.getCapHeight());
				text.draw(batch, "Price: " + InfoAndStats.UM_1034.getRocketDetailPrice() + " cosmocoins", rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 10.5F*text.getCapHeight());
				text.draw(batch, "You have: " + InfoAndStats.money + " cosmocoins", rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 12.0F*text.getCapHeight());
			}else{
				text.draw(batch, "Название: " + InfoAndStats.UM_1034.getRocketDetailName(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 1.5F*text.getCapHeight());
				text.draw(batch, "Тяга: " + InfoAndStats.UM_1034.getRocketDetailThrust(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 3.0F*text.getCapHeight());
				text.draw(batch, "Удельный импульс: " + InfoAndStats.UM_1034.getRocketDetailImpulse(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 4.5F*text.getCapHeight());
				text.draw(batch, "Рабочее время: " + InfoAndStats.UM_1034.getRocketDetailTime(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 6.0F*text.getCapHeight());
				text.draw(batch, "Количество: " + InfoAndStats.UM_1034.getRocketDetailAmount(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 7.5F*text.getCapHeight());
				text.draw(batch, "Уровень: " + InfoAndStats.UM_1034.getRocketDetailLevel(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 9.0F*text.getCapHeight());
				text.draw(batch, "Цена: " + InfoAndStats.UM_1034.getRocketDetailPrice() + " космокоинов", rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 10.5F*text.getCapHeight());
				text.draw(batch, "У Вас есть: " + InfoAndStats.money + " космкоинов", rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 12.0F*text.getCapHeight());
			}
			if(InfoAndStats.money >= InfoAndStats.UM_1034.getRocketDetailPrice()){
				if(controller.isOn(buy1X, buy1Y, buy1Width, buy1Height)){
					buy3Sprite.draw(batch);
				}else{
					buy2Sprite.draw(batch);
				}
			}else{
				buy1Sprite.draw(batch);
			}
		}else if(!isBoundDraw1 && !InfoAndStats.UM_1034.getRocketDetailAccessibility() && isUM_1034PriceSelected){
			if(!InfoAndStats.lngRussian){
				text.draw(batch, "Name: " + InfoAndStats.UM_1034.getRocketDetailName(), rocket1WindowX + 0.04F*rocket1WindowWidth, rocket1WindowY + 0.55F*rocket1WindowHeight + 1.5F*text.getCapHeight());
				text.draw(batch, "You need to research this detail", rocket1WindowX + 0.04F*rocket1WindowWidth, rocket1WindowY + 0.55F*rocket1WindowHeight);
			}else{
				text.draw(batch, "Название: " + InfoAndStats.UM_1034.getRocketDetailName(), rocket1WindowX + 0.04F*rocket1WindowWidth, rocket1WindowY + 0.55F*rocket1WindowHeight + 1.5F*text.getCapHeight());
				text.draw(batch, "Вы должны исследовать эту деталь", rocket1WindowX + 0.04F*rocket1WindowWidth, rocket1WindowY + 0.55F*rocket1WindowHeight);
			}
		}
	}
	private void MM_4TextDraw(){
		if(isMM_4Selected && isBoundDraw2 && InfoAndStats.MM_4.getRocketDetailAccessibility()){
			if(!InfoAndStats.lngRussian){
				text.draw(batch, "Name: " + InfoAndStats.MM_4.getRocketDetailName(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 1.5F*text.getCapHeight());
				text.draw(batch, "Thrust: " + InfoAndStats.MM_4.getRocketDetailThrust(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 3.0F*text.getCapHeight());
				text.draw(batch, "Specific impulse: " + InfoAndStats.MM_4.getRocketDetailImpulse(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 4.5F*text.getCapHeight());
				text.draw(batch, "Working time: " + InfoAndStats.MM_4.getRocketDetailTime(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 6.0F*text.getCapHeight());
				text.draw(batch, "Amount: " + InfoAndStats.MM_4.getRocketDetailAmount(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 7.5F*text.getCapHeight());
				text.draw(batch, "Level: " + InfoAndStats.MM_4.getRocketDetailLevel(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 9.0F*text.getCapHeight());
				text.draw(batch, "Price: " + InfoAndStats.MM_4.getRocketDetailPrice() + " cosmocoins", rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 10.5F*text.getCapHeight());
				text.draw(batch, "You have: " + InfoAndStats.money + " cosmocoins", rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 12.0F*text.getCapHeight());
			}else{
				text.draw(batch, "Название: " + InfoAndStats.MM_4.getRocketDetailName(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 1.5F*text.getCapHeight());
				text.draw(batch, "Тяга: " + InfoAndStats.MM_4.getRocketDetailThrust(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 3.0F*text.getCapHeight());
				text.draw(batch, "Удельный импульс: " + InfoAndStats.MM_4.getRocketDetailImpulse(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 4.5F*text.getCapHeight());
				text.draw(batch, "Рабочее время: " + InfoAndStats.MM_4.getRocketDetailTime(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 6.0F*text.getCapHeight());
				text.draw(batch, "Количество: " + InfoAndStats.MM_4.getRocketDetailAmount(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 7.5F*text.getCapHeight());
				text.draw(batch, "Уровень: " + InfoAndStats.MM_4.getRocketDetailLevel(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 9.0F*text.getCapHeight());
				text.draw(batch, "Цена: " + InfoAndStats.MM_4.getRocketDetailPrice() + " космкоинов", rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 10.5F*text.getCapHeight());
				text.draw(batch, "У Вас есть: " + InfoAndStats.money + " космокоинов", rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 12.0F*text.getCapHeight());
			}
			if(InfoAndStats.money >= InfoAndStats.MM_4.getRocketDetailPrice()){
				if(controller.isOn(buy1X, buy1Y, buy1Width, buy1Height)){
					buy3Sprite.draw(batch);
				}else{
					buy2Sprite.draw(batch);
				}
			}else{
				buy1Sprite.draw(batch);
			}
		}else if(!isBoundDraw2 && !InfoAndStats.MM_4.getRocketDetailAccessibility() && isMM_4PriceSelected){
			if(!InfoAndStats.lngRussian){
				text.draw(batch, "Name: " + InfoAndStats.MM_4.getRocketDetailName(), rocket1WindowX + 0.04F*rocket1WindowWidth, rocket1WindowY + 0.55F*rocket1WindowHeight + 1.5F*text.getCapHeight());
				text.draw(batch, "You need to research this detail", rocket1WindowX + 0.04F*rocket1WindowWidth, rocket1WindowY + 0.55F*rocket1WindowHeight);
			}else{
				text.draw(batch, "Название: " + InfoAndStats.MM_4.getRocketDetailName(), rocket1WindowX + 0.04F*rocket1WindowWidth, rocket1WindowY + 0.55F*rocket1WindowHeight + 1.5F*text.getCapHeight());
				text.draw(batch, "Вы должны исследовать эту деталь", rocket1WindowX + 0.04F*rocket1WindowWidth, rocket1WindowY + 0.55F*rocket1WindowHeight);
			}
		}
	}
	private void LM_087TextDraw(){
		if(isLM_087Selected && isBoundDraw3 && InfoAndStats.LM_087.getRocketDetailAccessibility()){
			if(!InfoAndStats.lngRussian){
				text.draw(batch, "Name: " + InfoAndStats.LM_087.getRocketDetailName(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 1.5F*text.getCapHeight());
				text.draw(batch, "Thrust: " + InfoAndStats.LM_087.getRocketDetailThrust(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 3.0F*text.getCapHeight());
				text.draw(batch, "Specific impulse: " + InfoAndStats.LM_087.getRocketDetailImpulse(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 4.5F*text.getCapHeight());
				text.draw(batch, "Working time: " + InfoAndStats.LM_087.getRocketDetailTime(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 6.0F*text.getCapHeight());
				text.draw(batch, "Amount: " + InfoAndStats.LM_087.getRocketDetailAmount(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 7.5F*text.getCapHeight());
				text.draw(batch, "Level: " + InfoAndStats.LM_087.getRocketDetailLevel(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 9.0F*text.getCapHeight());
				text.draw(batch, "Price: " + InfoAndStats.LM_087.getRocketDetailPrice() + " cosmocoins", rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 10.5F*text.getCapHeight());
				text.draw(batch, "You have: " + InfoAndStats.money + " cosmocoins", rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 12.0F*text.getCapHeight());
			}else{
				text.draw(batch, "Название: " + InfoAndStats.LM_087.getRocketDetailName(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 1.5F*text.getCapHeight());
				text.draw(batch, "Тяга: " + InfoAndStats.LM_087.getRocketDetailThrust(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 3.0F*text.getCapHeight());
				text.draw(batch, "Удельный импульс: " + InfoAndStats.LM_087.getRocketDetailImpulse(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 4.5F*text.getCapHeight());
				text.draw(batch, "Рабочее время: " + InfoAndStats.LM_087.getRocketDetailTime(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 6.0F*text.getCapHeight());
				text.draw(batch, "Количество: " + InfoAndStats.LM_087.getRocketDetailAmount(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 7.5F*text.getCapHeight());
				text.draw(batch, "Уровень: " + InfoAndStats.LM_087.getRocketDetailLevel(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 9.0F*text.getCapHeight());
				text.draw(batch, "Цена: " + InfoAndStats.LM_087.getRocketDetailPrice() + " космокоинов", rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 10.5F*text.getCapHeight());
				text.draw(batch, "У Вас есть: " + InfoAndStats.money + " космокоинов", rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 12.0F*text.getCapHeight());
			}
			if(InfoAndStats.money >= InfoAndStats.LM_087.getRocketDetailPrice()){
				if(controller.isOn(buy1X, buy1Y, buy1Width, buy1Height)){
					buy3Sprite.draw(batch);
				}else{
					buy2Sprite.draw(batch);
				}
			}else{
				buy1Sprite.draw(batch);
			}
		}else if(!isBoundDraw3 && !InfoAndStats.LM_087.getRocketDetailAccessibility() && isLM_087PriceSelected){
			if(!InfoAndStats.lngRussian){
				text.draw(batch, "Name: " + InfoAndStats.LM_087.getRocketDetailName(), rocket1WindowX + 0.04F*rocket1WindowWidth, rocket1WindowY + 0.55F*rocket1WindowHeight + 1.5F*text.getCapHeight());
				text.draw(batch, "You need to research this detail", rocket1WindowX + 0.04F*rocket1WindowWidth, rocket1WindowY + 0.55F*rocket1WindowHeight);
			}else{
				text.draw(batch, "Название: " + InfoAndStats.LM_087.getRocketDetailName(), rocket1WindowX + 0.04F*rocket1WindowWidth, rocket1WindowY + 0.55F*rocket1WindowHeight + 1.5F*text.getCapHeight());
				text.draw(batch, "Вы должны исследовать эту деталь", rocket1WindowX + 0.04F*rocket1WindowWidth, rocket1WindowY + 0.55F*rocket1WindowHeight);
			}
		}
	}
	private void UF_02TextDraw(){
		if(isUF_02Selected && isBoundDraw1 && InfoAndStats.UF_02.getRocketDetailAccessibility()){
			if(!InfoAndStats.lngRussian){
				text.draw(batch, "Name: " + InfoAndStats.UF_02.getRocketDetailName(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 1.5F*text.getCapHeight());
				text.draw(batch, "Thrust: " + InfoAndStats.UF_02.getRocketDetailThrust(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 3.0F*text.getCapHeight());
				text.draw(batch, "Specific impulse: " + InfoAndStats.UF_02.getRocketDetailImpulse(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 4.5F*text.getCapHeight());
				text.draw(batch, "Working time: " + InfoAndStats.UF_02.getRocketDetailTime(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 6.0F*text.getCapHeight());
				text.draw(batch, "Amount: " + InfoAndStats.UF_02.getRocketDetailAmount(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 7.5F*text.getCapHeight());
				text.draw(batch, "Level: " + InfoAndStats.UF_02.getRocketDetailLevel(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 9.0F*text.getCapHeight());
				text.draw(batch, "Price: " + InfoAndStats.UF_02.getRocketDetailPrice() + " cosmocoins", rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 10.5F*text.getCapHeight());
				text.draw(batch, "You have: " + InfoAndStats.money + " cosmocoins", rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 12.0F*text.getCapHeight());
			}else{
				text.draw(batch, "Название: " + InfoAndStats.UF_02.getRocketDetailName(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 1.5F*text.getCapHeight());
				text.draw(batch, "Тяга: " + InfoAndStats.UF_02.getRocketDetailThrust(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 3.0F*text.getCapHeight());
				text.draw(batch, "Удельный импульс: " + InfoAndStats.UF_02.getRocketDetailImpulse(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 4.5F*text.getCapHeight());
				text.draw(batch, "Рабочее время: " + InfoAndStats.UF_02.getRocketDetailTime(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 6.0F*text.getCapHeight());
				text.draw(batch, "Количество: " + InfoAndStats.UF_02.getRocketDetailAmount(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 7.5F*text.getCapHeight());
				text.draw(batch, "Уровень: " + InfoAndStats.UF_02.getRocketDetailLevel(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 9.0F*text.getCapHeight());
				text.draw(batch, "Цена: " + InfoAndStats.UF_02.getRocketDetailPrice() + " космокоинов", rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 10.5F*text.getCapHeight());
				text.draw(batch, "У Вас есть: " + InfoAndStats.money + " космкоинов", rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 12.0F*text.getCapHeight());
			}
			if(InfoAndStats.money >= InfoAndStats.UF_02.getRocketDetailPrice()){
				if(controller.isOn(buy1X, buy1Y, buy1Width, buy1Height)){
					buy3Sprite.draw(batch);
				}else{
					buy2Sprite.draw(batch);
				}
			}else{
				buy1Sprite.draw(batch);
			}
		}else if(!isBoundDraw1 && !InfoAndStats.UF_02.getRocketDetailAccessibility() && isUF_02PriceSelected){
			if(!InfoAndStats.lngRussian){
				text.draw(batch, "Name: " + InfoAndStats.UF_02.getRocketDetailName(), rocket1WindowX + 0.04F*rocket1WindowWidth, rocket1WindowY + 0.55F*rocket1WindowHeight + 1.5F*text.getCapHeight());
				text.draw(batch, "You need to research this detail", rocket1WindowX + 0.04F*rocket1WindowWidth, rocket1WindowY + 0.55F*rocket1WindowHeight);
			}else{
				text.draw(batch, "Название: " + InfoAndStats.UF_02.getRocketDetailName(), rocket1WindowX + 0.04F*rocket1WindowWidth, rocket1WindowY + 0.55F*rocket1WindowHeight + 1.5F*text.getCapHeight());
				text.draw(batch, "Вы должны исследовать эту деталь", rocket1WindowX + 0.04F*rocket1WindowWidth, rocket1WindowY + 0.55F*rocket1WindowHeight);
			}
		}
	}
	private void MF_043TextDraw(){
		if(isMF_043Selected && isBoundDraw2 && InfoAndStats.MF_043.getRocketDetailAccessibility()){
			if(!InfoAndStats.lngRussian){
				text.draw(batch, "Name: " + InfoAndStats.MF_043.getRocketDetailName(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 1.5F*text.getCapHeight());
				text.draw(batch, "Thrust: " + InfoAndStats.MF_043.getRocketDetailThrust(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 3.0F*text.getCapHeight());
				text.draw(batch, "Specific impulse: " + InfoAndStats.MF_043.getRocketDetailImpulse(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 4.5F*text.getCapHeight());
				text.draw(batch, "Working time: " + InfoAndStats.MF_043.getRocketDetailTime(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 6.0F*text.getCapHeight());
				text.draw(batch, "Amount: " + InfoAndStats.MF_043.getRocketDetailAmount(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 7.5F*text.getCapHeight());
				text.draw(batch, "Level: " + InfoAndStats.MF_043.getRocketDetailLevel(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 9.0F*text.getCapHeight());
				text.draw(batch, "Price: " + InfoAndStats.MF_043.getRocketDetailPrice() + " cosmocoins", rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 10.5F*text.getCapHeight());
				text.draw(batch, "You have: " + InfoAndStats.money + " cosmocoins", rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 12.0F*text.getCapHeight());
			}else{
				text.draw(batch, "Название: " + InfoAndStats.MF_043.getRocketDetailName(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 1.5F*text.getCapHeight());
				text.draw(batch, "Тяга: " + InfoAndStats.MF_043.getRocketDetailThrust(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 3.0F*text.getCapHeight());
				text.draw(batch, "Удельный импульс: " + InfoAndStats.MF_043.getRocketDetailImpulse(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 4.5F*text.getCapHeight());
				text.draw(batch, "Рабочее время: " + InfoAndStats.MF_043.getRocketDetailTime(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 6.0F*text.getCapHeight());
				text.draw(batch, "Количество: " + InfoAndStats.MF_043.getRocketDetailAmount(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 7.5F*text.getCapHeight());
				text.draw(batch, "Уровень: " + InfoAndStats.MF_043.getRocketDetailLevel(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 9.0F*text.getCapHeight());
				text.draw(batch, "Цена: " + InfoAndStats.MF_043.getRocketDetailPrice() + " космкоинов", rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 10.5F*text.getCapHeight());
				text.draw(batch, "У Вас есть: " + InfoAndStats.money + " космокоинов", rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 12.0F*text.getCapHeight());
			}
			if(InfoAndStats.money >= InfoAndStats.MF_043.getRocketDetailPrice()){
				if(controller.isOn(buy1X, buy1Y, buy1Width, buy1Height)){
					buy3Sprite.draw(batch);
				}else{
					buy2Sprite.draw(batch);
				}
			}else{
				buy1Sprite.draw(batch);
			}
		}else if(!isBoundDraw2 && !InfoAndStats.MF_043.getRocketDetailAccessibility() && isMF_043PriceSelected){
			if(!InfoAndStats.lngRussian){
				text.draw(batch, "Name: " + InfoAndStats.MF_043.getRocketDetailName(), rocket1WindowX + 0.04F*rocket1WindowWidth, rocket1WindowY + 0.55F*rocket1WindowHeight + 1.5F*text.getCapHeight());
				text.draw(batch, "You need to research this detail", rocket1WindowX + 0.04F*rocket1WindowWidth, rocket1WindowY + 0.55F*rocket1WindowHeight);
			}else{
				text.draw(batch, "Название: " + InfoAndStats.MF_043.getRocketDetailName(), rocket1WindowX + 0.04F*rocket1WindowWidth, rocket1WindowY + 0.55F*rocket1WindowHeight + 1.5F*text.getCapHeight());
				text.draw(batch, "Вы должны исследовать эту деталь", rocket1WindowX + 0.04F*rocket1WindowWidth, rocket1WindowY + 0.55F*rocket1WindowHeight);
			}
		}
	}
	private void LF_15TextDraw(){
		if(isLF_15Selected && isBoundDraw3 && InfoAndStats.LF_15.getRocketDetailAccessibility()){
			if(!InfoAndStats.lngRussian){
				text.draw(batch, "Name: " + InfoAndStats.LF_15.getRocketDetailName(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 1.5F*text.getCapHeight());
				text.draw(batch, "Thrust: " + InfoAndStats.LF_15.getRocketDetailThrust(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 3.0F*text.getCapHeight());
				text.draw(batch, "Specific impulse: " + InfoAndStats.LF_15.getRocketDetailImpulse(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 4.5F*text.getCapHeight());
				text.draw(batch, "Working time: " + InfoAndStats.LF_15.getRocketDetailTime(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 6.0F*text.getCapHeight());
				text.draw(batch, "Amount: " + InfoAndStats.LF_15.getRocketDetailAmount(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 7.5F*text.getCapHeight());
				text.draw(batch, "Level: " + InfoAndStats.LF_15.getRocketDetailLevel(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 9.0F*text.getCapHeight());
				text.draw(batch, "Price: " + InfoAndStats.LF_15.getRocketDetailPrice() + " cosmocoins", rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 10.5F*text.getCapHeight());
				text.draw(batch, "You have: " + InfoAndStats.money + " cosmocoins", rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 12.0F*text.getCapHeight());
			}else{
				text.draw(batch, "Название: " + InfoAndStats.LF_15.getRocketDetailName(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 1.5F*text.getCapHeight());
				text.draw(batch, "Тяга: " + InfoAndStats.LF_15.getRocketDetailThrust(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 3.0F*text.getCapHeight());
				text.draw(batch, "Удельный импульс: " + InfoAndStats.LF_15.getRocketDetailImpulse(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 4.5F*text.getCapHeight());
				text.draw(batch, "Рабочее время: " + InfoAndStats.LF_15.getRocketDetailTime(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 6.0F*text.getCapHeight());
				text.draw(batch, "Количество: " + InfoAndStats.LF_15.getRocketDetailAmount(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 7.5F*text.getCapHeight());
				text.draw(batch, "Уровень: " + InfoAndStats.LF_15.getRocketDetailLevel(), rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 9.0F*text.getCapHeight());
				text.draw(batch, "Цена: " + InfoAndStats.LF_15.getRocketDetailPrice() + " космокоинов", rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 10.5F*text.getCapHeight());
				text.draw(batch, "У Вас есть: " + InfoAndStats.money + " космокоинов", rocket1WindowX + 0.035F*rocket1WindowWidth, rocket1WindowY + 0.875F*rocket1WindowHeight - 12.0F*text.getCapHeight());
			}
			if(InfoAndStats.money >= InfoAndStats.LF_15.getRocketDetailPrice()){
				if(controller.isOn(buy1X, buy1Y, buy1Width, buy1Height)){
					buy3Sprite.draw(batch);
				}else{
					buy2Sprite.draw(batch);
				}
			}else{
				buy1Sprite.draw(batch);
			}
		}else if(!isBoundDraw3 && !InfoAndStats.LF_15.getRocketDetailAccessibility() && isLF_15PriceSelected){
			if(!InfoAndStats.lngRussian){
				text.draw(batch, "Name: " + InfoAndStats.LF_15.getRocketDetailName(), rocket1WindowX + 0.04F*rocket1WindowWidth, rocket1WindowY + 0.55F*rocket1WindowHeight + 1.5F*text.getCapHeight());
				text.draw(batch, "You need to research this detail", rocket1WindowX + 0.04F*rocket1WindowWidth, rocket1WindowY + 0.55F*rocket1WindowHeight);
			}else{
				text.draw(batch, "Название: " + InfoAndStats.LF_15.getRocketDetailName(), rocket1WindowX + 0.04F*rocket1WindowWidth, rocket1WindowY + 0.55F*rocket1WindowHeight + 1.5F*text.getCapHeight());
				text.draw(batch, "Вы должны исследовать эту деталь", rocket1WindowX + 0.04F*rocket1WindowWidth, rocket1WindowY + 0.55F*rocket1WindowHeight);
			}
		}
	}
	
	private void buttonListeners(){
		//Слушатель нажатия на кнопку "Back"//
		if(!isRocket1WindowDraw)
			if(controller.isClicked(backButtonX, backButtonY, backButtonWidth, backButtonHeight)){
				game.setScreen(new GameScreen(game));
				this.dispose();
			}
		//Слушатель нажатия на ракетную деталь 1//
		if(!isRocket1DetailActive && !isRocket2DetailActive && !isRocket3DetailActive)
			if(controller.isClicked(rocket11X, rocket11Y, rocket11Width, rocket11Height)){
				if(!isRocket1WindowDraw) isRocket1WindowDraw = true;
				isRocket1DetailActive = true;
			}
		//Слушатель нажатия на ракетную деталь 2//
		if(!isRocket1DetailActive && !isRocket2DetailActive && !isRocket3DetailActive)
			if(controller.isClicked(rocket21X, rocket21Y, rocket21Width, rocket21Height)){
				if(!isRocket1WindowDraw) isRocket1WindowDraw = true;
				isRocket2DetailActive = true;
			}
		//Слушатель нажатия на ракетную деталь 3//
		if(!isRocket1DetailActive && !isRocket2DetailActive && !isRocket3DetailActive) 
			if(controller.isClicked(rocket31X, rocket31Y, rocket31Width, rocket31Height)){
				if(!isRocket1WindowDraw) isRocket1WindowDraw = true;
				isRocket3DetailActive = true;
			}
		//Слушатель нажатия на выход из диалогового окна деталей ракет//
		if(controller.isClicked(backRocket1Window1X, backRocket1Window1Y, backRocket1Window1Width, backRocket1Window1Height)){
			if(isRocket1WindowDraw){
				isRocket1WindowDraw = false;
				isRocket1DetailActive = false;
				isRocket2DetailActive = false;
				isRocket3DetailActive = false;
			}
		}
		//Слушатель нажатия на деталь UT_1202//
		if(isRocket1WindowDraw){
			if(isRocket1DetailActive){
				if(controller.isClicked(UT_1202X, UT_1202Y, UT_1202Width, UT_1202Height) && InfoAndStats.UT_1202.getRocketDetailAccessibility()){
					if(!isBoundDraw1){
						InfoAndStats.selectedFirst = "UT_1202";
						boundGold1X = InfoAndStats.getFirstStage().getX() - 0.075F*InfoAndStats.getFirstStage().getWidth();
						boundGold1Y = InfoAndStats.getFirstStage().getY() - 0.075F*InfoAndStats.getFirstStage().getHeight();
						boundGold1Width = 1.15F*InfoAndStats.getFirstStage().getWidth();
						boundGold1Height = 1.15F*InfoAndStats.getFirstStage().getHeight();
						InfoAndStats.firstStage.setRocketDetailAccessibility(InfoAndStats.UT_1202.getRocketDetailAccessibility());
						InfoAndStats.firstStage.setRocketDetailAmount(InfoAndStats.UT_1202.getRocketDetailAmount());
						InfoAndStats.firstStage.setRocketDetailImpulse(InfoAndStats.UT_1202.getRocketDetailImpulse());
						InfoAndStats.firstStage.setRocketDetailName(InfoAndStats.UT_1202.getRocketDetailName(), InfoAndStats.UT_1202.getRocketDetailName());
						InfoAndStats.firstStage.setRocketDetailThrust(InfoAndStats.UT_1202.getRocketDetailThrust());
						InfoAndStats.firstStage.setRocketDetailTime(InfoAndStats.UT_1202.getRocketDetailTime());
						isBoundDraw1 = true;
						isUT_1202Selected = true;
						isUM_1034PriceSelected = false;
						isUF_02PriceSelected = false;
					}
					else if(isBoundDraw1 && isUT_1202Selected){
						isBoundDraw1 = false;
						InfoAndStats.selectedFirst = "null";
						InfoAndStats.firstStage.setRocketDetailAccessibility(false);
						InfoAndStats.firstStage.setRocketDetailAmount(0);
						InfoAndStats.firstStage.setRocketDetailImpulse(0.0F);
						InfoAndStats.firstStage.setRocketDetailName("","");
						InfoAndStats.firstStage.setRocketDetailThrust(0.0F);
						InfoAndStats.firstStage.setRocketDetailTime(0.0F);
						isUT_1202Selected = false;
					}else if(isBoundDraw1 && !isUT_1202Selected){
						isUM_1034Selected = false;
						isUM_1034PriceSelected = false;
						isUF_02Selected = false;
						isUF_02PriceSelected = false;
						InfoAndStats.selectedFirst = "UT_1202";
						boundGold1X = InfoAndStats.getFirstStage().getX() - 0.075F*InfoAndStats.getFirstStage().getWidth();
						boundGold1Y = InfoAndStats.getFirstStage().getY() - 0.075F*InfoAndStats.getFirstStage().getHeight();
						boundGold1Width = 1.15F*InfoAndStats.getFirstStage().getWidth();
						boundGold1Height = 1.15F*InfoAndStats.getFirstStage().getHeight();
						InfoAndStats.firstStage.setRocketDetailAccessibility(InfoAndStats.UT_1202.getRocketDetailAccessibility());
						InfoAndStats.firstStage.setRocketDetailAmount(InfoAndStats.UT_1202.getRocketDetailAmount());
						InfoAndStats.firstStage.setRocketDetailImpulse(InfoAndStats.UT_1202.getRocketDetailImpulse());
						InfoAndStats.firstStage.setRocketDetailName(InfoAndStats.UT_1202.getRocketDetailName(), InfoAndStats.UT_1202.getRocketDetailName());
						InfoAndStats.firstStage.setRocketDetailThrust(InfoAndStats.UT_1202.getRocketDetailThrust());
						InfoAndStats.firstStage.setRocketDetailTime(InfoAndStats.UT_1202.getRocketDetailTime());
						isBoundDraw1 = true;
						isUT_1202Selected = true;
						isUM_1034PriceSelected = false;
						isUF_02PriceSelected = false;
					}
					boundGold1.setBounds(UT_1202X - 0.075F*UT_1202Width, UT_1202Y - 0.075F*UT_1202Height, 1.15F*UT_1202Width, 1.15F*UT_1202Height);
					boundGold1XPREV = boundGold1.getX();
					boundGold1YPREV = boundGold1.getY();
					boundGold1WidthPREV = boundGold1.getWidth();
					boundGold1HeightPREV = boundGold1.getHeight();
				}
				if(isUT_1202Selected && controller.isClicked(buy1X, buy1Y, buy1Width, buy1Height) && InfoAndStats.money >= InfoAndStats.UT_1202.getRocketDetailPrice()){
					InfoAndStats.money -= InfoAndStats.UT_1202.getRocketDetailPrice();
					InfoAndStats.UT_1202.setRocketDetailAmount(InfoAndStats.UT_1202.getRocketDetailAmount() + 1);
					InfoAndStats.firstStage.setRocketDetailAmount(InfoAndStats.UT_1202.getRocketDetailAmount());
				}
			}
		}
		//Слушатель нажатия на деталь MT_0112//
		if(isRocket1WindowDraw){
			if(isRocket2DetailActive){
				if(controller.isClicked(MT_0112X, MT_0112Y, MT_0112Width, MT_0112Height) && InfoAndStats.MT_0112.getRocketDetailAccessibility()){
					if(!isBoundDraw2){
						InfoAndStats.selectedSecond = "MT_0112";
						boundGold2X = InfoAndStats.getSecondStage().getX() - 0.075F*InfoAndStats.getSecondStage().getWidth();
						boundGold2Y = InfoAndStats.getSecondStage().getY() - 0.075F*InfoAndStats.getSecondStage().getHeight();
						boundGold2Width = 1.15F*InfoAndStats.getSecondStage().getWidth();
						boundGold2Height = 1.15F*InfoAndStats.getSecondStage().getHeight();
						InfoAndStats.secondStage.setRocketDetailAccessibility(InfoAndStats.MT_0112.getRocketDetailAccessibility());
						InfoAndStats.secondStage.setRocketDetailAmount(InfoAndStats.MT_0112.getRocketDetailAmount());
						InfoAndStats.secondStage.setRocketDetailImpulse(InfoAndStats.MT_0112.getRocketDetailImpulse());
						InfoAndStats.secondStage.setRocketDetailName(InfoAndStats.MT_0112.getRocketDetailName(), InfoAndStats.MT_0112.getRocketDetailName());
						InfoAndStats.secondStage.setRocketDetailThrust(InfoAndStats.MT_0112.getRocketDetailThrust());
						InfoAndStats.secondStage.setRocketDetailTime(InfoAndStats.MT_0112.getRocketDetailTime());
						isBoundDraw2 = true;
						isMT_0112Selected = true;
						isMM_4PriceSelected = false;
						isMF_043PriceSelected = false;
					}
					else if(isBoundDraw2 && isMT_0112Selected){
						isBoundDraw2 = false;
						InfoAndStats.selectedSecond = "null";
						InfoAndStats.secondStage.setRocketDetailAccessibility(false);
						InfoAndStats.secondStage.setRocketDetailAmount(0);
						InfoAndStats.secondStage.setRocketDetailImpulse(0.0F);
						InfoAndStats.secondStage.setRocketDetailName("","");
						InfoAndStats.secondStage.setRocketDetailThrust(0.0F);
						InfoAndStats.secondStage.setRocketDetailTime(0.0F);
						isMT_0112Selected = false;
						isMM_4PriceSelected = false;
						isMF_043PriceSelected = false;
					}else if(isBoundDraw2 && !isMT_0112Selected){
						InfoAndStats.selectedSecond = "MT_0112";
						boundGold2X = InfoAndStats.getSecondStage().getX() - 0.075F*InfoAndStats.getSecondStage().getWidth();
						boundGold2Y = InfoAndStats.getSecondStage().getY() - 0.075F*InfoAndStats.getSecondStage().getHeight();
						boundGold2Width = 1.15F*InfoAndStats.getSecondStage().getWidth();
						boundGold2Height = 1.15F*InfoAndStats.getSecondStage().getHeight();
						InfoAndStats.secondStage.setRocketDetailAccessibility(InfoAndStats.MT_0112.getRocketDetailAccessibility());
						InfoAndStats.secondStage.setRocketDetailAmount(InfoAndStats.MT_0112.getRocketDetailAmount());
						InfoAndStats.secondStage.setRocketDetailImpulse(InfoAndStats.MT_0112.getRocketDetailImpulse());
						InfoAndStats.secondStage.setRocketDetailName(InfoAndStats.MT_0112.getRocketDetailName(), InfoAndStats.MT_0112.getRocketDetailName());
						InfoAndStats.secondStage.setRocketDetailThrust(InfoAndStats.MT_0112.getRocketDetailThrust());
						InfoAndStats.secondStage.setRocketDetailTime(InfoAndStats.MT_0112.getRocketDetailTime());
						isBoundDraw2 = true;
						isMT_0112Selected = true;
						isMM_4Selected = false;
						isMM_4PriceSelected = false;
						isMF_043Selected = false;
						isMF_043PriceSelected = false;
					}
					boundGold2.setBounds(MT_0112X - 0.075F*MT_0112Width, MT_0112Y - 0.075F*MT_0112Height, 1.15F*MT_0112Width, 1.15F*MT_0112Height);
					boundGold2XPREV = boundGold2.getX();
					boundGold2YPREV = boundGold2.getY();
					boundGold2WidthPREV = boundGold2.getWidth();
					boundGold2HeightPREV = boundGold2.getHeight();
				}
				if(isMT_0112Selected && controller.isClicked(buy1X, buy1Y, buy1Width, buy1Height) && InfoAndStats.money >= InfoAndStats.MT_0112.getRocketDetailPrice()){
					InfoAndStats.money -= InfoAndStats.MT_0112.getRocketDetailPrice();
					InfoAndStats.MT_0112.setRocketDetailAmount(InfoAndStats.MT_0112.getRocketDetailAmount() + 1);
					InfoAndStats.secondStage.setRocketDetailAmount(InfoAndStats.MT_0112.getRocketDetailAmount());
				}
			}
		}
		//Слушатель нажатия на деталь LT_116//
		if(isRocket1WindowDraw){
			if(isRocket3DetailActive){
				if(controller.isClicked(LT_116X, LT_116Y, LT_116Width, LT_116Height) && InfoAndStats.LT_116.getRocketDetailAccessibility()){
					if(!isBoundDraw3){
						InfoAndStats.selectedThird = "LT_116";
						boundGold3X = InfoAndStats.getThirdStage().getX() - 0.075F*InfoAndStats.getThirdStage().getWidth();
						boundGold3Y = InfoAndStats.getThirdStage().getY() - 0.075F*InfoAndStats.getThirdStage().getHeight();
						boundGold3Width = 1.15F*InfoAndStats.getThirdStage().getWidth();
						boundGold3Height = 1.15F*InfoAndStats.getThirdStage().getHeight();
						InfoAndStats.thirdStage.setRocketDetailAccessibility(InfoAndStats.LT_116.getRocketDetailAccessibility());
						InfoAndStats.thirdStage.setRocketDetailAmount(InfoAndStats.LT_116.getRocketDetailAmount());
						InfoAndStats.thirdStage.setRocketDetailImpulse(InfoAndStats.LT_116.getRocketDetailImpulse());
						InfoAndStats.thirdStage.setRocketDetailName(InfoAndStats.LT_116.getRocketDetailName(), InfoAndStats.LT_116.getRocketDetailName());
						InfoAndStats.thirdStage.setRocketDetailThrust(InfoAndStats.LT_116.getRocketDetailThrust());
						InfoAndStats.thirdStage.setRocketDetailTime(InfoAndStats.LT_116.getRocketDetailTime());
						isBoundDraw3 = true;
						isLT_116Selected = true;
						isLM_087PriceSelected = false;
						isLF_15PriceSelected = false;
					}
					else if(isBoundDraw3 && isLT_116Selected){
						isBoundDraw3 = false;
						InfoAndStats.selectedThird = "null";
						InfoAndStats.thirdStage.setRocketDetailAccessibility(false);
						InfoAndStats.thirdStage.setRocketDetailAmount(0);
						InfoAndStats.thirdStage.setRocketDetailImpulse(0.0F);
						InfoAndStats.thirdStage.setRocketDetailName("","");
						InfoAndStats.thirdStage.setRocketDetailThrust(0.0F);
						InfoAndStats.thirdStage.setRocketDetailTime(0.0F);
						isLT_116Selected = false;
						isLM_087PriceSelected = false;
						isLF_15PriceSelected = false;
					}else if(isBoundDraw3 && !isLT_116Selected){
						InfoAndStats.selectedThird = "LT_116";
						boundGold3X = InfoAndStats.getThirdStage().getX() - 0.075F*InfoAndStats.getThirdStage().getWidth();
						boundGold3Y = InfoAndStats.getThirdStage().getY() - 0.075F*InfoAndStats.getThirdStage().getHeight();
						boundGold3Width = 1.15F*InfoAndStats.getThirdStage().getWidth();
						boundGold3Height = 1.15F*InfoAndStats.getThirdStage().getHeight();
						InfoAndStats.thirdStage.setRocketDetailAccessibility(InfoAndStats.LT_116.getRocketDetailAccessibility());
						InfoAndStats.thirdStage.setRocketDetailAmount(InfoAndStats.LT_116.getRocketDetailAmount());
						InfoAndStats.thirdStage.setRocketDetailImpulse(InfoAndStats.LT_116.getRocketDetailImpulse());
						InfoAndStats.thirdStage.setRocketDetailName(InfoAndStats.LT_116.getRocketDetailName(), InfoAndStats.LT_116.getRocketDetailName());
						InfoAndStats.thirdStage.setRocketDetailThrust(InfoAndStats.LT_116.getRocketDetailThrust());
						InfoAndStats.thirdStage.setRocketDetailTime(InfoAndStats.LT_116.getRocketDetailTime());
						isBoundDraw3 = true;
						isLT_116Selected = true;
						isLM_087PriceSelected = false;
						isLM_087Selected = false;
						isLF_15PriceSelected = false;
						isLF_15Selected = false;
					}
					boundGold3.setBounds(LT_116X - 0.075F*LT_116Width, LT_116Y - 0.075F*LT_116Height, 1.15F*LT_116Width, 1.15F*LT_116Height);
					boundGold3XPREV = boundGold3.getX();
					boundGold3YPREV = boundGold3.getY();
					boundGold3WidthPREV = boundGold3.getWidth();
					boundGold3HeightPREV = boundGold3.getHeight();
				}
				if(isLT_116Selected && controller.isClicked(buy1X, buy1Y, buy1Width, buy1Height) && InfoAndStats.money >= InfoAndStats.LT_116.getRocketDetailPrice()){
					InfoAndStats.money -= InfoAndStats.LT_116.getRocketDetailPrice();
					InfoAndStats.LT_116.setRocketDetailAmount(InfoAndStats.LT_116.getRocketDetailAmount() + 1);
					InfoAndStats.thirdStage.setRocketDetailAmount(InfoAndStats.LT_116.getRocketDetailAmount());
				}
			}
		}
		//Слушатель нажатия на деталь UM_1034//
		if(isRocket1WindowDraw){
			if(isRocket1DetailActive){
				if(controller.isClicked(UM_1034X, UM_1034Y, UM_1034Width, UM_1034Height)){
					if(!isBoundDraw1 && InfoAndStats.UM_1034.getRocketDetailAccessibility()){
						InfoAndStats.selectedFirst = "UM_1034";
						boundGold1X = InfoAndStats.getFirstStage().getX() - 0.075F*InfoAndStats.getFirstStage().getWidth();
						boundGold1Y = InfoAndStats.getFirstStage().getY() - 0.075F*InfoAndStats.getFirstStage().getHeight();
						boundGold1Width = 1.15F*InfoAndStats.getFirstStage().getWidth();
						boundGold1Height = 1.15F*InfoAndStats.getFirstStage().getHeight();
						InfoAndStats.firstStage.setRocketDetailAccessibility(InfoAndStats.UM_1034.getRocketDetailAccessibility());
						InfoAndStats.firstStage.setRocketDetailAmount(InfoAndStats.UM_1034.getRocketDetailAmount());
						InfoAndStats.firstStage.setRocketDetailImpulse(InfoAndStats.UM_1034.getRocketDetailImpulse());
						InfoAndStats.firstStage.setRocketDetailName(InfoAndStats.UM_1034.getRocketDetailName(), InfoAndStats.UM_1034.getRocketDetailName());
						InfoAndStats.firstStage.setRocketDetailThrust(InfoAndStats.UM_1034.getRocketDetailThrust());
						InfoAndStats.firstStage.setRocketDetailTime(InfoAndStats.UM_1034.getRocketDetailTime());
						isBoundDraw1 = true;
						isUM_1034Selected = true;
						boundGold1.setBounds(UM_1034X - 0.075F*UM_1034Width, UM_1034Y - 0.075F*UM_1034Height, 1.15F*UM_1034Width, 1.15F*UM_1034Height);
						boundGold1XPREV = boundGold1.getX();
						boundGold1YPREV = boundGold1.getY();
						boundGold1WidthPREV = boundGold1.getWidth();
						boundGold1HeightPREV = boundGold1.getHeight();
					}else if(isBoundDraw1 && isUM_1034Selected && InfoAndStats.UM_1034.getRocketDetailAccessibility()){
						isBoundDraw1 = false;
						InfoAndStats.selectedFirst = "null";
						InfoAndStats.firstStage.setRocketDetailAccessibility(false);
						InfoAndStats.firstStage.setRocketDetailAmount(0);
						InfoAndStats.firstStage.setRocketDetailImpulse(0.0F);
						InfoAndStats.firstStage.setRocketDetailName("","");
						InfoAndStats.firstStage.setRocketDetailThrust(0.0F);
						InfoAndStats.firstStage.setRocketDetailTime(0.0F);
						isUM_1034Selected = false;
						isUF_02Selected = false;
					}else if(isBoundDraw1 && !isUM_1034Selected && InfoAndStats.UM_1034.getRocketDetailAccessibility()){
						isUT_1202Selected = false;
						isUF_02Selected = false;
						InfoAndStats.selectedFirst = "UM_1034";
						boundGold1X = InfoAndStats.getFirstStage().getX() - 0.075F*InfoAndStats.getFirstStage().getWidth();
						boundGold1Y = InfoAndStats.getFirstStage().getY() - 0.075F*InfoAndStats.getFirstStage().getHeight();
						boundGold1Width = 1.15F*InfoAndStats.getFirstStage().getWidth();
						boundGold1Height = 1.15F*InfoAndStats.getFirstStage().getHeight();
						InfoAndStats.firstStage.setRocketDetailAccessibility(InfoAndStats.UM_1034.getRocketDetailAccessibility());
						InfoAndStats.firstStage.setRocketDetailAmount(InfoAndStats.UM_1034.getRocketDetailAmount());
						InfoAndStats.firstStage.setRocketDetailImpulse(InfoAndStats.UM_1034.getRocketDetailImpulse());
						InfoAndStats.firstStage.setRocketDetailName(InfoAndStats.UM_1034.getRocketDetailName(), InfoAndStats.UM_1034.getRocketDetailName());
						InfoAndStats.firstStage.setRocketDetailThrust(InfoAndStats.UM_1034.getRocketDetailThrust());
						InfoAndStats.firstStage.setRocketDetailTime(InfoAndStats.UM_1034.getRocketDetailTime());
						isBoundDraw1 = true;
						isUM_1034Selected = true;
						boundGold1.setBounds(UM_1034X - 0.075F*UM_1034Width, UM_1034Y - 0.075F*UM_1034Height, 1.15F*UM_1034Width, 1.15F*UM_1034Height);
						boundGold1XPREV = boundGold1.getX();
						boundGold1YPREV = boundGold1.getY();
						boundGold1WidthPREV = boundGold1.getWidth();
						boundGold1HeightPREV = boundGold1.getHeight();
					}else if(!InfoAndStats.UM_1034.getRocketDetailAccessibility()){
						if(isUM_1034PriceSelected){
							isUM_1034PriceSelected = false;
							isUF_02PriceSelected = false;
						}else{
							isUM_1034PriceSelected = true;
							isUF_02PriceSelected = false;
						}
					}
				}
				if(isUM_1034Selected && controller.isClicked(buy1X, buy1Y, buy1Width, buy1Height) && InfoAndStats.money >= InfoAndStats.UM_1034.getRocketDetailPrice()){
					InfoAndStats.money -= InfoAndStats.UM_1034.getRocketDetailPrice();
					InfoAndStats.UM_1034.setRocketDetailAmount(InfoAndStats.UM_1034.getRocketDetailAmount() + 1);
					InfoAndStats.firstStage.setRocketDetailAmount(InfoAndStats.UM_1034.getRocketDetailAmount());
				}
			}
		}
		//Слушатель нажатия на деталь MM_4//
		if(isRocket1WindowDraw){
			if(isRocket2DetailActive){
				if(controller.isClicked(MM_4X, MM_4Y, MM_4Width, MM_4Height)){
					if(!isBoundDraw2 && InfoAndStats.MM_4.getRocketDetailAccessibility()){
						InfoAndStats.selectedSecond = "MM_4";
						boundGold2X = InfoAndStats.getSecondStage().getX() - 0.075F*InfoAndStats.getSecondStage().getWidth();
						boundGold2Y = InfoAndStats.getSecondStage().getY() - 0.075F*InfoAndStats.getSecondStage().getHeight();
						boundGold2Width = 1.15F*InfoAndStats.getSecondStage().getWidth();
						boundGold2Height = 1.15F*InfoAndStats.getSecondStage().getHeight();
						InfoAndStats.secondStage.setRocketDetailAccessibility(InfoAndStats.MM_4.getRocketDetailAccessibility());
						InfoAndStats.secondStage.setRocketDetailAmount(InfoAndStats.MM_4.getRocketDetailAmount());
						InfoAndStats.secondStage.setRocketDetailImpulse(InfoAndStats.MM_4.getRocketDetailImpulse());
						InfoAndStats.secondStage.setRocketDetailName(InfoAndStats.MM_4.getRocketDetailName(), InfoAndStats.MM_4.getRocketDetailName());
						InfoAndStats.secondStage.setRocketDetailThrust(InfoAndStats.MM_4.getRocketDetailThrust());
						InfoAndStats.secondStage.setRocketDetailTime(InfoAndStats.MM_4.getRocketDetailTime());
						isBoundDraw2 = true;
						isMM_4Selected = true;
						boundGold2.setBounds(MM_4X - 0.075F*MM_4Width, MM_4Y - 0.075F*MM_4Height, 1.15F*MM_4Width, 1.15F*MM_4Height);
						boundGold2XPREV = boundGold2.getX();
						boundGold2YPREV = boundGold2.getY();
						boundGold2WidthPREV = boundGold2.getWidth();
						boundGold2HeightPREV = boundGold2.getHeight();
					}
					else if(isBoundDraw2 && isMM_4Selected && InfoAndStats.MM_4.getRocketDetailAccessibility()){
						isBoundDraw2 = false;
						InfoAndStats.selectedSecond = "null";
						InfoAndStats.secondStage.setRocketDetailAccessibility(false);
						InfoAndStats.secondStage.setRocketDetailAmount(0);
						InfoAndStats.secondStage.setRocketDetailImpulse(0.0F);
						InfoAndStats.secondStage.setRocketDetailName("","");
						InfoAndStats.secondStage.setRocketDetailThrust(0.0F);
						InfoAndStats.secondStage.setRocketDetailTime(0.0F);
						isMM_4Selected = false;
						boundGold2.setBounds(MM_4X - 0.075F*MM_4Width, MM_4Y - 0.075F*MM_4Height, 1.15F*MM_4Width, 1.15F*MM_4Height);
						boundGold2XPREV = boundGold2.getX();
						boundGold2YPREV = boundGold2.getY();
						boundGold2WidthPREV = boundGold2.getWidth();
						boundGold2HeightPREV = boundGold2.getHeight();
					}else if(isBoundDraw2 && !isMM_4Selected && InfoAndStats.MM_4.getRocketDetailAccessibility()){
						isMT_0112Selected = false;
						isMF_043Selected = false;
						InfoAndStats.selectedSecond = "MM_4";
						boundGold2X = InfoAndStats.getSecondStage().getX() - 0.075F*InfoAndStats.getSecondStage().getWidth();
						boundGold2Y = InfoAndStats.getSecondStage().getY() - 0.075F*InfoAndStats.getSecondStage().getHeight();
						boundGold2Width = 1.15F*InfoAndStats.getSecondStage().getWidth();
						boundGold2Height = 1.15F*InfoAndStats.getSecondStage().getHeight();
						InfoAndStats.secondStage.setRocketDetailAccessibility(InfoAndStats.MM_4.getRocketDetailAccessibility());
						InfoAndStats.secondStage.setRocketDetailAmount(InfoAndStats.MM_4.getRocketDetailAmount());
						InfoAndStats.secondStage.setRocketDetailImpulse(InfoAndStats.MM_4.getRocketDetailImpulse());
						InfoAndStats.secondStage.setRocketDetailName(InfoAndStats.MM_4.getRocketDetailName(), InfoAndStats.MM_4.getRocketDetailName());
						InfoAndStats.secondStage.setRocketDetailThrust(InfoAndStats.MM_4.getRocketDetailThrust());
						InfoAndStats.secondStage.setRocketDetailTime(InfoAndStats.MM_4.getRocketDetailTime());
						isBoundDraw2 = true;
						isMM_4Selected = true;
						boundGold2.setBounds(MM_4X - 0.075F*MM_4Width, MM_4Y - 0.075F*MM_4Height, 1.15F*MM_4Width, 1.15F*MM_4Height);
						boundGold2XPREV = boundGold2.getX();
						boundGold2YPREV = boundGold2.getY();
						boundGold2WidthPREV = boundGold2.getWidth();
						boundGold2HeightPREV = boundGold2.getHeight();
					}else if(!InfoAndStats.MM_4.getRocketDetailAccessibility()){
						if(isMM_4PriceSelected){
							isMM_4PriceSelected = false;
							isMF_043PriceSelected = false;
						}else{
							isMM_4PriceSelected = true;
							isMF_043PriceSelected = false;
						}
					}
				}
				if(isMM_4Selected && controller.isClicked(buy1X, buy1Y, buy1Width, buy1Height) && InfoAndStats.money >= InfoAndStats.MM_4.getRocketDetailPrice()){
					InfoAndStats.money -= InfoAndStats.MM_4.getRocketDetailPrice();
					InfoAndStats.MM_4.setRocketDetailAmount(InfoAndStats.MM_4.getRocketDetailAmount() + 1);
					InfoAndStats.secondStage.setRocketDetailAmount(InfoAndStats.MM_4.getRocketDetailAmount());
				}
			}
		}
		//Слушатель нажатия на деталь LM_087//
		if(isRocket1WindowDraw){
			if(isRocket3DetailActive){
				if(controller.isClicked(LM_087X, LM_087Y, LM_087Width, LM_087Height)){
					if(!isBoundDraw3 && InfoAndStats.LM_087.getRocketDetailAccessibility()){
						InfoAndStats.selectedThird = "LM_087";
						boundGold3X = InfoAndStats.getThirdStage().getX() - 0.075F*InfoAndStats.getThirdStage().getWidth();
						boundGold3Y = InfoAndStats.getThirdStage().getY() - 0.075F*InfoAndStats.getThirdStage().getHeight();
						boundGold3Width = 1.15F*InfoAndStats.getThirdStage().getWidth();
						boundGold3Height = 1.15F*InfoAndStats.getThirdStage().getHeight();
						InfoAndStats.thirdStage.setRocketDetailAccessibility(InfoAndStats.LM_087.getRocketDetailAccessibility());
						InfoAndStats.thirdStage.setRocketDetailAmount(InfoAndStats.LM_087.getRocketDetailAmount());
						InfoAndStats.thirdStage.setRocketDetailImpulse(InfoAndStats.LM_087.getRocketDetailImpulse());
						InfoAndStats.thirdStage.setRocketDetailName(InfoAndStats.LM_087.getRocketDetailName(), InfoAndStats.LM_087.getRocketDetailName());
						InfoAndStats.thirdStage.setRocketDetailThrust(InfoAndStats.LM_087.getRocketDetailThrust());
						InfoAndStats.thirdStage.setRocketDetailTime(InfoAndStats.LM_087.getRocketDetailTime());
						isBoundDraw3 = true;
						isLM_087Selected = true;
						boundGold3.setBounds(LM_087X - 0.075F*LM_087Width, LM_087Y - 0.075F*LM_087Height, 1.15F*LM_087Width, 1.15F*LM_087Height);
						boundGold3XPREV = boundGold3.getX();
						boundGold3YPREV = boundGold3.getY();
						boundGold3WidthPREV = boundGold3.getWidth();
						boundGold3HeightPREV = boundGold3.getHeight();
					}
					else if(isBoundDraw3 && isLM_087Selected && InfoAndStats.LM_087.getRocketDetailAccessibility()){
						isBoundDraw3 = false;
						InfoAndStats.selectedThird = "null";
						InfoAndStats.thirdStage.setRocketDetailAccessibility(false);
						InfoAndStats.thirdStage.setRocketDetailAmount(0);
						InfoAndStats.thirdStage.setRocketDetailImpulse(0.0F);
						InfoAndStats.thirdStage.setRocketDetailName("","");
						InfoAndStats.thirdStage.setRocketDetailThrust(0.0F);
						InfoAndStats.thirdStage.setRocketDetailTime(0.0F);
						isLM_087Selected = false;
						boundGold3.setBounds(LM_087X - 0.075F*LM_087Width, LM_087Y - 0.075F*LM_087Height, 1.15F*LM_087Width, 1.15F*LM_087Height);
						boundGold3XPREV = boundGold3.getX();
						boundGold3YPREV = boundGold3.getY();
						boundGold3WidthPREV = boundGold3.getWidth();
						boundGold3HeightPREV = boundGold3.getHeight();
					}else if(isBoundDraw3 && !isLM_087Selected && InfoAndStats.LM_087.getRocketDetailAccessibility()){
						isLT_116Selected = false;
						isLF_15Selected = false;
						InfoAndStats.selectedThird = "LM_087";
						boundGold3X = InfoAndStats.getThirdStage().getX() - 0.075F*InfoAndStats.getThirdStage().getWidth();
						boundGold3Y = InfoAndStats.getThirdStage().getY() - 0.075F*InfoAndStats.getThirdStage().getHeight();
						boundGold3Width = 1.15F*InfoAndStats.getThirdStage().getWidth();
						boundGold3Height = 1.15F*InfoAndStats.getThirdStage().getHeight();
						InfoAndStats.thirdStage.setRocketDetailAccessibility(InfoAndStats.LM_087.getRocketDetailAccessibility());
						InfoAndStats.thirdStage.setRocketDetailAmount(InfoAndStats.LM_087.getRocketDetailAmount());
						InfoAndStats.thirdStage.setRocketDetailImpulse(InfoAndStats.LM_087.getRocketDetailImpulse());
						InfoAndStats.thirdStage.setRocketDetailName(InfoAndStats.LM_087.getRocketDetailName(), InfoAndStats.LM_087.getRocketDetailName());
						InfoAndStats.thirdStage.setRocketDetailThrust(InfoAndStats.LM_087.getRocketDetailThrust());
						InfoAndStats.thirdStage.setRocketDetailTime(InfoAndStats.LM_087.getRocketDetailTime());
						isBoundDraw3 = true;
						isLM_087Selected = true;
						boundGold3.setBounds(LM_087X - 0.075F*LM_087Width, LM_087Y - 0.075F*LM_087Height, 1.15F*LM_087Width, 1.15F*LM_087Height);
						boundGold3XPREV = boundGold3.getX();
						boundGold3YPREV = boundGold3.getY();
						boundGold3WidthPREV = boundGold3.getWidth();
						boundGold3HeightPREV = boundGold3.getHeight();
					}else if(!InfoAndStats.LM_087.getRocketDetailAccessibility()){
						if(isLM_087PriceSelected){
							isLM_087PriceSelected = false;
							isLF_15PriceSelected = false;
						}else{
							isLM_087PriceSelected = true;
							isLF_15PriceSelected = false;
						}
					}
				}
				if(isLM_087Selected && controller.isClicked(buy1X, buy1Y, buy1Width, buy1Height) && InfoAndStats.money >= InfoAndStats.LM_087.getRocketDetailPrice()){
					InfoAndStats.money -= InfoAndStats.LM_087.getRocketDetailPrice();
					InfoAndStats.LM_087.setRocketDetailAmount(InfoAndStats.LM_087.getRocketDetailAmount() + 1);
					InfoAndStats.thirdStage.setRocketDetailAmount(InfoAndStats.LM_087.getRocketDetailAmount());
				}
			}
		}
		//Слушатель нажатия на деталь UF_02//
		if(isRocket1WindowDraw){
			if(isRocket1DetailActive){
				if(controller.isClicked(UF_02X, UF_02Y, UF_02Width, UF_02Height)){
					if(!isBoundDraw1 && InfoAndStats.UF_02.getRocketDetailAccessibility()){
						InfoAndStats.selectedFirst = "UF_02";
						boundGold1X = InfoAndStats.getFirstStage().getX() - 0.075F*InfoAndStats.getFirstStage().getWidth();
						boundGold1Y = InfoAndStats.getFirstStage().getY() - 0.075F*InfoAndStats.getFirstStage().getHeight();
						boundGold1Width = 1.15F*InfoAndStats.getFirstStage().getWidth();
						boundGold1Height = 1.15F*InfoAndStats.getFirstStage().getHeight();
						InfoAndStats.firstStage.setRocketDetailAccessibility(InfoAndStats.UF_02.getRocketDetailAccessibility());
						InfoAndStats.firstStage.setRocketDetailAmount(InfoAndStats.UF_02.getRocketDetailAmount());
						InfoAndStats.firstStage.setRocketDetailImpulse(InfoAndStats.UF_02.getRocketDetailImpulse());
						InfoAndStats.firstStage.setRocketDetailName(InfoAndStats.UF_02.getRocketDetailName(), InfoAndStats.UF_02.getRocketDetailName());
						InfoAndStats.firstStage.setRocketDetailThrust(InfoAndStats.UF_02.getRocketDetailThrust());
						InfoAndStats.firstStage.setRocketDetailTime(InfoAndStats.UF_02.getRocketDetailTime());
						isBoundDraw1 = true;
						isUF_02Selected = true;
						boundGold1.setBounds(UF_02X - 0.075F*UF_02Width, UF_02Y - 0.075F*UF_02Height, 1.15F*UF_02Width, 1.15F*UF_02Height);
						boundGold1XPREV = boundGold1.getX();
						boundGold1YPREV = boundGold1.getY();
						boundGold1WidthPREV = boundGold1.getWidth();
						boundGold1HeightPREV = boundGold1.getHeight();
					}else if(isBoundDraw1 && isUF_02Selected && InfoAndStats.UF_02.getRocketDetailAccessibility()){
						isBoundDraw1 = false;
						InfoAndStats.selectedFirst = "null";
						InfoAndStats.firstStage.setRocketDetailAccessibility(false);
						InfoAndStats.firstStage.setRocketDetailAmount(0);
						InfoAndStats.firstStage.setRocketDetailImpulse(0.0F);
						InfoAndStats.firstStage.setRocketDetailName("","");
						InfoAndStats.firstStage.setRocketDetailThrust(0.0F);
						InfoAndStats.firstStage.setRocketDetailTime(0.0F);
						isUF_02Selected = false;
					}else if(isBoundDraw1 && !isUF_02Selected && InfoAndStats.UF_02.getRocketDetailAccessibility()){
						isUT_1202Selected = false;
						isUM_1034Selected = false;
						InfoAndStats.selectedFirst = "UF_02";
						boundGold1X = InfoAndStats.getFirstStage().getX() - 0.075F*InfoAndStats.getFirstStage().getWidth();
						boundGold1Y = InfoAndStats.getFirstStage().getY() - 0.075F*InfoAndStats.getFirstStage().getHeight();
						boundGold1Width = 1.15F*InfoAndStats.getFirstStage().getWidth();
						boundGold1Height = 1.15F*InfoAndStats.getFirstStage().getHeight();
						InfoAndStats.firstStage.setRocketDetailAccessibility(InfoAndStats.UF_02.getRocketDetailAccessibility());
						InfoAndStats.firstStage.setRocketDetailAmount(InfoAndStats.UF_02.getRocketDetailAmount());
						InfoAndStats.firstStage.setRocketDetailImpulse(InfoAndStats.UF_02.getRocketDetailImpulse());
						InfoAndStats.firstStage.setRocketDetailName(InfoAndStats.UF_02.getRocketDetailName(), InfoAndStats.UF_02.getRocketDetailName());
						InfoAndStats.firstStage.setRocketDetailThrust(InfoAndStats.UF_02.getRocketDetailThrust());
						InfoAndStats.firstStage.setRocketDetailTime(InfoAndStats.UF_02.getRocketDetailTime());
						isBoundDraw1 = true;
						isUF_02Selected = true;
						boundGold1.setBounds(UF_02X - 0.075F*UF_02Width, UF_02Y - 0.075F*UF_02Height, 1.15F*UF_02Width, 1.15F*UF_02Height);
						boundGold1XPREV = boundGold1.getX();
						boundGold1YPREV = boundGold1.getY();
						boundGold1WidthPREV = boundGold1.getWidth();
						boundGold1HeightPREV = boundGold1.getHeight();
					}else if(!InfoAndStats.UF_02.getRocketDetailAccessibility()){
						if(isUF_02PriceSelected){
							isUF_02PriceSelected = false;
							isUM_1034PriceSelected = false;
						}else{
							isUF_02PriceSelected = true;
							isUM_1034PriceSelected = false;
						}
					}
				}
				if(isUF_02Selected && controller.isClicked(buy1X, buy1Y, buy1Width, buy1Height) && InfoAndStats.money >= InfoAndStats.UF_02.getRocketDetailPrice()){
					InfoAndStats.money -= InfoAndStats.UF_02.getRocketDetailPrice();
					InfoAndStats.UF_02.setRocketDetailAmount(InfoAndStats.UF_02.getRocketDetailAmount() + 1);
					InfoAndStats.firstStage.setRocketDetailAmount(InfoAndStats.UF_02.getRocketDetailAmount());
				}
			}
		}
		//Слушатель нажатия на деталь MF_043//
		if(isRocket1WindowDraw){
			if(isRocket2DetailActive){
				if(controller.isClicked(MF_043X, MF_043Y, MF_043Width, MF_043Height)){
					if(!isBoundDraw2 && InfoAndStats.MF_043.getRocketDetailAccessibility()){
						InfoAndStats.selectedSecond = "MF_043";
						boundGold2X = InfoAndStats.getSecondStage().getX() - 0.075F*InfoAndStats.getSecondStage().getWidth();
						boundGold2Y = InfoAndStats.getSecondStage().getY() - 0.075F*InfoAndStats.getSecondStage().getHeight();
						boundGold2Width = 1.15F*InfoAndStats.getSecondStage().getWidth();
						boundGold2Height = 1.15F*InfoAndStats.getSecondStage().getHeight();
						InfoAndStats.secondStage.setRocketDetailAccessibility(InfoAndStats.MF_043.getRocketDetailAccessibility());
						InfoAndStats.secondStage.setRocketDetailAmount(InfoAndStats.MF_043.getRocketDetailAmount());
						InfoAndStats.secondStage.setRocketDetailImpulse(InfoAndStats.MF_043.getRocketDetailImpulse());
						InfoAndStats.secondStage.setRocketDetailName(InfoAndStats.MF_043.getRocketDetailName(), InfoAndStats.MF_043.getRocketDetailName());
						InfoAndStats.secondStage.setRocketDetailThrust(InfoAndStats.MF_043.getRocketDetailThrust());
						InfoAndStats.secondStage.setRocketDetailTime(InfoAndStats.MF_043.getRocketDetailTime());
						isBoundDraw2 = true;
						isMF_043Selected = true;
						boundGold2.setBounds(MF_043X - 0.075F*MF_043Width, MF_043Y - 0.075F*MF_043Height, 1.15F*MF_043Width, 1.15F*MF_043Height);
						boundGold2XPREV = boundGold2.getX();
						boundGold2YPREV = boundGold2.getY();
						boundGold2WidthPREV = boundGold2.getWidth();
						boundGold2HeightPREV = boundGold2.getHeight();
					}
					else if(isBoundDraw2 && isMF_043Selected && InfoAndStats.MF_043.getRocketDetailAccessibility()){
						isBoundDraw2 = false;
						InfoAndStats.selectedSecond = "null";
						InfoAndStats.secondStage.setRocketDetailAccessibility(false);
						InfoAndStats.secondStage.setRocketDetailAmount(0);
						InfoAndStats.secondStage.setRocketDetailImpulse(0.0F);
						InfoAndStats.secondStage.setRocketDetailName("","");
						InfoAndStats.secondStage.setRocketDetailThrust(0.0F);
						InfoAndStats.secondStage.setRocketDetailTime(0.0F);
						isMF_043Selected = false;
						boundGold2.setBounds(MF_043X - 0.075F*MF_043Width, MF_043Y - 0.075F*MF_043Height, 1.15F*MF_043Width, 1.15F*MF_043Height);
						boundGold2XPREV = boundGold2.getX();
						boundGold2YPREV = boundGold2.getY();
						boundGold2WidthPREV = boundGold2.getWidth();
						boundGold2HeightPREV = boundGold2.getHeight();
					}else if(isBoundDraw2 && !isMF_043Selected && InfoAndStats.MF_043.getRocketDetailAccessibility()){
						isMT_0112Selected = false;
						isMM_4Selected = false;
						InfoAndStats.selectedSecond = "MF_043";
						boundGold2X = InfoAndStats.getSecondStage().getX() - 0.075F*InfoAndStats.getSecondStage().getWidth();
						boundGold2Y = InfoAndStats.getSecondStage().getY() - 0.075F*InfoAndStats.getSecondStage().getHeight();
						boundGold2Width = 1.15F*InfoAndStats.getSecondStage().getWidth();
						boundGold2Height = 1.15F*InfoAndStats.getSecondStage().getHeight();
						InfoAndStats.secondStage.setRocketDetailAccessibility(InfoAndStats.MF_043.getRocketDetailAccessibility());
						InfoAndStats.secondStage.setRocketDetailAmount(InfoAndStats.MF_043.getRocketDetailAmount());
						InfoAndStats.secondStage.setRocketDetailImpulse(InfoAndStats.MF_043.getRocketDetailImpulse());
						InfoAndStats.secondStage.setRocketDetailName(InfoAndStats.MF_043.getRocketDetailName(), InfoAndStats.MF_043.getRocketDetailName());
						InfoAndStats.secondStage.setRocketDetailThrust(InfoAndStats.MF_043.getRocketDetailThrust());
						InfoAndStats.secondStage.setRocketDetailTime(InfoAndStats.MF_043.getRocketDetailTime());
						isBoundDraw2 = true;
						isMF_043Selected = true;
						boundGold2.setBounds(MF_043X - 0.075F*MF_043Width, MF_043Y - 0.075F*MF_043Height, 1.15F*MF_043Width, 1.15F*MF_043Height);
						boundGold2XPREV = boundGold2.getX();
						boundGold2YPREV = boundGold2.getY();
						boundGold2WidthPREV = boundGold2.getWidth();
						boundGold2HeightPREV = boundGold2.getHeight();
					}else if(!InfoAndStats.MF_043.getRocketDetailAccessibility()){
						if(isMF_043PriceSelected){
							isMF_043PriceSelected = false;
							isMM_4PriceSelected = false;
						}else{
							isMF_043PriceSelected = true;
							isMM_4PriceSelected = false;
						}
					}
				}
				if(isMF_043Selected && controller.isClicked(buy1X, buy1Y, buy1Width, buy1Height) && InfoAndStats.money >= InfoAndStats.MF_043.getRocketDetailPrice()){
					InfoAndStats.money -= InfoAndStats.MF_043.getRocketDetailPrice();
					InfoAndStats.MF_043.setRocketDetailAmount(InfoAndStats.MF_043.getRocketDetailAmount() + 1);
					InfoAndStats.secondStage.setRocketDetailAmount(InfoAndStats.MF_043.getRocketDetailAmount());
				}
			}
		}
		//Слушатель нажатия на деталь LF_15//
		if(isRocket1WindowDraw){
			if(isRocket3DetailActive){
				if(controller.isClicked(LF_15X, LF_15Y, LF_15Width, LF_15Height)){
					if(!isBoundDraw3 && InfoAndStats.LF_15.getRocketDetailAccessibility()){
						InfoAndStats.selectedThird = "LF_15";
						boundGold3X = InfoAndStats.getThirdStage().getX() - 0.075F*InfoAndStats.getThirdStage().getWidth();
						boundGold3Y = InfoAndStats.getThirdStage().getY() - 0.075F*InfoAndStats.getThirdStage().getHeight();
						boundGold3Width = 1.15F*InfoAndStats.getThirdStage().getWidth();
						boundGold3Height = 1.15F*InfoAndStats.getThirdStage().getHeight();
						InfoAndStats.thirdStage.setRocketDetailAccessibility(InfoAndStats.LF_15.getRocketDetailAccessibility());
						InfoAndStats.thirdStage.setRocketDetailAmount(InfoAndStats.LF_15.getRocketDetailAmount());
						InfoAndStats.thirdStage.setRocketDetailImpulse(InfoAndStats.LF_15.getRocketDetailImpulse());
						InfoAndStats.thirdStage.setRocketDetailName(InfoAndStats.LF_15.getRocketDetailName(), InfoAndStats.LF_15.getRocketDetailName());
						InfoAndStats.thirdStage.setRocketDetailThrust(InfoAndStats.LF_15.getRocketDetailThrust());
						InfoAndStats.thirdStage.setRocketDetailTime(InfoAndStats.LF_15.getRocketDetailTime());
						isBoundDraw3 = true;
						isLF_15Selected = true;
						boundGold3.setBounds(LF_15X - 0.075F*LF_15Width, LF_15Y - 0.075F*LF_15Height, 1.15F*LF_15Width, 1.15F*LF_15Height);
						boundGold3XPREV = boundGold3.getX();
						boundGold3YPREV = boundGold3.getY();
						boundGold3WidthPREV = boundGold3.getWidth();
						boundGold3HeightPREV = boundGold3.getHeight();
					}
					else if(isBoundDraw3 && isLF_15Selected && InfoAndStats.LF_15.getRocketDetailAccessibility()){
						isBoundDraw3 = false;
						InfoAndStats.selectedThird = "null";
						InfoAndStats.thirdStage.setRocketDetailAccessibility(false);
						InfoAndStats.thirdStage.setRocketDetailAmount(0);
						InfoAndStats.thirdStage.setRocketDetailImpulse(0.0F);
						InfoAndStats.thirdStage.setRocketDetailName("","");
						InfoAndStats.thirdStage.setRocketDetailThrust(0.0F);
						InfoAndStats.thirdStage.setRocketDetailTime(0.0F);
						isLF_15Selected = false;
						boundGold3.setBounds(LF_15X - 0.075F*LF_15Width, LF_15Y - 0.075F*LF_15Height, 1.15F*LF_15Width, 1.15F*LF_15Height);
						boundGold3XPREV = boundGold3.getX();
						boundGold3YPREV = boundGold3.getY();
						boundGold3WidthPREV = boundGold3.getWidth();
						boundGold3HeightPREV = boundGold3.getHeight();
					}else if(isBoundDraw3 && !isLF_15Selected && InfoAndStats.LF_15.getRocketDetailAccessibility()){
						isLT_116Selected = false;
						isLM_087Selected = false;
						InfoAndStats.selectedThird = "LF_15";
						boundGold3X = InfoAndStats.getThirdStage().getX() - 0.075F*InfoAndStats.getThirdStage().getWidth();
						boundGold3Y = InfoAndStats.getThirdStage().getY() - 0.075F*InfoAndStats.getThirdStage().getHeight();
						boundGold3Width = 1.15F*InfoAndStats.getThirdStage().getWidth();
						boundGold3Height = 1.15F*InfoAndStats.getThirdStage().getHeight();
						InfoAndStats.thirdStage.setRocketDetailAccessibility(InfoAndStats.LF_15.getRocketDetailAccessibility());
						InfoAndStats.thirdStage.setRocketDetailAmount(InfoAndStats.LF_15.getRocketDetailAmount());
						InfoAndStats.thirdStage.setRocketDetailImpulse(InfoAndStats.LF_15.getRocketDetailImpulse());
						InfoAndStats.thirdStage.setRocketDetailName(InfoAndStats.LF_15.getRocketDetailName(), InfoAndStats.LF_15.getRocketDetailName());
						InfoAndStats.thirdStage.setRocketDetailThrust(InfoAndStats.LF_15.getRocketDetailThrust());
						InfoAndStats.thirdStage.setRocketDetailTime(InfoAndStats.LF_15.getRocketDetailTime());
						isBoundDraw3 = true;
						isLF_15Selected = true;
						boundGold3.setBounds(LF_15X - 0.075F*LF_15Width, LF_15Y - 0.075F*LF_15Height, 1.15F*LF_15Width, 1.15F*LF_15Height);
						boundGold3XPREV = boundGold3.getX();
						boundGold3YPREV = boundGold3.getY();
						boundGold3WidthPREV = boundGold3.getWidth();
						boundGold3HeightPREV = boundGold3.getHeight();
					}else if(!InfoAndStats.LF_15.getRocketDetailAccessibility()){
						if(isLF_15PriceSelected){
							isLF_15PriceSelected = false;
							isLM_087PriceSelected = false;
						}else{
							isLF_15PriceSelected = true;
							isLM_087PriceSelected = false;
						}
					}
				}
				if(isLF_15Selected && controller.isClicked(buy1X, buy1Y, buy1Width, buy1Height) && InfoAndStats.money >= InfoAndStats.LF_15.getRocketDetailPrice()){
					InfoAndStats.money -= InfoAndStats.LF_15.getRocketDetailPrice();
					InfoAndStats.LF_15.setRocketDetailAmount(InfoAndStats.LF_15.getRocketDetailAmount() + 1);
					InfoAndStats.thirdStage.setRocketDetailAmount(InfoAndStats.LF_15.getRocketDetailAmount());
				}
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
		rocket1Inactive.dispose();
		rocket1Active.dispose();
		rocket2Inactive.dispose();
		rocket2Active.dispose();
		rocket3Inactive.dispose();
		rocket3Active.dispose();
		rocket1Window.dispose();
		lockTexture.dispose();
		UT_1202Texture.dispose();
		MT_0112Texture.dispose();
		LT_116Texture.dispose();
		UM_1034Texture.dispose();
		MM_4Texture.dispose();
		LM_087Texture.dispose();
		UF_02Texture.dispose();
		MF_043Texture.dispose();
		LF_15Texture.dispose();
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
