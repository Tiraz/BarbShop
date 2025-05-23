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

import java.util.ArrayList;
import java.util.List;

public class DialogCriarServico extends DialogFragment {

    //Variáveis de controle
    DialogCriarServico escutar;
    boolean cadastrarLoja;
    Button btnCancelar, btnSalvar;
    AdaptadorFragGerServicos adaptador;
    ControleDados contDados = ControleDados.getInstance();

    //Variáveis de informação
    EditText txtNome, txtValor;

    public void setAdaptador(AdaptadorFragGerServicos adaptador) {
        this.adaptador = adaptador;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        //Criando o dialog----------------------------------------------------------------
        AlertDialog.Builder bilder = new AlertDialog.Builder(requireContext());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_criar_servico_layout, null);
        AlertDialog dialog = bilder.setView(view).create();
        //----------------------------------------------------------------------------------

        //Apresentação Java + XML
        btnCancelar = view.findViewById(R.id.btnCancelarCriaServ);
        btnSalvar = view.findViewById(R.id.btnCriarSevice);

        txtNome = view.findViewById(R.id.txtCriarServNome);
        txtValor = view.findViewById(R.id.txtCriarServValor);

        //Botões---------------------------------------------------------------------
        //Botão Salvar
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Instanciando DAOServiço
                DaoServicoDAO daoServ = contDados.getDaoServ(getContext());

                //construindo o objeto
                float valor = Float.parseFloat(txtValor.getText().toString());
                String nome = txtNome.getText().toString();
                ObjServico servico = new ObjServico(nome, valor);

                //Adicionando serviço ao banco
                daoServ.inserir(servico);

                //Atualizar lista
                List<ObjServico> listaNova = daoServ.listarTodos();
                adaptador.atualizarLista(listaNova);

                //Fechar Dialog
                dialog.dismiss();

            }
        });

        //Botão Cancelar
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });




        return dialog;
    }
}
