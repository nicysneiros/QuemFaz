package com.android.quemfaz.activity;


import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.android.quemfaz.R;
import com.android.quemfaz.adapters.EstabelecimentoListAdapter;
import com.android.quemfaz.dados.Estabelecimento;
import com.android.quemfaz.dados.RepositorioEstabelecimento;
import com.parse.ParseException;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    /** VIEWS **/
    private ListView resultadosView;
    private EditText buscaInput;
    private ImageButton buscaButton;
    /************/

    private List<Estabelecimento> estabelecimentos;

    private RepositorioEstabelecimento repositorioEstabelecimento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.repositorioEstabelecimento = new RepositorioEstabelecimento();
        this.estabelecimentos = new ArrayList<Estabelecimento>();

        initViews();
        initAdapters();
        initListeners();
    }

    private void initViews(){
        this.resultadosView = (ListView) findViewById(R.id.resultados);
        this.buscaInput = (EditText) findViewById(R.id.busca_input);
        this.buscaButton = (ImageButton) findViewById(R.id.busca_button);
    }

    private void initAdapters(){
        EstabelecimentoListAdapter adapter = new EstabelecimentoListAdapter(this, this.estabelecimentos);
        this.resultadosView.setAdapter(adapter);
    }

    private void initListeners(){
        this.buscaButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.busca_button:
                try {
                    String pesquisa = this.buscaInput.getText().toString();
                    Log.d("MainActivity", "Pesquisando: " + pesquisa);
                    this.estabelecimentos = this.repositorioEstabelecimento.getEstabelecimentoByNameOrDescricao(pesquisa);
                    initAdapters();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
        }

    }
}
