package ru.erked.sflight;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.WindowManager;
import ru.erked.sflight.game.FlightScreen;
import ru.erked.sflight.menu.MainMenu;
import ru.erked.sflight.random.InfoAndStats;

public class AndroidLauncher extends AndroidApplication {
	
	SharedPreferences stats;
	public static final String TIME = "time";
	public static final String LAUNCH = "launch";
	public static final String DATE = "date";
	public static final String MONEY = "money";
	public static final String FUEL = "fuel";
	public static final String METAL = "metal";
	
	public static final String ORDER_AVAILABILITY = "order_availability";
	public static final String ORDER_NAME = "order_name";
	public static final String ORDER_MASS = "order_mass";
	public static final String ORDER_LEVEL = "order_level";
	
	public static final String LNG = "lng";
	
	public static final String UPGRADE_DAYS = "upgrade_days";
	public static final String HAS_TASK = "has_task";
	public static final String PREV_DAY = "prev_day";
	
	public static final String UPGRADE_DETAIL_ACCESS = "upgrade_detail_access";
	public static final String UPGRADE_DETAIL_AMOUNT = "upgrade_detail_amount";
	public static final String UPGRADE_DETAIL_IMPULSE = "upgrade_detail_impulse";
	public static final String UPGRADE_DETAIL_LEVEL = "upgrade_detail_level";
	public static final String UPGRADE_DETAIL_PRICE = "upgrade_detail_price";
	public static final String UPGRADE_DETAIL_THRUST = "upgrade_detail_thrust";
	public static final String UPGRADE_DETAIL_TIME = "upgrade_detail_time";
	
	public static final String UT_1202_ACCESS = "ut_1202_access";
	public static final String UT_1202_AMOUNT = "ut_1202_amount";
	public static final String UT_1202_IMPULSE = "ut_1202_impulse";
	public static final String UT_1202_LEVEL = "ut_1202_level";
	public static final String UT_1202_PRICE = "ut_1202_price";
	public static final String UT_1202_THRUST = "ut_1202_thrust";
	public static final String UT_1202_TIME = "ut_1202_time";
	public static final String UT_1202_UPD = "ut_1202_upd";
	
	public static final String UM_1034_ACCESS = "um_1034_access";
	public static final String UM_1034_AMOUNT = "um_1034_amount";
	public static final String UM_1034_IMPULSE = "um_1034_impulse";
	public static final String UM_1034_LEVEL = "um_1034_level";
	public static final String UM_1034_PRICE = "um_1034_price";
	public static final String UM_1034_THRUST = "um_1034_thrust";
	public static final String UM_1034_TIME = "um_1034_time";
	public static final String UM_1034_UPD = "um_1034_upd";
	
	public static final String UF_02_ACCESS = "uf_02_access";
	public static final String UF_02_AMOUNT = "uf_02_amount";
	public static final String UF_02_IMPULSE = "uf_02_impulse";
	public static final String UF_02_LEVEL = "uf_02_level";
	public static final String UF_02_PRICE = "uf_02_price";
	public static final String UF_02_THRUST = "uf_02_thrust";
	public static final String UF_02_TIME = "uf_02_time";
	public static final String UF_02_UPD = "uf_02_upd";
	
	public static final String MT_0112_ACCESS = "mt_0112_access";
	public static final String MT_0112_AMOUNT = "mt_0112_amount";
	public static final String MT_0112_IMPULSE = "mt_0112_impulse";
	public static final String MT_0112_LEVEL = "mt_0112_level";
	public static final String MT_0112_PRICE = "mt_0112_price";
	public static final String MT_0112_THRUST = "mt_0112_thrust";
	public static final String MT_0112_TIME = "mt_0112_time";
	public static final String MT_0112_UPD = "mt_0112_upd";
	
	public static final String MM_4_ACCESS = "mm_4_access";
	public static final String MM_4_AMOUNT = "mm_4_amount";
	public static final String MM_4_IMPULSE = "mm_4_impulse";
	public static final String MM_4_LEVEL = "mm_4_level";
	public static final String MM_4_PRICE = "mm_4_price";
	public static final String MM_4_THRUST = "mm_4_thrust";
	public static final String MM_4_TIME = "mm_4_time";
	public static final String MM_4_UPD = "mm_4_upd";
	
