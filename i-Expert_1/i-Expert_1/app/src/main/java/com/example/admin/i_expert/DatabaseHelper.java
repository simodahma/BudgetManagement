package com.example.admin.i_expert;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by admin on 28/05/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static  final String DATABASE_NAME = "FFD.db";
    public static  final String Table_Name_S = "Data";
    public static  final String Table_Name_T = "Emp";

    public static  final String C_1="Ids";
    public static  final String C_2="Desc";
    public static  final String C_3="Prix";
    public static  final String C_4="Date";
    public static  final String C_5="lieu";

    ///////////////

    public static  final String t_1="Idt";
    public static  final String t_2="Nomt";
    public static  final String t_3="password";
    public static  final String t_4="Email";

    public DatabaseHelper(Context context) {

        super(context,DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Table_Std());
        db.execSQL(Table_Teachers());
        db.execSQL(Insertdata());

    }

    public String  Table_Teachers()
    {

        String Sql_Stm;
        Sql_Stm="create table "+Table_Name_T+" (Idt INTEGER PRIMARY KEY AUTOINCREMENT,Nomt TEXT,password TEXT,Email TEXT)";

        return Sql_Stm;
    }


    public String Insertdata()

    {
        String Sql_Stm;
        Sql_Stm="insert into "+Table_Name_T+" values(1,'a','a','a')";

        return Sql_Stm;

    }

    public String  Table_Std()
    {

        String Sql_Stm;
        Sql_Stm="create table "+Table_Name_S+" (Ids INTEGER PRIMARY KEY AUTOINCREMENT,Desc Text,Prix INTEGER,Date Text,lieu Text)";

        return Sql_Stm;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS  " + Table_Name_S);

        onCreate(db);
    }

    public boolean Insertdata(String Name,String lieu,String Prix,String Date)

    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues CV = new ContentValues();
        CV.put(C_2,Name);
        CV.put(C_3,Prix);
        CV.put(C_4,Date);
        CV.put(C_5,lieu);
        Long Res = db.insert(Table_Name_S,null,CV);
        if(Res == -1)
        {

            return false;

        }
        else {

            return  true;

        }

    }

    public boolean updateDataTr(String idt,String Noms,String Password,String mail) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(t_2,Noms);
        contentValues.put(t_3,Password);
        contentValues.put(t_4,mail);
        db.update(Table_Name_T, contentValues, "Idt = ? ",new String[] { idt });
        return true;
    }
    public boolean updateDataStd(String ids,String desc,String lieu,String prix) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(C_2,desc);
        contentValues.put(C_3,prix);
        contentValues.put(C_5,lieu);
        db.update(Table_Name_S, contentValues, "Ids = ? ",new String[] { ids });
        return true;
    }
    public Cursor getTeacherData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + Table_Name_T,null);
        return res;
    }
    public Cursor getDatacombo(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + Table_Name_S + " where Ids = " +id,null);
        return res;
    }
    public Cursor getStdData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + Table_Name_S,null);
        return res;
    }
    public Cursor getStdDataSpi() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + Table_Name_S,null);
        return res;
    }

    public Cursor getMax() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res =db.rawQuery("select * from " + Table_Name_S,null);
        return res;
    }

}
