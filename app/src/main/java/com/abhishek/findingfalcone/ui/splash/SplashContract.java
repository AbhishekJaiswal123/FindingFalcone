package com.abhishek.findingfalcone.ui.splash;

import android.content.Context;

import com.abhishek.findingfalcone.base.BasePresenter;
import com.abhishek.findingfalcone.base.BaseView;

/**
 * Created by abhishek on 22/12/16.
 */

public interface SplashContract {

    interface View extends BaseView<Presenter>{

        void hideProgress();

    }

    interface  Presenter extends BasePresenter{

        void navigateToHome(Context context);
    }
}
