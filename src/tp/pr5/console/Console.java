package tp.pr5.console;

import java.util.List;

import tp.pr5.Direction;
import tp.pr5.NavigationObserver;
import tp.pr5.PlaceInfo;
import tp.pr5.RobotEngineObserver;
import tp.pr5.items.InventoryObserver;
import tp.pr5.items.Item;
import tp.pr5.messages.Messages;

/**
 * 
 * @author Antonio Núñez Guerrero
 *
 * The view that displays the application on the System.out. It implements all the observer interfaces in order 
 * to be notified about every event that occur when the robot is running.
 */
public class Console implements NavigationObserver, RobotEngineObserver, InventoryObserver {
	ConsoleController consoleController;

	public Console(ConsoleController consoleController){
		this.consoleController = consoleController;
		this.consoleController.addRobotEngineObserver(this);
		this.consoleController.addNavigationObserver(this);
		this.consoleController.addInventoryObserver(this);
	}	
	/**
	 * Notifica que el contenedor ha cambiado
	 * Especificado por: inventoryChange en la interfaz InventoryObserver
	 * @param inventory - Inventario nuevo
	 */
	@Override
	public void inventoryChange(List<Item> inventory){
		
	}
	
	/**
	 * Notifica que el usuario solicita una instruccion scan en el inventario
	 * Especificado por: inventoryScanned en la interfaz InventoryObserver
	 * @param collectionDescription - Información sobre el inventario
	 */
	@Override
	public void inventoryScanned(String collectionDescription){
		System.out.println(collectionDescription);
	}
	
	/**
	 * Notifica que el usuario quiere escanear un objeto del inventario
	 * Especifiaco por: itemScanned en la interfaz InventoryObserver
	 * @param description - Descripción del objeto
	 */
	@Override
	public void itemScanned(String description){
		
	}
	
	/**
	 * Notifica que un item esta gastado y habrá que eliminarlo del contenedor 
	 * Después invocará al método inventoryChange
	 * Especificado por itemEmpty en la interfaz InventoryObserver
	 * @param itemName - Nombre del objeto gastado
	 */
	@Override
	public void itemEmpty(String itemName){
		
	}
	
	/**
	 * El robot engine informa que el robot se ha apagado porque ha llegado a su nave espacial o porque se ha quedado
	 * sin fuel
	 * Especificado por engineOff en la interfaz RobotEngineObserver
	 * @param win - true si el robot se apaga porque ha llegado a la nave espacial y false si el robot se apaga porque se
	 * 				se ha quedado sin fuel
	 */
	@Override
	public void engineOff(boolean win){
		if(win){
			System.out.println(Messages.byeByeMessage());
		}else{
			System.out.println(Messages.deadMessage());
		}
	}
	
	/**
	 * El robot engine informa que la comunicación ha terminado
	 */
	@Override
	public void communicationCompleted(){
		
	}
	
	/**
	 * El robot engine informa que el fuel y/o la cantidad de material reciclado ha cambiado
	 * @param fuel - Cantidad actual de fuel
	 * @param recycledMaterial - Cantidad actual de material reciclado
	 */
	@Override
	public void robotUpdate(int fuel, int recycledMaterial){
		System.out.println(Messages.robotStateMessage(fuel, recycledMaterial));
	}
	
	/**
	 * El robot engine informa que el robot quiere decir algo
	 * @param message - El mensaje del robot
	 */
	@Override
	public void robotSays(String message){
		System.out.println(message);
	}

	/**
	 * El robot engine informa si ha ocurrido algún error
	 * @param msg - Mensaje de error
	 */
	@Override
	public void raiseError(String msg) {
		// TODO Auto-generated method stub
		System.out.println(msg);
	}

	/**
	 * El robot engine informa que ha habido una petición de la ayuda
	 * @param help - Un string con la información de ayuda
	 */
	@Override
	public void communicationHelp(String help) {
		// TODO Auto-generated method stub
		System.out.println(help);
	}

	/**
	 * Notifica que la dirección a la que estaba mirando el robot ha cambiado
	 * @param newHeading - La nueva direccion a la que está mirando el robot
	 */
	@Override
	public void headingChanged(Direction newHeading) {
		// TODO Auto-generated method stub
		System.out.println(Messages.currentHeadigTurn(newHeading));
	}

	/**
	 * Notifica que el NavigationModule ha sido inicializado
	 * @param initialPlace - El lugar donde el robot empieza la simulación
	 * @param heading - La dirección inicial a la que el robot esta mirando
	 */
	@Override
	public void initNavigationModule(PlaceInfo initialPlace, Direction heading) {
		// TODO Auto-generated method stub
		System.out.println(initialPlace.toString());
		System.out.println(Messages.currentHeadigTurn(heading));
	}

	/**
	 * Notifica que el robot ha llegado a un lugar
	 * @param heading - La dirección de movimiento del robot
	 * @param place - El lugar a donde llega el robot
	 */
	@Override
	public void robotArrivesAtPlace(Direction heading, PlaceInfo place) {
		// TODO Auto-generated method stub
		System.out.println(Messages.moveMessage(heading));
		System.out.println(place.toString());
	}

	/**
	 * Notifica una petición del usuario de una RadarInstruction
	 * @param placeDescription - Información del lugar actual
	 */
	@Override
	public void placeScanned(PlaceInfo placeDescription) {
		// TODO Auto-generated method stub
		System.out.println(placeDescription.getDescription());
	}

	/**
	 * Notifica que el lugar donde el robot permanecía ha cambiado (porque el robot ha cogido o soltado un objeto)
	 * @param placeDescription
	 */
	@Override
	public void placeHasChanged(PlaceInfo placeDescription) {
		// TODO Auto-generated method stub
		//System.out.println(placeDescription.toString());
	}
	
	/**
	 * El robot engine informa de que se ha terminado el juego
	 */
	@Override
	public void endGame() {
		// TODO Auto-generated method stub
		System.exit(0);
	}
	
}
