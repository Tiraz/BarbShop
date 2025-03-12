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


public class FrAgendaFragment extends Fragment {
    RecyclerView recyclerView;
    List<ObjAgendado> list_agendado = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fr_agenda, container, false);

        recyclerView = view.findViewById(R.id.recFragmentAgenda);


        adicionarAgenda("Hugo", "50", "xx/xx/xxxx", "00:00");
        adicionarAgenda("Pedro", "30", "xx/xx/xxxx", "00:00");
        adicionarAgenda("João", "110", "xx/xx/xxxx", "00:00");
        adicionarAgenda("Marcos", "70", "xx/xx/xxxx", "00:00");

        AdaptadorFragAgenda adaptadorFragAgenda = new AdaptadorFragAgenda(getContext(), list_agendado);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));

        recyclerView.hasFixedSize();

        recyclerView.setAdapter(adaptadorFragAgenda);

        return view;
    }


    private void adicionarAgenda(String nome, String valor, String dia, String hora){
        String textNome = "Nome: " + nome;
        String textValor = "Valor: R$" + valor;
        String textDia = "Dia: " + dia;
        String textHora = "Hora: " + hora;
        list_agendado.add(
                new ObjAgendado(textDia,textHora, textNome,textValor)
        );
    }

}