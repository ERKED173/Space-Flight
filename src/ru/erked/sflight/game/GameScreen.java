package ru.erked.sflight.game;

import java.util.Random;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Vector3;

import ru.erked.sflight.controllers.SFlightInputController;
import ru.erked.sflight.menu.MainMenu;
import ru.erked.sflight.random.ImgResDraw;
import ru.erked.sflight.random.InfoAndStats;

public class GameScreen implements Screen{

	final String FONT_CHARS_RU = "абвгдежзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
	private static final float width = Gdx.graphics.getWidth();
	private static final float height = Gdx.graphics.getHeight();
	private final Random rand = new Random();
	
	private Game game;
	public static OrthographicCamera camera;
	private SpriteBatch batch;
	private SFlightInputController controller;
	
	public static boolean isFromMenu = true;
	
//Фон
	private Texture backgroundTexture; //Текстура фона
	public static Sprite backgroundSprite; //Спрайт фона
	private float backgroundX;
	private float backgroundY;
	public static final float backgroundTentionIndex = (float)2*width/2560.0F;
	
//Ангар_1
	private Texture angar1Texture;
	public static Sprite angar1Sprite;
	private float angar1Width;
	private float angar1Height;
	private float angar1X;
	private float angar1Y;
	private float angar1TentionIndex;
//Ангар_2
	private Texture angar2Texture;
	public static Sprite angar2Sprite;
	private float angar2Width;
	private float angar2Height;
	private float angar2X;
	private float angar2Y;
	
//Аналитический_центр_1
	private Texture analytic1Texture;
	public static Sprite analytic1Sprite;
	private float analytic1Width;
	private float analytic1Height;
	private float analytic1X;
	private float analytic1Y;
	private float analytic1TentionIndex;
//Аналитический_центр_2
	private Texture analytic2Texture;
	public static Sprite analytic2Sprite;
	private float analytic2Width;
	private float analytic2Height;
	private float analytic2X;
	private float analytic2Y;
	
	//Диспетчерская_вышка_1
	private Texture control1Texture;
	public static Sprite control1Sprite;
	private float control1Width;
	private float control1Height;
	private float control1X;
	private float control1Y;
	private float control1TentionIndex;
	//Диспетчерская_вышка_2
	private Texture control2Texture;
	public static Sprite control2Sprite;
	private float control2Width;
	private float control2Height;
	private float control2X;
	private float control2Y;
	
	//Ангар_1
	private Texture scienceCentre1Texture;
	public static Sprite scienceCentre1Sprite;
	private float scienceCentre1Width;
	private float scienceCentre1Height;
	private float scienceCentre1X;
	private float scienceCentre1Y;
	private float scienceCentre1TentionIndex;
	//Ангар_2
	private Texture scienceCentre2Texture;
	public static Sprite scienceCentre2Sprite;
	private float scienceCentre2Width;
	private float scienceCentre2Height;
	private float scienceCentre2X;
	private float scienceCentre2Y;
	
//Облако_1
	private Texture cloud1Texture;
	public static Sprite cloud1Sprite;
	private float cloud1Width;
	private float cloud1Height;
	private float cloud1X;
	private static float cloud1PrevX;
	private float cloud1Y;
	private static float cloud1PrevY;
	private float cloud1TentionIndex;
//Облако_2
	private Texture cloud2Texture;
	public static Sprite cloud2Sprite;
	private float cloud2Width;
	private float cloud2Height;
	private float cloud2X;
	private static float cloud2PrevX;
	private float cloud2Y;
	private static float cloud2PrevY;
	private float cloud2TentionIndex;
//Ещё облака
	public static Sprite cloud3Sprite;
	private float cloud3X;
	private static float cloud3PrevX;
	private float cloud3Y;
	private static float cloud3PrevY;
	public static Sprite cloud4Sprite;
	private float cloud4X;
	private static float cloud4PrevX;
	private float cloud4Y;
	private static float cloud4PrevY;
	private float speed1 = 0.01F*rand.nextInt(100);
	private float speed2 = 0.01F*rand.nextInt(100);
	private float speed3 = 0.01F*rand.nextInt(100);
	private float speed4 = 0.01F*rand.nextInt(100);
	
//Для прокрутки
	private static float prevDragX;
	private static float prevDragY;
	
//Копка "Main Menu"
	private Texture backButtonInactive;
	private Texture backButtonActive;
	private Sprite backButtonInactiveSprite;
	private Sprite backButtonActiveSprite;
	private float backButtonX;
	private float backButtonY;
	private float backButtonWidth;
	private float backButtonHeight;
	public static float backButtonTentionIndex; //Соотношение сторон кнопки
	
//Иконка КосмоКоинов
	private Sprite moneySprite;
	private Sprite fuelSprite;
	private Sprite metalSprite;
	private float moneyX;
	private float moneyY;
	private float moneyWidth;
	private float moneyHeight;
	private static BitmapFont text;
	private Sprite line;
	private Sprite cosmocoinLine;
	private Sprite fuelLine;
	private Sprite metalLine;
	
