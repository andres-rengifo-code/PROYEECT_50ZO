# Cincuentazo
 
Juego de cartas Cincuentazo, desarrollado en Java con JavaFX, aplicando el patrón MVC.
 
## Descripción
 
Los jugadores van colocando cartas sobre la mesa sumando (o restando, según algunas cartas especiales) su valor. Pierde la partida quien se pasa de 50 al jugar una carta. Incluye modo contra la máquina, cuyas jugadas se calculan en un hilo aparte para no bloquear la interfaz.
 
## Estructura del proyecto (MVC)
 
- **MODEL**: `Carta`, `Mazo`, `Mesa`, `Jugador`, `OpcionJugada` — reglas y estado del juego.
- **CONTROLLER**: controladores de cada pantalla (inicio, juego, pantalla final), conectan la interfaz con el modelo.
- **resources**: archivos `.fxml` con el diseño de cada pantalla.
- **HILO**: hilos encargados de la toma de decisiones de la máquina.
- **EXCEPTION**: excepciones propias del juego (jugada inválida, mazo vacío, turno inválido).
- **UTILS**: utilidades de apoyo, como el cambio entre pantallas.
## Tecnologías
 
- Java 17
- JavaFX
- Maven

## Autores
 
- Andrés Felipe Rengifo Rodriguez 202519613-2724
- Álvaro Iván Ospina Capera
- Juan Pablo
