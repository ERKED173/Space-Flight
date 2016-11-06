package ru.erked.sflight.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

import ru.erked.sflight.StartSFlight;
import ru.erked.sflight.controllers.SFlightInputController;
import ru.erked.sflight.menu.MainMenu;
import ru.erked.sflight.random.INF;
import ru.erked.sflight.tech.SFButtonS;

public class ArmoryPanelScreen implements Screen{

	final String FONT_CHARS_RU = "абвгдежзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
	private static final float width = Gdx.graphics.getWidth();
	private static final float height = Gdx.graphics.getHeight();
	
	private final StartSFlight game;
	private SFlightInputController controller;
	
	//Background
	private Texture backgroundTexture;
	public static Sprite backgroundSprite;
	
	//"Back" Button
	private SFButtonS back;
	private String schBack;
	
	//Coin
	private Sprite coinIcon;
	
	//Fonts
	private BitmapFont text;
	
	//Guns
	public static SFButtonS gunGeneric;
	
	//Page
	private int page = 1;
	private SFButtonS next;
	private SFButtonS prev;
	private SFButtonS buyGun;
	
	public ArmoryPanelScreen(final StartSFlight game){
		this.game = game;
	}
	
	@Override
	public void show() {

		controller = new SFlightInputController();
		
		MainMenu.music.play();
		
		backgroundTexture = new Texture("bckgrnd/hangar/hangar_1.png");
		backgroundSprite = new Sprite(backgroundTexture);
		backgroundSprite.setBounds(0.0F, 0.0F, width, height);
		schBack = "bckgrnd/hangar/hangar_1.png";
		
		coinIcon = new Sprite(new Texture("objects/cosmocoin.png"));
		coinIcon.setBounds(0.095F*width, 0.325F*height, 0.1F*height, 0.1F*height);
		
		gunsInit();
		SFButtonsInit();
		
		FreeTypeFontGenerator genUS = new FreeTypeFontGenerator(Gdx.files.internal("fonts/prototype.ttf"));
		FreeTypeFontGenerator genRU = new FreeTypeFontGenerator(Gdx.files.internal("fonts/9840.otf"));
		FreeTypeFontParameter param = new FreeTypeFontParameter();
		param.color = Color.WHITE;
		param.size = 40;
		if(INF.lngRussian){
			param.characters = FONT_CHARS_RU;
			text = genRU.generateFont(param);
			text.getData().setScale((float)(0.001F*height));
		}else{
			text = genUS.generateFont(param);
			text.getData().setScale((float)(0.00115F*height));
		}
		
		genRU.dispose();
		genUS.dispose();
	}

	@Override
	public void render(float delta) {
		INF.elapsedTime++;
		resourceCheck();
		
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		drawBackground();
		
		game.batch.begin();
		
		backgroundSprite.draw(game.batch);

		coinIcon.draw(game.batch);
		text.draw(game.batch, ": " + INF.money, coinIcon.getX() + 1.1F*coinIcon.getWidth(), coinIcon.getY() + 0.65F*coinIcon.getHeight());
		
		drawBackButton();
		drawNextPrev();
		drawBuyButton();
		drawGuns();
		
		if(!INF.lngRussian){
			text.draw(game.batch, "Space guns' panel", 0.4F*width, 0.965F*height);
			text.draw(game.batch, "Page: " + page, 0.765F*width, 0.175F*height);
		}else{
			text.draw(game.batch, "Панель космооружия", 0.35F*width, 0.965F*height);
			text.draw(game.batch, "Страница: " + page, 0.72F*width, 0.175F*height);
		}
		
		game.batch.end();
		
		buttonListener();
		
	}
	
