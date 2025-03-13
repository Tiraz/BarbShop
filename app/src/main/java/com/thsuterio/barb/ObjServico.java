package com.thsuterio.barb;

public class ObjServico {
    private String nome_servico;
    private String valor_servico;



    public ObjServico(String nome_servico, String valor_servico) {
        this.nome_servico = nome_servico;
        this.valor_servico = valor_servico;
    }

    public String getValor_servico() {
        return valor_servico;
    }

    public void setValor_servico(String valor_servico) {
        this.valor_servico = valor_servico;
    }

    public String getNome_servico() {
        return nome_servico;
    }

    public void setNome_servico(String nome_servico) {
        this.nome_servico = nome_servico;
    }
}
