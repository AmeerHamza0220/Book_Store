package com.example.bookstore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.recyclerview.widget.RecyclerView;


public class BookStoreDatabase extends SQLiteOpenHelper {
    public static String mDatabaseName = "BookStore.db";
    public String mTableName = "Books";
    SQLiteDatabase db = this.getWritableDatabase();

    public BookStoreDatabase(Context context) {
        super(context, mDatabaseName, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + mTableName + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, Title TEXT, Author TEXT, Edition TEXT, Price INTEGER)");
    }
    public void SaveData(String mTitle, String mAuthor, String mEdition,int mPrice) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("Title", mTitle);
        contentValues.put("Author", mAuthor);
        contentValues.put("Edition", mEdition);
        contentValues.put("Price", mPrice);
        db.insert(mTableName, null, contentValues);
    }

    public Cursor ReadData() {
        String query = "SELECT * FROM " + mTableName;
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    public int getRowsCount(){
        Cursor cursor= ReadData();
        int i=0;
        while (cursor.moveToNext())
            i++;
        return i;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