	private Music launchSoundPath = Gdx.audio.newMusic(Gdx.files.internal("sounds/misc/WavLibraryNet_Sound6386.mp3"));
	
	private Sprite blackAlpha = new Sprite(new Texture("objects/black.png"));
	private float alp = 1.0F;
	private boolean isTransGame;
	
	public GameScreen(Game game){
		this.game = game;
	}
	
	@Override
	public void show() {

		resourcesCheck();
		
		batch = new SpriteBatch();
		controller = new SFlightInputController();
		MainMenu.music.play();
//Фон\\
		backgroundTexture = new Texture("bckgrnd/spaceport_4.png");
		backgroundSprite = new Sprite(backgroundTexture);
		backgroundX = 0.0F;
		backgroundY = 0.0F;
		backgroundSprite.setBounds(backgroundX, backgroundY, width*2, backgroundTentionIndex*2560.0F);
		
//Камера\\
		camera = new OrthographicCamera(width, height);
		camera.position.set(new Vector3(0.6F*backgroundSprite.getWidth(), 0.8F*backgroundSprite.getHeight(), 0));
		
//Обычный белый текст\\
		FreeTypeFontGenerator genUS = new FreeTypeFontGenerator(Gdx.files.internal("fonts/prototype.ttf"));
		FreeTypeFontGenerator genRU = new FreeTypeFontGenerator(Gdx.files.internal("fonts/9840.otf"));
		FreeTypeFontParameter param = new FreeTypeFontParameter();
		FreeTypeFontParameter param2 = new FreeTypeFontParameter();
		param.color = Color.WHITE;
		param.size = 40;
		param2.color = Color.RED;
		param2.size = 60;
		if(InfoAndStats.lngRussian){
			param.characters = FONT_CHARS_RU;
			param2.characters = FONT_CHARS_RU;
			text = genRU.generateFont(param);
		}else{
			text = genUS.generateFont(param);
		}
		text.getData().setScale((float)(0.00045F*width));
		
		mainMenuButtonInit();
		analyticInit();
		angarInit();
		scienceCentreInit();
		cloudsInit();
		moneyInit();
		controlInit();
		
		isTransGame = false;
		blackAlpha.setBounds(0.0F, 0.0F, backgroundSprite.getWidth(), backgroundSprite.getHeight());
		blackAlpha.setAlpha(1.0F);
	}

	@Override
	public void render(float delta) {
		InfoAndStats.elapsedTime++;
		
		if(alp>0.0F && (!isTransGame)){
			blackAlpha.setAlpha(alp);
			alp-=0.05F;
		}else if(!isTransGame){
			blackAlpha.setAlpha(0.0F);
			alp = 0.0F;
		}
		
//Необходимо для уничтожения эффекта следов*/
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		touchUpdate();
		
		mainMenuButtonCoords();
		cloudsCoords(delta);
		moneyCoords();
		
//Апдейт камеры обязательно после всех манипуляций с ней*/
//И именно в таком порядке*/
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		
		/**Отрисовка объектов*/
		batch.begin();
		
		backgroundSprite.draw(batch);
		
		drawBuildings();
		drawClouds();
		drawButtons();
		drawMoney();

		blackAlpha.draw(batch);
		
		batch.end();
		/**Отрисовка объектов*/
		
		buttonListener();
		
	}
	
