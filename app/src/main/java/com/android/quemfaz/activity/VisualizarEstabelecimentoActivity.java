package com.android.quemfaz.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.android.quemfaz.R;
import com.android.quemfaz.dados.Estabelecimento;

import java.io.ByteArrayOutputStream;
import java.util.Locale;


public class VisualizarEstabelecimentoActivity extends Activity implements View.OnClickListener {

    private ImageView imagem;
    private TextView descricao, nome;
    private Bitmap bitmap;
    private Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_estabelecimento);
         bundle = getIntent().getExtras();

        imagem = (ImageView) findViewById(R.id.foto_estabelecimento);
        descricao = (TextView) findViewById(R.id.descricao_estabelecimento);
        nome = (TextView) findViewById(R.id.nome_estabelecimento);
        ImageButton rotaButton = (ImageButton) findViewById(R.id.rota_button);
        ImageButton telefoneButton = (ImageButton) findViewById(R.id.telefone_button);
        rotaButton.setOnClickListener(this);
        telefoneButton.setOnClickListener(this);
        this.bitmap = BitmapFactory.decodeByteArray(bundle.getByteArray("foto"),0,bundle.getByteArray("foto").length);
        this.descricao.setText(bundle.getString("descricao"));
        this.nome.setText(bundle.getString("nome"));
        imagem.setImageBitmap(bitmap);

        setTitle(bundle.getString("nome"));

        if (getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
            getActionBar().setHomeButtonEnabled(true);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.telefone_button:
                intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + bundle.getString("telefone")));
                if(intent.resolveActivity(getPackageManager())!= null){
                    startActivity(intent);
                }
                break;
            case R.id.rota_button:
                intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://maps.google.com/maps?daddr="+bundle.getDouble("latitude")+","+bundle.getDouble("longitude")));
                if(intent.resolveActivity(getPackageManager())!= null){
                    startActivity(intent);
                }
                break;
        }
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
