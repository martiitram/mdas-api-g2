package trainers.trainer.domain;

import shared.DomainEvent;

public class AddedFavouritePokemonEvent extends DomainEvent {
    String trainerID;
    int pokemonID;
    AddedFavouritePokemonEvent(String trainerID, int pokemonID){
        this.trainerID = trainerID;
        this.pokemonID = pokemonID;
    }

    @Override
    public String type() {
        return "trainer.added_favourite_pokemon";
    }
}
