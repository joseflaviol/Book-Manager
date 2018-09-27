package com.example.aluno_3e1.trabalho_3bim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroLivroActivity extends AppCompatActivity {

    private EditText nome, autor, genero, dataLancamento;
    private Livro livro = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_livro);

        nome = (EditText) findViewById(R.id.txtNome);
        autor = (EditText) findViewById(R.id.txtAutor);
        genero = (EditText) findViewById(R.id.txtGenero);
        dataLancamento = (EditText) findViewById(R.id.txtData);

        Intent it = getIntent();
        if(it.hasExtra("livro")){
            Button btn = (Button) findViewById(R.id.btnAcao);
            btn.setText(R.string.atualizar);
            livro = (Livro) it.getSerializableExtra("livro");
            nome.setText(livro.getNome().toString());
            autor.setText(livro.getAutor().toString());
            genero.setText(livro.getGenero().toString());
            dataLancamento.setText(livro.getDataLancamento().toString());
        }

    }

    public void cadastraLivro(View view){
        try {
            if(livro == null){
                Livro livro = new Livro();

                livro.setNome(nome.getText().toString());
                livro.setAutor(autor.getText().toString());
                livro.setGenero(genero.getText().toString());
                livro.setDataLancamento(dataLancamento.getText().toString());

                LivroDAO lDAO = new LivroDAO(this);

                lDAO.inserir(livro);

                Toast.makeText(this, "Cadastro feito com sucesso.", Toast.LENGTH_LONG).show();
            }else{
                livro.setNome(nome.getText().toString());
                livro.setAutor(autor.getText().toString());
                livro.setGenero(genero.getText().toString());
                livro.setDataLancamento(dataLancamento.getText().toString());

                LivroDAO lDAO = new LivroDAO(this);
                lDAO.atualizaLivro(livro);
            }

        }catch(Exception e){
            Toast.makeText(this, "Falha no cadastro.", Toast.LENGTH_LONG).show();
        }




    }
}
