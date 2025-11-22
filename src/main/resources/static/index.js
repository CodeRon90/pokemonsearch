document.addEventListener("DOMContentLoaded", function() {
    const searchBtn = document.getElementById("searchBtn");
    const resultsDiv = document.getElementById("results");

    searchBtn.addEventListener("click", function() {
        const name = document.getElementById("searchBox").value.trim();
        if (!name) {
            resultsDiv.innerHTML = "<p>Please enter a Pokémon name.</p>";
            return;
        }

        fetch(`http://localhost:8090/pokemon/name/${encodeURIComponent(name)}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error("Pokémon not found");
                }
                return response.json();
            })
            .then(pokemon => {
                const schemaData = {
                    ID: pokemon.id,
                    Name: pokemon.name,
                    PokemonType: pokemon.pokemontype,   // match backend JSON
                    HomeRegion: pokemon.homeregion,     // match backend JSON
                    Weight: pokemon.weight,
                    Height: pokemon.height
                };

                // Display in browser
                resultsDiv.innerHTML = `
                    <h3>${schemaData.Name}</h3>
                    <p>Type: ${schemaData.PokemonType}</p>
                    <p>Region: ${schemaData.HomeRegion}</p>
                    <p>Weight: ${schemaData.Weight}</p>
                    <p>Height: ${schemaData.Height}</p>
                `;
            })
            .catch(error => {
                resultsDiv.innerHTML = `<p>${error.message}</p>`;
            });
    });
});

