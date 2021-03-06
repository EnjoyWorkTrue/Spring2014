package utils;


import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;
import android.widget.Toast;

public class DialogButton extends AlertDialog.Builder{
	getCursor cursor;
	Context ctx;
	String TableName;
	private NotesDbAdapter mDbHelper;
	long id;
	private ArrayList<String> names;
	public DialogButton(Context arg0,String TB,long id) {
		super(arg0);
		ctx = arg0;
		TableName = TB;
		this.id = id;
	}
	
	public DialogButton(Context ctx2, String tableName) {
		super(ctx2);
		ctx = ctx2;
		TableName = tableName;
		
	}
	public DialogButton(Context ctx){
		super(ctx);
		this.ctx = ctx;
	}

	public void createEdit() {
		openDB();
		setNegativeButton("Delete", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
            	mDbHelper.deleteNote(id, TableName);
            	cursor = (getCursor) ctx;
            	cursor.getNewAdapter();
            }
        });
        final EditText edit = new EditText(ctx);
        edit.setText("hello");
        edit.setTextSize(30);
        setView(edit);
        setPositiveButton("Edit", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
            	mDbHelper.updateNote(id, edit.getText().toString(), TableName,1);
            	cursor = (getCursor) ctx;
            	cursor.getNewAdapter();

            }
        });
	}
	public void createAdd(){
		openDB();
		setNegativeButton("Cancle", null);
		final EditText edit = new EditText(ctx);
        setTitle("Title");
        edit.setTextSize(23);
        setView(edit);
        setPositiveButton("Okay", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
            	mDbHelper.createNote(edit.getText().toString(), "",TableName);
            	cursor = (getCursor) ctx;
            	cursor.getNewAdapter();

            }
        });
	}
	public void createTable(){
		openDB();
		setNegativeButton("Cancle", null);
		final EditText edit = new EditText(ctx);
        setTitle("Title");
        edit.setTextSize(23);
        setView(edit);
        setPositiveButton("Okay", new DialogInterface.OnClickListener() {
        	
        	

            public void onClick(DialogInterface dialog, int which) {
            	//create Table if not exists
            	if(edit.getText().toString().contains(" ")){
            		Toast.makeText(ctx, "no space", Toast.LENGTH_SHORT);            		
            	}
            	else if (!(edit.getText().toString()).equals("")){       
            		if(!names.contains(edit.getText().toString())){
                   	mDbHelper.createNote(edit.getText().toString(), "");
            		cursor = (getCursor) ctx;
                	cursor.getNewAdapter();
           
                	}
            		else
            		{
            			Toast.makeText(ctx, "sameName", Toast.LENGTH_SHORT).show();
            			createTable();
            		}
            	}
            	

            }
        });
	}
	private void openDB() {
		mDbHelper = new NotesDbAdapter(ctx);
		mDbHelper.open();
		
	}

	public void setArraylist(ArrayList<String> names) {
		this.names = names;
		
	}
	
	

}
