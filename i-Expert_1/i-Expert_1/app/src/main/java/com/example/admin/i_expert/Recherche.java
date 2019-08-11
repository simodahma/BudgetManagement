package com.example.admin.i_expert;

import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Recherche extends AppCompatActivity {


    Button tnb;
    ImageView Ivt;
    EditText e1,e2,e3,e4;
    DatabaseHelper dbL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche);
        tnb = (Button)findViewById(R.id.rechbut);
        e1 = (EditText)findViewById(R.id.ed1);
        e2 = (EditText)findViewById(R.id.ed2);
        e3 = (EditText)findViewById(R.id.ed3);
        e4 = (EditText)findViewById(R.id.edrech);
        Ivt = (ImageView)findViewById(R.id.imghv);
        dbL = new DatabaseHelper(this);





        tnb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor b = dbL.getDatacombo(e4.getText().toString());
                while (b.moveToNext())
                {
                    e1.setText(b.getString(1));
                    e2.setText(b.getString(2));
                    e3.setText(b.getString(4));

                }

                Ivt.setImageBitmap(BitmapFactory.decodeFile("/storage/emulated/0/TrainersImg/"+e4.getText().toString()+".png"));

            }
        });

    }

}
