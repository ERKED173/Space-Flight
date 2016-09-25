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

public class AnalyticCentreScreen implements Screen{

	final String FONT_CHARS_RU = "абвгдежзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
	private static final float width = Gdx.graphics.getWidth();
	private static final float height = Gdx.graphics.getHeight();
	
	private Game game;
	private SpriteBatch batch;
	private SFlightInputController controller;
	public static float prevDay = (-1.0F)*1.0F;
	
	//Фон
	private Texture backgroundTexture; //Текстура фона
	public static Sprite backgroundSprite; //Спрайт фона
	private float backgroundX;
	private float backgroundY;
	public static final float backgroundTentionIndex = (float)width/400.0F;
	
	//Копка "Back"
	private Sprite backButtonInactiveSprite;
	private Sprite backButtonActiveSprite;
	private float backButtonX;
	private float backButtonY;
	private float backButtonWidth;
	private float backButtonHeight;
	public static float backButtonTentionIndex; //Соотношение сторон кнопки
	
	//Доска статистики
	private Texture scoreboardInactive;
	private Texture scoreboardActive;
	private Sprite scoreboardInactiveSprite;
	private Sprite scoreboardActiveSprite;
	private float scoreboard1X;
	private float scoreboard1Y;
	private float scoreboard1Width;
	private float scoreboard1Height;
	private float scoreboard2X;
	private float scoreboard2Y;
	private float scoreboard2Width;
	private float scoreboard2Height;
	//Текст
	private static BitmapFont text;
	
	public AnalyticCentreScreen(Game game){
		this.game = game;
	}
	
	@Override
	public void show() {

		MainMenu.music.play();
		
		batch = new SpriteBatch();
		controller = new SFlightInputController();
		
		//Фон\\
		backgroundTexture = new Texture("bckgrnd/analytic_inside.png");
		backgroundSprite = new Sprite(backgroundTexture);
		backgroundX = 0.0F;
		backgroundY = (-1)*(230*backgroundTentionIndex)/2 + height/2;
		backgroundSprite.setBounds(backgroundX, backgroundY, width, backgroundTentionIndex*230.0F);
		
		backButtonInit();
		scoreboardInit();
		
		//Текст\\
		FreeTypeFontGenerator genUS = new FreeTypeFontGenerator(Gdx.files.internal("fonts/prototype.ttf"));
		FreeTypeFontGenerator genRU = new FreeTypeFontGenerator(Gdx.files.internal("fonts/9840.otf"));
		FreeTypeFontParameter param = new FreeTypeFontParameter();
		param.color = Color.WHITE;
		param.size = 40;
		if(!InfoAndStats.lngRussian){
			text = genUS.generateFont(param);
			text.getData().setScale((float)(0.0007F*width));
		}else{
			param.characters = FONT_CHARS_RU;
			text = genRU.generateFont(param);
			text.getData().setScale((float)(0.0006F*width));
		}
	}

	private void backButtonInit(){
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
	}
	private void scoreboardInit(){
	//Доска статистики\\
		scoreboardInactive = new Texture("objects/graph_board_inactive.png");
		scoreboardActive = new Texture("objects/graph_board_active.png");
		scoreboardInactiveSprite = new Sprite(scoreboardInactive);
		scoreboardActiveSprite = new Sprite(scoreboardActive);
		scoreboard1Width = 0.2F*width;
		scoreboard1Height = scoreboard1Width;
		scoreboard1X = 0.605F*backgroundSprite.getWidth();
		scoreboard1Y = 0.55F*backgroundSprite.getHeight();
		scoreboard2Width = 0.34594594594594594594594594594595F*width;
		scoreboard2Height = scoreboard2Width;
		scoreboard2X = 0.605F*backgroundSprite.getWidth() - 0.2109375F*scoreboard2Width;
		scoreboard2Y = 0.55F*backgroundSprite.getHeight() - 0.2109375F*scoreboard2Height;
		scoreboardInactiveSprite.setBounds(scoreboard1X, scoreboard1Y, scoreboard1Width, scoreboard1Height);
		scoreboardActiveSprite.setBounds(scoreboard2X, scoreboard2Y, scoreboard2Width, scoreboard2Height);
	}
	
	private void btnListener(){
		//Слушатель нажатия на кнопку "Back"//
		if(controller.isClicked(backButtonX, backButtonY, backButtonWidth, backButtonHeight)){
			game.setScreen(new GameScreen(game));
			this.dispose();
		}
		//Слушатель нажатия на доску статистики//
		if(controller.isClicked(scoreboard1X, scoreboard1Y, scoreboard1Width, scoreboard1Height)){
			game.setScreen(new StatisticScreen(game));
			this.dispose();
		}
	}
	
	private void drawBackButton(){
		//Отрисовка кнопки "Back"//
		if(controller.isOn(backButtonX, backButtonY, backButtonWidth, backButtonHeight)){
			backButtonActiveSprite.draw(batch);
		}else{
			backButtonInactiveSprite.draw(batch);
		}
	}
	private void drawScoreboard(){
		//Отрисовка доски статистики//
		if(controller.isOn(scoreboard1X, scoreboard1Y, scoreboard1Width, scoreboard1Height)){
			scoreboardActiveSprite.draw(batch);
		}else{
			scoreboardInactiveSprite.draw(batch);
		}
	}

	@Override
	public void render(float delta) {
		InfoAndStats.elapsedTime++;
		
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		
		backgroundSprite.draw(batch);

		if(!InfoAndStats.lngRussian){
			text.draw(batch, "Analytic centre", 0.01F*width, 0.99F*height);
		}else{
			text.draw(batch, "Аналитический центр", 0.01F*width, 0.99F*height);
		}
		
		drawBackButton();
		drawScoreboard();
		
		batch.end();
		
		btnListener();
		
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
		scoreboardInactive.dispose();
		scoreboardActive.dispose();
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
