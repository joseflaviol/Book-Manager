package com.example.aluno_3e1.trabalho_3bim;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class LivroAdapter extends BaseAdapter {

    private List<Livro> livro;
    private Activity activity;
    public LivroAdapter(Activity activity, List<Livro> livro) {
        this.activity = activity;
        this.livro = livro;
    }

    @Override
    public int getCount() {
        return livro.size();
    }

    @Override
    public Object getItem(int i) {
        return livro.get(i);
    }

    @Override
    public long getItemId(int i) {
        return livro.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = activity.getLayoutInflater().inflate(R.layout.item, viewGroup, false);
        TextView nome = v.findViewById(R.id.txt_nome);
        TextView autor = v.findViewById(R.id.txt_autor);
        TextView genero = v.findViewById(R.id.txt_genero);

        Livro l = livro.get(i);

        nome.setText(l.getNome());
        autor.setText(l.getAutor());
        genero.setText(l.getGenero());

        return v;
    }
}
