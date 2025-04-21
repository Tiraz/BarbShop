package com.thsuterio.barb;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DialogAgendarInputFlagment extends DialogFragment {
    public interface DialogLister{
        void onInputReceived(int codigo, float vl_total);
    }

    DialogLister lister;
    AdaptadorDialogAgendar adp_dialog_agendar;
    AdaptadorDialogEscolhas adp_dialog_escolhas;

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

        //Apresentação XML + Java
        EditText edt_nome = view.findViewById(R.id.edtNomeAgendar);
        RecyclerView rec_servicos = view.findViewById(R.id.recServicosAgendar);
        RecyclerView rec_escolhas = view.findViewById(R.id.recServicosEscolhasAgendar);
        Button btn_salvar = view.findViewById(R.id.btnSalvarAgenda);
        Button btn_dia = view.findViewById(R.id.btnDataAgendar);
        Button btn_hora = view.findViewById(R.id.btnHoraAgendar);


        //Instancias
        lista_opcoes = ControleDados.getInstance().lista_escolha;
        lista_escolhas = new ArrayList<>();

        //Adaptadores------
        adp_dialog_agendar = new AdaptadorDialogAgendar(getContext(), lista_opcoes
                , new AdaptadorDialogAgendar.listaOpcoes() {
            @Override
            public void itemEscolhido(ObjServicoEscolha servico_escolha) {
                adicionarEscolha(lista_escolhas, servico_escolha);
                removerEscolha(servico_escolha, lista_opcoes);
            }
        });

        adp_dialog_escolhas = new AdaptadorDialogEscolhas(getContext(), lista_escolhas, new AdaptadorDialogEscolhas.listaEscolhido() {
            @Override
            public void servicoEscolhido(ObjServicoEscolha item_escolhido) {
                adicionarEscolha(lista_opcoes, item_escolhido);
                removerEscolha(item_escolhido, lista_escolhas);
            }
        });

        //Ligando RecycleView
        rec_servicos.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        rec_servicos.setAdapter(adp_dialog_agendar);
        rec_escolhas.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false));
        rec_escolhas.setAdapter(adp_dialog_escolhas);

        AlertDialog dialog = builder.setView(view).create();


        btn_salvar.setOnClickListener(v -> {
            String input = edt_nome.getText().toString();

            if (lister != null) {
                lister.onInputReceived(123,123);
            } else {
                // Log de erro para depuração
                System.err.println("Erro: lister está null!");
            }

            dialog.dismiss();
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
}
