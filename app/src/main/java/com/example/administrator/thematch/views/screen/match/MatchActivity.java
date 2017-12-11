package com.example.administrator.thematch.views.screen.match;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.thematch.ActivityFour;
import com.example.administrator.thematch.ActivityOne;
import com.example.administrator.thematch.ActivityThree;
import com.example.administrator.thematch.views.screen.Subcribe.SubcribeActivity;
import com.example.administrator.thematch.BottomNavigationViewHelper;
import com.example.administrator.thematch.R;
import com.example.administrator.thematch.models.MatchModel;
import com.example.administrator.thematch.services.GetMatchService;
import com.example.administrator.thematch.services.interfaces.GetMatchServiceInterface;
import com.google.firebase.database.DatabaseError;

import java.util.List;

/**
 * Created by User on 4/15/2017.
 */

public class MatchActivity extends AppCompatActivity {

    ListView listView;

    private static final String TAG = "MatchActivity";
    private GetMatchService getMatchService = new GetMatchService();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
         listView = (ListView) findViewById(R.id.listview);


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_arrow:

                        break;

                    case R.id.ic_android:
                        Intent intent1 = new Intent(MatchActivity.this, ActivityOne.class);
                        startActivity(intent1);
                        break;

                    case R.id.ic_books:
                        Intent intent2 = new Intent(MatchActivity.this, SubcribeActivity.class);
                        startActivity(intent2);
                        break;

                    case R.id.ic_center_focus:
                        Intent intent3 = new Intent(MatchActivity.this, ActivityThree.class);
                        startActivity(intent3);
                        break;

                    case R.id.ic_backup:
                        Intent intent4 = new Intent(MatchActivity.this, ActivityFour.class);
                        startActivity(intent4);
                        break;
                }


                return false;
            }
        });

        getMatchService.getMatchList(new GetMatchServiceInterface() {
            @Override
            public void onGetMatchListSuccess(List<MatchModel> matchList) {
                displayList(matchList);
            }

            @Override
            public void onGetMatchListFail(DatabaseError error) {

            }
        });
    }

    private void displayList(List<MatchModel> matchList) {
        MyListAdaper adaper = new MyListAdaper(this, R.layout.list_item_tab0fragment, matchList);
        listView.setAdapter(adaper);
    }

    private class MyListAdaper extends ArrayAdapter<MatchModel> {

        private int layout;
        private List<MatchModel> mObjects;

        private MyListAdaper(Context context, int resource, List<MatchModel> objects) {
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
                viewHolder.team1NameTextView = (TextView) convertView.findViewById(R.id.team_1_name_text_view);
                viewHolder.team2NameTextView = (TextView) convertView.findViewById(R.id.team_2_name_text_view);
                viewHolder.scoreTeam1TextView = (TextView) convertView.findViewById(R.id.team_1_score_text_view);
                viewHolder.scoreTeam2TextView = (TextView) convertView.findViewById(R.id.team_2_score_text_view);
                viewHolder.dateMatchTextView = (TextView) convertView.findViewById(R.id.date_match_text_view);


                convertView.setTag(viewHolder);
            }
            mainViewholder = (ViewHolder) convertView.getTag();
            mainViewholder.team1NameTextView.setText(mObjects.get(position).team1);
            mainViewholder.team2NameTextView.setText(mObjects.get(position).team2);
            mainViewholder.scoreTeam1TextView.setText(""+mObjects.get(position).score_team1);
            mainViewholder.scoreTeam2TextView.setText(""+mObjects.get(position).score_team2);
            mainViewholder.dateMatchTextView.setText(mObjects.get(position).start_date);

            return convertView;
        }
    }
    public class ViewHolder {

        TextView team1NameTextView;
        TextView team2NameTextView;
        TextView scoreTeam1TextView;
        TextView scoreTeam2TextView;
        TextView dateMatchTextView;

    }
}
