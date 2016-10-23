package ru.erked.sflight.menu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.erked.sflight.controllers.SFlightInputController;
import ru.erked.sflight.random.ImgResDraw;
import ru.erked.sflight.random.InfoAndStats;
import ru.erked.sflight.tech.SFButtonS;

public class AboutScreen implements Screen{

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
	private Texture creditsTexture;
	public static Sprite creditsSprite;
	public static float creditsTentionIndex;
	private float creditsX;
	private float creditsY;
	private float creditsWidth;
	private float creditsHeight;
	
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
		
		creditsTexture = new Texture("random/credits.png");
		if(!InfoAndStats.lngRussian)
			creditsSprite = new Sprite(creditsTexture);
		else
			creditsSprite = new Sprite(ImgResDraw.creditsRU);
		creditsTentionIndex = (float)(creditsSprite.getWidth()/creditsSprite.getHeight());
		creditsWidth = 0.5F*width;
		creditsHeight = (float)(creditsWidth/creditsTentionIndex);
		creditsX = width/2 - creditsWidth/2;
		creditsY = 0.0F - creditsHeight;
		creditsSprite.setBounds(creditsX, creditsY, creditsWidth, creditsHeight);
		
		if(!InfoAndStats.lngRussian){
			back = new SFButtonS("btns/back", 0.132F*width, width - 0.147F*width, 0.005F*height, 1.0F);
		}else{
			back = new SFButtonS("btns/RU/backR", 0.132F*width, width - 0.147F*width, 0.005F*height, 1.0F);
		}
		
		blackAlpha.setBounds(0.0F, 0.0F, width, height);
		blackAlpha.setAlpha(1.0F);
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
		
		creditsSprite.setY(creditsSprite.getY() + 0.85F);
		if(creditsSprite.getY() > height)
			creditsSprite.setY(0.0F - creditsHeight);
		if(creditsSprite.getY() < 0.0F - creditsHeight)
			creditsSprite.setY(height);
		if(prevDragY != 0.0F && SFlightInputController.touchDragY != 0.0F)
			creditsSprite.setY(creditsSprite.getY() - (SFlightInputController.touchDragY - prevDragY));
		prevDragY = SFlightInputController.touchDragY;
		
		batch.begin();
		
		MainMenu.backgroundSprite.draw(batch);
		MainMenu.planet1Sprite.draw(batch);
		MainMenu.planet2Sprite.draw(batch);		
		MainMenu.cometSprite.draw(batch);
		
		creditsSprite.draw(batch);
		
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
		creditsTexture.dispose();
		game.dispose();
		batch.dispose();
		MainMenu.planet2X = MainMenu.planet2Sprite.getX();
		MainMenu.planet2Y = MainMenu.planet2Sprite.getY();
		
		MainMenu.isFirstScreen = false;
	}

}