🃏 Blackjack API - Spring Boot WebFlux
Esta es una API reactiva desarrollada con Spring WebFlux para gestionar un juego de Blackjack. Utiliza dos bases de datos distintas: MongoDB (para las partidas y cartas) y MySQL (para la información de los jugadores).

🛠 Tecnologías
	•	Java 17
	•	Spring Boot
	•	Spring WebFlux
	•	MongoDB Reactive
	•	Spring Data JPA (MySQL)
	•	JUnit 5
	•	Mockito
	•	Swagger/OpenAPI

📂 Estructura del Proyecto
bash
CopiarEditar
├── controller         # Controladores REST
├── service            # Lógica del negocio
├── model              # Modelos de datos (Mongo y SQL)
├── repository         # Repositorios (Mongo y MySQL)
├── exception          # Gestión de errores global
├── enums              # Enums para las cartas y tipos de jugador
├── resources
│   └── application.properties
└── test               # Tests unitarios con JUnit y Mockito

🎯 Endpoints Disponibles
🎲 Partidas (/game)
Método
Endpoint
Descripción
POST
/game/new
Crea una nueva partida
GET
/game/{id}
Obtiene los detalles de una partida
POST
/game/{id}/hit
Realiza una jugada de tipo "hit"
POST
/game/{id}/stand
Realiza una jugada de tipo "stand"
DELETE
/game/{id}/delete
Elimina una partida
🧑‍💼 Jugadores (/player)
Método
Endpoint
Descripción
POST
/player/new
Crea un nuevo jugador
PUT
/player/{id}
Cambia el nombre del jugador
GET
/player/ranking
Obtiene el ranking de los 10 mejores

⚙️ Configuración de Bases de Datos
	•	MongoDB se usa de forma reactiva para gestionar cartas y partidas.
	•	MySQL almacena información persistente de los jugadores y su rendimiento.
Ambas se configuran en application.properties.

🧪 Testing
Se incluyen pruebas unitarias para:
	•	PlayerService
	•	PlayerController
Usando JUnit 5 y Mockito.

🧾 Documentación de la API
Documentación generada automáticamente con Swagger:
bash
CopiarEditar
http://localhost:8080/swagger-ui.html

🚀 Cómo ejecutar
	1	Clona el repositorio
	2	Configura tu application.properties
	3	Asegúrate de tener MongoDB y MySQL corriendo
	4	Ejecuta la clase S05T01N01BlackJackApplication.java
	5	Accede a los endpoints desde Postman, Swagger o tu frontend

