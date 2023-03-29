package com.lospibescompany.viewbinding;

import android.app.Application;
import android.content.Context;
import android.text.Editable;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.text.DecimalFormat;

//Clase padre de vistas en android
public class MainActivityViewModel extends AndroidViewModel {
    private Context context;//Variable contexto para pasar al constructor de la clase
    private MutableLiveData<Double> resultado = null;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.context=application.getApplicationContext();
    }

    //Generar getter para el mutable llamado conversion
    //LiveData es un objeto al que le puedo poner un observer (callback) y
    //que quede pendiente a ver si hay un cambio en el estado de la
    //variable resultado
    public LiveData<Double> getConversion(){
        //Pregunto si el resultado es nulo
        if(resultado == null){
            this.resultado = new MutableLiveData<>();
        }
        return resultado;
    }
    public void conversion(String dolar, String euro){
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        if(dolar.length() == 0){
            double nro2 = Double.parseDouble(euro);
            double eur = 0.92;
            double convertido = Double.parseDouble(decimalFormat.format(nro2/eur));
            this.resultado.setValue(convertido);
        }
        else if(euro.length() == 0){
            double nro1 = Double.parseDouble(dolar);
            double dol = 1.08;
            double convertido = Double.parseDouble(decimalFormat.format(nro1/dol));
            this.resultado.setValue(convertido);
        }
    }
}
