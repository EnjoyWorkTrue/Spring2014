package utils;

import interfaCe.notifyChange;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.widget.EditText;

public class DialogButton extends AlertDialog.Builder{
	DbManager db;
	Context ctx;
	public DialogButton(Context ctx) {
		super(ctx);
		this.ctx= ctx;
		db = DbManager.getInstances();
	}
	public void addItem(){
		setNegativeButton("Cancle",null);
		final EditText edit = new EditText(ctx);
		setTitle("Title");
		edit.setTextSize(23);
		setView(edit);
		setPositiveButton("Add", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if(check(edit.getText().toString()))
					db.add_title(edit.getText().toString());
					notifyChange change = (notifyChange) ctx;
					change.happened();
			}
		});
	}
	private boolean check(String s){
		if(s.equals(""))
			return false;
		if(s.contains(" "))
			return false;
		Cursor c = db.getData();
		c.moveToFirst();
		while(!c.isAfterLast()){
			if(s.equals(c.getString(1)))
					return false;
			c.moveToNext();
		}
		
	return true;
	}
	

}
