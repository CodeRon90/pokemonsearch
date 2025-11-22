package com.example.demo;

import com.example.demo.Pokemon;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PokemonRowMapper implements RowMapper<Pokemon> {
    @Override
    public Pokemon mapRow(ResultSet rs, int rowNum) throws SQLException {
        Pokemon pokemon = new Pokemon();
        pokemon.setID(rs.getInt("ID"));
        pokemon.setName(rs.getString("NAME"));
        pokemon.setType(rs.getString("POKEMONTYPE"));
        pokemon.setHome(rs.getString("HOMEREGION"));
        pokemon.setWeight(rs.getInt("WEIGHT"));
        pokemon.setHeight(rs.getInt("HEIGHT"));
        return pokemon;
    }
}
