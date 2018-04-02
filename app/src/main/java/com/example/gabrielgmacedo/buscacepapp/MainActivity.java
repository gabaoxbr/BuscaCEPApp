package com.example.gabrielgmacedo.buscacepapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    public Button btnBuscarCep;
    public EditText cep;
    public TextView resposta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cep = findViewById(R.id.etMain_cep);
        resposta = findViewById(R.id.etMain_resposta);

        btnBuscarCep = (Button) findViewById(R.id.btnMain_buscarCep);

        btnBuscarCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    CEP retorno = new HttpService(cep.getText().toString()).execute().get();
                    resposta.setText(retorno.toString());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
