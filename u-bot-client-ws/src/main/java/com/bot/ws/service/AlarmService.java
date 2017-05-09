package com.bot.ws.service;

import java.util.Date;

public interface AlarmService {

	public void setAlarm(Date date);
	
	public void cancelAlarm(Date date);
	
	public void modifyAlarm(Date date);
	
	
}
