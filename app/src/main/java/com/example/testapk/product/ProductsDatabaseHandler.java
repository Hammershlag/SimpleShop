package com.example.testapk.product;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.testapk.user.UserDTO;

import java.util.ArrayList;
import java.util.List;


public class ProductsDatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "userManager";
    private static final String TABLE_PRODUCTS = "products";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_RATING = "rating";
    private static final String KEY_PRICE = "price";
    private static final String KEY_NUMBER_OF_PRODUCTS = "nuber_of_products";
    private static final String KEY_IMAGE = "image";

    public ProductsDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_PRODUCTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_DESCRIPTION + " TEXT," + KEY_PRICE + " REAL," + KEY_RATING + " REAL," + KEY_NUMBER_OF_PRODUCTS + " INTEGER," + KEY_IMAGE + " BLOB" + ")";
        db.execSQL(CREATE_USERS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);

        onCreate(db);
    }

    public void addProduct(ProductDTO productDTO) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, productDTO.getName());
        values.put(KEY_DESCRIPTION, productDTO.getDesription());
        values.put(KEY_RATING, productDTO.getRating());
        values.put(KEY_PRICE, productDTO.getPrice());
        values.put(KEY_NUMBER_OF_PRODUCTS, productDTO.getNumber_of_products());
        //values.put(KEY_IMAGE, productDTO.getImage());

        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    }



}
