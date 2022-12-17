package com.example.finapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.finapp.R;
import com.example.finapp.domain.models.Movimentacao;
import com.example.finapp.view.adapter.ClassificacaoAdapter;

import java.util.ArrayList;

public class ListaClassificadaActivity extends AppCompatActivity {

    private RecyclerView rv_classification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_classificada);

        InitializeComponents();
        populateClassifiedList();
    }

    private void InitializeComponents(){
        rv_classification = findViewById(R.id.rv_classificacao);
    }

    private void populateClassifiedList(){
        //Busca no banco
        updateRecycleView(new ArrayList<>());
    }

    private void updateRecycleView(ArrayList<Movimentacao> transactionsClassified){
        rv_classification.setLayoutManager(new LinearLayoutManager(this));
        ClassificacaoAdapter classificationAdapter = new ClassificacaoAdapter(ListaClassificadaActivity.this, transactionsClassified);
        rv_classification.setAdapter(classificationAdapter);
    }


}