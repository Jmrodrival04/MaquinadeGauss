package com.example.maquinadegauss;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "historial.db";
    private static final String TABLE_NAME = "historial";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "FECHA";
    private static final String COL_3 = "TIPO_CONTENEDOR";
    private static final String COL_4 = "CANTIDAD";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, FECHA TEXT, TIPO_CONTENEDOR TEXT, CANTIDAD INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Método para obtener todos los datos
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    // Método para insertar un registro en la base de datos
    public boolean insertData(String fecha, String tipoContenedor, String cantidad) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO " + TABLE_NAME + " (FECHA, TIPO_CONTENEDOR, CANTIDAD) VALUES (?, ?, ?)", new Object[]{fecha, tipoContenedor, cantidad});
        return true;
    }
}
