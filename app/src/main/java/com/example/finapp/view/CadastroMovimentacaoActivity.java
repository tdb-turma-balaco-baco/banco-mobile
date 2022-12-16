package com.example.finapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.example.finapp.R;

import java.util.Calendar;

public class CadastroMovimentacaoActivity extends AppCompatActivity {

    private EditText edtDate;
    private Button btn_register;
    private RadioButton rdCredit;
    private RadioButton rdDebit;
    private Spinner dropdown_classification;

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
        dropdown_classification = findViewById(R.id.dropdown_operacao);
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
                openCalendar();
            }
        });
    }

    private void openCalendar(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(CadastroMovimentacaoActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1 += 1;
                String dateFormat = String.format("%d/%d/%d",i, i1,i2);
                edtDate.setText(dateFormat);
            }
        },year,month,day);
        dialog.show();
    }

    private void saveOperation(){

    }

}