package io.github.CrabK1ng.SaturnCart;


import com.github.puzzle.core.loader.provider.mod.entrypoint.impls.PreModInitializer;

public class SaturnCartPreinit implements PreModInitializer {

    @Override
    public void onPreInit() {
        Constants.LOGGER.info("Hello From PRE-INIT");
    }
}