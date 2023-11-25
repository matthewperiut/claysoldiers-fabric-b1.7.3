package com.matthewperiut.claysoldiers;

import com.matthewperiut.claysoldiers.optional.SPCSupport;
import net.fabricmc.loader.api.FabricLoader;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.mod.InitEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;
import org.apache.logging.log4j.Logger;

public class ClaySoldiersMod {

    @Entrypoint.Logger
    public static final Logger LOGGER = Null.get();

    @Entrypoint.Instance
    public static final ClaySoldiersMod INSTANCE = Null.get();

    @Entrypoint.Namespace
    public static final Namespace MODID = Null.get();

    @EventListener
    public void init(InitEvent event) {
        ClaySoldiersMod.LOGGER.info("Is this working? " + MODID);

        if (FabricLoader.getInstance().isModLoaded("spc")) {
            SPCSupport.addEntities();
        }
    }
}
