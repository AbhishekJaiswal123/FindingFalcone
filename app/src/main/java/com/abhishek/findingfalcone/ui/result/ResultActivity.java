package com.abhishek.findingfalcone.ui.result;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.abhishek.findingfalcone.R;
import com.abhishek.findingfalcone.ui.home.HomeActivity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by abhishek on 27/12/16.
 */

public class ResultActivity extends AppCompatActivity implements View.OnClickListener,ResultContract.View {


    private Toolbar mToolbar;
    private TextView planetName;
    private TextView message;
    private TextView timeTaken;
    private Button playAgainBtn;

    private String mTime;
    private ResultContract.Presenter mPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        mToolbar = (Toolbar)findViewById(R.id.tool_bar);
        message = (TextView)findViewById(R.id.success_msg_tv);
        planetName = (TextView)findViewById(R.id.planet_found_tv);
        timeTaken = (TextView)findViewById(R.id.time_take_id);
        playAgainBtn = (Button)findViewById(R.id.play_again_btn);

        mPresenter = new ResultPresenter(ResultActivity.this);
        try {
            String data = getIntent().getStringExtra("result");
            mTime = getIntent().getStringExtra("time");
            mPresenter.initData(data);
        }catch (Exception e){
            mPresenter.initData("Some error occured!");
        }

        initToolbar();
        playAgainBtn.setOnClickListener(this);




    }

    public void initToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Result");
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(ResultActivity.this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public void setData(String result) {
        try {
            JSONObject obj = new JSONObject(result);
            String status = obj.optString("status");
            if(status.equalsIgnoreCase("success")){
                String planet = obj.optString("planet_name");
                message.setText("Success! Congratulation on finding falcone. King shah is mighty pleased.");
                planetName.setText("Planet found: "+planet);
                timeTaken.setText("Time taken:"+mTime);
            }else{
                message.setText("Ahh! Failure on finding falcone. King shah is not pleased.");
                planetName.setVisibility(View.GONE);
                timeTaken.setVisibility(View.GONE);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setPresenter(ResultContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
