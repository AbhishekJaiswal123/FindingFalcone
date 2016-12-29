package com.abhishek.findingfalcone.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.abhishek.findingfalcone.R;
import com.abhishek.findingfalcone.data.model.Vehicle;
import com.abhishek.findingfalcone.ui.home.HomeFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 23/12/16.
 */

public class VehicleAdapter  extends RecyclerView.Adapter<VehicleAdapter.ViewHolder> {


    private static final String TAG = "VehicleAdapter";
    private Context mContext;
    private List<Vehicle> vehicles = new ArrayList<>();
    private onVehicleClickListener mOnVehicleClickListener;
    private AppCompatRadioButton lastCheckedRB = null;
    private HomeFragment homeFragment;




    public VehicleAdapter(Context pContext, HomeFragment fragment){
        mContext = pContext;
        this.homeFragment = fragment;
    }

    public void setData(List<Vehicle> vehicles){
        this.vehicles = vehicles;
        notifyDataSetChanged();
    }

    public void setOnVehicleClickListener(onVehicleClickListener onVehicleClickListener){
        this.mOnVehicleClickListener = onVehicleClickListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_vehicle,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return vehicles.size();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {



       final Vehicle model = vehicles.get(position);
        holder.vehicle.setText(model.getVehicle_name());
        holder.vehicleCount.setText(String.valueOf(model.getTotal_number()));

        if(!model.isEnable() || model.getTotal_number() < 0){
            if(model.getTotal_number() < 0){
                holder.vehicleCount.setText(0+"");
            }

            holder.vehicleCount.setEnabled(false);
            holder.vehicle.setEnabled(false);
        }else{
            holder.vehicle.setEnabled(true);

        }
        if(homeFragment.mFinalSelectedVehicle != null){
            if(model.getVehicle_name().equalsIgnoreCase(homeFragment.mFinalSelectedVehicle.getVehicle_name())){
                lastCheckedRB = holder.vehicle;
                holder.vehicle.setChecked(true);
            }
        }

        holder.vehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnVehicleClickListener.onVehicleClick(model);
            }
        });

        holder.vehicle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                AppCompatRadioButton checked_rb = (AppCompatRadioButton) compoundButton.findViewById(R.id.vehicle_rbtn);
                if (lastCheckedRB != null) {
                    lastCheckedRB.setChecked(false);
                }
                //store the clicked radiobutton
                lastCheckedRB = checked_rb;


            }
        });
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {

        private AppCompatRadioButton vehicle;
        private TextView vehicleCount;

        public ViewHolder(View itemView) {
            super(itemView);
            vehicleCount = (TextView)itemView.findViewById(R.id.vehicle_count_tv);
            vehicle = (AppCompatRadioButton)itemView.findViewById(R.id.vehicle_rbtn);
        }
    }

    public interface onVehicleClickListener{
              void onVehicleClick(Vehicle vehicle);
    }
}
