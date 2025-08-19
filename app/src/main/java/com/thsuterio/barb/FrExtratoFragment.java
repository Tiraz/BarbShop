package com.thsuterio.barb;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class FrExtratoFragment extends Fragment {

    RecyclerView recyclerView;
    List<ObjExtrato> lista_extrato = new ArrayList<>();
    AdaptadorFragExtrato adaptador_frag_extrato;
    /**Banco Local**/
    DaoExtrato daoExtrato;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fr_extrato, container, false);

        //Apresentação Java + XML
        recyclerView = view.findViewById(R.id.recFragmentExtrato);

        //Instancias
        daoExtrato = new DaoExtrato(requireContext());



        lista_extrato = new ArrayList<>(daoExtrato.readExtratos());

        adaptador_frag_extrato = new AdaptadorFragExtrato(getContext(),lista_extrato);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));

        recyclerView.hasFixedSize();

        recyclerView.setAdapter(adaptador_frag_extrato);


        return view;
    }

    @Override
    public void onResume() {

        // Limpa a lista atual
        lista_extrato.clear();

        // Adiciona os novos itens
        lista_extrato.addAll(daoExtrato.readExtratos());

        // Notifica o adaptador que os dados mudaram
        adaptador_frag_extrato.notifyDataSetChanged();

        super.onResume();
    }
}