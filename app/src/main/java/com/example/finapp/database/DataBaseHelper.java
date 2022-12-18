package com.example.finapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "bank_db";

    public static final String TABLE_NAME = "transacao";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TIPO_OPERACAO = "tipo_operacao";
    public static final String COLUMN_VALOR = "valor";
    public static final String COLUMN_CLASSIFICACAO = "classificacao";
    public static final String COLUMN_DATA = "data";

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_TRANSACAO_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_CLASSIFICACAO + " TEXT NOT NULL, "
                + COLUMN_TIPO_OPERACAO + " INTEGER NOT NULL, "
                + COLUMN_DATA + " INTEGER NOT NULL, "
                + COLUMN_VALOR + " REAL NOT NULL "
                + ")";

        sqLiteDatabase.execSQL(CREATE_TRANSACAO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
