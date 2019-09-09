package com.fit5046.calorietrackerapp;

import android.app.FragmentManager;
import android.os.Bundle;

import android.support.design.widget.NavigationView;
import android.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.fit5046.RESTfulWSEntities.Appuser;
import com.fit5046.commonTools.ObjectSave;

public class HomePageActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(this);


        TextView tv_nav_head_username = (TextView) headerView.findViewById(R.id.tv_nav_header_username);
        TextView tv_nav_head_email = (TextView) headerView.findViewById(R.id.tv_nav_header_email);

        Appuser currentUser = (Appuser) ObjectSave.readObject(getApplicationContext(), "currentUser");
        String username = currentUser.getFirstname().substring(0,1).toUpperCase() + currentUser.getFirstname().substring(1)
                + " "
                + currentUser.getSurname().substring(0, 1).toUpperCase()+currentUser.getSurname().substring(1);
        tv_nav_head_username.setText(username);
        tv_nav_head_email.setText(currentUser.getEmail());

        getSupportActionBar().setTitle("Calorie Tracker");
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, new HomePageFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home_page) {
            FragmentManager fragmentManager = getFragmentManager();
            HomePageFragment homePageFragment = new HomePageFragment();
            fragmentManager.beginTransaction().replace(R.id.content_frame, homePageFragment).commit();
        } else if (id == R.id.nav_find_food) {
            FragmentManager fragmentManager = getFragmentManager();
            FindFoodFragment findFoodFragment = new FindFoodFragment();
            fragmentManager.beginTransaction().replace(R.id.content_frame, findFoodFragment).commit();
        } else if (id == R.id.nav_step_record) {
            FragmentManager fragmentManager = getFragmentManager();
            StepRecordFragment stepRecordFragment = new StepRecordFragment();
            fragmentManager.beginTransaction().replace(R.id.content_frame, stepRecordFragment).commit();
        } else if (id == R.id.nav_calorie_tracker) {

            // To be continue...

        } else if (id == R.id.nav_daily_report) {

            // To be continue...

        } else if (id == R.id.nav_personal_map) {
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            MapsFragment mapsFragment = new MapsFragment();
            fragmentManager.beginTransaction().replace(R.id.content_frame, mapsFragment).commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
