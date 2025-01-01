package io.github.CrabK1ng.SaturnCart.util;

import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.networking.packets.MessagePacket;
import finalforeach.cosmicreach.networking.server.ServerIdentity;
import finalforeach.cosmicreach.networking.server.ServerSingletons;

public class MessageSend { //MassageSend
    
    public static void sendMessage(String massage, Player player){
        MessagePacket messagepacket = new MessagePacket(massage);
        ServerIdentity serveridentity = ServerSingletons.getConnection(player);
        messagepacket.setupAndSend(serveridentity);
    }

    public static void sendMessage(String massage){
        MessagePacket messagepacket = new MessagePacket(massage);
        ServerSingletons.SERVER.broadcastToAll(messagepacket);
    }
}
