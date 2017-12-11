package com.example.administrator.thematch;



import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class Tab0Fragment extends Fragment  {
    private static final String TAG = "Tab1Fragment";
    private ArrayList<String> data = new ArrayList<String>();
//    private Button btnTEST;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment0_layout,container,false);


        ListView lv = (ListView)view.findViewById(R.id.list);
        generateListContent();
        lv.setAdapter(new MyListAdaper(getActivity(), R.layout.list_item_tab0fragment, data));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "List item was clicked at " + position, Toast.LENGTH_SHORT).show();
            }
        });


//        btnTEST = (Button) view.findViewById(R.id.btnTEST);
//
//        btnTEST.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getActivity(), "TESTING BUTTON CLICK 1",Toast.LENGTH_SHORT).show();
//            }
//        });

        return view;
    }


    private void generateListContent() {
        for(int i = 0; i < 55; i++) {
            data.add("This is row number " + i);
        }
    }



    class MyListAdaper extends ArrayAdapter<String> {
        private int layout;
        private List<String> mObjects;
        private MyListAdaper(Context context, int resource, List<String> objects) {
            super(context, resource, objects);
            mObjects = objects;
            layout = resource;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder mainViewholder = null;
            if(convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout, parent, false);
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.thumbnail = (ImageView) convertView.findViewById(R.id.list_item_thumbnail);
                viewHolder.title = (TextView) convertView.findViewById(R.id.list_item_text1);
                convertView.setTag(viewHolder);
            }
            return convertView;
        }
    }
    public class ViewHolder {

        ImageView thumbnail;
        TextView title;
    }
}