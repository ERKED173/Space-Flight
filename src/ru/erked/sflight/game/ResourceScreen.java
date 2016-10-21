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

public class ResourceScreen implements Screen{

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
	
	//Upgrade buttons
	private SFButtonS upC;
	private SFButtonS upF;
	private SFButtonS upM;
	
	//Resources
	private Sprite moneySprite;
	private Sprite fuelSprite;
	private Sprite metalSprite;
	
	//Fonts
	private static BitmapFont text;
	private static BitmapFont textBtn;
	
	public ResourceScreen(Game game){
		this.game = game;
	}
	
	@Override
	public void show() {

		batch = new SpriteBatch();
		controller = new SFlightInputController();
		
		MainMenu.music.play();
		
		backgroundTexture = new Texture("bckgrnd/resource/resource_1.png");
		backgroundSprite = new Sprite(backgroundTexture);
		backgroundSprite.setBounds(0.0F, 0.0F, width, height);
		schBack = "bckgrnd/resource/resource_1.png";
		
		back = new SFButtonS("btns/back", 0.132F*width, width - 0.2F*width, -0.005F*height);

		FreeTypeFontGenerator genUS = new FreeTypeFontGenerator(Gdx.files.internal("fonts/prototype.ttf"));
		FreeTypeFontGenerator genRU = new FreeTypeFontGenerator(Gdx.files.internal("fonts/9840.otf"));
		FreeTypeFontParameter param = new FreeTypeFontParameter();
		FreeTypeFontParameter param2 = new FreeTypeFontParameter();
		param.color = Color.WHITE;
		param.size = 40;
		param2.color = Color.WHITE;
		param2.size = 40;
		if(InfoAndStats.lngRussian){
			param.characters = FONT_CHARS_RU;
			param2.characters = FONT_CHARS_RU;
			text = genRU.generateFont(param);
			textBtn = genRU.generateFont(param2);
			textBtn.getData().setScale((float)(0.0006F*width));
		}else{
			text = genUS.generateFont(param);
			textBtn = genUS.generateFont(param2);
			textBtn.getData().setScale((float)(0.00075F*width));
		}
		text.getData().setScale((float)(0.00075F*width));
		
		resourcesInit();
		
		upC = new SFButtonS("btns/button", 0.15F*width, 0.55F*width, 0.675F*height);
		upC.getSprite().setColor(Color.TEAL);
		upF = new SFButtonS("btns/button", 0.15F*width, 0.55F*width, 0.475F*height);
		upF.getSprite().setColor(Color.TEAL);
		upM = new SFButtonS("btns/button", 0.15F*width, 0.55F*width, 0.275F*height);
		upM.getSprite().setColor(Color.TEAL);
		
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
		
		if(controller.isOn(back.getX(), back.getY(), back.getWidth(), back.getHeight())){
			back.setMode(true);
		}else{
			back.setMode(false);
		}
		back.getSprite().draw(batch);

		if(!InfoAndStats.lngRussian){
			text.draw(batch, "Information about resources", 0.315F*width, 0.965F*height);
			text.draw(batch, InfoAndStats.money + "/" + InfoAndStats.moneyFull + " cosmocoins", 0.125F*width, 0.8F*height - 0.0F*text.getCapHeight());
			text.draw(batch, 60/(60/InfoAndStats.moneyAmount) + " cosmocoins per 60 sec", 0.125F*width, 0.8F*height - 1.5F*text.getCapHeight());
			text.draw(batch, InfoAndStats.fuel + "/" + InfoAndStats.fuelFull + " fuel", 0.125F*width, 0.8F*height - 6.0F*text.getCapHeight());
			text.draw(batch, 60/(60/InfoAndStats.fuelAmount) + " fuel per 60 sec", 0.125F*width, 0.8F*height - 7.5F*text.getCapHeight());
			text.draw(batch, InfoAndStats.metal + "/" + InfoAndStats.metalFull + " metal", 0.125F*width, 0.8F*height - 12.0F*text.getCapHeight());
			text.draw(batch, 60/(60/InfoAndStats.metalAmount) + " metal per 60 sec", 0.125F*width, 0.8F*height - 13.5F*text.getCapHeight());
		}else{
			text.draw(batch, "Информация о ресурсах", 0.315F*width, 0.965F*height);
			text.draw(batch, InfoAndStats.money + "/" + InfoAndStats.moneyFull + " космокоинов", 0.125F*width, 0.8F*height - 0.0F*text.getCapHeight());
			text.draw(batch, 60/(60/InfoAndStats.moneyAmount) + " космокоинов за 60 сек", 0.125F*width, 0.8F*height - 1.5F*text.getCapHeight());
			text.draw(batch, InfoAndStats.fuel + "/" + InfoAndStats.fuelFull + " топлива", 0.125F*width, 0.8F*height - 6.0F*text.getCapHeight());
			text.draw(batch, 60/(60/InfoAndStats.fuelAmount) + " топлива за 60 сек", 0.125F*width, 0.8F*height - 7.5F*text.getCapHeight());
			text.draw(batch, InfoAndStats.metal + "/" + InfoAndStats.metalFull + " металла", 0.125F*width, 0.8F*height - 12.0F*text.getCapHeight());
			text.draw(batch, 60/(60/InfoAndStats.metalAmount) + " металла за 60 сек", 0.125F*width, 0.8F*height - 13.5F*text.getCapHeight());
		}
		
		drawResources();
		drawUpBtns();
		
		batch.end();
		
		btnListener();
		resourcesCheck();
		
	}

