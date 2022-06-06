package com.ynov.monopoly;

import com.ynov.monopoly.Case.CaseManager;
import com.ynov.monopoly.packets.ClientHandle;
import com.ynov.monopoly.packets.SocketManager;
import com.ynov.monopoly.player.PlayerManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Monopoly {
    private ServerSocket serverSocket;
    final SocketManager socketManager;
    final CaseManager caseManager;
    final PlayerManager playerManager;

    public Monopoly(){
        this.socketManager = new SocketManager();
        this.caseManager = new CaseManager();
        this.playerManager = new PlayerManager();
    }

    public void start() {
        System.out.println("Start Server..");

        try {
            this.serverSocket = new ServerSocket(3942);

            System.out.println("Listening on " + 3942);

            while (true) {
                Socket socket = this.serverSocket.accept();
                new Thread(new ClientHandle(socket)).start();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}

