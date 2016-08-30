package com.cassioik.lembretes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.cassioik.lembretes.DAO.LembreteDAO;
import com.cassioik.lembretes.model.Lembrete;

public class VisualizarLembreteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView tvTitulo;
        TextView tvDescricao;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_lembrete);

        tvTitulo = (TextView)findViewById(R.id.tvTitulo);
        tvDescricao = (TextView)findViewById(R.id.tvDescricao);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", -1);

        Lembrete lembrete = new Lembrete();
        LembreteDAO lembreteDAO = new LembreteDAO(getBaseContext());
        lembrete = lembreteDAO.buscarLembrete(id);

        tvTitulo.setText(String.valueOf(lembrete.getTitulo().toString()));
        tvDescricao.setText(String.valueOf(lembrete.getDescricao().toString()));
    }
}
