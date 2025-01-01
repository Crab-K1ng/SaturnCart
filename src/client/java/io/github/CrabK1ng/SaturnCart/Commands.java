package io.github.CrabK1ng.SaturnCart;

import com.github.puzzle.game.commands.ClientCommandSource;
import com.github.puzzle.game.commands.CommandManager;
import com.github.puzzle.game.commands.ServerCommandSource;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.networking.packets.MessagePacket;
import finalforeach.cosmicreach.networking.server.ServerIdentity;
import finalforeach.cosmicreach.networking.server.ServerSingletons;
import io.github.CrabK1ng.SaturnCart.util.PositionParser;

public class Commands {
    public static void register() {

//        LiteralArgumentBuilder<ClientCommandSource> show = CommandManager.literal("show");
//        show.executes(context -> {
//            Player player = context.getSource().getPlayer();
//            RaceTrack track = GameManager.getPlayerTrack(player);
//            MessagePacket messagePacket = new MessagePacket("FinishLine " + track.getFinishLinePositions().toString() + " CheckpoinOne " + track.getCheckpoinOnePositions().toString() + "CheckpoinTwo " + track.getCheckpoinTwoPositions().toString());
//            ServerIdentity serveridentity = ServerSingletons.getConnection(player);
//            messagePacket.setupAndSend(serveridentity);
//            Constants.LOGGER.info("FinishLine " + track.getFinishLinePositions().toString() + " CheckpoinOne " + track.getCheckpoinOnePositions().toString() + "CheckpoinTwo " + track.getCheckpoinTwoPositions().toString());;
//
//            PositionParser parser = new PositionParser();
////            parser.parsePositions(input);
//            return 0;
//        });
//        CommandManager.DISPATCHER.register(show);
    }
}
