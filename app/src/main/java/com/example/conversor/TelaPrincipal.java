package com.example.conversor;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * @author Rafael Carlos de Oliveira
 *         Faculdade Católica do Tocantins
 */
public class TelaPrincipal extends AppCompatActivity {

    Toolbar mToolbar;
    Button btConverter;
    Button btLimpar;
    static EditText etValor;
    static EditText etDolar;
    TextView tvResultadoFinal;
    double resultadoFinal = 0;

    static double etValor1;
    static final double etTaxa1 = 60;
    static final double etIof1 = 0.0038;

    static double etDolar1;

    double result;
    double result2;
    double result3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dolar_turismo);

        btConverter = (Button) findViewById(R.id.bt_calcular_activity_dolar_turismo);


        etDolar = (EditText) findViewById(R.id.et_dolar_turismo_activity_dolar_turismo);


        etValor = (EditText) findViewById(R.id.et_valor_activity_dolar_turismo);


        tvResultadoFinal = (TextView) findViewById(R.id.tv_resultado_activity_dolar_turismo);

        mToolbar = (Toolbar) findViewById(R.id.tb_activity_dolar_turismo);

        mToolbar.setTitle("Dólar Turismo");


        mToolbar.setLogo(R.drawable.ic_launcher);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);


        btConverter.setOnClickListener(new OnClickListener() {


            @Override
            public void onClick(View v) {

//				etIof1 = Double.parseDouble(etIof.getText().toString());
//				etTaxa1 = Double.parseDouble(etTaxa.getText().toString());
//				etValor1 = Double.parseDouble(etValor.getText().toString());

                etValor1 = 0;
                etDolar1 = 0;
                resultadoFinal = 0;


                recebeValores();

                if (etValor1 == 0 || etDolar1 == 0) {
                    AlertDialog.Builder Mensagem = new AlertDialog.Builder(TelaPrincipal.this, R.style.MaterialDrawerBaseTheme_AlertDialog);
                    Mensagem.setTitle("ERRO!");
                    Mensagem.setMessage("Insira algum valor maior que zero em todos os campos!");
                    Mensagem.setNegativeButton("OK", null);
                    Mensagem.show();
                    //result.setText(String.valueOf("ERRO"));

                } else {

                    result = etValor1 - etTaxa1;

                    result2 = result * etIof1;

                    result3 = result - result2;

                    resultadoFinal = result3 / etDolar1;

                    tvResultadoFinal.setText(String.valueOf(resultadoFinal));

                }
            }
        });

//        btLimpar.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                etDolar.setText("");
//                etValor.setText("");
//                tvResultadoFinal.setText("");
//
//
//            }
//        });


    }

    public static void recebeValores() {
        boolean valido = false;

        try {


            etValor1 = Double.parseDouble(etValor.getText().toString());
            etDolar1 = Double.parseDouble(etDolar.getText().toString());

            valido = true;

        } catch (Exception e) {
            valido = false;


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }

        return true;
    }
}
