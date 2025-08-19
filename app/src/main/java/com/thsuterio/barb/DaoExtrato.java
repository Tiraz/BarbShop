package com.thsuterio.barb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DaoExtrato {

    private DbHelper dbHelper;

    /**
     * Construtor recebe o Context e cria o DbHelper.
     * Assim conseguimos acessar o banco de dados.
     */
    public DaoExtrato(Context context) {
        dbHelper = new DbHelper(context);
    }

    /**
     * Método para inserir um novo extrato no banco de dados.
     */
    public Long inserirExtrato(ObjExtrato extrato) {

        // Obtém o banco no modo de escrita.
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Cria um ContentValues para mapear as colunas aos valores.
        ContentValues values = new ContentValues();

        values.put(DbHelper.COLUMN_EXTRATO_NOME, extrato.getNome_extrato());
        values.put(DbHelper.COLUMN_EXTRATO_DIA, extrato.getDia_extrato());
        values.put(DbHelper.COLUMN_EXTRATO_HORA, extrato.getHora_extrato());
        values.put(DbHelper.COLUMN_EXTRATO_VALOR, extrato.getValor_extrato());
        values.put(DbHelper.COLUMN_EXTRATO_SERVICOS, extrato.getServicos_extrato());

        // Insere no banco e retorna o ID gerado.
        long id = db.insert(DbHelper.TABLE_EXTRATO, null, values);

        // Fecha o banco após operação.
        db.close();

        return id; // Retorna o ID do registro inserido.
    }

    /**
     * Método para retornar todos os registros da tabela extrato.
     */
    public List<ObjExtrato> readExtratos() {

        // Lista que será retornada com os objetos do banco.
        List<ObjExtrato> lista = new ArrayList<>();

        // Obtém o banco no modo de leitura.
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define as colunas que queremos buscar.
        String[] colunas = {
                DbHelper.COLUMN_EXTRATO_ID,
                DbHelper.COLUMN_EXTRATO_NOME,
                DbHelper.COLUMN_EXTRATO_DIA,
                DbHelper.COLUMN_EXTRATO_HORA,
                DbHelper.COLUMN_EXTRATO_VALOR,
                DbHelper.COLUMN_EXTRATO_SERVICOS
        };

        // Consulta no banco de dados.
        Cursor cursor = db.query(
                DbHelper.TABLE_EXTRATO,
                colunas,
                null,
                null,
                null,
                null,
                null
        );

        // Percorre os resultados do cursor e cria objetos ObjExtrato.
        if (cursor.moveToFirst()) {
            do {
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_EXTRATO_ID));
                String nome = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_EXTRATO_NOME));
                String dia = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_EXTRATO_DIA));
                String hora = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_EXTRATO_HORA));
                double valor = cursor.getDouble(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_EXTRATO_VALOR));
                String servicos = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_EXTRATO_SERVICOS));

                // Instancia um novo extrato.
                ObjExtrato extrato = new ObjExtrato();

                // Preenche os dados no objeto.
                extrato.setCodigo_extrato(id);
                extrato.setNome_extrato(nome);
                extrato.setDia_extrato(dia);
                extrato.setHora_extrato(hora);
                extrato.setValor_extrato(valor);
                extrato.setServicos_extrato(servicos);

                // Adiciona na lista.
                lista.add(extrato);

            } while (cursor.moveToNext());
        }

        // Fecha cursor e banco.
        cursor.close();
        db.close();

        return lista; // Retorna a lista de extratos.
    }

    /**
     * Método para atualizar um extrato existente no banco.
     */
    public int atualizarExtrato(ObjExtrato extrato) {

        // Obtém o banco no modo de escrita.
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Preenche os novos dados no ContentValues.
        ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_EXTRATO_NOME, extrato.getNome_extrato());
        values.put(DbHelper.COLUMN_EXTRATO_DIA, extrato.getDia_extrato());
        values.put(DbHelper.COLUMN_EXTRATO_HORA, extrato.getHora_extrato());
        values.put(DbHelper.COLUMN_EXTRATO_VALOR, extrato.getValor_extrato());
        values.put(DbHelper.COLUMN_EXTRATO_SERVICOS, extrato.getServicos_extrato());

        // Define a condição de qual extrato será atualizado (pelo ID).
        String whereClause = DbHelper.COLUMN_EXTRATO_ID + " = ?";
        String[] whereArgs = { String.valueOf(extrato.getCodigo_extrato()) };

        // Atualiza o registro e retorna quantas linhas foram afetadas.
        int linhasAfetadas = db.update(DbHelper.TABLE_EXTRATO, values, whereClause, whereArgs);

        // Fecha o banco após operação.
        db.close();

        return linhasAfetadas; // Retorna o número de registros alterados.
    }

    /**
     * Método para deletar um extrato do banco de dados pelo ID.
     */
    public int deletarExtrato(long idExtrato) {

        // Obtém o banco no modo de escrita.
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Define a cláusula WHERE para identificar o extrato pelo ID.
        String whereClause = DbHelper.COLUMN_EXTRATO_ID + " = ?";
        String[] whereArgs = { String.valueOf(idExtrato) };

        // Realiza a exclusão e retorna o número de linhas afetadas.
        int linhasExcluidas = db.delete(DbHelper.TABLE_EXTRATO, whereClause, whereArgs);

        // Fecha o banco após operação.
        db.close();

        return linhasExcluidas; // Retorna quantos registros foram excluídos.
    }
}