	private void SFButtonsInit(){
		if(!INF.lngRussian){
			back = new SFButtonS("btns/back", 0.132F*width, width - 0.2F*width, -0.005F*height, 1.0F);
		}else{
			back = new SFButtonS("btns/RU/backR", 0.132F*width, width - 0.2F*width, -0.005F*height, 1.0F);
		}
		next = new SFButtonS("btns/button", 0.1F*width, 0.815F*width, 0.2F*height, 1.0F);
		next.getSprite().setColor(Color.LIME);
		prev = new SFButtonS("btns/button", 0.1F*width, 0.7F*width, 0.2F*height, 1.0F);
		prev.getSprite().setColor(Color.LIME);
		buyGun = new SFButtonS("btns/button", 0.125F*width, 0.1F*width, 0.15F*height, 1.0F);
		buyGun.getSprite().setColor(Color.LIME);
	}
	
	
	private void gunsInit(){
		gunGeneric = new SFButtonS("guns/guns/gun_1", 0.15F*height, 0.1F*width, 0.65F*height, 1.0F);
		/***/
	}
	
	private void drawGuns(){
		if(page==1){
			if(controller.isClicked(gunGeneric.getX(), gunGeneric.getY(), gunGeneric.getWidth(), gunGeneric.getHeight())){
				if(gunGeneric.isActiveMode()) gunGeneric.setMode(false);
				else{
					gunGeneric.setMode(true);
				}
			}
			if(gunGeneric.isActiveMode()){
				if(!INF.lngRussian){
					text.draw(game.batch, "Name: " + INF.gunGeneric.getNameUS(), 0.625F*width, 0.825F*height);
					text.draw(game.batch, "Damage: " + INF.gunGeneric.getDamage() + " units", 0.625F*width, 0.825F*height - 1.5F*text.getCapHeight());
					text.draw(game.batch, "Fire rate: " + INF.gunGeneric.getSpeed(), 0.625F*width, 0.825F*height - 3.0F*text.getCapHeight());
					text.draw(game.batch, "Price: " + INF.gunGeneric.getCost() + " cosmocoins", 0.625F*width, 0.825F*height - 4.5F*text.getCapHeight());
				}else{
					text.draw(game.batch, "Название: " + INF.gunGeneric.getNameRU(), 0.55F*width, 0.825F*height);
					text.draw(game.batch, "Урон: " + INF.gunGeneric.getDamage() + " едениц", 0.55F*width, 0.825F*height - 1.5F*text.getCapHeight());
					text.draw(game.batch, "Скорострельность: " + INF.gunGeneric.getSpeed(), 0.55F*width, 0.825F*height - 3.0F*text.getCapHeight());
					text.draw(game.batch, "Цена: " + INF.gunGeneric.getCost() + " космокоинов", 0.55F*width, 0.825F*height - 4.5F*text.getCapHeight());
				}
			}
			if(INF.currentGun.equals("generic-1")){
				if(!INF.lngRussian){
					text.draw(game.batch, "Selected", gunGeneric.getX() - 0.0F*gunGeneric.getWidth(), gunGeneric.getY() - 0.5F*gunGeneric.getHeight());
				}else{
					text.draw(game.batch, "Выбрано", gunGeneric.getX() - 0.0F*gunGeneric.getWidth(), gunGeneric.getY() - 0.5F*gunGeneric.getHeight());
				}
			}
			gunGeneric.getSprite().draw(game.batch);
		}
	}
	private void drawBackground(){
		if(INF.elapsedTime % 15 == 0){
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
		back.getSprite().draw(game.batch);
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
		next.getSprite().draw(game.batch);
		prev.getSprite().draw(game.batch);
		buyGun.getSprite().draw(game.batch);
		/***/
		text.getData().setScale((float)(0.0015F*width));
		if(!next.isActiveMode())
			text.draw(game.batch, ">", next.getX() + 0.3F*next.getWidth(), next.getY() + 0.85F*next.getHeight());
		else
			text.draw(game.batch, ">", next.getX() + 0.3F*next.getWidth(), next.getY() + 0.8F*next.getHeight());
		if(!prev.isActiveMode())
			text.draw(game.batch, "<", prev.getX() + 0.3F*prev.getWidth(), prev.getY() + 0.85F*prev.getHeight());
		else
			text.draw(game.batch, "<", prev.getX() + 0.3F*prev.getWidth(), prev.getY() + 0.8F*prev.getHeight());
		text.getData().setScale((float)(0.00125F*height));
		/***/
	}
	private void drawBuyButton(){
		if(INF.currentGun.equals("null")){
			if(page==1){
				if(gunGeneric.isActiveMode() && INF.money >= INF.gunGeneric.getCost()){
					buyGun.getSprite().setColor(Color.LIME);
					if(controller.isOn(buyGun.getX(), buyGun.getY(), buyGun.getWidth(), buyGun.getHeight())){
						buyGun.setMode(true);
					}else{
						buyGun.setMode(false);
					}
				}else{
					buyGun.getSprite().setColor(Color.FOREST);
				}
			}else if(page==2){

			}else{
				buyGun.getSprite().setColor(Color.FOREST);
			}
		}else{
			buyGun.getSprite().setColor(Color.FOREST);
		}
		/***/
		if(!INF.lngRussian){
			if(buyGun.isActiveMode())
				text.draw(game.batch, "Buy", buyGun.getX() + 0.275F*buyGun.getWidth(), buyGun.getY() + 0.625F*buyGun.getHeight());
			else
				text.draw(game.batch, "Buy", buyGun.getX() + 0.275F*buyGun.getWidth(), buyGun.getY() + 0.65F*buyGun.getHeight());
		}else{
			if(buyGun.isActiveMode())
				text.draw(game.batch, "Купить", buyGun.getX() + 0.075F*buyGun.getWidth(), buyGun.getY() + 0.625F*buyGun.getHeight());
			else
				text.draw(game.batch, "Купить", buyGun.getX() + 0.075F*buyGun.getWidth(), buyGun.getY() + 0.65F*buyGun.getHeight());
		}
	}
	
	private void buttonListener(){
		if(controller.isClicked(back.getX(), back.getY(), back.getWidth(), back.getHeight())){
			game.setScreen(new ArmoryScreen(game));
			this.dispose();
		}
		if(controller.isClicked(next.getX(), next.getY(), next.getWidth(), next.getHeight())){
			page++;
		}
		if(controller.isClicked(prev.getX(), prev.getY(), prev.getWidth(), prev.getHeight())){
			page--;
		}
		if(controller.isClicked(buyGun.getX(), buyGun.getY(), buyGun.getWidth(), buyGun.getHeight()) && (INF.currentGun.equals("null"))){
			if(gunGeneric.isActiveMode() && INF.money>=INF.gunGeneric.getCost()){
				INF.money -= INF.gunGeneric.getCost();
				INF.currentGun = "generic-1";
			}
		}
	}
	
	private void resourceCheck(){
		if(INF.elapsedTime%(3600/INF.moneyAmount) == 0){
			INF.money++;
		}
		if(INF.elapsedTime%(3600/INF.fuelAmount) == 60){
			INF.fuel++;
		}
		if(INF.elapsedTime%(3600/INF.metalAmount) == 120){
			INF.metal++;
		}
		if(INF.money>INF.moneyFull) INF.money = INF.moneyFull;
		if(INF.fuel>INF.fuelFull) INF.fuel = INF.fuelFull;
		if(INF.metal>INF.metalFull) INF.metal = INF.metalFull;
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

	private void texturesDispose(){
		gunGeneric.getSprite().getTexture().dispose();
		coinIcon.getTexture().dispose();
		back.getSprite().getTexture().dispose();
		next.getSprite().getTexture().dispose();
		prev.getSprite().getTexture().dispose();
		buyGun.getSprite().getTexture().dispose();
		backgroundTexture.dispose();
	}
	
	@Override
	public void dispose() {
		gunGeneric.setMode(false);
		texturesDispose();
		text.dispose();
	}

}