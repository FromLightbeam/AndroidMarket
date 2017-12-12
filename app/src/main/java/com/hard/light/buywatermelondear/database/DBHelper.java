package com.hard.light.buywatermelondear.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Cart";

    public static final String TABLE_NAME = "products";
    public static final String TABLE_CATEGORY = "categories";
    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_PICUREURL = "pictureURL";
    public static final String KEY_PRICE = "price";
    public static final String KEY_DESCRIPTION = "descriprion";
    public static final String KEY_COUNT = "count";

    public static final String KEY_CATEGORY_ID = "category_id";
    public static final String KEY_CATEGORY_NAME = "category_name";



    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DATA", "CREATE BASE");
        db.execSQL("create table " + TABLE_NAME + "("
                + KEY_ID + " integer primary key autoincrement,"
                + KEY_NAME + " text,"
                + KEY_PICUREURL + " text,"
                + KEY_PRICE + " text,"
                + KEY_DESCRIPTION + " text,"
                + KEY_CATEGORY_ID + " integer,"
                + KEY_COUNT + " integer);");

        db.execSQL("create table " + TABLE_CATEGORY + "("
                + KEY_CATEGORY_ID + " integer primary key autoincrement,"
                + KEY_CATEGORY_NAME + " text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists " + TABLE_NAME);

        onCreate(db);
    }
}
