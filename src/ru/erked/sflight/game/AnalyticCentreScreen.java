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
	//Панель ресурсов
	private Texture resourcePanelInactive;
	private Texture resourcePanelActive;
	private Sprite resourcePanelInactiveSprite;
	private Sprite resourcePanelActiveSprite;
	private float resourcePanel1X;
	private float resourcePanel1Y;
	private float resourcePanel1Width;
	private float resourcePanel1Height;
	private float resourcePanel2X;
	private float resourcePanel2Y;
	private float resourcePanel2Width;
	private float resourcePanel2Height;
	//Текст
	private static BitmapFont text;
	
	private Sprite blackAlpha = new Sprite(new Texture("objects/black.png"));
	private float alp = 1.0F;
	private boolean isTransAnalytic;
	
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
		resourcePanelInit();
		
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
		
		isTransAnalytic = false;
		blackAlpha.setBounds(0.0F, 0.0F, width, height);
		blackAlpha.setAlpha(1.0F);
		
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
		scoreboard1X = 0.6F*backgroundSprite.getWidth();
		scoreboard1Y = backgroundSprite.getY() + 0.475F*backgroundSprite.getHeight();
		scoreboard2Width = 0.34594594594594594594594594594595F*width;
		scoreboard2Height = scoreboard2Width;
		scoreboard2X = 0.6F*backgroundSprite.getWidth() - 0.2109375F*scoreboard2Width;
		scoreboard2Y = backgroundSprite.getY() + 0.475F*backgroundSprite.getHeight() - 0.2109375F*scoreboard2Height;
		scoreboardInactiveSprite.setBounds(scoreboard1X, scoreboard1Y, scoreboard1Width, scoreboard1Height);
		scoreboardActiveSprite.setBounds(scoreboard2X, scoreboard2Y, scoreboard2Width, scoreboard2Height);
	}
	private void resourcePanelInit(){
		//Панель ресурсов\\
		resourcePanelInactive = new Texture("objects/resourcesPanelInactive.png");
		resourcePanelActive = new Texture("objects/resourcesPanelActive.png");
		resourcePanelInactiveSprite = new Sprite(resourcePanelInactive);
		resourcePanelActiveSprite = new Sprite(resourcePanelActive);
		resourcePanel1Width = 0.115F*width;
		resourcePanel1Height = 1.4090909090909090909090909090909F*resourcePanel1Width;
		resourcePanel1X = 0.415F*backgroundSprite.getWidth();
		resourcePanel1Y = backgroundSprite.getY() + 0.385F*backgroundSprite.getHeight();
		resourcePanel2Width = 2.5F*resourcePanel1Width;
		resourcePanel2Height = 2.5F*resourcePanel1Height;
		resourcePanel2X = 0.415F*backgroundSprite.getWidth() - 0.3F*resourcePanel2Width;
		resourcePanel2Y = backgroundSprite.getY() + 0.385F*backgroundSprite.getHeight() - 0.3F*resourcePanel2Height;
		resourcePanelInactiveSprite.setBounds(resourcePanel1X, resourcePanel1Y, resourcePanel1Width, resourcePanel1Height);
		resourcePanelActiveSprite.setBounds(resourcePanel2X, resourcePanel2Y, resourcePanel2Width, resourcePanel2Height);
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
		//Слушатель нажатия на панель ресурсов//
		if(controller.isClicked(resourcePanel1X, resourcePanel1Y, resourcePanel1Width, resourcePanel1Height)){
			game.setScreen(new ResourceScreen(game));
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
	private void drawResourcePanel(){
		//Отрисовка панели ресурсов//
		if(controller.isOn(resourcePanel1X, resourcePanel1Y, resourcePanel1Width, resourcePanel1Height)){
			resourcePanelActiveSprite.draw(batch);
		}else{
			resourcePanelInactiveSprite.draw(batch);
		}
	}

	@Override
	public void render(float delta) {
		InfoAndStats.elapsedTime++;
		
		if(alp>0.0F && (!isTransAnalytic)){
			blackAlpha.setAlpha(alp);
			alp-=0.05F;
		}else if(!isTransAnalytic){
			blackAlpha.setAlpha(0.0F);
			alp = 0.0F;
		}
		
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
		drawResourcePanel();
		
		blackAlpha.draw(batch);
		
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