	public static final String MF_043_ACCESS = "mf_043_access";
	public static final String MF_043_AMOUNT = "mf_043_amount";
	public static final String MF_043_IMPULSE = "mf_043_impulse";
	public static final String MF_043_LEVEL = "mf_043_level";
	public static final String MF_043_PRICE = "mf_043_price";
	public static final String MF_043_THRUST = "mf_043_thrust";
	public static final String MF_043_TIME = "mf_043_time";
	public static final String MF_043_UPD = "mf_043_upd";
	
	public static final String LT_116_ACCESS = "lt_116_access";
	public static final String LT_116_AMOUNT = "lt_116_amount";
	public static final String LT_116_IMPULSE = "lt_116_impulse";
	public static final String LT_116_LEVEL = "lt_116_level";
	public static final String LT_116_PRICE = "lt_116_price";
	public static final String LT_116_THRUST = "lt_116_thrust";
	public static final String LT_116_TIME = "lt_116_time";
	public static final String LT_116_UPD = "lt_116_upd";
	
	public static final String LM_087_ACCESS = "lm_087_access";
	public static final String LM_087_AMOUNT = "lm_087_amount";
	public static final String LM_087_IMPULSE = "lm_087_impulse";
	public static final String LM_087_LEVEL = "lm_087_level";
	public static final String LM_087_PRICE = "lm_087_price";
	public static final String LM_087_THRUST = "lm_087_thrust";
	public static final String LM_087_TIME = "lm_087_time";
	public static final String LM_087_UPD = "lm_087_upd";
	
	public static final String LF_15_ACCESS = "lf_15_access";
	public static final String LF_15_AMOUNT = "lf_15_amount";
	public static final String LF_15_IMPULSE = "lf_15_impulse";
	public static final String LF_15_LEVEL = "lf_15_level";
	public static final String LF_15_PRICE = "lf_15_price";
	public static final String LF_15_THRUST = "lf_15_thrust";
	public static final String LF_15_TIME = "lf_15_time";
	public static final String LF_15_UPD = "lf_15_upd";
	
