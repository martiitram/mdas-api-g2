package trainers.trainer.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import shared.DomainEvent;
import shared.EventPublisher;
import trainers.trainer.domain.*;
import trainers.trainer.domain.exceptions.PokemonAlreadyExistInFavouritePokemonsException;
import trainers.trainer.domain.exceptions.PokemonIdOutOfRangeException;
import trainers.trainer.domain.exceptions.PokemonNotExistInFavouritePokemonsException;
import trainers.trainer.domain.exceptions.TrainerDontExistException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RemoveFavouritePokemonTest {
    @Mock
    private EventPublisher publisher;
    @Mock
    private TrainerRepository trainerRepository;

    private RemoveFavouritePokemon useCase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this); // Initialize the mocks
        useCase = new RemoveFavouritePokemon(trainerRepository, publisher);
    }

    @Test
    void whenRemoveFavouritePokemonItShouldPublishAnEvent() throws PokemonIdOutOfRangeException, TrainerDontExistException, PokemonNotExistInFavouritePokemonsException, PokemonAlreadyExistInFavouritePokemonsException {
        // GIVEN
        doNothing().when(trainerRepository).create(any(Trainer.class));
        when(trainerRepository.exist(any())).thenReturn(true);
        var trainer = new Trainer(new TrainerID("hiho"));
        trainer.addFavouritePokemon(new PokemonID(1));
        trainer.pullDomainEvents();
        when(trainerRepository.get(any())).thenReturn(trainer);

        // WHEN
        useCase.execute("hiho", 1);
        ArgumentCaptor<ArrayList<DomainEvent>> eventCaptor = ArgumentCaptor.forClass(ArrayList.class);
        verify(publisher, times(1)).publish(eventCaptor.capture());
        ArrayList<DomainEvent> capturedEvents = eventCaptor.getValue();

        // THEN
        assertEquals(capturedEvents.size(), 1);
        assertEquals(capturedEvents.get(0).type(), new FavouritePokemonRemovedEvent("hiho", 1).type());
    }
}