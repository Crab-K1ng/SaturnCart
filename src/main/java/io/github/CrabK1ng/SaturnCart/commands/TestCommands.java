package io.github.CrabK1ng.SaturnCart.commands;

import com.badlogic.gdx.math.Vector3;
import com.github.puzzle.game.commands.CommandManager;
import com.github.puzzle.game.commands.ServerCommandSource;
import com.github.puzzle.game.util.BlockUtil;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.blocks.BlockState;
import finalforeach.cosmicreach.blocks.MissingBlockStateResult;
import finalforeach.cosmicreach.networking.packets.MessagePacket;
import finalforeach.cosmicreach.networking.server.ServerIdentity;
import finalforeach.cosmicreach.networking.server.ServerSingletons;
import io.github.CrabK1ng.SaturnCart.GameManager;
import io.github.CrabK1ng.SaturnCart.RaceTrack;
import io.github.CrabK1ng.SaturnCart.util.Vector3Utils;

import java.util.List;

public class TestCommands {

    public static void register() {
        //TODO update this 
//        LiteralArgumentBuilder<ServerCommandSource> testf = CommandManager.literal("testf");
//        testf.executes(context -> {
//            if (context.getSource().hasOperator()){
//                RaceTrack track = GameManager.getPlayerSelectedTrack(context.getSource().getAccount().getUniqueId());
//                if (track == null){
//                    MessagePacket messagepacket = new MessagePacket("Track is null use /selecttrack to select a track");
//                    ServerIdentity serveridentity = ServerSingletons.getConnection(context.getSource().getPlayer());
//                    messagepacket.setupAndSend(serveridentity);
//                    return 0;
//                }
//                List<Vector3> finishLinePositions = track.getFinishLinePositions();
//                Vector3 pos1 = finishLinePositions.get(0);
//                Vector3 pos2 = finishLinePositions.get(1);
//
//                Vector3Utils.getAllInBox(pos1, pos2).forEach(pos ->
//                        BlockUtil.setBlockAt(context.getSource().getPlayer().getZone(), BlockState.getInstance("base:aluminium_panel[default]", MissingBlockStateResult.MISSING_OBJECT), (int) pos.x, (int) pos.y, (int) pos.z)
//                );
//
//                List<Vector3> checkpoinOnePositions = track.getCheckpoinOnePositions();
//                pos1 = checkpoinOnePositions.get(0);
//                pos2 = checkpoinOnePositions.get(1);
//
//                Vector3Utils.getAllInBox(pos1, pos2).forEach(pos ->
//                        BlockUtil.setBlockAt(context.getSource().getPlayer().getZone(), BlockState.getInstance("base:aluminium_panel[default]", MissingBlockStateResult.MISSING_OBJECT), (int) pos.x, (int) pos.y, (int) pos.z)
//                );
//
//                List<Vector3> checkpoinTwoPositions = track.getCheckpoinTwoPositions();
//                pos1 = checkpoinTwoPositions.get(0);
//                pos2 = checkpoinTwoPositions.get(1);
//
//                Vector3Utils.getAllInBox(pos1, pos2).forEach(pos ->
//                        BlockUtil.setBlockAt(context.getSource().getPlayer().getZone(), BlockState.getInstance("base:aluminium_panel[default]", MissingBlockStateResult.MISSING_OBJECT), (int) pos.x, (int) pos.y, (int) pos.z)
//                );
//            } else {
//                MessagePacket messagepacket = new MessagePacket("need to be op to run this command");
//                ServerIdentity serveridentity = ServerSingletons.getConnection(context.getSource().getPlayer());
//                messagepacket.setupAndSend(serveridentity);
//            }
//            return 0;
//        });
//        CommandManager.DISPATCHER.register(testf);
//
//        LiteralArgumentBuilder<ServerCommandSource> testa = CommandManager.literal("testa");
//        testa.executes(context -> {
//            if (context.getSource().hasOperator()){
//                RaceTrack track = GameManager.getPlayerSelectedTrack(context.getSource().getAccount().getUniqueId());
//                if (track == null){
//                    MessagePacket messagepacket = new MessagePacket("Track is null use /selecttrack to select a track");
//                    ServerIdentity serveridentity = ServerSingletons.getConnection(context.getSource().getPlayer());
//                    messagepacket.setupAndSend(serveridentity);
//                    return 0;
//                }
//
//                List<Vector3> finishLinePositions = track.getFinishLinePositions();
//                Vector3 pos1 = finishLinePositions.get(0);
//                Vector3 pos2 = finishLinePositions.get(1);
//
//
//                BlockUtil.setBlockAt(context.getSource().getPlayer().getZone(), BlockState.getInstance("base:aluminium_panel[default]", MissingBlockStateResult.MISSING_OBJECT), (int) pos1.x, (int) pos1.y, (int) pos1.z);
//                BlockUtil.setBlockAt(context.getSource().getPlayer().getZone(), BlockState.getInstance("base:metal_panel[default]", MissingBlockStateResult.MISSING_OBJECT), (int) pos2.x, (int) pos2.y, (int) pos2.z);
//
//                List<Vector3> checkpoinOnePositions = track.getCheckpoinOnePositions();
//                pos1 = checkpoinOnePositions.get(0);
//                pos2 = checkpoinOnePositions.get(1);
//
//                BlockUtil.setBlockAt(context.getSource().getPlayer().getZone(), BlockState.getInstance("base:aluminium_panel[default]", MissingBlockStateResult.MISSING_OBJECT), (int) pos1.x, (int) pos1.y, (int) pos1.z);
//                BlockUtil.setBlockAt(context.getSource().getPlayer().getZone(), BlockState.getInstance("base:metal_panel[default]", MissingBlockStateResult.MISSING_OBJECT), (int) pos2.x, (int) pos2.y, (int) pos2.z);
//
//                List<Vector3> checkpoinTwoPositions = track.getCheckpoinTwoPositions();
//                pos1 = checkpoinTwoPositions.get(0);
//                pos2 = checkpoinTwoPositions.get(1);
//
//                BlockUtil.setBlockAt(context.getSource().getPlayer().getZone(), BlockState.getInstance("base:aluminium_panel[default]", MissingBlockStateResult.MISSING_OBJECT), (int) pos1.x, (int) pos1.y, (int) pos1.z);
//                BlockUtil.setBlockAt(context.getSource().getPlayer().getZone(), BlockState.getInstance("base:metal_panel[default]", MissingBlockStateResult.MISSING_OBJECT), (int) pos2.x, (int) pos2.y, (int) pos2.z);
//            } else {
//                MessagePacket messagepacket = new MessagePacket("need to be op to run this command");
//                ServerIdentity serveridentity = ServerSingletons.getConnection(context.getSource().getPlayer());
//                messagepacket.setupAndSend(serveridentity);
//            }
//            return 0;
//        });
//        CommandManager.DISPATCHER.register(testa);
//
//        LiteralArgumentBuilder<ServerCommandSource> testd = CommandManager.literal("testd");
//        testd.executes(context -> {
//            if (context.getSource().hasOperator()){
//                RaceTrack track = GameManager.getPlayerSelectedTrack(context.getSource().getAccount().getUniqueId());
//                if (track == null){
//                    MessagePacket messagepacket = new MessagePacket("Track is null use /selecttrack to select a track");
//                    ServerIdentity serveridentity = ServerSingletons.getConnection(context.getSource().getPlayer());
//                    messagepacket.setupAndSend(serveridentity);
//                    return 0;
//                }
//
//                List<Vector3> finishLinePositions = track.getFinishLinePositions();
//                Vector3 pos1 = finishLinePositions.get(0);
//                Vector3 pos2 = finishLinePositions.get(1);
//
//                BlockUtil.setBlockAt(context.getSource().getPlayer().getZone(), BlockState.getInstance("base:aluminium_panel[default]", MissingBlockStateResult.MISSING_OBJECT), (int) pos1.x, (int) pos1.y, (int) pos1.z);
//                BlockUtil.setBlockAt(context.getSource().getPlayer().getZone(), BlockState.getInstance("base:metal_panel[default]", MissingBlockStateResult.MISSING_OBJECT), (int) pos2.x, (int) pos2.y, (int) pos2.z);
//
//                List<Vector3> checkpoinOnePositions = track.getCheckpoinOnePositions();
//                pos1 = checkpoinOnePositions.get(0);
//                pos2 = checkpoinOnePositions.get(1);
//
//                BlockUtil.setBlockAt(context.getSource().getPlayer().getZone(), BlockState.getInstance("base:aluminium_panel[default]", MissingBlockStateResult.MISSING_OBJECT), (int) pos1.x, (int) pos1.y, (int) pos1.z);
//                BlockUtil.setBlockAt(context.getSource().getPlayer().getZone(), BlockState.getInstance("base:metal_panel[default]", MissingBlockStateResult.MISSING_OBJECT), (int) pos2.x, (int) pos2.y, (int) pos2.z);
//
//                List<Vector3> checkpoinTwoPositions = track.getCheckpoinTwoPositions();
//                pos1 = checkpoinTwoPositions.get(0);
//                pos2 = checkpoinTwoPositions.get(1);
//
//                BlockUtil.setBlockAt(context.getSource().getPlayer().getZone(), BlockState.getInstance("base:aluminium_panel[default]", MissingBlockStateResult.MISSING_OBJECT), (int) pos1.x, (int) pos1.y, (int) pos1.z);
//                BlockUtil.setBlockAt(context.getSource().getPlayer().getZone(), BlockState.getInstance("base:metal_panel[default]", MissingBlockStateResult.MISSING_OBJECT), (int) pos2.x, (int) pos2.y, (int) pos2.z);
//
//            } else {
//                MessagePacket messagepacket = new MessagePacket("need to be op to run this command");
//                ServerIdentity serveridentity = ServerSingletons.getConnection(context.getSource().getPlayer());
//                messagepacket.setupAndSend(serveridentity);
//            }
//            return 0;
//        });
//        CommandManager.DISPATCHER.register(testd);
//
//        LiteralArgumentBuilder<ServerCommandSource> tests = CommandManager.literal("tests");
//        tests.executes(context -> {
//            if (context.getSource().hasOperator()){
//                RaceTrack track = GameManager.getPlayerSelectedTrack(context.getSource().getAccount().getUniqueId());
//                if (track == null){
//                    MessagePacket messagepacket = new MessagePacket("Track is null use /selecttrack to select a track");
//                    ServerIdentity serveridentity = ServerSingletons.getConnection(context.getSource().getPlayer());
//                    messagepacket.setupAndSend(serveridentity);
//                    return 0;
//                }
//
//                List<Vector3> finishLinePositions = track.getFinishLinePositions();
//                Vector3 pos1 = finishLinePositions.get(0);
//                Vector3 pos2 = finishLinePositions.get(1);
//
//                Vector3Utils.getAllInBox(pos1, pos2).forEach(pos ->
//                        BlockUtil.setBlockAt(context.getSource().getPlayer().getZone(), BlockState.getInstance("base:aluminium_panel[default]", MissingBlockStateResult.MISSING_OBJECT), (int) pos.x, (int) pos.y, (int) pos.z)
//                );
//
//                List<Vector3> checkpoinOnePositions = track.getCheckpoinOnePositions();
//                pos1 = checkpoinOnePositions.get(0);
//                pos2 = checkpoinOnePositions.get(1);
//
//                Vector3Utils.getAllInBox(pos1, pos2).forEach(pos ->
//                        BlockUtil.setBlockAt(context.getSource().getPlayer().getZone(), BlockState.getInstance("base:aluminium_panel[default]", MissingBlockStateResult.MISSING_OBJECT), (int) pos.x, (int) pos.y, (int) pos.z)
//                );
//
//
//                List<Vector3> checkpoinTwoPositions = track.getCheckpoinTwoPositions();
//                pos1 = checkpoinTwoPositions.get(0);
//                pos2 = checkpoinTwoPositions.get(1);
//
//                Vector3Utils.getAllInBox(pos1, pos2).forEach(pos ->
//                        BlockUtil.setBlockAt(context.getSource().getPlayer().getZone(), BlockState.getInstance("base:aluminium_panel[default]", MissingBlockStateResult.MISSING_OBJECT), (int) pos.x, (int) pos.y, (int) pos.z)
//                );
//            } else {
//                MessagePacket messagepacket = new MessagePacket("need to be op to run this command");
//                ServerIdentity serveridentity = ServerSingletons.getConnection(context.getSource().getPlayer());
//                messagepacket.setupAndSend(serveridentity);
//            }
//            return 0;
//        });
//        CommandManager.DISPATCHER.register(tests);
    }

}
