package com.abhishek.findingfalcone.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.abhishek.findingfalcone.R;
import com.abhishek.findingfalcone.data.model.Planet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 23/12/16.
 */

public class AutoCompleteAdapter extends BaseAdapter implements Filterable {

    Context mContext;
    List<Planet> planets;
    private List<Planet> suggestions = new ArrayList<>();
    private Filter filter = new CustomFilter();


    public AutoCompleteAdapter(Context context, List<Planet> objects) {
        mContext = context;
        planets = objects;
        for(Planet planet : planets){
            if(!planet.isSelected()){
                suggestions.add(planet);
            }
        }
    }


    @Override
    public int getCount() {
        return suggestions.size();
    }

    @Override
    public Planet getItem(int index) {
        return suggestions.get(index);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Planet planet = getItem(position);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item_planet, parent, false);
        }

            ViewHolder holder;
            holder = new ViewHolder();
            holder.textView = (TextView) convertView.findViewById(R.id.planet_name_tv);

        holder.textView.setText(planet.getPlanet());

        return convertView;
    }


    @Override
    public Filter getFilter() {
        return filter;
    }


    private class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            suggestions.clear();

            if (planets != null && constraint != null) {
                for (int i = 0; i < planets.size(); i++) {
                    if (planets.get(i).getPlanet().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        if(!planets.get(i).isSelected())
                           suggestions.add(planets.get(i));
                    }
                }
            }else{
                for(Planet planet : planets){
                    if(!planet.isSelected()){
                        suggestions.add(planet);
                    }
                }

            }
            FilterResults results = new FilterResults();
            results.values = suggestions;
            results.count = suggestions.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }
    }
    class ViewHolder{
        TextView textView;
    }
}
