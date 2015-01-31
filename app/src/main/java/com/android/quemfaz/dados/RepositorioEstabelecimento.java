package com.android.quemfaz.dados;

import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nicolle on 31/01/15.
 */
public class RepositorioEstabelecimento {

    private static final String ESTABELECTIMENTO="estabelecimento";
    private static final String NOME="nome";
    private static final String DESCRICAO="descricao";
    private static final String CATEGORIA="categoria";
    private static final String TELEFONE="telefone";
    private static final String EMAIL="email";
    private static final String PAGINA_WEB="pagina_web";
    private static final String PAGINA_FACEBOOK="pagina_facebook";
    private static final String LATITUDE="latitude";
    private static final String LONGITUDE="longitude";
    private static final String FOTO_NAME="foto.png";
    private static final String FOTO="foto";
    private static final String USUARIO="usuario";



    public void cadastrarEstabelecimento(Estabelecimento estabelecimento) throws com.parse.ParseException {

        ParseObject estab = new ParseObject(ESTABELECTIMENTO);

        ParseFile foto = new ParseFile(FOTO_NAME, estabelecimento.getFoto());

        foto.save();

        estab.put(NOME,estabelecimento.getNome());
        estab.put(DESCRICAO,estabelecimento.getDescricao());
        estab.put(CATEGORIA,estabelecimento.getCategoria());
        estab.put(TELEFONE, estabelecimento.getTelefone());
        estab.put(EMAIL, estabelecimento.getEmail());
        estab.put(PAGINA_WEB, estabelecimento.getPaginaWeb());
        estab.put(PAGINA_FACEBOOK, estabelecimento.getPaginaFacebook());
        estab.put(LONGITUDE,estabelecimento.getLongitude());
        estab.put(LATITUDE,estabelecimento.getLatitude());
        estab.put(FOTO,foto);
        estab.put(USUARIO,estabelecimento.getUsuario());


        estab.save();

    }

    public ArrayList<Estabelecimento> getEstabelecimentoByCategoria(String categoria) throws com.parse.ParseException {
        ArrayList<Estabelecimento> estabelecimentos = new ArrayList<Estabelecimento>();

        ParseQuery<ParseObject> query = ParseQuery.getQuery(ESTABELECTIMENTO);
        query.whereEqualTo(CATEGORIA,categoria);
        List<ParseObject> result = query.find();

        for(ParseObject object : result){
            estabelecimentos.add(fetchEstabelecimento(object));

        }
        return estabelecimentos;
    }

    public ArrayList<Estabelecimento> getEstabelecimentoByUsuario(ParseUser usuario) throws com.parse.ParseException {
        ArrayList<Estabelecimento> estabelecimentos = new ArrayList<Estabelecimento>();

        ParseQuery<ParseObject> query = ParseQuery.getQuery(ESTABELECTIMENTO);
        query.whereEqualTo(USUARIO,usuario);
        List<ParseObject> result = query.find();

        for(ParseObject object : result){
            estabelecimentos.add(fetchEstabelecimento(object));

        }
        return estabelecimentos;
    }

    public ArrayList<Estabelecimento> getEstabelecimentoByNameOrDescricao(String pesquisa) throws com.parse.ParseException {
        ArrayList<Estabelecimento> estabelecimentos = new ArrayList<Estabelecimento>();

        ParseQuery<ParseObject> query = ParseQuery.getQuery(ESTABELECTIMENTO);
        query.whereContains(NOME,pesquisa);

        ParseQuery<ParseObject> query2 = ParseQuery.getQuery(ESTABELECTIMENTO);
        query2.whereContains(DESCRICAO,pesquisa);

        List<ParseQuery<ParseObject>> querys= new ArrayList<ParseQuery<ParseObject>>();
        querys.add(query);
        querys.add(query2);


        List<ParseObject> result = ParseQuery.or(querys).find();

        for(ParseObject object : result){
            estabelecimentos.add(fetchEstabelecimento(object));

        }
        return estabelecimentos;
    }

    private Estabelecimento fetchEstabelecimento(ParseObject object) throws com.parse.ParseException {

        ParseFile fotoFile= object.getParseFile(FOTO);

        Estabelecimento estabelecimento;
        estabelecimento = new Estabelecimento(
                object.getString(NOME),
                object.getString(DESCRICAO),
                object.getString(CATEGORIA),
                object.getString(TELEFONE),
                object.getString(EMAIL),
                object.getString(PAGINA_WEB),
                object.getString(PAGINA_FACEBOOK),
                fotoFile.getData(),
                object.getDouble(LATITUDE),
                object.getDouble(LONGITUDE),
                object.getParseUser(USUARIO)
                );

        return estabelecimento;
    }

}
