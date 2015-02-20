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
import android.widget.Toast;

import com.android.quemfaz.R;
import com.android.quemfaz.adapters.FragmentTabAdapter;
import com.android.quemfaz.dados.Estabelecimento;
import com.android.quemfaz.dados.RepositorioEstabelecimento;
import com.android.quemfaz.fragments.ContatoEstabelecimentoFragment;
import com.android.quemfaz.fragments.InformacoesEstabelecimentoFragment;
import com.android.quemfaz.fragments.LocalizacaoEstabelecimentoFragment;
import com.google.android.gms.maps.model.LatLng;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.viewpagerindicator.TabPageIndicator;

import org.apache.commons.logging.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class CadastrarEstabelecimentoActivity extends FragmentActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private RepositorioEstabelecimento repEstabelecimento;
    /**
     * FRAGMENTS *
     */
    private InformacoesEstabelecimentoFragment informacoes;
    private LocalizacaoEstabelecimentoFragment localizacao;
    private ContatoEstabelecimentoFragment contato;
    private ArrayList<Fragment> fragments;

    /**
     * TITLES *
     */
    private static final String INFORMACOES_TITLE = "INFORMAÇÕES";
    private static final String LOCALIZACAO_TITLE = "LOCALIZAÇÃO";
    private static final String CONTATO_TITLE = "CONTATOS";
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

        this.repEstabelecimento = new RepositorioEstabelecimento();

        this.informacoes = new InformacoesEstabelecimentoFragment();
        this.localizacao = new LocalizacaoEstabelecimentoFragment();
        this.contato = new ContatoEstabelecimentoFragment();

        this.viewPager = (ViewPager) findViewById(R.id.pager);
        this.tabPageIndicator = (TabPageIndicator) findViewById(R.id.titles);

        this.viewPager.setOnPageChangeListener(this);

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
        fragments.add(localizacao);
        fragments.add(contato);

        titles = new ArrayList<String>();
        titles.add(INFORMACOES_TITLE);
        titles.add(LOCALIZACAO_TITLE);
        titles.add(CONTATO_TITLE);


        FragmentTabAdapter adapter = new FragmentTabAdapter(getSupportFragmentManager(), fragments, titles);
        this.viewPager.setAdapter(adapter);
        this.tabPageIndicator.setViewPager(this.viewPager);

    }

    private void initButtons(){

        this.cancelarButton = (Button) findViewById(R.id.cancelar_button);
        this.avancarButton = (Button) findViewById(R.id.avancar_button);
        this.salvarButton = (Button) findViewById(R.id.salvar_button);

        this.salvarButton.setVisibility(Button.GONE);
        this.avancarButton.setVisibility(Button.VISIBLE);

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
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.avancar_button:
                int currentFragment = this.viewPager.getCurrentItem();
                if (currentFragment == this.viewPager.getAdapter().getCount() - 1){
                    this.avancarButton.setVisibility(Button.GONE);
                    this.salvarButton.setVisibility(Button.VISIBLE);
                }

                break;
            case R.id.salvar_button:
                adicionarEstabelecimento();
                break;

        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        if (position == this.viewPager.getAdapter().getCount()-1){
            this.avancarButton.setVisibility(Button.GONE);
            this.salvarButton.setVisibility(Button.VISIBLE);
        } else {
            this.avancarButton.setVisibility(Button.VISIBLE);
            this.salvarButton.setVisibility(Button.GONE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void adicionarEstabelecimento(){

        String nome = this.informacoes.getNomeEstabelecimento();
        if (nome == null){
            Toast.makeText(this, "Seu estabelecimento precisa de um nome", Toast.LENGTH_LONG).show();
            return;
        }

        String descricao = this.informacoes.getDescricaoEstabelecimento();
        String categoria = this.informacoes.getCategoriaEstabelecimento();
        LatLng posicao = this.localizacao.getPosicaoEstabelecimento();
        String telefone = this.contato.getTelefoneEstabelecimento();
        String email = this.contato.getEmailEstabelecimento();
        String paginaWeb = this.contato.getPaginaWebEstabelecimento();
        byte [] fotoEstabelecimento = this.informacoes.getFotoEstabelecimento();

        Estabelecimento estabelecimento = new Estabelecimento(nome, descricao, categoria, telefone,
                email, paginaWeb, "", fotoEstabelecimento, posicao.latitude, posicao.longitude,
                ParseUser.getCurrentUser());

        try {
            this.repEstabelecimento.cadastrarEstabelecimento(estabelecimento);
            Toast.makeText(this, "Estabelecimento cadastrado com sucesso!", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
