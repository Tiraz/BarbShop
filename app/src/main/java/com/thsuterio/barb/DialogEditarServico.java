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

public class DialogEditarServico extends DialogFragment {


    //Variáveis de controle
    Button btnCancelar, btnExcluir, btnSalvar;
    ControleDados contDados = ControleDados.getInstance();
    private Context context;
    AdaptadorFragGerServicos adaptador;

    //Variáveis de informações
    EditText txtNome, txtValor;
    int codigo;
    private String nome;
    private double valor;

    public DialogEditarServico(Context context, String nome, double valor, int codigo , AdaptadorFragGerServicos adaptador) {
        this.context = context;
        this.nome = nome;
        this.valor = valor;
        this.codigo = codigo;
        this.adaptador = adaptador;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        //Criando o dialog----------------------------------------------------------------
        AlertDialog.Builder bilder = new AlertDialog.Builder(requireContext());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_editar_servico_layout, null);
        AlertDialog dialog = bilder.setView(view).create();
        //----------------------------------------------------------------------------------

        //Apresentações Java + XML
        txtNome = view.findViewById(R.id.txtEditServiceNome);
        txtValor = view.findViewById(R.id.txtEditServiceValor);

        btnCancelar = view.findViewById(R.id.btnEditarServicoCancelar);
        btnExcluir = view.findViewById(R.id.btnEditarServicoExcluir);
        btnSalvar = view.findViewById(R.id.btnEditarServicoSalvar);

        //Formatar valor
        String fmtValor = contDados.valorFormatado(valor);

        //Passando as informaçoes iniciais
        txtNome.setText(nome);
        txtValor.setText(fmtValor);

        //Criando o objeto com as informaçoes
        ObjServico servico = new ObjServico(nome, valor);
        servico.setIdServico(codigo);

        //Botões------------------------------------------------------------------------
        //Botão Cancelar atualização
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Atualizar o serviço
                contDados.getDaoServ(context).atualizar(servico);
                //atualiza a lista de serviços
                adaptador.atualizarLista(contDados.getDaoServ(context).listarTodos());
                //fecha o dialog
                dismiss();
            }
        });

        //Botão Excluir
        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //deleta o serviço selecionado
                contDados.getDaoServ(context).deletar(codigo);
                //atualiza a lista de serviços
                adaptador.atualizarLista(contDados.getDaoServ(context).listarTodos());
                //fecha o dialog
                dismiss();
            }
        });

        //Botão Atualizar dados
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return dialog;
    }
}
