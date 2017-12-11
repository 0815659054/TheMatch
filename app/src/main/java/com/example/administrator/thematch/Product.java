package com.example.administrator.thematch;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static java.lang.Boolean.getBoolean;

/**
 * Created by NgocTri on 10/22/2016.
 */

public class Product {
    private int imageId;
    private String title;
    private String description;
    private String subscribeName;
    private Boolean subscribe;
    Context mContext;

    private final String NAME_FILE_SETTING = "setting_notification";
    private SharedPreferences settings;
    SharedPreferences.Editor editor;

    public Product(int imageId, String title, String description,boolean subscribe,String subscribeName) {
        this.imageId = imageId;
        this.title = title;
        this.description = description;
        this.subscribe = subscribe ;
        this.subscribeName = subscribeName;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Boolean subscribe) {
        this.subscribe = subscribe;
    }

    public String getSubscribeName() {
        return subscribeName;
    }

    public void setSubscribeName(String subscribeName) {
        this.subscribeName = subscribeName;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = mInflater.inflate(R.layout.activity_two, viewGroup, false);
            holder = new ViewHolder();
            view.setTag(holder);
        }
//        holder.favorite_team = (ImageView) view.findViewById(R.id.favorite_team_image);
//        if(settings.getBoolean(subscribeName,false)){
//            Log.i("111",i+" "+subscribeName);
//            holder.favorite_team .setImageResource(R.drawable.true1);
//        }else {
//            Log.i("111",i+" "+subscribeName);
//            holder.favorite_team .setImageResource(R.drawable.false1);
//        }

//        holder.tv_info.setText(name.get(i));
//        holder.im_logo.setImageResource(iconadapter.get(i));
        // holder.favorite_team .setImageResource(R.drawable.true1);
        return view;
    }

    class ViewHolder {

        ImageView favorite_team;
    }


}
