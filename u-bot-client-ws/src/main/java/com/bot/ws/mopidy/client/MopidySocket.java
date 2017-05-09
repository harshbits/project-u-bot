package com.bot.ws.mopidy.client;

import java.util.ArrayList;
import java.util.List;


public abstract class MopidySocket implements DataSender {

    private final List<DataParser> dataParsers = new ArrayList<>();
    private final List<ConnectionChangedListener> connectionChangedListeners = new ArrayList<>();
    private final List<ErrorListener> errorListeners = new ArrayList<>();

    protected abstract void connect();

    protected abstract void disconnect();

    @Override
    public abstract void sendData(String data);

    public final void addDataParser(DataParser dataParser) {
        dataParsers.add(dataParser);
    }

    public final void removeDataParser(DataParser dataParser) {
        dataParsers.remove(dataParser);
    }

    public final void addConnectionChangedListener(ConnectionChangedListener connectionChangedListener) {
        this.connectionChangedListeners.add(connectionChangedListener);
    }

    public final void removeConnectionChangedListener(ConnectionChangedListener connectionChangedListener) {
        this.connectionChangedListeners.remove(connectionChangedListener);
    }

    public final void addErrorListener(ErrorListener errorListener) {
        this.errorListeners.add(errorListener);
    }

    public final void removeErrorListener(ErrorListener errorListener) {
        this.errorListeners.remove(errorListener);
    }

    protected final void onConnected() {
        for (ConnectionChangedListener listener : connectionChangedListeners) {
            listener.onConnected();
        }
    }

    protected final void onDisconnected(boolean error) {
        for (ConnectionChangedListener listener : connectionChangedListeners) {
            listener.onDisconnected();
        }
    }

    protected final void onDataReceived(String data) {
        for (DataParser dataParser : dataParsers) {
            dataParser.onDataReceived(data);
        }
    }

    protected final void onError(Exception exception) {
        for (ErrorListener listener : errorListeners) {
            listener.onError(exception);
        }
    }
}
