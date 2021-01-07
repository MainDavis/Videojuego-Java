package configuracion;

import control.Boton;

public interface Accionable {

	// Meto los botones
	int[] dameAccion(Boton btt_personajes[], Boton btt_objetos[], Boton btt_localizaciones[], Boton btt_acciones[],
			Jugador jugador, PersonajesAI personajesAI[], Mapa mapa);

}
