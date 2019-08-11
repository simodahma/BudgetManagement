package com.example.admin.i_expert;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Connexion extends AppCompatActivity {

    Button Btn,btn3;
    String N,P;
    boolean S=false;
    DatabaseHelper dbL;
    EditText Et1,Et2;

    static String Bname;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        dbL = new DatabaseHelper(this);
        btn3 = (Button)findViewById(R.id.btn2);
        Et1 = (EditText)findViewById(R.id.E_Namek);
        Et2 = (EditText)findViewById(R.id.E_NameF);

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                N = Et1.getText().toString();
                P = Et2.getText().toString();
                Cursor res = dbL.getTeacherData();
                if(res.getCount() == 0) {

                    showMessage("Error","Nothing found");
                    return;
                }

                while (res.moveToNext()) {
                    if (res.getString(1).equals(N) && res.getString(2).equals(P))
                    {
                        S=true;
                    }break;

                }

                if (S==true)
                {
                    Bname=N;
                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String strDate = sdf.format(c.getTime());
                    String[] calend = strDate.split("-");
                    int month = Integer.parseInt(calend[1]);
                    if(month < 7) {
                        Intent I = new Intent(Connexion.this, FirstPage.class);
                        startActivity(I);
                    }else
                    {

                        Toast.makeText(getApplicationContext(),"30 jours d'essai est terminée",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                    Toast.makeText(getApplicationContext(),"Nom d'utilisateur ou Mot de passe incorrect",Toast.LENGTH_SHORT).show();

            }
        });

    }
    public String getN()
    {
        return Bname;
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
