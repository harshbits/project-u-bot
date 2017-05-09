package com.bot.ws.mopidy.client;

import java.util.HashMap;
import java.util.Map;

import com.bot.ws.mopidy.jsonrpc.Command;
import com.bot.ws.mopidy.jsonrpc.Notification;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CommandHandler implements DataParser {
	private final Map<String, Command> commandMap = new HashMap<>();
	private DataSender dataSender;

	public void setDataSender(DataSender dataSender) {
		this.dataSender = dataSender;
	}

	public void send(Command command) {
		if (dataSender == null)
			return;
		commandMap.put(command.getId(), command);
		String data = command.toJson();
		dataSender.sendData(data);
	}

	public void send(Notification notification) {
		if (dataSender == null)
			return;
		
		String data = notification.toJson();
		dataSender.sendData(data);
	}

	public void cleanup() {
		commandMap.clear();
	}

	@Override
	public void onDataReceived(String data) {
		parseResponse(data);
	}

	public void parseResponse(String data) {
		JsonObject jsonObject = new JsonParser().parse(data).getAsJsonObject();
		if (jsonObject.has("event"))
			return;
		String id = jsonObject.get("id").getAsString();
		Command command = commandMap.get(id);
		if (command != null) {
			boolean hasError = jsonObject.has("error");
			JsonElement result = jsonObject.get(hasError ? "error" : "result");
			command.parseData(result, hasError);
		}
	}
}
