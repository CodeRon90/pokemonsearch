package com.example.demo;

import com.example.demo.Pokemon;
import com.example.demo.PokemonRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Repository   // <-- This tells Spring to register it as a bean
public class PokemonDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Pokemon> findAll() {
        String sql = "SELECT * FROM Pokemon";
        return jdbcTemplate.query(sql, new PokemonRowMapper());
    }

    public Pokemon findById(int id) {
        String sql = "SELECT * FROM Pokemon WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, new PokemonRowMapper(), id);
    }

/*
    public Pokemon findByName(String name) {
        String sql = "SELECT * FROM Pokemon WHERE NAME = ?";
        return jdbcTemplate.queryForObject(sql, new PokemonRowMapper(), name);
    }

 */

    public Pokemon findByName(String name) {
        String sql = "SELECT * FROM Pokemon WHERE NAME = ?";
        List<Pokemon> results = jdbcTemplate.query(sql, new PokemonRowMapper(), name);
        return results.isEmpty() ? null : results.get(0);
    }


    public int save(Pokemon pokemon) {
        String sql = "INSERT INTO Pokemon (ID, Name, PokemonType, HomeRegion, Weight, Height) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                pokemon.getID(),
                pokemon.getName(),
                pokemon.getType(),
                pokemon.getHome(),
                pokemon.getWeight(),
                pokemon.getHeight()
        );
    }
}