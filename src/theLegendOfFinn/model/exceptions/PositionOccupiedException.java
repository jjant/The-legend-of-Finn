package theLegendOfFinn.model.exceptions;

/**
 * Exception launched when trying to overwrite a position in the entitites grid.
 */
public class PositionOccupiedException extends Exception {
	private static final long serialVersionUID = 1L;

	public PositionOccupiedException(String message){
		super(message);
	}
	
	public PositionOccupiedException(Throwable throwable){
		super(throwable);
	}
	public PositionOccupiedException(String message, Throwable throwable){
		super(message, throwable);
	}
}
