package com.abhishek.findingfalcone.ui.result;

/**
 * Created by abhishek on 22/12/16.
 */

public class ResultPresenter implements ResultContract.Presenter{

    private ResultContract.View mResultView;

    public ResultPresenter(ResultContract.View resultView){

        mResultView = resultView;
        mResultView.setPresenter(this);
    }

    @Override
    public void start() {
    }


    @Override
    public void initData(String data) {
        mResultView.setData(data);
    }
}
