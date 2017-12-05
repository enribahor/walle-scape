package tp.pr5;

import tp.pr5.items.InventoryObserver;

/**
 * 
 * @author Antonio
 *
 */
public abstract class Controller {

	protected RobotEngine robotEngine;
	
	public Controller(RobotEngine robotEngine){
		this.robotEngine = robotEngine;
	}
	
	/**
	 * Método abstracto que se encarga de ejecutar al aplicación de tres posibles maneras.
	 * 1- Mediante interfaz gráfica
	 * 2- Mediante consola
	 * 3- De ambas formas   
	 */
	public abstract void startController();
	
	/**
	 * Método que añade un EngineObserver al robot engine
	 * @param engineObserver - observador que se fija al robot engine
	 */
	public void addRobotEngineObserver(RobotEngineObserver engineObserver){
		robotEngine.addEngineObserver(engineObserver);
	}
	
	/**
	 * Método que añade un NavigationObserver al robot engine
	 * @param navigationObserver - observador que se fija al robot engine
	 */
	public void addNavigationObserver(NavigationObserver navigationObserver){
		robotEngine.addNavigationObserver(navigationObserver);
	}
	
	/**
	 * Método que se añade un InventoryObserver al robot engine
	 * @param containerObserver - observador que se fija al robot engine
	 */
	public void addInventoryObserver(InventoryObserver containerObserver){
		robotEngine.addItemContainerObserver(containerObserver);
	}
}
