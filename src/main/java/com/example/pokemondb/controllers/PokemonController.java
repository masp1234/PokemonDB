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

        return "redirect:/allPokemon";
    }
    @PostMapping(value = "/add-new-pokemon")
    public String addPokemon(@RequestParam("pokedexNumber") int id,
                             @RequestParam("attack") int attack,
                             @RequestParam("defence") int defence,
                             @RequestParam("hp") int hp,
                             @RequestParam("name") String name,
                             @RequestParam("primaryType") String primaryType,
                             @RequestParam("secondaryType") String secondaryType,
                             @RequestParam("specialAttack") int specialAttack,
                             @RequestParam("specialDefence") int specialDefence,
                             @RequestParam("speed") int speed) {
        pokemonService.addPokemon(new Pokemon(id, name, speed, specialDefence, specialAttack,
                                                defence, attack, hp, primaryType, secondaryType));

        return "redirect:/allPokemon";
    }
    @GetMapping("/addNewPokemon")
    public String addPokemonPage() {
        return "add-new-pokemon.html";
    }
}
