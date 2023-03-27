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
    public String aux = "Dolar a Euro";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //Inicializar MainActivityViewModel mv              //referencia a la app        //Clase que quiero crear
        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);

        binding.rbDolarEuro.setChecked(true);
        binding.etDolaresCant.setEnabled(true);
        binding.rbEuroDolar.setChecked(false);
        binding.etEurosCant.setEnabled(false);

        //Poner listener al boton Convertir//De parametro va objeto OnClickListener
        binding.btConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Devuelve un editable que se puede parsear a necesidad
                if(aux == "Euro a Dolar"){
                    mv.conversion(binding.etEurosCant.getText().toString(), aux);
                    Toast.makeText(MainActivity.this, binding.etEurosCant.getText().toString() , Toast.LENGTH_LONG).show();
                }
                else if (aux == "Dolar a Euro"){
                    mv.conversion(binding.etDolaresCant.getText().toString(), aux);
                    Toast.makeText(MainActivity.this, binding.etDolaresCant.getText().toString() , Toast.LENGTH_SHORT).show();
                }

            }
        });

        //Poner Observer en esta activity al mutable
        mv.getConversion().observe(this, new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                binding.tvResultado.setText(aDouble.toString());
            }
        });

        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Manejar cambio de selección
                RadioButton radioButton = findViewById(checkedId);

                //Toast.makeText(context, aux, Toast.LENGTH_SHORT).show();
                if(aux == "Euro a Dolar"){
                    aux = "Dolar a Euro";
                    binding.rbDolarEuro.setChecked(true);
                    binding.etDolaresCant.setEnabled(true);
                    binding.rbEuroDolar.setChecked(false);
                    binding.etEurosCant.setEnabled(false);
                    binding.etEurosCant.setText("");
                    //Toast.makeText(context, "dolares deshabilitados", Toast.LENGTH_SHORT).show();
                }
                else if(aux == "Dolar a Euro"){
                    aux = "Euro a Dolar";
                    binding.rbDolarEuro.setChecked(false);
                    binding.etDolaresCant.setEnabled(false);
                    binding.rbEuroDolar.setChecked(true);
                    binding.etEurosCant.setEnabled(true);
                    binding.etDolaresCant.setText("");
                    //Toast.makeText(context, "euros deshabilitados", Toast.LENGTH_SHORT).show();
                }
                //Toast.makeText(MainActivity.this, radioButton.getText() , Toast.LENGTH_SHORT).show();
            }
        });

    }
}