package pokedex.pokemonDetails.infrastructure;

import pokedex.pokemonDetails.domain.FavouriteCountRepository;
import pokedex.pokemonDetails.domain.PokemonID;

import java.util.HashMap;
import java.util.Map;

public class InMemoryFavouriteCountRepository implements FavouriteCountRepository {
    private static final Map<Integer, Integer> countOfFabouritePokemons = new HashMap<>();

    @Override
    public int getById(PokemonID pokemonID) {
        if (countOfFabouritePokemons.containsKey(pokemonID.ID())) {
            return countOfFabouritePokemons.get(pokemonID.ID());
        } else {
            return 0;
        }
    }

    @Override
    public void modify(PokemonID pokemonID, int countModifier) {
        if (!countOfFabouritePokemons.containsKey(pokemonID.ID())) {
            countOfFabouritePokemons.put(pokemonID.ID(), 0);
        }
        var previousCount = countOfFabouritePokemons.get(pokemonID.ID());

        countOfFabouritePokemons.put(pokemonID.ID(), previousCount + countModifier);
    }
}
