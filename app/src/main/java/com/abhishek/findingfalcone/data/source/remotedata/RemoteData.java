package com.abhishek.findingfalcone.data.source.remotedata;

import android.content.Context;
import android.util.Log;

import com.abhishek.findingfalcone.api.GetApi;
import com.abhishek.findingfalcone.api.PostApi;
import com.abhishek.findingfalcone.app.App;
import com.abhishek.findingfalcone.data.model.Planet;
import com.abhishek.findingfalcone.data.model.Vehicle;
import com.abhishek.findingfalcone.data.source.DataSource;
import com.abhishek.findingfalcone.data.source.RemoteDataSource;
import com.abhishek.findingfalcone.data.source.localdata.LocalDataSource;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by abhishek on 23/12/16.
 */

public class RemoteData implements RemoteDataSource {

    private static final String TAG = "RemoteData";
    private DataSource localDataSource;

    public RemoteData(Context context){
        localDataSource = LocalDataSource.getInstance(context);

    }

    @Override
    public void fetchPlanetData(String url, final onDataCallback callback) {
        GetApi api = new GetApi(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, "fetchPlanetData: "+response);
               Gson gson = new Gson();
               List<Planet> planets = gson.fromJson(response.toString(), new TypeToken<ArrayList<Planet>>() {}.getType());
               localDataSource.savePlanets(planets);
               callback.onTasksLoaded(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onError();
            }
        });

        App.getVolleyQueue().add(api);
    }

    @Override
    public void fetchVehicleData(String url,final onDataCallback callback) {

        GetApi api = new GetApi(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, "fetchVehicleData: "+response);
                Gson gson = new Gson();
                List<Vehicle> vehicles = gson.fromJson(response.toString(), new TypeToken<ArrayList<Vehicle>>() {}.getType());
                localDataSource.saveVehicle(vehicles);
                callback.onTasksLoaded(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onError();
            }
        });

        App.getVolleyQueue().add(api);
    }

    @Override
    public void fetchToken(String url,final onDataCallback callback) {
        JSONObject obj = new JSONObject();
        PostApi api = new PostApi(url, obj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "onResponse Token: "+response.toString());
                App.getPref().put("token",response.optString("token"));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onError();
            }
        });
        App.getVolleyQueue().add(api);
    }

    @Override
    public void findQueen(String url, JSONObject data,final onDataCallback callback) {

        PostApi api = new PostApi(url, data, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                callback.onTasksLoaded(response.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onError();
            }
        });
        App.getVolleyQueue().add(api);


    }


}
