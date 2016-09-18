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
import ru.erked.sflight.random.RocketDetailStats;

public class ControlCentreScreen implements Screen{

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
	public static final float backgroundTentionIndex = (float)width/185.0F;
	
	//Копка "Back"
	private Sprite backButtonInactiveSprite;
	private Sprite backButtonActiveSprite;
	private float backButtonX;
	private float backButtonY;
	private float backButtonWidth;
	private float backButtonHeight;
	public static float backButtonTentionIndex; //Соотношение сторон кнопки
	
	//Панель управления
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
	
	//Окно панели управления
	private Sprite startWindowSprite;
	private float startWindowX;
	private float startWindowY;
	private float startWindowWidth;
	private float startWindowHeight;
	public static boolean isStartWindowDraw;
	//Выход из окна панели управления
	private Sprite backStartWindowSpriteInactive;
	private Sprite backStartWindowSpriteActive;
	private float backStartWindow1X;
	private float backStartWindow1Y;
	private float backStartWindow1Width;
	private float backStartWindow1Height;
	private float backStartWindow2X;
	private float backStartWindow2Y;
	private float backStartWindow2Width;
	private float backStartWindow2Height;
	//Вход во окно характеристик ракеты
	private Texture inCharWindowInactive;
	private Texture inCharWindowActive;
	private Sprite inCharWindowSpriteInactive;
	private Sprite inCharWindowSpriteActive;
	private float inCharWindowX;
	private float inCharWindowY;
	private float inCharWindowWidth;
	private float inCharWindowHeight;
	private float inCharWindowTentionIndex;
	//Окно характеристик ракеты
	private Sprite charWindowSprite;
	private float charWindowX;
	private float charWindowY;
	private float charWindowWidth;
	private float charWindowHeight;
	private static boolean isCharWindowDraw;
	//Выход из окна характеристик ракеты
	private Sprite backCharWindowSpriteInactive;
	private Sprite backCharWindowSpriteActive;
	private float backCharWindow1X;
	private float backCharWindow1Y;
	private float backCharWindow1Width;
	private float backCharWindow1Height;
	private float backCharWindow2X;
	private float backCharWindow2Y;
	private float backCharWindow2Width;
	private float backCharWindow2Height;
	//Вход во окно текщего заказа
	private Texture inOrderWindowInactive;
	private Texture inOrderWindowActive;
	private Sprite inOrderWindowSpriteInactive;
	private Sprite inOrderWindowSpriteActive;
	private float inOrderWindowX;
	private float inOrderWindowY;
	private float inOrderWindowWidth;
	private float inOrderWindowHeight;
	private float inOrderWindowTentionIndex;
	//Окно текущего заказа
	private Sprite orderWindowSprite;
	private float orderWindowX;
	private float orderWindowY;
	private float orderWindowWidth;
	private float orderWindowHeight;
	private static boolean isOrderWindowDraw;
	//Выход из окна текщео заказа
	private Sprite backOrderWindowSpriteInactive;
	private Sprite backOrderWindowSpriteActive;
	private float backOrderWindow1X;
	private float backOrderWindow1Y;
	private float backOrderWindow1Width;
	private float backOrderWindow1Height;
	private float backOrderWindow2X;
	private float backOrderWindow2Y;
	private float backOrderWindow2Width;
	private float backOrderWindow2Height;
	//Окно панели управления
	private Sprite monogrammSprite;
	private float monogrammX;
	private float monogrammY;
	private float monogrammWidth;
	private float monogrammHeight;
	//Кнопка старта ракеты
	private Texture startButtonInactive;
	private Texture startButtonActive;
	private Sprite startButtonInactiveSprite;
	private Sprite startButtonActiveSprite;
	private float startButton1X;
	private float startButton1Y;
	private float startButton1Width;
	private float startButton1Height;
	private float startButton2X;
	private float startButton2Y;
	private float startButton2Width;
	private float startButton2Height;
	//Заглушка
	private Sprite backZSprite;
	
	//Текст
	private static BitmapFont text;
	
	public ControlCentreScreen(Game game){
		this.game = game;
	}
	
