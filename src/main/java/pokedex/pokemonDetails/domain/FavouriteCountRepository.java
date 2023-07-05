package pokedex.pokemonDetails.domain;

public interface FavouriteCountRepository {
    int getById(PokemonID pokemonID);

    void modify(PokemonID pokemonID, int countModifier);
}
