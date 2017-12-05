
package tp.pr5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import tp.pr5.cityLoader.CityLoaderFromTxtFile;
import tp.pr5.cityLoader.cityLoaderExceptions.WrongCityFormatException;
import tp.pr5.console.Console;
import tp.pr5.console.ConsoleController;
import tp.pr5.gui.GUIController;
import tp.pr5.gui.MainWindow;
import tp.pr5.sintax.Sintax;

/**
 * 
 * @author Antonio Núñez Guerrero
 *
 * Application entry-point. The application admits a parameter -m | --map with the name of the map file to be used and 
 * a parameter -i | --interface with the type of interface (console or swing)
 *
 * If no arg is specified (or more than one file is given), it prints an error message (in System.err) and the application finishes with an error code (-1).
 *
 * If the map file cannot be read (or it does not exist), the application ends with a different error code (-2).
 *
 * If the interface arg is not correct (console or swing) the application prints a message and the application finishes with an error code (-3). If the interface arg is not included it starts the application in console mode. Otherwise, the simulation starts and eventually the application will end normally (return code 0).
 */
public class Main {
	
	/**
	 * Metodo estatico que sirve para ejecutar el juego
	 * @param Args
	 */
	public static void main(String [] args){
		City city = new City();
		String [] arrayStr;
		String archivo;
		String type;
		
		//Si los parámetros introducidos son correctos, entonces devuelve el tipo de ejecucion y nombre del archivo
		arrayStr = Sintax.checkAllLine(args);
		archivo = arrayStr[1];
		type = arrayStr[0];
		
		//Si type es null cerramos la aplicacion
		if(type == null){
			System.exit(1);
		}
		
		if(archivo != null){
			CityLoaderFromTxtFile cl = new CityLoaderFromTxtFile();
			
			try {
				FileInputStream file = new FileInputStream(archivo);
				try {
					city = cl.loadCity(file);
				} catch (WrongCityFormatException e) {
					// TODO Auto-generated catch block
					System.err.println("Error loading the map");
					System.exit(2);
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.err.println("Error reading the map file: " + archivo + " (No existe el fichero o "
						+ "el directorio)");
				System.exit(2);
			}
			
			RobotEngine r = new RobotEngine(city, cl.getInitialPlace(), Direction.NORTH);
			
			if(type.equals("swing")){
				GUIController guiController = new GUIController(r);
				new MainWindow(guiController);
				//Iniciamos la aplicación
				guiController.startController();
			}else if (type.equals("console")){
				ConsoleController consoleController = new ConsoleController(r);
				new Console(consoleController);
				//Iniciamos la aplicación
				consoleController.startController();
			}else if(type.equals("both")){
				GUIController guiController = new GUIController(r);
				ConsoleController consoleController = new ConsoleController(r);
				new MainWindow(guiController);
				new Console(consoleController);
				//Iniciamos la aplicación
			    guiController.startController();
			}
		}
	}

}
