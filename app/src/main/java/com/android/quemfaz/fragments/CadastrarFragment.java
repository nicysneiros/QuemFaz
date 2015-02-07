package com.android.quemfaz.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.quemfaz.R;
import com.android.quemfaz.dados.RepositorioUsuario;
import com.parse.ParseException;


public class CadastrarFragment extends Fragment implements View.OnClickListener {

    private EditText emailField;
    private EditText passwordField;
    private EditText nameField;
    private Button cadastrarButton;
    private RepositorioUsuario repositorioUsuario;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        repositorioUsuario= new RepositorioUsuario();

         nameField = (EditText) view.findViewById(R.id.nomeEditText);
         emailField = (EditText) view.findViewById(R.id.emailEditText);
         passwordField = (EditText) view.findViewById(R.id.passwordEditText);
         cadastrarButton = (Button) view.findViewById(R.id.cadastrarButton);
         cadastrarButton.setOnClickListener(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cadastrar, container, false);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cadastrarButton:
                String nome = this.nameField.getText().toString();
                String email = this.emailField.getText().toString();
                String password = this.passwordField.getText().toString();

                if(!nome.isEmpty() && !email.isEmpty() && !password.isEmpty()){

                    try {
                        repositorioUsuario.cadastrarUsuario(nome,email,password,"teste".getBytes());
                    } catch (ParseException e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(),"Erro",Toast.LENGTH_LONG).show();

                    }
                }else{
                    Toast.makeText(getActivity(),"Dados Incompletos",Toast.LENGTH_LONG).show();
                }

                break;
        }

    }
}
