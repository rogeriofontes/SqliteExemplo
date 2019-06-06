package br.com.sqliteexemplo.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.sqliteexemplo.R;
import br.com.sqliteexemplo.model.Aluno;

public class AlunoAdapter extends BaseAdapter {
    private Context context;
    private List<Aluno> alunos;

    public AlunoAdapter(Context context, List<Aluno> alunos) {
        this.context = context;
        this.alunos = alunos;
    }

    @Override
    public int getCount() {
        return !alunos.isEmpty() ? alunos.size() : 0;
    }

    @Override
    public Aluno getItem(int position) {
        return !alunos.isEmpty() ? alunos.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return !alunos.isEmpty() ? alunos.get(position).getId() : 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Aluno aluno = alunos.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.aluno_item, null);

        TextView nametv = (TextView) view.findViewById(R.id.nomeTv);
        nametv.setText(aluno.getNome());

        return view;
    }
}
