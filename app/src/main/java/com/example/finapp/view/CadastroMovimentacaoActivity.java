package com.example.finapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.finapp.R;
import com.example.finapp.domain.enums.Operacao;
import com.example.finapp.domain.models.Movimentacao;
import com.example.finapp.view.util.CalendarUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CadastroMovimentacaoActivity extends AppCompatActivity {

    private EditText edtDate;
    private Button btn_register;
    private EditText edtMoney;
    private RadioButton rdCredit;
    private RadioGroup rg_operation;
    private RadioButton rdDebit;
    private Spinner dropdown_classification;

    private SimpleDateFormat dateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_movimentacao);

        initializeComponents();
        setEditTextAction();
        setButtonAction();
        setRadioButtonActions();
    }

    private void initializeComponents(){
        edtDate = findViewById(R.id.edtData);
        btn_register = findViewById(R.id.btn_salvar);
        rdCredit = findViewById(R.id.rb_credito);
        rdDebit = findViewById(R.id.rb_debito);
        edtMoney = findViewById(R.id.edt_valor);
        rg_operation = findViewById(R.id.rdOperacao);
        dropdown_classification = findViewById(R.id.dropdown_operacao);

        dateFormat = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","BR"));
    }

    private void setButtonAction(){
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveOperation();
            }
        });
    }

    private void setRadioButtonActions(){
        rdDebit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateSpinnerValueContent(false);
            }
        });

        rdCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateSpinnerValueContent(true);
            }
        });
    }


    private void updateSpinnerValueContent(boolean creditSelected){
        ArrayAdapter<CharSequence> adapter = creditSelected ? getCreditOperationsValue() : getDebitOperationsValue();
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown_classification.setAdapter(adapter);
    }

    private ArrayAdapter<CharSequence> getDebitOperationsValue(){
        return ArrayAdapter.createFromResource(this, R.array.operacoes_debito, android.R.layout.simple_spinner_item);
    }

    private ArrayAdapter<CharSequence> getCreditOperationsValue(){
        return ArrayAdapter.createFromResource(this, R.array.operacoes_credito, android.R.layout.simple_spinner_item);
    }

    private void setEditTextAction(){
        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalendarUtil.openCalendar(CadastroMovimentacaoActivity.this,edtDate);
            }
        });
    }

    private void saveOperation(){
        Movimentacao transaction = createTransaction();
        if(transaction == null){
            Toast.makeText(this,R.string.msg_erro_movimentacao_model, Toast.LENGTH_SHORT).show();
        }else{
            //Insere
        }
    }

    private Movimentacao createTransaction(){
        Double money;
        try{
            money = Double.parseDouble(edtMoney.getText().toString());
        } catch (NumberFormatException ex){
            return null;
        }

        Operacao operationSelected = getOperation(rg_operation.getCheckedRadioButtonId());
        if(operationSelected == null) return null;

        Date registerDate;
        try {
            registerDate = dateFormat.parse(edtDate.getText().toString());
        } catch (ParseException e) {
            return null;
        }

        String classification = dropdown_classification.getSelectedItem().toString();

        return new Movimentacao(classification, operationSelected, registerDate, money);
    }

    private Operacao getOperation(int radioButtonSelected)
    {
        switch (radioButtonSelected)
        {
            case R.id.rb_debito:
                return Operacao.DEBITO;
            case R.id.rb_credito:
                return Operacao.CREDITO;
            default:
                return null;
        }
    }

}