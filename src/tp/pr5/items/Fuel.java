package tp.pr5.items;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.messages.Messages;

/**
 * 
 * @author Antonio Núñez Guerrero
 *
 * An item that represents fuel. This item can be used at least once and it provides power energy to the robot. 
 * When the item is used the configured number of times, then it must be removed from the robot inventory
 */
public class Fuel extends Item {

	private int power;
	private int times;
	
	public Fuel(String id,String description){
		super(id,description);
	}
	
	public Fuel(String id,String description, int power,int times){
		super(id,description);
		this.power = power;
		this.times = times;
	}
	
	/**
	 * El Fuel puede usarse siempre que el numero de veces que se pueden usar es mayor que 0
	 */
	@Override
	public boolean canBeUsed() {
		
		return this.times>0;
	}

	/**
	 * Método que intenta usar el objeto Fuel que le llama.
	 * Devuelve true si lo ha podido utilizar y false si no lo ha podido utilizar.
	 */
	@Override
	public boolean use(RobotEngine r, NavigationModule nav) {
		boolean used = false;
		if(this.canBeUsed()){
			//Aumentar la energia de WALL·E
			r.addFuel(this.power);
			this.times--;
			used = true;
		}
		
		return used;
	}
	
	/**
	 * 
	 * @return la energía que da el fuel
	 */
	public int getPower() {
		return power;
	}

	/**
	 * 
	 * @return las veces que se puede usar el objeto fuel
	 */
	public int getTimes() {
		return times;
	}
	
	@Override
	public String toString(){
		return Messages.itemDescriptionFuel(id, description, power, times);
	}

}
