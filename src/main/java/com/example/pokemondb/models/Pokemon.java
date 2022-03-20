package com.example.pokemondb.models;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;




@Getter
@Setter
public class Pokemon {
    private int pokedexNumber;
    private String name;
    private int speed;
    private int specialDefence;
    private int specialAttack;
    private int defence;
    private int attack;
    private int hp;
    private String primaryType;
    private String secondaryType;

    public Pokemon(int pokedexNumber, String name,
                   int speed, int specialDefence,
                   int specialAttack, int defence,
                   int attack, int hp,
                   String primaryType, String secondaryType) {
        this.pokedexNumber = pokedexNumber;
        this.name = name;
        this.speed = speed;
        this.specialDefence = specialDefence;
        this.specialAttack = specialAttack;
        this.defence = defence;
        this.attack = attack;
        this.hp = hp;
        this.primaryType = primaryType;
        this.secondaryType = secondaryType;
    }
}
