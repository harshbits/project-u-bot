package com.bot.chat.ws.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.bot.chat.ws.beans.ErrorObject;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author harshbhavsar
 *
 */
@ControllerAdvice
@Slf4j
public class CommonExceptionHandler {
	
	/**
	 * It is a global exception handler for the uncaught Exception
	 * 
	 * @param e
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<?> hadnleException(Exception e) {
		log.error("Error occured {}", e.getMessage());
		return new ResponseEntity<ErrorObject>(new ErrorObject(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
