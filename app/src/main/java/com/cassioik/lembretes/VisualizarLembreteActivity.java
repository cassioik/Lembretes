package com.cassioik.lembretes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cassioik.lembretes.DAO.LembreteDAO;
import com.cassioik.lembretes.model.Lembrete;

public class VisualizarLembreteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EditText etTitulo;
        EditText etDescricao;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_lembrete);

        etTitulo = (EditText)findViewById(R.id.etTituloVisualizar);
        etDescricao = (EditText)findViewById(R.id.etDescricaoVisualizar);

        Intent intent = getIntent();
        final int id = intent.getIntExtra("id", -1);

        Lembrete lembrete = new Lembrete();
        LembreteDAO lembreteDAO = new LembreteDAO(getBaseContext());
        lembrete = lembreteDAO.buscarLembrete(id);

        etTitulo.setText(String.valueOf(lembrete.getTitulo().toString()));
        etDescricao.setText(String.valueOf(lembrete.getDescricao().toString()));


        Button btnSalvar = (Button)findViewById(R.id.btnSalvarVisualizar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LembreteDAO crud = new LembreteDAO(getBaseContext());
                EditText titulo = (EditText)findViewById(R.id.etTituloVisualizar);
                EditText descricao = (EditText)findViewById((R.id.etDescricaoVisualizar));
                String tituloString = titulo.getText().toString();
                String descricaoString = descricao.getText().toString();
                Lembrete lembrete = new Lembrete();
                lembrete.setId(id);
                lembrete.setTitulo(tituloString);
                lembrete.setDescricao(descricaoString);

                String resultado;
                resultado = crud.editarLembrete(lembrete);

                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

                onBackPressed();
            }
        });

        Button btnExcluir = (Button)findViewById(R.id.btnExcluirVisualizar);
        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LembreteDAO crud = new LembreteDAO(getBaseContext());

                String resultado;
                resultado = crud.excluirLembrete(id);

                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

                onBackPressed();
            }
        });
    }
}
