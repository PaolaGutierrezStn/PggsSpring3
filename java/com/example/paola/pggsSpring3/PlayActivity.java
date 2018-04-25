package com.example.paola.pggsSpring3;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Paola on 03/04/2018.
 */

public class PlayActivity extends AppCompatActivity {
    private String[] nameMonument={"campana_de_dolores", "el_arbol_de_la_noche_triste", "cabeza_de_aguila", "monumento_a_hidalgo", "parroquia_de_los_dolores", "monumento_a_los_ninos_heroes"}; //
    private String[] shadowMonument={"s_campana_de_dolores", "s_el_arbol_de_la_noche_triste", "s_cabeza_de_aguila", "s_monumento_a_hidalgo", "s_parroquia_de_los_dolores", "s_monumento_a_los_ninos_heroes"}; //piece - recortar un pedazo de imagen
    private int attempts = 3;

    private Button btnAccept;
    private TextView txvAttempts;
    private EditText edtResponse;
    private ImageView imvImage;
    private TextView txvNext;

    private int numGenerated = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        btnAccept = findViewById(R.id.btn_accept);
        txvAttempts = findViewById(R.id.txv_attempts);
        edtResponse = findViewById(R.id.edt_response);
        imvImage = findViewById(R.id.imv_image);
        txvNext = findViewById(R.id.txv_next);
        numGenerated = randomM();
        shadow(numGenerated);
        txvAttempts.setText("Te quedan "+ attempts + " intentos");
        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtResponse.getText().toString().toLowerCase();
                if(name.equals(nameMonument[numGenerated])){
                    monument(numGenerated);
                    next();
                }else{
                    Toast.makeText(getApplicationContext(), "Incorrecto, vuelve a intentar", Toast.LENGTH_SHORT).show();
                    attempts = attempts -1;
                    txvAttempts.setText("Te quedan "+ attempts + " intentos");
                }

                if(attempts==0){
                    finish();
                }
            }
        });
    }

    public void next(){
        new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUtilFinished) {
                txvNext.setText("Siguiente monumento en " + (millisUtilFinished/1000));
            }

            @Override
            public void onFinish() {
                numGenerated = randomM();
                shadow(numGenerated);
                txvNext.setText("");
                edtResponse.setText("");
            }
        }.start();
    }

    private void monument(int num){
        int resId = getResources().getIdentifier(nameMonument[numGenerated], "drawable", getPackageName());
        imvImage.setImageResource(resId);
    }

    private void shadow(int num){
        int resId = getResources().getIdentifier(shadowMonument[numGenerated], "drawable", getPackageName());
        imvImage.setImageResource(resId);
    }

    private int randomM(){
        return (int)(Math.random()*nameMonument.length);
    }
}