	@Override
	public void show() {
		
		batch = new SpriteBatch();
		controller = new SFlightInputController();

		MainMenu.music.play();
		
		RocketDetailStats.totalThrust = InfoAndStats.firstStage.getRocketDetailThrust() + InfoAndStats.secondStage.getRocketDetailThrust() + InfoAndStats.thirdStage.getRocketDetailThrust();
		RocketDetailStats.totalSpecificImpulse = InfoAndStats.firstStage.getRocketDetailImpulse() + InfoAndStats.secondStage.getRocketDetailImpulse() + InfoAndStats.thirdStage.getRocketDetailImpulse();
		RocketDetailStats.totalWorkingTime = InfoAndStats.firstStage.getRocketDetailTime() + InfoAndStats.secondStage.getRocketDetailTime() + InfoAndStats.thirdStage.getRocketDetailTime();
		
		//Фон\\
		backgroundTexture = new Texture("bckgrnd/control_inside.png");
		backgroundSprite = new Sprite(backgroundTexture);
		backgroundX = 0.0F;
		backgroundY = (-1)*(74*backgroundTentionIndex)/2 + height/2;
		backgroundSprite.setBounds(backgroundX, backgroundY, width, backgroundTentionIndex*74.0F);
		
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
	
		//Инициализация\\
		panelInit();
		startWindowInit();
		charWindowInit();
		charInInit();
		orderWindowInit();
		orderInInit();
		startButtonInit();
		
		//Составление имени ракеты\\
		if(!InfoAndStats.selectedFirst.equals("null") && !InfoAndStats.selectedSecond.equals("null") && !InfoAndStats.selectedThird.equals("null")){
			RocketDetailStats.totalName += InfoAndStats.firstStage.getRocketDetailName().substring(1, 2) + InfoAndStats.secondStage.getRocketDetailName().substring(1, 2) + InfoAndStats.thirdStage.getRocketDetailName().substring(1, 2);
			RocketDetailStats.totalName += "-";
			RocketDetailStats.totalName += InfoAndStats.firstStage.getRocketDetailName().substring(InfoAndStats.firstStage.getRocketDetailName().length()-1);
			RocketDetailStats.totalName += InfoAndStats.secondStage.getRocketDetailName().substring(InfoAndStats.secondStage.getRocketDetailName().length()-1);
			RocketDetailStats.totalName += InfoAndStats.thirdStage.getRocketDetailName().substring(InfoAndStats.thirdStage.getRocketDetailName().length()-1);
		}else{
			RocketDetailStats.totalName = "";
		}
		
		//Текст\\
		FreeTypeFontGenerator genUS = new FreeTypeFontGenerator(Gdx.files.internal("fonts/prototype.ttf"));
		FreeTypeFontGenerator genRU = new FreeTypeFontGenerator(Gdx.files.internal("fonts/9840.otf"));
		FreeTypeFontParameter param = new FreeTypeFontParameter();
		param.color = Color.WHITE;
		param.size = 40;
		if(!InfoAndStats.lngRussian){
			text = genUS.generateFont(param);
		}else{
			param.characters = FONT_CHARS_RU;
			text = genRU.generateFont(param);
		}
		text.getData().setScale((float)(0.00075F*width));
		
	}

	@Override
	public void render(float delta) {
		InfoAndStats.elapsedTime++;
		
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		
		backgroundSprite.draw(batch);
		
		if(!InfoAndStats.lngRussian){
			text.draw(batch, "Control tower", 0.01F*width, 0.99F*height);
		}else{
			text.draw(batch, "Диспетчерская вышка", 0.01F*width, 0.99F*height);
		}
		
		//Отрисовка кнопки "Back"//
		if(controller.isOn(backButtonX, backButtonY, backButtonWidth, backButtonHeight)){
			if(!isStartWindowDraw){
				backButtonActiveSprite.draw(batch);
			}else{
				backButtonInactiveSprite.draw(batch);
			}
		}else{
			backButtonInactiveSprite.draw(batch);
		}
		
		drawPanel();
		drawStartWindow();
		drawCharWindow();
		drawOrderWindow();
		
		batch.end();
		
		btnListeners();
	
	}

