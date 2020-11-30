package com.example.livedata;

import androidx.lifecycle.LiveData;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.SECONDS;

public class EntrenadorPokemon {

    interface EntrenadorPokemonListener {
        void cuandoCambie(String orden);
    }

    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    ScheduledFuture<?> enEvolucion;

    void iniciarActo(final EntrenadorPokemonListener entrenadorPokemonListener) {
        if (enEvolucion == null || enEvolucion.isCancelled()) {
            enEvolucion = scheduler.scheduleAtFixedRate(new Runnable() {
                int numEvo;

                @Override
                public void run() {
                    switch (numEvo) {
                        case 0:
                            entrenadorPokemonListener.cuandoCambie("EVOLUCION 1");
                            break;
                        case 1:
                            entrenadorPokemonListener.cuandoCambie("EVOLUCION 2");
                            break;
                        case 2:
                            entrenadorPokemonListener.cuandoCambie("EVOLUCION 3");
                    }
                    numEvo++;
                    if (numEvo == 3) {
                        numEvo = 0;
                    }
                }
            }, 0, 1, SECONDS);
        }
    }

    void pararEvolucion() {
        if (enEvolucion != null) {
            enEvolucion.cancel(true);
        }
    }

    LiveData<String> evolucionLiveData = new LiveData<String>() {
        @Override
        protected void onActive() {
            super.onActive();

            //iniciarEntrenamiento -> iniciarActo
            iniciarActo(new EntrenadorPokemonListener() {
                @Override
                public void cuandoCambie(String acto) {
                    postValue(acto);
                }
            });
        }

        @Override
        protected void onInactive() {
            super.onInactive();
            pararEvolucion();
        }
    };
}