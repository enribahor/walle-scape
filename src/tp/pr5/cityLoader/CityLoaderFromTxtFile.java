package tp.pr5.cityLoader;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import tp.pr5.City;
import tp.pr5.Direction;
import tp.pr5.Place;
import tp.pr5.PlaceList;
import tp.pr5.Street;
import tp.pr5.auxiliaryOperations.Parser;
import tp.pr5.cityLoader.cityLoaderExceptions.WrongCityFormatException;
import tp.pr5.items.CodeCard;
import tp.pr5.items.Fuel;
import tp.pr5.items.Garbage;
import tp.pr5.items.Item;

/**
 * 
 * @author Antonio Núñez Guerrero
 *
 * CityLoaderFromTxtFile loads the map
 */
public class CityLoaderFromTxtFile {
	private Place initialPlace;

	public CityLoaderFromTxtFile(){
		
	}
	
	/**
	 * Método que carga el mapa de un fichero.
	 * Si el formato del mapa es incorrecto lanza una WrongCityFormatException
	 * @param file
	 * @return
	 * @throws WrongCityFormatException
	 */
	public City loadCity(java.io.InputStream file) throws WrongCityFormatException{
		
		BufferedReader input = new BufferedReader(new InputStreamReader(file));
		City cityMap = null;
		try{
			String line = input.readLine();
			if(!line.equals("BeginCity"))//Primera linea BeginCity
				throw new WrongCityFormatException();
			
			line = input.readLine();//Segunda linea BeginPlaces
			PlaceList places = loadPlace(input);//cargamos los places leidos en el fichero
			line = input.readLine();//linea BeginStreets
			cityMap = loadStreets(places, input);//cargamos las streets leidas del fichero

			line = input.readLine();//linea BeginItems
			loadItems(places, input);
			line = input.readLine();//linea EndCity
		}catch(IOException e){
			throw new WrongCityFormatException();
		}finally{
			  try {
				input.close();//cerramos el fichero
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return cityMap;
	}
	
	/**
	 * Metodo auxiliar que sirve para cargar los place que aparecen en el archivo de texto en un ArrayList de Place.
	 * Si el formato de una linea no es correcto lanza una WrongCityFormatException
	 * @param input - El BufferedReader para leer del archivo de texto
	 * @return - Un ArrayList con los place cargados del fichero.
	 * @throws IOException
	 * @throws WrongCityFormatException
	 */
	private PlaceList loadPlace(BufferedReader input) throws IOException, WrongCityFormatException{
		String line = input.readLine();
		if(line == null)//Si no hay ninguna linea en el texto
			throw new WrongCityFormatException();
		
		PlaceList places = new PlaceList();
		while(!line.equals("EndPlaces")){
			//procesamos la linea
			String array[] = line.split(" ");
			/*
			 * array[0] == Place
			 * array[1] == Identificador numérico
			 * array[2] == Nombre del place
			 * array[3] == Descripción del place
			 * array[4] == spaceShip/noSpaceShip
			 */
			
			//Para comprobar que el formato es correcto
			if(array.length != 5 || !array[0].equals("place") || 
					( !array[4].equals("spaceShip") && !array[4].equals("noSpaceShip" ) ))
				throw new WrongCityFormatException();
			
			
			//para comprobar que el identificador numérico del lugar en el texto se va aumentando de uno en uno
			if( !Parser.isInt(array[1]) || (Parser.isInt(array[1]) && places.numElems() != Parser.parseInt(array[1]))){
				throw new WrongCityFormatException();
				
			}
			
			//cambiamos las barras bajas por espacios en blanco
			array[3] = array[3].replace('_', ' ');
			Place p = new Place(array[2], this.stringToBooleanSpaceship(array[4]), array[3]);
			
		
			//Si el identificador numérico del lugar es 0 entonces el el lugar inicial
			if(Parser.isInt(array[1]) ){
				if(Parser.parseInt(array[1]) == 0)
					this.initialPlace = p;
			}else{
				throw new WrongCityFormatException();
			}
			
			//añadimos el place al arrayList
			places.addPlace(p);
			line = input.readLine();//leemos la siguiente línea
			
			if(line == null)//Si no hay ninguna linea en el texto
				throw new WrongCityFormatException();
		}
		
		return places;
	}
	
	/**
	 * Metodo que sirve para cargar las calles de un archivo de texto en una City. Si el formato de la linea no es
	 * correcto lanza una WrongCityFormatException
	 * @param p - ArrayList de lugares para conseguir el source y el target place de una calle.
	 * @param input - El BufferedReader para leer del archivo de texto
	 * @return City con todas las calles cargadas.
	 * @throws IOException
	 * @throws WrongCityFormatException
	 */
	private City loadStreets(PlaceList p, BufferedReader input) throws IOException, WrongCityFormatException{
		City cityMap = new City();
		String line = input.readLine();
		if(line == null)//Si no hay ninguna linea en el texto
			throw new WrongCityFormatException();
		
		while(!line.equals("EndStreets")){
			String array[] = line.split(" ");
			
			/* array[0] == "street"
			 * array[1] == numero de place
			 * array[2] == "place" (ignorar)
			 * array[3] == identificador numerico del lugar origen
			 * array[4] == direccion de la calle
			 * array[5] == "place" (ignorar)
			 * array[6] == identificador numérico del lugar destino
			 * array[7] == open/closed
			 * si open -> fin
			 * si closed -> array[8] == clave para abrir la puerta
			 */
			
			/*si la longitud es menor que 8 o
			 * si array[0] no es street
			 * si array[7] contiene open y la longitud es distinta de 8 o si array[7] es closed y 
			 * la longitud del array es distinta de 9 lanzamos la excepcion
			 * */
			if( (array.length < 8) || !array[0].equals("street") 
					|| (array[7].equals("open") && array.length != 8) || 
					(array[7].equals("closed") && array.length != 9) )
				throw new WrongCityFormatException();
			
			//Para asegurar que el identificador numérico de las street va aumentando de uno en uno.
			
			if(!Parser.isInt(array[1]) || ( cityMap.getNumStreet() != Parser.parseInt(array[1]) ) )
				throw new WrongCityFormatException();
			
			Place source = null;
			Place target = null;
			
			
			if(!Parser.isInt(array[3]) || !Parser.isInt(array[6])
					 || Parser.parseInt(array[3]) > p.numElems() || Parser.parseInt(array[6]) > p.numElems())
				throw new WrongCityFormatException();
			else{	
				source = p.getPlace( Parser.parseInt(array[3]) );
				target = p.getPlace( Parser.parseInt(array[6]) );
				if(source == null || target == null){
					throw new WrongCityFormatException();
				}
			}
			
			Street st = null;
			if(array[7].equals("closed"))
				st = new Street(source,Direction.valueOf(array[4].toUpperCase()),target,false, array[8]);
			else if (array[7].equals("open"))
				st = new Street(source,Direction.valueOf(array[4].toUpperCase()),target);
			
			
			cityMap.addStreet(st);
			line = input.readLine();
			
			if(line == null)//Si no hay ninguna linea en el texto
				throw new WrongCityFormatException();
		}
		
		return cityMap;
	}
	
	/**
	 * Metodo que carga los items que lee del fichero. Si el formato de la linea no es correcto, lanza una
	 * WrongCityFormatException
	 * @param p - Es donde guarda los items que lee del fichero
	 * @param input
	 * @throws IOException
	 * @throws WrongCityFormatException
	 */
	private void loadItems( PlaceList p, BufferedReader input) throws IOException, WrongCityFormatException{
		String line = input.readLine();
		if(line == null)//Si no hay ninguna linea en el texto
			throw new WrongCityFormatException();
		int i = 0; //contador para llevar la cuenta de los items que van apareciendo
		while(!line.equals("EndItems")){
			String[] array = line.split(" ");
			
			/*
			 * Dependiendo de lo que aparezca en array[0], es decir si es fuel o es garbage o es code card
			 * la linea va a tener longitud distinta
			 */
			
			if( array.length < 7 || (array.length!= 8 && array[0].equals("fuel")  || 
					array.length != 7 && array[0].equals("garbage") || 
					array.length != 7  && array[0].equals("codecard")) )
				throw new WrongCityFormatException();
			
			//si el identificador numérico del item no coincide con el contador de items que van apareciendo
			if(!Parser.isInt(array[1]) || i != Parser.parseInt(array[1])){
				throw new WrongCityFormatException();
			}
			
			 //numero de place en el que aparece el item inicializado a uno por si acaso no hay ningún numero de
			 //place al que pertenece el item en el la línea leida.
			int lugar = -1;
			Item it = null;
			
		
			//Dependiendo que tipo de item aparezca creamos un item u otro
			if(array[0].equals("fuel")){
				if(!Parser.isInt(array[4]) || !Parser.isInt(array[5]) || !Parser.isInt(array[7]) )
					throw new WrongCityFormatException();
				else{
					it = new Fuel(array[2], array[3].replace('_', ' '), Parser.parseInt(array[4]), Parser.parseInt(array[5]));
					lugar = Parser.parseInt(array[7]);
				}
			}else if(array[0].equals("codecard")){
				if(!Parser.isInt(array[6]) ){
					throw new WrongCityFormatException();
				}else{
					it = new CodeCard(array[2], array[3].replace('_', ' '), array[4]);
					lugar = Parser.parseInt(array[6]);
				}
			}else if(array[0].equals("garbage")){
				if(!Parser.isInt(array[4]) || !Parser.isInt(array[6])){
					throw new WrongCityFormatException();
				}else{
					it = new Garbage(array[2], array[3].replace('_', ' '), Parser.parseInt(array[4]));
					lugar = Parser.parseInt(array[6]);
				}
			}
		
			//En el caso de que en la linea leida falte el tipo de item
			if(it == null)
				throw new WrongCityFormatException();
			
			//Intentamos sacar el place. En caso de que no exista se lanza una WrongCityFormatException
			Place place = p.getPlace(lugar);
			if(place == null){
				throw new WrongCityFormatException();
			}
			
			//Insertamos el item en el contenedor del place
			p.getPlace(lugar).getContainerItem().addItem(it);
			
			line = input.readLine();//leemos la siguiente linea
			
			if(line == null)//Si no hay ninguna linea en el texto
				throw new WrongCityFormatException();
			i++;
			
		}
		
	}
	
	/**
	 * Método que sirve para transformar un string, que indica si es spaceShip o no, a booleano
	 * @param s - cadena que contiene spaceShip o noSpaceShip dependiendo si lo es o no
	 * @return true si es spaceShip, false si no es spaceShip
	 */
	private boolean stringToBooleanSpaceship(String s){
		return s.equals("spaceShip");
	}
	
	public Place getInitialPlace(){
		return this.initialPlace;
	}
}














