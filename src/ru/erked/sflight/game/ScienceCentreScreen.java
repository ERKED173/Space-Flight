package ru.erked.sflight.game;

import java.util.Random;

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

public class ScienceCentreScreen implements Screen{
	
	final String FONT_CHARS_RU = "абвгдежзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
	private static final float width = Gdx.graphics.getWidth();
	private static final float height = Gdx.graphics.getHeight();
	
	private Random rand1 = new Random();
	private Game game;
	private SpriteBatch batch;
	private SFlightInputController controller;
	
	//Фон
	private Texture backgroundTexture; //Текстура фона
	public static Sprite backgroundSprite; //Спрайт фона
	private float backgroundX;
	private float backgroundY;
	public static final float backgroundTentionIndex = (float)width/600.0F;
	
	//Копка "Back"
	private Sprite backButtonInactiveSprite;
	private Sprite backButtonActiveSprite;
	private float backButtonX;
	private float backButtonY;
	private float backButtonWidth;
	private float backButtonHeight;
	public static float backButtonTentionIndex; //Соотношение сторон кнопки
	
	//Апгрейд деталей
	private Texture upgradeInactive;
	private Texture upgradeActive;
	private Sprite upgradeSpriteInactive;
	private Sprite upgradeSpriteActive;
	private float upgrade1X;
	private float upgrade1Y;
	private float upgrade1Width;
	private float upgrade1Height;
	private float upgrade2X;
	private float upgrade2Y;
	private float upgrade2Width;
	private float upgrade2Height;
	private float upgradeAspectRatio;
	
	//Диалоговое окно апгрейдов
	private Sprite upgradeWindowSprite;
	private float upgradeWindowX;
	private float upgradeWindowY;
	private float upgradeWindowWidth;
	private float upgradeWindowHeight;
	private static boolean isUpgradeWindowDraw;
	//Выход из окна апгрейда
	private Sprite backUpgradeWindowSpriteInactive;
	private Sprite backUpgradeWindowSpriteActive;
	private float backUpgradeWindow1X;
	private float backUpgradeWindow1Y;
	private float backUpgradeWindow1Width;
	private float backUpgradeWindow1Height;
	private float backUpgradeWindow2X;
	private float backUpgradeWindow2Y;
	private float backUpgradeWindow2Width;
	private float backUpgradeWindow2Height;
	
	//Заглушка
	private Sprite backZSprite;
	
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
	//MM_4
	private Texture MM_4Texture;
	private Sprite MM_4Sprite;
	private float MM_4Width;
	private float MM_4Height;
	private float MM_4X;
	private float MM_4Y;
	public static boolean isMM_4Selected;
	//LM_087
	private Texture LM_087Texture;
	private Sprite LM_087Sprite;
	private float LM_087Width;
	private float LM_087Height;
	private float LM_087X;
	private float LM_087Y;
	public static boolean isLM_087Selected;
	//UF_02
	private Texture UF_02Texture;
	private Sprite UF_02Sprite;
	private float UF_02Width;
	private float UF_02Height;
	private float UF_02X;
	private float UF_02Y;
	public static boolean isUF_02Selected;
	//MF_043
	private Texture MF_043Texture;
	private Sprite MF_043Sprite;
	private float MF_043Width;
	private float MF_043Height;
	private float MF_043X;
	private float MF_043Y;
	public static boolean isMF_043Selected;
	//LF_15
	private Texture LF_15Texture;
	private Sprite LF_15Sprite;
	private float LF_15Width;
	private float LF_15Height;
	private float LF_15X;
	private float LF_15Y;
	public static boolean isLF_15Selected;
	
	//Кнопка апгрейда
	private Sprite upgradeButtonSpriteInactive;
	private Sprite upgradeButtonSpriteActive;
	private float upgradeButton1X;
	private float upgradeButton1Y;
	private float upgradeButton1Width;
	private float upgradeButton1Height;
	private float upgradeButton2X;
	private float upgradeButton2Y;
	private float upgradeButton2Width;
	private float upgradeButton2Height;
	
	//Кнопка исследования
	private Sprite researchButtonSpriteInactive;
	private Sprite researchButtonSpriteActive;
	private float researchButton1X;
	private float researchButton1Y;
	private float researchButton1Width;
	private float researchButton1Height;
	private float researchButton2X;
	private float researchButton2Y;
	private float researchButton2Width;
	private float researchButton2Height;
	
	//Стрелочки
	private Sprite arrowSprite1;
	private Sprite arrowSprite2;
	private Sprite arrowSprite3;
	private Sprite arrowSprite4;

	public static int firstNUM1;
	public static int firstNUM2;
	public static int secondNUM1;
	public static int secondNUM2;
	public static int thirdNUM1;
	public static int thirdNUM2;
	public static int fourthNUM1;
	public static int fourthNUM2;
	
	//Текст
	private static BitmapFont text;
	private static BitmapFont textFail;
	private static BitmapFont textSuc;
	
	public ScienceCentreScreen(Game game){
		this.game = game;
	}
	
	@Override
	public void show() {
		batch = new SpriteBatch();
		controller = new SFlightInputController();

		MainMenu.music.play();
		
		if(InfoAndStats.prevDay != InfoAndStats.date && !InfoAndStats.hasTask){
			InfoAndStats.prevDay = InfoAndStats.date;
			//***\\ Проценты для апгрейда
			firstNUM1 = rand1.nextInt(100) + 1;
			firstNUM2 = 100 - firstNUM1;
			secondNUM1 = rand1.nextInt(100) + 1;
			secondNUM2 = 100 - secondNUM1;
			thirdNUM1 = rand1.nextInt(100) + 1;
			thirdNUM2 = 100 - thirdNUM1;
			fourthNUM1 = rand1.nextInt(100) + 1;
			fourthNUM2 = 100 - fourthNUM1;
			//***\\
		}
		
		//Фон\\
		backgroundTexture = new Texture("bckgrnd/scienceCentreInside.png");
		backgroundSprite = new Sprite(backgroundTexture);
		backgroundX = 0.0F;
		backgroundY = (-1)*(337*backgroundTentionIndex)/2 + height/2;
		backgroundSprite.setBounds(backgroundX, backgroundY, width, backgroundTentionIndex*337.0F);
		
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
		
		upgradeWindowInit();
		upgradeInit();
		upgradeButtonInit();
		researchButtonInit();
		
		UT_1202Init();
		MT_0112Init();
		LT_116Init();
		UM_1034Init();
		MM_04Init();
		LM_087Init();
		UF_02Init();
		MF_043Init();
		LF_15Init();
		
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
		text.getData().setScale((float)(0.0006F*width));
		textFail.getData().setScale((float)(0.0006F*width));
		textSuc.getData().setScale((float)(0.0006F*width));
		
		//Собсна улучшение
		if(InfoAndStats.hasTask && ((int)(InfoAndStats.date - InfoAndStats.days) >= (int)(InfoAndStats.upgradeDetail.getRocketDetailImpulse() + InfoAndStats.upgradeDetail.getRocketDetailThrust() + InfoAndStats.upgradeDetail.getRocketDetailTime()/5.0F + InfoAndStats.upgradeDetail.getRocketDetailPrice()/5.0F))){
//******************************************************************\\
			if(InfoAndStats.isUT_1202Upgrading){
				if(firstNUM1 >= firstNUM2){
					if(rand1.nextInt(100) + 1 < firstNUM1)
						InfoAndStats.UT_1202.setRocketDetailThrust(InfoAndStats.upgradeDetail.getRocketDetailThrust() + InfoAndStats.upgradeDetail.getRocketDetailThrust()*(Math.min(firstNUM1, firstNUM2)/100.0F));
					else
						InfoAndStats.UT_1202.setRocketDetailThrust(InfoAndStats.upgradeDetail.getRocketDetailThrust() - InfoAndStats.upgradeDetail.getRocketDetailThrust()*(Math.min(firstNUM1, firstNUM2)/100.0F));
				}else{
					if(rand1.nextInt(100) + 1 < firstNUM1)
						InfoAndStats.UT_1202.setRocketDetailThrust(InfoAndStats.upgradeDetail.getRocketDetailThrust() - InfoAndStats.upgradeDetail.getRocketDetailThrust()*(Math.min(firstNUM1, firstNUM2)/100.0F));
					else
						InfoAndStats.UT_1202.setRocketDetailThrust(InfoAndStats.upgradeDetail.getRocketDetailThrust() + InfoAndStats.upgradeDetail.getRocketDetailThrust()*(Math.min(firstNUM1, firstNUM2)/100.0F));
				}
				if(secondNUM1 >= secondNUM2){
					if(rand1.nextInt(100) + 1 < secondNUM1)
						InfoAndStats.UT_1202.setRocketDetailImpulse(InfoAndStats.upgradeDetail.getRocketDetailImpulse() + InfoAndStats.upgradeDetail.getRocketDetailImpulse()*(Math.min(secondNUM1, secondNUM2)/100.0F));
					else
						InfoAndStats.UT_1202.setRocketDetailImpulse(InfoAndStats.upgradeDetail.getRocketDetailImpulse() - InfoAndStats.upgradeDetail.getRocketDetailImpulse()*(Math.min(secondNUM1, secondNUM2)/100.0F));
				}else{
					if(rand1.nextInt(100) + 1 < secondNUM1)
						InfoAndStats.UT_1202.setRocketDetailImpulse(InfoAndStats.upgradeDetail.getRocketDetailImpulse() - InfoAndStats.upgradeDetail.getRocketDetailImpulse()*(Math.min(secondNUM1, secondNUM2)/100.0F));
					else
						InfoAndStats.UT_1202.setRocketDetailImpulse(InfoAndStats.upgradeDetail.getRocketDetailImpulse() + InfoAndStats.upgradeDetail.getRocketDetailImpulse()*(Math.min(secondNUM1, secondNUM2)/100.0F));
				}
				if(thirdNUM1 >= thirdNUM2){
					if(rand1.nextInt(100) + 1 < thirdNUM1)
						InfoAndStats.UT_1202.setRocketDetailTime(InfoAndStats.upgradeDetail.getRocketDetailTime() + InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(thirdNUM1, thirdNUM2)/100.0F));
					else
						InfoAndStats.UT_1202.setRocketDetailTime(InfoAndStats.upgradeDetail.getRocketDetailTime() - InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(thirdNUM1, thirdNUM2)/100.0F));
				}else{
					if(rand1.nextInt(100) + 1 < thirdNUM1)
						InfoAndStats.UT_1202.setRocketDetailTime(InfoAndStats.upgradeDetail.getRocketDetailTime() - InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(thirdNUM1, thirdNUM2)/100.0F));
					else
						InfoAndStats.UT_1202.setRocketDetailTime(InfoAndStats.upgradeDetail.getRocketDetailTime() + InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(thirdNUM1, thirdNUM2)/100.0F));
				}
				if(fourthNUM1 >= fourthNUM2){
					if(rand1.nextInt(100) + 1 < fourthNUM1)
						InfoAndStats.UT_1202.setRocketDetailPrice(InfoAndStats.upgradeDetail.getRocketDetailPrice() + (int)(InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(fourthNUM1, fourthNUM2)/100.0F)));
					else
						InfoAndStats.UT_1202.setRocketDetailPrice(InfoAndStats.upgradeDetail.getRocketDetailPrice() - (int)(InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(fourthNUM1, fourthNUM2)/100.0F)));
				}else{
					if(rand1.nextInt(100) + 1 < fourthNUM1)
						InfoAndStats.UT_1202.setRocketDetailPrice(InfoAndStats.upgradeDetail.getRocketDetailPrice() - (int)(InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(fourthNUM1, fourthNUM2)/100.0F)));
					else
						InfoAndStats.UT_1202.setRocketDetailPrice(InfoAndStats.upgradeDetail.getRocketDetailPrice() + (int)(InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(fourthNUM1, fourthNUM2)/100.0F)));
				}
				InfoAndStats.isUT_1202Upgrading = false;
				InfoAndStats.UT_1202.setRocketDetailLevel(InfoAndStats.UT_1202.getRocketDetailLevel() + 1);
//******************************************************************\\
			}else if(InfoAndStats.isUM_1034Upgrading){
				if(InfoAndStats.UM_1034.getRocketDetailAccessibility()){
					if(firstNUM1 >= firstNUM2){
						if(rand1.nextInt(100) + 1 < firstNUM1)
							InfoAndStats.UM_1034.setRocketDetailThrust(InfoAndStats.upgradeDetail.getRocketDetailThrust() + InfoAndStats.upgradeDetail.getRocketDetailThrust()*(Math.min(firstNUM1, firstNUM2)/100.0F));
						else
							InfoAndStats.UM_1034.setRocketDetailThrust(InfoAndStats.upgradeDetail.getRocketDetailThrust() - InfoAndStats.upgradeDetail.getRocketDetailThrust()*(Math.min(firstNUM1, firstNUM2)/100.0F));
					}else{
						if(rand1.nextInt(100) + 1 < firstNUM1)
							InfoAndStats.UM_1034.setRocketDetailThrust(InfoAndStats.upgradeDetail.getRocketDetailThrust() - InfoAndStats.upgradeDetail.getRocketDetailThrust()*(Math.min(firstNUM1, firstNUM2)/100.0F));
						else
							InfoAndStats.UM_1034.setRocketDetailThrust(InfoAndStats.upgradeDetail.getRocketDetailThrust() + InfoAndStats.upgradeDetail.getRocketDetailThrust()*(Math.min(firstNUM1, firstNUM2)/100.0F));
					}
					if(secondNUM1 >= secondNUM2){
						if(rand1.nextInt(100) + 1 < secondNUM1)
							InfoAndStats.UM_1034.setRocketDetailImpulse(InfoAndStats.upgradeDetail.getRocketDetailImpulse() + InfoAndStats.upgradeDetail.getRocketDetailImpulse()*(Math.min(secondNUM1, secondNUM2)/100.0F));
						else
							InfoAndStats.UM_1034.setRocketDetailImpulse(InfoAndStats.upgradeDetail.getRocketDetailImpulse() - InfoAndStats.upgradeDetail.getRocketDetailImpulse()*(Math.min(secondNUM1, secondNUM2)/100.0F));
					}else{
						if(rand1.nextInt(100) + 1 < secondNUM1)
							InfoAndStats.UM_1034.setRocketDetailImpulse(InfoAndStats.upgradeDetail.getRocketDetailImpulse() - InfoAndStats.upgradeDetail.getRocketDetailImpulse()*(Math.min(secondNUM1, secondNUM2)/100.0F));
						else
							InfoAndStats.UM_1034.setRocketDetailImpulse(InfoAndStats.upgradeDetail.getRocketDetailImpulse() + InfoAndStats.upgradeDetail.getRocketDetailImpulse()*(Math.min(secondNUM1, secondNUM2)/100.0F));
					}
					if(thirdNUM1 >= thirdNUM2){
						if(rand1.nextInt(100) + 1 < thirdNUM1)
							InfoAndStats.UM_1034.setRocketDetailTime(InfoAndStats.upgradeDetail.getRocketDetailTime() + InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(thirdNUM1, thirdNUM2)/100.0F));
						else
							InfoAndStats.UM_1034.setRocketDetailTime(InfoAndStats.upgradeDetail.getRocketDetailTime() - InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(thirdNUM1, thirdNUM2)/100.0F));
					}else{
						if(rand1.nextInt(100) + 1 < thirdNUM1)
							InfoAndStats.UM_1034.setRocketDetailTime(InfoAndStats.upgradeDetail.getRocketDetailTime() - InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(thirdNUM1, thirdNUM2)/100.0F));
						else
							InfoAndStats.UM_1034.setRocketDetailTime(InfoAndStats.upgradeDetail.getRocketDetailTime() + InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(thirdNUM1, thirdNUM2)/100.0F));
					}
					if(fourthNUM1 >= fourthNUM2){
						if(rand1.nextInt(100) + 1 < fourthNUM1)
							InfoAndStats.UM_1034.setRocketDetailPrice(InfoAndStats.upgradeDetail.getRocketDetailPrice() + (int)(InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(fourthNUM1, fourthNUM2)/100.0F)));
						else
							InfoAndStats.UM_1034.setRocketDetailPrice(InfoAndStats.upgradeDetail.getRocketDetailPrice() - (int)(InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(fourthNUM1, fourthNUM2)/100.0F)));
					}else{
						if(rand1.nextInt(100) + 1 < fourthNUM1)
							InfoAndStats.UM_1034.setRocketDetailPrice(InfoAndStats.upgradeDetail.getRocketDetailPrice() - (int)(InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(fourthNUM1, fourthNUM2)/100.0F)));
						else
							InfoAndStats.UM_1034.setRocketDetailPrice(InfoAndStats.upgradeDetail.getRocketDetailPrice() + (int)(InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(fourthNUM1, fourthNUM2)/100.0F)));
					}
					InfoAndStats.isUM_1034Upgrading = false;
					InfoAndStats.UM_1034.setRocketDetailLevel(InfoAndStats.UM_1034.getRocketDetailLevel() + 1);
				}else{
					InfoAndStats.isUM_1034Upgrading = false;
					InfoAndStats.UM_1034.setRocketDetailAccessibility(true);
				}
//******************************************************************\\
			}else if(InfoAndStats.isUF_02Upgrading){
				if(InfoAndStats.UF_02.getRocketDetailAccessibility()){
					if(firstNUM1 >= firstNUM2){
						if(rand1.nextInt(100) + 1 < firstNUM1)
							InfoAndStats.UF_02.setRocketDetailThrust(InfoAndStats.upgradeDetail.getRocketDetailThrust() + InfoAndStats.upgradeDetail.getRocketDetailThrust()*(Math.min(firstNUM1, firstNUM2)/100.0F));
						else
							InfoAndStats.UF_02.setRocketDetailThrust(InfoAndStats.upgradeDetail.getRocketDetailThrust() - InfoAndStats.upgradeDetail.getRocketDetailThrust()*(Math.min(firstNUM1, firstNUM2)/100.0F));
					}else{
						if(rand1.nextInt(100) + 1 < firstNUM1)
							InfoAndStats.UF_02.setRocketDetailThrust(InfoAndStats.upgradeDetail.getRocketDetailThrust() - InfoAndStats.upgradeDetail.getRocketDetailThrust()*(Math.min(firstNUM1, firstNUM2)/100.0F));
						else
							InfoAndStats.UF_02.setRocketDetailThrust(InfoAndStats.upgradeDetail.getRocketDetailThrust() + InfoAndStats.upgradeDetail.getRocketDetailThrust()*(Math.min(firstNUM1, firstNUM2)/100.0F));
					}
					if(secondNUM1 >= secondNUM2){
						if(rand1.nextInt(100) + 1 < secondNUM1)
							InfoAndStats.UF_02.setRocketDetailImpulse(InfoAndStats.upgradeDetail.getRocketDetailImpulse() + InfoAndStats.upgradeDetail.getRocketDetailImpulse()*(Math.min(secondNUM1, secondNUM2)/100.0F));
						else
							InfoAndStats.UF_02.setRocketDetailImpulse(InfoAndStats.upgradeDetail.getRocketDetailImpulse() - InfoAndStats.upgradeDetail.getRocketDetailImpulse()*(Math.min(secondNUM1, secondNUM2)/100.0F));
					}else{
						if(rand1.nextInt(100) + 1 < secondNUM1)
							InfoAndStats.UF_02.setRocketDetailImpulse(InfoAndStats.upgradeDetail.getRocketDetailImpulse() - InfoAndStats.upgradeDetail.getRocketDetailImpulse()*(Math.min(secondNUM1, secondNUM2)/100.0F));
						else
							InfoAndStats.UF_02.setRocketDetailImpulse(InfoAndStats.upgradeDetail.getRocketDetailImpulse() + InfoAndStats.upgradeDetail.getRocketDetailImpulse()*(Math.min(secondNUM1, secondNUM2)/100.0F));
					}
					if(thirdNUM1 >= thirdNUM2){
						if(rand1.nextInt(100) + 1 < thirdNUM1)
							InfoAndStats.UF_02.setRocketDetailTime(InfoAndStats.upgradeDetail.getRocketDetailTime() + InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(thirdNUM1, thirdNUM2)/100.0F));
						else
							InfoAndStats.UF_02.setRocketDetailTime(InfoAndStats.upgradeDetail.getRocketDetailTime() - InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(thirdNUM1, thirdNUM2)/100.0F));
					}else{
						if(rand1.nextInt(100) + 1 < thirdNUM1)
							InfoAndStats.UF_02.setRocketDetailTime(InfoAndStats.upgradeDetail.getRocketDetailTime() - InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(thirdNUM1, thirdNUM2)/100.0F));
						else
							InfoAndStats.UF_02.setRocketDetailTime(InfoAndStats.upgradeDetail.getRocketDetailTime() + InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(thirdNUM1, thirdNUM2)/100.0F));
					}
					if(fourthNUM1 >= fourthNUM2){
						if(rand1.nextInt(100) + 1 < fourthNUM1)
							InfoAndStats.UF_02.setRocketDetailPrice(InfoAndStats.upgradeDetail.getRocketDetailPrice() + (int)(InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(fourthNUM1, fourthNUM2)/100.0F)));
						else
							InfoAndStats.UF_02.setRocketDetailPrice(InfoAndStats.upgradeDetail.getRocketDetailPrice() - (int)(InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(fourthNUM1, fourthNUM2)/100.0F)));
					}else{
						if(rand1.nextInt(100) + 1 < fourthNUM1)
							InfoAndStats.UF_02.setRocketDetailPrice(InfoAndStats.upgradeDetail.getRocketDetailPrice() - (int)(InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(fourthNUM1, fourthNUM2)/100.0F)));
						else
							InfoAndStats.UF_02.setRocketDetailPrice(InfoAndStats.upgradeDetail.getRocketDetailPrice() + (int)(InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(fourthNUM1, fourthNUM2)/100.0F)));
					}
					InfoAndStats.isUF_02Upgrading = false;
					InfoAndStats.UF_02.setRocketDetailLevel(InfoAndStats.UF_02.getRocketDetailLevel() + 1);
				}else{
					InfoAndStats.isUF_02Upgrading = false;
					InfoAndStats.UF_02.setRocketDetailAccessibility(true);
				}
