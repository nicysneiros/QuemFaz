package com.android.quemfaz.dados;

import com.parse.ParseUser;

/**
 * Created by nicolle on 31/01/15.
 */
public class Estabelecimento {

    private String nome, descricao, categoria, telefone, email, paginaWeb, paginaFacebook;
    private byte[] foto;
    private double latitude, longitude;
    private ParseUser usuario;

    public Estabelecimento(String nome, String descricao, String categoria, String telefone, String email, String paginaWeb, String paginaFacebook, byte[] foto, double latitude, double longitude, ParseUser usuario) {

        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
        this.telefone = telefone;
        this.email = email;
        this.paginaWeb = paginaWeb;
        this.paginaFacebook = paginaFacebook;
        this.foto = foto;
        this.latitude = latitude;
        this.longitude = longitude;
        this.usuario=usuario;

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    public String getPaginaFacebook() {
        return paginaFacebook;
    }

    public void setPaginaFacebook(String paginaFacebook) {
        this.paginaFacebook = paginaFacebook;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public ParseUser getUsuario() {
        return usuario;
    }

    public void setUsuario(ParseUser usuario) {
        this.usuario = usuario;
    }
}
