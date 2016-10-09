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

public class ResourceScreen implements Screen{

	final String FONT_CHARS_RU = "абвгдежзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
	private static final float width = Gdx.graphics.getWidth();
	private static final float height = Gdx.graphics.getHeight();
	
	private Game game;
	private SpriteBatch batch;
	private SFlightInputController controller;
	
	//Фон
	private Texture backgroundTexture; //Текстура фона
	public static Sprite backgroundSprite; //Спрайт фона
	
	//Копка "Back"
	private Sprite backButtonInactiveSprite;
	private Sprite backButtonActiveSprite;
	private float backButtonX;
	private float backButtonY;
	private float backButtonWidth;
	private float backButtonHeight;
	public static float backButtonTentionIndex; //Соотношение сторон кнопки
	private String schBack;
	
	//Копки Апгрейда
	private Sprite upSprI1;
	private Sprite upSprA1;
	private Sprite upSprI2;
	private Sprite upSprA2;
	private Sprite upSprI3;
	private Sprite upSprA3;
	
	//Иконка ресурсов
	private Sprite moneySprite;
	private Sprite fuelSprite;
	private Sprite metalSprite;
	
	//Шрифты
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
		
		//Фон\\
		backgroundTexture = new Texture("bckgrnd/resource/resource_1.png");
		backgroundSprite = new Sprite(backgroundTexture);
		backgroundSprite.setBounds(0.0F, 0.0F, width, height);
		schBack = "bckgrnd/resource/resource_1.png";
		
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

		//Шрифты\\
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
		upgradeButtonsInit();
		
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
		
		//Отрисовка кнопки "Back"//
		if(controller.isOn(backButtonX, backButtonY, backButtonWidth, backButtonHeight))
			backButtonActiveSprite.draw(batch);
		else
			backButtonInactiveSprite.draw(batch);

