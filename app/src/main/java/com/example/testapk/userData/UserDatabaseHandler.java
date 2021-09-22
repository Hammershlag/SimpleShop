package com.example.testapk.userData;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import static com.example.testapk.data.SHA256.encrypt;


public class UserDatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 8;
    private static final String DATABASE_NAME = "userManager";
    private static final String TABLE_USERS = "users";
    private static final String KEY_ID = "id";
    private static final String KEY_LOGIN = "login";
    private static final String KEY_PASS = "password";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_ROLE = "role";
    private static final String KEY_BALANCE = "balance";
    private static final String KEY_PRODUCTS = "products_owned";


    private static final String TABLE_PRODUCTS = "products";
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

    public UserDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_LOGIN + " TEXT,"
                + KEY_PASS + " TEXT," + KEY_EMAIL + " TEXT," + KEY_ROLE + " TEXT," + KEY_BALANCE + " INTEGER," + KEY_PRODUCTS + " TEXT" + ")";
        System.out.println(CREATE_USERS_TABLE);
        db.execSQL(CREATE_USERS_TABLE);

        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_PRODUCTS + " ("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_DESCRIPTION + " TEXT," + KEY_PRICE + " REAL,"
                + KEY_RATING + " REAL," + KEY_NUMBER_OF_PRODUCTS + " INTEGER,"
                + KEY_IMAGE + " BLOB," + KEY_AUTHOR + " TEXT," + KEY_STATUS + " TEXT,"
                + KEY_CHECK_TIME + " BIGINT," + KEY_ADD_TIME + " BIGINT" + ");";
        System.out.println(CREATE_PRODUCTS_TABLE);
        db.execSQL(CREATE_PRODUCTS_TABLE);

        ContentValues values = new ContentValues();
        values.put(KEY_LOGIN, "admin");
        values.put(KEY_PASS, encrypt("admin"));
        values.put(KEY_EMAIL, "admin@admin.admin");
        values.put(KEY_ROLE, "ADMIN");
        values.put(KEY_BALANCE, 9999);
        values.put(KEY_PRODUCTS, "");

        db.insert(TABLE_USERS, null, values);

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

        ContentValues values4 = new ContentValues();
        values4.put(KEY_NAME, "Initial product");
        values4.put(KEY_DESCRIPTION, "Initial description");
        values4.put(KEY_RATING, 5);
        values4.put(KEY_PRICE, -1);
        values4.put(KEY_NUMBER_OF_PRODUCTS, -1);
        values4.put(KEY_AUTHOR, "admin");
        values4.put(KEY_STATUS, "APPROVED");
        values4.put(KEY_ADD_TIME, System.currentTimeMillis());
        values4.put(KEY_CHECK_TIME, System.currentTimeMillis());

        db.insert(TABLE_PRODUCTS, null, values4);

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

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);

        onCreate(db);
    }

    public void addUser(UserDTO user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_LOGIN, user.getUsername());
        values.put(KEY_PASS, user.getPassword());
        values.put(KEY_EMAIL, user.getEmail());
        values.put(KEY_ROLE, user.getRole());
        values.put(KEY_BALANCE, user.getBalance());
        values.put(KEY_PRODUCTS, user.getProducts_owned());

        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    public UserDTO getUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USERS, new String[] { KEY_ID,
                        KEY_LOGIN, KEY_PASS, KEY_EMAIL, KEY_ROLE, KEY_BALANCE }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        UserDTO user = new UserDTO(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getInt(5), cursor.getString(6));
        db.close();
        return user;
    }

    public List<UserDTO> getAllUsers() {
        List<UserDTO> userList = new ArrayList<UserDTO>();
        String selectQuery = "SELECT  * FROM " + TABLE_USERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                UserDTO user = new UserDTO();
                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setUsername(cursor.getString(1));
                user.setPassword(cursor.getString(2));
                user.setEmail(cursor.getString(3));
                user.setRole(cursor.getString(4));
                user.setBalance(cursor.getInt(5));
                user.setProducts_owned(cursor.getString(6));
                userList.add(user);
            } while (cursor.moveToNext());
        }
        db.close();
        return userList;
    }
    public int getUsersCount() {
        String countQuery = "SELECT  * FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        db.close();
        return cursor.getCount();
    }

    public boolean checkIfUserExists(UserDTO user)
    {
        boolean empty = true;

        String selectQeury = "SELECT " + KEY_LOGIN + " FROM " + TABLE_USERS + " WHERE " + KEY_LOGIN + " = \'" + user.getUsername() + " /'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur = db.rawQuery(selectQeury, null);

        if (cur == null && cur.moveToFirst()) {
            empty = (cur.getInt (0) == 0);
        }
        cur.close();

        return !empty;
    }

    public int updateUser(UserDTO user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_LOGIN, user.getUsername());
        values.put(KEY_PASS, user.getPassword());
        values.put(KEY_EMAIL, user.getEmail());
        values.put(KEY_ROLE, user.getRole());
        values.put(KEY_BALANCE, user.getBalance());
        values.put(KEY_PRODUCTS, user.getProducts_owned());

        return db.update(TABLE_USERS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(user.getId()) });
    }

    public void deleteUser(UserDTO user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, KEY_ID + " = ?",
                new String[] { String.valueOf(user.getId()) });
        db.close();
    }


    public boolean isEmpty() {
        SQLiteDatabase db = this.getReadableDatabase();
        boolean empty = true;
        Cursor cur = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_USERS, null);
        if (cur != null && cur.moveToFirst()) {
            empty = (cur.getInt (0) == 0);
        }
        cur.close();

        return empty;
    }

}
