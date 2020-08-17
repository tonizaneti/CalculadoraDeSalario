package com.example.calculasalario2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
    private EditText edtTxtSalarioBruto;
    private EditText edtTxtDependentes;
    private EditText edtTxtDescontos;
    private Button btnCalcular;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtTxtSalarioBruto = (EditText) findViewById(R.id.edtTxtSalarioBruto);
        edtTxtDependentes = (EditText) findViewById(R.id.edtTxtDependentes);
        edtTxtDescontos = (EditText) findViewById(R.id.edtTxtDependentes);

        btnCalcular =(Button) findViewById(R.id.btnCalcular);

        //Clique do Botao

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Instancia a Intenty definindo a ResultsActivity como "destino"
                intent = new Intent(MainActivity.this, ResultsActivity.class);

                // passa os valores das TextEdits para o intent
                intent.putExtra("SALARIOBRUTO", edtTxtSalarioBruto.getText().toString());
                intent.putExtra("DEPENDENTES", edtTxtDependentes.getText().toString());
                intent.putExtra("DESCONTOS", edtTxtDescontos.getText().toString());

                //lanca a Activity com o intent por parametro
                startActivity(intent);

            }
        });
    }
}