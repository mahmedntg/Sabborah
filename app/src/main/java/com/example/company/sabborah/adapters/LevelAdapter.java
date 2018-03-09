package com.example.company.sabborah.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.company.sabborah.R;
import com.example.company.sabborah.responses.tutor.Level;

import java.util.List;

/**
 * Created by Mohamed Sayed on 1/29/2018.
 */

public class LevelAdapter extends ArrayAdapter<Level> {
    public LevelAdapter(Context ctx, List<Level> levels) {

        super(ctx, R.layout.level_layout, R.id.levelTV, levels);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Level level = getItem(position);
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.level_layout, parent, false);
        TextView nameTV = (TextView) convertView.findViewById(R.id.levelTV);
        nameTV.setText(level.getName());
        return convertView;
    }
}
