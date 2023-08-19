package devandroid.claudio.appgaseta.view;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import devandroid.claudio.appgaseta.R;
import devandroid.claudio.appgaseta.apoio.UtilGasEta;
import devandroid.claudio.appgaseta.controller.CombustivelController;
import devandroid.claudio.appgaseta.model.Combustivel;

public class GasEtaActivity extends AppCompatActivity {
    Combustivel combustivelGasolina;
    Combustivel combustivelEtanol;
    public TextView textTitulo;
    public ImageView imageView;
    public EditText editLitroGasolina;
    public EditText editLitroEtanol;
    public Button btnCalcular;
    public TextView txtResultado;
    public Button btnLimpar;
    public Button btnSalvar;
    public Button btnFinalizar;

    double valorLitroEtanol;
    double valorLitroGasolina;
    String mensagemRetorno;

    CombustivelController combustivelController;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gaseta);
        iniciarComponentes();



    }

    public void iniciarComponentes(){
        textTitulo =  findViewById(R.id.textTitulo);
        imageView = findViewById(R.id.imageView);
        editLitroGasolina = findViewById(R.id.editLitroGasolina);
        editLitroEtanol = findViewById(R.id.editLitroEtanol);
        btnCalcular = findViewById(R.id.btnCalcular);
        txtResultado = findViewById(R.id.textResultado);
        btnLimpar = findViewById(R.id.btnLimpar);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnFinalizar = findViewById(R.id.btnFinalizar);
        combustivelController = new CombustivelController(GasEtaActivity.this);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(editLitroEtanol.getText())){
                    editLitroEtanol.setError("Obrigatório");
                    editLitroEtanol.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(editLitroGasolina.getText())){
                    editLitroGasolina.setError("Obrigatório");
                    editLitroGasolina.requestFocus();
                    return;
                }

                 valorLitroEtanol =  Double.valueOf(editLitroEtanol.getText().toString());
                 valorLitroGasolina = Double.valueOf(editLitroGasolina.getText().toString());
                 mensagemRetorno = UtilGasEta.calcularMelhorOpcao(valorLitroGasolina,valorLitroEtanol);
                 txtResultado.setText(mensagemRetorno);
                 combustivelController.limpar();
                 btnCalcular.setEnabled(true);
            }
        });


        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText( GasEtaActivity.this, "Até logo", Toast.LENGTH_LONG);
                finish();
            }
        });

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editLitroEtanol.setText("");
                editLitroGasolina.setText("");
                txtResultado.setText("RESULTADO");

                btnSalvar.setEnabled(false);
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                combustivelGasolina = new Combustivel();
                combustivelEtanol = new Combustivel();

                combustivelGasolina.setNomeDoCombustivel("Gasolina");
                combustivelEtanol.setNomeDoCombustivel("Etanol");
                combustivelGasolina.setPrecoDoCombustivel(valorLitroGasolina);
                combustivelEtanol.setPrecoDoCombustivel(valorLitroEtanol);

                combustivelGasolina.setRecomendacao(UtilGasEta.calcularMelhorOpcao(valorLitroGasolina,valorLitroEtanol));
                combustivelEtanol.setRecomendacao(UtilGasEta.calcularMelhorOpcao(valorLitroGasolina,valorLitroEtanol));
                combustivelController.salvar(combustivelEtanol);
                combustivelController.salvar(combustivelGasolina);

            }
        });
    }




}
