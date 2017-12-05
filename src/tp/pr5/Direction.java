package tp.pr5;

/**
 * 
 * @author Antonio Núñez Guerrero
 *
 *	Enumerado con las distintas direcciones.
 */
public enum Direction {

	NORTH, EAST, SOUTH,  WEST, UNKNOWN;

	/**
	 * Metodo que devuelve la dirección opuesta a la pasada por parámetro.
	 * @param dir
	 * @return la direccion opuesta. En caso de ser una direción desconocida devuelve UNKNOWN
	 */
	public static Direction oppositeDirection(Direction dir) {
		Direction direction;
		if (dir == NORTH){
			direction = SOUTH;
		}else if (dir == SOUTH){
			direction = NORTH;
		}else if (dir == EAST){
			direction = WEST;
		}else if (dir == WEST){
			direction = EAST;
		}else{
			direction = UNKNOWN;
		}
		
		return direction;
	}
	
	public static void valueOf(){
		
	}
 

}
