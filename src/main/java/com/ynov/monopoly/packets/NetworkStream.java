package com.ynov.monopoly.packets;

import java.io.IOException;
import java.io.InputStream;

public class NetworkStream extends InputStream {
    private final InputStream in;

    public NetworkStream(InputStream in) {
        this.in = in;
    }

    @Override
    public int read() throws IOException {
        return this.in.read();
    }

    public int readSafely(byte[] buffer, int offset, int size) {
        try {
            return (this.in.read(buffer, offset, size));
        } catch (IOException exception) {
            return 0;
        }
    }

    public boolean readExactly(InputStream reader, byte[] buffer, int amount) {
        int bytesRead = 0;
        while (bytesRead < amount) {
            int remaining = amount - bytesRead;
            int result = readSafely(buffer, bytesRead, remaining);

            if (result == 0)
                return false;
            bytesRead += result;
        }
        return true;
    }
}
