package com.example.company.sabborah.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.company.sabborah.R;
import com.example.company.sabborah.models.Country;
import com.example.company.sabborah.responses.CountryResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohamed Sayed on 1/29/2018.
 */

public class CountryAdapter extends ArrayAdapter<Country> {
    public CountryAdapter(Context ctx) {

        super(ctx, 0);
    }

    public void setAdapterData(List<Country> countries) {
        this.addAll(countries);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Country country = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.country_layout, parent, false);

        TextView nameTV = (TextView) convertView.findViewById(R.id.countryNameTV);
        nameTV.setText(country.toString());

        return convertView;
    }
}
