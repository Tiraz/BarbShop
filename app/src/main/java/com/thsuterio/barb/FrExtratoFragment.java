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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fr_extrato, container, false);

        recyclerView = view.findViewById(R.id.recFragmentExtrato);

        adicionarExtrato(300.00,"01/01/2025",10);
        adicionarExtrato(150.00,"02/01/2025",5);
        adicionarExtrato(00.00,"03/01/2025",0);
        adicionarExtrato(300.00,"04/01/2025",10);
        adicionarExtrato(300.00,"05/01/2025",10);
        adicionarExtrato(600.00,"06/01/2025",20);
        adicionarExtrato(00.00,"07/01/2025",0);
        adicionarExtrato(300.00,"08/01/2025",10);
        adicionarExtrato(300.00,"09/01/2025",10);
        adicionarExtrato(300.00,"10/01/2025",10);
        adicionarExtrato(300.00,"11/01/2025",10);
        adicionarExtrato(00.00,"12/01/2025",0);
        adicionarExtrato(300.00,"13/01/2025",10);
        adicionarExtrato(300.00,"14/01/2025",10);
        adicionarExtrato(300.00,"15/01/2025",10);
        adicionarExtrato(300.00,"16/01/2025",10);
        adicionarExtrato(150.00,"17/01/2025",5);
        adicionarExtrato(300.00,"18/01/2025",10);
        adicionarExtrato(30.00,"19/01/2025",1);
        adicionarExtrato(300.00,"20/01/2025",10);
        adicionarExtrato(150.00,"21/01/2025",5);
        adicionarExtrato(300.00,"22/01/2025",10);
        adicionarExtrato(300.00,"23/01/2025",10);
        adicionarExtrato(300.00,"24/01/2025",10);
        adicionarExtrato(150.00,"25/01/2025",5);
        adicionarExtrato(300.00,"26/01/2025",10);
        adicionarExtrato(300.00,"27/01/2025",10);
        adicionarExtrato(300.00,"28/01/2025",10);
        adicionarExtrato(300.00,"29/01/2025",10);
        adicionarExtrato(300.00,"30/01/2025",10);
        adicionarExtrato(300.00,"31/01/2025",10);




        AdaptadorFragExtrato adaptador_frag_extrato = new AdaptadorFragExtrato(getContext(),lista_extrato);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));

        recyclerView.hasFixedSize();

        recyclerView.setAdapter(adaptador_frag_extrato);


        return view;
    }

    private void adicionarExtrato(double valor, String dia, int quantidade){
        lista_extrato.add(
                new ObjExtrato(dia,quantidade, valor)
        );
    }
}