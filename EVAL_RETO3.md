## Hace todos los puntos pedidos (40%)

#### Permite obtener los detalles de un pokemon vía endpoint

OK

#### Si no existe el pokemon, ¿se lanza una excepción de dominio?

OK

#### Si la api da timeout, ¿se lanza una excepción de dominio?

OK

#### Si se lanza una excepción desde el dominio, ¿se traduce en infraestructura a un código HTTP?

OK, aunque se mapean a códigos 500. Hay códigos HTTPs que se adaptan mejor al error en cuestión

#### Tests de aceptación

OK

#### Tests de integración

KO, no tiene tests de integración con los repositorios in memory

**Puntuación:  32/40**

## Se aplican conceptos explicados (40%)

#### Separación correcta de capas (application, domain, infrastructure + BC/module/layer)

OK

#### Aggregates + VOs

OK, aunque el nombre del agregado sería más claro si le llamamos `Pokemon` en lugar de `PokemonDetails`

#### No se trabajan con tipos primitivos en dominio

OK

#### Hay use cases en aplicación reutilizables

OK

#### Se aplica el patrón repositorio

OK

#### Se utilizan object mothers

KO

**Puntuación:  38/40**

## Facilidad setup + README (20%)

#### El README contiene al menos los apartados ""cómo ejecutar la aplicación"", ""cómo usar la aplicación""

OK

#### Es sencillo seguir el apartado ""cómo ejecutar la aplicación""

OK

**Puntuación:  20/20**

## Observaciones

- Sigue habiendo el mismo problema con las URLs incluso para el endpoint nuevo

**Puntuación:  -10**

**PUNTUACIÓN FINAL: 80/100**
