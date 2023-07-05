package pokedex.pokemonDetails.infrasctructure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pokedex.pokemonDetails.application.FavouritePokemonCount;
import pokedex.pokemonDetails.domain.exceptions.PokemonIdOutOfRangeException;
import pokedex.pokemonDetails.infrastructure.CountFabouritePokemonsSubscriber;
import trainers.trainer.domain.FavouritePokemonAddedEvent;
import trainers.trainer.domain.FavouritePokemonRemovedEvent;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class CountFabouritePokemonsSubscriberTest {
    private CountFabouritePokemonsSubscriber subscriber;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private FavouritePokemonCount favouritePokemonCount;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        subscriber = new CountFabouritePokemonsSubscriber(favouritePokemonCount);
    }

    @Test
    void handleFavouritePokemonAddedMessage_shouldCallUseCaseModifyWithPositiveValue() throws JsonProcessingException, PokemonIdOutOfRangeException {
        // Arrange
        FavouritePokemonAddedEvent event = new FavouritePokemonAddedEvent("hiho", 123);
        var objectMapper = new ObjectMapper();
        var message = objectMapper.writeValueAsString(event);
        // Act

        subscriber.handleFavouritePokemonAddedMessage(message);

        // Assert
        verify(favouritePokemonCount, times(1)).modify(eq(123), eq(1));
    }

    @Test
    void handleFavouritePokemonRemovedMessage_shouldCallUseCaseModifyWithNegativeValue() throws JsonProcessingException, PokemonIdOutOfRangeException {
        // Arrange
        FavouritePokemonRemovedEvent event = new FavouritePokemonRemovedEvent("hiho", 123);
        var objectMapper = new ObjectMapper();
        var message = objectMapper.writeValueAsString(event);

        // Act
        subscriber.handleFavouritePokemonRemovedMessage(message);

        // Assert
        verify(favouritePokemonCount, times(1)).modify(eq(123), eq(-1));
    }
}
