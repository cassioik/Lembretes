package com.cassioik.lembretes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cassioik.lembretes.DAO.LembreteDAO;

import java.util.ArrayList;

public class LembretesActivity extends AppCompatActivity {
    //Componenter
    ListView listView;

    //Adaptadores
    ArrayAdapter<String> adaptador;

    //Conteudo
    ArrayList<String> conteudo = new ArrayList<String>();

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

        /**
         * Populando ListView
         */
        listView = (ListView)findViewById(R.id.listView);

        LembreteDAO lembreteDAO = new LembreteDAO(getBaseContext());
        conteudo = lembreteDAO.listarLembretes();

        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, conteudo);

        listView.setAdapter(adaptador);
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
