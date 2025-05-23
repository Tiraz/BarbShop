package com.thsuterio.barb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    // Nome do Banco de Dados
    private static final String DATABASE_NAME = "BarbeariaDB.db";

    // Versão do Banco (aumente se fizer alterações na estrutura)
    private static final int DATABASE_VERSION = 1;

    // Nome da tabela
    public static final String TABLE_SERVICO = "servico";

    // Colunas
    public static final String COLUMN_ID = "idServico";
    public static final String COLUMN_NOME = "nome_servico";
    public static final String COLUMN_VALOR = "valor_servico";

    // SQL para criar a tabela
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_SERVICO + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NOME + " TEXT, " +
                    COLUMN_VALOR + " REAL);";


    /**
     * Construtor obrigatório.
     */
    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Método chamado na criação do banco.
     * Criamos a tabela aqui.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);  // Executa o comando SQL de criação da tabela.
    }

    /**
     * Método chamado quando a versão do banco muda.
     * Aqui podemos atualizar a estrutura.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Exclui a tabela se existir
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICO);
        // Cria novamente
        onCreate(db);
    }

}
