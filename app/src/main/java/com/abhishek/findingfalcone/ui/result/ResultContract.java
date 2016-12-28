package com.abhishek.findingfalcone.ui.result;

import com.abhishek.findingfalcone.base.BasePresenter;
import com.abhishek.findingfalcone.base.BaseView;

/**
 * Created by abhishek on 22/12/16.
 */

public interface ResultContract {

    interface View extends BaseView<Presenter>{

        void setData(String result);

    }

    interface  Presenter extends BasePresenter{

        void initData(String data);
    }
}
