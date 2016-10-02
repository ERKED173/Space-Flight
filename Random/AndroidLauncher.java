package ru.erked.sflight;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.WindowManager;
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
	public static final String MONEY_FULL = "money_full";
	public static final String FUEL_FULL = "fuel_full";
	public static final String METAL_FULL = "metal_full";
	public static final String MONEY_AMOUNT = "money_amount";
	public static final String FUEL_AMOUNT = "fuel_amount";
	public static final String METAL_AMOUNT = "metal_amount";
	
	public static final String LNG = "lng";
	
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
	    ed.putLong(MONEY, InfoAndStats.money);
	    ed.putLong(FUEL, InfoAndStats.fuel);
	    ed.putLong(METAL, InfoAndStats.metal);
	    ed.putLong(MONEY_FULL, InfoAndStats.moneyFull);
	    ed.putLong(FUEL_FULL, InfoAndStats.fuelFull);
	    ed.putLong(METAL_FULL, InfoAndStats.metalFull);
	    ed.putLong(MONEY_AMOUNT, InfoAndStats.moneyAmount);
	    ed.putLong(FUEL_AMOUNT, InfoAndStats.fuelAmount);
	    ed.putLong(METAL_AMOUNT, InfoAndStats.metalAmount);
	    
	    ed.putBoolean(LNG, InfoAndStats.lngRussian);
	    
	    ed.commit();
	}
	
	public void load(){
		stats = getPreferences(MODE_PRIVATE);
		
	    InfoAndStats.elapsedTime = stats.getLong(TIME, 0);
	    InfoAndStats.launch = stats.getLong(LAUNCH, 0);
	    InfoAndStats.money = stats.getLong(MONEY, 3);
	    InfoAndStats.fuel = stats.getLong(FUEL, 4);
	    InfoAndStats.metal = stats.getLong(METAL, 7);
	    InfoAndStats.moneyFull = stats.getLong(MONEY_FULL, 10);
	    InfoAndStats.fuelFull = stats.getLong(FUEL_FULL, 10);
	    InfoAndStats.metalFull = stats.getLong(METAL_FULL, 10);
	    InfoAndStats.moneyAmount = stats.getLong(MONEY_AMOUNT, 1);
	    InfoAndStats.fuelAmount = stats.getLong(FUEL_AMOUNT, 1);
	    InfoAndStats.metalAmount = stats.getLong(METAL_AMOUNT, 1);
	    
	    InfoAndStats.lngRussian = stats.getBoolean(LNG, false);

	}
	
	@Override
	protected void onDestroy(){
		super.onDestroy();
		MainMenu.music.dispose();
		save();
	}
	
	@Override
	protected void onPause(){
		super.onPause();
		MainMenu.music.pause();
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
