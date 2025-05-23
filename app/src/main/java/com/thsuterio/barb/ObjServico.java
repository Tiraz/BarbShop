package com.thsuterio.barb;

public class ObjServico {
    private int idServico;
    private String nome_servico;
    private float valor_servico;



    public ObjServico(String nome_servico, float valor_servico) {
        this.nome_servico = nome_servico;
        this.valor_servico = valor_servico;
    }

    public float getValor_servico() {
        return valor_servico;
    }

    public void setValor_servico(float valor_servico) {
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
