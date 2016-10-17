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
import ru.erked.sflight.random.InfoAndStats;
import ru.erked.sflight.tech.SFButtonS;

public class HangarPanelScreen implements Screen{

	final String FONT_CHARS_RU = "абвгдежзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
	private static final float width = Gdx.graphics.getWidth();
	private static final float height = Gdx.graphics.getHeight();
	
	private Game game;
	private SpriteBatch batch;
	private SFlightInputController controller;
	
	
	
	//Background
	private Texture backgroundTexture;
	public static Sprite backgroundSprite;
	
	//"Back" Button
	private SFButtonS back;
	private String schBack;
	
	//Fonts
	private static BitmapFont text;
	
	//Page
	private int page = 1;
	private SFButtonS next;
	private SFButtonS prev;
	
	//Rockets
	private SFButtonS rocketBall;
	private SFButtonS rocketCircle;
	private SFButtonS rocketBasic;
	
	public HangarPanelScreen(Game game){
		this.game = game;
	}
	
	@Override
	public void show() {

		batch = new SpriteBatch();
		controller = new SFlightInputController();
		
		MainMenu.music.play();
		
		backgroundTexture = new Texture("bckgrnd/hangar/hangar_1.png");
		backgroundSprite = new Sprite(backgroundTexture);
		backgroundSprite.setBounds(0.0F, 0.0F, width, height);
		schBack = "bckgrnd/hangar/hangar_1.png";
		
		SFButtonsInit();

		FreeTypeFontGenerator genUS = new FreeTypeFontGenerator(Gdx.files.internal("fonts/prototype.ttf"));
		FreeTypeFontGenerator genRU = new FreeTypeFontGenerator(Gdx.files.internal("fonts/9840.otf"));
		FreeTypeFontParameter param2 = new FreeTypeFontParameter();
		param2.color = Color.WHITE;
		param2.size = 40;
		if(InfoAndStats.lngRussian){
			param2.characters = FONT_CHARS_RU;
			text = genRU.generateFont(param2);
		}else{
			text = genUS.generateFont(param2);
		}
		text.getData().setScale((float)(0.00075F*width));
		
		genRU.dispose();
		genUS.dispose();
	}

	@Override
	public void render(float delta) {
		InfoAndStats.elapsedTime++;
		
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		drawBackground();
		
		batch.begin();
		
		backgroundSprite.draw(batch);

		drawBackButton();
		drawNextPrev();
		drawRockets();
		
		if(!InfoAndStats.lngRussian){
			text.draw(batch, "Control panel", 0.4F*width, 0.965F*height);
			text.draw(batch, "Page: " + page, 0.765F*width, 0.175F*height);
		}else{
			text.draw(batch, "Панель управления", 0.35F*width, 0.965F*height);
			text.draw(batch, "Страница: " + page, 0.72F*width, 0.175F*height);
		}
		
		batch.end();
		
		buttonListener();
		hangarsCheck();
		
	}
	
	private void SFButtonsInit(){
		back = new SFButtonS("btns/back", 0.132F*width, width - 0.2F*width, -0.005F*height);
		next = new SFButtonS("btns/button", 0.1F*width, width - 0.185F*width, 0.2F*height);
		next.getSprite().setColor(Color.LIME);
		prev = new SFButtonS("btns/button", 0.1F*width, width - 0.3F*width, 0.2F*height);
		prev.getSprite().setColor(Color.LIME);
		/***/
		rocketBall = new SFButtonS("rockets/rocketBall", 0.1F*width, 0.115F*width, 0.5F*height);
		rocketBall.setY(0.5F*height - 0.5F*rocketBall.getHeight());
		rocketCircle = new SFButtonS("rockets/rocketCircle", 0.1F*width, 0.265F*width, 0.5F*height);
		rocketCircle.setY(0.5F*height - 0.5F*rocketCircle.getHeight());
		rocketBasic = new SFButtonS("rockets/rocketBasic", 0.059262771F*width, 0.415F*width, 0.5F*height);
		rocketBasic.setY(0.5F*height - 0.5F*rocketBasic.getHeight());
	}
	
