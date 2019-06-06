package br.com.sqliteexemplo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.List;

import br.com.sqliteexemplo.dao.AlunoDAO;
import br.com.sqliteexemplo.model.Aluno;
import br.com.sqliteexemplo.ui.AlunoAdapter;

public class MainActivity extends AppCompatActivity {
    private AlunoDAO alunoDAO = null;
    private AlunoAdapter alunoAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView listaAlunos = (ListView) findViewById(R.id.listaAlunos);

        alunoDAO = new AlunoDAO(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AlunoFormActivity.class);
                startActivity(i);
            }
        });

        List<Aluno> alunoList = alunoDAO.consultar();

        for (Aluno a: alunoList) {
            Log.i("List", "sk" + a.toString());
        }

        alunoAdapter = new AlunoAdapter(this, alunoList);
        listaAlunos.setAdapter(alunoAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
