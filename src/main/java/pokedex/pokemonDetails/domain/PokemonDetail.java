package pokedex.pokemonDetails.domain;


public class PokemonDetail {
    private final PokemonID ID;
    private final PokemonName name;
    private final PokemonHeight height;
    private final PokemonWeight weight;
    private final CountFavouritePokemon count;

    public PokemonDetail(PokemonID ID,
                         PokemonName name,
                         PokemonHeight height,
                         PokemonWeight weight, CountFavouritePokemon count) {
        this.ID = ID;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.count = count;
    }

    public PokemonID ID() {
        return ID;
    }

    public PokemonName name() {
        return name;
    }

    public PokemonHeight height() {
        return height;
    }

    public PokemonWeight weight() {
        return weight;
    }

    public CountFavouritePokemon count() {
        return count;
    }
}
