package com.example.tarefas;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AdicionarPasta extends AppCompatActivity {

    private EditText edtNome, edtDescricao;
    private Button btnSalvarPasta;
    private Pasta p;
    private DBHelper pastaHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_pasta);
        edtNome = findViewById(R.id.edtNomePasta);
        edtDescricao = findViewById(R.id.edtDescPasta);
        btnSalvarPasta = findViewById(R.id.btnSalvarPasta);
        pastaHelper = new DBHelper(AdicionarPasta.this);
        p = new Pasta();


        btnSalvarPasta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomeP = edtNome.getText().toString();
                String desc = edtDescricao.getText().toString();
                p.setNomePasta(nomeP);
                p.setDescricao(desc);

                pastaHelper.addPasta(p);
                pastaHelper.close();

            }
        });

    }
}