package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public final class Connected implements Connection {

    private final String name;
    private final TcpConnection tcpConnection;

    public Connected(TcpConnection tcpConn) {
        this.tcpConnection = tcpConn;
        this.name = "connected";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void write(String data) {
        System.out.println("Writing data to buffer: " + data);
        tcpConnection.addToBuffer(data);
    }

    @Override
    public void connect() {
        System.out.println("Error: Try to connect when connection already established");
    }

    @Override
    public void disconnect() {
        tcpConnection.setState(new Disconnected(tcpConnection));
        System.out.println("Disconnecting from " + tcpConnection.getAddress() + " on " + tcpConnection.getPort());
    }
}
// END
