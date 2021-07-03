package com.example.tarefas;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;


public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME= "tarefas.db";
    //tabelas
    private static final String TABLE_PASTA = "pasta";
    private static final String TABLE_TAREFA = "tarefa";


    //colunas  pasta
    private static final String COLUM_PASTA_ID = "pastaId";
    private static final String COLUM_NOMEPASTA = "nomePasta";
    private static final String COLUM_DESCRICAO = "descricao";

    //colunas tarefa
    private static final String COLUM_TAREFA_ID = "tarefaId";
    private static final String COLUM_PASTAID = "pastaId";
    private static final String COLUM_NOMETAREFA = "nomeTarefa";
    private static final String COLUM_DATATERMINO = "dataTermino";
    private static final String COLUM_LOCALIZACAO = "localizacao";
    private static final String COLUM_STATUS = "status";
    private static final String TAG = "Eroor enquanto tentava adicionar vaga no banco de dados";


    SQLiteDatabase db;

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PASTA_TABLE = "CREATE TABLE "+ TABLE_PASTA +
                "("+
                COLUM_PASTA_ID +" INTEGER PRIMARY KEY,"+
                COLUM_NOMEPASTA +" TEXT,"+
                COLUM_DESCRICAO+" TEXT"+
                ")";
        //REFERENCES "+ TABLE_VAGA
        String CREATE_TAREFA_TABLE = "CREATE TABLE "+ TABLE_TAREFA +
                "("+
                COLUM_TAREFA_ID +" INTEGER PRIMARY KEY,"+
                COLUM_PASTAID+" INTEGER REFERENCES "+ TABLE_PASTA+","+
                COLUM_NOMETAREFA +" TEXT,"+
                COLUM_DATATERMINO +" TEXT,"+
                COLUM_LOCALIZACAO+" TEXT,"+
                COLUM_STATUS+" TEXT"+
                ")";

        db.execSQL(CREATE_PASTA_TABLE);
        db.execSQL(CREATE_TAREFA_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if(oldVersion != newVersion){
            db.execSQL("DROP TABLE IF EXISTS "+ TABLE_PASTA);
            db.execSQL("DROP TABLE IF EXISTS "+ TABLE_TAREFA);
            onCreate(db);
        }

    }


    public long addPasta(Pasta p){


        long retornoDB;

        db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(COLUM_NOMEPASTA, p.getNomePasta());
        values.put(COLUM_DESCRICAO, p.getDescricao());



        retornoDB=db.insert(TABLE_PASTA,null,values);
        String res = "Erro";
        Log.i("DBHelper",res);
        db.close();
        return retornoDB;

    }

    //@SuppressLint("LongLogTag")
    public long addTarefa(Tarefa t){
        long retornoDB;

        db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(COLUM_PASTAID, t.getPastaId());
        values.put(COLUM_NOMETAREFA, t.getTarefaNome());
        values.put(COLUM_DATATERMINO, t.getDataTermino());
        values.put(COLUM_LOCALIZACAO, t.getLocalizacao());
        values.put(COLUM_STATUS, t.getStatus());

        retornoDB=db.insert(TABLE_TAREFA,null,values);
        String res = "Erro";
        Log.i("DBHelper",res);
        db.close();
        return retornoDB;



    }





    public ArrayList<Pasta> selectAllPastas() {
        String[] coluns = {COLUM_PASTA_ID, COLUM_NOMEPASTA, COLUM_DESCRICAO};
        Cursor cursor = getWritableDatabase().query(TABLE_PASTA, coluns, null, null, null, null, null, null);
        ArrayList<Pasta> listaPastas = new ArrayList<Pasta>();
        while(cursor.moveToNext()){
            Pasta p = new Pasta();
            p.setPastaId(cursor.getInt(0));
            p.setNomePasta(cursor.getString(1));
            p.setDescricao(cursor.getString(2));
            listaPastas.add(p);
        }
        return listaPastas;
    };
    //COLUM_VAGAID,
    public ArrayList<Tarefa> selectTodasTarefas(){
        String[] coluns = {COLUM_TAREFA_ID,COLUM_PASTAID, COLUM_NOMETAREFA, COLUM_DATATERMINO,COLUM_LOCALIZACAO,COLUM_STATUS};
        Cursor cursor = getWritableDatabase().query(TABLE_TAREFA, coluns, null, null, null, null, null, null);
        ArrayList<Tarefa> listaTarefas = new ArrayList<Tarefa>();
        while(cursor.moveToNext()){
            Tarefa t = new Tarefa();
            t.setTarefaId(cursor.getInt(0));
            t.setPastaId(cursor.getInt(1));
            t.setTarefaNome(cursor.getString(2));
            t.setDataTermino(cursor.getString(3));
            t.setLocalizacao(cursor.getString(4));
            t.setStatus(cursor.getString(5));
            listaTarefas.add(t);
        }
        return listaTarefas;
    }

 /*   public ArrayList<Todos> selectTodosCandidatos2(){
        String[] coluns = {COLUM_PESSOA_ID,COLUM_VAGAID, COLUM_NOME, COLUM_CPF,COLUM_EMAIL,COLUM_TELEFONE};
        Cursor cursor = getWritableDatabase().query(TABLE_PESSOA, coluns, null, null, null, null, null, null);
        ArrayList<Todos> listaCandidato = new ArrayList<Todos>();
        while(cursor.moveToNext()){
            Todos c = new Todos();
            c.setCandidatoId(cursor.getInt(0));
            c.setVagaId(cursor.getInt(1));
            c.setNome(cursor.getString(2));
            c.setCpf(cursor.getString(3));
            c.setEmail(cursor.getString(4));
            c.setTelefone(cursor.getString(5));
            listaCandidato.add(c);
        }
        return listaCandidato;
    }

*/
    public long updateCandidato(Tarefa t){
        long retornoDB;
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUM_PASTAID, t.getPastaId());
        values.put(COLUM_NOMETAREFA, t.getTarefaNome());
        values.put(COLUM_DATATERMINO, t.getDataTermino());
        values.put(COLUM_LOCALIZACAO, t.getLocalizacao());
        values.put(COLUM_STATUS, t.getStatus());
        String[] args = {String.valueOf(t.getTarefaId())};
        retornoDB = db.update(TABLE_TAREFA,values,"tarefaId=?",args);
        db.close();
        return retornoDB;
    }
