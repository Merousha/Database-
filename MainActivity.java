package com.example.vanil_singh.datab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Vanil-Singh on 9/7/2017.
 */

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);

        FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(getApplicationContext());
        SQLiteDatabase db = mDbHelper.getWritableDatabase();


        String[] checkProjection = {FeedReaderContract.FeedEntry._ID};

        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.CARNAME,"BMW");
        values.put(FeedReaderContract.FeedEntry.SPEED,120);
        values.put(FeedReaderContract.FeedEntry.TANKSIZE, 40);

        db.insert(FeedReaderContract.FeedEntry.TABLE_NAME,null,values);


        db = mDbHelper.getReadableDatabase();
        String[] projection = {
                FeedReaderContract.FeedEntry._ID,
                FeedReaderContract.FeedEntry.CARNAME,
                FeedReaderContract.FeedEntry.SPEED,
                FeedReaderContract.FeedEntry.TANKSIZE
        };

        String selection = FeedReaderContract.FeedEntry.CARNAME+ " = ? ";
        String[] selectionArgs = {"BMW"};


        String sortOrder =
                FeedReaderContract.FeedEntry.SPEED + "TANKSIZE > 40";

        Cursor cursor = db.query(
                FeedReaderContract.FeedEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        String result = "";
        while (cursor.moveToNext()) {
            result += String.valueOf(cursor.getLong(cursor.getColumnIndex(FeedReaderContract.FeedEntry._ID))) + " ";
            result += cursor.getString(cursor.getColumnIndex(FeedReaderContract.FeedEntry.CARNAME)) + ", ";
            result += "speed " + String.valueOf(cursor.getLong(cursor.getColumnIndex(FeedReaderContract.FeedEntry.SPEED))) + ", ";
            result += "tank " + String.valueOf(cursor.getLong(cursor.getColumnIndex(FeedReaderContract.FeedEntry.TANKSIZE))) + "\n";
        }

        cursor.close();

        textView.setText(result);
    }

}
