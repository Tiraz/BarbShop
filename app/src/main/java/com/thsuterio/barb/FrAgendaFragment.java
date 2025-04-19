package com.thsuterio.barb;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class FrAgendaFragment extends Fragment implements DialogAgendarInputFlagment.DialogLister{
    RecyclerView recyclerView;
    List<ObjAgendado> list_agendado = new ArrayList<>();
    Button btn_novo_agendamento;


    CalendarView calendarView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fr_agenda, container, false);

        //Apresentação de variaveis
        recyclerView = view.findViewById(R.id.recFragmentAgenda);
        btn_novo_agendamento = view.findViewById(R.id.btnNovoAgendamento);
        calendarView = view.findViewById(R.id.dtpAgenda);


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                int ajuste_mes = i1 + 1;
                Toast.makeText(getContext(), " data: " + i2 + "/" + ajuste_mes + "/" + i, Toast.LENGTH_SHORT).show();
            }
        });



        btn_novo_agendamento.setOnClickListener(v -> {
            DialogAgendarInputFlagment dialog = new DialogAgendarInputFlagment();
            dialog.show(getChildFragmentManager(), "Teste");
        });



        /*adicionarAgenda("Hugo", "50", "xx/xx/xxxx", "00:00");
        adicionarAgenda("Pedro", "30", "xx/xx/xxxx", "00:00");
        adicionarAgenda("João", "110", "xx/xx/xxxx", "00:00");
        adicionarAgenda("Marcos", "70", "xx/xx/xxxx", "00:00");*/

        AdaptadorFragAgenda adaptadorFragAgenda = new AdaptadorFragAgenda(getContext(), ControleDados.getInstance().lista_agendado_cd);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));

        recyclerView.setAdapter(adaptadorFragAgenda);

        return view;
    }


    private void adicionarAgenda(String nome, String valor, String dia, String hora){
        String textNome = "Nome: " + nome;
        String textValor = "Valor: R$" + valor + ",00";
        String textDia = "Dia: " + dia;
        String textHora = "Hora: " + hora;
        list_agendado.add(
                new ObjAgendado(textDia,textHora, textNome,textValor, avatarRandom())
        );
    }

    private int avatarRandom(){
        Random gerador = new Random();
        int num = gerador.nextInt(6);
        switch (num){
            case 0:
                return R.drawable.person_um;
            case 1:
                return R.drawable.person_dois;
            case 2:
                return R.drawable.person_tres;
            case 3:
                return R.drawable.person_quatro;
            case 4:
                return R.drawable.person_cinco;
        }
        return R.drawable.person_um;
    }

    @Override
    public void onInputReceived(int codigo, float vl_total) {
        Toast.makeText(getContext(), codigo, Toast.LENGTH_SHORT).show();
    }
}