package com.example.admin.i_expert;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Actions extends AppCompatActivity {
    ImageButton Ajouter;
    ImageButton Modifier;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actions);
        Ajouter= (ImageButton)findViewById(R.id.imageButton4);
        Modifier =(ImageButton)findViewById(R.id.imageButton3);
        Ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Actions.this,AjouterAction.class);
                startActivity(i);
            }
        });
        Modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Actions.this,ModifierAction.class);
                startActivity(i);
            }
        });
    }
}
