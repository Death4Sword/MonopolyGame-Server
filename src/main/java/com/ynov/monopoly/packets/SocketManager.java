package com.ynov.monopoly.packets;

import java.io.IOException;
import java.net.ServerSocket;

public class SocketManager {
    private final int port = 8000;
    private ServerSocket listener;

    public SocketManager(){
        try {
            this.listener = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            java.net.Socket socketClient = listener.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