	private void drawBackground(){
		if(InfoAndStats.elapsedTime % 15 == 0){
			backgroundSprite.setTexture(new Texture(schBack));
			if(schBack.equals("bckgrnd/hangar/hangar_1.png")) schBack = "bckgrnd/hangar/hangar_2.png";
			else if(schBack.equals("bckgrnd/hangar/hangar_2.png")) schBack = "bckgrnd/hangar/hangar_3.png";
			else if(schBack.equals("bckgrnd/hangar/hangar_3.png")) schBack = "bckgrnd/hangar/hangar_4.png";
			else if(schBack.equals("bckgrnd/hangar/hangar_4.png")) schBack = "bckgrnd/hangar/hangar_5.png";
			else if(schBack.equals("bckgrnd/hangar/hangar_5.png")) schBack = "bckgrnd/hangar/hangar_6.png";
			else if(schBack.equals("bckgrnd/hangar/hangar_6.png")) schBack = "bckgrnd/hangar/hangar_7.png";
			else if(schBack.equals("bckgrnd/hangar/hangar_7.png")) schBack = "bckgrnd/hangar/hangar_8.png";
			else if(schBack.equals("bckgrnd/hangar/hangar_8.png")) schBack = "bckgrnd/hangar/hangar_9.png";
			else if(schBack.equals("bckgrnd/hangar/hangar_9.png")) schBack = "bckgrnd/hangar/hangar_10.png";
			else if(schBack.equals("bckgrnd/hangar/hangar_10.png")) schBack = "bckgrnd/hangar/hangar_11.png";
			else if(schBack.equals("bckgrnd/hangar/hangar_11.png")) schBack = "bckgrnd/hangar/hangar_12.png";
			else if(schBack.equals("bckgrnd/hangar/hangar_12.png")) schBack = "bckgrnd/hangar/hangar_13.png";
			else if(schBack.equals("bckgrnd/hangar/hangar_13.png")) schBack = "bckgrnd/hangar/hangar_1.png";
		}
	}
	private void drawBackButton(){
		if(controller.isOn(back.getX(), back.getY(), back.getWidth(), back.getHeight())){
			back.setMode(true);
		}else{
			back.setMode(false);
		}
		back.getSprite().draw(batch);
	}
	private void drawNextPrev(){
		if(page == 1) prev.getSprite().setColor(Color.FOREST);
		else prev.getSprite().setColor(Color.LIME);
		if(page == 5) next.getSprite().setColor(Color.FOREST);
		else next.getSprite().setColor(Color.LIME);
		if(controller.isOn(next.getX(), next.getY(), next.getWidth(), next.getHeight()) && page != 5){
			next.setMode(true);
		}else{
			next.setMode(false);
		}
		if(controller.isOn(prev.getX(), prev.getY(), prev.getWidth(), prev.getHeight()) && page != 1){
			prev.setMode(true);
		}else{
			prev.setMode(false);
		}
		next.getSprite().draw(batch);
		prev.getSprite().draw(batch);
		/***/
		text.getData().setScale((float)(0.0015F*width));
		if(!next.isActiveMode())
			text.draw(batch, ">", next.getX() + 0.3F*next.getWidth(), next.getY() + 0.85F*next.getHeight());
		else
			text.draw(batch, ">", next.getX() + 0.3F*next.getWidth(), next.getY() + 0.8F*next.getHeight());
		if(!prev.isActiveMode())
			text.draw(batch, "<", prev.getX() + 0.3F*prev.getWidth(), prev.getY() + 0.85F*prev.getHeight());
		else
			text.draw(batch, "<", prev.getX() + 0.3F*prev.getWidth(), prev.getY() + 0.8F*prev.getHeight());
		text.getData().setScale((float)(0.00075F*width));
	}
	private void drawRockets(){
		if(page == 1){
			if(controller.isClicked(rocketBall.getX(), rocketBall.getY(), rocketBall.getWidth(), rocketBall.getHeight())){
				if(rocketBall.isActiveMode()) rocketBall.setMode(false);
				else{
					rocketBall.setMode(true);
					rocketCircle.setMode(false);
					rocketBasic.setMode(false);
				}
			}
			if(rocketBall.isActiveMode()){
				if(!InfoAndStats.lngRussian){
					text.draw(batch, "Name: " + InfoAndStats.rocketBall.getNameUS(), 0.55F*width, 0.825F*height);
					text.draw(batch, "HP: " + InfoAndStats.rocketBall.getHp(), 0.55F*width, 0.825F*height - 1.5F*text.getCapHeight());
					text.draw(batch, "Resorurses' extraction: ", 0.55F*width, 0.825F*height - 3.0F*text.getCapHeight());
					text.draw(batch, InfoAndStats.rocketBall.getSpeedC() + " cosmocoins per 60 sec", 0.55F*width, 0.825F*height - 4.5F*text.getCapHeight());
					text.draw(batch, InfoAndStats.rocketBall.getSpeedF() + " fuel per 60 sec", 0.55F*width, 0.825F*height - 6.0F*text.getCapHeight());
					text.draw(batch, InfoAndStats.rocketBall.getSpeedM() + " metal per 60 sec", 0.55F*width, 0.825F*height - 7.5F*text.getCapHeight());
					text.draw(batch, "Rocket's price: " + InfoAndStats.rocketBall.getCost() + " metal", 0.55F*width, 0.825F*height - 9.0F*text.getCapHeight());
				}else{
					text.draw(batch, "Название: " + InfoAndStats.rocketBall.getNameRU(), 0.55F*width, 0.825F*height);
					text.draw(batch, "НР: " + InfoAndStats.rocketBall.getHp(), 0.55F*width, 0.825F*height - 1.5F*text.getCapHeight());
					text.draw(batch, "Добыча ресурсов: ", 0.55F*width, 0.825F*height - 3.0F*text.getCapHeight());
					text.draw(batch, InfoAndStats.rocketBall.getSpeedC() + " космокоинов за 60 сек", 0.55F*width, 0.825F*height - 4.5F*text.getCapHeight());
					text.draw(batch, InfoAndStats.rocketBall.getSpeedF() + " топлива за 60 сек", 0.55F*width, 0.825F*height - 6.0F*text.getCapHeight());
					text.draw(batch, InfoAndStats.rocketBall.getSpeedM() + " металла за 60 сек", 0.55F*width, 0.825F*height - 7.5F*text.getCapHeight());
					text.draw(batch, "Цена ракеты: " + InfoAndStats.rocketBall.getCost() + " металла", 0.55F*width, 0.825F*height - 9.0F*text.getCapHeight());
				}
			}
			rocketBall.getSprite().draw(batch);
			/***/
			if(controller.isClicked(rocketCircle.getX(), rocketCircle.getY(), rocketCircle.getWidth(), rocketCircle.getHeight())){
				if(rocketCircle.isActiveMode()) rocketCircle.setMode(false);
				else{
					rocketBall.setMode(false);
					rocketCircle.setMode(true);
					rocketBasic.setMode(false);
				}
			}
			if(rocketCircle.isActiveMode()){
				if(!InfoAndStats.lngRussian){
					text.draw(batch, "Name: " + InfoAndStats.rocketCircle.getNameUS(), 0.55F*width, 0.825F*height);
					text.draw(batch, "HP: " + InfoAndStats.rocketCircle.getHp(), 0.55F*width, 0.825F*height - 1.5F*text.getCapHeight());
					text.draw(batch, "Cosmocoin extraction: ", 0.55F*width, 0.825F*height - 3.0F*text.getCapHeight());
					text.draw(batch, InfoAndStats.rocketCircle.getSpeedC() + " per 60 sec", 0.75F*width, 0.825F*height - 4.5F*text.getCapHeight());
					text.draw(batch, "Fuel extraction: ", 0.55F*width, 0.825F*height - 6.0F*text.getCapHeight());
					text.draw(batch, InfoAndStats.rocketCircle.getSpeedF() + " per 60 sec", 0.75F*width, 0.825F*height - 7.5F*text.getCapHeight());
					text.draw(batch, "Metal extraction: ", 0.55F*width, 0.825F*height - 9.0F*text.getCapHeight());
					text.draw(batch, InfoAndStats.rocketCircle.getSpeedM() + " per 60 sec", 0.75F*width, 0.825F*height - 10.5F*text.getCapHeight());
					text.draw(batch, "Rocket's price: " + InfoAndStats.rocketCircle.getCost() + " metal", 0.55F*width, 0.825F*height - 12.0F*text.getCapHeight());
				}else{
					text.draw(batch, "Название: " + InfoAndStats.rocketCircle.getNameRU(), 0.55F*width, 0.825F*height);
					text.draw(batch, "НР: " + InfoAndStats.rocketCircle.getHp(), 0.55F*width, 0.825F*height - 1.5F*text.getCapHeight());
					text.draw(batch, "Добыча космокоинов: ", 0.55F*width, 0.825F*height - 3.0F*text.getCapHeight());
					text.draw(batch, InfoAndStats.rocketCircle.getSpeedC() + " за 60 сек", 0.75F*width, 0.825F*height - 4.5F*text.getCapHeight());
					text.draw(batch, "Добыча топлива: ", 0.55F*width, 0.825F*height - 6.0F*text.getCapHeight());
					text.draw(batch, InfoAndStats.rocketCircle.getSpeedF() + " за 60 сек", 0.75F*width, 0.825F*height - 7.5F*text.getCapHeight());
					text.draw(batch, "Добыча металла: ", 0.55F*width, 0.825F*height - 9.0F*text.getCapHeight());
					text.draw(batch, InfoAndStats.rocketCircle.getSpeedM() + " за 60 сек", 0.75F*width, 0.825F*height - 10.5F*text.getCapHeight());
					text.draw(batch, "Цена ракеты: " + InfoAndStats.rocketCircle.getCost() + " металла", 0.55F*width, 0.825F*height - 12.0F*text.getCapHeight());
				}
			}
			rocketCircle.getSprite().draw(batch);
			/***/
			if(controller.isClicked(rocketBasic.getX(), rocketBasic.getY(), rocketBasic.getWidth(), rocketBasic.getHeight())){
				if(rocketBasic.isActiveMode()) rocketBasic.setMode(false);
				else{
					rocketBall.setMode(false);
					rocketCircle.setMode(false);
					rocketBasic.setMode(true);
				}
			}
			if(rocketBasic.isActiveMode()){
				if(!InfoAndStats.lngRussian){
					text.draw(batch, "Name: " + InfoAndStats.rocketBasic.getNameUS(), 0.55F*width, 0.825F*height);
					text.draw(batch, "HP: " + InfoAndStats.rocketBasic.getHp(), 0.55F*width, 0.825F*height - 1.5F*text.getCapHeight());
					text.draw(batch, "Cosmocoin extraction: ", 0.55F*width, 0.825F*height - 3.0F*text.getCapHeight());
					text.draw(batch, InfoAndStats.rocketBasic.getSpeedC() + " per 60 sec", 0.75F*width, 0.825F*height - 4.5F*text.getCapHeight());
					text.draw(batch, "Fuel extraction: ", 0.55F*width, 0.825F*height - 6.0F*text.getCapHeight());
					text.draw(batch, InfoAndStats.rocketBasic.getSpeedF() + " per 60 sec", 0.75F*width, 0.825F*height - 7.5F*text.getCapHeight());
					text.draw(batch, "Metal extraction: ", 0.55F*width, 0.825F*height - 9.0F*text.getCapHeight());
					text.draw(batch, InfoAndStats.rocketBasic.getSpeedM() + " per 60 sec", 0.75F*width, 0.825F*height - 10.5F*text.getCapHeight());
					text.draw(batch, "Rocket's price: " + InfoAndStats.rocketBasic.getCost() + " metal", 0.55F*width, 0.825F*height - 12.0F*text.getCapHeight());
				}else{
					text.draw(batch, "Название: " + InfoAndStats.rocketBasic.getNameRU(), 0.55F*width, 0.825F*height);
					text.draw(batch, "НР: " + InfoAndStats.rocketBasic.getHp(), 0.55F*width, 0.825F*height - 1.5F*text.getCapHeight());
					text.draw(batch, "Добыча космокоинов: ", 0.55F*width, 0.825F*height - 3.0F*text.getCapHeight());
					text.draw(batch, InfoAndStats.rocketBasic.getSpeedC() + " за 60 сек", 0.75F*width, 0.825F*height - 4.5F*text.getCapHeight());
					text.draw(batch, "Добыча топлива: ", 0.55F*width, 0.825F*height - 6.0F*text.getCapHeight());
					text.draw(batch, InfoAndStats.rocketBasic.getSpeedF() + " за 60 сек", 0.75F*width, 0.825F*height - 7.5F*text.getCapHeight());
					text.draw(batch, "Добыча металла: ", 0.55F*width, 0.825F*height - 9.0F*text.getCapHeight());
					text.draw(batch, InfoAndStats.rocketBasic.getSpeedM() + " за 60 сек", 0.75F*width, 0.825F*height - 10.5F*text.getCapHeight());
					text.draw(batch, "Цена ракеты: " + InfoAndStats.rocketBasic.getCost() + " металла", 0.55F*width, 0.825F*height - 12.0F*text.getCapHeight());
				}
			}
			rocketBasic.getSprite().draw(batch);
			/***/
		}
	}
	
