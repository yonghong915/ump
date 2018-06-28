package com.ump.core.util.web;

public interface RestStatus {
	/**
	 * the status codes of per restful request.
	 *
	 * @return 20xxx if succeed, 40xxx if client error, 50xxx if server side crash.
	 */
	String code();

	/**
	 * @return status enum name
	 */
	String name();

	/**
	 * @return message summary
	 */
	String message();

}
