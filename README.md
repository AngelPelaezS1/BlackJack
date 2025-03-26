# 🃏 Blackjack con Spring Boot, MySQL y MongoDB

## 📌 Descripción

Este repositorio contiene un proyecto Spring Boot que implementa una API RESTful para la gestión de un juego de Blackjack. La aplicación está estructurada por capas y utiliza dos bases de datos:

- **MongoDB**: para guardar las partidas y las cartas (modelo no relacional, reactivo).
- **MySQL**: para guardar la información de los jugadores (modelo relacional, persistente).

La app se basa en el patrón MVC (Modelo-Vista-Controlador) y está construida de forma completamente **reactiva con Spring WebFlux**.

---

## 📂 Contenido

### 🏆 Nivel 1 - Aplicación Reactiva con Spring WebFlux

- Desarrollo de una API REST para gestionar partidas y jugadores de Blackjack.
- Configuración de una base de datos **MongoDB reactiva** para las partidas.
- Configuración de una base de datos **MySQL relacional** para los jugadores.
- Implementación de controladores, servicios y repositorios reactivos.
- Manejo global de excepciones con `GlobalExceptionHandler`.
- Generación automática de documentación Swagger.

---

## 📂 Estructura del proyecto

```bash
├── controller
│   ├── GameController.java
│   └── PlayerController.java
├── service
│   ├── GameService.java
│   └── PlayerService.java
├── model
│   ├── mongo
│   │   ├── Game.java
│   │   └── Card.java
│   └── sql
│       └── Player.java
├── repository
│   ├── GameRepository.java
│   └── PlayerRepository.java
├── exception
│   ├── GameNotFoundException.java
│   ├── PlayerNotFoundException.java
│   ├── RankingEmptyException.java
│   └── GlobalExceptionHandler.java
├── enums
│   ├── RankCard.java
│   ├── SuitCard.java
│   └── PlayerType.java
└── test
    ├── PlayerServiceTest.java
    ├── PlayerControllerTest.java
    └── S05T01N01BlackJackApplicationTests.java
```

---

## 🚀 Endpoints disponibles

### 🎲 Partidas (`/game`)

| Método HTTP | Endpoint               | Descripción                            |
|-------------|------------------------|----------------------------------------|
| POST        | `/game/new`            | Crea una nueva partida                 |
| GET         | `/game/{id}`           | Obtiene detalles de una partida        |
| POST        | `/game/{id}/hit`       | Realiza una jugada de tipo "hit"       |
| POST        | `/game/{id}/stand`     | Realiza una jugada de tipo "stand"     |
| DELETE      | `/game/{id}/delete`    | Elimina una partida                    |

### 🧑‍💼 Jugadores (`/player`)

| Método HTTP | Endpoint               | Descripción                            |
|-------------|------------------------|----------------------------------------|
| POST        | `/player/new`          | Crea un nuevo jugador                  |
| PUT         | `/player/{id}`         | Cambia el nombre del jugador           |
| GET         | `/player/ranking`      | Obtiene el ranking de los 10 mejores   |

---

## ⚙️ Configuración de Bases de Datos

- MongoDB se usa de forma reactiva para gestionar cartas y partidas.  
- MySQL almacena información persistente de los jugadores y su rendimiento.  
- Ambas se configuran en `application.properties`.

---

## 🧪 Testing

Se incluyen pruebas unitarias para:

- `PlayerService`  
- `PlayerController`

Usando **JUnit 5** y **Mockito**.

---

## 🧾 Documentación de la API

Documentación generada automáticamente con Swagger:

```
http://localhost:8080/swagger-ui/index.html
```

---

## 🚀 Cómo ejecutar

1. Clona el repositorio  
2. Configura tu `application.properties`  
3. Asegúrate de tener **MongoDB** y **MySQL** corriendo  
4. Ejecuta la clase `S05T01N01BlackJackApplication.java`  
5. Accede a los endpoints desde **Postman**, **Swagger** o tu **frontend**

---

## 📌 Pendiente para siguientes niveles

- Reglas completas del juego (dealer, puntuación, blackjack natural…)  
- Más tests unitarios y tests de integración  
- Seguridad, validaciones, DTOs  
- Frontend opcional con React
