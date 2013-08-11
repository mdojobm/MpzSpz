package sk.mmi.mpzspz;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class MpzDao {
	
	  // Database fields
	  private SQLiteDatabase db;
	  private DbHandler dbHandler;
	  private String[] allColumns = { 
			  DbHandler.COLUMN_ID,
			  DbHandler.COLUMN_CODE,
			  DbHandler.COLUMN_COUNTRY, 
			  DbHandler.COLUMN_CONTINENT,
			  DbHandler.COLUMN_WIKI_LINK,
			  };
	  
	  public MpzDao(Context context) {
		  dbHandler = new DbHandler(context);
	  }

	  public void open() throws SQLException {
		  db = dbHandler.getWritableDatabase();
	  }

	  public void close() {
		  dbHandler.close();
	  }
	  
	  public MpzModel createDbContent(String code, String country, String continent, String wiki_link) {
		  ContentValues values = new ContentValues();
		  values.put(DbHandler.COLUMN_CODE, code);
		  values.put(DbHandler.COLUMN_COUNTRY, country);
		  values.put(DbHandler.COLUMN_CONTINENT, continent);
		  values.put(DbHandler.COLUMN_WIKI_LINK, wiki_link);
		   
		  long insertId = db.insert(DbHandler.TABLE_NAME_MPZ, null, values);
		  Cursor cursor = db.query(
				  DbHandler.TABLE_NAME_MPZ,
				  allColumns, 
				  DbHandler.COLUMN_ID + " = " + insertId,
				  null, null, null, null);
		  cursor.moveToFirst();
		  MpzModel new_content = cursorToContent(cursor);
		  cursor.close();
		  return new_content;
	  }
	  
	  /*
	   * Receive all rows from DB table 
	   * */
	  public List<MpzModel> getAllDbContent() {
		  List<MpzModel> receive_db_content = new ArrayList<MpzModel>();
		  Cursor cursor = db.query(DbHandler.TABLE_NAME_MPZ,
				  allColumns, null, null, null, null, null);
		  cursor.moveToFirst();	    
	    
		  while (!cursor.isAfterLast()) {
			  MpzModel db_content = cursorToContent(cursor);
			  receive_db_content.add(db_content);
			  cursor.moveToNext();
		  }
		  // Make sure to close the cursor
		  cursor.close();	    
		  return receive_db_content;
	  }
	  
	  /*
	   * Receive all required rows from DB table 
	   * */
	  public List<MpzModel> getWatchedDbContent(String watched_string) {
		  List<MpzModel> receive_db_content = new ArrayList<MpzModel>();

//		  Cursor cursor = db.query(DbHandler.TABLE_NAME_MPZ, allColumns, DbHandler.COLUMN_CODE + " LIKE ?",new String[]{watched_string+"%"}, null, null,null);
		  
		  Cursor cursor = db.query(DbHandler.TABLE_NAME_MPZ, null,
				  DbHandler.COLUMN_CODE+" like '"+watched_string+"%'",
				  null, null, null, null);
		  
		  cursor.moveToFirst();	    
	    		  
		  while (!cursor.isAfterLast()) {
			  MpzModel db_content = cursorToContent(cursor);
			  receive_db_content.add(db_content);
			  cursor.moveToNext();
		  }
		  // Make sure to close the cursor
		  cursor.close();	    
		  return receive_db_content;
	  }
	  
	  private MpzModel cursorToContent(Cursor cursor) {
		  MpzModel content = new MpzModel();
		  content.setId(cursor.getLong(0));
		  content.setCode(cursor.getString(1));
		  content.setCountry(cursor.getString(2));
		  content.setContinent(cursor.getString(3));
		  content.setWikiLink(cursor.getString(4));
		  return content;
	  }
}
