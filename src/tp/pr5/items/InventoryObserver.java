package tp.pr5.items;

import java.util.List;

/**
 * 
 * @author Antonio Núñez Guerrero
 * 
 * Interface of the observers that want to be notified about the events ocurred in the robot inventory. The container 
 * will notify its observer every change in the container (when the robot picks or drops items) and when an item is 
 * removed from the container beacuse it is empty. The container will also notify when the user requests to scan an 
 * item or the whole container
 */
public interface InventoryObserver {

	/**
	 * Notifica que el contenedor ha cambiado
	 * @param inventory - Nuevo inventario
	 */
	public void inventoryChange(List<Item> inventory);
	
	/**
	 * Notifica que el usuario solicita una instrucción scan en el inventario
	 * @param inventoryDescription - Información sobre el inventario
	 */
	public void inventoryScanned(String inventoryDescription);
	
	/**
	 * Notifica que el usuario desea escanear un objeto del inventario
	 * @param description - Descrición del objeto
	 */
	public void itemScanned(String description);
	
	/**
	 * Notifica que un objeto se ha gastado y que hay que eliminarlo del contenedor.
	 * Después invocará al método inventoryChange
	 * @param itemName - Nombre del objeto gastado
	 */
	public void itemEmpty(java.lang.String itemName);
	
}
