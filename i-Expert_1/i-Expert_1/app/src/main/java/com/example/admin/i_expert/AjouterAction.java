package com.example.admin.i_expert;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;

public class AjouterAction extends AppCompatActivity {
    ImageView imgk;
    SQLiteDatabase dbm;
    // int i=1;
    DatabaseHelper dbL;
    int X,Y;
    EditText Et, Et1, Et2, Et3;
    public static final int CAMERA_REQUEST = 10;
    String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_action);
        imgk = (ImageView)findViewById(R.id.img);
        dbL = new DatabaseHelper(this);

        Et = (EditText) findViewById(R.id.etC3);
        Et1 = (EditText) findViewById(R.id.etC1);
        Et2 = (EditText) findViewById(R.id.etC2);
        dbm = this.openOrCreateDatabase("FFD.db", Context.MODE_PRIVATE, null);

        //getimage();
    }



    public void B(View view) {

        boolean DtInsert = dbL.Insertdata(Et.getText().toString(),
                Et1.getText().toString(), Et2.getText().toString(), currentDateTimeString);


        if (DtInsert == true) {

            Et.setText("");
            Et1.setText("");
            Et2.setText("");
            imgk.setImageDrawable(getResources().getDrawable(R.drawable.cam));
            //Toast.makeText(getApplicationContext(),Integer.toString(Sum),Toast.LENGTH_SHORT).show();
            Toast.makeText(AjouterAction.this, "Donnee ajouté avec success", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(AjouterAction.this, "Donnee non ajouté", Toast.LENGTH_SHORT).show();
        }



    }





    public File getFile() {

        File Fol = new File("/storage/emulated/0/TrainersImg");

        if(!Fol.exists())
        {
            Fol.mkdir();
        }

        Cursor res = dbL.getMax();
        X=res.getCount()+1;

        File Im_f = new File(Fol,X+".png");

        return Im_f;
    }

    public void Pic(View v) {

        File FL = getFile();
        Intent CameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        CameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(FL));
        startActivityForResult(CameraIntent, CAMERA_REQUEST);


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        getimage();
    }
    public void getimage()
    {
        Cursor res = dbL.getMax();
        Y=res.getCount()+1;
        imgk.setImageBitmap(BitmapFactory.decodeFile("/storage/emulated/0/TrainersImg/"+Y+".png"));

    }
}
