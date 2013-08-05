package com.example.umdcampushours;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;
import java.util.TimeZone;



public class MainActivity extends Activity {
	private static GoogleMap mMap;
	private static Calendar calendar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setUpMapIfNeeded();
		setUpCalendar();
		mark251();
		markNorthDiner();
		markSouthDiner();
		markMcKeldin();
		markStamp();
		markEppley();
		//markCSPAC();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void setUpMapIfNeeded() {//add to onResume()
	    // Do a null check to confirm that we have not already instantiated the map.
	    if (mMap == null) {
	        mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
	                            .getMap();
	        	
	        // Check if we were successful in obtaining the map.
	        if (mMap != null) {
	            // The Map is verified. It is now safe to manipulate the map.

	        }
	    }
	}
	
	private static void setUpCalendar(){//adapted from Google sample code
		
		 // get the supported ids for GMT-04:00 (Eastern Standard Time)
		 String[] ids = TimeZone.getAvailableIDs(-4 * 60 * 60 * 1000);

		 // create a Pacific Standard Time time zone
		 SimpleTimeZone est = new SimpleTimeZone(-4 * 60 * 60 * 1000, ids[0]);

		 // set up rules for daylight savings time
		 est.setStartRule(Calendar.APRIL, 1, Calendar.SUNDAY, 2 * 60 * 60 * 1000);
		 est.setEndRule(Calendar.OCTOBER, -1, Calendar.SUNDAY, 2 * 60 * 60 * 1000);

		 calendar = new GregorianCalendar(est);
		 Date trialTime = new Date();
		 calendar.setTime(trialTime);
	}
	
	private static void mark251(){
		Marker mark251 = mMap.addMarker(new MarkerOptions()
        .position(new LatLng(38.992668,  -76.949722))
        .title("251"));
		
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		boolean halfOpen = false;
		
		//setting half hour
		if(hour==17){
			halfOpen = (minute>30)?true:false;
		}
		if(hour==20){
			halfOpen = (minute>30)?false:true;
		}
		if(hour ==18||hour==19){
			halfOpen = true;
		}
		
		//set strange openings/closings (start/end semester, thanksgiving)
		
		//normal semester days
		if(day==Calendar.SUNDAY || day==Calendar.SATURDAY ||
				(day==Calendar.FRIDAY && hour>=20 && halfOpen ==false)){//saturday, sunday, or after closing on friday
			
			mark251.setSnippet("Closed until Monday at 5:30 pm");
			
		}else if(hour>=17 && hour<=20 && halfOpen == true){//time that actually open
			
			mark251.setSnippet("Open until 8:30 pm");
			
		}else{//weekday when not open
			String message = (hour>=20)?"Closed until tomorrow at 5:30 pm":"Closed until today at 5:30 pm";
			mark251.setSnippet(message);				
		}
	}
	
	private static void markSouthDiner(){
		Marker markSouthDiner = mMap.addMarker(new MarkerOptions()
        .position(new LatLng(38.983135,  -76.94369))
        .title("South Diner"));
		
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		boolean halfOpen = false;
		
		//setting half hour
		if(hour==7){
			halfOpen = (minute>30)?true:false;
		}
		if(hour>7){
			halfOpen = true;
		}
			
		//set strange openings/closings (starting school, thanksgiving, end of semester)
		
		//normal semester days
		if(day==Calendar.FRIDAY){
			if(halfOpen ==false ){
				markSouthDiner.setSnippet("Closed until 7:30 am");
			}else if(hour>=20){
				markSouthDiner.setSnippet("Closed until 10 am");
			}else{
				markSouthDiner.setSnippet("Open until 8 pm");
			}
		}else if(day==Calendar.SATURDAY||day==Calendar.SUNDAY){
			if(hour< 10){
				markSouthDiner.setSnippet("Closed until 10 am");
			}else if(hour>=20){
				markSouthDiner.setSnippet("Closed until 10 am");				
			}else{			
				markSouthDiner.setSnippet("Open until 8 pm");
			}
		}else{//mon, tues, wed, thurs			
			String opClTimes = halfOpen?"Open until midnight":"Closed until 7:30 am"; 
			markSouthDiner.setSnippet(opClTimes);			
		}
	}
	
	private static void markNorthDiner(){
		Marker markNorthDiner = mMap.addMarker(new MarkerOptions()
        .position(new LatLng(38.992446,  -76.946676))
        .title("the Diner"));
		
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		boolean halfOpen = false;
		
		//setting half hour
		if(hour==7){
			halfOpen = (minute>30)?true:false;
		}
		if(hour>7){
			halfOpen = true;
		}
		//set strange openings/closings (starting school, thanksgiving, end of semester)
		
		//normal semester days
		if(day==Calendar.FRIDAY){
			if(halfOpen == false ){
				markNorthDiner.setSnippet("Closed until 7:30 am");
			}else if(hour>=20){
				markNorthDiner.setSnippet("Closed until 10 am");
			}else{
				markNorthDiner.setSnippet("Open until 8 pm");
			}
		}else if(day==Calendar.SATURDAY){
			if(hour< 10){
				markNorthDiner.setSnippet("Closed until 10 am");
			}else if(hour>=20){
				markNorthDiner.setSnippet("Closed until 10 am");
			}else{
				markNorthDiner.setSnippet("Open until 8 pm");
			}
		}else if(day==Calendar.SUNDAY){
			if(hour< 10){
				markNorthDiner.setSnippet("Closed until 10 am");
			}else{
				markNorthDiner.setSnippet("Open until midnight");
			}
			
		}else{//mon, tues, wed, thurs
			String opClTimes = halfOpen?"Open until midnight":"Closed until 7:30 am"; 
			markNorthDiner.setSnippet(opClTimes);
		}
	}
	
	private static void markMcKeldin(){
		Marker markMcKeldin = mMap.addMarker(new MarkerOptions()
        .position(new LatLng(38.98594,  -76.94512))
        .title("McKeldin"));
		
		//set strange openings/closings (starting school, thanksgiving, end of semester)
		if(calendar.get(Calendar.DATE)==1||calendar.get(Calendar.DATE)==2){
			markMcKeldin.setSnippet("CLOSED");
		}else{
			markMcKeldin.setSnippet("update hours when available");
		}
		
		//normal semester days
		
	}
	
	private static void markStamp(){
		Marker markStamp = mMap.addMarker(new MarkerOptions()
        .position(new LatLng(38.987926,  -76.944881))
        .title("Stamp"));
		
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		
		//set strange openings/closings (starting school, thanksgiving, end of semester)
		if(day==Calendar.SUNDAY){
			if(hour<11){
				markStamp.setSnippet("Closed until 11 am");
			}else if(hour<=18){
				markStamp.setSnippet("Closed until 7 am");
			}else{
				markStamp.setSnippet("Open until 6 pm");
			}			
		}else if(day==Calendar.SATURDAY){
			if(hour<10){
				markStamp.setSnippet("Closed until 10 am");
			}else if(hour<=18){
				markStamp.setSnippet("Closed until 11 am");
			}else{
				markStamp.setSnippet("Open until 6 pm");
			}
		}else{//mon, tues, wed, thurs, fri
			if(hour<7){
				markStamp.setSnippet("Closed until 7 am");
			}else if(hour<=22){
				String setSnip = day ==Calendar.FRIDAY?"Closed until 10 am":"Closed until 7 am";
				markStamp.setSnippet(setSnip);
			}else{
				markStamp.setSnippet("Open until 10 pm");
			}
		}
		
		
		//normal semester days
	}
	
	private static void markEppley(){
		Marker markEppley = mMap.addMarker(new MarkerOptions()
        .position(new LatLng(38.993563,  -76.945077))
        .title("Eppley"));
		
		//set strange openings/closings (starting school, thanksgiving, end of semester)
		markEppley.setSnippet("update hours when available");
		//normal semester days
	}
	
	
	//other markers to add: CMSC cafe, CSPAC library, professor office hours 
	

}
