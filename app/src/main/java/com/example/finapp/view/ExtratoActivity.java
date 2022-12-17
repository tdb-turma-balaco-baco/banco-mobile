package com.example.finapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.finapp.R;
import com.example.finapp.domain.enums.Operacao;
import com.example.finapp.domain.models.Movimentacao;
import com.example.finapp.view.adapter.TransacaoBancariaAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ExtratoActivity extends AppCompatActivity {

    private RecyclerView rv_transactions;
    private TextView txt_balance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extrato);

        initializeComponents();
        populateTransactionList();
    }

    private void initializeComponents(){
        rv_transactions = findViewById(R.id.rv_extrato);
        txt_balance = findViewById(R.id.txt_saldoTotal);
    }

    private void populateTransactionList(){
        Calendar calendar = Calendar.getInstance();
        ArrayList<Movimentacao> movimentacoes = new ArrayList<Movimentacao>(){{
            add(new Movimentacao("Moradia", Operacao.DEBITO, calendar.getTime(), 123.23));
            add(new Movimentacao("Salário", Operacao.CREDITO, calendar.getTime(), 5123.23));
            add(new Movimentacao("Salário", Operacao.CREDITO, calendar.getTime(), 5123.23));
            add(new Movimentacao("Moradia", Operacao.DEBITO, calendar.getTime(), 123.23));
            add(new Movimentacao("Moradia", Operacao.DEBITO, calendar.getTime(), 123.23));
            add(new Movimentacao("Moradia", Operacao.DEBITO, calendar.getTime(), 123.23));
            add(new Movimentacao("Saúde", Operacao.DEBITO, calendar.getTime(), 2433.13));
            add(new Movimentacao("Saúde", Operacao.DEBITO, calendar.getTime(), 2433.13));
            add(new Movimentacao("Saúde", Operacao.DEBITO, calendar.getTime(), 2433.13));
            add(new Movimentacao("Saúde", Operacao.DEBITO, calendar.getTime(), 2433.13));
        }};

        TransacaoBancariaAdapter tbAdapter = new TransacaoBancariaAdapter(this, movimentacoes);
        rv_transactions.setLayoutManager(new LinearLayoutManager(this));
        rv_transactions.setAdapter(tbAdapter);
    }
}