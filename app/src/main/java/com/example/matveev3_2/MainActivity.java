package com.example.matveev3_2;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);

        Button btnViewGroupmates = findViewById(R.id.btnViewGroupmates);
        btnViewGroupmates.setOnClickListener(view -> viewGroupmates());

        Button btnAddGroupmate = findViewById(R.id.btnAddGroupmate);
        btnAddGroupmate.setOnClickListener(view -> addGroupmate());

        Button btnUpdateLastGroupmate = findViewById(R.id.btnUpdateLastGroupmate);
        btnUpdateLastGroupmate.setOnClickListener(view -> updateLastGroupmate());
    }

    private void viewGroupmates() {
        Intent intent = new Intent(this, GroupmatesActivity.class);
        startActivity(intent);
    }

    private void addGroupmate() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_LAST_NAME, "Матвеев");
        values.put(DatabaseHelper.COLUMN_FIRST_NAME, "Дмитрий");
        values.put(DatabaseHelper.COLUMN_MIDDLE_NAME, "Николаевич");
        db.insert(DatabaseHelper.TABLE_GROUPMATES, null, values);
    }

    private void updateLastGroupmate() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_LAST_NAME, "Иванов");
        values.put(DatabaseHelper.COLUMN_FIRST_NAME, "Иван");
        values.put(DatabaseHelper.COLUMN_MIDDLE_NAME, "Иванович");

        String query = "SELECT " + DatabaseHelper.COLUMN_ID + " FROM " + DatabaseHelper.TABLE_GROUPMATES +
                " ORDER BY " + DatabaseHelper.COLUMN_ID + " DESC LIMIT 1";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            @SuppressLint("Range") int lastId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID));
            db.update(DatabaseHelper.TABLE_GROUPMATES, values, DatabaseHelper.COLUMN_ID + " = ?", new String[]{String.valueOf(lastId)});
        }
        cursor.close();
    }
}