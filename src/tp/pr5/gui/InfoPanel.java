package tp.pr5.gui;

import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import tp.pr5.Direction;
import tp.pr5.NavigationObserver;
import tp.pr5.PlaceInfo;
import tp.pr5.RobotEngineObserver;
import tp.pr5.items.InventoryObserver;
import tp.pr5.items.Item;
import tp.pr5.messages.Messages;

/**
 * 
 * @author Antonio
 *
 * Panel at the bottom of the window that displays messages about the events that occur during the simulation. 
 * This panel implements all the observer interfaces in order to be notified about all event ocurred
 */
@SuppressWarnings("serial")
public class InfoPanel extends JPanel implements RobotEngineObserver, NavigationObserver, InventoryObserver{
	public JLabel JLabelInfo;
	
	public InfoPanel(){
		this.JLabelInfo = new JLabel();
		this.add(this.JLabelInfo);
	}

	/**
	 * Notifica que el contenedor ha cambiado
	 * @param inventory - Nuevo inventario
	 */
	@Override
	public void inventoryChange(List<Item> inventory) {
		// TODO Auto-generated method stub
	}

	/**
	 * Notifica que el usuario solicita una instrucción scan en el inventario
	 * @param inventoryDescription - Información sobre el inventario
	 */
	@Override
	public void inventoryScanned(String inventoryDescription) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Notifica que el usuario desea escanear un objeto del inventario
	 * @param description - Descrición del objeto
	 */
	@Override
	public void itemScanned(String description) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Notifica que un objeto se ha gastado y que hay que eliminarlo del contenedor.
	 * Después invocará al método inventoryChange
	 * @param itemName - Nombre del objeto gastado
	 */
	@Override
	public void itemEmpty(String itemName) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Notifica que la dirección a la que estaba mirando el robot ha cambiado
	 * @param newHeading - La nueva direccion a la que está mirando el robot
	 */
	@Override
	public void headingChanged(Direction newHeading) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Notifica que el NavigationModule ha sido inicializado
	 * @param initialPlace - El lugar donde el robot empieza la simulación
	 * @param heading - La dirección inicial a la que el robot esta mirando
	 */
	@Override
	public void initNavigationModule(PlaceInfo initialPlace, Direction heading) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Notifica que el robot ha llegado a un lugar
	 * @param heading - La dirección de movimiento del robot
	 * @param place - El lugar a donde llega el robot
	 */
	@Override
	public void robotArrivesAtPlace(Direction heading, PlaceInfo place) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Notifica una petición del usuario de una RadarInstruction
	 * @param placeDescription - Información del lugar actual
	 */
	@Override
	public void placeScanned(PlaceInfo placeDescription) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Notifica que el lugar donde el robot permanecía ha cambiado (porque el robot ha cogido o soltado un objeto)
	 * @param placeDescription
	 */
	@Override
	public void placeHasChanged(PlaceInfo placeDescription) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * El robot engine informa si ha ocurrido algún error
	 * @param msg - Mensaje de error
	 */
	@Override
	public void raiseError(String msg) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * El robot engine informa que ha habido una petición de la ayuda
	 * @param help - Un string con la información de ayuda
	 */
	@Override
	public void communicationHelp(String help) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * El robot engine informa que el robot se ha apagado porque ha llegado a su nave espacial o porque se ha quedado
	 * sin fuel
	 * @param atShip - true si el robot se apaga porque ha llegado a la nave espacial y false si el robot se apaga porque se
	 * 				   se ha quedado sin fuel
	 */
	@Override
	public void engineOff(boolean atShip) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * El robot engine informa que la comunicación ha terminado
	 */
	@Override
	public void communicationCompleted() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * El robot engine informa que el fuel y/o la cantidad de material reciclado ha cambiado
	 * @param fuel - Cantidad actual de fuel
	 * @param recycledMaterial - Cantidad actual de material reciclado
	 */
	@Override
	public void robotUpdate(int fuel, int recycledMaterial) {
		// TODO Auto-generated method stub
		this.JLabelInfo.setText(Messages.attributesUpdated(fuel, recycledMaterial));
	}

	/**
	 * El robot engine informa que el robot quiere decir algo
	 * @param message - El mensaje del robot
	 */
	@Override
	public void robotSays(String message) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * El robot engine informa de que se ha terminado el juego
	 */
	@Override
	public void endGame() {
		// TODO Auto-generated method stub
		
	}

}
