package com.bot.ws.mopidy.client;

import com.bot.ws.config.model.mopidy.PlaybackState;
import com.bot.ws.config.model.mopidy.TrackListTrack;
import com.bot.ws.mopidy.commands.PlayerCommands;
import com.bot.ws.mopidy.events.EventListener;
import com.bot.ws.mopidy.jsonrpc.Command;
import com.bot.ws.mopidy.jsonrpc.Notification;

public class Mopidy implements MopidyPlayerController, ConnectionChangedListener, MopidyState.UpdateCompletedListener {
    private final CommandHandler commandHandler;
    private final EventParser eventParser;
    private final MopidyState state;
    private final MopidySocket socket;

    private ConnectionChangedListener connectionChangedListener;
    private boolean isConnected = false;

    public Mopidy(MopidySocket mopidySocket) {
        this.commandHandler = new CommandHandler();
        this.eventParser = new EventParser();
        this.state = new MopidyState();
        this.socket = mopidySocket;

        this.state.setUpdateCompletedListener(this);
        this.socket.addDataParser(eventParser);
        this.socket.addDataParser(commandHandler);
        this.socket.addConnectionChangedListener(this);
        this.commandHandler.setDataSender(socket);
        this.eventParser.addEventListener(state);
    }

    public void setOnConnectionChangedListener(ConnectionChangedListener listener) {
        this.connectionChangedListener = listener;
    }

    public void addErrorListener(ErrorListener errorListener) {
        this.socket.addErrorListener(errorListener);
    }

    public void removeErrorListener(ErrorListener errorListener) {
        this.socket.removeErrorListener(errorListener);
    }

    public void addEventListener(EventListener eventListener) {
        this.eventParser.addEventListener(eventListener);
    }

    public void removeEventListener(EventListener eventListener) {
        this.eventParser.removeEventListener(eventListener);
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void connect() {
        socket.connect();
    }

    public void disconnect() {
        socket.disconnect();
    }

    private void send(Command command) {
        commandHandler.send(command);
    }

    private void send(Notification notification) {
        commandHandler.send(notification);
    }

    @Override
    public void toggle() {
        send(PlayerCommands.toggle(state.getPlaybackState()));
    }

    @Override
    public void stop() {
        send(PlayerCommands.stop());
        state.setPlaybackState(PlaybackState.STOPPED);
    }

    @Override
    public void play() {
        send(PlayerCommands.play());
        state.setPlaybackState(PlaybackState.PLAYING);
    }

    @Override
    public void pause() {
        send(PlayerCommands.pause());
        state.setPlaybackState(PlaybackState.PAUSED);
    }

    @Override
    public void next() {
        send(PlayerCommands.next());
        state.setPlaybackState(PlaybackState.PLAYING);
    }

    @Override
    public void previous() {
        send(PlayerCommands.previous());
        state.setPlaybackState(PlaybackState.PLAYING);
    }

    @Override
    public void shuffle() {
        send(PlayerCommands.shuffle());
    }

    @Override
    public void clear() {
        send(PlayerCommands.clear());
    }

    @Override
    public void addUri(String uri) {
        send(PlayerCommands.addUri(uri));
    }

    @Override
    public TrackListTrack getCurrentTrack() {
        return state.getTrackListTrack();
    }

    @Override
    public int getVolume() {
        return state.getVolume();
    }

    @Override
    public void setVolume(int volume) {
        send(PlayerCommands.setVolume(volume));
    }

    @Override
    public PlaybackState getPlaybackState() {
        return state.getPlaybackState();
    }

    @Override
    public void onConnected() {
        isConnected = true;
        send(PlayerCommands.getCurrentTrackListTrack(state.getTrackResponseListener(), null));
        send(PlayerCommands.getVolume(state.getVolumeResponseListener(), null));
        send(PlayerCommands.getPlaybackState(state.getPlaybackStateResponseListener(), null));
        if (connectionChangedListener != null) connectionChangedListener.onConnected();
    }

    @Override
    public void onDisconnected() {
        isConnected = false;
        state.reset();
        if (connectionChangedListener != null) connectionChangedListener.onDisconnected();
    }

    @Override
    public void onUpdateCompleted() {
        if (connectionChangedListener != null) connectionChangedListener.onConnected();
    }
}
