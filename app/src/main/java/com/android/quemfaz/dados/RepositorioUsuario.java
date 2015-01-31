package com.android.quemfaz.dados;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;

/**
 * Created by nicolle on 31/01/15.
 */
public class RepositorioUsuario {


    public void cadastrarUsuario(String nome, String email, String senha, byte[] foto ) throws ParseException {
        ParseUser usuario = ParseUser.getCurrentUser();
        ParseFile fotoPerfil = new ParseFile("perfil.png", foto);

        fotoPerfil.save();

        usuario.setUsername(email);
        usuario.setEmail(email);
        usuario.setPassword(senha);
        usuario.put("name", nome);
        usuario.put("foto",fotoPerfil);

        usuario.signUp();
    }

    public void loginUsuario(String email, String senha) throws ParseException {
        ParseUser.logIn(email, senha);
    }
}
