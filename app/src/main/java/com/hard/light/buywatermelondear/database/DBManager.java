package com.hard.light.buywatermelondear.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hard.light.buywatermelondear.models.Category;
import com.hard.light.buywatermelondear.models.Product;

import java.util.ArrayList;

/**
 * Created by light on 12.12.17.
 */

public class DBManager {

    DBHelper dbHelper;

    public DBManager(Context context){
        dbHelper = new DBHelper(context);
    }

    public void addProduct(Product p){
        ContentValues contentValues = new ContentValues();
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        contentValues.put(dbHelper.KEY_NAME, p.getName());
        contentValues.put(dbHelper.KEY_PICUREURL, p.getPictureURL());
        contentValues.put(dbHelper.KEY_PRICE, p.getPrice());
        contentValues.put(dbHelper.KEY_DESCRIPTION, p.getDescription());
        contentValues.put(dbHelper.KEY_COUNT, p.getCount());
        contentValues.put(dbHelper.KEY_CATEGORY_ID, p.getCategoryId());
        database.insert(dbHelper.TABLE_NAME, null, contentValues );
        dbHelper.close();
    }

    public void initDB(){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        dbHelper.close();
    }

    public void addCategory(Category c){
        ContentValues contentValues = new ContentValues();
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        contentValues.put(dbHelper.KEY_CATEGORY_NAME, c.getName());
        database.insert(dbHelper.TABLE_CATEGORY, null, contentValues );
        dbHelper.close();
    }


    public ArrayList<Product> getProducts(){
        ContentValues contentValues = new ContentValues();
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        Cursor cursor = database.query(dbHelper.TABLE_NAME, null, null, null, null, null, null);

        ArrayList<Product> products = new ArrayList<>();
        if (cursor.moveToFirst()) {
            int idColumn = cursor.getColumnIndex(dbHelper.KEY_ID);
            int nameColumn = cursor.getColumnIndex(dbHelper.KEY_NAME);
            int pictureURLColumn = cursor.getColumnIndex(dbHelper.KEY_PICUREURL);
            int priceColumn = cursor.getColumnIndex(dbHelper.KEY_PRICE);
            int descriptionColumn = cursor.getColumnIndex(dbHelper.KEY_DESCRIPTION);
            int countColumn = cursor.getColumnIndex(dbHelper.KEY_COUNT);
            int categoryColumn = cursor.getColumnIndex(dbHelper.KEY_CATEGORY_ID);

            do {
                int id = cursor.getInt(idColumn);
                String name = cursor.getString(nameColumn);
                String pictureURL = cursor.getString(pictureURLColumn);
                String price = cursor.getString(priceColumn);
                String description = cursor.getString(descriptionColumn);
                int count = cursor.getInt(countColumn);
                int category_id = cursor.getInt(categoryColumn);
                Product p = new Product(id, name, pictureURL, price, description, count, category_id);
                products.add(p);
            } while(cursor.moveToNext());

        }
        cursor.close();
        dbHelper.close();
        return products;
    }

    public void deleteProduct(Product p){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.delete(dbHelper.TABLE_NAME, dbHelper.KEY_ID + " = " + p.getID(), null);
        dbHelper.close();
    }

    public void updateProduct(Product p) {
        ContentValues contentValues = new ContentValues();
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        contentValues.put(dbHelper.KEY_NAME, p.getName());
        contentValues.put(dbHelper.KEY_PICUREURL, p.getPictureURL());
        contentValues.put(dbHelper.KEY_PRICE, p.getPrice());
        contentValues.put(dbHelper.KEY_DESCRIPTION, p.getDescription());
        contentValues.put(dbHelper.KEY_COUNT, p.getCount());
        contentValues.put(dbHelper.KEY_CATEGORY_ID, p.getCategoryId());
        database.update(dbHelper.TABLE_NAME, contentValues, dbHelper.KEY_ID + " = " + p.getID(), null);
        dbHelper.close();
    }

    public ArrayList<String[]> getPriceList(){
        ArrayList<String[]> result = new ArrayList<>();
        ContentValues contentValues = new ContentValues();
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        String table = dbHelper.TABLE_NAME + " inner join " + dbHelper.TABLE_CATEGORY
                + " on " + dbHelper.TABLE_NAME + "." + dbHelper.KEY_CATEGORY_ID
                + " = " + dbHelper.TABLE_CATEGORY + "." + dbHelper.KEY_CATEGORY_ID;
        String columns[] = { dbHelper.TABLE_NAME + "." + dbHelper.KEY_PICUREURL,
                             dbHelper.TABLE_NAME + "." + dbHelper.KEY_NAME,
                             dbHelper.TABLE_CATEGORY + "." + dbHelper.KEY_CATEGORY_NAME };
        Cursor cursor = database.query(table, columns, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            int pictureURLColumn = cursor.getColumnIndex(dbHelper.KEY_PICUREURL);
            int nameProductColumn = cursor.getColumnIndex(dbHelper.KEY_NAME);
            int nameCategoryColumn = cursor.getColumnIndex(dbHelper.KEY_CATEGORY_NAME);

            do {

                String nameProduct = cursor.getString(nameProductColumn);
                String pictureURL = cursor.getString(pictureURLColumn);
                String nameCategory = cursor.getString(nameCategoryColumn);

                String res[] = {pictureURL, nameProduct, nameCategory};
                result.add(res);
            } while (cursor.moveToNext());
        }
        cursor.close();
        dbHelper.close();
        return result;
    }
}
