package com.example.admin.i_expert;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;


public class MainActivity extends AppCompatActivity
{ ProgressBar pb;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pb=(ProgressBar)findViewById(R.id.progressBar2) ;

        new Thread(new Runnable()
        {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void run()
            {
                try
                {
                    Thread.sleep(3000);
                    //pb.setVisibility(View.INVISIBLE);
                    Intent i= new Intent(MainActivity.this,Connexion.class);
                    startActivity(i);


                }
                catch (InterruptedException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();


    }
}