/*
    public long updateVaga(Vaga v){
        long retornoDB;
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUM_DESCRICAO, v.getDescricao());
        values.put(COLUM_HORAS, v.getHorasSemana());
        values.put(COLUM_VALOR, v.getValor());
        String[] args = {String.valueOf(v.getId())};
        retornoDB = db.update(TABLE_VAGA,values,"vagaId=?",args);
        db.close();
        return retornoDB;
    }
*/
    public long deleteVaga(Tarefa t){
        long retornoDB;
        db = this.getWritableDatabase();
        String[] args = {String.valueOf(t.getTarefaId())};
        retornoDB = db.delete(TABLE_TAREFA,COLUM_TAREFA_ID+"=?", args);
        return  retornoDB;
    }

/*
    public long deleteCandidato(Candidato c){
        long retornoDB;
        db = this.getWritableDatabase();
        String[] args = {String.valueOf(c.getCandidatoId())};
        retornoDB = db.delete(TABLE_PESSOA,COLUM_PESSOA_ID+"=?", args);
        return  retornoDB;
    }

*/

    public ArrayList<Tarefa> selectTodasTarefasPorPasta(int id){
        String[] coluns = {COLUM_TAREFA_ID,COLUM_PASTAID, COLUM_NOMETAREFA, COLUM_DATATERMINO,COLUM_LOCALIZACAO,COLUM_STATUS};
        String tarefas_select_query = String.format("select * from %s where %s=%x",
                TABLE_TAREFA,
                COLUM_PASTAID,
                id
        );
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(tarefas_select_query,null);
        ArrayList<Tarefa> listaTarefas = new ArrayList<Tarefa>();
        cursor.moveToFirst();
        while(cursor.moveToNext()){
            Tarefa t = new Tarefa();
            t.setTarefaId(cursor.getInt(0));
            t.setPastaId(cursor.getInt(1));
            t.setTarefaNome(cursor.getString(2));
            t.setDataTermino(cursor.getString(3));
            t.setLocalizacao(cursor.getString(4));
            t.setStatus(cursor.getString(5));
            listaTarefas.add(t);
        }
        return listaTarefas;
    }


}
