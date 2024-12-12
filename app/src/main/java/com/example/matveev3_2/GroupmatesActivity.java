package com.example.matveev3_2;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class GroupmatesActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groupmates);

        dbHelper = new DatabaseHelper(this);

        TextView textView = findViewById(R.id.textViewGroupmates);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_GROUPMATES, null, null, null, null, null, null);

        StringBuilder builder = new StringBuilder();
        while (cursor.moveToNext()) {
            @SuppressLint("Range") String lastName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_LAST_NAME));
            @SuppressLint("Range") String firstName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_FIRST_NAME));
            @SuppressLint("Range") String middleName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_MIDDLE_NAME));
            @SuppressLint("Range") String timestamp = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TIMESTAMP));

            builder.append(lastName).append(" ").append(firstName).append(" ").append(middleName)
                    .append(" - ").append(timestamp).append("\n");
        }
        textView.setText(builder.toString());
        cursor.close();
    }
}