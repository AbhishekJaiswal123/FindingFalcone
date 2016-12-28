package com.abhishek.findingfalcone.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by abhishek on 22/12/16.
 */

public class Planet {

    @SerializedName("name")
    private String planet_name;

    @SerializedName("distance")
    private Integer planet_distance;

    private boolean selected = false;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String  showPlanet(){
        return "Planet:"+planet_name+" distance:"+planet_distance;
    }


    public String getPlanet() {
        return planet_name;
    }

    public void setPlanet(String planet_name) {
        this.planet_name = planet_name;
    }

    public Integer getdistance() {
        return planet_distance;
    }

    public void setdistance(Integer planet_distance) {
        this.planet_distance = planet_distance;
    }
}
