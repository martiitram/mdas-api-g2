## Hace todos los puntos pedidos (40%)

#### El nombre del repositorio es el correcto (mdas-api-${nombre}_${apellido})

KO, aunque no lo penalizaré porque lo consultaste y si te hubiera pedido cambiarlo, hubieras tardado más ;-)

#### Permite obtener los detalles de un pokemon vía endpoint (datos + número de veces que se ha marcado como favorito)

- Según el código sí, pero no consigo arrancar la aplicación debido a este error:

```
Caused by: com.rabbitmq.client.ShutdownSignalException: channel error; protocol method: #method<channel.close>(reply-code=404, reply-text=NOT_FOUND - no queue 'trainer.favourite_pokemon_added' in vhost '/', class-id=50, method-id=10)
```

#### Actualiza el contador de favoritos vía eventos

OK

#### ¿Se controlan las excepciones de dominio? Y si se lanza una excepción desde el dominio, ¿se traduce en infraestructura a un código HTTP?

OK

#### Tests unitarios

OK

#### Tests de aceptación

KO

#### Tests de integración

KO

**Puntuación: 23/40**

## Se aplican conceptos explicados (50%)

#### Separación correcta de capas (application, domain, infrastructure + BC/module/layer)

- La responsabilidad de cuanto aumenta o cuanto se reduce el contador no es del subscriber (infra). Lo correcto sería
  que hubiera dos subscribers (increment & decrease) y que el agregado tuviera la lógica de incrementar o reducir este
  contador.

#### Aggregates + VOs

- El agregado es dummy, no es responsable de su propio contador... únicamente tiene atributos, sin lógica.

#### No se trabajan con tipos primitivos en dominio

OK

#### Hay use cases en aplicación reutilizables

OK, aunque el nombre `FavouritePokemonCount` no es demasiado descriptivo

#### Se aplica el patrón repositorio

- Solo se puede tener un repositorio por módulo, y tienes dos: `FavouriteCountRepository` y `PokemonDetailRepository`.
  En este caso, lo correcto hubiese sido que hubiese una única interfaz (`PokemonDetailRepository`) que actúe de "proxy"
  entre el dominio y la infra, dándole igual al dominio cómo se construya el agregado. Si en infra hay que buscar parte
  de la información en la API y otra parte en un in memory, que lo haga.

#### Se usan subscribers

OK, aunque el subscriber tiene una doble
responsabilidad: `handleFavouritePokemonRemovedMessage` & `handleFavouritePokemonAddedMessage`

#### Se lanzan eventos de dominio

OK

#### Se utilizan object mothers

KO

**Puntuación: 25/50**

## Facilidad setup + README (10%)

#### El README contiene al menos los apartados ""cómo ejecutar la aplicación"", ""cómo usar la aplicación""

OK

#### Es sencillo seguir el apartado ""cómo ejecutar la aplicación""

- El paso `Create the rabitMQ instance on docker` falla ya que el fichero docker-compose esta dentro de la carpeta
  docker.
- El comando `./mvnw clean test` falla:

```
org.springframework.amqp.rabbit.listener.BlockingQueueConsumer$DeclarationException: Failed to declare queue(s):[trainer.favourite_pokemon_added]
...
[ERROR] Errors: 
[ERROR]   GetPokemonDetailsWithHttpWebTest.shouldReturnPokemonDetails_whenPokemonExists » IllegalState
[ERROR]   GetPokemonTypeWithHttpWebTest.shouldReturnPokemonTypes_whenPokemonExists » IllegalState
[ERROR]   TrainerWebTest.shouldCreateTrainer_AndAddFavouritePoken_AndRemoveFavouritePokemon » IllegalState
[ERROR]   TrainerWebTest.shouldReturnTrainerDontExistException_whenUserIdDoesNotExist » IllegalState
[INFO] 
[ERROR] Tests run: 42, Failures: 0, Errors: 4, Skipped: 0
```

- El paso `Running a http server` tampoco funciona. No es `./mvn clean package spring-boot:run`, debería
  ser `./mvnw clean package spring-boot:run`

**Puntuación: 5/10**

## Observaciones

- Bien pensado el añadir dos eventos, uno para añadir favorito y otro para quitarlo ;-)

**PUNTUACIÓN FINAL: 53/100**
