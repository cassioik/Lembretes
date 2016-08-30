package com.cassioik.lembretes.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cassioik.lembretes.model.Lembrete;

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

    public ArrayList<Lembrete> listarLembretes(){
        Cursor cursor;
        ArrayList<Lembrete> resultado = new ArrayList<Lembrete>();
        String[] campos = {banco.ID, banco.TITULO, banco.DESCRICAO};
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELA, campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }

        for (cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()) {
            Lembrete lembrete = new Lembrete();
            lembrete.setId(Integer.parseInt(cursor.getString(0)));
            lembrete.setTitulo(cursor.getString(1));
            lembrete.setDescricao(cursor.getString(2));
            resultado.add(lembrete);
        }

        db.close();
        return resultado;
    }

    public Lembrete buscarLembrete(int id){
        Cursor cursor;
        String[] campos = {banco.ID, banco.TITULO, banco.DESCRICAO};
        String where = banco.ID + " = ?";
        String[] id_string = {String.valueOf(id)};
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELA, campos, where, id_string, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }

        Lembrete lembrete = new Lembrete();
        lembrete.setId(Integer.parseInt(cursor.getString(0)));
        lembrete.setTitulo(cursor.getString(1));
        lembrete.setDescricao(cursor.getString(2));

        return lembrete;
    }

}
