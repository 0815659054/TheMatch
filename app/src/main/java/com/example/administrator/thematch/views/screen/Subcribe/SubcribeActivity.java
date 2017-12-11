package com.example.administrator.thematch.views.screen.Subcribe;

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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.thematch.ActivityFour;
import com.example.administrator.thematch.ActivityOne;
import com.example.administrator.thematch.ActivityThree;
import com.example.administrator.thematch.BottomNavigationViewHelper;
import com.example.administrator.thematch.GridViewAdapter;
import com.example.administrator.thematch.ListViewAdapter;
import com.example.administrator.thematch.R;
import com.example.administrator.thematch.models.MatchModel;
import com.example.administrator.thematch.models.TeamModel;
import com.example.administrator.thematch.models.TeamSubModel;
import com.example.administrator.thematch.services.AddTeamSubService;
import com.example.administrator.thematch.services.GetSubscribeService;
import com.example.administrator.thematch.services.GetTeamSubService;
import com.example.administrator.thematch.services.interfaces.GetSubscribeServicelnterface;

import com.example.administrator.thematch.services.interfaces.GetTeamSubServiceInterface;
import com.example.administrator.thematch.views.screen.match.MatchActivity;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.List;

/**
 * Created by User on 4/15/2017.
 */

public class SubcribeActivity extends AppCompatActivity {
    private ViewStub stubGrid;
    private ViewStub stubList;
    private ListView listView;
    private GridView gridView;
    private ListViewAdapter listViewAdapter;
    private GridViewAdapter gridViewAdapter;
    private List<TeamModel> mTeamList;

    private int currentViewMode = 0;
    static boolean check;
    static final int VIEW_MODE_LISTVIEW = 0;
    static final int VIEW_MODE_GRIDVIEW = 1;

    private GetSubscribeService getSubscribeService = new GetSubscribeService();
    private GetTeamSubService getTeamSubService = new GetTeamSubService();

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


        SharedPreferences sharedPreferences = getSharedPreferences("ViewMode", MODE_PRIVATE);
        currentViewMode = sharedPreferences.getInt("currentViewMode", VIEW_MODE_LISTVIEW);//Default is view listview
        //Register item lick
        listView.setOnItemClickListener(onItemClick);
        gridView.setOnItemClickListener(onItemClick);

        //switchView();

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
                        Intent intent0 = new Intent(SubcribeActivity.this, MatchActivity.class);
                        startActivity(intent0);
                        break;

                    case R.id.ic_android:
                        Intent intent1 = new Intent(SubcribeActivity.this, ActivityOne.class);
                        startActivity(intent1);
                        break;

                    case R.id.ic_books:

                        break;

                    case R.id.ic_center_focus:
                        Intent intent3 = new Intent(SubcribeActivity.this, ActivityThree.class);
                        startActivity(intent3);
                        break;

                    case R.id.ic_backup:
                        Intent intent4 = new Intent(SubcribeActivity.this, ActivityFour.class);
                        startActivity(intent4);
                        break;
                }


                return false;
            }
        });

        getSubscribeService.getSubscribeList(new GetSubscribeServicelnterface() {
            @Override
            public void onGetSubscribeSuccess(List<TeamModel> teamList) {
                findSubscribeTeam(teamList);
            }

            @Override
            public void onGetSubscribeListFail(DatabaseError error) {
            }
        });
    }

    private void findSubscribeTeam(final List<TeamModel> teamList) {
        getTeamSubService.getTeamSubList(new GetTeamSubServiceInterface() {
            @Override
            public void onGetTeamSubListSuccess(List<TeamSubModel> teamSubList) {
                String currentToken = FirebaseInstanceId.getInstance().getToken();
                for (TeamModel team : teamList) {
                    if (findTeamWithIdAndTokenMatch(team, teamSubList, currentToken)) {
                        team.isSubscribed = true;
                    }
                }
                displayList(teamList);
            }

            @Override
            public void onGetTeamSubListFail(DatabaseError error) {

            }
        });
    }

    private Boolean findTeamWithIdAndTokenMatch(TeamModel team, List<TeamSubModel> teamSubList, String currentToken) {
        for (TeamSubModel teamSubModel : teamSubList) {
            if (team.id.equals(teamSubModel.team_id)) {
                if (findTokenIdWith(team, teamSubModel, currentToken)) {
                    return true;
                }
            }
        }
        return false;
    }

    private Boolean findTokenIdWith(TeamModel team, TeamSubModel teamSubModel, String currentToken) {
        for (String token : teamSubModel.token) {
            if (token.equals(currentToken)) {
                return true;
            }
        }
        return false;
    }



    private void displayList(List<TeamModel> teamList) {
        if(VIEW_MODE_LISTVIEW == currentViewMode) {
            listViewAdapter = new ListViewAdapter(this, R.layout.list_item, teamList);
            listView.setAdapter(listViewAdapter);
        } else {
            gridViewAdapter = new GridViewAdapter(this, R.layout.grid_item, teamList);
            gridView.setAdapter(gridViewAdapter);
        }
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
        displayList(mTeamList);
    }

    AdapterView.OnItemClickListener onItemClick = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //Do any thing when user click to item
            ImageView favorite = (ImageView)view.findViewById(R.id.favorite_team_image);

            Log.i("111", "check : "+String.valueOf(check));
//            if(productList.get(position).getSubscribe()){
//
//                FirebaseMessaging.getInstance().unsubscribeFromTopic(productList.get(position).getSubscribeName());
//
//                productList.get(position).setSubscribe(false);
//
//                favorite.setImageResource(R.drawable.false1);
//
//                Log.i("111", "check : "+String.valueOf(check));
//                Log.i("111", productList.get(position).getTitle() + " - " + productList.get(position).getDescription()+"-"+productList.get(position).getSubscribe());
//                Toast.makeText(getApplicationContext(), position+" "+productList.get(position).getTitle() + " - " + productList.get(position).getDescription()+"-"+productList.get(position).getSubscribe(), Toast.LENGTH_SHORT).show();
//
//            }else{
//
//                FirebaseMessaging.getInstance().subscribeToTopic(productList.get(position).getSubscribeName());
//
//                productList.get(position).setSubscribe(true);
//
//                favorite.setImageResource(R.drawable.true1);
//
//                Log.i("111", "check : "+String.valueOf(check));
//                Log.i("111", productList.get(position).getTitle() + " - " + productList.get(position).getDescription()+"-"+productList.get(position).getSubscribe());
//                Toast.makeText(getApplicationContext(), position+" "+productList.get(position).getTitle() + " - " + productList.get(position).getDescription()+"-"+productList.get(position).getSubscribe(), Toast.LENGTH_SHORT).show();

  //          }
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
