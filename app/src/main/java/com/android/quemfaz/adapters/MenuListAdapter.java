package com.android.quemfaz.adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.quemfaz.R;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by nicolle on 31/01/15.
 */
public class MenuListAdapter extends BaseAdapter {

    private List<MenuItem> menuItens;
    private boolean logado;
    private Context context;

    public MenuListAdapter(Context context){

        this.context = context;
        this.menuItens = new ArrayList<MenuItem>();
        if(ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())){
            logado = true;

            menuItens.add(new MenuItem("Início", R.drawable.ic_menu_home));
            menuItens.add(new MenuItem("Histórico de Busca", R.drawable.ic_menu_recent_history));
            menuItens.add(new MenuItem("Favoritos", R.drawable.ic_menu_star));
            menuItens.add(new MenuItem("Categorias", R.drawable.ic_menu_find_holo_light));

        } else {
            //Há usuário logado
            logado = false;

            menuItens.add(new MenuItem("Início", R.drawable.ic_menu_home));
            menuItens.add(new MenuItem("Cadastrar Estabelecimento", R.drawable.ic_menu_add));
            menuItens.add(new MenuItem("Histórico de Busca", R.drawable.ic_menu_recent_history));
            menuItens.add(new MenuItem("Favoritos", R.drawable.ic_menu_star));
            menuItens.add(new MenuItem("Categorias", R.drawable.ic_menu_find_holo_light));
            menuItens.add(new MenuItem("LogOut",R.drawable.ic_menu_add));

        }

    }

    private static class ViewHolder{
        TextView menuText;
        ImageView menuIcon;

    }

    private class MenuItem{
        String text;
        int icon;

        MenuItem(String text, int icon){
            this.text = text;
            this.icon = icon;
        }
    }



    @Override
    public int getCount() {
        return menuItens.size();
    }

    @Override
    public Object getItem(int position) {

        if(position < menuItens.size()) {
            return menuItens.get(position);
        } else {
            return null;
        }

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {

            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.menu_item, null);

            holder = new ViewHolder();
            holder.menuText = (TextView) convertView.findViewById(R.id.menu_text);
            holder.menuIcon = (ImageView) convertView.findViewById(R.id.menu_icon);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        MenuItem menuItem = this.menuItens.get(position);

        String text = menuItem.text;
        int icon = menuItem.icon;

        holder.menuText.setText(text);
        holder.menuIcon.setImageResource(icon);



        return convertView;

    }
}
