package sk.mmi.mpzspz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

/** 
 * Activity for loading layout resources 
 * 
 * This activity is used to display "search listview" for 
 * for search VRP - vehicle registration plate
 * 
 * @author Martin Mihal 
 * @version 2013.08.09 
 * @since 1.0 
 */
public class SpzActivity extends ListActivity implements TextWatcher {
	
	private EditText edit_text;
	private SpzDao db_source;
	private ArrayAdapter<SpzModel> adapter;
	private String table_name_spz;

    		
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spz_activity);
        
        // getting intent data
        Intent in_spz_intro = getIntent();   
        table_name_spz = in_spz_intro.getStringExtra("sk.mmi.mpzspz.table_name_spz");
        
        // connect to DB via DAO class
        db_source = new SpzDao(this); 
        db_source.table_name_spz = table_name_spz;
        db_source.open();
        
        edit_text=(EditText)findViewById(R.id.editText_spz);
        edit_text.addTextChangedListener(this);

        AssetManager assetManager = getAssets();
        List<SpzModel> values = db_source.getAllDbContent();   

        adapter = new ArrayAdapter<SpzModel>(this, R.layout.list_item_textview, values); 
        setListAdapter(adapter);

        SpzModel ivm = null;       
        
        // Fill DB after first start app.
        if (values.isEmpty()){
	        /*
	         * READ FROM CSV FILE TO DB OUTPUT
	         * */
	        try {
	        	InputStream input = assetManager.open("spz/" + table_name_spz + ".csv");            
	        	Reader reader = new InputStreamReader(input);
		        BufferedReader br = new BufferedReader(reader);
		    	String line = "";
		    	String cvsSplitBy = this.getString(R.string.csv_separator);
	
		    	while ((line = br.readLine()) != null) {
		     
		    		// use comma as separator
		    		String[] csv_value = line.split(cvsSplitBy);
		     
		        	ivm = db_source.createDbContent(
		        			csv_value[0],
		        			csv_value[1],
		        			csv_value[2],
		        			csv_value[3]
		        			);
		            adapter.add(ivm);	     
		    	}	        
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	        
        } //end if
        // close DB
        db_source.close();
        
		/*
		 * 
		 * USER SELECT ITEM DETAIL
		 * */
        // selecting single ListView item
        ListView lv = getListView();
        
        // Launching new screen on Selecting Single ListItem
        lv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            	            	
              // getting values from selected ListItem
              String view_code = adapter.getItem(position).getCode();               
              String view_code_meaning = adapter.getItem(position).getCodeMeaning();         
              String view_region = adapter.getItem(position).getRegion();         
              String view_wiki_link = adapter.getItem(position).getWikiLink();
                
                // Starting new intent
                Intent in = new Intent(getApplicationContext(), SpzDetailActivity.class);
                in.putExtra("sk.mmi.mpzspz.code", view_code);
                in.putExtra("sk.mmi.mpzspz.code_meaning", view_code_meaning);
                in.putExtra("sk.mmi.mpzspz.region", view_region);
                in.putExtra("sk.mmi.mpzspz.wiki_link", view_wiki_link);
                in.putExtra("sk.mmi.mpzspz.spz_example", table_name_spz);
                startActivity(in);
            }          
        });        
    }

	@Override
	public void afterTextChanged(Editable s) {

		// open DB
        db_source.open();
        
        String editable_string = s.toString();

        List<SpzModel> values_over_watch = db_source.getWatchedDbContent(editable_string);  
        
        adapter = new ArrayAdapter<SpzModel>(this, R.layout.list_item_textview, values_over_watch);  
        setListAdapter(adapter);
        
        // close DB
        db_source.close();
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub
		
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