	private void buttonListener(){
		if(controller.isClicked(back.getX(), back.getY(), back.getWidth(), back.getHeight())){
			game.setScreen(new AngarScreen(game));
			this.dispose();
		}
		if(controller.isClicked(next.getX(), next.getY(), next.getWidth(), next.getHeight())){
			page++;
		}
		if(controller.isClicked(prev.getX(), prev.getY(), prev.getWidth(), prev.getHeight())){
			page--;
		}
	}
	
	private void hangarsCheck(){
		if(InfoAndStats.elapsedTime%(3600/InfoAndStats.moneyAmount) == 0){
			InfoAndStats.money++;
		}
		if(InfoAndStats.elapsedTime%(3600/InfoAndStats.fuelAmount) == 60){
			InfoAndStats.fuel++;
		}
		if(InfoAndStats.elapsedTime%(3600/InfoAndStats.metalAmount) == 120){
			InfoAndStats.metal++;
		}
		if(InfoAndStats.money>InfoAndStats.moneyFull) InfoAndStats.money = InfoAndStats.moneyFull;
		if(InfoAndStats.fuel>InfoAndStats.fuelFull) InfoAndStats.fuel = InfoAndStats.fuelFull;
		if(InfoAndStats.metal>InfoAndStats.metalFull) InfoAndStats.metal = InfoAndStats.metalFull;
		/***/
		if(page < 1) page = 1;
		if(page > 5) page = 5;
		/***/
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

	@Override
	public void dispose() {
		backgroundTexture.dispose();
		game.dispose();
		batch.dispose();
		text.dispose();
	}

}