	private void mainMenuButtonCoords(){
//Установка координат кнопки "Main Menu"*/
		backButtonX = camera.position.x - backButtonWidth + (width/2 - 0.01F*width);
		backButtonY = camera.position.y - (height/2 - 0.01F*height);
		backButtonInactiveSprite.setX(backButtonX);
		backButtonInactiveSprite.setY(backButtonY);
		backButtonActiveSprite.setX(backButtonX);
		backButtonActiveSprite.setY(backButtonY);
	}	
	private void cloudsCoords(float delta){
		//Установка коодрдинат для облаков
		if(cloud1Sprite.getX() > backgroundX + backgroundSprite.getWidth()){
			cloud1Sprite.setX(0.0F - rand.nextInt((int)(width)) - cloud1Width);
			cloud1Sprite.setY(0.0F + rand.nextInt((int)(backgroundSprite.getHeight() - cloud1Height)));
			speed1 = delta*rand.nextInt(100);
		}
		else
			cloud1Sprite.setX(cloud1Sprite.getX() + speed1);
		/***/
		if(cloud2Sprite.getX() > backgroundX + backgroundSprite.getWidth()){
			cloud2Sprite.setX(0.0F - rand.nextInt((int)(width)) - cloud2Width);
			cloud2Sprite.setY(0.0F + rand.nextInt((int)(backgroundSprite.getHeight() - cloud1Height)));
			speed2 = delta*rand.nextInt(100);
		}
		else
			cloud2Sprite.setX(cloud2Sprite.getX() + speed2);
		/***/
		if(cloud3Sprite.getX() > backgroundX + backgroundSprite.getWidth()){
			cloud3Sprite.setX(0.0F - rand.nextInt((int)(width)) - cloud1Width);
			cloud3Sprite.setY(0.0F + rand.nextInt((int)(backgroundSprite.getHeight() - cloud1Height)));
			speed3 = delta*rand.nextInt(100);
		}
		else
			cloud3Sprite.setX(cloud3Sprite.getX() + speed3);
		/***/
		if(cloud4Sprite.getX() > backgroundX + backgroundSprite.getWidth()){
			cloud4Sprite.setX(0.0F - rand.nextInt((int)(width)) - cloud2Width);
			cloud4Sprite.setY(0.0F + rand.nextInt((int)(backgroundSprite.getHeight() - cloud2Height)));
			speed4 = delta*rand.nextInt(100);
		}
		else
			cloud4Sprite.setX(cloud4Sprite.getX() + speed4);
		//Установка коодрдинат для облаков
	}
	private void moneyCoords(){
		//Установка координат иконки КосмоКоинов*/
		moneyX = camera.position.x - moneyWidth - (width/2 - 0.065F*width);
		moneyY = camera.position.y - moneyHeight + (height/2 - 0.015F*height);
		moneySprite.setX(moneyX);
		moneySprite.setY(moneyY);
		fuelSprite.setX(moneyX + 10.0F*moneyWidth);
		fuelSprite.setY(moneyY);
		metalSprite.setX(moneyX + 5.0F*moneyWidth);
		metalSprite.setY(moneyY);
		
		cosmocoinLine.setBounds(
				cosmocoinLine.getX(),
				cosmocoinLine.getY(),
				((float)InfoAndStats.money/(float)InfoAndStats.moneyFull)*0.8F*line.getWidth() + 0.005F*width,
				cosmocoinLine.getHeight()
				);
		fuelLine.setBounds(
				fuelLine.getX(),
				fuelLine.getY(),
				((float)InfoAndStats.fuel/(float)InfoAndStats.fuelFull)*0.8F*line.getWidth() + 0.005F*width,
				fuelLine.getHeight()
				);
		metalLine.setBounds(
				metalLine.getX(),
				metalLine.getY(),
				((float)InfoAndStats.metal/(float)InfoAndStats.metalFull)*0.8F*line.getWidth() + 0.005F*width,
				metalLine.getHeight()
				);
		
		line.setX(moneyX + 1.15F*moneyWidth);
		line.setY(moneyY);
		cosmocoinLine.setX(moneySprite.getX() + 1.5F*moneySprite.getWidth());
		cosmocoinLine.setY(moneySprite.getY() + 0.25F*moneySprite.getHeight());
		fuelLine.setX(fuelSprite.getX() + 1.5F*fuelSprite.getWidth());
		fuelLine.setY(fuelSprite.getY() + 0.25F*fuelSprite.getHeight());
		metalLine.setX(metalSprite.getX() + 1.5F*metalSprite.getWidth());
		metalLine.setY(metalSprite.getY() + 0.25F*metalSprite.getHeight());
	}	
	
