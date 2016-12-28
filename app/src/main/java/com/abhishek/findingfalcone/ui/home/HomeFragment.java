package com.abhishek.findingfalcone.ui.home;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.abhishek.findingfalcone.R;
import com.abhishek.findingfalcone.adapter.AutoCompleteAdapter;
import com.abhishek.findingfalcone.adapter.VehicleAdapter;
import com.abhishek.findingfalcone.data.model.Planet;
import com.abhishek.findingfalcone.data.model.Vehicle;
import com.abhishek.findingfalcone.ui.result.ResultActivity;

import java.util.List;

/**
 * Created by abhishek on 22/12/16.
 */

public class HomeFragment extends Fragment implements HomeContract.FragmentView {

    private static final String TAG = "HomeFragment";
    private AppCompatAutoCompleteTextView autoCompleteTextView;
    private RecyclerView vehicleList;
    private HomeContract.FragmentPresenter presenter;
    private HomeActivity parentActivity;
    private VehicleAdapter vehicleAdapter;
    private AppCompatButton nextBtn;
    public  Vehicle mFinalSelectedVehicle;
    public  Vehicle mSelectedVehicle;
    private Planet mSelectedPlanet;
    private ProgressDialog progressDialog;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        presenter = new HomeFragmentPresenter(this,getActivity());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach");
        parentActivity = (HomeActivity)context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        autoCompleteTextView = (AppCompatAutoCompleteTextView)view.findViewById(R.id.autocomplete_planet_atv);
        vehicleList = (RecyclerView)view.findViewById(R.id.vehicle_list_rv);
        nextBtn = (AppCompatButton)view.findViewById(R.id.next_btn);
        presenter.initData();
        return view;
    }


    @Override
    public void setData() {
        AutoCompleteAdapter adapter = new AutoCompleteAdapter(getActivity(),parentActivity.mPlanetList);
        autoCompleteTextView.setDropDownAnchor(R.id.autocomplete_planet_atv);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Planet planet = (Planet) adapterView.getItemAtPosition(i);
                mSelectedPlanet = planet;
                autoCompleteTextView.setText(planet.getPlanet());
                presenter.showVehicles(planet,parentActivity.mVehicleList);
            }
        });

        autoCompleteTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View arg0) {
                autoCompleteTextView.showDropDown();
            }
        });

        vehicleList.setLayoutManager(new LinearLayoutManager(getActivity()));
        vehicleAdapter = new VehicleAdapter(getActivity(),HomeFragment.this);
        vehicleList.setAdapter(vehicleAdapter);
        presenter.showVehiclesBasedOnCount(parentActivity.mVehicleList);

        vehicleAdapter.setOnVehicleClickListener(new VehicleAdapter.onVehicleClickListener() {
            @Override
            public void onVehicleClick(Vehicle vehicle) {
                mSelectedVehicle = vehicle;
            }
        });

        if(parentActivity.selectedPlanet.size() >= 3){
            nextBtn.setText("Find Falcone");
        }

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    if (mSelectedPlanet != null && mSelectedVehicle != null) {
                        mSelectedPlanet.setSelected(true);
                        mSelectedVehicle.setTotal_number(mSelectedVehicle.getTotal_number()-1);

                        parentActivity.selectedPlanet.push(mSelectedPlanet);
                        parentActivity.selectedVehicle.push(mSelectedVehicle);
                        mFinalSelectedVehicle = mSelectedVehicle;

                        if (parentActivity.selectedPlanet.size() >= 4) {

                            presenter.findFalcone(parentActivity.selectedPlanet,parentActivity.selectedVehicle);

                        } else {
                            if(mSelectedVehicle.getTotal_number() == 0){
                                mSelectedVehicle.setEnable(false);
                            }
                            parentActivity.mPresenter.moveToNextStep();
                        }
                    } else {
                        Toast.makeText(getActivity(), "Select planet/Vehicle from list", Toast.LENGTH_SHORT).show();
                    }
            }
        });
    }

    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Finding Falcone");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        if(progressDialog.isShowing())
            progressDialog.dismiss();
    }

    @Override
    public void setVechiclesData(List<Vehicle> vehicles) {
        vehicleAdapter.setData(vehicles);
    }

    @Override
    public void showResult(String response,int time) {

        Intent intent = new Intent(getActivity(), ResultActivity.class);
        intent.putExtra("result",response.toString());
        intent.putExtra("time",String.valueOf(time));
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void setPresenter(HomeContract.FragmentPresenter presenter) {
        this.presenter = presenter;
    }


}
