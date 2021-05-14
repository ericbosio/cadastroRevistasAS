package com.example.cadastrorevistas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class RevistaDAO {

    public static void inserir(Revista revista, Context context){
        ContentValues valores = new ContentValues();
        valores.put("nome", revista.nome );
        valores.put("categoria", revista.categoria );
        valores.put("ano", revista.getAno() );

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.insert("revista", null, valores);
    }

    public static void editar(Revista revista, Context context){
        ContentValues valores = new ContentValues();
        valores.put("nome", revista.nome );
        valores.put("categoria", revista.categoria );
        valores.put("ano", revista.getAno() );

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.update("revista", valores, " id = " + revista.id , null );
    }

    public static void excluir(int id, Context context){
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();
        db.delete("revista", " id = " + id , null);
    }

    public static List<Revista> getRevistas(Context context){
        List<Revista> lista = new ArrayList<>();
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, nome, categoria, ano FROM revista ORDER BY nome", null );
        if( cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do{
                Revista revista = new Revista();
                revista.id = cursor.getInt( 0);
                revista.nome = cursor.getString(1);
                revista.categoria = cursor.getString(2);
                revista.setAno( cursor.getInt(3) );
                lista.add(revista);
            }while( cursor.moveToNext() );
        }
        return lista;
    }

    public static Revista getRevistaById(Context context, int id){
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, nome, categoria, ano FROM revista WHERE id = " + id, null );
        if( cursor.getCount() > 0 ){
            cursor.moveToFirst();
            Revista revista = new Revista();
            revista.id = cursor.getInt( 0);
            revista.nome = cursor.getString(1);
            revista.categoria = cursor.getString(2);
            revista.setAno( cursor.getInt(3) );
            return revista;
        }else{
            return null;
        }
    }



}