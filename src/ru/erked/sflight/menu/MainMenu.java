package ru.erked.sflight.menu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

import ru.erked.sflight.controllers.SFlightInputController;
import ru.erked.sflight.game.GameScreen;
import ru.erked.sflight.random.ImgResDraw;
import ru.erked.sflight.random.InfoAndStats;

public class MainMenu implements Screen {
	
	final String FONT_CHARS_RU = "абвгдежзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
	private static final float width = Gdx.graphics.getWidth();
	private static final float height = Gdx.graphics.getHeight();
	public static final float backgroundTentionIndex = width/256;
	
	private Game game;
	private SpriteBatch batch;
	public static Music music = Gdx.audio.newMusic(Gdx.files.internal("sounds/music/Deliberate Thought.mp3"));
	private static BitmapFont text;
	private static BitmapFont textBlack;
	
	public static boolean isFirstScreen = true;
	
	private Texture backgroundTexture; //Текстура фона
	public static Sprite backgroundSprite; //Спрайт фона
	private float backgroundX;
	private float backgroundY;
	
	private Texture planet1Texture; //Текстура планеты
	public static Sprite planet1Sprite; //Спрайт планеты
	public static float planet1X;
	public static float planet1Y;
	
	private Texture planet2Texture; //Текстура планеты
	public static Sprite planet2Sprite; //Спрайт планеты
	public static float planet2X;
	public static float planet2Y;
	
	private Texture cometTexture; //Текстура кометы
	public static Sprite cometSprite; //Спрайт кометы
	public static float cometX;
	public static float cometY;
	
	private Texture startButtonInactive;
	private Texture startButtonActive;
	private Sprite startButtonInactiveSprite;
	private Sprite startButtonActiveSprite;
	private float startButtonX;
	private float startButtonY;
	private float startButtonWidth;
	private float startButtonHeight;
	public static float startButtonTentionIndex; //Соотношение сторон кнопки
	
	private Texture aboutButtonInactive;
	private Texture aboutButtonActive;
	private Sprite aboutButtonInactiveSprite;
	private Sprite aboutButtonActiveSprite;
	private float aboutButtonX;
	private float aboutButtonY;
	private float aboutButtonWidth;
	private float aboutButtonHeight;
	public static float aboutButtonTentionIndex; //Соотношение сторон кнопки
	
	private Texture exitButtonInactive;
	private Texture exitButtonActive;
	private Sprite exitButtonInactiveSprite;
	private Sprite exitButtonActiveSprite;
	private float exitButtonX;
	private float exitButtonY;
	private float exitButtonWidth;
	private float exitButtonHeight;
	public static float exitButtonTentionIndex; //Соотношение сторон кнопки
	
	private Texture optionsButtonInactive;
	private Texture optionsButtonActive;
	private Sprite optionsButtonInactiveSprite;
	private Sprite optionsButtonActiveSprite;
	private float optionsButtonX;
	private float optionsButtonY;
	private float optionsButtonWidth;
	private float optionsButtonHeight;
	public static float optionsButtonTentionIndex; //Соотношение сторон кнопки

	private Sprite blackAlpha = new Sprite(new Texture("objects/black.png"));
	private float alp = 1.0F;
	private boolean isTransGame;
	private boolean isTransAbout;
	private boolean isTransOptions;
	private boolean isTransExit;
	
	private SFlightInputController controller;

	/**Конструктор MainMenu*/
	public MainMenu(Game game){
		this.game = game;
	}
	
