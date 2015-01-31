package com.android.quemfaz.dados;

import com.parse.ParseException;
import com.parse.ParseUser;

/**
 * Created by nicolle on 31/01/15.
 */
public class RepositorioUsuario {


    public void cadastrarUsuario(String nome, String email, String senha) throws ParseException {
        ParseUser usuario = ParseUser.getCurrentUser();
        usuario.setUsername(email);
        usuario.setEmail(email);
        usuario.setPassword(senha);
        usuario.put("name", nome);

        usuario.signUp();
    }

    public void loginUsuario(String email, String senha) throws ParseException {
        ParseUser.logIn(email, senha);
    }



}
