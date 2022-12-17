package com.example.finapp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finapp.R;
import com.example.finapp.domain.enums.Operacao;
import com.example.finapp.domain.models.Movimentacao;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class ClassificacaoAdapter extends RecyclerView.Adapter<TransacaoBancariaHolder> {

    Context context;
    List<Movimentacao> bankTransactions;

    public ClassificacaoAdapter(Context context, List<Movimentacao> bankTransactions) {
        this.context = context;
        this.bankTransactions = bankTransactions;
    }

    @NonNull
    @Override
    public TransacaoBancariaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TransacaoBancariaHolder(LayoutInflater.from(context).inflate(R.layout.activity_lista_categorias,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TransacaoBancariaHolder holder, int position) {
        Movimentacao movimentacao = bankTransactions.get(position);
        holder.txtClassificacao.setText(movimentacao.getClassification());
        holder.txtOperacao.setText(movimentacao.getOperationType().toString());

        holder.txtValor.setTextColor(getOperationColor(context, movimentacao.getOperationType()));
        holder.txtValor.setText(NumberFormat.getCurrencyInstance().format(movimentacao.getMoney()));
    }

    @Override
    public int getItemCount() {
        return bankTransactions.size();
    }

    private int getOperationColor(Context context, Operacao operation){
        if(operation.equals(Operacao.DEBITO)){
            return context.getResources().getColor(R.color.debito);
        }else{
            return context.getResources().getColor(R.color.credito);
        }
    }
}
