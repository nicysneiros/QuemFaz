package com.android.quemfaz.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.quemfaz.R;
import com.android.quemfaz.activity.LoginCadastroActivity;
import com.android.quemfaz.adapters.MenuListAdapter;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseUser;


public class NavigationDrawerFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    /** VIEWS **/
    private ImageView usuarioProfilePic;
    private TextView usuarioNome;
    private ListView itensMenu;
    private Button entreCadastre;
    private LinearLayout dadosUsuario;

    private boolean logado;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        initListeners();
        initAdapters();
        checkLogin();


    }

    public void checkLogin(){
        if(ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())){
            //Usuario não logado
            logado = false;
        } else {
            logado = true;
        }

        if (logado){
            this.usuarioProfilePic.setVisibility(ImageView.VISIBLE);
            this.usuarioNome.setVisibility(TextView.VISIBLE);
            this.entreCadastre.setVisibility(Button.GONE);
            ParseUser currentUser = ParseUser.getCurrentUser();
            this.usuarioNome.setText(currentUser.getUsername());
        } else {
            this.usuarioProfilePic.setVisibility(ImageView.GONE);
            this.usuarioNome.setVisibility(TextView.GONE);
            this.entreCadastre.setVisibility(Button.VISIBLE);
        }
    }

    private void initListeners() {

        this.entreCadastre.setOnClickListener(this);
        this.itensMenu.setOnItemClickListener(this);

    }

    private void initViews(View view){

        this.usuarioProfilePic = (ImageView) view.findViewById(R.id.usuario_profile_pic);
        this.usuarioNome = (TextView) view.findViewById(R.id.usuario_nome);
        this.itensMenu = (ListView) view.findViewById(R.id.menu);
        this.entreCadastre = (Button) view.findViewById(R.id.entre_cadastre);
        this.dadosUsuario = (LinearLayout) view.findViewById(R.id.dados_usuario);
    }

    private void initAdapters(){
        MenuListAdapter adapter = new MenuListAdapter(getActivity());
        this.itensMenu.setAdapter(adapter);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.entre_cadastre:
                Intent intent = new Intent(getActivity(), LoginCadastroActivity.class);
                startActivity(intent);
            break;
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 5:
                ParseUser.getCurrentUser().logOut();
                this.checkLogin();
                break;
        }
    }
}
