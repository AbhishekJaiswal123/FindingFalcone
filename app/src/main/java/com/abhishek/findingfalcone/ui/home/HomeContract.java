package com.abhishek.findingfalcone.ui.home;

import com.abhishek.findingfalcone.base.BasePresenter;
import com.abhishek.findingfalcone.base.BaseView;
import com.abhishek.findingfalcone.data.model.Planet;
import com.abhishek.findingfalcone.data.model.Vehicle;

import java.util.List;
import java.util.Stack;

/**
 * Created by abhishek on 22/12/16.
 */

public interface HomeContract {


     interface ActivityView extends BaseView<Presenter>{

         void replaceFragment();
         void loadFragment();
         void initPlanetData(List<Planet> planets);
         void initVehicleData(List<Vehicle> vehicles);
         void initToolbar();


    }

     interface Presenter extends BasePresenter{

        void getData();
        void initToolbar();
        void moveToNextStep();

    }

     interface FragmentView extends BaseView<FragmentPresenter>{

        void setData();
        void showProgress();
        void hideProgress();
        void setVechiclesData(List<Vehicle> vehicles);
        void showResult(String response,int time);
    }

     interface FragmentPresenter extends BasePresenter{

        void initData();
        void showVehiclesBasedOnCount(List<Vehicle> vehicles);
        void showVehicles(Planet planet,List<Vehicle> vehicles);
        void findFalcone(Stack<Planet> selectedPlanet,Stack<Vehicle> selectedVehicle);
    }



}
