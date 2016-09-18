package ru.erked.sflight.game;

import java.util.Random;

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
import ru.erked.sflight.random.Order;

public class AnalyticCentreScreen implements Screen{

	final String FONT_CHARS_RU = "абвгдежзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
	private static final float width = Gdx.graphics.getWidth();
	private static final float height = Gdx.graphics.getHeight();
	private Random rand1;
	private Random rand2;
	private Random rand3;
	private Random rand4;
	
	private Game game;
	private SpriteBatch batch;
	private SFlightInputController controller;
	public static float prevDay = (-1.0F)*1.0F;
	
	//Фон
	private Texture backgroundTexture; //Текстура фона
	public static Sprite backgroundSprite; //Спрайт фона
	private float backgroundX;
	private float backgroundY;
	public static final float backgroundTentionIndex = (float)width/400.0F;
	
	//Копка "Back"
	private Sprite backButtonInactiveSprite;
	private Sprite backButtonActiveSprite;
	private float backButtonX;
	private float backButtonY;
	private float backButtonWidth;
	private float backButtonHeight;
	public static float backButtonTentionIndex; //Соотношение сторон кнопки
	
	//Доска статистики
	private Texture scoreboardInactive;
	private Texture scoreboardActive;
	private Sprite scoreboardInactiveSprite;
	private Sprite scoreboardActiveSprite;
	private float scoreboard1X;
	private float scoreboard1Y;
	private float scoreboard1Width;
	private float scoreboard1Height;
	private float scoreboard2X;
	private float scoreboard2Y;
	private float scoreboard2Width;
	private float scoreboard2Height;
	
//Финансист
	private Texture financierInactive;
	private Texture financierActive;
	private Sprite financierInactiveSprite;
	private Sprite financierActiveSprite;
	private float financier1X;
	private float financier1Y;
	private float financier1Width;
	private float financier1Height;
	private float financier2X;
	private float financier2Y;
	private float financier2Width;
	private float financier2Height;
//Окно финансов
	private Sprite financeWindowSprite;
	private float financeWindowX;
	private float financeWindowY;
	private float financeWindowWidth;
	private float financeWindowHeight;
	private static boolean isFinanceWindowDraw;
//Выход из окна финансов
	private Sprite backFinanceWindowSpriteInactive;
	private Sprite backFinanceWindowSpriteActive;
	private float backFinanceWindow1X;
	private float backFinanceWindow1Y;
	private float backFinanceWindow1Width;
	private float backFinanceWindow1Height;
	private float backFinanceWindow2X;
	private float backFinanceWindow2Y;
	private float backFinanceWindow2Width;
	private float backFinanceWindow2Height;
	
	//Заказы
	public static Order order1;
	public static Order order2;
	public static Order order3;
	public static Order order4;
	private Texture orderInactive;
	private Texture orderActive;
	private Sprite orderInactiveSprite;
	private Sprite orderActiveSprite;
	private float order1X;
	private float order1Y;
	private float order1Width;
	private float order1Height;
	private float order2X;
	private float order2Y;
	private float order2Width;
	private float order2Height;
	//Окно заказов
	private Sprite orderWindowSprite;
	private float orderWindowX;
	private float orderWindowY;
	private float orderWindowWidth;
	private float orderWindowHeight;
	private static boolean isOrderWindowDraw;
	//Выход из окна заказов
	private Sprite backOrderWindowSpriteInactive;
	private Sprite backOrderWindowSpriteActive;
	private float backOrderWindow1X;
	private float backOrderWindow1Y;
	private float backOrderWindow1Width;
	private float backOrderWindow1Height;
	private float backOrderWindow2X;
	private float backOrderWindow2Y;
	private float backOrderWindow2Width;
	private float backOrderWindow2Height;
	//Компания 1
	private Sprite company1SpriteInactive;
	private Sprite company1SpriteActive;
	private float company1X;
	private float company1Y;
	private float company1Width;
	private float company1Height;
	private static boolean isCompany1Active;
	//Компания 2
	private Sprite company2SpriteInactive;
	private Sprite company2SpriteActive;
	private float company2X;
	private float company2Y;
	private float company2Width;
	private float company2Height;
	private static boolean isCompany2Active;
	//Компания 3
	private Sprite company3SpriteInactive;
	private Sprite company3SpriteActive;
	private float company3X;
	private float company3Y;
	private float company3Width;
	private float company3Height;
	private static boolean isCompany3Active;
	//Компания 4
	private Sprite company4SpriteInactive;
	private Sprite company4SpriteActive;
	private float company4X;
	private float company4Y;
	private float company4Width;
	private float company4Height;
	private static boolean isCompany4Active;
	//Заглушка
	private Sprite backZSprite;
	//Принятие заказа
	private Sprite acceptSpriteInactive;
	private Sprite acceptSpriteActive;
	private float accept1X;
	private float accept1Y;
	private float accept1Width;
	private float accept1Height;
	private float accept2X;
	private float accept2Y;
	private float accept2Width;
	private float accept2Height;
	//Отказ от заказа
	private Sprite declineSpriteInactive;
	private Sprite declineSpriteActive;
	private float decline1X;
	private float decline1Y;
	private float decline1Width;
	private float decline1Height;
	private float decline2X;
	private float decline2Y;
	private float decline2Width;
	private float decline2Height;
	
	//Текст
	private static BitmapFont text;
	
	public AnalyticCentreScreen(Game game){
		this.game = game;
	}
	