	private void resourcesInit(){
		moneySprite = new Sprite(new Texture("objects/cosmocoin.png"));
		fuelSprite = new Sprite(new Texture("objects/fuel.png"));
		metalSprite = new Sprite(new Texture("objects/metal.png"));
		moneySprite.setBounds(0.025F*width, 0.8F*height - 3.0F*text.getCapHeight(), 0.08F*width, 0.08F*width);
		fuelSprite.setBounds(0.025F*width, 0.8F*height - 9.0F*text.getCapHeight(), 0.08F*width, 0.08F*width);
		metalSprite.setBounds(0.025F*width, 0.8F*height - 15.0F*text.getCapHeight(), 0.08F*width, 0.08F*width);
	}

	
	private void drawBackground(){
		if(InfoAndStats.elapsedTime % 15 == 0){
			backgroundSprite.setTexture(new Texture(schBack));
			if(schBack.equals("bckgrnd/resource/resource_1.png")) schBack = "bckgrnd/resource/resource_2.png";
			else if(schBack.equals("bckgrnd/resource/resource_2.png")) schBack = "bckgrnd/resource/resource_3.png";
			else if(schBack.equals("bckgrnd/resource/resource_3.png")) schBack = "bckgrnd/resource/resource_4.png";
			else if(schBack.equals("bckgrnd/resource/resource_4.png")) schBack = "bckgrnd/resource/resource_5.png";
			else if(schBack.equals("bckgrnd/resource/resource_5.png")) schBack = "bckgrnd/resource/resource_6.png";
			else if(schBack.equals("bckgrnd/resource/resource_6.png")) schBack = "bckgrnd/resource/resource_7.png";
			else if(schBack.equals("bckgrnd/resource/resource_7.png")) schBack = "bckgrnd/resource/resource_8.png";
			else if(schBack.equals("bckgrnd/resource/resource_8.png")) schBack = "bckgrnd/resource/resource_9.png";
			else if(schBack.equals("bckgrnd/resource/resource_9.png")) schBack = "bckgrnd/resource/resource_10.png";
			else if(schBack.equals("bckgrnd/resource/resource_10.png")) schBack = "bckgrnd/resource/resource_11.png";
			else if(schBack.equals("bckgrnd/resource/resource_11.png")) schBack = "bckgrnd/resource/resource_12.png";
			else if(schBack.equals("bckgrnd/resource/resource_12.png")) schBack = "bckgrnd/resource/resource_13.png";
			else if(schBack.equals("bckgrnd/resource/resource_13.png")) schBack = "bckgrnd/resource/resource_1.png";
		}
	}
	private void drawResources(){
		moneySprite.draw(batch);
		fuelSprite.draw(batch);
		metalSprite.draw(batch);
	}
	private void drawUpBtns(){
		/***/
		upC.getSprite().draw(batch);
		if(controller.isOn(upC.getX(), upC.getY(), upC.getWidth(), upC.getHeight()) && !(upC.getSprite().getColor().equals(Color.TEAL))){
			upC.setMode(true);
			if(!InfoAndStats.lngRussian){
				textBtn.draw(batch, "Upgrade", upC.getX() + 0.125F*upC.getWidth(), upC.getY() + 0.575F*upC.getHeight());
			}else{
				textBtn.draw(batch, "Улучшить", upC.getX() + 0.115F*upC.getWidth(), upC.getY() + 0.575F*upC.getHeight());
			}
		}else{
			upC.setMode(false);
			if(!InfoAndStats.lngRussian){
				textBtn.draw(batch, "Upgrade", upC.getX() + 0.125F*upC.getWidth(), upC.getY() + 0.6F*upC.getHeight());
			}else{
				textBtn.draw(batch, "Улучшить", upC.getX() + 0.115F*upC.getWidth(), upC.getY() + 0.6F*upC.getHeight());
			}
		}
		upF.getSprite().draw(batch);
		if(controller.isOn(upF.getX(), upF.getY(), upF.getWidth(), upF.getHeight()) && !(upF.getSprite().getColor().equals(Color.TEAL))){
			upF.setMode(true);
			if(!InfoAndStats.lngRussian){
				textBtn.draw(batch, "Upgrade", upF.getX() + 0.125F*upF.getWidth(), upF.getY() + 0.575F*upF.getHeight());
			}else{
				textBtn.draw(batch, "Улучшить", upF.getX() + 0.115F*upF.getWidth(), upF.getY() + 0.575F*upF.getHeight());
			}
		}else{
			upF.setMode(false);
			if(!InfoAndStats.lngRussian){
				textBtn.draw(batch, "Upgrade", upF.getX() + 0.125F*upF.getWidth(), upF.getY() + 0.6F*upF.getHeight());
			}else{
				textBtn.draw(batch, "Улучшить", upF.getX() + 0.115F*upM.getWidth(), upF.getY() + 0.6F*upF.getHeight());
			}
		}
		upM.getSprite().draw(batch);
		if(controller.isOn(upM.getX(), upM.getY(), upM.getWidth(), upM.getHeight()) && !(upM.getSprite().getColor().equals(Color.TEAL))){
			upM.setMode(true);
			if(!InfoAndStats.lngRussian){
				textBtn.draw(batch, "Upgrade", upM.getX() + 0.125F*upM.getWidth(), upM.getY() + 0.575F*upM.getHeight());
			}else{
				textBtn.draw(batch, "Улучшить", upM.getX() + 0.115F*upM.getWidth(), upM.getY() + 0.575F*upM.getHeight());
			}
		}else{
			upM.setMode(false);
			if(!InfoAndStats.lngRussian){
				textBtn.draw(batch, "Upgrade", upM.getX() + 0.125F*upM.getWidth(), upM.getY() + 0.6F*upM.getHeight());
			}else{
				textBtn.draw(batch, "Улучшить", upM.getX() + 0.115F*upM.getWidth(), upM.getY() + 0.6F*upM.getHeight());
			}
		}
		/***/
		drawUpInfo();
		/***/
	}
	private void drawUpInfo(){
		/***/
		if(!InfoAndStats.lngRussian){
			text.draw(batch, "Cost:", upC.getX() + 1.1F*upC.getWidth(), upC.getY() + 0.95F*upC.getHeight());
			if(InfoAndStats.moneyLevel == 0){
				if(InfoAndStats.fuel < 10 || InfoAndStats.metal < 10){
					upC.getSprite().setColor(Color.TEAL);
				}else{
					upC.getSprite().setColor(Color.CYAN);
				}
				text.draw(batch, "10 metal", upC.getX() + 1.1F*upC.getWidth(), upC.getY() + 0.65F*upC.getHeight());
				text.draw(batch, "10 fuel", upC.getX() + 1.1F*upC.getWidth(), upC.getY() + 0.35F*upC.getHeight());
			}else if(InfoAndStats.moneyLevel == 1){
				if(InfoAndStats.fuel < 25 || InfoAndStats.metal < 25){
					upC.getSprite().setColor(Color.TEAL);
				}else{
					upC.getSprite().setColor(Color.CYAN);
				}
				text.draw(batch, "25 metal", upC.getX() + 1.1F*upC.getWidth(), upC.getY() + 0.65F*upC.getHeight());
				text.draw(batch, "25 fuel", upC.getX() + 1.1F*upC.getWidth(), upC.getY() + 0.35F*upC.getHeight());
			}else if(InfoAndStats.moneyLevel == 2){
				if(InfoAndStats.fuel < 50 || InfoAndStats.metal < 50){
					upC.getSprite().setColor(Color.TEAL);
				}else{
					upC.getSprite().setColor(Color.CYAN);
				}
				text.draw(batch, "50 metal", upC.getX() + 1.1F*upC.getWidth(), upC.getY() + 0.65F*upC.getHeight());
				text.draw(batch, "50 fuel", upC.getX() + 1.1F*upC.getWidth(), upC.getY() + 0.35F*upC.getHeight());
			}else if(InfoAndStats.moneyLevel == 3){
				if(InfoAndStats.fuel < 75 || InfoAndStats.metal < 75){
					upC.getSprite().setColor(Color.TEAL);
				}else{
					upC.getSprite().setColor(Color.CYAN);
				}
				text.draw(batch, "75 metal", upC.getX() + 1.1F*upC.getWidth(), upC.getY() + 0.65F*upC.getHeight());
				text.draw(batch, "75 fuel", upC.getX() + 1.1F*upC.getWidth(), upC.getY() + 0.35F*upC.getHeight());
			}else if(InfoAndStats.moneyLevel == 4){
				text.draw(batch, "MAX level", upM.getX() + 1.1F*upC.getWidth(), upC.getY() + 0.65F*upC.getHeight());
				upC.getSprite().setColor(Color.TEAL);
			}else{
				upC.getSprite().setColor(Color.CYAN);
			}
			////
			text.draw(batch, "Cost:", upF.getX() + 1.1F*upF.getWidth(), upF.getY() + 0.95F*upF.getHeight());
			if(InfoAndStats.fuelLevel == 0){
				if(InfoAndStats.money < 10 || InfoAndStats.metal < 10){
					upF.getSprite().setColor(Color.TEAL);
				}else{
					upF.getSprite().setColor(Color.CYAN);
				}
				text.draw(batch, "10 cosmocoins", upF.getX() + 1.1F*upF.getWidth(), upF.getY() + 0.65F*upF.getHeight());
				text.draw(batch, "10 metal", upF.getX() + 1.1F*upF.getWidth(), upF.getY() + 0.35F*upF.getHeight());
			}else if(InfoAndStats.fuelLevel == 1){
				if(InfoAndStats.money < 25 || InfoAndStats.metal < 25){
					upF.getSprite().setColor(Color.TEAL);
				}else{
					upF.getSprite().setColor(Color.CYAN);
				}
				text.draw(batch, "25 cosmocoins", upF.getX() + 1.1F*upF.getWidth(), upF.getY() + 0.65F*upF.getHeight());
				text.draw(batch, "25 metal", upF.getX() + 1.1F*upF.getWidth(), upF.getY() + 0.35F*upF.getHeight());
			}else if(InfoAndStats.fuelLevel == 2){
				if(InfoAndStats.money < 50 || InfoAndStats.metal < 50){
					upF.getSprite().setColor(Color.TEAL);
				}else{
					upF.getSprite().setColor(Color.CYAN);
				}
				text.draw(batch, "50 cosmocoins", upF.getX() + 1.1F*upF.getWidth(), upF.getY() + 0.65F*upF.getHeight());
				text.draw(batch, "50 metal", upF.getX() + 1.1F*upF.getWidth(), upF.getY() + 0.35F*upF.getHeight());
			}else if(InfoAndStats.fuelLevel == 3){
				if(InfoAndStats.money < 75 || InfoAndStats.metal < 75){
					upF.getSprite().setColor(Color.TEAL);
				}else{
					upF.getSprite().setColor(Color.CYAN);
				}
				text.draw(batch, "75 cosmocoins", upF.getX() + 1.1F*upF.getWidth(), upF.getY() + 0.65F*upF.getHeight());
				text.draw(batch, "75 metal", upF.getX() + 1.1F*upF.getWidth(), upF.getY() + 0.35F*upF.getHeight());
			}else if(InfoAndStats.fuelLevel == 4){
				text.draw(batch, "MAX level", upF.getX() + 1.1F*upF.getWidth(), upF.getY() + 0.65F*upF.getHeight());
				upF.getSprite().setColor(Color.TEAL);
			}else{
				upF.getSprite().setColor(Color.CYAN);
			}
			////
			text.draw(batch, "Cost:", upM.getX() + 1.1F*upM.getWidth(), upM.getY() + 0.95F*upM.getHeight());
			if(InfoAndStats.metalLevel == 0){
				if(InfoAndStats.money < 10 || InfoAndStats.fuel < 10){
					upM.getSprite().setColor(Color.TEAL);
				}else{
					upM.getSprite().setColor(Color.CYAN);
				}
				text.draw(batch, "10 cosmocoins", upM.getX() + 1.1F*upM.getWidth(), upM.getY() + 0.65F*upM.getHeight());
				text.draw(batch, "10 fuel", upM.getX() + 1.1F*upM.getWidth(), upM.getY() + 0.35F*upM.getHeight());
			}else if(InfoAndStats.metalLevel == 1){
				if(InfoAndStats.money < 25 || InfoAndStats.fuel < 25){
					upM.getSprite().setColor(Color.TEAL);
				}else{
					upM.getSprite().setColor(Color.CYAN);
				}
				text.draw(batch, "25 cosmocoins", upM.getX() + 1.1F*upM.getWidth(), upM.getY() + 0.65F*upM.getHeight());
				text.draw(batch, "25 fuel", upM.getX() + 1.1F*upM.getWidth(), upM.getY() + 0.35F*upM.getHeight());
			}else if(InfoAndStats.metalLevel == 2){
				if(InfoAndStats.money < 50 || InfoAndStats.fuel < 50){
					upM.getSprite().setColor(Color.TEAL);
				}else{
					upM.getSprite().setColor(Color.CYAN);
				}
				text.draw(batch, "50 cosmocoins", upM.getX() + 1.1F*upM.getWidth(), upM.getY() + 0.65F*upM.getHeight());
				text.draw(batch, "50 fuel", upM.getX() + 1.1F*upM.getWidth(), upM.getY() + 0.35F*upM.getHeight());
			}else if(InfoAndStats.metalLevel == 3){
				if(InfoAndStats.money < 75 || InfoAndStats.fuel < 75){
					upM.getSprite().setColor(Color.TEAL);
				}else{
					upM.getSprite().setColor(Color.CYAN);
				}
				text.draw(batch, "75 cosmocoins", upM.getX() + 1.1F*upM.getWidth(), upM.getY() + 0.65F*upM.getHeight());
				text.draw(batch, "75 fuel", upM.getX() + 1.1F*upM.getWidth(), upM.getY() + 0.35F*upM.getHeight());
			}else if(InfoAndStats.metalLevel == 4){
				text.draw(batch, "MAX level", upM.getX() + 1.1F*upM.getWidth(), upM.getY() + 0.65F*upM.getHeight());
				upM.getSprite().setColor(Color.TEAL);
			}else{
				upM.getSprite().setColor(Color.CYAN);
			}
			////
		}else{
			text.draw(batch, "Цена:", upC.getX() + 1.1F*upC.getWidth(), upC.getY() + 0.95F*upC.getHeight());
			if(InfoAndStats.moneyLevel == 0){
				if(InfoAndStats.fuel < 10 || InfoAndStats.metal < 10){
					upC.getSprite().setColor(Color.TEAL);
				}else{
					upC.getSprite().setColor(Color.CYAN);
				}
				text.draw(batch, "10 металла", upC.getX() + 1.1F*upC.getWidth(), upC.getY() + 0.65F*upC.getHeight());
				text.draw(batch, "10 топлива", upC.getX() + 1.1F*upC.getWidth(), upC.getY() + 0.35F*upC.getHeight());
			}else if(InfoAndStats.moneyLevel == 1){
				if(InfoAndStats.fuel < 25 || InfoAndStats.metal < 25){
					upC.getSprite().setColor(Color.TEAL);
				}else{
					upC.getSprite().setColor(Color.CYAN);
				}
				text.draw(batch, "25 металла", upC.getX() + 1.1F*upC.getWidth(), upC.getY() + 0.65F*upC.getHeight());
				text.draw(batch, "25 топлива", upC.getX() + 1.1F*upC.getWidth(), upC.getY() + 0.35F*upC.getHeight());
			}else if(InfoAndStats.moneyLevel == 2){
				if(InfoAndStats.fuel < 50 || InfoAndStats.metal < 50){
					upC.getSprite().setColor(Color.TEAL);
				}else{
					upC.getSprite().setColor(Color.CYAN);
				}
				text.draw(batch, "50 металла", upC.getX() + 1.1F*upC.getWidth(), upC.getY() + 0.65F*upC.getHeight());
				text.draw(batch, "50 топлива", upC.getX() + 1.1F*upC.getWidth(), upC.getY() + 0.35F*upC.getHeight());
			}else if(InfoAndStats.moneyLevel == 3){
				if(InfoAndStats.fuel < 75 || InfoAndStats.metal < 75){
					upC.getSprite().setColor(Color.TEAL);
				}else{
					upC.getSprite().setColor(Color.CYAN);
				}
				text.draw(batch, "75 металла", upC.getX() + 1.1F*upC.getWidth(), upC.getY() + 0.65F*upC.getHeight());
				text.draw(batch, "75 топлива", upC.getX() + 1.1F*upC.getWidth(), upC.getY() + 0.35F*upC.getHeight());
			}else if(InfoAndStats.moneyLevel == 4){
				text.draw(batch, "Макс. уровень", upC.getX() + 1.1F*upC.getWidth(), upC.getY() + 0.65F*upC.getHeight());
				upC.getSprite().setColor(Color.TEAL);
			}else{
				upC.getSprite().setColor(Color.CYAN);
			}
			////
			text.draw(batch, "Цена:", upF.getX() + 1.1F*upF.getWidth(), upF.getY() + 0.95F*upF.getHeight());
			if(InfoAndStats.fuelLevel == 0){
				if(InfoAndStats.money < 10 || InfoAndStats.metal < 10){
					upF.getSprite().setColor(Color.TEAL);
				}else{
					upF.getSprite().setColor(Color.CYAN);
				}
				text.draw(batch, "10 космокоинов", upF.getX() + 1.1F*upF.getWidth(), upF.getY() + 0.65F*upF.getHeight());
				text.draw(batch, "10 металла", upF.getX() + 1.1F*upF.getWidth(), upF.getY() + 0.35F*upF.getHeight());
			}else if(InfoAndStats.fuelLevel == 1){
				if(InfoAndStats.money < 25 || InfoAndStats.metal < 25){
					upF.getSprite().setColor(Color.TEAL);
				}else{
					upF.getSprite().setColor(Color.CYAN);
				}
				text.draw(batch, "25 космокоинов", upF.getX() + 1.1F*upF.getWidth(), upF.getY() + 0.65F*upF.getHeight());
				text.draw(batch, "25 металла", upF.getX() + 1.1F*upF.getWidth(), upF.getY() + 0.35F*upF.getHeight());
			}else if(InfoAndStats.fuelLevel == 2){
				if(InfoAndStats.money < 50 || InfoAndStats.metal < 50){
					upF.getSprite().setColor(Color.TEAL);
				}else{
					upF.getSprite().setColor(Color.CYAN);
				}
				text.draw(batch, "50 космокоинов", upF.getX() + 1.1F*upF.getWidth(), upF.getY() + 0.65F*upF.getHeight());
				text.draw(batch, "50 металла", upF.getX() + 1.1F*upF.getWidth(), upF.getY() + 0.35F*upF.getHeight());
			}else if(InfoAndStats.fuelLevel == 3){
				if(InfoAndStats.money < 75 || InfoAndStats.metal < 75){
					upF.getSprite().setColor(Color.TEAL);
				}else{
					upF.getSprite().setColor(Color.CYAN);
				}
				text.draw(batch, "75 космокоинов", upF.getX() + 1.1F*upF.getWidth(), upF.getY() + 0.65F*upF.getHeight());
				text.draw(batch, "75 металла", upF.getX() + 1.1F*upF.getWidth(), upF.getY() + 0.35F*upF.getHeight());
			}else if(InfoAndStats.fuelLevel == 4){
				text.draw(batch, "Макс. уровень", upF.getX() + 1.1F*upF.getWidth(), upF.getY() + 0.65F*upF.getHeight());
				upF.getSprite().setColor(Color.TEAL);
			}else{
				upF.getSprite().setColor(Color.CYAN);
			}
			////
			text.draw(batch, "Цена:", upM.getX() + 1.1F*upM.getWidth(), upM.getY() + 0.95F*upM.getHeight());
			if(InfoAndStats.metalLevel == 0){
				if(InfoAndStats.money < 10 || InfoAndStats.fuel < 10){
					upM.getSprite().setColor(Color.TEAL);
				}else{
					upM.getSprite().setColor(Color.CYAN);
				}
				text.draw(batch, "10 космокоинов", upM.getX() + 1.1F*upM.getWidth(), upM.getY() + 0.65F*upM.getHeight());
				text.draw(batch, "10 топлива", upM.getX() + 1.1F*upM.getWidth(), upM.getY() + 0.35F*upM.getHeight());
			}else if(InfoAndStats.metalLevel == 1){
				if(InfoAndStats.money < 25 || InfoAndStats.fuel < 25){
					upM.getSprite().setColor(Color.TEAL);
				}else{
					upM.getSprite().setColor(Color.CYAN);
				}
				text.draw(batch, "25 космокоинов", upM.getX() + 1.1F*upM.getWidth(), upM.getY() + 0.65F*upM.getHeight());
				text.draw(batch, "25 топлива", upM.getX() + 1.1F*upM.getWidth(), upM.getY() + 0.35F*upM.getHeight());
			}else if(InfoAndStats.metalLevel == 2){
				if(InfoAndStats.money < 50 || InfoAndStats.fuel < 50){
					upM.getSprite().setColor(Color.TEAL);
				}else{
					upM.getSprite().setColor(Color.CYAN);
				}
				text.draw(batch, "50 космокоинов", upM.getX() + 1.1F*upM.getWidth(), upM.getY() + 0.65F*upM.getHeight());
				text.draw(batch, "50 топлива", upM.getX() + 1.1F*upM.getWidth(), upM.getY() + 0.35F*upM.getHeight());
			}else if(InfoAndStats.metalLevel == 3){
				if(InfoAndStats.money < 75 || InfoAndStats.fuel < 75){
					upM.getSprite().setColor(Color.TEAL);
				}else{
					upM.getSprite().setColor(Color.CYAN);
				}
				text.draw(batch, "75 космокоинов", upM.getX() + 1.1F*upM.getWidth(), upM.getY() + 0.65F*upM.getHeight());
				text.draw(batch, "75 топлива", upM.getX() + 1.1F*upM.getWidth(), upM.getY() + 0.35F*upM.getHeight());
			}else if(InfoAndStats.metalLevel == 4){
				text.draw(batch, "Макс. уровень", upM.getX() + 1.1F*upM.getWidth(), upM.getY() + 0.65F*upM.getHeight());
				upM.getSprite().setColor(Color.TEAL);
			}else{
				upM.getSprite().setColor(Color.CYAN);
			}
			////
		}
		/***/
	}
	
