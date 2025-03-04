package com.thsuterio.barb;

public class ObjServico {
    private String nome_servico;
    private int tempo_estimado_servico;
    private int valor_servico;



    public ObjServico(String nome_servico, int tempo_estimado_servico, int valor_servico) {
        this.nome_servico = nome_servico;
        this.tempo_estimado_servico = tempo_estimado_servico;
        this.valor_servico = valor_servico;
    }

    public int getValor_servico() {
        return valor_servico;
    }

    public void setValor_servico(int valor_servico) {
        this.valor_servico = valor_servico;
    }

    public int getTempo_estimado_servico() {
        return tempo_estimado_servico;
    }

    public void setTempo_estimado_servico(int tempo_estimado_servico) {
        this.tempo_estimado_servico = tempo_estimado_servico;
    }

    public String getNome_servico() {
        return nome_servico;
    }

    public void setNome_servico(String nome_servico) {
        this.nome_servico = nome_servico;
    }
}
