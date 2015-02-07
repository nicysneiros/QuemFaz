package com.android.quemfaz.adapters;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.quemfaz.R;
import com.android.quemfaz.dados.Estabelecimento;
import com.android.quemfaz.utils.CircularImageView;

import java.util.List;

/**
 * Created by nicolle on 07/02/15.
 */
public class EstabelecimentoListAdapter extends BaseAdapter {

    private Context context;
    private List<Estabelecimento> estabelecimentos;

    public EstabelecimentoListAdapter (Context context, List<Estabelecimento> estabelecimentos){
        this.context = context;
        this.estabelecimentos = estabelecimentos;
    }

    private static class ViewHolder{
        CircularImageView foto;
        TextView nome, descricao;
    }

    @Override
    public int getCount() {
        if (this.estabelecimentos != null) return this.estabelecimentos.size();
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (this.estabelecimentos != null && this.estabelecimentos.size() > position){
            return this.estabelecimentos.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Estabelecimento estabelecimento = this.estabelecimentos.get(position);
        ViewHolder holder;

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.resultados_busca_item, null);

            holder = new ViewHolder();
            holder.foto = (CircularImageView) convertView.findViewById(R.id.foto_estabelecimento);
            holder.nome = (TextView) convertView.findViewById(R.id.nome_estabelecimento);
            holder.descricao = (TextView) convertView.findViewById(R.id.descricao_estabelecimento);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.foto.setImageBitmap(BitmapFactory.decodeByteArray(estabelecimento.getFoto(), 0, estabelecimento.getFoto().length));
        holder.nome.setText(estabelecimento.getNome());
        holder.descricao.setText(estabelecimento.getDescricao());

        return convertView;
    }
}
