package com.thsuterio.barb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    // Nome do Banco de Dados
    private static final String DATABASE_NAME = "BarbeariaDB.db";

    // Versão do Banco (aumente se fizer alterações na estrutura)
    private static final int DATABASE_VERSION = 1;

    // Nome das tabelas
    public static final String TABLE_AGENDA = "agenda";
    public static final String TABLE_SERVICO = "servico";
    public static final String TABLE_EXTRATO = "extrato";
    public static final String TABLE_TRABALHO = "trabalho";

    /** ------------------------------------ Colunas ------------------------------------ **/

    //--------------------------------AGENDA----------------------------------------
    public static final String COLUMN_AGENDA_ID = "idAgenda";             // Chave primária
    public static final String COLUMN_AGENDA_NOME = "nome_cliente";       // Nome do cliente
    public static final String COLUMN_AGENDA_DIA = "dia_agendado";        // Dia agendado
    public static final String COLUMN_AGENDA_HORA = "hora_agendada";      // Hora agendada
    public static final String COLUMN_AGENDA_VALOR = "valor_total";       // Valor total da agenda

    //--------------------------------SERVIÇO----------------------------------------
    public static final String COLUMN_SERV_ID = "idServico";              // Chave primária
    public static final String COLUMN_SERV_NOME = "nome_servico";         // Nome do serviço
    public static final String COLUMN_SERV_VALOR = "valor_servico";       // Valor do serviço

    //--------------------------------EXTRATO----------------------------------------
    public static final String COLUMN_EXTRATO_ID = "idExtrato";           // Chave primária
    public static final String COLUMN_EXTRATO_NOME = "nome_cliente";      // Nome do cliente
    public static final String COLUMN_EXTRATO_DIA = "dia_servico";        // Dia do serviço
    public static final String COLUMN_EXTRATO_HORA = "hora_servico";      // Hora do serviço
    public static final String COLUMN_EXTRATO_VALOR = "valor_pago";       // Valor pago
    public static final String COLUMN_EXTRATO_SERVICOS = "descricao_servicos"; // Lista de serviços

    //--------------------------------TRABALHO----------------------------------------
    public static final String COLUMN_TRABALHO_ID = "idTrabalho";         // Chave primária
    public static final String COLUMN_TRABALHO_COD_SERV = "cod_servico";  // Chave estrangeira de serviço
    public static final String COLUMN_TRABALHO_COD_AGENDA = "cod_agenda"; // Chave estrangeira de agenda


    // SQL para criar a tabela SERVICO
    private static final String TABLE_CREATE_SERVICO =
            "CREATE TABLE " + TABLE_SERVICO + " (" +
                    COLUMN_SERV_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_SERV_NOME + " TEXT, " +
                    COLUMN_SERV_VALOR + " REAL);";

    // SQL para criar a tabela AGENDA
    private static final String TABLE_CREATE_AGENDA =
            "CREATE TABLE " + TABLE_AGENDA + " (" +
                    COLUMN_AGENDA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_AGENDA_NOME + " TEXT, " +
                    COLUMN_AGENDA_DIA + " TEXT, " +
                    COLUMN_AGENDA_HORA + " TEXT, " +
                    COLUMN_AGENDA_VALOR + " REAL);";

    // SQL para criar a tabela EXTRATO
    private static final String TABLE_CREATE_EXTRATO =
            "CREATE TABLE " + TABLE_EXTRATO + " (" +
                    COLUMN_EXTRATO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_EXTRATO_NOME + " TEXT, " +
                    COLUMN_EXTRATO_DIA + " TEXT, " +
                    COLUMN_EXTRATO_HORA + " TEXT, " +
                    COLUMN_EXTRATO_VALOR + " REAL, " +
                    COLUMN_EXTRATO_SERVICOS + " TEXT);";

    // SQL para criar a tabela TRABALHO
    private static final String TABLE_CREATE_TRABALHO =
            "CREATE TABLE " + TABLE_TRABALHO + " (" +
                    COLUMN_TRABALHO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TRABALHO_COD_SERV + " INTEGER, " +
                    COLUMN_TRABALHO_COD_AGENDA + " INTEGER, " +
                    "FOREIGN KEY(" + COLUMN_TRABALHO_COD_SERV + ") REFERENCES " + TABLE_SERVICO + "(" + COLUMN_SERV_ID + "), " +
                    "FOREIGN KEY(" + COLUMN_TRABALHO_COD_AGENDA + ") REFERENCES " + TABLE_AGENDA + "(" + COLUMN_AGENDA_ID + "));";


    /**
     * Construtor obrigatório.
     */
    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Método chamado na criação do banco.
     * Criamos as tabelas aqui.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_SERVICO);
        db.execSQL(TABLE_CREATE_AGENDA);
        db.execSQL(TABLE_CREATE_EXTRATO);
        db.execSQL(TABLE_CREATE_TRABALHO);
    }

    /**
     * Método chamado quando a versão do banco muda.
     * Aqui podemos atualizar a estrutura.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRABALHO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXTRATO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AGENDA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICO);
        onCreate(db);
    }

}
