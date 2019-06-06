package br.com.sqliteexemplo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.sqliteexemplo.dao.AlunoDAO;
import br.com.sqliteexemplo.model.Aluno;

public class AlunoFormActivity extends AppCompatActivity {
    private ViewHolder viewHolder = new ViewHolder();
    private AlunoDAO alunoDAO = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno_form);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewHolder.nomeEdt = (EditText) findViewById(R.id.nomeEdt);
        viewHolder.emailEdt = (EditText) findViewById(R.id.emailEdt);
        viewHolder.salvarBtn = (Button) findViewById(R.id.salvarBtn);

        alunoDAO = new AlunoDAO(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AlunoFormActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        viewHolder.salvarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = viewHolder.nomeEdt.getText().toString();
                String email = viewHolder.emailEdt.getText().toString();

                Aluno aluno = new Aluno(nome, email);
                alunoDAO.salvar(aluno);
            }
        });
    }

    public static class ViewHolder {
        EditText nomeEdt;
        EditText emailEdt;
        Button salvarBtn;
    }
}
