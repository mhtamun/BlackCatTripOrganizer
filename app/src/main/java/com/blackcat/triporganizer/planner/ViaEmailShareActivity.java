package com.blackcat.triporganizer.planner;

import com.blackcat.triporganizer.R;
import com.blackcat.triporganizer.R.layout;
import com.blackcat.triporganizer.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class ViaEmailShareActivity extends Activity {

    EditText ET_Email, ET_Subject, ET_MessageBody;

    ImageButton IB_Send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_via_email_share);

        ET_Email = (EditText) findViewById(R.id.editText_EmailActivity_MailAdd);

        ET_Subject = (EditText) findViewById(R.id.editText_EmailActivity_Subject);
        ET_Subject.setText(PlannerListViewerActivity.name);

        ET_MessageBody = (EditText) findViewById(R.id.editText_EmailActivity_Body);
        ET_MessageBody.setText(PlannerListViewerActivity.EmailMessage);

        IB_Send = (ImageButton) findViewById(R.id.imageButton_EmailActivity_Send);
        IB_Send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String to = ET_Email.getText().toString();
                String subject = ET_Subject.getText().toString();
                String message = ET_MessageBody.getText().toString();

                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
                email.putExtra(Intent.EXTRA_SUBJECT, subject);
                email.putExtra(Intent.EXTRA_TEXT, message);

                //need this to prompts email client only
                email.setType("message/rfc822");

                startActivity(Intent.createChooser(email, "Choose an Email client :"));


            }
        });
    }

    /*
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {

                case KeyEvent.KEYCODE_BACK:

                    Intent BacktoAddNew = new Intent(ViaEmailShareActivity.this, PlannerListwithCreateNewActivity.class);
                    startActivity(BacktoAddNew);

            }

        }

        return super.onKeyDown(keyCode, event);
    }
    */
}
