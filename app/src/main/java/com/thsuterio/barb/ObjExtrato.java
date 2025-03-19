package com.thsuterio.barb;

public class ObjExtrato {
    private int quantidade_extrato;
    private double valor_extrato;
    private String dia_extrato;

    public ObjExtrato(String dia_extrato, int quantidade_extrato, double valor_extrato) {
        this.dia_extrato = dia_extrato;
        this.quantidade_extrato = quantidade_extrato;
        this.valor_extrato = valor_extrato;
    }

    public String getDia_extrato() {
        return dia_extrato;
    }

    public void setDia_extrato(String dia_extrato) {
        this.dia_extrato = dia_extrato;
    }

    public int getQuantidade_extrato() {
        return quantidade_extrato;
    }

    public void setQuantidade_extrato(int quantidade_extrato) {
        this.quantidade_extrato = quantidade_extrato;
    }

    public double getValor_extrato() {
        return valor_extrato;
    }

    public void setValor_extrato(double valor_extrato) {
        this.valor_extrato = valor_extrato;
    }


}
