package com.android.quemfaz.activity;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.android.quemfaz.R;
import com.android.quemfaz.adapters.EstabelecimentoListAdapter;
import com.android.quemfaz.dados.Estabelecimento;
import com.android.quemfaz.dados.RepositorioEstabelecimento;
import com.parse.ParseException;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    /** VIEWS **/
    private ListView resultadosView;
    private EditText buscaInput;
    private ImageButton buscaButton;
    private EstabelecimentoListAdapter adapter;
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

        Intent intent = getIntent();
        String categoria = (intent.hasExtra("CATEGORIA")) ? intent.getStringExtra("CATEGORIA") : null;

        if (categoria != null){
            try {
                Log.d("MainActivity", "Pesquisando por categoria: " + categoria);
                this.estabelecimentos = this.repositorioEstabelecimento.getEstabelecimentoByCategoria(categoria);
                initAdapters();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w(this.getClass().getName(),"onResume");
        this.checkLogin();

    }

    private void initViews(){
        this.resultadosView = (ListView) findViewById(R.id.resultados);
        this.buscaInput = (EditText) findViewById(R.id.busca_input);
        this.buscaButton = (ImageButton) findViewById(R.id.busca_button);
    }

    private void initAdapters(){
        adapter = new EstabelecimentoListAdapter(this, this.estabelecimentos);
        this.resultadosView.setAdapter(adapter);
    }

    private void initListeners(){
        this.buscaButton.setOnClickListener(this);
        this.resultadosView.setOnItemClickListener(this);

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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Estabelecimento estabelecimento = (Estabelecimento) adapter.getItem(position);
        Intent intent = new Intent(this,VisualizarEstabelecimentoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("nome", estabelecimento.getNome());
        bundle.putByteArray("foto", estabelecimento.getFoto());
        bundle.putString("descricao", estabelecimento.getDescricao());
        bundle.putString("facebook", estabelecimento.getPaginaFacebook());
        bundle.putDouble("latitude", estabelecimento.getLatitude());
        bundle.putDouble("longitude", estabelecimento.getLongitude());
        bundle.putString("telefone", estabelecimento.getTelefone());
        intent.putExtras(bundle);
        startActivity(intent);

    }
}
