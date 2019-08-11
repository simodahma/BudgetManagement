package com.example.admin.i_expert;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EtatJournalier extends AppCompatActivity {

    final static String TAG = MainActivity.class.getName();
    CheckBox chkParent;
    SQLiteDatabase dbm;
    Button et;
    String strDate;
    String S;

    CharSequence []values={"Android","Apple","Java"};
    private ProgressDialog pd;
    int progress=0;
    Handler ph;
    Button btn;
    boolean[] itemChecked = new boolean[values.length];


    DatabaseHelper dbL;
    Image image = null;
    Image imagek = null;


    String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 14,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.BLUE);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD, BaseColor.RED);
    private static Font subFnt = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD, BaseColor.BLACK);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 22,
            Font.BOLDITALIC);

    private String dataPath = "/storage/emulated/0/TrainersImg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etat_journalier);
        dbL = new DatabaseHelper(this);
        et = (Button) findViewById(R.id.BtnSavef);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        strDate = sdf.format(c.getTime());

        dbm = this.openOrCreateDatabase("FFD.db", Context.MODE_PRIVATE, null);

        ph = new Handler(){
            public void handleMessage(Message msg)
            {
                super.handleMessage(msg);
                if(progress>=100)
                {
                    pd.dismiss();
                }
                else
                {
                    progress++;
                    pd.incrementProgressBy(3);
                    ph.sendEmptyMessageDelayed(0,100);
                }
            }
        };
        et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Document Dox = new Document();
                String outpath = Environment.getExternalStorageDirectory() + "/TrainersImg/Rapport_"+strDate+".pdf";
                try {
                    PdfWriter instance = PdfWriter.getInstance(Dox, new FileOutputStream(outpath));
                    Dox.open();

                    Paragraph P0 = new Paragraph();
                    Paragraph P2 = new Paragraph();
                    Paragraph P4 = new Paragraph();
                    Phrase P1 = new Phrase();
                    Phrase P3 = new Phrase();

                    try {
                        // get input stream
                        InputStream ims = getAssets().open("ex.png");
                        Bitmap bmp = BitmapFactory.decodeStream(ims);
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                        Image imagek = Image.getInstance(stream.toByteArray());
                        imagek.scaleAbsoluteWidth(56);
                        imagek.scaleAbsoluteHeight(56);
                        imagek.setAbsolutePosition(500,760);

                        Dox.add(imagek);
                    }
                    catch(IOException ex)
                    {
                        return;
                    }

                    // We add one empty line
                    P1.add(new Phrase(currentDateTimeString, redFont));
                    Dox.add(P1);
                    addEmptyLine(P0, 1, Dox);

                    //FirstPage F = new FirstPage();
                    P0.add(new Paragraph("**  Bonjour Monsieur  **", smallBold));
                    P0.setIndentationLeft(130);
                    Dox.add(P0);
                    addEmptyLine(P2, 3, Dox);
                    Chunk chunk = new Chunk("Voici le rapport d'aujoudhui : ", catFont);
                    chunk.setUnderline(1, 17);
                    chunk.setUnderline(1, -10);
                    P3.add(chunk);
                    Dox.add(P3);

                    Cursor res = dbL.getStdData();
                    if (res.getCount() == 0) {
                        showMessage("Error", "Nothing found");
                        return;
                    }

                    while (res.moveToNext()) {


                        addEmptyLine(P2, 2, Dox);
                        Paragraph c5 = new Paragraph("Action numéro : " +res.getString(0),subFont);
                        c5.setAlignment(Paragraph.ALIGN_LEFT);
                        Dox.add(c5);

                        addEmptyLine(P2, 3, Dox);
                        Chunk c1 = new Chunk("Description : ", redFont);
                        Dox.add(c1);
                        Dox.add(new Paragraph(res.getString(1).toString()));
                        addEmptyLine(P2, 1, Dox);
                        Chunk c2 = new Chunk("Montant : ", redFont);
                        Dox.add(c2);
                        Dox.add(new Paragraph(res.getString(2).toString()));
                        addEmptyLine(P2, 1, Dox);
                        Chunk c3 = new Chunk("Lieu : ", redFont);
                        Dox.add(c3);
                        Dox.add(new Paragraph(res.getString(4).toString()));
                        addEmptyLine(P2, 1, Dox);
                        Chunk c4 = new Chunk("Date : ", redFont);
                        Dox.add(c4);
                        Dox.add(new Paragraph(res.getString(3).toString()));

                        try {
                            addEmptyLine(P2, 2, Dox);
                            image = Image.getInstance("/storage/emulated/0/TrainersImg/" + res.getString(0).toString() + ".png");
                            image.scaleAbsoluteWidth(250);
                            image.scaleAbsoluteHeight(190);
                            image.setAlignment(Image.ALIGN_CENTER);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Dox.add(image);
                        addEmptyLine(P2, 2, Dox);

                    }
                    addEmptyLine(P2, 4, Dox);
                    Paragraph v2 = new Paragraph("la somme des montants est  : "+TotalServings()+" DH",subFnt);
                    v2.setAlignment(Paragraph.ALIGN_LEFT);
                    Dox.add(v2);
                    addEmptyLine(P2, 2, Dox);
                    Paragraph v5 = new Paragraph("Fin.",subFnt);
                    v5.setAlignment(Paragraph.ALIGN_CENTER);
                    Dox.add(v5);

                    Dox.close();

                    showDialog(1);
                    progress=0;
                    pd.setProgress(0);
                    ph.sendEmptyMessage(0);

                    Toast.makeText(getApplicationContext(), "Fichier créé avec succes", Toast.LENGTH_SHORT).show();
                } catch (DocumentException e) {
                    Toast.makeText(getApplicationContext(), "Fichier non créé", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    Toast.makeText(getApplicationContext(), "Fichier non créé", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                dbm.execSQL("DROP TABLE IF EXISTS Data");
                dbm.execSQL("create table Data (Ids INTEGER PRIMARY KEY AUTOINCREMENT,Desc Text,Prix Text,Date Text,lieu Text)");

            }

        });

    }

    /* public void C(View view) {


     }*/

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void addEmptyLine(Paragraph paragraph, int number, Document Dox) throws DocumentException {
        for (int k = 0; k < number; k++) {
            Dox.add(new Paragraph(" "));
        }
    }

    @Override
    @Deprecated
    protected Dialog onCreateDialog(int id)
    {
        switch(id)
        {
            case 0:
                return new AlertDialog.Builder(this).setIcon(R.drawable.send).setTitle("Envoi du rapport ...").
                        setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getBaseContext(),"Envoi",Toast.LENGTH_SHORT).show();
                            }
                        }).setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getBaseContext(),"Envoi",Toast.LENGTH_LONG).show();
                    }
                }).setMultiChoiceItems(values, itemChecked, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                Toast.makeText(getBaseContext(),values[which]+(isChecked?"checked":"unchecked"),Toast.LENGTH_SHORT).show();
                            }
                        }
                ).create();
            case 1:
                pd = new ProgressDialog(this);
                pd.setIcon(R.drawable.send);
                pd.setTitle("Chargement des fichiers ...");
                pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pd.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getBaseContext(),"Hide clicked",Toast.LENGTH_SHORT).show();

                    }
                });
                pd.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getBaseContext(),"OK clicked",Toast.LENGTH_SHORT).show();



                    }
                });
                return pd;
        }
        return null;
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
