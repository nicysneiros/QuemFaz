package com.android.quemfaz.fragments;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.quemfaz.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by nicolle on 20/02/15.
 */
public class LocalizacaoEstabelecimentoFragment extends Fragment implements View.OnClickListener, GoogleMap.OnMapLongClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, GoogleMap.OnMyLocationButtonClickListener {

    private LatLng posicaoEstabelecimento;
    private Marker marker;

    private GoogleApiClient googleApiClient;

    /** VIEWS **/
    private EditText pesquisarEnderecoInput;
    private ImageButton pesquisarEnderecoButton;
    private GoogleMap mapa;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_localizacao_estabelecimento, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.pesquisarEnderecoInput = (EditText) view.findViewById(R.id.pesquisar_endereco);
        this.pesquisarEnderecoButton = (ImageButton) view.findViewById(R.id.pesquisar_endereco_button);
        this.pesquisarEnderecoButton.setOnClickListener(this);

        this.googleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        this.googleApiClient.connect();

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        this.mapa = mapFragment.getMap();
        if(this.mapa != null){
            this.mapa.setMyLocationEnabled(true);
            this.mapa.setOnMapLongClickListener(this);
            this.mapa.setOnMyLocationButtonClickListener(this);

        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.pesquisar_endereco_button:

                if (this.pesquisarEnderecoInput.getText() != null) {
                    String enderecoPesquisado = this.pesquisarEnderecoInput.getText().toString();
                    Log.d("LocalizacaoFragment", "Endereço Pesquisado: " + enderecoPesquisado);
                    LatLng posicao = getPositionFromAddress(enderecoPesquisado);

                    if (posicao != null) {
                        this.posicaoEstabelecimento = posicao;

                        if (this.marker != null) this.marker.remove();
                        MarkerOptions options = new MarkerOptions().position(posicao);
                        this.marker = this.mapa.addMarker(options);
                        this.mapa.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(posicao, 14, 0,0)));
                    } else {
                        Toast.makeText(getActivity(), "Endereço não encontrado!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Digite um endereço para pesquisar!", Toast.LENGTH_LONG).show();
                }

                break;
        }
    }

    private LatLng getPositionFromAddress(String endereco){
        try {

            Geocoder geocoder = new Geocoder(getActivity().getApplicationContext(), Locale.getDefault());
            List<Address> enderecos = geocoder.getFromLocationName(endereco, 1);
            if (enderecos != null && enderecos.size() > 0){
                Address enderecoEncontrado = enderecos.get(0);
                LatLng posicao = new LatLng(enderecoEncontrado.getLatitude(), enderecoEncontrado.getLongitude());
                return posicao;
            } else {
                return null;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        if (latLng != null){
            this.posicaoEstabelecimento = latLng;

            this.pesquisarEnderecoInput.setText("");

            if (this.marker != null) this.marker.remove();
            MarkerOptions options = new MarkerOptions().position(latLng);
            this.marker = this.mapa.addMarker(options);

        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        Location posicaoAtual = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        if (posicaoAtual != null) {
            LatLng posicao = new LatLng(posicaoAtual.getLatitude(), posicaoAtual.getLongitude());
            this.posicaoEstabelecimento = posicao;
            this.mapa.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(posicao, 14, 0,0)));
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public boolean onMyLocationButtonClick() {

        Location posicaoAtual = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        if (posicaoAtual != null) {
            LatLng posicao = new LatLng(posicaoAtual.getLatitude(), posicaoAtual.getLongitude());
            this.posicaoEstabelecimento = posicao;

            this.pesquisarEnderecoInput.setText("");

            if (this.marker != null) this.marker.remove();
            MarkerOptions options = new MarkerOptions().position(posicao);
            this.marker = this.mapa.addMarker(options);
        }
        return false;
    }
}
