package tp.pr5;

import tp.pr5.items.CodeCard;

/**
 * 
 * @author Antonio Núñez Guerrero
 *
 * A street links two places A and B in one direction. If it is defined as Street(A,NORTH,B) it means that Place B is 
 * at NORTH of Place A. Streets are two-way streets, i.e. if B is at NORTH of A then A is at SOUTH of B.
 *
 * Some streets are closed and a code (contained in a code card) is needed to open them
 */
public class Street {
	private Place source;
	private Place target;
	private Direction direction;
	private boolean isOpen;
	private String code;

	public Street(Place source, Direction direction, Place target) {
		this.source = source;
		this.target = target;
		this.direction = direction;
		this.isOpen = true;

	}
	
	public Street(Place source, Direction direction, Place target, boolean isOpen, String code){
		this.source = source;
		this.target = target;
		this.direction = direction;
		this.isOpen = isOpen;
		this.code = code;
	}

	/**
	 * Método que comprueba si la calle sale de un lugar en una direccion
		determinada. Devuelve true si el lugar origen de la calle es el mismo que
		el que le entra por parámetro y si la direccion de la calle es la misma
		que la que le entra por parámetro.
	 * @param place: el lugar donde está WALL*E en ese momento
	 * @param whichDirection: direccion a la que está mirando WALL·E
	 * @return devuelve true si existe una calle cuyo lugar en el que esta WALL·E y la direccion a
	 * la que esta mirando son iguales que los que le entran por parámetro	 
	 * */
	public boolean comeOutFrom(Place place, Direction whichDirection) {
		
		return (this.source == place && this.direction == whichDirection)
				|| ((this.target == place)
						&& (this.direction == Direction.oppositeDirection(whichDirection)));
	}

	/**
	 * Método que devuelve el siguiente lugar en el que esta WALL·E cuando se mueve.
	 * @param whereAmI-el lugar donde inicialmente se encuentra WALL·E
	 * @return El place correspondiente, null en caso de que no coincida.
	 */
	public Place nextPlace(Place whereAmI){
		Place place;
		//Si el lugar de origen de WALL·E coincide con el lugar que le pasamos por parámetro
		if (this.source == whereAmI){
			place = this.target;//devolvemos el lugar a donde debe ir
		//En el caso de que el lugar a donde debe ir WALL·E es el lugar que le pasamos por parámetro
		}else if (this.target == whereAmI){
			place = this.source;//devolvemos el lugar a donde debe ir que en este caso sera el source
		}else{
			place = null;
		}
		
		return place;
	}
	
	/**
	 * Método que intenta abrir un a puerta 
	 * @param card
	 * @return true si se ha conseguido abrir la puerta
	 */
	public boolean open(CodeCard card){
		boolean abierta;
		this.isOpen = this.code.equals(card.getCode());//Si coinciden los códigos
		abierta = this.isOpen;
	
		return abierta;
	}
	
	/**
	 * Metodo que intenta cerrar una puerta.
	 * @param card
	 * @return true si se ha conseguido cerrar.
	 */
	public boolean close(CodeCard card){
		boolean cerrada;
		this.isOpen = !this.code.equalsIgnoreCase(card.getCode()); //Abierta si no coinciden los codigos
		cerrada = !this.isOpen;
	
		return cerrada;
	}
	
	/**
	 * 
	 * @return el código para abrir la calle
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * 
	 * @return si la calle está abierta o no
	 */
	public boolean isOpen(){
		return this.isOpen;
	}
	
	/**
	 * Método que sirve para fijar el atributo isOpen al parámetro isOpen
	 * @param isOpen
	 */
	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

}
