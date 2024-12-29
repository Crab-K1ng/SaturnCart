package io.github.CrabK1ng.SaturnCart.mixins;

import finalforeach.cosmicreach.io.ChunkLoader;
import finalforeach.cosmicreach.world.World;
import io.github.CrabK1ng.SaturnCart.RaceTrack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.FileNotFoundException;

@Mixin(ChunkLoader.class)
public class ChunkLoaderMixin {
    @Inject(method = "loadWorld", at = @At(value = "RETURN", ordinal = 1))
    private static void loadWorld(String worldFolderName, CallbackInfoReturnable<World> cir) {
        RaceTrack.loadAllTracks(worldFolderName);
    }
}
