package exercise;

import exercise.connections.Connection;
import exercise.connections.Disconnected;

import java.util.ArrayList;
import java.util.List;

// BEGIN

public final class TcpConnection {

    private final String address;
    private final int port;

    private Connection state;
    private List<String> buffer;

    public TcpConnection(String tcpAddress, int tcpPort) {
        this.address = tcpAddress;
        this.port = tcpPort;
        this.state = new Disconnected(this);
        this.buffer = new ArrayList<>();
    }

    public void setState(Connection connState) {
        this.state = connState;
    }

    public String getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    public void write(String data) {
        state.write(data);
    }

    public void addToBuffer(String data) {
        buffer.add(data);
    }

    public void connect() {
        state.connect();
    }

    public void disconnect() {
        state.disconnect();
    }

    public String getCurrentState() {
        return state.getName();
    }
}

// END
