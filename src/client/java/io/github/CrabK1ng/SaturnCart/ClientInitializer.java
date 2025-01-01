package io.github.CrabK1ng.SaturnCart;

import com.github.puzzle.core.loader.launch.provider.mod.entrypoint.impls.ClientModInitializer;
import io.github.CrabK1ng.SaturnCart.commands.Commands;

public class ClientInitializer implements ClientModInitializer {

    @Override
    public void onInit() {
        Constants.LOGGER.info("Hello From INIT");
//        Commands.register();
    }

}
