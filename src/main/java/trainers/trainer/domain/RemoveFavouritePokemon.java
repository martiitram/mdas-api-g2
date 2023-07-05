package trainers.trainer.domain;

import shared.DomainEvent;
import trainers.trainer.domain.exceptions.PokemonNotExistInFavouritePokemonsException;
import trainers.trainer.domain.exceptions.TrainerDontExistException;

import java.util.ArrayList;

public class RemoveFavouritePokemon {
    private final TrainerRepository trainerRepository;

    public RemoveFavouritePokemon(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    public ArrayList<DomainEvent> execute(TrainerID ID, PokemonID pokemonID) throws TrainerDontExistException, PokemonNotExistInFavouritePokemonsException {
        trainerExistGuard(ID);
        Trainer trainer = trainerRepository.get(ID);
        trainer.removeFavouritePokemon(pokemonID);
        trainerRepository.update(trainer);
        return trainer.pullDomainEvents();
    }

    private void trainerExistGuard(TrainerID id) throws TrainerDontExistException {
        if (!trainerRepository.exist(id)) {
            throw new TrainerDontExistException();
        }
    }
}
