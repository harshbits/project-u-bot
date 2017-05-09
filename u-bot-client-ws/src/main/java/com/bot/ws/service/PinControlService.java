package com.bot.ws.service;

public interface PinControlService {

	// Turn pin on
	public void on(int pinNum);
	
	// Turn pin off
	public void off(int pinNum);
	
	// Tell the state of the pin
	public  boolean state(int pinNum);
	

	// Shuts down ALL pins, like ALL OF THEM
	public void shutdown();

}
