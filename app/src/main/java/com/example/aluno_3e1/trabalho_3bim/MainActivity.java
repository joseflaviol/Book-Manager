package com.example.aluno_3e1.trabalho_3bim;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lista;
    private LivroDAO lDAO;
    private List<Livro> listaLivros, livrosFiltrados;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = (ListView) findViewById(R.id.lista);

        lDAO = new LivroDAO(this);

        livrosFiltrados = lDAO.buscaTodos();

        listaLivros = lDAO.buscaTodos();

        LivroAdapter adapter = new LivroAdapter(this, livrosFiltrados);

        lista.setAdapter(adapter);

        registerForContextMenu(lista);
    }

    public void excluir(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Livro lExclui = livrosFiltrados.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(R.string.atencao)
                .setMessage(R.string.msgExlcui)
                .setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listaLivros.remove(lExclui);
                        livrosFiltrados.remove(lExclui);
                        lDAO.excluiLivro(lExclui);
                        lista.invalidateViews();
                    }
                })
                .setNegativeButton(R.string.nao, null).create();

        dialog.show();
    }

    public void atualizar(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Livro lAtualiza = livrosFiltrados.get(menuInfo.position);

        Intent it = new Intent(this, CadastroLivroActivity.class);
        it.putExtra("livro", lAtualiza);
        startActivity(it);

    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.principal, menu);

        SearchView sv = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                procuraAluno(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                procuraAluno(s);
                return false;
            }
        });
        return true;
    }

    public void onCreateContextMenu(ContextMenu menu,View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_contexto, menu);
    }
    public void procuraAluno(String nome){
        livrosFiltrados.clear();
        for(Livro l : listaLivros){
            if(l.getNome().toLowerCase().contains(nome.toLowerCase())){
                livrosFiltrados.add(l);
            }
        }
        lista.invalidateViews();
    }

    public void abrirCadastro(MenuItem menu){
        Intent it = new Intent(this, CadastroLivroActivity.class);
        startActivity(it);
    }

    public void onResume(){
        super.onResume();
        livrosFiltrados.clear();
        listaLivros.clear();
        listaLivros.addAll(lDAO.buscaTodos());
        livrosFiltrados.addAll(lDAO.buscaTodos());
        lista.invalidateViews();
    }
}
