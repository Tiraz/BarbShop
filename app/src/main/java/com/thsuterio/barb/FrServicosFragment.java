package com.thsuterio.barb;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;


public class FrServicosFragment extends Fragment {

   TabLayout tab_lay_Fragment_servico;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fr_servicos, container, false);

        tab_lay_Fragment_servico = view.findViewById(R.id.tabLayoutFragmentServico);

        carregarFragmento(new FrGerenciarServicoFragment());

        tab_lay_Fragment_servico.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment escolhido = null;

                switch (tab.getPosition()){
                    case 0:
                        escolhido = new FrGerenciarServicoFragment();
                        break;
                }

                if(escolhido != null){
                    carregarFragmento(escolhido);
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



        return view;
    }

    private void carregarFragmento(Fragment fragment){
        FragmentTransaction mudar = getChildFragmentManager().beginTransaction();
        mudar.replace(R.id.layContainerFragmentServico, fragment);
        mudar.commit();
    }

}