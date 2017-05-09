package com.bot.ws.mopidy.commands;

import java.util.List;

import com.bot.ws.config.model.mopidy.PlaybackState;
import com.bot.ws.config.model.mopidy.TrackListTrack;
import com.bot.ws.mopidy.jsonrpc.Command;
import com.bot.ws.mopidy.jsonrpc.Method;
import com.bot.ws.mopidy.jsonrpc.Notification;

public class PlayerCommands {
	private PlayerCommands() {

	}

	public static Notification play() {
		return new Notification(Method.PLAY);
	}

	public static Notification play(int trackListPosition) {
		return new PlayAtPositionNotification(trackListPosition);
	}

	public static Notification pause() {
		return new Notification(Method.PAUSE);
	}

	public static Notification stop() {
		return new Notification(Method.STOP);
	}

	public static Notification next() {
		return new Notification(Method.NEXT);
	}

	public static Notification previous() {
		return new Notification(Method.PREVIOUS);
	}

	public static Notification shuffle() {
		return new Notification(Method.SHUFFLE);
	}

	public static Notification clear() {
		return new Notification(Method.CLEAR);
	}

	public static Notification setVolume(final int volume) {
		return new SetVolumeNotification(volume);
	}

	public static Command getCurrentTrackListTrack(Command.ResponseListener<TrackListTrack> responseListener,
			Command.ErrorListener errorListener) {
		return new GetTrackListTrackCommand(responseListener, errorListener);
	}

	public static Command getVolume(Command.ResponseListener<Integer> responseListener,
			Command.ErrorListener errorListener) {
		return new GetVolumeCommand(responseListener, errorListener);
	}

	public static Command getTimePosition(Command.ResponseListener<Integer> responseListener,
			Command.ErrorListener errorListener) {
		return new GetTimePositionCommand(responseListener, errorListener);
	}

	public static Command getPlaybackState(Command.ResponseListener<PlaybackState> responseListener,
			Command.ErrorListener errorListener) {
		return new GetPlaybackStateCommand(responseListener, errorListener);
	}

	public static Command getTrackList(Command.ResponseListener<List<TrackListTrack>> responseListener,
			Command.ErrorListener errorListener) {
		return new GetTrackList(responseListener, errorListener);
	}

	public static Notification addUri(String uri) {
		return new AddUriNotification(uri);
	}

	public static Notification toggle(PlaybackState currentState) {
		if (currentState == null)
			return play();
		switch (currentState) {
		case PAUSED:
			return play();
		case PLAYING:
			return pause();
		case STOPPED:
			return play();
		default:
			return play();
		}
	}
}
