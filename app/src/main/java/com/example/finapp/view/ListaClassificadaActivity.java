package com.example.finapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.finapp.R;
import com.example.finapp.database.TransacaoDAO;
import com.example.finapp.domain.exceptions.DatabaseException;
import com.example.finapp.domain.models.Movimentacao;
import com.example.finapp.view.adapter.ClassificacaoAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListaClassificadaActivity extends AppCompatActivity {

    private RecyclerView rv_classification;

    private TransacaoDAO transacaoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_classificada);

        InitializeComponents();
        populateClassifiedList();
    }

    private void InitializeComponents(){
        rv_classification = findViewById(R.id.rv_classificacao);

        transacaoDAO = new TransacaoDAO(this);
        transacaoDAO.openConnection();
    }

    private void populateClassifiedList(){
        List<Movimentacao> classifiedList = getClassifiedList();
        updateRecycleView(classifiedList);
    }

    private void updateRecycleView(List<Movimentacao> transactionsClassified){
        rv_classification.setLayoutManager(new LinearLayoutManager(this));
        ClassificacaoAdapter classificationAdapter = new ClassificacaoAdapter(ListaClassificadaActivity.this, transactionsClassified);
        rv_classification.setAdapter(classificationAdapter);
    }

    private List<Movimentacao> getClassifiedList(){
        try {
            return transacaoDAO.getTransactionsClassified();
        }catch (DatabaseException e){
            Toast.makeText(this, "Erro banco de dados!", Toast.LENGTH_SHORT).show();
            return new ArrayList<>();
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        transacaoDAO.openConnection();
    }

    @Override
    protected void onPause(){
        super.onPause();
        transacaoDAO.closeConnection();
    }


}