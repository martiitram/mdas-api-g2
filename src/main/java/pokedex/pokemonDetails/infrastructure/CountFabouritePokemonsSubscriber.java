package pokedex.pokemonDetails.infrastructure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import pokedex.pokemonDetails.application.FavouritePokemonCount;
import trainers.trainer.domain.FavouritePokemonAddedEvent;
import trainers.trainer.domain.FavouritePokemonRemovedEvent;


@Component
public class CountFabouritePokemonsSubscriber {
    @RabbitListener(queues = "trainer.favourite_pokemon_added")
    public void handleFavouritePokemonAddedMessage(String message) {
        var useCase = new FavouritePokemonCount();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            FavouritePokemonAddedEvent event = objectMapper.readValue(message, FavouritePokemonAddedEvent.class);
            useCase.modify(event.pokemonID, 1);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    @RabbitListener(queues = "trainer.favourite_pokemon_removed")
    public void handleFavouritePokemonRemovedMessage(String message) {
        var useCase = new FavouritePokemonCount();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            FavouritePokemonRemovedEvent event = objectMapper.readValue(message, FavouritePokemonRemovedEvent.class);
            useCase.modify(event.pokemonID, -1);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
