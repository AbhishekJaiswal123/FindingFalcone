package com.abhishek.findingfalcone.ui.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.abhishek.findingfalcone.R;

/**
 * Created by abhishek on 22/12/16.
 */

public class SplashActivity extends AppCompatActivity implements SplashContract.View {


    private SplashContract.Presenter mPresenter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        progressBar = (ProgressBar)findViewById(R.id.progress_pv);
        mPresenter = new SplashPresenter(SplashActivity.this,SplashActivity.this);
        mPresenter.start();
    }

    @Override
    public void setPresenter(SplashContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
        mPresenter.navigateToHome(SplashActivity.this);
        finish();

    }


}
