package com.ynov.monopoly.packets;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientHandle implements Runnable {
    private final Socket socket;
    private NetworkStream reader;

    private final int MAX_MESSAGE_SIZE = 16 * 1024;

    public ClientHandle(Socket socket) {
        this.socket = socket;

        try {
            this.reader = new NetworkStream(this.socket.getInputStream());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println("[Server <-> Client] Client has connected");

        while (!this.socket.isClosed()) {
            byte[] content = readMessage();
            if (content == null)
                break;
            String s = new String(content, StandardCharsets.UTF_8);
            System.out.println("msg=" + s);
        }
        disconnect();
    }

    private void disconnect() {
        System.out.println("[Server <-> Client] -> ClientHandler has disconnected");

        try {
            this.reader.close();
            this.socket.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private byte[] readMessage() {
        byte[] header = new byte[4];
        if (!this.reader.readExactly(this.reader, header, 4))
            return null;

        int size = PacketUtils.readInt(header);
        if (size > 0 && size <= MAX_MESSAGE_SIZE) {
            byte[] content = new byte[size];
            if (!this.reader.readExactly(this.reader, content, size))
                return null;
            return content;
        }
        return null;
    }
}
