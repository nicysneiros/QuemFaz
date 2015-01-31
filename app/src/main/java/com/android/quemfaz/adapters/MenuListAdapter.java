package com.android.quemfaz.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.parse.ParseAnonymousUtils;
import com.parse.ParseUser;

import java.util.Arrays;
import java.util.List;

/**
 * Created by nicolle on 31/01/15.
 */
public class MenuListAdapter extends BaseAdapter {

    private List<String> menuItens;
    private boolean logado;

    public MenuListAdapter(){

        if(ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())){
            //Não há usuário logado
            logado = false;
            menuItens = Arrays.asList("Início", "Histórico de Busca", "Favoritos", "Categorias");
        } else {
            //Há usuário logado
            logado = true;
            menuItens = Arrays.asList("Início", "Cadastrar Estabelecimento", "Histórico de Busca", "Favoritos", "Categorias");
        }

    }

    @Override
    public int getCount() {
        return menuItens.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
