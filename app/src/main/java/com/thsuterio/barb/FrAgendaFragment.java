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

    AdaptadorFragAgenda adaptadorFragAgenda;
    CalendarView calendarView;

    /**Banco Local**/
    DaoAgenda daoAgenda;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fr_agenda, container, false);

        //Apresentação de variaveis
        recyclerView = view.findViewById(R.id.recFragmentAgenda);
        btn_novo_agendamento = view.findViewById(R.id.btnNovoAgendamento);
        calendarView = view.findViewById(R.id.dtpAgenda);

        //Instancias
        daoAgenda = new DaoAgenda(requireContext());



        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                int ajuste_mes = i1 + 1;
                Toast.makeText(getContext(), " data: " + i2 + "/" + ajuste_mes + "/" + i, Toast.LENGTH_SHORT).show();
            }
        });

        int c = ControleDados.getInstance().lista_escolha_cd.size();
        //Botão agendar novo horario
        btn_novo_agendamento.setOnClickListener(v -> {
            //Toast.makeText(getContext(), ": " + c, Toast.LENGTH_SHORT).show();
            DialogAgendarInputFlagment dialog = new DialogAgendarInputFlagment();
            dialog.show(getChildFragmentManager(), "Teste");
        });


        adaptadorFragAgenda = new AdaptadorFragAgenda(getContext(), ControleDados.getInstance().lista_agendado_cd);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));

        recyclerView.setAdapter(adaptadorFragAgenda);

        return view;
    }


    private void adicionarAgenda(String nome, double valor, String dia, String hora, List<ObjServicoEscolha> list){
        String textNome = "Nome: " + nome;
        //String textValor = "Valor: R$" + valor + ",00";
        String textDia = "Dia: " + dia;
        String textHora = "Hora: " + hora;

        ObjAgendado objAgendado = new ObjAgendado(dia,hora, nome,valor);

        long id = daoAgenda.inserir(objAgendado);

        /*for (int i = 0; i < list.size(); i++) {

        }*/

        adaptadorFragAgenda.notifyDataSetChanged();
    }

    @Override
    public void onInputReceived(double vl_total, String nome, String dia, String hora, List<ObjServicoEscolha> listaServ) {
        adicionarAgenda(nome, vl_total,dia, hora, listaServ);
    }

    @Override
    public void onResume() {
        super.onResume();
        adaptadorFragAgenda.atualizarListaAgendados(daoAgenda.readAgenda());
    }
}