package com.matthewperiut.claysoldiers;

import com.matthewperiut.claysoldiers.optional.RetroCommandsSupport;
import net.fabricmc.loader.api.FabricLoader;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.mod.InitEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;
import org.apache.logging.log4j.Logger;

public class ClaySoldiersMod {

    @Entrypoint.Logger
    public static Logger LOGGER = Null.get();

    @Entrypoint.Namespace
    public static Namespace MOD_ID = Null.get();

    @EventListener
    public void init(InitEvent event) {
        ClaySoldiersMod.LOGGER.info("Clay soldiers loaded as: " + MOD_ID);

        if (FabricLoader.getInstance().isModLoaded("retrocommands")) {
            RetroCommandsSupport.addEntities();
        }
    }
}
