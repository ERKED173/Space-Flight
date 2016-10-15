package ru.erked.sflight.tech;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SFButtonS {

	private float x;
	private float y;
	private float width;
	private float height;
	private Texture textureCur;
	private Texture textureI;
	private Texture textureA;
	private float aspectRatio;
	private float aspect;
	private Sprite sprite;
	
	public SFButtonS(String texture, float width, float x, float y){
		this.x = x;
		this.y = y;
		textureI = new Texture(texture + "I.png");
		textureA = new Texture(texture + "A.png");
		aspect = (float)textureA.getWidth()/(float)textureI.getWidth();
		textureCur = textureI;
		aspectRatio = (float)this.textureI.getWidth()/(float)this.textureI.getHeight();
		this.width = width;
		height = (float)this.width/(float)aspectRatio;
		sprite = new Sprite(this.textureI);
		sprite.setBounds(this.x, this.y, this.width, height);
	}
	
	public float getX(){
		return x;
	}
	
	public void setX(float x){
		this.x = x;
		sprite.setBounds(this.x, y, width, height);
	}
	
	public float getY(){
		return y;
	}
	
	public void setY(float y){
		this.y = y;
		sprite.setBounds(x, this.y, width, height);
	}
	
	public float getWidth(){
		return width;
	}
	
	public void setWidth(float width){
		this.width = width;
		sprite.setBounds(x, y, this.width, height);
	}
	
	public float getHeight(){
		return height;
	}
	
	public void setHeight(float height){
		this.height = height;
		sprite.setBounds(x, y, width, this.height);
	}

	public Texture getTexture(){
		return textureCur;
	}
	
	public void setMode(boolean bool){
		if(bool){
			textureCur = textureA;
			sprite.setTexture(textureCur);
			sprite.setBounds(x - 0.5F*((width*aspect)-width), y - 0.5F*((height*aspect)-height), width*aspect, height*aspect);
		}else{
			textureCur = textureI;
			sprite.setTexture(textureCur);
			sprite.setBounds(x, y, width, height);
		}
	}
	
	public boolean isActiveMode(){
		if(textureCur.equals(textureA)){
			return true;
		}else{
			return false;
		}
	}
	
	public Sprite getSprite(){
		return sprite;
	}
	
	public void setSprite(Sprite sprite){
		this.sprite = sprite;
		this.sprite.setBounds(x, y, width, height);
	}
	
}
