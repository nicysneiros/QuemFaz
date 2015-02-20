package com.android.quemfaz.fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
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

import java.util.concurrent.Executor;


public class LoginFragment extends Fragment implements View.OnClickListener {

    private EditText emailField;
    private EditText passwordField;
    private Button entrarButton;
    private RepositorioUsuario repositorioUsuario;

    ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        repositorioUsuario= new RepositorioUsuario();

        emailField = (EditText) view.findViewById(R.id.emailEditText);
        passwordField = (EditText) view.findViewById(R.id.passwordEditText);
        entrarButton = (Button) view.findViewById(R.id.entrarButton);
        entrarButton.setOnClickListener(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.entrarButton:
                /*
                String email = this.emailField.getText().toString();
                String password = this.passwordField.getText().toString();

                if( !email.isEmpty() && !password.isEmpty()){

                    try {
                        repositorioUsuario.loginUsuario(email,password);

                        Toast.makeText(getActivity(), "Usuario logado com sucesso", Toast.LENGTH_LONG).show();

                        getActivity().finish();


                    } catch (ParseException e) {
                        Toast.makeText(getActivity(),"Erro",Toast.LENGTH_LONG).show();

                    }
                }else{
                    Toast.makeText(getActivity(),"Dados Incompletos",Toast.LENGTH_LONG).show();
                }
                */

                progressDialog = new ProgressDialog(getActivity());
                progressDialog.setProgress(ProgressDialog.STYLE_SPINNER);
                progressDialog.setMessage("Carregando...");
                progressDialog.show();

                new efetuarLogin().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"");

                break;
        }
    }

    private class efetuarLogin extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... params) {
            String email = emailField.getText().toString();
            String password = passwordField.getText().toString();

            if( !email.isEmpty() && !password.isEmpty()){

                try {
                    repositorioUsuario.loginUsuario(email,password);

                    progressDialog.dismiss();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(), "Usuario logado com sucesso", Toast.LENGTH_LONG).show();
                        }
                    });

                    getActivity().finish();


                } catch (ParseException e) {

                    progressDialog.dismiss();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(), "Erro", Toast.LENGTH_LONG).show();
                        }
                    });

                }
            }else{

                progressDialog.dismiss();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "Dados Incompletos", Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }
    }
}
