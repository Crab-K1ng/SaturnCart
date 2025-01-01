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
import io.github.CrabK1ng.SaturnCart.util.MessageSend;

public class MakingTracksCommands {
    private static final String notOpError = "need to be op to run this command";
    private static final String trackIsNullError = "Track is null use /track to select a track";
    private static final String use1or2Error = "use 1 or 2 for pos1 or pos2";

    public static void register() {
        //TODO update this
        LiteralArgumentBuilder<ServerCommandSource> setFinishlLne = CommandManager.literal("setfinishline");
        setFinishlLne.then(CommandManager.argument(ServerCommandSource.class, "1or2Pos", IntegerArgumentType.integer())
                .executes(context -> {
                    int oneOrTwo = IntegerArgumentType.getInteger(context, "1or2Pos");
                    Player player = context.getSource().getPlayer();
                    Vector3 playerPos = player.getEntity().getPosition();
                    RaceTrack track = GameManager.getPlayerTrack(player);
                    if (context.getSource().hasOperator()){
                        if (track != null){
                            if (oneOrTwo == 1){
                                track.setFinishLinePositionOne(playerPos);
                            } else if (oneOrTwo == 2) {
                                track.setFinishLinePositionTwo(playerPos);
                            }else {
                                MessageSend.sendMessage(use1or2Error, player);
                            }
                        }else{
                            MessageSend.sendMessage(trackIsNullError, player);
                        }
                    } else {
                        MessageSend.sendMessage(notOpError, player);
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
                    RaceTrack track = GameManager.getPlayerTrack(player);
                    if (context.getSource().hasOperator()){
                        if (track != null){
                            if (oneOrTwo == 1){
                                track.setCheckpoinOnePositionOne(playerPos);
                            } else if (oneOrTwo == 2) {
                                track.setCheckpoinOnePositionTwo(playerPos);
                            }else {
                                MessageSend.sendMessage(use1or2Error, player);
                            }
                        }else{
                            MessageSend.sendMessage(trackIsNullError, player);
                        }
                    } else {
                        MessageSend.sendMessage(notOpError, player);
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
                    RaceTrack track = GameManager.getPlayerTrack(player);
                    if (context.getSource().hasOperator()){
                        if (track != null){
                            if (oneOrTwo == 1){
                                track.setCheckpoinTwoPositionOne(playerPos);
                            } else if (oneOrTwo == 2) {
                                track.setCheckpoinTwoPositionTwo(playerPos);
                            }else {
                                MessageSend.sendMessage(use1or2Error, player);
                            }
                        }else{
                            MessageSend.sendMessage(trackIsNullError, player);
                        }
                    } else {
                        MessageSend.sendMessage(notOpError, player);
                    }
                    return 0;
                }));
        CommandManager.DISPATCHER.register(setCheckpoinTwo);

        LiteralArgumentBuilder<ServerCommandSource> setlaps = CommandManager.literal("setlaps");
        setlaps.then(CommandManager.argument(ServerCommandSource.class, "Laps", IntegerArgumentType.integer())
                .executes(context -> {
                    int laps = IntegerArgumentType.getInteger(context, "Laps");
                    Player player = context.getSource().getPlayer();
                    RaceTrack track = GameManager.getPlayerTrack(player);
                    if (context.getSource().hasOperator()){
                        if (track != null){
                            track.setLaps(laps);
                        }else{
                            MessageSend.sendMessage(trackIsNullError, player);
                        }
                    } else {
                        MessageSend.sendMessage(notOpError, player);
                    }
                    return 0;
                }));
        CommandManager.DISPATCHER.register(setlaps);

        LiteralArgumentBuilder<ServerCommandSource> setstart = CommandManager.literal("setstart");
        setstart.executes(context -> {
            Player player = context.getSource().getPlayer();
            Vector3 playerPos = player.getEntity().getPosition();
            RaceTrack track = GameManager.getPlayerTrack(player);
            if (context.getSource().hasOperator()){
                if (track != null){
                    track.setStartPositions(playerPos);
                }else{
                    MessageSend.sendMessage(trackIsNullError, player);
                }
            } else {
                MessageSend.sendMessage(notOpError, player);
            }
            return 0;
        });
        CommandManager.DISPATCHER.register(setstart);
    }
}
