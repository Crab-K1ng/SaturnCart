package io.github.CrabK1ng.SaturnCart.commands;

import com.github.puzzle.game.commands.CommandManager;
import com.github.puzzle.game.commands.ServerCommandSource;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.networking.packets.MessagePacket;
import finalforeach.cosmicreach.networking.server.ServerIdentity;
import finalforeach.cosmicreach.networking.server.ServerSingletons;
import io.github.CrabK1ng.SaturnCart.GameManager;
import io.github.CrabK1ng.SaturnCart.RaceTrack;
import io.github.CrabK1ng.SaturnCart.api.IPlayer;
import io.github.CrabK1ng.SaturnCart.util.MessageSend;


public class TrackCommands {
    private static final String notOpError = "need to be op to run this command";

    public static void register() {
        LiteralArgumentBuilder<ServerCommandSource> newTrack = CommandManager.literal("new");
        newTrack.then(CommandManager.argument(ServerCommandSource.class, "name", StringArgumentType.greedyString())
                .executes(context -> {
                    String name = StringArgumentType.getString(context, "name");
                    Player player = context.getSource().getPlayer();
                    if (context.getSource().hasOperator()){
                        if (name != null){
                            GameManager.newTrack(name);
                            MessageSend.sendMessage("new track: " + name, player);
                        }else{
                            MessageSend.sendMessage("set a name", player);
                        }
                    } else {
                        MessageSend.sendMessage(notOpError, player);
                    }
                    return 0;
                }));
        CommandManager.DISPATCHER.register(newTrack);

        LiteralArgumentBuilder<ServerCommandSource> saveAll = CommandManager.literal("saveall");
        saveAll.executes(context -> {
            Player player = context.getSource().getPlayer();
            if (context.getSource().hasOperator()){
                RaceTrack.saveAllTracks();
                MessageSend.sendMessage("saving", player);
            } else {
                MessageSend.sendMessage(notOpError, player);
            }
            return 0;
        });
        CommandManager.DISPATCHER.register(saveAll);

        LiteralArgumentBuilder<ServerCommandSource> save = CommandManager.literal("tsave");
        save.executes(context -> {
            Player player = context.getSource().getPlayer();
            RaceTrack track = GameManager.getPlayerTrack(player);
            if (context.getSource().hasOperator()){
                track.saveTrack();
                MessageSend.sendMessage("saving track: " + track.getName(), player);
            } else {
                MessageSend.sendMessage(notOpError, player);
            }
            return 0;
        });
        CommandManager.DISPATCHER.register(save);

        LiteralArgumentBuilder<ServerCommandSource> track = CommandManager.literal("track");
        track.then(CommandManager.argument(ServerCommandSource.class, "name", StringArgumentType.greedyString())
                .executes(context -> {
                    String name = StringArgumentType.getString(context, "name");
                    Player player = context.getSource().getPlayer();
                    if (context.getSource().hasOperator()){
                        if (name != null){
                            GameManager.setPlayerTrack(player, name);
                            MessageSend.sendMessage("selected track: " + name, player);
                        }else{
                            MessageSend.sendMessage("set a name", player);
                        }
                    } else {
                        MessageSend.sendMessage(notOpError, player);
                    }
                    return 0;
        }));
        CommandManager.DISPATCHER.register(track);

        LiteralArgumentBuilder<ServerCommandSource> editsigns = CommandManager.literal("editsigns");
        editsigns.executes(context -> {
            Player player = context.getSource().getPlayer();
            IPlayer iplayer = (IPlayer) player;
            if (context.getSource().hasOperator()){
                iplayer.setCanEditSigns(!iplayer.canEditSigns());
            } else {
                MessageSend.sendMessage(notOpError, player);
            }
            return 0;
        });
        CommandManager.DISPATCHER.register(editsigns);
    }


}
