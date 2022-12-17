package com.example.finapp.domain.enums;

public enum Operacao {
    CREDITO("CRÉDITO",1),
    DEBITO("DÉBITO",2);

    private String nomeOperacao;
    private int ordem;

    Operacao(String nomeOperacao, int ordem) {
        this.nomeOperacao = nomeOperacao;
        this.ordem = ordem;
    }

    @Override
    public String toString() {
        return nomeOperacao;
    }
}
