package com.android.quemfaz.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.android.quemfaz.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by nicolle on 19/02/15.
 */
public class InformacoesEstabelecimentoFragment extends Fragment implements View.OnClickListener, DialogInterface.OnClickListener{

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_IMAGE_GET = 2;

    private String imageFilePath;
    private String [] listaCategorias;
    private Bitmap fotoEstabelecimento;

    /** VIEWS **/
    private EditText nomeInput, descricaoInput;
    private Spinner categoriaInput;
    private ImageView fotoInput;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_informacoes_estabelecimento, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        listaCategorias = getResources().getStringArray(R.array.lista_categorias);

        this.nomeInput = (EditText) view.findViewById(R.id.nome_estabelecimento);
        this.descricaoInput = (EditText) view.findViewById(R.id.descricao);
        this.categoriaInput = (Spinner) view.findViewById(R.id.categoria);
        this.fotoInput = (ImageView) view.findViewById(R.id.foto_estabelecimento);


        initAdapters();
        initListeners();

    }

    private void initAdapters(){

        //Adapter para a lista de categorias

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, listaCategorias);
        this.categoriaInput.setAdapter(adapter);

    }

    private void initListeners(){
        this.fotoInput.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.foto_estabelecimento:
                showOptions();
                break;
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which){
            case 0:
                //Tirar Foto
                Intent intentTirarFoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                if (intentTirarFoto.resolveActivity(getActivity().getPackageManager()) != null) {
                    File imageFile = createImageFile();
                    if (imageFile != null) {
                        this.imageFilePath = imageFile.getAbsolutePath();
                        intentTirarFoto.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFile));
                        startActivityForResult(intentTirarFoto, REQUEST_IMAGE_CAPTURE);
                    }
                }

                dialog.dismiss();
                break;
            case 1:
                //Escolher Foto
                Intent intentEscolherFoto = new Intent(Intent.ACTION_GET_CONTENT);
                intentEscolherFoto.setType("image/*");
                if (intentEscolherFoto.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivityForResult(intentEscolherFoto, REQUEST_IMAGE_GET);
                }
                dialog.dismiss();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode){
            case REQUEST_IMAGE_CAPTURE:
                if (resultCode == Activity.RESULT_OK){
                    this.fotoEstabelecimento = BitmapFactory.decodeFile(this.imageFilePath);
                    this.fotoInput.setImageBitmap(this.fotoEstabelecimento);
                }
                break;
            case REQUEST_IMAGE_GET:
                if (resultCode == Activity.RESULT_OK){
                    try {
                        Uri fotoEscolhida = data.getData();
                        this.fotoEstabelecimento = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), fotoEscolhida);
                        this.fotoInput.setImageBitmap(this.fotoEstabelecimento);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    private void showOptions(){

        String [] opcoesFotos = getResources().getStringArray(R.array.opcoes_foto);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, opcoesFotos);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setAdapter(adapter, this);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    private File createImageFile(){
        try {
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String imageFileName = "IMG_" + timeStamp + "_";
            File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            File imageFile = File.createTempFile(imageFileName, ".jpg", storageDir);
            return imageFile;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getNomeEstabelecimento(){
        if (this.nomeInput.getText() != null){
            return this.nomeInput.getText().toString();
        } else {
            return null;
        }
    }

    public String getDescricaoEstabelecimento(){
        if (this.descricaoInput.getText() != null){
            return this.descricaoInput.getText().toString();
        } else {
            return "";
        }
    }

    public String getCategoriaEstabelecimento(){
        if (this.categoriaInput.getSelectedItem() instanceof String) {
            return (String)this.categoriaInput.getSelectedItem();
        } else {
            return null;
        }
    }

    public byte [] getFotoEstabelecimento(){
        if (this.fotoEstabelecimento != null){
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            this.fotoEstabelecimento.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            return bos.toByteArray();
        } else {
            return new byte[0];
        }
    }

}
