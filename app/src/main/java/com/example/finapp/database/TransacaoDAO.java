package com.example.finapp.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.finapp.domain.enums.Operacao;
import com.example.finapp.domain.exceptions.DatabaseException;
import com.example.finapp.domain.models.Movimentacao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransacaoDAO {

    private SQLiteDatabase db;
    private DataBaseHelper dbHelper;

    public TransacaoDAO(Context context){
        dbHelper = new DataBaseHelper(context);
    }

    public void openConnection(){
        db = dbHelper.getWritableDatabase();
    }

    public void closeConnection(){
        dbHelper.close();
    }

    public boolean insertTransaction(Movimentacao transaction) throws DatabaseException {
        try {
            ContentValues values = new ContentValues();
            values.put(DataBaseHelper.COLUMN_VALOR, transaction.getMoney());
            values.put(DataBaseHelper.COLUMN_CLASSIFICACAO, transaction.getClassification());
            values.put(DataBaseHelper.COLUMN_DATA, transaction.getDate().getTime());
            values.put(DataBaseHelper.COLUMN_TIPO_OPERACAO, transaction.getOperationType().getOrdem());

            long id = db.insert(DataBaseHelper.TABLE_NAME, null, values);
            return id > 0;
        }catch (SQLException ex){
            throw new DatabaseException("insertError", ex);
        }
    }

    public List<Movimentacao> getLastFifteenTransactions() throws DatabaseException {
        try{
        List<Movimentacao> transactions = new ArrayList<>();

        String selectLastFifteenQuery = "SELECT  * FROM " + DataBaseHelper.TABLE_NAME + " ORDER BY " +
                DataBaseHelper.COLUMN_DATA + " DESC";

        Cursor cursor = db.rawQuery(selectLastFifteenQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Movimentacao transaction = parseTransaction(cursor);
                transactions.add(transaction);
            } while (cursor.moveToNext());
        }

        return transactions;
        }catch (SQLException ex){
            throw new DatabaseException("LastFifteenError",ex);
        }
    }


    public List<Movimentacao> getTransactionsClassified() throws DatabaseException {
        try {
            List<Movimentacao> transactions = new ArrayList<>();

            String selectQuery = "SELECT "+ DataBaseHelper.COLUMN_CLASSIFICACAO + "," + DataBaseHelper.COLUMN_TIPO_OPERACAO + ", SUM(" + DataBaseHelper.COLUMN_VALOR + ") AS valor FROM " + DataBaseHelper.TABLE_NAME +
                    " GROUP BY " + DataBaseHelper.COLUMN_CLASSIFICACAO + "," + DataBaseHelper.COLUMN_TIPO_OPERACAO +
                    " ORDER BY " + DataBaseHelper.COLUMN_TIPO_OPERACAO + " DESC";

            Cursor cursor = db.rawQuery(selectQuery, null);

            if (cursor.moveToFirst()) {
                do {
                    Movimentacao transaction = parseClassified(cursor);
                    transactions.add(transaction);
                } while (cursor.moveToNext());
            }

            return transactions;
        }catch (SQLException ex){
            throw new DatabaseException("ClassifiedError",ex);
        }
    }

    @SuppressLint("Range")
    public Double getBalance() throws DatabaseException {
        try {
            String selectQuery = "SELECT SUM(" + DataBaseHelper.COLUMN_VALOR + ") AS total FROM " + DataBaseHelper.TABLE_NAME +
                    " GROUP BY " + DataBaseHelper.COLUMN_TIPO_OPERACAO +
                    " ORDER BY " + DataBaseHelper.COLUMN_TIPO_OPERACAO + " DESC";

            Cursor cursor = db.rawQuery(selectQuery, null);

            Double debitValue = 0.0;
            Double creditValue = 0.0;

            if (cursor.moveToFirst()) {
                debitValue = cursor.getDouble(cursor.getColumnIndex("total"));
            }

            if (cursor.moveToNext()) {
                creditValue = cursor.getDouble(cursor.getColumnIndex("total"));
            }

            return creditValue - debitValue;
        }catch (SQLException ex){
            throw new DatabaseException("BalanceError",ex);
        }
    }

    public List<Movimentacao> getFilteredTransactions(Date startDate, Date endDate, int operationType) throws DatabaseException {
        try {
            List<Movimentacao> transactions = new ArrayList<>();

            String selectFiltered = "SELECT  * FROM " + DataBaseHelper.TABLE_NAME + " WHERE " +
                    DataBaseHelper.COLUMN_DATA + " BETWEEN " + startDate.getTime() + " AND " + endDate.getTime();

            if (operationType != 0) {
                selectFiltered = selectFiltered.concat(" AND " + DataBaseHelper.COLUMN_TIPO_OPERACAO + " = " + operationType);
            }

            selectFiltered = selectFiltered.concat(" ORDER BY " + DataBaseHelper.COLUMN_DATA + " DESC");

            Cursor cursor = db.rawQuery(selectFiltered, null);

            if (cursor.moveToFirst()) {
                do {
                    Movimentacao transaction = parseTransaction(cursor);
                    transactions.add(transaction);
                } while (cursor.moveToNext());
            }

            return transactions;
        }catch (SQLException ex){
            throw new DatabaseException("FilteredError",ex);
        }
    }

    @SuppressLint("Range")
    private Movimentacao parseTransaction(Cursor cursor) throws DatabaseException {
        Movimentacao transaction = new Movimentacao();
        transaction.setId(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COLUMN_ID)));
        transaction.setClassification(cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_CLASSIFICACAO)));
        transaction.setOperationType(Operacao.values()[cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COLUMN_TIPO_OPERACAO)) - 1]);
        transaction.setMoney(cursor.getDouble(cursor.getColumnIndex(DataBaseHelper.COLUMN_VALOR)));
        transaction.setDate(new Date(cursor.getLong(cursor.getColumnIndex(DataBaseHelper.COLUMN_DATA))));
        return transaction;
    }

    @SuppressLint("Range")
    private Movimentacao parseClassified(Cursor cursor) throws DatabaseException {
        Movimentacao transaction = new Movimentacao();
        transaction.setClassification(cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_CLASSIFICACAO)));
        transaction.setOperationType(Operacao.values()[cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COLUMN_TIPO_OPERACAO)) - 1]);
        transaction.setMoney(cursor.getDouble(cursor.getColumnIndex(DataBaseHelper.COLUMN_VALOR)));
        return transaction;
    }


}
