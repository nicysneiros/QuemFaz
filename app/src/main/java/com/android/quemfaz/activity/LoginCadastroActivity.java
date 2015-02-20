package com.android.quemfaz.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.MenuItem;

import com.android.quemfaz.R;
import com.android.quemfaz.adapters.FragmentTabAdapter;
import com.android.quemfaz.fragments.CadastrarFragment;
import com.android.quemfaz.fragments.LoginFragment;
import com.viewpagerindicator.TabPageIndicator;
import com.viewpagerindicator.TitlePageIndicator;

import java.util.ArrayList;

public class LoginCadastroActivity extends FragmentActivity {

    private FragmentTabAdapter adapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_cadastro);

        Fragment loginFragment = new LoginFragment();
        Fragment cadastroFragment = new CadastrarFragment();

        ArrayList<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(loginFragment);
        fragments.add(cadastroFragment);

        String loginTitle = "LOGIN";
        String cadastroTitle = "CADASTRAR";

        ArrayList<String> titles = new ArrayList<String>();
        titles.add(loginTitle);
        titles.add(cadastroTitle);

        adapter = new FragmentTabAdapter(getSupportFragmentManager(), fragments, titles);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(adapter);

        if (getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
            getActionBar().setHomeButtonEnabled(true);
        }

        TabPageIndicator tabIndicator = (TabPageIndicator)findViewById(R.id.titles);
        tabIndicator.setViewPager(mViewPager);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
