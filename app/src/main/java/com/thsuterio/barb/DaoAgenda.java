package com.thsuterio.barb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DaoAgenda {

    private DbHelper dbHelper;

    /**
     * Construtor recebe o Context e cria o DbHelper.
     * Assim conseguimos acessar o banco de dados.
     */
    public DaoAgenda(Context context) {
        dbHelper = new DbHelper(context);
    }

    /**
     * Método para inserir um novo horario na agenda do banco de dados.
     */

    public Long inserir (ObjAgendado agendado) {

        // Obtém o banco no modo de escrita.
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Cria um ContentValues para mapear as colunas aos valores.
        ContentValues values = new ContentValues();

        values.put(DbHelper.COLUMN_AGENDA_NOME, agendado.getNome_agendado());
        values.put(DbHelper.COLUMN_AGENDA_VALOR, agendado.getValor_agendado());
        values.put(DbHelper.COLUMN_AGENDA_DIA, agendado.getDia_agendado());
        values.put(DbHelper.COLUMN_AGENDA_HORA, agendado.getHora_agendado());

        // Insere no banco e retorna o ID gerado.
        long id = db.insert(DbHelper.TABLE_AGENDA, null, values);

        // Fecha o banco após operação.
        db.close();

        return id; // Retorna o ID do registro inserido.
    }


    /**
     * Metódo de leitura do banco
     *
     */
    public List<ObjAgendado> readAgenda() {

        // Instanciando a lista que vai ser retornada
        List<ObjAgendado> lista = new ArrayList<>();

        // Obtém o banco no modo de leitura.
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define as colunas que queremos consultar.
        String[] colunas = {
                dbHelper.COLUMN_AGENDA_ID,
                dbHelper.COLUMN_AGENDA_DIA,
                dbHelper.COLUMN_AGENDA_HORA,
                dbHelper.COLUMN_AGENDA_NOME,
                dbHelper.COLUMN_AGENDA_VALOR,
        };

        // Faz a consulta no banco.
        Cursor cursor = db.query(
                DbHelper.TABLE_AGENDA, // tabela
                null,                 // colunas
                null,                    // where
                null,                    // whereArgs
                null,                    // groupBy
                null,                    // having
                null                     // orderBy
        );

        // Percorre o Cursor e cria objetos ObjServico.
        if (cursor.moveToFirst()) {

            do {
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_AGENDA_ID));
                String nome = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_AGENDA_NOME));
                String dia =  cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_AGENDA_DIA));
                String hora =  cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_AGENDA_NOME));
                double valor =  cursor.getDouble(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_AGENDA_VALOR));

                //Objeto Loja vazio
                ObjAgendado agenda = new ObjAgendado();

                // setando as informaçoes
                agenda.setCodigo(id);
                agenda.setDia_agendado(dia);
                agenda.setHora_agendado(hora);
                agenda.setValor_agendado(valor);
                agenda.setNome_agendado(nome);

                //Adicionando a lista
                lista.add(agenda);

            }while (cursor.moveToNext());
        }

        // Fecha o cursor e o banco.
        cursor.close();
        db.close();

        return lista;  // Retorna a lista

    }

    /**
     * Método para atualizar um agendamento existente no banco de dados.
     */
    public int atualizar(ObjAgendado agendado) {

        // Obtém o banco no modo de escrita.
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Cria um ContentValues para mapear as colunas aos novos valores.
        ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_AGENDA_NOME, agendado.getNome_agendado());
        values.put(DbHelper.COLUMN_AGENDA_VALOR, agendado.getValor_agendado());
        values.put(DbHelper.COLUMN_AGENDA_DIA, agendado.getDia_agendado());
        values.put(DbHelper.COLUMN_AGENDA_HORA, agendado.getHora_agendado());

        // Define a cláusula WHERE para identificar o agendamento pelo ID.
        String whereClause = DbHelper.COLUMN_AGENDA_ID + " = ?";
        String[] whereArgs = { String.valueOf(agendado.getCodigo()) };

        // Realiza a atualização e retorna o número de linhas afetadas.
        int linhasAfetadas = db.update(DbHelper.TABLE_AGENDA, values, whereClause, whereArgs);

        // Fecha o banco após operação.
        db.close();

        return linhasAfetadas; // Retorna quantas linhas foram modificadas.
    }

    /**
     * Método para deletar um agendamento do banco de dados.
     */
    public int deletar(long idAgendamento) {

        // Obtém o banco no modo de escrita.
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Define a cláusula WHERE para identificar o agendamento pelo ID.
        String whereClause = DbHelper.COLUMN_AGENDA_ID + " = ?";
        String[] whereArgs = { String.valueOf(idAgendamento) };

        // Realiza a exclusão e retorna o número de linhas afetadas.
        int linhasExcluidas = db.delete(DbHelper.TABLE_AGENDA, whereClause, whereArgs);

        // Fecha o banco após operação.
        db.close();

        return linhasExcluidas; // Retorna quantas linhas foram deletadas.
    }


}
