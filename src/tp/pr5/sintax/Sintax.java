package tp.pr5.sintax;

import tp.pr5.messages.Messages;

public class Sintax {
	public final static String NAME_OF_FILE = "madrid.txt";

	/**
	 * Método que escribe por consola la ayuda cuando se ejecuta el comando -h
	 */
	public static void showHelp(){
		System.out.println("Execute this assignment with these parameters:" +
				"\n" +
				"usage: tp.pr4.Main [-h] [-i <type>] [-m <mapfile>]" +
				"\n" +
				" -h,--help               Shows this help message" +
				"\n" +
				" -i,--interface <type>   The type of interface: console or swing" +
				"\n" +
				" -m,--map <mapfile>      File with the description of the city");
	}
	
	/**
	 * Comprueba si la interfaz introducida es correcta
	 * @param command
	 */
	public static boolean correctInterface(String command){
		
		return command.equals("console") || command.equals("swing") || command.equals("both");
	}
	
	/**
	 * Comprueba si se ha introducido el comando -i o --interface
	 * @param command
	 */
	public static void checkI(String command){
		if(!command.equals("-i") && !command.equals("--interface")){
			System.out.println(command + ": command not found");
		}
	}
	
	/**
	 * Método que muestra el correspondiente mensaje cuando el comando pasado por parámetro no es -m ni --map
	 * @param command
	 */
	public static void wrongMMessage(String command){
		if(!command.equals("-m") && !command.equals("--map")){
			System.out.println(command + ": command not found");
		}
	}
	/**
	 * Comprueba si la longitud de la linea de comandos es la correcta.
	 * @param args - vector de string con la linea de comandos 
	 * @return true si la longitud de la linea es correcta, false si no lo es 
	 */
	public static boolean correctLenght(String [] args){
		return (args.length >= 1 && args.length <= 4);
	}
	
	/**
	 * Método que comprueba si lo correspondiente al tipo es correcto (consola o swing).
	 * @param args - vector de string de longitud 4
	 * @return true si es correcto, false si no es correcto.
	 */
	public static boolean correctType(String [] args){
		
		return ( (args[0].equals("-i") || args[0].equals("--interface")) 
				&& (args[1].equals("console") || args[1].equals("swing") || args[1].equals("both")) )
				|| (args[2].equals("-i") || args[2].equals("--interface"))
				&& (args[3].equals("console") || args[3].equals("swing") || args[3].equals("both"));
	}
	
	/**
	 * Comprueba si el comando es -m o --map
	 * @param command - string con el comando
	 * @return true si el comando es -m o si es --map
	 */
	public static boolean commandIsMap(String command){
		
		return command.equals("-m") || command.equals("--map");
	}
	
	/**
	 * Comprueba si el comando es --help o -h
	 * @param command - string con el comando
	 * @return true si el comando es -h o si es --help, false en otro caso
	 */
	public static boolean commandIsHelp(String command){
		
		return command.equals("--help") || command.equals("-h");
	}
	
	/**
	 * Método que comprueba la incorreccion de toda la linea de comandos y mostrando los correspondientes mensajes.
	 * @param args
	 * @return Un array de String de longitud 2 con el tipo de interfaz que se va a ejecutar(swing/console) en la primera 
	 * posicion si la sintaxis es correcta, null si es incorrecta; y en la segunda posicion el nombre del archivo si la 
	 * sintaxis es correcta, null si la sintaxis es incorrecta
	 */
	public static String [] checkAllLine(String [] args){
		String archivo = null;
		String type = null;
		String [] arrayStr = new String[2];
		
		//Si la longitud es igual a 0
		if(args.length == 0){
			System.err.println(Messages.isNotMapFile());
			
		}else if (args.length == 2 && commandIsMap(args[0])){
			System.err.println(Messages.notSpecifiedInterface());
			
		}else if (args.length == 2 && correctType(args)){
			System.err.println(Messages.isNotMapFile());
			
		}
		//Si la longitud es 1 y el comando no es ni --help ni -h
		else if(args.length == 1 && !commandIsHelp(args[0])){
			System.err.println("Try 'tp.pr4.Main -h or --help' for more information");
			
		//Si la longitud es 1 y el comando no es --help o -h
		}else if (args.length == 1 && commandIsHelp(args[0])){
			showHelp();
			System.exit(0);
		}
		//Linea de comandos correcta
		else if (args.length == 4 && commandIsMap(args[0]) && correctType(args) ){
			archivo = args[1];
			if(correctInterface(args[3])){
				type = args[3];
			}else{
				System.err.println(Messages.wrongInterface());
			}
		
		} else if(args.length == 4 && commandIsMap(args[2]) && correctType(args) ){
			archivo = args[3];
			if(correctInterface(args[1])){
				type = args[1];
			}else{
				System.err.println(Messages.wrongInterface());
			}
		}else if(!correctType(args)){
			System.err.println(Messages.wrongInterface());
			System.exit(3);
		}
		
		arrayStr[0] = type;
		arrayStr[1] = archivo;
		
		return arrayStr;
	}
}
