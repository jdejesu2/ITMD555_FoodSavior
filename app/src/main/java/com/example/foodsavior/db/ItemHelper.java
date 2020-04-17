package com.example.foodsavior.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;


public class ItemHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "com.example.foodsavior.db";
    public static final int DB_VERSION = 5;

    public class ItemFridge implements BaseColumns {
        public static final String TABLE = "items";
        public static final String COL_ITEM_TITLE = "title";
    }

    public ItemHelper(Context context) {
        super(context, DB_NAME , null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable =
                "Create Table " + ItemFridge.TABLE + " ( " +
                        ItemFridge._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + ItemFridge.COL_ITEM_TITLE + " TEXT NOT NULL);";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ItemFridge.TABLE);
        onCreate(db);
    }
}
