package com.thsuterio.barb;

public class ObjServicoEscolha {
    String nome_escolha;
    double valor_escolha;
    private int codigo;

    public ObjServicoEscolha(String nome_escolha, double valor_escolha, int codigo) {
        this.nome_escolha = nome_escolha;
        this.valor_escolha = valor_escolha;
        this.codigo = codigo;
    }


    public String getNome_escolha() {
        return nome_escolha;
    }

    public void setNome_escolha(String nome_escolha) {
        this.nome_escolha = nome_escolha;
    }

    public double getValor_escolha() {
        return valor_escolha;
    }

    public void setValor_escolha(float valor_escolha) {
        this.valor_escolha = valor_escolha;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}
