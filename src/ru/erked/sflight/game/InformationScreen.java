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

public class InformationScreen implements Screen{

	final String FONT_CHARS_RU = "абвгдежзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
	private static final float width = Gdx.graphics.getWidth();
	private static final float height = Gdx.graphics.getHeight();
	
	private final StartSFlight game;
	private SFlightInputController controller;
	
	private int iter;
	private Texture[] textures;
	private Screen screen;
	
	//Background
	public static Sprite backgroundSprite;
	
	//"Back" Button
	private SFButtonS back;
	
	//Fonts
	private static BitmapFont text;
	private float ots;
	
	public InformationScreen(final StartSFlight game, int iter, Screen screen){
		this.game = game;
		this.iter = iter;
		this.screen = screen;
	}
	
	@Override
	public void show() {

		ots = 0.06F*height;
		
		controller = new SFlightInputController();
		
		textures = new Texture[10];
		for(int i=1;i<=textures.length;i++){
			switch(i){
			case 1:{
				textures[i] = new Texture("bckgrnd/info1.png");
			}default:{
				/**TODO: Infos*/
			}
			}
		}
		
		MainMenu.music.play();
		
		backgroundSprite = new Sprite(textures[iter]);
		backgroundSprite.setBounds(0.0F, 0.0F, width, height);
		backgroundSprite.setColor(Color.GRAY);
		
		if(!INF.lngRussian){
			back = new SFButtonS("btns/back", 0.132F*width, width - 0.147F*width, 0.005F*height, 1.0F);
		}else{
			back = new SFButtonS("btns/RU/backR", 0.132F*width, width - 0.147F*width, 0.005F*height, 1.0F);
		}

		FreeTypeFontGenerator genUS = new FreeTypeFontGenerator(Gdx.files.internal("fonts/prototype.ttf"));
		FreeTypeFontGenerator genRU = new FreeTypeFontGenerator(Gdx.files.internal("fonts/9840.otf"));
		FreeTypeFontParameter param2 = new FreeTypeFontParameter();
		param2.color = Color.WHITE;
		param2.size = 40;
		if(INF.lngRussian){
			param2.characters = FONT_CHARS_RU;
			text = genRU.generateFont(param2);
		}else{
			text = genUS.generateFont(param2);
		}
		text.getData().setScale((float)(0.00115F*height));
		
		genRU.dispose();
		genUS.dispose();
	}

	@Override
	public void render(float delta) {
		INF.elapsedTime++;
		resourcesCheck();
		
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.batch.begin();
		
		backgroundSprite.draw(game.batch);
		
		if(controller.isOn(back.getX(), back.getY(), back.getWidth(), back.getHeight())){
			back.setMode(true);
		}else{
			back.setMode(false);
		}
		back.getSprite().draw(game.batch);
		
		textDraw();
		
		game.batch.end();
		
		if(controller.isClicked(back.getX(), back.getY(), back.getWidth(), back.getHeight())){
			game.setScreen(screen);
			this.dispose();
		}
		
	}
	
	private void textDraw(){
		if(iter==1){
			if(!INF.lngRussian){
				text.draw(game.batch, "Launch vehicle", 0.4F*width, 0.965F*height);
				text.draw(game.batch, "Launch vehicle - a missile which launchs into the", 0.01F*width, 0.965F*height - 2.0F*ots);
				text.draw(game.batch, "space satellites, telescopes and payload for the", 0.01F*width, 0.965F*height - 3.0F*ots);
				text.draw(game.batch, "astronauts. There are light, middle, heavy", 0.01F*width, 0.965F*height - 4.0F*ots);
				text.draw(game.batch, "and super-heavy launch vehicles. Usually, these", 0.01F*width, 0.965F*height - 5.0F*ots);
				text.draw(game.batch, "rockets have a few stages. Every stage has", 0.01F*width, 0.965F*height - 6.0F*ots);
				text.draw(game.batch, "some fuel for the rocket. When fuel is over stage", 0.01F*width, 0.965F*height - 7.0F*ots);
				text.draw(game.batch, "detaches from the rocket. Empty stage has", 0.01F*width, 0.965F*height - 8.0F*ots);
				text.draw(game.batch, "a huge mass, so it is detached from the", 0.01F*width, 0.965F*height - 9.0F*ots);
				text.draw(game.batch, "rocket to make it lighter. The most of launch", 0.01F*width, 0.965F*height - 10.0F*ots);
				text.draw(game.batch, "vehicles uses kerosene and hydrogen as a fuel.", 0.01F*width, 0.965F*height - 11.0F*ots);
			}else{
				text.draw(game.batch, "Ракета-носитель", 0.38F*width, 0.965F*height);
				text.draw(game.batch, "Ракета-носитель - это ракета, которая выводит", 0.01F*width, 0.965F*height - 2.0F*ots);
				text.draw(game.batch, "в космос спутники, телескопы и полезный груз", 0.01F*width, 0.965F*height - 3.0F*ots);
				text.draw(game.batch, "для космонавтов. Бывают лёгкие, средние,", 0.01F*width, 0.965F*height - 4.0F*ots);
				text.draw(game.batch, "тяжёлые и сверхтяжёлые ракеты-носители.", 0.01F*width, 0.965F*height - 5.0F*ots);
				text.draw(game.batch, "Обычно у таких ракет есть несколько ступеней.", 0.01F*width, 0.965F*height - 6.0F*ots);
				text.draw(game.batch, "Каждая ступень несёт в себе топливо для", 0.01F*width, 0.965F*height - 7.0F*ots);
				text.draw(game.batch, "ракеты. Когда топливо заканчивается,", 0.01F*width, 0.965F*height - 8.0F*ots);
				text.draw(game.batch, "ступень отделяется от ракеты. Пустая", 0.01F*width, 0.965F*height - 9.0F*ots);
				text.draw(game.batch, "ступень имеет большую массу,", 0.01F*width, 0.965F*height - 10.0F*ots);
				text.draw(game.batch, "поэтому её отделяют от ракеты, чтобы", 0.01F*width, 0.965F*height - 11.0F*ots);
				text.draw(game.batch, "было легче продолжать полёт. Большинство", 0.01F*width, 0.965F*height - 12.0F*ots);
				text.draw(game.batch, "ракет-носителей летают на жидком топливе,", 0.01F*width, 0.965F*height - 13.0F*ots);
				text.draw(game.batch, "например, на керосине или водороде.", 0.01F*width, 0.965F*height - 14.0F*ots);
			}
		}
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
		text.dispose();
	}

}