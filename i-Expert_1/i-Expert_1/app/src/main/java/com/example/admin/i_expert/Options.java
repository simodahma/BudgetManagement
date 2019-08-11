package com.example.admin.i_expert;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;

public class Options extends AppCompatActivity {
    public CircleMenu circleMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        circleMenu = (CircleMenu)findViewById(R.id.c);

        circleMenu.setMainMenu(Color.parseColor("#CDCDCD"), R.mipmap.menu, R.mipmap.cancel)
                .addSubMenu(Color.parseColor("#258CFF"), R.mipmap.gps)
                .addSubMenu(Color.parseColor("#8A39FF"), R.mipmap.calcul)
                .addSubMenu(Color.parseColor("#FF4B32"), R.mipmap.search)
                .addSubMenu(Color.parseColor("#30A400"), R.mipmap.menuu)

                .setOnMenuSelectedListener(new OnMenuSelectedListener() {

                    @Override
                    public void onMenuSelected(int index)
                    {
                        if(index==1)
                        {
                            new Thread(new Runnable()
                            {
                                @RequiresApi(api = Build.VERSION_CODES.N)
                                @Override
                                public void run()
                                {
                                    try
                                    {
                                        Thread.sleep(600);
                                        //pb.setVisibility(View.INVISIBLE);
                                        Intent i = new Intent(Options.this,Facture.class);
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
                        if(index==2)
                        {
                            new Thread(new Runnable()
                            {
                                @RequiresApi(api = Build.VERSION_CODES.N)
                                @Override
                                public void run()
                                {
                                    try
                                    {
                                        Thread.sleep(600);
                                        //pb.setVisibility(View.INVISIBLE);
                                        Intent i = new Intent(Options.this,Recherche.class);
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
                        if(index==3)
                        {
                            new Thread(new Runnable()
                            {
                                @RequiresApi(api = Build.VERSION_CODES.N)
                                @Override
                                public void run()
                                {
                                    try
                                    {
                                        Thread.sleep(600);
                                        //pb.setVisibility(View.INVISIBLE);
                                        Intent i = new Intent(Options.this,FirstPage.class);
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
                        if(index==0)
                        {
                            new Thread(new Runnable()
                            {
                                @RequiresApi(api = Build.VERSION_CODES.N)
                                @Override
                                public void run()
                                {
                                    try
                                    {
                                        Thread.sleep(600);
                                        //pb.setVisibility(View.INVISIBLE);
                                        Intent i = new Intent(Options.this,gps.class);
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

                }).setOnMenuStatusChangeListener(new OnMenuStatusChangeListener() {

            @Override
            public void onMenuOpened() {}

            @Override
            public void onMenuClosed() {}

        });



    }
}

