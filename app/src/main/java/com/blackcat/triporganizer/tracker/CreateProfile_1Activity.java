package com.blackcat.triporganizer.tracker;

import com.blackcat.triporganizer.MainMenuActivity;
import com.blackcat.triporganizer.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class CreateProfile_1Activity extends Activity implements OnClickListener, OnCheckedChangeListener {

    View backgroundimage;
    Drawable background;

    ImageButton continueButton, backButton;
    EditText editText_tripname;
    TextView tv_reason, tv_TripName;
    RadioGroup selectionList;

    String tripReason_Radio;        //Radio Button Value
    String tripName, tripReason;    //Variable for Sending Value

    Animation tvTripNameAnime;
    Animation etTripNameAnime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile_1);

        backgroundimage = (View) findViewById(R.id.linearlayout_creteProfile1);
        background = backgroundimage.getBackground();
        background.setAlpha(70);

        tv_TripName = (TextView) findViewById(R.id.textView_tripName);

        tvTripNameAnime = AnimationUtils.loadAnimation(this, R.anim.lefttorighttranslate);
        tv_TripName.startAnimation(tvTripNameAnime);

        editText_tripname = (EditText) findViewById(R.id.editText_tripname);
        editText_tripname.setInputType(InputType.TYPE_CLASS_TEXT);

        etTripNameAnime = AnimationUtils.loadAnimation(this, R.anim.righttolefttranslate);
        editText_tripname.startAnimation(etTripNameAnime);

        tv_reason = (TextView) findViewById(R.id.textView_reason);

        selectionList = (RadioGroup) findViewById(R.id.radio_reason);
        selectionList.setOnCheckedChangeListener(this);

        continueButton = (ImageButton) findViewById(R.id.imageButton_continue);
        continueButton.setOnClickListener(this);

        backButton = (ImageButton) findViewById(R.id.imageButton_cancel);
        backButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.imageButton_continue:

                tripName = editText_tripname.getText().toString();

                if (tripName.isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Required Field Not Entered", Toast.LENGTH_LONG).show();

                    break;

                } else {

                    tvTripNameAnime = AnimationUtils.loadAnimation(this, R.anim.righttolefttranslate);
                    tv_TripName.startAnimation(tvTripNameAnime);

                    etTripNameAnime = AnimationUtils.loadAnimation(this, R.anim.lefttorighttranslate);
                    editText_tripname.startAnimation(etTripNameAnime);


                    Bundle basket_tripNreason = new Bundle();
                    basket_tripNreason.putString("key_name", tripName);
                    basket_tripNreason.putString("key_reason", tripReason);

                    Intent i_CNA1 = new Intent(CreateProfile_1Activity.this, CreateProfile_2Activity.class);
                    i_CNA1.putExtras(basket_tripNreason);
                    startActivity(i_CNA1);


                }

                break;

            case R.id.imageButton_cancel:

                tvTripNameAnime = AnimationUtils.loadAnimation(this, R.anim.righttolefttranslate);
                tv_TripName.startAnimation(tvTripNameAnime);

                etTripNameAnime = AnimationUtils.loadAnimation(this, R.anim.lefttorighttranslate);
                editText_tripname.startAnimation(etTripNameAnime);

                Intent openMainActivity_CNA1 = new Intent(CreateProfile_1Activity.this, MainMenuActivity.class);
                startActivity(openMainActivity_CNA1);

                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup arg0, int arg1) {

        switch (arg1) {
            case R.id.radioButton_personal:
                tripReason_Radio = "Personal";
                tv_reason.setText("Reason For Journey: " + tripReason_Radio);
                tripReason = tripReason_Radio;
                break;
            case R.id.radioButton_Bussiness:
                tripReason_Radio = "Bussiness";
                tv_reason.setText("Reason For Journey: " + tripReason_Radio);
                tripReason = tripReason_Radio;
                break;
            case R.id.radioButton_Travel:
                tripReason_Radio = "Travel";
                tv_reason.setText("Reason For Journey: " + tripReason_Radio);
                tripReason = tripReason_Radio;
                break;
        }
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

	/*
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_DOWN){
            switch(keyCode){
            
            case KeyEvent.KEYCODE_BACK:
 
            	Intent OpenMyActivity = new Intent (CreateProfile_1Activity.this, MainMenuActivity.class);
            	startActivity(OpenMyActivity);
            	
            }

        }
        return super.onKeyDown(keyCode, event);
    }
	*/
}
