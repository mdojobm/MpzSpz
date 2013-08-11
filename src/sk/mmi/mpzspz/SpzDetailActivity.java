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

public class SpzDetailActivity extends Activity {

    // JSON node keys
    private static final String STR_CODE = "sk.mmi.mpzspz.code";
    private static final String STR_CODE_MEANING = "sk.mmi.mpzspz.code_meaning";
    private static final String STR_REGION = "sk.mmi.mpzspz.region";
    private static final String STR_WIKI_LINK = "sk.mmi.mpzspz.wiki_link";
    private static final String STR_SPZ_EXAMPLE = "sk.mmi.mpzspz.spz_example";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.spz_detail_activity);

        // getting intent data
        Intent in = getIntent();

        // Get values from previous intent
        String code = in.getStringExtra(STR_CODE);
        String code_meaning = in.getStringExtra(STR_CODE_MEANING);
        String region = in.getStringExtra(STR_REGION);
        String wiki_link = in.getStringExtra(STR_WIKI_LINK);
        String spz_example = in.getStringExtra(STR_SPZ_EXAMPLE);
        
        // Displaying all values on the screen
        TextView label_code = (TextView) findViewById(R.id.code);
        TextView label_code_meaning = (TextView) findViewById(R.id.code_meaning);
        TextView label_region = (TextView) findViewById(R.id.region);
        TextView label_wiki_link = (TextView) findViewById(R.id.wiki_link);
        ImageView label_spz_example = (ImageView) findViewById(R.id.flag_ico);
           
        label_code.setText(code);
        label_code_meaning.setText(code_meaning);
        label_region.setText(region);
        label_wiki_link.setText(wiki_link);

        AssetManager assetManager = getAssets();
        try {
            // get input stream
            InputStream ims = assetManager.open("spz/"+spz_example+".png");
             
            // create drawable from stream
            Drawable d = Drawable.createFromStream(ims, null);
             
            // set the drawable to imageview
            label_spz_example.setImageDrawable(d);
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
