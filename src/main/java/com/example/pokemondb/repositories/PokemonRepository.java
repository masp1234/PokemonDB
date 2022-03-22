package com.example.pokemondb.repositories;


import com.example.pokemondb.models.Pokemon;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PokemonRepository {
    private static String DB_URL = "jdbc:mysql://localhost:3306/pokedex?useSSL=false&serverTimezone=UTC"; //efter3306 skriver hvad det er for en tabel
    private static String user = "root";
    private static String password = "masp123123";
    private static Connection connection;

    public PokemonRepository() {
        connectToMySQL();
    }


    public List<Pokemon> getAllPokemon() {
        ArrayList<Pokemon> pokemons = new ArrayList<>();

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

    public void insertPokemon(int pokedexNumber, String name, int speed, int specialDefence, int specialAttack,
                              int defence, int attack, int hp, String primaryType, String secondaryType) {
        String query = "INSERT INTO pokemon VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, pokedexNumber);
            ps.setInt(2, attack);
            ps.setInt(3, defence);
            ps.setInt(4, hp);
            ps.setString(5, name);
            ps.setString(6, primaryType);
            ps.setString(7, secondaryType);
            ps.setInt(8, specialDefence);
            ps.setInt(9, specialAttack);
            ps.setInt(10, speed);

            ps.executeUpdate();


        } catch (Exception e) {
            System.out.println("Indsatte ikke pokemon: ");
            e.printStackTrace();
        }
    }

    public void connectToMySQL() {
        try {
            connection = DriverManager.getConnection(DB_URL, user, password);
            System.out.println("Virker nu");
        } catch (Exception e) {
            System.out.println("Virker ikke: ");
            e.printStackTrace();
        }
    }

    public Pokemon deletePokemon(int id) {
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

    public static Pokemon selectPokemon(int id) {
        Pokemon selectedPokemon = null;
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM pokemon WHERE pokedex_number = " + id;
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
  /*  public void updateUser(int userId) {
        Pokemon selectPokemon = selectPokemon(userId);

        System.out.println("Du har valgt at redigere: " + selectPokemon);
        Scanner input = new Scanner(System.in);
        System.out.println("Indtast nyt pokedex nummer: ");
        String newName = input.nextLine();
        System.out.println("Vælg nyt password: ");
        String newPassword = input.nextLine();
        System.out.println("Vælg nyt gruppe id");
        int newGroupID = Integer.parseInt(input.nextLine());

        String query = "UPDATE brugere SET brugernavn = ?, kodeord = ?, gruppe_id = ? " +
                "WHERE id_bruger = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newName);
            preparedStatement.setString(2, newPassword);
            preparedStatement.setInt(3, newGroupID);
            preparedStatement.setInt(4, userId);

            preparedStatement.executeUpdate();
            User updatedUser = new User(userId, newName, newPassword, newGroupID);
            System.out.println("You updated: " + selectPokemon + "to " + updatedUser);
        }
        catch(Exception e) {
            System.out.println("Kunne ikke opdatere bruger: " + e.getMessage());
        }

    }

   */
}

