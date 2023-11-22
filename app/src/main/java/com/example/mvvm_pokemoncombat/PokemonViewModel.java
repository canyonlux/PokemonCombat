package com.example.mvvm_pokemoncombat;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Random;

public class PokemonViewModel extends ViewModel {
    // LiveData para mantener el estado de los dos Pokémon.
    private MutableLiveData<Pokemon> pokemon1 = new MutableLiveData<>();
    private MutableLiveData<Pokemon> pokemon2 = new MutableLiveData<>();

    // LiveData para actualizar el estado del combate en la UI.
    private MutableLiveData<String> estadoCombate = new MutableLiveData<>();
    private Random random = new Random();

    // Getter para obtener el estado actual del combate.
    public LiveData<String> getEstadoCombate() {
        return estadoCombate;
    }

    // Getter y Setter para los Pokémon.
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

    // Método para iniciar el combate. Se alterna el turno entre los Pokémon.
    public void iniciarCombate() {
        if (pokemon1.getValue() != null && pokemon2.getValue() != null) {
            realizarTurno(pokemon1.getValue(), pokemon2.getValue());
            realizarTurno(pokemon2.getValue(), pokemon1.getValue());
            // Verificar el estado de los Pokémon después de cada turno.
            verificarEstadoPokemon();
        }
    }

    // Verificar si alguno de los Pokémon se ha debilitado.
    private void verificarEstadoPokemon() {
        Pokemon p1 = pokemon1.getValue();
        Pokemon p2 = pokemon2.getValue();

        if (p1.getHp() <= 0 || p2.getHp() <= 0) {
            String perdedor = p1.getHp() <= 0 ? p1.getNombre() : p2.getNombre();
            estadoCombate.setValue(perdedor + " se ha debilitado! El juego ha terminado.");
        }
    }

    // Método para realizar un turno de combate.
    private void realizarTurno(Pokemon atacante, Pokemon defensor) {
        // Probabilidad de golpe especial.
        if (random.nextInt(100) < 20) { // 20% de probabilidad.
            realizarGolpeEspecial(atacante, defensor);
        } else {
            defensor.recibirDaño(atacante.getAtaque(), atacante.getAtaqueEspecial());
        }
        pokemon1.setValue(pokemon1.getValue());
        pokemon2.setValue(pokemon2.getValue());
    }

    // Método para realizar un golpe especial.
    private void realizarGolpeEspecial(Pokemon atacante, Pokemon defensor) {
        int dañoExtra = 50; // Valor de ejemplo para el daño extra.
        defensor.recibirDaño(atacante.getAtaque() + dañoExtra, atacante.getAtaqueEspecial() + dañoExtra);

        estadoCombate.setValue(atacante.getNombre() + " ha realizado un golpe especial!");
    }
}
