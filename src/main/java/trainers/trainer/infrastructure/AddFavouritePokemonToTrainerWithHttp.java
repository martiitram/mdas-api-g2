package trainers.trainer.infrastructure;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import shared.RabbitMQEventPublisher;
import trainers.trainer.application.AddFavouritePokemon;
import trainers.trainer.domain.exceptions.PokemonAlreadyExistInFavouritePokemonsException;
import trainers.trainer.domain.exceptions.PokemonIdOutOfRangeException;
import trainers.trainer.domain.exceptions.TrainerDontExistException;

@RestController
public class AddFavouritePokemonToTrainerWithHttp {
    @GetMapping("AddFavouritePokemonToTrainer/{pokemonID}")
    public ResponseEntity<String> AddFavouritePokemonToTrainer(@RequestHeader("user_id") String trainerID, @PathVariable int pokemonID) {
        var trainerRepository = new InMemoryTrainerRepository();
        var publisher = new RabbitMQEventPublisher();
        var addFavouritePokemon = new AddFavouritePokemon(trainerRepository, publisher);
        blankIdGuard(trainerID);

        try {
            addFavouritePokemon.execute(trainerID,pokemonID);
        } catch (TrainerDontExistException e) {
            return ResponseEntity.badRequest().body("TrainerDontExistException");
        } catch (PokemonAlreadyExistInFavouritePokemonsException e) {
            return ResponseEntity.badRequest().body("PokemonAlreadyExistInFavouritePokemons");
        } catch (PokemonIdOutOfRangeException e) {
            return ResponseEntity.badRequest().body("PokemonIdOutOfRangeException");
        }

        return ResponseEntity.ok().body("ok");
    }

    private static void blankIdGuard(String ID) {
        if (ID.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "blankID");
        }
    }
}


