package com.example.pokemondb.services;


import com.example.pokemondb.models.Pokemon;
import com.example.pokemondb.repositories.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonService {
    @Autowired
    PokemonRepository pokemonRepository;


    public List<Pokemon> getAllPokemon() {
        return pokemonRepository.getAllPokemon();
    }

    public Pokemon deletePokemon(int id) {
        return pokemonRepository.deletePokemon(id);
    }
}
