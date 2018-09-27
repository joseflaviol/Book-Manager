package com.example.aluno_3e1.trabalho_3bim;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BancoHelper extends SQLiteOpenHelper {

    private static final String banco = "banco.db";
    private static final int versao = 1;
    public BancoHelper(Context context) {
        super(context, banco, null, versao);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE livro(id integer primary key autoincrement, " +
                "nome varchar(50), " +
                "autor varchar(50), " +
                "genero varchar(30), "+
                "dataLancamento varchar (20))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
