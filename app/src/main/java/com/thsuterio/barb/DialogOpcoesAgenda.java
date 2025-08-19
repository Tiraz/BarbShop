package com.thsuterio.barb;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.function.Consumer;

public class DialogOpcoesAgenda extends DialogFragment {

    //Elementos de controle
    private Button btnFinalizar;
    private Button btnAtualiza;
    private Button btnCancelar;
    private Button btnFechar;

    //Interface para devolver escolha
    IDevolverEscolhaAgendada devolver;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        //Crianção dialog---------------------------------------
        AlertDialog.Builder bilder = new AlertDialog.Builder(requireContext());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_opcoes_agenda_layout, null);
        AlertDialog dialog = bilder.setView(view).create();
        //------------------------------------------------------

        //Java + XML
        //btnAtualiza = view.findViewById(R.id.btnAtualizarTrab);
        btnCancelar = view.findViewById(R.id.btnCancelarTrab);
        btnFinalizar = view.findViewById(R.id.btnFinalizarTrab);
        btnFechar = view.findViewById(R.id.btnFecharJanelaTrab);
        //--------------------------------------------------------

        //Botoes
        //finalizar
        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                devolver.escolhaAgendada(1);
                dialog.dismiss();
            }
        });

        //Cancelar
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                devolver.escolhaAgendada(3);
                dialog.dismiss();
            }
        });
        //Fechar
        btnFechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                devolver.escolhaAgendada(0);
                dialog.dismiss();
            }
        });

        //----------------------------


        return dialog;
    }

    public Consumer<Integer> escolha;
}
