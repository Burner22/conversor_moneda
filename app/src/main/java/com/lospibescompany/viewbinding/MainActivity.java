package com.lospibescompany.viewbinding;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.lospibescompany.viewbinding.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mv;
    private ActivityMainBinding binding;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //Inicializar MainActivityViewModel mv              //referencia a la app        //Clase que quiero crear
        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);

        binding.etDolaresCant.setEnabled(true);
        binding.etEurosCant.setEnabled(false);
        binding.rbDolarEuro.setChecked(true);
        binding.rbEuroDolar.setChecked(false);

        binding.btConvertir.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mv.conversion(binding.etDolaresCant.getText().toString(), binding.etEurosCant.getText().toString());
            }
        });

        //Poner Observer en esta activity al mutable
        mv.getConversion().observe(this, new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                binding.tvResultado.setText(aDouble.toString());
            }
        });

        binding.rbDolarEuro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.etDolaresCant.setEnabled(true);
                binding.etEurosCant.setEnabled(false);
                binding.etEurosCant.setText("");

            }
        });
        binding.rbEuroDolar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.etDolaresCant.setEnabled(false);
                binding.etEurosCant.setEnabled(true);
                binding.etDolaresCant.setText("");
            }
        });

    }
}