package com.example.testapk.product;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


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
    private static final String KEY_STATUS = "status";
    private static final String KEY_AUTHOR = "author";
    private static final String KEY_ADD_TIME = "add_time";
    private static final String KEY_CHECK_TIME = "check_time";
    private static final String KEY_IMAGE = "image";

    public ProductsDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_PRODUCTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_DESCRIPTION + " TEXT," + KEY_PRICE + " REAL," + KEY_RATING + " REAL," + KEY_NUMBER_OF_PRODUCTS + " INTEGER," + KEY_IMAGE + " BLOB," + KEY_AUTHOR + " TEXT," + KEY_STATUS + " TEXT," + KEY_CHECK_TIME + " BIGINT," + KEY_ADD_TIME + " BIGINT" + ")";
        db.execSQL(CREATE_USERS_TABLE);

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, "Initial product");
        values.put(KEY_DESCRIPTION, "Initial description");
        values.put(KEY_RATING, 5);
        values.put(KEY_PRICE, -1);
        values.put(KEY_NUMBER_OF_PRODUCTS, -1);
        values.put(KEY_AUTHOR, "admin");
        values.put(KEY_STATUS, "APPROVED");
        values.put(KEY_ADD_TIME, System.currentTimeMillis());
        values.put(KEY_CHECK_TIME, System.currentTimeMillis());

        db.insert(TABLE_PRODUCTS, null, values);

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
        values.put(KEY_AUTHOR, productDTO.getAuthor());
        values.put(KEY_STATUS, productDTO.getStatus());
        values.put(KEY_ADD_TIME, productDTO.getTime_created());
        values.put(KEY_CHECK_TIME, productDTO.getTime_checked());

        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    }



}
