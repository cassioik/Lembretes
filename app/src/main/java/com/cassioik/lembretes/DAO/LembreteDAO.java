package com.cassioik.lembretes.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Cássio on 8/28/2016.
 */
public class LembreteDAO {
    private SQLiteDatabase db;
    private CriaBanco banco;

    public LembreteDAO(Context context){
        banco = new CriaBanco(context);
    }

    public String inserirLembrete(String titulo, String descricao){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CriaBanco.TITULO, titulo);
        valores.put(CriaBanco.DESCRICAO, descricao);

        resultado = db.insert(CriaBanco.TABELA, null, valores);
        db.close();

        if (resultado ==-1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";
    }

    public ArrayList<String> listarLembretes(){
        Cursor cursor;
        ArrayList<String> resultado = new ArrayList<String>();
        String[] campos = {banco.ID, banco.TITULO, banco.DESCRICAO};
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELA, campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }

        for (cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()) {
            resultado.add(cursor.getString(1));
        }

        db.close();
        return resultado;
    }


}
