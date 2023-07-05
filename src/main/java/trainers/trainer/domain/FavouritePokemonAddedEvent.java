package trainers.trainer.domain;

import shared.DomainEvent;

public class FavouritePokemonAddedEvent extends DomainEvent {
    public final String trainerID;
    public final int pokemonID;
    public FavouritePokemonAddedEvent(String trainerID, int pokemonID){
        super(trainerID);
        this.trainerID = trainerID;
        this.pokemonID = pokemonID;
    }

    @Override
    public String type() {
        return "trainer.favourite_pokemon_added";
    }
}
