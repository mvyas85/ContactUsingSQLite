package npu.dce.project;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class MyDBAdapter {

	private static final String DATABASE_NAME = "conDatabase.db";
    private static final String DATABASE_TABLE = "contactsTable";
    private static final int DATABASE_VERSION = 2;

    //EACH COLUMN IN DATABASE TABLE
    public static final String KEY_ID = "_id"; //primary key, CursorAdapter will use this
    public static final String KEY_NAME = "NAME";
    public static final String KEY_PHONE = "PHONE";
    public static final String KEY_EMAIL = "EMAIL";
    public static final String KEY_POSTALADDR = "POSTALADDR";

    private SQLiteDatabase db;
    private final Context context;
    private myDatabaseOpenHelper dbHelper;
    
    private String tmpID,tempName,tempPhone,tempEmail,temAdd;

    
    public MyDBAdapter(Context _context) 
    {
        this.context = _context;
        dbHelper = new myDatabaseOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void open() throws SQLiteException 
    {
        try 
        {
            db = dbHelper.getWritableDatabase();
        } 
        catch (SQLiteException ex) 
        {
            db = dbHelper.getReadableDatabase();
        }
    }

    //wrapper method, release database object
    public void close() 
    {
        db.close();
    }

    //Insert a new entry (consists a set of rows) into the table
    public long insertEntry(DataModel dataModel) 
    {
        ContentValues rows = new ContentValues();
        
        rows.put(KEY_NAME, dataModel.getName());
        rows.put(KEY_PHONE, dataModel.getPhone());
        rows.put(KEY_EMAIL, dataModel.getEmail());
        rows.put(KEY_POSTALADDR, dataModel.getPostaladdr());
        
        return db.insert(DATABASE_TABLE, null, rows);
    }
    //return a single DataModel object based on what name to search
    public Cursor getAllEntries() throws SQLException {
    	    
        Cursor cursor = db.query(DATABASE_TABLE,
        		new String[] {KEY_ID, KEY_NAME, KEY_PHONE, KEY_EMAIL, KEY_POSTALADDR},null, null, null, null,null, null);

        if (cursor != null) 
        {
        	cursor.moveToFirst();
        }
        return cursor;       
    }
    
    public Cursor getEntry(String searchname) throws SQLException {
    	    
    	Cursor cursor = db.query(DATABASE_TABLE,
        		new String[] {KEY_ID, KEY_NAME, KEY_PHONE, KEY_EMAIL, KEY_POSTALADDR},
        		KEY_NAME + "=" + "'" + searchname.trim() + "'", null, null, null,null, null);

        return cursor;
    }

    public void deleteAllEntries() {
        db.execSQL("DELETE FROM " + DATABASE_TABLE);
    }

    public boolean removeEntry(long _rowIndex) {
        return db.delete(DATABASE_TABLE, KEY_ID + "=" + _rowIndex, null) > 0;
    }

    private static class myDatabaseOpenHelper extends SQLiteOpenHelper {

        public myDatabaseOpenHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
			// TODO Auto-generated constructor stub
		}

        private static final String CREATE_TABLE =
                "create table " + DATABASE_TABLE + " (" +
                        KEY_ID + " integer primary key autoincrement, " +
                        KEY_NAME + " text not null, " +
                        KEY_PHONE + " text, " +
                        KEY_EMAIL + " text, " +
                        KEY_POSTALADDR + " text);";

		@Override
		public void onCreate(SQLiteDatabase _db) {
	       _db.execSQL(CREATE_TABLE);
	     }

		@Override
	     public void onUpgrade(SQLiteDatabase _db, 
	                int _oldVersion, int _newVersion) {
	       Log.w("TaskDBAdapter", "Upgrading from version " +
	                              _oldVersion + " to " +
	                              _newVersion 
	                + ", which will destroy all old data");

	       _db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);

	       onCreate(_db);
	     }
    }
 
}
