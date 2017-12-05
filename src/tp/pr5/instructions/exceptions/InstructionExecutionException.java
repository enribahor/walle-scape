package tp.pr5.instructions.exceptions;

/**
 * 
 * @author Antonio Núñez Guerrero
 *
 */
public class InstructionExecutionException extends Throwable {

	
	private static final long serialVersionUID = 1L;

	public InstructionExecutionException(){
		
	}
	
	public InstructionExecutionException(String arg0){
		super(arg0);
	}

	public InstructionExecutionException(String arg0, java.lang.Throwable arg1){
		super(arg0);
	}
	
	public InstructionExecutionException(java.lang.Throwable arg0) {
		super(arg0);
	}
}