//******************************************************************\\
			}else if(InfoAndStats.isMT_0112Upgrading){
				if(firstNUM1 >= firstNUM2){
					if(rand1.nextInt(100) + 1 < firstNUM1)
						InfoAndStats.MT_0112.setRocketDetailThrust(InfoAndStats.upgradeDetail.getRocketDetailThrust() + InfoAndStats.upgradeDetail.getRocketDetailThrust()*(Math.min(firstNUM1, firstNUM2)/100.0F));
					else
						InfoAndStats.MT_0112.setRocketDetailThrust(InfoAndStats.upgradeDetail.getRocketDetailThrust() - InfoAndStats.upgradeDetail.getRocketDetailThrust()*(Math.min(firstNUM1, firstNUM2)/100.0F));
				}else{
					if(rand1.nextInt(100) + 1 < firstNUM1)
						InfoAndStats.MT_0112.setRocketDetailThrust(InfoAndStats.upgradeDetail.getRocketDetailThrust() - InfoAndStats.upgradeDetail.getRocketDetailThrust()*(Math.min(firstNUM1, firstNUM2)/100.0F));
					else
						InfoAndStats.MT_0112.setRocketDetailThrust(InfoAndStats.upgradeDetail.getRocketDetailThrust() + InfoAndStats.upgradeDetail.getRocketDetailThrust()*(Math.min(firstNUM1, firstNUM2)/100.0F));
				}
				if(secondNUM1 >= secondNUM2){
					if(rand1.nextInt(100) + 1 < secondNUM1)
						InfoAndStats.MT_0112.setRocketDetailImpulse(InfoAndStats.upgradeDetail.getRocketDetailImpulse() + InfoAndStats.upgradeDetail.getRocketDetailImpulse()*(Math.min(secondNUM1, secondNUM2)/100.0F));
					else
						InfoAndStats.MT_0112.setRocketDetailImpulse(InfoAndStats.upgradeDetail.getRocketDetailImpulse() - InfoAndStats.upgradeDetail.getRocketDetailImpulse()*(Math.min(secondNUM1, secondNUM2)/100.0F));
				}else{
					if(rand1.nextInt(100) + 1 < secondNUM1)
						InfoAndStats.MT_0112.setRocketDetailImpulse(InfoAndStats.upgradeDetail.getRocketDetailImpulse() - InfoAndStats.upgradeDetail.getRocketDetailImpulse()*(Math.min(secondNUM1, secondNUM2)/100.0F));
					else
						InfoAndStats.MT_0112.setRocketDetailImpulse(InfoAndStats.upgradeDetail.getRocketDetailImpulse() + InfoAndStats.upgradeDetail.getRocketDetailImpulse()*(Math.min(secondNUM1, secondNUM2)/100.0F));
				}
				if(thirdNUM1 >= thirdNUM2){
					if(rand1.nextInt(100) + 1 < thirdNUM1)
						InfoAndStats.MT_0112.setRocketDetailTime(InfoAndStats.upgradeDetail.getRocketDetailTime() + InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(thirdNUM1, thirdNUM2)/100.0F));
					else
						InfoAndStats.MT_0112.setRocketDetailTime(InfoAndStats.upgradeDetail.getRocketDetailTime() - InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(thirdNUM1, thirdNUM2)/100.0F));
				}else{
					if(rand1.nextInt(100) + 1 < thirdNUM1)
						InfoAndStats.MT_0112.setRocketDetailTime(InfoAndStats.upgradeDetail.getRocketDetailTime() - InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(thirdNUM1, thirdNUM2)/100.0F));
					else
						InfoAndStats.MT_0112.setRocketDetailTime(InfoAndStats.upgradeDetail.getRocketDetailTime() + InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(thirdNUM1, thirdNUM2)/100.0F));
				}
				if(fourthNUM1 >= fourthNUM2){
					if(rand1.nextInt(100) + 1 < fourthNUM1)
						InfoAndStats.MT_0112.setRocketDetailPrice(InfoAndStats.upgradeDetail.getRocketDetailPrice() + (int)(InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(fourthNUM1, fourthNUM2)/100.0F)));
					else
						InfoAndStats.MT_0112.setRocketDetailPrice(InfoAndStats.upgradeDetail.getRocketDetailPrice() - (int)(InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(fourthNUM1, fourthNUM2)/100.0F)));
				}else{
					if(rand1.nextInt(100) + 1 < fourthNUM1)
						InfoAndStats.MT_0112.setRocketDetailPrice(InfoAndStats.upgradeDetail.getRocketDetailPrice() - (int)(InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(fourthNUM1, fourthNUM2)/100.0F)));
					else
						InfoAndStats.MT_0112.setRocketDetailPrice(InfoAndStats.upgradeDetail.getRocketDetailPrice() + (int)(InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(fourthNUM1, fourthNUM2)/100.0F)));
				}
				InfoAndStats.isMT_0112Upgrading = false;
				InfoAndStats.MT_0112.setRocketDetailLevel(InfoAndStats.MT_0112.getRocketDetailLevel() + 1);
//******************************************************************\\
			}else if(InfoAndStats.isMM_4Upgrading){
				if(InfoAndStats.MM_4.getRocketDetailAccessibility()){
					if(firstNUM1 >= firstNUM2){
						if(rand1.nextInt(100) + 1 < firstNUM1)
							InfoAndStats.MM_4.setRocketDetailThrust(InfoAndStats.upgradeDetail.getRocketDetailThrust() + InfoAndStats.upgradeDetail.getRocketDetailThrust()*(Math.min(firstNUM1, firstNUM2)/100.0F));
						else
							InfoAndStats.MM_4.setRocketDetailThrust(InfoAndStats.upgradeDetail.getRocketDetailThrust() - InfoAndStats.upgradeDetail.getRocketDetailThrust()*(Math.min(firstNUM1, firstNUM2)/100.0F));
					}else{
						if(rand1.nextInt(100) + 1 < firstNUM1)
							InfoAndStats.MM_4.setRocketDetailThrust(InfoAndStats.upgradeDetail.getRocketDetailThrust() - InfoAndStats.upgradeDetail.getRocketDetailThrust()*(Math.min(firstNUM1, firstNUM2)/100.0F));
						else
							InfoAndStats.MM_4.setRocketDetailThrust(InfoAndStats.upgradeDetail.getRocketDetailThrust() + InfoAndStats.upgradeDetail.getRocketDetailThrust()*(Math.min(firstNUM1, firstNUM2)/100.0F));
					}
					if(secondNUM1 >= secondNUM2){
						if(rand1.nextInt(100) + 1 < secondNUM1)
							InfoAndStats.MM_4.setRocketDetailImpulse(InfoAndStats.upgradeDetail.getRocketDetailImpulse() + InfoAndStats.upgradeDetail.getRocketDetailImpulse()*(Math.min(secondNUM1, secondNUM2)/100.0F));
						else
							InfoAndStats.MM_4.setRocketDetailImpulse(InfoAndStats.upgradeDetail.getRocketDetailImpulse() - InfoAndStats.upgradeDetail.getRocketDetailImpulse()*(Math.min(secondNUM1, secondNUM2)/100.0F));
					}else{
						if(rand1.nextInt(100) + 1 < secondNUM1)
							InfoAndStats.MM_4.setRocketDetailImpulse(InfoAndStats.upgradeDetail.getRocketDetailImpulse() - InfoAndStats.upgradeDetail.getRocketDetailImpulse()*(Math.min(secondNUM1, secondNUM2)/100.0F));
						else
							InfoAndStats.MM_4.setRocketDetailImpulse(InfoAndStats.upgradeDetail.getRocketDetailImpulse() + InfoAndStats.upgradeDetail.getRocketDetailImpulse()*(Math.min(secondNUM1, secondNUM2)/100.0F));
					}
					if(thirdNUM1 >= thirdNUM2){
						if(rand1.nextInt(100) + 1 < thirdNUM1)
							InfoAndStats.MM_4.setRocketDetailTime(InfoAndStats.upgradeDetail.getRocketDetailTime() + InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(thirdNUM1, thirdNUM2)/100.0F));
						else
							InfoAndStats.MM_4.setRocketDetailTime(InfoAndStats.upgradeDetail.getRocketDetailTime() - InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(thirdNUM1, thirdNUM2)/100.0F));
					}else{
						if(rand1.nextInt(100) + 1 < thirdNUM1)
							InfoAndStats.MM_4.setRocketDetailTime(InfoAndStats.upgradeDetail.getRocketDetailTime() - InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(thirdNUM1, thirdNUM2)/100.0F));
						else
							InfoAndStats.MM_4.setRocketDetailTime(InfoAndStats.upgradeDetail.getRocketDetailTime() + InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(thirdNUM1, thirdNUM2)/100.0F));
					}
					if(fourthNUM1 >= fourthNUM2){
						if(rand1.nextInt(100) + 1 < fourthNUM1)
							InfoAndStats.MM_4.setRocketDetailPrice(InfoAndStats.upgradeDetail.getRocketDetailPrice() + (int)(InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(fourthNUM1, fourthNUM2)/100.0F)));
						else
							InfoAndStats.MM_4.setRocketDetailPrice(InfoAndStats.upgradeDetail.getRocketDetailPrice() - (int)(InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(fourthNUM1, fourthNUM2)/100.0F)));
					}else{
						if(rand1.nextInt(100) + 1 < fourthNUM1)
							InfoAndStats.MM_4.setRocketDetailPrice(InfoAndStats.upgradeDetail.getRocketDetailPrice() - (int)(InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(fourthNUM1, fourthNUM2)/100.0F)));
						else
							InfoAndStats.MM_4.setRocketDetailPrice(InfoAndStats.upgradeDetail.getRocketDetailPrice() + (int)(InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(fourthNUM1, fourthNUM2)/100.0F)));
					}
					InfoAndStats.isMM_4Upgrading = false;
					InfoAndStats.MM_4.setRocketDetailLevel(InfoAndStats.MM_4.getRocketDetailLevel() + 1);
				}else{
					InfoAndStats.isMM_4Upgrading = false;
					InfoAndStats.MM_4.setRocketDetailAccessibility(true);
				}
//******************************************************************\\
			}else if(InfoAndStats.isMF_043Upgrading){
				if(InfoAndStats.MF_043.getRocketDetailAccessibility()){
					if(firstNUM1 >= firstNUM2){
						if(rand1.nextInt(100) + 1 < firstNUM1)
							InfoAndStats.MF_043.setRocketDetailThrust(InfoAndStats.upgradeDetail.getRocketDetailThrust() + InfoAndStats.upgradeDetail.getRocketDetailThrust()*(Math.min(firstNUM1, firstNUM2)/100.0F));
						else
							InfoAndStats.MF_043.setRocketDetailThrust(InfoAndStats.upgradeDetail.getRocketDetailThrust() - InfoAndStats.upgradeDetail.getRocketDetailThrust()*(Math.min(firstNUM1, firstNUM2)/100.0F));
					}else{
						if(rand1.nextInt(100) + 1 < firstNUM1)
							InfoAndStats.MF_043.setRocketDetailThrust(InfoAndStats.upgradeDetail.getRocketDetailThrust() - InfoAndStats.upgradeDetail.getRocketDetailThrust()*(Math.min(firstNUM1, firstNUM2)/100.0F));
						else
							InfoAndStats.MF_043.setRocketDetailThrust(InfoAndStats.upgradeDetail.getRocketDetailThrust() + InfoAndStats.upgradeDetail.getRocketDetailThrust()*(Math.min(firstNUM1, firstNUM2)/100.0F));
					}
					if(secondNUM1 >= secondNUM2){
						if(rand1.nextInt(100) + 1 < secondNUM1)
							InfoAndStats.MF_043.setRocketDetailImpulse(InfoAndStats.upgradeDetail.getRocketDetailImpulse() + InfoAndStats.upgradeDetail.getRocketDetailImpulse()*(Math.min(secondNUM1, secondNUM2)/100.0F));
						else
							InfoAndStats.MF_043.setRocketDetailImpulse(InfoAndStats.upgradeDetail.getRocketDetailImpulse() - InfoAndStats.upgradeDetail.getRocketDetailImpulse()*(Math.min(secondNUM1, secondNUM2)/100.0F));
					}else{
						if(rand1.nextInt(100) + 1 < secondNUM1)
							InfoAndStats.MF_043.setRocketDetailImpulse(InfoAndStats.upgradeDetail.getRocketDetailImpulse() - InfoAndStats.upgradeDetail.getRocketDetailImpulse()*(Math.min(secondNUM1, secondNUM2)/100.0F));
						else
							InfoAndStats.MF_043.setRocketDetailImpulse(InfoAndStats.upgradeDetail.getRocketDetailImpulse() + InfoAndStats.upgradeDetail.getRocketDetailImpulse()*(Math.min(secondNUM1, secondNUM2)/100.0F));
					}
					if(thirdNUM1 >= thirdNUM2){
						if(rand1.nextInt(100) + 1 < thirdNUM1)
							InfoAndStats.MF_043.setRocketDetailTime(InfoAndStats.upgradeDetail.getRocketDetailTime() + InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(thirdNUM1, thirdNUM2)/100.0F));
						else
							InfoAndStats.MF_043.setRocketDetailTime(InfoAndStats.upgradeDetail.getRocketDetailTime() - InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(thirdNUM1, thirdNUM2)/100.0F));
					}else{
						if(rand1.nextInt(100) + 1 < thirdNUM1)
							InfoAndStats.MF_043.setRocketDetailTime(InfoAndStats.upgradeDetail.getRocketDetailTime() - InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(thirdNUM1, thirdNUM2)/100.0F));
						else
							InfoAndStats.MF_043.setRocketDetailTime(InfoAndStats.upgradeDetail.getRocketDetailTime() + InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(thirdNUM1, thirdNUM2)/100.0F));
					}
					if(fourthNUM1 >= fourthNUM2){
						if(rand1.nextInt(100) + 1 < fourthNUM1)
							InfoAndStats.MF_043.setRocketDetailPrice(InfoAndStats.upgradeDetail.getRocketDetailPrice() + (int)(InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(fourthNUM1, fourthNUM2)/100.0F)));
						else
							InfoAndStats.MF_043.setRocketDetailPrice(InfoAndStats.upgradeDetail.getRocketDetailPrice() - (int)(InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(fourthNUM1, fourthNUM2)/100.0F)));
					}else{
						if(rand1.nextInt(100) + 1 < fourthNUM1)
							InfoAndStats.MF_043.setRocketDetailPrice(InfoAndStats.upgradeDetail.getRocketDetailPrice() - (int)(InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(fourthNUM1, fourthNUM2)/100.0F)));
						else
							InfoAndStats.MF_043.setRocketDetailPrice(InfoAndStats.upgradeDetail.getRocketDetailPrice() + (int)(InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(fourthNUM1, fourthNUM2)/100.0F)));
					}
					InfoAndStats.isMF_043Upgrading = false;
					InfoAndStats.MF_043.setRocketDetailLevel(InfoAndStats.MF_043.getRocketDetailLevel() + 1);
				}else{
					InfoAndStats.isMF_043Upgrading = false;
					InfoAndStats.MF_043.setRocketDetailAccessibility(true);
				}
//******************************************************************\\
			}else if(InfoAndStats.isLT_116Upgrading){
				if(firstNUM1 >= firstNUM2){
					if(rand1.nextInt(100) + 1 < firstNUM1)
						InfoAndStats.LT_116.setRocketDetailThrust(InfoAndStats.upgradeDetail.getRocketDetailThrust() + InfoAndStats.upgradeDetail.getRocketDetailThrust()*(Math.min(firstNUM1, firstNUM2)/100.0F));
					else
						InfoAndStats.LT_116.setRocketDetailThrust(InfoAndStats.upgradeDetail.getRocketDetailThrust() - InfoAndStats.upgradeDetail.getRocketDetailThrust()*(Math.min(firstNUM1, firstNUM2)/100.0F));
				}else{
					if(rand1.nextInt(100) + 1 < firstNUM1)
						InfoAndStats.LT_116.setRocketDetailThrust(InfoAndStats.upgradeDetail.getRocketDetailThrust() - InfoAndStats.upgradeDetail.getRocketDetailThrust()*(Math.min(firstNUM1, firstNUM2)/100.0F));
					else
						InfoAndStats.LT_116.setRocketDetailThrust(InfoAndStats.upgradeDetail.getRocketDetailThrust() + InfoAndStats.upgradeDetail.getRocketDetailThrust()*(Math.min(firstNUM1, firstNUM2)/100.0F));
				}
				if(secondNUM1 >= secondNUM2){
					if(rand1.nextInt(100) + 1 < secondNUM1)
						InfoAndStats.LT_116.setRocketDetailImpulse(InfoAndStats.upgradeDetail.getRocketDetailImpulse() + InfoAndStats.upgradeDetail.getRocketDetailImpulse()*(Math.min(secondNUM1, secondNUM2)/100.0F));
					else
						InfoAndStats.LT_116.setRocketDetailImpulse(InfoAndStats.upgradeDetail.getRocketDetailImpulse() - InfoAndStats.upgradeDetail.getRocketDetailImpulse()*(Math.min(secondNUM1, secondNUM2)/100.0F));
				}else{
					if(rand1.nextInt(100) + 1 < secondNUM1)
						InfoAndStats.LT_116.setRocketDetailImpulse(InfoAndStats.upgradeDetail.getRocketDetailImpulse() - InfoAndStats.upgradeDetail.getRocketDetailImpulse()*(Math.min(secondNUM1, secondNUM2)/100.0F));
					else
						InfoAndStats.LT_116.setRocketDetailImpulse(InfoAndStats.upgradeDetail.getRocketDetailImpulse() + InfoAndStats.upgradeDetail.getRocketDetailImpulse()*(Math.min(secondNUM1, secondNUM2)/100.0F));
				}
				if(thirdNUM1 >= thirdNUM2){
					if(rand1.nextInt(100) + 1 < thirdNUM1)
						InfoAndStats.LT_116.setRocketDetailTime(InfoAndStats.upgradeDetail.getRocketDetailTime() + InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(thirdNUM1, thirdNUM2)/100.0F));
					else
						InfoAndStats.LT_116.setRocketDetailTime(InfoAndStats.upgradeDetail.getRocketDetailTime() - InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(thirdNUM1, thirdNUM2)/100.0F));
				}else{
					if(rand1.nextInt(100) + 1 < thirdNUM1)
						InfoAndStats.LT_116.setRocketDetailTime(InfoAndStats.upgradeDetail.getRocketDetailTime() - InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(thirdNUM1, thirdNUM2)/100.0F));
					else
						InfoAndStats.LT_116.setRocketDetailTime(InfoAndStats.upgradeDetail.getRocketDetailTime() + InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(thirdNUM1, thirdNUM2)/100.0F));
				}
				if(fourthNUM1 >= fourthNUM2){
					if(rand1.nextInt(100) + 1 < fourthNUM1)
						InfoAndStats.LT_116.setRocketDetailPrice(InfoAndStats.upgradeDetail.getRocketDetailPrice() + (int)(InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(fourthNUM1, fourthNUM2)/100.0F)));
					else
						InfoAndStats.LT_116.setRocketDetailPrice(InfoAndStats.upgradeDetail.getRocketDetailPrice() - (int)(InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(fourthNUM1, fourthNUM2)/100.0F)));
				}else{
					if(rand1.nextInt(100) + 1 < fourthNUM1)
						InfoAndStats.LT_116.setRocketDetailPrice(InfoAndStats.upgradeDetail.getRocketDetailPrice() - (int)(InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(fourthNUM1, fourthNUM2)/100.0F)));
					else
						InfoAndStats.LT_116.setRocketDetailPrice(InfoAndStats.upgradeDetail.getRocketDetailPrice() + (int)(InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(fourthNUM1, fourthNUM2)/100.0F)));
				}
				InfoAndStats.isLT_116Upgrading = false;
				InfoAndStats.LT_116.setRocketDetailLevel(InfoAndStats.LT_116.getRocketDetailLevel() + 1);
//******************************************************************\\
			}else if(InfoAndStats.isLM_087Upgrading){
				if(InfoAndStats.LM_087.getRocketDetailAccessibility()){
					if(firstNUM1 >= firstNUM2){
						if(rand1.nextInt(100) + 1 < firstNUM1)
							InfoAndStats.LM_087.setRocketDetailThrust(InfoAndStats.upgradeDetail.getRocketDetailThrust() + InfoAndStats.upgradeDetail.getRocketDetailThrust()*(Math.min(firstNUM1, firstNUM2)/100.0F));
						else
							InfoAndStats.LM_087.setRocketDetailThrust(InfoAndStats.upgradeDetail.getRocketDetailThrust() - InfoAndStats.upgradeDetail.getRocketDetailThrust()*(Math.min(firstNUM1, firstNUM2)/100.0F));
					}else{
						if(rand1.nextInt(100) + 1 < firstNUM1)
							InfoAndStats.LM_087.setRocketDetailThrust(InfoAndStats.upgradeDetail.getRocketDetailThrust() - InfoAndStats.upgradeDetail.getRocketDetailThrust()*(Math.min(firstNUM1, firstNUM2)/100.0F));
						else
							InfoAndStats.LM_087.setRocketDetailThrust(InfoAndStats.upgradeDetail.getRocketDetailThrust() + InfoAndStats.upgradeDetail.getRocketDetailThrust()*(Math.min(firstNUM1, firstNUM2)/100.0F));
					}
					if(secondNUM1 >= secondNUM2){
						if(rand1.nextInt(100) + 1 < secondNUM1)
							InfoAndStats.LM_087.setRocketDetailImpulse(InfoAndStats.upgradeDetail.getRocketDetailImpulse() + InfoAndStats.upgradeDetail.getRocketDetailImpulse()*(Math.min(secondNUM1, secondNUM2)/100.0F));
						else
							InfoAndStats.LM_087.setRocketDetailImpulse(InfoAndStats.upgradeDetail.getRocketDetailImpulse() - InfoAndStats.upgradeDetail.getRocketDetailImpulse()*(Math.min(secondNUM1, secondNUM2)/100.0F));
					}else{
						if(rand1.nextInt(100) + 1 < secondNUM1)
							InfoAndStats.LM_087.setRocketDetailImpulse(InfoAndStats.upgradeDetail.getRocketDetailImpulse() - InfoAndStats.upgradeDetail.getRocketDetailImpulse()*(Math.min(secondNUM1, secondNUM2)/100.0F));
						else
							InfoAndStats.LM_087.setRocketDetailImpulse(InfoAndStats.upgradeDetail.getRocketDetailImpulse() + InfoAndStats.upgradeDetail.getRocketDetailImpulse()*(Math.min(secondNUM1, secondNUM2)/100.0F));
					}
					if(thirdNUM1 >= thirdNUM2){
						if(rand1.nextInt(100) + 1 < thirdNUM1)
							InfoAndStats.LM_087.setRocketDetailTime(InfoAndStats.upgradeDetail.getRocketDetailTime() + InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(thirdNUM1, thirdNUM2)/100.0F));
						else
							InfoAndStats.LM_087.setRocketDetailTime(InfoAndStats.upgradeDetail.getRocketDetailTime() - InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(thirdNUM1, thirdNUM2)/100.0F));
					}else{
						if(rand1.nextInt(100) + 1 < thirdNUM1)
							InfoAndStats.LM_087.setRocketDetailTime(InfoAndStats.upgradeDetail.getRocketDetailTime() - InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(thirdNUM1, thirdNUM2)/100.0F));
						else
							InfoAndStats.LM_087.setRocketDetailTime(InfoAndStats.upgradeDetail.getRocketDetailTime() + InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(thirdNUM1, thirdNUM2)/100.0F));
					}
					if(fourthNUM1 >= fourthNUM2){
						if(rand1.nextInt(100) + 1 < fourthNUM1)
							InfoAndStats.LM_087.setRocketDetailPrice(InfoAndStats.upgradeDetail.getRocketDetailPrice() + (int)(InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(fourthNUM1, fourthNUM2)/100.0F)));
						else
							InfoAndStats.LM_087.setRocketDetailPrice(InfoAndStats.upgradeDetail.getRocketDetailPrice() - (int)(InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(fourthNUM1, fourthNUM2)/100.0F)));
					}else{
						if(rand1.nextInt(100) + 1 < fourthNUM1)
							InfoAndStats.LM_087.setRocketDetailPrice(InfoAndStats.upgradeDetail.getRocketDetailPrice() - (int)(InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(fourthNUM1, fourthNUM2)/100.0F)));
						else
							InfoAndStats.LM_087.setRocketDetailPrice(InfoAndStats.upgradeDetail.getRocketDetailPrice() + (int)(InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(fourthNUM1, fourthNUM2)/100.0F)));
					}
					InfoAndStats.isLM_087Upgrading = false;
					InfoAndStats.LM_087.setRocketDetailLevel(InfoAndStats.LM_087.getRocketDetailLevel() + 1);
				}else{
					InfoAndStats.isLM_087Upgrading = false;
					InfoAndStats.LM_087.setRocketDetailAccessibility(true);
				}
