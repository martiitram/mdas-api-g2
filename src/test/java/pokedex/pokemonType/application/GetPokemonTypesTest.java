package pokedex.pokemonType.application;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pokedex.pokemonType.domain.PokemonName;
import pokedex.pokemonType.domain.PokemonTypeCollection;
import pokedex.pokemonType.domain.PokemonTypeRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetPokemonTypesTest {

    @Mock
    private PokemonTypeRepository pokemonTypeRepository;

    @Test
    void shouldReturnCorrectPokemonTypes() {
        // GIVEN
        GetPokemonTypes getPokemonTypes = new GetPokemonTypes(pokemonTypeRepository);
        when(pokemonTypeRepository.get(any(PokemonName.class))).thenReturn(any(PokemonTypeCollection.class));

        // WHEN
        getPokemonTypes.execute("");

        // THEN
        Mockito.verify(pokemonTypeRepository).get(any(PokemonName.class));
    }
}