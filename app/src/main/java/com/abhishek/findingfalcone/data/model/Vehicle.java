package com.abhishek.findingfalcone.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by abhishek on 22/12/16.
 */

public class Vehicle {

    @SerializedName("name")
    private String vehicle_name;

    @SerializedName("total_no")
    private Integer total_number;

    @SerializedName("max_distance")
    private Integer total_distance;



    @SerializedName("speed")
    private Integer vehicle_speed;


    private boolean isEnable = true ;

    public String  showVehicle(){
        return "Vehicle:"+vehicle_name+" Vehicle count:"+total_number+
                " Vehicle distance:"+ total_distance+" Vehicle speed: "+vehicle_speed;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }

    public String getVehicle_name() {
        return vehicle_name;
    }

    public void setVehicle_name(String vehicle_name) {
        this.vehicle_name = vehicle_name;
    }

    public Integer getTotal_number() {
        return total_number;
    }

    public void setTotal_number(Integer total_number) {
        this.total_number = total_number;
    }

    public Integer getTotal_distance() {
        return total_distance;
    }

    public void setTotal_distance(Integer total_distance) {
        this.total_distance = total_distance;
    }

    public Integer getVehicle_speed() {
        return vehicle_speed;
    }

    public void setVehicle_speed(Integer vehicle_speed) {
        this.vehicle_speed = vehicle_speed;
    }
}
