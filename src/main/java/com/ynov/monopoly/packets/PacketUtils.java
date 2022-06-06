package com.ynov.monopoly.packets;

public class PacketUtils {
    public static int readInt(byte[] bytes) {
        return (bytes[0] << 24) | (bytes[1] << 16) | (bytes[2] << 8) | bytes[3];
    }
}
