package com.abhishek.findingfalcone.ui.home;

import com.abhishek.findingfalcone.data.model.Planet;
import com.abhishek.findingfalcone.data.model.Vehicle;
import com.abhishek.findingfalcone.data.source.DataSource;
import java.util.List;

/**
 * Created by abhishek on 22/12/16.
 */

public class HomePresenter implements HomeContract.Presenter{


    private static final String TAG = "HomePresenter";
    private HomeContract.ActivityView mHomeView;
    private DataSource dataSource;

    public HomePresenter(HomeContract.ActivityView view, DataSource pDataSource ){
        mHomeView = view;
        mHomeView.setPresenter(this);
        dataSource = pDataSource;
    }



    @Override
    public void start() {

        mHomeView.loadFragment();
    }

    @Override
    public void getData() {
        dataSource.getPlanets(new DataSource.LoadPlanetCallback() {
            @Override
            public void onTasksLoaded(List<Planet> planets) {
                mHomeView.initPlanetData(planets);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });

        dataSource.getVehicles(new DataSource.LoadVehicleCallback() {
            @Override
            public void onTasksLoaded(List<Vehicle> vehicles) {
                mHomeView.initVehicleData(vehicles);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    @Override
    public void initToolbar() {
        mHomeView.initToolbar();
    }

    @Override
    public void moveToNextStep() {
        mHomeView.replaceFragment();
    }

}
