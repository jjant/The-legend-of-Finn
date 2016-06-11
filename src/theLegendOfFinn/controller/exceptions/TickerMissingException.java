package theLegendOfFinn.controller.exceptions;
/**
 * Exception thrown when a valid ticker is not found.
 *
 */
public class TickerMissingException extends Exception {
	public TickerMissingException(String string) {
		super(string);
	}

	private static final long serialVersionUID = 1L;

}
