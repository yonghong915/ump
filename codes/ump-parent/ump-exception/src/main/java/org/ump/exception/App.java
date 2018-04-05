package org.ump.exception;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		try {
			testExp();
		} catch (BusinessRuntimeException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void testExp() {
		try {
			"".substring(3, 4);
		} catch (Exception e) {
			throw new BusinessRuntimeException(ErrorCode.E_1001);
		}
	}
}
