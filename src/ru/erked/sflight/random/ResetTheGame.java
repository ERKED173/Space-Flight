package ru.erked.sflight.random;

import ru.erked.sflight.game.AngarScreen;

public class ResetTheGame {

	public static void reset(){
		InfoAndStats.elapsedTime = 0;
		InfoAndStats.date = 0;
		InfoAndStats.launch = 0;
		InfoAndStats.money = 50;
		InfoAndStats.days = 0;
		
		InfoAndStats.hasOrder = false;
	    InfoAndStats.currentOrder.setOrderName("");
	    InfoAndStats.currentOrder.setOrderMass(0.0F);
	    InfoAndStats.currentOrder.setOrderLevel(0);
		
		InfoAndStats.UT_1202.setRocketDetailAccessibility(true);
	    InfoAndStats.UT_1202.setRocketDetailAmount(3);
	    InfoAndStats.UT_1202.setRocketDetailImpulse(0.25F);
	    InfoAndStats.UT_1202.setRocketDetailLevel(0);
	    InfoAndStats.UT_1202.setRocketDetailPrice(5);
	    InfoAndStats.UT_1202.setRocketDetailThrust(1.0F);
	    InfoAndStats.UT_1202.setRocketDetailTime(5.0F);
	    
	    InfoAndStats.UM_1034.setRocketDetailAccessibility(false);
	    InfoAndStats.UM_1034.setRocketDetailAmount(0);
	    InfoAndStats.UM_1034.setRocketDetailImpulse(0.35F);
	    InfoAndStats.UM_1034.setRocketDetailLevel(0);
	    InfoAndStats.UM_1034.setRocketDetailPrice(8);
	    InfoAndStats.UM_1034.setRocketDetailThrust(1.25F);
	    InfoAndStats.UM_1034.setRocketDetailTime(6.25F);
	    
	    InfoAndStats.UF_02.setRocketDetailAccessibility(false);
	    InfoAndStats.UF_02.setRocketDetailAmount(0);
	    InfoAndStats.UF_02.setRocketDetailImpulse(0.45F);
	    InfoAndStats.UF_02.setRocketDetailLevel(0);
	    InfoAndStats.UF_02.setRocketDetailPrice(11);
	    InfoAndStats.UF_02.setRocketDetailThrust(1.75F);
	    InfoAndStats.UF_02.setRocketDetailTime(8.0F);
	    
	    InfoAndStats.MT_0112.setRocketDetailAccessibility(true);
	    InfoAndStats.MT_0112.setRocketDetailAmount(3);
	    InfoAndStats.MT_0112.setRocketDetailImpulse(0.45F);
	    InfoAndStats.MT_0112.setRocketDetailLevel(0);
	    InfoAndStats.MT_0112.setRocketDetailPrice(8);
	    InfoAndStats.MT_0112.setRocketDetailThrust(1.75F);
	    InfoAndStats.MT_0112.setRocketDetailTime(6.75F);
	    
	    InfoAndStats.MM_4.setRocketDetailAccessibility(false);
	    InfoAndStats.MM_4.setRocketDetailAmount(0);
	    InfoAndStats.MM_4.setRocketDetailImpulse(0.75F);
	    InfoAndStats.MM_4.setRocketDetailLevel(0);
	    InfoAndStats.MM_4.setRocketDetailPrice(11);
	    InfoAndStats.MM_4.setRocketDetailThrust(2.0F);
	    InfoAndStats.MM_4.setRocketDetailTime(7.95F);
	    
	    InfoAndStats.MF_043.setRocketDetailAccessibility(false);
	    InfoAndStats.MF_043.setRocketDetailAmount(0);
	    InfoAndStats.MF_043.setRocketDetailImpulse(0.95F);
	    InfoAndStats.MF_043.setRocketDetailLevel(0);
	    InfoAndStats.MF_043.setRocketDetailPrice(15);
	    InfoAndStats.MF_043.setRocketDetailThrust(2.5F);
	    InfoAndStats.MF_043.setRocketDetailTime(8.5F);
	    
	    InfoAndStats.LT_116.setRocketDetailAccessibility(true);
	    InfoAndStats.LT_116.setRocketDetailAmount(3);
	    InfoAndStats.LT_116.setRocketDetailImpulse(1.0F);
	    InfoAndStats.LT_116.setRocketDetailLevel(0);
	    InfoAndStats.LT_116.setRocketDetailPrice(12);
	    InfoAndStats.LT_116.setRocketDetailThrust(2.25F);
	    InfoAndStats.LT_116.setRocketDetailTime(8.25F);
	    
	    InfoAndStats.LM_087.setRocketDetailAccessibility(false);
	    InfoAndStats.LM_087.setRocketDetailAmount(0);
	    InfoAndStats.LM_087.setRocketDetailImpulse(1.15F);
	    InfoAndStats.LM_087.setRocketDetailLevel(0);
	    InfoAndStats.LM_087.setRocketDetailPrice(15);
	    InfoAndStats.LM_087.setRocketDetailThrust(2.5F);
	    InfoAndStats.LM_087.setRocketDetailTime(9.95F);
	    
	    InfoAndStats.LF_15.setRocketDetailAccessibility(false);
	    InfoAndStats.LF_15.setRocketDetailAmount(0);
	    InfoAndStats.LF_15.setRocketDetailImpulse(1.35F);
	    InfoAndStats.LF_15.setRocketDetailLevel(0);
	    InfoAndStats.LF_15.setRocketDetailPrice(20);
	    InfoAndStats.LF_15.setRocketDetailThrust(3.15F);
	    InfoAndStats.LF_15.setRocketDetailTime(10.45F);
	    
	    InfoAndStats.selectedFirst = "null";
		InfoAndStats.firstStage.setRocketDetailAccessibility(false);
		InfoAndStats.firstStage.setRocketDetailAmount(0);
		InfoAndStats.firstStage.setRocketDetailImpulse(0.0F);
		InfoAndStats.firstStage.setRocketDetailName("","");
		InfoAndStats.firstStage.setRocketDetailThrust(0.0F);
		InfoAndStats.firstStage.setRocketDetailTime(0.0F);
	    InfoAndStats.selectedSecond = "null";
		InfoAndStats.secondStage.setRocketDetailAccessibility(false);
		InfoAndStats.secondStage.setRocketDetailAmount(0);
		InfoAndStats.secondStage.setRocketDetailImpulse(0.0F);
		InfoAndStats.secondStage.setRocketDetailName("","");
		InfoAndStats.secondStage.setRocketDetailThrust(0.0F);
		InfoAndStats.secondStage.setRocketDetailTime(0.0F);
		InfoAndStats.selectedThird = "null";
	    InfoAndStats.thirdStage.setRocketDetailAccessibility(false);
		InfoAndStats.thirdStage.setRocketDetailAmount(0);
		InfoAndStats.thirdStage.setRocketDetailImpulse(0.0F);
		InfoAndStats.thirdStage.setRocketDetailName("","");
		InfoAndStats.thirdStage.setRocketDetailThrust(0.0F);
		InfoAndStats.thirdStage.setRocketDetailTime(0.0F);
		
		InfoAndStats.isUT_1202Upgrading = false;
		InfoAndStats.isUM_1034Upgrading = false;
		InfoAndStats.isUF_02Upgrading = false;
		InfoAndStats.isMT_0112Upgrading = false;
		InfoAndStats.isMM_4Upgrading = false;
		InfoAndStats.isMF_043Upgrading = false;
		InfoAndStats.isLT_116Upgrading = false;
		InfoAndStats.isLM_087Upgrading = false;
		InfoAndStats.isLF_15Upgrading = false;

		AngarScreen.isBoundDraw1 = false;
		AngarScreen.isBoundDraw2 = false;
		AngarScreen.isBoundDraw3 = false;
		AngarScreen.isUT_1202Selected = false;
		AngarScreen.isUM_1034Selected = false;
		AngarScreen.isUF_02Selected = false;
		AngarScreen.isMT_0112Selected = false;
		AngarScreen.isMM_4Selected = false;
		AngarScreen.isMF_043Selected = false;
		AngarScreen.isLT_116Selected = false;
		AngarScreen.isLM_087Selected = false;
		AngarScreen.isLF_15Selected = false;
	}
	
}
