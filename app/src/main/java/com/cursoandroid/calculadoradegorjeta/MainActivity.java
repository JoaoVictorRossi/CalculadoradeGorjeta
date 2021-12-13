package com.cursoandroid.calculadoradegorjeta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.Touch;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private SeekBar tipSeek;
    private EditText userValue;
    private TextView textTotal, textTip, porcent;

    private double progressPorcent;

    NumberFormat formatter = new DecimalFormat("#0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tipSeek = findViewById(R.id.seekPorcent);
        userValue = findViewById(R.id.userValue);
        textTip = findViewById(R.id.textTip);
        textTotal = findViewById(R.id.textTotal);
        porcent = findViewById(R.id.porcentValue);


        seekBarPorcentTip();

    }

    public void seekBarPorcentTip() {

        tipSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressPorcent = progress;
                porcent.setText( Math.round(progress) + "%" );
                calculate();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                String valueUserString = userValue.getText().toString();

                if (valueUserString.equals("")) {
                    Toast.makeText(getApplicationContext(),
                            "Por favor, preencha o valor da conta.",
                            Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar){}

        });
    }

    public void calculate() {

        String valueUserString = userValue.getText().toString();

        if (!valueUserString.equals("")){
            double valueDouble = Double.parseDouble(valueUserString);

            Double finalValueTip = valueDouble * (progressPorcent / 100);
            Double finalValueTotal = valueDouble * (progressPorcent / 100) + valueDouble;

            textTip.setText("R$ " + formatter.format( finalValueTip));
            textTotal.setText("R$ " + formatter.format( finalValueTotal));
        }

    }

}