package com.example.testapk.product;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import static com.example.testapk.data.SHA256.encrypt;


public class ProductsDatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 8;
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


    private static final String TABLE_USERS = "users";
    private static final String KEY_LOGIN = "login";
    private static final String KEY_PASS = "password";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_ROLE = "role";
    private static final String KEY_BALANCE = "balance";
    private static final String KEY_PRODUCTS = "products_owned";


    public ProductsDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_PRODUCTS + " ("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_DESCRIPTION + " TEXT," + KEY_PRICE + " REAL,"
                + KEY_RATING + " REAL," + KEY_NUMBER_OF_PRODUCTS + " INTEGER,"
                + KEY_IMAGE + " BLOB," + KEY_AUTHOR + " TEXT," + KEY_STATUS + " TEXT,"
                + KEY_CHECK_TIME + " BIGINT," + KEY_ADD_TIME + " BIGINT" + ");";
        System.out.println(CREATE_PRODUCTS_TABLE);
        db.execSQL(CREATE_PRODUCTS_TABLE);

        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_LOGIN + " TEXT,"
                + KEY_PASS + " TEXT," + KEY_EMAIL + " TEXT," + KEY_ROLE + " TEXT," + KEY_BALANCE + " INTEGER," + KEY_PRODUCTS + " TEXT" + ")";
        System.out.println(CREATE_USERS_TABLE);
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

        ContentValues values10 = new ContentValues();
        values10.put(KEY_NAME, "a");
        values10.put(KEY_DESCRIPTION, "a");
        values10.put(KEY_RATING, -1);
        values10.put(KEY_PRICE, -1);
        values10.put(KEY_NUMBER_OF_PRODUCTS, -1);
        values10.put(KEY_AUTHOR, "admin");
        values10.put(KEY_STATUS, "WAITING");
        values10.put(KEY_ADD_TIME, System.currentTimeMillis());
        values10.put(KEY_CHECK_TIME, -1);

        db.insert(TABLE_PRODUCTS, null, values10);

        ContentValues values11 = new ContentValues();
        values11.put(KEY_NAME, "b");
        values11.put(KEY_DESCRIPTION, "a");
        values11.put(KEY_RATING, -1);
        values11.put(KEY_PRICE, -1);
        values11.put(KEY_NUMBER_OF_PRODUCTS, -1);
        values11.put(KEY_AUTHOR, "admin");
        values11.put(KEY_STATUS, "WAITING");
        values11.put(KEY_ADD_TIME, System.currentTimeMillis());
        values11.put(KEY_CHECK_TIME, -1);

        db.insert(TABLE_PRODUCTS, null, values11);

        ContentValues values12 = new ContentValues();
        values12.put(KEY_NAME, "c");
        values12.put(KEY_DESCRIPTION, "a");
        values12.put(KEY_RATING, -1);
        values12.put(KEY_PRICE, -1);
        values12.put(KEY_NUMBER_OF_PRODUCTS, -1);
        values12.put(KEY_AUTHOR, "admin");
        values12.put(KEY_STATUS, "WAITING");
        values12.put(KEY_ADD_TIME, System.currentTimeMillis());
        values12.put(KEY_CHECK_TIME, -1);

        db.insert(TABLE_PRODUCTS, null, values12);

        ContentValues values13 = new ContentValues();
        values13.put(KEY_NAME, "d");
        values13.put(KEY_DESCRIPTION, "a");
        values13.put(KEY_RATING, -1);
        values13.put(KEY_PRICE, -1);
        values13.put(KEY_NUMBER_OF_PRODUCTS, -1);
        values13.put(KEY_AUTHOR, "admin");
        values13.put(KEY_STATUS, "WAITING");
        values13.put(KEY_ADD_TIME, System.currentTimeMillis());
        values13.put(KEY_CHECK_TIME, -1);

        db.insert(TABLE_PRODUCTS, null, values13);

        ContentValues values4 = new ContentValues();
        values4.put(KEY_LOGIN, "admin");
        values4.put(KEY_PASS, encrypt("admin"));
        values4.put(KEY_EMAIL, "admin@admin.admin");
        values4.put(KEY_ROLE, "ADMIN");
        values4.put(KEY_BALANCE, 9999);
        values4.put(KEY_PRODUCTS, "");

        db.insert(TABLE_USERS, null, values4);

        ContentValues values2 = new ContentValues();
        values2.put(KEY_LOGIN, "banned");
        values2.put(KEY_PASS, encrypt("banned"));
        values2.put(KEY_EMAIL, "banned@banned.banned");
        values2.put(KEY_ROLE, "BANNED - for being example XDD");
        values2.put(KEY_BALANCE, 0);
        values2.put(KEY_PRODUCTS, "");

        db.insert(TABLE_USERS, null, values2);

        ContentValues values3 = new ContentValues();
        values3.put(KEY_LOGIN, "user");
        values3.put(KEY_PASS, encrypt("user"));
        values3.put(KEY_EMAIL, "user@user.user");
        values3.put(KEY_ROLE, "USER");
        values3.put(KEY_BALANCE, 500);
        values3.put(KEY_PRODUCTS, "");

        db.insert(TABLE_USERS, null, values3);


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
        values.put(KEY_DESCRIPTION, productDTO.getDescription());
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

    public ProductDTO getProduct(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PRODUCTS, new String[] { KEY_ID,
                        KEY_NAME, KEY_DESCRIPTION, KEY_RATING, KEY_PRICE, KEY_NUMBER_OF_PRODUCTS, KEY_AUTHOR, KEY_STATUS, KEY_ADD_TIME, KEY_CHECK_TIME }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
//        if (cursor != null)
//            cursor.moveToFirst();

        if (cursor.getCount() > 0)
            cursor.moveToFirst();

        ProductDTO productDTO = new ProductDTO(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getDouble(3), cursor.getDouble(4), cursor.getInt(5), cursor.getString(6), cursor.getString(7), cursor.getLong(8), cursor.getLong(9));
        db.close();
        return productDTO;
    }


    public void updateProduct(ProductDTO productDTO) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, productDTO.getName());
        values.put(KEY_DESCRIPTION, productDTO.getDescription());
        values.put(KEY_RATING, productDTO.getRating());
        values.put(KEY_PRICE, productDTO.getPrice());
        values.put(KEY_NUMBER_OF_PRODUCTS, productDTO.getNumber_of_products());
        //values.put(KEY_IMAGE, productDTO.getImage());
        values.put(KEY_AUTHOR, productDTO.getAuthor());
        values.put(KEY_STATUS, productDTO.getStatus());
        values.put(KEY_ADD_TIME, productDTO.getTime_created());
        values.put(KEY_CHECK_TIME, productDTO.getTime_checked());

        //return
        db.update(TABLE_PRODUCTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(productDTO.getId()) });
    }

    public int getProductsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_PRODUCTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();
    }

    public List<ProductDTO> getAllProducts() {
        List<ProductDTO> productList = new ArrayList<ProductDTO>();
        String selectQuery = "SELECT  * FROM " + TABLE_PRODUCTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ProductDTO product = new ProductDTO();
                product.setId(cursor.getInt(0));
                product.setName(cursor.getString(1));
                product.setDescription(cursor.getString(2));
                product.setPrice(cursor.getDouble(3));
                product.setRating(cursor.getDouble(4));
                product.setNumber_of_products(cursor.getInt(5));
                product.setImage(cursor.getBlob(6));
                product.setAuthor(cursor.getString(7));
                product.setStatus(cursor.getString(8));
                product.setTime_checked(cursor.getLong(9));
                product.setTime_created(cursor.getLong(10));

                productList.add(product);
            } while (cursor.moveToNext());
        }
        db.close();
        return productList;
    }

}
