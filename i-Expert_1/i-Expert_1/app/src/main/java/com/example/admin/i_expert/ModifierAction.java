package com.example.admin.i_expert;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

import static com.example.admin.i_expert.AjouterAction.CAMERA_REQUEST;
public class ModifierAction extends AppCompatActivity {
    Spinner sp;
    Button tnb,Btms;
    String name;
    ImageView Ivt;
    EditText e1,e2,e3;
    DatabaseHelper dbL;
    ArrayList<String> names=new ArrayList<String>();
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_action);
        tnb = (Button)findViewById(R.id.b4);
        sp=(Spinner) findViewById(R.id.sp2);
        e1 = (EditText)findViewById(R.id.edsc);
        e2 = (EditText)findViewById(R.id.elieu);
        Btms = (Button)findViewById(R.id.btnpict);
        e3 = (EditText)findViewById(R.id.eprix);
        Ivt = (ImageView)findViewById(R.id.Inv);
        dbL = new DatabaseHelper(this);
        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,names);

        names.clear();

        Cursor c=dbL.getStdDataSpi();
        while(c.moveToNext())
        {
            name=c.getString(0);
            names.add(name);

        }


        sp.setAdapter(adapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Cursor b = dbL.getDatacombo(sp.getSelectedItem().toString());
                while (b.moveToNext())
                {
                    e1.setText(b.getString(1));
                    e2.setText(b.getString(4));
                    e3.setText(b.getString(2));

                }
                Ivt.setImageBitmap(BitmapFactory.decodeFile("/storage/emulated/0/TrainersImg/"+sp.getSelectedItem().toString()+".png"));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(ModifierAction.this,"Non",Toast.LENGTH_SHORT).show();

            }
        });
        tnb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean DtInsert = dbL.updateDataStd(sp.getSelectedItem().toString(),e1.getText().toString(),
                        e2.getText().toString(),
                        e3.getText().toString()
                );
                if (DtInsert==true)
                {
                    Toast.makeText(ModifierAction.this, "votre Action a été Modifié avec succès", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(ModifierAction.this, "Action Non Modifie", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Btms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File FL = getFile();
                Intent CameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                CameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(FL));
                startActivityForResult(CameraIntent, CAMERA_REQUEST);
            }
        });

    }

    public File getFile() {

        File Fol = new File("/storage/emulated/0/TrainersImg");

        if(!Fol.exists())
        {
            Fol.mkdir();
        }

        Cursor res = dbL.getMax();


        File Im_f = new File(Fol,sp.getSelectedItem().toString()+".png");

        return Im_f;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

}
