package com.bot.ws.mopidy.jsonrpc;

import java.lang.reflect.Modifier;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

public abstract class JsonRPCCommand {
	@SerializedName("jsonrpc")
	private static final String version = "2.0";
	private transient static AtomicLong ID_COUNTER = new AtomicLong(0);
	@SerializedName("id")
	protected String id;
	@SerializedName("method")
	private String method;

	protected JsonRPCCommand() {

	}

	public JsonRPCCommand(String method) {
		this.method = method;
		this.id = UUID.randomUUID().toString();
	}

	public String toJson() {
		Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.TRANSIENT).create();
		String json = gson.toJson(this);
		return json;
	}

	@Override
	public String toString() {
		return id + " - " + method;
	}
}
