package com.example.administrator.thematch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 4/15/2017.
 */

public class ActivityTwo extends AppCompatActivity {
    private ViewStub stubGrid;
    private ViewStub stubList;
    private ListView listView;
    private GridView gridView;
    private ListViewAdapter listViewAdapter;
    private GridViewAdapter gridViewAdapter;
    private List<Product> productList;
    private int currentViewMode = 0;
    static boolean check;
    static final int VIEW_MODE_LISTVIEW = 0;
    static final int VIEW_MODE_GRIDVIEW = 1;
    static boolean test;
    private final String NAME_FILE_SETTING = "setting_notification";
    private SharedPreferences settings;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        stubList = (ViewStub) findViewById(R.id.stub_list);
        stubGrid = (ViewStub) findViewById(R.id.stub_grid);

        stubList.inflate();
        stubGrid.inflate();

        listView = (ListView) findViewById(R.id.mylistview);
        gridView = (GridView) findViewById(R.id.mygridview);
        getProductList();

        //Get current view mode in share reference
        SharedPreferences sharedPreferences = getSharedPreferences("ViewMode", MODE_PRIVATE);
        currentViewMode = sharedPreferences.getInt("currentViewMode", VIEW_MODE_LISTVIEW);//Default is view listview
        //Register item lick
        listView.setOnItemClickListener(onItemClick);
        gridView.setOnItemClickListener(onItemClick);

