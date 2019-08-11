package com.example.admin.i_expert;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EvoyerRapport extends AppCompatActivity {
    private Button Bn;
    EditText Et1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evoyer_rapport);
        Et1 = (EditText) findViewById(R.id.et10);
        Bn = (Button) findViewById(R.id.BS);
        Bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{Et1.getText().toString()});
                emailIntent.putExtra(Intent.EXTRA_CC, new String[]{""});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");
                emailIntent.setType("message/rfc822");
                startActivity(Intent.createChooser(emailIntent, "clique sur l'icone Gmail"));

            }

        });

    }
}
