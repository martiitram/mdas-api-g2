package trainers.trainer.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import trainers.trainer.domain.exceptions.PokemonAlreadyExistInFavouritePokemonsException;
import trainers.trainer.domain.exceptions.PokemonIdOutOfRangeException;
import trainers.trainer.domain.exceptions.PokemonNotExistInFavouritePokemonsException;

@ExtendWith(MockitoExtension.class)
public class TrainerEventHandlingTest {
    @Test
    void shouldCreateAnEventOnAddingAFavouritePokemon() throws PokemonIdOutOfRangeException {
        // GIVEN
        TrainerID ID = new TrainerID("1234");
        var trainer = new Trainer(ID);

        try {
            trainer.addFavouritePokemon(new PokemonID(1));
        } catch (PokemonAlreadyExistInFavouritePokemonsException e) {
            throw new RuntimeException(e);
        }

        // THEN
        //verify it has 1 event
        var caughtEvents = trainer.pullDomainEvents();
        Assertions.assertEquals(caughtEvents.size(), 1);
        Assertions.assertEquals(caughtEvents.get(0).type(), "trainer.favourite_pokemon_added");
    }

    @Test
    void shouldCreateAnEventOnDeletingAFavouritePokemon() throws PokemonIdOutOfRangeException {
        // GIVEN
        TrainerID ID = new TrainerID("1234");
        var trainer = new Trainer(ID);

        try {
            trainer.addFavouritePokemon(new PokemonID(1));
            //clear event buffer
            trainer.pullDomainEvents();
            trainer.removeFavouritePokemon(new PokemonID(1));
        } catch (PokemonNotExistInFavouritePokemonsException | PokemonAlreadyExistInFavouritePokemonsException e) {
            throw new RuntimeException(e);
        }

        // THEN
        //verify it has 1 event
        var caughtEvents = trainer.pullDomainEvents();
        Assertions.assertEquals(caughtEvents.size(), 1);
        Assertions.assertEquals(caughtEvents.get(0).type(), "trainer.favourite_pokemon_removed");
    }
    @Test
    void shouldEmptyTheEventListAfterConsumeTheEvents() throws PokemonIdOutOfRangeException {
        // GIVEN
        TrainerID ID = new TrainerID("1234");
        var trainer = new Trainer(ID);

        try {
            trainer.addFavouritePokemon(new PokemonID(1));
        } catch (PokemonAlreadyExistInFavouritePokemonsException e) {
            throw new RuntimeException(e);
        }

        //consume the events
        trainer.pullDomainEvents();
        // THEN
        //verify it has 1 event
        Assertions.assertEquals(trainer.pullDomainEvents().size(), 0);
    }

    @Test
    void shouldCreateMultipleEventsOnAddingMultipleAFavouritePokemon() throws PokemonIdOutOfRangeException {
        // GIVEN
        TrainerID ID = new TrainerID("1234");
        var trainer = new Trainer(ID);

        try {
            trainer.addFavouritePokemon(new PokemonID(1));
            trainer.addFavouritePokemon(new PokemonID(2));
        } catch (PokemonAlreadyExistInFavouritePokemonsException e) {
            throw new RuntimeException(e);
        }

        // THEN
        //verify it has 2 event
        Assertions.assertEquals(trainer.pullDomainEvents().size(), 2);
    }


}
