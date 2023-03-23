package com.lospibescompany.viewbinding;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

//Clase padre de vistas en android
public class MainActivityViewModel extends AndroidViewModel {
    private Context context;//Variable contexto para pasar al constructor de la clase
    private MutableLiveData<Double> conversion = null;

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
        if(conversion == null){
            this.conversion = new MutableLiveData<>();
        }
        return conversion;
    }

    public void conversion(String nro, String conv){
        double numero = Double.parseDouble(nro);
        double dolar = 1.09;
        double euro = 0.92;
        Toast.makeText(context, nro, Toast.LENGTH_SHORT).show();
        if(conv == "Dolar a Euro"){
            this.conversion.setValue(numero/dolar);
            Toast.makeText(context, "llego de dolar a euro", Toast.LENGTH_LONG).show();
        }
        else if(conv == "Euro a Dolar") {
            this.conversion.setValue(numero/euro);
            Toast.makeText(context, "llego de euro a dolar", Toast.LENGTH_LONG).show();
        }
    }
}
