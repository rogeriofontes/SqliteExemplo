package br.com.sqliteexemplo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.sqliteexemplo.infra.DatabaseHelper;
import br.com.sqliteexemplo.model.Aluno;

public class AlunoDAO {
    private DatabaseHelper helper;
    private final String[] colunas = { "id", "nome", "email" };

    public AlunoDAO(Context context) {
        helper = new DatabaseHelper(context);
    }

    public boolean salvar(Aluno aluno){
        long id = 0;
        SQLiteDatabase db = helper.gravacao();
        ContentValues valores = new ContentValues();

        valores.put("nome", aluno.getNome());
        valores.put("email", aluno.getEmail());

        if (aluno.getId() != null) {
            String[] valoresAlterados = { aluno.getEmail(), aluno.getNome() };
            id = db.update("ALUNO", valores, "email=?,nome=?", valoresAlterados);
        } else {
            id = db.insert("ALUNO", null, valores);
        }

        return id > 0;
    }

    public List<Aluno> consultar(){
        SQLiteDatabase db = helper.leitura();
        Cursor cursor = db.query("ALUNO", colunas, null, null, null, null, null, null);
        List<Aluno> alunoList = new ArrayList<>();

        while(cursor.moveToNext()) {
            Aluno aluno = getAluno(cursor);
            alunoList.add(aluno);
        }

        return alunoList;
    }

    public Aluno consultarPorId(Long alunoId){
        Aluno aluno = null;
        SQLiteDatabase db = helper.leitura();
        Cursor cursor = db.query("ALUNO", colunas, "id =" +alunoId, null, null, null, null);

        if(cursor.moveToFirst()) {
            aluno = getAluno(cursor);
        }

        return aluno;
    }

    public boolean remove(Long id){
        SQLiteDatabase db = helper.gravacao();
        String[] where = new String[]{ id.toString() };

        long removeu = db.delete("ALUNO",  "id=?", where);

        return removeu > 0;
    }

    private Aluno getAluno(Cursor cursor) {
        Long id = cursor.getLong(cursor.getColumnIndex("id"));
        String nome = cursor.getString(cursor.getColumnIndex("nome"));
        String email = cursor.getString(cursor.getColumnIndex("email"));

        return criaAluno(id, nome, email);
    }

    private Aluno criaAluno(Long id, String nome, String email) {
        Aluno aluno = new Aluno();
        aluno.setId(id);
        aluno.setNome(nome);
        aluno.setEmail(email);
        return aluno;
    }
}
