package exceptions;

import java.io.IOException;

public class MalformedFileException extends IOException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8932982291392265826L;

	public MalformedFileException(String s) {
		super(s);
	}
}
