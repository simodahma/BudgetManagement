package com.example.admin.i_expert;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Facture extends AppCompatActivity {
    TextView date,rt;
    SQLiteDatabase dbm;
    int Sum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facture);
        date= (TextView)findViewById(R.id.textView12);
        rt=(TextView)findViewById(R.id.textView14);
        dbm = this.openOrCreateDatabase("FFD.db", Context.MODE_PRIVATE, null);
        String currentDateandTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        date.setText(currentDateandTime);
        Sum=TotalServings();
        rt.setText(Integer.toString(Sum) );
        //Toast.makeText(getApplicationContext(),Integer.toString(ET.Sum),Toast.LENGTH_SHORT).show();
    }

    public int TotalServings(){
        int serving =0;

        Cursor cursor = dbm.rawQuery(
                "SELECT SUM(Prix) FROM Data", null);
        if(cursor.moveToFirst()) {
            serving = cursor.getInt(0);
        }

        return serving;
    }
}
