package tp.pr5.items;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import tp.pr5.Observable;

/**
 * 
 * @author Antonio Núñez Guerrero
 *
 * A container of items. It can be employed by any class that stores items. A container cannot store two items with the same identifier
 *
 * It provides methods to add new items, access them and remove them from the container.
 */
public class ItemContainer extends Observable<InventoryObserver> {

	private List<Item> container;
	
	public ItemContainer(){
		this.container = new ArrayList<Item>();
	}
	
	/**
	 * 
	 * @return una lista que representa el contenedor de objetos de WALL·E
	 */
	public List<Item> getContainer(){
		return container;
	}

	/**
	 * Método que sirve para fijar el atributo container al parámetro container
	 * @param container
	 */
	public void setContainer(List<Item> container){
		this.container = container;
	}
	
	/**
	 * 
	 * @return el número de items que tiene el contenedor de objetos
	 */
	public int numberOfItems(){
		return this.container.size();
	}
	
	/**
	 * Añade el elemento que le pasamos por parámetro.
	 * @param item
	 * @return true si se ha añadido al contenedor. False en caso contrario
	 */
	public boolean addItem(Item item){
		boolean insert = false; //boleano para saber si se ha añadido
		if(this.container.size() == 0){//si el numero de elementos del contenedor es 0
			this.container.add(item);// añadimos el item
			insert = true;
			
		}else if(!this.containsItem(item.getId()) ){ //Si no esta contenido ya
			//Buscamos la posicion en la que debería estar para que el contenedor quede ordenado
			int i = this.index(item); 
			this.container.add(i, item); //Lo insertamos
			insert = true;
		}

		return insert;
	}
	
	/**
	 * Método que devuelve un item cuyo identificador coincide con el identificador que le pasamos por 
	 * parámetro.
	 * @param id
	 * @return Devuelve null en caso de que no se encuentre el item, el item en caso de que se encuentre.
	 */
	public Item getItem(String id){
		Item item = new ItemGen(id, "");
		
		int i = Collections.binarySearch(this.container,  item);
		if(i<0){
			item = null;
		}else{
			item = this.container.get(i);
		}
		
		return item;
	}
	
	/**
	 * Método para coger un item del contenedor.
	 * @param id - el id del objeto que queremos coger
	 * @return null si no hay ningún objeto con ese id
	 */
	public Item pickItem(String id){
		Item it = this.getItem(id); //cogemos el item del contenedor
		if(it != null){ // si no es null lo borramos del contenedor
			this.container.remove(it);
			requestInventoryChange(this.container);//se informa que el inventario del lugar ha cambiado
			
		}
		
		return it;
	}
	
	/**
	 * Método para pasar a string toda la informacion relativa al itemContainer que se debe mostrar
	 */
	public String toString(){
		String c="";
		for (int i=0; i<this.container.size(); i++){
			c = c + "   " + this.container.get(i).getId() + "\n";
		}
		return c;
	}
	
	/**
	 * Método para devolver la posición que debe ocupa en el contenedor el item que le pasamos por parámetro
	 * para posteriormente insertarlo. Utiliza binarySearch
	 * @param item
	 * @return i - Posicion que ocupa en la lista el item pasado por parámetro.
	 */
	private int index(Item item){
		int i;
		
		i = Collections.binarySearch(this.container,  item);
		if(i<0){
			i = -i-1;
		}
		
		return i;
	}
	
	/**
	 * Comprueba si el item con su id existe en el contenedor
	 * @param id - identificador del objeto a buscar
	 * @return true si existe en el contenedor un item con el mismo id
	 */
	public boolean containsItem(String id){
		
		return this.getItem(id)!=null;
	}
	
	/**
	 * Método que elimina el elemento del contenedor.
	 * @param it - item que queremos eliminar del contenedor
	 * @return true si se ha podido eliminar, false si no se ha podido eliminar
	 */
	public boolean removeItem(Item it){
		boolean removed;
		removed = this.container.remove(it);
		return removed;
	}
	

	/**
	 * Solicita al motor que el inventario se ha cambiado
	 * @param inventory - lista con el nuevo inventario
	 */
	public void requestInventoryChange(List<Item> inventory){
		for(Iterator<InventoryObserver> it = this.iterator(); it.hasNext(); ){
			it.next().inventoryChange(inventory);
		}
	}
	
	/**
	 * Notifica de que el inventario se ha escaneado
	 */
	public void requestInventoryScanned(){
		for(Iterator<InventoryObserver> it = this.iterator(); it.hasNext(); ){
			it.next().inventoryScanned(toString());
		}
	}
	
}
