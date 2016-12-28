package com.abhishek.findingfalcone.data.source;

import android.support.annotation.NonNull;

import com.abhishek.findingfalcone.data.model.Planet;
import com.abhishek.findingfalcone.data.model.Vehicle;

import java.util.List;

/**
 * Created by abhishek on 22/12/16.
 */

public interface DataSource {

    interface LoadVehicleCallback {

        void onTasksLoaded(List<Vehicle> vehicles);

        void onDataNotAvailable();
    }

    interface LoadPlanetCallback {

        void onTasksLoaded(List<Planet> planets);

        void onDataNotAvailable();
    }

    void getVehicles(@NonNull LoadVehicleCallback callback);
    void saveVehicle(List<Vehicle> vehicles);

    void getPlanets(@NonNull LoadPlanetCallback callback);
    void savePlanets(List<Planet> vehicles);
}
