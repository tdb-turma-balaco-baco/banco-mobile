package com.example.finapp.view.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.TextView;

import com.example.finapp.R;

public class TransacaoBancariaHolder extends RecyclerView.ViewHolder {

    TextView txtOperacao;
    TextView txtValor;
    TextView txtData;
    TextView txtClassificacao;

    public TransacaoBancariaHolder(@NonNull View itemView) {
        super(itemView);
        declareFields(itemView);
    }

    private void declareFields(@NonNull View itemView) {
        txtClassificacao = itemView.findViewById(R.id.v_txtClassificacao);
        txtValor = itemView.findViewById(R.id.v_txtValor);
        txtData = itemView.findViewById(R.id.v_txtData);
        txtOperacao = itemView.findViewById(R.id.v_txtOperacao);
    }
}