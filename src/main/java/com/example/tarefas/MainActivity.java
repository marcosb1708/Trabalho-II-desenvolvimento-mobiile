package com.example.tarefas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listaPasta;
    private Button btnNovaPasta;
    private Pasta p;
    DBHelper dbHelper;
    ArrayList<Pasta> arrayListPasta;
    ArrayAdapter<Pasta> arrayAdapterPasta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaPasta = findViewById(R.id.listPastas);
        btnNovaPasta = findViewById(R.id.btnNovaTarefa);
        registerForContextMenu(listaPasta);


        btnNovaPasta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this,AdicionarPasta.class);
                startActivity(it);
            }
        });

        listaPasta.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Pasta pasta = (Pasta) arrayAdapterPasta.getItem(position);
            Intent it = new Intent(MainActivity.this,tarefas.class);
            it.putExtra("Chave_pasta",pasta);
            startActivity(it);
            }
        });

        listaPasta.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView,View view, int position, long id){
                p = arrayAdapterPasta.getItem(position);
                return false;
            }
        });

    }

    public void preencheLista(){
        dbHelper = new DBHelper(MainActivity.this);
        arrayListPasta = dbHelper.selectAllPastas();
        dbHelper.close();
        if(listaPasta !=null){
            arrayAdapterPasta = new ArrayAdapter<Pasta>(MainActivity.this,
                    android.R.layout.simple_list_item_1,arrayListPasta);
            listaPasta.setAdapter(arrayAdapterPasta);

        }
    }


    protected void onResume(){
        super.onResume();
        preencheLista();





    }

}

