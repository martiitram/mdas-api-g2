package trainers.trainer.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import shared.DomainEvent;


public class FavouritePokemonAddedEvent extends DomainEvent {
    public final String trainerID;
    public final int pokemonID;
    public FavouritePokemonAddedEvent(String trainerID, int pokemonID){
        super(trainerID);
        this.trainerID = trainerID;
        this.pokemonID = pokemonID;
    }

    @JsonCreator
    public FavouritePokemonAddedEvent(@JsonProperty("aggregateId") String aggregateId,
                                      @JsonProperty("occurredOn") String occurredOn,
                                      @JsonProperty("trainerID") String trainerID,
                                      @JsonProperty("pokemonID") int pokemonID
                                      ) {
        super(aggregateId,occurredOn);
        this.trainerID = trainerID;
        this.pokemonID = pokemonID;
    }

    @Override
    public String type() {
        return "trainer.favourite_pokemon_added";
    }
}
