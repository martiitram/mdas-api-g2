package trainers.trainer.domain;

import shared.DomainEvent;

public class AddedFavouritePokemonEvent extends DomainEvent {
    String trainerID;
    int pokemonID;
    AddedFavouritePokemonEvent(String trainerID, int pokemonID){
        super(trainerID);
        this.trainerID = trainerID;
        this.pokemonID = pokemonID;
    }

    @Override
    public String type() {
        return "trainer.favourite_pokemon_added";
    }
}
