package com.example.conversor;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.conversor.conexao.FabricaNet;
import com.example.conversor.model.Cotacoes;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;
import com.nispok.snackbar.enums.SnackbarType;
import com.nispok.snackbar.listeners.ActionClickListener;

import java.lang.reflect.Type;
import java.math.BigDecimal;

/**
 * @author Rafael Carlos de Oliveira
 *         Faculdade Católica do Tocantins
 */
public class MainActivity extends AppCompatActivity {


    Toolbar mToolbar;
    ImageView ivInfo;
    TextView tvChamaTelaDolarTurismo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.tb_novo);
        ivInfo = (ImageView) findViewById(R.id.iv_info_novo);
        tvChamaTelaDolarTurismo = (TextView) findViewById(R.id.tv_chama_tela_dolar_novo);
        mToolbar.setTitle("Cotação");
        mToolbar.setLogo(R.drawable.ic_launcher);
        setSupportActionBar(mToolbar);

        SpannableString spanString = new SpannableString(tvChamaTelaDolarTurismo.getText().toString());
        spanString.setSpan(new UnderlineSpan(), 0, spanString.length(), 0);
        spanString.setSpan(new StyleSpan(Typeface.ITALIC), 0, spanString.length(), 0);
        tvChamaTelaDolarTurismo.setText(spanString);

//        BuscaDados buscaDados = new BuscaDados(this);
//        buscaDados.execute("http://developers.agenciaideias.com.br/cotacoes/json");

        if (FabricaNet.isNetworkAvailable(this)) {
            new BuscaDados(this).execute("http://developers.agenciaideias.com.br/cotacoes/json");

        } else {
//            Toast.makeText(this, "Por favor, verifique sua conexão de internet!", Toast.LENGTH_LONG).show();
            SnackbarManager.show(Snackbar.with(this).text("Por favor, verifique sua conexão de internet!")
                    .color(this.getResources().getColor(android.R.color.black))
                    .textColor(this.getResources().getColor(android.R.color.white))
                    .actionLabel("OK")
                    .type(SnackbarType.MULTI_LINE)
                    .duration(Snackbar.SnackbarDuration.LENGTH_INDEFINITE)
                    .actionColor(this.getResources().getColor(R.color.coloLink))
                    .actionListener(new ActionClickListener() {
                        @Override
                        public void onActionClicked(Snackbar snackbar) {

                        }
                    }));
        }

        ivInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TelaSobreActivity.class));
            }
        });

        tvChamaTelaDolarTurismo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TelaPrincipal.class));
            }
        });
    }
}

class BuscaDados extends AsyncTask<String, Void, Cotacoes> {

    Activity activity = null;
    ProgressDialog dialog;
    Button btCalcular;
    RadioButton rbDolar;
    RadioButton rbEuro;
    TextView tvResultado;
    EditText etMoeda, etValor;
    String jSon, dolar, euro;
    BigDecimal casas;
    double resultado = 0;


    public BuscaDados(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        dialog = new ProgressDialog(activity, R.style.MaterialDrawerBaseTheme_Dialog);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setMessage("Aguarde...");
        dialog.show();

    }

    @Override
    protected void onPostExecute(Cotacoes cotacoes) {
        super.onPostExecute(cotacoes);
        btCalcular = (Button) activity.findViewById(R.id.bt_calcular_cotacao_novo);
        etMoeda = (EditText) activity.findViewById(R.id.et_moeda_novo);
        etValor = (EditText) activity.findViewById(R.id.et_valor_real_calculo_novo);
        tvResultado = (TextView) activity.findViewById(R.id.tv_resultado_activity_novo);
        TextView tvAtualizacao = (TextView) activity.findViewById(R.id.tv_atualizacao_novo);
        RadioGroup rbMoedas = (RadioGroup) activity.findViewById(R.id.rg_moedas_novo);
        rbDolar = (RadioButton) activity.findViewById(R.id.rb_dolar_novo);
        rbEuro = (RadioButton) activity.findViewById(R.id.rb_euro_novo);
        long tempo = System.currentTimeMillis();

        if (System.currentTimeMillis() - tempo == 10000) {
            SnackbarManager.show(Snackbar.with(activity).text("Por favor, verifique sua conexão de internet!")
                    .color(activity.getResources().getColor(android.R.color.black))
                    .textColor(activity.getResources().getColor(android.R.color.white))
                    .actionLabel("OK")
                    .type(SnackbarType.MULTI_LINE)
                    .duration(Snackbar.SnackbarDuration.LENGTH_INDEFINITE)
                    .actionColor(activity.getResources().getColor(R.color.coloLink))
                    .actionListener(new ActionClickListener() {
                        @Override
                        public void onActionClicked(Snackbar snackbar) {

                        }
                    }));
        }

        dolar = cotacoes.getDolar().getCotacao();
        euro = cotacoes.getEuro().getCotacao();

        int indice = rbMoedas.getCheckedRadioButtonId();

        if (indice == R.id.rb_dolar_novo) {
            etMoeda.setText(dolar);
        }
        rbDolar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etMoeda.setText(dolar);
            }
        });

        rbEuro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etMoeda.setText(euro);
            }
        });

        btCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etMoeda == null || etValor == null || etValor.getText().toString().equals("")) {
                    mensagem("ERRO", "Por favor, insira valores nos campos!");
                } else {

                    double dolarConvertido = Double.parseDouble(etMoeda.getText().toString());
                    double realConvertido = Double.parseDouble(etValor.getText().toString());
                    resultado = dolarConvertido * realConvertido;
//                casas = new BigDecimal(resultado);
//                casas.setScale(4);
                    tvResultado.setText("R$ " + String.valueOf(resultado));
                }

            }
        });


        tvAtualizacao.setText("Última atualização: " + cotacoes.getAtualizacao().trim());
        dialog.dismiss();

        SnackbarManager.show(Snackbar.with(activity).text("Sucesso!").
                color(activity.getResources().getColor(android.R.color.black))
                .textColor(activity.getResources().getColor(android.R.color.white))
                .actionLabel("Ok")
                .duration(Snackbar.SnackbarDuration.LENGTH_LONG)
                .actionColor(activity.getResources().getColor(R.color.coloLink))
                .actionListener(new ActionClickListener() {
                    @Override
                    public void onActionClicked(Snackbar snackbar) {

                    }
                }));

    }

    @Override
    protected Cotacoes
    doInBackground(String... params) {

        jSon = FabricaNet.buscaDadosWebService(params[0]);

        Cotacoes cote = new Cotacoes();
        try {
            Gson gson = new Gson();

            Type tipo = new TypeToken<Cotacoes>() {
            }.getType();

            cote = gson.fromJson(jSon, tipo);
        } catch (Exception e) {
            Log.i("Erro", e.toString());
        }

        return cote;
    }

    public void mensagem(String titulo, String mensagem) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(activity, R.style.MaterialDrawerBaseTheme_AlertDialog);
        dialog.setTitle(titulo).setMessage(mensagem).setNegativeButton("Ok", null).show();
    }

}