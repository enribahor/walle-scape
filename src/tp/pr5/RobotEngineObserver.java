package tp.pr5;

/**
 * 
 * @author Antonio Núñez Guerrero
 *
 * Interface of the observers that want to be notified about the events ocurred in the robot engine. The robot engine 
 * will notify the changes in the robot (fuel and recycled material), will inform about communication problems, errors 
 * and when the robot wants to say something. Finally, the engine will also notify when the user requests help and when 
 * the robot shuts down (because the robot run out of fuel or when it arrived at the spaceship)
 * 
 */
public interface RobotEngineObserver {

	/**
	 * El robot engine informa si ha ocurrido algún error
	 * @param msg - Mensaje de error
	 */
	public void raiseError(String msg);
	
	/**
	 * El robot engine informa que ha habido una petición de la ayuda
	 * @param help - Un string con la información de ayuda
	 */
	public void communicationHelp(String help);
	
	/**
	 * El robot engine informa que el robot se ha apagado porque ha llegado a su nave espacial o porque se ha quedado
	 * sin fuel
	 * @param atShip - true si el robot se apaga porque ha llegado a la nave espacial y false si el robot se apaga porque se
	 * 				   se ha quedado sin fuel
	 */
	public void engineOff(boolean atShip);
	
	/**
	 * El robot engine informa que la comunicación ha terminado
	 */
	public void communicationCompleted();
	
	/**
	 * El robot engine informa que el fuel y/o la cantidad de material reciclado ha cambiado
	 * @param fuel - Cantidad actual de fuel
	 * @param recycledMaterial - Cantidad actual de material reciclado
	 */
	public void robotUpdate(int fuel, int recycledMaterial);
	
	/**
	 * El robot engine informa que el robot quiere decir algo
	 * @param message - El mensaje del robot
	 */
	public void robotSays(String message);
	
	/**
	 * El robot engine informa de que se ha terminado el juego
	 */
	public void endGame();
}
