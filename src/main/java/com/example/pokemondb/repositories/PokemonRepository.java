package com.example.pokemondb.repositories;


import com.example.pokemondb.models.Pokemon;
import com.example.pokemondb.utilities.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PokemonRepository {
    // Kan også bare kalde metoden i ConnectionManager i
    // construktor og have Connection connection som attribut



    public List<Pokemon> getAllPokemon() {
        ArrayList<Pokemon> pokemons = new ArrayList<>();

        // Kan også bare kalde metoden i construktor og have Connection connection som attribut
        Connection connection = ConnectionManager.connectToMySQL();

        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM pokemon";
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                int pokedexNumber = rs.getInt(1);
                int attack = rs.getInt(2);
                int defence = rs.getInt(3);
                int hp = rs.getInt(4);
                String name = rs.getString(5);
                String primaryType = rs.getString(6);
                String secondaryType = rs.getString(7);
                int specialDefence = rs.getInt(8);
                int specialAttack = rs.getInt(9);
                int speed = rs.getInt(10);

                pokemons.add(new Pokemon(pokedexNumber, name, speed,
                        specialDefence, specialAttack, defence, attack,
                        hp, primaryType, secondaryType));
            }
        } catch (Exception e) {
            System.out.println("Kunne ikke hente pokemons: ");
            e.printStackTrace();
        }
        return pokemons;
    }

    public void insertPokemon(Pokemon pokemon) {
        Connection connection = ConnectionManager.connectToMySQL();
        String query = "INSERT INTO pokemon VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, pokemon.getPokedexNumber());
            ps.setInt(2, pokemon.getAttack());
            ps.setInt(3, pokemon.getDefence());
            ps.setInt(4, pokemon.getHp());
            ps.setString(5, pokemon.getName());
            ps.setString(6, pokemon.getPrimaryType());
            ps.setString(7, pokemon.getSecondaryType());
            ps.setInt(8, pokemon.getSpecialDefence());
            ps.setInt(9, pokemon.getSpecialAttack());
            ps.setInt(10, pokemon.getSpeed());

            ps.executeUpdate();


        } catch (Exception e) {
            System.out.println("Indsatte ikke pokemon: ");
            e.printStackTrace();
        }
    }

    public Pokemon deletePokemon(int id) {
        Connection connection = ConnectionManager.connectToMySQL();
        Pokemon selectedPokemon = selectPokemon(id);
        String query = "DELETE FROM pokemon WHERE pokedex_number=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("You deleted: " + selectedPokemon);

        } catch (Exception e) {
            System.out.print("kunne ikke slette pokemon: ");
            e.printStackTrace();
        }
        return selectedPokemon;
    }

    public Pokemon selectPokemon(int id) {
        Connection connection = ConnectionManager.connectToMySQL();
        Pokemon selectedPokemon = null;
        try {
            String query = "SELECT * FROM pokemon WHERE pokedex_number= " + id;
            Statement statement = connection.prepareStatement(query);

            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                int pokedexNumber = rs.getInt(1);
                int attack = rs.getInt(2);
                int defence = rs.getInt(3);
                int hp = rs.getInt(4);
                String name = rs.getString(5);
                String primaryType = rs.getString(6);
                String secondaryType = rs.getString(7);
                int specialDefence = rs.getInt(8);
                int specialAttack = rs.getInt(9);
                int speed = rs.getInt(10);
                selectedPokemon = new Pokemon(pokedexNumber, name, speed, specialDefence,
                                                specialAttack, defence, attack, hp,
                                                primaryType, secondaryType);
            }

        } catch (Exception e) {
            System.out.print("Kunne ikke hente den valgte pokemon: ");
            e.printStackTrace();
        }
        return selectedPokemon;
    }

    public void updatePokemon(Pokemon pokemon) {
        Connection connection = ConnectionManager.connectToMySQL();
        String query = "UPDATE pokemon SET attack = ?, defence = ?, " +
                          "hp = ?, name = ?, primary_type = ?, secondary_type = ?, " +
                            "special_attack = ?, special_defence = ?, speed = ? " +
                        "WHERE pokedex_number = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, pokemon.getAttack());
            preparedStatement.setInt(2, pokemon.getDefence());
            preparedStatement.setInt(3, pokemon.getHp());
            preparedStatement.setString(4, pokemon.getName());
            preparedStatement.setString(5, pokemon.getPrimaryType());
            preparedStatement.setString(6, pokemon.getSecondaryType());
            preparedStatement.setInt(7, pokemon.getSpecialAttack());
            preparedStatement.setInt(8, pokemon.getSpecialDefence());
            preparedStatement.setInt(9, pokemon.getSpeed());
            preparedStatement.setInt(10, pokemon.getPokedexNumber());

            preparedStatement.executeUpdate();

        }
        catch(SQLException e) {
            System.out.print("Kunne ikke opdatere pokemon: ");
            e.printStackTrace();
        }

    }
}

