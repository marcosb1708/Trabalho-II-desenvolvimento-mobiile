package com.example.tarefas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddTarefas extends AppCompatActivity {

    private EditText edtNomeTarefa, edtDataTermino, edtLocal;
    private Button btnSalvarTarefa;
    private Tarefa t;
    private DBHelper TarefaHelper;
    private Integer idPasta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tarefas);

        edtNomeTarefa = findViewById(R.id.edtNomeTarefa);
        edtDataTermino = findViewById(R.id.edtDataTermino);
        edtLocal = findViewById(R.id.edtLocal);
        btnSalvarTarefa = findViewById(R.id.btnSalvarTarefa);
        TarefaHelper = new DBHelper(AddTarefas.this);
        t = new Tarefa();
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            idPasta = extras.getInt("id_pasta");
        }


        btnSalvarTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomeTarefa = edtNomeTarefa.getText().toString();
                String dataTermino = edtDataTermino.getText().toString();
                String local = edtLocal.getText().toString();

                t.setPastaId(idPasta);
                t.setTarefaNome(nomeTarefa);
                t.setDataTermino(dataTermino);
                t.setLocalizacao(local);
                t.setStatus("pendente");

                TarefaHelper.addTarefa(t);
                TarefaHelper.close();

            }
        });

    }
}