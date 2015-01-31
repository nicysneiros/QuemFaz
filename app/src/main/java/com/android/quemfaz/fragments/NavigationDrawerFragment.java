package com.android.quemfaz.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.quemfaz.R;
import com.android.quemfaz.adapters.MenuListAdapter;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseUser;


public class NavigationDrawerFragment extends Fragment {

    /** VIEWS **/
    private ImageView usuarioProfilePic;
    private TextView usuarioNome;
    private ListView itensMenu;
    private Button entreCadastre;
    private LinearLayout dadosUsuario;

    private boolean logado;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        initAdapters();

        if(!ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())){
            //Usuario não logado
            logado = false;
        } else {
            logado = true;
        }

        if (logado){
            this.usuarioProfilePic.setVisibility(ImageView.VISIBLE);
            this.usuarioNome.setVisibility(TextView.VISIBLE);
            this.entreCadastre.setVisibility(Button.GONE);
        } else {
            this.usuarioProfilePic.setVisibility(ImageView.GONE);
            this.usuarioNome.setVisibility(TextView.GONE);
            this.entreCadastre.setVisibility(Button.VISIBLE);
        }

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

}
