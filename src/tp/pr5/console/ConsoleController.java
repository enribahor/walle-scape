package tp.pr5.console;

import java.util.Scanner;

import tp.pr5.Controller;
import tp.pr5.RobotEngine;

/**
 * 
 * @author Antonio Núñez Guerrero
 *
 * The controller employed when the application is configured as a console application. It contains the simulation 
 * loop that executes the instructions written by the user on the console.
 */
public class ConsoleController extends Controller{

	public ConsoleController(RobotEngine robot){
		super(robot);
	}

	/**
	 * Método que inicia la aplicación mediante consola
	 */
	@Override
	public void startController() {
		// TODO Auto-generated method stub
		Scanner sc =  new Scanner(System.in); //leemos de consola
		robotEngine.getNavigation().requestInitNavigationModule();
		this.robotEngine.requestStart();
		
		while(true){
			System.out.print("WALL·E> ");
			this.robotEngine.generarYEjecutarInstruccion(sc.nextLine());
			
		}
	}
}