	private void mainMenuButtonInit(){
		//Кнопка "Main Menu"\\
				backButtonInactive = new Texture("btns/button_main_menu_inactive.png");
				backButtonActive = new Texture("btns/button_main_menu_active.png");
				backButtonInactiveSprite = new Sprite(backButtonInactive);
				backButtonActiveSprite = new Sprite(backButtonActive);
				if(InfoAndStats.lngRussian){
					backButtonInactiveSprite.setTexture(ImgResDraw.mainMenuButtonInactiveRU);
					backButtonActiveSprite.setTexture(ImgResDraw.mainMenuButtonActiveRU);
				}
				backButtonTentionIndex = (float)backButtonInactive.getWidth()/backButtonInactive.getHeight();
				backButtonWidth = 0.132F*width;
				backButtonHeight = backButtonWidth/backButtonTentionIndex;
				backButtonX = camera.position.x - backButtonWidth + (width/2 - 0.01F*width);
				backButtonY = camera.position.y - (height/2 - 0.01F*height);
				backButtonInactiveSprite.setBounds(backButtonX, backButtonY, backButtonWidth, backButtonHeight);
				backButtonActiveSprite.setBounds(backButtonX, backButtonY, backButtonWidth, backButtonHeight);
			}
	private void angarInit(){
		//Ангар_1\\
		angar1Texture = new Texture("objects/angar_1.png");
		angar1Sprite = new Sprite(angar1Texture);
		angar1TentionIndex = (float)angar1Sprite.getWidth()/angar1Sprite.getHeight();
		angar1Width = 0.5F*width;
		angar1Height = (float)angar1Width/angar1TentionIndex;
		angar1X = 0.150F*backgroundSprite.getWidth();
		angar1Y = 0.625F*backgroundSprite.getHeight();
		angar1Sprite.setBounds(angar1X, angar1Y, angar1Width, angar1Height);
		//Ангар_2\\
		angar2Texture = new Texture("objects/angar_2.png");
		angar2Sprite = new Sprite(angar2Texture);
		angar2Width = 0.625F*width;
		angar2Height = (float)angar2Width/angar1TentionIndex;
		angar2X = 0.150F*backgroundSprite.getWidth() - 0.1F*angar2Width;
		angar2Y = 0.625F*backgroundSprite.getHeight() - 0.10035714285714285714285714285714F*angar2Height;
		angar2Sprite.setBounds(angar2X, angar2Y, angar2Width, angar2Height);
	}	
	private void scienceCentreInit(){
		//Научный_Центр_1\\
		scienceCentre1Texture = new Texture("objects/scienceCentre1.png");
		scienceCentre1Sprite = new Sprite(scienceCentre1Texture);
		scienceCentre1TentionIndex = (float)scienceCentre1Sprite.getWidth()/scienceCentre1Sprite.getHeight();
		scienceCentre1Width = 0.5F*width;
		scienceCentre1Height = (float)scienceCentre1Width/scienceCentre1TentionIndex;
		scienceCentre1X = 0.359F*backgroundSprite.getWidth();
		scienceCentre1Y = 0.45F*backgroundSprite.getHeight();
		scienceCentre1Sprite.setBounds(scienceCentre1X, scienceCentre1Y, scienceCentre1Width, scienceCentre1Height);
		//Научный_Центр_2\\
		scienceCentre2Texture = new Texture("objects/scienceCentre2.png");
		scienceCentre2Sprite = new Sprite(scienceCentre2Texture);
		scienceCentre2Width = 0.73818897637795275590551181102363F*width;
		scienceCentre2Height = (float)scienceCentre2Width/scienceCentre1TentionIndex;
		scienceCentre2X = 0.359F*backgroundSprite.getWidth() - 0.16133333333333333333333333333334F*scienceCentre2Width;
		scienceCentre2Y = 0.45F*backgroundSprite.getHeight() - 0.16153846153846153846153846153846F*scienceCentre2Height;
		scienceCentre2Sprite.setBounds(scienceCentre2X, scienceCentre2Y, scienceCentre2Width, scienceCentre2Height);
	}	
	private void cloudsInit(){
		//Облако_1\\
		cloud1Texture = new Texture("objects/cloud_1.png");
		cloud1Sprite = new Sprite(cloud1Texture);
		cloud1TentionIndex = (float)cloud1Sprite.getWidth()/cloud1Sprite.getHeight();
		cloud1Width = 0.125F*width;
		cloud1Height = (float)cloud1Width/cloud1TentionIndex;
		if(isFromMenu){
			cloud1X = 0.0F - rand.nextInt((int)(width)) - cloud1Width;
			cloud1Y = 0.0F + rand.nextInt((int)(backgroundSprite.getHeight() - cloud1Height));
		}else{
			cloud1X = cloud1PrevX;
			cloud1Y = cloud1PrevY;
		}
		cloud1Sprite.setBounds(cloud1X, cloud1Y, cloud1Width, cloud1Height);
		//Облако_2\\
		cloud2Texture = new Texture("objects/cloud_2.png");
		cloud2Sprite = new Sprite(cloud2Texture);
		cloud2TentionIndex = (float)cloud2Sprite.getWidth()/cloud2Sprite.getHeight();
		cloud2Width = 0.150F*width;
		cloud2Height = (float)cloud2Width/cloud2TentionIndex;
		if(isFromMenu){
			cloud2X = 0.0F - rand.nextInt((int)(width)) - cloud2Width;
			cloud2Y = 0.0F + rand.nextInt((int)(backgroundSprite.getHeight() - cloud2Height));
		}else{
			cloud2X = cloud2PrevX;
			cloud2Y = cloud2PrevY;
		}
		cloud2Sprite.setBounds(cloud2X, cloud2Y, cloud2Width, cloud2Height);
		//Ещё облака\\
		cloud3Sprite = new Sprite(cloud1Texture);
		if(isFromMenu){
			cloud3X = 0.0F - rand.nextInt((int)(width)) - cloud1Width;
			cloud3Y = 0.0F + rand.nextInt((int)(backgroundSprite.getHeight() - cloud1Height));
		}else{
			cloud3X = cloud3PrevX;
			cloud3Y = cloud3PrevY;
		}
		cloud3Sprite.setBounds(cloud3X, cloud3Y, cloud1Width, cloud1Height);
		cloud4Sprite = new Sprite(cloud2Texture);
		if(isFromMenu){
			cloud4X = 0.0F - rand.nextInt((int)(width)) - cloud2Width;
			cloud4Y = 0.0F + rand.nextInt((int)(backgroundSprite.getHeight() - cloud2Height));
		}else{
			cloud4X = cloud4PrevX;
			cloud4Y = cloud4PrevY;
		}
		cloud4Sprite.setBounds(cloud4X, cloud4Y, cloud2Width, cloud2Height);	
	}
	private void analyticInit(){
		//Аналитический_центр_1\\
		analytic1Texture = new Texture("objects/analytic_1.png");
		analytic1Sprite = new Sprite(analytic1Texture);
		analytic1TentionIndex = (float)analytic1Sprite.getWidth()/analytic1Sprite.getHeight();
		analytic1Width = 0.2F*width;
		analytic1Height = (float)analytic1Width/analytic1TentionIndex;
		analytic1X = 0.739F*backgroundSprite.getWidth();
		analytic1Y = 0.785F*backgroundSprite.getHeight();
		analytic1Sprite.setBounds(analytic1X, analytic1Y, analytic1Width, analytic1Height);
		//Аналитический_центр_2\\
		analytic2Texture = new Texture("objects/analytic_2.png");
		analytic2Sprite = new Sprite(analytic2Texture);
		analytic2Width = 0.4F*width;
		analytic2Height = (float)analytic2Width/analytic1TentionIndex;
		analytic2X = 0.739F*backgroundSprite.getWidth() - 0.24909090909090909090909090909091F*analytic2Width;
		analytic2Y = 0.785F*backgroundSprite.getHeight() - 0.2509090909090909090909090909091F*analytic2Height;
		analytic2Sprite.setBounds(analytic2X, analytic2Y, analytic2Width, analytic2Height);
	}
	private void moneyInit(){
		//Иконки ресурсов\\
		moneySprite = new Sprite(new Texture("objects/cosmocoin.png"));
		fuelSprite = new Sprite(new Texture("objects/fuel.png"));
		metalSprite = new Sprite(new Texture("objects/metal.png"));
		
		line = new Sprite(new Texture("objects/line.png"));
		cosmocoinLine = new Sprite(new Texture("objects/cosmocoinLine.png"));
		fuelLine = new Sprite(new Texture("objects/fuelLine.png"));
		metalLine = new Sprite(new Texture("objects/metalLine.png"));
		
		moneyWidth = 0.05F*width;
		moneyHeight = moneyWidth;
		moneyX = camera.position.x - moneyWidth - (width/2 - 0.065F*width);
		moneyY = camera.position.y - moneyHeight + (height/2 - 0.015F*height);
		
		moneySprite.setBounds(moneyX, moneyY, moneyWidth, moneyHeight);
		fuelSprite.setBounds(moneyX + moneyWidth, moneyY, moneyWidth, moneyHeight);
		metalSprite.setBounds(moneyX + 2.0F*moneyWidth, moneyY, moneyWidth, moneyHeight);
		
		line.setBounds(moneyX + 1.1F*moneyWidth, moneyY - 2.1F*moneyHeight, moneyWidth/0.28125F, moneyWidth);
		cosmocoinLine.setBounds(moneySprite.getX() + moneySprite.getWidth() + moneySprite.getWidth()*0.5F, moneySprite.getY() + moneySprite.getHeight(), 0.5F*moneyWidth, 0.5F*moneyHeight);
		fuelLine.setBounds(fuelSprite.getX() + fuelSprite.getWidth() + fuelSprite.getWidth()*0.5F, fuelSprite.getY() + fuelSprite.getHeight(), 0.5F*moneyWidth, 0.5F*moneyHeight);
		metalLine.setBounds(metalLine.getX() + metalLine.getWidth() + metalLine.getWidth()*0.5F, metalLine.getY() + metalLine.getHeight(), 0.5F*moneyWidth, 0.5F*moneyHeight);
		
	}
	private void controlInit(){
		//Диспетчерская_вышка_1\\
		control1Texture = new Texture("objects/control_1.png");
		control1Sprite = new Sprite(control1Texture);
		control1TentionIndex = (float)control1Sprite.getWidth()/control1Sprite.getHeight();
		control1Width = 0.125F*width;
		control1Height = (float)control1Width/control1TentionIndex;
		control1X = 0.469F*backgroundSprite.getWidth();
		control1Y = 0.665F*backgroundSprite.getHeight();
		control1Sprite.setBounds(control1X, control1Y, control1Width, control1Height);
		//Диспетчерская_вышка_2\\
		control2Texture = new Texture("objects/control_2.png");
		control2Sprite = new Sprite(control2Texture);
		control2Width = 0.22163120567375886524822695035461F*width;
		control2Height = (float)control2Width/control1TentionIndex;
		control2X = 0.469F*backgroundSprite.getWidth() - 0.21568627450980392156862745098039F*control2Width;
		control2Y = 0.665F*backgroundSprite.getHeight() - 0.218F*control2Height;
		control2Sprite.setBounds(control2X, control2Y, control2Width, control2Height);
	}	

