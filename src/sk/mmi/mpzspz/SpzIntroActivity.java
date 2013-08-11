package sk.mmi.mpzspz;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

/** 
 * Activity for loading layout resources 
 * 
 * This activity is used to display menu for define country
 * for VRP - vehicle registration plate.  
 * 
 * @author Martin Mihal 
 * @version 2013.08.09 
 * @since 1.0 
 */
public class SpzIntroActivity extends Activity {
	
	ImageButton imageButtonSk;
	ImageButton imageButtonCz;
	ImageButton imageButtonPl;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spz_intro_activity);

		addListenerOnButton();
    }

	/** 
	 * The method that listens click of a button
	 * 
	 * @param void
	 * @return void
	 */ 
    public void addListenerOnButton() {    	 
    	imageButtonCz = (ImageButton) findViewById(R.id.imageButton_cz);
    	imageButtonPl = (ImageButton) findViewById(R.id.imageButton_pl);
    	imageButtonSk = (ImageButton) findViewById(R.id.imageButton_sk);
     	
    	/** 
    	 * Displayed imageButton
    	 */ 
    	imageButtonCz.setOnClickListener(new OnClickListener() {    		 
			@Override
			public void onClick(View arg0) {
 
                // Starting new intent
                Intent in_spz_intro = new Intent(getApplicationContext(), SpzActivity.class);
                in_spz_intro.putExtra("sk.mmi.mpzspz.table_name_spz", "cz");
                startActivity(in_spz_intro);
			}
 
		});
    	
    	/** 
    	 * Displayed imageButton
    	 */     	
    	imageButtonPl.setOnClickListener(new OnClickListener() {    		 
			@Override
			public void onClick(View arg0) {
 
                // Starting new intent
                Intent in_spz_intro = new Intent(getApplicationContext(), SpzActivity.class);
                in_spz_intro.putExtra("sk.mmi.mpzspz.table_name_spz", "pl");
                startActivity(in_spz_intro);
			}
 
		});
    	
    	/** 
    	 * Displayed imageButton
    	 */   	
    	imageButtonSk.setOnClickListener(new OnClickListener() { 
			@Override
			public void onClick(View arg0) {
 
                // Starting new intent
                Intent in_spz_intro = new Intent(getApplicationContext(), SpzActivity.class);
                in_spz_intro.putExtra("sk.mmi.mpzspz.table_name_spz", "sk");
                startActivity(in_spz_intro);
			}
		});
	}
}