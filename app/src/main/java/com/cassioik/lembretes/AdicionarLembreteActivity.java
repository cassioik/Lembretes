package com.cassioik.lembretes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cassioik.lembretes.DAO.LembreteDAO;

public class AdicionarLembreteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_lembrete);

        Button btnSalvar = (Button)findViewById(R.id.btnSalvar);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LembreteDAO crud = new LembreteDAO(getBaseContext());
                EditText titulo = (EditText)findViewById(R.id.etTitulo);
                EditText descricao = (EditText)findViewById((R.id.etDescricao));
                String tituloString = titulo.getText().toString();
                String descricaoString = descricao.getText().toString();
                String resultado;

                resultado = crud.inserirLembrete(tituloString,descricaoString);

                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

                onBackPressed();
            }
        });
    }
}
