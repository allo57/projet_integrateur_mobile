package com.example.zootopia_mobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class SQLiteManager extends SQLiteOpenHelper
{
    private static SQLiteManager sqLiteManager;
    private static final String DATABASE_NAME = "Zootopia";
    private static final int DATABASE_VERSION = 2;

    public SQLiteManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static SQLiteManager instanceOfDatabase(Context context)
    {
        if(sqLiteManager == null)
            sqLiteManager = new SQLiteManager(context);

        return sqLiteManager;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        StringBuilder sql;

        //sql = new StringBuilder()

        //sqLiteDatabase.execSQL(sql.toString());

    }
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    public void addItemToCart(SQLiteDatabase sqLiteDatabase) {

    }
    public void addTicket(SQLiteDatabase sqLiteDatabase) {
    }

    /*public void addUser(Utilisateur utilisateur) {

    }*/
}
