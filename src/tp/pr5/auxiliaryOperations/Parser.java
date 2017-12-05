package tp.pr5.auxiliaryOperations;

/**
 * 
 * @author Antonio Núñez Guerrero
 *
 * Clase auxiliar (no viene en la documentacion) con algunas operaciones auxiliares.
 */
public class Parser {

	/**
	 * Método que dado un String dice si ese string lo forma un entero o no.
	 * @param s - string con un posible numero
	 * @return true si el string esta formado en su totalidad por un entero, false en caso contrario
	 */
	public static boolean isInt(String s){
		int i=0;
		boolean esNumero;
		do{
			char c = s.charAt(i);
			// es numero cuando es un numero entre el 0 y 9 o si empieza por -
			esNumero = isNumber(c) || (i == 0  && c=='-'); 
			i++;
		}while(i<s.length() && esNumero);
		return esNumero;
	};
	
	/**
	 * Método auxiliar que nos dice si el caracter pasado como parámetro es un numero o no.
	 * @param c 
	 * @return true si c es número, false si no lo es;
	 */
	private static boolean isNumber(char c){
		return (c=='0' || c=='1' || c=='2' || c=='3' || c=='4' || c=='5' || c=='6' || c=='7' || c=='8' || c=='9'); 
	}
	
	
	/**
	 * Método que transforma a entero el string, que contiene un entero, pasado como parametro.
	 * El metodo NO COMPRUEBA que el string pasado como parámetro corresponda a un entero: usar isNumber(char c).
	 * @param s - string con un entero
	 * @return Un int correspondiente al entero que esta en el string
	 */
	public static int parseInt(String s) {
		String str;
		int n;
		str = ""+s;
		n = Integer.parseInt(str);
		
		return n;
	}
	
}
