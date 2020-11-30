package com.example.livedata;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

public class EntrenadorViewModel extends AndroidViewModel {
    EntrenadorPokemon entrenadorPokemon;
    LiveData<Integer> imagenLiveData;

    public EntrenadorViewModel(@NonNull Application application) {
        super(application);

        entrenadorPokemon = new EntrenadorPokemon();

        imagenLiveData = Transformations.switchMap(entrenadorPokemon.evolucionLiveData, new Function<String, LiveData<Integer>>() {

            @Override
            public LiveData<Integer> apply(String evo) {
                    int imagen;

                    switch (evo) {
                        case "EVOLUCION 1":
                        default:
                            imagen = R.drawable.char1;
                            break;
                        case "EVOLUCION 2":
                            imagen = R.drawable.char2;
                            break;
                        case "EVOLUCION 3":
                            imagen = R.drawable.char3;
                            break;
                    }
                    return new MutableLiveData<>(imagen);
            }
        });
    }

    LiveData<Integer> obtenerImagen(){
        return imagenLiveData;
    }
}