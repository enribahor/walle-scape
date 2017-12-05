package tp.pr5;

/**
 * 
 * @author Antonio Núñez Guerrero
 * 
 * PlaceInfo defines a non-modifiable interface over a Place. It is employed by the classes that need to access the 
 * information contained in the place but that cannot modify the place itself.
 *
 */
public interface PlaceInfo {

	/**
	 * 
	 * @return el nombre del lugar
	 */
	public String getName();
	
	/**
	 * 
	 * @return devuelve la descripción del lugar
	 */
	public String getDescription();
	
	/**
	 * Método que nos dice si un lugar es o no la nave espacial
	 * @return true si el lugar es una nave espacial
	 */
	public boolean isSpaceship();
	
}