//******************************************************************\\
			}else if(InfoAndStats.isLF_15Upgrading){
				if(InfoAndStats.LF_15.getRocketDetailAccessibility()){
					if(firstNUM1 >= firstNUM2){
						if(rand1.nextInt(100) + 1 < firstNUM1)
							InfoAndStats.LF_15.setRocketDetailThrust(InfoAndStats.upgradeDetail.getRocketDetailThrust() + InfoAndStats.upgradeDetail.getRocketDetailThrust()*(Math.min(firstNUM1, firstNUM2)/100.0F));
						else
							InfoAndStats.LF_15.setRocketDetailThrust(InfoAndStats.upgradeDetail.getRocketDetailThrust() - InfoAndStats.upgradeDetail.getRocketDetailThrust()*(Math.min(firstNUM1, firstNUM2)/100.0F));
					}else{
						if(rand1.nextInt(100) + 1 < firstNUM1)
							InfoAndStats.LF_15.setRocketDetailThrust(InfoAndStats.upgradeDetail.getRocketDetailThrust() - InfoAndStats.upgradeDetail.getRocketDetailThrust()*(Math.min(firstNUM1, firstNUM2)/100.0F));
						else
							InfoAndStats.LF_15.setRocketDetailThrust(InfoAndStats.upgradeDetail.getRocketDetailThrust() + InfoAndStats.upgradeDetail.getRocketDetailThrust()*(Math.min(firstNUM1, firstNUM2)/100.0F));
					}
					if(secondNUM1 >= secondNUM2){
						if(rand1.nextInt(100) + 1 < secondNUM1)
							InfoAndStats.LF_15.setRocketDetailImpulse(InfoAndStats.upgradeDetail.getRocketDetailImpulse() + InfoAndStats.upgradeDetail.getRocketDetailImpulse()*(Math.min(secondNUM1, secondNUM2)/100.0F));
						else
							InfoAndStats.LF_15.setRocketDetailImpulse(InfoAndStats.upgradeDetail.getRocketDetailImpulse() - InfoAndStats.upgradeDetail.getRocketDetailImpulse()*(Math.min(secondNUM1, secondNUM2)/100.0F));
					}else{
						if(rand1.nextInt(100) + 1 < secondNUM1)
							InfoAndStats.LF_15.setRocketDetailImpulse(InfoAndStats.upgradeDetail.getRocketDetailImpulse() - InfoAndStats.upgradeDetail.getRocketDetailImpulse()*(Math.min(secondNUM1, secondNUM2)/100.0F));
						else
							InfoAndStats.LF_15.setRocketDetailImpulse(InfoAndStats.upgradeDetail.getRocketDetailImpulse() + InfoAndStats.upgradeDetail.getRocketDetailImpulse()*(Math.min(secondNUM1, secondNUM2)/100.0F));
					}
					if(thirdNUM1 >= thirdNUM2){
						if(rand1.nextInt(100) + 1 < thirdNUM1)
							InfoAndStats.LF_15.setRocketDetailTime(InfoAndStats.upgradeDetail.getRocketDetailTime() + InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(thirdNUM1, thirdNUM2)/100.0F));
						else
							InfoAndStats.LF_15.setRocketDetailTime(InfoAndStats.upgradeDetail.getRocketDetailTime() - InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(thirdNUM1, thirdNUM2)/100.0F));
					}else{
						if(rand1.nextInt(100) + 1 < thirdNUM1)
							InfoAndStats.LF_15.setRocketDetailTime(InfoAndStats.upgradeDetail.getRocketDetailTime() - InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(thirdNUM1, thirdNUM2)/100.0F));
						else
							InfoAndStats.LF_15.setRocketDetailTime(InfoAndStats.upgradeDetail.getRocketDetailTime() + InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(thirdNUM1, thirdNUM2)/100.0F));
					}
					if(fourthNUM1 >= fourthNUM2){
						if(rand1.nextInt(100) + 1 < fourthNUM1)
							InfoAndStats.LF_15.setRocketDetailPrice(InfoAndStats.upgradeDetail.getRocketDetailPrice() + (int)(InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(fourthNUM1, fourthNUM2)/100.0F)));
						else
							InfoAndStats.LF_15.setRocketDetailPrice(InfoAndStats.upgradeDetail.getRocketDetailPrice() - (int)(InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(fourthNUM1, fourthNUM2)/100.0F)));
					}else{
						if(rand1.nextInt(100) + 1 < fourthNUM1)
							InfoAndStats.LF_15.setRocketDetailPrice(InfoAndStats.upgradeDetail.getRocketDetailPrice() - (int)(InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(fourthNUM1, fourthNUM2)/100.0F)));
						else
							InfoAndStats.LF_15.setRocketDetailPrice(InfoAndStats.upgradeDetail.getRocketDetailPrice() + (int)(InfoAndStats.upgradeDetail.getRocketDetailTime()*(Math.min(fourthNUM1, fourthNUM2)/100.0F)));
					}
					InfoAndStats.isLF_15Upgrading = false;
					InfoAndStats.LF_15.setRocketDetailLevel(InfoAndStats.LF_15.getRocketDetailLevel() + 1);
				}else{
					InfoAndStats.isLF_15Upgrading = false;
					InfoAndStats.LF_15.setRocketDetailAccessibility(true);
				}
