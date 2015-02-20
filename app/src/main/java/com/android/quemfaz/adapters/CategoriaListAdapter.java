package com.android.quemfaz.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.quemfaz.R;
import com.android.quemfaz.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by nicolle on 31/01/15.
 */
public class CategoriaListAdapter extends BaseAdapter {

    private List<MenuItem> categoriaItens;
    private Context context;

    public CategoriaListAdapter(Context context){

        this.context = context;
        this.categoriaItens = new ArrayList<MenuItem>();

        String [] categorias = context.getResources().getStringArray(R.array.lista_categorias);

        categoriaItens.add(new MenuItem(categorias[0], R.drawable.alimentos_bebidas));
        categoriaItens.add(new MenuItem(categorias[1], R.drawable.automoveis_veiculos));
        categoriaItens.add(new MenuItem(categorias[2], R.drawable.beleza));
        categoriaItens.add(new MenuItem(categorias[3], R.drawable.casa_decoracao));
        categoriaItens.add(new MenuItem(categorias[4], R.drawable.esporte_lazer));
        categoriaItens.add(new MenuItem(categorias[5], R.drawable.flores_cestas_presentes));
        categoriaItens.add(new MenuItem(categorias[6], R.drawable.fotografia));
        categoriaItens.add(new MenuItem(categorias[7], R.drawable.imoveis));
        categoriaItens.add(new MenuItem(categorias[8], R.drawable.moda_acessorios));
        categoriaItens.add(new MenuItem(categorias[9], R.drawable.papelaria_escritorio));
        categoriaItens.add(new MenuItem(categorias[10], R.drawable.perfume_cosmeticos));
        categoriaItens.add(new MenuItem(categorias[11], R.drawable.pet_shop));
        categoriaItens.add(new MenuItem(categorias[12], R.drawable.turismo));

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
        return categoriaItens.size();
    }

    @Override
    public Object getItem(int position) {

        if(position < categoriaItens.size()) {
            return categoriaItens.get(position);
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
            convertView = inflater.inflate(R.layout.custom_categoria_item, null);

            holder = new ViewHolder();
            holder.menuText = (TextView) convertView.findViewById(R.id.categoria);
            holder.menuIcon = (ImageView) convertView.findViewById(R.id.icone);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        MenuItem menuItem = this.categoriaItens.get(position);

        final String text = menuItem.text;
        int icon = menuItem.icon;

        holder.menuText.setText(text);
        holder.menuIcon.setImageResource(icon);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("CATEGORIA", text);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        return convertView;

    }
}