        switchView();

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);



        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_arrow:
                        Intent intent0 = new Intent(ActivityTwo.this, Main.class);
                        startActivity(intent0);
                        break;

                    case R.id.ic_android:
                        Intent intent1 = new Intent(ActivityTwo.this, ActivityOne.class);
                        startActivity(intent1);
                        break;

                    case R.id.ic_books:

                        break;

                    case R.id.ic_center_focus:
                        Intent intent3 = new Intent(ActivityTwo.this, ActivityThree.class);
                        startActivity(intent3);
                        break;

                    case R.id.ic_backup:
                        Intent intent4 = new Intent(ActivityTwo.this, ActivityFour.class);
                        startActivity(intent4);
                        break;
                }


                return false;
            }
        });
        settings = getApplicationContext().getSharedPreferences(NAME_FILE_SETTING, 0);
        test = settings.getBoolean("test", false);
        Log.i("111", "test "+String.valueOf(test));


    }
    private void switchView() {

        if(VIEW_MODE_LISTVIEW == currentViewMode) {
            //Display listview
            stubList.setVisibility(View.VISIBLE);
            //Hide gridview
            stubGrid.setVisibility(View.GONE);
        } else {
            //Hide listview
            stubList.setVisibility(View.GONE);
            //Display gridview
            stubGrid.setVisibility(View.VISIBLE);
        }
        setAdapters();
    }

    private void setAdapters() {
        if(VIEW_MODE_LISTVIEW == currentViewMode) {
            listViewAdapter = new ListViewAdapter(this, R.layout.list_item, productList);
            listView.setAdapter(listViewAdapter);
        } else {
            gridViewAdapter = new GridViewAdapter(this, R.layout.grid_item, productList);
            gridView.setAdapter(gridViewAdapter);
        }
    }

    public List<Product> getProductList() {
        //pseudo code to get product, replace your code to get real product here
        settings = getApplicationContext().getSharedPreferences(NAME_FILE_SETTING, 0);
        productList = new ArrayList<>();

        productList.add(new Product(R.drawable.arsenal, "Arsenal", "Emirates Stadium",settings.getBoolean("Arsenal", false),"Arsenal"));
        productList.add(new Product(R.drawable.bournemouth, "Bournemouth", "Dean CourtStadium",settings.getBoolean("Bournemouth", false),"Bournemouth"));
        productList.add(new Product(R.drawable.brighton, "Brighton", "Falmer Stadium",false,"Brighton"));
        productList.add(new Product(R.drawable.burnley, "Burnley", "Turf Moor Stadium",false,"Burnley"));
        productList.add(new Product(R.drawable.chelsea, "Chelsea", "Stamford Bridge Stadium",false,"Chelsea"));

        productList.add(new Product(R.drawable.cystalpalace, "Crystal Palace", "Selhurst Park Stadium",false,"CrystalPalace"));
        productList.add(new Product(R.drawable.everton, "Everton", "Goodison Park Stadium",false,"Everton"));
        productList.add(new Product(R.drawable.huddersfield, "Huddersfield Town", "Kirklees Stadium",false,"Huddersfield Town"));
        productList.add(new Product(R.drawable.leicester, "Leicester City", "King Power Stadium",false,"LeicesterCity"));
        productList.add(new Product(R.drawable.liverpool, "Liverpool", "Anfield Stadium",false,"Liverpool"));

        productList.add(new Product(R.drawable.manchestercity, "Manchester City", "Etihad S tadium",false,"ManchesterCity"));
        productList.add(new Product(R.drawable.manu, "Manchester United", "Old Trafford Stadium",false,"ManchesterUnited"));
        productList.add(new Product(R.drawable.newcastle, "Newcastle United", "St James' Park Stadium",false,"NewcastleUnited"));
        productList.add(new Product(R.drawable.southampton, "Southampton", "St Mary's Stadium",false,"Southampton"));
        productList.add(new Product(R.drawable.stoke, "Stoke City", "Bet365 Stadium",false,"StokeCity"));

        productList.add(new Product(R.drawable.swansea, "Swansea City", "Liberty Stadium",false,"SwanseaCity"));
        productList.add(new Product(R.drawable.tottenham, "Tottenham Hotspur", "Wembley Stadium",false,"TottenhamHotspur"));
        productList.add(new Product(R.drawable.watford, "Watford", "Vicarage Road Stadium",false,"Watford"));
        productList.add(new Product(R.drawable.westbromwich, "West Bromwich Albion", "The Hawthorns Stadium",false,"WestBromwichAlbion"));
        productList.add(new Product(R.drawable.westham, "West Ham United", "London Stadium",false,"WestHamUnited"));


        return productList;
    }

    AdapterView.OnItemClickListener onItemClick = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            settings = getApplicationContext().getSharedPreferences(NAME_FILE_SETTING, 0);
            //Do any thing when user click to item
            editor = settings.edit();
            ImageView favorite = (ImageView)view.findViewById(R.id.favorite_team_image);

            Log.i("111", "check : "+String.valueOf(check));
            if(productList.get(position).getSubscribe()){

                FirebaseMessaging.getInstance().unsubscribeFromTopic(productList.get(position).getSubscribeName());

                productList.get(position).setSubscribe(false);

                favorite.setImageResource(R.drawable.false1);
                editor.putBoolean(productList.get(position).getSubscribeName(), true);
                check = settings.getBoolean(productList.get(position).getSubscribeName(), false);
                Log.i("111", "check : "+String.valueOf(check));
                //Log.i("111", "test "+String.valueOf(test));
                Log.i("111", productList.get(position).getTitle() + " - " + productList.get(position).getDescription()+"-"+productList.get(position).getSubscribe());
                Toast.makeText(getApplicationContext(), position+" "+productList.get(position).getTitle() + " - " + productList.get(position).getDescription()+"-"+productList.get(position).getSubscribe(), Toast.LENGTH_SHORT).show();

            }else{

                FirebaseMessaging.getInstance().subscribeToTopic(productList.get(position).getSubscribeName());

                productList.get(position).setSubscribe(true);
                favorite.setImageResource(R.drawable.true1);
                editor.putBoolean(productList.get(position).getSubscribeName(),false);
                check = settings.getBoolean(productList.get(position).getSubscribeName(),true);
                Log.i("111", "check : "+String.valueOf(check));

                //Log.i("111", "test "+String.valueOf(test));
                Log.i("111", productList.get(position).getTitle() + " - " + productList.get(position).getDescription()+"-"+productList.get(position).getSubscribe());
                Toast.makeText(getApplicationContext(), position+" "+productList.get(position).getTitle() + " - " + productList.get(position).getDescription()+"-"+productList.get(position).getSubscribe(), Toast.LENGTH_SHORT).show();

            }
            editor.commit();
        }

    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.setting, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_menu_1:
                if(VIEW_MODE_LISTVIEW == currentViewMode) {
                    currentViewMode = VIEW_MODE_GRIDVIEW;
                } else {
                    currentViewMode = VIEW_MODE_LISTVIEW;
                }
                //Switch view
                switchView();
                //Save view mode in share reference
                SharedPreferences sharedPreferences = getSharedPreferences("ViewMode", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("currentViewMode", currentViewMode);
                editor.commit();

                break;
        }
        return true;
    }
}
