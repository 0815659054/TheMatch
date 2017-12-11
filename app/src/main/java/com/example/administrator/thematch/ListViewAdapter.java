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
import com.example.administrator.thematch.services.AddTeamSubService;
import com.example.administrator.thematch.services.RemoveTeamSubService;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.List;

/**
 * Created by NgocTri on 10/22/2016.
 */

public class ListViewAdapter extends ArrayAdapter<TeamModel> {

    private AddTeamSubService addTeamSubService = new AddTeamSubService();
    private RemoveTeamSubService removeTeamSubService = new RemoveTeamSubService();

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
        final TeamModel team = getItem(position);
        ImageView img = (ImageView) v.findViewById(R.id.imageView);
        ImageView subscribeImageView = (ImageView) v.findViewById(R.id.favorite_team_image);
        TextView txtTitle = (TextView) v.findViewById(R.id.txtTitle);
        TextView txtDescription = (TextView) v.findViewById(R.id.txtDescription);

        Glide.with(v.getContext())
                .load(team.image_url)
                .into(img);
        txtTitle.setText(team.name);
        txtDescription.setText(team.stadium);
        if (team.isSubscribed) {
            subscribeImageView.setImageResource(R.drawable.true1);
        } else {
            subscribeImageView.setImageResource(R.drawable.false1);
        }
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (team.isSubscribed) {
                    removeTeamSubService.removeSubscription(team.id, FirebaseInstanceId.getInstance().getToken());
                } else {
                    addTeamSubService.addSubscription(team.id, FirebaseInstanceId.getInstance().getToken());
                }
                team.isSubscribed = !team.isSubscribed;
                notifyDataSetChanged();
            }
        });

        return v;
    }
}