	@SuppressLint("InlinedApi")
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		load();
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new StartSFlight(), config);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}
	
	public void save(){
		stats = getPreferences(MODE_PRIVATE);
	    Editor ed = stats.edit();
	    
	    ed.putLong(TIME, InfoAndStats.elapsedTime);
	    ed.putLong(LAUNCH, InfoAndStats.launch);
	    ed.putLong(DATE, InfoAndStats.date);
	    ed.putLong(MONEY, InfoAndStats.money);
	    ed.putLong(FUEL, InfoAndStats.fuel);
	    ed.putLong(METAL, InfoAndStats.metal);
	    
	    ed.putBoolean(LNG, InfoAndStats.lngRussian);
	    
	    ed.putBoolean(ORDER_AVAILABILITY, InfoAndStats.hasOrder); 
	    ed.putString(ORDER_NAME, InfoAndStats.currentOrder.getOrderName());
	    ed.putFloat(ORDER_MASS, InfoAndStats.currentOrder.getOrderMass());
	    ed.putInt(ORDER_LEVEL, InfoAndStats.currentOrder.getOrderLevel());
	    
	    ed.putInt(UPGRADE_DAYS, InfoAndStats.days);
	    ed.putBoolean(HAS_TASK, InfoAndStats.hasTask);
	    ed.putFloat(PREV_DAY, InfoAndStats.prevDay);
	    
	    ed.putBoolean(UPGRADE_DETAIL_ACCESS, InfoAndStats.upgradeDetail.getRocketDetailAccessibility());
	    ed.putInt(UPGRADE_DETAIL_AMOUNT, InfoAndStats.upgradeDetail.getRocketDetailAmount());
	    ed.putFloat(UPGRADE_DETAIL_IMPULSE, InfoAndStats.upgradeDetail.getRocketDetailImpulse());
	    ed.putInt(UPGRADE_DETAIL_LEVEL, InfoAndStats.upgradeDetail.getRocketDetailLevel());
	    ed.putInt(UPGRADE_DETAIL_PRICE, InfoAndStats.upgradeDetail.getRocketDetailPrice());
	    ed.putFloat(UPGRADE_DETAIL_THRUST, InfoAndStats.upgradeDetail.getRocketDetailThrust());
	    ed.putFloat(UPGRADE_DETAIL_TIME, InfoAndStats.upgradeDetail.getRocketDetailTime());
	    
	    ed.putBoolean(UT_1202_ACCESS, InfoAndStats.UT_1202.getRocketDetailAccessibility());
	    ed.putInt(UT_1202_AMOUNT, InfoAndStats.UT_1202.getRocketDetailAmount());
	    ed.putFloat(UT_1202_IMPULSE, InfoAndStats.UT_1202.getRocketDetailImpulse());
	    ed.putInt(UT_1202_LEVEL, InfoAndStats.UT_1202.getRocketDetailLevel());
	    ed.putInt(UT_1202_PRICE, InfoAndStats.UT_1202.getRocketDetailPrice());
	    ed.putFloat(UT_1202_THRUST, InfoAndStats.UT_1202.getRocketDetailThrust());
	    ed.putFloat(UT_1202_TIME, InfoAndStats.UT_1202.getRocketDetailTime());
	    ed.putBoolean(UT_1202_UPD, InfoAndStats.isUT_1202Upgrading);

	    ed.putBoolean(UM_1034_ACCESS, InfoAndStats.UM_1034.getRocketDetailAccessibility());
	    ed.putInt(UM_1034_AMOUNT, InfoAndStats.UM_1034.getRocketDetailAmount());
	    ed.putFloat(UM_1034_IMPULSE, InfoAndStats.UM_1034.getRocketDetailImpulse());
	    ed.putInt(UM_1034_LEVEL, InfoAndStats.UM_1034.getRocketDetailLevel());
	    ed.putInt(UM_1034_PRICE, InfoAndStats.UM_1034.getRocketDetailPrice());
	    ed.putFloat(UM_1034_THRUST, InfoAndStats.UM_1034.getRocketDetailThrust());
	    ed.putFloat(UM_1034_TIME, InfoAndStats.UM_1034.getRocketDetailTime());
	    ed.putBoolean(UM_1034_UPD, InfoAndStats.isUM_1034Upgrading);
	    
	    ed.putBoolean(UF_02_ACCESS, InfoAndStats.UF_02.getRocketDetailAccessibility());
	    ed.putInt(UF_02_AMOUNT, InfoAndStats.UF_02.getRocketDetailAmount());
	    ed.putFloat(UF_02_IMPULSE, InfoAndStats.UF_02.getRocketDetailImpulse());
	    ed.putInt(UF_02_LEVEL, InfoAndStats.UF_02.getRocketDetailLevel());
	    ed.putInt(UF_02_PRICE, InfoAndStats.UF_02.getRocketDetailPrice());
	    ed.putFloat(UF_02_THRUST, InfoAndStats.UF_02.getRocketDetailThrust());
	    ed.putFloat(UF_02_TIME, InfoAndStats.UF_02.getRocketDetailTime());
	    ed.putBoolean(UF_02_UPD, InfoAndStats.isUF_02Upgrading);
	    
	    ed.putBoolean(MT_0112_ACCESS, InfoAndStats.MT_0112.getRocketDetailAccessibility());
	    ed.putInt(MT_0112_AMOUNT, InfoAndStats.MT_0112.getRocketDetailAmount());
	    ed.putFloat(MT_0112_IMPULSE, InfoAndStats.MT_0112.getRocketDetailImpulse());
	    ed.putInt(MT_0112_LEVEL, InfoAndStats.MT_0112.getRocketDetailLevel());
	    ed.putInt(MT_0112_PRICE, InfoAndStats.MT_0112.getRocketDetailPrice());
	    ed.putFloat(MT_0112_THRUST, InfoAndStats.MT_0112.getRocketDetailThrust());
	    ed.putFloat(MT_0112_TIME, InfoAndStats.MT_0112.getRocketDetailTime());
	    ed.putBoolean(MT_0112_UPD, InfoAndStats.isMT_0112Upgrading);
	    
	    ed.putBoolean(MM_4_ACCESS, InfoAndStats.MM_4.getRocketDetailAccessibility());
	    ed.putInt(MM_4_AMOUNT, InfoAndStats.MM_4.getRocketDetailAmount());
	    ed.putFloat(MM_4_IMPULSE, InfoAndStats.MM_4.getRocketDetailImpulse());
	    ed.putInt(MM_4_LEVEL, InfoAndStats.MM_4.getRocketDetailLevel());
	    ed.putInt(MM_4_PRICE, InfoAndStats.MM_4.getRocketDetailPrice());
	    ed.putFloat(MM_4_THRUST, InfoAndStats.MM_4.getRocketDetailThrust());
	    ed.putFloat(MM_4_TIME, InfoAndStats.MM_4.getRocketDetailTime());
	    ed.putBoolean(MM_4_UPD, InfoAndStats.isMM_4Upgrading);
	    
	    ed.putBoolean(MF_043_ACCESS, InfoAndStats.MF_043.getRocketDetailAccessibility());
	    ed.putInt(MF_043_AMOUNT, InfoAndStats.MF_043.getRocketDetailAmount());
	    ed.putFloat(MF_043_IMPULSE, InfoAndStats.MF_043.getRocketDetailImpulse());
	    ed.putInt(MF_043_LEVEL, InfoAndStats.MF_043.getRocketDetailLevel());
	    ed.putInt(MF_043_PRICE, InfoAndStats.MF_043.getRocketDetailPrice());
	    ed.putFloat(MF_043_THRUST, InfoAndStats.MF_043.getRocketDetailThrust());
	    ed.putFloat(MF_043_TIME, InfoAndStats.MF_043.getRocketDetailTime());
	    ed.putBoolean(MF_043_UPD, InfoAndStats.isMF_043Upgrading);
	    
	    ed.putBoolean(LT_116_ACCESS, InfoAndStats.LT_116.getRocketDetailAccessibility());
	    ed.putInt(LT_116_AMOUNT, InfoAndStats.LT_116.getRocketDetailAmount());
	    ed.putFloat(LT_116_IMPULSE, InfoAndStats.LT_116.getRocketDetailImpulse());
	    ed.putInt(LT_116_LEVEL, InfoAndStats.LT_116.getRocketDetailLevel());
	    ed.putInt(LT_116_PRICE, InfoAndStats.LT_116.getRocketDetailPrice());
	    ed.putFloat(LT_116_THRUST, InfoAndStats.LT_116.getRocketDetailThrust());
	    ed.putFloat(LT_116_TIME, InfoAndStats.LT_116.getRocketDetailTime());
	    ed.putBoolean(LT_116_UPD, InfoAndStats.isLT_116Upgrading);
	    
	    ed.putBoolean(LM_087_ACCESS, InfoAndStats.LM_087.getRocketDetailAccessibility());
	    ed.putInt(LM_087_AMOUNT, InfoAndStats.LM_087.getRocketDetailAmount());
	    ed.putFloat(LM_087_IMPULSE, InfoAndStats.LM_087.getRocketDetailImpulse());
	    ed.putInt(LM_087_LEVEL, InfoAndStats.LM_087.getRocketDetailLevel());
	    ed.putInt(LM_087_PRICE, InfoAndStats.LM_087.getRocketDetailPrice());
	    ed.putFloat(LM_087_THRUST, InfoAndStats.LM_087.getRocketDetailThrust());
	    ed.putFloat(LM_087_TIME, InfoAndStats.LM_087.getRocketDetailTime());
	    ed.putBoolean(LM_087_UPD, InfoAndStats.isLM_087Upgrading);
	    
	    ed.putBoolean(LF_15_ACCESS, InfoAndStats.LF_15.getRocketDetailAccessibility());
	    ed.putInt(LF_15_AMOUNT, InfoAndStats.LF_15.getRocketDetailAmount());
	    ed.putFloat(LF_15_IMPULSE, InfoAndStats.LF_15.getRocketDetailImpulse());
	    ed.putInt(LF_15_LEVEL, InfoAndStats.LF_15.getRocketDetailLevel());
	    ed.putInt(LF_15_PRICE, InfoAndStats.LF_15.getRocketDetailPrice());
	    ed.putFloat(LF_15_THRUST, InfoAndStats.LF_15.getRocketDetailThrust());
	    ed.putFloat(LF_15_TIME, InfoAndStats.LF_15.getRocketDetailTime());
	    ed.putBoolean(LF_15_UPD, InfoAndStats.isLF_15Upgrading);
	    
	    ed.commit();
	}
	
	public void load(){
		stats = getPreferences(MODE_PRIVATE);
		
	    InfoAndStats.elapsedTime = stats.getLong(TIME, 0);
	    InfoAndStats.launch = stats.getLong(LAUNCH, 0);
	    InfoAndStats.date = stats.getLong(DATE, 0);
	    InfoAndStats.money = stats.getLong(MONEY, 50);
	    InfoAndStats.fuel = stats.getLong(FUEL, 50);
	    InfoAndStats.metal = stats.getLong(METAL, 50);
	    
	    InfoAndStats.lngRussian = stats.getBoolean(LNG, false);
	    
	    InfoAndStats.days = stats.getInt(UPGRADE_DAYS, 0);
	    InfoAndStats.hasTask = stats.getBoolean(HAS_TASK, false);
	    InfoAndStats.prevDay = stats.getFloat(PREV_DAY, -1.0F);
	    
	    InfoAndStats.hasOrder = stats.getBoolean(ORDER_AVAILABILITY, false);
	    InfoAndStats.currentOrder.setOrderName(stats.getString(ORDER_NAME, ""));
	    InfoAndStats.currentOrder.setOrderMass(stats.getFloat(ORDER_MASS, 0.0F));
	    InfoAndStats.currentOrder.setOrderLevel(stats.getInt(ORDER_LEVEL, 0));
	    
	    InfoAndStats.upgradeDetail.setRocketDetailAccessibility(stats.getBoolean(UPGRADE_DETAIL_ACCESS, false));
	    InfoAndStats.upgradeDetail.setRocketDetailAmount(stats.getInt(UPGRADE_DETAIL_AMOUNT, 0));
	    InfoAndStats.upgradeDetail.setRocketDetailImpulse(stats.getFloat(UPGRADE_DETAIL_IMPULSE, 0.0F));
	    InfoAndStats.upgradeDetail.setRocketDetailLevel(stats.getInt(UPGRADE_DETAIL_LEVEL, 0));
	    InfoAndStats.upgradeDetail.setRocketDetailPrice(stats.getInt(UPGRADE_DETAIL_PRICE, 0));
	    InfoAndStats.upgradeDetail.setRocketDetailThrust(stats.getFloat(UPGRADE_DETAIL_THRUST, 0.0F));
	    InfoAndStats.upgradeDetail.setRocketDetailTime(stats.getFloat(UPGRADE_DETAIL_TIME, 0.0F));
	    
	    InfoAndStats.UT_1202.setRocketDetailAccessibility(stats.getBoolean(UT_1202_ACCESS, true));
	    InfoAndStats.UT_1202.setRocketDetailAmount(stats.getInt(UT_1202_AMOUNT, 3));
	    InfoAndStats.UT_1202.setRocketDetailImpulse(stats.getFloat(UT_1202_IMPULSE, 0.25F));
	    InfoAndStats.UT_1202.setRocketDetailLevel(stats.getInt(UT_1202_LEVEL, 0));
	    InfoAndStats.UT_1202.setRocketDetailPrice(stats.getInt(UT_1202_PRICE, 5));
	    InfoAndStats.UT_1202.setRocketDetailThrust(stats.getFloat(UT_1202_THRUST, 1.0F));
	    InfoAndStats.UT_1202.setRocketDetailTime(stats.getFloat(UT_1202_TIME, 5.0F));
	    InfoAndStats.isUT_1202Upgrading = stats.getBoolean(UT_1202_UPD, false);
	    
	    InfoAndStats.UM_1034.setRocketDetailAccessibility(stats.getBoolean(UM_1034_ACCESS, false));
	    InfoAndStats.UM_1034.setRocketDetailAmount(stats.getInt(UM_1034_AMOUNT, 0));
	    InfoAndStats.UM_1034.setRocketDetailImpulse(stats.getFloat(UM_1034_IMPULSE, 0.35F));
	    InfoAndStats.UM_1034.setRocketDetailLevel(stats.getInt(UM_1034_LEVEL, 0));
	    InfoAndStats.UM_1034.setRocketDetailPrice(stats.getInt(UM_1034_PRICE, 7));
	    InfoAndStats.UM_1034.setRocketDetailThrust(stats.getFloat(UM_1034_THRUST, 1.25F));
	    InfoAndStats.UM_1034.setRocketDetailTime(stats.getFloat(UM_1034_TIME, 6.25F));
	    InfoAndStats.isUM_1034Upgrading = stats.getBoolean(UM_1034_UPD, false);
	    
	    InfoAndStats.UF_02.setRocketDetailAccessibility(stats.getBoolean(UF_02_ACCESS, false));
	    InfoAndStats.UF_02.setRocketDetailAmount(stats.getInt(UF_02_AMOUNT, 0));
	    InfoAndStats.UF_02.setRocketDetailImpulse(stats.getFloat(UF_02_IMPULSE, 0.45F));
	    InfoAndStats.UF_02.setRocketDetailLevel(stats.getInt(UF_02_LEVEL, 0));
	    InfoAndStats.UF_02.setRocketDetailPrice(stats.getInt(UF_02_PRICE, 10));
	    InfoAndStats.UF_02.setRocketDetailThrust(stats.getFloat(UF_02_THRUST, 1.75F));
	    InfoAndStats.UF_02.setRocketDetailTime(stats.getFloat(UF_02_TIME, 8.0F));
	    InfoAndStats.isUF_02Upgrading = stats.getBoolean(UF_02_UPD, false);
	    
	    InfoAndStats.MT_0112.setRocketDetailAccessibility(stats.getBoolean(MT_0112_ACCESS, true));
	    InfoAndStats.MT_0112.setRocketDetailAmount(stats.getInt(MT_0112_AMOUNT, 3));
	    InfoAndStats.MT_0112.setRocketDetailImpulse(stats.getFloat(MT_0112_IMPULSE, 0.45F));
	    InfoAndStats.MT_0112.setRocketDetailLevel(stats.getInt(MT_0112_LEVEL, 0));
	    InfoAndStats.MT_0112.setRocketDetailPrice(stats.getInt(MT_0112_PRICE, 8));
	    InfoAndStats.MT_0112.setRocketDetailThrust(stats.getFloat(MT_0112_THRUST, 1.75F));
	    InfoAndStats.MT_0112.setRocketDetailTime(stats.getFloat(MT_0112_TIME, 6.75F));
	    InfoAndStats.isMT_0112Upgrading = stats.getBoolean(MT_0112_UPD, false);
	    
	    InfoAndStats.MM_4.setRocketDetailAccessibility(stats.getBoolean(MM_4_ACCESS, false));
	    InfoAndStats.MM_4.setRocketDetailAmount(stats.getInt(MM_4_AMOUNT, 0));
	    InfoAndStats.MM_4.setRocketDetailImpulse(stats.getFloat(MM_4_IMPULSE, 0.75F));
	    InfoAndStats.MM_4.setRocketDetailLevel(stats.getInt(MM_4_LEVEL, 0));
	    InfoAndStats.MM_4.setRocketDetailPrice(stats.getInt(MM_4_PRICE, 11));
	    InfoAndStats.MM_4.setRocketDetailThrust(stats.getFloat(MM_4_THRUST, 2.0F));
	    InfoAndStats.MM_4.setRocketDetailTime(stats.getFloat(MM_4_TIME, 7.95F));
	    InfoAndStats.isMM_4Upgrading = stats.getBoolean(MM_4_UPD, false);
	    
	    InfoAndStats.MF_043.setRocketDetailAccessibility(stats.getBoolean(MF_043_ACCESS, false));
	    InfoAndStats.MF_043.setRocketDetailAmount(stats.getInt(MF_043_AMOUNT, 0));
	    InfoAndStats.MF_043.setRocketDetailImpulse(stats.getFloat(MF_043_IMPULSE, 0.95F));
	    InfoAndStats.MF_043.setRocketDetailLevel(stats.getInt(MF_043_LEVEL, 0));
	    InfoAndStats.MF_043.setRocketDetailPrice(stats.getInt(MF_043_PRICE, 14));
	    InfoAndStats.MF_043.setRocketDetailThrust(stats.getFloat(MF_043_THRUST, 2.5F));
	    InfoAndStats.MF_043.setRocketDetailTime(stats.getFloat(MF_043_TIME, 9.75F));
	    InfoAndStats.isMF_043Upgrading = stats.getBoolean(MF_043_UPD, false);
	    
	    InfoAndStats.LT_116.setRocketDetailAccessibility(stats.getBoolean(LT_116_ACCESS, true));
	    InfoAndStats.LT_116.setRocketDetailAmount(stats.getInt(LT_116_AMOUNT, 3));
	    InfoAndStats.LT_116.setRocketDetailImpulse(stats.getFloat(LT_116_IMPULSE, 1.0F));
	    InfoAndStats.LT_116.setRocketDetailLevel(stats.getInt(LT_116_LEVEL, 0));
	    InfoAndStats.LT_116.setRocketDetailPrice(stats.getInt(LT_116_PRICE, 12));
	    InfoAndStats.LT_116.setRocketDetailThrust(stats.getFloat(LT_116_THRUST, 2.25F));
	    InfoAndStats.LT_116.setRocketDetailTime(stats.getFloat(LT_116_TIME, 8.25F));
	    InfoAndStats.isLT_116Upgrading = stats.getBoolean(LT_116_UPD, false);
	    
	    InfoAndStats.LM_087.setRocketDetailAccessibility(stats.getBoolean(LM_087_ACCESS, false));
	    InfoAndStats.LM_087.setRocketDetailAmount(stats.getInt(LM_087_AMOUNT, 0));
	    InfoAndStats.LM_087.setRocketDetailImpulse(stats.getFloat(LM_087_IMPULSE, 1.15F));
	    InfoAndStats.LM_087.setRocketDetailLevel(stats.getInt(LM_087_LEVEL, 0));
	    InfoAndStats.LM_087.setRocketDetailPrice(stats.getInt(LM_087_PRICE, 15));
	    InfoAndStats.LM_087.setRocketDetailThrust(stats.getFloat(LM_087_THRUST, 2.5F));
	    InfoAndStats.LM_087.setRocketDetailTime(stats.getFloat(LM_087_TIME, 9.95F));
	    InfoAndStats.isLM_087Upgrading = stats.getBoolean(LM_087_UPD, false);
	    
	    InfoAndStats.LF_15.setRocketDetailAccessibility(stats.getBoolean(LF_15_ACCESS, false));
	    InfoAndStats.LF_15.setRocketDetailAmount(stats.getInt(LF_15_AMOUNT, 0));
	    InfoAndStats.LF_15.setRocketDetailImpulse(stats.getFloat(LF_15_IMPULSE, 1.45F));
	    InfoAndStats.LF_15.setRocketDetailLevel(stats.getInt(LF_15_LEVEL, 0));
	    InfoAndStats.LF_15.setRocketDetailPrice(stats.getInt(LF_15_PRICE, 18));
	    InfoAndStats.LF_15.setRocketDetailThrust(stats.getFloat(LF_15_THRUST, 3.15F));
	    InfoAndStats.LF_15.setRocketDetailTime(stats.getFloat(LF_15_TIME, 10.65F));
	    InfoAndStats.isLF_15Upgrading = stats.getBoolean(LF_15_UPD, false);
	}
	
	@Override
	protected void onDestroy(){
		super.onDestroy();
		MainMenu.music.dispose();
		FlightScreen.music.dispose();
		FlightScreen.flightSound.dispose();
		save();
	}
	
	@Override
	protected void onPause(){
		super.onPause();
		MainMenu.music.pause();
		FlightScreen.music.pause();
		FlightScreen.flightSound.pause();
		save();
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		load();
	}
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus){
		
	}
}
