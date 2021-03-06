package utils;

import org.json.JSONObject;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * This will be our list adapter for our list view
 * displays items in the list based on the results obtained
 * from the cursor.
 * @author josh
 *
 */
public class MyCursorAdapter extends CursorAdapter{
	/**
	 * return cursor adapter which uses the cursor
	 * that iterates over all columns in all rows 
	 * for the sports table
	 * 
	 * @param ctx
	 * @param db
	 * @return
	 */
	int count = 0;
	
		
	SQLiteDatabase sql;
	public static final MyCursorAdapter defaultImplementation(Activity ctx,SportsManager db){
		Cursor c = db.getWritableDatabase().query(
				SportsManager.TABLE_NAME, 
				null, null, null, null, null, null);
		
		return new MyCursorAdapter(ctx,c,db.getWritableDatabase());
	}

	private Activity ctx;
	/**
	 * we use this constructor so that android doesn't auto-query
	 * which will make thing to slow, and cause the app to be unresponsive
	 * 
	 * @param context
	 * @param c
	 * @param flags
	 */
	public void delete(long id){
		sql.delete(SportsManager.TABLE_NAME, "_id=?", new String[]{Long.toString(id)});
		Cursor c = sql.query(
				SportsManager.TABLE_NAME, 
				null, null, null, null, null, null);
		super.changeCursor(c);
		//notifyDataSetChanged();
	}
	public MyCursorAdapter(Activity context, Cursor c, SQLiteDatabase sql) {
		super(context, c, 0);
		this.ctx = context;
		this.sql = sql;
		
	}
	public void insert(String arena){
		ContentValues cv = new ContentValues();
		cv.put(SportsManager.DATE, "2013-12-10");
		count ++;
		if (count == 5)
			count = 1;
		cv.put(SportsManager.ARENA, arena+count);
		cv.put(SportsManager.CITY, "this is");
		cv.put(SportsManager.STATE, "what what");
		sql.insert(SportsManager.TABLE_NAME, null, cv);
		Cursor c = sql.query(
				SportsManager.TABLE_NAME, 
				null, null, null, null, null, null);
		super.changeCursor(c);
	}

	/*
	 * bindView re-uses the view passed in from newView to re-use the data
	 * as we iterate the cursor
	 * (non-Javadoc)
	 * @see android.support.v4.widget.CursorAdapter#bindView(android.view.View, android.content.Context, android.database.Cursor)
	 */
	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		//we get the data field contained in the 'arena' column 
		//for whatever record is currently being pointed to by the cursor
		String arena = cursor.getString(cursor.getColumnIndex(SportsManager.ARENA));
		TextView tv = (TextView) view;
		tv.setText(arena);
	}
	/**
	 * newView requires a view to be returned to the cursor adapter
	 * to be re-used for each list item
	 */
	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		LayoutInflater inflater = ctx.getLayoutInflater();
		//stock android list item. just a text view
		TextView tv = (TextView) inflater.inflate(android.R.layout.simple_list_item_1, null);
		//we get the data field contained in the 'arena' column 
		//for whatever record is currently being pointed to by the cursor
		String arena = cursor.getString(cursor.getColumnIndex(SportsManager.ARENA));
		tv.setText(arena);
		return tv;
	}

	@Override
	public long getItemId(int position) {
		return super.getItemId(position);
	}

}
