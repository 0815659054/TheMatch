package com.example.administrator.thematch;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Main extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private SectionsPageAdapter mSectionsPageAdapter;

    private ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


        String weekDay;
        String Day;
        int number ;
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEE yyyy.MM.dd", Locale.US);
        SimpleDateFormat E = new SimpleDateFormat("E", Locale.US);
        SimpleDateFormat D = new SimpleDateFormat("d", Locale.US);
        SimpleDateFormat U = new SimpleDateFormat("u", Locale.US);


        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);


        tabLayout.setupWithViewPager(mViewPager);
        //line color
        //tabLayout.setSelectedTabIndicatorColor(R.color.colorAccent);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home_black);
        for (int i=1; i <=7 ; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_YEAR, i - 4);
            weekDay = E.format(calendar.getTime());
            Day = D.format(calendar.getTime());
            number = Integer.parseInt(U.format(calendar.getTime()));
            if (number==1) {
                tabLayout.getTabAt(i).setText("Mo"+"\n"+Day);

            }
            else if(number==3){
                tabLayout.getTabAt(i).setText("we"+"\n"+Day);
            }
            else {
                tabLayout.getTabAt(i).setText(weekDay + "\n" + Day);
            }

            Log.i(Day, String.valueOf(number));
        }
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
                        Intent intent1 = new Intent(Main.this, ActivityOne.class);
                        startActivity(intent1);
                        break;

                    case R.id.ic_books:
                        Intent intent2 = new Intent(Main.this, ActivityTwo.class);
                        startActivity(intent2);
                        break;

                    case R.id.ic_center_focus:
                        Intent intent3 = new Intent(Main.this, ActivityThree.class);
                        startActivity(intent3);
                        break;

                    case R.id.ic_backup:
                        Intent intent4 = new Intent(Main.this, ActivityFour.class);
                        startActivity(intent4);
                        break;
                }


                return false;
            }
        });

    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab0Fragment());
        adapter.addFragment(new Tab1Fragment());
        adapter.addFragment(new Tab2Fragment());
        adapter.addFragment(new Tab3Fragment());
        adapter.addFragment(new Tab4Fragment());
        adapter.addFragment(new Tab5Fragment());
        adapter.addFragment(new Tab6Fragment());
        adapter.addFragment(new Tab7Fragment());
        viewPager.setAdapter(adapter);
    }

}
