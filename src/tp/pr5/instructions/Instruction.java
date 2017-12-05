package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.ItemContainer;

/**
 * 
 * @author Antonio Núñez Guerrero
 *
 * This interface represents an instruction supported by the application. Every instruction that the robot may perform 
 * implements this interface. It forces instructions to provide with the implementation of four different methods:
 *
 *   Parse method tries to parse a string with the information of the instruction the class represents
 *   Help method returns a string with an explanation of the kind of expression that the parse method supports
 *   ConfigureContext method establishes the context needed to execute the instruction
 *   Execute method performs the actual work of the instruction, executing it.
 *
 * The execute method does not have any parameter. Therefore the context of execution must be given to the instruction 
 * object prior to its invocation using the configureContext method. Note that the actual context depends on the subclass 
 * because the information needed varies between instructions.
 */
public interface Instruction {

	/**
	 * Método que se encarga de paersear al cadena de acuerdo con cada instruccion
	 * @param cad - string con la cadena a parsear
	 * @return Devuelve la instruccion correspondiente, si todo ha salido bien.
	 * @throws WrongInstructionFormatException si algo ha salido mal
	 */
	public abstract Instruction parse(java.lang.String cad)throws WrongInstructionFormatException;
	
	/**
	 * Método que devuelve un String con la ayuda relativa a cada instrucción
	 * @return
	 */
	public abstract String getHelp();
	
	/**
	 * Método que configura el contexto de la instrucción, es decir, fija sus atributos a los valores correspondientes.
	 * @param engine
	 * @param navigation
	 * @param robotContainer
	 * @param ejec
	 */
	public abstract void configureContext(RobotEngine engine, NavigationModule navigation,
            ItemContainer robotContainer);
	
	/**
	 * Método que se encarga de ejecutar la instrucción
	 * @throws InstructionExecutionException  si algo ha ido mal.
	 */
	public abstract void execute() throws InstructionExecutionException;

}
