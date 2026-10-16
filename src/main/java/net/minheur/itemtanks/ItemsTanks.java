package net.minheur.itemtanks;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minheur.itemtanks.file.ConfigHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ItemsTanks implements ModInitializer {

    public static final String MODID = "itemsanks";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    @Override
    public void onInitialize() {
        ConfigHandler.load();

        ServerLifecycleEvents.BEFORE_SAVE.register((server, flush, force) -> {
            ConfigHandler.save();
        });

        ServerLifecycleEvents.SERVER_STARTED.register(TankHandler::loadTanks);
        ServerLifecycleEvents.SERVER_STOPPING.register(TankHandler::unloadTanks);

        TankCommands.register();
    }
}
