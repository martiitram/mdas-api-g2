package trainers.trainer.domain;

import shared.DomainEvent;
import trainers.trainer.domain.exceptions.PokemonAlreadyExistInFavouritePokemonsException;
import trainers.trainer.domain.exceptions.PokemonNotExistInFavouritePokemonsException;

import java.util.ArrayList;

public class Trainer {
    private final TrainerID ID;
    private final FavouritePokemons favoritePokemons;

    private ArrayList<DomainEvent> events;

    public Trainer(TrainerID ID) {
        events = new ArrayList<>();
        this.ID = ID;
        this.favoritePokemons = new FavouritePokemons();
    }

    public TrainerID ID() {
        return ID;
    }

    public void addFavouritePokemon(PokemonID pokemonID) throws PokemonAlreadyExistInFavouritePokemonsException {
        favoritePokemons.addFavouritePokemon(pokemonID);
        events.add(new FavouritePokemonAddedEvent(ID.ID(), pokemonID.ID()));
    }

    public void removeFavouritePokemon(PokemonID pokemonID) throws PokemonNotExistInFavouritePokemonsException {
        favoritePokemons.removeFavouritePokemon(pokemonID);
        events.add(new FavouritePokemonRemovedEvent(ID.ID(), pokemonID.ID()));
    }

    public boolean hasFavouritePokemon(PokemonID pokemonID) {
        return favoritePokemons.pokemonExist(pokemonID);
    }

    ArrayList<DomainEvent> pullDomainEvents() {
        var recordedEvents = events;
        events = new ArrayList<>();
        return recordedEvents;
    }
}
