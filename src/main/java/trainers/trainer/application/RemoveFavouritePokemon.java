package trainers.trainer.application;

import shared.EventPublisher;
import trainers.trainer.domain.PokemonID;
import trainers.trainer.domain.TrainerID;
import trainers.trainer.domain.TrainerRepository;
import trainers.trainer.domain.exceptions.PokemonIdOutOfRangeException;
import trainers.trainer.domain.exceptions.PokemonNotExistInFavouritePokemonsException;
import trainers.trainer.domain.exceptions.TrainerDontExistException;

public class RemoveFavouritePokemon {
    public RemoveFavouritePokemon(TrainerRepository trainerRepository, EventPublisher publisher) {
        this.trainerRepository = trainerRepository;
        this.publisher = publisher;
    }

    private final TrainerRepository trainerRepository;
    private final EventPublisher publisher;

    public void execute(String trainerId, int pokemonID) throws PokemonIdOutOfRangeException, TrainerDontExistException, PokemonNotExistInFavouritePokemonsException {
        var removeFavouritePokemon = new trainers.trainer.domain.RemoveFavouritePokemon(trainerRepository);

        publisher.publish(removeFavouritePokemon.execute(new TrainerID(trainerId), new PokemonID(pokemonID)));
    }
}
