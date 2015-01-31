package com.android.quemfaz.dados;

import android.util.Log;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by nicolle on 31/01/15.
 */
public class RepositorioUsuario {

    public void cadastrarUsuario(String nome, String email, String senha){
        ParseUser usuario = ParseUser.getCurrentUser();
        usuario.setUsername(nome);
        usuario.setEmail(email);
        usuario.setPassword(senha);

        usuario.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null){
                    Log.d("RepositorioUsuario", "Erro ao cadastrar usuario");
                }
            }
        });
    }
}
