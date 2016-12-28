package com.abhishek.findingfalcone.ui.home;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.abhishek.findingfalcone.R;
import com.abhishek.findingfalcone.base.BaseActivity;
import com.abhishek.findingfalcone.data.model.Planet;
import com.abhishek.findingfalcone.data.model.Vehicle;
import com.abhishek.findingfalcone.data.source.localdata.LocalDataSource;

import java.util.List;
import java.util.Stack;

/**
 * Created by abhishek on 22/12/16.
 */

public class HomeActivity extends BaseActivity implements HomeContract.ActivityView {


    private static final String TAG = "HomeActivity" ;
    protected HomeContract.Presenter mPresenter;
    protected List<Planet> mPlanetList;
    protected List<Vehicle> mVehicleList;
    private Toolbar mToolbar;
    protected Stack<Planet> selectedPlanet = new Stack<>();
    protected Stack<Vehicle> selectedVehicle = new Stack<>();
    private Menu menu;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mToolbar = (Toolbar)findViewById(R.id.tool_bar);
        mPresenter = new HomePresenter(this, LocalDataSource.getInstance(HomeActivity.this));
        mPresenter.getData();
        mPresenter.initToolbar();
        mPresenter.start();

    }


    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
         mPresenter = presenter;
    }

    @Override
    public void replaceFragment() {

        updateSteps();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.animator.slide_left_enter,
                R.animator.slide_left_exit,
                R.animator.slide_right_enter,
                R.animator.slide_right_exit);
        HomeFragment homeFragment = new HomeFragment();
        fragmentTransaction.replace(R.id.main_container, homeFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    @Override
    public void loadFragment() {

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        HomeFragment homeFragment = new HomeFragment();
        fragmentTransaction.replace(R.id.main_container, homeFragment);
        fragmentTransaction.commit();


    }

    private void updateSteps() {
        MenuItem homeMenuItem = menu.findItem(R.id.steps);
        int step = selectedPlanet.size()+1;
        homeMenuItem.setTitle("STEP "+step);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public void initPlanetData(List<Planet> planets) {
        mPlanetList = planets;
    }

    @Override
    public void initVehicleData(List<Vehicle> vehicles) {
        mVehicleList = vehicles;
    }

    @Override
    public void initToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Finding Falcone");
    }

    @Override
    public void onBackPressed() {

        if(selectedPlanet.size() > 0 && selectedVehicle.size() > 0 && selectedPlanet.size() == selectedVehicle.size()){
            Planet planet = selectedPlanet.pop();
            Vehicle vehicle = selectedVehicle.pop();

            vehicle.setTotal_number(vehicle.getTotal_number()+1);
            planet.setSelected(false);
            vehicle.setEnable(true);
            updateSteps();
        }
        super.onBackPressed();

    }
}
