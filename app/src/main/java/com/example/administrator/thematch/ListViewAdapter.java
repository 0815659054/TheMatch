package com.example.administrator.thematch;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.thematch.models.TeamModel;

import java.util.List;

/**
 * Created by NgocTri on 10/22/2016.
 */

public class ListViewAdapter extends ArrayAdapter<TeamModel> {
    public ListViewAdapter(Context context, int resource, List<TeamModel> objects) {
        super(context, resource, objects);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if(null == v) {
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_item, null);

        }
        TeamModel team = getItem(position);
        ImageView img = (ImageView) v.findViewById(R.id.imageView);
        TextView txtTitle = (TextView) v.findViewById(R.id.txtTitle);
        TextView txtDescription = (TextView) v.findViewById(R.id.txtDescription);

        Glide.with(v.getContext())
                .load(team.image_url)
                .into(img);
        txtTitle.setText(team.name);
        txtDescription.setText(team.stadium);

        return v;
    }
}
