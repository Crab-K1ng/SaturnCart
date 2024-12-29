package io.github.CrabK1ng.SaturnCart.commands;

import com.github.puzzle.game.commands.CommandManager;
import com.github.puzzle.game.commands.ServerCommandSource;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import finalforeach.cosmicreach.networking.packets.MessagePacket;
import finalforeach.cosmicreach.networking.server.ServerIdentity;
import finalforeach.cosmicreach.networking.server.ServerSingletons;
import io.github.CrabK1ng.SaturnCart.GameManager;
import io.github.CrabK1ng.SaturnCart.RaceTrack;


public class TrackCommands {

    public static void register() {
        LiteralArgumentBuilder<ServerCommandSource> newTrack = CommandManager.literal("new");
        newTrack.then(CommandManager.argument(ServerCommandSource.class, "name", StringArgumentType.greedyString())
                .executes(context -> {
                    String name = StringArgumentType.getString(context, "name");
                    if (context.getSource().hasOperator()){
                        if (name != null){
                            GameManager.newTrack(name);
                            MessagePacket messagepacket = new MessagePacket("new track:" + name);
                            ServerIdentity serveridentity = ServerSingletons.getConnection(context.getSource().getPlayer());
                            messagepacket.setupAndSend(serveridentity);
                        }else{
                            MessagePacket messagepacket = new MessagePacket("set a name");
                            ServerIdentity serveridentity = ServerSingletons.getConnection(context.getSource().getPlayer());
                            messagepacket.setupAndSend(serveridentity);
                        }
                    } else {
                        MessagePacket messagepacket = new MessagePacket("need to be op to run this command");
                        ServerIdentity serveridentity = ServerSingletons.getConnection(context.getSource().getPlayer());
                        messagepacket.setupAndSend(serveridentity);
                    }
                    return 0;
                }));
        CommandManager.DISPATCHER.register(newTrack);

        LiteralArgumentBuilder<ServerCommandSource> saveAll = CommandManager.literal("saveall");
        saveAll.executes(context -> {
            if (context.getSource().hasOperator()){
                MessagePacket messagepacket = new MessagePacket("saving");
                ServerIdentity serveridentity = ServerSingletons.getConnection(context.getSource().getPlayer());
                messagepacket.setupAndSend(serveridentity);
                RaceTrack.saveAllTracks();
            } else {
                MessagePacket messagepacket = new MessagePacket("need to be op to run this command");
                ServerIdentity serveridentity = ServerSingletons.getConnection(context.getSource().getPlayer());
                messagepacket.setupAndSend(serveridentity);
            }
            return 0;
        });
        CommandManager.DISPATCHER.register(saveAll);

        LiteralArgumentBuilder<ServerCommandSource> selectTrack = CommandManager.literal("selecttrack");
        selectTrack.then(CommandManager.argument(ServerCommandSource.class, "name", StringArgumentType.greedyString())
                .executes(context -> {
                    String name = StringArgumentType.getString(context, "name");
                    String uniqueId = context.getSource().getAccount().getUniqueId();
                    if (name != null){
                        GameManager.setSelectPlayerTrack(uniqueId, name);
                        MessagePacket messagepacket = new MessagePacket("selected track:" + name);
                        ServerIdentity serveridentity = ServerSingletons.getConnection(context.getSource().getPlayer());
                        messagepacket.setupAndSend(serveridentity);
                    }else{
                        MessagePacket messagepacket = new MessagePacket("set a name");
                        ServerIdentity serveridentity = ServerSingletons.getConnection(context.getSource().getPlayer());
                        messagepacket.setupAndSend(serveridentity);
                    }
                    return 0;
        }));
        CommandManager.DISPATCHER.register(selectTrack);
    }


}