	private void panelInit(){
		//Панель управления\\
		panelInactive = new Texture("objects/controlPanelInactive.png");
		panelActive = new Texture("objects/controlPanelActive.png");
		panelInactiveSprite = new Sprite(panelInactive);
		panelActiveSprite = new Sprite(panelActive);
		panelAspectRatio = (float)panelInactive.getWidth()/panelInactive.getHeight();
		panel1Width = 0.075F*width;
		panel1Height = (float)panel1Width/panelAspectRatio;
		panel1X = 0.55F*backgroundSprite.getWidth();
		panel1Y = 0.625F*backgroundSprite.getHeight();
		panel2Width = 0.14423076923076923076923076923077F*width;
		panel2Height = (float)panel2Width/panelAspectRatio;
		panel2X = 0.55F*backgroundSprite.getWidth() - 0.24F*panel2Width;
		panel2Y = 0.625F*backgroundSprite.getHeight() - 0.24038461538461538461538461538462F*panel2Height;
		panelInactiveSprite.setBounds(panel1X, panel1Y, panel1Width, panel1Height);
		panelActiveSprite.setBounds(panel2X, panel2Y, panel2Width, panel2Height);
	}
	private void startWindowInit(){
		//Окно панели управления\\
		startWindowSprite = new Sprite(ImgResDraw.dialogWindow);
		startWindowWidth = 0.75F*width;
		startWindowHeight = startWindowWidth/1.45F;
		startWindowX = 0.5F*width - 0.5F*startWindowWidth;
		startWindowY = 0.5F*height - 0.5F*startWindowHeight;
		startWindowSprite.setBounds(startWindowX, startWindowY, startWindowWidth, startWindowHeight);
		isStartWindowDraw = false;
		monogrammSprite = new Sprite(new Texture("objects/monogramm.png"));
		monogrammWidth = 0.4F*width;
		monogrammHeight = monogrammWidth/2.0F;
		monogrammX = startWindowX + 0.05F*startWindowWidth;
		monogrammY = startWindowY + 0.4F*startWindowHeight;
		monogrammSprite.setBounds(monogrammX, monogrammY, monogrammWidth, monogrammHeight);
		backStartWindowSpriteInactive = new Sprite(ImgResDraw.backIconInactive);
		backStartWindowSpriteActive = new Sprite(ImgResDraw.backIconActive);
		backStartWindow1Width = 0.065F*width;
		backStartWindow1Height = backStartWindow1Width;
		backStartWindow1X = startWindowX + 0.95F*startWindowWidth - backStartWindow1Width;
		backStartWindow1Y = startWindowY + 0.05F*startWindowHeight;
		backStartWindowSpriteInactive.setBounds(backStartWindow1X, backStartWindow1Y, backStartWindow1Width, backStartWindow1Height);
		backStartWindow2Width = 0.0975F*width;
		backStartWindow2Height = backStartWindow2Width;
		backStartWindow2X = startWindowX + 0.95F*startWindowWidth - backStartWindow1Width - 0.16666666666666666666666666666667F*backStartWindow2Width;
		backStartWindow2Y = startWindowY + 0.05F*startWindowHeight - 0.16666666666666666666666666666667F*backStartWindow2Height;
		backStartWindowSpriteActive.setBounds(backStartWindow2X, backStartWindow2Y, backStartWindow2Width, backStartWindow2Height);
		backZSprite = new Sprite(new Texture("bckgrnd/backgroundGray.png"));
		backZSprite.setBounds(0.0F, 0.0F, width, height);
	}
	private void charWindowInit(){
		//Окно характеристик ракеты\\
		charWindowSprite = new Sprite(ImgResDraw.dialogWindow);
		charWindowWidth = 0.75F*width;
		charWindowHeight = charWindowWidth/1.45F;
		charWindowX = 0.5F*width - 0.5F*charWindowWidth;
		charWindowY = 0.5F*height - 0.5F*charWindowHeight;
		charWindowSprite.setBounds(charWindowX, charWindowY, charWindowWidth, charWindowHeight);
		isCharWindowDraw = false;
		backCharWindowSpriteInactive = new Sprite(ImgResDraw.backIconInactive);
		backCharWindowSpriteActive = new Sprite(ImgResDraw.backIconActive);
		backCharWindow1Width = 0.065F*width;
		backCharWindow1Height = backCharWindow1Width;
		backCharWindow1X = charWindowX + 0.95F*charWindowWidth - backCharWindow1Width;
		backCharWindow1Y = charWindowY + 0.05F*charWindowHeight;
		backCharWindowSpriteInactive.setBounds(backCharWindow1X, backCharWindow1Y, backCharWindow1Width, backCharWindow1Height);
		backCharWindow2Width = 0.0975F*width;
		backCharWindow2Height = backCharWindow2Width;
		backCharWindow2X = charWindowX + 0.95F*charWindowWidth - backCharWindow1Width - 0.16666666666666666666666666666667F*backCharWindow2Width;
		backCharWindow2Y = charWindowY + 0.05F*charWindowHeight - 0.16666666666666666666666666666667F*backCharWindow2Height;
		backCharWindowSpriteActive.setBounds(backCharWindow2X, backCharWindow2Y, backCharWindow2Width, backCharWindow2Height);
		backZSprite = new Sprite(new Texture("bckgrnd/backgroundGray.png"));
		backZSprite.setBounds(0.0F, 0.0F, width, height);
	}
	private void charInInit(){
		//Кнопка входа в характеристики ракеты\\
		inCharWindowInactive = new Texture("btns/button_rocket_stats_inactive_US.png");
		inCharWindowActive = new Texture("btns/button_rocket_stats_active_US.png");
		inCharWindowSpriteInactive = new Sprite(inCharWindowInactive);
		inCharWindowSpriteActive = new Sprite(inCharWindowActive);
		if(InfoAndStats.lngRussian){
			inCharWindowSpriteInactive.setTexture(new Texture("btns/RU/button_rocket_stats_inactive_RU.png"));
			inCharWindowSpriteActive.setTexture(new Texture("btns/RU/button_rocket_stats_active_RU.png"));
		}
		inCharWindowTentionIndex = (float)inCharWindowInactive.getWidth()/inCharWindowInactive.getHeight();
		inCharWindowWidth = 0.3F*width;
		inCharWindowHeight = inCharWindowWidth/inCharWindowTentionIndex;
		inCharWindowX = charWindowX + 0.25F*charWindowWidth - 0.5F*inCharWindowWidth;
		inCharWindowY = charWindowY + 0.11F*charWindowHeight;
		inCharWindowSpriteInactive.setBounds(inCharWindowX, inCharWindowY, inCharWindowWidth, inCharWindowHeight);
		inCharWindowSpriteActive.setBounds(inCharWindowX, inCharWindowY, inCharWindowWidth, inCharWindowHeight);
	}
	private void orderWindowInit(){
		//Окно характеристик ракеты\\
		orderWindowSprite = new Sprite(ImgResDraw.dialogWindow);
		orderWindowWidth = 0.75F*width;
		orderWindowHeight = orderWindowWidth/1.45F;
		orderWindowX = 0.5F*width - 0.5F*orderWindowWidth;
		orderWindowY = 0.5F*height - 0.5F*orderWindowHeight;
		orderWindowSprite.setBounds(orderWindowX, orderWindowY, orderWindowWidth, orderWindowHeight);
		isOrderWindowDraw = false;
		backOrderWindowSpriteInactive = new Sprite(ImgResDraw.backIconInactive);
		backOrderWindowSpriteActive = new Sprite(ImgResDraw.backIconActive);
		backOrderWindow1Width = 0.065F*width;
		backOrderWindow1Height = backOrderWindow1Width;
		backOrderWindow1X = orderWindowX + 0.95F*orderWindowWidth - backOrderWindow1Width;
		backOrderWindow1Y = orderWindowY + 0.05F*orderWindowHeight;
		backOrderWindowSpriteInactive.setBounds(backOrderWindow1X, backOrderWindow1Y, backOrderWindow1Width, backOrderWindow1Height);
		backOrderWindow2Width = 0.0975F*width;
		backOrderWindow2Height = backOrderWindow2Width;
		backOrderWindow2X = orderWindowX + 0.95F*orderWindowWidth - backOrderWindow1Width - 0.16666666666666666666666666666667F*backOrderWindow2Width;
		backOrderWindow2Y = orderWindowY + 0.05F*orderWindowHeight - 0.16666666666666666666666666666667F*backOrderWindow2Height;
		backOrderWindowSpriteActive.setBounds(backOrderWindow2X, backOrderWindow2Y, backOrderWindow2Width, backOrderWindow2Height);
		backZSprite = new Sprite(new Texture("bckgrnd/backgroundGray.png"));
		backZSprite.setBounds(0.0F, 0.0F, width, height);
	}
	private void orderInInit(){
		//Кнопка входа в характеристики ракеты\\
		inOrderWindowInactive = new Texture("btns/button_current_order_inactive_US.png");
		inOrderWindowActive = new Texture("btns/button_current_order_active_US.png");
		inOrderWindowSpriteInactive = new Sprite(inOrderWindowInactive);
		inOrderWindowSpriteActive = new Sprite(inOrderWindowActive);
		if(InfoAndStats.lngRussian){
			inOrderWindowSpriteInactive.setTexture(new Texture("btns/RU/button_current_order_inactive_RU.png"));
			inOrderWindowSpriteActive.setTexture(new Texture("btns/RU/button_current_order_active_RU.png"));
		}
		inOrderWindowTentionIndex = (float)inOrderWindowInactive.getWidth()/inOrderWindowInactive.getHeight();
		inOrderWindowWidth = 0.3F*width;
		inOrderWindowHeight = inOrderWindowWidth/inOrderWindowTentionIndex;
		inOrderWindowX = orderWindowX + 0.65F*orderWindowWidth - 0.5F*inOrderWindowWidth;
		inOrderWindowY = orderWindowY + 0.14F*orderWindowHeight;
		inOrderWindowSpriteInactive.setBounds(inOrderWindowX, inOrderWindowY, inOrderWindowWidth, inOrderWindowHeight);
		inOrderWindowSpriteActive.setBounds(inOrderWindowX, inOrderWindowY, inOrderWindowWidth, inOrderWindowHeight);
	}
	private void startButtonInit(){
		//Кнопка старта ракеты\\
		startButtonInactive = new Texture("btns/startButtonInactive.png");
		startButtonActive = new Texture("btns/startButtonActive.png");
		startButtonInactiveSprite = new Sprite(startButtonInactive);
		startButtonActiveSprite = new Sprite(startButtonActive);
		startButton1Width = 0.2F*width;
		startButton1Height = (float)startButton1Width;
		startButton1X = startWindowX + 0.635F*startWindowWidth;
		startButton1Y = monogrammY + monogrammHeight/2 - startButton1Height/2;
		startButton2Width = 0.27659574468085106382978723404255F*width;
		startButton2Height = (float)startButton2Width;
		startButton2X = startWindowX + 0.635F*startWindowWidth - 0.13846153846153846153846153846154F*startButton2Width;
		startButton2Y = monogrammY + monogrammHeight/2 - startButton1Height/2 - 0.13846153846153846153846153846154F*startButton2Height;
		startButtonInactiveSprite.setBounds(startButton1X, startButton1Y, startButton1Width, startButton1Height);
		startButtonActiveSprite.setBounds(startButton2X, startButton2Y, startButton2Width, startButton2Height);
	}
	
