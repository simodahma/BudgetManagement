package com.example.admin.i_expert;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EnvoyerMessage extends AppCompatActivity {
     EditText subject,message;
    Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envoyer_message);
        subject=(EditText)findViewById(R.id.editText);
        message=(EditText)findViewById(R.id.editText2);
        send = (Button)findViewById(R.id.button2);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   String sub = subject.getText().toString();
                String msg = message.getText().toString();
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL,new String[]{"to"});
                email.putExtra(Intent.EXTRA_SUBJECT,sub);
                email.putExtra(Intent.EXTRA_TEXT,msg);
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email,"simo.dahma20@gmail.com"));

            }
        });

    }
}
