package com.example.maquinadegauss;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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

    // Constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Crear la tabla de historial
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DatabaseHelper", "Creando la base de datos y la tabla historial_table");
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
        Log.d("DatabaseHelper", "Actualizando la base de datos de la versión " + oldVersion + " a " + newVersion);

        if (oldVersion < 4) {
            try {
                db.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + COL_3 + " INTEGER DEFAULT 0");
                Log.d("DatabaseHelper", "Columna POSICION_FINAL añadida con éxito.");
            } catch (Exception e) {
                Log.e("DatabaseHelper", "Error al añadir la columna POSICION_FINAL: " + e.getMessage());
            }
        }

        if (oldVersion < 5) {
            try {
                db.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + COL_4 + " INTEGER DEFAULT 0");
                Log.d("DatabaseHelper", "Columna NUM_BOLAS añadida con éxito.");
            } catch (Exception e) {
                Log.e("DatabaseHelper", "Error al añadir la columna NUM_BOLAS: " + e.getMessage());
            }
        }
    }

    // Método para insertar datos en la tabla historial_table
    public boolean insertarDatos(String fecha, int posicionFinal, int numBolas) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, fecha);
        contentValues.put(COL_3, posicionFinal);
        contentValues.put(COL_4, numBolas);

        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;  // Si el resultado es -1, significa que hubo un error
    }

    // Método para obtener todos los datos de la tabla historial_table
    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    // Método para borrar todos los datos de la tabla historial_table
    public void borrarHistorial() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}