	private void drawStartWindow(){
		//Отрисовка диалогового окна панели управления//
		if(isStartWindowDraw){
			backZSprite.draw(batch);
			startWindowSprite.draw(batch);
			if(!InfoAndStats.lngRussian){
				text.draw(batch, "Control panel", startWindowX + 0.385F*startWindowWidth, 0.5F*startWindowY + startWindowHeight);
			}else{
				text.draw(batch, "Панель управления", startWindowX + 0.315F*startWindowWidth, 0.5F*startWindowY + startWindowHeight);
			}
			if(controller.isOn(backStartWindow1X, backStartWindow1Y, backStartWindow1Width, backStartWindow1Height)){
				backStartWindowSpriteActive.draw(batch);
			}else{
				backStartWindowSpriteInactive.draw(batch);
			}
			if(controller.isOn(inCharWindowX, inCharWindowY, inCharWindowWidth, inCharWindowHeight)){
				inCharWindowSpriteActive.draw(batch);
			}else{
				inCharWindowSpriteInactive.draw(batch);
			}
			if(controller.isOn(inOrderWindowX, inOrderWindowY, inOrderWindowWidth, inOrderWindowHeight)){
				inOrderWindowSpriteActive.draw(batch);
			}else{
				inOrderWindowSpriteInactive.draw(batch);
			}
			monogrammSprite.draw(batch);
			if(controller.isOn(startButton1X, startButton1Y, startButton1Width, startButton1Height)){
				startButtonActiveSprite.draw(batch);
			}else{
				startButtonInactiveSprite.draw(batch);
			}
		}
	}
	private void drawCharWindow(){
		//Отрисовка диалогового окна характеристик ракеты//
		if(isCharWindowDraw){
			backZSprite.draw(batch);
			charWindowSprite.draw(batch);
			int price = InfoAndStats.getFirstDetail().getRocketDetailPrice() + InfoAndStats.getSecondDetail().getRocketDetailPrice() + InfoAndStats.getThirdDetail().getRocketDetailPrice();
			if(!InfoAndStats.lngRussian){
				text.draw(batch, "Rocket's characteristics", charWindowX + 0.28F*charWindowWidth, 0.5F*charWindowY + charWindowHeight);
				text.draw(batch, "Rocket's name: " + RocketDetailStats.totalName, charWindowX + 0.05F*charWindowWidth, charWindowY + 0.875F*charWindowHeight - 1.5F*text.getCapHeight());
				text.draw(batch, "Total thrust: " + RocketDetailStats.totalThrust, charWindowX + 0.05F*charWindowWidth, charWindowY + 0.875F*charWindowHeight - 3.0F*text.getCapHeight());
				text.draw(batch, "Total specific impulse: " + RocketDetailStats.totalSpecificImpulse, charWindowX + 0.05F*charWindowWidth, charWindowY + 0.875F*charWindowHeight - 4.5F*text.getCapHeight());
				text.draw(batch, "Total working time: " + RocketDetailStats.totalWorkingTime, charWindowX + 0.05F*charWindowWidth, charWindowY + 0.875F*charWindowHeight - 6.0F*text.getCapHeight());
				text.draw(batch, "Total rocket's price: " + price + " cosmocoins", charWindowX + 0.05F*charWindowWidth, charWindowY + 0.875F*charWindowHeight - 7.5F*text.getCapHeight());
			}else{
				text.draw(batch, "Характеристики ракеты", charWindowX + 0.255F*charWindowWidth, 0.5F*charWindowY + charWindowHeight);
				text.draw(batch, "Название ракеты: " + RocketDetailStats.totalName, charWindowX + 0.05F*charWindowWidth, charWindowY + 0.875F*charWindowHeight - 1.5F*text.getCapHeight());
				text.draw(batch, "Общая тяга: " + RocketDetailStats.totalThrust, charWindowX + 0.05F*charWindowWidth, charWindowY + 0.875F*charWindowHeight - 3.0F*text.getCapHeight());
				text.draw(batch, "Общий удельный импульс: " + RocketDetailStats.totalSpecificImpulse, charWindowX + 0.05F*charWindowWidth, charWindowY + 0.875F*charWindowHeight - 4.5F*text.getCapHeight());
				text.draw(batch, "Общее время работы: " + RocketDetailStats.totalWorkingTime, charWindowX + 0.05F*charWindowWidth, charWindowY + 0.875F*charWindowHeight - 6.0F*text.getCapHeight());
				text.draw(batch, "Стоимость ракеты: " + price + " космокоинов", charWindowX + 0.05F*charWindowWidth, charWindowY + 0.875F*charWindowHeight - 7.5F*text.getCapHeight());
			}
			if(controller.isOn(backCharWindow1X, backCharWindow1Y, backCharWindow1Width, backCharWindow1Height)){
				backCharWindowSpriteActive.draw(batch);
			}else{
				backCharWindowSpriteInactive.draw(batch);
			}
		}
	}
	private void drawPanel(){
		//Отрисовка панели управления//
		if(controller.isOn(panel1X, panel1Y, panel1Width, panel1Height)){
			if(!isStartWindowDraw){
				panelActiveSprite.draw(batch);
			}else{
				panelInactiveSprite.draw(batch);
			}
		}else{
			panelInactiveSprite.draw(batch);
		}
	}
	private void drawOrderWindow(){
		//Отрисовка диалогового окна текущего заказа//
		if(isOrderWindowDraw){
			backZSprite.draw(batch);
			orderWindowSprite.draw(batch);
			if(!InfoAndStats.lngRussian){
				text.draw(batch, "Current order", orderWindowX + 0.375F*orderWindowWidth, 0.5F*orderWindowY + orderWindowHeight);
				text.draw(batch, "Order's name: " + InfoAndStats.currentOrder.getOrderName(), orderWindowX + 0.05F*orderWindowWidth, orderWindowY + 0.875F*orderWindowHeight - 1.5F*text.getCapHeight());
				text.draw(batch, "Load's mass: " + InfoAndStats.currentOrder.getOrderMass(), orderWindowX + 0.05F*orderWindowWidth, orderWindowY + 0.875F*orderWindowHeight - 3.0F*text.getCapHeight());
				text.draw(batch, "Destination: ", orderWindowX + 0.05F*orderWindowWidth, orderWindowY + 0.875F*orderWindowHeight - 4.5F*text.getCapHeight());
				switch(InfoAndStats.currentOrder.getOrderLevel()){
					case 0:{
						text.draw(batch, "Lower layers of the atmosphere", orderWindowX + 0.05F*orderWindowWidth, orderWindowY + 0.875F*orderWindowHeight - 6.0F*text.getCapHeight());
						break;
					}
					case 1:{
						text.draw(batch, "Upper layers of the atmosphere", orderWindowX + 0.05F*orderWindowWidth, orderWindowY + 0.875F*orderWindowHeight - 6.0F*text.getCapHeight());
						break;
					}
					case 2:{
						text.draw(batch, "Low earth orbit", orderWindowX + 0.05F*orderWindowWidth, orderWindowY + 0.875F*orderWindowHeight - 6.0F*text.getCapHeight());
						break;
					}
					case 3:{
						text.draw(batch, "High earth orbit", orderWindowX + 0.05F*orderWindowWidth, orderWindowY + 0.875F*orderWindowHeight - 6.0F*text.getCapHeight());
						break;
					}
				}
				text.draw(batch, "Reward: " + InfoAndStats.currentOrder.getOrderReward() + " cosmocoins", orderWindowX + 0.05F*orderWindowWidth, orderWindowY + 0.875F*orderWindowHeight - 7.5F*text.getCapHeight());
			}else{
				text.draw(batch, "Текущий заказ", orderWindowX + 0.35F*orderWindowWidth, 0.5F*orderWindowY + orderWindowHeight);
				text.draw(batch, "Название заказа: " + InfoAndStats.currentOrder.getOrderName(), orderWindowX + 0.05F*orderWindowWidth, orderWindowY + 0.875F*orderWindowHeight - 1.5F*text.getCapHeight());
				text.draw(batch, "Масса груза: " + InfoAndStats.currentOrder.getOrderMass(), orderWindowX + 0.05F*orderWindowWidth, orderWindowY + 0.875F*orderWindowHeight - 3.0F*text.getCapHeight());
				text.draw(batch, "Пункт назначения: ", orderWindowX + 0.05F*orderWindowWidth, orderWindowY + 0.875F*orderWindowHeight - 4.5F*text.getCapHeight());
				switch(InfoAndStats.currentOrder.getOrderLevel()){
					case 0:{
						text.draw(batch, "Нижние слои атмосферы", orderWindowX + 0.05F*orderWindowWidth, orderWindowY + 0.875F*orderWindowHeight - 6.0F*text.getCapHeight());
						break;
					}
					case 1:{
						text.draw(batch, "Верхние слои атмосферы", orderWindowX + 0.05F*orderWindowWidth, orderWindowY + 0.875F*orderWindowHeight - 6.0F*text.getCapHeight());
						break;
					}
					case 2:{
						text.draw(batch, "Низкая околоземная орбита", orderWindowX + 0.05F*orderWindowWidth, orderWindowY + 0.875F*orderWindowHeight - 6.0F*text.getCapHeight());
						break;
					}
					case 3:{
						text.draw(batch, "Высокая околоземная орбита", orderWindowX + 0.05F*orderWindowWidth, orderWindowY + 0.875F*orderWindowHeight - 6.0F*text.getCapHeight());
						break;
					}
				}
				text.draw(batch, "Награда: " + InfoAndStats.currentOrder.getOrderReward() + " космокоинов", orderWindowX + 0.05F*orderWindowWidth, orderWindowY + 0.875F*orderWindowHeight - 7.5F*text.getCapHeight());
			}
			if(controller.isOn(backOrderWindow1X, backOrderWindow1Y, backOrderWindow1Width, backOrderWindow1Height)){
				backOrderWindowSpriteActive.draw(batch);
			}else{
				backOrderWindowSpriteInactive.draw(batch);
			}
		}
	}
	