	private void resourcesCheck(){
		if(InfoAndStats.money>InfoAndStats.moneyFull) InfoAndStats.money = InfoAndStats.moneyFull;
		if(InfoAndStats.fuel>InfoAndStats.fuelFull) InfoAndStats.fuel = InfoAndStats.fuelFull;
		if(InfoAndStats.metal>InfoAndStats.metalFull) InfoAndStats.metal = InfoAndStats.metalFull;
	}
	
	private void touchUpdate(){
		/**Обработка нажатия aka прокрутки*/
		if(prevDragX != 0.0F && SFlightInputController.touchDragX != 0.0F)
			camera.position.x -= SFlightInputController.touchDragX - prevDragX;
		if(prevDragY != 0.0F && SFlightInputController.touchDragY != 0.0F)
			camera.position.y += SFlightInputController.touchDragY - prevDragY;
		prevDragX = SFlightInputController.touchDragX;
		prevDragY = SFlightInputController.touchDragY;
		/**Обработка нажатия aka прокрутки*/
			
		/**Границы прокрутки*/
		//Выравниваем по левой грани*/
		if(camera.position.x < backgroundSprite.getX() + width/2)
			camera.position.set(new Vector3(backgroundSprite.getX() + width/2, camera.position.y, 0));
		//Выравниваем по нижней грани*/
		if(camera.position.y < backgroundSprite.getY() + height/2)
			camera.position.set(new Vector3(camera.position.x, backgroundSprite.getY() + height/2, 0));
		//Выравниваем по правой грани*/
		if(camera.position.x > (backgroundSprite.getX() + backgroundSprite.getWidth()) - width/2)
			camera.position.set(new Vector3((backgroundSprite.getX() + backgroundSprite.getWidth()) - width/2, camera.position.y, 0));
		//Выравниваем по верхней грани*/
		if(camera.position.y > (backgroundSprite.getY() + backgroundSprite.getHeight()) - height/2)
			camera.position.set(new Vector3(camera.position.x, (backgroundSprite.getY() + backgroundSprite.getHeight()) - height/2, 0));
		/**Границы прокрутки*/
	}
	private void drawClouds(){
		cloud1Sprite.draw(batch);
		cloud2Sprite.draw(batch);
		cloud3Sprite.draw(batch);
		cloud4Sprite.draw(batch);
	}
	private void drawButtons(){
		if(controller.isOnGameStatic(backButtonX, backButtonY, backButtonWidth, backButtonHeight)){
			backButtonActiveSprite.draw(batch);
		}else{
			backButtonInactiveSprite.draw(batch);
		}
	}
	private void drawBuildings(){
		//Ангар
		if(controller.isOnGame(angar1Sprite.getX(), angar1Sprite.getY(), angar1Width, angar1Height)){
			angar2Sprite.draw(batch);
		}else{
			angar1Sprite.draw(batch);
		}
		//Аналитический центр
		if(controller.isOnGame(analytic1Sprite.getX(), analytic1Sprite.getY(), analytic1Width, analytic1Height)){
			analytic2Sprite.draw(batch);
		}else{
			analytic1Sprite.draw(batch);
		}
		//Диспетчерская вышка
		if(controller.isOnGame(control1Sprite.getX(), control1Sprite.getY(), control1Width, control1Height)){
			control2Sprite.draw(batch);
		}else{
			control1Sprite.draw(batch);
		}
		//Научный центр
		if(controller.isOnGame(scienceCentre1Sprite.getX(), scienceCentre1Sprite.getY(), scienceCentre1Width, scienceCentre1Height)){
			scienceCentre2Sprite.draw(batch);
		}else{
			scienceCentre1Sprite.draw(batch);
		}
	}
	private void drawMoney(){
		for(float i=0.25F;i<3.25F;i+=1.0F){
			line.setX(moneyX + (i*5.0F)*moneyWidth);
			line.draw(batch);
		}
		moneySprite.draw(batch);
		fuelSprite.draw(batch);
		metalSprite.draw(batch);
		cosmocoinLine.draw(batch);
		fuelLine.draw(batch);
		metalLine.draw(batch);
		text.draw(batch, ":    " + Long.toString((int)(InfoAndStats.money)) + "/" + Long.toString((int)(InfoAndStats.moneyFull)), moneySprite.getX() + 1.05F*moneySprite.getWidth(), moneySprite.getY() + 0.825F*moneySprite.getHeight() - text.getCapHeight()/1.4F);
		text.draw(batch, ":    " + Long.toString((int)(InfoAndStats.fuel)) + "/" + Long.toString((int)(InfoAndStats.fuelFull)), fuelSprite.getX() + 1.05F*fuelSprite.getWidth(), fuelSprite.getY() + 0.825F*moneySprite.getHeight() - text.getCapHeight()/1.4F);
		text.draw(batch, ":    " + Long.toString((int)(InfoAndStats.metal)) + "/" + Long.toString((int)(InfoAndStats.metalFull)), metalSprite.getX() + 1.05F*metalSprite.getWidth(), metalSprite.getY() + 0.825F*moneySprite.getHeight() - text.getCapHeight()/1.4F);
	}
	
