package com.thsuterio.barb;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ControleDados {

    //Atribto estatico que armazena a unica intancia da classe.
    private static final ControleDados INSTANCE = new ControleDados();
    private Context contextoDaoServ;

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
    public void adicionarServico(String nome, double valor){
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
    public DaoServicoDAO getDaoServSemContexto(){

        return daoServ;
    }
    public void  setContextDaoServ(Context context) {
        daoServ = new DaoServicoDAO(context);
    }

    public List<ObjServico> getLista_servico_cd() {
        return lista_servico_cd;
    }

    public void setLista_servico_cd(List<ObjServico> lista_servico_cd) {
        this.lista_servico_cd = lista_servico_cd;
    }

    //-----------------------------------------------------------------------------

    /**Metódo para escolher avatar da foto de maeneira aleatoria**/
    public int avatarRandom(){
        Random gerador = new Random();
        int num = gerador.nextInt(6);
        switch (num){
            case 0:
                return R.drawable.person_um;
            case 1:
                return R.drawable.person_dois;
            case 2:
                return R.drawable.person_tres;
            case 3:
                return R.drawable.person_quatro;
            case 4:
                return R.drawable.person_cinco;
        }
        return R.drawable.person_um;
    }

}
