package ru.erked.sflight.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;

import ru.erked.sflight.game.GameScreen;
import ru.erked.sflight.game.PlanetScreen;
 
public class SFlightInputController implements InputProcessor {
	
	public static final float width = Gdx.graphics.getWidth();
	public static final float height = Gdx.graphics.getHeight();
	private Sound clickSound = Gdx.audio.newSound(Gdx.files.internal("sounds/misc/click.wav"));
	
	/**Current cursor coordinates*/
	public static int cursorX;
	public static int cursorY;
	
	/**Current cursor coordinates in drag*/
	public static int touchDragX;
	public static int touchDragY;
	
	/**Current cursor coordinates from start drag*/
	public static int touchDownX;
	public static int touchDownY;
	public static int touchDownXGame;
	public static int touchDownYGame;
	public static int touchDownXNo;
	public static int touchDownYNo;
	
	/**Current cursor coordinates where finished drag*/
	public static int touchUpX;
	public static int touchUpY;
	
	public boolean isOn(float x, float y, float width, float height){
		if(touchDownX >= x && touchDownX <= x + width){
			if(touchDownY >= SFlightInputController.height - y - height && touchDownY <= SFlightInputController.height - y){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	public boolean isOnForClick(float x, float y, float width, float height){
		if(touchDownXNo >= x && touchDownXNo <= x + width){
			if(touchDownYNo >= SFlightInputController.height - y - height && touchDownYNo <= SFlightInputController.height - y){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	public boolean isClicked(float x, float y, float width, float height){
		if(isOnForClick(x, y, width, height)){
			if(touchUpX >= x && touchUpX <= x + width){
				if(touchUpY >= SFlightInputController.height - y - height && touchUpY <= SFlightInputController.height - y){
					touchDownXNo = 10000;
					touchDownYNo = 10000;
					touchUpX = 10000;
					touchUpY = 10000;
					clickSound.play(0.5F);
					return true;
				}else{
					return false;
				}
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	/**Magic*/
	public boolean isOnGame(float x, float y, float width, float height){
		if(touchDownX != 0 && touchDownY != 0){
			if((touchDownX + (GameScreen.camera.position.x - SFlightInputController.width/2)) >= x && (SFlightInputController.touchDownX + (GameScreen.camera.position.x - SFlightInputController.width/2)) <= x + width){
				if((Math.abs((-1)*SFlightInputController.touchDownY + SFlightInputController.height) + (GameScreen.camera.position.y + SFlightInputController.height/2 - SFlightInputController.height)) >= y && (Math.abs((-1)*SFlightInputController.touchDownY + SFlightInputController.height) + (GameScreen.camera.position.y - SFlightInputController.height/2)) <= y + height){
					return true;
				}else{
					return false;
				}
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	public boolean isOnGameStatic(float x, float y, float width, float height){
		if((touchDownXGame + (GameScreen.camera.position.x - SFlightInputController.width/2)) >= x && (SFlightInputController.touchDownXGame + (GameScreen.camera.position.x - SFlightInputController.width/2)) <= x + width){
			if((Math.abs((-1)*SFlightInputController.touchDownYGame + SFlightInputController.height) + (GameScreen.camera.position.y - SFlightInputController.height/2)) >= y && (Math.abs((-1)*SFlightInputController.touchDownYGame + SFlightInputController.height) + (GameScreen.camera.position.y - SFlightInputController.height/2)) <= y + height){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	public boolean isClickedGame(float x, float y, float width, float height){
		if(isOnGameStatic(x, y, width, height)){
			if((touchUpX + (GameScreen.camera.position.x - SFlightInputController.width/2)) >= x && (SFlightInputController.touchUpX + (GameScreen.camera.position.x - SFlightInputController.width/2)) <= x + width){
				if((Math.abs((-1)*SFlightInputController.touchUpY + SFlightInputController.height) + (GameScreen.camera.position.y - SFlightInputController.height/2)) >= y && (Math.abs((-1)*SFlightInputController.touchUpY + SFlightInputController.height) + (GameScreen.camera.position.y - SFlightInputController.height/2)) <= y + height){
					touchUpX = 10000;
					touchUpY = 10000;
					touchDownXGame = 0;
					touchDownYGame = 0;
					clickSound.play(0.5F);
					return true;
				}else{
					return false;
				}
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	/**Magic*/
	/**Magic#2*/
	public boolean isOnGameHangar(float x, float y, float width, float height){
		if(touchDownX != 0 && touchDownY != 0){
			if((touchDownX + (PlanetScreen.camera.position.x - SFlightInputController.width/2)) >= x && (SFlightInputController.touchDownX + (PlanetScreen.camera.position.x - SFlightInputController.width/2)) <= x + width){
				if((Math.abs((-1)*SFlightInputController.touchDownY + SFlightInputController.height) + (PlanetScreen.camera.position.y - SFlightInputController.height/2)) >= y && (Math.abs((-1)*SFlightInputController.touchDownY + SFlightInputController.height) + (PlanetScreen.camera.position.y - SFlightInputController.height/2)) <= y + height){
					return true;
				}else{
					return false;
				}
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	public boolean isOnGameStaticHangar(float x, float y, float width, float height){
		if((touchDownXGame + (PlanetScreen.camera.position.x - SFlightInputController.width/2)) >= x && (SFlightInputController.touchDownXGame + (PlanetScreen.camera.position.x - SFlightInputController.width/2)) <= x + width){
			if((Math.abs((-1)*SFlightInputController.touchDownYGame + SFlightInputController.height) + (PlanetScreen.camera.position.y - SFlightInputController.height/2)) >= y && (Math.abs((-1)*SFlightInputController.touchDownYGame + SFlightInputController.height) + (PlanetScreen.camera.position.y - SFlightInputController.height/2)) <= y + height){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	public boolean isClickedGameHangar(float x, float y, float width, float height){
		if(isOnGameStaticHangar(x, y, width, height)){
			if((touchUpX + (PlanetScreen.camera.position.x - SFlightInputController.width/2)) >= x && (SFlightInputController.touchUpX + (PlanetScreen.camera.position.x - SFlightInputController.width/2)) <= x + width){
				if((Math.abs((-1)*SFlightInputController.touchUpY + SFlightInputController.height) + (PlanetScreen.camera.position.y - SFlightInputController.height/2)) >= y && (Math.abs((-1)*SFlightInputController.touchUpY + SFlightInputController.height) + (PlanetScreen.camera.position.y - SFlightInputController.height/2)) <= y + height){
					touchUpX = 10000;
					touchUpY = 10000;
					touchDownXGame = 0;
					touchDownYGame = 0;
					clickSound.play(0.5F);
					return true;
				}else{
					return false;
				}
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	/**Magic#2*/
	
	@Override
	public boolean keyDown(int keycode) {
		
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {

		return false;
	}

	@Override
	public boolean keyTyped(char character) {

		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		touchDownX = screenX;
		touchDownY = screenY;
		touchDownXGame = screenX;
		touchDownYGame = screenY;
		touchDownXNo = screenX;
		touchDownYNo = screenY;
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		touchDragX = 0;
		touchDragY = 0;
		touchDownX = 0;
		touchDownY = 0;
		touchUpX = screenX;
		touchUpY = screenY;
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		touchDownXGame = 0;
		touchDownYGame = 0;
		touchDragX = screenX;
		touchDragY = screenY;
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		cursorX = screenX;
		cursorY = screenY;
		return false;
	}

	@Override
	public boolean scrolled(int amount) {

		return false;
	}

}
