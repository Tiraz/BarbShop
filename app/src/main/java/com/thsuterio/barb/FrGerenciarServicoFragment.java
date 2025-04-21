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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fr_gerenciar_servico, container, false);

        recycler_view = view.findViewById(R.id.recFragmentGerServico);


        ControleDados.getInstance().adicionarServico("Luzes", 30.00f);
        ControleDados.getInstance().adicionarServico("Corte", 40.00f);
        ControleDados.getInstance().adicionarServico("Sombrancelha", 15.00f);
        ControleDados.getInstance().adicionarServico("Barba", 35.00f);


        AdaptadorFragGerServicos adaptador_servicos = new AdaptadorFragGerServicos(getContext(),ControleDados
                .getInstance().lista_servico_cd);

        recycler_view.setLayoutManager(new GridLayoutManager(getContext(),1));

        recycler_view.hasFixedSize();

        recycler_view.setAdapter(adaptador_servicos);
        return view;
    }

    /*private void adicionarServico(String nome, float valor){
        String textNome = "Serviço: " + nome;
        ControleDados.getInstance().lista_servico_cd.add(
                new ObjServico(textNome, valor)
        );
        ControleDados.getInstance().lista_escolha.add(
                new ObjServicoEscolha(nome, valor)
        );
    }*/

}