	/**Вызывается при ориентации на скрин MainMenu*/
	@Override
	public void show() {
		
		resourcesCheck();
		
		/**Установка слушателя контроллера*/
		controller = new SFlightInputController();
		Gdx.input.setInputProcessor(controller);

		/**Инициализация*/
		batch = new SpriteBatch();
		
		music.setLooping(true);
		music.setVolume(1.0f);
		music.play();
		
		isTransGame = false;
		isTransAbout = false;
		isTransOptions = false;
		isTransExit = false;
		
		//Фон//
		backgroundTexture = new Texture("bckgrnd/menu_background.png");
		backgroundSprite = new Sprite(backgroundTexture);
		backgroundX = 0.0F;
		backgroundY = (-1)*(256*backgroundTentionIndex)/2 + height/2; //Выставляю относительно центра устройства по Y
		backgroundSprite.setBounds(backgroundX, backgroundY, width, 256*backgroundTentionIndex);
		//Планета 1//
		planet1Texture = new Texture("objects/menu_planet_1.png");
		planet1Sprite = new Sprite(planet1Texture);
		if(isFirstScreen){
			planet1X = 0.0F - 0.2F*width;
			planet1Y = height - 0.35F*height;
			planet1Sprite.setBounds(planet1X, planet1Y, 2*width, 2*width);
			planet1Sprite.setRotation(45.0F);
		}else{
			planet1Sprite.setBounds(planet1X, planet1Y, 2*width, 2*width);
			planet1Sprite.setRotation(Math.max(OptionsScreen.planet1PrevRotation, AboutScreen.planet1PrevRotation));
		}
		//Планета 2//
		planet2Texture = new Texture("objects/menu_planet_2.png");
		planet2Sprite = new Sprite(planet2Texture);
		if(isFirstScreen){
			planet2X = 1.05F*width + planet2Sprite.getWidth();
			planet2Y = 0.05F*height;
			planet2Sprite.setBounds(planet2X, planet2Y, 0.15F*width, 0.15F*width);
		}else{
			planet2Sprite.setBounds(planet2X, planet2Y, 0.15F*width, 0.15F*width);
			planet2Sprite.setRotation(Math.max(OptionsScreen.planet2PrevRotation, AboutScreen.planet2PrevRotation));
		}
		//Комета//
		cometTexture = new Texture("objects/menu_comet.png");
		cometSprite = new Sprite(cometTexture);
		if(isFirstScreen){
			cometX = 0.0F;
			cometY = height;
			cometSprite.setBounds(cometX, cometY, 0.15F*width, 0.15F*width);
		}else{
			cometSprite.setBounds(cometX, cometY, 0.15F*width, 0.15F*width);
			cometSprite.setRotation(Math.max(OptionsScreen.cometPrevRotation, AboutScreen.cometPrevRotation));
		}
		
		//Кнопка "Start Game"//
		startButtonInactive = new Texture("btns/button_start_game_inactive.png");
		startButtonActive = new Texture("btns/button_start_game_active.png");
		startButtonInactiveSprite = new Sprite(startButtonInactive);
		startButtonActiveSprite = new Sprite(startButtonActive);
		if(InfoAndStats.lngRussian){
			startButtonInactiveSprite.setTexture(ImgResDraw.startGameButtonInactiveRU);
			startButtonActiveSprite.setTexture(ImgResDraw.startGameButtonActiveRU);
		}
		startButtonTentionIndex = (float)startButtonInactive.getWidth()/startButtonInactive.getHeight();
		startButtonWidth = 0.4F*width;
		startButtonHeight = startButtonWidth/startButtonTentionIndex;
		startButtonX = width/2 - startButtonWidth/2;
		startButtonY = height/2 - startButtonHeight/2 + startButtonHeight + 0.015F*height;
		startButtonInactiveSprite.setBounds(startButtonX, startButtonY, startButtonWidth, startButtonHeight);
		startButtonActiveSprite.setBounds(startButtonX, startButtonY, startButtonWidth, startButtonHeight);
		
		//Кнопка "About"//
		aboutButtonInactive = new Texture("btns/button_about_inactive.png");
		aboutButtonActive = new Texture("btns/button_about_active.png");
		aboutButtonInactiveSprite = new Sprite(aboutButtonInactive);
		aboutButtonActiveSprite = new Sprite(aboutButtonActive);
		if(InfoAndStats.lngRussian){
			aboutButtonInactiveSprite.setTexture(ImgResDraw.aboutButtonInactiveRU);
			aboutButtonActiveSprite.setTexture(ImgResDraw.aboutButtonActiveRU);
		}
		aboutButtonTentionIndex = (float)aboutButtonInactive.getWidth()/aboutButtonInactive.getHeight();
		aboutButtonWidth = 0.22F*width;
		aboutButtonHeight = aboutButtonWidth/aboutButtonTentionIndex;
		aboutButtonX = width/2 - aboutButtonWidth/2;
		aboutButtonY = height/2 - aboutButtonHeight/2;
		aboutButtonInactiveSprite.setBounds(aboutButtonX, aboutButtonY, aboutButtonWidth, aboutButtonHeight);
		aboutButtonActiveSprite.setBounds(aboutButtonX, aboutButtonY, aboutButtonWidth, aboutButtonHeight);
		
		//Кнопка "Options"//
		optionsButtonInactive = new Texture("btns/button_options_inactive.png");
		optionsButtonActive = new Texture("btns/button_options_active.png");
		optionsButtonInactiveSprite = new Sprite(optionsButtonInactive);
		optionsButtonActiveSprite = new Sprite(optionsButtonActive);
		if(InfoAndStats.lngRussian){
			optionsButtonInactiveSprite.setTexture(ImgResDraw.optionsButtonInactiveRU);
			optionsButtonActiveSprite.setTexture(ImgResDraw.optionsButtonActiveRU);
		}
		optionsButtonTentionIndex = (float)optionsButtonInactive.getWidth()/optionsButtonInactive.getHeight();
		optionsButtonWidth = 0.25F*width;
		optionsButtonHeight = optionsButtonWidth/optionsButtonTentionIndex;
		optionsButtonX = width/2 - optionsButtonWidth/2;
		optionsButtonY = height/2 - optionsButtonHeight/2 - optionsButtonHeight;
		optionsButtonInactiveSprite.setBounds(optionsButtonX, optionsButtonY, optionsButtonWidth, optionsButtonHeight);
		optionsButtonActiveSprite.setBounds(optionsButtonX, optionsButtonY, optionsButtonWidth, optionsButtonHeight);
		
		//Кнопка "Exit"//
		exitButtonInactive = new Texture("btns/button_exit_inactive.png");
		exitButtonActive = new Texture("btns/button_exit_active.png");
		exitButtonInactiveSprite = new Sprite(exitButtonInactive);
		exitButtonActiveSprite = new Sprite(exitButtonActive);
		if(InfoAndStats.lngRussian){
			exitButtonInactiveSprite.setTexture(ImgResDraw.exitButtonInactiveRU);
			exitButtonActiveSprite.setTexture(ImgResDraw.exitButtonActiveRU);
		}
		exitButtonTentionIndex = (float)exitButtonInactive.getWidth()/exitButtonInactive.getHeight();
		exitButtonWidth = 0.132F*width;
		exitButtonHeight = exitButtonWidth/exitButtonTentionIndex;
		exitButtonX = width/2 - exitButtonWidth/2;
		exitButtonY = height/2 - exitButtonHeight/2 - exitButtonHeight - optionsButtonHeight - 0.015F*height;
		exitButtonInactiveSprite.setBounds(exitButtonX, exitButtonY, exitButtonWidth, exitButtonHeight);
		exitButtonActiveSprite.setBounds(exitButtonX, exitButtonY, exitButtonWidth, exitButtonHeight);
		/**Инициализация*/
	
		//Текст лого\\
		FreeTypeFontGenerator genUS = new FreeTypeFontGenerator(Gdx.files.internal("fonts/prototype.ttf"));
		FreeTypeFontGenerator genRU = new FreeTypeFontGenerator(Gdx.files.internal("fonts/9840.otf"));
		FreeTypeFontParameter param = new FreeTypeFontParameter();
		FreeTypeFontParameter paramBlack = new FreeTypeFontParameter();
		param.color = Color.WHITE;
		param.size = 150;
		paramBlack.color = Color.BLACK;
		paramBlack.size = 150;
		if(InfoAndStats.lngRussian){
			param.characters = FONT_CHARS_RU;
			paramBlack.characters = FONT_CHARS_RU;
			text = genRU.generateFont(param);
			textBlack = genRU.generateFont(paramBlack);
		}else{
			text = genUS.generateFont(param);
			textBlack = genUS.generateFont(paramBlack);
		}
		text.getData().setScale((float)(0.00075F*width));
		textBlack.getData().setScale((float)(0.00075F*width));
		
		/**Плавный переход*/
		blackAlpha.setBounds(0.0F, 0.0F, width, height);
		blackAlpha.setAlpha(1.0F);
	}

