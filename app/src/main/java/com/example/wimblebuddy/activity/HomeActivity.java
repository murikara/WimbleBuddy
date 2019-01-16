package com.example.wimblebuddy.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.wimblebuddy.NavigationPagerAdapter;
import com.example.wimblebuddy.NavigationViewPager;
import com.example.wimblebuddy.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private NavigationPagerAdapter mNavigationPagerAdapterPagerAdapter;

    /**
     * The {@link NavigationViewPager} that will host the section contents.
     */
    @BindView(R.id.containerViewPager)
    NavigationViewPager mViewPager;

    private final int HOME_POSITION = 0;
    private final int SEARCH_POSITION = 1;
    private final int PROFILE_POSITION = 2;
    //logging tag
    private static final String TAG = "HomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mNavigationPagerAdapterPagerAdapter = new NavigationPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager.setAdapter(mNavigationPagerAdapterPagerAdapter);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home_outline:
                    Log.e(TAG, "onNavigationItemSelected: setting current item on position: "
                    + HOME_POSITION);
                    mViewPager.setCurrentItem(HOME_POSITION);
                    return true;
                case R.id.magnify:
                    Log.e(TAG, "onNavigationItemSelected: setting current item on position: "
                            + SEARCH_POSITION);
                    mViewPager.setCurrentItem(SEARCH_POSITION);
                    return true;
                case R.id.account_circle:
                    Log.e(TAG, "onNavigationItemSelected: setting current item on position: "
                            + PROFILE_POSITION);
                    mViewPager.setCurrentItem(PROFILE_POSITION);
                    return true;
            }
            return false;
        }
    };
}
