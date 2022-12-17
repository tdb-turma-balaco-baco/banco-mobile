package com.example.finapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.finapp.R;

public class DashboardActivity extends AppCompatActivity {


    private Button btn_classifiedList;
    private Button btn_searchStatement;
    private Button btn_exit;
    private Button btn_registerTransaction;
    private Button btn_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        initializeComponents();
        setButtonActions();
    }


    private void initializeComponents(){
        btn_registerTransaction = findViewById(R.id.btn_cadastro);
        btn_exit = findViewById(R.id.btn_sair);
        btn_search = findViewById(R.id.btn_pesquisa);
        btn_searchStatement = findViewById(R.id.btn_consultaExtrato);
        btn_classifiedList = findViewById(R.id.btn_listaClassificada);
    }

    private void setButtonActions(){
        btn_registerTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegisterTransactionActivity();
            }
        });

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSearchActivity();
            }
        });

        btn_classifiedList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openClassifiedListActivity();
            }
        });

        btn_searchStatement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBankStatementActivity();
            }
        });

        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeApp();
            }
        });


    }

    private void openRegisterTransactionActivity(){
        Intent registerTransaction = new Intent(this, CadastroMovimentacaoActivity.class);
        startActivity(registerTransaction);
    }

    private void openClassifiedListActivity(){
        Intent classifiedList = new Intent(this, ListaClassificadaActivity.class);
        startActivity(classifiedList);
    }

    private void openSearchActivity(){
        Intent search = new Intent(this, PesquisaActivity.class);
        startActivity(search);
    }

    private void openBankStatementActivity(){
        Intent bankStatement = new Intent(this, ExtratoActivity.class);
        startActivity(bankStatement);
    }

    private void closeApp(){
        finishAffinity();
        System.exit(0);
    }
}