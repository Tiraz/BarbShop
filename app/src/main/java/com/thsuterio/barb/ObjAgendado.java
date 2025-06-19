package com.thsuterio.barb;

public class ObjAgendado {

    private long codigo;
    private String nome_agendado;
    private double valor_agendado;
    private String dia_agendado;
    private int img_agendado;
    private String hora_agendado;

    /** Código **/
    public long getCodigo() {
        return codigo;
    }
    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    /** Imagem **/
    public int getImg_agendado() {
        return img_agendado;
    }
    public void setImg_agendado(int img_agendado) {
        this.img_agendado = img_agendado;
    }

    /** Dia **/
    public String getDia_agendado() {
        return dia_agendado;
    }
    public void setDia_agendado(String dia_agendado) {
        this.dia_agendado = dia_agendado;
    }

    /** Hora **/
    public String getHora_agendado() {
        return hora_agendado;
    }
    public void setHora_agendado(String hora_agendado) {
        this.hora_agendado = hora_agendado;
    }

    /** Nome **/
    public String getNome_agendado() {
        return nome_agendado;
    }
    public void setNome_agendado(String nome_agendado) {
        this.nome_agendado = nome_agendado;
    }

    /** Valor **/
    public double getValor_agendado() {
        return valor_agendado;
    }
    public void setValor_agendado(double valor_agendado) {
        this.valor_agendado = valor_agendado;
    }

    /**Construtores**/
    public ObjAgendado(String dia_agendado, String hora_agendado, String nome_agendado, double valor_agendado, int img_agendado) {
        this.dia_agendado = dia_agendado;
        this.hora_agendado = hora_agendado;
        this.nome_agendado = nome_agendado;
        this.valor_agendado = valor_agendado;
        this.img_agendado = img_agendado;
    }
    //Sem imagem
    public ObjAgendado(String dia_agendado, String hora_agendado, String nome_agendado, double valor_agendado) {
        this.dia_agendado = dia_agendado;
        this.hora_agendado = hora_agendado;
        this.nome_agendado = nome_agendado;
        this.valor_agendado = valor_agendado;
    }

    public ObjAgendado() {}


}
