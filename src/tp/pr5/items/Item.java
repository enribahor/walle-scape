package tp.pr5.items;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;

/**
 * 
 * @author Antonio Núñez Guerrero
 *
 * The superclass of every type of item. It contains the common information for all the items and it defines 
 * the interface that the items must match
 */
public abstract class Item implements Comparable<Object>{

	protected String id; //id del objeto
	protected String description;//descripcion del objeto
	
	public Item(String id, String description){
		this.id = id;
		this.description = description;
	}
	
	/**
	 * Comprueba si un objeto puede usarse. Un objeto puede usarse si times es distinto
	 * de cero. Al ser un método abstracto, debe implementarse en las subclases 
	 * correspondientes.
	 * @return un booleano que dice si puede usarse el objeto o no.
	 */
	public abstract boolean canBeUsed();
	
	
	/**
	 * Metodo que intenta usar un objeto dado. Se debe implementar en las subclases
	 * ya que es un método abstracto.
	 * @param r 
	 * @param p
	 * @return un booleano que idica se ha podido usar el objeto o no.
	 */
	public abstract boolean use(RobotEngine r, NavigationModule nav);
	
	/**
	 * Método que comprueba si el objeto de clase es igual que el objeto pasado por parametro
	 * Se hace un casting para transformarlo a item
	 * @param item
	 * @retrun True si el id del Item de clase es igual al id del Item pasado por parámetro.
	 */
	public boolean equals(Object obj){
		Item item = (Item) obj;
		
		return this.id.equalsIgnoreCase(item.id);
	}
	
	/**
	 * Método que compara el objeto de clase con el objeto pasado por parámetro según su id
	 * Se hace un casting para transformarlo a item
	 * Ignora las mayúsculas
	 * @param anObject
	 * @return -1 si this.id < anObject.id
	 * 			1 si this.id > anObject.id
	 * 			0 si this.id = anObject.id
	 */
	public int compareTo(Object obj){
		Item item = (Item) obj;
		return this.id.compareToIgnoreCase(item.id);
	}
	
	/**
	 * 
	 * @return el id del objeto
	 */
	public String getId(){
		return this.id;
	}
	
	/**
	 * @return un string con la descripcion del item
	 */
	public String toString(){
		return this.description;
	}

	/**
	 * 
	 * @return un string con la descripción del objeto
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Método que sirve para fijar el atributo description al parámetro description
	 * 
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
