package com.example.matveev3_2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "groupmates_v2.db"; // Название базы данных
    private static final int DATABASE_VERSION = 2; // Версия базы данных обновлена

    // Определение колонок таблицы "Groupmates"
    public static final String TABLE_GROUPMATES = "Groupmates";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_LAST_NAME = "lastName";
    public static final String COLUMN_FIRST_NAME = "firstName";
    public static final String COLUMN_MIDDLE_NAME = "middleName";
    public static final String COLUMN_TIMESTAMP = "timestamp";

    // SQL-запрос для создания новой таблицы с разделением ФИО
    private static final String TABLE_CREATE_V2 =
            "CREATE TABLE " + TABLE_GROUPMATES + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_LAST_NAME + " TEXT, " +
                    COLUMN_FIRST_NAME + " TEXT, " +
                    COLUMN_MIDDLE_NAME + " TEXT, " +
                    COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_V2); // Создание новой таблицы
        insertInitialData(db); // Вставка начальных данных
    }

    // Метод для вставки начальных данных в таблицу
    private void insertInitialData(SQLiteDatabase db) {
        db.execSQL("DELETE FROM " + TABLE_GROUPMATES); // Очистка таблицы перед добавлением данных
        for (int i = 1; i <= 5; i++) {
            db.execSQL("INSERT INTO " + TABLE_GROUPMATES +
                    " (" + COLUMN_LAST_NAME + ", " + COLUMN_FIRST_NAME + ", " + COLUMN_MIDDLE_NAME + ") " +
                    "VALUES ('Фамилия" + i + "', 'Имя" + i + "', 'Отчество" + i + "')");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GROUPMATES); // Удаление старой таблицы
        onCreate(db); // Создание новой таблицы с обновленной структурой
    }
}
