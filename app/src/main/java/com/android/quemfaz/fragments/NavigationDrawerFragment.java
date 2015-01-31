package com.android.quemfaz.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.quemfaz.R;


public class NavigationDrawerFragment extends Fragment {

    /** VIEWS **/
    private ImageView usuarioProfilePic;
    private TextView usuarioNome;
    private ListView itensMenu;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        initViews(view);

    }

    private void initViews(View view){
        this.usuarioProfilePic = (ImageView) view.findViewById(R.id.usuario_profile_pic);
        this.usuarioNome = (TextView) view.findViewById(R.id.usuario_nome);
        this.itensMenu = (ListView) view.findViewById(R.id.menu);
    }

    private void initAdapters(){

    }

}
