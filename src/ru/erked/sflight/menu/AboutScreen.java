package ru.erked.sflight.menu;

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
import ru.erked.sflight.random.InfoAndStats;
import ru.erked.sflight.tech.SFButtonS;

public class AboutScreen implements Screen{

	final String FONT_CHARS_RU = "абвгдежзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
	private static final float width = Gdx.graphics.getWidth();
	private static final float height = Gdx.graphics.getHeight();
	
	private Game game;
	private SpriteBatch batch;
	private SFlightInputController controller;
	
	//Rotation
	public static float planet1PrevRotation = 0.0F;
	public static float planet2PrevRotation = 0.0F;
	public static float cometPrevRotation;
	
	//Credits
	private String[] str;
	private float merge[];
	private float edge[];
	private static BitmapFont header;
	private static BitmapFont headerB;
	private static BitmapFont text;
	private static BitmapFont textB;
	private float textY;
	
	//"Back" Button
	private SFButtonS back;
	
	private Sprite blackAlpha = new Sprite(new Texture("objects/black.png"));
	private float alp = 1.0F;
	private boolean isTransAbout;
	
	//Scroll
	private static float prevDragY;
	
	public AboutScreen(Game game){
		this.game = game;
	}
	
	@Override
	public void show() {
		
		batch = new SpriteBatch();
		controller = new SFlightInputController();
		isTransAbout = false;
		
		MainMenu.music.play();
		
		if(!InfoAndStats.lngRussian){
			back = new SFButtonS("btns/back", 0.132F*width, width - 0.147F*width, 0.005F*height, 1.0F);
		}else{
			back = new SFButtonS("btns/RU/backR", 0.132F*width, width - 0.147F*width, 0.005F*height, 1.0F);
		}
		
		blackAlpha.setBounds(0.0F, 0.0F, width, height);
		blackAlpha.setAlpha(1.0F);
		
		textY = 0;
		FreeTypeFontGenerator genUS = new FreeTypeFontGenerator(Gdx.files.internal("fonts/prototype.ttf"));
		FreeTypeFontGenerator genRU = new FreeTypeFontGenerator(Gdx.files.internal("fonts/9840.otf"));
		FreeTypeFontParameter param = new FreeTypeFontParameter();
		FreeTypeFontParameter paramBH = new FreeTypeFontParameter();
		FreeTypeFontParameter paramBT = new FreeTypeFontParameter();
		param.color = Color.WHITE;
		param.size = 50;
		paramBT.color = Color.BLACK;
		paramBT.size = 50;
		if(InfoAndStats.lngRussian){
			param.characters = FONT_CHARS_RU;
			paramBT.characters = FONT_CHARS_RU;
			header = genRU.generateFont(param);
			headerB = genRU.generateFont(paramBT);
		}else{
			header = genUS.generateFont(param);
			headerB = genUS.generateFont(paramBT);
		}
		FreeTypeFontParameter param2 = new FreeTypeFontParameter();
		param2.color = Color.WHITE;
		param2.size = 40;
		paramBH.color = Color.BLACK;
		paramBH.size = 40;
		if(InfoAndStats.lngRussian){
			paramBH.characters = FONT_CHARS_RU;
			param2.characters = FONT_CHARS_RU;
			text = genRU.generateFont(param2);
			textB = genRU.generateFont(paramBH);
		}else{
			text = genUS.generateFont(param2);
			textB = genUS.generateFont(paramBH);
		}
		
		header.getData().setScale((float)(0.0015F*width));
		text.getData().setScale((float)(0.00085F*width));
		headerB.getData().setScale((float)(0.0015F*width));
		textB.getData().setScale((float)(0.00085F*width));
		
		if(!InfoAndStats.lngRussian)
			str = new String[]{"Space Flight","Developers:","Programmer, sound engineer","and main designer:","Egor Klement'ev","Designers:","Aleksey Solovyov","Danis Latipov","With support of:","Samsung SW&IT School"};
		else
			str = new String[]{"Space Flight","Разработчики:","Программист, звукорежиссер","и главный дизайнер:","Егор Клементьев","Дизайнеры:","Алексей Соловьев","Данис Латыпов","При поддержке:","Samsung SW&IT School"};
		
		if(!InfoAndStats.lngRussian){
			merge = new float[]{0.0F,
					4.5F*text.getCapHeight(),
					6.0F*text.getCapHeight(),
					7.5F*text.getCapHeight(),
					10.0F*text.getCapHeight(),
					12.5F*text.getCapHeight(),
					15.0F*text.getCapHeight(),
					16.5F*text.getCapHeight(),
					19.0F*text.getCapHeight(),
					20.5F*text.getCapHeight()};
			edge = new float[]{0.0F,
					0.4F*width,
					0.275F*width,
					0.35F*width,
					0.36F*width,
					0.4075F*width,
					0.345F*width,
					0.385F*width,
					0.365F*width,
					0.305F*width};
		}else{
			merge = new float[]{0.0F,
					4.5F*text.getCapHeight(),
					6.0F*text.getCapHeight(),
					7.5F*text.getCapHeight(),
					10.0F*text.getCapHeight(),
					12.5F*text.getCapHeight(),
					14.0F*text.getCapHeight(),
					15.5F*text.getCapHeight(),
					18.0F*text.getCapHeight(),
					19.5F*text.getCapHeight()};
			edge = new float[]{0.0F,
					0.385F*width,
					0.25F*width,
					0.335F*width,
					0.36F*width,
					0.4075F*width,
					0.35F*width,
					0.3675F*width,
					0.365F*width,
					0.305F*width};
		}
		genRU.dispose();
		genUS.dispose();
		
	}

