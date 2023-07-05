package trainers.trainer.infrastructure;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import shared.RabbitMQEventPublisher;
import trainers.trainer.application.RemoveFavouritePokemon;
import trainers.trainer.domain.exceptions.PokemonIdOutOfRangeException;
import trainers.trainer.domain.exceptions.PokemonNotExistInFavouritePokemonsException;
import trainers.trainer.domain.exceptions.TrainerDontExistException;

@RestController
public class RemoveFavouritePokemonToTrainerWithHttp {
    @GetMapping("RemoveFavouritePokemonToTrainer/{pokemonID}")
    public static void RemoveFavouritePokemonToTrainer(@RequestHeader("user_id") String trainerID, @PathVariable int pokemonID) {
        var trainerRepository = new InMemoryTrainerRepository();
        var publisher = new RabbitMQEventPublisher();
        var removeFavouritePokemon = new RemoveFavouritePokemon(trainerRepository, publisher);
        blankIdGuard(trainerID);

        try {
            removeFavouritePokemon.execute(trainerID,pokemonID);
        } catch (TrainerDontExistException e) {
            //TODO check HTTP STATUS
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"TrainerDontExistException");
        } catch (PokemonIdOutOfRangeException e) {
            //TODO check HTTP STATUS
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"PokemonIdOutOfRangeException");
        } catch (PokemonNotExistInFavouritePokemonsException e) {
            //TODO check HTTP STATUS
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"PokemonNotExistInFavouritePokemonsException");
        }
    }

    private static void blankIdGuard(String ID) {
        if (ID.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "blankID");
        }
    }
}


