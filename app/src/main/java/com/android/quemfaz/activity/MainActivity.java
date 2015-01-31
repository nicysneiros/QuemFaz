package com.android.quemfaz.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.android.quemfaz.R;
import com.android.quemfaz.fragments.NavigationDrawerFragment;

public class MainActivity extends Activity {

    private DrawerLayout mDrawerLayout;
    private FrameLayout mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);


        Fragment fragment = new NavigationDrawerFragment();


        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().add(R.id.drawer_layout, fragment).commit();




        //getActionBar().setDisplayHomeAsUpEnabled(true);
        //getActionBar().setHomeButtonEnabled(true);


        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.string.title_activity_main,  /* "open drawer" description for accessibility */
                R.string.title_activity_main  /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
                //getActionBar().setTitle("teste1");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                //getActionBar().setTitle("teste1");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

    }

}
