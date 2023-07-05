package pokedex.pokemonDetails.application;

import pokedex.pokemonDetails.domain.FavouriteCountRepository;
import pokedex.pokemonDetails.domain.PokemonID;
import pokedex.pokemonDetails.domain.exceptions.PokemonIdOutOfRangeException;

public class FavouritePokemonCount {
    private final FavouriteCountRepository favouriteCountRepository;
    public FavouritePokemonCount(FavouriteCountRepository favouriteCountRepository){

        this.favouriteCountRepository = favouriteCountRepository;
    }

    public void modify(int pokemonId, int countModifier) throws PokemonIdOutOfRangeException {
        favouriteCountRepository.modify(new PokemonID(pokemonId),countModifier);
    }

}

