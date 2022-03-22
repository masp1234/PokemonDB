package com.example.pokemondb.controllers;


import com.example.pokemondb.models.Pokemon;
import com.example.pokemondb.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PokemonController {
    @Autowired
    PokemonService pokemonService;

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("/allPokemon")
    public String allPokemon(Model model) {
        model.addAttribute("pokemon", pokemonService.getAllPokemon());

        return "all-pokemon.html";

    }
    @PostMapping(value = "/all-pokemon")
    public String deletePokemon(@RequestParam("pokedexNumber") int id) {
       pokemonService.deletePokemon(id);

        return "redirect:/";
    }
}