	private void buttonListener(){
		//Слушатель нажатия на кнопку "Main Menu"*/
		if(controller.isClickedGame(backButtonX, backButtonY, backButtonWidth, backButtonHeight) || isTransGame){
			isTransGame = true;
			if(alp>1.0F){
				this.dispose();
				game.setScreen(new MainMenu(game));
				alp = 1.0F;
			}else{
				blackAlpha.setAlpha(alp);
				alp+=0.05F;
			}
		}
		//Слушатель нажатия на ангар*/
		if(controller.isClickedGame(angar1Sprite.getX(), angar1Sprite.getY(), angar1Width, angar1Height)){
				game.setScreen(new AngarScreen(game));
				this.dispose();
		}
		//Слушатель нажатия на аналитический центр*/
		if(controller.isClickedGame(analytic1Sprite.getX(), analytic1Sprite.getY(), analytic1Width, analytic1Height)){
			game.setScreen(new AnalyticCentreScreen(game));
			this.dispose();
		}
		//Слушатель нажатия на диспетчерскую вышку*/
		if(controller.isClickedGame(control1Sprite.getX(), control1Sprite.getY(), control1Width, control1Height)){
			game.setScreen(new ControlCentreScreen(game));
			this.dispose();
		}
		//Слушатель нажатия на научный центр*/
		if(controller.isClickedGame(scienceCentre1Sprite.getX(), scienceCentre1Sprite.getY(), scienceCentre1Width, scienceCentre1Height)){
			game.setScreen(new ScienceCentreScreen(game));
			this.dispose();
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

	private void textureDispose(){
		backgroundTexture.dispose();
		angar1Texture.dispose();
		angar2Texture.dispose();
		analytic1Texture.dispose();
		analytic2Texture.dispose();
		control1Texture.dispose();
		control2Texture.dispose();
		scienceCentre1Texture.dispose();
		scienceCentre2Texture.dispose();
		cloud1Texture.dispose();
		cloud2Texture.dispose();
		backButtonInactive.dispose();
		backButtonActive.dispose();
	}
	
	@Override
	public void dispose() {
		text.dispose();
		launchSoundPath.stop();
		launchSoundPath.dispose();
		game.dispose();
		textureDispose();
		cloud1PrevX = cloud1Sprite.getX();
		cloud1PrevY = cloud1Sprite.getY();
		cloud2PrevX = cloud2Sprite.getX();
		cloud2PrevY = cloud2Sprite.getY();
		cloud3PrevX = cloud3Sprite.getX();
		cloud3PrevY = cloud3Sprite.getY();
		cloud4PrevX = cloud4Sprite.getX();
		cloud4PrevY = cloud4Sprite.getY();
	}

}