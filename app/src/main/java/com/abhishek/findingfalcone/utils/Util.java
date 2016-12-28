package com.abhishek.findingfalcone.utils;

import com.abhishek.findingfalcone.app.App;
import com.abhishek.findingfalcone.data.model.Planet;
import com.abhishek.findingfalcone.data.model.Vehicle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Stack;

/**
 * Created by abhishek on 28/12/16.
 */

public class Util {

    public static JSONObject createJsonObject(Stack<Planet> selectedPlanet, Stack<Vehicle> selectedVehicle){

        JSONObject obj = new JSONObject();
        try {
            obj.put("token", App.getPref().getString("token",""));
            obj.put("planet_names",getPatientArray(selectedPlanet));
            obj.put("vehicle_names",getVehicleArray(selectedVehicle));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj;
    }

    private static JSONArray getVehicleArray(Stack<Vehicle> selectedVehicle){
        JSONArray array = new JSONArray();
        for(Vehicle vehicle : selectedVehicle){
            array.put(vehicle.getVehicle_name());
        }
        return array;

    }

    private static JSONArray getPatientArray(Stack<Planet> selectedPlanet){
        JSONArray array = new JSONArray();
        for(Planet planet : selectedPlanet){
            array.put(planet.getPlanet());
        }
        return array;

    }

    public static  int getTotalTime(Stack<Vehicle> selectedVehicle,Stack<Planet> selectedPlanets){

        int total = 0;
        int i = 0;
        while(i < selectedPlanets.size()){
            int planetDistance = selectedPlanets.get(i).getdistance();
            int vehicleSpeed = selectedVehicle.get(i).getVehicle_speed();

            int timeTaken = planetDistance/vehicleSpeed;
            total += timeTaken;
            i++;
        }

        return  total;

    }
}
