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

public class HangarPanelScreen implements Screen{

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
	
	//Metal
	private Sprite metalIcon;
	
	//Fonts
	private BitmapFont text;
	
	//Page
	private int page = 1;
	private SFButtonS next;
	private SFButtonS prev;
	private SFButtonS buyRocket;
	
	//Information button
	private SFButtonS info;
	
	//Rockets
	public static SFButtonS rocketBall;
	public static SFButtonS rocketCircle;
	public static SFButtonS rocketBasic;
	public static SFButtonS rocketKinetic;
	
	public HangarPanelScreen(final StartSFlight game){
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
		
		metalIcon = new Sprite(new Texture("objects/metal.png"));
		metalIcon.setBounds(0.095F*width, 0.325F*height, 0.1F*height, 0.1F*height);
		
		rocketsInit();
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

		metalIcon.draw(game.batch);
		text.draw(game.batch, ": " + INF.metal, metalIcon.getX() + 1.1F*metalIcon.getWidth(), metalIcon.getY() + 0.65F*metalIcon.getHeight());
		
		drawBackButton();
		drawNextPrev();
		drawRockets();
		drawBuyButton();
		
		if(!INF.lngRussian){
			text.draw(game.batch, "Rockets' panel", 0.4F*width, 0.965F*height);
			text.draw(game.batch, "Page: " + page, 0.765F*width, 0.175F*height);
		}else{
			text.draw(game.batch, "Панель ракет", 0.35F*width, 0.965F*height);
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
		buyRocket = new SFButtonS("btns/button", 0.125F*width, 0.1F*width, 0.15F*height, 1.0F);
		buyRocket.getSprite().setColor(Color.LIME);
		info = new SFButtonS("btns/button", 0.0825F*width, 0.25F*width, 0.15F*height, 0.65F);
		info.getSprite().setColor(Color.LIME);
	}
	private void rocketsInit(){
		rocketBall = new SFButtonS("rockets/rocketBall", 0.15F*height, 0.115F*width, 0.55F*height, 1.0F);
		/***/
		rocketCircle = new SFButtonS("rockets/rocketCircle", 0.15F*height, 0.265F*width, 0.55F*height, 1.0F);
		/***/
		rocketBasic = new SFButtonS("rockets/rocketBasic", 0.0937855248200075786282682834407F*height, 0.415F*width, 0.55F*height, 1.0F);
		/***/
		rocketKinetic = new SFButtonS("rockets/rocketKinetic", 0.07112068965517241379310344827586F*height, 0.135F*width, 0.55F*height, 1.0F);
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
		buyRocket.getSprite().draw(game.batch);
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
		if(controller.isOn(info.getX(), info.getY(), info.getWidth(), info.getHeight())){
			info.setMode(true);
		}else{
			info.setMode(false);
		}
		info.getSprite().draw(game.batch);
		if(!info.isActiveMode()){
			text.getData().setScale((float)(0.0015F*width));
			if(!INF.lngRussian){
				text.draw(game.batch, "i", info.getX() + 0.4F*info.getWidth(), info.getY() + 0.75F*info.getHeight());
			}else{
				text.draw(game.batch, "i", info.getX() + 0.375F*info.getWidth(), info.getY() + 0.75F*info.getHeight());
			}
			text.getData().setScale((float)(0.00125F*height));
		}else{
			text.getData().setScale((float)(0.0015F*width));
			if(!INF.lngRussian){
				text.draw(game.batch, "i", info.getX() + 0.4F*info.getWidth(), info.getY() + 0.7F*info.getHeight());
			}else{
				text.draw(game.batch, "i", info.getX() + 0.375F*info.getWidth(), info.getY() + 0.7F*info.getHeight());
			}
			text.getData().setScale((float)(0.00125F*height));
		}
		/***/
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
				if(!INF.lngRussian){
					text.draw(game.batch, "Name: " + INF.rocketBall.getNameUS(), 0.55F*width, 0.825F*height);
					text.draw(game.batch, "HP: " + INF.rocketBall.getHp(), 0.55F*width, 0.825F*height - 1.5F*text.getCapHeight());
					text.draw(game.batch, "Resourses extraction: ", 0.55F*width, 0.825F*height - 3.0F*text.getCapHeight());
					text.draw(game.batch, INF.rocketBall.getSpeedC() + " cosmocoins per 60 sec", 0.55F*width, 0.825F*height - 4.5F*text.getCapHeight());
					text.draw(game.batch, INF.rocketBall.getSpeedF() + " fuel per 60 sec", 0.55F*width, 0.825F*height - 6.0F*text.getCapHeight());
					text.draw(game.batch, INF.rocketBall.getSpeedM() + " metal per 60 sec", 0.55F*width, 0.825F*height - 7.5F*text.getCapHeight());
					text.draw(game.batch, "Price: " + INF.rocketBall.getCost() + " metal", 0.55F*width, 0.825F*height - 9.0F*text.getCapHeight());
				}else{
					text.draw(game.batch, "Название: " + INF.rocketBall.getNameRU(), 0.55F*width, 0.825F*height);
					text.draw(game.batch, "НР: " + INF.rocketBall.getHp(), 0.55F*width, 0.825F*height - 1.5F*text.getCapHeight());
					text.draw(game.batch, "Добыча ресурсов: ", 0.55F*width, 0.825F*height - 3.0F*text.getCapHeight());
					text.draw(game.batch, INF.rocketBall.getSpeedC() + " космокоинов за 60 сек", 0.55F*width, 0.825F*height - 4.5F*text.getCapHeight());
					text.draw(game.batch, INF.rocketBall.getSpeedF() + " топлива за 60 сек", 0.55F*width, 0.825F*height - 6.0F*text.getCapHeight());
					text.draw(game.batch, INF.rocketBall.getSpeedM() + " металла за 60 сек", 0.55F*width, 0.825F*height - 7.5F*text.getCapHeight());
					text.draw(game.batch, "Цена: " + INF.rocketBall.getCost() + " металла", 0.55F*width, 0.825F*height - 9.0F*text.getCapHeight());
				}
			}
			if(INF.currentRocket.equals("rocketBall")){
				if(!INF.lngRussian){
					text.draw(game.batch, "Selected", rocketBall.getX() - 0.1F*rocketBall.getWidth(), rocketBall.getY() - 0.1F*rocketBall.getHeight());
				}else{
					text.draw(game.batch, "Выбрана", rocketBall.getX() - 0.175F*rocketBall.getWidth(), rocketBall.getY() - 0.1F*rocketBall.getHeight());
				}
			}
			rocketBall.getSprite().draw(game.batch);
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
				if(!INF.lngRussian){
					text.draw(game.batch, "Name: " + INF.rocketCircle.getNameUS(), 0.55F*width, 0.825F*height);
					text.draw(game.batch, "HP: " + INF.rocketCircle.getHp(), 0.55F*width, 0.825F*height - 1.5F*text.getCapHeight());
					text.draw(game.batch, "Resourses extraction: ", 0.55F*width, 0.825F*height - 3.0F*text.getCapHeight());
					text.draw(game.batch, INF.rocketCircle.getSpeedC() + " cosmocoins per 60 sec", 0.55F*width, 0.825F*height - 4.5F*text.getCapHeight());
					text.draw(game.batch, INF.rocketCircle.getSpeedF() + " fuel per 60 sec", 0.55F*width, 0.825F*height - 6.0F*text.getCapHeight());
					text.draw(game.batch, INF.rocketCircle.getSpeedM() + " metal per 60 sec", 0.55F*width, 0.825F*height - 7.5F*text.getCapHeight());
					text.draw(game.batch, "Price: " + INF.rocketCircle.getCost() + " metal", 0.55F*width, 0.825F*height - 9.0F*text.getCapHeight());
				}else{
					text.draw(game.batch, "Название: " + INF.rocketCircle.getNameRU(), 0.55F*width, 0.825F*height);
					text.draw(game.batch, "НР: " + INF.rocketCircle.getHp(), 0.55F*width, 0.825F*height - 1.5F*text.getCapHeight());
					text.draw(game.batch, "Добыча ресурсов: ", 0.55F*width, 0.825F*height - 3.0F*text.getCapHeight());
					text.draw(game.batch, INF.rocketCircle.getSpeedC() + " космокоинов за 60 сек", 0.55F*width, 0.825F*height - 4.5F*text.getCapHeight());
					text.draw(game.batch, INF.rocketCircle.getSpeedF() + " топлива за 60 сек", 0.55F*width, 0.825F*height - 6.0F*text.getCapHeight());
					text.draw(game.batch, INF.rocketCircle.getSpeedM() + " металла за 60 сек", 0.55F*width, 0.825F*height - 7.5F*text.getCapHeight());
					text.draw(game.batch, "Цена: " + INF.rocketCircle.getCost() + " металла", 0.55F*width, 0.825F*height - 9.0F*text.getCapHeight());
				}
			}
			if(INF.currentRocket.equals("rocketCircle")){
				if(!INF.lngRussian){
					text.draw(game.batch, "Selected", rocketCircle.getX() - 0.1F*rocketCircle.getWidth(), rocketCircle.getY() - 0.1F*rocketCircle.getHeight());
				}else{
					text.draw(game.batch, "Выбрана", rocketCircle.getX() - 0.175F*rocketCircle.getWidth(), rocketCircle.getY() - 0.1F*rocketCircle.getHeight());
				}
			}
			rocketCircle.getSprite().draw(game.batch);
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
				if(!INF.lngRussian){
					text.draw(game.batch, "Name: " + INF.rocketBasic.getNameUS(), 0.55F*width, 0.825F*height);
					text.draw(game.batch, "HP: " + INF.rocketBasic.getHp(), 0.55F*width, 0.825F*height - 1.5F*text.getCapHeight());
					text.draw(game.batch, "Resourses extraction: ", 0.55F*width, 0.825F*height - 3.0F*text.getCapHeight());
					text.draw(game.batch, INF.rocketBasic.getSpeedC() + " cosmocoins per 60 sec", 0.55F*width, 0.825F*height - 4.5F*text.getCapHeight());
					text.draw(game.batch, INF.rocketBasic.getSpeedF() + " fuel per 60 sec", 0.55F*width, 0.825F*height - 6.0F*text.getCapHeight());
					text.draw(game.batch, INF.rocketBasic.getSpeedM() + " metal per 60 sec", 0.55F*width, 0.825F*height - 7.5F*text.getCapHeight());
					text.draw(game.batch, "Price: " + INF.rocketBasic.getCost() + " metal", 0.55F*width, 0.825F*height - 9.0F*text.getCapHeight());
				}else{
					text.draw(game.batch, "Название: " + INF.rocketBasic.getNameRU(), 0.55F*width, 0.825F*height);
					text.draw(game.batch, "НР: " + INF.rocketBasic.getHp(), 0.55F*width, 0.825F*height - 1.5F*text.getCapHeight());
					text.draw(game.batch, "Добыча ресурсов: ", 0.55F*width, 0.825F*height - 3.0F*text.getCapHeight());
					text.draw(game.batch, INF.rocketBasic.getSpeedC() + " космокоинов за 60 сек", 0.55F*width, 0.825F*height - 4.5F*text.getCapHeight());
					text.draw(game.batch, INF.rocketBasic.getSpeedF() + " топлива за 60 сек", 0.55F*width, 0.825F*height - 6.0F*text.getCapHeight());
					text.draw(game.batch, INF.rocketBasic.getSpeedM() + " металла за 60 сек", 0.55F*width, 0.825F*height - 7.5F*text.getCapHeight());
					text.draw(game.batch, "Цена: " + INF.rocketBasic.getCost() + " металла", 0.55F*width, 0.825F*height - 9.0F*text.getCapHeight());
				}
			}
			if(INF.currentRocket.equals("rocketBasic")){
				if(!INF.lngRussian){
					text.draw(game.batch, "Selected", rocketBasic.getX() - 0.5F*rocketBasic.getWidth(), rocketBasic.getY() - 0.1F*rocketBasic.getHeight());
				}else{
					text.draw(game.batch, "Выбрана", rocketBasic.getX() - 0.575F*rocketBasic.getWidth(), rocketBasic.getY() - 0.1F*rocketBasic.getHeight());
				}
			}
			rocketBasic.getSprite().draw(game.batch);
			/***/
		}else if(page == 2){
			if(controller.isClicked(rocketBall.getX(), rocketBall.getY(), rocketBall.getWidth(), rocketBall.getHeight())){
				if(rocketKinetic.isActiveMode()) rocketKinetic.setMode(false);
				else{
					rocketKinetic.setMode(true);
					/**Disable another rockets*/
				}
			}
			/***/
			if(rocketKinetic.isActiveMode()){
				if(!INF.lngRussian){
					text.draw(game.batch, "Name: " + INF.rocketKinetic.getNameUS(), 0.55F*width, 0.825F*height);
					text.draw(game.batch, "HP: " + INF.rocketKinetic.getHp(), 0.55F*width, 0.825F*height - 1.5F*text.getCapHeight());
					text.draw(game.batch, "Resourses extraction: ", 0.55F*width, 0.825F*height - 3.0F*text.getCapHeight());
					text.draw(game.batch, INF.rocketKinetic.getSpeedC() + " cosmocoins per 60 sec", 0.55F*width, 0.825F*height - 4.5F*text.getCapHeight());
					text.draw(game.batch, INF.rocketKinetic.getSpeedF() + " fuel per 60 sec", 0.55F*width, 0.825F*height - 6.0F*text.getCapHeight());
					text.draw(game.batch, INF.rocketKinetic.getSpeedM() + " metal per 60 sec", 0.55F*width, 0.825F*height - 7.5F*text.getCapHeight());
					text.draw(game.batch, "Price: " + INF.rocketKinetic.getCost() + " metal", 0.55F*width, 0.825F*height - 9.0F*text.getCapHeight());
				}else{
					text.draw(game.batch, "Название: " + INF.rocketKinetic.getNameRU(), 0.55F*width, 0.825F*height);
					text.draw(game.batch, "НР: " + INF.rocketKinetic.getHp(), 0.55F*width, 0.825F*height - 1.5F*text.getCapHeight());
					text.draw(game.batch, "Добыча ресурсов: ", 0.55F*width, 0.825F*height - 3.0F*text.getCapHeight());
					text.draw(game.batch, INF.rocketKinetic.getSpeedC() + " космокоинов за 60 сек", 0.55F*width, 0.825F*height - 4.5F*text.getCapHeight());
					text.draw(game.batch, INF.rocketKinetic.getSpeedF() + " топлива за 60 сек", 0.55F*width, 0.825F*height - 6.0F*text.getCapHeight());
					text.draw(game.batch, INF.rocketKinetic.getSpeedM() + " металла за 60 сек", 0.55F*width, 0.825F*height - 7.5F*text.getCapHeight());
					text.draw(game.batch, "Цена: " + INF.rocketKinetic.getCost() + " металла", 0.55F*width, 0.825F*height - 9.0F*text.getCapHeight());
				}
			}
			if(INF.currentRocket.equals("rocketKinetic")){
				if(!INF.lngRussian){
					text.draw(game.batch, "Selected", rocketKinetic.getX() - 1.1F*rocketKinetic.getWidth(), rocketKinetic.getY() - 0.1F*rocketKinetic.getHeight());
				}else{
					text.draw(game.batch, "Выбрана", rocketKinetic.getX() - 1.175F*rocketKinetic.getWidth(), rocketKinetic.getY() - 0.1F*rocketKinetic.getHeight());
				}
			}
			rocketKinetic.getSprite().draw(game.batch);
			/***/
		}
	}
	private void drawBuyButton(){
		if(INF.currentRocket.equals("null")){
			if(page==1){
				if(rocketBall.isActiveMode() && INF.metal >= INF.rocketBall.getCost()){
					buyRocket.getSprite().setColor(Color.LIME);
					if(controller.isOn(buyRocket.getX(), buyRocket.getY(), buyRocket.getWidth(), buyRocket.getHeight())){
						buyRocket.setMode(true);
					}else{
						buyRocket.setMode(false);
					}
				}else if(rocketCircle.isActiveMode() && INF.metal >= INF.rocketCircle.getCost()){
					buyRocket.getSprite().setColor(Color.LIME);
					if(controller.isOn(buyRocket.getX(), buyRocket.getY(), buyRocket.getWidth(), buyRocket.getHeight())){
						buyRocket.setMode(true);
					}else{
						buyRocket.setMode(false);
					}
				}else if(rocketBasic.isActiveMode() && INF.metal >= INF.rocketBasic.getCost()){
					buyRocket.getSprite().setColor(Color.LIME);
					if(controller.isOn(buyRocket.getX(), buyRocket.getY(), buyRocket.getWidth(), buyRocket.getHeight())){
						buyRocket.setMode(true);
					}else{
						buyRocket.setMode(false);
					}
				}else{
					buyRocket.getSprite().setColor(Color.FOREST);
				}
			}else if(page==2){
				if(rocketKinetic.isActiveMode() && INF.metal >= INF.rocketKinetic.getCost()){
					buyRocket.getSprite().setColor(Color.LIME);
					if(controller.isOn(buyRocket.getX(), buyRocket.getY(), buyRocket.getWidth(), buyRocket.getHeight())){
						buyRocket.setMode(true);
					}else{
						buyRocket.setMode(false);
					}
				}
			}else{
				buyRocket.getSprite().setColor(Color.FOREST);
			}
		}else{
			buyRocket.getSprite().setColor(Color.FOREST);
		}
		buyRocket.getSprite().draw(game.batch);
		/***/
		if(!INF.lngRussian){
			if(buyRocket.isActiveMode())
				text.draw(game.batch, "Buy", buyRocket.getX() + 0.275F*buyRocket.getWidth(), buyRocket.getY() + 0.625F*buyRocket.getHeight());
			else
				text.draw(game.batch, "Buy", buyRocket.getX() + 0.275F*buyRocket.getWidth(), buyRocket.getY() + 0.65F*buyRocket.getHeight());
		}else{
			if(buyRocket.isActiveMode())
				text.draw(game.batch, "Купить", buyRocket.getX() + 0.075F*buyRocket.getWidth(), buyRocket.getY() + 0.625F*buyRocket.getHeight());
			else
				text.draw(game.batch, "Купить", buyRocket.getX() + 0.075F*buyRocket.getWidth(), buyRocket.getY() + 0.65F*buyRocket.getHeight());
		}
	}
	
	private void buttonListener(){
		if(controller.isClicked(back.getX(), back.getY(), back.getWidth(), back.getHeight())){
			game.setScreen(new HangarScreen(game));
			this.dispose();
		}
		if(controller.isClicked(next.getX(), next.getY(), next.getWidth(), next.getHeight())){
			page++;
			rocketBall.setMode(false);
			rocketCircle.setMode(false);
			rocketBasic.setMode(false);
			rocketKinetic.setMode(false);
		}
		if(controller.isClicked(prev.getX(), prev.getY(), prev.getWidth(), prev.getHeight())){
			page--;
			rocketBall.setMode(false);
			rocketCircle.setMode(false);
			rocketBasic.setMode(false);
			rocketKinetic.setMode(false);
		}
		if(controller.isClicked(buyRocket.getX(), buyRocket.getY(), buyRocket.getWidth(), buyRocket.getHeight()) && (INF.currentRocket.equals("null"))){
			if(rocketBall.isActiveMode() && INF.metal>=INF.rocketBall.getCost()){
				INF.metal -= INF.rocketBall.getCost();
				INF.currentRocket = "rocketBall";
			}else if(rocketCircle.isActiveMode() && INF.metal>=INF.rocketCircle.getCost()){
				INF.metal -= INF.rocketCircle.getCost();
				INF.currentRocket = "rocketCircle";
			}else if(rocketBasic.isActiveMode() && INF.metal>=INF.rocketBasic.getCost()){
				INF.metal -= INF.rocketBasic.getCost();
				INF.currentRocket = "rocketBasic";
			}else if(rocketKinetic.isActiveMode() && INF.metal>=INF.rocketKinetic.getCost()){
				INF.metal -= INF.rocketKinetic.getCost();
				INF.currentRocket = "rocketKinetic";
			}
		}
		if(controller.isClicked(info.getX(), info.getY(), info.getWidth(), info.getHeight())){
			game.setScreen(new InformationScreen(game, 1, new HangarPanelScreen(game)));
			this.dispose();
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

	@Override
	public void dispose() {
		rocketBall.setMode(false);
		rocketBall.getSprite().getTexture().dispose();
		rocketBasic.setMode(false);
		rocketBasic.getSprite().getTexture().dispose();
		rocketCircle.setMode(false);
		rocketCircle.getSprite().getTexture().dispose();
		rocketKinetic.setMode(false);
		rocketKinetic.getSprite().getTexture().dispose();
		backgroundTexture.dispose();
		text.dispose();
	}

}