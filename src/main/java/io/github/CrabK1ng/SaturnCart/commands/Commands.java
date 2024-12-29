package io.github.CrabK1ng.SaturnCart.commands;

import com.github.puzzle.game.commands.CommandManager;
import com.github.puzzle.game.commands.ServerCommandSource;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.networking.packets.MessagePacket;
import finalforeach.cosmicreach.networking.server.ServerIdentity;
import finalforeach.cosmicreach.networking.server.ServerSingletons;
import io.github.CrabK1ng.SaturnCart.GameManager;
import io.github.CrabK1ng.SaturnCart.RaceTrack;
import io.github.CrabK1ng.SaturnCart.networking.IPlayer;

public class Commands {

    public static void register() {
        LiteralArgumentBuilder<ServerCommandSource> cmd = CommandManager.literal("start");
        cmd.executes(context -> {
            RaceTrack track = GameManager.getPlayerSelectedTrack(context.getSource().getAccount().getUniqueId());
            if (track != null){
                track.start();
            }else{
                MessagePacket messagepacket = new MessagePacket("Track is null use /selecttrack to select a track");
                ServerIdentity serveridentity = ServerSingletons.getConnection(context.getSource().getPlayer());
                messagepacket.setupAndSend(serveridentity);
            }
            return 0;
        });
        CommandManager.DISPATCHER.register(cmd);

        LiteralArgumentBuilder<ServerCommandSource> stop = CommandManager.literal("stop");
        stop.executes(context -> {
            RaceTrack track = GameManager.getPlayerSelectedTrack(context.getSource().getAccount().getUniqueId());
            if (track != null){
                track.stop();
            }else{
                MessagePacket messagepacket = new MessagePacket("Track is null use /selecttrack to select a track");
                ServerIdentity serveridentity = ServerSingletons.getConnection(context.getSource().getPlayer());
                messagepacket.setupAndSend(serveridentity);
            }
            return 0;
        });
        CommandManager.DISPATCHER.register(stop);

        LiteralArgumentBuilder<ServerCommandSource> r = CommandManager.literal("r");
        r.executes(context -> {
            RaceTrack track = GameManager.getSelectedTrack();
            for (Player player : context.getSource().getWorld().players){
                IPlayer iPlayer = ((IPlayer) player);
                iPlayer.setRacing(false);
                iPlayer.setcheckpoint1(false);
                iPlayer.setcheckpoint2(false);
                iPlayer.setLap(0);

            }
            track.start = true;
            return 0;
        });
        CommandManager.DISPATCHER.register(r);
    }

}
