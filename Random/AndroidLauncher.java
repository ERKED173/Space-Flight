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
	    
	    ed.putBoolean(LNG, InfoAndStats.lngRussian);
	    
	    ed.commit();
	}
	
	public void load(){
		stats = getPreferences(MODE_PRIVATE);
		
	    InfoAndStats.elapsedTime = stats.getLong(TIME, 0);
	    InfoAndStats.launch = stats.getLong(LAUNCH, 0);
	    InfoAndStats.money = stats.getLong(MONEY, 50);
	    InfoAndStats.fuel = stats.getLong(FUEL, 50);
	    InfoAndStats.metal = stats.getLong(METAL, 50);
	    
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
