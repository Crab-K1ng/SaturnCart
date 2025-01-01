package io.github.CrabK1ng.SaturnCart.util;

import com.badlogic.gdx.math.Vector3;
import finalforeach.cosmicreach.accounts.Account;
import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.networking.packets.sounds.PlaySound2DPacket;
import finalforeach.cosmicreach.networking.server.ServerIdentity;
import finalforeach.cosmicreach.networking.server.ServerSingletons;
import io.github.CrabK1ng.SaturnCart.api.IPlayer;

public class SoundManager {
    public static String finalLap = "ice_racing:sounds/sfx/final_lap.ogg";
    public static String start = "ice_racing:sounds/sfx/start.ogg";

    public static void cyclePlayerSound(Player player){
        IPlayer iplayer = (IPlayer) player;
        iplayer.setSound(!iplayer.isSoundOn());
        String message = "disabled";
        if (iplayer.isSoundOn()){
            message = "enabled";
        }
        MessageSend.sendMessage("Sound " + message, player);
        
        if  (iplayer.isSoundOn()){
            MessageSend.sendMessage("Enabling sound requires the optional datamod, your game may crash if it is not installed", player);
        }
    }

    public static void playSound(String soundId){
        ServerSingletons.SERVER.authenticatedConnections.forEach(serverIdentity -> {
            Account account = serverIdentity.getAccount();
            IPlayer player = (IPlayer) account.getPlayer();
            if (player.isSoundOn()){
                PlaySound2DPacket playsound2dpacket = new PlaySound2DPacket(soundId, new Vector3(), 1, 1, 0);
                playsound2dpacket.setupAndSend(serverIdentity);
            }
        });
    }

    public static void playSound(String soundId, float volume){
        ServerSingletons.SERVER.authenticatedConnections.forEach(serverIdentity -> {
            Account account = serverIdentity.getAccount();
            IPlayer player = (IPlayer) account.getPlayer();
            if (player.isSoundOn()){
                PlaySound2DPacket playsound2dpacket = new PlaySound2DPacket(soundId, new Vector3(), volume, 1, 0);
                playsound2dpacket.setupAndSend(serverIdentity);
            }
        });
    }

    public static void playSound(String soundId, ServerIdentity serverIdentity){
        Account account = serverIdentity.getAccount();
        IPlayer player = (IPlayer) account.getPlayer();
        if (player.isSoundOn()){
            PlaySound2DPacket playsound2dpacket = new PlaySound2DPacket(soundId, new Vector3(), 1, 1, 0);
            playsound2dpacket.setupAndSend(serverIdentity);
        }
    }

    public static void playSound(String soundId, Player player){
        ServerIdentity serverIdentity = ServerSingletons.getConnection(player);
        IPlayer iPlayer = (IPlayer) player;
        if (iPlayer.isSoundOn()){
            PlaySound2DPacket playsound2dpacket = new PlaySound2DPacket(soundId, new Vector3(), 1, 1, 0);
            playsound2dpacket.setupAndSend(serverIdentity);
        }
    }

    public static void playSound(String soundId, ServerIdentity serverIdentity, int volume){
        Account account = serverIdentity.getAccount();
        IPlayer player = (IPlayer) account.getPlayer();
        if (player.isSoundOn()){
            PlaySound2DPacket playsound2dpacket = new PlaySound2DPacket(soundId, new Vector3(), volume, 1, 0);
            playsound2dpacket.setupAndSend(serverIdentity);
        }
    }

    public static void playSound(String soundId, Player player, float volume){
        ServerIdentity serverIdentity = ServerSingletons.getConnection(player);
        IPlayer iPlayer = (IPlayer) player;
        if (iPlayer.isSoundOn()){
            PlaySound2DPacket playsound2dpacket = new PlaySound2DPacket(soundId, new Vector3(), volume, 1, 0);
            playsound2dpacket.setupAndSend(serverIdentity);
        }
    }
}
