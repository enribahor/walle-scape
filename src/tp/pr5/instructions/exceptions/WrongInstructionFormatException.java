package tp.pr5.instructions.exceptions;

/**
 * 
 * @author Antonio Núñez Guerrero
 *
 */
public class WrongInstructionFormatException extends Throwable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String message;

	public WrongInstructionFormatException(){
		
	}
	
	public WrongInstructionFormatException(java.lang.String arg0) {
		super(arg0);
	}
	
	public WrongInstructionFormatException(java.lang.String arg0, java.lang.Throwable arg1) {
		super(arg0, arg1);
		
	}
	
	public WrongInstructionFormatException(java.lang.Throwable arg0) {
		super(arg0);
	}
	

}
