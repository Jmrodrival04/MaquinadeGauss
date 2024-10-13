package com.example.maquinadegauss;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Nombre de la base de datos y tabla
    public static final String DATABASE_NAME = "historial.db";
    public static final String TABLE_NAME = "historial_table";
    public static final int DATABASE_VERSION = 5;  // Incrementamos la versión de la base de datos

    // Columnas de la tabla historial
    public static final String COL_1 = "ID";
    public static final String COL_2 = "FECHA";
    public static final String COL_3 = "POSICION_FINAL";
    public static final String COL_4 = "NUM_BOLAS";

    private SQLiteDatabase database;  // Variable para la base de datos

    // Constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Crear la tabla de historial
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_2 + " TEXT, " +
                COL_3 + " INTEGER, " +
                COL_4 + " INTEGER)";
        db.execSQL(CREATE_TABLE);
    }

    // Método de actualización de la base de datos
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Método sincronizado para insertar datos en la tabla historial_table
    public synchronized boolean insertarDatos(String fecha, int posicionFinal, int numBolas) {
        SQLiteDatabase db = null;
        try {
            db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_2, fecha);
            contentValues.put(COL_3, posicionFinal);
            contentValues.put(COL_4, numBolas);

            long result = db.insert(TABLE_NAME, null, contentValues);
            return result != -1;  // Si el resultado es -1, significa que hubo un error
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (db != null && db.isOpen()) {
                db.close();  // Asegurarse de cerrar la conexión
            }
        }
    }

    // Método sincronizado para obtener todos los datos de la tabla historial_table
    public synchronized Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    // Método para cerrar correctamente la base de datos
    @Override
    public synchronized void close() {
        super.close();
        if (database != null && database.isOpen()) {
            database.close();  // Cerrar la base de datos si está abierta
        }
    }
}
