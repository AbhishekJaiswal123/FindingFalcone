package com.abhishek.findingfalcone.data.source;

import org.json.JSONObject;

/**
 * Created by abhishek on 22/12/16.
 */

public interface RemoteDataSource {


    interface onDataCallback {

        void onTasksLoaded(String response);

        void onError();

    }


    void fetchPlanetData(String url,onDataCallback callback);
    void fetchVehicleData(String url,onDataCallback callback);
    void fetchToken(String url,onDataCallback callback);
    void findQueen(String url, JSONObject data, onDataCallback callback);


}