	@Override
	public void show() {

		rand1 = new Random();
		rand2 = new Random();
		rand3 = new Random();
		rand4 = new Random();

		MainMenu.music.play();
		
		batch = new SpriteBatch();
		controller = new SFlightInputController();
		if(prevDay != InfoAndStats.date){
			prevDay = InfoAndStats.date;
			if(!InfoAndStats.lngRussian){
				order1 = new Order(InfoAndStats.orderList1[rand1.nextInt(InfoAndStats.orderList1.length - 1)], rand1.nextFloat()*2.0F, rand1.nextInt(1), (int)(rand1.nextInt(1)*5 + rand1.nextInt(3)*10));
				order1.setOrderReward((int)((order1.getOrderLevel()+1)*10 + rand1.nextFloat()*10 + order1.getOrderMass()));
				order2 = new Order(InfoAndStats.orderList2[rand2.nextInt(InfoAndStats.orderList2.length - 1)], rand2.nextFloat()*4.0F, rand2.nextInt(2), (int)(rand2.nextInt(2)*5 + rand2.nextInt(3)*10));
				order2.setOrderReward((int)((order2.getOrderLevel()+1)*10 + rand2.nextFloat()*10 + order2.getOrderMass()));
				order3 = new Order(InfoAndStats.orderList3[rand3.nextInt(InfoAndStats.orderList3.length - 1)], rand3.nextFloat()*6.0F, rand3.nextInt(3), (int)(rand3.nextInt(3)*5 + rand3.nextInt(3)*10));
				order3.setOrderReward((int)((order3.getOrderLevel()+1)*10 + rand3.nextFloat()*10 + order3.getOrderMass()));
				order4 = new Order(InfoAndStats.orderList4[rand4.nextInt(InfoAndStats.orderList4.length - 1)], rand4.nextFloat()*8.0F, rand4.nextInt(4), (int)(rand4.nextInt(4)*5 + rand4.nextInt(3)*10));
				order4.setOrderReward((int)((order4.getOrderLevel()+1)*10 + rand4.nextFloat()*10 + order4.getOrderMass()));
			}else{
				order1 = new Order(InfoAndStats.orderList1RU[rand1.nextInt(InfoAndStats.orderList1RU.length - 1)], rand1.nextFloat()*2.0F, rand1.nextInt(1), (int)(rand1.nextInt(1)*5 + rand1.nextInt(3)*10));
				order1.setOrderReward((int)((order1.getOrderLevel()+1)*10 + rand1.nextFloat()*10 + order1.getOrderMass()));
				order2 = new Order(InfoAndStats.orderList2RU[rand2.nextInt(InfoAndStats.orderList2RU.length - 1)], rand2.nextFloat()*4.0F, rand2.nextInt(2), (int)(rand2.nextInt(2)*5 + rand2.nextInt(3)*10));
				order2.setOrderReward((int)((order2.getOrderLevel()+1)*10 + rand2.nextFloat()*10 + order2.getOrderMass()));
				order3 = new Order(InfoAndStats.orderList3RU[rand3.nextInt(InfoAndStats.orderList3RU.length - 1)], rand3.nextFloat()*6.0F, rand3.nextInt(3), (int)(rand3.nextInt(3)*5 + rand3.nextInt(3)*10));
				order3.setOrderReward((int)((order3.getOrderLevel()+1)*10 + rand3.nextFloat()*10 + order3.getOrderMass()));
				order4 = new Order(InfoAndStats.orderList4RU[rand4.nextInt(InfoAndStats.orderList4RU.length - 1)], rand4.nextFloat()*8.0F, rand4.nextInt(4), (int)(rand4.nextInt(4)*5 + rand4.nextInt(3)*10));
				order4.setOrderReward((int)((order4.getOrderLevel()+1)*10 + rand4.nextFloat()*10 + order4.getOrderMass()));
			}
		}
		
		//Фон\\
		backgroundTexture = new Texture("bckgrnd/analytic_inside.png");
		backgroundSprite = new Sprite(backgroundTexture);
		backgroundX = 0.0F;
		backgroundY = (-1)*(230*backgroundTentionIndex)/2 + height/2;
		backgroundSprite.setBounds(backgroundX, backgroundY, width, backgroundTentionIndex*230.0F);
		
		backButtonInit();
		scoreboardInit();
		financierInit();
		financeWindowInit();
		orderInit();
		orderWindowInit();
		companiesInit();
		acceptDeclineInit();
		
		//Текст\\
		FreeTypeFontGenerator genUS = new FreeTypeFontGenerator(Gdx.files.internal("fonts/prototype.ttf"));
		FreeTypeFontGenerator genRU = new FreeTypeFontGenerator(Gdx.files.internal("fonts/9840.otf"));
		FreeTypeFontParameter param = new FreeTypeFontParameter();
		param.color = Color.WHITE;
		param.size = 40;
		if(!InfoAndStats.lngRussian){
			text = genUS.generateFont(param);
			text.getData().setScale((float)(0.0007F*width));
		}else{
			param.characters = FONT_CHARS_RU;
			text = genRU.generateFont(param);
			text.getData().setScale((float)(0.0006F*width));
		}
	}

