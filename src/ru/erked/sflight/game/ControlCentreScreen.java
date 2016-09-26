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
	//Монограмма
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
	
	private Sprite blackAlpha = new Sprite(new Texture("objects/black.png"));
	private float alp = 1.0F;
	private boolean isTransControl;
	
	public ControlCentreScreen(Game game){
		this.game = game;
	}
	
	@Override
	public void show() {
		
		batch = new SpriteBatch();
		controller = new SFlightInputController();

		MainMenu.music.play();
		
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
		startButtonInit();
		
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
		
		isTransControl = false;
		blackAlpha.setBounds(0.0F, 0.0F, width, height);
		blackAlpha.setAlpha(1.0F);
		
	}

	@Override
	public void render(float delta) {
		InfoAndStats.elapsedTime++;
		
		if(alp>0.0F && (!isTransControl)){
			blackAlpha.setAlpha(alp);
			alp-=0.05F;
		}else if(!isTransControl){
			blackAlpha.setAlpha(0.0F);
			alp = 0.0F;
		}
		
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
		
		blackAlpha.draw(batch);
		
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
		panel1Y = backgroundSprite.getY() + 0.35F*backgroundSprite.getHeight();
		panel2Width = 0.14423076923076923076923076923077F*width;
		panel2Height = (float)panel2Width/panelAspectRatio;
		panel2X = 0.55F*backgroundSprite.getWidth() - 0.24F*panel2Width;
		panel2Y = backgroundSprite.getY() + 0.35F*backgroundSprite.getHeight() - 0.24038461538461538461538461538462F*panel2Height;
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
			monogrammSprite.draw(batch);
			if(controller.isOn(startButton1X, startButton1Y, startButton1Width, startButton1Height)){
				startButtonActiveSprite.draw(batch);
			}else{
				startButtonInactiveSprite.draw(batch);
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
	
	private void btnListeners(){
		//Слушатель нажатия на кнопку "Back"//
		if(!isStartWindowDraw){
			if(controller.isClicked(backButtonX, backButtonY, backButtonWidth, backButtonHeight)){
				game.setScreen(new GameScreen(game));
				this.dispose();
			}
			//Слушатель нажатия на командную панель//
			if(controller.isClicked(panel1X, panel1Y, panel1Width, panel1Height)){
				isStartWindowDraw = true;
			}
		}
		//Слушатель нажатия на выход из панели управления//
		if(isStartWindowDraw){
			if(controller.isClicked(backStartWindow1X, backStartWindow1Y, backStartWindow1Width, backStartWindow1Height)){
				isStartWindowDraw = false;
			}
			//Слушатель нажатия на старт ракеты//
			if(controller.isClicked(startButton1X, startButton1Y, startButton1Width, startButton1Height)){
				/**TODO: Запуск ракеты*/
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