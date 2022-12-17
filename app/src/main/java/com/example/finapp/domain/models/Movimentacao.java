package com.example.finapp.domain.models;

import com.example.finapp.domain.enums.Operacao;

import java.util.Date;

public class Movimentacao {
    private String classification;
    private Operacao operationType;
    private Date date;
    private Double money;

    public Movimentacao(String classification, Operacao operationType, Date date, Double money) {
        this.classification = classification;
        this.operationType = operationType;
        this.date = date;
        this.money = money;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public Operacao getOperationType() {
        return operationType;
    }

    public void setOperationType(Operacao operationType) {
        this.operationType = operationType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}
