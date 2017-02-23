package com.metagenomix.android.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.metagenomix.android.model.Record;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by sarki on 09.12.16..
 */
public class DatabaseManager extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Patogen2.db";
    public static final String TABLE = "data";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_CONTENT = "content";

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE + "(" +
                COLUMN_DATE + " TEXT, " +
                COLUMN_CONTENT + " TEXT " +
                "  )";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public void addRecord(Record record) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_DATE, record.getDate());
        values.put(COLUMN_CONTENT, new JSONObject(record.getMap()).toString());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE, null, values);
        db.close();
    }

    public ArrayList<Record> getAllRecords() {
        ArrayList<Record> history = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Record record = new Record();
                record.setDate(cursor.getString(0));
                String map = cursor.getString(1);
                Map<String, Float> hashMap = jsonToMap(map);
                record.setMap(hashMap);

                history.add(record);
            } while (cursor.moveToNext());
        }

        return history;
    }

    public static Map<String, Float> jsonToMap(String t) {
        HashMap<String, Float> map = new HashMap<>();
        try {
            JSONObject jObject = new JSONObject(t);
            Iterator<?> keys = jObject.keys();

            while (keys.hasNext()) {
                String key = (String) keys.next();
                Float value = Float.parseFloat(jObject.getString(key));
                map.put(key, value);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return map;
    }

    public String[] getColumns() {
        SQLiteDatabase dataBase = getReadableDatabase();
        Cursor dbCursor = dataBase.query(TABLE, null, null, null, null, null, null);
        String[] columnNames = dbCursor.getColumnNames();

        return columnNames;
    }
}
