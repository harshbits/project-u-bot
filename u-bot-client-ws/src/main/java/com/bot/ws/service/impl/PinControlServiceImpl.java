package com.bot.ws.service.impl;

import com.bot.ws.service.PinControlService;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class PinControlServiceImpl implements PinControlService {

	// Set up controller
//	private static final GpioController gpio = GpioFactory.getInstance();

	private static GpioController gpio;
	// Make array of Pin
	private static GpioPinDigitalOutput[] pins = new GpioPinDigitalOutput[30];

	@Override
	public void on(int pinNum) {
		convert(pinNum);
		pins[pinNum].high();
	}

	@Override
	public void off(int pinNum) {
		convert(pinNum);
		pins[pinNum].low();
	}

	@Override
	public boolean state(int pinNum) {
		return pins[pinNum].isHigh();
	}

	@Override
	public void shutdown() {
		gpio = GpioFactory.getInstance();
		gpio.shutdown();
	}

	// Convert simple GPIO number to the actual GPIO
	private void convert(int pinNum) {
		gpio = GpioFactory.getInstance();
		if (pins[pinNum] == null) {
			switch (pinNum) {
			// Pins for pi1
			case 0:
				pins[pinNum] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, "MyPin", PinState.LOW);
				pins[pinNum].setShutdownOptions(true, PinState.LOW);
				break;
			case 1:
				pins[pinNum] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "MyPin", PinState.LOW);
				pins[pinNum].setShutdownOptions(true, PinState.LOW);
				break;
			case 2:
				pins[pinNum] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "MyPin", PinState.LOW);
				pins[pinNum].setShutdownOptions(true, PinState.LOW);
				break;
			case 3:
				pins[pinNum] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "MyPin", PinState.LOW);
				pins[pinNum].setShutdownOptions(true, PinState.LOW);
				break;
			case 4:
				pins[pinNum] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "MyPin", PinState.LOW);
				pins[pinNum].setShutdownOptions(true, PinState.LOW);
				break;
			case 5:
				pins[pinNum] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "MyPin", PinState.LOW);
				pins[pinNum].setShutdownOptions(true, PinState.LOW);
				break;
			case 6:
				pins[pinNum] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06, "MyPin", PinState.LOW);
				pins[pinNum].setShutdownOptions(true, PinState.LOW);
				break;
			case 7:
				pins[pinNum] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_07, "MyPin", PinState.LOW);
				pins[pinNum].setShutdownOptions(true, PinState.LOW);
				break;
			case 8:
				pins[pinNum] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_08, "MyPin", PinState.LOW);
				pins[pinNum].setShutdownOptions(true, PinState.LOW);
				break;
			case 9:
				pins[pinNum] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_09, "MyPin", PinState.LOW);
				pins[pinNum].setShutdownOptions(true, PinState.LOW);
				break;
			case 10:
				pins[pinNum] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_10, "MyPin", PinState.LOW);
				pins[pinNum].setShutdownOptions(true, PinState.LOW);
				break;
			case 11:
				pins[pinNum] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_11, "MyPin", PinState.LOW);
				pins[pinNum].setShutdownOptions(true, PinState.LOW);
				break;
			case 12:
				pins[pinNum] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_12, "MyPin", PinState.LOW);
				pins[pinNum].setShutdownOptions(true, PinState.LOW);
				break;
			case 13:
				pins[pinNum] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_13, "MyPin", PinState.LOW);
				pins[pinNum].setShutdownOptions(true, PinState.LOW);
				break;
			case 14:
				pins[pinNum] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_14, "MyPin", PinState.LOW);
				pins[pinNum].setShutdownOptions(true, PinState.LOW);
				break;
			case 15:
				pins[pinNum] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_15, "MyPin", PinState.LOW);
				pins[pinNum].setShutdownOptions(true, PinState.LOW);
				break;
			case 16:
				pins[pinNum] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_16, "MyPin", PinState.LOW);
				pins[pinNum].setShutdownOptions(true, PinState.LOW);
				break;

			// Pins for pi2, will not through exeption for pi1
			case 17:
				pins[pinNum] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_17, "MyPin", PinState.LOW);
				pins[pinNum].setShutdownOptions(true, PinState.LOW);
				break;
			case 18:
				pins[pinNum] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_18, "MyPin", PinState.LOW);
				pins[pinNum].setShutdownOptions(true, PinState.LOW);
				break;
			case 19:
				pins[pinNum] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_19, "MyPin", PinState.LOW);
				pins[pinNum].setShutdownOptions(true, PinState.LOW);
				break;
			case 20:
				pins[pinNum] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_20, "MyPin", PinState.LOW);
				pins[pinNum].setShutdownOptions(true, PinState.LOW);
				break;
			case 21:
				pins[pinNum] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_21, "MyPin", PinState.LOW);
				pins[pinNum].setShutdownOptions(true, PinState.LOW);
				break;
			case 22:
				pins[pinNum] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_22, "MyPin", PinState.LOW);
				pins[pinNum].setShutdownOptions(true, PinState.LOW);
				break;
			case 23:
				pins[pinNum] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_23, "MyPin", PinState.LOW);
				pins[pinNum].setShutdownOptions(true, PinState.LOW);
				break;
			case 24:
				pins[pinNum] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_24, "MyPin", PinState.LOW);
				pins[pinNum].setShutdownOptions(true, PinState.LOW);
				break;
			case 25:
				pins[pinNum] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_25, "MyPin", PinState.LOW);
				pins[pinNum].setShutdownOptions(true, PinState.LOW);
				break;
			case 26:
				pins[pinNum] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_26, "MyPin", PinState.LOW);
				pins[pinNum].setShutdownOptions(true, PinState.LOW);
				break;
			case 27:
				pins[pinNum] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_27, "MyPin", PinState.LOW);
				pins[pinNum].setShutdownOptions(true, PinState.LOW);
				break;
			case 28:
				pins[pinNum] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_28, "MyPin", PinState.LOW);
				pins[pinNum].setShutdownOptions(true, PinState.LOW);
				break;
			case 29:
				pins[pinNum] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_29, "MyPin", PinState.LOW);
				pins[pinNum].setShutdownOptions(true, PinState.LOW);
				break;

			// Null if pin dosent exist
			default:
				pins[pinNum] = null;
			}
		}
	}

}
