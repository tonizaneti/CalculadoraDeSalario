package com.example.calculasalario2;


import android.app.Activity;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import  android.content.Intent;

import org.w3c.dom.Text;


public class ResultsActivity extends Activity {
    private Button btnRetornar;

     private TextView txtSalarioBruto;
     private TextView txtINSS;
     private TextView txtIRRF;
     private TextView txtDescontos;
     private TextView txtSalarioLiquido;
     private TextView txtPercentualDescontos;
     private Intent intent;
     private String SALARIOBRUTO;
    private String DEPENDENTES;
    private String DESCONTOS;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);


        //associa os botões visuais
        txtSalarioBruto = (TextView) findViewById(R.id.txtSalarioBruto);
        txtINSS = (TextView) findViewById(R.id.txtINSS);
        txtIRRF = (TextView) findViewById(R.id.txtIRRF);
        txtDescontos=(TextView) findViewById(R.id.txtDescontos);
        txtSalarioLiquido=(TextView) findViewById(R.id.txtSalarioLiquido);
        txtPercentualDescontos = (TextView) findViewById(R.id.txtPercentualDescontos);
        btnRetornar = (Button) findViewById(R.id.btnRetornar);

        // recebe a intent enviada pela tela principal
        intent = getIntent();

        // obtem os parâmetros enviados pela intent
        SALARIOBRUTO = intent.getStringExtra("SALARIOBRUTO");
        //double SALARIOBRUTO = Double.parseDouble(SALARIOBRUTO);
        Double salarioBrutoDouble = Double.parseDouble(SALARIOBRUTO);
        DEPENDENTES = intent.getStringExtra("DEPENDENTES");
        Integer Dependentes = Integer.parseInt(DEPENDENTES);
        DESCONTOS = intent.getStringExtra("DESCONTOS");
        Double Descontos = Double.parseDouble(DESCONTOS);

        double contribuicaoINSS = calculaINSS(salarioBrutoDouble);
        double baseCalculo = salarioBrutoDouble - contribuicaoINSS - (Dependentes*189.59);
        double contribuicaoIRRF = calculaIRRF(baseCalculo);
        double salarioLiquido = salarioBrutoDouble - contribuicaoINSS - contribuicaoIRRF - Descontos;
        double percentualDescontos = (1-salarioLiquido/salarioBrutoDouble)*100;
        
        

        /*// soma os bichos
        soma = Double.parseDouble(numero1) + Double.parseDouble(numero2);*/

        // joga o resultado para tela
        txtSalarioBruto.setText(String.valueOf(SALARIOBRUTO));
        txtINSS.setText(String.valueOf(contribuicaoINSS));
        txtIRRF.setText(String.valueOf(contribuicaoIRRF));
        txtDescontos.setText(String.valueOf(Descontos));
        txtSalarioLiquido.setText(String.valueOf(salarioLiquido));
        txtPercentualDescontos.setText(String.valueOf(percentualDescontos));

        // clique do botão

        btnRetornar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish(); // isso fecha e retorna a Activity que me chamou - o mesmo que o hardbutton "BACK"
            }
        });
    }
    private double calculaINSS(double salarioBrutoDouble) {
        if(salarioBrutoDouble <=1045)
            return salarioBrutoDouble *0.075;
        if(salarioBrutoDouble <=2089.60)
            return(salarioBrutoDouble *0.09)-15.67;
        if(salarioBrutoDouble <=3134.40)
            return (salarioBrutoDouble *0.12)-78.36;
        if(salarioBrutoDouble<=6101.06)
            return (salarioBrutoDouble * 0.14)-141.05;
        else
            return 713.10;
    }

    private double calculaIRRF(double baseCalculo) {
        if(baseCalculo<=1903.98)
            return 0;
        if (baseCalculo<=2826.65)
            return (baseCalculo*0.075) -142.80;
        if(baseCalculo<=3751.05)
            return(baseCalculo*0.15)-354.80;
        if(baseCalculo<=4664.68)
            return(baseCalculo*0.225)-636.13;
        return(baseCalculo*0.275)-869.36;
    }
}