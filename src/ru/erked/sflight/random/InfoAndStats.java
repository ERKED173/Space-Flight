package ru.erked.sflight.random;

import com.badlogic.gdx.graphics.g2d.Sprite;

import ru.erked.sflight.game.FlightScreen;
import ru.erked.sflight.game.GameScreen;
import ru.erked.sflight.game.RocketDetail;

public class InfoAndStats {
	
	public static long elapsedTime;
	public static long launch;
	public static long date;
	public static long money;
	
	public static String selectedFirst = "null";
	public static String selectedSecond = "null";
	public static String selectedThird = "null";
	
	public static Order currentOrder = new Order("null", 0.0F, 0, 0);
	public static boolean hasOrder = false;
	
	public static int days;
	public static float prevDay;
	public static boolean hasTask;
	public static boolean isFirstTutorial = true;
	
	public static RocketDetail firstStage = new RocketDetail("","",0.0F,0.0F,0.0F,0,0,0,false);
	public static RocketDetail secondStage = new RocketDetail("","",0.0F,0.0F,0.0F,0,0,0,false);
	public static RocketDetail thirdStage = new RocketDetail("","",0.0F,0.0F,0.0F,0,0,0,false);
	public static RocketDetail upgradeDetail = new RocketDetail("", "", 0.0F, 0.0F, 0.0F, 0, 0, 0, false);
	
	public static Sprite getThirdStage(){
		if(selectedThird.equals("LT_116")) return GameScreen.LT_116Sprite;
		else if(selectedThird.equals("LM_087")) return GameScreen.LM_087Sprite;
		else if(selectedThird.equals("LF_15")) return GameScreen.LF_15Sprite;
		return GameScreen.nullSprite;
	}
	public static Sprite getSecondStage(){
		if(selectedSecond.equals("MT_0112")) return GameScreen.MT_0112Sprite;
		else if(selectedSecond.equals("MM_4")) return GameScreen.MM_4Sprite;
		else if(selectedSecond.equals("MF_043")) return GameScreen.MF_043Sprite;
		return GameScreen.nullSprite;
	}
	public static Sprite getFirstStage(){
		if(selectedFirst.equals("UT_1202")) return GameScreen.UT_1202Sprite;
		else if(selectedFirst.equals("UM_1034")) return GameScreen.UM_1034Sprite;
		else if(selectedFirst.equals("UF_02")) return GameScreen.UF_02Sprite;
		return GameScreen.nullSprite;
	}

	public static Sprite getThirdStageFlight(){
		if(selectedThird.equals("LT_116")) return FlightScreen.LT_116Sprite;
		else if(selectedThird.equals("LM_087")) return FlightScreen.LM_087Sprite;
		else if(selectedThird.equals("LF_15")) return FlightScreen.LF_15Sprite;
		return FlightScreen.nullSprite;
	}
	public static Sprite getSecondStageFlight(){
		if(selectedSecond.equals("MT_0112")) return FlightScreen.MT_0112Sprite;
		else if(selectedSecond.equals("MM_4")) return FlightScreen.MM_4Sprite;
		else if(selectedSecond.equals("MF_043")) return FlightScreen.MF_043Sprite;
		return FlightScreen.nullSprite;
	}
	public static Sprite getFirstStageFlight(){
		if(selectedFirst.equals("UT_1202")) return FlightScreen.UT_1202Sprite;
		else if(selectedFirst.equals("UM_1034")) return FlightScreen.UM_1034Sprite;
		else if(selectedFirst.equals("UF_02")) return FlightScreen.UF_02Sprite;
		return FlightScreen.nullSprite;
	}
	
	public static RocketDetail getFirstDetail(){
		if(selectedFirst.equals("UT_1202")) return UT_1202;
		else if(selectedFirst.equals("UM_1034")) return UM_1034;
		else if(selectedFirst.equals("UF_02")) return UF_02;
		else return new RocketDetail("","",0.0F,0.0F,0.0F,0,0,0,false);
	}
	
	public static RocketDetail getSecondDetail(){
		if(selectedSecond.equals("MT_0112")) return MT_0112;
		else if(selectedSecond.equals("MM_4")) return MM_4;
		else if(selectedSecond.equals("MF_043")) return MF_043;
		else return new RocketDetail("","",0.0F,0.0F,0.0F,0,0,0,false);
	}
	
	public static RocketDetail getThirdDetail(){
		if(selectedThird.equals("LT_116")) return LT_116;
		else if(selectedThird.equals("LM_087")) return LM_087;
		else if(selectedThird.equals("LF_15")) return LF_15;
		else return new RocketDetail("","",0.0F,0.0F,0.0F,0,0,0,false);
	}
	
	public static boolean lngRussian = false;
	
	//Детальки
	public static RocketDetail UT_1202 = new RocketDetail("UT-1202","УТ-1202",1.0F,0.25F,5.0F,5,2,0,true);
	public static RocketDetail MT_0112 = new RocketDetail("MT-0112","МТ-0112",1.75F,0.45F,6.75F,8,2,0,true);
	public static RocketDetail LT_116 = new RocketDetail("LT-116","ЛТ-116",2.25F,1.0F,8.25F,12,2,0,true);
	public static RocketDetail UM_1034 = new RocketDetail("UM-1034","УМ-1034",1.25F,0.35F,6.25F,8,0,0,false);
	public static RocketDetail MM_4 = new RocketDetail("MM-4","ММ-4",2.0F,0.75F,7.95F,11,0,0,false);
	public static RocketDetail LM_087 = new RocketDetail("LM-087","ЛМ-087",2.5F,1.15F,9.95F,15,0,0,false);
	public static RocketDetail UF_02 = new RocketDetail("UF-02","УФ-02",1.75F,0.45F,8.0F,11,0,0,false);
	public static RocketDetail MF_043 = new RocketDetail("MF-043","МФ-043",2.5F,0.95F,8.5F,15,0,0,false);
	public static RocketDetail LF_15 = new RocketDetail("LF-15","ЛФ-15",3.15F,1.35F,10.45F,20,0,0,false);
	//Детальки
	public static boolean isUT_1202Upgrading = false;
	public static boolean isUM_1034Upgrading = false;
	public static boolean isUF_02Upgrading = false;
	public static boolean isMT_0112Upgrading = false;
	public static boolean isMM_4Upgrading = false;
	public static boolean isMF_043Upgrading = false;
	public static boolean isLT_116Upgrading = false;
	public static boolean isLM_087Upgrading = false;
	public static boolean isLF_15Upgrading = false;
	
	public static final String[] orderList1 = {"Research","Improvement of coverage","Hardware replacement"};
	public static final String[] orderList1RU = {"Исследования","Улучшение покрытия","Замена оборудования"};
	public static final String[] orderList2 = {"Updating the satellite","Collection of information","Technology development"};
	public static final String[] orderList2RU = {"Обновление спутника","Сбор информации","Развитие технологий"};
	public static final String[] orderList3 = {"Improving communication","Increasing accuracy","Experiments"};
	public static final String[] orderList3RU = {"Улучшение связи","Увеличение точности","Эксперименты"};
	public static final String[] orderList4 = {"Improving tracking","Weapons test","Cartography"};
	public static final String[] orderList4RU = {"Улучшение слежения","Испытание оружия","Составление карт"};
}
