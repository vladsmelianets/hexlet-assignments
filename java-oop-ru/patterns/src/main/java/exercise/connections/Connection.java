package exercise.connections;

public interface Connection {
    // BEGIN
    void write(String data);

    void connect();

    void disconnect();

    String getName();

        // END
}
