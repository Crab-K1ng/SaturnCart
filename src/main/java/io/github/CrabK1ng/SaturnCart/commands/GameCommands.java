package io.github.CrabK1ng.SaturnCart.commands;

import com.badlogic.gdx.math.Vector3;
import com.github.puzzle.game.commands.CommandManager;
import com.github.puzzle.game.commands.ServerCommandSource;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.networking.packets.MessagePacket;
import finalforeach.cosmicreach.networking.packets.sounds.PlaySound2DPacket;
import finalforeach.cosmicreach.networking.server.ServerIdentity;
import finalforeach.cosmicreach.networking.server.ServerSingletons;
import io.github.CrabK1ng.SaturnCart.GameManager;
import io.github.CrabK1ng.SaturnCart.RaceTrack;
import io.github.CrabK1ng.SaturnCart.util.MessageSend;

public class GameCommands {
    public static void register() {

        LiteralArgumentBuilder<ServerCommandSource> cmd = CommandManager.literal("start");
        cmd.executes(context -> {
            GameManager.start();
            return 0;
        });
        CommandManager.DISPATCHER.register(cmd);

        LiteralArgumentBuilder<ServerCommandSource> stop = CommandManager.literal("stop");
        stop.executes(context -> {
            RaceTrack track = GameManager.getCurrentTrack();
            if (track != null){
                track.stop();
            }
            GameManager.stop();
            return 0;
        });
        CommandManager.DISPATCHER.register(stop);

        LiteralArgumentBuilder<ServerCommandSource> play = CommandManager.literal("play");
        play.executes(context -> {
            PlaySound2DPacket playsound2dpacket = new PlaySound2DPacket("ice_racing:sounds/sfx/start.ogg", new Vector3(), 0.5F, 1, 0);
            ServerSingletons.SERVER.broadcastToAll(playsound2dpacket);
            return 0;
        });
        CommandManager.DISPATCHER.register(play);

        LiteralArgumentBuilder<ServerCommandSource> join = CommandManager.literal("join");
        join.executes(context -> {
            Player player = context.getSource().getPlayer();
            GameManager.joinPlayer(player);
            return 0;
        });
        CommandManager.DISPATCHER.register(join);
    }
}
