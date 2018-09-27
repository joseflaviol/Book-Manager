package com.example.aluno_3e1.trabalho_3bim;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class LivroDAO {
    private BancoHelper helper;
    private SQLiteDatabase banco;

    public LivroDAO(Context context){
        helper = new BancoHelper(context);
        banco = helper.getWritableDatabase();
    }

    public void inserir(Livro livro){
        ContentValues values = new ContentValues();
        values.put("nome", livro.getNome());
        values.put("autor", livro.getAutor());
        values.put("genero", livro.getGenero());
        values.put("dataLancamento", livro.getDataLancamento());

        banco.insert("livro", null, values);
    }

    public List<Livro> buscaTodos(){
        List<Livro> livros = new ArrayList<>();
        Cursor cursor = banco.query("livro", new String[]{"id", "nome", "autor", "genero", "dataLancamento"}, null, null, null, null, null);
        while(cursor.moveToNext()){
            Livro l = new Livro();
            l.setId(cursor.getInt(0));
            l.setNome(cursor.getString(1));
            l.setAutor(cursor.getString(2));
            l.setGenero(cursor.getString(3));
            l.setDataLancamento(cursor.getString(4));
            livros.add(l);
        }

        return livros;
    }

    public void excluiLivro(Livro l){
        banco.delete("livro", "id = ?", new String[]{Integer.toString(l.getId())});
    }

    public void atualizaLivro(Livro livro){
        ContentValues values = new ContentValues();
        values.put("nome", livro.getNome());
        values.put("autor", livro.getAutor());
        values.put("genero", livro.getGenero());
        values.put("dataLancamento", livro.getDataLancamento());

        banco.update("livro", values, "id = ?", new String[]{Integer.toString(livro.getId())});
    }
}
