package com.example.realtimelocationwithfirebase;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Geolocation> {

    private Activity context;
    private List<Geolocation> GeolocationLists;

    public CustomAdapter(Activity context, List<Geolocation> GeolocationLists) {
        super(context, R.layout.samplelayout, GeolocationLists);
        this.context = context;
        this.GeolocationLists = GeolocationLists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.samplelayout, null, true );

        Geolocation Geolocation = GeolocationLists.get(position);

        TextView t1 = view.findViewById(R.id.tvSampleName);
        TextView t2 = view.findViewById(R.id.tvSampleAge);

        t1.setText(Geolocation.getLatitude());
        t2.setText(Geolocation.getLongitude());


        return view;
    }
}
