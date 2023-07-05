## Prerequisites
- Java SDK 17

## Stack used 
- Java 17
- SpringBoot 3

## How to use
At the root of the project you have two maven wrapper executables, that will allow you to compile, execute and running tests
without the need of installing maven.

You have the linux executable(mvnw) and the windows executable(mvnw.cmd). Just check you have execute permission set on these files to be able to run it.
In the following sections you will see example commands made with the linux maven wrapper.

## Unit Testing
```
./mvnw clean test
```

## all Testing
```
./mvnw clean verify
```

## How to run
There are to ways to execute this project, via terminal commands or a http request.

### Commands via terminal
Follow this steps:
  - Open a terminal and change the directory to the root of this project.
  - Execute the following command: 
```
./mvn clean compile -Dexec.mainClass=pokedex.pokemonType.infrastructure.GetPokemonTypeWithConsole exec:java -Dexec.args="pikachu"
```

The pokemon type should appear on console.

### Running a http server
Spring Boot provides an internal tomcat server that we can use to easily run a http server.

Follow this steps:
- Open a terminal and change the directory to the root of the project.
- Execute the following command:
``` 
./mvn clean package spring-boot:run
```

- Open a web browser and type the following URL: http://localhost:8080/getPokemonTypesByName/(the name of the pokemon that you want to search).
For example: http://localhost:8080/getPokemonTypesByName/pikachu
  
The pokemon type should appear in the browser.

## Database
This application is just a proof of software design concepts, so isn't needed a real database. We have use a simple java Map to simulate InMemoryDatabase

## Endpoints
The tomcat server by default will start at 8080 port, so you could check all the request with http://localhost:8080 address
You can use any http client, but just for the convenience of testing header injection, we will use wget in the next examples.

### /getPokemonTypesByName/{pokemonName}
```
wget -q -O- localhost:8080/getPokemonTypesByName/pikachu
["electric"]
```
### /CreateTrainer/{ID}
Create a trainer in the given ID
```
wget -O- localhost:8080/CreateTrainer/99
```

### /AddFavouritePokemonToTrainer/{pokemonID}
Note: you need to inject an existing user_id custom header, to be able to add a FavoritePokemon from a Trainer
```
wget -q -S -O - --header='user_id:99' localhost:8080/AddFavouritePokemonToTrainer/1
```

### /RemoveFavouritePokemonToTrainer/{pokemonID}
Note: you need to inject an existing user_id custom header, to be able to remove a FavoritePokemon from a Trainer
```
wget -q -S -O - --header='user_id:99' localhost:8080/RemoveFavouritePokemonToTrainer/1
```

### /getPokemonDetailsByID/{pokemonID}
```
wget -O- localhost:8080/getPokemonDetailsByID/1
```



docker-compose up -d 