package com.example.mvvm_pokemoncombat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import com.example.mvvm_pokemoncombat.databinding.ActivityMainBinding;
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private PokemonViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Inicializa el PokemonViewModel
        viewModel = new ViewModelProvider(this).get(PokemonViewModel.class);

        binding.buttonIniciarCombate.setOnClickListener(v -> {
            try {
                Pokemon pokemon1 = crearPokemonDesdeUI(
                        binding.editTextPokemon1Nombre.getText().toString(),
                        Integer.parseInt(binding.editTextPokemon1Hp.getText().toString()),
                        Integer.parseInt(binding.editTextPokemon1Ataque.getText().toString()),
                        Integer.parseInt(binding.editTextPokemon1Defensa.getText().toString()),
                        Integer.parseInt(binding.editTextPokemon1AtaqueEspecial.getText().toString()),
                        Integer.parseInt(binding.editTextPokemon1DefensaEspecial.getText().toString())
                );

                Pokemon pokemon2 = crearPokemonDesdeUI(
                        binding.editTextPokemon2Nombre.getText().toString(),
                        Integer.parseInt(binding.editTextPokemon2Hp.getText().toString()),
                        Integer.parseInt(binding.editTextPokemon2Ataque.getText().toString()),
                        Integer.parseInt(binding.editTextPokemon2Defensa.getText().toString()),
                        Integer.parseInt(binding.editTextPokemon2AtaqueEspecial.getText().toString()),
                        Integer.parseInt(binding.editTextPokemon2DefensaEspecial.getText().toString())
                );

                viewModel.setPokemon1(pokemon1);
                viewModel.setPokemon2(pokemon2);

                viewModel.iniciarCombate();
            } catch (NumberFormatException e) {
                // Manejo de errores, por ejemplo, mostrar un mensaje de error al usuario
            }
        });


        // Observar los LiveData de PokemonViewModel
        viewModel.getPokemon1().observe(this, this::actualizarUIPokemon1);
        viewModel.getPokemon2().observe(this, this::actualizarUIPokemon2);
    }

    // Método para validar la entrada del usuario
    private boolean validarEntrada() {
        // Validar el nombre del primer Pokémon
        if (binding.editTextPokemon1Nombre.getText().toString().trim().isEmpty()) {
            // Mostrar algún mensaje de error o enfocar el campo de texto
            return false;
        }

        // Repetir la validación para los demás campos del primer Pokémon
        if (binding.editTextPokemon1Hp.getText().toString().trim().isEmpty() ||
                binding.editTextPokemon1Ataque.getText().toString().trim().isEmpty() ||
                binding.editTextPokemon1Defensa.getText().toString().trim().isEmpty() ||
                binding.editTextPokemon1AtaqueEspecial.getText().toString().trim().isEmpty() ||
                binding.editTextPokemon1DefensaEspecial.getText().toString().trim().isEmpty()) {
            // Mostrar algún mensaje de error o enfocar el campo de texto
            return false;
        }

        // Validar el nombre del segundo Pokémon
        if (binding.editTextPokemon2Nombre.getText().toString().trim().isEmpty()) {
            // Mostrar algún mensaje de error o enfocar el campo de texto
            return false;
        }

        // Repetir la validación para los demás campos del segundo Pokémon
        if (binding.editTextPokemon2Hp.getText().toString().trim().isEmpty() ||
                binding.editTextPokemon2Ataque.getText().toString().trim().isEmpty() ||
                binding.editTextPokemon2Defensa.getText().toString().trim().isEmpty() ||
                binding.editTextPokemon2AtaqueEspecial.getText().toString().trim().isEmpty() ||
                binding.editTextPokemon2DefensaEspecial.getText().toString().trim().isEmpty()) {
            // Mostrar algún mensaje de error o enfocar el campo de texto
            return false;
        }

        // Si llegamos aquí, significa que todas las entradas son válidas
        return true;
    }


    // Método para crear un objeto Pokemon a partir de la UI
    private Pokemon crearPokemonDesdeUI(String nombre, int hp, int ataque, int i, int parseInt, int anInt) {
        return new Pokemon(nombre, hp, ataque);
    }

    // Método para actualizar la UI con los datos del primer Pokemon
    private void actualizarUIPokemon1(Pokemon pokemon) {
        binding.textViewResultado.setText("Pokemon 1: " + pokemon.getNombre() + " HP: " + pokemon.getHp());
    }

    // Método para actualizar la UI con los datos del segundo Pokemon
    private void actualizarUIPokemon2(Pokemon pokemon) {
        binding.textViewResultado.append("\nPokemon 2: " + pokemon.getNombre() + " HP: " + pokemon.getHp());
    }
}
