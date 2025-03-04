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


public class FrGerenciarServicoFragment extends Fragment {

   RecyclerView recycler_view;
   List<ObjServico> lista_servicos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fr_gerenciar_servico, container, false);

        recycler_view = view.findViewById(R.id.recFragmentGerServico);

        lista_servicos = new ArrayList<>();

        lista_servicos.add(
                new ObjServico("Corte", 30, 40)
        );

        lista_servicos.add(
                new ObjServico("Sombrancelha", 15, 15)
        );

        lista_servicos.add(
                new ObjServico("Barba", 30, 35)
        );

        lista_servicos.add(
                new ObjServico("Luzes", 45, 40)
        );
        lista_servicos.add(
                new ObjServico("Corte", 30, 40)
        );

        lista_servicos.add(
                new ObjServico("Sombrancelha", 15, 15)
        );

        lista_servicos.add(
                new ObjServico("Barba", 30, 35)
        );

        lista_servicos.add(
                new ObjServico("Luzes", 45, 40)
        );

        lista_servicos.add(
                new ObjServico("Barba", 30, 35)
        );

        lista_servicos.add(
                new ObjServico("Luzes", 45, 40)
        );
        lista_servicos.add(
                new ObjServico("Corte", 30, 40)
        );

        lista_servicos.add(
                new ObjServico("Sombrancelha", 15, 15)
        );

        lista_servicos.add(
                new ObjServico("Barba", 30, 35)
        );

        lista_servicos.add(
                new ObjServico("Luzes", 45, 40)
        );

        AdaptadorFragGerServicos adaptador_servicos = new AdaptadorFragGerServicos(getContext(),lista_servicos);

        recycler_view.setLayoutManager(new GridLayoutManager(getContext(),2));

        recycler_view.hasFixedSize();

        recycler_view.setAdapter(adaptador_servicos);
        return view;
    }
}