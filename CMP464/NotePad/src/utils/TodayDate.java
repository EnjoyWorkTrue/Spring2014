package utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class TodayDate extends Calendar {
	private Calendar rightNow;
	private static SimpleDateFormat sdf;
	private static SimpleDateFormat day;
	public TodayDate() {
		rightNow = getInstance(TimeZone.getDefault());
		sdf = new SimpleDateFormat("HH:mm:ss a",Locale.getDefault());
		//today "EEEE" mean full length of day like "Friday" if there is one E, meaning "Fri"
		day = new SimpleDateFormat("EEEE",Locale.getDefault());
	
	}
	public String today(){

		return rightNow.getTime().toLocaleString();
	}
	public String getDay(){
		
		return day.format(rightNow.getTime());
	}
	public void refresh(){
		rightNow = getInstance(TimeZone.getDefault());
	}
	public String getDate(){
		return sdf.format(rightNow.getTime());
	}

	@Override
	public void add(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void computeFields() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void computeTime() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getGreatestMinimum(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getLeastMaximum(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaximum(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMinimum(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void roll(int arg0, boolean arg1) {
		// TODO Auto-generated method stub
		
	}

}
