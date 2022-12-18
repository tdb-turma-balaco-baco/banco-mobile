package com.example.finapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finapp.R;
import com.example.finapp.database.TransacaoDAO;
import com.example.finapp.domain.enums.Operacao;
import com.example.finapp.domain.models.Movimentacao;
import com.example.finapp.view.adapter.TransacaoBancariaAdapter;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ExtratoActivity extends AppCompatActivity {

    private RecyclerView rv_transactions;
    private TextView txt_balance;
    private TransacaoDAO transacaoDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extrato);

        initializeComponents();
        populateTransactionList();
        updateBalance();
    }

    private void initializeComponents(){
        rv_transactions = findViewById(R.id.rv_extrato);
        txt_balance = findViewById(R.id.txt_saldoTotal);

        transacaoDAO = new TransacaoDAO(this);
        transacaoDAO.openConnection();
    }

    private void populateTransactionList(){
        List<Movimentacao> transactions = getTransactions();

        TransacaoBancariaAdapter tbAdapter = new TransacaoBancariaAdapter(this, transactions);
        rv_transactions.setLayoutManager(new LinearLayoutManager(this));
        rv_transactions.setAdapter(tbAdapter);
    }

    private void updateBalance(){
        txt_balance.setText(NumberFormat.getCurrencyInstance().format(getBalance()));
    }

    private List<Movimentacao> getTransactions(){
        try {
            return transacaoDAO.getLastFifteenTransactions();
        }catch (Exception ex){
            Toast.makeText(this, "Erro banco de dados!", Toast.LENGTH_SHORT).show();
            return new ArrayList<>();
        }
    }

    private Double getBalance(){
        try {
            return transacaoDAO.getBalance();
        }catch (Exception ex){
            Toast.makeText(this, "Erro banco de dados!", Toast.LENGTH_SHORT).show();
            return 0.0;
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