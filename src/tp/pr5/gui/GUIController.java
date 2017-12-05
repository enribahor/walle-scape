package tp.pr5.gui;

import tp.pr5.Controller;
import tp.pr5.RobotEngine;
import tp.pr5.Rotation;
import tp.pr5.instructions.*;

/**
 * 
 * @author Antonio Núñez Guerrero
 * 
 * The controller employed when the application is configured as a swing application. It is responsible for requesting the 
 * robot engine start and it redirects the actions performed by the user on the window to the robot engine.
 *
 */
public class GUIController extends Controller{
	public GUIController(RobotEngine robot){
		super(robot);
	}

	/**
	 * Método abstracto que se encarga de ejecutar al aplicación de tres posibles maneras.
	 * 1- Mediante interfaz gráfica
	 * 2- Mediante consola
	 * 3- De ambas formas   
	 */
	@Override
	public void startController() {
		robotEngine.getNavigation().requestInitNavigationModule();
		robotEngine.requestStart();
		
	}
	
	/**
	 * Crea una instrucción pick e informa al modelo para que la ejecute.
	 * @param id - String con el id del objeto a coger
	 */
	public void executePickInstruction(String id){
		Instruction inst = new PickInstruction(id);
		super.robotEngine.communicateRobot(inst);
	}
	
	/**
	 * Crea una instrucción drop e informa al modelo para que la ejecute.
	 * @param id - String con el id del objeto a tirar
	 */
	public void executeDropInstruction(String id){
		if(id == null)
			id = "";
		Instruction inst = new DropInstruction(id);
		super.robotEngine.communicateRobot(inst);
	}
	
	/**
	 * Crea una instrucción operate e informa al modelo para que la ejecute.
	 * @param id - String con el id del objeto a usar
	 */
	public void executeOperateInstruction(String id){
		if(id == null)
			id = "";
		Instruction inst = new OperateInstruction(id);
		super.robotEngine.communicateRobot(inst);
	}
	
	/**
	 * Crea una instrucción turn e informa al modelo para que la ejecute.
	 * @param rotation - String con la dirección de rotación
	 */
	public void executeTurnInstruction(String rotation){
		Rotation rot = Rotation.valueOf(rotation);
		Instruction inst = new TurnInstruction(rot);
		super.robotEngine.communicateRobot(inst);
	}
	
	/**
	 * Crea una instrucción move e informa al modelo para que la ejecute.
	 */
	public void executeMoveInstruction(){
		Instruction inst = new MoveInstruction();
		super.robotEngine.communicateRobot(inst);
	}
	
	/**
	 * Crea una instrucción quit e informa al modelo para que la ejecute.
	 */
	public void executeQuitInstruction(){
		Instruction inst = new QuitInstruction();
		super.robotEngine.communicateRobot(inst);
	}
}
