package com.example.admin.i_expert;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EspaceAdmin extends AppCompatActivity {

    EditText Etd, Ed1, Ed2, Ed3;
    Button btn_User;
    DatabaseHelper dbL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        dbL = new DatabaseHelper(this);
        btn_User = (Button) findViewById(R.id.Add_F);
        Etd = (EditText) findViewById(R.id.E_NameF);
        Ed1 = (EditText) findViewById(R.id.pass);
        Ed3 = (EditText) findViewById(R.id.E_Mail);

        btn_User.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Etd.getText().toString().equals("") || Ed1.getText().toString().equals("") || Ed3.getText().toString().equals("")) {
                    Toast.makeText(EspaceAdmin.this, "veuillez s'il vous plait remplir tous champs", Toast.LENGTH_SHORT).show();
                } else {
                    boolean DtInsert = dbL.updateDataTr("1", Etd.getText().toString(),
                            Ed1.getText().toString(),
                            Ed3.getText().toString()
                    );
                    if (DtInsert == true) {
                        Toast.makeText(EspaceAdmin.this, "votre compte a été créé avec succès", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(EspaceAdmin.this, "votre compte n'est pas créé", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
