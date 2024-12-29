package io.github.CrabK1ng.SaturnCart.mixins;

import com.badlogic.gdx.math.Vector3;
import finalforeach.cosmicreach.entities.Entity;
import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.networking.NetworkIdentity;
import finalforeach.cosmicreach.networking.packets.MessagePacket;
import finalforeach.cosmicreach.networking.packets.entities.PlayerPositionPacket;
import finalforeach.cosmicreach.networking.server.ServerIdentity;
import finalforeach.cosmicreach.networking.server.ServerSingletons;
import io.github.CrabK1ng.SaturnCart.Constants;
import io.github.CrabK1ng.SaturnCart.GameManager;
import io.github.CrabK1ng.SaturnCart.RaceTrack;
import io.github.CrabK1ng.SaturnCart.networking.IPlayer;
import io.github.CrabK1ng.SaturnCart.util.EntityUtils;
import io.netty.channel.ChannelHandlerContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerPositionPacket.class)
public class PlayerPositionPacketMixin {

    @Shadow public String playerUniqueId;

    @Shadow public Vector3 position;

    @Inject(method = "handle", at = @At("TAIL"))
    public void handle(NetworkIdentity identity, ChannelHandlerContext ctx, CallbackInfo ci) {
        Player p = identity.getPlayer();
        IPlayer player = (IPlayer) p;
        Entity playerEntity = p.getEntity();

        if (player.isRacing()) {
            RaceTrack track = GameManager.getPlayerSelectedTrack(p.getAccount().getUniqueId());
            // Checkpoint 1
            if (EntityUtils.isPlayerInArea(playerEntity, track.getCheckpoinOnePositions())
                    && !player.getcheckpoint1()) {
                player.setcheckpoint1(true);
                Constants.LOGGER.info("Checkpoint 1");

                // Checkpoint 2
            } else if (EntityUtils.isPlayerInArea(playerEntity, track.getCheckpoinTwoPositions())
                    && player.getcheckpoint1() && !player.getcheckpoint2()) {
                player.setcheckpoint2(true);
                Constants.LOGGER.info("Checkpoint 2");

                // Finish Line
            } else if (EntityUtils.isPlayerInArea(playerEntity, track.getFinishLinePositions())) {
                Constants.LOGGER.info("Finish Line");

                // If player went the wrong way
                if (player.getcheckpoint1() && !player.getcheckpoint2()) {

                    // TODO send to player only
                    MessagePacket messagepacket = new MessagePacket("You're going the wrong way!");
                    ServerIdentity serveridentity = ServerSingletons.getConnection(p);
                    messagepacket.setupAndSend(serveridentity);

                    player.setcheckpoint1(false);

                    // If player successfully completed a lap
                } else if (player.getcheckpoint1() && player.getcheckpoint2()) {
                    int lap = player.getLap();
                    player.setLap(++lap);
                    player.setcheckpoint1(false);
                    player.setcheckpoint2(false);

//                    for (UUID uuid : arena.getPlayers())
//                        gm.createScoreboard(Bukkit.getPlayer(uuid));

                    if (player.getLap() == track.getLaps()) {
                        MessagePacket messagepacket = new MessagePacket("Final Lap!");
                        ServerIdentity serveridentity = ServerSingletons.getConnection(p);
                        messagepacket.setupAndSend(serveridentity);
                    } else if (player.getLap() < track.getLaps()){
                        MessagePacket messagepacket = new MessagePacket("Lap " + player.getLap());
                        ServerIdentity serveridentity = ServerSingletons.getConnection(p);
                        messagepacket.setupAndSend(serveridentity);
                    }

                    if (player.getLap() > track.getLaps()) {

//                        arena.addScore(p);
//                        for (UUID uuid : arena.getPlayers()) {
//                            gm.createScoreboard(p);
//                            Bukkit.getPlayer(uuid)
//                                    .sendMessage(Commands.prefix() + p.getDisplayName() + " �efinished!");
//                        }
//
//                        p.getVehicle().remove();
//                        p.setGameMode(GameMode.SPECTATOR);

                        MessagePacket messagepacket = new MessagePacket("Finished!");
                        ServerIdentity serveridentity = ServerSingletons.getConnection(p);
                        messagepacket.setupAndSend(serveridentity);
                        Constants.LOGGER.info("Finished!");

                        if (!track.getFinished().contains(p)){
                            track.addFinished(p);
                        }
                        if (track.getplayers().size() == track.getFinished().size()){
                            track.stop();
                        }

//                        p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);


//                        if (!gm.endCountdown.containsKey(arena))
//                            endTimer(arena.getName());
//                        if (arena.getScoreboard().size() == arena.getPlayers().size()) {
//                            gm.endGame(arena.getName());
//                        }
                    }
                }
            }
        }
//        if (p.getGameMode() == GameMode.SPECTATOR) {
//            if (!Utils.playerInArea(arena.getCorner1(), arena.getCorner2(), p)) {
//                p.teleport(arena.getSpawns().get(0));
////					p.teleport(event.getFrom()); // Very buggy
//            }
//        }
    }
}
