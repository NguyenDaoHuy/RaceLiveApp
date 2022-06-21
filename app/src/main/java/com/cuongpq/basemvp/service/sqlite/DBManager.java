package com.cuongpq.basemvp.service.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.cuongpq.basemvp.model.User;

import java.util.ArrayList;
import java.util.List;
//******************************
//******************************
//***** Create by cuongpq  *****
//******************************
//******************************


public class DBManager extends SQLiteOpenHelper {
    private final String TAG = "DBManager";
    private static final String DATABASE_NAME = "user_manager";
    private static final String TABLE_NAME = "user";
    private static final String ID = "id";
    private static final String EMAIL = "email";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String AVATAR = "avatar";
    private static final int VERSION = 1;
    private final Context context;
    private final String SQLQuery = "CREATE TABLE " + TABLE_NAME + " (" +
            ID + " integer primary key, " +
            EMAIL + " TEXT, " +
            FIRST_NAME + " TEXT, " +
            LAST_NAME + " TEXT, " +
            AVATAR + " TEXT)";
    public static final DBManager getInstance(Context context){
        DBManager db = new DBManager(context);
        return db;
    }
    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
        Log.d(TAG, "DBManager: ");
    }

    public void insertUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EMAIL, user.getEmail());
        values.put(FIRST_NAME, user.getFirstName());
        values.put(LAST_NAME, user.getLastName());
        values.put(AVATAR, user.getAvatar());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public void insertListUser(List<User> users) {
        SQLiteDatabase db = this.getWritableDatabase();
        for (User user : users) {
            ContentValues values = new ContentValues();
            values.put(EMAIL, user.getEmail());
            values.put(FIRST_NAME, user.getFirstName());
            values.put(LAST_NAME, user.getLastName());
            values.put(AVATAR, user.getAvatar());
            db.insert(TABLE_NAME, null, values);
        }
        db.close();

    }

    public List<User> getUsers() {
        List<User> listUsers = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(cursor.getInt(0));
                user.setEmail(cursor.getString(1) + "");
                user.setFirstName(cursor.getString(2));
                user.setLastName(cursor.getString(3));
                user.setAvatar(cursor.getString(4));
                listUsers.add(user);
            } while (cursor.moveToNext());
        }
        db.close();
        return listUsers;
    }

    public int deleteUser(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, ID + "=?", new String[]{String.valueOf(id)});
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQLQuery);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d(TAG, "onUpgrade: ");
    }

    public Context getContext() {
        return context;
    }
}
