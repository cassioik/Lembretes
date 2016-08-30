package com.cassioik.lembretes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.cassioik.lembretes.DAO.LembreteDAO;
import com.cassioik.lembretes.model.Lembrete;

import java.util.ArrayList;
import java.util.List;

public class LembretesActivity extends AppCompatActivity {
    //Componenter
    //ListView listView;

    //Adaptadores
    ArrayAdapter<Lembrete> adaptador;

    //Conteudo
    ArrayList<Lembrete> conteudo = new ArrayList<Lembrete>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lembretes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /**
         *  Botao Flutuante
         */
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent irParaTelaDeAdicionarLembrete = new Intent(LembretesActivity.this, AdicionarLembreteActivity.class);
                LembretesActivity.this.startActivity(irParaTelaDeAdicionarLembrete);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        final ListView listView;

        /**
         * Populando ListView
         */
        listView = (ListView)findViewById(R.id.listView);

        LembreteDAO lembreteDAO = new LembreteDAO(getBaseContext());
        conteudo = lembreteDAO.listarLembretes();

        adaptador = new ArrayAdapter<Lembrete>(this, android.R.layout.simple_list_item_1, conteudo);

        listView.setAdapter(adaptador);

        /**
         *  ListView Listener
         */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Lembrete lembrete = (Lembrete)listView.getItemAtPosition(i);
                //Toast.makeText(getApplicationContext(), lembrete.toString(), Toast.LENGTH_LONG).show();
                Intent irParaVisualizador = new Intent(LembretesActivity.this, VisualizarLembreteActivity.class);
                irParaVisualizador.putExtra("id", lembrete.getId());
                LembretesActivity.this.startActivity(irParaVisualizador);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lembretes, menu);
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
