package tp.pr5.cityLoader.cityLoaderExceptions;

/**
 * 
 * @author Antonio Núñez Guerrero
 *
 */
public class WrongCityFormatException extends Throwable {
	
	private static final long serialVersionUID = 1L;

	public WrongCityFormatException(){
		
	}
	
	public WrongCityFormatException(java.lang.String msg) {
		super(msg);
	}

	public WrongCityFormatException(String msg, Throwable arg) {
		super(arg);
	}
	
	public WrongCityFormatException(java.lang.Throwable arg) {
		super(arg);
	}
	
}