		if(!InfoAndStats.lngRussian){
			text.draw(batch, "Information about resources", 0.315F*width, 0.965F*height);
			text.draw(batch, InfoAndStats.money + "/" + InfoAndStats.moneyFull + " cosmocoins", 0.125F*width, 0.8F*height - 0.0F*text.getCapHeight());
			text.draw(batch, "1 cosmocoin per " + 60/InfoAndStats.moneyAmount + " seconds", 0.125F*width, 0.8F*height - 1.5F*text.getCapHeight());
			text.draw(batch, InfoAndStats.fuel + "/" + InfoAndStats.fuelFull + " fuel", 0.125F*width, 0.8F*height - 6.0F*text.getCapHeight());
			text.draw(batch, "1 fuel per " + 60/InfoAndStats.fuelAmount + " seconds", 0.125F*width, 0.8F*height - 7.5F*text.getCapHeight());
			text.draw(batch, InfoAndStats.metal + "/" + InfoAndStats.metalFull + " metal", 0.125F*width, 0.8F*height - 12.0F*text.getCapHeight());
			text.draw(batch, "1 metal per " + 60/InfoAndStats.metalAmount + " seconds", 0.125F*width, 0.8F*height - 13.5F*text.getCapHeight());
		}else{
			text.draw(batch, "Информация о ресурсах", 0.315F*width, 0.965F*height);
			text.draw(batch, InfoAndStats.money + "/" + InfoAndStats.moneyFull + " космокоинов", 0.125F*width, 0.8F*height - 0.0F*text.getCapHeight());
			text.draw(batch, "1 космокоин за " + 60/InfoAndStats.moneyAmount + " секунд", 0.125F*width, 0.8F*height - 1.5F*text.getCapHeight());
			text.draw(batch, InfoAndStats.fuel + "/" + InfoAndStats.fuelFull + " топлива", 0.125F*width, 0.8F*height - 6.0F*text.getCapHeight());
			text.draw(batch, "1 топливо за " + 60/InfoAndStats.fuelAmount + " секунд", 0.125F*width, 0.8F*height - 7.5F*text.getCapHeight());
			text.draw(batch, InfoAndStats.metal + "/" + InfoAndStats.metalFull + " металла", 0.125F*width, 0.8F*height - 12.0F*text.getCapHeight());
			text.draw(batch, "1 метал за " + 60/InfoAndStats.metalAmount + " секунд", 0.125F*width, 0.8F*height - 13.5F*text.getCapHeight());
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
	private void upgradeButtonsInit(){
		upSprI1 = new Sprite(ImgResDraw.buttonI);
		upSprI1.setBounds(0.55F*width, 0.675F*height, 0.15F*width, 0.1F*width);
		upSprA1 = new Sprite(ImgResDraw.buttonA);
		upSprA1.setBounds(0.55F*width - 0.5F*upSprI1.getWidth(), 0.675F*height - 0.5F*upSprI1.getHeight(), 0.3F*width, 0.2F*width);
		upSprI2 = new Sprite(ImgResDraw.buttonI);
		upSprI2.setBounds(0.55F*width, 0.475F*height, 0.15F*width, 0.1F*width);
		upSprA2 = new Sprite(ImgResDraw.buttonA);
		upSprA2.setBounds(0.55F*width - 0.5F*upSprI1.getWidth(), 0.475F*height - 0.5F*upSprI1.getHeight(), 0.3F*width, 0.2F*width);
		upSprI3 = new Sprite(ImgResDraw.buttonI);
		upSprI3.setBounds(0.55F*width, 0.275F*height, 0.15F*width, 0.1F*width);
		upSprA3 = new Sprite(ImgResDraw.buttonA);
		upSprA3.setBounds(0.55F*width - 0.5F*upSprI1.getWidth(), 0.275F*height - 0.5F*upSprI1.getHeight(), 0.3F*width, 0.2F*width);
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
		if(controller.isOn(upSprI1.getX(), upSprI1.getY(), upSprI1.getWidth(), upSprI1.getHeight())){
			upSprA1.draw(batch);
			if(!InfoAndStats.lngRussian){
				textBtn.draw(batch, "Upgrade", upSprI1.getX() + 0.125F*upSprI1.getWidth(), upSprI1.getY() + 0.575F*upSprI1.getHeight());
			}else{
				textBtn.draw(batch, "Улучшить", upSprI1.getX() + 0.115F*upSprI1.getWidth(), upSprI1.getY() + 0.575F*upSprI1.getHeight());
			}
		}else{
			upSprI1.draw(batch);
			if(!InfoAndStats.lngRussian){
				textBtn.draw(batch, "Upgrade", upSprI1.getX() + 0.125F*upSprI1.getWidth(), upSprI1.getY() + 0.6F*upSprI1.getHeight());
			}else{
				textBtn.draw(batch, "Улучшить", upSprI1.getX() + 0.115F*upSprI1.getWidth(), upSprI1.getY() + 0.6F*upSprI1.getHeight());
			}
		}
		if(controller.isOn(upSprI2.getX(), upSprI2.getY(), upSprI2.getWidth(), upSprI2.getHeight())){
			upSprA2.draw(batch);
			if(!InfoAndStats.lngRussian){
				textBtn.draw(batch, "Upgrade", upSprI2.getX() + 0.125F*upSprI2.getWidth(), upSprI2.getY() + 0.575F*upSprI2.getHeight());
			}else{
				textBtn.draw(batch, "Улучшить", upSprI2.getX() + 0.115F*upSprI2.getWidth(), upSprI2.getY() + 0.575F*upSprI2.getHeight());
			}
		}else{
			upSprI2.draw(batch);
			if(!InfoAndStats.lngRussian){
				textBtn.draw(batch, "Upgrade", upSprI2.getX() + 0.125F*upSprI2.getWidth(), upSprI2.getY() + 0.6F*upSprI2.getHeight());
			}else{
				textBtn.draw(batch, "Улучшить", upSprI2.getX() + 0.115F*upSprI2.getWidth(), upSprI2.getY() + 0.6F*upSprI2.getHeight());
			}
		}
		if(controller.isOn(upSprI3.getX(), upSprI3.getY(), upSprI3.getWidth(), upSprI3.getHeight())){
			upSprA3.draw(batch);
			if(!InfoAndStats.lngRussian){
				textBtn.draw(batch, "Upgrade", upSprI3.getX() + 0.125F*upSprI3.getWidth(), upSprI3.getY() + 0.575F*upSprI3.getHeight());
			}else{
				textBtn.draw(batch, "Улучшить", upSprI3.getX() + 0.115F*upSprI3.getWidth(), upSprI3.getY() + 0.575F*upSprI3.getHeight());
			}
		}else{
			upSprI3.draw(batch);
			if(!InfoAndStats.lngRussian){
				textBtn.draw(batch, "Upgrade", upSprI3.getX() + 0.125F*upSprI3.getWidth(), upSprI3.getY() + 0.6F*upSprI3.getHeight());
			}else{
				textBtn.draw(batch, "Улучшить", upSprI3.getX() + 0.115F*upSprI3.getWidth(), upSprI3.getY() + 0.6F*upSprI3.getHeight());
			}
		}
		/***/
		drawUpInfo();
		/***/
	}
	private void drawUpInfo(){
		/***/
		if(!InfoAndStats.lngRussian){
			text.draw(batch, "Cost:", upSprI1.getX() + 1.1F*upSprI1.getWidth(), upSprI1.getY() + 0.95F*upSprI1.getHeight());
			if(InfoAndStats.moneyLevel == 0){
				if(InfoAndStats.fuel < 10 || InfoAndStats.metal < 10){
					upSprI1.setColor(Color.LIGHT_GRAY);
					upSprA1.setColor(Color.LIGHT_GRAY);
				}else{
					upSprI1.setColor(Color.WHITE);
					upSprA1.setColor(Color.WHITE);
				}
				text.draw(batch, "10 metal", upSprI1.getX() + 1.1F*upSprI1.getWidth(), upSprI1.getY() + 0.65F*upSprI1.getHeight());
				text.draw(batch, "10 fuel", upSprI1.getX() + 1.1F*upSprI1.getWidth(), upSprI1.getY() + 0.35F*upSprI1.getHeight());
			}else if(InfoAndStats.moneyLevel == 1){
				if(InfoAndStats.fuel < 25 || InfoAndStats.metal < 25){
					upSprI1.setColor(Color.LIGHT_GRAY);
					upSprA1.setColor(Color.LIGHT_GRAY);
				}else{
					upSprI1.setColor(Color.WHITE);
					upSprA1.setColor(Color.WHITE);
				}
				text.draw(batch, "25 metal", upSprI1.getX() + 1.1F*upSprI1.getWidth(), upSprI1.getY() + 0.65F*upSprI1.getHeight());
				text.draw(batch, "25 fuel", upSprI1.getX() + 1.1F*upSprI1.getWidth(), upSprI1.getY() + 0.35F*upSprI1.getHeight());
			}else if(InfoAndStats.moneyLevel == 2){
				if(InfoAndStats.fuel < 50 || InfoAndStats.metal < 50){
					upSprI1.setColor(Color.LIGHT_GRAY);
					upSprA1.setColor(Color.LIGHT_GRAY);
				}else{
					upSprI1.setColor(Color.WHITE);
					upSprA1.setColor(Color.WHITE);
				}
				text.draw(batch, "50 metal", upSprI1.getX() + 1.1F*upSprI1.getWidth(), upSprI1.getY() + 0.65F*upSprI1.getHeight());
				text.draw(batch, "50 fuel", upSprI1.getX() + 1.1F*upSprI1.getWidth(), upSprI1.getY() + 0.35F*upSprI1.getHeight());
			}else if(InfoAndStats.moneyLevel == 3){
				if(InfoAndStats.fuel < 75 || InfoAndStats.metal < 75){
					upSprI1.setColor(Color.LIGHT_GRAY);
					upSprA1.setColor(Color.LIGHT_GRAY);
				}else{
					upSprI1.setColor(Color.WHITE);
					upSprA1.setColor(Color.WHITE);
				}
				text.draw(batch, "75 metal", upSprI1.getX() + 1.1F*upSprI1.getWidth(), upSprI1.getY() + 0.65F*upSprI1.getHeight());
				text.draw(batch, "75 fuel", upSprI1.getX() + 1.1F*upSprI1.getWidth(), upSprI1.getY() + 0.35F*upSprI1.getHeight());
			}else if(InfoAndStats.moneyLevel == 4){
				text.draw(batch, "MAX level", upSprI3.getX() + 1.1F*upSprI1.getWidth(), upSprI1.getY() + 0.65F*upSprI1.getHeight());
				upSprI1.setColor(Color.LIGHT_GRAY);
				upSprA1.setColor(Color.LIGHT_GRAY);
			}else{
				upSprI1.setColor(Color.WHITE);
				upSprA1.setColor(Color.WHITE);
			}
			////
			text.draw(batch, "Cost:", upSprI2.getX() + 1.1F*upSprI2.getWidth(), upSprI2.getY() + 0.95F*upSprI2.getHeight());
			if(InfoAndStats.fuelLevel == 0){
				if(InfoAndStats.money < 10 || InfoAndStats.metal < 10){
					upSprI2.setColor(Color.LIGHT_GRAY);
					upSprA2.setColor(Color.LIGHT_GRAY);
				}else{
					upSprI2.setColor(Color.WHITE);
					upSprA2.setColor(Color.WHITE);
				}
				text.draw(batch, "10 cosmocoins", upSprI2.getX() + 1.1F*upSprI2.getWidth(), upSprI2.getY() + 0.65F*upSprI2.getHeight());
				text.draw(batch, "10 metal", upSprI2.getX() + 1.1F*upSprI2.getWidth(), upSprI2.getY() + 0.35F*upSprI2.getHeight());
			}else if(InfoAndStats.fuelLevel == 1){
				if(InfoAndStats.money < 25 || InfoAndStats.metal < 25){
					upSprI2.setColor(Color.LIGHT_GRAY);
					upSprA2.setColor(Color.LIGHT_GRAY);
				}else{
					upSprI2.setColor(Color.WHITE);
					upSprA2.setColor(Color.WHITE);
				}
				text.draw(batch, "25 cosmocoins", upSprI2.getX() + 1.1F*upSprI2.getWidth(), upSprI2.getY() + 0.65F*upSprI2.getHeight());
				text.draw(batch, "25 metal", upSprI2.getX() + 1.1F*upSprI2.getWidth(), upSprI2.getY() + 0.35F*upSprI2.getHeight());
			}else if(InfoAndStats.fuelLevel == 2){
				if(InfoAndStats.money < 50 || InfoAndStats.metal < 50){
					upSprI2.setColor(Color.LIGHT_GRAY);
					upSprA2.setColor(Color.LIGHT_GRAY);
				}else{
					upSprI2.setColor(Color.WHITE);
					upSprA2.setColor(Color.WHITE);
				}
				text.draw(batch, "50 cosmocoins", upSprI2.getX() + 1.1F*upSprI2.getWidth(), upSprI2.getY() + 0.65F*upSprI2.getHeight());
				text.draw(batch, "50 metal", upSprI2.getX() + 1.1F*upSprI2.getWidth(), upSprI2.getY() + 0.35F*upSprI2.getHeight());
			}else if(InfoAndStats.fuelLevel == 3){
				if(InfoAndStats.money < 75 || InfoAndStats.metal < 75){
					upSprI2.setColor(Color.LIGHT_GRAY);
					upSprA2.setColor(Color.LIGHT_GRAY);
				}else{
					upSprI2.setColor(Color.WHITE);
					upSprA2.setColor(Color.WHITE);
				}
				text.draw(batch, "75 cosmocoins", upSprI2.getX() + 1.1F*upSprI2.getWidth(), upSprI2.getY() + 0.65F*upSprI2.getHeight());
				text.draw(batch, "75 metal", upSprI2.getX() + 1.1F*upSprI2.getWidth(), upSprI2.getY() + 0.35F*upSprI2.getHeight());
			}else if(InfoAndStats.fuelLevel == 4){
				text.draw(batch, "MAX level", upSprI2.getX() + 1.1F*upSprI2.getWidth(), upSprI2.getY() + 0.65F*upSprI2.getHeight());
				upSprI2.setColor(Color.LIGHT_GRAY);
				upSprA2.setColor(Color.LIGHT_GRAY);
			}else{
				upSprI2.setColor(Color.WHITE);
				upSprA2.setColor(Color.WHITE);
			}
			////
			text.draw(batch, "Cost:", upSprI3.getX() + 1.1F*upSprI3.getWidth(), upSprI3.getY() + 0.95F*upSprI3.getHeight());
			if(InfoAndStats.metalLevel == 0){
				if(InfoAndStats.money < 10 || InfoAndStats.fuel < 10){
					upSprI3.setColor(Color.LIGHT_GRAY);
					upSprA3.setColor(Color.LIGHT_GRAY);
				}else{
					upSprI3.setColor(Color.WHITE);
					upSprA3.setColor(Color.WHITE);
				}
				text.draw(batch, "10 cosmocoins", upSprI3.getX() + 1.1F*upSprI3.getWidth(), upSprI3.getY() + 0.65F*upSprI3.getHeight());
				text.draw(batch, "10 fuel", upSprI3.getX() + 1.1F*upSprI3.getWidth(), upSprI3.getY() + 0.35F*upSprI3.getHeight());
			}else if(InfoAndStats.metalLevel == 1){
				if(InfoAndStats.money < 25 || InfoAndStats.fuel < 25){
					upSprI3.setColor(Color.LIGHT_GRAY);
					upSprA3.setColor(Color.LIGHT_GRAY);
				}else{
					upSprI3.setColor(Color.WHITE);
					upSprA3.setColor(Color.WHITE);
				}
				text.draw(batch, "25 cosmocoins", upSprI3.getX() + 1.1F*upSprI3.getWidth(), upSprI3.getY() + 0.65F*upSprI3.getHeight());
				text.draw(batch, "25 fuel", upSprI3.getX() + 1.1F*upSprI3.getWidth(), upSprI3.getY() + 0.35F*upSprI3.getHeight());
			}else if(InfoAndStats.metalLevel == 2){
				if(InfoAndStats.money < 50 || InfoAndStats.fuel < 50){
					upSprI3.setColor(Color.LIGHT_GRAY);
					upSprA3.setColor(Color.LIGHT_GRAY);
				}else{
					upSprI3.setColor(Color.WHITE);
					upSprA3.setColor(Color.WHITE);
				}
				text.draw(batch, "50 cosmocoins", upSprI3.getX() + 1.1F*upSprI3.getWidth(), upSprI3.getY() + 0.65F*upSprI3.getHeight());
				text.draw(batch, "50 fuel", upSprI3.getX() + 1.1F*upSprI3.getWidth(), upSprI3.getY() + 0.35F*upSprI3.getHeight());
			}else if(InfoAndStats.metalLevel == 3){
				if(InfoAndStats.money < 75 || InfoAndStats.fuel < 75){
					upSprI3.setColor(Color.LIGHT_GRAY);
					upSprA3.setColor(Color.LIGHT_GRAY);
				}else{
					upSprI3.setColor(Color.WHITE);
					upSprA3.setColor(Color.WHITE);
				}
				text.draw(batch, "75 cosmocoins", upSprI3.getX() + 1.1F*upSprI3.getWidth(), upSprI3.getY() + 0.65F*upSprI3.getHeight());
				text.draw(batch, "75 fuel", upSprI3.getX() + 1.1F*upSprI3.getWidth(), upSprI3.getY() + 0.35F*upSprI3.getHeight());
			}else if(InfoAndStats.metalLevel == 4){
				text.draw(batch, "MAX level", upSprI3.getX() + 1.1F*upSprI3.getWidth(), upSprI3.getY() + 0.65F*upSprI3.getHeight());
				upSprI3.setColor(Color.LIGHT_GRAY);
				upSprA3.setColor(Color.LIGHT_GRAY);
			}else{
				upSprI3.setColor(Color.WHITE);
				upSprA3.setColor(Color.WHITE);
			}
			////
		}else{
			text.draw(batch, "Цена:", upSprI1.getX() + 1.1F*upSprI1.getWidth(), upSprI1.getY() + 0.95F*upSprI1.getHeight());
			if(InfoAndStats.moneyLevel == 0){
				if(InfoAndStats.fuel < 10 || InfoAndStats.metal < 10){
					upSprI1.setColor(Color.LIGHT_GRAY);
					upSprA1.setColor(Color.LIGHT_GRAY);
				}else{
					upSprI1.setColor(Color.WHITE);
					upSprA1.setColor(Color.WHITE);
				}
				text.draw(batch, "10 металла", upSprI1.getX() + 1.1F*upSprI1.getWidth(), upSprI1.getY() + 0.65F*upSprI1.getHeight());
				text.draw(batch, "10 топлива", upSprI1.getX() + 1.1F*upSprI1.getWidth(), upSprI1.getY() + 0.35F*upSprI1.getHeight());
			}else if(InfoAndStats.moneyLevel == 1){
				if(InfoAndStats.fuel < 25 || InfoAndStats.metal < 25){
					upSprI1.setColor(Color.LIGHT_GRAY);
					upSprA1.setColor(Color.LIGHT_GRAY);
				}else{
					upSprI1.setColor(Color.WHITE);
					upSprA1.setColor(Color.WHITE);
				}
				text.draw(batch, "25 металла", upSprI1.getX() + 1.1F*upSprI1.getWidth(), upSprI1.getY() + 0.65F*upSprI1.getHeight());
				text.draw(batch, "25 топлива", upSprI1.getX() + 1.1F*upSprI1.getWidth(), upSprI1.getY() + 0.35F*upSprI1.getHeight());
			}else if(InfoAndStats.moneyLevel == 2){
				if(InfoAndStats.fuel < 50 || InfoAndStats.metal < 50){
					upSprI1.setColor(Color.LIGHT_GRAY);
					upSprA1.setColor(Color.LIGHT_GRAY);
				}else{
					upSprI1.setColor(Color.WHITE);
					upSprA1.setColor(Color.WHITE);
				}
				text.draw(batch, "50 металла", upSprI1.getX() + 1.1F*upSprI1.getWidth(), upSprI1.getY() + 0.65F*upSprI1.getHeight());
				text.draw(batch, "50 топлива", upSprI1.getX() + 1.1F*upSprI1.getWidth(), upSprI1.getY() + 0.35F*upSprI1.getHeight());
			}else if(InfoAndStats.moneyLevel == 3){
				if(InfoAndStats.fuel < 75 || InfoAndStats.metal < 75){
					upSprI1.setColor(Color.LIGHT_GRAY);
					upSprA1.setColor(Color.LIGHT_GRAY);
				}else{
					upSprI1.setColor(Color.WHITE);
					upSprA1.setColor(Color.WHITE);
				}
				text.draw(batch, "75 металла", upSprI1.getX() + 1.1F*upSprI1.getWidth(), upSprI1.getY() + 0.65F*upSprI1.getHeight());
				text.draw(batch, "75 топлива", upSprI1.getX() + 1.1F*upSprI1.getWidth(), upSprI1.getY() + 0.35F*upSprI1.getHeight());
			}else if(InfoAndStats.moneyLevel == 4){
				text.draw(batch, "Макс. уровень", upSprI1.getX() + 1.1F*upSprI1.getWidth(), upSprI1.getY() + 0.65F*upSprI1.getHeight());
				upSprI1.setColor(Color.LIGHT_GRAY);
				upSprA1.setColor(Color.LIGHT_GRAY);
			}else{
				upSprI1.setColor(Color.WHITE);
				upSprA1.setColor(Color.WHITE);
			}
			////
			text.draw(batch, "Цена:", upSprI2.getX() + 1.1F*upSprI2.getWidth(), upSprI2.getY() + 0.95F*upSprI2.getHeight());
			if(InfoAndStats.fuelLevel == 0){
				if(InfoAndStats.money < 10 || InfoAndStats.metal < 10){
					upSprI2.setColor(Color.LIGHT_GRAY);
					upSprA2.setColor(Color.LIGHT_GRAY);
				}else{
					upSprI2.setColor(Color.WHITE);
					upSprA2.setColor(Color.WHITE);
				}
				text.draw(batch, "10 космокоинов", upSprI2.getX() + 1.1F*upSprI2.getWidth(), upSprI2.getY() + 0.65F*upSprI2.getHeight());
				text.draw(batch, "10 металла", upSprI2.getX() + 1.1F*upSprI2.getWidth(), upSprI2.getY() + 0.35F*upSprI2.getHeight());
			}else if(InfoAndStats.fuelLevel == 1){
				if(InfoAndStats.money < 25 || InfoAndStats.metal < 25){
					upSprI2.setColor(Color.LIGHT_GRAY);
					upSprA2.setColor(Color.LIGHT_GRAY);
				}else{
					upSprI2.setColor(Color.WHITE);
					upSprA2.setColor(Color.WHITE);
				}
				text.draw(batch, "25 космокоинов", upSprI2.getX() + 1.1F*upSprI2.getWidth(), upSprI2.getY() + 0.65F*upSprI2.getHeight());
				text.draw(batch, "25 металла", upSprI2.getX() + 1.1F*upSprI2.getWidth(), upSprI2.getY() + 0.35F*upSprI2.getHeight());
			}else if(InfoAndStats.fuelLevel == 2){
				if(InfoAndStats.money < 50 || InfoAndStats.metal < 50){
					upSprI2.setColor(Color.LIGHT_GRAY);
					upSprA2.setColor(Color.LIGHT_GRAY);
				}else{
					upSprI2.setColor(Color.WHITE);
					upSprA2.setColor(Color.WHITE);
				}
				text.draw(batch, "50 космокоинов", upSprI2.getX() + 1.1F*upSprI2.getWidth(), upSprI2.getY() + 0.65F*upSprI2.getHeight());
				text.draw(batch, "50 металла", upSprI2.getX() + 1.1F*upSprI2.getWidth(), upSprI2.getY() + 0.35F*upSprI2.getHeight());
			}else if(InfoAndStats.fuelLevel == 3){
				if(InfoAndStats.money < 75 || InfoAndStats.metal < 75){
					upSprI2.setColor(Color.LIGHT_GRAY);
					upSprA2.setColor(Color.LIGHT_GRAY);
				}else{
					upSprI2.setColor(Color.WHITE);
					upSprA2.setColor(Color.WHITE);
				}
				text.draw(batch, "75 космокоинов", upSprI2.getX() + 1.1F*upSprI2.getWidth(), upSprI2.getY() + 0.65F*upSprI2.getHeight());
				text.draw(batch, "75 металла", upSprI2.getX() + 1.1F*upSprI2.getWidth(), upSprI2.getY() + 0.35F*upSprI2.getHeight());
			}else if(InfoAndStats.fuelLevel == 4){
				text.draw(batch, "Макс. уровень", upSprI2.getX() + 1.1F*upSprI2.getWidth(), upSprI2.getY() + 0.65F*upSprI2.getHeight());
				upSprI2.setColor(Color.LIGHT_GRAY);
				upSprA2.setColor(Color.LIGHT_GRAY);
			}else{
				upSprI2.setColor(Color.WHITE);
				upSprA2.setColor(Color.WHITE);
			}
			////
			text.draw(batch, "Цена:", upSprI3.getX() + 1.1F*upSprI3.getWidth(), upSprI3.getY() + 0.95F*upSprI3.getHeight());
			if(InfoAndStats.metalLevel == 0){
				if(InfoAndStats.money < 10 || InfoAndStats.fuel < 10){
					upSprI3.setColor(Color.LIGHT_GRAY);
					upSprA3.setColor(Color.LIGHT_GRAY);
				}else{
					upSprI3.setColor(Color.WHITE);
					upSprA3.setColor(Color.WHITE);
				}
				text.draw(batch, "10 космокоинов", upSprI3.getX() + 1.1F*upSprI3.getWidth(), upSprI3.getY() + 0.65F*upSprI3.getHeight());
				text.draw(batch, "10 топлива", upSprI3.getX() + 1.1F*upSprI3.getWidth(), upSprI3.getY() + 0.35F*upSprI3.getHeight());
			}else if(InfoAndStats.metalLevel == 1){
				if(InfoAndStats.money < 25 || InfoAndStats.fuel < 25){
					upSprI3.setColor(Color.LIGHT_GRAY);
					upSprA3.setColor(Color.LIGHT_GRAY);
				}else{
					upSprI3.setColor(Color.WHITE);
					upSprA3.setColor(Color.WHITE);
				}
				text.draw(batch, "25 космокоинов", upSprI3.getX() + 1.1F*upSprI3.getWidth(), upSprI3.getY() + 0.65F*upSprI3.getHeight());
				text.draw(batch, "25 топлива", upSprI3.getX() + 1.1F*upSprI3.getWidth(), upSprI3.getY() + 0.35F*upSprI3.getHeight());
			}else if(InfoAndStats.metalLevel == 2){
				if(InfoAndStats.money < 50 || InfoAndStats.fuel < 50){
					upSprI3.setColor(Color.LIGHT_GRAY);
					upSprA3.setColor(Color.LIGHT_GRAY);
				}else{
					upSprI3.setColor(Color.WHITE);
					upSprA3.setColor(Color.WHITE);
				}
				text.draw(batch, "50 космокоинов", upSprI3.getX() + 1.1F*upSprI3.getWidth(), upSprI3.getY() + 0.65F*upSprI3.getHeight());
				text.draw(batch, "50 топлива", upSprI3.getX() + 1.1F*upSprI3.getWidth(), upSprI3.getY() + 0.35F*upSprI3.getHeight());
			}else if(InfoAndStats.metalLevel == 3){
				if(InfoAndStats.money < 75 || InfoAndStats.fuel < 75){
					upSprI3.setColor(Color.LIGHT_GRAY);
					upSprA3.setColor(Color.LIGHT_GRAY);
				}else{
					upSprI3.setColor(Color.WHITE);
					upSprA3.setColor(Color.WHITE);
				}
				text.draw(batch, "75 космокоинов", upSprI3.getX() + 1.1F*upSprI3.getWidth(), upSprI3.getY() + 0.65F*upSprI3.getHeight());
				text.draw(batch, "75 топлива", upSprI3.getX() + 1.1F*upSprI3.getWidth(), upSprI3.getY() + 0.35F*upSprI3.getHeight());
			}else if(InfoAndStats.metalLevel == 4){
				text.draw(batch, "Макс. уровень", upSprI3.getX() + 1.1F*upSprI3.getWidth(), upSprI3.getY() + 0.65F*upSprI3.getHeight());
				upSprI3.setColor(Color.LIGHT_GRAY);
				upSprA3.setColor(Color.LIGHT_GRAY);
			}else{
				upSprI3.setColor(Color.WHITE);
				upSprA3.setColor(Color.WHITE);
			}
			////
		}
		/***/
	}
	
	private void btnListener(){
		//Слушатель нажатия на кнопку "Back"//
		if(controller.isClicked(backButtonX, backButtonY, backButtonWidth, backButtonHeight)){
			game.setScreen(new AnalyticCentreScreen(game));
			this.dispose();
		}
		//Слушатель нажатия на коноку апгрейда космокоинов//
		if(controller.isClicked(upSprI1.getX(), upSprI1.getY(), upSprI1.getWidth(), upSprI1.getHeight())){
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
		if(controller.isClicked(upSprI2.getX(), upSprI2.getY(), upSprI2.getWidth(), upSprI2.getHeight())){
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
		if(controller.isClicked(upSprI3.getX(), upSprI3.getY(), upSprI3.getWidth(), upSprI3.getHeight())){
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