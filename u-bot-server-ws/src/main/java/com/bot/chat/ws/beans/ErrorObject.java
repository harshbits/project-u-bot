package com.bot.chat.ws.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private String code;

	private String message;

	private String message_details;

	public ErrorObject() {

	}

	/**
	 * Constructs for error code and message and message_details
	 * 
	 * @param code
	 *            Error code
	 * @param message
	 *            Error message
	 * @param message
	 *            Error message details
	 */
	public ErrorObject(String code, String message, String message_details) {
		this.code = code;
		this.message = message;
		this.message_details = message_details;
	}

	/**
	 * Constructs for error code and message and message_details
	 * 
	 * @param code
	 *            Error code
	 * @param message
	 *            Error message
	 */
	public ErrorObject(String code, String message) {
		this.code = code;
		this.message = message;
	}

	/**
	 * Method getMessageDetails.
	 * 
	 * @return String
	 */

	public String getMessage_details() {
		return message_details;
	}

	/**
	 * Method setMessageDetails.
	 * 
	 * @param message_details
	 *            String
	 */
	public void setMessage_details(String message_details) {
		this.message_details = message_details;
	}

	/**
	 * Method getCode.
	 * 
	 * @return String
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Method setCode.
	 * 
	 * @param code
	 *            String
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Method getMessage.
	 * 
	 * @return String
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Method setMessage.
	 * 
	 * @param message
	 *            String
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
