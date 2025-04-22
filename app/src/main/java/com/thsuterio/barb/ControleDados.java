package com.thsuterio.barb;

import java.util.ArrayList;
import java.util.List;

public class ControleDados {

    //Atribto estatico que armazena a unica intancia da classe.
    private static final ControleDados INSTANCE = new ControleDados();

    //Dados para guardar.
    List<ObjAgendado> lista_agendado_cd = new ArrayList<>();
    List<ObjExtrato> lista_extrato_cd = new ArrayList<>();
    List<ObjServico> lista_servico_cd = new ArrayList<>();
    List<ObjServicoEscolha> lista_escolha_cd = new ArrayList<>();

    //Construtor privado que impede que outras classes criem novos objetos ControleDados
    private ControleDados(){};

    //Metodo publico para acessar
    public static ControleDados getInstance(){
        return INSTANCE;
    }

    public void adicionarServico(String nome, float valor){
        String textNome = "Serviço: " + nome;
        ControleDados.getInstance().lista_servico_cd.add(
                new ObjServico(textNome, valor)
        );
        ControleDados.getInstance().lista_escolha_cd.add(
                new ObjServicoEscolha(nome, valor)
        );
    }
}
