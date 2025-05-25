package com.thsuterio.barb;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class ControleDados {

    //Atribto estatico que armazena a unica intancia da classe.
    private static final ControleDados INSTANCE = new ControleDados();

    // DAO dos Objtos controle do banco de dados
    private DaoServicoDAO daoServ;

    //Dados para guardar.
    List<ObjAgendado> lista_agendado_cd = new ArrayList<>();
    List<ObjExtrato> lista_extrato_cd = new ArrayList<>();
    private List<ObjServico> lista_servico_cd = new ArrayList<>();
    List<ObjServicoEscolha> lista_escolha_cd = new ArrayList<>();

    //Construtor privado que impede que outras classes criem novos objetos ControleDados
    private ControleDados(){};

    //Metodo publico para acessar
    public static ControleDados getInstance(){
        return INSTANCE;
    }


    // Adicionar serviço
    public void adicionarServico(String nome, float valor){
        String textNome = "Serviço: " + nome;
        ControleDados.getInstance().lista_servico_cd.add(
                new ObjServico(textNome, valor)
        );

    }

    //Metodo para formatar em reais
    public String valorFormatado(double valor){
        //String fmt_valor = String.format("Valor Total: R$ %.2f", valor);
        return String.format("R$ %.2f", valor);
    }


    //DAO do serviço--------------------------------------------------------------
    public DaoServicoDAO getDaoServ(Context context){
        return daoServ = new DaoServicoDAO(context);
    }

    public List<ObjServico> getLista_servico_cd() {
        return lista_servico_cd;
    }

    public void setLista_servico_cd(List<ObjServico> lista_servico_cd) {
        this.lista_servico_cd = daoServ.listarTodos();
    }

    //-----------------------------------------------------------------------------
}
