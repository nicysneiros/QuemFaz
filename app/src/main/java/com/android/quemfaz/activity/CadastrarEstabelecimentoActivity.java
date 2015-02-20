package com.android.quemfaz.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.android.quemfaz.R;
import com.android.quemfaz.adapters.FragmentTabAdapter;
import com.android.quemfaz.fragments.InformacoesEstabelecimentoFragment;
import com.viewpagerindicator.TabPageIndicator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class CadastrarEstabelecimentoActivity extends FragmentActivity implements View.OnClickListener {

    /**
     * FRAGMENTS *
     */
    private InformacoesEstabelecimentoFragment informacoes;
    private ArrayList<Fragment> fragments;

    /**
     * TITLES *
     */
    private static final String INFORMACOES_TITLE = "INFORMAÇÕES";
    private ArrayList<String> titles;

    /**
     * VIEWS *
     */
    private ViewPager viewPager;
    private TabPageIndicator tabPageIndicator;
    private Button cancelarButton, avancarButton, salvarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_estabelecimento);

        this.informacoes = new InformacoesEstabelecimentoFragment();

        this.viewPager = (ViewPager) findViewById(R.id.pager);
        this.tabPageIndicator = (TabPageIndicator) findViewById(R.id.titles);

        initAdapter();
        initButtons();
        initListeners();

        if (getActionBar() != null) {

            getActionBar().setDisplayHomeAsUpEnabled(true);
            getActionBar().setHomeButtonEnabled(true);

        }
    }

    private void initAdapter() {

        fragments = new ArrayList<Fragment>();
        fragments.add(informacoes);

        titles = new ArrayList<String>();
        titles.add(INFORMACOES_TITLE);


        FragmentTabAdapter adapter = new FragmentTabAdapter(getSupportFragmentManager(), fragments, titles);
        this.viewPager.setAdapter(adapter);
        this.tabPageIndicator.setViewPager(this.viewPager);

    }

    private void initButtons(){

        this.cancelarButton = (Button) findViewById(R.id.cancelar_button);
        this.avancarButton = (Button) findViewById(R.id.avancar_button);
        this.salvarButton = (Button) findViewById(R.id.salvar_button);

        if (this.viewPager.getCurrentItem() == this.viewPager.getAdapter().getCount() - 1){
            //É o último fragment
            this.salvarButton.setVisibility(Button.VISIBLE);
            this.avancarButton.setVisibility(Button.GONE);
        } else {
            this.salvarButton.setVisibility(Button.GONE);
            this.avancarButton.setVisibility(Button.VISIBLE);
        }

    }

    private void initListeners(){

        this.cancelarButton.setOnClickListener(this);
        this.avancarButton.setOnClickListener(this);
        this.salvarButton.setOnClickListener(this);

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

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.cancelar_button:

                break;
            case R.id.avancar_button:
                int currentFragment = this.viewPager.getCurrentItem();
                if (currentFragment < this.viewPager.getAdapter().getCount() - 1){
                    this.viewPager.setCurrentItem(currentFragment + 1);

                    this.avancarButton.setVisibility(Button.VISIBLE);
                    this.salvarButton.setVisibility(Button.GONE);
                } else {
                    //último fragment
                    this.avancarButton.setVisibility(Button.GONE);
                    this.salvarButton.setVisibility(Button.VISIBLE);
                }

                break;
            case R.id.salvar_button:
                //TODO Pegar dados dos fragments
                break;

        }

    }
}
