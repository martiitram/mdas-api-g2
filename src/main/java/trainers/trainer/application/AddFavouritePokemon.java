package trainers.trainer.application;

import shared.EventPublisher;
import trainers.trainer.domain.PokemonID;
import trainers.trainer.domain.TrainerID;
import trainers.trainer.domain.TrainerRepository;
import trainers.trainer.domain.exceptions.PokemonAlreadyExistInFavouritePokemonsException;
import trainers.trainer.domain.exceptions.PokemonIdOutOfRangeException;
import trainers.trainer.domain.exceptions.TrainerDontExistException;

public class AddFavouritePokemon {
    private final TrainerRepository trainerRepository;
    private final EventPublisher publisher;

    public AddFavouritePokemon(TrainerRepository trainerRepository, EventPublisher publisher) {
        this.trainerRepository = trainerRepository;
        this.publisher = publisher;
    }

    public void execute(String trainerId, int pokemonID) throws PokemonIdOutOfRangeException, TrainerDontExistException, PokemonAlreadyExistInFavouritePokemonsException {
        var addFavouritePokemon = new trainers.trainer.domain.AddFavouritePokemon(trainerRepository);
        publisher.publish(addFavouritePokemon.execute(new TrainerID(trainerId), new PokemonID(pokemonID)));
    }
}
