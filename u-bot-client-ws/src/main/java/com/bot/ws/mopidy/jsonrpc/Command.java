package com.bot.ws.mopidy.jsonrpc;

import java.io.IOException;

import com.google.gson.JsonElement;

public abstract class Command<T> extends JsonRPCCommand {

	private transient ResponseListener<T> responseListener;
	private transient ErrorListener errorListener;

	protected Command() {

	}

	public Command(String method, ResponseListener<T> responseListener, ErrorListener errorListener) {
		super(method);
		this.responseListener = responseListener;
		this.errorListener = errorListener;
	}

	public final void parseData(JsonElement data, boolean error) {
		Response<T> response;
		if (error) {
			response = parseError(data);
		} else {
			try {
				response = parseResponse(data);
			} catch (Exception e) {
				response = Response.error(e);
			}
		}

		if (response.hasError() && errorListener != null) {
			errorListener.onError(response.error);
		} else {
			responseListener.onResponse(response.response);
		}
	}

	protected abstract Response<T> parseResponse(JsonElement data);

	protected Response<T> parseError(JsonElement data) {
		return Response.error(new IOException("Unknown response"));
	}

	public String getId() {
		return id;
	}

	public interface ResponseListener<T> {
		void onResponse(T response);
	}

	public interface ErrorListener {
		void onError(Throwable throwable);
	}

	protected static class Response<T> {

		private final T response;
		private final Throwable error;

		private Response(T response, Throwable error) {
			this.response = response;
			this.error = error;
		}

		public static <T> Response<T> success(T response) {
			return new Response<>(response, null);
		}

		public static <T> Response<T> error(Throwable error) {
			return new Response<>(null, error);
		}

		public boolean hasError() {
			return error != null;
		}
	}
}
