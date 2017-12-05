package tp.pr5;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import tp.pr5.instructions.DropInstruction;
import tp.pr5.instructions.HelpInstruction;
import tp.pr5.instructions.Instruction;
import tp.pr5.instructions.MoveInstruction;
import tp.pr5.instructions.OperateInstruction;
import tp.pr5.instructions.PickInstruction;
import tp.pr5.instructions.QuitInstruction;
import tp.pr5.instructions.RadarInstruction;
import tp.pr5.instructions.ScanInstruction;
import tp.pr5.instructions.TurnInstruction;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;

/**
 * 
 * @author Antonio Núñez Guerrero
 *
 * The interpreter is in charge of converting the user input in an instruction for the robot. Up to now, the valid instructions are:
 *
 *   MOVE | MOVER
 *   TURN | GIRAR { LEFT | RIGHT }
 *   PICK | COGER <ITEM>
 *   DROP | SOLTAR <ITEM>
 *   SCAN | ESCANEAR [ <ITEM> ]
 *   RADAR
 *   OPERATE | OPERAR <ITEM>
 *   HELP | AYUDA
 *   QUIT | SALIR
 *
 */
public class Interpreter {

	/**
	 * @param Un string con la posible instrucción de consola. Recorre una lista que contiene todas las posibles
	 * instrucciones.
	 * @return la instruccion parseada  
	 * throws WrongInstructionFormatException Si la instruccion no se corresponde con ninguna de la lista. 
	 */
	public static Instruction generateInstruction(java.lang.String line)
    throws WrongInstructionFormatException{
		List<Instruction> list = new ArrayList<Instruction>();
		boolean exc = true;//Para comprobar cuando no se captura la excepcion
		Instruction inst = null;
		
		//Crear la lista con las instrucciones
		list = creaListaInstruccines();
		
		Iterator<Instruction> it = list.iterator();
		
		while(it.hasNext() && exc){
			exc = false;
			inst = it.next();
			try{
				inst.parse(line);
			}catch(WrongInstructionFormatException e){
				//Si da error de parseo entonces se captura la excepcion y hay que seguir buscando la instruccion correcta
				exc = true;
			}	
		}
		if(exc)
			throw new WrongInstructionFormatException("I do not understand. Please repeat");
		
		return inst;
		
	}
	
	/**
	 * Método que crea una lista de instrucciones con todas las intrucciones
	 * @return la lista de instrucciones creada.
	 */
	public static List<Instruction> creaListaInstruccines(){
		List<Instruction> list = new ArrayList<Instruction>();

		list.add(new TurnInstruction());
		list.add(new PickInstruction());
		list.add(new ScanInstruction());
		list.add(new DropInstruction());
		list.add(new OperateInstruction());
		list.add(new MoveInstruction());
		list.add(new RadarInstruction());
		list.add(new HelpInstruction());
		list.add(new QuitInstruction());
		
		return list;
	}
	

	/**
	 * Método que devuelve un string con la ayuda.
	 * @return un string con la descripcion que mostrara el programa cuando se ejecute help
	 */
	public static String interpreterHelp() {
		String line = "The valid instructions for WALL-E are:" + "\n" + 
				"     MOVE"+ "\n" +
		        "     TURN <LEFT | RIGHT>\n" +
				"     PICK <id>\n" +
				"     SCAN [ <id> ]\n" +
				"     OPERATE <id>\n" +
				"	  RADAR\n"	+
				"	  DROP <id>\n" +	
				"     HELP\n" +
				"     QUIT\n";
		return line;
	}
}