	private void btnListeners(){
		//Слушатель нажатия на кнопку "Back"//
		if(!isStartWindowDraw && !isOrderWindowDraw && !isCharWindowDraw){
			if(controller.isClicked(backButtonX, backButtonY, backButtonWidth, backButtonHeight)){
				game.setScreen(new GameScreen(game));
				this.dispose();
			}
		}
		//Слушатель нажатия на командную панель//
		if(!isStartWindowDraw && !isOrderWindowDraw && !isCharWindowDraw){
			if(controller.isClicked(panel1X, panel1Y, panel1Width, panel1Height)){
				isStartWindowDraw = true;
			}
		}
		//Слушатель нажатия на выход из панели управления//
		if(isStartWindowDraw && !isOrderWindowDraw && !isCharWindowDraw){
			if(controller.isClicked(backStartWindow1X, backStartWindow1Y, backStartWindow1Width, backStartWindow1Height)){
				isStartWindowDraw = false;
			}
		}
		//Слушатель нажатия на вход в характеристики ракеты//
		if(isStartWindowDraw && !isOrderWindowDraw){
			if(controller.isClicked(inCharWindowX, inCharWindowY, inCharWindowWidth, inCharWindowHeight)){
				isCharWindowDraw = true;
				isStartWindowDraw = false;
			}
		}
		//Слушатель нажатия на выход из характеристик ракеты//
		if(isCharWindowDraw){
			if(controller.isClicked(backCharWindow1X, backCharWindow1Y, backCharWindow1Width, backCharWindow1Height)){
				isCharWindowDraw = false;
				isStartWindowDraw = true;
			}
		}
		//Слушатель нажатия на вход в текущий заказ//
		if(isStartWindowDraw && !isCharWindowDraw && InfoAndStats.hasOrder){
			if(controller.isClicked(inOrderWindowX, inOrderWindowY, inOrderWindowWidth, inOrderWindowHeight)){
				isOrderWindowDraw = true;
				isStartWindowDraw = false;
			}
		}
		//Слушатель нажатия на выход из текущего заказа//
		if(isOrderWindowDraw && !isCharWindowDraw){
			if(controller.isClicked(backOrderWindow1X, backOrderWindow1Y, backOrderWindow1Width, backOrderWindow1Height)){
				isOrderWindowDraw = false;
				isStartWindowDraw = true;
			}
		}
		//Слушатель нажатия на старт ракеты//
		if(isStartWindowDraw){
			if(controller.isClicked(startButton1X, startButton1Y, startButton1Width, startButton1Height)){
				if(!InfoAndStats.selectedFirst.equals("null")
					&& !InfoAndStats.selectedSecond.equals("null")
					&& !InfoAndStats.selectedThird.equals("null")
					&& InfoAndStats.firstStage.getRocketDetailAmount() > 0
					&& InfoAndStats.secondStage.getRocketDetailAmount() > 0
					&& InfoAndStats.thirdStage.getRocketDetailAmount() > 0
					&& InfoAndStats.hasOrder){
					this.dispose();
					game.setScreen(new GameScreen(game));
					GameScreen.isLaunch = true;
					AngarScreen.isBoundDraw1 = false;
					AngarScreen.isBoundDraw2 = false;
					AngarScreen.isBoundDraw3 = false;
					AngarScreen.isLT_116Selected = false;
					AngarScreen.isMT_0112Selected = false;
					AngarScreen.isUT_1202Selected = false;
					AngarScreen.isLM_087Selected = false;
					AngarScreen.isMM_4Selected = false;
					AngarScreen.isUM_1034Selected = false;
					AngarScreen.isLF_15Selected = false;
					AngarScreen.isMF_043Selected = false;
					AngarScreen.isUF_02Selected = false;
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
		panelInactive.dispose();
		panelActive.dispose();
		inCharWindowInactive.dispose();
		inCharWindowActive.dispose();
		inOrderWindowInactive.dispose();
		inOrderWindowActive.dispose();
		startButtonInactive.dispose();
		startButtonActive.dispose();
	}
	
	@Override
	public void dispose() {
		game.dispose();
		batch.dispose();
		text.dispose();
		textureDispose();
		GameScreen.isFromMenu = false;
		RocketDetailStats.totalName = "";
	}

}
