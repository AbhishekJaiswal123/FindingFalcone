package com.abhishek.findingfalcone.data.source.localdata;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by abhishek on 22/12/16.
 */


public class DatabaseHelper extends SQLiteOpenHelper {


    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "falcone.db";


    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";

    private static final String CREATE_PLANET_TABLE =
            "CREATE TABLE " + DatabaseColumns.PlanetColumn.TABLE_NAME + " (" +
                    DatabaseColumns.PlanetColumn._ID + INTEGER_TYPE + " PRIMARY KEY AUTOINCREMENT," +
                    DatabaseColumns.PlanetColumn.COLUMN_PLANET + TEXT_TYPE +" UNIQUE "+ COMMA_SEP +
                    DatabaseColumns.PlanetColumn.COLUMN_PLANET_DISTANCE + TEXT_TYPE +
                    " )";

    private static final String CREATE_VEHICLE_TABLE =
            "CREATE TABLE " + DatabaseColumns.VehicleColumn.TABLE_NAME + " (" +
                    DatabaseColumns.VehicleColumn._ID + INTEGER_TYPE + " PRIMARY KEY AUTOINCREMENT," +
                    DatabaseColumns.VehicleColumn.COLUMN_VEHICLE_NAME + TEXT_TYPE +" UNIQUE "+ COMMA_SEP +
                    DatabaseColumns.VehicleColumn.COLUMN_VEHICLE_COUNT + INTEGER_TYPE + COMMA_SEP +
                    DatabaseColumns.VehicleColumn.COLUMN_VEHICLE_DISTANCE + INTEGER_TYPE + COMMA_SEP +
                    DatabaseColumns.VehicleColumn.COLUMN_VEHICLE_SPEED + INTEGER_TYPE +
                    " )";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_PLANET_TABLE);
        sqLiteDatabase.execSQL(CREATE_VEHICLE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseColumns.PlanetColumn.TABLE_NAME);
        sqLiteDatabase.execSQL(CREATE_PLANET_TABLE);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +  DatabaseColumns.VehicleColumn.TABLE_NAME);
        sqLiteDatabase.execSQL(CREATE_VEHICLE_TABLE);
    }



}
