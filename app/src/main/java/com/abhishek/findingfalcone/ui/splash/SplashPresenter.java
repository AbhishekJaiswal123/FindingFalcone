package com.abhishek.findingfalcone.ui.splash;

import android.content.Context;
import android.content.Intent;

import com.abhishek.findingfalcone.data.source.RemoteDataSource;
import com.abhishek.findingfalcone.data.source.remotedata.RemoteData;
import com.abhishek.findingfalcone.ui.home.HomeActivity;
import com.abhishek.findingfalcone.utils.Constants;

/**
 * Created by abhishek on 22/12/16.
 */

public class SplashPresenter implements SplashContract.Presenter{

    private SplashContract.View mSplashView;
    private RemoteDataSource mDataRepository;
    private int count = 1;

    public SplashPresenter(SplashContract.View splashView, Context context){
        mDataRepository = new RemoteData(context);
        mSplashView = splashView;
        mSplashView.setPresenter(this);
    }

    @Override
    public void start() {
        mDataRepository.fetchPlanetData(Constants.PLANET_URL,new RemoteDataSource.onDataCallback() {
            @Override
            public void onTasksLoaded(String response) {
                if(count > 1)
                  mSplashView.hideProgress();

                else
                    count++;
            }

            @Override
            public void onError() {

            }
        });

        mDataRepository.fetchVehicleData(Constants.VEHICLE_URL,new RemoteDataSource.onDataCallback() {
            @Override
            public void onTasksLoaded(String response) {
                if(count > 1)
                   mSplashView.hideProgress();
                else
                    count++;
            }

            @Override
            public void onError() {

            }
        });

        mDataRepository.fetchToken(Constants.TOKEN_URL, new RemoteDataSource.onDataCallback() {
            @Override
            public void onTasksLoaded(String response) {

            }

            @Override
            public void onError() {

            }
        });
    }

    @Override
    public void navigateToHome(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);

    }
}
