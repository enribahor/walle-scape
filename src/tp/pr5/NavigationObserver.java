package tp.pr5;

/**
 * 
 * @author Antonio Núñez Guerrero
 * 
 * Interface of the observers that want to be notified about the events related to the navigation module. 
 * Classes that implement this interface will be informed when the robot changes its heading, when it arrives at a place, 
 * when the place is modified (because the robot picked or dropped an item) or when the user requests to use the radar.
 *
 */
public interface NavigationObserver {

	/**
	 * Notifica que la dirección a la que estaba mirando el robot ha cambiado
	 * @param newHeading - La nueva direccion a la que está mirando el robot
	 */
	public void headingChanged(Direction newHeading);
	
	/**
	 * Notifica que el NavigationModule ha sido inicializado
	 * @param initialPlace - El lugar donde el robot empieza la simulación
	 * @param heading - La dirección inicial a la que el robot esta mirando
	 */
	public void initNavigationModule(PlaceInfo initialPlace, Direction heading);
	
	/**
	 * Notifica que el robot ha llegado a un lugar
	 * @param heading - La dirección de movimiento del robot
	 * @param place - El lugar a donde llega el robot
	 */
	public void robotArrivesAtPlace(Direction heading, PlaceInfo place);
	
	/**
	 * Notifica una petición del usuario de una RadarInstruction
	 * @param placeDescription - Información del lugar actual
	 */
	public void placeScanned(PlaceInfo placeDescription);
	
	/**
	 * Notifica que el lugar donde el robot permanecía ha cambiado (porque el robot ha cogido o soltado un objeto)
	 * @param placeDescription
	 */
	public void placeHasChanged(PlaceInfo placeDescription);
	
}