	@Override
	public void render(float delta) {
		InfoAndStats.elapsedTime++;
		
		if(alp>0.0F && (!isTransGame && !isTransAbout && !isTransOptions && !isTransExit)){
			blackAlpha.setAlpha(alp);
			alp-=0.05F;
		}else if(!isTransGame && !isTransAbout && !isTransOptions && !isTransExit){
			blackAlpha.setAlpha(0.0F);
			alp = 0.0F;
		}
		
		/**Необходимо для уничтожения эффекта следов*/
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		planet1Sprite.setOriginCenter();
		planet1Sprite.rotate(0.0175F);
		
		planet2Sprite.setOriginCenter();
		planet2Sprite.rotate(-0.01F);
		planet2Sprite.setY((float)(planet2Sprite.getY() + 0.03F));
		planet2Sprite.setX(planet2Sprite.getX() - 0.3F);
		if(planet2Sprite.getX() < (0-1)*width){
			planet2Sprite.setX(1.05F*width + planet2Sprite.getWidth());
			planet2Sprite.setY(0.05F*height);
		}
		
		cometSprite.setOrigin(2*width, 2*height);
		cometSprite.rotate(0.25F);
		
		batch.begin();
		
		backgroundSprite.draw(batch);
		planet1Sprite.draw(batch);
		planet2Sprite.draw(batch);
		cometSprite.draw(batch);
		
		//Отрисовка кнопки "Start Game"//
		if(controller.isOn(startButtonX, startButtonY, startButtonWidth, startButtonHeight))
			startButtonActiveSprite.draw(batch);
		else
			startButtonInactiveSprite.draw(batch);
		//Отрисовка кнопки "About"//
		if(controller.isOn(aboutButtonX, aboutButtonY, aboutButtonWidth, aboutButtonHeight))
			aboutButtonActiveSprite.draw(batch);
		else
			aboutButtonInactiveSprite.draw(batch);
		//Отрисовка кнопки "Options"//
		if(controller.isOn(optionsButtonX, optionsButtonY, optionsButtonWidth, optionsButtonHeight))
			optionsButtonActiveSprite.draw(batch);
		else
			optionsButtonInactiveSprite.draw(batch);
		//Отрисовка кнопки "Exit"//
		if(controller.isOn(exitButtonX, exitButtonY, exitButtonWidth, exitButtonHeight))
			exitButtonActiveSprite.draw(batch);
		else
			exitButtonInactiveSprite.draw(batch);
		
		//Лого игры в меню//
		textBlack.draw(batch, "Space Flight", 0.204F*width, 0.97F*height);
		text.draw(batch, "Space Flight", 0.2F*width, 0.975F*height);
		
		blackAlpha.draw(batch);
		
		batch.end();
		
		/**Проверка нажатий на кнопки*/
		if(controller.isClicked(exitButtonX, exitButtonY, exitButtonWidth, exitButtonHeight) || isTransExit){
			isTransExit = true;
			isTransAbout = false;
			isTransGame = false;
			isTransOptions = false;
			if(alp>1.0F){
				Gdx.app.exit();
			}else{
				blackAlpha.setAlpha(alp);
				alp+=0.05F;
			}
		}
		if(controller.isClicked(aboutButtonX, aboutButtonY, aboutButtonWidth, aboutButtonHeight) || isTransAbout){
			isTransExit = false;
			isTransAbout = true;
			isTransGame = false;
			isTransOptions = false;
			if(alp>1.0F){
				this.dispose();
				game.setScreen(new AboutScreen(game));
				alp = 1.0F;
			}else{
				blackAlpha.setAlpha(alp);
				alp+=0.05F;
			}
		}
		if(controller.isClicked(startButtonX, startButtonY, startButtonWidth, startButtonHeight) || isTransGame){
			isTransExit = false;
			isTransAbout = false;
			isTransGame = true;
			isTransOptions = false;
			if(alp>1.0F){
				this.dispose();
				game.setScreen(new GameScreen(game));
				alp = 1.0F;
			}else{
				blackAlpha.setAlpha(alp);
				alp+=0.05F;
			}
		}
		if(controller.isClicked(optionsButtonX, optionsButtonY, optionsButtonWidth, optionsButtonHeight) || isTransOptions){
			isTransExit = false;
			isTransAbout = false;
			isTransGame = false;
			isTransOptions = true;
			if(alp>1.0F){
				this.dispose();
				game.setScreen(new OptionsScreen(game));
				alp = 1.0F;
			}else{
				blackAlpha.setAlpha(alp);
				alp+=0.05F;
			}
		}
		/**Проверка нажатий на кнопки*/
	}
	
	private void resourcesCheck(){
		if(InfoAndStats.money>InfoAndStats.moneyFull) InfoAndStats.money = InfoAndStats.moneyFull;
		if(InfoAndStats.fuel>InfoAndStats.fuelFull) InfoAndStats.fuel = InfoAndStats.fuelFull;
		if(InfoAndStats.metal>InfoAndStats.metalFull) InfoAndStats.metal = InfoAndStats.metalFull;
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
		startButtonInactive.dispose();
		startButtonActive.dispose();
		aboutButtonInactive.dispose();
		aboutButtonActive.dispose();
		optionsButtonInactive.dispose();
		optionsButtonActive.dispose();
		exitButtonInactive.dispose();
		exitButtonActive.dispose();
	}
	
	@Override
	public void dispose() {
		game.dispose();
		batch.dispose();
		music.pause();
		textureDispose();
	}

}
