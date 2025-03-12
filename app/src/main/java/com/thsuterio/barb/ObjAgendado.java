package com.thsuterio.barb;

public class ObjAgendado {
    String nome_agendado;
    String valor_agendado;
    String dia_agendado;

    public String getDia_agendado() {
        return dia_agendado;
    }

    public void setDia_agendado(String dia_agendado) {
        this.dia_agendado = dia_agendado;
    }

    public String getHora_agendado() {
        return hora_agendado;
    }

    public void setHora_agendado(String hora_agendado) {
        this.hora_agendado = hora_agendado;
    }

    public String getNome_agendado() {
        return nome_agendado;
    }

    public void setNome_agendado(String nome_agendado) {
        this.nome_agendado = nome_agendado;
    }

    public String getValor_agendado() {
        return valor_agendado;
    }

    public void setValor_agendado(String valor_agendado) {
        this.valor_agendado = valor_agendado;
    }

    String hora_agendado;

    public ObjAgendado(String dia_agendado, String hora_agendado, String nome_agendado, String valor_agendado) {
        this.dia_agendado = dia_agendado;
        this.hora_agendado = hora_agendado;
        this.nome_agendado = nome_agendado;
        this.valor_agendado = valor_agendado;
    }



}
