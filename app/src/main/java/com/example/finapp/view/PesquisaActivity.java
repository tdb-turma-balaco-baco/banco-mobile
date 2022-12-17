package com.example.finapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.finapp.R;
import com.example.finapp.domain.dtos.PesquisaDto;
import com.example.finapp.domain.enums.Operacao;
import com.example.finapp.domain.models.Movimentacao;
import com.example.finapp.view.adapter.TransacaoBancariaAdapter;
import com.example.finapp.view.util.CalendarUtil;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class PesquisaActivity extends AppCompatActivity {

    private EditText edt_dateStart;
    private EditText edt_dateEnd;
    private Button btn_search;
    private RecyclerView rv_bankTransactions;
    private RadioGroup rg_operation;

    private SimpleDateFormat dateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa);

        initializeComponents();
        setEditTextActions();
        setButtonAction();
    }

    private void initializeComponents(){
        edt_dateEnd = findViewById(R.id.edt_dtFim);
        edt_dateStart = findViewById(R.id.edt_dtInicio);
        btn_search = findViewById(R.id.btn_pesquisa);
        rg_operation = findViewById(R.id.p_rdGroup);
        rv_bankTransactions = findViewById(R.id.p_rv_transactions);
        rv_bankTransactions.setLayoutManager(new LinearLayoutManager(this));

        dateFormat = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","BR"));
    }

    private void setButtonAction(){
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchTransactions();
            }
        });
    }

    private void setEditTextActions(){
        edt_dateStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalendarUtil.openCalendar(PesquisaActivity.this, edt_dateStart);
            }
        });

        edt_dateEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalendarUtil.openCalendar(PesquisaActivity.this, edt_dateEnd);
            }
        });
    }

    private void searchTransactions(){
        PesquisaDto pesquisaDto = CreateSearchDto();
        if(pesquisaDto == null){
            Toast.makeText(this,R.string.msg_erro_pesquisa_dto_invalido, Toast.LENGTH_SHORT).show();
        }
        else{
            //Busca banco
            Toast.makeText(this,"Parou", Toast.LENGTH_SHORT).show();
            updateTransactionList(new ArrayList<>());
        }

    }

    private PesquisaDto CreateSearchDto(){
        try {
            Date startDate = dateFormat.parse(edt_dateStart.getText().toString());
            Date endDate = dateFormat.parse(edt_dateEnd.getText().toString());
            if (startDate.after(endDate)) return null;

            int filter = getOperationFilter(rg_operation.getCheckedRadioButtonId());
            if(filter == -1) return null;

            return new PesquisaDto(startDate, endDate, filter);
        }
        catch (ParseException e){
            return null;
        }
    }

    private int getOperationFilter(int radioButtonSelected)
    {
        switch (radioButtonSelected)
        {
            case R.id.p_rb_debito:
                return Operacao.DEBITO.ordinal();
            case R.id.p_rb_credito:
                return Operacao.CREDITO.ordinal();
            case R.id.p_rb_todas:
                return 0;
            default:
                return -1;
        }
    }

    private void updateTransactionList(ArrayList<Movimentacao> transactions){
        TransacaoBancariaAdapter tbAdapter = new TransacaoBancariaAdapter(this, transactions);
        rv_bankTransactions.setAdapter(tbAdapter);
    }
}