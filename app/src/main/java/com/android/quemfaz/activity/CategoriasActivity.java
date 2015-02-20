package com.android.quemfaz.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.quemfaz.R;
import com.android.quemfaz.adapters.CategoriaListAdapter;

import java.util.List;

public class CategoriasActivity extends ActionBarActivity {

    String [] categorias;
    /** VIEWS **/
    private ListView listaCategorias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);

        this.categorias = getResources().getStringArray(R.array.lista_categorias);
        this.listaCategorias = (ListView) findViewById(R.id.lista_categorias);
        CategoriaListAdapter adapter = new CategoriaListAdapter(this);
        this.listaCategorias.setAdapter(adapter);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_categorias, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
