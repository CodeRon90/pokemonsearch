package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pokemon")
public class  PokemonController {

    @Autowired
    private PokemonDao pokemonDao;

    public PokemonController(PokemonDao pokemonDao) {
        this.pokemonDao = pokemonDao;
    }

    // GET all Pokémon
    @GetMapping
    public List<Pokemon> getAllPokemon() {
        return pokemonDao.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> getPokemonById(@PathVariable int id) {
        Pokemon pokemon = pokemonDao.findById(id);
        return pokemon == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(pokemon);
    }

/*
    @GetMapping("/{name}")
    public Pokemon getPokemonByName(@PathVariable String name) {
        return pokemonDao.findByName(name);
    }
*/
@GetMapping("/name/{name}")
public ResponseEntity<Pokemon> getPokemonByName(@PathVariable String name) {
    Pokemon pokemon = pokemonDao.findByName(name);
    return pokemon == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(pokemon);
}
}