	private void backButtonInit(){
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
	}
	private void scoreboardInit(){
	//Доска статистики\\
		scoreboardInactive = new Texture("objects/graph_board_inactive.png");
		scoreboardActive = new Texture("objects/graph_board_active.png");
		scoreboardInactiveSprite = new Sprite(scoreboardInactive);
		scoreboardActiveSprite = new Sprite(scoreboardActive);
		scoreboard1Width = 0.2F*width;
		scoreboard1Height = scoreboard1Width;
		scoreboard1X = 0.605F*backgroundSprite.getWidth();
		scoreboard1Y = 0.55F*backgroundSprite.getHeight();
		scoreboard2Width = 0.34594594594594594594594594594595F*width;
		scoreboard2Height = scoreboard2Width;
		scoreboard2X = 0.605F*backgroundSprite.getWidth() - 0.2109375F*scoreboard2Width;
		scoreboard2Y = 0.55F*backgroundSprite.getHeight() - 0.2109375F*scoreboard2Height;
		scoreboardInactiveSprite.setBounds(scoreboard1X, scoreboard1Y, scoreboard1Width, scoreboard1Height);
		scoreboardActiveSprite.setBounds(scoreboard2X, scoreboard2Y, scoreboard2Width, scoreboard2Height);
	}
	private void financierInit(){
		//Финансист\\
		financierInactive = new Texture("objects/financierInactive.png");
		financierActive = new Texture("objects/financierActive.png");
		financierInactiveSprite = new Sprite(financierInactive);
		financierActiveSprite = new Sprite(financierActive);
		financier1Width = 0.1F*width;
		financier1Height = financier1Width;
		financier1X = 0.25F*backgroundSprite.getWidth();
		financier1Y = 0.215F*backgroundSprite.getHeight();
		financier2Width = 0.1488372093023255813953488372093F*width;
		financier2Height = financier2Width;
		financier2X = 0.25F*backgroundSprite.getWidth() - 0.1640625F*financier2Width;
		financier2Y = 0.215F*backgroundSprite.getHeight() - 0.1640625F*financier2Height;
		financierInactiveSprite.setBounds(financier1X, financier1Y, financier1Width, financier1Height);
		financierActiveSprite.setBounds(financier2X, financier2Y, financier2Width, financier2Height);
	}
	private void financeWindowInit(){
		//Окно финансов\\
		financeWindowSprite = new Sprite(ImgResDraw.dialogWindow);
		financeWindowWidth = 0.55F*width;
		financeWindowHeight = financeWindowWidth/1.45F;
		financeWindowX = 0.5F*width - 0.5F*financeWindowWidth;
		financeWindowY = 0.5F*height - 0.5F*financeWindowHeight;
		financeWindowSprite.setBounds(financeWindowX, financeWindowY, financeWindowWidth, financeWindowHeight);
		isFinanceWindowDraw = false;
		backFinanceWindowSpriteInactive = new Sprite(ImgResDraw.backIconInactive);
		backFinanceWindowSpriteActive = new Sprite(ImgResDraw.backIconActive);
		backFinanceWindow1Width = 0.065F*width;
		backFinanceWindow1Height = backFinanceWindow1Width;
		backFinanceWindow1X = financeWindowX + 0.95F*financeWindowWidth - backFinanceWindow1Width;
		backFinanceWindow1Y = financeWindowY + 0.05F*financeWindowHeight;
		backFinanceWindowSpriteInactive.setBounds(backFinanceWindow1X, backFinanceWindow1Y, backFinanceWindow1Width, backFinanceWindow1Height);
		backFinanceWindow2Width = 0.0975F*width;
		backFinanceWindow2Height = backFinanceWindow2Width;
		backFinanceWindow2X = financeWindowX + 0.95F*financeWindowWidth - backFinanceWindow1Width - 0.16666666666666666666666666666667F*backFinanceWindow2Width;
		backFinanceWindow2Y = financeWindowY + 0.05F*financeWindowHeight - 0.16666666666666666666666666666667F*backFinanceWindow2Height;
		backFinanceWindowSpriteActive.setBounds(backFinanceWindow2X, backFinanceWindow2Y, backFinanceWindow2Width, backFinanceWindow2Height);
		backZSprite = new Sprite(new Texture("bckgrnd/backgroundGray.png"));
		backZSprite.setBounds(0.0F, 0.0F, width, height);
	}
	private void orderInit(){
		//Заказы\\
		orderInactive = new Texture("objects/orderInactive.png");
		orderActive = new Texture("objects/orderActive.png");
		orderInactiveSprite = new Sprite(orderInactive);
		orderActiveSprite = new Sprite(orderActive);
		order1Width = 0.11F*width;
		order1Height = order1Width;
		order1X = 0.5F*backgroundSprite.getWidth();
		order1Y = 0.475F*backgroundSprite.getHeight();
		order2Width = 0.17368421052631578947368421052632F*width;
		order2Height = order2Width;
		order2X = 0.5F*backgroundSprite.getWidth() - 0.18333333333333333333333333333333F*order2Width;
		order2Y = 0.475F*backgroundSprite.getHeight() - 0.18333333333333333333333333333333F*order2Height;
		orderInactiveSprite.setBounds(order1X, order1Y, order1Width, order1Height);
		orderActiveSprite.setBounds(order2X, order2Y, order2Width, order2Height);
	}
	private void orderWindowInit(){
		//Окно заказов\\
		orderWindowSprite = new Sprite(ImgResDraw.dialogWindow);
		orderWindowWidth = 0.75F*width;
		orderWindowHeight = orderWindowWidth/1.45F;
		orderWindowX = 0.5F*width - 0.5F*orderWindowWidth;
		orderWindowY = 0.5F*height - 0.5F*orderWindowHeight;
		orderWindowSprite.setBounds(orderWindowX, orderWindowY, orderWindowWidth, orderWindowHeight);
		isOrderWindowDraw = false;
		backOrderWindowSpriteInactive = new Sprite(ImgResDraw.backIconInactive);
		backOrderWindowSpriteActive = new Sprite(ImgResDraw.backIconActive);
		backOrderWindow1Width = 0.065F*width;
		backOrderWindow1Height = backOrderWindow1Width;
		backOrderWindow1X = orderWindowX + 0.95F*orderWindowWidth - backOrderWindow1Width;
		backOrderWindow1Y = orderWindowY + 0.05F*orderWindowHeight;
		backOrderWindowSpriteInactive.setBounds(backOrderWindow1X, backOrderWindow1Y, backOrderWindow1Width, backOrderWindow1Height);
		backOrderWindow2Width = 0.0975F*width;
		backOrderWindow2Height = backOrderWindow2Width;
		backOrderWindow2X = orderWindowX + 0.95F*orderWindowWidth - backOrderWindow1Width - 0.16666666666666666666666666666667F*backOrderWindow2Width;
		backOrderWindow2Y = orderWindowY + 0.05F*orderWindowHeight - 0.16666666666666666666666666666667F*backOrderWindow2Height;
		backOrderWindowSpriteActive.setBounds(backOrderWindow2X, backOrderWindow2Y, backOrderWindow2Width, backOrderWindow2Height);
		backZSprite = new Sprite(new Texture("bckgrnd/backgroundGray.png"));
		backZSprite.setBounds(0.0F, 0.0F, width, height);
	}
	private void companiesInit(){
		//Компании\\
		company1SpriteInactive = new Sprite(new Texture("objects/company_1_inactive.png"));
		company1SpriteActive = new Sprite(new Texture("objects/company_1_active.png"));
		company1X = orderWindowX + 0.05F*orderWindowWidth;
		company1Y = orderWindowY + 0.05F*orderWindowHeight;
		company1Width = 0.4F*orderWindowHeight;
		company1Height = 0.2F*orderWindowHeight;
		isCompany1Active = false;
		company1SpriteInactive.setBounds(company1X, company1Y, company1Width, company1Height);
		company1SpriteActive.setBounds(company1X, company1Y, company1Width, company1Height);
		
		company2SpriteInactive = new Sprite(new Texture("objects/company_2_inactive.png"));
		company2SpriteActive = new Sprite(new Texture("objects/company_2_active.png"));
		company2X = orderWindowX + 0.05F*orderWindowWidth;
		company2Y = orderWindowY + 0.25F*orderWindowHeight;
		company2Width = 0.4F*orderWindowHeight;
		company2Height = 0.2F*orderWindowHeight;
		isCompany2Active = false;
		company2SpriteInactive.setBounds(company2X, company2Y, company2Width, company2Height);
		company2SpriteActive.setBounds(company2X, company2Y, company2Width, company2Height);
		
		company3SpriteInactive = new Sprite(new Texture("objects/company_3_inactive.png"));
		company3SpriteActive = new Sprite(new Texture("objects/company_3_active.png"));
		company3X = orderWindowX + 0.05F*orderWindowWidth;
		company3Y = orderWindowY + 0.45F*orderWindowHeight;
		company3Width = 0.4F*orderWindowHeight;
		company3Height = 0.2F*orderWindowHeight;
		isCompany3Active = false;
		company3SpriteInactive.setBounds(company3X, company3Y, company3Width, company3Height);
		company3SpriteActive.setBounds(company3X, company3Y, company3Width, company3Height);
		
		company4SpriteInactive = new Sprite(new Texture("objects/company_4_inactive.png"));
		company4SpriteActive = new Sprite(new Texture("objects/company_4_active.png"));
		company4X = orderWindowX + 0.05F*orderWindowWidth;
		company4Y = orderWindowY + 0.65F*orderWindowHeight;
		company4Width = 0.4F*orderWindowHeight;
		company4Height = 0.2F*orderWindowHeight;
		isCompany4Active = false;
		company4SpriteInactive.setBounds(company4X, company4Y, company4Width, company4Height);
		company4SpriteActive.setBounds(company4X, company4Y, company4Width, company4Height);
	}
	private void acceptDeclineInit(){
		acceptSpriteInactive = new Sprite(new Texture("btns/acceptInactive.png"));
		acceptSpriteActive = new Sprite(new Texture("btns/acceptActive.png"));
		accept1Width = 0.15F*width;
		accept1Height = accept1Width/2.0F;
		accept1X = 0.55F*orderWindowSprite.getWidth(); 
		accept1Y = orderWindowY + 0.1F*orderWindowHeight;
		acceptSpriteInactive.setBounds(accept1X, accept1Y, accept1Width, accept1Height);
		accept2Width = 0.234375F*width;
		accept2Height = accept2Width/2;
		accept2X = accept1X - 0.18F*accept2Width;
		accept2Y = accept1Y - 0.18F*accept2Height;
		acceptSpriteActive.setBounds(accept2X, accept2Y, accept2Width, accept2Height);
		//***\\
		declineSpriteInactive = new Sprite(new Texture("btns/declineInactive.png"));
		declineSpriteActive = new Sprite(new Texture("btns/declineActive.png"));
		decline1Width = 0.15F*width;
		decline1Height = decline1Width/2.0F;
		decline1X = accept1X + 0.025F*orderWindowSprite.getWidth() + decline1Width;
		decline1Y = orderWindowY + 0.1F*orderWindowHeight;
		declineSpriteInactive.setBounds(decline1X, decline1Y, decline1Width, decline1Height);
		decline2Width = 0.234375F*width;
		decline2Height = decline2Width/2;
		decline2X = decline1X - 0.18F*decline2Width;
		decline2Y = decline1Y - 0.18F*decline2Height;
		declineSpriteActive.setBounds(decline2X, decline2Y, decline2Width, decline2Height);
	}
	
