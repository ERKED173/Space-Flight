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

public class ResourceScreen implements Screen{

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
	
	public ResourceScreen(final StartSFlight game){
		this.game = game;
	}
	
	@Override
	public void show() {

		controller = new SFlightInputController();
		
		MainMenu.music.play();
		
		backgroundTexture = new Texture("bckgrnd/resource/resource_1.png");
		backgroundSprite = new Sprite(backgroundTexture);
		backgroundSprite.setBounds(0.0F, 0.0F, width, height);
		schBack = "bckgrnd/resource/resource_1.png";
		
		if(!INF.lngRussian){
			back = new SFButtonS("btns/back", 0.132F*width, width - 0.2F*width, -0.005F*height, 1.0F);
		}else{
			back = new SFButtonS("btns/RU/backR", 0.132F*width, width - 0.2F*width, -0.005F*height, 1.0F);
		}

		FreeTypeFontGenerator genUS = new FreeTypeFontGenerator(Gdx.files.internal("fonts/prototype.ttf"));
		FreeTypeFontGenerator genRU = new FreeTypeFontGenerator(Gdx.files.internal("fonts/9840.otf"));
		FreeTypeFontParameter param = new FreeTypeFontParameter();
		FreeTypeFontParameter param2 = new FreeTypeFontParameter();
		param.color = Color.WHITE;
		param.size = 40;
		param2.color = Color.WHITE;
		param2.size = 40;
		if(INF.lngRussian){
			param.characters = FONT_CHARS_RU;
			param2.characters = FONT_CHARS_RU;
			text = genRU.generateFont(param);
			textBtn = genRU.generateFont(param2);
			textBtn.getData().setScale((float)(0.0011F*height));
		}else{
			text = genUS.generateFont(param);
			textBtn = genUS.generateFont(param2);
			textBtn.getData().setScale((float)(0.0013F*height));
		}
		text.getData().setScale((float)(0.00115F*height));
		
		resourcesInit();
		
		upC = new SFButtonS("btns/button", 0.275F*height, 0.55F*width, 0.675F*height, 1.0F);
		upC.getSprite().setColor(Color.TEAL);
		upF = new SFButtonS("btns/button", 0.275F*height, 0.55F*width, 0.475F*height, 1.0F);
		upF.getSprite().setColor(Color.TEAL);
		upM = new SFButtonS("btns/button", 0.275F*height, 0.55F*width, 0.275F*height, 1.0F);
		upM.getSprite().setColor(Color.TEAL);
		
		genRU.dispose();
		genUS.dispose();
	}

