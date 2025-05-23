package com.thsuterio.barb;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;


public class FrGerenciarServicoFragment extends Fragment {

    //VAriáveis de controle
    RecyclerView recycler_view;
    Button btnNovoServico;
    DaoServicoDAO daoServ;
    AdaptadorFragGerServicos adaptador_servicos;

    //Variáveis de informaçoes
    List<ObjServico> listaServicos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fr_gerenciar_servico, container, false);

        //Apresentações Java + XML
        recycler_view = view.findViewById(R.id.recFragmentGerServico);
        btnNovoServico = view.findViewById(R.id.btnNovoServico);

        //Instancias
        daoServ = new DaoServicoDAO(getContext());
        //Carregando alista com as informaçoes do banco
        listaServicos = new ArrayList<>(daoServ.listarTodos());

        adaptador_servicos = new AdaptadorFragGerServicos(getContext(),listaServicos);

        recycler_view.setLayoutManager(new GridLayoutManager(getContext(),1));
        recycler_view.setAdapter(adaptador_servicos);

        //Botões--------------------------------------------------------
        //Botão novo serviço
        btnNovoServico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Chama o dialog fragment para preencher as informações do novo serviço
                DialogCriarServico dialog = new DialogCriarServico();
                dialog.setAdaptador(adaptador_servicos);
                dialog.show(getChildFragmentManager(), "Criar novo serviço");
            }
        });


        return view;
    }


    /*private void adicionarServico(String nome, float valor){
        String textNome = "Serviço: " + nome;
        ControleDados.getInstance().lista_servico_cd.add(
                new ObjServico(textNome, valor)
        );
        ControleDados.getInstance().lista_escolha.add(
                new ObjServicoEscolha(nome, valor)
        );
    }*/

}