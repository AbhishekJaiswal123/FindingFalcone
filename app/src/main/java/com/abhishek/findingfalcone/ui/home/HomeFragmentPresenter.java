package com.abhishek.findingfalcone.ui.home;

import android.content.Context;

import com.abhishek.findingfalcone.data.model.Planet;
import com.abhishek.findingfalcone.data.model.Vehicle;
import com.abhishek.findingfalcone.data.source.RemoteDataSource;
import com.abhishek.findingfalcone.data.source.remotedata.RemoteData;
import com.abhishek.findingfalcone.utils.Constants;
import com.abhishek.findingfalcone.utils.Util;

import java.util.List;
import java.util.Stack;

/**
 * Created by abhishek on 22/12/16.
 */

public class HomeFragmentPresenter implements HomeContract.FragmentPresenter{


    private static final String TAG = "HomePresenter";
    private HomeContract.FragmentView mFragView;
    private RemoteDataSource mDataRepository;


    public HomeFragmentPresenter(HomeContract.FragmentView view, Context context){
        mDataRepository = new RemoteData(context);
        mFragView = view;
        mFragView.setPresenter(this);
    }



    @Override
    public void start() {

    }

    @Override
    public void initData() {
        mFragView.setData();
    }

    @Override
    public void showVehiclesBasedOnCount(List<Vehicle> vehicles) {

        for(Vehicle vehicle : vehicles) {

                if(vehicle.getTotal_number() <= 0)
                    vehicle.setEnable(false);
                else
                    vehicle.setEnable(true);
        }
        mFragView.setVechiclesData(vehicles);

    }

    @Override
    public void showVehicles(Planet planet, List<Vehicle> vehicles) {

        for(Vehicle vehicle : vehicles) {

            if (vehicle.getTotal_distance() < planet.getdistance()){
                vehicle.setEnable(false);
            }  else{
                if(vehicle.getTotal_number() == 0)
                    vehicle.setEnable(false);
                else
                    vehicle.setEnable(true);

            }
        }
        mFragView.setVechiclesData(vehicles);
    }

    @Override
    public void findFalcone(final Stack<Planet> selectedPlanet, final Stack<Vehicle> selectedVehicle) {

        mFragView.showProgress();

        mDataRepository.findQueen(Constants.FIND_URL, Util.createJsonObject(selectedPlanet, selectedVehicle), new RemoteDataSource.onDataCallback() {
            @Override
            public void onTasksLoaded(String response) {
                mFragView.showResult(response,Util.getTotalTime(selectedVehicle,selectedPlanet));
                mFragView.hideProgress();
            }

            @Override
            public void onError() {
                mFragView.hideProgress();
            }
        });
    }





}
