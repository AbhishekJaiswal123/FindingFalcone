package com.abhishek.findingfalcone.data.source.localdata;

import android.provider.BaseColumns;

/**
 * Created by abhishek on 22/12/16.
 */


public final class DatabaseColumns {

    private DatabaseColumns(){}

    public static abstract class PlanetColumn implements BaseColumns{
        public static final String TABLE_NAME = "planet";
        public static final String COLUMN_PLANET = "planet_name";
        public static final String COLUMN_PLANET_DISTANCE = "planet_distance";
    }

    public static abstract class VehicleColumn implements BaseColumns{
        public static final String TABLE_NAME = "vehicle";
        public static final String COLUMN_VEHICLE_NAME = "vehicle_name";
        public static final String COLUMN_VEHICLE_COUNT = "total_number";
        public static final String COLUMN_VEHICLE_DISTANCE = "total_distance";
        public static final String COLUMN_VEHICLE_SPEED = "vehicle_speed";

    }
}
