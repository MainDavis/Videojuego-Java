package configuracion;

public class CrearPersonajes extends Leer {

	public CrearPersonajes(Personajes personaje[]) {
		// Pongo los datos necesarios a los personajes (Nombre, localización)
		// System.out.println(getNumPersonajes());
		for (int i = 0; i < getNumPersonajes(); i++) {
			System.out.println("Hola");
			personaje[i].setNombre(getPersonajes().get(i));

			// personaje[i].setLocalizacion(getPersonajesLocIni().get(i));
		}
		// Añado el objeto inicial que pueda tener

		for (String element : getObj()) {
			String nombrePersonaje = element.split("\\(")[0];
			element = element.split("\\(")[1].substring(0, element.split("\\(")[1].length() - 1);

			if (getPersonajes().contains(nombrePersonaje)) {
				personaje[getPersonajes().indexOf(nombrePersonaje)].setObjeto(getObj().indexOf(element));
			}
		}

		for (Personajes personajeElement : personaje) {
			System.out.println(personajeElement.getNombre());
		}

	}
}
