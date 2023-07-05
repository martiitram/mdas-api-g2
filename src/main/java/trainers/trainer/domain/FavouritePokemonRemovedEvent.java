package trainers.trainer.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import shared.DomainEvent;


public class FavouritePokemonRemovedEvent extends DomainEvent {
    public String trainerID;
    public int pokemonID;
    public FavouritePokemonRemovedEvent(String trainerID, int pokemonID){
        super(trainerID);
        this.trainerID = trainerID;
        this.pokemonID = pokemonID;
    }

    @JsonCreator
    public FavouritePokemonRemovedEvent(@JsonProperty("aggregateId") String aggregateId,
                                        @JsonProperty("occurredOn") String occurredOn,
                                        @JsonProperty("trainerID") String trainerID,
                                        @JsonProperty("pokemonID") int pokemonID) {
        super(aggregateId, occurredOn);
        this.trainerID = trainerID;
        this.pokemonID = pokemonID;
    }

    @Override
    public String type() {
        return "trainer.favourite_pokemon_removed";
    }
}