	@Override
	public void render(float delta) {
		InfoAndStats.elapsedTime++;
		
		if(alp>0.0F && !isTransAbout){
			blackAlpha.setAlpha(alp);
			alp-=0.05F;
		}else if(!isTransAbout){
			blackAlpha.setAlpha(0.0F);
			alp = 0.0F;
		}
		
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		MainMenu.planet1Sprite.setOriginCenter();
		MainMenu.planet1Sprite.rotate(0.0175F);
		
		MainMenu.planet2Sprite.setOriginCenter();
		MainMenu.planet2Sprite.rotate(-0.01F);
		MainMenu.planet2Sprite.setY((float)(MainMenu.planet2Sprite.getY() + 0.03F));
		MainMenu.planet2Sprite.setX(MainMenu.planet2Sprite.getX() - 0.3F);
		if(MainMenu.planet2Sprite.getX() < (0-1)*width){
			MainMenu.planet2Sprite.setX(1.05F*width + MainMenu.planet2Sprite.getWidth());
			MainMenu.planet2Sprite.setY(0.05F*height);
		}

		MainMenu.cometSprite.setOrigin(2*width, 2*height);
		MainMenu.cometSprite.rotate(0.25F);
		
		textY+=0.85F;
		if(!((textY - 18.0F*text.getCapHeight()) < (height + 1.5F*header.getCapHeight()))){
			textY=-1.5F*header.getCapHeight();
		}
		if(textY < -1.5F*header.getCapHeight()){
			textY=height + 18.0F*text.getCapHeight();
		}
		
		if(prevDragY != 0.0F && SFlightInputController.touchDragY != 0.0F)
			textY -= SFlightInputController.touchDragY - prevDragY;
		prevDragY = SFlightInputController.touchDragY;
		
		batch.begin();
		
		MainMenu.backgroundSprite.draw(batch);
		MainMenu.planet1Sprite.draw(batch);
		MainMenu.planet2Sprite.draw(batch);		
		MainMenu.cometSprite.draw(batch);
		
		for(int i=0;i<10;i++){
			headerB.draw(batch, str[0], 0.285F*width + 0.005F*width, textY - 0.005F*height);
			header.draw(batch, str[0], 0.285F*width, textY);
			if(i!=0){
				textB.draw(batch, str[i], edge[i] + 0.0025F*width, textY - merge[i] - 0.005F*height);
				text.draw(batch, str[i], edge[i], textY - merge[i]);
			}
		}
		
		if(controller.isOn(back.getX(), back.getY(), back.getWidth(), back.getHeight())){
			back.setMode(true);
		}else{
			back.setMode(false);
		}
		back.getSprite().draw(batch);
		
		blackAlpha.draw(batch);
		
		batch.end();
		
		cometPrevRotation = MainMenu.cometSprite.getRotation();
		planet1PrevRotation = MainMenu.planet1Sprite.getRotation();
		planet2PrevRotation = MainMenu.planet2Sprite.getRotation();
		
		if(controller.isClicked(back.getX(), back.getY(), back.getWidth(), back.getHeight()) || isTransAbout){
			isTransAbout = true;
			if(alp>1.0F){
				this.dispose();
				game.setScreen(new MainMenu(game));
			}else{
				blackAlpha.setAlpha(alp);
				alp+=0.05F;
			}
		}
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
		game.dispose();
		batch.dispose();
		MainMenu.planet2X = MainMenu.planet2Sprite.getX();
		MainMenu.planet2Y = MainMenu.planet2Sprite.getY();
		
		MainMenu.isFirstScreen = false;
	}

}