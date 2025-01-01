package io.github.CrabK1ng.SaturnCart;

import com.github.puzzle.core.loader.provider.mod.entrypoint.impls.ModInitializer;
import com.github.puzzle.game.PuzzleRegistries;
import com.github.puzzle.game.events.OnPacketRecieveIntercept;
import finalforeach.cosmicreach.networking.GamePacket;
import io.github.CrabK1ng.SaturnCart.commands.*;
import meteordevelopment.orbit.EventHandler;

public class SaturnCart implements ModInitializer {

    @Override
    public void onInit() {
        PuzzleRegistries.EVENT_BUS.subscribe(this);
        GameCommands.register();
        TrackCommands.register();
        TestCommands.register();
        MakingTracksCommands.register();
        Commands.register();
    }

}
