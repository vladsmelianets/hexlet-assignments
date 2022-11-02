package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public final class Disconnected implements Connection {

    private final String name;
    private final TcpConnection tcpConnection;

    public Disconnected(TcpConnection tcpConn) {
        this.tcpConnection = tcpConn;
        this.name = "disconnected";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void write(String data) {
        System.out.println("Error: Try to write to disconnected connection");
    }

    @Override
    public void connect() {
        System.out.println("Connecting to " + tcpConnection.getAddress() + " on " + tcpConnection.getPort());
        tcpConnection.setState(new Connected(tcpConnection));
    }

    @Override
    public void disconnect() {
        System.out.println("Error: Try to disconnect when connection disconnected");
    }
}
// END
