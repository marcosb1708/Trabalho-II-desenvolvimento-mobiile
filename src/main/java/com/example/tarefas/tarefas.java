package com.example.tarefas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class tarefas extends AppCompatActivity {

    private ListView listaTarefa;
    private Button btnNovaTarefa;
    private Tarefa t;
    DBHelper dbHelper;
    ArrayList<Tarefa> arrayListaTarefa;
    ArrayAdapter<Tarefa> arrayAdapterTarefa;
    Pasta p;
    int idPasta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefas);
        listaTarefa = findViewById(R.id.listTarefas);
        btnNovaTarefa = findViewById(R.id.btnNovaTarefa);
        Intent it = getIntent();

        p = (Pasta) it.getSerializableExtra("Chave_pasta");
        idPasta = p.getPastaId();
        btnNovaTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(tarefas.this,AddTarefas.class);
                it.putExtra("id_pasta", idPasta);
                startActivity(it);
            }
        });



    }
    public void preencheLista(){
        dbHelper = new DBHelper(tarefas.this);
        arrayListaTarefa = dbHelper.selectTodasTarefasPorPasta(idPasta);
        dbHelper.close();
        if(listaTarefa !=null){
            arrayAdapterTarefa = new ArrayAdapter<Tarefa>(tarefas.this,
                    android.R.layout.simple_list_item_1,arrayListaTarefa);
            listaTarefa.setAdapter(arrayAdapterTarefa);

        }
    }


    protected void onResume(){
        super.onResume();
        preencheLista();





    }

}