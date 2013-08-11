package sk.mmi.mpzspz;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SpzDao {
	
	public String table_name_spz;
	
	  // Database fields
	private SQLiteDatabase db;
	private DbHandler dbHandler;
	private String[] allColumns = { 
			  DbHandler.COLUMN_ID,
			  DbHandler.COLUMN_CODE,
			  DbHandler.COLUMN_CODE_MEANING, 
			  DbHandler.COLUMN_REGION,
			  DbHandler.COLUMN_WIKI_LINK,
	};
	  
	  public SpzDao(Context context) {
		  dbHandler = new DbHandler(context);
	  }

	  public void open() throws SQLException {
		  db = dbHandler.getWritableDatabase();
	  }

	  public void close() {
		  dbHandler.close();
	  }
	  
	  public SpzModel createDbContent(String code, String code_meaning, String region, String wiki_link) {
		  ContentValues values = new ContentValues();
		  values.put(DbHandler.COLUMN_CODE, code);
		  values.put(DbHandler.COLUMN_CODE_MEANING, code_meaning);
		  values.put(DbHandler.COLUMN_REGION, region);
		  values.put(DbHandler.COLUMN_WIKI_LINK, wiki_link);
		   
		  long insertId = db.insert(table_name_spz, null, values);
		  Cursor cursor = db.query(
				  table_name_spz,
				  allColumns, 
				  DbHandler.COLUMN_ID + " = " + insertId,
				  null, null, null, null);
		  cursor.moveToFirst();
		  SpzModel new_content = cursorToContent(cursor);
		  cursor.close();
		  return new_content;
	  }
	  
	  /*
	   * Receive all rows from DB table 
	   * */
	  public List<SpzModel> getAllDbContent() {
		  List<SpzModel> receive_db_content = new ArrayList<SpzModel>();
		  Cursor cursor = db.query(table_name_spz,
				  allColumns, null, null, null, null, null);
		  cursor.moveToFirst();	    
	    
		  while (!cursor.isAfterLast()) {
			  SpzModel db_content = cursorToContent(cursor);
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
	  public List<SpzModel> getWatchedDbContent(String watched_string) {
		  List<SpzModel> receive_db_content = new ArrayList<SpzModel>();
		  
//		  Cursor cursor = db.query(table_name_spz, allColumns, DbHandler.COLUMN_CODE + " LIKE ?",new String[]{watched_string+"%"}, null, null,null);

		  Cursor cursor = db.query(table_name_spz, null,
				  DbHandler.COLUMN_CODE+" like '"+watched_string+"%'",
				  null, null, null, null);
		  
		  cursor.moveToFirst();	    
	    
		  while (!cursor.isAfterLast()) {
			  SpzModel db_content = cursorToContent(cursor);
			  receive_db_content.add(db_content);
			  cursor.moveToNext();
		  }
		  // Make sure to close the cursor
		  cursor.close();	    
		  return receive_db_content;
	  }
	  
	  private SpzModel cursorToContent(Cursor cursor) {
		  SpzModel content = new SpzModel();
		  content.setId(cursor.getLong(0));
		  content.setCode(cursor.getString(1));
		  content.setCodeMeaning(cursor.getString(2));
		  content.setRegion(cursor.getString(3));
		  content.setWikiLink(cursor.getString(4));
		  return content;
	  }
}
