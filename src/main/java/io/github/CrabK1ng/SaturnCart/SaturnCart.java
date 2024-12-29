package io.github.CrabK1ng.SaturnCart;

import com.github.puzzle.core.loader.provider.mod.entrypoint.impls.ModInitializer;
import com.github.puzzle.game.PuzzleRegistries;
import io.github.CrabK1ng.SaturnCart.commands.Commands;
import io.github.CrabK1ng.SaturnCart.commands.MakingTracksCommands;
import io.github.CrabK1ng.SaturnCart.commands.TestCommands;
import io.github.CrabK1ng.SaturnCart.commands.TrackCommands;

public class SaturnCart implements ModInitializer {

    @Override
    public void onInit() {
        PuzzleRegistries.EVENT_BUS.subscribe(this);

        Commands.register();
        TrackCommands.register();
        TestCommands.register();
        MakingTracksCommands.register();

    }
}
