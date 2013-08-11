package sk.mmi.mpzspz;

import android.os.Bundle;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

/** 
 * Activity for loading layout resources 
 * 
 * This activity is used to display content - two tabs with searchable ListView. 
 * IRC - international registration codes
 * VRP - vehicle registration plate
 * 
 * @author Martin Mihal 
 * @version 2013.08.09 
 * @since 1.0 
 */
@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {
	 
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);
	        
			Resources ressources = getResources(); 
			TabHost tabHost = getTabHost(); 

			// MPZ tab
			Intent intentMpz = new Intent().setClass(this, MpzActivity.class);
			TabSpec tabSpecMpz = tabHost
			  .newTabSpec("MPZ")
			  .setIndicator("", ressources.getDrawable(R.drawable.icon_mpz_config))
			  .setContent(intentMpz);
	 
			// SPZ tab
			Intent intentSpz = new Intent().setClass(this, SpzIntroActivity.class);
			TabSpec tabSpecSpz = tabHost
			  .newTabSpec("SPZ")
			  .setIndicator("", ressources.getDrawable(R.drawable.icon_spz_config))
			  .setContent(intentSpz);

			// add all tabs 
			tabHost.addTab(tabSpecMpz);
			tabHost.addTab(tabSpecSpz);
	 
			//set Windows tab as default (zero based)
			tabHost.setCurrentTab(0);
		}
	 
	}