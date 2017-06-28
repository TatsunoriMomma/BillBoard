package BillBoard.exception;

import java.io.IOException;

public class IORuntimeException extends RuntimeException {
	private static final long serialVersionUId = 1L;

	public IORuntimeException(IOException cause) {
		super(cause);
	}
}
