package com.thsuterio.barb;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
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

    IDevolverEscolhaAgendada escolhaAgendada;

    /**Banco Local**/
    DaoAgenda daoAgenda;
    DaoExtrato daoExtrato;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fr_agenda, container, false);

        //Apresentação de variaveis
        recyclerView = view.findViewById(R.id.recFragmentAgenda);
        btn_novo_agendamento = view.findViewById(R.id.btnNovoAgendamento);

        //Instancias
        daoAgenda = new DaoAgenda(requireContext());
        daoExtrato = new DaoExtrato(requireContext());


        int c = ControleDados.getInstance().lista_escolha_cd.size();
        //Botão agendar novo horario
        btn_novo_agendamento.setOnClickListener(v -> {
            //Toast.makeText(getContext(), ": " + c, Toast.LENGTH_SHORT).show();
            DialogAgendarInputFlagment dialog = new DialogAgendarInputFlagment();
            dialog.show(getChildFragmentManager(), "NovoHorario");
        });


        adaptadorFragAgenda = new AdaptadorFragAgenda(getContext(), ControleDados.getInstance().lista_agendado_cd,
                new InExtratoAdmin() {
                    @Override
                    public void criarExtrato(ObjAgendado agendado) {

                        DialogOpcoesAgenda dialogOpcoesAgenda = new DialogOpcoesAgenda();
                        dialogOpcoesAgenda.show(getChildFragmentManager(), "Opcao");
                        dialogOpcoesAgenda.devolver = escolha -> {

                            switch (escolha) {
                                case 1:
                                    adicionarExtrato(agendado);
                                    Toast.makeText(getContext(), "Serviço finalizado!", Toast.LENGTH_SHORT).show();
                                    break;
                                case 2:
                                    break;
                                case 3:
                                    exculirAgendado(agendado);
                                    break;
                                case 0:

                                    break;
                                default:
                                    Toast.makeText(getContext(), "Default", Toast.LENGTH_SHORT).show();
                                    break;
                            }

                        };

                    }
                });

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

        adaptadorFragAgenda.notifyDataSetChanged();
        adaptadorFragAgenda.atualizarListaAgendados(daoAgenda.readAgenda());
    }

    // Adicionar ao extrato
    private void adicionarExtrato(ObjAgendado agendado) {

         //Criando o objExtrato para inserir no banco
         ObjExtrato extrato = new ObjExtrato();

         extrato.setNome_extrato(agendado.getNome_agendado());
         extrato.setDia_extrato(agendado.getDia_agendado());
         extrato.setHora_extrato(agendado.getHora_agendado());
         extrato.setValor_extrato(agendado.getValor_agendado());

         //Create no banco
         daoExtrato.inserirExtrato(extrato);

         //excluindo agendado finalizado
         exculirAgendado(agendado);
    }

    //Excluir agendado
    private void exculirAgendado(ObjAgendado agendado){

        daoAgenda.deletar(agendado.getCodigo());

        adaptadorFragAgenda.atualizarListaAgendados(daoAgenda.readAgenda());
        adaptadorFragAgenda.notifyDataSetChanged();
    }


    @Override
    public void onInputReceived(double vl_total, String nome, String dia, String hora, List<ObjServicoEscolha> listaServ) {
        adicionarAgenda(nome, vl_total,dia, hora, listaServ);
        adaptadorFragAgenda.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        adaptadorFragAgenda.atualizarListaAgendados(daoAgenda.readAgenda());
    }




}