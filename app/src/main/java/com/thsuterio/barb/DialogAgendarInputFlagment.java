package com.thsuterio.barb;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DialogAgendarInputFlagment extends DialogFragment {
    public interface DialogLister{
        void onInputReceived(String vl_total, String nome, String dia, String hora);
    }

    MaterialDatePicker datePicker;
    MaterialTimePicker timePicker;

    DialogLister lister;

    AdaptadorDialogAgendar adp_dialog_agendar;
    AdaptadorDialogEscolhas adp_dialog_escolhas;

    RecyclerView rec_servicos;
    RecyclerView rec_escolhas;

    EditText edt_nome;
    TextView txt_valor_total;

    Button btn_salvar;
    Button btn_dia;
    Button btn_hora;

    float valor_total;
    String dia_passar, hora_passar;

    List<ObjServicoEscolha> lista_opcoes, lista_escolhas;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Fragment parent_fragment = getParentFragment();
        try {
            lister = (DialogLister) parent_fragment;

        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " deve implementar DialogListern");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_agendar_input_layout,null);

        //variaveis para iniciar a data
        int hora_atual = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int minuto_atual = Calendar.getInstance().get(Calendar.MINUTE);

        //construção data e time piker
        timePicker = new MaterialTimePicker.Builder().setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(hora_atual)
                .setMinute(minuto_atual)
                .setTitleText("Selecionar Hora").build();
        datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Selecionar Dia").build();

        //Apresentação XML + Java
        edt_nome = view.findViewById(R.id.edtNomeAgendar);
        rec_servicos = view.findViewById(R.id.recServicosAgendar);
        rec_escolhas = view.findViewById(R.id.recServicosEscolhasAgendar);
        btn_salvar = view.findViewById(R.id.btnSalvarAgenda);
        btn_dia = view.findViewById(R.id.btnDataAgendar);
        btn_hora = view.findViewById(R.id.btnHoraAgendar);
        txt_valor_total = view.findViewById(R.id.txtValorTotalDialogAgendar);

        //Instancias
        lista_opcoes = new ArrayList<>(ControleDados.getInstance().lista_escolha_cd);
        lista_escolhas = new ArrayList<>();

        //Setando Informaçoes iniciais
        btn_dia.setText(diaFormatado(diaHoje()));
        btn_hora.setText(horaFormatado(hora_atual,minuto_atual));
        valor_total = 0;
        txt_valor_total.setText(valorFormatado(valor_total));

        //Adaptadores------
        adp_dialog_agendar = new AdaptadorDialogAgendar(getContext(), lista_opcoes
                , new AdaptadorDialogAgendar.listaOpcoes() {
            @Override
            public void itemEscolhido(ObjServicoEscolha servico_escolha) {

                adicionarEscolha(lista_escolhas, servico_escolha);
                removerEscolha(servico_escolha, lista_opcoes);
                int index = lista_escolhas.indexOf(servico_escolha);
                //atualizando valor total
                txt_valor_total.setText(valorFormatado
                        (valor_total += lista_escolhas.get(index).getValor_escolha()));
            }
        });

        adp_dialog_escolhas = new AdaptadorDialogEscolhas(getContext(), lista_escolhas, new AdaptadorDialogEscolhas.listaEscolhido() {
            @Override
            public void servicoEscolhido(ObjServicoEscolha item_escolhido) {

                adicionarEscolha(lista_opcoes, item_escolhido);
                removerEscolha(item_escolhido, lista_escolhas);
                int index = lista_opcoes.indexOf(item_escolhido);
                //atualizando valor total
                txt_valor_total.setText(valorFormatado
                        (valor_total -= lista_opcoes.get(index).getValor_escolha()));
            }
        });

        //Ligando RecycleView
        rec_servicos.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        rec_servicos.setAdapter(adp_dialog_agendar);
        rec_escolhas.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false));
        rec_escolhas.setAdapter(adp_dialog_escolhas);

        AlertDialog dialog = builder.setView(view).create();
        //Seta a cor do dialog como transparente
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //Botões--------------------------
        btn_salvar.setOnClickListener(v -> {
            String input = edt_nome.getText().toString();

            if (lister != null) {
                lister.onInputReceived(valorFormatado(valor_total), input
                        ,btn_dia.getText().toString()
                        ,btn_hora.getText().toString());
                //reiniciandoLista();
            } else {
                // Log de erro para depuração
                System.err.println("Erro: lister está null!");
            }

            dialog.dismiss();
        });

        btn_dia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.show(getChildFragmentManager(),"dia");
            }
        });

        btn_hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePicker.show(getChildFragmentManager(),"hora");
            }
        });

        //Pegando a hora e dia escolhidos e passando para os botoes
        datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
            @Override
            public void onPositiveButtonClick(Long selection) {
                btn_dia.setText(diaFormatado(selection));
            }
        });
        timePicker.addOnPositiveButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_hora.setText(horaFormatado(timePicker.getHour(), timePicker.getMinute()));
            }
        });



        return dialog;
    }

    //Metodos de controle das listas
    //Entreda
    private void adicionarEscolha(List<ObjServicoEscolha> lista, ObjServicoEscolha escolha){
        //pega o index do item a ser removido;
        int index = lista_opcoes.indexOf(escolha);

        //adiciona objeto a lista
        lista.add(escolha);
        notificarAdapters();
        //Notifica o adapter sobre adicionar a escolha, tirar de opções
        //adp_dialog_escolhas.notifyItemInserted(lista_escolhas.size() - 1);
        //adp_dialog_agendar.notifyItemRemoved(index);

    }
    //Saída
    private void removerEscolha(ObjServicoEscolha escolha, List<ObjServicoEscolha> lista){
        //pega o index do item a ser removido;
        int index = lista_escolhas.indexOf(escolha);

        //adiciona objeto a lista
        lista.remove(escolha);
        notificarAdapters();
        //Notifica o adapter, inverso do metodo acima
        //adp_dialog_agendar.notifyItemInserted(lista_escolhas.size() - 1);
        //adp_dialog_escolhas.notifyItemRemoved(index);
    }

    //Metodo rapido de notificaçao de adapters
    private void notificarAdapters(){
        adp_dialog_escolhas.notifyDataSetChanged();
        adp_dialog_agendar.notifyDataSetChanged();
    }

    //OnResume carrega toda vez que a tela fica visivel (proximo do update ou start)
    @Override
    public void onResume() {
        super.onResume();
        //Atualizando listas
        adp_dialog_agendar.atualizarListaEscolhas(lista_opcoes);
        adp_dialog_escolhas.atualizarListaEscolhas(lista_escolhas);
    }

    //Função que retorna o dia atual em Long
    private Long diaHoje(){

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTimeInMillis();
    }


    //Metodo que devolve o dia formatado
    private String diaFormatado(Long dia){
        SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String dia_formatado = formatar.format(new Date(dia));
        return dia_formatado;
    }

    // Metodo que devolve a hora formatada
    private String horaFormatado(int horas, int minutos){

        //Instanciando calendario
        Calendar calendar = Calendar.getInstance();

        //Passando horas e minutos, em int, para o calendar instanciado
        calendar.set(Calendar.HOUR_OF_DAY,horas);
        calendar.set(Calendar.MINUTE,minutos);

        SimpleDateFormat formatar = new SimpleDateFormat("HH:mm",Locale.getDefault());
        String hora_formatada = formatar.format(calendar.getTime());

        return hora_formatada;
    }

    //Metodo para formatar em reais
    private String valorFormatado(float valor){
        //String fmt_valor = String.format("Valor Total: R$ %.2f", valor);
        return String.format("Valor Total: R$ %.2f", valor);
    }

    //Função para reiniciar a lista de opçoes
    private void reiniciandoLista(){
        lista_opcoes.clear();
        lista_opcoes = ControleDados.getInstance().lista_escolha_cd;
        adp_dialog_agendar.notifyDataSetChanged();
        Toast.makeText(getContext(), String.valueOf(lista_opcoes.size()) + "  " +
                String.valueOf(ControleDados.getInstance().lista_escolha_cd.size()), Toast.LENGTH_SHORT).show();
    }
}