	private void btnListener(){
		//Слушатель нажатия на кнопку "Back"//
		if(!isFinanceWindowDraw && !isOrderWindowDraw)
			if(controller.isClicked(backButtonX, backButtonY, backButtonWidth, backButtonHeight)){
				game.setScreen(new GameScreen(game));
				this.dispose();
			}
		//Слушатель нажатия на доску статистики//
		if(!isFinanceWindowDraw && !isOrderWindowDraw)
			if(controller.isClicked(scoreboard1X, scoreboard1Y, scoreboard1Width, scoreboard1Height)){
				game.setScreen(new StatisticScreen(game));
				this.dispose();
			}
		//Слушатель нажатия на финансиста//
		if(!isFinanceWindowDraw && !isOrderWindowDraw)
			if(controller.isClicked(financier1X, financier1Y, financier1Width, financier1Height))
				isFinanceWindowDraw = true;
		//Слушатель нажатия на кнопку закрытия окна финансов//
		if(isFinanceWindowDraw)
			if(controller.isClicked(backFinanceWindow1X, backFinanceWindow1Y, backFinanceWindow1Width, backFinanceWindow1Height))
				isFinanceWindowDraw = false;
		//Слушатель нажатия на заказы//
		if(!isOrderWindowDraw && !isFinanceWindowDraw)
			if(controller.isClicked(order1X, order1Y, order1Width, order1Height))
				isOrderWindowDraw = true;
		//Слушатель нажатия на кнопку закрытия окна заказов//
		if(isOrderWindowDraw){
			if(controller.isClicked(backOrderWindow1X, backOrderWindow1Y, backOrderWindow1Width, backOrderWindow1Height))
				isOrderWindowDraw = false;
			if(controller.isClicked(company1X, company1Y, company1Width, company1Height))
				if(!isCompany1Active){
					isCompany1Active = true;
					isCompany2Active = false;
					isCompany3Active = false;
					isCompany4Active = false;
				}else{
					isCompany1Active = false;
					isCompany2Active = false;
					isCompany3Active = false;
					isCompany4Active = false;
				}
			if(controller.isClicked(company2X, company2Y, company2Width, company2Height))
				if(!isCompany2Active){
					isCompany2Active = true;
					isCompany1Active = false;
					isCompany3Active = false;
					isCompany4Active = false;
				}else{
					isCompany1Active = false;
					isCompany2Active = false;
					isCompany3Active = false;
					isCompany4Active = false;
				}
			if(controller.isClicked(company3X, company3Y, company3Width, company3Height))
				if(!isCompany3Active){
					isCompany3Active = true;
					isCompany1Active = false;
					isCompany2Active = false;
					isCompany4Active = false;
				}else{
					isCompany1Active = false;
					isCompany2Active = false;
					isCompany3Active = false;
					isCompany4Active = false;
				}
			if(controller.isClicked(company4X, company4Y, company4Width, company4Height))
				if(!isCompany4Active){
					isCompany4Active = true;
					isCompany1Active = false;
					isCompany2Active = false;
					isCompany3Active = false;
				}else{
					isCompany1Active = false;
					isCompany2Active = false;
					isCompany3Active = false;
					isCompany4Active = false;
				}
			if(isCompany1Active){
				if(controller.isClicked(accept1X, accept1Y, accept1Width, accept1Height) && !InfoAndStats.hasOrder){
					InfoAndStats.currentOrder.setOrderName(order1.getOrderName());
					InfoAndStats.currentOrder.setOrderMass(order1.getOrderMass());
					InfoAndStats.currentOrder.setOrderLevel(order1.getOrderLevel());
					InfoAndStats.currentOrder.setOrderReward(order1.getOrderReward());
					InfoAndStats.hasOrder = true;
					isCompany1Active = false;
				}
				if(controller.isClicked(decline1X, decline1Y, decline1Width, decline1Height)){
					isCompany1Active = false;
				}
			}
			if(isCompany2Active){
				if(controller.isClicked(accept1X, accept1Y, accept1Width, accept1Height) && !InfoAndStats.hasOrder){
					InfoAndStats.currentOrder.setOrderName(order2.getOrderName());
					InfoAndStats.currentOrder.setOrderMass(order2.getOrderMass());
					InfoAndStats.currentOrder.setOrderLevel(order2.getOrderLevel());
					InfoAndStats.currentOrder.setOrderReward(order2.getOrderReward());
					InfoAndStats.hasOrder = true;
					isCompany2Active = false;
				}
				if(controller.isClicked(decline1X, decline1Y, decline1Width, decline1Height)){
					isCompany2Active = false;
				}
			}
			if(isCompany3Active){
				if(controller.isClicked(accept1X, accept1Y, accept1Width, accept1Height) && !InfoAndStats.hasOrder){
					InfoAndStats.currentOrder.setOrderName(order3.getOrderName());
					InfoAndStats.currentOrder.setOrderMass(order3.getOrderMass());
					InfoAndStats.currentOrder.setOrderLevel(order3.getOrderLevel());
					InfoAndStats.currentOrder.setOrderReward(order3.getOrderReward());
					InfoAndStats.hasOrder = true;
					isCompany3Active = false;
				}
				if(controller.isClicked(decline1X, decline1Y, decline1Width, decline1Height)){
					isCompany3Active = false;
				}
			}
			if(isCompany4Active){
				if(controller.isClicked(accept1X, accept1Y, accept1Width, accept1Height) && !InfoAndStats.hasOrder){
					InfoAndStats.currentOrder.setOrderName(order4.getOrderName());
					InfoAndStats.currentOrder.setOrderMass(order4.getOrderMass());
					InfoAndStats.currentOrder.setOrderLevel(order4.getOrderLevel());
					InfoAndStats.currentOrder.setOrderReward(order4.getOrderReward());
					InfoAndStats.hasOrder = true;
					isCompany4Active = false;
				}
				if(controller.isClicked(decline1X, decline1Y, decline1Width, decline1Height)){
					isCompany4Active = false;
				}
			}
			
		}
	}
	
