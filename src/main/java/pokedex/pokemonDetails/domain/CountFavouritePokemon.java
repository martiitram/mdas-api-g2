package pokedex.pokemonDetails.domain;


public class CountFavouritePokemon {
    private final int count;

    public CountFavouritePokemon(int count) {
        this.count = count;
    }

    public int count() {
        return count;
    }
}
