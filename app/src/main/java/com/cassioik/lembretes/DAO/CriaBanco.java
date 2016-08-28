package com.cassioik.lembretes.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Cássio on 8/28/2016.
 */
public class CriaBanco extends SQLiteOpenHelper {
    //VERIFICAR PQ NAO CONSIGO DEIXAR COMO PRIVATE OS ATRIBUTOS
    public static final String NOME_BANCO = "lembretes.db";
    public static final String TABELA = "lembrete";
    public static final String ID = "_id";
    public static final String TITULO = "titulo";
    public static final String DESCRICAO = "descricao";
    public static final int VERSAO = 1;

    public CriaBanco(Context context){
        super(context, NOME_BANCO,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABELA+"("
                + ID + " integer primary key autoincrement,"
                + TITULO + " text,"
                + DESCRICAO + " text"
                +")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABELA);
        onCreate(db);
    }
}
