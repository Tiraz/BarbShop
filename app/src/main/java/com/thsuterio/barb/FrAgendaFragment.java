package com.thsuterio.barb;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class FrAgendaFragment extends Fragment implements DialogAgendarInputFlagment.DialogLister{
    RecyclerView recyclerView;
    List<ObjAgendado> list_agendado = new ArrayList<>();
    Button btn_novo_agendamento;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fr_agenda, container, false);

        recyclerView = view.findViewById(R.id.recFragmentAgenda);

        btn_novo_agendamento = view.findViewById(R.id.btnNovoAgendamento);
        btn_novo_agendamento.setOnClickListener(v -> {
            DialogAgendarInputFlagment dialog = new DialogAgendarInputFlagment();
            dialog.show(getChildFragmentManager(), "Teste");
        });





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
        String textValor = "Valor: R$" + valor + ",00";
        String textDia = "Dia: " + dia;
        String textHora = "Hora: " + hora;
        list_agendado.add(
                new ObjAgendado(textDia,textHora, textNome,textValor)
        );
    }

    public void onInputReceived(String input){
        Toast.makeText(getContext(), input, Toast.LENGTH_SHORT).show();

    }

}