	private void btnListener(){
		//Слушатель нажатия на кнопку "Back"//
		if(controller.isClicked(back.getX(), back.getY(), back.getWidth(), back.getHeight())){
			game.setScreen(new AnalyticCentreScreen(game));
			this.dispose();
		}
		//Слушатель нажатия на коноку апгрейда космокоинов//
		if(controller.isClicked(upC.getX(), upC.getY(), upC.getWidth(), upC.getHeight())){
			switch((int)InfoAndStats.moneyLevel){
			case 0:{
				if(InfoAndStats.fuel >= 10 && InfoAndStats.metal >= 10){
					InfoAndStats.fuel -= 10;
					InfoAndStats.metal -= 10;
					InfoAndStats.moneyFull = 25;
					InfoAndStats.moneyAmount = 2;
					InfoAndStats.moneyLevel = 1;
				}
				break;
			}
			case 1:{
				if(InfoAndStats.fuel >= 25 && InfoAndStats.metal >= 25){
					InfoAndStats.fuel -= 25;
					InfoAndStats.metal -= 25;
					InfoAndStats.moneyFull = 50;
					InfoAndStats.moneyAmount = 3;
					InfoAndStats.moneyLevel = 2;
				}
				break;
			}
			case 2:{
				if(InfoAndStats.fuel >= 50 && InfoAndStats.metal >= 50){
					InfoAndStats.fuel -= 50;
					InfoAndStats.metal -= 50;
					InfoAndStats.moneyFull = 75;
					InfoAndStats.moneyAmount = 4;
					InfoAndStats.moneyLevel = 3;
				}
				break;
			}
			case 3:{
				if(InfoAndStats.fuel >= 75 && InfoAndStats.metal >= 75){
					InfoAndStats.fuel -= 75;
					InfoAndStats.metal -= 75;
					InfoAndStats.moneyFull = 100;
					InfoAndStats.moneyAmount = 5;
					InfoAndStats.moneyLevel = 4;
				}
				break;
			}
			}
		}
		//---//
		//Слушатель нажатия на коноку апгрейда топлива//
		if(controller.isClicked(upF.getX(), upF.getY(), upF.getWidth(), upF.getHeight())){
			switch((int)InfoAndStats.fuelLevel){
			case 0:{
				if(InfoAndStats.money >= 10 && InfoAndStats.metal >= 10){
					InfoAndStats.money -= 10;
					InfoAndStats.metal -= 10;
					InfoAndStats.fuelFull = 25;
					InfoAndStats.fuelAmount = 2;
					InfoAndStats.fuelLevel = 1;
				}
				break;
			}
			case 1:{
				if(InfoAndStats.money >= 25 && InfoAndStats.metal >= 25){
					InfoAndStats.money -= 25;
					InfoAndStats.metal -= 25;
					InfoAndStats.fuelFull = 50;
					InfoAndStats.fuelAmount = 3;
					InfoAndStats.fuelLevel = 2;
				}
				break;
			}
			case 2:{
				if(InfoAndStats.money >= 50 && InfoAndStats.metal >= 50){
					InfoAndStats.money -= 50;
					InfoAndStats.metal -= 50;
					InfoAndStats.fuelFull = 75;
					InfoAndStats.fuelAmount = 4;
					InfoAndStats.fuelLevel = 3;
				}
				break;
			}
			case 3:{
				if(InfoAndStats.money >= 75 && InfoAndStats.metal >= 75){
					InfoAndStats.money -= 75;
					InfoAndStats.metal -= 75;
					InfoAndStats.fuelFull = 100;
					InfoAndStats.fuelAmount = 5;
					InfoAndStats.fuelLevel = 4;
				}
				break;
			}
			}
		}
		//---//
		//Слушатель нажатия на коноку апгрейда металла//
		if(controller.isClicked(upM.getX(), upM.getY(), upM.getWidth(), upM.getHeight())){
			switch((int)InfoAndStats.metalLevel){
			case 0:{
				if(InfoAndStats.fuel >= 10 && InfoAndStats.money >= 10){
					InfoAndStats.fuel -= 10;
					InfoAndStats.money -= 10;
					InfoAndStats.metalFull = 25;
					InfoAndStats.metalAmount = 2;
					InfoAndStats.metalLevel = 1;
				}
				break;
			}
			case 1:{
				if(InfoAndStats.fuel >= 25 && InfoAndStats.money >= 25){
					InfoAndStats.fuel -= 25;
					InfoAndStats.money -= 25;
					InfoAndStats.metalFull = 50;
					InfoAndStats.metalAmount = 3;
					InfoAndStats.metalLevel = 2;
				}
				break;
			}
			case 2:{
				if(InfoAndStats.fuel >= 50 && InfoAndStats.money >= 50){
					InfoAndStats.fuel -= 50;
					InfoAndStats.money -= 50;
					InfoAndStats.metalFull = 75;
					InfoAndStats.metalAmount = 4;
					InfoAndStats.metalLevel = 3;
				}
				break;
			}
			case 3:{
				if(InfoAndStats.fuel >= 75 && InfoAndStats.money >= 75){
					InfoAndStats.fuel -= 75;
					InfoAndStats.money -= 75;
					InfoAndStats.metalFull = 100;
					InfoAndStats.metalAmount = 5;
					InfoAndStats.metalLevel = 4;
				}
				break;
			}
			}
		}
		//---//
	}	
	
	private void resourcesCheck(){
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