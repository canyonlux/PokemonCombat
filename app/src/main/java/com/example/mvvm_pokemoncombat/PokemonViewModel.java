package com.example.mvvm_pokemoncombat;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Random;

public class PokemonViewModel extends ViewModel {
    private MutableLiveData<Pokemon> pokemon1;
    private MutableLiveData<Pokemon> pokemon2;

    private MutableLiveData<String> estadoCombate = new MutableLiveData<>();

    public LiveData<String> getEstadoCombate() {
        return estadoCombate;
    }
    private Random random = new Random();

    public PokemonViewModel() {
        pokemon1 = new MutableLiveData<>();
        pokemon2 = new MutableLiveData<>();
    }

    public LiveData<Pokemon> getPokemon1() {
        return pokemon1;
    }

    public LiveData<Pokemon> getPokemon2() {
        return pokemon2;
    }

    public void setPokemon1(Pokemon pokemon) {
        pokemon1.setValue(pokemon);
    }

    public void setPokemon2(Pokemon pokemon) {
        pokemon2.setValue(pokemon);
    }

    // Método para iniciar el combate mejorado
    public void iniciarCombate() {
        if (pokemon1.getValue() != null && pokemon2.getValue() != null) {
            Pokemon p1 = pokemon1.getValue();
            Pokemon p2 = pokemon2.getValue();

            realizarTurno(p1, p2);
            if (p2.getHp() <= 0) {
                estadoCombate.setValue(p2.getNombre() + " se ha debilitado!");
                return; // Terminar el combate si uno se debilita
            }

            realizarTurno(p2, p1);
            if (p1.getHp() <= 0) {
                estadoCombate.setValue(p1.getNombre() + " se ha debilitado!");
                return; // Terminar el combate si uno se debilita
            }

            pokemon1.setValue(p1);
            pokemon2.setValue(p2);
        }
    }

    // Método para realizar un turno de combate
    private void realizarTurno(Pokemon atacante, Pokemon defensor) {
        // Probabilidad de golpe especial
        if (random.nextInt(100) < 20) { // 20% de probabilidad
            realizarGolpeEspecial(atacante, defensor);
        } else {
            defensor.recibirDaño(atacante.getAtaque(), atacante.getAtaqueEspecial());
        }

        // Probabilidad de defensa especial
        if (random.nextInt(100) < 15) { // 15% de probabilidad
            realizarDefensaEspecial(defensor);
        }
    }

    // Método para realizar un golpe especial
    private void realizarGolpeEspecial(Pokemon atacante, Pokemon defensor) {
        int dañoExtra = 50;
        defensor.recibirDaño(atacante.getAtaque() + dañoExtra, atacante.getAtaqueEspecial() + dañoExtra);
        estadoCombate.setValue(atacante.getNombre() + " ha realizado un golpe especial!");

    }

    // Método para realizar una defensa especial
    private void realizarDefensaEspecial(Pokemon defensor) {
        int recuperación = 30;
        defensor.setHp(Math.min(defensor.getHp() + recuperación, 999));
        estadoCombate.setValue(defensor.getNombre() + " ha realizado una defensa especial!");
        // Otras lógicas de la defensa especial
    }
}

