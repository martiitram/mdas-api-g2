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
import trainers.trainer.domain.exceptions.TrainerDontExistException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AddFavouritePokemonTest {
    @Mock
    private EventPublisher publisher;
    @Mock
    private TrainerRepository trainerRepository;

    private AddFavouritePokemon useCase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this); // Initialize the mocks
        useCase = new AddFavouritePokemon(trainerRepository, publisher);
    }

    @Test
    void whenAddFavouritePokemonItShouldPublishAnEvent() throws PokemonIdOutOfRangeException, TrainerDontExistException, PokemonAlreadyExistInFavouritePokemonsException {
        // GIVEN
        doNothing().when(trainerRepository).create(any(Trainer.class));
        when(trainerRepository.exist(any())).thenReturn(true);
        when(trainerRepository.get(any())).thenReturn(new Trainer(new TrainerID("hiho")));

        // WHEN
        useCase.execute("hiho", 1);
        ArgumentCaptor<ArrayList<DomainEvent>> eventCaptor = ArgumentCaptor.forClass(ArrayList.class);
        verify(publisher, times(1)).publish(eventCaptor.capture());
        ArrayList<DomainEvent> capturedEvents = eventCaptor.getValue();

        // THEN
        assertEquals(capturedEvents.size(), 1);
        assertEquals(capturedEvents.get(0).type(), new FavouritePokemonAddedEvent("hiho", 1).type());
    }
}