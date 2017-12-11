package com.example.administrator.thematch;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 4/15/2017.
 */

public class ActivityThree extends AppCompatActivity implements  AdapterView.OnItemClickListener{

    private ListView mListView;

    private List<com.example.administrator.thematch.Data> datas = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);

        mListView = (ListView) findViewById(R.id.listView);
        ArrayList<Card> list = new ArrayList<>();

        list.add(new Card("drawable://" + R.drawable.arizona_dessert, "Arizona Dessert","1"));
        list.add(new Card("drawable://" + R.drawable.bamf1, "Bamf","2"));
        list.add(new Card("drawable://" + R.drawable.colorado_mountains, "Colorado Mountains","3"));
        list.add(new Card("drawable://" + R.drawable.cork, "Cork","4"));
        list.add(new Card("drawable://" + R.drawable.davenport_california, "DavenPort California","5"));
        list.add(new Card("drawable://" + R.drawable.denmark_austrailia, "Denmark Austrailia",""));
        list.add(new Card("drawable://" + R.drawable.foggy_iceland, "Foggy Iceland",""));
        list.add(new Card("drawable://" + R.drawable.havasu_falls, "Havasu Falls",""));
        list.add(new Card("drawable://" + R.drawable.hawaii_rainforest, "Hawaii RainForest",""));
        list.add(new Card("drawable://" + R.drawable.newfoundland_ice, "NewFoundLand Ice",""));
        list.add(new Card("drawable://" + R.drawable.oregon_greens, "Oregon Greens",""));
        list.add(new Card("drawable://" + R.drawable.smokey_mountain, "Smokey Mountain",""));
        list.add(new Card("drawable://" + R.drawable.yosemite, "Yosemite",""));


        CustomListAdapter adapter = new CustomListAdapter(getApplicationContext(), R.layout.card_layout_main, list);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);
//        datas.add(new com.example.administrator.thematch.Data("Hungry Emoji Icon", "Hungry", R.drawable.p1));
//        datas.add(new com.example.administrator.thematch.Data("Smiling Emoji Icon", "Smiling", R.drawable.p2));
//        datas.add(new com.example.administrator.thematch.Data("Unamused face Emoji Icon", "unamused face", R.drawable.p3));
//        datas.add(new com.example.administrator.thematch.Data("Crying Emoji Icon", "Crying", R.drawable.p4));
//        datas.add(new com.example.administrator.thematch.Data("Angry Emoji Icon", "Angry", R.drawable.p5));
//        datas.add(new com.example.administrator.thematch.Data("Pinocchio Emoji Icon", "Pinocchio", R.drawable.s1));
//        datas.add(new com.example.administrator.thematch.Data("Sick Emoji", "Sick", R.drawable.s2));
//        datas.add(new com.example.administrator.thematch.Data("Sneezing Emoji Icon", "Sneezing", R.drawable.s3));
//        datas.add(new com.example.administrator.thematch.Data("Sick Emoji", "Sick", R.drawable.s4));
//        datas.add(new com.example.administrator.thematch.Data("Poisoned Emoji Icon", "Poisoned", R.drawable.s5));
//
//        MyAdapter adapter = new MyAdapter(this,datas);
//
//        ListView lv = (ListView) findViewById(R.id.listView);
//        lv.setAdapter(adapter);
//        lv.setOnItemClickListener(this);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_arrow:
                        Intent intent0 = new Intent(ActivityThree.this, Main.class);
                        startActivity(intent0);
                        break;

                    case R.id.ic_android:
                        Intent intent1 = new Intent(ActivityThree.this, ActivityOne.class);
                        startActivity(intent1);
                        break;

                    case R.id.ic_books:
                        Intent intent2 = new Intent(ActivityThree.this, ActivityTwo.class);
                        startActivity(intent2);
                        break;

                    case R.id.ic_center_focus:

                        break;

                    case R.id.ic_backup:
                        Intent intent4 = new Intent(ActivityThree.this, ActivityFour.class);
                        startActivity(intent4);
                        break;
                }


                return false;
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View v, int position, long l){
        Toast.makeText(this, "Click "+ String.valueOf(position)+" "+datas.get(position).getmText1(),Toast.LENGTH_SHORT).show();
        Log.d("Click ", "onItemClick: " +datas.get(position).getmText1());
    }
}
