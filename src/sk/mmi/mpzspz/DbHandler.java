package sk.mmi.mpzspz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHandler extends SQLiteOpenHelper {

	public static List<String> spz_array=new ArrayList<String>(Arrays.asList("sk","cz","pl"));
	
	// All Static variables
	public static final String TABLE_NAME_MPZ = "International_VRC";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_CODE = "code";
	public static final String COLUMN_COUNTRY = "country";
	public static final String COLUMN_CONTINENT = "continent";
	public static final String COLUMN_WIKI_LINK = "wiki_link";
	
	public static final String COLUMN_CODE_MEANING = "code_meaning";
	public static final String COLUMN_REGION = "region";

	private static final String DATABASE_NAME = "InternationalIrcVrp.db";
	private static final int DATABASE_VERSION = 1;
    
    // Table creation sql statement (MPZ)
    private static final String CREATE_TABLE_MPZ = "create table " + TABLE_NAME_MPZ + "(" 
        	+ COLUMN_ID + " integer primary key autoincrement, "
    		+ COLUMN_CODE + " text, "		
    		+ COLUMN_COUNTRY + " text, "
    		+ COLUMN_CONTINENT + " text, "
    	    + COLUMN_WIKI_LINK + " text"
    	+");";
    
    // Table creation sql statement (SPZ)
    private String CREATE_TABLE_SPZ = "(" 
        	+ COLUMN_ID + " integer primary key autoincrement, "
    		+ COLUMN_CODE + " text, "		
    		+ COLUMN_CODE_MEANING + " text, "
    		+ COLUMN_REGION + " text, "
    	    + COLUMN_WIKI_LINK + " text"
    	+");";
    
    public DbHandler(Context context) {
    	super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_MPZ);
		for(String table_name_spz: spz_array) {
			db.execSQL("create table " + table_name_spz + CREATE_TABLE_SPZ);
		}
	}
    
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    Log.w(DbHandler.class.getName(), "Upgrading database from version "
	            + oldVersion + " to " + newVersion
	            + ", which will destroy all old data");
	    
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_MPZ);
	    for(String table_name_spz: spz_array) {
			db.execSQL("DROP TABLE IF EXISTS " + table_name_spz);
		}
	    onCreate(db);
	}
}