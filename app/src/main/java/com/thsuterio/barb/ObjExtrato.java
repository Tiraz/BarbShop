package com.thsuterio.barb;

public class ObjExtrato {

    private long codigo_extrato;
    private int quantidade_extrato;
    private double valor_extrato;
    private String dia_extrato;
    private String hora_extrato;
    private String nome_extrato;
    private String servicos_extrato;


    /**Construtores**/
    public ObjExtrato(String dia_extrato, int quantidade_extrato, double valor_extrato) {
        this.dia_extrato = dia_extrato;
        this.quantidade_extrato = quantidade_extrato;
        this.valor_extrato = valor_extrato;
    }

    public ObjExtrato() {}

    /**Dia**/
    public String getDia_extrato() {
        return dia_extrato;
    }
    public void setDia_extrato(String dia_extrato) {
        this.dia_extrato = dia_extrato;
    }

    /**Quantidade**/
    public int getQuantidade_extrato() {
        return quantidade_extrato;
    }
    public void setQuantidade_extrato(int quantidade_extrato) {
        this.quantidade_extrato = quantidade_extrato;
    }

    /**Valor**/
    public double getValor_extrato() {
        return valor_extrato;
    }
    public void setValor_extrato(double valor_extrato) {
        this.valor_extrato = valor_extrato;
    }

    /**Código**/
    public long getCodigo_extrato() {
        return codigo_extrato;
    }
    public void setCodigo_extrato(long codigo_extrato) {
        this.codigo_extrato = codigo_extrato;
    }

    /**Hora**/
    public String getHora_extrato() {
        return hora_extrato;
    }
    public void setHora_extrato(String hora_extrato) {
        this.hora_extrato = hora_extrato;
    }

    /**Nome**/
    public String getNome_extrato() {
        return nome_extrato;
    }
    public void setNome_extrato(String nome_extrato) {
        this.nome_extrato = nome_extrato;
    }

    /**Serviços**/
    public String getServicos_extrato() {
        return servicos_extrato;
    }
    public void setServicos_extrato(String servicos_extrato) {
        this.servicos_extrato = servicos_extrato;
    }

}
