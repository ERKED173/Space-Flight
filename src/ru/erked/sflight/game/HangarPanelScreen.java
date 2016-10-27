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
	
	//Metal
	private Sprite metalIcon;
	
	//Fonts
	private static BitmapFont text;
	
	//Page
	private int page = 1;
	private SFButtonS next;
	private SFButtonS prev;
	private SFButtonS buyRocket;
	
	//Information button
	private SFButtonS info;
	
	//Rockets
	public static SFButtonS rocketBall = new SFButtonS("rockets/rocketBall", 0.1F*width, 0.115F*width, 0.5F*height, 1.0F);
	public static SFButtonS rocketCircle = new SFButtonS("rockets/rocketCircle", 0.1F*width, 0.265F*width, 0.5F*height, 1.0F);
	public static SFButtonS rocketBasic = new SFButtonS("rockets/rocketBasic", 0.059262771F*width, 0.415F*width, 0.5F*height, 1.0F);
	public static SFButtonS rocketKinetic = new SFButtonS("rockets/rocketKinetic", 0.03884503531366846697133361030328F*width, 0.135F*width, 0.5F*height, 1.0F);
	
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
		
		metalIcon = new Sprite(new Texture("objects/metal.png"));
		metalIcon.setBounds(0.095F*width, 0.185F*width, 0.075F*width, 0.075F*width);
		
		SFButtonsInit();

		FreeTypeFontGenerator genUS = new FreeTypeFontGenerator(Gdx.files.internal("fonts/prototype.ttf"));
		FreeTypeFontGenerator genRU = new FreeTypeFontGenerator(Gdx.files.internal("fonts/9840.otf"));
		FreeTypeFontParameter param = new FreeTypeFontParameter();
		param.color = Color.WHITE;
		param.size = 40;
		if(InfoAndStats.lngRussian){
			param.characters = FONT_CHARS_RU;
			text = genRU.generateFont(param);
			text.getData().setScale((float)(0.0006F*width));
		}else{
			text = genUS.generateFont(param);
			text.getData().setScale((float)(0.00075F*width));
		}
		
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

		metalIcon.draw(batch);
		text.draw(batch, ": " + InfoAndStats.metal, metalIcon.getX() + 1.1F*metalIcon.getWidth(), metalIcon.getY() + 0.65F*metalIcon.getHeight());
		
		drawBackButton();
		drawNextPrev();
		drawRockets();
		drawBuyButton();
		
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
		if(!InfoAndStats.lngRussian){
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
		/***/
		rocketsInit();
		/***/
		info = new SFButtonS("btns/button", 0.0825F*width, 0.25F*width, 0.15F*height, 0.65F);
		info.getSprite().setColor(Color.LIME);
	}
	private void rocketsInit(){
		rocketBall.setX(0.115F*width);
		rocketBall.setY(0.5F*height);
		rocketBall.setWidth(0.1F*width);
		rocketBall.setHeight(0.1F*width/rocketBall.getAspectRatio());
		/***/
		rocketCircle.setX(0.265F*width);
		rocketCircle.setY(0.5F*height);
		rocketCircle.setWidth(0.1F*width);
		rocketCircle.setHeight(0.1F*width/rocketCircle.getAspectRatio());
		/***/
		rocketBasic.setX(0.415F*width);
		rocketBasic.setY(0.5F*height);
		rocketBasic.setWidth(0.059262771F*width);
		rocketBasic.setHeight(0.059262771F*width/rocketBasic.getAspectRatio());
		/***/
		rocketKinetic.setX(0.135F*width);
		rocketKinetic.setY(0.5F*height);
		rocketKinetic.setWidth(0.03884503531366846697133361030328F*width);
		rocketKinetic.setHeight(0.03884503531366846697133361030328F*width/rocketKinetic.getAspectRatio());
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
		buyRocket.getSprite().draw(batch);
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
		/***/
		if(controller.isOn(info.getX(), info.getY(), info.getWidth(), info.getHeight())){
			info.setMode(true);
		}else{
			info.setMode(false);
		}
		info.getSprite().draw(batch);
		if(!info.isActiveMode()){
			text.getData().setScale((float)(0.0015F*width));
			if(!InfoAndStats.lngRussian){
				text.draw(batch, "i", info.getX() + 0.4F*info.getWidth(), info.getY() + 0.75F*info.getHeight());
			}else{
				text.draw(batch, "i", info.getX() + 0.375F*info.getWidth(), info.getY() + 0.75F*info.getHeight());
			}
			text.getData().setScale((float)(0.00075F*width));
		}else{
			text.getData().setScale((float)(0.0015F*width));
			if(!InfoAndStats.lngRussian){
				text.draw(batch, "i", info.getX() + 0.4F*info.getWidth(), info.getY() + 0.7F*info.getHeight());
			}else{
				text.draw(batch, "i", info.getX() + 0.375F*info.getWidth(), info.getY() + 0.7F*info.getHeight());
			}
			text.getData().setScale((float)(0.00075F*width));
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
				if(!InfoAndStats.lngRussian){
					text.draw(batch, "Name: " + InfoAndStats.rocketBall.getNameUS(), 0.55F*width, 0.825F*height);
					text.draw(batch, "HP: " + InfoAndStats.rocketBall.getHp(), 0.55F*width, 0.825F*height - 1.5F*text.getCapHeight());
					text.draw(batch, "Resourses extraction: ", 0.55F*width, 0.825F*height - 3.0F*text.getCapHeight());
					text.draw(batch, InfoAndStats.rocketBall.getSpeedC() + " cosmocoins per 60 sec", 0.55F*width, 0.825F*height - 4.5F*text.getCapHeight());
					text.draw(batch, InfoAndStats.rocketBall.getSpeedF() + " fuel per 60 sec", 0.55F*width, 0.825F*height - 6.0F*text.getCapHeight());
					text.draw(batch, InfoAndStats.rocketBall.getSpeedM() + " metal per 60 sec", 0.55F*width, 0.825F*height - 7.5F*text.getCapHeight());
					text.draw(batch, "Price: " + InfoAndStats.rocketBall.getCost() + " metal", 0.55F*width, 0.825F*height - 9.0F*text.getCapHeight());
				}else{
					text.draw(batch, "Название: " + InfoAndStats.rocketBall.getNameRU(), 0.55F*width, 0.825F*height);
					text.draw(batch, "НР: " + InfoAndStats.rocketBall.getHp(), 0.55F*width, 0.825F*height - 1.5F*text.getCapHeight());
					text.draw(batch, "Добыча ресурсов: ", 0.55F*width, 0.825F*height - 3.0F*text.getCapHeight());
					text.draw(batch, InfoAndStats.rocketBall.getSpeedC() + " космокоинов за 60 сек", 0.55F*width, 0.825F*height - 4.5F*text.getCapHeight());
					text.draw(batch, InfoAndStats.rocketBall.getSpeedF() + " топлива за 60 сек", 0.55F*width, 0.825F*height - 6.0F*text.getCapHeight());
					text.draw(batch, InfoAndStats.rocketBall.getSpeedM() + " металла за 60 сек", 0.55F*width, 0.825F*height - 7.5F*text.getCapHeight());
					text.draw(batch, "Цена: " + InfoAndStats.rocketBall.getCost() + " металла", 0.55F*width, 0.825F*height - 9.0F*text.getCapHeight());
				}
			}
			if(InfoAndStats.currentRocket.equals("rocketBall")){
				if(!InfoAndStats.lngRussian){
					text.draw(batch, "Selected", rocketBall.getX() - 0.1F*rocketBall.getWidth(), rocketBall.getY() - 0.1F*rocketBall.getHeight());
				}else{
					text.draw(batch, "Выбрана", rocketBall.getX() - 0.175F*rocketBall.getWidth(), rocketBall.getY() - 0.1F*rocketBall.getHeight());
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
					text.draw(batch, "Resourses extraction: ", 0.55F*width, 0.825F*height - 3.0F*text.getCapHeight());
					text.draw(batch, InfoAndStats.rocketCircle.getSpeedC() + " cosmocoins per 60 sec", 0.55F*width, 0.825F*height - 4.5F*text.getCapHeight());
					text.draw(batch, InfoAndStats.rocketCircle.getSpeedF() + " fuel per 60 sec", 0.55F*width, 0.825F*height - 6.0F*text.getCapHeight());
					text.draw(batch, InfoAndStats.rocketCircle.getSpeedM() + " metal per 60 sec", 0.55F*width, 0.825F*height - 7.5F*text.getCapHeight());
					text.draw(batch, "Price: " + InfoAndStats.rocketCircle.getCost() + " metal", 0.55F*width, 0.825F*height - 9.0F*text.getCapHeight());
				}else{
					text.draw(batch, "Название: " + InfoAndStats.rocketCircle.getNameRU(), 0.55F*width, 0.825F*height);
					text.draw(batch, "НР: " + InfoAndStats.rocketCircle.getHp(), 0.55F*width, 0.825F*height - 1.5F*text.getCapHeight());
					text.draw(batch, "Добыча ресурсов: ", 0.55F*width, 0.825F*height - 3.0F*text.getCapHeight());
					text.draw(batch, InfoAndStats.rocketCircle.getSpeedC() + " космокоинов за 60 сек", 0.55F*width, 0.825F*height - 4.5F*text.getCapHeight());
					text.draw(batch, InfoAndStats.rocketCircle.getSpeedF() + " топлива за 60 сек", 0.55F*width, 0.825F*height - 6.0F*text.getCapHeight());
					text.draw(batch, InfoAndStats.rocketCircle.getSpeedM() + " металла за 60 сек", 0.55F*width, 0.825F*height - 7.5F*text.getCapHeight());
					text.draw(batch, "Цена: " + InfoAndStats.rocketCircle.getCost() + " металла", 0.55F*width, 0.825F*height - 9.0F*text.getCapHeight());
				}
			}
			if(InfoAndStats.currentRocket.equals("rocketCircle")){
				if(!InfoAndStats.lngRussian){
					text.draw(batch, "Selected", rocketCircle.getX() - 0.1F*rocketCircle.getWidth(), rocketCircle.getY() - 0.1F*rocketCircle.getHeight());
				}else{
					text.draw(batch, "Выбрана", rocketCircle.getX() - 0.175F*rocketCircle.getWidth(), rocketCircle.getY() - 0.1F*rocketCircle.getHeight());
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
					text.draw(batch, "Resourses extraction: ", 0.55F*width, 0.825F*height - 3.0F*text.getCapHeight());
					text.draw(batch, InfoAndStats.rocketBasic.getSpeedC() + " cosmocoins per 60 sec", 0.55F*width, 0.825F*height - 4.5F*text.getCapHeight());
					text.draw(batch, InfoAndStats.rocketBasic.getSpeedF() + " fuel per 60 sec", 0.55F*width, 0.825F*height - 6.0F*text.getCapHeight());
					text.draw(batch, InfoAndStats.rocketBasic.getSpeedM() + " metal per 60 sec", 0.55F*width, 0.825F*height - 7.5F*text.getCapHeight());
					text.draw(batch, "Price: " + InfoAndStats.rocketBasic.getCost() + " metal", 0.55F*width, 0.825F*height - 9.0F*text.getCapHeight());
				}else{
					text.draw(batch, "Название: " + InfoAndStats.rocketBasic.getNameRU(), 0.55F*width, 0.825F*height);
					text.draw(batch, "НР: " + InfoAndStats.rocketBasic.getHp(), 0.55F*width, 0.825F*height - 1.5F*text.getCapHeight());
					text.draw(batch, "Добыча ресурсов: ", 0.55F*width, 0.825F*height - 3.0F*text.getCapHeight());
					text.draw(batch, InfoAndStats.rocketBasic.getSpeedC() + " космокоинов за 60 сек", 0.55F*width, 0.825F*height - 4.5F*text.getCapHeight());
					text.draw(batch, InfoAndStats.rocketBasic.getSpeedF() + " топлива за 60 сек", 0.55F*width, 0.825F*height - 6.0F*text.getCapHeight());
					text.draw(batch, InfoAndStats.rocketBasic.getSpeedM() + " металла за 60 сек", 0.55F*width, 0.825F*height - 7.5F*text.getCapHeight());
					text.draw(batch, "Цена: " + InfoAndStats.rocketBasic.getCost() + " металла", 0.55F*width, 0.825F*height - 9.0F*text.getCapHeight());
				}
			}
			if(InfoAndStats.currentRocket.equals("rocketBasic")){
				if(!InfoAndStats.lngRussian){
					text.draw(batch, "Selected", rocketBasic.getX() - 0.5F*rocketBasic.getWidth(), rocketBasic.getY() - 0.1F*rocketBasic.getHeight());
				}else{
					text.draw(batch, "Выбрана", rocketBasic.getX() - 0.575F*rocketBasic.getWidth(), rocketBasic.getY() - 0.1F*rocketBasic.getHeight());
				}
			}
			rocketBasic.getSprite().draw(batch);
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
				if(!InfoAndStats.lngRussian){
					text.draw(batch, "Name: " + InfoAndStats.rocketKinetic.getNameUS(), 0.55F*width, 0.825F*height);
					text.draw(batch, "HP: " + InfoAndStats.rocketKinetic.getHp(), 0.55F*width, 0.825F*height - 1.5F*text.getCapHeight());
					text.draw(batch, "Resourses extraction: ", 0.55F*width, 0.825F*height - 3.0F*text.getCapHeight());
					text.draw(batch, InfoAndStats.rocketKinetic.getSpeedC() + " cosmocoins per 60 sec", 0.55F*width, 0.825F*height - 4.5F*text.getCapHeight());
					text.draw(batch, InfoAndStats.rocketKinetic.getSpeedF() + " fuel per 60 sec", 0.55F*width, 0.825F*height - 6.0F*text.getCapHeight());
					text.draw(batch, InfoAndStats.rocketKinetic.getSpeedM() + " metal per 60 sec", 0.55F*width, 0.825F*height - 7.5F*text.getCapHeight());
					text.draw(batch, "Price: " + InfoAndStats.rocketKinetic.getCost() + " metal", 0.55F*width, 0.825F*height - 9.0F*text.getCapHeight());
				}else{
					text.draw(batch, "Название: " + InfoAndStats.rocketKinetic.getNameRU(), 0.55F*width, 0.825F*height);
					text.draw(batch, "НР: " + InfoAndStats.rocketKinetic.getHp(), 0.55F*width, 0.825F*height - 1.5F*text.getCapHeight());
					text.draw(batch, "Добыча ресурсов: ", 0.55F*width, 0.825F*height - 3.0F*text.getCapHeight());
					text.draw(batch, InfoAndStats.rocketKinetic.getSpeedC() + " космокоинов за 60 сек", 0.55F*width, 0.825F*height - 4.5F*text.getCapHeight());
					text.draw(batch, InfoAndStats.rocketKinetic.getSpeedF() + " топлива за 60 сек", 0.55F*width, 0.825F*height - 6.0F*text.getCapHeight());
					text.draw(batch, InfoAndStats.rocketKinetic.getSpeedM() + " металла за 60 сек", 0.55F*width, 0.825F*height - 7.5F*text.getCapHeight());
					text.draw(batch, "Цена: " + InfoAndStats.rocketKinetic.getCost() + " металла", 0.55F*width, 0.825F*height - 9.0F*text.getCapHeight());
				}
			}
			if(InfoAndStats.currentRocket.equals("rocketKinetic")){
				if(!InfoAndStats.lngRussian){
					text.draw(batch, "Selected", rocketKinetic.getX() - 1.1F*rocketKinetic.getWidth(), rocketKinetic.getY() - 0.1F*rocketKinetic.getHeight());
				}else{
					text.draw(batch, "Выбрана", rocketKinetic.getX() - 1.175F*rocketKinetic.getWidth(), rocketKinetic.getY() - 0.1F*rocketKinetic.getHeight());
				}
			}
			rocketKinetic.getSprite().draw(batch);
			/***/
		}
	}
	private void drawBuyButton(){
		if(InfoAndStats.currentRocket.equals("null")){
			if(page==1){
				if(rocketBall.isActiveMode() && InfoAndStats.metal >= InfoAndStats.rocketBall.getCost()){
					buyRocket.getSprite().setColor(Color.LIME);
					if(controller.isOn(buyRocket.getX(), buyRocket.getY(), buyRocket.getWidth(), buyRocket.getHeight())){
						buyRocket.setMode(true);
					}else{
						buyRocket.setMode(false);
					}
				}else if(rocketCircle.isActiveMode() && InfoAndStats.metal >= InfoAndStats.rocketCircle.getCost()){
					buyRocket.getSprite().setColor(Color.LIME);
					if(controller.isOn(buyRocket.getX(), buyRocket.getY(), buyRocket.getWidth(), buyRocket.getHeight())){
						buyRocket.setMode(true);
					}else{
						buyRocket.setMode(false);
					}
				}else if(rocketBasic.isActiveMode() && InfoAndStats.metal >= InfoAndStats.rocketBasic.getCost()){
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
				if(rocketKinetic.isActiveMode() && InfoAndStats.metal >= InfoAndStats.rocketKinetic.getCost()){
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
		buyRocket.getSprite().draw(batch);
		/***/
		if(!InfoAndStats.lngRussian){
			if(buyRocket.isActiveMode())
				text.draw(batch, "Buy", buyRocket.getX() + 0.275F*buyRocket.getWidth(), buyRocket.getY() + 0.625F*buyRocket.getHeight());
			else
				text.draw(batch, "Buy", buyRocket.getX() + 0.275F*buyRocket.getWidth(), buyRocket.getY() + 0.65F*buyRocket.getHeight());
		}else{
			if(buyRocket.isActiveMode())
				text.draw(batch, "Купить", buyRocket.getX() + 0.075F*buyRocket.getWidth(), buyRocket.getY() + 0.625F*buyRocket.getHeight());
			else
				text.draw(batch, "Купить", buyRocket.getX() + 0.075F*buyRocket.getWidth(), buyRocket.getY() + 0.65F*buyRocket.getHeight());
		}
	}
	
	private void buttonListener(){
		if(controller.isClicked(back.getX(), back.getY(), back.getWidth(), back.getHeight())){
			game.setScreen(new AngarScreen(game));
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
		if(controller.isClicked(buyRocket.getX(), buyRocket.getY(), buyRocket.getWidth(), buyRocket.getHeight()) && (InfoAndStats.currentRocket.equals("null"))){
			if(rocketBall.isActiveMode() && InfoAndStats.metal>=InfoAndStats.rocketBall.getCost()){
				InfoAndStats.metal -= InfoAndStats.rocketBall.getCost();
				InfoAndStats.currentRocket = "rocketBall";
			}else if(rocketCircle.isActiveMode() && InfoAndStats.metal>=InfoAndStats.rocketCircle.getCost()){
				InfoAndStats.metal -= InfoAndStats.rocketCircle.getCost();
				InfoAndStats.currentRocket = "rocketCircle";
			}else if(rocketBasic.isActiveMode() && InfoAndStats.metal>=InfoAndStats.rocketBasic.getCost()){
				InfoAndStats.metal -= InfoAndStats.rocketBasic.getCost();
				InfoAndStats.currentRocket = "rocketBasic";
			}else if(rocketKinetic.isActiveMode() && InfoAndStats.metal>=InfoAndStats.rocketKinetic.getCost()){
				InfoAndStats.metal -= InfoAndStats.rocketKinetic.getCost();
				InfoAndStats.currentRocket = "rocketKinetic";
			}
		}
		if(controller.isClicked(info.getX(), info.getY(), info.getWidth(), info.getHeight())){
			game.setScreen(new InformationScreen(game, 1));
			this.dispose();
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