//******************************************************************\\
			}
			InfoAndStats.hasTask = false;
			InfoAndStats.days = (int)InfoAndStats.date;
			//***\\ Проценты для апгрейда
			firstNUM1 = rand1.nextInt(100) + 1;
			firstNUM2 = 100 - firstNUM1;
			secondNUM1 = rand1.nextInt(100) + 1;
			secondNUM2 = 100 - secondNUM1;
			thirdNUM1 = rand1.nextInt(100) + 1;
			thirdNUM2 = 100 - thirdNUM1;
			fourthNUM1 = rand1.nextInt(100) + 1;
			fourthNUM2 = 100 - fourthNUM1;
			//***\\
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
			if(!isUpgradeWindowDraw){
				backButtonActiveSprite.draw(batch);
			}else{
				backButtonInactiveSprite.draw(batch);
			}
		}else{
			backButtonInactiveSprite.draw(batch);
		}
		
		//Отрисовка текста в научном центре//
		if(!InfoAndStats.lngRussian){
			text.draw(batch, "Science centre", 0.01F*width, 0.99F*height);
		}else{
			text.draw(batch, "Научный центр", 0.01F*width, 0.99F*height);
		}
		
		drawUpgrade();
		drawUpgradeWindow();
		
		batch.end();
		
		btnListeners();
	
	}
	
	private void upgradeInit(){
		upgradeInactive = new Texture("objects/upgradeInactive.png");
		upgradeActive = new Texture("objects/upgradeActive.png");
		upgradeSpriteInactive = new Sprite(upgradeInactive);
		upgradeSpriteActive = new Sprite(upgradeActive);
		upgradeAspectRatio = (float)upgradeInactive.getWidth()/upgradeInactive.getHeight();
		//***\\
		upgrade1Width = 0.2F*backgroundSprite.getWidth();
		upgrade1Height = (float)upgrade1Width/upgradeAspectRatio;
		upgrade1X = 0.2F*backgroundSprite.getWidth();
		upgrade1Y = 0.15F*backgroundSprite.getHeight();
		upgradeSpriteInactive.setBounds(upgrade1X, upgrade1Y, upgrade1Width, upgrade1Height);
		//***\\
		upgrade2Width = 0.34042553191489361702127659574468F*backgroundSprite.getWidth();
		upgrade2Height = (float)upgrade2Width/upgradeAspectRatio;
		upgrade2X = 0.2F*backgroundSprite.getWidth() - 0.20625F*upgrade2Width;
		upgrade2Y = 0.15F*backgroundSprite.getHeight() - 0.20645161290322580645161290322581F*upgrade2Height;
		upgradeSpriteActive.setBounds(upgrade2X, upgrade2Y, upgrade2Width, upgrade2Height);
	}
	private void upgradeWindowInit(){
		//Диалоговое окно апгрейдов\\
		upgradeWindowSprite = new Sprite(ImgResDraw.dialogWindow);
		upgradeWindowWidth = 0.75F*width;
		upgradeWindowHeight = upgradeWindowWidth/1.45F;
		upgradeWindowX = 0.5F*width - 0.5F*upgradeWindowWidth;
		upgradeWindowY = 0.5F*height - 0.5F*upgradeWindowHeight;
		upgradeWindowSprite.setBounds(upgradeWindowX, upgradeWindowY, upgradeWindowWidth, upgradeWindowHeight);
		isUpgradeWindowDraw = false;
		backUpgradeWindowSpriteInactive = new Sprite(ImgResDraw.backIconInactive);
		backUpgradeWindowSpriteActive = new Sprite(ImgResDraw.backIconActive);
		backUpgradeWindow1Width = 0.065F*width;
		backUpgradeWindow1Height = backUpgradeWindow1Width;
		backUpgradeWindow1X = upgradeWindowX + 0.865F*upgradeWindowWidth;
		backUpgradeWindow1Y = upgradeWindowY + 0.05F*upgradeWindowHeight;
		backUpgradeWindowSpriteInactive.setBounds(backUpgradeWindow1X, backUpgradeWindow1Y, backUpgradeWindow1Width, backUpgradeWindow1Height);
		backUpgradeWindow2Width = 0.0975F*width;
		backUpgradeWindow2Height = backUpgradeWindow2Width;
		backUpgradeWindow2X = backUpgradeWindow1X - 0.16666666666666666666666666666667F*backUpgradeWindow2Width;
		backUpgradeWindow2Y = backUpgradeWindow1Y - 0.16666666666666666666666666666667F*backUpgradeWindow2Height;
		backUpgradeWindowSpriteActive.setBounds(backUpgradeWindow2X, backUpgradeWindow2Y, backUpgradeWindow2Width, backUpgradeWindow2Height);
		backZSprite = new Sprite(new Texture("bckgrnd/backgroundGray.png"));
		backZSprite.setBounds(0.0F, 0.0F, width, height);
		arrowSprite1 = new Sprite(new Texture("objects/arrow.png"));
		arrowSprite2 = new Sprite(new Texture("objects/arrow.png"));
		arrowSprite3 = new Sprite(new Texture("objects/arrow.png"));
		arrowSprite4 = new Sprite(new Texture("objects/arrow.png"));
		arrowSprite1.setBounds(upgradeWindowX + 0.45F*upgradeWindowWidth, upgradeWindowY + 0.69F*upgradeWindowHeight, 0.25F*upgradeWindowWidth, 0.125F*upgradeWindowWidth);
		arrowSprite2.setBounds(upgradeWindowX + 0.45F*upgradeWindowWidth, upgradeWindowY + 0.54F*upgradeWindowHeight, 0.25F*upgradeWindowWidth, 0.125F*upgradeWindowWidth);
		arrowSprite3.setBounds(upgradeWindowX + 0.45F*upgradeWindowWidth, upgradeWindowY + 0.39F*upgradeWindowHeight, 0.25F*upgradeWindowWidth, 0.125F*upgradeWindowWidth);
		arrowSprite4.setBounds(upgradeWindowX + 0.45F*upgradeWindowWidth, upgradeWindowY + 0.24F*upgradeWindowHeight, 0.25F*upgradeWindowWidth, 0.125F*upgradeWindowWidth);
	}
	private void upgradeButtonInit(){
		upgradeButtonSpriteInactive = new Sprite(new Texture("btns/upgradeInactiveUS.png"));
		upgradeButtonSpriteActive = new Sprite(new Texture("btns/upgradeActiveUS.png"));
		if(InfoAndStats.lngRussian){
			upgradeButtonSpriteInactive.setTexture(new Texture("btns/RU/upgradeInactiveRU.png"));
			upgradeButtonSpriteActive.setTexture(new Texture("btns/RU/upgradeActiveRU.png"));
		}
		upgradeButton1Width = 0.25F*upgradeWindowWidth;
		upgradeButton1Height = upgradeButton1Width/2.0F;
		upgradeButton1X = upgradeWindowX + 0.05F*upgradeWindowWidth;
		upgradeButton1Y = upgradeWindowY + 0.05F*upgradeWindowHeight;
		upgradeButton2Width = 0.390625F*upgradeWindowWidth;
		upgradeButton2Height = upgradeButton2Width/2;
		upgradeButton2X = upgradeButton1X - 0.18F*upgradeButton2Width;
		upgradeButton2Y = upgradeButton1Y - 0.18F*upgradeButton2Height;
		upgradeButtonSpriteInactive.setBounds(upgradeButton1X, upgradeButton1Y, upgradeButton1Width, upgradeButton1Height);
		upgradeButtonSpriteActive.setBounds(upgradeButton2X, upgradeButton2Y, upgradeButton2Width, upgradeButton2Height);
	}
	private void researchButtonInit(){
		researchButtonSpriteInactive = new Sprite(new Texture("btns/researchInactiveUS.png"));
		researchButtonSpriteActive = new Sprite(new Texture("btns/researchActiveUS.png"));
		if(InfoAndStats.lngRussian){
			researchButtonSpriteInactive.setTexture(new Texture("btns/RU/researchInactiveRU.png"));
			researchButtonSpriteActive.setTexture(new Texture("btns/RU/researchActiveRU.png"));
		}
		researchButton1Width = 0.25F*upgradeWindowWidth;
		researchButton1Height = researchButton1Width/2.0F;
		researchButton1X = upgradeWindowX + 0.05F*upgradeWindowWidth;
		researchButton1Y = upgradeWindowY + 0.05F*upgradeWindowHeight;
		researchButton2Width = 0.390625F*upgradeWindowWidth;
		researchButton2Height = researchButton2Width/2;
		researchButton2X = researchButton1X - 0.18F*researchButton2Width;
		researchButton2Y = researchButton1Y - 0.18F*researchButton2Height;
		researchButtonSpriteInactive.setBounds(researchButton1X, researchButton1Y, researchButton1Width, researchButton1Height);
		researchButtonSpriteActive.setBounds(researchButton2X, researchButton2Y, researchButton2Width, researchButton2Height);
	}
	
	private void UT_1202Init(){
		//UT_1202\\
		UT_1202Texture = new Texture("rockets/UT_1202_stin.png");
		UT_1202Sprite = new Sprite(UT_1202Texture);
		UT_1202Width = 0.15F*upgradeWindowHeight;
		UT_1202Height = UT_1202Width;
		UT_1202X = upgradeWindowX + 0.05F*upgradeWindowWidth;
		UT_1202Y = upgradeWindowY + 0.7F*upgradeWindowHeight;
		UT_1202Sprite.setBounds(UT_1202X, UT_1202Y, UT_1202Width, UT_1202Height);
	}
	private void UT_1202Coords(){
		UT_1202X = upgradeWindowX + 0.05F*upgradeWindowWidth;
		UT_1202Y = upgradeWindowY + 0.7F*upgradeWindowHeight;
		UT_1202Sprite.setBounds(UT_1202X, UT_1202Y, UT_1202Width, UT_1202Height);
	}
	private void MT_0112Init(){
		//MT_0112\\
		MT_0112Texture = new Texture("rockets/MT_0112.png");
		MT_0112Sprite = new Sprite(MT_0112Texture);
		MT_0112Width = 0.15F*upgradeWindowHeight;
		MT_0112Height = MT_0112Width;
		MT_0112X = UT_1202X;
		MT_0112Y = UT_1202Y - 1.5F*MT_0112Height;
		MT_0112Sprite.setBounds(MT_0112X, MT_0112Y, MT_0112Width, MT_0112Height);
	}
	private void MT_0112Coords(){
		MT_0112X = UT_1202X;
		MT_0112Y = UT_1202Y - 1.5F*MT_0112Height;
		MT_0112Sprite.setBounds(MT_0112X, MT_0112Y, MT_0112Width, MT_0112Height);
	}
	private void LT_116Init(){
		//LT_116\\
		LT_116Texture = new Texture("rockets/LT_116.png");
		LT_116Sprite = new Sprite(LT_116Texture);
		LT_116Width = 0.15F*upgradeWindowHeight;
		LT_116Height = LT_116Width;
		LT_116X = UT_1202X;
		LT_116Y = MT_0112Y - 1.5F*LT_116Height;
		LT_116Sprite.setBounds(LT_116X, LT_116Y, LT_116Width, LT_116Height);
	}
	private void LT_116Coords(){
		LT_116X = UT_1202X;
		LT_116Y = MT_0112Y - 1.5F*LT_116Height;
		LT_116Sprite.setBounds(LT_116X, LT_116Y, LT_116Width, LT_116Height);
	}
	private void UM_1034Init(){
		//UM_1034\\
		UM_1034Texture = new Texture("rockets/UM_1034.png");
		UM_1034Sprite = new Sprite(UM_1034Texture);
		UM_1034Width = 0.15F*upgradeWindowHeight;
		UM_1034Height = UM_1034Width;
		UM_1034X = UT_1202X + UM_1034Width;
		UM_1034Y = upgradeWindowY + 0.7F*upgradeWindowHeight;
		UM_1034Sprite.setBounds(UM_1034X, UM_1034Y, UM_1034Width, UM_1034Height);
	}
	private void UM_1034Coords(){
		UM_1034X = UT_1202X + UM_1034Width;
		UM_1034Y = upgradeWindowY + 0.7F*upgradeWindowHeight;
		UM_1034Sprite.setBounds(UM_1034X, UM_1034Y, UM_1034Width, UM_1034Height);
	}
	private void MM_04Init(){
		//MM_4\\
		MM_4Texture = new Texture("rockets/MM_4.png");
		MM_4Sprite = new Sprite(MM_4Texture);
		MM_4Width = 0.15F*upgradeWindowHeight;
		MM_4Height = MM_4Width;
		MM_4X = MT_0112X + MM_4Width;
		MM_4Y = UT_1202Y - 1.5F*MM_4Height;
		MM_4Sprite.setBounds(MM_4X, MM_4Y, MM_4Width, MM_4Height);
	}
	private void MM_4Coords(){
		MM_4X = MT_0112X + MM_4Width;
		MM_4Y = UT_1202Y - 1.5F*MM_4Height;
		MM_4Sprite.setBounds(MM_4X, MM_4Y, MM_4Width, MM_4Height);
	}
	private void LM_087Init(){
		//LM_087\\
		LM_087Texture = new Texture("rockets/LM_087.png");
		LM_087Sprite = new Sprite(LM_087Texture);
		LM_087Width = 0.15F*upgradeWindowHeight;
		LM_087Height = LM_087Width;
		LM_087X = LT_116X + LM_087Width;
		LM_087Y = MT_0112Y - 1.5F*LM_087Height;
		LM_087Sprite.setBounds(LM_087X, LM_087Y, LM_087Width, LM_087Height);
	}
	private void LM_087Coords(){
		LM_087X = LT_116X + LM_087Width;
		LM_087Y = MT_0112Y - 1.5F*LM_087Height;
		LM_087Sprite.setBounds(LM_087X, LM_087Y, LM_087Width, LM_087Height);
	}
	private void UF_02Init(){
		//UF_02\\
		UF_02Texture = new Texture("rockets/UF_02.png");
		UF_02Sprite = new Sprite(UF_02Texture);
		UF_02Width = 0.15F*upgradeWindowHeight;
		UF_02Height = UF_02Width;
		UF_02X = UM_1034X + UF_02Width;
		UF_02Y = upgradeWindowY + 0.7F*upgradeWindowHeight;
		UF_02Sprite.setBounds(UF_02X, UF_02Y, UF_02Width, UF_02Height);
	}
	private void UF_02Coords(){
		UF_02X = UM_1034X + UF_02Width;
		UF_02Y = upgradeWindowY + 0.7F*upgradeWindowHeight;
		UF_02Sprite.setBounds(UF_02X, UF_02Y, UF_02Width, UF_02Height);
	}
	private void MF_043Init(){
		//MF_043\\
		MF_043Texture = new Texture("rockets/MF_043.png");
		MF_043Sprite = new Sprite(MF_043Texture);
		MF_043Width = 0.15F*upgradeWindowHeight;
		MF_043Height = MF_043Width;
		MF_043X = MM_4X + MF_043Width;
		MF_043Y = UM_1034Y - 1.5F*MF_043Height;
		MF_043Sprite.setBounds(MF_043X, MF_043Y, MF_043Width, MF_043Height);
	}
	private void MF_043Coords(){
		MF_043X = MM_4X + MF_043Width;
		MF_043Y = UM_1034Y - 1.5F*MF_043Height;
		MF_043Sprite.setBounds(MF_043X, MF_043Y, MF_043Width, MF_043Height);
	}
	private void LF_15Init(){
		//LF_15\\
		LF_15Texture = new Texture("rockets/LF_15.png");
		LF_15Sprite = new Sprite(LF_15Texture);
		LF_15Width = 0.15F*upgradeWindowHeight;
		LF_15Height = LF_15Width;
		LF_15X = LM_087X + LF_15Width;
		LF_15Y = MM_4Y - 1.5F*LF_15Height;
		LF_15Sprite.setBounds(LF_15X, LF_15Y, LF_15Width, LF_15Height);
	}
	private void LF_15Coords(){
		LF_15X = LM_087X + LF_15Width;
		LF_15Y = MM_4Y - 1.5F*LF_15Height;
		LF_15Sprite.setBounds(LF_15X, LF_15Y, LF_15Width, LF_15Height);
	}
	
	private void drawUpgrade(){
		if(controller.isOn(upgrade1X, upgrade1Y, upgrade1Width, upgrade1Height)){
			if(!isUpgradeWindowDraw){
				upgradeSpriteActive.draw(batch);
			}else{
				upgradeSpriteInactive.draw(batch);
			}
		}else{
			upgradeSpriteInactive.draw(batch);
		}
	}
	private void drawUpgradeWindow(){
		if(isUpgradeWindowDraw && !(isUT_1202Selected || isUM_1034Selected || isUF_02Selected || isMT_0112Selected || isMM_4Selected ||isMF_043Selected || isLT_116Selected || isLM_087Selected || isLF_15Selected)){
			backZSprite.draw(batch);
			upgradeWindowSprite.draw(batch);
			if(InfoAndStats.hasTask){
				int days = (int)(InfoAndStats.upgradeDetail.getRocketDetailImpulse() + InfoAndStats.upgradeDetail.getRocketDetailThrust() + InfoAndStats.upgradeDetail.getRocketDetailTime()/5.0F + InfoAndStats.upgradeDetail.getRocketDetailPrice()/5.0F) - (int)(InfoAndStats.date - InfoAndStats.days);
				if(!InfoAndStats.lngRussian){
					if(InfoAndStats.isUT_1202Upgrading){
						text.draw(batch, "Now is researching/upgrading:", upgradeWindowX + 0.05F*upgradeWindowWidth, upgradeWindowY + 0.2F*upgradeWindowHeight);
						text.draw(batch, "UT-1202 detail", upgradeWindowX + 0.05F*upgradeWindowWidth, upgradeWindowY + 0.2F*upgradeWindowHeight - 1.5F*text.getCapHeight());
						text.draw(batch, days + " days left", upgradeWindowX + 0.05F*upgradeWindowWidth, upgradeWindowY + 0.2F*upgradeWindowHeight - 3.0F*text.getCapHeight());
					}
					if(InfoAndStats.isUM_1034Upgrading){
						text.draw(batch, "Now is researching/upgrading:", upgradeWindowX + 0.05F*upgradeWindowWidth, upgradeWindowY + 0.2F*upgradeWindowHeight);
						text.draw(batch, "UM-1034 detail", upgradeWindowX + 0.05F*upgradeWindowWidth, upgradeWindowY + 0.2F*upgradeWindowHeight - 1.5F*text.getCapHeight());
						text.draw(batch, days + " days left", upgradeWindowX + 0.05F*upgradeWindowWidth, upgradeWindowY + 0.2F*upgradeWindowHeight - 3.0F*text.getCapHeight());
					}
					if(InfoAndStats.isUF_02Upgrading){
						text.draw(batch, "Now is researching/upgrading:", upgradeWindowX + 0.05F*upgradeWindowWidth, upgradeWindowY + 0.2F*upgradeWindowHeight);
						text.draw(batch, "UF-02 detail", upgradeWindowX + 0.05F*upgradeWindowWidth, upgradeWindowY + 0.2F*upgradeWindowHeight - 1.5F*text.getCapHeight());
						text.draw(batch, days + " days left", upgradeWindowX + 0.05F*upgradeWindowWidth, upgradeWindowY + 0.2F*upgradeWindowHeight - 3.0F*text.getCapHeight());
					}
					if(InfoAndStats.isMT_0112Upgrading){
						text.draw(batch, "Now is researching/upgrading:", upgradeWindowX + 0.05F*upgradeWindowWidth, upgradeWindowY + 0.2F*upgradeWindowHeight);
						text.draw(batch, "MT-0112 detail", upgradeWindowX + 0.05F*upgradeWindowWidth, upgradeWindowY + 0.2F*upgradeWindowHeight - 1.5F*text.getCapHeight());
						text.draw(batch, days + " days left", upgradeWindowX + 0.05F*upgradeWindowWidth, upgradeWindowY + 0.2F*upgradeWindowHeight - 3.0F*text.getCapHeight());
					}
					if(InfoAndStats.isMM_4Upgrading){
						text.draw(batch, "Now is researching/upgrading:", upgradeWindowX + 0.05F*upgradeWindowWidth, upgradeWindowY + 0.2F*upgradeWindowHeight);
						text.draw(batch, "MM-4 detail", upgradeWindowX + 0.05F*upgradeWindowWidth, upgradeWindowY + 0.2F*upgradeWindowHeight - 1.5F*text.getCapHeight());
						text.draw(batch, days + " days left", upgradeWindowX + 0.05F*upgradeWindowWidth, upgradeWindowY + 0.2F*upgradeWindowHeight - 3.0F*text.getCapHeight());
					}
					if(InfoAndStats.isMF_043Upgrading){
						text.draw(batch, "Now is researching/upgrading:", upgradeWindowX + 0.05F*upgradeWindowWidth, upgradeWindowY + 0.2F*upgradeWindowHeight);
						text.draw(batch, "MF-043 detail", upgradeWindowX + 0.05F*upgradeWindowWidth, upgradeWindowY + 0.2F*upgradeWindowHeight - 1.5F*text.getCapHeight());
						text.draw(batch, days + " days left", upgradeWindowX + 0.05F*upgradeWindowWidth, upgradeWindowY + 0.2F*upgradeWindowHeight - 3.0F*text.getCapHeight());
					}
					if(InfoAndStats.isLT_116Upgrading){
						text.draw(batch, "Now is researching/upgrading:", upgradeWindowX + 0.05F*upgradeWindowWidth, upgradeWindowY + 0.2F*upgradeWindowHeight);
						text.draw(batch, "LT-116 detail", upgradeWindowX + 0.05F*upgradeWindowWidth, upgradeWindowY + 0.2F*upgradeWindowHeight - 1.5F*text.getCapHeight());
						text.draw(batch, days + " days left", upgradeWindowX + 0.05F*upgradeWindowWidth, upgradeWindowY + 0.2F*upgradeWindowHeight - 3.0F*text.getCapHeight());
					}
					if(InfoAndStats.isLM_087Upgrading){
						text.draw(batch, "Now is researching/upgrading:", upgradeWindowX + 0.05F*upgradeWindowWidth, upgradeWindowY + 0.2F*upgradeWindowHeight);
						text.draw(batch, "LM-087 detail", upgradeWindowX + 0.05F*upgradeWindowWidth, upgradeWindowY + 0.2F*upgradeWindowHeight - 1.5F*text.getCapHeight());
						text.draw(batch, days + " days left", upgradeWindowX + 0.05F*upgradeWindowWidth, upgradeWindowY + 0.2F*upgradeWindowHeight - 3.0F*text.getCapHeight());
					}
					if(InfoAndStats.isLF_15Upgrading){
						text.draw(batch, "Now is researching/upgrading:", upgradeWindowX + 0.05F*upgradeWindowWidth, upgradeWindowY + 0.2F*upgradeWindowHeight);
						text.draw(batch, "LF-15 detail", upgradeWindowX + 0.05F*upgradeWindowWidth, upgradeWindowY + 0.2F*upgradeWindowHeight - 1.5F*text.getCapHeight());
						text.draw(batch, days + " days left", upgradeWindowX + 0.05F*upgradeWindowWidth, upgradeWindowY + 0.2F*upgradeWindowHeight - 3.0F*text.getCapHeight());
					}
				}else{
					if(InfoAndStats.isUT_1202Upgrading){
						text.draw(batch, "Сейчас исследуется/улучшается деталь:", upgradeWindowX + 0.05F*upgradeWindowWidth, upgradeWindowY + 0.2F*upgradeWindowHeight);
						text.draw(batch, "УТ-1202", upgradeWindowX + 0.05F*upgradeWindowWidth, upgradeWindowY + 0.2F*upgradeWindowHeight - 1.5F*text.getCapHeight());
						text.draw(batch, days + " дней осталось", upgradeWindowX + 0.05F*upgradeWindowWidth, upgradeWindowY + 0.2F*upgradeWindowHeight - 3.0F*text.getCapHeight());
					}
					if(InfoAndStats.isUM_1034Upgrading){
						text.draw(batch, "Сейчас исследуется/улучшается деталь:", upgradeWindowX + 0.05F*upgradeWindowWidth, upgradeWindowY + 0.2F*upgradeWindowHeight);
						text.draw(batch, "УМ-1034", upgradeWindowX + 0.05F*upgradeWindowWidth, upgradeWindowY + 0.2F*upgradeWindowHeight - 1.5F*text.getCapHeight());
						text.draw(batch, days + " дней осталось", upgradeWindowX + 0.05F*upgradeWindowWidth, upgradeWindowY + 0.2F*upgradeWindowHeight - 3.0F*text.getCapHeight());
					}
					if(InfoAndStats.isUF_02Upgrading){
						text.draw(batch, "Сейчас исследуется/улучшается деталь:", upgradeWindowX + 0.05F*upgradeWindowWidth, upgradeWindowY + 0.2F*upgradeWindowHeight);
						text.draw(batch, "УФ-02", upgradeWindowX + 0.05F*upgradeWindowWidth, upgradeWindowY + 0.2F*upgradeWindowHeight - 1.5F*text.getCapHeight());
						text.draw(batch, days + " дней осталось", upgradeWindowX + 0.05F*upgradeWindowWidth, upgradeWindowY + 0.2F*upgradeWindowHeight - 3.0F*text.getCapHeight());
					}
					if(InfoAndStats.isMT_0112Upgrading){
						text.draw(batch, "Сейчас исследуется/улучшается деталь:", upgradeWindowX + 0.05F*upgradeWindowWidth, upgradeWindowY + 0.2F*upgradeWindowHeight);
						text.draw(batch, "МТ-0112", upgradeWindowX + 0.05F*upgradeWindowWidth, upgradeWindowY + 0.2F*upgradeWindowHeight - 1.5F*text.getCapHeight());
						text.draw(batch, days + " дней осталось", upgradeWindowX + 0.05F*upgradeWindowWidth, upgradeWindowY + 0.2F*upgradeWindowHeight - 3.0F*text.getCapHeight());
					}
					if(InfoAndStats.isMM_4Upgrading){
						text.draw(batch, "Сейчас исследуется/улучшается деталь:", upgradeWindowX + 0.05F*upgradeWindowWidth, upgradeWindowY + 0.2F*upgradeWindowHeight);
						text.draw(batch, "ММ-4", upgradeWindowX + 0.05F*upgradeWindowWidth, upgradeWindowY + 0.2F*upgradeWindowHeight - 1.5F*text.getCapHeight());
						text.draw(batch, days + " дней осталось", upgradeWindowX + 0.05F*upgradeWindowWidth, upgradeWindowY + 0.2F*upgradeWindowHeight - 3.0F*text.getCapHeight());
					}
					if(InfoAndStats.isMF_043Upgrading){
						text.draw(batch, "Сейчас исследуется/улучшается деталь:", upgradeWindowX + 0.05F*upgradeWindowWidth, upgradeWindowY + 0.2F*upgradeWindowHeight);
						text.draw(batch, "МФ-043", upgradeWindowX + 0.05F*upgradeWindowWidth, upgradeWindowY + 0.2F*upgradeWindowHeight - 1.5F*text.getCapHeight());
						text.draw(batch, days + " дней осталось", upgradeWindowX + 0.05F*upgradeWindowWidth, upgradeWindowY + 0.2F*upgradeWindowHeight - 3.0F*text.getCapHeight());
					}
					if(InfoAndStats.isLT_116Upgrading){
						text.draw(batch, "Сейчас исследуется/улучшается деталь:", upgradeWindowX + 0.05F*upgradeWindowWidth, upgradeWindowY + 0.2F*upgradeWindowHeight);
						text.draw(batch, "ЛТ-116", upgradeWindowX + 0.05F*upgradeWindowWidth, upgradeWindowY + 0.2F*upgradeWindowHeight - 1.5F*text.getCapHeight());
						text.draw(batch, days + " дней осталось", upgradeWindowX + 0.05F*upgradeWindowWidth, upgradeWindowY + 0.2F*upgradeWindowHeight - 3.0F*text.getCapHeight());
					}
					if(InfoAndStats.isLM_087Upgrading){
						text.draw(batch, "Сейчас исследуется/улучшается деталь:", upgradeWindowX + 0.05F*upgradeWindowWidth, upgradeWindowY + 0.2F*upgradeWindowHeight);
						text.draw(batch, "ЛМ-087", upgradeWindowX + 0.05F*upgradeWindowWidth, upgradeWindowY + 0.2F*upgradeWindowHeight - 1.5F*text.getCapHeight());
						text.draw(batch, days + " дней осталось", upgradeWindowX + 0.05F*upgradeWindowWidth, upgradeWindowY + 0.2F*upgradeWindowHeight - 3.0F*text.getCapHeight());
					}
					if(InfoAndStats.isLF_15Upgrading){
						text.draw(batch, "Сейчас исследуется/улучшается деталь:", upgradeWindowX + 0.05F*upgradeWindowWidth, upgradeWindowY + 0.2F*upgradeWindowHeight);
						text.draw(batch, "ЛФ-15", upgradeWindowX + 0.05F*upgradeWindowWidth, upgradeWindowY + 0.2F*upgradeWindowHeight - 1.5F*text.getCapHeight());
						text.draw(batch, days + " дней осталось", upgradeWindowX + 0.05F*upgradeWindowWidth, upgradeWindowY + 0.2F*upgradeWindowHeight - 3.0F*text.getCapHeight());
					}
				}
			}
			if(controller.isOn(backUpgradeWindow1X, backUpgradeWindow1Y, backUpgradeWindow1Width, backUpgradeWindow1Height)){
				backUpgradeWindowSpriteActive.draw(batch);
			}else{
				backUpgradeWindowSpriteInactive.draw(batch);
			}
			if(!InfoAndStats.lngRussian){
				text.draw(batch, "Upgrade/Research of details", upgradeWindowX + 0.245F*upgradeWindowWidth, upgradeWindowY + 0.95F*upgradeWindowHeight);
			}else{
				text.draw(batch, "Улучшение/Исследование деталей", upgradeWindowX + 0.2F*upgradeWindowWidth, upgradeWindowY + 0.95F*upgradeWindowHeight);
			}
			drawRocketDetails();
			UT_1202Coords();
			UM_1034Coords();
			UF_02Coords();
			MT_0112Coords();
			MM_4Coords();
			MF_043Coords();
			LT_116Coords();
			LM_087Coords();
			LF_15Coords();
		}else if(isUpgradeWindowDraw && isUT_1202Selected){
			drawUT_1202Upgrade();
		}else if(isUpgradeWindowDraw && isUM_1034Selected){
			drawUM_1034Upgrade();
		}else if(isUpgradeWindowDraw && isUF_02Selected){
			drawUF_02Upgrade();
		}else if(isUpgradeWindowDraw && isMT_0112Selected){
			drawMT_0112Upgrade();
		}else if(isUpgradeWindowDraw && isMM_4Selected){
			drawMM_4Upgrade();
		}else if(isUpgradeWindowDraw && isMF_043Selected){
			drawMF_043Upgrade();
		}else if(isUpgradeWindowDraw && isLT_116Selected){
			drawLT_116Upgrade();
		}else if(isUpgradeWindowDraw && isLM_087Selected){
			drawLM_087Upgrade();
		}else if(isUpgradeWindowDraw && isLF_15Selected){
			drawLF_15Upgrade();
		}
	}
	
	private void drawRocketDetails(){
		UT_1202Sprite.draw(batch);
		UM_1034Sprite.draw(batch);
		UF_02Sprite.draw(batch);
		MT_0112Sprite.draw(batch);
		MM_4Sprite.draw(batch);
		MF_043Sprite.draw(batch);
		LT_116Sprite.draw(batch);
		LM_087Sprite.draw(batch);
		LF_15Sprite.draw(batch);
	}
	
	private void drawUT_1202Upgrade(){
		backZSprite.draw(batch);
		upgradeWindowSprite.draw(batch);
		UT_1202Sprite.setBounds(upgradeWindowSprite.getX() + 0.005F*upgradeWindowSprite.getWidth(), upgradeWindowSprite.getY() + 0.55F*upgradeWindowSprite.getHeight() - UT_1202Height, 2.0F*UT_1202Width, 2.0F*UT_1202Height);
		UT_1202Sprite.draw(batch);
		//***\\
		int duration = (int)(InfoAndStats.UT_1202.getRocketDetailImpulse() + InfoAndStats.UT_1202.getRocketDetailThrust() + InfoAndStats.UT_1202.getRocketDetailTime()/5.0F + InfoAndStats.UT_1202.getRocketDetailPrice()/5.0F);
		text.draw(batch, InfoAndStats.UT_1202.getRocketDetailName(), upgradeWindowX + 0.425F*upgradeWindowWidth, upgradeWindowY + 0.95F*upgradeWindowHeight);
		if(!InfoAndStats.lngRussian){
			text.draw(batch, "Thrust: " + Math.rint(100.0F*InfoAndStats.UT_1202.getRocketDetailThrust())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.8F*upgradeWindowHeight);
			text.draw(batch, "Sp. impulse: " + Math.rint(100.0F*InfoAndStats.UT_1202.getRocketDetailImpulse())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.65F*upgradeWindowHeight);
			text.draw(batch, "Working time: " + Math.rint(100.0F*InfoAndStats.UT_1202.getRocketDetailTime())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.5F*upgradeWindowHeight);
			text.draw(batch, "Price: " + InfoAndStats.UT_1202.getRocketDetailPrice(), upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.35F*upgradeWindowHeight);
			text.draw(batch, "Upgrade will take " + duration + " days", upgradeWindowX + 0.325F*upgradeWindowWidth, upgradeWindowY + 0.165F*upgradeWindowHeight);
		}else{
			text.draw(batch, "Тяга: " + Math.rint(100.0F*InfoAndStats.UT_1202.getRocketDetailThrust())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.8F*upgradeWindowHeight);
			text.draw(batch, "Уд.импульс: " + Math.rint(100.0F*InfoAndStats.UT_1202.getRocketDetailImpulse())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.65F*upgradeWindowHeight);
			text.draw(batch, "Раб.время: " + Math.rint(100.0F*InfoAndStats.UT_1202.getRocketDetailTime())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.5F*upgradeWindowHeight);
			text.draw(batch, "Цена: " + InfoAndStats.UT_1202.getRocketDetailPrice(), upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.35F*upgradeWindowHeight);
			text.draw(batch, "Улучшение займет " + duration + " дней", upgradeWindowX + 0.315F*upgradeWindowWidth, upgradeWindowY + 0.16F*upgradeWindowHeight);
		}
		textSuc.draw(batch, firstNUM1 + "%", 1.075F*arrowSprite1.getX(), 1.1675F*arrowSprite1.getY());
		textFail.draw(batch, firstNUM2 + "%", 1.075F*arrowSprite1.getX(), 1.1F*arrowSprite1.getY());
		textSuc.draw(batch, secondNUM1 + "%", 1.075F*arrowSprite2.getX(), 1.205F*arrowSprite2.getY());
		textFail.draw(batch, secondNUM2 + "%", 1.075F*arrowSprite2.getX(), 1.125F*arrowSprite2.getY());
		textSuc.draw(batch,thirdNUM1 + "%", 1.075F*arrowSprite3.getX(), 1.265F*arrowSprite3.getY());
		textFail.draw(batch, thirdNUM2 + "%", 1.075F*arrowSprite3.getX(), 1.16F*arrowSprite3.getY());
		textSuc.draw(batch,fourthNUM1 + "%", 1.075F*arrowSprite4.getX(), 1.375F*arrowSprite4.getY());
		textFail.draw(batch, fourthNUM2 + "%", 1.075F*arrowSprite4.getX(), 1.225F*arrowSprite4.getY());
		//***\\
		text.draw(batch, Math.rint(100.0F*InfoAndStats.UT_1202.getRocketDetailThrust())/100.0F + " + " + Math.rint(100.0F*InfoAndStats.UT_1202.getRocketDetailThrust()*(Math.min(firstNUM1, firstNUM2)/100.0F))/100.0F, arrowSprite1.getX() + 1.01F*arrowSprite1.getWidth(), arrowSprite1.getY() + 0.85F*arrowSprite1.getHeight());
		text.draw(batch, Math.rint(100.0F*InfoAndStats.UT_1202.getRocketDetailThrust())/100.0F + " - " + Math.rint(100.0F*InfoAndStats.UT_1202.getRocketDetailThrust()*(Math.min(firstNUM1, firstNUM2)/100.0F))/100.0F, arrowSprite1.getX() + 1.01F*arrowSprite1.getWidth(), arrowSprite1.getY() + 0.35F*arrowSprite1.getHeight());
		text.draw(batch, Math.rint(100.0F*InfoAndStats.UT_1202.getRocketDetailImpulse())/100.0F + " + " + Math.rint(100.0F*InfoAndStats.UT_1202.getRocketDetailImpulse()*(Math.min(secondNUM1, secondNUM2)/100.0F))/100.0F, arrowSprite2.getX() + 1.01F*arrowSprite2.getWidth(), arrowSprite2.getY() + 0.85F*arrowSprite2.getHeight());
		text.draw(batch, Math.rint(100.0F*InfoAndStats.UT_1202.getRocketDetailImpulse())/100.0F + " - " + Math.rint(100.0F*InfoAndStats.UT_1202.getRocketDetailImpulse()*(Math.min(secondNUM1, secondNUM2)/100.0F))/100.0F, arrowSprite2.getX() + 1.01F*arrowSprite2.getWidth(), arrowSprite2.getY() + 0.35F*arrowSprite2.getHeight());
		text.draw(batch, Math.rint(100.0F*InfoAndStats.UT_1202.getRocketDetailTime())/100.0F + " + " + Math.rint(100.0F*InfoAndStats.UT_1202.getRocketDetailTime()*(Math.min(thirdNUM1, thirdNUM2)/100.0F))/100.0F, arrowSprite3.getX() + 1.01F*arrowSprite3.getWidth(), arrowSprite3.getY() + 0.85F*arrowSprite3.getHeight());
		text.draw(batch, Math.rint(100.0F*InfoAndStats.UT_1202.getRocketDetailTime())/100.0F + " - " + Math.rint(100.0F*InfoAndStats.UT_1202.getRocketDetailTime()*(Math.min(thirdNUM1, thirdNUM2)/100.0F))/100.0F, arrowSprite3.getX() + 1.01F*arrowSprite3.getWidth(), arrowSprite3.getY() + 0.35F*arrowSprite3.getHeight());
		text.draw(batch, InfoAndStats.UT_1202.getRocketDetailPrice() + " + " + (int)(InfoAndStats.UT_1202.getRocketDetailPrice()*(Math.min(fourthNUM1, fourthNUM2)/100.0F)), arrowSprite4.getX() + 1.01F*arrowSprite4.getWidth(), arrowSprite4.getY() + 0.85F*arrowSprite4.getHeight());
		text.draw(batch, InfoAndStats.UT_1202.getRocketDetailPrice() + " - " + (int)(InfoAndStats.UT_1202.getRocketDetailPrice()*(Math.min(fourthNUM1, fourthNUM2)/100.0F)), arrowSprite4.getX() + 1.01F*arrowSprite4.getWidth(), arrowSprite4.getY() + 0.35F*arrowSprite4.getHeight());
		//***\\
		arrowSprite1.draw(batch);
		arrowSprite2.draw(batch);
		arrowSprite3.draw(batch);
		arrowSprite4.draw(batch);
		//***\\
		if(controller.isOn(backUpgradeWindow1X, backUpgradeWindow1Y, backUpgradeWindow1Width, backUpgradeWindow1Height)){
			backUpgradeWindowSpriteActive.draw(batch);
		}else{
			backUpgradeWindowSpriteInactive.draw(batch);
		}
		if(controller.isOn(upgradeButton1X, upgradeButton1Y, upgradeButton1Width, upgradeButton1Height)){
			upgradeButtonSpriteActive.draw(batch);
		}else{
			upgradeButtonSpriteInactive.draw(batch);
		}
	}
	private void drawUM_1034Upgrade(){
		backZSprite.draw(batch);
		upgradeWindowSprite.draw(batch);
		UM_1034Sprite.setBounds(upgradeWindowSprite.getX() + 0.005F*upgradeWindowSprite.getWidth(), upgradeWindowSprite.getY() + 0.55F*upgradeWindowSprite.getHeight() - UM_1034Height, 2.0F*UM_1034Width, 2.0F*UM_1034Height);
		UM_1034Sprite.draw(batch);
		text.draw(batch, InfoAndStats.UM_1034.getRocketDetailName(), upgradeWindowX + 0.425F*upgradeWindowWidth, upgradeWindowY + 0.95F*upgradeWindowHeight);
		if(controller.isOn(backUpgradeWindow1X, backUpgradeWindow1Y, backUpgradeWindow1Width, backUpgradeWindow1Height)){
			backUpgradeWindowSpriteActive.draw(batch);
		}else{
			backUpgradeWindowSpriteInactive.draw(batch);
		}
		int duration = (int)(InfoAndStats.UM_1034.getRocketDetailImpulse() + InfoAndStats.UM_1034.getRocketDetailThrust() + InfoAndStats.UM_1034.getRocketDetailTime()/5.0F + InfoAndStats.UM_1034.getRocketDetailPrice()/5.0F);
		if(InfoAndStats.UM_1034.getRocketDetailAccessibility()){
			//***\\
			if(!InfoAndStats.lngRussian){
				text.draw(batch, "Thrust: " + Math.rint(100.0F*InfoAndStats.UM_1034.getRocketDetailThrust())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.8F*upgradeWindowHeight);
				text.draw(batch, "Sp. impulse: " + Math.rint(100.0F*InfoAndStats.UM_1034.getRocketDetailImpulse())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.65F*upgradeWindowHeight);
				text.draw(batch, "Working time: " + Math.rint(100.0F*InfoAndStats.UM_1034.getRocketDetailTime())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.5F*upgradeWindowHeight);
				text.draw(batch, "Price: " + InfoAndStats.UM_1034.getRocketDetailPrice(), upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.35F*upgradeWindowHeight);
				text.draw(batch, "Upgrade will take " + duration + " days", upgradeWindowX + 0.325F*upgradeWindowWidth, upgradeWindowY + 0.165F*upgradeWindowHeight);
			}else{
				text.draw(batch, "Тяга: " + Math.rint(100.0F*InfoAndStats.UM_1034.getRocketDetailThrust())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.8F*upgradeWindowHeight);
				text.draw(batch, "Уд.импульс: " + Math.rint(100.0F*InfoAndStats.UM_1034.getRocketDetailImpulse())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.65F*upgradeWindowHeight);
				text.draw(batch, "Раб.время: " + Math.rint(100.0F*InfoAndStats.UM_1034.getRocketDetailTime())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.5F*upgradeWindowHeight);
				text.draw(batch, "Цена: " + InfoAndStats.UM_1034.getRocketDetailPrice(), upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.35F*upgradeWindowHeight);
				text.draw(batch, "Улучшение займет " + duration + " дней", upgradeWindowX + 0.315F*upgradeWindowWidth, upgradeWindowY + 0.16F*upgradeWindowHeight);
			}
			textSuc.draw(batch, firstNUM1 + "%", 1.075F*arrowSprite1.getX(), 1.1675F*arrowSprite1.getY());
			textFail.draw(batch, firstNUM2 + "%", 1.075F*arrowSprite1.getX(), 1.1F*arrowSprite1.getY());
			textSuc.draw(batch, secondNUM1 + "%", 1.075F*arrowSprite2.getX(), 1.205F*arrowSprite2.getY());
			textFail.draw(batch, secondNUM2 + "%", 1.075F*arrowSprite2.getX(), 1.125F*arrowSprite2.getY());
			textSuc.draw(batch,thirdNUM1 + "%", 1.075F*arrowSprite3.getX(), 1.265F*arrowSprite3.getY());
			textFail.draw(batch, thirdNUM2 + "%", 1.075F*arrowSprite3.getX(), 1.16F*arrowSprite3.getY());
			textSuc.draw(batch,fourthNUM1 + "%", 1.075F*arrowSprite4.getX(), 1.375F*arrowSprite4.getY());
			textFail.draw(batch, fourthNUM2 + "%", 1.075F*arrowSprite4.getX(), 1.225F*arrowSprite4.getY());
			//***\\
			text.draw(batch, Math.rint(100.0F*InfoAndStats.UM_1034.getRocketDetailThrust())/100.0F + " + " + Math.rint(100.0F*InfoAndStats.UM_1034.getRocketDetailThrust()*(Math.min(firstNUM1, firstNUM2)/100.0F))/100.0F, arrowSprite1.getX() + 1.01F*arrowSprite1.getWidth(), arrowSprite1.getY() + 0.85F*arrowSprite1.getHeight());
			text.draw(batch, Math.rint(100.0F*InfoAndStats.UM_1034.getRocketDetailThrust())/100.0F + " - " + Math.rint(100.0F*InfoAndStats.UM_1034.getRocketDetailThrust()*(Math.min(firstNUM1, firstNUM2)/100.0F))/100.0F, arrowSprite1.getX() + 1.01F*arrowSprite1.getWidth(), arrowSprite1.getY() + 0.35F*arrowSprite1.getHeight());
			text.draw(batch, Math.rint(100.0F*InfoAndStats.UM_1034.getRocketDetailImpulse())/100.0F + " + " + Math.rint(100.0F*InfoAndStats.UM_1034.getRocketDetailImpulse()*(Math.min(secondNUM1, secondNUM2)/100.0F))/100.0F, arrowSprite2.getX() + 1.01F*arrowSprite2.getWidth(), arrowSprite2.getY() + 0.85F*arrowSprite2.getHeight());
			text.draw(batch, Math.rint(100.0F*InfoAndStats.UM_1034.getRocketDetailImpulse())/100.0F + " - " + Math.rint(100.0F*InfoAndStats.UM_1034.getRocketDetailImpulse()*(Math.min(secondNUM1, secondNUM2)/100.0F))/100.0F, arrowSprite2.getX() + 1.01F*arrowSprite2.getWidth(), arrowSprite2.getY() + 0.35F*arrowSprite2.getHeight());
			text.draw(batch, Math.rint(100.0F*InfoAndStats.UM_1034.getRocketDetailTime())/100.0F + " + " + Math.rint(100.0F*InfoAndStats.UM_1034.getRocketDetailTime()*(Math.min(thirdNUM1, thirdNUM2)/100.0F))/100.0F, arrowSprite3.getX() + 1.01F*arrowSprite3.getWidth(), arrowSprite3.getY() + 0.85F*arrowSprite3.getHeight());
			text.draw(batch, Math.rint(100.0F*InfoAndStats.UM_1034.getRocketDetailTime())/100.0F + " - " + Math.rint(100.0F*InfoAndStats.UM_1034.getRocketDetailTime()*(Math.min(thirdNUM1, thirdNUM2)/100.0F))/100.0F, arrowSprite3.getX() + 1.01F*arrowSprite3.getWidth(), arrowSprite3.getY() + 0.35F*arrowSprite3.getHeight());
			text.draw(batch, InfoAndStats.UM_1034.getRocketDetailPrice() + " + " + (int)(InfoAndStats.UM_1034.getRocketDetailPrice()*(Math.min(fourthNUM1, fourthNUM2)/100.0F)), arrowSprite4.getX() + 1.01F*arrowSprite4.getWidth(), arrowSprite4.getY() + 0.85F*arrowSprite4.getHeight());
			text.draw(batch, InfoAndStats.UM_1034.getRocketDetailPrice() + " - " + (int)(InfoAndStats.UM_1034.getRocketDetailPrice()*(Math.min(fourthNUM1, fourthNUM2)/100.0F)), arrowSprite4.getX() + 1.01F*arrowSprite4.getWidth(), arrowSprite4.getY() + 0.35F*arrowSprite4.getHeight());
			//***\\
			arrowSprite1.draw(batch);
			arrowSprite2.draw(batch);
			arrowSprite3.draw(batch);
			arrowSprite4.draw(batch);
			//***\\
			if(controller.isOn(upgradeButton1X, upgradeButton1Y, upgradeButton1Width, upgradeButton1Height)){
				upgradeButtonSpriteActive.draw(batch);
			}else{
				upgradeButtonSpriteInactive.draw(batch);
			}
		}else{
			if(!InfoAndStats.lngRussian){
				text.draw(batch, "Thrust: " + Math.rint(100.0F*InfoAndStats.UM_1034.getRocketDetailThrust())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.8F*upgradeWindowHeight);
				text.draw(batch, "Sp. impulse: " + Math.rint(100.0F*InfoAndStats.UM_1034.getRocketDetailImpulse())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.65F*upgradeWindowHeight);
				text.draw(batch, "Working time: " + Math.rint(100.0F*InfoAndStats.UM_1034.getRocketDetailTime())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.5F*upgradeWindowHeight);
				text.draw(batch, "Price: " + InfoAndStats.UM_1034.getRocketDetailPrice(), upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.35F*upgradeWindowHeight);
				text.draw(batch, "Research will take " + duration + " days", upgradeWindowX + 0.325F*upgradeWindowWidth, upgradeWindowY + 0.165F*upgradeWindowHeight);
			}else{
				text.draw(batch, "Тяга: " + Math.rint(100.0F*InfoAndStats.UM_1034.getRocketDetailThrust())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.8F*upgradeWindowHeight);
				text.draw(batch, "Уд.импульс: " + Math.rint(100.0F*InfoAndStats.UM_1034.getRocketDetailImpulse())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.65F*upgradeWindowHeight);
				text.draw(batch, "Раб.время: " + Math.rint(100.0F*InfoAndStats.UM_1034.getRocketDetailTime())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.5F*upgradeWindowHeight);
				text.draw(batch, "Цена: " + InfoAndStats.UM_1034.getRocketDetailPrice(), upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.35F*upgradeWindowHeight);
				text.draw(batch, "Исследование займет " + duration + " дней", upgradeWindowX + 0.315F*upgradeWindowWidth, upgradeWindowY + 0.16F*upgradeWindowHeight);
			}
			if(controller.isOn(researchButton1X, researchButton1Y, researchButton1Width, researchButton1Height)){
				researchButtonSpriteActive.draw(batch);
			}else{
				researchButtonSpriteInactive.draw(batch);
			}
		}
	}
	private void drawUF_02Upgrade(){
		backZSprite.draw(batch);
		upgradeWindowSprite.draw(batch);
		UF_02Sprite.setBounds(upgradeWindowSprite.getX() + 0.005F*upgradeWindowSprite.getWidth(), upgradeWindowSprite.getY() + 0.55F*upgradeWindowSprite.getHeight() - UF_02Height, 2.0F*UF_02Width, 2.0F*UF_02Height);
		UF_02Sprite.draw(batch);
		text.draw(batch, InfoAndStats.UF_02.getRocketDetailName(), upgradeWindowX + 0.425F*upgradeWindowWidth, upgradeWindowY + 0.95F*upgradeWindowHeight);
		if(controller.isOn(backUpgradeWindow1X, backUpgradeWindow1Y, backUpgradeWindow1Width, backUpgradeWindow1Height)){
			backUpgradeWindowSpriteActive.draw(batch);
		}else{
			backUpgradeWindowSpriteInactive.draw(batch);
		}
		int duration = (int)(InfoAndStats.UF_02.getRocketDetailImpulse() + InfoAndStats.UF_02.getRocketDetailThrust() + InfoAndStats.UF_02.getRocketDetailTime()/5.0F + InfoAndStats.UF_02.getRocketDetailPrice()/5.0F);
		if(InfoAndStats.UF_02.getRocketDetailAccessibility()){
			//***\\
			if(!InfoAndStats.lngRussian){
				text.draw(batch, "Thrust: " + Math.rint(100.0F*InfoAndStats.UF_02.getRocketDetailThrust())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.8F*upgradeWindowHeight);
				text.draw(batch, "Sp. impulse: " + Math.rint(100.0F*InfoAndStats.UF_02.getRocketDetailImpulse())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.65F*upgradeWindowHeight);
				text.draw(batch, "Working time: " + Math.rint(100.0F*InfoAndStats.UF_02.getRocketDetailTime())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.5F*upgradeWindowHeight);
				text.draw(batch, "Price: " + InfoAndStats.UF_02.getRocketDetailPrice(), upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.35F*upgradeWindowHeight);
				text.draw(batch, "Upgrade will take " + duration + " days", upgradeWindowX + 0.325F*upgradeWindowWidth, upgradeWindowY + 0.165F*upgradeWindowHeight);
			}else{
				text.draw(batch, "Тяга: " + Math.rint(100.0F*InfoAndStats.UF_02.getRocketDetailThrust())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.8F*upgradeWindowHeight);
				text.draw(batch, "Уд.импульс: " + Math.rint(100.0F*InfoAndStats.UF_02.getRocketDetailImpulse())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.65F*upgradeWindowHeight);
				text.draw(batch, "Раб.время: " + Math.rint(100.0F*InfoAndStats.UF_02.getRocketDetailTime())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.5F*upgradeWindowHeight);
				text.draw(batch, "Цена: " + InfoAndStats.UF_02.getRocketDetailPrice(), upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.35F*upgradeWindowHeight);
				text.draw(batch, "Улучшение займет " + duration + " дней", upgradeWindowX + 0.315F*upgradeWindowWidth, upgradeWindowY + 0.16F*upgradeWindowHeight);
			}
			textSuc.draw(batch, firstNUM1 + "%", 1.075F*arrowSprite1.getX(), 1.1675F*arrowSprite1.getY());
			textFail.draw(batch, firstNUM2 + "%", 1.075F*arrowSprite1.getX(), 1.1F*arrowSprite1.getY());
			textSuc.draw(batch, secondNUM1 + "%", 1.075F*arrowSprite2.getX(), 1.205F*arrowSprite2.getY());
			textFail.draw(batch, secondNUM2 + "%", 1.075F*arrowSprite2.getX(), 1.125F*arrowSprite2.getY());
			textSuc.draw(batch,thirdNUM1 + "%", 1.075F*arrowSprite3.getX(), 1.265F*arrowSprite3.getY());
			textFail.draw(batch, thirdNUM2 + "%", 1.075F*arrowSprite3.getX(), 1.16F*arrowSprite3.getY());
			textSuc.draw(batch,fourthNUM1 + "%", 1.075F*arrowSprite4.getX(), 1.375F*arrowSprite4.getY());
			textFail.draw(batch, fourthNUM2 + "%", 1.075F*arrowSprite4.getX(), 1.225F*arrowSprite4.getY());
			//***\\
			text.draw(batch, Math.rint(100.0F*InfoAndStats.UF_02.getRocketDetailThrust())/100.0F + " + " + Math.rint(100.0F*InfoAndStats.UF_02.getRocketDetailThrust()*(Math.min(firstNUM1, firstNUM2)/100.0F))/100.0F, arrowSprite1.getX() + 1.01F*arrowSprite1.getWidth(), arrowSprite1.getY() + 0.85F*arrowSprite1.getHeight());
			text.draw(batch, Math.rint(100.0F*InfoAndStats.UF_02.getRocketDetailThrust())/100.0F + " - " + Math.rint(100.0F*InfoAndStats.UF_02.getRocketDetailThrust()*(Math.min(firstNUM1, firstNUM2)/100.0F))/100.0F, arrowSprite1.getX() + 1.01F*arrowSprite1.getWidth(), arrowSprite1.getY() + 0.35F*arrowSprite1.getHeight());
			text.draw(batch, Math.rint(100.0F*InfoAndStats.UF_02.getRocketDetailImpulse())/100.0F + " + " + Math.rint(100.0F*InfoAndStats.UF_02.getRocketDetailImpulse()*(Math.min(secondNUM1, secondNUM2)/100.0F))/100.0F, arrowSprite2.getX() + 1.01F*arrowSprite2.getWidth(), arrowSprite2.getY() + 0.85F*arrowSprite2.getHeight());
			text.draw(batch, Math.rint(100.0F*InfoAndStats.UF_02.getRocketDetailImpulse())/100.0F + " - " + Math.rint(100.0F*InfoAndStats.UF_02.getRocketDetailImpulse()*(Math.min(secondNUM1, secondNUM2)/100.0F))/100.0F, arrowSprite2.getX() + 1.01F*arrowSprite2.getWidth(), arrowSprite2.getY() + 0.35F*arrowSprite2.getHeight());
			text.draw(batch, Math.rint(100.0F*InfoAndStats.UF_02.getRocketDetailTime())/100.0F + " + " + Math.rint(100.0F*InfoAndStats.UF_02.getRocketDetailTime()*(Math.min(thirdNUM1, thirdNUM2)/100.0F))/100.0F, arrowSprite3.getX() + 1.01F*arrowSprite3.getWidth(), arrowSprite3.getY() + 0.85F*arrowSprite3.getHeight());
			text.draw(batch, Math.rint(100.0F*InfoAndStats.UF_02.getRocketDetailTime())/100.0F + " - " + Math.rint(100.0F*InfoAndStats.UF_02.getRocketDetailTime()*(Math.min(thirdNUM1, thirdNUM2)/100.0F))/100.0F, arrowSprite3.getX() + 1.01F*arrowSprite3.getWidth(), arrowSprite3.getY() + 0.35F*arrowSprite3.getHeight());
			text.draw(batch, InfoAndStats.UF_02.getRocketDetailPrice() + " + " + (int)(InfoAndStats.UF_02.getRocketDetailPrice()*(Math.min(fourthNUM1, fourthNUM2)/100.0F)), arrowSprite4.getX() + 1.01F*arrowSprite4.getWidth(), arrowSprite4.getY() + 0.85F*arrowSprite4.getHeight());
			text.draw(batch, InfoAndStats.UF_02.getRocketDetailPrice() + " - " + (int)(InfoAndStats.UF_02.getRocketDetailPrice()*(Math.min(fourthNUM1, fourthNUM2)/100.0F)), arrowSprite4.getX() + 1.01F*arrowSprite4.getWidth(), arrowSprite4.getY() + 0.35F*arrowSprite4.getHeight());
			//***\\
			arrowSprite1.draw(batch);
			arrowSprite2.draw(batch);
			arrowSprite3.draw(batch);
			arrowSprite4.draw(batch);
			//***\\
			if(controller.isOn(upgradeButton1X, upgradeButton1Y, upgradeButton1Width, upgradeButton1Height)){
				upgradeButtonSpriteActive.draw(batch);
			}else{
				upgradeButtonSpriteInactive.draw(batch);
			}
		}else{
			if(!InfoAndStats.lngRussian){
				text.draw(batch, "Thrust: " + Math.rint(100.0F*InfoAndStats.UF_02.getRocketDetailThrust())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.8F*upgradeWindowHeight);
				text.draw(batch, "Sp. impulse: " + Math.rint(100.0F*InfoAndStats.UF_02.getRocketDetailImpulse())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.65F*upgradeWindowHeight);
				text.draw(batch, "Working time: " + Math.rint(100.0F*InfoAndStats.UF_02.getRocketDetailTime())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.5F*upgradeWindowHeight);
				text.draw(batch, "Price: " + InfoAndStats.UF_02.getRocketDetailPrice(), upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.35F*upgradeWindowHeight);
				text.draw(batch, "Research will take " + duration + " days", upgradeWindowX + 0.325F*upgradeWindowWidth, upgradeWindowY + 0.165F*upgradeWindowHeight);
			}else{
				text.draw(batch, "Тяга: " + Math.rint(100.0F*InfoAndStats.UF_02.getRocketDetailThrust())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.8F*upgradeWindowHeight);
				text.draw(batch, "Уд.импульс: " + Math.rint(100.0F*InfoAndStats.UF_02.getRocketDetailImpulse())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.65F*upgradeWindowHeight);
				text.draw(batch, "Раб.время: " + Math.rint(100.0F*InfoAndStats.UF_02.getRocketDetailTime())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.5F*upgradeWindowHeight);
				text.draw(batch, "Цена: " + InfoAndStats.UF_02.getRocketDetailPrice(), upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.35F*upgradeWindowHeight);
				text.draw(batch, "Исследование займет " + duration + " дней", upgradeWindowX + 0.315F*upgradeWindowWidth, upgradeWindowY + 0.16F*upgradeWindowHeight);
			}
			if(controller.isOn(researchButton1X, researchButton1Y, researchButton1Width, researchButton1Height)){
				researchButtonSpriteActive.draw(batch);
			}else{
				researchButtonSpriteInactive.draw(batch);
			}
		}
	}
	private void drawMT_0112Upgrade(){
		backZSprite.draw(batch);
		upgradeWindowSprite.draw(batch);
		MT_0112Sprite.setBounds(upgradeWindowSprite.getX() + 0.005F*upgradeWindowSprite.getWidth(), upgradeWindowSprite.getY() + 0.55F*upgradeWindowSprite.getHeight() - MT_0112Height, 2.0F*MT_0112Width, 2.0F*MT_0112Height);
		MT_0112Sprite.draw(batch);
		//***\\
		int duration = (int)(InfoAndStats.MT_0112.getRocketDetailImpulse() + InfoAndStats.MT_0112.getRocketDetailThrust() + InfoAndStats.MT_0112.getRocketDetailTime()/5.0F + InfoAndStats.MT_0112.getRocketDetailPrice()/5.0F);
		text.draw(batch, InfoAndStats.MT_0112.getRocketDetailName(), upgradeWindowX + 0.425F*upgradeWindowWidth, upgradeWindowY + 0.95F*upgradeWindowHeight);
		if(!InfoAndStats.lngRussian){
			text.draw(batch, "Thrust: " + Math.rint(100.0F*InfoAndStats.MT_0112.getRocketDetailThrust())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.8F*upgradeWindowHeight);
			text.draw(batch, "Sp. impulse: " + Math.rint(100.0F*InfoAndStats.MT_0112.getRocketDetailImpulse())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.65F*upgradeWindowHeight);
			text.draw(batch, "Working time: " + Math.rint(100.0F*InfoAndStats.MT_0112.getRocketDetailTime())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.5F*upgradeWindowHeight);
			text.draw(batch, "Price: " + InfoAndStats.MT_0112.getRocketDetailPrice(), upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.35F*upgradeWindowHeight);
			text.draw(batch, "Upgrade will take " + duration + " days", upgradeWindowX + 0.325F*upgradeWindowWidth, upgradeWindowY + 0.165F*upgradeWindowHeight);
		}else{
			text.draw(batch, "Тяга: " + Math.rint(100.0F*InfoAndStats.MT_0112.getRocketDetailThrust())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.8F*upgradeWindowHeight);
			text.draw(batch, "Уд.импульс: " + Math.rint(100.0F*InfoAndStats.MT_0112.getRocketDetailImpulse())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.65F*upgradeWindowHeight);
			text.draw(batch, "Раб.время: " + Math.rint(100.0F*InfoAndStats.MT_0112.getRocketDetailTime())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.5F*upgradeWindowHeight);
			text.draw(batch, "Цена: " + InfoAndStats.MT_0112.getRocketDetailPrice(), upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.35F*upgradeWindowHeight);
			text.draw(batch, "Улучшение займет " + duration + " дней", upgradeWindowX + 0.315F*upgradeWindowWidth, upgradeWindowY + 0.16F*upgradeWindowHeight);
		}
		textSuc.draw(batch, firstNUM1 + "%", 1.075F*arrowSprite1.getX(), 1.1675F*arrowSprite1.getY());
		textFail.draw(batch, firstNUM2 + "%", 1.075F*arrowSprite1.getX(), 1.1F*arrowSprite1.getY());
		textSuc.draw(batch, secondNUM1 + "%", 1.075F*arrowSprite2.getX(), 1.205F*arrowSprite2.getY());
		textFail.draw(batch, secondNUM2 + "%", 1.075F*arrowSprite2.getX(), 1.125F*arrowSprite2.getY());
		textSuc.draw(batch,thirdNUM1 + "%", 1.075F*arrowSprite3.getX(), 1.265F*arrowSprite3.getY());
		textFail.draw(batch, thirdNUM2 + "%", 1.075F*arrowSprite3.getX(), 1.16F*arrowSprite3.getY());
		textSuc.draw(batch,fourthNUM1 + "%", 1.075F*arrowSprite4.getX(), 1.375F*arrowSprite4.getY());
		textFail.draw(batch, fourthNUM2 + "%", 1.075F*arrowSprite4.getX(), 1.225F*arrowSprite4.getY());
		//***\\
		text.draw(batch, Math.rint(100.0F*InfoAndStats.MT_0112.getRocketDetailThrust())/100.0F + " + " + Math.rint(100.0F*InfoAndStats.MT_0112.getRocketDetailThrust()*(Math.min(firstNUM1, firstNUM2)/100.0F))/100.0F, arrowSprite1.getX() + 1.01F*arrowSprite1.getWidth(), arrowSprite1.getY() + 0.85F*arrowSprite1.getHeight());
		text.draw(batch, Math.rint(100.0F*InfoAndStats.MT_0112.getRocketDetailThrust())/100.0F + " - " + Math.rint(100.0F*InfoAndStats.MT_0112.getRocketDetailThrust()*(Math.min(firstNUM1, firstNUM2)/100.0F))/100.0F, arrowSprite1.getX() + 1.01F*arrowSprite1.getWidth(), arrowSprite1.getY() + 0.35F*arrowSprite1.getHeight());
		text.draw(batch, Math.rint(100.0F*InfoAndStats.MT_0112.getRocketDetailImpulse())/100.0F + " + " + Math.rint(100.0F*InfoAndStats.MT_0112.getRocketDetailImpulse()*(Math.min(secondNUM1, secondNUM2)/100.0F))/100.0F, arrowSprite2.getX() + 1.01F*arrowSprite2.getWidth(), arrowSprite2.getY() + 0.85F*arrowSprite2.getHeight());
		text.draw(batch, Math.rint(100.0F*InfoAndStats.MT_0112.getRocketDetailImpulse())/100.0F + " - " + Math.rint(100.0F*InfoAndStats.MT_0112.getRocketDetailImpulse()*(Math.min(secondNUM1, secondNUM2)/100.0F))/100.0F, arrowSprite2.getX() + 1.01F*arrowSprite2.getWidth(), arrowSprite2.getY() + 0.35F*arrowSprite2.getHeight());
		text.draw(batch, Math.rint(100.0F*InfoAndStats.MT_0112.getRocketDetailTime())/100.0F + " + " + Math.rint(100.0F*InfoAndStats.MT_0112.getRocketDetailTime()*(Math.min(thirdNUM1, thirdNUM2)/100.0F))/100.0F, arrowSprite3.getX() + 1.01F*arrowSprite3.getWidth(), arrowSprite3.getY() + 0.85F*arrowSprite3.getHeight());
		text.draw(batch, Math.rint(100.0F*InfoAndStats.MT_0112.getRocketDetailTime())/100.0F + " - " + Math.rint(100.0F*InfoAndStats.MT_0112.getRocketDetailTime()*(Math.min(thirdNUM1, thirdNUM2)/100.0F))/100.0F, arrowSprite3.getX() + 1.01F*arrowSprite3.getWidth(), arrowSprite3.getY() + 0.35F*arrowSprite3.getHeight());
		text.draw(batch, InfoAndStats.MT_0112.getRocketDetailPrice() + " + " + (int)(InfoAndStats.MT_0112.getRocketDetailPrice()*(Math.min(fourthNUM1, fourthNUM2)/100.0F)), arrowSprite4.getX() + 1.01F*arrowSprite4.getWidth(), arrowSprite4.getY() + 0.85F*arrowSprite4.getHeight());
		text.draw(batch, InfoAndStats.MT_0112.getRocketDetailPrice() + " - " + (int)(InfoAndStats.MT_0112.getRocketDetailPrice()*(Math.min(fourthNUM1, fourthNUM2)/100.0F)), arrowSprite4.getX() + 1.01F*arrowSprite4.getWidth(), arrowSprite4.getY() + 0.35F*arrowSprite4.getHeight());
		//***\\
		arrowSprite1.draw(batch);
		arrowSprite2.draw(batch);
		arrowSprite3.draw(batch);
		arrowSprite4.draw(batch);
		//***\\
		if(controller.isOn(backUpgradeWindow1X, backUpgradeWindow1Y, backUpgradeWindow1Width, backUpgradeWindow1Height)){
			backUpgradeWindowSpriteActive.draw(batch);
		}else{
			backUpgradeWindowSpriteInactive.draw(batch);
		}
		if(controller.isOn(upgradeButton1X, upgradeButton1Y, upgradeButton1Width, upgradeButton1Height)){
			upgradeButtonSpriteActive.draw(batch);
		}else{
			upgradeButtonSpriteInactive.draw(batch);
		}
	}
	private void drawMM_4Upgrade(){
		backZSprite.draw(batch);
		upgradeWindowSprite.draw(batch);
		MM_4Sprite.setBounds(upgradeWindowSprite.getX() + 0.005F*upgradeWindowSprite.getWidth(), upgradeWindowSprite.getY() + 0.55F*upgradeWindowSprite.getHeight() - MM_4Height, 2.0F*MM_4Width, 2.0F*MM_4Height);
		MM_4Sprite.draw(batch);
		text.draw(batch, InfoAndStats.MM_4.getRocketDetailName(), upgradeWindowX + 0.425F*upgradeWindowWidth, upgradeWindowY + 0.95F*upgradeWindowHeight);
		if(controller.isOn(backUpgradeWindow1X, backUpgradeWindow1Y, backUpgradeWindow1Width, backUpgradeWindow1Height)){
			backUpgradeWindowSpriteActive.draw(batch);
		}else{
			backUpgradeWindowSpriteInactive.draw(batch);
		}
		int duration = (int)(InfoAndStats.MM_4.getRocketDetailImpulse() + InfoAndStats.MM_4.getRocketDetailThrust() + InfoAndStats.MM_4.getRocketDetailTime()/5.0F + InfoAndStats.MM_4.getRocketDetailPrice()/5.0F);
		if(InfoAndStats.MM_4.getRocketDetailAccessibility()){
			//***\\
			if(!InfoAndStats.lngRussian){
				text.draw(batch, "Thrust: " + Math.rint(100.0F*InfoAndStats.MM_4.getRocketDetailThrust())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.8F*upgradeWindowHeight);
				text.draw(batch, "Sp. impulse: " + Math.rint(100.0F*InfoAndStats.MM_4.getRocketDetailImpulse())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.65F*upgradeWindowHeight);
				text.draw(batch, "Working time: " + Math.rint(100.0F*InfoAndStats.MM_4.getRocketDetailTime())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.5F*upgradeWindowHeight);
				text.draw(batch, "Price: " + InfoAndStats.MM_4.getRocketDetailPrice(), upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.35F*upgradeWindowHeight);
				text.draw(batch, "Upgrade will take " + duration + " days", upgradeWindowX + 0.325F*upgradeWindowWidth, upgradeWindowY + 0.165F*upgradeWindowHeight);
			}else{
				text.draw(batch, "Тяга: " + Math.rint(100.0F*InfoAndStats.MM_4.getRocketDetailThrust())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.8F*upgradeWindowHeight);
				text.draw(batch, "Уд.импульс: " + Math.rint(100.0F*InfoAndStats.MM_4.getRocketDetailImpulse())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.65F*upgradeWindowHeight);
				text.draw(batch, "Раб.время: " + Math.rint(100.0F*InfoAndStats.MM_4.getRocketDetailTime())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.5F*upgradeWindowHeight);
				text.draw(batch, "Цена: " + InfoAndStats.MM_4.getRocketDetailPrice(), upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.35F*upgradeWindowHeight);
				text.draw(batch, "Улучшение займет " + duration + " дней", upgradeWindowX + 0.315F*upgradeWindowWidth, upgradeWindowY + 0.16F*upgradeWindowHeight);
			}
			textSuc.draw(batch, firstNUM1 + "%", 1.075F*arrowSprite1.getX(), 1.1675F*arrowSprite1.getY());
			textFail.draw(batch, firstNUM2 + "%", 1.075F*arrowSprite1.getX(), 1.1F*arrowSprite1.getY());
			textSuc.draw(batch, secondNUM1 + "%", 1.075F*arrowSprite2.getX(), 1.205F*arrowSprite2.getY());
			textFail.draw(batch, secondNUM2 + "%", 1.075F*arrowSprite2.getX(), 1.125F*arrowSprite2.getY());
			textSuc.draw(batch,thirdNUM1 + "%", 1.075F*arrowSprite3.getX(), 1.265F*arrowSprite3.getY());
			textFail.draw(batch, thirdNUM2 + "%", 1.075F*arrowSprite3.getX(), 1.16F*arrowSprite3.getY());
			textSuc.draw(batch,fourthNUM1 + "%", 1.075F*arrowSprite4.getX(), 1.375F*arrowSprite4.getY());
			textFail.draw(batch, fourthNUM2 + "%", 1.075F*arrowSprite4.getX(), 1.225F*arrowSprite4.getY());
			//***\\
			text.draw(batch, Math.rint(100.0F*InfoAndStats.MM_4.getRocketDetailThrust())/100.0F + " + " + Math.rint(100.0F*InfoAndStats.MM_4.getRocketDetailThrust()*(Math.min(firstNUM1, firstNUM2)/100.0F))/100.0F, arrowSprite1.getX() + 1.01F*arrowSprite1.getWidth(), arrowSprite1.getY() + 0.85F*arrowSprite1.getHeight());
			text.draw(batch, Math.rint(100.0F*InfoAndStats.MM_4.getRocketDetailThrust())/100.0F + " - " + Math.rint(100.0F*InfoAndStats.MM_4.getRocketDetailThrust()*(Math.min(firstNUM1, firstNUM2)/100.0F))/100.0F, arrowSprite1.getX() + 1.01F*arrowSprite1.getWidth(), arrowSprite1.getY() + 0.35F*arrowSprite1.getHeight());
			text.draw(batch, Math.rint(100.0F*InfoAndStats.MM_4.getRocketDetailImpulse())/100.0F + " + " + Math.rint(100.0F*InfoAndStats.MM_4.getRocketDetailImpulse()*(Math.min(secondNUM1, secondNUM2)/100.0F))/100.0F, arrowSprite2.getX() + 1.01F*arrowSprite2.getWidth(), arrowSprite2.getY() + 0.85F*arrowSprite2.getHeight());
			text.draw(batch, Math.rint(100.0F*InfoAndStats.MM_4.getRocketDetailImpulse())/100.0F + " - " + Math.rint(100.0F*InfoAndStats.MM_4.getRocketDetailImpulse()*(Math.min(secondNUM1, secondNUM2)/100.0F))/100.0F, arrowSprite2.getX() + 1.01F*arrowSprite2.getWidth(), arrowSprite2.getY() + 0.35F*arrowSprite2.getHeight());
			text.draw(batch, Math.rint(100.0F*InfoAndStats.MM_4.getRocketDetailTime())/100.0F + " + " + Math.rint(100.0F*InfoAndStats.MM_4.getRocketDetailTime()*(Math.min(thirdNUM1, thirdNUM2)/100.0F))/100.0F, arrowSprite3.getX() + 1.01F*arrowSprite3.getWidth(), arrowSprite3.getY() + 0.85F*arrowSprite3.getHeight());
			text.draw(batch, Math.rint(100.0F*InfoAndStats.MM_4.getRocketDetailTime())/100.0F + " - " + Math.rint(100.0F*InfoAndStats.MM_4.getRocketDetailTime()*(Math.min(thirdNUM1, thirdNUM2)/100.0F))/100.0F, arrowSprite3.getX() + 1.01F*arrowSprite3.getWidth(), arrowSprite3.getY() + 0.35F*arrowSprite3.getHeight());
			text.draw(batch, InfoAndStats.MM_4.getRocketDetailPrice() + " + " + (int)(InfoAndStats.MM_4.getRocketDetailPrice()*(Math.min(fourthNUM1, fourthNUM2)/100.0F)), arrowSprite4.getX() + 1.01F*arrowSprite4.getWidth(), arrowSprite4.getY() + 0.85F*arrowSprite4.getHeight());
			text.draw(batch, InfoAndStats.MM_4.getRocketDetailPrice() + " - " + (int)(InfoAndStats.MM_4.getRocketDetailPrice()*(Math.min(fourthNUM1, fourthNUM2)/100.0F)), arrowSprite4.getX() + 1.01F*arrowSprite4.getWidth(), arrowSprite4.getY() + 0.35F*arrowSprite4.getHeight());
			//***\\
			arrowSprite1.draw(batch);
			arrowSprite2.draw(batch);
			arrowSprite3.draw(batch);
			arrowSprite4.draw(batch);
			//***\\
			if(controller.isOn(upgradeButton1X, upgradeButton1Y, upgradeButton1Width, upgradeButton1Height)){
				upgradeButtonSpriteActive.draw(batch);
			}else{
				upgradeButtonSpriteInactive.draw(batch);
			}
		}else{
			if(!InfoAndStats.lngRussian){
				text.draw(batch, "Thrust: " + Math.rint(100.0F*InfoAndStats.MM_4.getRocketDetailThrust())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.8F*upgradeWindowHeight);
				text.draw(batch, "Sp. impulse: " + Math.rint(100.0F*InfoAndStats.MM_4.getRocketDetailImpulse())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.65F*upgradeWindowHeight);
				text.draw(batch, "Working time: " + Math.rint(100.0F*InfoAndStats.MM_4.getRocketDetailTime())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.5F*upgradeWindowHeight);
				text.draw(batch, "Price: " + InfoAndStats.MM_4.getRocketDetailPrice(), upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.35F*upgradeWindowHeight);
				text.draw(batch, "Research will take " + duration + " days", upgradeWindowX + 0.325F*upgradeWindowWidth, upgradeWindowY + 0.165F*upgradeWindowHeight);
			}else{
				text.draw(batch, "Тяга: " + Math.rint(100.0F*InfoAndStats.MM_4.getRocketDetailThrust())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.8F*upgradeWindowHeight);
				text.draw(batch, "Уд.импульс: " + Math.rint(100.0F*InfoAndStats.MM_4.getRocketDetailImpulse())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.65F*upgradeWindowHeight);
				text.draw(batch, "Раб.время: " + Math.rint(100.0F*InfoAndStats.MM_4.getRocketDetailTime())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.5F*upgradeWindowHeight);
				text.draw(batch, "Цена: " + InfoAndStats.MM_4.getRocketDetailPrice(), upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.35F*upgradeWindowHeight);
				text.draw(batch, "Исследование займет " + duration + " дней", upgradeWindowX + 0.315F*upgradeWindowWidth, upgradeWindowY + 0.16F*upgradeWindowHeight);
			}
			if(controller.isOn(researchButton1X, researchButton1Y, researchButton1Width, researchButton1Height)){
				researchButtonSpriteActive.draw(batch);
			}else{
				researchButtonSpriteInactive.draw(batch);
			}
		}
	}
	private void drawMF_043Upgrade(){
		backZSprite.draw(batch);
		upgradeWindowSprite.draw(batch);
		MF_043Sprite.setBounds(upgradeWindowSprite.getX() + 0.005F*upgradeWindowSprite.getWidth(), upgradeWindowSprite.getY() + 0.55F*upgradeWindowSprite.getHeight() - MF_043Height, 2.0F*MF_043Width, 2.0F*MF_043Height);
		MF_043Sprite.draw(batch);
		text.draw(batch, InfoAndStats.MF_043.getRocketDetailName(), upgradeWindowX + 0.425F*upgradeWindowWidth, upgradeWindowY + 0.95F*upgradeWindowHeight);
		if(controller.isOn(backUpgradeWindow1X, backUpgradeWindow1Y, backUpgradeWindow1Width, backUpgradeWindow1Height)){
			backUpgradeWindowSpriteActive.draw(batch);
		}else{
			backUpgradeWindowSpriteInactive.draw(batch);
		}
		int duration = (int)(InfoAndStats.MF_043.getRocketDetailImpulse() + InfoAndStats.MF_043.getRocketDetailThrust() + InfoAndStats.MF_043.getRocketDetailTime()/5.0F + InfoAndStats.MF_043.getRocketDetailPrice()/5.0F);
		if(InfoAndStats.MF_043.getRocketDetailAccessibility()){
			//***\\
			if(!InfoAndStats.lngRussian){
				text.draw(batch, "Thrust: " + Math.rint(100.0F*InfoAndStats.MF_043.getRocketDetailThrust())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.8F*upgradeWindowHeight);
				text.draw(batch, "Sp. impulse: " + Math.rint(100.0F*InfoAndStats.MF_043.getRocketDetailImpulse())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.65F*upgradeWindowHeight);
				text.draw(batch, "Working time: " + Math.rint(100.0F*InfoAndStats.MF_043.getRocketDetailTime())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.5F*upgradeWindowHeight);
				text.draw(batch, "Price: " + InfoAndStats.MF_043.getRocketDetailPrice(), upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.35F*upgradeWindowHeight);
				text.draw(batch, "Upgrade will take " + duration + " days", upgradeWindowX + 0.325F*upgradeWindowWidth, upgradeWindowY + 0.165F*upgradeWindowHeight);
			}else{
				text.draw(batch, "Тяга: " + Math.rint(100.0F*InfoAndStats.MF_043.getRocketDetailThrust())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.8F*upgradeWindowHeight);
				text.draw(batch, "Уд.импульс: " + Math.rint(100.0F*InfoAndStats.MF_043.getRocketDetailImpulse())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.65F*upgradeWindowHeight);
				text.draw(batch, "Раб.время: " + Math.rint(100.0F*InfoAndStats.MF_043.getRocketDetailTime())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.5F*upgradeWindowHeight);
				text.draw(batch, "Цена: " + InfoAndStats.MF_043.getRocketDetailPrice(), upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.35F*upgradeWindowHeight);
				text.draw(batch, "Улучшение займет " + duration + " дней", upgradeWindowX + 0.315F*upgradeWindowWidth, upgradeWindowY + 0.16F*upgradeWindowHeight);
			}
			textSuc.draw(batch, firstNUM1 + "%", 1.075F*arrowSprite1.getX(), 1.1675F*arrowSprite1.getY());
			textFail.draw(batch, firstNUM2 + "%", 1.075F*arrowSprite1.getX(), 1.1F*arrowSprite1.getY());
			textSuc.draw(batch, secondNUM1 + "%", 1.075F*arrowSprite2.getX(), 1.205F*arrowSprite2.getY());
			textFail.draw(batch, secondNUM2 + "%", 1.075F*arrowSprite2.getX(), 1.125F*arrowSprite2.getY());
			textSuc.draw(batch,thirdNUM1 + "%", 1.075F*arrowSprite3.getX(), 1.265F*arrowSprite3.getY());
			textFail.draw(batch, thirdNUM2 + "%", 1.075F*arrowSprite3.getX(), 1.16F*arrowSprite3.getY());
			textSuc.draw(batch,fourthNUM1 + "%", 1.075F*arrowSprite4.getX(), 1.375F*arrowSprite4.getY());
			textFail.draw(batch, fourthNUM2 + "%", 1.075F*arrowSprite4.getX(), 1.225F*arrowSprite4.getY());
			//***\\
			text.draw(batch, Math.rint(100.0F*InfoAndStats.MF_043.getRocketDetailThrust())/100.0F + " + " + Math.rint(100.0F*InfoAndStats.MF_043.getRocketDetailThrust()*(Math.min(firstNUM1, firstNUM2)/100.0F))/100.0F, arrowSprite1.getX() + 1.01F*arrowSprite1.getWidth(), arrowSprite1.getY() + 0.85F*arrowSprite1.getHeight());
			text.draw(batch, Math.rint(100.0F*InfoAndStats.MF_043.getRocketDetailThrust())/100.0F + " - " + Math.rint(100.0F*InfoAndStats.MF_043.getRocketDetailThrust()*(Math.min(firstNUM1, firstNUM2)/100.0F))/100.0F, arrowSprite1.getX() + 1.01F*arrowSprite1.getWidth(), arrowSprite1.getY() + 0.35F*arrowSprite1.getHeight());
			text.draw(batch, Math.rint(100.0F*InfoAndStats.MF_043.getRocketDetailImpulse())/100.0F + " + " + Math.rint(100.0F*InfoAndStats.MF_043.getRocketDetailImpulse()*(Math.min(secondNUM1, secondNUM2)/100.0F))/100.0F, arrowSprite2.getX() + 1.01F*arrowSprite2.getWidth(), arrowSprite2.getY() + 0.85F*arrowSprite2.getHeight());
			text.draw(batch, Math.rint(100.0F*InfoAndStats.MF_043.getRocketDetailImpulse())/100.0F + " - " + Math.rint(100.0F*InfoAndStats.MF_043.getRocketDetailImpulse()*(Math.min(secondNUM1, secondNUM2)/100.0F))/100.0F, arrowSprite2.getX() + 1.01F*arrowSprite2.getWidth(), arrowSprite2.getY() + 0.35F*arrowSprite2.getHeight());
			text.draw(batch, Math.rint(100.0F*InfoAndStats.MF_043.getRocketDetailTime())/100.0F + " + " + Math.rint(100.0F*InfoAndStats.MF_043.getRocketDetailTime()*(Math.min(thirdNUM1, thirdNUM2)/100.0F))/100.0F, arrowSprite3.getX() + 1.01F*arrowSprite3.getWidth(), arrowSprite3.getY() + 0.85F*arrowSprite3.getHeight());
			text.draw(batch, Math.rint(100.0F*InfoAndStats.MF_043.getRocketDetailTime())/100.0F + " - " + Math.rint(100.0F*InfoAndStats.MF_043.getRocketDetailTime()*(Math.min(thirdNUM1, thirdNUM2)/100.0F))/100.0F, arrowSprite3.getX() + 1.01F*arrowSprite3.getWidth(), arrowSprite3.getY() + 0.35F*arrowSprite3.getHeight());
			text.draw(batch, InfoAndStats.MF_043.getRocketDetailPrice() + " + " + (int)(InfoAndStats.MF_043.getRocketDetailPrice()*(Math.min(fourthNUM1, fourthNUM2)/100.0F)), arrowSprite4.getX() + 1.01F*arrowSprite4.getWidth(), arrowSprite4.getY() + 0.85F*arrowSprite4.getHeight());
			text.draw(batch, InfoAndStats.MF_043.getRocketDetailPrice() + " - " + (int)(InfoAndStats.MF_043.getRocketDetailPrice()*(Math.min(fourthNUM1, fourthNUM2)/100.0F)), arrowSprite4.getX() + 1.01F*arrowSprite4.getWidth(), arrowSprite4.getY() + 0.35F*arrowSprite4.getHeight());
			//***\\
			arrowSprite1.draw(batch);
			arrowSprite2.draw(batch);
			arrowSprite3.draw(batch);
			arrowSprite4.draw(batch);
			//***\\
			if(controller.isOn(upgradeButton1X, upgradeButton1Y, upgradeButton1Width, upgradeButton1Height)){
				upgradeButtonSpriteActive.draw(batch);
			}else{
				upgradeButtonSpriteInactive.draw(batch);
			}
		}else{
			if(!InfoAndStats.lngRussian){
				text.draw(batch, "Thrust: " + Math.rint(100.0F*InfoAndStats.MF_043.getRocketDetailThrust())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.8F*upgradeWindowHeight);
				text.draw(batch, "Sp. impulse: " + Math.rint(100.0F*InfoAndStats.MF_043.getRocketDetailImpulse())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.65F*upgradeWindowHeight);
				text.draw(batch, "Working time: " + Math.rint(100.0F*InfoAndStats.MF_043.getRocketDetailTime())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.5F*upgradeWindowHeight);
				text.draw(batch, "Price: " + InfoAndStats.MF_043.getRocketDetailPrice(), upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.35F*upgradeWindowHeight);
				text.draw(batch, "Research will take " + duration + " days", upgradeWindowX + 0.325F*upgradeWindowWidth, upgradeWindowY + 0.165F*upgradeWindowHeight);
			}else{
				text.draw(batch, "Тяга: " + Math.rint(100.0F*InfoAndStats.MF_043.getRocketDetailThrust())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.8F*upgradeWindowHeight);
				text.draw(batch, "Уд.импульс: " + Math.rint(100.0F*InfoAndStats.MF_043.getRocketDetailImpulse())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.65F*upgradeWindowHeight);
				text.draw(batch, "Раб.время: " + Math.rint(100.0F*InfoAndStats.MF_043.getRocketDetailTime())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.5F*upgradeWindowHeight);
				text.draw(batch, "Цена: " + InfoAndStats.MF_043.getRocketDetailPrice(), upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.35F*upgradeWindowHeight);
				text.draw(batch, "Исследование займет " + duration + " дней", upgradeWindowX + 0.315F*upgradeWindowWidth, upgradeWindowY + 0.16F*upgradeWindowHeight);
			}
			if(controller.isOn(researchButton1X, researchButton1Y, researchButton1Width, researchButton1Height)){
				researchButtonSpriteActive.draw(batch);
			}else{
				researchButtonSpriteInactive.draw(batch);
			}
		}
	}
	private void drawLT_116Upgrade(){
		backZSprite.draw(batch);
		upgradeWindowSprite.draw(batch);
		LT_116Sprite.setBounds(upgradeWindowSprite.getX() + 0.005F*upgradeWindowSprite.getWidth(), upgradeWindowSprite.getY() + 0.55F*upgradeWindowSprite.getHeight() - LT_116Height, 2.0F*LT_116Width, 2.0F*LT_116Height);
		LT_116Sprite.draw(batch);
		//***\\
		int duration = (int)(InfoAndStats.LT_116.getRocketDetailImpulse() + InfoAndStats.LT_116.getRocketDetailThrust() + InfoAndStats.LT_116.getRocketDetailTime()/5.0F + InfoAndStats.LT_116.getRocketDetailPrice()/5.0F);
		text.draw(batch, InfoAndStats.LT_116.getRocketDetailName(), upgradeWindowX + 0.425F*upgradeWindowWidth, upgradeWindowY + 0.95F*upgradeWindowHeight);
		if(!InfoAndStats.lngRussian){
			text.draw(batch, "Thrust: " + Math.rint(100.0F*InfoAndStats.LT_116.getRocketDetailThrust())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.8F*upgradeWindowHeight);
			text.draw(batch, "Sp. impulse: " + Math.rint(100.0F*InfoAndStats.LT_116.getRocketDetailImpulse())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.65F*upgradeWindowHeight);
			text.draw(batch, "Working time: " + Math.rint(100.0F*InfoAndStats.LT_116.getRocketDetailTime())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.5F*upgradeWindowHeight);
			text.draw(batch, "Price: " + InfoAndStats.LT_116.getRocketDetailPrice(), upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.35F*upgradeWindowHeight);
			text.draw(batch, "Upgrade will take " + duration + " days", upgradeWindowX + 0.325F*upgradeWindowWidth, upgradeWindowY + 0.165F*upgradeWindowHeight);
		}else{
			text.draw(batch, "Тяга: " + Math.rint(100.0F*InfoAndStats.LT_116.getRocketDetailThrust())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.8F*upgradeWindowHeight);
			text.draw(batch, "Уд.импульс: " + Math.rint(100.0F*InfoAndStats.LT_116.getRocketDetailImpulse())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.65F*upgradeWindowHeight);
			text.draw(batch, "Раб.время: " + Math.rint(100.0F*InfoAndStats.LT_116.getRocketDetailTime())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.5F*upgradeWindowHeight);
			text.draw(batch, "Цена: " + InfoAndStats.LT_116.getRocketDetailPrice(), upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.35F*upgradeWindowHeight);
			text.draw(batch, "Улучшение займет " + duration + " дней", upgradeWindowX + 0.315F*upgradeWindowWidth, upgradeWindowY + 0.16F*upgradeWindowHeight);
		}
		textSuc.draw(batch, firstNUM1 + "%", 1.075F*arrowSprite1.getX(), 1.1675F*arrowSprite1.getY());
		textFail.draw(batch, firstNUM2 + "%", 1.075F*arrowSprite1.getX(), 1.1F*arrowSprite1.getY());
		textSuc.draw(batch, secondNUM1 + "%", 1.075F*arrowSprite2.getX(), 1.205F*arrowSprite2.getY());
		textFail.draw(batch, secondNUM2 + "%", 1.075F*arrowSprite2.getX(), 1.125F*arrowSprite2.getY());
		textSuc.draw(batch,thirdNUM1 + "%", 1.075F*arrowSprite3.getX(), 1.265F*arrowSprite3.getY());
		textFail.draw(batch, thirdNUM2 + "%", 1.075F*arrowSprite3.getX(), 1.16F*arrowSprite3.getY());
		textSuc.draw(batch,fourthNUM1 + "%", 1.075F*arrowSprite4.getX(), 1.375F*arrowSprite4.getY());
		textFail.draw(batch, fourthNUM2 + "%", 1.075F*arrowSprite4.getX(), 1.225F*arrowSprite4.getY());
		//***\\
		text.draw(batch, Math.rint(100.0F*InfoAndStats.LT_116.getRocketDetailThrust())/100.0F + " + " + Math.rint(100.0F*InfoAndStats.LT_116.getRocketDetailThrust()*(Math.min(firstNUM1, firstNUM2)/100.0F))/100.0F, arrowSprite1.getX() + 1.01F*arrowSprite1.getWidth(), arrowSprite1.getY() + 0.85F*arrowSprite1.getHeight());
		text.draw(batch, Math.rint(100.0F*InfoAndStats.LT_116.getRocketDetailThrust())/100.0F + " - " + Math.rint(100.0F*InfoAndStats.LT_116.getRocketDetailThrust()*(Math.min(firstNUM1, firstNUM2)/100.0F))/100.0F, arrowSprite1.getX() + 1.01F*arrowSprite1.getWidth(), arrowSprite1.getY() + 0.35F*arrowSprite1.getHeight());
		text.draw(batch, Math.rint(100.0F*InfoAndStats.LT_116.getRocketDetailImpulse())/100.0F + " + " + Math.rint(100.0F*InfoAndStats.LT_116.getRocketDetailImpulse()*(Math.min(secondNUM1, secondNUM2)/100.0F))/100.0F, arrowSprite2.getX() + 1.01F*arrowSprite2.getWidth(), arrowSprite2.getY() + 0.85F*arrowSprite2.getHeight());
		text.draw(batch, Math.rint(100.0F*InfoAndStats.LT_116.getRocketDetailImpulse())/100.0F + " - " + Math.rint(100.0F*InfoAndStats.LT_116.getRocketDetailImpulse()*(Math.min(secondNUM1, secondNUM2)/100.0F))/100.0F, arrowSprite2.getX() + 1.01F*arrowSprite2.getWidth(), arrowSprite2.getY() + 0.35F*arrowSprite2.getHeight());
		text.draw(batch, Math.rint(100.0F*InfoAndStats.LT_116.getRocketDetailTime())/100.0F + " + " + Math.rint(100.0F*InfoAndStats.LT_116.getRocketDetailTime()*(Math.min(thirdNUM1, thirdNUM2)/100.0F))/100.0F, arrowSprite3.getX() + 1.01F*arrowSprite3.getWidth(), arrowSprite3.getY() + 0.85F*arrowSprite3.getHeight());
		text.draw(batch, Math.rint(100.0F*InfoAndStats.LT_116.getRocketDetailTime())/100.0F + " - " + Math.rint(100.0F*InfoAndStats.LT_116.getRocketDetailTime()*(Math.min(thirdNUM1, thirdNUM2)/100.0F))/100.0F, arrowSprite3.getX() + 1.01F*arrowSprite3.getWidth(), arrowSprite3.getY() + 0.35F*arrowSprite3.getHeight());
		text.draw(batch, InfoAndStats.LT_116.getRocketDetailPrice() + " + " + (int)(InfoAndStats.LT_116.getRocketDetailPrice()*(Math.min(fourthNUM1, fourthNUM2)/100.0F)), arrowSprite4.getX() + 1.01F*arrowSprite4.getWidth(), arrowSprite4.getY() + 0.85F*arrowSprite4.getHeight());
		text.draw(batch, InfoAndStats.LT_116.getRocketDetailPrice() + " - " + (int)(InfoAndStats.LT_116.getRocketDetailPrice()*(Math.min(fourthNUM1, fourthNUM2)/100.0F)), arrowSprite4.getX() + 1.01F*arrowSprite4.getWidth(), arrowSprite4.getY() + 0.35F*arrowSprite4.getHeight());
		//***\\
		arrowSprite1.draw(batch);
		arrowSprite2.draw(batch);
		arrowSprite3.draw(batch);
		arrowSprite4.draw(batch);
		//***\\
		if(controller.isOn(backUpgradeWindow1X, backUpgradeWindow1Y, backUpgradeWindow1Width, backUpgradeWindow1Height)){
			backUpgradeWindowSpriteActive.draw(batch);
		}else{
			backUpgradeWindowSpriteInactive.draw(batch);
		}
		if(controller.isOn(upgradeButton1X, upgradeButton1Y, upgradeButton1Width, upgradeButton1Height)){
			upgradeButtonSpriteActive.draw(batch);
		}else{
			upgradeButtonSpriteInactive.draw(batch);
		}
	}
	private void drawLM_087Upgrade(){
		backZSprite.draw(batch);
		upgradeWindowSprite.draw(batch);
		LM_087Sprite.setBounds(upgradeWindowSprite.getX() + 0.005F*upgradeWindowSprite.getWidth(), upgradeWindowSprite.getY() + 0.55F*upgradeWindowSprite.getHeight() - LM_087Height, 2.0F*LM_087Width, 2.0F*LM_087Height);
		LM_087Sprite.draw(batch);
		text.draw(batch, InfoAndStats.LM_087.getRocketDetailName(), upgradeWindowX + 0.425F*upgradeWindowWidth, upgradeWindowY + 0.95F*upgradeWindowHeight);
		if(controller.isOn(backUpgradeWindow1X, backUpgradeWindow1Y, backUpgradeWindow1Width, backUpgradeWindow1Height)){
			backUpgradeWindowSpriteActive.draw(batch);
		}else{
			backUpgradeWindowSpriteInactive.draw(batch);
		}
		int duration = (int)(InfoAndStats.LM_087.getRocketDetailImpulse() + InfoAndStats.LM_087.getRocketDetailThrust() + InfoAndStats.LM_087.getRocketDetailTime()/5.0F + InfoAndStats.LM_087.getRocketDetailPrice()/5.0F);
		if(InfoAndStats.LM_087.getRocketDetailAccessibility()){
			//***\\
			if(!InfoAndStats.lngRussian){
				text.draw(batch, "Thrust: " + Math.rint(100.0F*InfoAndStats.LM_087.getRocketDetailThrust())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.8F*upgradeWindowHeight);
				text.draw(batch, "Sp. impulse: " + Math.rint(100.0F*InfoAndStats.LM_087.getRocketDetailImpulse())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.65F*upgradeWindowHeight);
				text.draw(batch, "Working time: " + Math.rint(100.0F*InfoAndStats.LM_087.getRocketDetailTime())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.5F*upgradeWindowHeight);
				text.draw(batch, "Price: " + InfoAndStats.LM_087.getRocketDetailPrice(), upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.35F*upgradeWindowHeight);
				text.draw(batch, "Upgrade will take " + duration + " days", upgradeWindowX + 0.325F*upgradeWindowWidth, upgradeWindowY + 0.165F*upgradeWindowHeight);
			}else{
				text.draw(batch, "Тяга: " + Math.rint(100.0F*InfoAndStats.LM_087.getRocketDetailThrust())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.8F*upgradeWindowHeight);
				text.draw(batch, "Уд.импульс: " + Math.rint(100.0F*InfoAndStats.LM_087.getRocketDetailImpulse())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.65F*upgradeWindowHeight);
				text.draw(batch, "Раб.время: " + Math.rint(100.0F*InfoAndStats.LM_087.getRocketDetailTime())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.5F*upgradeWindowHeight);
				text.draw(batch, "Цена: " + InfoAndStats.LM_087.getRocketDetailPrice(), upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.35F*upgradeWindowHeight);
				text.draw(batch, "Улучшение займет " + duration + " дней", upgradeWindowX + 0.315F*upgradeWindowWidth, upgradeWindowY + 0.16F*upgradeWindowHeight);
			}
			textSuc.draw(batch, firstNUM1 + "%", 1.075F*arrowSprite1.getX(), 1.1675F*arrowSprite1.getY());
			textFail.draw(batch, firstNUM2 + "%", 1.075F*arrowSprite1.getX(), 1.1F*arrowSprite1.getY());
			textSuc.draw(batch, secondNUM1 + "%", 1.075F*arrowSprite2.getX(), 1.205F*arrowSprite2.getY());
			textFail.draw(batch, secondNUM2 + "%", 1.075F*arrowSprite2.getX(), 1.125F*arrowSprite2.getY());
			textSuc.draw(batch,thirdNUM1 + "%", 1.075F*arrowSprite3.getX(), 1.265F*arrowSprite3.getY());
			textFail.draw(batch, thirdNUM2 + "%", 1.075F*arrowSprite3.getX(), 1.16F*arrowSprite3.getY());
			textSuc.draw(batch,fourthNUM1 + "%", 1.075F*arrowSprite4.getX(), 1.375F*arrowSprite4.getY());
			textFail.draw(batch, fourthNUM2 + "%", 1.075F*arrowSprite4.getX(), 1.225F*arrowSprite4.getY());
			//***\\
			text.draw(batch, Math.rint(100.0F*InfoAndStats.LM_087.getRocketDetailThrust())/100.0F + " + " + Math.rint(100.0F*InfoAndStats.LM_087.getRocketDetailThrust()*(Math.min(firstNUM1, firstNUM2)/100.0F))/100.0F, arrowSprite1.getX() + 1.01F*arrowSprite1.getWidth(), arrowSprite1.getY() + 0.85F*arrowSprite1.getHeight());
			text.draw(batch, Math.rint(100.0F*InfoAndStats.LM_087.getRocketDetailThrust())/100.0F + " - " + Math.rint(100.0F*InfoAndStats.LM_087.getRocketDetailThrust()*(Math.min(firstNUM1, firstNUM2)/100.0F))/100.0F, arrowSprite1.getX() + 1.01F*arrowSprite1.getWidth(), arrowSprite1.getY() + 0.35F*arrowSprite1.getHeight());
			text.draw(batch, Math.rint(100.0F*InfoAndStats.LM_087.getRocketDetailImpulse())/100.0F + " + " + Math.rint(100.0F*InfoAndStats.LM_087.getRocketDetailImpulse()*(Math.min(secondNUM1, secondNUM2)/100.0F))/100.0F, arrowSprite2.getX() + 1.01F*arrowSprite2.getWidth(), arrowSprite2.getY() + 0.85F*arrowSprite2.getHeight());
			text.draw(batch, Math.rint(100.0F*InfoAndStats.LM_087.getRocketDetailImpulse())/100.0F + " - " + Math.rint(100.0F*InfoAndStats.LM_087.getRocketDetailImpulse()*(Math.min(secondNUM1, secondNUM2)/100.0F))/100.0F, arrowSprite2.getX() + 1.01F*arrowSprite2.getWidth(), arrowSprite2.getY() + 0.35F*arrowSprite2.getHeight());
			text.draw(batch, Math.rint(100.0F*InfoAndStats.LM_087.getRocketDetailTime())/100.0F + " + " + Math.rint(100.0F*InfoAndStats.LM_087.getRocketDetailTime()*(Math.min(thirdNUM1, thirdNUM2)/100.0F))/100.0F, arrowSprite3.getX() + 1.01F*arrowSprite3.getWidth(), arrowSprite3.getY() + 0.85F*arrowSprite3.getHeight());
			text.draw(batch, Math.rint(100.0F*InfoAndStats.LM_087.getRocketDetailTime())/100.0F + " - " + Math.rint(100.0F*InfoAndStats.LM_087.getRocketDetailTime()*(Math.min(thirdNUM1, thirdNUM2)/100.0F))/100.0F, arrowSprite3.getX() + 1.01F*arrowSprite3.getWidth(), arrowSprite3.getY() + 0.35F*arrowSprite3.getHeight());
			text.draw(batch, InfoAndStats.LM_087.getRocketDetailPrice() + " + " + (int)(InfoAndStats.LM_087.getRocketDetailPrice()*(Math.min(fourthNUM1, fourthNUM2)/100.0F)), arrowSprite4.getX() + 1.01F*arrowSprite4.getWidth(), arrowSprite4.getY() + 0.85F*arrowSprite4.getHeight());
			text.draw(batch, InfoAndStats.LM_087.getRocketDetailPrice() + " - " + (int)(InfoAndStats.LM_087.getRocketDetailPrice()*(Math.min(fourthNUM1, fourthNUM2)/100.0F)), arrowSprite4.getX() + 1.01F*arrowSprite4.getWidth(), arrowSprite4.getY() + 0.35F*arrowSprite4.getHeight());
			//***\\
			arrowSprite1.draw(batch);
			arrowSprite2.draw(batch);
			arrowSprite3.draw(batch);
			arrowSprite4.draw(batch);
			//***\\
			if(controller.isOn(upgradeButton1X, upgradeButton1Y, upgradeButton1Width, upgradeButton1Height)){
				upgradeButtonSpriteActive.draw(batch);
			}else{
				upgradeButtonSpriteInactive.draw(batch);
			}
		}else{
			if(!InfoAndStats.lngRussian){
				text.draw(batch, "Thrust: " + Math.rint(100.0F*InfoAndStats.LM_087.getRocketDetailThrust())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.8F*upgradeWindowHeight);
				text.draw(batch, "Sp. impulse: " + Math.rint(100.0F*InfoAndStats.LM_087.getRocketDetailImpulse())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.65F*upgradeWindowHeight);
				text.draw(batch, "Working time: " + Math.rint(100.0F*InfoAndStats.LM_087.getRocketDetailTime())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.5F*upgradeWindowHeight);
				text.draw(batch, "Price: " + InfoAndStats.LM_087.getRocketDetailPrice(), upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.35F*upgradeWindowHeight);
				text.draw(batch, "Research will take " + duration + " days", upgradeWindowX + 0.325F*upgradeWindowWidth, upgradeWindowY + 0.165F*upgradeWindowHeight);
			}else{
				text.draw(batch, "Тяга: " + Math.rint(100.0F*InfoAndStats.LM_087.getRocketDetailThrust())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.8F*upgradeWindowHeight);
				text.draw(batch, "Уд.импульс: " + Math.rint(100.0F*InfoAndStats.LM_087.getRocketDetailImpulse())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.65F*upgradeWindowHeight);
				text.draw(batch, "Раб.время: " + Math.rint(100.0F*InfoAndStats.LM_087.getRocketDetailTime())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.5F*upgradeWindowHeight);
				text.draw(batch, "Цена: " + InfoAndStats.LM_087.getRocketDetailPrice(), upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.35F*upgradeWindowHeight);
				text.draw(batch, "Исследование займет " + duration + " дней", upgradeWindowX + 0.315F*upgradeWindowWidth, upgradeWindowY + 0.16F*upgradeWindowHeight);
			}
			if(controller.isOn(researchButton1X, researchButton1Y, researchButton1Width, researchButton1Height)){
				researchButtonSpriteActive.draw(batch);
			}else{
				researchButtonSpriteInactive.draw(batch);
			}
		}
	}
	private void drawLF_15Upgrade(){
		backZSprite.draw(batch);
		upgradeWindowSprite.draw(batch);
		LF_15Sprite.setBounds(upgradeWindowSprite.getX() + 0.005F*upgradeWindowSprite.getWidth(), upgradeWindowSprite.getY() + 0.55F*upgradeWindowSprite.getHeight() - LF_15Height, 2.0F*LF_15Width, 2.0F*LF_15Height);
		LF_15Sprite.draw(batch);
		text.draw(batch, InfoAndStats.LF_15.getRocketDetailName(), upgradeWindowX + 0.425F*upgradeWindowWidth, upgradeWindowY + 0.95F*upgradeWindowHeight);
		if(controller.isOn(backUpgradeWindow1X, backUpgradeWindow1Y, backUpgradeWindow1Width, backUpgradeWindow1Height)){
			backUpgradeWindowSpriteActive.draw(batch);
		}else{
			backUpgradeWindowSpriteInactive.draw(batch);
		}
		int duration = (int)(InfoAndStats.LF_15.getRocketDetailImpulse() + InfoAndStats.LF_15.getRocketDetailThrust() + InfoAndStats.LF_15.getRocketDetailTime()/5.0F + InfoAndStats.LF_15.getRocketDetailPrice()/5.0F);
		if(InfoAndStats.LF_15.getRocketDetailAccessibility()){
			//***\\
			if(!InfoAndStats.lngRussian){
				text.draw(batch, "Thrust: " + Math.rint(100.0F*InfoAndStats.LF_15.getRocketDetailThrust())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.8F*upgradeWindowHeight);
				text.draw(batch, "Sp. impulse: " + Math.rint(100.0F*InfoAndStats.LF_15.getRocketDetailImpulse())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.65F*upgradeWindowHeight);
				text.draw(batch, "Working time: " + Math.rint(100.0F*InfoAndStats.LF_15.getRocketDetailTime())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.5F*upgradeWindowHeight);
				text.draw(batch, "Price: " + InfoAndStats.LF_15.getRocketDetailPrice(), upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.35F*upgradeWindowHeight);
				text.draw(batch, "Upgrade will take " + duration + " days", upgradeWindowX + 0.325F*upgradeWindowWidth, upgradeWindowY + 0.165F*upgradeWindowHeight);
			}else{
				text.draw(batch, "Тяга: " + Math.rint(100.0F*InfoAndStats.LF_15.getRocketDetailThrust())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.8F*upgradeWindowHeight);
				text.draw(batch, "Уд.импульс: " + Math.rint(100.0F*InfoAndStats.LF_15.getRocketDetailImpulse())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.65F*upgradeWindowHeight);
				text.draw(batch, "Раб.время: " + Math.rint(100.0F*InfoAndStats.LF_15.getRocketDetailTime())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.5F*upgradeWindowHeight);
				text.draw(batch, "Цена: " + InfoAndStats.LF_15.getRocketDetailPrice(), upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.35F*upgradeWindowHeight);
				text.draw(batch, "Улучшение займет " + duration + " дней", upgradeWindowX + 0.315F*upgradeWindowWidth, upgradeWindowY + 0.16F*upgradeWindowHeight);
			}
			textSuc.draw(batch, firstNUM1 + "%", 1.075F*arrowSprite1.getX(), 1.1675F*arrowSprite1.getY());
			textFail.draw(batch, firstNUM2 + "%", 1.075F*arrowSprite1.getX(), 1.1F*arrowSprite1.getY());
			textSuc.draw(batch, secondNUM1 + "%", 1.075F*arrowSprite2.getX(), 1.205F*arrowSprite2.getY());
			textFail.draw(batch, secondNUM2 + "%", 1.075F*arrowSprite2.getX(), 1.125F*arrowSprite2.getY());
			textSuc.draw(batch,thirdNUM1 + "%", 1.075F*arrowSprite3.getX(), 1.265F*arrowSprite3.getY());
			textFail.draw(batch, thirdNUM2 + "%", 1.075F*arrowSprite3.getX(), 1.16F*arrowSprite3.getY());
			textSuc.draw(batch,fourthNUM1 + "%", 1.075F*arrowSprite4.getX(), 1.375F*arrowSprite4.getY());
			textFail.draw(batch, fourthNUM2 + "%", 1.075F*arrowSprite4.getX(), 1.225F*arrowSprite4.getY());
			//***\\
			text.draw(batch, Math.rint(100.0F*InfoAndStats.LF_15.getRocketDetailThrust())/100.0F + " + " + Math.rint(100.0F*InfoAndStats.LF_15.getRocketDetailThrust()*(Math.min(firstNUM1, firstNUM2)/100.0F))/100.0F, arrowSprite1.getX() + 1.01F*arrowSprite1.getWidth(), arrowSprite1.getY() + 0.85F*arrowSprite1.getHeight());
			text.draw(batch, Math.rint(100.0F*InfoAndStats.LF_15.getRocketDetailThrust())/100.0F + " - " + Math.rint(100.0F*InfoAndStats.LF_15.getRocketDetailThrust()*(Math.min(firstNUM1, firstNUM2)/100.0F))/100.0F, arrowSprite1.getX() + 1.01F*arrowSprite1.getWidth(), arrowSprite1.getY() + 0.35F*arrowSprite1.getHeight());
			text.draw(batch, Math.rint(100.0F*InfoAndStats.LF_15.getRocketDetailImpulse())/100.0F + " + " + Math.rint(100.0F*InfoAndStats.LF_15.getRocketDetailImpulse()*(Math.min(secondNUM1, secondNUM2)/100.0F))/100.0F, arrowSprite2.getX() + 1.01F*arrowSprite2.getWidth(), arrowSprite2.getY() + 0.85F*arrowSprite2.getHeight());
			text.draw(batch, Math.rint(100.0F*InfoAndStats.LF_15.getRocketDetailImpulse())/100.0F + " - " + Math.rint(100.0F*InfoAndStats.LF_15.getRocketDetailImpulse()*(Math.min(secondNUM1, secondNUM2)/100.0F))/100.0F, arrowSprite2.getX() + 1.01F*arrowSprite2.getWidth(), arrowSprite2.getY() + 0.35F*arrowSprite2.getHeight());
			text.draw(batch, Math.rint(100.0F*InfoAndStats.LF_15.getRocketDetailTime())/100.0F + " + " + Math.rint(100.0F*InfoAndStats.LF_15.getRocketDetailTime()*(Math.min(thirdNUM1, thirdNUM2)/100.0F))/100.0F, arrowSprite3.getX() + 1.01F*arrowSprite3.getWidth(), arrowSprite3.getY() + 0.85F*arrowSprite3.getHeight());
			text.draw(batch, Math.rint(100.0F*InfoAndStats.LF_15.getRocketDetailTime())/100.0F + " - " + Math.rint(100.0F*InfoAndStats.LF_15.getRocketDetailTime()*(Math.min(thirdNUM1, thirdNUM2)/100.0F))/100.0F, arrowSprite3.getX() + 1.01F*arrowSprite3.getWidth(), arrowSprite3.getY() + 0.35F*arrowSprite3.getHeight());
			text.draw(batch, InfoAndStats.LF_15.getRocketDetailPrice() + " + " + (int)(InfoAndStats.LF_15.getRocketDetailPrice()*(Math.min(fourthNUM1, fourthNUM2)/100.0F)), arrowSprite4.getX() + 1.01F*arrowSprite4.getWidth(), arrowSprite4.getY() + 0.85F*arrowSprite4.getHeight());
			text.draw(batch, InfoAndStats.LF_15.getRocketDetailPrice() + " - " + (int)(InfoAndStats.LF_15.getRocketDetailPrice()*(Math.min(fourthNUM1, fourthNUM2)/100.0F)), arrowSprite4.getX() + 1.01F*arrowSprite4.getWidth(), arrowSprite4.getY() + 0.35F*arrowSprite4.getHeight());
			//***\\
			arrowSprite1.draw(batch);
			arrowSprite2.draw(batch);
			arrowSprite3.draw(batch);
			arrowSprite4.draw(batch);
			//***\\
			if(controller.isOn(upgradeButton1X, upgradeButton1Y, upgradeButton1Width, upgradeButton1Height)){
				upgradeButtonSpriteActive.draw(batch);
			}else{
				upgradeButtonSpriteInactive.draw(batch);
			}
		}else{
			if(!InfoAndStats.lngRussian){
				text.draw(batch, "Thrust: " + Math.rint(100.0F*InfoAndStats.LF_15.getRocketDetailThrust())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.8F*upgradeWindowHeight);
				text.draw(batch, "Sp. impulse: " + Math.rint(100.0F*InfoAndStats.LF_15.getRocketDetailImpulse())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.65F*upgradeWindowHeight);
				text.draw(batch, "Working time: " + Math.rint(100.0F*InfoAndStats.LF_15.getRocketDetailTime())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.5F*upgradeWindowHeight);
				text.draw(batch, "Price: " + InfoAndStats.LF_15.getRocketDetailPrice(), upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.35F*upgradeWindowHeight);
				text.draw(batch, "Research will take " + duration + " days", upgradeWindowX + 0.325F*upgradeWindowWidth, upgradeWindowY + 0.165F*upgradeWindowHeight);
			}else{
				text.draw(batch, "Тяга: " + Math.rint(100.0F*InfoAndStats.LF_15.getRocketDetailThrust())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.8F*upgradeWindowHeight);
				text.draw(batch, "Уд.импульс: " + Math.rint(100.0F*InfoAndStats.LF_15.getRocketDetailImpulse())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.65F*upgradeWindowHeight);
				text.draw(batch, "Раб.время: " + Math.rint(100.0F*InfoAndStats.LF_15.getRocketDetailTime())/100.0F, upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.5F*upgradeWindowHeight);
				text.draw(batch, "Цена: " + InfoAndStats.LF_15.getRocketDetailPrice(), upgradeWindowX + 0.17F*upgradeWindowWidth, upgradeWindowY + 0.35F*upgradeWindowHeight);
				text.draw(batch, "Исследование займет " + duration + " дней", upgradeWindowX + 0.315F*upgradeWindowWidth, upgradeWindowY + 0.16F*upgradeWindowHeight);
			}
			if(controller.isOn(researchButton1X, researchButton1Y, researchButton1Width, researchButton1Height)){
				researchButtonSpriteActive.draw(batch);
			}else{
				researchButtonSpriteInactive.draw(batch);
			}
		}
	}
	
	private void btnListeners(){
		if(!isUpgradeWindowDraw){
			if(controller.isClicked(backButtonX, backButtonY, backButtonWidth, backButtonHeight)){
				game.setScreen(new GameScreen(game));
				this.dispose();
			}
			if(controller.isClicked(upgrade1X, upgrade1Y, upgrade1Width, upgrade1Height)){
				isUpgradeWindowDraw = true;
			}
		}else{
			if(!isUT_1202Selected && !isUM_1034Selected && !isUF_02Selected && !isMT_0112Selected && !isMM_4Selected && !isMF_043Selected && !isLT_116Selected && !isLM_087Selected && !isLF_15Selected){
				if(controller.isClicked(backUpgradeWindow1X, backUpgradeWindow1Y, backUpgradeWindow1Width, backUpgradeWindow1Height)){
					isUpgradeWindowDraw = false;
				}
				if(controller.isClicked(UT_1202X, UT_1202Y, UT_1202Width, UT_1202Height) && !InfoAndStats.hasTask){
					isUT_1202Selected = true;
				}
				if(controller.isClicked(UM_1034X, UM_1034Y, UM_1034Width, UM_1034Height) && !InfoAndStats.hasTask){
					isUM_1034Selected = true;
				}
				if(controller.isClicked(UF_02X, UF_02Y, UF_02Width, UF_02Height) && !InfoAndStats.hasTask){
					isUF_02Selected = true;
				}
				if(controller.isClicked(MT_0112X, MT_0112Y, MT_0112Width, MT_0112Height) && !InfoAndStats.hasTask){
					isMT_0112Selected = true;
				}
				if(controller.isClicked(MM_4X, MM_4Y, MM_4Width, MM_4Height) && !InfoAndStats.hasTask){
					isMM_4Selected = true;
				}
				if(controller.isClicked(MF_043X, MF_043Y, MF_043Width, MF_043Height) && !InfoAndStats.hasTask){
					isMF_043Selected = true;
				}
				if(controller.isClicked(LT_116X, LT_116Y, LT_116Width, LT_116Height) && !InfoAndStats.hasTask){
					isLT_116Selected = true;
				}
				if(controller.isClicked(LM_087X, LM_087Y, LM_087Width, LM_087Height) && !InfoAndStats.hasTask){
					isLM_087Selected = true;
				}
				if(controller.isClicked(LF_15X, LF_15Y, LF_15Width, LF_15Height) && !InfoAndStats.hasTask){
					isLF_15Selected = true;
				}
			}
			if(isUT_1202Selected){
				if(controller.isClicked(backUpgradeWindow1X, backUpgradeWindow1Y, backUpgradeWindow1Width, backUpgradeWindow1Height)){
					isUT_1202Selected = false;
				}
				if(InfoAndStats.UT_1202.getRocketDetailAccessibility() && controller.isClicked(upgradeButton1X, upgradeButton1Y, upgradeButton1Width, upgradeButton1Height)){
					InfoAndStats.days = (int)InfoAndStats.date;
					InfoAndStats.hasTask = true;
					InfoAndStats.upgradeDetail.setRocketDetailThrust(InfoAndStats.UT_1202.getRocketDetailThrust());
					InfoAndStats.upgradeDetail.setRocketDetailImpulse(InfoAndStats.UT_1202.getRocketDetailImpulse());
					InfoAndStats.upgradeDetail.setRocketDetailTime(InfoAndStats.UT_1202.getRocketDetailTime());
					InfoAndStats.upgradeDetail.setRocketDetailPrice(InfoAndStats.UT_1202.getRocketDetailPrice());
					InfoAndStats.isUT_1202Upgrading = true;
					isUT_1202Selected = false;
				}
			}
			if(isUM_1034Selected){
				if(controller.isClicked(backUpgradeWindow1X, backUpgradeWindow1Y, backUpgradeWindow1Width, backUpgradeWindow1Height)){
					isUM_1034Selected = false;
				}
				if(InfoAndStats.UM_1034.getRocketDetailAccessibility() && controller.isClicked(upgradeButton1X, upgradeButton1Y, upgradeButton1Width, upgradeButton1Height)){
					InfoAndStats.days = (int)InfoAndStats.date;
					InfoAndStats.hasTask = true;
					InfoAndStats.upgradeDetail.setRocketDetailThrust(InfoAndStats.UM_1034.getRocketDetailThrust());
					InfoAndStats.upgradeDetail.setRocketDetailImpulse(InfoAndStats.UM_1034.getRocketDetailImpulse());
					InfoAndStats.upgradeDetail.setRocketDetailTime(InfoAndStats.UM_1034.getRocketDetailTime());
					InfoAndStats.upgradeDetail.setRocketDetailPrice(InfoAndStats.UM_1034.getRocketDetailPrice());
					InfoAndStats.isUM_1034Upgrading = true;
					isUM_1034Selected = false;
				}
				if(!InfoAndStats.UM_1034.getRocketDetailAccessibility() && controller.isClicked(researchButton1X, researchButton1Y, researchButton1Width, researchButton1Height)){
					InfoAndStats.days = (int)InfoAndStats.date;
					InfoAndStats.hasTask = true;
					InfoAndStats.upgradeDetail.setRocketDetailThrust(InfoAndStats.UM_1034.getRocketDetailThrust());
					InfoAndStats.upgradeDetail.setRocketDetailImpulse(InfoAndStats.UM_1034.getRocketDetailImpulse());
					InfoAndStats.upgradeDetail.setRocketDetailTime(InfoAndStats.UM_1034.getRocketDetailTime());
					InfoAndStats.upgradeDetail.setRocketDetailPrice(InfoAndStats.UM_1034.getRocketDetailPrice());
					InfoAndStats.isUM_1034Upgrading = true;
					isUM_1034Selected = false;
				}
			}
			if(isUF_02Selected){
				if(controller.isClicked(backUpgradeWindow1X, backUpgradeWindow1Y, backUpgradeWindow1Width, backUpgradeWindow1Height)){
					isUF_02Selected = false;
				}
				if(InfoAndStats.UF_02.getRocketDetailAccessibility() && controller.isClicked(upgradeButton1X, upgradeButton1Y, upgradeButton1Width, upgradeButton1Height)){
					InfoAndStats.days = (int)InfoAndStats.date;
					InfoAndStats.hasTask = true;
					InfoAndStats.upgradeDetail.setRocketDetailThrust(InfoAndStats.UF_02.getRocketDetailThrust());
					InfoAndStats.upgradeDetail.setRocketDetailImpulse(InfoAndStats.UF_02.getRocketDetailImpulse());
					InfoAndStats.upgradeDetail.setRocketDetailTime(InfoAndStats.UF_02.getRocketDetailTime());
					InfoAndStats.upgradeDetail.setRocketDetailPrice(InfoAndStats.UF_02.getRocketDetailPrice());
					InfoAndStats.isUF_02Upgrading = true;
					isUF_02Selected = false;
				}
				if(!InfoAndStats.UF_02.getRocketDetailAccessibility() && controller.isClicked(researchButton1X, researchButton1Y, researchButton1Width, researchButton1Height)){
					InfoAndStats.days = (int)InfoAndStats.date;
					InfoAndStats.hasTask = true;
					InfoAndStats.upgradeDetail.setRocketDetailThrust(InfoAndStats.UF_02.getRocketDetailThrust());
					InfoAndStats.upgradeDetail.setRocketDetailImpulse(InfoAndStats.UF_02.getRocketDetailImpulse());
					InfoAndStats.upgradeDetail.setRocketDetailTime(InfoAndStats.UF_02.getRocketDetailTime());
					InfoAndStats.upgradeDetail.setRocketDetailPrice(InfoAndStats.UF_02.getRocketDetailPrice());
					InfoAndStats.isUF_02Upgrading = true;
					isUF_02Selected = false;
				}
			}
			if(isMT_0112Selected){
				if(controller.isClicked(backUpgradeWindow1X, backUpgradeWindow1Y, backUpgradeWindow1Width, backUpgradeWindow1Height)){
					isMT_0112Selected = false;
				}
				if(InfoAndStats.MT_0112.getRocketDetailAccessibility() && controller.isClicked(upgradeButton1X, upgradeButton1Y, upgradeButton1Width, upgradeButton1Height)){
					InfoAndStats.days = (int)InfoAndStats.date;
					InfoAndStats.hasTask = true;
					InfoAndStats.upgradeDetail.setRocketDetailThrust(InfoAndStats.MT_0112.getRocketDetailThrust());
					InfoAndStats.upgradeDetail.setRocketDetailImpulse(InfoAndStats.MT_0112.getRocketDetailImpulse());
					InfoAndStats.upgradeDetail.setRocketDetailTime(InfoAndStats.MT_0112.getRocketDetailTime());
					InfoAndStats.upgradeDetail.setRocketDetailPrice(InfoAndStats.MT_0112.getRocketDetailPrice());
					InfoAndStats.isMT_0112Upgrading = true;
					isMT_0112Selected = false;
				}
			}
			if(isMM_4Selected){
				if(controller.isClicked(backUpgradeWindow1X, backUpgradeWindow1Y, backUpgradeWindow1Width, backUpgradeWindow1Height)){
					isMM_4Selected = false;
				}
				if(InfoAndStats.MM_4.getRocketDetailAccessibility() && controller.isClicked(upgradeButton1X, upgradeButton1Y, upgradeButton1Width, upgradeButton1Height)){
					InfoAndStats.days = (int)InfoAndStats.date;
					InfoAndStats.hasTask = true;
					InfoAndStats.upgradeDetail.setRocketDetailThrust(InfoAndStats.MM_4.getRocketDetailThrust());
					InfoAndStats.upgradeDetail.setRocketDetailImpulse(InfoAndStats.MM_4.getRocketDetailImpulse());
					InfoAndStats.upgradeDetail.setRocketDetailTime(InfoAndStats.MM_4.getRocketDetailTime());
					InfoAndStats.upgradeDetail.setRocketDetailPrice(InfoAndStats.MM_4.getRocketDetailPrice());
					InfoAndStats.isMM_4Upgrading = true;
					isMM_4Selected = false;
				}
				if(!InfoAndStats.MM_4.getRocketDetailAccessibility() && controller.isClicked(researchButton1X, researchButton1Y, researchButton1Width, researchButton1Height)){
					InfoAndStats.days = (int)InfoAndStats.date;
					InfoAndStats.hasTask = true;
					InfoAndStats.upgradeDetail.setRocketDetailThrust(InfoAndStats.MM_4.getRocketDetailThrust());
					InfoAndStats.upgradeDetail.setRocketDetailImpulse(InfoAndStats.MM_4.getRocketDetailImpulse());
					InfoAndStats.upgradeDetail.setRocketDetailTime(InfoAndStats.MM_4.getRocketDetailTime());
					InfoAndStats.upgradeDetail.setRocketDetailPrice(InfoAndStats.MM_4.getRocketDetailPrice());
					InfoAndStats.isMM_4Upgrading = true;
					isMM_4Selected = false;
				}
			}
			if(isMF_043Selected){
				if(controller.isClicked(backUpgradeWindow1X, backUpgradeWindow1Y, backUpgradeWindow1Width, backUpgradeWindow1Height)){
					isMF_043Selected = false;
				}
				if(InfoAndStats.MF_043.getRocketDetailAccessibility() && controller.isClicked(upgradeButton1X, upgradeButton1Y, upgradeButton1Width, upgradeButton1Height)){
					InfoAndStats.days = (int)InfoAndStats.date;
					InfoAndStats.hasTask = true;
					InfoAndStats.upgradeDetail.setRocketDetailThrust(InfoAndStats.MF_043.getRocketDetailThrust());
					InfoAndStats.upgradeDetail.setRocketDetailImpulse(InfoAndStats.MF_043.getRocketDetailImpulse());
					InfoAndStats.upgradeDetail.setRocketDetailTime(InfoAndStats.MF_043.getRocketDetailTime());
					InfoAndStats.upgradeDetail.setRocketDetailPrice(InfoAndStats.MF_043.getRocketDetailPrice());
					InfoAndStats.isMF_043Upgrading = true;
					isMF_043Selected = false;
				}
				if(!InfoAndStats.MF_043.getRocketDetailAccessibility() && controller.isClicked(researchButton1X, researchButton1Y, researchButton1Width, researchButton1Height)){
					InfoAndStats.days = (int)InfoAndStats.date;
					InfoAndStats.hasTask = true;
					InfoAndStats.upgradeDetail.setRocketDetailThrust(InfoAndStats.MF_043.getRocketDetailThrust());
					InfoAndStats.upgradeDetail.setRocketDetailImpulse(InfoAndStats.MF_043.getRocketDetailImpulse());
					InfoAndStats.upgradeDetail.setRocketDetailTime(InfoAndStats.MF_043.getRocketDetailTime());
					InfoAndStats.upgradeDetail.setRocketDetailPrice(InfoAndStats.MF_043.getRocketDetailPrice());
					InfoAndStats.isMF_043Upgrading = true;
					isMF_043Selected = false;
				}
			}
			if(isLT_116Selected){
				if(controller.isClicked(backUpgradeWindow1X, backUpgradeWindow1Y, backUpgradeWindow1Width, backUpgradeWindow1Height)){
					isLT_116Selected = false;
				}
				if(InfoAndStats.LT_116.getRocketDetailAccessibility() && controller.isClicked(upgradeButton1X, upgradeButton1Y, upgradeButton1Width, upgradeButton1Height)){
					InfoAndStats.days = (int)InfoAndStats.date;
					InfoAndStats.hasTask = true;
					InfoAndStats.upgradeDetail.setRocketDetailThrust(InfoAndStats.LT_116.getRocketDetailThrust());
					InfoAndStats.upgradeDetail.setRocketDetailImpulse(InfoAndStats.LT_116.getRocketDetailImpulse());
					InfoAndStats.upgradeDetail.setRocketDetailTime(InfoAndStats.LT_116.getRocketDetailTime());
					InfoAndStats.upgradeDetail.setRocketDetailPrice(InfoAndStats.LT_116.getRocketDetailPrice());
					InfoAndStats.isLT_116Upgrading = true;
					isLT_116Selected = false;
				}
			}
			if(isLM_087Selected){
				if(controller.isClicked(backUpgradeWindow1X, backUpgradeWindow1Y, backUpgradeWindow1Width, backUpgradeWindow1Height)){
					isLM_087Selected = false;
				}
				if(InfoAndStats.LM_087.getRocketDetailAccessibility() && controller.isClicked(upgradeButton1X, upgradeButton1Y, upgradeButton1Width, upgradeButton1Height)){
					InfoAndStats.days = (int)InfoAndStats.date;
					InfoAndStats.hasTask = true;
					InfoAndStats.upgradeDetail.setRocketDetailThrust(InfoAndStats.LM_087.getRocketDetailThrust());
					InfoAndStats.upgradeDetail.setRocketDetailImpulse(InfoAndStats.LM_087.getRocketDetailImpulse());
					InfoAndStats.upgradeDetail.setRocketDetailTime(InfoAndStats.LM_087.getRocketDetailTime());
					InfoAndStats.upgradeDetail.setRocketDetailPrice(InfoAndStats.LM_087.getRocketDetailPrice());
					InfoAndStats.isLM_087Upgrading = true;
					isLM_087Selected = false;
				}
				if(!InfoAndStats.LM_087.getRocketDetailAccessibility() && controller.isClicked(researchButton1X, researchButton1Y, researchButton1Width, researchButton1Height)){
					InfoAndStats.days = (int)InfoAndStats.date;
					InfoAndStats.hasTask = true;
					InfoAndStats.upgradeDetail.setRocketDetailThrust(InfoAndStats.LM_087.getRocketDetailThrust());
					InfoAndStats.upgradeDetail.setRocketDetailImpulse(InfoAndStats.LM_087.getRocketDetailImpulse());
					InfoAndStats.upgradeDetail.setRocketDetailTime(InfoAndStats.LM_087.getRocketDetailTime());
					InfoAndStats.upgradeDetail.setRocketDetailPrice(InfoAndStats.LM_087.getRocketDetailPrice());
					InfoAndStats.isLM_087Upgrading = true;
					isLM_087Selected = false;
				}
			}
			if(isLF_15Selected){
				if(controller.isClicked(backUpgradeWindow1X, backUpgradeWindow1Y, backUpgradeWindow1Width, backUpgradeWindow1Height)){
					isLF_15Selected = false;
				}
				if(InfoAndStats.LF_15.getRocketDetailAccessibility() && controller.isClicked(upgradeButton1X, upgradeButton1Y, upgradeButton1Width, upgradeButton1Height)){
					InfoAndStats.days = (int)InfoAndStats.date;
					InfoAndStats.hasTask = true;
					InfoAndStats.upgradeDetail.setRocketDetailThrust(InfoAndStats.LF_15.getRocketDetailThrust());
					InfoAndStats.upgradeDetail.setRocketDetailImpulse(InfoAndStats.LF_15.getRocketDetailImpulse());
					InfoAndStats.upgradeDetail.setRocketDetailTime(InfoAndStats.LF_15.getRocketDetailTime());
					InfoAndStats.upgradeDetail.setRocketDetailPrice(InfoAndStats.LF_15.getRocketDetailPrice());
					InfoAndStats.isLF_15Upgrading = true;
					isLF_15Selected = false;
				}
				if(!InfoAndStats.LF_15.getRocketDetailAccessibility() && controller.isClicked(researchButton1X, researchButton1Y, researchButton1Width, researchButton1Height)){
					InfoAndStats.days = (int)InfoAndStats.date;
					InfoAndStats.hasTask = true;
					InfoAndStats.upgradeDetail.setRocketDetailThrust(InfoAndStats.LF_15.getRocketDetailThrust());
					InfoAndStats.upgradeDetail.setRocketDetailImpulse(InfoAndStats.LF_15.getRocketDetailImpulse());
					InfoAndStats.upgradeDetail.setRocketDetailTime(InfoAndStats.LF_15.getRocketDetailTime());
					InfoAndStats.upgradeDetail.setRocketDetailPrice(InfoAndStats.LF_15.getRocketDetailPrice());
					InfoAndStats.isLF_15Upgrading = true;
					isLF_15Selected = false;
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
		upgradeInactive.dispose();
		upgradeActive.dispose();
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
		textFail.dispose();
		textSuc.dispose();
		textureDispose();
		GameScreen.isFromMenu = false;
	}
	
}
