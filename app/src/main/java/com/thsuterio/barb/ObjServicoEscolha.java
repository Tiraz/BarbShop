package com.thsuterio.barb;

public class ObjServicoEscolha {
    String nome_escolha;
    float valor_escolha;

    public ObjServicoEscolha(String nome_escolha, float valor_escolha) {
        this.nome_escolha = nome_escolha;
        this.valor_escolha = valor_escolha;
    }


    public String getNome_escolha() {
        return nome_escolha;
    }

    public void setNome_escolha(String nome_escolha) {
        this.nome_escolha = nome_escolha;
    }

    public float getValor_escolha() {
        return valor_escolha;
    }

    public void setValor_escolha(float valor_escolha) {
        this.valor_escolha = valor_escolha;
    }
}
