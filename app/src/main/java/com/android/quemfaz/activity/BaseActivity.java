package com.android.quemfaz.activity;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.android.quemfaz.R;
import com.android.quemfaz.fragments.NavigationDrawerFragment;

/**
 * Created by nicolle on 07/02/15.
 */
public class BaseActivity extends FragmentActivity {

    private DrawerToggle drawerToggle;

    private NavigationDrawerFragment navigationDrawerFragment;

    @Override
    public void setContentView(int layoutResID) {

        DrawerLayout drawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_base, null);
        FrameLayout contentFrame = (FrameLayout) drawerLayout.findViewById(R.id.content_frame);

        this.drawerToggle = new DrawerToggle(this, drawerLayout, R.string.app_name, R.string.app_name);
        drawerLayout.setDrawerListener(this.drawerToggle);

        super.setContentView(drawerLayout);

        getLayoutInflater().inflate(layoutResID, contentFrame, true);



        navigationDrawerFragment = new NavigationDrawerFragment();
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.drawer_layout, navigationDrawerFragment).commit();

        if (getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
            getActionBar().setHomeButtonEnabled(true);
        }

    }

    public void checkLogin(){
        navigationDrawerFragment.checkLogin();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private class DrawerToggle extends ActionBarDrawerToggle {

        public DrawerToggle(Activity activity, DrawerLayout drawerLayout, int openDrawerContentDescRes, int closeDrawerContentDescRes) {
            super(activity, drawerLayout, openDrawerContentDescRes, closeDrawerContentDescRes);
        }

        public void onDrawerClosed(View view) {
            super.onDrawerClosed(view);
        }

        public void onDrawerOpened(View drawerView) {
            super.onDrawerOpened(drawerView);
        }
    }
}