	private void drawBackButton(){
		//Отрисовка кнопки "Back"//
		if(controller.isOn(backButtonX, backButtonY, backButtonWidth, backButtonHeight)){
			if(!isFinanceWindowDraw && !isOrderWindowDraw){
				backButtonActiveSprite.draw(batch);
			}else{
				backButtonInactiveSprite.draw(batch);
			}
		}else{
			backButtonInactiveSprite.draw(batch);
		}
	}
	private void drawScoreboard(){
		//Отрисовка доски статистики//
		if(controller.isOn(scoreboard1X, scoreboard1Y, scoreboard1Width, scoreboard1Height)){
			if(!isFinanceWindowDraw && !isOrderWindowDraw){
				scoreboardActiveSprite.draw(batch);
			}else{
				scoreboardInactiveSprite.draw(batch);
			}
		}else{
			scoreboardInactiveSprite.draw(batch);
		}
	}
	private void drawFinancier(){
		//Отрисовка финансиста//
		if(controller.isOn(financier1X, financier1Y, financier1Width, financier1Height)){
			if(!isFinanceWindowDraw && !isOrderWindowDraw){
				financierActiveSprite.draw(batch);
			}else{
				financierInactiveSprite.draw(batch);
			}
		}else{
			financierInactiveSprite.draw(batch);
		}
	}
	private void drawFinance(){
		//Отрисовка окна финансов//
		if(isFinanceWindowDraw){
			backZSprite.draw(batch);
			financeWindowSprite.draw(batch);
			if(controller.isOn(backFinanceWindow1X, backFinanceWindow1Y, backFinanceWindow1Width, backFinanceWindow1Height))
				backFinanceWindowSpriteActive.draw(batch);
			else
				backFinanceWindowSpriteInactive.draw(batch);
			if(!InfoAndStats.lngRussian)
				text.draw(batch, "Financier", 0.8F*financeWindowSprite.getWidth(), 1.275F*financeWindowSprite.getHeight());
			else
				text.draw(batch, "Финансист", 0.765F*financeWindowSprite.getWidth(), 1.275F*financeWindowSprite.getHeight());
		}
	}
	private void drawOrder(){
		//Отрисовка финансиста//
		if(controller.isOn(order1X, order1Y, order1Width, order1Height)){
			if(!isOrderWindowDraw && !isFinanceWindowDraw){
				orderActiveSprite.draw(batch);
			}else{
				orderInactiveSprite.draw(batch);
			}
		}else{
			orderInactiveSprite.draw(batch);
		}
	}
	private void drawOrderWindow(){
		//Отрисовка окна финансов//
		if(isOrderWindowDraw){
			backZSprite.draw(batch);
			orderWindowSprite.draw(batch);
			
			if(controller.isOn(backOrderWindow1X, backOrderWindow1Y, backOrderWindow1Width, backOrderWindow1Height))
				backOrderWindowSpriteActive.draw(batch);
			else
				backOrderWindowSpriteInactive.draw(batch);
			
			if(!InfoAndStats.lngRussian)
				text.draw(batch, "Orders", 0.6F*orderWindowSprite.getWidth(), 1.05F*orderWindowSprite.getHeight());
			else
				text.draw(batch, "Заказы", 0.59F*orderWindowSprite.getWidth(), 1.05F*orderWindowSprite.getHeight());
			
			if(isCompany1Active){
				company1SpriteActive.draw(batch);
				if(!InfoAndStats.lngRussian){
					text.draw(batch, "Name: Vote Trouble", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight());
					text.draw(batch, "Order's name:", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 1.5F*text.getCapHeight());
					text.draw(batch, order1.getOrderName(), 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 3.0F*text.getCapHeight());
					text.draw(batch, "Load's mass: " + order1.getOrderMass(), 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 4.5F*text.getCapHeight());
					text.draw(batch, "Destination:", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 6.0F*text.getCapHeight());
					switch(order1.getOrderLevel()){
						case 0:{
							text.draw(batch, "Lower layers of the atmosphere", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 7.5F*text.getCapHeight());
							break;
						}
						case 1:{
							text.draw(batch, "Upper layers of the atmosphere", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 7.5F*text.getCapHeight());
							break;
						}
						case 2:{
							text.draw(batch, "Low earth orbit", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 7.5F*text.getCapHeight());
							break;
						}
						case 3:{
							text.draw(batch, "High earth orbit", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 7.5F*text.getCapHeight());
							break;
						}
					}
					text.draw(batch, "Reward: " + order1.getOrderReward() + " cosmocoins", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 9.0F*text.getCapHeight());
				}else{
					text.draw(batch, "Название: Vote Trouble", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight());
					text.draw(batch, "Заказ:", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 1.5F*text.getCapHeight());
					text.draw(batch, order1.getOrderName(), 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 3.0F*text.getCapHeight());
					text.draw(batch, "Масса груза: " + order1.getOrderMass(), 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 4.5F*text.getCapHeight());
					text.draw(batch, "Пункт назначения:", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 6.0F*text.getCapHeight());
					switch(order1.getOrderLevel()){
						case 0:{
							text.draw(batch, "Нижние слои атмосферы", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 7.5F*text.getCapHeight());
							break;
						}
						case 1:{
							text.draw(batch, "Верхние слои атмосферы", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 7.5F*text.getCapHeight());
							break;
						}
						case 2:{
							text.draw(batch, "Низкая околоземная орбита", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 7.5F*text.getCapHeight());
							break;
						}
						case 3:{
							text.draw(batch, "Высокая околоземная орбита", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 7.5F*text.getCapHeight());
							break;
						}
					}
					text.draw(batch, "Награда: " + order1.getOrderReward() + " космокоинов", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 9.0F*text.getCapHeight());
				}
				/***/
				if(!InfoAndStats.hasOrder){
					if(controller.isOn(accept1X, accept1Y, accept1Width, accept1Height)){
						acceptSpriteActive.draw(batch);
					}else{
						acceptSpriteInactive.draw(batch);
					}
				}
				if(controller.isOn(decline1X, decline1Y, decline1Width, decline1Height))
					declineSpriteActive.draw(batch);
				else
					declineSpriteInactive.draw(batch);
				/***/
			}
			else company1SpriteInactive.draw(batch);
			
			if(isCompany2Active){
				company2SpriteActive.draw(batch);
				if(!InfoAndStats.lngRussian){
					text.draw(batch, "Name: Reazon of life", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight());
					text.draw(batch, "Order's name:", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 1.5F*text.getCapHeight());
					text.draw(batch, order2.getOrderName(), 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 3.0F*text.getCapHeight());
					text.draw(batch, "Load's mass: " + order2.getOrderMass(), 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 4.5F*text.getCapHeight());
					text.draw(batch, "Destination:", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 6.0F*text.getCapHeight());
					switch(order2.getOrderLevel()){
						case 0:{
							text.draw(batch, "Lower layers of the atmosphere", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 7.5F*text.getCapHeight());
							break;
						}
						case 1:{
							text.draw(batch, "Upper layers of the atmosphere", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 7.5F*text.getCapHeight());
							break;
						}
						case 2:{
							text.draw(batch, "Low earth orbit", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 7.5F*text.getCapHeight());
							break;
						}
						case 3:{
							text.draw(batch, "High earth orbit", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 7.5F*text.getCapHeight());
							break;
						}
					}
					text.draw(batch, "Reward: " + order2.getOrderReward() + " cosmocoins", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 9.0F*text.getCapHeight());
				}else{
					text.draw(batch, "Название: Reazon of life", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight());
					text.draw(batch, "Заказ:", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 1.5F*text.getCapHeight());
					text.draw(batch, order2.getOrderName(), 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 3.0F*text.getCapHeight());
					text.draw(batch, "Масса груза: " + order2.getOrderMass(), 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 4.5F*text.getCapHeight());
					text.draw(batch, "Пункт назначения:", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 6.0F*text.getCapHeight());
					switch(order2.getOrderLevel()){
						case 0:{
							text.draw(batch, "Нижние слои атмосферы", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 7.5F*text.getCapHeight());
							break;
						}
						case 1:{
							text.draw(batch, "Верхние слои атмосферы", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 7.5F*text.getCapHeight());
							break;
						}
						case 2:{
							text.draw(batch, "Низкая околоземная орбита", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 7.5F*text.getCapHeight());
							break;
						}
						case 3:{
							text.draw(batch, "Высокая околоземная орбита", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 7.5F*text.getCapHeight());
							break;
						}
					}
					text.draw(batch, "Награда: " + order2.getOrderReward() + " космокоинов", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 9.0F*text.getCapHeight());
				}
				/***/
				if(!InfoAndStats.hasOrder){
					if(controller.isOn(accept1X, accept1Y, accept1Width, accept1Height)){
						acceptSpriteActive.draw(batch);
					}else{
						acceptSpriteInactive.draw(batch);
					}
				}
				if(controller.isOn(decline1X, decline1Y, decline1Width, decline1Height))
					declineSpriteActive.draw(batch);
				else
					declineSpriteInactive.draw(batch);
				/***/
			}
			else company2SpriteInactive.draw(batch);
			
			if(isCompany3Active){
				company3SpriteActive.draw(batch);
				if(!InfoAndStats.lngRussian){
					text.draw(batch, "Name: Media may satisfy", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight());
					text.draw(batch, "Order's name:", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 1.5F*text.getCapHeight());
					text.draw(batch, order3.getOrderName(), 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 3.0F*text.getCapHeight());
					text.draw(batch, "Load's mass: " + order3.getOrderMass(), 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 4.5F*text.getCapHeight());
					text.draw(batch, "Destination:", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 6.0F*text.getCapHeight());
					switch(order3.getOrderLevel()){
						case 0:{
							text.draw(batch, "Lower layers of the atmosphere", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 7.5F*text.getCapHeight());
							break;
						}
						case 1:{
							text.draw(batch, "Upper layers of the atmosphere", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 7.5F*text.getCapHeight());
							break;
						}
						case 2:{
							text.draw(batch, "Low earth orbit", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 7.5F*text.getCapHeight());
							break;
						}
						case 3:{
							text.draw(batch, "High earth orbit", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 7.5F*text.getCapHeight());
							break;
						}
					}
					text.draw(batch, "Reward: " + order3.getOrderReward() + " cosmocoins", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 9.0F*text.getCapHeight());
				}else{
					text.draw(batch, "Название: Media may satisfy", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight());
					text.draw(batch, "Заказ:", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 1.5F*text.getCapHeight());
					text.draw(batch, order3.getOrderName(), 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 3.0F*text.getCapHeight());
					text.draw(batch, "Масса груза: " + order3.getOrderMass(), 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 4.5F*text.getCapHeight());
					text.draw(batch, "Пункт назначения:", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 6.0F*text.getCapHeight());
					switch(order3.getOrderLevel()){
						case 0:{
							text.draw(batch, "Нижние слои атмосферы", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 7.5F*text.getCapHeight());
							break;
						}
						case 1:{
							text.draw(batch, "Верхние слои атмосферы", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 7.5F*text.getCapHeight());
							break;
						}
						case 2:{
							text.draw(batch, "Низкая околоземная орбита", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 7.5F*text.getCapHeight());
							break;
						}
						case 3:{
							text.draw(batch, "Высокая околоземная орбита", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 7.5F*text.getCapHeight());
							break;
						}
					}
					text.draw(batch, "Награда: " + order3.getOrderReward() + " космокоинов", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 9.0F*text.getCapHeight());
				}
				/***/
				if(!InfoAndStats.hasOrder){
					if(controller.isOn(accept1X, accept1Y, accept1Width, accept1Height)){
						acceptSpriteActive.draw(batch);
					}else{
						acceptSpriteInactive.draw(batch);
					}
				}
				if(controller.isOn(decline1X, decline1Y, decline1Width, decline1Height))
					declineSpriteActive.draw(batch);
				else
					declineSpriteInactive.draw(batch);
				/***/
			}
			else company3SpriteInactive.draw(batch);
			
			if(isCompany4Active){
				company4SpriteActive.draw(batch);
				if(!InfoAndStats.lngRussian){
					text.draw(batch, "Name: D.N.A.", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight());
					text.draw(batch, "Order's name:", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 1.5F*text.getCapHeight());
					text.draw(batch, order4.getOrderName(), 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 3.0F*text.getCapHeight());
					text.draw(batch, "Load's mass: " + order4.getOrderMass(), 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 4.5F*text.getCapHeight());
					text.draw(batch, "Destination:", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 6.0F*text.getCapHeight());
					switch(order4.getOrderLevel()){
						case 0:{
							text.draw(batch, "Lower layers of the atmosphere", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 7.5F*text.getCapHeight());
							break;
						}
						case 1:{
							text.draw(batch, "Upper layers of the atmosphere", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 7.5F*text.getCapHeight());
							break;
						}
						case 2:{
							text.draw(batch, "Low earth orbit", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 7.5F*text.getCapHeight());
							break;
						}
						case 3:{
							text.draw(batch, "High earth orbit", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 7.5F*text.getCapHeight());
							break;
						}
					}
					text.draw(batch, "Reward: " + order4.getOrderReward() + " cosmocoins", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 9.0F*text.getCapHeight());
				}else{
					text.draw(batch, "Название: D.N.A.", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight());
					text.draw(batch, "Заказ:", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 1.5F*text.getCapHeight());
					text.draw(batch, order4.getOrderName(), 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 3.0F*text.getCapHeight());
					text.draw(batch, "Масса груза: " + order4.getOrderMass(), 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 4.5F*text.getCapHeight());
					text.draw(batch, "Пункт назначения:", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 6.0F*text.getCapHeight());
					switch(order4.getOrderLevel()){
						case 0:{
							text.draw(batch, "Нижние слои атмосферы", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 7.5F*text.getCapHeight());
							break;
						}
						case 1:{
							text.draw(batch, "Верхние слои атмосферы", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 7.5F*text.getCapHeight());
							break;
						}
						case 2:{
							text.draw(batch, "Низкая околоземная орбита", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 7.5F*text.getCapHeight());
							break;
						}
						case 3:{
							text.draw(batch, "Высокая околоземная орбита", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 7.5F*text.getCapHeight());
							break;
						}
					}
					text.draw(batch, "Награда: " + order4.getOrderReward() + " космокоинов", 0.55F*orderWindowSprite.getWidth(), 0.925F*orderWindowSprite.getHeight() - 9.0F*text.getCapHeight());
				}
				/***/
				if(!InfoAndStats.hasOrder){
					if(controller.isOn(accept1X, accept1Y, accept1Width, accept1Height)){
						acceptSpriteActive.draw(batch);
					}else{
						acceptSpriteInactive.draw(batch);
					}
				}
				if(controller.isOn(decline1X, decline1Y, decline1Width, decline1Height))
					declineSpriteActive.draw(batch);
				else
					declineSpriteInactive.draw(batch);
				/***/
			}
			else company4SpriteInactive.draw(batch);
			
		}
	}
	
	@Override
	public void render(float delta) {
		InfoAndStats.elapsedTime++;
		
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		
		backgroundSprite.draw(batch);

		if(!InfoAndStats.lngRussian){
			text.draw(batch, "Analytic centre", 0.01F*width, 0.99F*height);
		}else{
			text.draw(batch, "Аналитический центр", 0.01F*width, 0.99F*height);
		}
		
		drawBackButton();
		drawScoreboard();
		drawFinancier();
		drawOrder();
		drawFinance();
		drawOrderWindow();
		
		batch.end();
		
		btnListener();
		
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
		scoreboardInactive.dispose();
		scoreboardActive.dispose();
		financierInactive.dispose();
		financierActive.dispose();
		orderInactive.dispose();
		orderActive.dispose();
	}
	
	@Override
	public void dispose() {
		game.dispose();
		batch.dispose();
		text.dispose();
		textureDispose();
		GameScreen.isFromMenu = false;
	}

}
