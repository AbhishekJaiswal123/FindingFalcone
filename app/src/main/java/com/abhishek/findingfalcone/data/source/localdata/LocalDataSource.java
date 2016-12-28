package com.abhishek.findingfalcone.data.source.localdata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.abhishek.findingfalcone.data.model.Planet;
import com.abhishek.findingfalcone.data.model.Vehicle;
import com.abhishek.findingfalcone.data.source.DataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 22/12/16.
 */

public class LocalDataSource implements DataSource {

    private static LocalDataSource INSTANCE;

    private DatabaseHelper mDbHelper;

    // Prevent direct instantiation.
    private LocalDataSource(@NonNull Context context) {
        mDbHelper = new DatabaseHelper(context);
    }

    public static LocalDataSource getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new LocalDataSource(context);
        }
        return INSTANCE;
    }


    @Override
    public void getVehicles(@NonNull LoadVehicleCallback callback) {
        List<Vehicle> vehicles = new ArrayList<>();
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                DatabaseColumns.VehicleColumn.COLUMN_VEHICLE_NAME,
                DatabaseColumns.VehicleColumn.COLUMN_VEHICLE_SPEED,
                DatabaseColumns.VehicleColumn.COLUMN_VEHICLE_DISTANCE,
                DatabaseColumns.VehicleColumn.COLUMN_VEHICLE_COUNT
        };

        Cursor c = db.query(DatabaseColumns.VehicleColumn.TABLE_NAME, projection, null, null, null, null, null);

        if (c != null && c.getCount() > 0) {
            while (c.moveToNext()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setVehicle_name(c.getString(c.getColumnIndexOrThrow(DatabaseColumns.VehicleColumn.COLUMN_VEHICLE_NAME)));
                vehicle.setVehicle_speed(c.getInt(c.getColumnIndexOrThrow(DatabaseColumns.VehicleColumn.COLUMN_VEHICLE_SPEED)));
                vehicle.setTotal_number(c.getInt(c.getColumnIndexOrThrow(DatabaseColumns.VehicleColumn.COLUMN_VEHICLE_COUNT)));
                vehicle.setTotal_distance(c.getInt(c.getColumnIndexOrThrow(DatabaseColumns.VehicleColumn.COLUMN_VEHICLE_DISTANCE)));
                vehicles.add(vehicle);
            }
        }
        if (c != null) {
            c.close();
        }

        db.close();

        if (vehicles.isEmpty()) {
            // This will be called if the table is new or just empty.
            callback.onDataNotAvailable();
        } else {
            callback.onTasksLoaded(vehicles);
        }
    }

    @Override
    public void saveVehicle(List<Vehicle> vehicles) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        for(Vehicle vehicle : vehicles){
            ContentValues values = new ContentValues();
            values.put(DatabaseColumns.VehicleColumn.COLUMN_VEHICLE_NAME,vehicle.getVehicle_name());
            values.put(DatabaseColumns.VehicleColumn.COLUMN_VEHICLE_COUNT,vehicle.getTotal_number());
            values.put(DatabaseColumns.VehicleColumn.COLUMN_VEHICLE_DISTANCE,vehicle.getTotal_distance());
            values.put(DatabaseColumns.VehicleColumn.COLUMN_VEHICLE_SPEED,vehicle.getVehicle_speed());

            db.beginTransaction();
            db.insertWithOnConflict(DatabaseColumns.VehicleColumn.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
            db.setTransactionSuccessful();
            db.endTransaction();
        }
        db.close();
    }

    @Override
    public void getPlanets(@NonNull DataSource.LoadPlanetCallback callback) {

        List<Planet> planets = new ArrayList<>();
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                DatabaseColumns.PlanetColumn.COLUMN_PLANET,
                DatabaseColumns.PlanetColumn.COLUMN_PLANET_DISTANCE,
        };

        Cursor c = db.query(DatabaseColumns.PlanetColumn.TABLE_NAME, projection, null, null, null, null, null);

        if (c != null && c.getCount() > 0) {
            while (c.moveToNext()) {
                Planet planet = new Planet();
                planet.setPlanet(c.getString(c.getColumnIndexOrThrow(DatabaseColumns.PlanetColumn.COLUMN_PLANET)));
                planet.setdistance(c.getInt(c.getColumnIndexOrThrow(DatabaseColumns.PlanetColumn.COLUMN_PLANET_DISTANCE)));
                planets.add(planet);
            }
        }
        if (c != null) {
            c.close();
        }

        db.close();

        if (planets.isEmpty()) {
            // This will be called if the table is new or just empty.
            callback.onDataNotAvailable();
        } else {
            callback.onTasksLoaded(planets);
        }
    }

    @Override
    public void savePlanets(List<Planet> planets) {

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        for(Planet planet : planets){
            ContentValues values = new ContentValues();
            values.put(DatabaseColumns.PlanetColumn.COLUMN_PLANET,planet.getPlanet());
            values.put(DatabaseColumns.PlanetColumn.COLUMN_PLANET_DISTANCE,planet.getdistance());

            db.beginTransaction();
            db.insertWithOnConflict(DatabaseColumns.PlanetColumn.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
            db.setTransactionSuccessful();
            db.endTransaction();
        }
        db.close();
    }
}
