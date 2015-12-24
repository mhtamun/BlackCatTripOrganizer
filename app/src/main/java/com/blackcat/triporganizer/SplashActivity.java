package com.blackcat.triporganizer;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Thread timer = new Thread(){
        	public void run(){
        		try{
        			sleep(2000);
        		} catch( InterruptedException e){
        			e.printStackTrace();
        		} finally{
        			Intent openMainActivity = new Intent(SplashActivity.this, MainMenuActivity.class);
        			startActivity(openMainActivity);
        		}
        	}
        };
        
        timer.start();
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

		finish();
	}

	@Override
	public void onStop() {
		super.onStop();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}
}
