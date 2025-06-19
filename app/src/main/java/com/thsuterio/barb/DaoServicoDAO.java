package com.thsuterio.barb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DaoServicoDAO {

    private DbHelper dbHelper;

    /**
     * Construtor recebe o Context e cria o DbHelper.
     * Assim conseguimos acessar o banco de dados.
     */
    public DaoServicoDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    /**
     * Método para inserir um novo serviço no banco de dados.
     * Recebe um objeto ObjServico como parâmetro.
     */

    public Long inserir (ObjServico servico) {
        // Obtém o banco no modo de escrita.
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Cria um ContentValues para mapear as colunas aos valores.
        ContentValues values = new ContentValues();

        values.put(DbHelper.COLUMN_SERV_NOME, servico.getNome_servico());
        values.put(DbHelper.COLUMN_SERV_VALOR, servico.getValor_servico());

        // Insere no banco e retorna o ID gerado.
        long id = db.insert(DbHelper.TABLE_SERVICO, null, values);

        // Fecha o banco após operação.
        db.close();

        return id; // Retorna o ID do registro inserido.
    }


    /**
     * Método para listar todos os serviços do banco.
     * Retorna uma Lista de ObjServico.
     */

    public List<ObjServico> listarTodos() {
        List<ObjServico> lista = new ArrayList<>();

        // Obtém o banco no modo de leitura.
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define as colunas que queremos consultar.
        String[] colunas = {
                DbHelper.COLUMN_SERV_ID,
                DbHelper.COLUMN_SERV_NOME,
                DbHelper.COLUMN_SERV_VALOR
        };

        // Faz a consulta no banco.
        Cursor cursor = db.query(
                DbHelper.TABLE_SERVICO, // tabela
                colunas,                 // colunas
                null,                    // where
                null,                    // whereArgs
                null,                    // groupBy
                null,                    // having
                null                     // orderBy
        );

        // Percorre o Cursor e cria objetos ObjServico.
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_SERV_ID));
                String nome = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_SERV_NOME));
                float valor = cursor.getFloat(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_SERV_VALOR));

                ObjServico servico = new ObjServico(nome, valor);
                servico.setIdServico(id);

                lista.add(servico);

            } while (cursor.moveToNext());
        }

        // Fecha o cursor e o banco.
        cursor.close();
        db.close();

        return lista;  // Retorna a lista de serviços.

    }

    /**
     * Método para atualizar um serviço no banco de dados.
     * Recebe um objeto ObjServico com o ID e os novos dados.
     */
    public int atualizar(ObjServico servico) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_SERV_NOME, servico.getNome_servico());
        values.put(DbHelper.COLUMN_SERV_VALOR, servico.getValor_servico());

        // Condição de qual registro será atualizado.
        String whereClause = DbHelper.COLUMN_SERV_ID + " = ?";
        String[] whereArgs = { String.valueOf(servico.getIdServico()) };

        // Executa o update.
        int linhasAfetadas = db.update(DbHelper.TABLE_SERVICO, values, whereClause, whereArgs);

        db.close();
        return linhasAfetadas; // Retorna o número de linhas afetadas.
    }

    /**
     * Método para deletar um serviço do banco de dados.
     * Recebe o ID do serviço a ser excluído.
     */
    public int deletar(int idServico) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String whereClause = DbHelper.COLUMN_SERV_ID + " = ?";
        String[] whereArgs = { String.valueOf(idServico) };

        // Executa o delete.
        int linhasAfetadas = db.delete(DbHelper.TABLE_SERVICO, whereClause, whereArgs);

        db.close();
        return linhasAfetadas; // Retorna o número de linhas afetadas.
    }





}
