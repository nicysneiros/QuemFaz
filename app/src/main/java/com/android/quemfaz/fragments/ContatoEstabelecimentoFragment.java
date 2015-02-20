package com.android.quemfaz.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.android.quemfaz.R;

/**
 * Created by nicolle on 20/02/15.
 */
public class ContatoEstabelecimentoFragment extends Fragment {

    /** VIEWS **/
    private EditText telefoneInput, emailInput, paginaWebInput;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contato_estabelecimento, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.telefoneInput = (EditText) view.findViewById(R.id.telefone);
        this.emailInput = (EditText) view.findViewById(R.id.email_estabelecimento);
        this.paginaWebInput = (EditText) view.findViewById(R.id.pagina_web);

    }

    public String getTelefoneEstabelecimento(){
        if(this.telefoneInput.getText() != null) {
            return this.telefoneInput.getText().toString();
        } else {
            return "";
        }
    }

    public String getEmailEstabelecimento(){
        if (this.emailInput.getText() != null){
            return this.emailInput.getText().toString();
        } else {
            return "";
        }
    }

    public String getPaginaWebEstabelecimento(){
        if (this.paginaWebInput.getText() != null){
            return this.paginaWebInput.getText().toString();
        } else {
            return "";
        }
    }

}
