package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DbManager {
	private Context ctx;
	private readDB mDbHelper;
	private SQLiteDatabase mDb;
	public DbManager(Context ctx){
		this.ctx = ctx;
	}
	
	public static abstract class FeedEntry implements BaseColumns{
		public static final String TABLE_NAME = "main";
		public static final String COLUMN_NAME_TITLE = "title";
		public static final String KEY_ROWID = "_id";
		public static final String COLUMN_NAME_BODY = "body";
		public static final String COLUMN_NAME_BODY2 = "pubdate";
	}
	
	private static final String TEXT_TYPE = " TEXT";
	
	private static final String SQL_CREATE_ENTRIES = 
			"CREATE TABLE " + 
			FeedEntry.TABLE_NAME + " (" +
			"_id integer primary key autoincrement, " +
			FeedEntry.COLUMN_NAME_TITLE + TEXT_TYPE  + ", "+
			FeedEntry.COLUMN_NAME_BODY + TEXT_TYPE + ", " +
			FeedEntry.COLUMN_NAME_BODY2 + TEXT_TYPE +
			" );";
	public class readDB extends SQLiteOpenHelper{
		public static final int DATABASE_VERSION = 1;
		public static final String DATABASE_NAME = "CNNRSS.db";
		public readDB(Context context){
			super(context,DATABASE_NAME,null,DATABASE_VERSION);
		}
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(SQL_CREATE_ENTRIES);			
		}
		@Override
		public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}
	}
	public void open() throws SQLException{
		
		mDbHelper = new readDB(ctx);
		mDb = mDbHelper.getWritableDatabase();
	}
	public void close(){
		mDbHelper.close();
	}
	
	public long add_title(String title,String body,String pubDate){
		ContentValues initialValues = new ContentValues();
		initialValues.put(FeedEntry.COLUMN_NAME_TITLE,title);
		initialValues.put(FeedEntry.COLUMN_NAME_BODY, body);
		initialValues.put(FeedEntry.COLUMN_NAME_BODY2, pubDate);
		return mDb.insert(FeedEntry.TABLE_NAME, null, initialValues);
	}
	public void cleanAllinfo(){
		String clean = "delete from " + FeedEntry.TABLE_NAME;
		mDb.execSQL(clean);
	}
	
	public Cursor getTitle(){
		String[] s = new String[]{FeedEntry.KEY_ROWID,FeedEntry.COLUMN_NAME_TITLE};
		return mDb.query(FeedEntry.TABLE_NAME, s, null, null, null, null, null);
	}
	public Cursor getDescription(){
		String[] s = new String[]{FeedEntry.KEY_ROWID,FeedEntry.COLUMN_NAME_BODY};
		return mDb.query(FeedEntry.TABLE_NAME, s, null, null, null, null, null);
	}
	public Cursor firstPubdate() {
		String[] s = new String[]{FeedEntry.KEY_ROWID,FeedEntry.COLUMN_NAME_BODY2};
		return mDb.query(FeedEntry.TABLE_NAME, s, null, null, null, null, null);
	}
	public Cursor getSpecificData(long rowId) throws SQLException {
		Cursor mCursor =

	            mDb.query(true, FeedEntry.TABLE_NAME, new String[] {FeedEntry.KEY_ROWID,
	            		FeedEntry.COLUMN_NAME_TITLE}, FeedEntry.KEY_ROWID + "=" + rowId, null,
	                    null, null, null, null);
	        if (mCursor != null) {
	            mCursor.moveToFirst();
	        }
	        return mCursor;
	}

	

}
