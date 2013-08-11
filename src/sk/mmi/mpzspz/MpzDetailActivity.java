package sk.mmi.mpzspz;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class MpzDetailActivity extends Activity {

    // JSON node keys
    private static final String STR_CODE = "sk.mmi.mpzspz.code";
    private static final String STR_COUNTRY = "sk.mmi.mpzspz.country";
    private static final String STR_CONTINENT = "sk.mmi.mpzspz.continent";
    private static final String STR_WIKI_LINK = "sk.mmi.mpzspz.wiki_link";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mpz_detail_activity);

        // getting intent data
        Intent in = getIntent();

        // Get values from previous intent
        String code = in.getStringExtra(STR_CODE);
        String country = in.getStringExtra(STR_COUNTRY);
        String continent = in.getStringExtra(STR_CONTINENT);
        String wiki_link = in.getStringExtra(STR_WIKI_LINK);	

        // Displaying all values on the screen
        TextView label_code = (TextView) findViewById(R.id.code);
        TextView label_country = (TextView) findViewById(R.id.country);
        TextView label_continent = (TextView) findViewById(R.id.continent);
        TextView label_wiki_link = (TextView) findViewById(R.id.wiki_link);
        ImageView label_flag = (ImageView) findViewById(R.id.flag_ico);
           
        label_code.setText(code);
        label_country.setText(country);
        label_continent.setText(continent);
        label_wiki_link.setText(wiki_link);

        AssetManager assetManager = getAssets();
        try {
            // get input stream
            InputStream ims = assetManager.open("flag/"+code+".png");
             
            // create drawable from stream
            Drawable d = Drawable.createFromStream(ims, null);
             
            // set the drawable to imageview
            label_flag.setImageDrawable(d);
        }
        catch(IOException ex) {
            return;
        }
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
