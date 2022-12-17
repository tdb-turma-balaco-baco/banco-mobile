package com.example.finapp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finapp.R;
import com.example.finapp.domain.enums.Operacao;
import com.example.finapp.domain.models.Movimentacao;

import java.util.List;

public class TransacaoBancariaAdapter extends RecyclerView.Adapter<TransacaoBancariaHolder> {

    Context context;
    List<Movimentacao> bankTransactions;

    public TransacaoBancariaAdapter(Context context, List<Movimentacao> bankTransactions) {
        this.context = context;
        this.bankTransactions = bankTransactions;
    }

    @NonNull
    @Override
    public TransacaoBancariaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TransacaoBancariaHolder(LayoutInflater.from(context).inflate(R.layout.activity_lista_dados_bancarios,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TransacaoBancariaHolder holder, int position) {
        Movimentacao movimentacao = bankTransactions.get(position);
        holder.txtClassificacao.setText(movimentacao.getClassification());
        holder.txtOperacao.setText(movimentacao.getOperationType().toString());

        holder.txtValor.setTextColor(getOperationColor(context, movimentacao.getOperationType()));
        holder.txtValor.setText(String.format("R$ %s", movimentacao.getMoney().toString()));
        holder.txtData.setText(movimentacao.getDate().toString());
    }

    @Override
    public int getItemCount() {
        return bankTransactions.size();
    }

    private int getOperationColor(Context context, Operacao operation){
        if(operation.equals(Operacao.DEBITO)){
            return context.getResources().getColor(com.google.android.material.R.color.design_default_color_error);
        }else{
            return R.color.purple_200;
        }
    }
}
