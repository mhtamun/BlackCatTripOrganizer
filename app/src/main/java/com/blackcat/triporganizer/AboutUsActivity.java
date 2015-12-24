package com.blackcat.triporganizer;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;

public class AboutUsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about_us);
	}

	@Override
	public void onRestart() {
		super.onRestart();
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onResume() {
		super.onResume();
	}


	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onStop() {
		super.onStop();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	/*
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
	    if(event.getAction() == KeyEvent.ACTION_DOWN){
	        switch(keyCode){
	        
	        case KeyEvent.KEYCODE_BACK:
	        
            	Intent OpenMyActivity = new Intent (AboutUsActivity.this, MainMenuActivity.class);
            	startActivity(OpenMyActivity);

	        }
	        
	
	    }
	    return super.onKeyDown(keyCode, event);
	}
	*/
}