	@Override
	public void render(float delta) {
		INF.elapsedTime++;
		resourcesCheck();
		
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		drawBackground();
		
		game.batch.begin();
		
		backgroundSprite.draw(game.batch);
		
		if(controller.isOn(back.getX(), back.getY(), back.getWidth(), back.getHeight())){
			back.setMode(true);
		}else{
			back.setMode(false);
		}
		back.getSprite().draw(game.batch);

		if(!INF.lngRussian){
			text.draw(game.batch, "Information about resources", 0.315F*width, 0.965F*height);
			text.draw(game.batch, INF.money + "/" + INF.moneyFull + " cosmocoins", 0.125F*width, 0.8F*height - 0.0F*text.getCapHeight());
			text.draw(game.batch, 60/(60/INF.moneyAmount) + " cosmocoins per 60 sec", 0.125F*width, 0.8F*height - 1.5F*text.getCapHeight());
			text.draw(game.batch, INF.fuel + "/" + INF.fuelFull + " fuel", 0.125F*width, 0.8F*height - 6.0F*text.getCapHeight());
			text.draw(game.batch, 60/(60/INF.fuelAmount) + " fuel per 60 sec", 0.125F*width, 0.8F*height - 7.5F*text.getCapHeight());
			text.draw(game.batch, INF.metal + "/" + INF.metalFull + " metal", 0.125F*width, 0.8F*height - 12.0F*text.getCapHeight());
			text.draw(game.batch, 60/(60/INF.metalAmount) + " metal per 60 sec", 0.125F*width, 0.8F*height - 13.5F*text.getCapHeight());
		}else{
			text.draw(game.batch, "Информация о ресурсах", 0.315F*width, 0.965F*height);
			text.draw(game.batch, INF.money + "/" + INF.moneyFull + " космокоинов", 0.125F*width, 0.8F*height - 0.0F*text.getCapHeight());
			text.draw(game.batch, 60/(60/INF.moneyAmount) + " космокоинов за 60 сек", 0.125F*width, 0.8F*height - 1.5F*text.getCapHeight());
			text.draw(game.batch, INF.fuel + "/" + INF.fuelFull + " топлива", 0.125F*width, 0.8F*height - 6.0F*text.getCapHeight());
			text.draw(game.batch, 60/(60/INF.fuelAmount) + " топлива за 60 сек", 0.125F*width, 0.8F*height - 7.5F*text.getCapHeight());
			text.draw(game.batch, INF.metal + "/" + INF.metalFull + " металла", 0.125F*width, 0.8F*height - 12.0F*text.getCapHeight());
			text.draw(game.batch, 60/(60/INF.metalAmount) + " металла за 60 сек", 0.125F*width, 0.8F*height - 13.5F*text.getCapHeight());
		}
		
		drawResources();
		drawUpBtns();
		
		game.batch.end();
		
		btnListener();
		
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
		if(INF.elapsedTime % 15 == 0){
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
		moneySprite.draw(game.batch);
		fuelSprite.draw(game.batch);
		metalSprite.draw(game.batch);
	}
	private void drawUpBtns(){
		/***/
		upC.getSprite().draw(game.batch);
		if(controller.isOn(upC.getX(), upC.getY(), upC.getWidth(), upC.getHeight()) && !(upC.getSprite().getColor().equals(Color.TEAL))){
			upC.setMode(true);
			if(!INF.lngRussian){
				textBtn.draw(game.batch, "Upgrade", upC.getX() + 0.125F*upC.getWidth(), upC.getY() + 0.575F*upC.getHeight());
			}else{
				textBtn.draw(game.batch, "Улучшить", upC.getX() + 0.115F*upC.getWidth(), upC.getY() + 0.575F*upC.getHeight());
			}
		}else{
			upC.setMode(false);
			if(!INF.lngRussian){
				textBtn.draw(game.batch, "Upgrade", upC.getX() + 0.125F*upC.getWidth(), upC.getY() + 0.6F*upC.getHeight());
			}else{
				textBtn.draw(game.batch, "Улучшить", upC.getX() + 0.115F*upC.getWidth(), upC.getY() + 0.6F*upC.getHeight());
			}
		}
		upF.getSprite().draw(game.batch);
		if(controller.isOn(upF.getX(), upF.getY(), upF.getWidth(), upF.getHeight()) && !(upF.getSprite().getColor().equals(Color.TEAL))){
			upF.setMode(true);
			if(!INF.lngRussian){
				textBtn.draw(game.batch, "Upgrade", upF.getX() + 0.125F*upF.getWidth(), upF.getY() + 0.575F*upF.getHeight());
			}else{
				textBtn.draw(game.batch, "Улучшить", upF.getX() + 0.115F*upF.getWidth(), upF.getY() + 0.575F*upF.getHeight());
			}
		}else{
			upF.setMode(false);
			if(!INF.lngRussian){
				textBtn.draw(game.batch, "Upgrade", upF.getX() + 0.125F*upF.getWidth(), upF.getY() + 0.6F*upF.getHeight());
			}else{
				textBtn.draw(game.batch, "Улучшить", upF.getX() + 0.115F*upM.getWidth(), upF.getY() + 0.6F*upF.getHeight());
			}
		}
		upM.getSprite().draw(game.batch);
		if(controller.isOn(upM.getX(), upM.getY(), upM.getWidth(), upM.getHeight()) && !(upM.getSprite().getColor().equals(Color.TEAL))){
			upM.setMode(true);
			if(!INF.lngRussian){
				textBtn.draw(game.batch, "Upgrade", upM.getX() + 0.125F*upM.getWidth(), upM.getY() + 0.575F*upM.getHeight());
			}else{
				textBtn.draw(game.batch, "Улучшить", upM.getX() + 0.115F*upM.getWidth(), upM.getY() + 0.575F*upM.getHeight());
			}
		}else{
			upM.setMode(false);
			if(!INF.lngRussian){
				textBtn.draw(game.batch, "Upgrade", upM.getX() + 0.125F*upM.getWidth(), upM.getY() + 0.6F*upM.getHeight());
			}else{
				textBtn.draw(game.batch, "Улучшить", upM.getX() + 0.115F*upM.getWidth(), upM.getY() + 0.6F*upM.getHeight());
			}
		}
		/***/
		drawUpInfo();
		/***/
	}
	private void drawUpInfo(){
		/***/
		if(!INF.lngRussian){
			text.draw(game.batch, "Cost:", upC.getX() + 1.1F*upC.getWidth(), upC.getY() + 0.95F*upC.getHeight());
			if(INF.moneyLevel == 0){
				if(INF.fuel < 10 || INF.metal < 10){
					upC.getSprite().setColor(Color.TEAL);
				}else{
					upC.getSprite().setColor(Color.CYAN);
				}
				text.draw(game.batch, "10 metal", upC.getX() + 1.1F*upC.getWidth(), upC.getY() + 0.65F*upC.getHeight());
				text.draw(game.batch, "10 fuel", upC.getX() + 1.1F*upC.getWidth(), upC.getY() + 0.35F*upC.getHeight());
			}else if(INF.moneyLevel == 1){
				if(INF.fuel < 25 || INF.metal < 25){
					upC.getSprite().setColor(Color.TEAL);
				}else{
					upC.getSprite().setColor(Color.CYAN);
				}
				text.draw(game.batch, "25 metal", upC.getX() + 1.1F*upC.getWidth(), upC.getY() + 0.65F*upC.getHeight());
				text.draw(game.batch, "25 fuel", upC.getX() + 1.1F*upC.getWidth(), upC.getY() + 0.35F*upC.getHeight());
			}else if(INF.moneyLevel == 2){
				if(INF.fuel < 50 || INF.metal < 50){
					upC.getSprite().setColor(Color.TEAL);
				}else{
					upC.getSprite().setColor(Color.CYAN);
				}
				text.draw(game.batch, "50 metal", upC.getX() + 1.1F*upC.getWidth(), upC.getY() + 0.65F*upC.getHeight());
				text.draw(game.batch, "50 fuel", upC.getX() + 1.1F*upC.getWidth(), upC.getY() + 0.35F*upC.getHeight());
			}else if(INF.moneyLevel == 3){
				if(INF.fuel < 75 || INF.metal < 75){
					upC.getSprite().setColor(Color.TEAL);
				}else{
					upC.getSprite().setColor(Color.CYAN);
				}
				text.draw(game.batch, "75 metal", upC.getX() + 1.1F*upC.getWidth(), upC.getY() + 0.65F*upC.getHeight());
				text.draw(game.batch, "75 fuel", upC.getX() + 1.1F*upC.getWidth(), upC.getY() + 0.35F*upC.getHeight());
			}else if(INF.moneyLevel == 4){
				text.draw(game.batch, "MAX level", upM.getX() + 1.1F*upC.getWidth(), upC.getY() + 0.65F*upC.getHeight());
				upC.getSprite().setColor(Color.TEAL);
			}else{
				upC.getSprite().setColor(Color.CYAN);
			}
			////
			text.draw(game.batch, "Cost:", upF.getX() + 1.1F*upF.getWidth(), upF.getY() + 0.95F*upF.getHeight());
			if(INF.fuelLevel == 0){
				if(INF.money < 10 || INF.metal < 10){
					upF.getSprite().setColor(Color.TEAL);
				}else{
					upF.getSprite().setColor(Color.CYAN);
				}
				text.draw(game.batch, "10 cosmocoins", upF.getX() + 1.1F*upF.getWidth(), upF.getY() + 0.65F*upF.getHeight());
				text.draw(game.batch, "10 metal", upF.getX() + 1.1F*upF.getWidth(), upF.getY() + 0.35F*upF.getHeight());
			}else if(INF.fuelLevel == 1){
				if(INF.money < 25 || INF.metal < 25){
					upF.getSprite().setColor(Color.TEAL);
				}else{
					upF.getSprite().setColor(Color.CYAN);
				}
				text.draw(game.batch, "25 cosmocoins", upF.getX() + 1.1F*upF.getWidth(), upF.getY() + 0.65F*upF.getHeight());
				text.draw(game.batch, "25 metal", upF.getX() + 1.1F*upF.getWidth(), upF.getY() + 0.35F*upF.getHeight());
			}else if(INF.fuelLevel == 2){
				if(INF.money < 50 || INF.metal < 50){
					upF.getSprite().setColor(Color.TEAL);
				}else{
					upF.getSprite().setColor(Color.CYAN);
				}
				text.draw(game.batch, "50 cosmocoins", upF.getX() + 1.1F*upF.getWidth(), upF.getY() + 0.65F*upF.getHeight());
				text.draw(game.batch, "50 metal", upF.getX() + 1.1F*upF.getWidth(), upF.getY() + 0.35F*upF.getHeight());
			}else if(INF.fuelLevel == 3){
				if(INF.money < 75 || INF.metal < 75){
					upF.getSprite().setColor(Color.TEAL);
				}else{
					upF.getSprite().setColor(Color.CYAN);
				}
				text.draw(game.batch, "75 cosmocoins", upF.getX() + 1.1F*upF.getWidth(), upF.getY() + 0.65F*upF.getHeight());
				text.draw(game.batch, "75 metal", upF.getX() + 1.1F*upF.getWidth(), upF.getY() + 0.35F*upF.getHeight());
			}else if(INF.fuelLevel == 4){
				text.draw(game.batch, "MAX level", upF.getX() + 1.1F*upF.getWidth(), upF.getY() + 0.65F*upF.getHeight());
				upF.getSprite().setColor(Color.TEAL);
			}else{
				upF.getSprite().setColor(Color.CYAN);
			}
			////
			text.draw(game.batch, "Cost:", upM.getX() + 1.1F*upM.getWidth(), upM.getY() + 0.95F*upM.getHeight());
			if(INF.metalLevel == 0){
				if(INF.money < 10 || INF.fuel < 10){
					upM.getSprite().setColor(Color.TEAL);
				}else{
					upM.getSprite().setColor(Color.CYAN);
				}
				text.draw(game.batch, "10 cosmocoins", upM.getX() + 1.1F*upM.getWidth(), upM.getY() + 0.65F*upM.getHeight());
				text.draw(game.batch, "10 fuel", upM.getX() + 1.1F*upM.getWidth(), upM.getY() + 0.35F*upM.getHeight());
			}else if(INF.metalLevel == 1){
				if(INF.money < 25 || INF.fuel < 25){
					upM.getSprite().setColor(Color.TEAL);
				}else{
					upM.getSprite().setColor(Color.CYAN);
				}
				text.draw(game.batch, "25 cosmocoins", upM.getX() + 1.1F*upM.getWidth(), upM.getY() + 0.65F*upM.getHeight());
				text.draw(game.batch, "25 fuel", upM.getX() + 1.1F*upM.getWidth(), upM.getY() + 0.35F*upM.getHeight());
			}else if(INF.metalLevel == 2){
				if(INF.money < 50 || INF.fuel < 50){
					upM.getSprite().setColor(Color.TEAL);
				}else{
					upM.getSprite().setColor(Color.CYAN);
				}
				text.draw(game.batch, "50 cosmocoins", upM.getX() + 1.1F*upM.getWidth(), upM.getY() + 0.65F*upM.getHeight());
				text.draw(game.batch, "50 fuel", upM.getX() + 1.1F*upM.getWidth(), upM.getY() + 0.35F*upM.getHeight());
			}else if(INF.metalLevel == 3){
				if(INF.money < 75 || INF.fuel < 75){
					upM.getSprite().setColor(Color.TEAL);
				}else{
					upM.getSprite().setColor(Color.CYAN);
				}
				text.draw(game.batch, "75 cosmocoins", upM.getX() + 1.1F*upM.getWidth(), upM.getY() + 0.65F*upM.getHeight());
				text.draw(game.batch, "75 fuel", upM.getX() + 1.1F*upM.getWidth(), upM.getY() + 0.35F*upM.getHeight());
			}else if(INF.metalLevel == 4){
				text.draw(game.batch, "MAX level", upM.getX() + 1.1F*upM.getWidth(), upM.getY() + 0.65F*upM.getHeight());
				upM.getSprite().setColor(Color.TEAL);
			}else{
				upM.getSprite().setColor(Color.CYAN);
			}
			////
		}else{
			text.draw(game.batch, "Цена:", upC.getX() + 1.1F*upC.getWidth(), upC.getY() + 0.95F*upC.getHeight());
			if(INF.moneyLevel == 0){
				if(INF.fuel < 10 || INF.metal < 10){
					upC.getSprite().setColor(Color.TEAL);
				}else{
					upC.getSprite().setColor(Color.CYAN);
				}
				text.draw(game.batch, "10 металла", upC.getX() + 1.1F*upC.getWidth(), upC.getY() + 0.65F*upC.getHeight());
				text.draw(game.batch, "10 топлива", upC.getX() + 1.1F*upC.getWidth(), upC.getY() + 0.35F*upC.getHeight());
			}else if(INF.moneyLevel == 1){
				if(INF.fuel < 25 || INF.metal < 25){
					upC.getSprite().setColor(Color.TEAL);
				}else{
					upC.getSprite().setColor(Color.CYAN);
				}
				text.draw(game.batch, "25 металла", upC.getX() + 1.1F*upC.getWidth(), upC.getY() + 0.65F*upC.getHeight());
				text.draw(game.batch, "25 топлива", upC.getX() + 1.1F*upC.getWidth(), upC.getY() + 0.35F*upC.getHeight());
			}else if(INF.moneyLevel == 2){
				if(INF.fuel < 50 || INF.metal < 50){
					upC.getSprite().setColor(Color.TEAL);
				}else{
					upC.getSprite().setColor(Color.CYAN);
				}
				text.draw(game.batch, "50 металла", upC.getX() + 1.1F*upC.getWidth(), upC.getY() + 0.65F*upC.getHeight());
				text.draw(game.batch, "50 топлива", upC.getX() + 1.1F*upC.getWidth(), upC.getY() + 0.35F*upC.getHeight());
			}else if(INF.moneyLevel == 3){
				if(INF.fuel < 75 || INF.metal < 75){
					upC.getSprite().setColor(Color.TEAL);
				}else{
					upC.getSprite().setColor(Color.CYAN);
				}
				text.draw(game.batch, "75 металла", upC.getX() + 1.1F*upC.getWidth(), upC.getY() + 0.65F*upC.getHeight());
				text.draw(game.batch, "75 топлива", upC.getX() + 1.1F*upC.getWidth(), upC.getY() + 0.35F*upC.getHeight());
			}else if(INF.moneyLevel == 4){
				text.draw(game.batch, "Макс. уровень", upC.getX() + 1.1F*upC.getWidth(), upC.getY() + 0.65F*upC.getHeight());
				upC.getSprite().setColor(Color.TEAL);
			}else{
				upC.getSprite().setColor(Color.CYAN);
			}
			////
			text.draw(game.batch, "Цена:", upF.getX() + 1.1F*upF.getWidth(), upF.getY() + 0.95F*upF.getHeight());
			if(INF.fuelLevel == 0){
				if(INF.money < 10 || INF.metal < 10){
					upF.getSprite().setColor(Color.TEAL);
				}else{
					upF.getSprite().setColor(Color.CYAN);
				}
				text.draw(game.batch, "10 космокоинов", upF.getX() + 1.1F*upF.getWidth(), upF.getY() + 0.65F*upF.getHeight());
				text.draw(game.batch, "10 металла", upF.getX() + 1.1F*upF.getWidth(), upF.getY() + 0.35F*upF.getHeight());
			}else if(INF.fuelLevel == 1){
				if(INF.money < 25 || INF.metal < 25){
					upF.getSprite().setColor(Color.TEAL);
				}else{
					upF.getSprite().setColor(Color.CYAN);
				}
				text.draw(game.batch, "25 космокоинов", upF.getX() + 1.1F*upF.getWidth(), upF.getY() + 0.65F*upF.getHeight());
				text.draw(game.batch, "25 металла", upF.getX() + 1.1F*upF.getWidth(), upF.getY() + 0.35F*upF.getHeight());
			}else if(INF.fuelLevel == 2){
				if(INF.money < 50 || INF.metal < 50){
					upF.getSprite().setColor(Color.TEAL);
				}else{
					upF.getSprite().setColor(Color.CYAN);
				}
				text.draw(game.batch, "50 космокоинов", upF.getX() + 1.1F*upF.getWidth(), upF.getY() + 0.65F*upF.getHeight());
				text.draw(game.batch, "50 металла", upF.getX() + 1.1F*upF.getWidth(), upF.getY() + 0.35F*upF.getHeight());
			}else if(INF.fuelLevel == 3){
				if(INF.money < 75 || INF.metal < 75){
					upF.getSprite().setColor(Color.TEAL);
				}else{
					upF.getSprite().setColor(Color.CYAN);
				}
				text.draw(game.batch, "75 космокоинов", upF.getX() + 1.1F*upF.getWidth(), upF.getY() + 0.65F*upF.getHeight());
				text.draw(game.batch, "75 металла", upF.getX() + 1.1F*upF.getWidth(), upF.getY() + 0.35F*upF.getHeight());
			}else if(INF.fuelLevel == 4){
				text.draw(game.batch, "Макс. уровень", upF.getX() + 1.1F*upF.getWidth(), upF.getY() + 0.65F*upF.getHeight());
				upF.getSprite().setColor(Color.TEAL);
			}else{
				upF.getSprite().setColor(Color.CYAN);
			}
			////
			text.draw(game.batch, "Цена:", upM.getX() + 1.1F*upM.getWidth(), upM.getY() + 0.95F*upM.getHeight());
			if(INF.metalLevel == 0){
				if(INF.money < 10 || INF.fuel < 10){
					upM.getSprite().setColor(Color.TEAL);
				}else{
					upM.getSprite().setColor(Color.CYAN);
				}
				text.draw(game.batch, "10 космокоинов", upM.getX() + 1.1F*upM.getWidth(), upM.getY() + 0.65F*upM.getHeight());
				text.draw(game.batch, "10 топлива", upM.getX() + 1.1F*upM.getWidth(), upM.getY() + 0.35F*upM.getHeight());
			}else if(INF.metalLevel == 1){
				if(INF.money < 25 || INF.fuel < 25){
					upM.getSprite().setColor(Color.TEAL);
				}else{
					upM.getSprite().setColor(Color.CYAN);
				}
				text.draw(game.batch, "25 космокоинов", upM.getX() + 1.1F*upM.getWidth(), upM.getY() + 0.65F*upM.getHeight());
				text.draw(game.batch, "25 топлива", upM.getX() + 1.1F*upM.getWidth(), upM.getY() + 0.35F*upM.getHeight());
			}else if(INF.metalLevel == 2){
				if(INF.money < 50 || INF.fuel < 50){
					upM.getSprite().setColor(Color.TEAL);
				}else{
					upM.getSprite().setColor(Color.CYAN);
				}
				text.draw(game.batch, "50 космокоинов", upM.getX() + 1.1F*upM.getWidth(), upM.getY() + 0.65F*upM.getHeight());
				text.draw(game.batch, "50 топлива", upM.getX() + 1.1F*upM.getWidth(), upM.getY() + 0.35F*upM.getHeight());
			}else if(INF.metalLevel == 3){
				if(INF.money < 75 || INF.fuel < 75){
					upM.getSprite().setColor(Color.TEAL);
				}else{
					upM.getSprite().setColor(Color.CYAN);
				}
				text.draw(game.batch, "75 космокоинов", upM.getX() + 1.1F*upM.getWidth(), upM.getY() + 0.65F*upM.getHeight());
				text.draw(game.batch, "75 топлива", upM.getX() + 1.1F*upM.getWidth(), upM.getY() + 0.35F*upM.getHeight());
			}else if(INF.metalLevel == 4){
				text.draw(game.batch, "Макс. уровень", upM.getX() + 1.1F*upM.getWidth(), upM.getY() + 0.65F*upM.getHeight());
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
			switch((int)INF.moneyLevel){
			case 0:{
				if(INF.fuel >= 10 && INF.metal >= 10){
					INF.fuel -= 10;
					INF.metal -= 10;
					INF.moneyFull = 25;
					INF.moneyAmount = 2;
					INF.moneyLevel = 1;
				}
				break;
			}
			case 1:{
				if(INF.fuel >= 25 && INF.metal >= 25){
					INF.fuel -= 25;
					INF.metal -= 25;
					INF.moneyFull = 50;
					INF.moneyAmount = 3;
					INF.moneyLevel = 2;
				}
				break;
			}
			case 2:{
				if(INF.fuel >= 50 && INF.metal >= 50){
					INF.fuel -= 50;
					INF.metal -= 50;
					INF.moneyFull = 75;
					INF.moneyAmount = 4;
					INF.moneyLevel = 3;
				}
				break;
			}
			case 3:{
				if(INF.fuel >= 75 && INF.metal >= 75){
					INF.fuel -= 75;
					INF.metal -= 75;
					INF.moneyFull = 100;
					INF.moneyAmount = 5;
					INF.moneyLevel = 4;
				}
				break;
			}
			}
		}
		//---//
		//Слушатель нажатия на коноку апгрейда топлива//
		if(controller.isClicked(upF.getX(), upF.getY(), upF.getWidth(), upF.getHeight())){
			switch((int)INF.fuelLevel){
			case 0:{
				if(INF.money >= 10 && INF.metal >= 10){
					INF.money -= 10;
					INF.metal -= 10;
					INF.fuelFull = 25;
					INF.fuelAmount = 2;
					INF.fuelLevel = 1;
				}
				break;
			}
			case 1:{
				if(INF.money >= 25 && INF.metal >= 25){
					INF.money -= 25;
					INF.metal -= 25;
					INF.fuelFull = 50;
					INF.fuelAmount = 3;
					INF.fuelLevel = 2;
				}
				break;
			}
			case 2:{
				if(INF.money >= 50 && INF.metal >= 50){
					INF.money -= 50;
					INF.metal -= 50;
					INF.fuelFull = 75;
					INF.fuelAmount = 4;
					INF.fuelLevel = 3;
				}
				break;
			}
			case 3:{
				if(INF.money >= 75 && INF.metal >= 75){
					INF.money -= 75;
					INF.metal -= 75;
					INF.fuelFull = 100;
					INF.fuelAmount = 5;
					INF.fuelLevel = 4;
				}
				break;
			}
			}
		}
		//---//
		//Слушатель нажатия на коноку апгрейда металла//
		if(controller.isClicked(upM.getX(), upM.getY(), upM.getWidth(), upM.getHeight())){
			switch((int)INF.metalLevel){
			case 0:{
				if(INF.fuel >= 10 && INF.money >= 10){
					INF.fuel -= 10;
					INF.money -= 10;
					INF.metalFull = 25;
					INF.metalAmount = 2;
					INF.metalLevel = 1;
				}
				break;
			}
			case 1:{
				if(INF.fuel >= 25 && INF.money >= 25){
					INF.fuel -= 25;
					INF.money -= 25;
					INF.metalFull = 50;
					INF.metalAmount = 3;
					INF.metalLevel = 2;
				}
				break;
			}
			case 2:{
				if(INF.fuel >= 50 && INF.money >= 50){
					INF.fuel -= 50;
					INF.money -= 50;
					INF.metalFull = 75;
					INF.metalAmount = 4;
					INF.metalLevel = 3;
				}
				break;
			}
			case 3:{
				if(INF.fuel >= 75 && INF.money >= 75){
					INF.fuel -= 75;
					INF.money -= 75;
					INF.metalFull = 100;
					INF.metalAmount = 5;
					INF.metalLevel = 4;
				}
				break;
			}
			}
		}
		//---//
	}	
	
	private void resourcesCheck(){
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
		text.dispose();
	}

}