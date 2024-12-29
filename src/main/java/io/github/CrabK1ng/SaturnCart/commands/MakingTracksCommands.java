package io.github.CrabK1ng.SaturnCart.commands;

import com.badlogic.gdx.math.Vector3;
import com.github.puzzle.game.commands.CommandManager;
import com.github.puzzle.game.commands.ServerCommandSource;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.networking.packets.MessagePacket;
import finalforeach.cosmicreach.networking.server.ServerIdentity;
import finalforeach.cosmicreach.networking.server.ServerSingletons;
import io.github.CrabK1ng.SaturnCart.GameManager;
import io.github.CrabK1ng.SaturnCart.RaceTrack;

public class MakingTracksCommands {
    public static void register() {
        LiteralArgumentBuilder<ServerCommandSource> setFinishlLne = CommandManager.literal("setfinishline");
        setFinishlLne.then(CommandManager.argument(ServerCommandSource.class, "1or2Pos", IntegerArgumentType.integer())
                .executes(context -> {
                    int oneOrTwo = IntegerArgumentType.getInteger(context, "1or2Pos");
                    Player player = context.getSource().getPlayer();
                    Vector3 playerPos = player.getEntity().getPosition();
                    RaceTrack track = GameManager.getPlayerSelectedTrack(context.getSource().getAccount().getUniqueId());
                    if (track != null){
                        if (oneOrTwo == 1){
                            track.setFinishLinePositionOne(playerPos);
                        } else if (oneOrTwo == 2) {
                            track.setFinishLinePositionTwo(playerPos);
                        }else {
                            MessagePacket messagepacket = new MessagePacket("use 1 or 2 for pos1 or pos2");
                            ServerIdentity serveridentity = ServerSingletons.getConnection(context.getSource().getPlayer());
                            messagepacket.setupAndSend(serveridentity);
                        }
                    }else{
                        MessagePacket messagepacket = new MessagePacket("Track is null use /selecttrack to select a track");
                        ServerIdentity serveridentity = ServerSingletons.getConnection(context.getSource().getPlayer());
                        messagepacket.setupAndSend(serveridentity);
                    }
                    return 0;
                }));
        CommandManager.DISPATCHER.register(setFinishlLne);

        LiteralArgumentBuilder<ServerCommandSource> setCheckpoinOne = CommandManager.literal("setcheckpoinone");
        setCheckpoinOne.then(CommandManager.argument(ServerCommandSource.class, "1or2Pos", IntegerArgumentType.integer())
                .executes(context -> {
                    int oneOrTwo = IntegerArgumentType.getInteger(context, "1or2Pos");
                    Player player = context.getSource().getPlayer();
                    Vector3 playerPos = player.getEntity().getPosition();
                    RaceTrack track = GameManager.getPlayerSelectedTrack(context.getSource().getAccount().getUniqueId());
                    if (track != null){
                        if (oneOrTwo == 1){
                            track.setCheckpoinOnePositionOne(playerPos);
                        } else if (oneOrTwo == 2) {
                            track.setCheckpoinOnePositionTwo(playerPos);
                        }else {
                            MessagePacket messagepacket = new MessagePacket("use 1 or 2 for pos1 or pos2");
                            ServerIdentity serveridentity = ServerSingletons.getConnection(context.getSource().getPlayer());
                            messagepacket.setupAndSend(serveridentity);
                        }
                    }else{
                        MessagePacket messagepacket = new MessagePacket("Track is null use /selecttrack to select a track");
                        ServerIdentity serveridentity = ServerSingletons.getConnection(context.getSource().getPlayer());
                        messagepacket.setupAndSend(serveridentity);
                    }
                    return 0;
                }));
        CommandManager.DISPATCHER.register(setCheckpoinOne);

        LiteralArgumentBuilder<ServerCommandSource> setCheckpoinTwo = CommandManager.literal("setcheckpointwo");
        setCheckpoinTwo.then(CommandManager.argument(ServerCommandSource.class, "1or2Pos", IntegerArgumentType.integer())
                .executes(context -> {
                    int oneOrTwo = IntegerArgumentType.getInteger(context, "1or2Pos");
                    Player player = context.getSource().getPlayer();
                    Vector3 playerPos = player.getEntity().getPosition();
                    RaceTrack track = GameManager.getPlayerSelectedTrack(context.getSource().getAccount().getUniqueId());
                    if (track != null){
                        if (oneOrTwo == 1){
                            track.setCheckpoinTwoPositionOne(playerPos);
                        } else if (oneOrTwo == 2) {
                            track.setCheckpoinTwoPositionTwo(playerPos);
                        }else {
                            MessagePacket messagepacket = new MessagePacket("use 1 or 2 for pos1 or pos2");
                            ServerIdentity serveridentity = ServerSingletons.getConnection(context.getSource().getPlayer());
                            messagepacket.setupAndSend(serveridentity);
                        }
                    }else{
                        MessagePacket messagepacket = new MessagePacket("Track is null use /selecttrack to select a track");
                        ServerIdentity serveridentity = ServerSingletons.getConnection(context.getSource().getPlayer());
                        messagepacket.setupAndSend(serveridentity);
                    }
                    return 0;
                }));
        CommandManager.DISPATCHER.register(setCheckpoinTwo);

        LiteralArgumentBuilder<ServerCommandSource> setAll = CommandManager.literal("setall");
        setAll.then(CommandManager.argument(ServerCommandSource.class, "1or2Pos", IntegerArgumentType.integer())
                .executes(context -> {
                    int oneOrTwo = IntegerArgumentType.getInteger(context, "1or2Pos");
                    Player player = context.getSource().getPlayer();
                    Vector3 playerPos = player.getEntity().getPosition();
                    RaceTrack track = GameManager.getPlayerSelectedTrack(context.getSource().getAccount().getUniqueId());
                    MessagePacket messagepacket = new MessagePacket("DO NOT USE THIS!!!!!!");
                    ServerIdentity serveridentity = ServerSingletons.getConnection(context.getSource().getPlayer());
                    messagepacket.setupAndSend(serveridentity);
                    if (track != null){
                        if (oneOrTwo == 1){
                            track.setFinishLinePositionOne(playerPos);
                            track.setCheckpoinOnePositionOne(playerPos);
                            track.setCheckpoinTwoPositionOne(playerPos);
                        } else if (oneOrTwo == 2) {
                            track.setFinishLinePositionTwo(playerPos);
                            track.setCheckpoinOnePositionTwo(playerPos);
                            track.setCheckpoinTwoPositionTwo(playerPos);
                        }else {
                            messagepacket = new MessagePacket("use 1 or 2 for pos1 or pos2");
                            serveridentity = ServerSingletons.getConnection(context.getSource().getPlayer());
                            messagepacket.setupAndSend(serveridentity);
                        }
                    }else{
                        messagepacket = new MessagePacket("Track is null use /selecttrack to select a track");
                        serveridentity = ServerSingletons.getConnection(context.getSource().getPlayer());
                        messagepacket.setupAndSend(serveridentity);
                    }
                    return 0;
                }));
        CommandManager.DISPATCHER.register(setAll);

        LiteralArgumentBuilder<ServerCommandSource> setlaps = CommandManager.literal("setlaps");
        setlaps.then(CommandManager.argument(ServerCommandSource.class, "Laps", IntegerArgumentType.integer())
                .executes(context -> {
                    int laps = IntegerArgumentType.getInteger(context, "Laps");
                    RaceTrack track = GameManager.getPlayerSelectedTrack(context.getSource().getAccount().getUniqueId());
                    if (track != null){
                        track.setLaps(laps);
                    }else{
                        MessagePacket messagepacket = new MessagePacket("Track is null use /selecttrack to select a track");
                        ServerIdentity serveridentity = ServerSingletons.getConnection(context.getSource().getPlayer());
                        messagepacket.setupAndSend(serveridentity);
                    }
                    return 0;
                }));
        CommandManager.DISPATCHER.register(setlaps);
    }
}
