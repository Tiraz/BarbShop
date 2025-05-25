package com.thsuterio.barb;

public class ObjServico {
    private int idServico;
    private String nome_servico;
    private double valor_servico;



    public ObjServico(String nome_servico, double valor_servico) {
        this.nome_servico = nome_servico;
        this.valor_servico = valor_servico;
    }

    public double getValor_servico() {
        return valor_servico;
    }

    public void setValor_servico(double valor_servico) {
        this.valor_servico = valor_servico;
    }

    public String getNome_servico() {
        return nome_servico;
    }

    public void setNome_servico(String nome_servico) {
        this.nome_servico = nome_servico;
    }

    public int getIdServico() {
        return idServico;
    }

    public void setIdServico(int idServico) {
        this.idServico = idServico;
    }
}
